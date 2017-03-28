package com.cyw.mammoth.service;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.Map;

import com.cyw.common.base.service.BaseSvc;
import com.cyw.mammoth.bean.Parameter;

public interface ParameterSvc extends BaseSvc<Parameter, Integer> {
	/** 
	 * 查询夜审状态
	 * @return true 为正在夜审 false 为没有进行夜审
	 * @see [类、类#方法、类#成员]
	 */
	public boolean getNightAuditState();
	
	/** 
	 * 获取酒店当前日期
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	public Date GetHotelDate();
	/** 
	 * 获取是否含IP
	 * @return true 为IP false 不是IP
	 * @see [类、类#方法、类#成员]
	 */
	public boolean GetIPFlag();
	
	
	public void uploadImage(InputStream inword);
	
	public InputStream getImageByte(String tableName,String colName,String where);
	
	/**
	 * 获取星期
	 * @return
	 */
	Parameter findWeek();
}
