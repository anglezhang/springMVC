package com.zoomoor.jy.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zoomoor.common.base.service.impl.BaseSvcImpl;
import com.zoomoor.jy.dao.DepotMothDao;
import com.zoomoor.jy.entity.DepotMoth;
import com.zoomoor.jy.service.DepotMothSvc;
@Service
public class DepotMothSvcImpl extends BaseSvcImpl<DepotMoth, Integer> implements
		DepotMothSvc {
	@Resource
	private DepotMothDao dao;

	@Autowired
	public void setBaseDao(DepotMothDao dao) {
		super.setBaseDao(dao);
	}

	@Override
	public List getMonthList(Integer depId) {
		// TODO Auto-generated method stub
		return dao.getMonthList(depId);
	}

	@Override
	public List getMonthCheck(Integer depId, String curMonth) {
		// TODO Auto-generated method stub
		return dao.getMonthCheck(depId, curMonth);
	}

	@Override
	public void checkBalance(Integer depId) {
		dao.checkBalance(depId);
		
	}
	
}
