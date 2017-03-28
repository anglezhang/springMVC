package com.zoomoor.jy.dao;

import com.zoomoor.common.base.dao.BaseDao;
import com.zoomoor.jy.entity.OrderList;

public interface OrderListDao extends BaseDao<OrderList, Integer> {
	public void deleteById(Integer orderId) ;
	public Double getSumOrder(Integer orderId);
	
	public Double getSumOrderByTax(Integer orderId);
	
	
	public Double getDepotNum(Integer orderId);
}
