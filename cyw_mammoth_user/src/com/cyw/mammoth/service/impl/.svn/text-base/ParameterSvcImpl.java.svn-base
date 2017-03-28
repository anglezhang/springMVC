package com.cyw.mammoth.service.impl;

import java.io.InputStream;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cyw.common.base.service.impl.BaseSvcImpl;
import com.cyw.mammoth.bean.Parameter;
import com.cyw.mammoth.dao.ParameterDao;
import com.cyw.mammoth.service.ParameterSvc;
@Service
public class ParameterSvcImpl extends BaseSvcImpl<Parameter, Integer> implements
		ParameterSvc {

	@Autowired
	ParameterDao dao;
	
	@Autowired
	public void setBaseDao(ParameterDao dao) {
		super.setBaseDao(dao);
	}
	/** 
	 * 查询夜审状态
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	@Override
	public boolean getNightAuditState() {
		return dao.getNightAuditState();
	}
	/** 
	 * 获取酒店当前日期
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	@Override
	public Date GetHotelDate() {
		return dao.GetHotelDate();
	}
	@Override
	public boolean GetIPFlag() {
		return dao.GetIPFlag();
	}
	@Override
	public void uploadImage(InputStream inword) {
		dao.uploadImage(inword);
		
	}
	@Override
	public InputStream getImageByte(String tableName,String colName,String where) {
		return dao.getImageByte(tableName, colName, where);
	}
	@Override
	public Parameter findWeek() {
		return dao.findWeek();
	}
	
	
}
