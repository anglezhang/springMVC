package com.cyw.mammoth.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cyw.common.base.service.impl.BaseSvcImpl;
import com.cyw.mammoth.bean.ShiftLog;
import com.cyw.mammoth.dao.ShiftLogDao;
import com.cyw.mammoth.service.ShiftLogSvc;

@Service
public class ShiftLogSvcImpl extends BaseSvcImpl<ShiftLog, Integer> implements ShiftLogSvc {
	private ShiftLogDao shiftLogDao;

	@Autowired
	public void setShiftLogDao(ShiftLogDao shiftLogDao) {
		this.shiftLogDao = shiftLogDao;
		setBaseDao(shiftLogDao);
	}
}
