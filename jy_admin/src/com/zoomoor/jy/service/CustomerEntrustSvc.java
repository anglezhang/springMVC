package com.zoomoor.jy.service;

import java.util.List;
import java.util.Map;

import com.zoomoor.common.base.bean.Pager;
import com.zoomoor.common.base.service.BaseSvc;
import com.zoomoor.jy.entity.CustomerEntrust;
import com.zoomoor.jy.entity.InfoCar;
import com.zoomoor.jy.entity.InfoCustome;
import com.zoomoor.jy.entity.InfoGoods;
import com.zoomoor.jy.entity.vo.CusEntrusAmout;
import com.zoomoor.jy.entity.vo.CusEntrustGoodsVo;

/**
 * 描述：CustomerEntrustSvc.java
 * 
 * @author Zhangzhenxing
 * @Date 2015年7月31日 下午1:59:26
 * @version 1.0
 */
public interface CustomerEntrustSvc extends BaseSvc<CustomerEntrust, Integer> {

	/**
	 * 保存委托单
	 * 
	 * @param customEn
	 *            委托对象
	 * @param infoCus
	 *            客户对象
	 * @param infoCar车辆对象
	 * */
	void saveCustomEntrus(CustomerEntrust customEn, InfoCustome infoCus,
			InfoCar infoCar);

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
	Pager getPager(Pager pager, String queryOrderNO,
			String queryPlatenum, String queryCusName, String queryStatus,
			String queryFixplace, String queryAppType,
			String queryOrderBeginTime, String queryOrderEndTime,
			String queryOperator, String queryFixMan,Integer deptId);
	/**
	 * 保存委托单
	 * 
	 * @param customEn
	 *            委托对象
	 * @param infoCus
	 *            客户对象
	 * @param infoCar车辆对象
	 * */
	void updateCustomEntrus(CustomerEntrust customEn, InfoCustome infoCus,
			InfoCar infoCar);
	/**
	 * 将状态置为删除
	 * */
	void deleteByOrderID(Integer entrustId);
	
	/**
	 * 结算
	 * @param entrustId 委托单ID
	 * @param workHourAmout 工时费
	 * @param goodsAmout 物料费
	 * @return  false 结算失败 true 成功
	 * */
	boolean amount(Integer entrustId,Integer accountmanID,Double workHourAmout,Double goodsAmout);
	
	/**
	 * 委托单结算对象
	 * */
	CusEntrusAmout getAmont(Integer entrustId);
	
	/**
	 * 描述:获取结算页面pager对象
	 * @param queryCarInf 车主信息
	 * @param queryPlatenum 车牌号
	 * @param queryStatu 结算状态
	 * @param queryFixplace 维修地点
	 * @param serviceType 服务类型
	 * @param queryBeginTime 开单开始时间
	 * @param queryEndTime 开始结束时间
	 * @param deptId部门id
	 * */
	Pager getSettlementPager(Pager pager,String queryCarInf,
			String queryPlatenum, Integer queryStatu, Integer queryFixplace,
			Integer serviceType, String queryBeginTime,String queryEndTime,Integer deptId);
	/**
	 * 统计各类状态委托单数量
	 * @param deptId 部门id
	 * */
	List<Map<Integer, Integer>> getSettlementCount(Integer deptId);
	
	/**
	 * 描述:解释
	 * @param entrustId 委托单ID
	 * @param reallymoney 实收金额
	 * */
	void setSettleSave(Integer entrustId,Double reallymoney,Integer empid);
	
	/**
	 * 描述:设置挂账
	 * */
	void setBillsave(Integer entrustId,Integer billcustomerID,Integer empId);
	
	/**
	 * 描述：设置挂账结清
	 * */
	void billsettlesave(Integer entrustId,Double reallymoney,Integer empId);
	
	/**
	 * 统计
	 * 委托单状态 委托单数目统计
	 * */
	List<Map<Integer, Integer>> getCustomeCount(Integer deptId,Integer fixplace);
	
	/**
	 * 返回配件列表
	 * */
	List<CusEntrustGoodsVo> getGoodsByEntrustId(Integer entrustId);
	
	/**
	 * 返回物料总价
	 * */
	Double getEntrustPrice(Integer entrustId);
	
	/**
	 * 委托单列表
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
	List getCustomerList(Integer deptId, String queryOrderNO,
			String queryPlatenum, String queryCusName, Integer queryStatus,
			Integer queryFixplace, String queryOrderBeginTime,
			String queryOrderEndTime, String queryOperator, String queryFixMan);
}
