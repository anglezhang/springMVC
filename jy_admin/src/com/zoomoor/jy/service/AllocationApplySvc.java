package com.zoomoor.jy.service;

import java.util.List;

import com.zoomoor.common.base.bean.Pager;
import com.zoomoor.common.base.service.BaseSvc;
import com.zoomoor.jy.entity.AllocationApply;
import com.zoomoor.jy.entity.vo.AllocationCountVo;
import com.zoomoor.jy.entity.vo.DepotVo;

public interface AllocationApplySvc extends BaseSvc<AllocationApply, Integer> {
	public Pager getPage(String  queryGoodsName,
			String  queryGoodsBrand,String queryGoodsCode,String unitDate,Integer queryStatus,String  queryStartDate,String  queryEndDate, Integer typeId,Integer deptId, int pageNo, int pageSize);
	
	public List<AllocationCountVo> getCountPage(String  queryStartDate,String  queryEndDate,Integer deptId,Integer currentIndex);
	public List<AllocationCountVo> getCountGoodsList(String  queryGoodsName,String  queryGoodsCode,Integer currentIndex);
	public List<AllocationApply> getListByIds(String applyIds);
	public List<AllocationApply> getListByDeptId(Integer deptId);
	
	public List<DepotVo> getDepotNum(Integer deptId,Integer supId,Integer positionId,Integer goodsId,Integer showDept,Integer showSup,Integer showPosition);
	
	public List getDepotListBySummaryId(Integer tagId);
	
	public void updateAllocationApplyByTagId(Integer tagId);
	
	public List getDepotNumByGoodsId(Integer goodsId);
	
	
	public List getDepotView(Integer goodsId);
	
	
	public List getDepotCount(Integer deptId,Integer positionId,Integer brandId,Integer typeId,String showDept,String showSup,String showPosition);
	
	
	
}
