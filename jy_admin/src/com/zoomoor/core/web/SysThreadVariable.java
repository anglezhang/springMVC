package com.zoomoor.core.web;

import com.zoomoor.admin.entity.SysUser;

/**
 * SYS线程变量
 */
public class SysThreadVariable {
	/**
	 * 当前用户线程变量
	 */
	private static ThreadLocal<SysUser> sysUserVariable = new ThreadLocal<SysUser>();

	/**
	 * 获得当前用户
	 * 
	 * @return
	 */
	public static SysUser getUser() {
		return sysUserVariable.get();
	}

	/**
	 * 设置当前用户
	 * 
	 * @param user
	 */
	public static void setUser(SysUser user) {
		sysUserVariable.set(user);
	}

	/**
	 * 移除当前用户
	 */
	public static void removeUser() {
		sysUserVariable.remove();
	}
}