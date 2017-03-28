package com.zoomoor.admin.service;

import com.zoomoor.admin.entity.SysUser;
import com.zoomoor.common.base.bean.Pager;
import com.zoomoor.common.base.service.BaseSvc;
import com.zoomoor.common.security.BadCredentialsException;
import com.zoomoor.common.security.UsernameNotFoundException;

public interface SysUserSvc extends BaseSvc<SysUser, Integer>{
	public SysUser findByUsername(String username);
	
	public Integer errorRemaining(String username);
	
	public void updateLoginInfo(Integer userId, String ip);

	public SysUser login(String username, String password, String ip) throws UsernameNotFoundException, BadCredentialsException;
	
	public boolean isSuper(SysUser sysUser);
	
	public SysUser save(SysUser sysUser, String ip, Integer[] roleIds);
	
	public SysUser update(SysUser sysUser, Integer userId, Integer[] roleIds);
	
	public SysUser deleteById(Integer id);
	
	public SysUser[] deleteByIds(Integer[] ids);
	
	public Pager getPage(Pager pager,String queryUsername,String queryRealname,int pagerNum,int numPerPage);
	
}