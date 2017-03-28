package com.zoomoor.jy.dao;

import java.util.List;

import com.zoomoor.common.base.bean.Pager;
import com.zoomoor.common.base.dao.BaseDao;
import com.zoomoor.jy.entity.DepotCheck;

public interface DepotCheckDao extends BaseDao<DepotCheck, Integer> {
	public List getCheckListByDeptId(Integer deptId,String positions);
	public List saveCheck(Integer deptId,String docStr);
	public List getCheckListByCheckNo(Integer deptId,String checkNo);
	public List enterCheck(Integer deptId,String docStr);
	public List getCheckListForResult(Integer deptId,String checkNo);
	public List createResultCheck(Integer deptId,String checkNo,Integer empId,String memo,Integer userId);
	public Pager getPage(String queryAllocationNo,String queryEmpName,String queryStartDate,String queryEndDate,Integer status,Integer queryDeptId,int pageNo, int pageSize);
	public List<DepotCheck> getDepotListByNo(Integer deptId,String checkNo);
}
