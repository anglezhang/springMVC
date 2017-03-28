package com.zoomoor.core.web.util;

import javax.servlet.http.HttpServletRequest;

import com.zoomoor.admin.entity.SysUser;

/**
 * 提供一些系统中使用到的共用方法
 * 
 * 比如获得会员信息,获得后台站点信息
 */
public class SysUtils {
	/**
	 * 用户KEY
	 */
	public static final String USER_KEY = "_user_key";

	/**
	 * 获得用户
	 * 
	 * @param request
	 * @return
	 */
	public static SysUser getUser(HttpServletRequest request) {
		return (SysUser) request.getAttribute(USER_KEY);
	}

	/**
	 * 获得用户ID
	 * 
	 * @param request
	 * @return
	 */
	public static Integer getUserId(HttpServletRequest request) {
		SysUser user = getUser(request);
		if (user != null) {
			return user.getUserId();
		} else {
			return null;
		}
	}

	/**
	 * 设置用户
	 * 
	 * @param request
	 * @param user
	 */
	public static void setUser(HttpServletRequest request, SysUser user) {
		request.setAttribute(USER_KEY, user);
	}
}
