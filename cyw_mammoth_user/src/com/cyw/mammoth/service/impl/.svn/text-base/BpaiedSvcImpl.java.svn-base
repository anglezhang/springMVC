package com.cyw.mammoth.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cyw.common.base.service.impl.BaseSvcImpl;
import com.cyw.mammoth.bean.BPaied;
import com.cyw.mammoth.bean.Bills;
import com.cyw.mammoth.dao.BPaiedDao;
import com.cyw.mammoth.dao.GuestdocDao;
import com.cyw.mammoth.service.BPaiedSvc;
import com.cyw.mammoth.service.BillsSvc;

/***
 * 
 * 消费账目 (BPaiedSvc)
 * @author yangjunpeng
 * 2015-10-12 
 * 
 */
@Service
public class BpaiedSvcImpl extends BaseSvcImpl<BPaied, Integer> implements BPaiedSvc{
	
	@Autowired
	BPaiedDao bPaiedDao;
	
	@Autowired
	public void setBaseDao(BPaiedDao bPaiedDao) {
		super.setBaseDao(bPaiedDao);
	}
}
