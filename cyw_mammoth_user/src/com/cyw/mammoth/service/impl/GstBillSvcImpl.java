package com.cyw.mammoth.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cyw.common.base.service.impl.BaseSvcImpl;
import com.cyw.mammoth.bean.GstBill;
import com.cyw.mammoth.dao.GstBillDao;
import com.cyw.mammoth.dao.GuestdocDao;
import com.cyw.mammoth.service.GstBillSvc;
@Service
public class GstBillSvcImpl   extends BaseSvcImpl<GstBill, Integer> implements GstBillSvc{
	
	@Autowired
	GstBillDao gstBillDao;
	@Autowired
	public void setBaseDao(GstBillDao gstBillDao) {
		super.setBaseDao(gstBillDao);
	}
}
