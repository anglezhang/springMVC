package com.cyw.common.web.intercepter;

import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.ehcache.Cache;
import net.sf.ehcache.Element;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.util.UrlPathHelper;

import com.cyw.mammoth.auth.AppBaseCfg;
import com.cyw.mammoth.auth.ShiroDbAuthRealm.ShiroUser;
import com.cyw.mammoth.bean.Hfunction;
import com.cyw.mammoth.bean.Operator;
import com.cyw.mammoth.service.OperatorSvc;

/**
 * 
 * @ClassName: LoginIntercepter
 * @Title:
 * @Description:TODO(这里用一句话描述这个类的作用)
 * @Author:wangxiaojun
 * @Since:2014-10-25下午5:24:54
 * @Version:1.0
 */
@SuppressWarnings({ "unused" })
public class LoginInterceptor extends HandlerInterceptorAdapter {
	private static final String[] IGNORE_URI = { "/login.do", "/tologin.do",
			"/css", "/js", "/img" };
	
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
		
		public static final String authedFuncKey="auth_funcs";

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {

		String pathUrl = request.getRequestURI();
		String gurl=";JSESSIONID";
		if(pathUrl.indexOf(gurl)!=-1){
			// TODO 去除gurl并创建JSESSIONID的cookie，暂不处理，留待观察。
		}
		if(request.getSession().getAttribute("operator")==null){//session已丢失
			Subject currentUser = SecurityUtils.getSubject();
			ShiroUser shiroUser = (ShiroUser) currentUser.getPrincipal();
			if(shiroUser!=null){
				//System.out.println("-------"+shiroUser.getOperator().getOperName());
				Operator operator=shiroUser.getOperator();
				if(null==shiroUser.getOperator()){
					//operator =  operatorSvc.get(shiroUser.getLoginName());
					operator =  AppBaseCfg.getOperator(shiroUser.getLoginName());
					shiroUser.setOperator(operator);
					//判断授权数据并加入到缓存
					//operatorSvc.getOperatorAuthedFunctions(operator);
					
				}
				request.getSession().setAttribute("operator", operator);
			}
		}
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler, ModelAndView mav)
			throws Exception {
		// System.out.println("postHandle.........");
	}

	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// System.out.println("afterCompletion.........");
	}

	private boolean exclude(String uri) {
		if (IGNORE_URI != null) {
			for (String exc : IGNORE_URI) {
				if ((uri.indexOf(exc)) != -1) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * 获得第三个路径分隔符的位置
	 * 
	 * @param request
	 * @throws IllegalStateException
	 *             访问路径错误，没有三(四)个'/'
	 */
	private static String getURI(HttpServletRequest request)
			throws IllegalStateException {
		UrlPathHelper helper = new UrlPathHelper();
		String uri = helper.getOriginatingRequestUri(request);
		return uri;
	}
}
