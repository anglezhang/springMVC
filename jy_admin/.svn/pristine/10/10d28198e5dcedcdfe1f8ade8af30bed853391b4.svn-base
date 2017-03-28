package com.zoomoor.common.web.session;

import java.io.Serializable;
import java.util.HashSet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.zoomoor.admin.entity.SysAuth;
import com.zoomoor.admin.entity.SysUser;

/**
 * HttpSession提供类
 */
public class HttpSessionProvider implements SessionProvider {

	public Serializable getAttribute(HttpServletRequest request, String name) {
		HttpSession session = request.getSession(false);
		if (session != null) {
			return (Serializable) session.getAttribute(name);
		} else {
			return null;
		}
	}

	public void setAttribute(HttpServletRequest request, HttpServletResponse response, String name, Serializable value) {
		HttpSession session = request.getSession();
		session.setAttribute(name, value);
	}

	public String getSessionId(HttpServletRequest request, HttpServletResponse response) {
		return request.getSession().getId();
	}

	public void logout(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession(false);
		if (session != null) {
			session.invalidate();
		}
	}

	@Override
	public SysUser getUserSession(HttpServletRequest request, String name) {
		HttpSession session = request.getSession(false);
		if (session != null) {
			return (SysUser) session.getAttribute(name);
		} else {
			return null;
		}
	}

	@Override
	public void setUserSession(HttpServletRequest request, HttpServletResponse response, String name, SysUser user) {
		HttpSession session = request.getSession();
		session.setAttribute(name, user);
	}

	@Override
	public boolean getIsSuperSession(HttpServletRequest request, String name) {
		HttpSession session = request.getSession(false);
		if (session != null) {
			return (Boolean) session.getAttribute(name);
		} else {
			return false;
		}
	}

	@Override
	public void setIsSuperSession(HttpServletRequest request, HttpServletResponse response, String name, boolean isSuper) {
		HttpSession session = request.getSession();
		session.setAttribute(name, isSuper);
	}

	@Override
	public HashSet<SysAuth> getAuthSession(HttpServletRequest request, String name) {
		HttpSession session = request.getSession(false);
		if (session != null) {
			return (HashSet<SysAuth>) session.getAttribute(name);
		} else {
			return null;
		}
	}
	
	@Override
	public void setAuthSession(HttpServletRequest request, HttpServletResponse response, String name, HashSet<SysAuth> authSet) {
		HttpSession session = request.getSession();
		session.setAttribute(name, authSet);
	}
}
