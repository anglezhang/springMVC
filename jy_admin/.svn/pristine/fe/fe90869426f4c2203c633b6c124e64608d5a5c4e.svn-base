package com.zoomoor.admin.action;


import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.octo.captcha.service.image.ImageCaptchaService;
import com.zoomoor.common.security.BadCredentialsException;
import com.zoomoor.common.security.encoder.PwdEncoder;
import com.zoomoor.common.web.session.SessionProvider;
import com.zoomoor.core.web.WebErrors;
import com.zoomoor.jy.entity.CustomerFixaddr;
import com.zoomoor.jy.service.CustomerFixaddrSvc;

@Controller
public class Sys4sAct {
	public static final String USER_4S = "user4s";
	@Autowired
	private CustomerFixaddrSvc addrSvc;
	@Resource
	private PwdEncoder pwdEncoder;
	@Autowired
	private SessionProvider session;
	@Autowired
	private ImageCaptchaService imageCaptchaService;

	/**
	 * @Title: Login4s
	 * @Description: 4s店登录
	 * @return String
	 * @throws
	 */
	@RequestMapping("/login4s.html")
	public String Login4s(HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
		CustomerFixaddr cfa = (CustomerFixaddr) request.getSession().getAttribute(USER_4S);
		List<CustomerFixaddr> addrList = new ArrayList<CustomerFixaddr>();
		if (cfa == null) {
			addrList = addrSvc.getList("del", false);
			model.addAttribute("addrList", addrList);
			return "login4s";
		} else {
			return "redirect:index4s.html";
		}
	}

	@RequestMapping("/index4s.html")
	public String index4s(HttpServletRequest request,
			HttpServletResponse response, ModelMap model, Integer addrId,
			String password,String captcha )throws BadCredentialsException {
			CustomerFixaddr cfa = (CustomerFixaddr) request.getSession().getAttribute(USER_4S);
			Integer errorCount=(Integer) request.getSession().getAttribute("errorCount");
		if(cfa!=null){
			return "/fourshop/fourshop";
		}else{
			//错误次数大于1时验证码出现
			if(errorCount!=null&&errorCount>0){
				if(captcha!=null){
					if(!imageCaptchaService.validateResponseForID(session.getSessionId(request, response), captcha)){
						setErrorsBy4s(request,model,"验证码错误",0);
						return "login4s";
					}
					
				}else{
					setErrorsBy4s(request,model,"验证码错误",0);
					return "login4s";
				}
			}
			if (addrId != null && password != null) {
				String[] prom = { "addrId", "password", "del" };
				Object[] objetm = { addrId, pwdEncoder.encodePassword(password),false };
				CustomerFixaddr addr = addrSvc.get(prom, objetm);
				if (addr != null) {
					session.setAttribute(request, response, USER_4S, addr);
					session.setAttribute(request, response, "USER_4SID",addr.getAddrId());
					request.getSession().removeAttribute("errorCount");
					return "/fourshop/fourshop";
				} else {
					setErrorsBy4s(request,model,"密码错误",1);
					return "login4s";
				}
			}
			else if(addrId == null && password == null)
				return "redirect:/login4s.html";
			return "login4s";
		}
	}

	/**  
	 * @Title: setErrorsBy4s  
	 * @Description: 放置错误信息
	 * @param request
	 * @param model
	 * @param message
	 * @param checkCode void 
	 * @throws  
	 */  
	private void setErrorsBy4s(HttpServletRequest request,ModelMap model,String message,Integer checkCode) {
		Integer errorCount=0;
		if(checkCode!=null&&checkCode==1){
			if(errorCount<=0){
				errorCount++;
			}
			request.getSession().setAttribute("errorCount", errorCount);
		}
		
		WebErrors we= new WebErrors();
		we.addError(message);
		we.toModel(model);
		List<CustomerFixaddr> addrList = new ArrayList<CustomerFixaddr>();
		addrList = addrSvc.getList("del", false);
		model.addAttribute("addrList", addrList);
	}
	/**  
	 * @Title: logout4s  
	 * @Description: 注销 
	 * @param request
	 * @param response
	 * @return String 
	 * @throws  
	 */  
	@RequestMapping("/logout4s.html")
	public String logout4s(HttpServletRequest request, HttpServletResponse response) {
		session.logout(request, response);
		return "redirect:login4s.html";
	}
}
