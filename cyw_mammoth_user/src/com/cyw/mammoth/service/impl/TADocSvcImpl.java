package com.cyw.mammoth.service.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cyw.common.base.bean.Pager;
import com.cyw.common.base.service.impl.BaseSvcImpl;
import com.cyw.mammoth.bean.TaDoc;
import com.cyw.mammoth.dao.RoomsDao;
import com.cyw.mammoth.dao.TADocDao;
import com.cyw.mammoth.service.TADocSvc;
import com.cyw.mammoth.vo.TADocSearchVo;
@Service
public class TADocSvcImpl extends BaseSvcImpl<TaDoc, Integer> implements TADocSvc {

	@Autowired
	private TADocDao tadocDao;
	
	@Autowired
	public void setBaseDao(TADocDao tadocDao) {
		super.setBaseDao(tadocDao);
	}

	@Override
	public List<TaDoc> getAllTaDocList(TADocSearchVo tadoc) {
		return tadocDao.getAllTaDocList(tadoc);
	}
	
	
}
