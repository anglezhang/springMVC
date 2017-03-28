package com.zoomoor.common.web.interceptor;


import java.lang.reflect.Method;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.zoomoor.common.util.SessionToken;




/**   
 * 类名称：TokenInterceptor   
 * 类描述：   防止重复提交拦截器
 * 创建人：liuweixing
 * 创建时间：2015-8-10 下午2:34:23   
 * 修改人：liuweixing
 * 修改时间：2015-8-10 下午2:34:23   
 *   修改备注：  	在需要生成token的controller上增加@Token(save=true)，而在需要检查重复提交的controller上添加@Token(remove=true)就可以了。
 *  			 在页面中加上 <input type="hidden" name="token" value="${token}" />

 * @version       
 */ 
public class TokenInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		if(handler instanceof HandlerMethod){
			HandlerMethod handlerMethod=(HandlerMethod) handler;
			Method method=handlerMethod.getMethod();
			SessionToken annotation =method.getAnnotation(SessionToken.class);
			if(annotation!=null){
				boolean needSaveSession=annotation.save();
				if(needSaveSession){
					request.getSession(false).setAttribute("token",UUID.randomUUID().toString());
				}
				boolean needRemoveSession=annotation.remove();
				if(needRemoveSession){
					if(isRepeatSubmit(request)){
						return false;
					}
					request.getSession(false).removeAttribute("token");
				}
			}
			return true;
		}else{
			return super.preHandle(request, response, handler);
			
		}
	}
	public boolean isRepeatSubmit(HttpServletRequest request){
		String serverToken=(String) request.getSession(false).getAttribute("token");
		if(serverToken==null){
			return true;
		}
		String clientToken=request.getParameter("token");
		if(clientToken==null){
			return true;
		}
		if(!serverToken.equals(serverToken)){
			return true;
		}
		return false;
	}
}
