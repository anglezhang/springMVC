package com.zoomoor.admin.service.impl;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zoomoor.admin.dao.SysEmailRecordDao;
import com.zoomoor.admin.entity.SysEmailRecord;
import com.zoomoor.admin.service.SysEmailRecordSvc;
import com.zoomoor.common.base.service.impl.BaseSvcImpl;
@Service
@Transactional
public class SysEmailRecordSvcImpl extends BaseSvcImpl<SysEmailRecord, Integer> implements SysEmailRecordSvc {
	@Resource
	private SysEmailRecordDao dao;

	@Autowired
	public void setBaseDao(SysEmailRecordDao dao) {
		super.setBaseDao(dao);
	}

}
