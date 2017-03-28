package com.zoomoor.jy.dao;

import java.util.List;

import com.zoomoor.common.base.dao.BaseDao;
import com.zoomoor.jy.entity.OrderAudit;

public interface OrderAuditDao extends BaseDao<OrderAudit, Integer> {
	public List<OrderAudit> getStatusByOrderId(Integer orderId);
}
