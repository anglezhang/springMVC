package com.zoomoor.jy.fourshop.service;

import com.zoomoor.common.base.bean.Pager;
import com.zoomoor.common.base.service.BaseSvc;
import com.zoomoor.jy.entity.CustomerEntrust;
import com.zoomoor.jy.entity.vo.CusEntrusAmout;

/**
 * 描述：CustTrustEnSvc.java
 * @author Zhangzhenxing
 * @Date 2015年8月8日 下午5:11:28
 * @version 1.0
 */
public interface CustTrustEnSvc extends BaseSvc<CustomerEntrust, Integer>{

	/**
	 * 描述获取分页对象
	 * 
	 * @param queryPlatenum
	 *            车牌号
	 * @param queryCusName
	 *            车主姓名
	 * @param querySpeed
	 *            服务进度
	 * @param queryFixplace
	 *            服务位置
	 * @param queryServiceType
	 *            服务类型
	 * @param queryOrderBeginTime
	 *            开单时间开始时间段
	 * @param queryOrderEndTime
	 *            开单时间结束时间
	 * @param queryOperator
	 *            开单人
	 * @param queryFixMan
	 *            维修工
	 * */
	Pager  getPager(Pager pager, String queryOrderNO,
			String queryPlatenum, String queryCusName, 			
			String queryOrderBeginTime, String queryOrderEndTime,
			String queryOperator, String queryStatus,Integer deptId);
	
	/**
	 * 设置费用
	 * @author Administrator
	 * @param entrustId 委托单ID
	 * @param shopmonney 4s店收取费用
	 * */
	boolean setShopMonney(Integer entrustId,Double shopmonney);
	
	/**
	 * 委托单结算对象
	 * */
	CusEntrusAmout getAmont(Integer entrustId);
}
