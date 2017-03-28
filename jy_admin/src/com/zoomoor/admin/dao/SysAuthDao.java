package com.zoomoor.admin.dao;

import java.util.List;

import com.zoomoor.admin.entity.SysAuth;
import com.zoomoor.common.base.dao.BaseDao;

public interface SysAuthDao extends BaseDao<SysAuth, Integer>{
	
	public List<SysAuth> queryAuthRoot();

	public List<SysAuth> queryAuthById(Integer id, Integer authType);
	
	public List<SysAuth> getAll();

	public SysAuth deleteById(Integer id);
	public List<SysAuth> getChildById(Integer authid,Integer tpid);
}