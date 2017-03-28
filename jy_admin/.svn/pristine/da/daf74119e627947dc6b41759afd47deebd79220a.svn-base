package com.zoomoor.admin.dao;

import com.zoomoor.admin.entity.SysUser;
import com.zoomoor.common.base.bean.Pager;
import com.zoomoor.common.base.dao.BaseDao;

public interface SysUserDao extends BaseDao<SysUser, Integer>{ 
	
	public SysUser deleteById(Integer id);
	
	public Pager getPage(Pager pager,String queryUsername,String queryRealname,int pagerNum,int numPerPage);
	
}