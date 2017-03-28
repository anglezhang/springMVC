package com.zoomoor.jy.service.impl;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zoomoor.common.base.service.impl.BaseSvcImpl;
import com.zoomoor.jy.dao.OrderListDao;
import com.zoomoor.jy.entity.OrderList;
import com.zoomoor.jy.service.OrderListSvc;
@Service
public class OrderListSvcImpl extends BaseSvcImpl<OrderList, Integer> implements
		OrderListSvc {
	@Resource
	private OrderListDao dao;

	@Autowired
	public void setBaseDao(OrderListDao dao) {
		super.setBaseDao(dao);
	}

	@Override
	public void updateById(Integer orderId) {
		//
		
	}

	@Override
	public Double getSumOrder(Integer orderId) {
		return dao.getSumOrder(orderId);
	}

	@Override
	public void deleteById(Integer orderId) {
		dao.deleteById(orderId);
		
	}
}
