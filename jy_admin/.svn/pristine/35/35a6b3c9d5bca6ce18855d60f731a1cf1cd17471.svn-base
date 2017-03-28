package com.zoomoor.common.web.session;

import java.io.Serializable;
import java.util.HashSet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zoomoor.admin.entity.SysAuth;
import com.zoomoor.admin.entity.SysUser;

/**
 * Session提供者
 */
public interface SessionProvider {
	public Serializable getAttribute(HttpServletRequest request, String name);

	public void setAttribute(HttpServletRequest request, HttpServletResponse response, String name, Serializable value);

	public String getSessionId(HttpServletRequest request, HttpServletResponse response);

	public void logout(HttpServletRequest request, HttpServletResponse response);

	public SysUser getUserSession(HttpServletRequest request, String name);
	
	public void setUserSession(HttpServletRequest request, HttpServletResponse response, String name, SysUser user);

	public boolean getIsSuperSession(HttpServletRequest request, String name);
	
	public void setIsSuperSession(HttpServletRequest request, HttpServletResponse response, String name, boolean isSuper);
	
	public HashSet<SysAuth> getAuthSession(HttpServletRequest request, String name);
	
	public void setAuthSession(HttpServletRequest request, HttpServletResponse response, String name, HashSet<SysAuth> authSet);
}