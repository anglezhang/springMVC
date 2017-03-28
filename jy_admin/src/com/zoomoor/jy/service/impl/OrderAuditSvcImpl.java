package com.zoomoor.jy.service.impl;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zoomoor.admin.entity.SysUser;
import com.zoomoor.common.base.service.impl.BaseSvcImpl;
import com.zoomoor.jy.dao.OrderAuditDao;
import com.zoomoor.jy.entity.OrderAudit;
import com.zoomoor.jy.entity.OrderPurchase;
import com.zoomoor.jy.service.OrderAuditSvc;
@Service
public class OrderAuditSvcImpl extends BaseSvcImpl<OrderAudit, Integer> implements
		OrderAuditSvc {
	@Resource
	private OrderAuditDao dao;

	@Autowired
	public void setBaseDao(OrderAuditDao dao) {
		super.setBaseDao(dao);
	}

	@Override
	public void save(OrderPurchase porder, OrderAudit orderAudit,
			SysUser sysUser) {
		orderAudit.setAuditDate(new Date());
		orderAudit.setOrderPurchase(porder);
		orderAudit.setAuditUserId(sysUser.getUserId());
		dao.save(orderAudit);
		
	}
}
