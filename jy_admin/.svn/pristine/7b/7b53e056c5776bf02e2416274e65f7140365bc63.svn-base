package com.zoomoor.jy.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.zoomoor.common.base.dao.impl.BaseDaoImpl;
import com.zoomoor.jy.dao.OrderAuditDao;
import com.zoomoor.jy.entity.OrderAudit;
@Repository
public class OrderAuditDaoImpl extends BaseDaoImpl<OrderAudit, Integer> implements
		OrderAuditDao {

	@Override
	public List<OrderAudit> getStatusByOrderId(Integer orderId) {
		String hql =" from OrderAudit bean where 1=1 and bean.orderPurchase.purOrderId="+orderId;
		hql+=" order by bean.auditDate desc";
		return createQueryList(hql);
	}

}
