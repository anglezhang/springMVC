package com.zoomoor.jy.dao;

import com.zoomoor.common.base.bean.Pager;
import com.zoomoor.common.base.dao.BaseDao;
import com.zoomoor.jy.entity.OrderPurchase;

public interface OrderDao extends BaseDao<OrderPurchase, Integer> {
	public Pager getPage(String queryOrderNo,String  queryEmpName,
			String  querySubName,Integer queryStatus,Integer auditType,String  queryStartDate,String  queryEndDate, int pageNo, int pageSize);
	public Integer getMaxByCurrent();
	
	public Double getMaxBySysUserId(Integer userId);
}
