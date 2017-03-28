package com.zoomoor.jy.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zoomoor.admin.dao.SysUserDao;
import com.zoomoor.admin.entity.SysUser;
import com.zoomoor.common.base.bean.Pager;
import com.zoomoor.common.base.service.impl.BaseSvcImpl;
import com.zoomoor.common.util.DateUtils;
import com.zoomoor.jy.dao.AllocationDao;
import com.zoomoor.jy.dao.InfoDeptDao;
import com.zoomoor.jy.entity.Allocation;
import com.zoomoor.jy.entity.InfoDept;
import com.zoomoor.jy.entity.OrderPurchase;
import com.zoomoor.jy.service.AllocationSvc;
import com.zoomoor.jy.util.CodeUtils;
@Service
public class AllocationSvcImpl extends BaseSvcImpl<Allocation, Integer> implements
		AllocationSvc {

	@Resource
	private AllocationDao dao;

	@Autowired
	public void setBaseDao(AllocationDao dao) {
		super.setBaseDao(dao);
	}
	@Resource
	private InfoDeptDao deptDao;
	
	@Resource
	private SysUserDao userDao;
	@Override
	public Integer getMaxByCurrent() {
		return dao.getMaxByCurrent();
	}

	@SuppressWarnings("unchecked")
	@Override
	public Pager getPage(String queryAllocationNo,String queryEmpName,Integer queryDeptId,String queryStartDate,String queryEndDate,int pageNo, int pageSize) {
		Pager pager=dao.getPage(queryAllocationNo, queryEmpName,queryDeptId,queryStartDate,queryEndDate,pageNo, pageSize);
		List<Allocation>  list= new ArrayList<Allocation>();
		List<Allocation>  plist=pager.getList();
		for(Allocation ac:plist){
			if(ac.getDeptId()!=null){
				InfoDept dept=deptDao.get(ac.getDeptId());
				ac.setDeptName(dept.getName());
			}if(ac.getUserId()!=null){
				SysUser user= userDao.get(ac.getUserId());
				ac.setEmpName(user.getInfoEmp().getEmpName());
			}
			list.add(ac);
		}
		pager.setList(list);
		
		return pager;
	}
	//保存调拨信息
	public Allocation saveAllocation(String memo, Integer deptId,
			SysUser sysUser) {
		Allocation allocation= new Allocation();
		allocation.setDel(false);
		allocation.setSwDate(new Date());
		allocation.setMemo(memo);
		allocation.setDeptId(deptId);
		allocation.setUserId(sysUser.getUserId());
		String datestr=DateUtils.getCurrentDate();
		Integer dateMax=dao.getMaxByCurrent();
		String dateAllocationNo=CodeUtils.getNo(dateMax);
		String allocationNo="DB"+datestr+dateAllocationNo;
		allocation.setAllocationNo(allocationNo);
		allocation.setDateAllocationNo(dateMax);
		dao.save(allocation);
		return allocation;
	}

	@Override
	public List getAllocationForPrint(String allocationNo) {
		// TODO Auto-generated method stub
		return dao.getAllocationForPrint(allocationNo);
	}
}
