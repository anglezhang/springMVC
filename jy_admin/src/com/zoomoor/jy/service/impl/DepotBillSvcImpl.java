package com.zoomoor.jy.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zoomoor.common.base.service.impl.BaseSvcImpl;
import com.zoomoor.jy.dao.DepotBillDao;
import com.zoomoor.jy.entity.DepotBill;
import com.zoomoor.jy.service.DepotBillSvc;
@Service
public class DepotBillSvcImpl extends BaseSvcImpl<DepotBill, Integer> implements
		DepotBillSvc {
	@Resource
	private DepotBillDao dao;

	@Autowired
	public void setBaseDao(DepotBillDao dao) {
		super.setBaseDao(dao);
	}

	@Override
	public List getBillListById(Integer summaryId,String queryBusinessNo, Integer userId) {
		return dao.getBillListById(summaryId,queryBusinessNo, userId);
	}

	@Override
	public List getServiceItemByTagId(Integer tagId) {
		// TODO Auto-generated method stub
		return dao.getServiceItemByTagId(tagId);
	}

	@Override
	public List getGoodsInfoExt(Integer goodsId) {
		return dao.getGoodsInfoExt(goodsId);
	}

	@Override
	public List getDepotDetailList(Integer queryDeptId, String queryStartDate,
			String queryEndDate,Integer  summaryId, String queryBno) {
		// TODO Auto-generated method stub
		return dao.getDepotDetailList(queryDeptId, queryStartDate, queryEndDate,summaryId, queryBno);
	}

	@Override
	public List getDepotBillPrint(Integer queryDeptId, String queryBno) {
		// TODO Auto-generated method stub
		return dao.getDepotBillPrint(queryDeptId, queryBno);
	}
}
