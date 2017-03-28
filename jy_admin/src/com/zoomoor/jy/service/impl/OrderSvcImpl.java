package com.zoomoor.jy.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zoomoor.admin.entity.SysUser;
import com.zoomoor.admin.service.SysRoleSvc;
import com.zoomoor.admin.service.SysUserSvc;
import com.zoomoor.common.base.bean.Pager;
import com.zoomoor.common.base.service.impl.BaseSvcImpl;
import com.zoomoor.common.util.DateUtils;
import com.zoomoor.jy.dao.OrderAuditDao;
import com.zoomoor.jy.dao.OrderDao;
import com.zoomoor.jy.dao.OrderListDao;
import com.zoomoor.jy.entity.OrderList;
import com.zoomoor.jy.entity.OrderPurchase;
import com.zoomoor.jy.service.OrderSvc;
import com.zoomoor.jy.util.CodeUtils;

@Service
public class OrderSvcImpl extends BaseSvcImpl<OrderPurchase, Integer> implements
		OrderSvc {
	@Resource
	private OrderDao dao;

	@Autowired
	public void setBaseDao(OrderDao dao) {
		super.setBaseDao(dao);
	}

	@Resource
	private OrderListDao orderListDao;

	@SuppressWarnings({ "unchecked" })
	@Override
	public Pager getPage(String queryOrderNo, String queryEmpName,
			String querySubName, Integer queryStatus, Integer auditType,
			String queryStartDate, String queryEndDate, int pageNo, int pageSize) {
		Pager p = dao.getPage(queryOrderNo, queryEmpName, querySubName,
				queryStatus, auditType, queryStartDate, queryEndDate, pageNo,
				pageSize);
		List<OrderPurchase> list = new ArrayList<OrderPurchase>();
		List<OrderPurchase> plist = p.getList();
		for (OrderPurchase porder : plist) {
			Double countPrice = orderListDao
					.getSumOrder(porder.getPurOrderId());
			porder.setCountPrice(countPrice == null ? 0 : countPrice);
			Double countTaxPrice = orderListDao.getSumOrderByTax(porder
					.getPurOrderId());
			porder.setCountTaxPrice(countTaxPrice);
			Double depotNum = orderListDao.getDepotNum(porder.getPurOrderId());
			porder.setDepotNum(depotNum);
			list.add(porder);
		}
		p.setList(list);
		return p;
	}

	@Override
	public Integer saveOrder(OrderPurchase order, Integer deptId) {
		String datestr = DateUtils.getCurrentDate();
		Integer dateMax = dao.getMaxByCurrent();
		String dateOrderNo = CodeUtils.getNo(dateMax);
		String purOrderNo = "CG" + datestr + dateOrderNo;
		order.setPurOrderNo(purOrderNo);
		order.setPurOrderDate(new Date());
		order.setDateOrderNo(dateMax);
		order.setStatus(0);
		order.setDel(false);
		order.setDeptId(deptId);
		Integer orderId = dao.save(order);
		for (OrderList ol : order.getOrderList()) {
			if (ol.getInfoGoods() != null) {
				if (ol.getInfoGoods().getGoodsId() != null) {
					ol.setOrderPurchase(order);
					ol.setDel(false);
					orderListDao.save(ol);
				}
			}
		}
		return orderId;
	}

	@Override
	public void updateOrder(OrderPurchase order, Integer orderId) {
		OrderPurchase neworder = dao.get(orderId);
		BeanUtils.copyProperties(order, neworder, new String[] { "purOrderId",
				"del", "purOrderDate", "purOrderNo", "status", "deptId",
				"dateOrderNo" });
		dao.update(neworder);
		for (OrderList ol : order.getOrderList()) {
			if (ol.getInfoGoods() != null) {
				if (ol.getInfoGoods().getGoodsId() != null) {
					ol.setOrderPurchase(neworder);
					ol.setDel(false);
					orderListDao.save(ol);
				}
			}

		}
	}

	@Override
	public Boolean verAudit(Double price, SysUser user) {
		Double authMaxPrice = dao.getMaxBySysUserId(user.getUserId());
		if (authMaxPrice * 10000 >= price) {
			return true;
		} else {
			return false;
		}
	}
}
