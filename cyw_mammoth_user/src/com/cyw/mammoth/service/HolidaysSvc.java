package com.cyw.mammoth.service;

import java.util.List;
import java.util.Map;

import com.cyw.common.base.service.BaseSvc;
import com.cyw.mammoth.bean.Holidays;

public interface HolidaysSvc extends BaseSvc<Holidays, Integer> {

	/**
	 * 根据id数组修改对应的实体的状态
	 * @param ids 删除id数组
	 * @param status 0 有效 1无效
	 */
	void updateStatusById(String[] ids, Integer status);
	
	/**
	 * 新增，修改，删除实体
	 * @param hbuildings 实体集合
	 * @param delIds     被删除ids
	 * @param week       星期每天选中的状态 0,0,0,0,0,1,1
	 * @param operId     session userId
	 */
	void saveOrUpdateOrDel(List<Holidays> holidays, String delIds ,String week , String operId);
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
