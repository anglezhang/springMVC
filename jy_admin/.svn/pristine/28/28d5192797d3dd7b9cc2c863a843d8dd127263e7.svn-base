package com.zoomoor.jy.service.impl;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zoomoor.common.base.service.impl.BaseSvcImpl;
import com.zoomoor.jy.dao.InfoDeptSubDao;
import com.zoomoor.jy.entity.InfoDeptSub;
import com.zoomoor.jy.service.InfoDeptSubSvc;
@Service
public class InfoDeptSubSvcImpl extends BaseSvcImpl<InfoDeptSub, Integer> implements
		InfoDeptSubSvc {
	@Resource
	private InfoDeptSubDao dao;

	@Autowired
	public void setBaseDao(InfoDeptSubDao dao) {
		super.setBaseDao(dao);
	}
}
