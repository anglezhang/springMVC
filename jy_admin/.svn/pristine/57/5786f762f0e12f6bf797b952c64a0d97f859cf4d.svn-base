package com.zoomoor.jy.dao;

import java.util.List;

import com.zoomoor.common.base.dao.BaseDao;
import com.zoomoor.jy.entity.DepotBill;

public interface DepotBillDao extends BaseDao<DepotBill, Integer> {
	public List saveDepot(String mainStr,String listStr,String listRowStr,Integer userId);
	public List getBillListById(Integer summaryId,String queryBusinessNo,Integer userId);
	public List getServiceItemByTagId(Integer tagId);
	public List getGoodsInfoExt(Integer goodsId);
	public List getDepotDetailList(Integer queryDeptId,String queryStartDate,String queryEndDate,Integer summaryId,String queryBno);
	public List getDepotBillPrint(Integer queryDeptId,String queryBno);
}
