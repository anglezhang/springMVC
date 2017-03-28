package com.zoomoor.jy.service;

import com.zoomoor.admin.entity.SysUser;
import com.zoomoor.common.base.bean.Pager;
import com.zoomoor.common.base.service.BaseSvc;
import com.zoomoor.jy.entity.OrderPurchase;

public interface OrderSvc extends BaseSvc<OrderPurchase, Integer> {
	public Pager getPage(String queryOrderNo,String  queryEmpName,
			String  querySubName,Integer queryStatus, Integer auditType,String  queryStartDate,String  queryEndDate, int pageNo, int pageSize);
	public Integer saveOrder(OrderPurchase order,Integer deptId);
	
	public void updateOrder(OrderPurchase order,Integer orderId);
	
	public Boolean verAudit(Double price,SysUser user);
}
