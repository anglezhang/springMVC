package com.zoomoor.jy.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zoomoor.common.base.service.impl.BaseSvcImpl;
import com.zoomoor.jy.dao.AllocationApplyDao;
import com.zoomoor.jy.dao.AllocationListDao;
import com.zoomoor.jy.dao.InfoDeptDao;
import com.zoomoor.jy.dao.InfoGoodsDao;
import com.zoomoor.jy.entity.Allocation;
import com.zoomoor.jy.entity.AllocationApply;
import com.zoomoor.jy.entity.AllocationList;
import com.zoomoor.jy.entity.InfoDept;
import com.zoomoor.jy.entity.InfoGoods;
import com.zoomoor.jy.service.AllocationListSvc;
import com.zoomoor.jy.util.MathUtil;
@Service
public class AllocationListSvcImpl extends BaseSvcImpl<AllocationList, Integer> implements
AllocationListSvc{
	@Resource
	private AllocationListDao dao;

	@Autowired
	public void setBaseDao(AllocationListDao dao) {
		super.setBaseDao(dao);
	}
	@Resource
	private InfoDeptDao deptDao;
	@Resource
	private InfoGoodsDao goodsDao;
	@Resource
	private AllocationApplyDao applyDao;
	
	@Override
	public List<AllocationList> getAllocationListById(Integer id) {
		String[] prom={"del","allocation.id"};
		Object[] objetm={false,id};
		List<AllocationList>  allocationList = dao.getList(prom, objetm);
		List<AllocationList> list= new ArrayList<AllocationList>();
		for(AllocationList atl:allocationList){
			InfoDept fromDept=deptDao.get(atl.getFromId());
			atl.setFromDeptName(fromDept.getName());
			InfoDept toDept=deptDao.get(atl.getToId());
			atl.setToDeptName(toDept.getName());
			InfoGoods goods= goodsDao.get(atl.getGoodsId());
			atl.setInfoGoods(goods);
			AllocationApply apply=applyDao.getApplyNumById(atl.getToId(),atl.getGoodsId(),id);
			if(apply!=null){
				atl.setApplyNum(apply.getNum());
			}else{
				atl.setApplyNum(0.0);
			}
			list.add(atl);
		}
		return list;
	}
	//保存调拨明细
	public  void saveAllocationList(Integer deptId, Allocation allocation,
			Integer goodsId, Integer unitId, String deptNameSource,
			Integer formdeptId, Double num,String applyId) {
		AllocationList allocationList= new AllocationList();	
		allocationList.setAllocation(allocation);
		allocationList.setFromId(formdeptId);
		allocationList.setToId(deptId);
		allocationList.setNum(num);
		allocationList.setGoodsId(goodsId);
		allocationList.setSource(deptNameSource);
		allocationList.setDel(false);
		allocationList.setUnitId(unitId);
		if(MathUtil.isInteger(applyId)){
			allocationList.setApplyId(Integer.parseInt(applyId));
		}else{
			allocationList.setApplyId(0);
			
		}
		dao.save(allocationList);
	}
}
