package com.zoomoor.jy.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zoomoor.admin.dao.SysUserDao;
import com.zoomoor.admin.entity.SysUser;
import com.zoomoor.common.base.bean.Pager;
import com.zoomoor.common.base.service.impl.BaseSvcImpl;
import com.zoomoor.jy.dao.AllocationApplyDao;
import com.zoomoor.jy.dao.InfoDeptDao;
import com.zoomoor.jy.dao.InfoEmpDao;
import com.zoomoor.jy.dao.InfoGoodsDao;
import com.zoomoor.jy.entity.AllocationApply;
import com.zoomoor.jy.entity.InfoDept;
import com.zoomoor.jy.entity.InfoGoods;
import com.zoomoor.jy.entity.vo.AllocationCountVo;
import com.zoomoor.jy.entity.vo.DepotVo;
import com.zoomoor.jy.service.AllocationApplySvc;
@Service
public class AllocationApplySvcImpl extends BaseSvcImpl<AllocationApply, Integer> implements
		AllocationApplySvc {
	@Resource
	private AllocationApplyDao dao;

	@Autowired
	public void setBaseDao(AllocationApplyDao dao) {
		super.setBaseDao(dao);
	}
	@Resource
	private InfoGoodsDao goodsDao;
	
	@Resource
	private InfoDeptDao deptDao;
	@Resource
	private SysUserDao userDao;
	
	@Resource
	private InfoEmpDao empDao;
	@Override
	public Pager getPage(String queryGoodsName, String queryGoodsBrand,String queryGoodsCode,String unitDate,
			Integer queryStatus, String queryStartDate, String queryEndDate,
			Integer typeId,Integer deptId, int pageNo, int pageSize) {
		Pager p=dao.getPage(queryGoodsName, queryGoodsBrand,queryGoodsCode,unitDate, queryStatus, queryStartDate, queryEndDate, typeId,deptId, pageNo, pageSize);
		List<AllocationApply>  list= new ArrayList<AllocationApply>();
		List<AllocationApply>  plist=p.getList();
		for(AllocationApply apply:plist){
			SysUser user=userDao.get(apply.getUserId());
			if(user!=null&&user.getInfoEmp()!=null){
				apply.setEmpName(user.getInfoEmp().getEmpName());
			}
			list.add(apply);
		}
		p.setList(list);
		return p;
		
	}
	@Override
	public List<AllocationCountVo> getCountPage(String queryStartDate, String queryEndDate,Integer deptId,Integer currentIndex) {
		return dao.getCountPage(queryStartDate, queryEndDate,deptId,currentIndex);
	}
	@Override
	public List<AllocationCountVo> getCountGoodsList(String queryGoodsName,
			String queryGoodsCode,Integer currentIndex) {
		return dao.getCountGoodsList(queryGoodsName, queryGoodsCode, currentIndex);
	}
	@Override
	public List<AllocationApply> getListByIds(String applyIds) {
		List<AllocationApply> list= new ArrayList<AllocationApply>();
		String[] ids=applyIds.split(",");
		for(String id:ids){
			AllocationApply apply=dao.get(Integer.parseInt(id));
			list.add(apply);
		}
		return list;
	}
	@Override
	public List<AllocationApply> getListByDeptId(Integer deptId) {
		return dao.getListByDeptId(deptId);
	}
	@SuppressWarnings("rawtypes")
	@Override
	public List<DepotVo> getDepotNum(Integer deptId,Integer supId,Integer positionId,Integer goodsId,Integer showDept,Integer showSup,Integer showPosition) {
		List<DepotVo> list= new ArrayList<DepotVo>();
		List  daolist=dao.getDepotNum(deptId,supId,positionId, goodsId, showDept,showSup,showPosition);
		if(daolist.size()>0){
			for(int i=0;i<daolist.size();i++){
				DepotVo dv= new DepotVo();
				Map map=(Map) daolist.get(i);
				InfoGoods goods=goodsDao.get(Integer.parseInt(map.get("goods_id").toString()));
				if(goods!=null){
					dv.setInfoGoods(goods);
				}
				InfoDept dept=deptDao.get(Integer.parseInt(map.get("dep_id").toString()));
				if(dept!=null){
					dv.setInfoDept(dept);
				}
				dv.setBalance(Double.parseDouble(map.get("balance").toString()));
				
				if(map.get("position")!=null){
					dv.setPosition(map.get("position").toString());
				}
				if(map.get("depot_position_id")!=null){
					dv.setPositionId(Integer.parseInt(map.get("depot_position_id").toString()));
				}
				dv.setComeId(Integer.parseInt(map.get("come_id").toString()));
				if(map.get("info_sup_id")!=null){
					dv.setInfoSupId(Integer.parseInt(map.get("info_sup_id").toString()));
				}
				list.add(dv);
			}
		}
		return list;
	}
	@Override
	public List getDepotListBySummaryId(Integer tagId) {
		return dao.getDepotListBySummaryId(tagId);
	}
	@Override
	public void updateAllocationApplyByTagId(Integer tagId) {
		dao.updateAllocationApplyByTagId(tagId);
		
	}
	@Override
	public List getDepotView(Integer goodsId) {
		
		return dao.getDepotView(goodsId);
	}
	@Override
	public List getDepotCount(Integer deptId,Integer positionId,Integer brandId,Integer typeId, String showDept,
			String showSup, String showPosition) {
		// TODO Auto-generated method stub
		return dao.getDepotCount(deptId, positionId,brandId,typeId, showDept, showSup, showPosition);
	}
	@Override
	public List getDepotNumByGoodsId(Integer goodsId) {
		// TODO Auto-generated method stub
		return dao.getDepotNumByGoodsId(goodsId);
	}
	
}
