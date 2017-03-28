package com.zoomoor.jy.service;

import java.util.List;

import com.zoomoor.common.base.bean.Pager;
import com.zoomoor.common.base.service.BaseSvc;
import com.zoomoor.jy.entity.InfoDept;
import com.zoomoor.jy.entity.InfoGoodsType;

public interface InfoDeptSvc extends BaseSvc<InfoDept, Integer> {
	public String getInfoDeptTreeJson();
	public InfoDept save(InfoDept infodept, Integer upId);
	public InfoDept update(InfoDept infodept, Integer deptId);
	
	public List<InfoDept> getListByName(String name,Integer deptid);
	
	public List<InfoDept> getChildById(Integer authid,Integer tpid);
	
	/**
	 * 描述：部门信息lookup分页
	 * @param queryDeptName 部门名称
	 * @param queryUpDeptName 上级部门名称
	 * */
	Pager getLookupPager(Pager pager,String queryDeptName,
			String queryUpDeptName);
	
	public List<InfoDept> getListById(Integer deptId);
	
}
