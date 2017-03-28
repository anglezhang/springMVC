package com.zoomoor.jy.service;

import java.util.List;

import com.zoomoor.common.base.service.BaseSvc;
import com.zoomoor.jy.entity.DepotBill;

public interface DepotBillSvc extends BaseSvc<DepotBill, Integer> {
	
	public List getBillListById(Integer summaryId,String queryBusinessNo,Integer userId);
	
	public List getServiceItemByTagId(Integer tagId);
	
	public List getGoodsInfoExt(Integer goodsId);
	
	public List getDepotDetailList(Integer queryDeptId,String queryStartDate,String queryEndDate,Integer summaryId,String queryBno);
	
	public List getDepotBillPrint(Integer queryDeptId,String queryBno);

}
