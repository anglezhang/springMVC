package com.zoomoor.jy.dao;

import java.util.List;

import com.zoomoor.common.base.bean.Pager;
import com.zoomoor.common.base.dao.BaseDao;
import com.zoomoor.jy.entity.InfoDept;

public interface InfoDeptDao extends BaseDao<InfoDept, Integer> {
	public List<InfoDept> getListByName(String DeptName,Integer upid);
	public List<InfoDept> getChildById(Integer authid, Integer tpid);
	Pager getLookupPager(Pager pager,String queryDeptName, String queryUpDeptName);
	public List<InfoDept> getListById(Integer deptId);
}
