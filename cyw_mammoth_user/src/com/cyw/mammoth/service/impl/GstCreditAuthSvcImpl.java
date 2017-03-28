package com.cyw.mammoth.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cyw.common.base.service.impl.BaseSvcImpl;
import com.cyw.mammoth.bean.GstCreditAuth;
import com.cyw.mammoth.dao.GrpDocDao;
import com.cyw.mammoth.dao.GstCreditAuthDao;
import com.cyw.mammoth.dao.GuestdocDao;
import com.cyw.mammoth.service.GstCreditAuthSvc;
@Service
@Transactional
public class GstCreditAuthSvcImpl extends BaseSvcImpl<GstCreditAuth, Integer> implements
		GstCreditAuthSvc {
	
	@Autowired
	private GstCreditAuthDao creditAuthDao;
	@Autowired
	public void setBaseDao(GstCreditAuthDao creditAuthDao) {
		super.setBaseDao(creditAuthDao);
	}
}
