package com.cyw.mammoth.dao;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;

import com.cyw.common.base.dao.BaseDao;
import com.cyw.mammoth.bean.Parameter;

public interface ParameterDao extends BaseDao<Parameter, Integer> {
	/** 
	 * 查询夜审状态
	 * @return
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
	 * 获取酒店当前日期
	 * @return
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
