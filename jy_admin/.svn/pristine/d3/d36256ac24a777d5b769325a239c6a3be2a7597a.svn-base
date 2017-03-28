package com.zoomoor.jy.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zoomoor.admin.entity.SysUser;
import com.zoomoor.common.base.bean.Pager;
import com.zoomoor.common.base.service.impl.BaseSvcImpl;
import com.zoomoor.jy.dao.DepotCheckDao;
import com.zoomoor.jy.dao.InfoDepotPositionDao;
import com.zoomoor.jy.dao.InfoDeptDao;
import com.zoomoor.jy.entity.DepotCheck;
import com.zoomoor.jy.entity.InfoDept;
import com.zoomoor.jy.service.DepotCheckSvc;
@Service
@SuppressWarnings("rawtypes")
public class DepotCheckSvcImpl extends BaseSvcImpl<DepotCheck, Integer> implements
		DepotCheckSvc {
	@Resource
	private DepotCheckDao dao;

	@Autowired
	public void setBaseDao(DepotCheckDao dao) {
		super.setBaseDao(dao);
	}
	@Override
	public List getCheckListByDeptId(Integer deptId, String positions) {
		return dao.getCheckListByDeptId(deptId, positions);
	}

	@Override
	public List saveCheck(Integer deptId, String docStr) {
		return dao.saveCheck(deptId, docStr);
	}

	@Override
	public List getCheckListByCheckNo(Integer deptId, String checkNo) {
		return dao.getCheckListByCheckNo(deptId, checkNo);
	}

	@Override
	public List enterCheck(Integer deptId, String docStr) {
		return dao.enterCheck(deptId, docStr);
	}

	@Override
	public List getCheckListForResult(Integer deptId,String checkNo) {
		return dao.getCheckListForResult(deptId,checkNo);
	}

	@Override
	public List createResultCheck(Integer deptId, String checkNo,
			Integer empId, String memo,Integer userId) {
		return dao.createResultCheck(deptId, checkNo, empId, memo,userId);
	}

	@Override
	public Pager getPage(String queryAllocationNo,String queryEmpName,String queryStartDate,String queryEndDate,Integer status,Integer queryDeptId,int pageNo, int pageSize) {
		Pager pager=dao.getPage(queryAllocationNo, queryEmpName, queryStartDate, queryEndDate, status, queryDeptId, pageNo, pageSize);
		List<DepotCheck>  list= new ArrayList<DepotCheck>();
		List<DepotCheck>  plist=pager.getList();
		for(DepotCheck ac:plist){
			if(ac.getDepId()!=null){
				InfoDept dept=deptDao.get(ac.getDepId());
				ac.setDeptName(dept.getName());
			}
			if(ac.getDepotPositionId()!=null){
				String positionName=positionDao.getPositionFullName(ac.getDepotPositionId());
				ac.setPositionName(positionName);
			}
			list.add(ac);
		}
		pager.setList(list);
		
		return pager;
		
	}
	@Override
	public List<DepotCheck> getDepotListByNo(Integer deptId,String checkNo) {
		// TODO Auto-generated method stub
		return dao.getDepotListByNo(deptId,checkNo);
	}
	@Resource
	private InfoDeptDao deptDao;
	@Resource 
	private InfoDepotPositionDao positionDao;
}
