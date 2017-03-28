package com.cyw.mammoth.dao;

import java.util.List;
import java.util.Map;

import com.cyw.common.base.dao.BaseDao;
import com.cyw.mammoth.bean.Holidays;

public interface HolidaysDao extends BaseDao<Holidays, Integer> {
	
	/**
	 * 获取节假日列表
	 * @param status  状态
	 * @return
	 * @throws Exception
	 */
	List<Holidays> findListBy(Integer status) throws Exception ;
	
	/**
	 * 获取节假日列表
	 * @param startDate  起始时间
	 * @param endDate  结束时间
	 * @return
	 * @throws Exception
	 */
	List<Map<String, Object>> findListBy(String startDate , String endDate) throws Exception ;

}
