package com.zoomoor.admin.dao;

import java.util.Date;

import com.zoomoor.admin.entity.SysLog;
import com.zoomoor.common.base.bean.Pager;
import com.zoomoor.common.base.dao.BaseDao;

public interface SysLogDao extends BaseDao<SysLog, Integer>{

	public Pager getPage(Integer category, Integer userId, String title, String ip, int pageNo, int pageSize);

	public SysLog deleteById(Integer id);

	public int deleteBatch(Integer category, Date before);
}