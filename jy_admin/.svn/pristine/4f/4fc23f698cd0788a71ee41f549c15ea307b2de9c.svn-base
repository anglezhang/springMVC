package com.zoomoor.admin.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.zoomoor.admin.entity.SysAuth;
import com.zoomoor.admin.entity.SysRole;
import com.zoomoor.common.base.service.BaseSvc;

public interface SysAuthSvc extends BaseSvc<SysAuth, Integer>{
	
	public List<SysAuth> queryAuthRoot();
	
	public List<SysAuth> queryAuthById(Integer id, Integer authType);
	
	public List<SysAuth> queryUserAuth(HttpServletRequest request, Integer id);

	public SysAuth save(SysAuth sysAuth, Integer parentAuthId);
	
	public SysAuth update(SysAuth sysAuth, Integer authId);
	
	public SysAuth deleteById(Integer id);
	
	public SysAuth[] deleteByIds(Integer[] ids);
	
	public String getSysTreeJson(SysRole sysRole);
	
	public List<SysAuth> getChildById(Integer authid,Integer tpid);
	
}