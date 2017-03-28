package com.zoomoor.jy.dao;

import java.util.List;

import com.zoomoor.common.base.bean.Pager;
import com.zoomoor.common.base.dao.BaseDao;
import com.zoomoor.jy.entity.Allocation;

public interface AllocationDao extends BaseDao<Allocation, Integer> {
	public Integer getMaxByCurrent();
	public Pager getPage(String queryAllocationNo,String queryEmpName,Integer queryDeptId,String queryStartDate,String queryEndDate,int pageNo, int pageSize);
	public List getAllocationForPrint(String allocationNo);
}
