package com.cyw.mammoth.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cyw.common.base.service.impl.BaseSvcImpl;
import com.cyw.mammoth.bean.GstPriceList;
import com.cyw.mammoth.dao.GstPriceListDao;
import com.cyw.mammoth.dao.GuestdocDao;
import com.cyw.mammoth.service.GstPriceListSvc;

@Service
@Transactional
public class GstPriceListSvcImpl extends BaseSvcImpl<GstPriceList, Integer> implements
		GstPriceListSvc {
	
	@Autowired
	GstPriceListDao gstPriceListDao;
	
	@Autowired
	public void setBaseDao(GstPriceListDao dao) {
		super.setBaseDao(dao);
	}

	@Override
	public void updatePrice(Integer id, Double price) {
		// TODO Auto-generated method stub
		gstPriceListDao.updatePrice(id, price);
	}
	
}
