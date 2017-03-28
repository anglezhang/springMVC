package com.zoomoor.admin.service;

import java.util.Set;

import com.zoomoor.admin.entity.SysRole;
import com.zoomoor.common.base.bean.Pager;
import com.zoomoor.common.base.service.BaseSvc;

public interface SysRoleSvc extends BaseSvc<SysRole, Integer>{

	public Set<SysRole> getRoleSetById(Integer[] roleIds);
	
	public SysRole save(SysRole sysRole, Integer[] authIds);
	
	public SysRole update(SysRole sysRole, Integer roleId, Integer[] authIds);
	
	public SysRole deleteById(Integer id);
	
	public SysRole[] deleteByIds(Integer[] ids);
	
	
	public Boolean getUserByRoleIds(Integer[] ids);
	
	public Pager getPage(Pager pager,int pagerNum,int numPerPage);
}