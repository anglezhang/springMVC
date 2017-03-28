package com.zoomoor.jy.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zoomoor.common.base.service.impl.BaseSvcImpl;
import com.zoomoor.jy.dao.DepotBillDao;
import com.zoomoor.jy.dao.InfoDeptDao;
import com.zoomoor.jy.dao.InfoSummaryDao;
import com.zoomoor.jy.entity.InfoSummary;
import com.zoomoor.jy.service.InfoSummarySvc;
@Service
public class InfoSummarySvcImpl extends BaseSvcImpl<InfoSummary, Integer> implements
		InfoSummarySvc {
	@Resource
	private InfoSummaryDao dao;
	@Resource
	private DepotBillDao billDao;
	@Autowired
	public void setBaseDao(InfoSummaryDao dao) {
		super.setBaseDao(dao);
	}

	@Override
	public List getListByUserId(Boolean stype, Integer userId) {
		
		return dao.getListByUserId(stype, userId);
	}

	@Override
	public List saveDepot(String mainStr, String listStr, String listRowStr,Integer userId) {
		// TODO Auto-generated method stub
		return billDao.saveDepot(mainStr, listStr, listRowStr,userId);
	}
}
