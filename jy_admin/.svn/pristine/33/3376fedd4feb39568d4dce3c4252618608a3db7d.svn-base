package com.zoomoor.jy.dao;

import java.util.List;

import com.zoomoor.common.base.bean.Pager;
import com.zoomoor.common.base.dao.BaseDao;
import com.zoomoor.jy.entity.AllocationApply;
import com.zoomoor.jy.entity.vo.AllocationCountVo;

public interface AllocationApplyDao extends BaseDao<AllocationApply, Integer> {
	public Pager getPage(String queryGoodsName, String queryGoodsBrand,String queryGoodsCode,String unitDate,
			Integer queryStatus, String queryStartDate, String queryEndDate,
			Integer typeId,Integer deptId, int pageNo, int pageSize);
	public List<AllocationCountVo> getCountPage(String queryStartDate, String queryEndDate,Integer deptId,Integer currentIndex);
	public List<AllocationCountVo> getCountGoodsList(String  queryGoodsName,String  queryGoodsCode,Integer currentIndex);
	public List<AllocationApply> getListByDeptId(Integer deptId);
	public List getDepotNum(Integer deptId,Integer supId,Integer positionId,Integer goodsId,Integer showDept,Integer showSup,Integer showPosition);
	
	public AllocationApply getApplyNumById(Integer todeptId,Integer goodsId,Integer id);
	public List getDepotListBySummaryId(Integer tagId);
	public void updateAllocationApplyByTagId(Integer tagId);
	
	public List getDepotView(Integer goodsId);
	public List getDepotCount(Integer deptId,Integer positionId,Integer brandId,Integer typeId,String showDept,String showSup,String showPosition);
	public List getDepotNumByGoodsId(Integer goodsId);
}
