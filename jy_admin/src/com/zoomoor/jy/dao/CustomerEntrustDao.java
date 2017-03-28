package com.zoomoor.jy.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.ui.ModelMap;

import com.zoomoor.common.base.bean.Pager;
import com.zoomoor.common.base.dao.BaseDao;
import com.zoomoor.jy.entity.CustomerEntrust;

/**
 * 描述：CustomerEntrustDao.java
 * 
 * @author Zhangzhenxing
 * @Date 2015年7月31日 下午2:01:49
 * @version 1.0
 */
public interface CustomerEntrustDao extends BaseDao<CustomerEntrust, Integer> {
	/**
	 * 描述: 调用存储过程保存委托单信息
	 * 
	 * @param mainTale
	 *            主表信息
	 * @param rowsList
	 *            子表信息
	 * */
	void saveCustomerEntrusSave(String mainTale, String rowsList);

	/**
	 * 描述:获取分页对象 * @param currenIndex 当前页签下标
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
	Pager getPager(Pager pager, String queryOrderNO, String queryPlatenum,
			String queryCusName, String queryStatus, String queryFixplace,
			String queryAppType, String queryOrderBeginTime,
			String queryOrderEndTime, String queryOperator, String queryFixMan,
			Integer deptId);

	/**
	 * 描述:修改委托单之前删除子委托单
	 * */
	void deleteSunCusEntrust(Integer cusEntrustId);

	/**
	 * 结算
	 * */
	boolean amount(Integer entrustId, Integer accountmanID,
			Double workHourAmout, Double goodsAmout,String jsdh);

	/**
	 * 描述:服务项目数统计
	 * */
	Integer serviceCount(Integer entrustId);

	/**
	 * 描述:工时费统计
	 * */
	Double workHourAmout(Integer entrustId);
	
	/**
	 * 描述:4s店委托单列表
	 * */
	Pager getFourshopPager(Pager pager, String queryOrderNO,
			String queryPlatenum, String queryCusName,
			String queryOrderBeginTime, String queryOrderEndTime,
			String queryOperator,String queryStatus,Integer deptId);
	
	/**
	 * 描述：结算pager
	 * */
	Pager getSettlementPager(Pager pager,String queryCarInf, String queryPlatenum,
			Integer queryStatu, Integer queryFixplace, Integer serviceType,
			String queryBeginTime,String queryEndTime,Integer deptId);
	
	/**
	 * 统计各类状态委托单数目
	 * @param 部门id
	 * @return Integer 状态key value 数量
	 * */
	List<Map<Integer, Integer>> getCustomCount(Integer deptId,Integer fixplace);
	
	/**
	 * 服务委托单列表
	 * @param 部门id
	 * @param queryOrderNO 服务单号
	 * @param queryPlatenum 车牌号
	 * @param queryCusName 客户姓名
	 * @param queryStatus 订单状态
	 * @param queryFixplace 服务地址
	 * @param queryOrderBeginTime 服务开始时间
	 * @param queryOrderEndTime 服务结束时间
	 * @param queryOperator 接待人
	 * @param queryFixMan 维修人员
	 * */
	List getCustomerList(Integer deptId,String queryOrderNO,String queryPlatenum
			,String queryCusName, Integer queryStatus,Integer queryFixplace,String queryOrderBeginTime,
			String queryOrderEndTime,String queryOperator, String queryFixMan);

	/**
	 * 获取上次进店时间
	 * @param cusappoitId预约单ID
	 * */
	String getLastComeTime(Integer cusappoitId);
}
