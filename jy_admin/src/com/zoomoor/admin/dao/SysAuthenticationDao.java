package com.zoomoor.admin.dao;

import java.util.Date;

import com.zoomoor.admin.entity.SysAuthentication;
import com.zoomoor.common.base.dao.BaseDao;

public interface SysAuthenticationDao extends BaseDao<SysAuthentication, String>{

	public int deleteExpire(Date d);

//	public SysAuthentication getByUserId(Long userId);

//	public Pager getPage(int pageNo, int pageSize);

	public SysAuthentication findById(String id);

	public SysAuthentication deleteById(String id);
}