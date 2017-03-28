package com.cyw.mammoth.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cyw.common.base.service.impl.BaseSvcImpl;
import com.cyw.mammoth.bean.Bills;
import com.cyw.mammoth.dao.BillsDao;
import com.cyw.mammoth.dao.GuestdocDao;
import com.cyw.mammoth.service.BillsSvc;

/***
 * 
 * 帐单资料 (BillsSvcImpl)
 * @author litiangang
 * 2015-9-11 17:23:57
 * 
 */
@Service
public class BillsSvcImpl extends BaseSvcImpl<Bills, Integer> implements BillsSvc{
	
	@Autowired
	BillsDao billsDao;
	@Autowired
	public void setBaseDao(BillsDao dao) {
		super.setBaseDao(dao);
	}
}
