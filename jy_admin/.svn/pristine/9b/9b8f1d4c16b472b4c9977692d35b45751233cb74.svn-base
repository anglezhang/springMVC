package com.zoomoor.jy.service;

import java.util.List;

import com.zoomoor.admin.entity.SysUser;
import com.zoomoor.common.base.bean.Pager;
import com.zoomoor.common.base.service.BaseSvc;
import com.zoomoor.jy.entity.Allocation;

public interface AllocationSvc extends BaseSvc<Allocation, Integer> {
	public Integer getMaxByCurrent();
	public Pager getPage(String queryAllocationNo,String queryEmpName,Integer queryDeptId,String queryStartDate,String queryEndDate,int pageNo, int pageSize);
	public Allocation saveAllocation(String memo, Integer deptId,
			SysUser sysUser);
	
	
	public List getAllocationForPrint(String allocationNo);

}
