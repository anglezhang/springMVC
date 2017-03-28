package com.zoomoor.admin.action;

import static com.zoomoor.admin.service.SysAuthenticationSvc.AUTH_KEY;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.octo.captcha.service.CaptchaServiceException;
import com.octo.captcha.service.image.ImageCaptchaService;
import com.zoomoor.admin.entity.SysAuthentication;
import com.zoomoor.admin.entity.SysUser;
import com.zoomoor.admin.service.SysAuthenticationSvc;
import com.zoomoor.admin.service.SysLogSvc;
import com.zoomoor.admin.service.SysUserSvc;
import com.zoomoor.common.security.BadCredentialsException;
import com.zoomoor.common.security.DisabledException;
import com.zoomoor.common.security.UsernameNotFoundException;
import com.zoomoor.common.util.MyFileUtils;
import com.zoomoor.common.web.CookieUtils;
import com.zoomoor.common.web.RequestUtils;
import com.zoomoor.common.web.session.SessionProvider;
import com.zoomoor.core.web.WebErrors;

@Controller
public class SysLoginAct {
	private static final Logger log = LoggerFactory.getLogger(SysLoginAct.class);
	
	public static final String COOKIE_ERROR_REMAINING = "_error_remaining";
	public static final String PROCESS_URL = "processUrl";
	public static final String RETURN_URL = "returnUrl";
	public static final String MESSAGE = "message";
	
	
	@RequestMapping(value = "/login.do", method = RequestMethod.GET)
	public String input(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
		String processUrl = RequestUtils.getQueryParam(request, PROCESS_URL);
		String returnUrl = RequestUtils.getQueryParam(request, RETURN_URL);
		String message = RequestUtils.getQueryParam(request, MESSAGE);
		String authId = (String) session.getAttribute(request, AUTH_KEY);
		if (authId != null) {
			// 存在认证ID
			SysAuthentication auth = sysAuthenticationSvc.retrieve(authId);
			// 存在认证信息，且未过期
			if (auth != null) {
				String view = getView(processUrl, returnUrl, auth.getAuthenticationId());
				if (view != null) {
					return view;
				} else {
					model.addAttribute("auth", auth);
					return "login";
				}
			}
		}
		writeCookieErrorRemaining(null, request, response, model);
		if (!StringUtils.isBlank(processUrl)) {
			model.addAttribute(PROCESS_URL, processUrl);
		}
		if (!StringUtils.isBlank(returnUrl)) {
			model.addAttribute(RETURN_URL, returnUrl);
		}
		if (!StringUtils.isBlank(message)) {
			model.addAttribute(MESSAGE, message);
		}
		return "login";
	}
	
	@RequestMapping(value = "/login.do", method = RequestMethod.POST)
	public String submit(String username, String password, String captcha, String processUrl, String returnUrl, String message, HttpServletRequest request, HttpServletResponse response, ModelMap model) {
		Integer errorRemaining = sysUserSvc.errorRemaining(username);
		WebErrors errors = validateSubmit(username, password, captcha, errorRemaining, request, response);
		if (!errors.hasErrors()) {
			try {
				String ip = RequestUtils.getIpAddr(request);
				SysAuthentication auth = sysAuthenticationSvc.login(username, password, ip, request, response, session);
				SysUser user = sysUserSvc.get(auth.getUserid());
				if (user.getIsDisabled() != 0) {
					// 如果已经禁用，则退出登录。
					sysAuthenticationSvc.deleteById(auth.getAuthenticationId());
					session.logout(request, response);
					throw new DisabledException("user disabled");
				}
				removeCookieErrorRemaining(request, response);
				String view = getView(processUrl, returnUrl, auth.getAuthenticationId());
				sysLogSvc.loginSuccess(request, user);
				if (view != null) {
					return view;
				} else {
					return "redirect:index.do";
				}
			} catch (UsernameNotFoundException e) {
				errors.addErrorString(e.getMessage());
				sysLogSvc.loginFailure(request, "username=" + username + ";password=" + password);
			} catch (BadCredentialsException e) {
				errors.addErrorString(e.getMessage());
				sysLogSvc.loginFailure(request, "username=" + username + ";password=" + password);
			} catch (DisabledException e) {
				errors.addErrorString(e.getMessage());
				sysLogSvc.loginFailure(request, "username=" + username + ";password=" + password);
			}
		}
		// 登录失败
		//writeCookieErrorRemaining(errorRemaining, request, response, model);
		errors.toModel(model);
		if (!StringUtils.isBlank(processUrl)) {
			model.addAttribute(PROCESS_URL, processUrl);
		}
		if (!StringUtils.isBlank(returnUrl)) {
			model.addAttribute(RETURN_URL, returnUrl);
		}
		if (!StringUtils.isBlank(message)) {
			model.addAttribute(MESSAGE, message);
		}
		return "login";
	}
	
	@RequestMapping(value = "/logout.do")
	public String logout(HttpServletRequest request, HttpServletResponse response) {
		String authId = (String) session.getAttribute(request, AUTH_KEY);
		if (authId != null) {
			sysAuthenticationSvc.delete(authId);
			session.logout(request, response);
		}
		return "redirect:login.do";
	}
	
	private void writeCookieErrorRemaining(Integer userErrorRemaining,
			HttpServletRequest request, HttpServletResponse response,
			ModelMap model) {
		// 所有访问的页面都需要写一个cookie，这样可以判断已经登录了几次。
		Integer errorRemaining = getCookieErrorRemaining(request, response);
		
		Integer errorInterval = Integer.parseInt(MyFileUtils.getValueByPropertyName("config", "login_error_interval"));
		if (userErrorRemaining != null && (errorRemaining == null || userErrorRemaining < errorRemaining)) {
			errorRemaining = userErrorRemaining;
		}
		int maxErrorTimes = Integer.parseInt(MyFileUtils.getValueByPropertyName("config", "login_error_times"));
		if (errorRemaining == null || errorRemaining > maxErrorTimes) {
			errorRemaining = maxErrorTimes;
		} else if (errorRemaining <= 0) {
			errorRemaining = 0;
		} else {
			errorRemaining--;
		}
		model.addAttribute("errorRemaining", errorRemaining);
		CookieUtils.addCookie(request, response, COOKIE_ERROR_REMAINING, errorRemaining.toString(), errorInterval * 60, null);
	}
	
	private void removeCookieErrorRemaining(HttpServletRequest request, HttpServletResponse response) {
		CookieUtils.cancleCookie(request, response, COOKIE_ERROR_REMAINING, null);
	}

	private Integer getCookieErrorRemaining(HttpServletRequest request, HttpServletResponse response) {
		Cookie cookie = CookieUtils.getCookie(request, COOKIE_ERROR_REMAINING);
		if (cookie != null) {
			String value = cookie.getValue();
			if (NumberUtils.isDigits(value)) {
				return Integer.parseInt(value);
			}
		}
		return null;
	}
	
	public  WebErrors validateSubmit(String username, String password,
			String captcha, Integer errorRemaining, HttpServletRequest request,
			HttpServletResponse response) {
		WebErrors errors = WebErrors.create(request);
		if (errors.ifOutOfLength(username, "username", 1, 100)) {
			return errors;
		}
		if (errors.ifOutOfLength(password, "password", 1, 32)) {
			return errors;
		}
		// 如果输入了验证码，那么必须验证；如果没有输入验证码，则根据当前用户判断是否需要验证码。
		if (!StringUtils.isBlank(captcha)
				|| (errorRemaining != null && errorRemaining < 0)) {
			if (errors.ifBlank(captcha, "captcha", 100)) {
				return errors;
			}
			try {
				if (!imageCaptchaService.validateResponseForID(session.getSessionId(request, response), captcha)) {
					//errors.addErrorCode("error.invalidCaptcha");
					errors.addErrorString("验证码不正确");
					return errors;
				}
			} catch (CaptchaServiceException e) {
				//errors.addErrorCode("error.exceptionCaptcha");
				errors.addErrorString("验证码不正确");
				log.warn("", e);
				return errors;
			}
		}
		return errors;
	}
	
	/**
	 * 获得地址
	 * 
	 * @param processUrl
	 * @param returnUrl
	 * @param authId
	 * @param defaultUrl
	 * @return
	 */
	private String getView(String processUrl, String returnUrl, String authId) {
		if (!StringUtils.isBlank(processUrl)) {
			StringBuilder sb = new StringBuilder("redirect:");
			sb.append(processUrl).append("?").append(AUTH_KEY).append("=").append(authId);
			if (!StringUtils.isBlank(returnUrl)) {
				sb.append("&").append(RETURN_URL).append("=").append(returnUrl);
			}
			return sb.toString();
		} else if (!StringUtils.isBlank(returnUrl)) {
			StringBuilder sb = new StringBuilder("redirect:");
			sb.append(returnUrl);
			return sb.toString();
		} else {
			return null;
		}
	}
	
	@Autowired
	private SysLogSvc sysLogSvc;
	@Autowired
	private SysUserSvc sysUserSvc;
	@Autowired
	private SysAuthenticationSvc sysAuthenticationSvc;
	@Autowired
	private SessionProvider session;
	@Autowired
	private ImageCaptchaService imageCaptchaService;
}
