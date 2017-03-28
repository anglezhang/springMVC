package com.zoomoor.jy.service;

import java.util.List;
import java.util.Map;

import com.zoomoor.common.base.bean.Pager;
import com.zoomoor.common.base.service.BaseSvc;
import com.zoomoor.jy.entity.CustomerAppoit;

/**
 * 描述：客户预约服务接口
 * 
 * @author Zhangzhenxing
 * @Date 2015年7月17日 下午5:45:00
 * @version 1.0
 */
public interface CustomerAppoitSvc extends BaseSvc<CustomerAppoit, Integer> {

	/**
	 * 查询列表对象
	 * 
	 * @param queryTel
	 *            车主姓名/联系电话
	 * @param queryPlatenum
	 *            车牌号
	 * @param queryAppType
	 *            预约类型
	 * @param querySource
	 *            预约来源 pc 微信 官网
	 * @param queryBegintime
	 *            预约开始时间(时间段查询)
	 * @param queryEndtime
	 *            预约结束时间(时间段查询)
	 * */
	public Pager getPager(Pager pager, String queryTel, String queryPlatenum,
			Integer queryAppType, Integer querySource, String queryBegintime,
			String queryEndtime, String querySellstart, String querySellend,
			Integer queryStatus);

	/**
	 * 描述：客户车辆预约点保存
	 * */
	public Integer saveCustomer(CustomerAppoit entity);

	/**
	 * 描述：删除当前订单
	 * */
	void deleteCustomerById(Integer cusappoitId);

	/**
	 * 描述：修改
	 * 
	 * @param entity
	 *            预约单对象
	 * */
	void editCustomer(CustomerAppoit entity);
	
	/**
	 * 描述:统计
	 * */
	List<Map<Integer, Integer>> getCountCus();
	
	/**
	 * 根据ID返回实体
	 * */
	CustomerAppoit get(Integer id);

}
