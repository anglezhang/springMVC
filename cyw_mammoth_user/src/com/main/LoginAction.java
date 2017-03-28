package com.main;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.ehcache.Cache;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cyw.common.util.DateUtil;
import com.cyw.mammoth.auth.AppBaseCfg;
import com.cyw.mammoth.auth.CaptchaUsernamePasswordToken;
import com.cyw.mammoth.auth.IncorrectCaptchaException;
import com.cyw.mammoth.auth.ShiroDbAuthRealm.ShiroUser;
import com.cyw.mammoth.bean.Operator;
import com.cyw.mammoth.bean.Parameter;
import com.cyw.mammoth.service.OperatorSvc;
import com.cyw.mammoth.service.ParameterSvc;
import com.cyw.mammoth.vo.AjaxJson;

/**
 *登录
 * @author  liuweixing@cyw.so
 * @version  [版本号, 2015-10-10]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Controller
public class LoginAction {
	/**
     * 日志对象
     */
    protected Logger logger = LoggerFactory.getLogger(getClass());
    
    private OperatorSvc operatorSvc;
	 @Autowired
	 public void setOperatorSvc(OperatorSvc operatorSvc) {
			this.operatorSvc = operatorSvc;
    }
   private Cache simpleCache;
	@Autowired
	public void setSimpleCache(Cache simpleCache) {
		this.simpleCache = simpleCache;
	}
	
	private ParameterSvc parameterSvc;
	@Autowired
	public void setParameterSvc(ParameterSvc parameterSvc) {
		this.parameterSvc = parameterSvc;
	}
/** 
 * 跳转至登录页面
 * @return
 * @see [类、类#方法、类#成员]
 */

	@RequestMapping(value = {"/{login:login;?.*}"}, method = RequestMethod.GET) 
	public String login() {
		try{
			Subject currentUser = SecurityUtils.getSubject();
			currentUser.logout();
		}catch(Exception e){
			logger.debug("用户凭据不存在,不予处理,请无视."); 
		}
		return "login";
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String fail(@RequestParam(FormAuthenticationFilter.DEFAULT_USERNAME_PARAM) String userName, Model model) {
		model.addAttribute(FormAuthenticationFilter.DEFAULT_USERNAME_PARAM, userName);
		return "login";
	}

	@RequestMapping(value = "/logout", method = RequestMethod.POST)
	public String logout() {
		Subject currentUser = SecurityUtils.getSubject();
		currentUser.logout();
		return "login";
	}
	/**
	 * ajax登录 spring mvc 实现
	 */
	@RequestMapping(value = "/ajaxLogin", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public AjaxJson ajaxLogin(@RequestParam String username, @RequestParam String password, @RequestParam String captcha, @RequestParam boolean rememberMe,
			HttpServletRequest request) {
		Subject currentUser = SecurityUtils.getSubject();
		AjaxJson aj = new AjaxJson();
		String resut = "";
		Boolean success = null;
		if (!currentUser.isAuthenticated()) {
			String host = request.getLocalAddr();
			CaptchaUsernamePasswordToken token = new CaptchaUsernamePasswordToken(username, password, rememberMe, host, captcha);
			token.setRememberMe(rememberMe);
			try {
				currentUser.login(token);
				success = true;
				resut = "身份验证成功！";
			} catch (IncorrectCaptchaException e) {
				success = false;
				resut = "验证码不正确！";
				logger.debug("验证码不正确！");
			} catch (UnknownAccountException e) {
				success = false;
				resut = "未知账号错误！";
				logger.debug("未知账号错误！");
			} catch (IncorrectCredentialsException e) {
				success = false;
				resut = "密码错误！";
				logger.debug("密码错误！");
			} catch (LockedAccountException e) {
				success = false;
				resut = "账号已被锁定，请与系统管理员联系！";
				logger.debug("账号已被锁定，请与系统管理员联系！");
			} catch (AuthenticationException e) {
				success = false;
				resut = "您没有授权,认证失败!";
				logger.debug("您没有授权,认证失败!");
			}
		} else {
			success = true;
			resut = "已登录";
		}
		if (success) {
			aj.setSuccess(true);
			aj.setMsg(resut);
			//将用户信息加入到全局scope
			ShiroUser shiroUser = (ShiroUser) currentUser.getPrincipal();
			Operator operator = AppBaseCfg.getOperator(shiroUser.getLoginName());
			request.getSession().setAttribute("operator", operator);
			//权限信息加入缓存
		} else {
			aj.setSuccess(false);
			aj.setMsg(resut);
		}
		// 返回json数据
		return aj;
	}
	/** 
	 * 登录
	 * @see [类、类#方法、类#成员]
	 */
	@RequestMapping("/tologin.do") 
	public String toLogin(HttpServletRequest request,HttpServletResponse response,Operator operator){
		HttpSession session=request.getSession();
		//永不失效
		session.setMaxInactiveInterval(-1);
		session.setAttribute("operator", operator);
		/*//记住密码
		if(remember!=null && remember==1){
			Cookie cookie_name= new Cookie("operName",operator.getOperName());
			Cookie cookie_password= new Cookie("password",operator.getPasswd());
			cookie_name.setMaxAge(60*60*24*30);//cookie 保存30天
			response.addCookie(cookie_name);
			cookie_password.setMaxAge(60*60*24*30);//cookie 保存30天
			response.addCookie(cookie_password);
		}else{
			Cookie cookie_name= new Cookie("operName",null);
			Cookie cookie_password= new Cookie("password",null);
			cookie_password.setMaxAge(60*60*24*30);//cookie 保存30天
			cookie_name.setMaxAge(60*60*24*30);//cookie 保存30天
			response.addCookie(cookie_name);
			response.addCookie(cookie_password);
		}*/
		return "redirect:/main";
 }

}
