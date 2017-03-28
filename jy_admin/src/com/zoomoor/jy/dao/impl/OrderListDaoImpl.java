package com.zoomoor.jy.dao.impl;


import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.zoomoor.common.base.dao.impl.BaseDaoImpl;
import com.zoomoor.jy.dao.OrderListDao;
import com.zoomoor.jy.entity.OrderList;
@Repository
public class OrderListDaoImpl extends BaseDaoImpl<OrderList, Integer> implements
		OrderListDao {

	@Override
	public void deleteById(Integer orderId) {
	String hql="delete from OrderList  where orderPurchase.purOrderId="+orderId;
	this.getSession().createQuery(hql).executeUpdate();
	}

	@Override
	public Double getSumOrder(Integer orderId) {
		String hql="select sum(orderPrice*orderNum) from OrderList where del=0 and  orderPurchase.purOrderId="+orderId;
		return (Double) createQuery(hql).uniqueResult();
	}

	@Override
	public Double getSumOrderByTax(Integer orderId) {
		String hql="select sum(orderPrice*orderNum*(1+taxRate/100)) from OrderList where del=0 and  orderPurchase.purOrderId="+orderId;
		return (Double) createQuery(hql).uniqueResult();
	}

	@Override
	public Double getDepotNum(Integer orderId) {
		String hql="SELECT GetOrderInMoney("+orderId+")";
		Query query = this.getSession().createSQLQuery(hql);
		Number num=(Number)query.uniqueResult();
		return num.doubleValue();
	}
	
	
	
}
