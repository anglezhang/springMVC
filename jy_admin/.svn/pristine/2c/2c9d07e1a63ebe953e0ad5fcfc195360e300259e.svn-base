package com.zoomoor.jy.service.impl;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zoomoor.common.base.service.impl.BaseSvcImpl;
import com.zoomoor.jy.dao.DepotBillListDao;
import com.zoomoor.jy.entity.DepotBillList;
import com.zoomoor.jy.service.DepotBillListSvc;
@Service
public class DepotBillListSvcImpl extends BaseSvcImpl<DepotBillList, Integer> implements
		DepotBillListSvc {
	@Resource
	private DepotBillListDao dao;

	@Autowired
	public void setBaseDao(DepotBillListDao dao) {
		super.setBaseDao(dao);
	}
}
