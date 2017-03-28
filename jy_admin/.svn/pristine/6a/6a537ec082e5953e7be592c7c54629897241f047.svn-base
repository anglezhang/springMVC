package com.zoomoor.jy.dao;

import java.util.List;
import java.util.Map;

import com.zoomoor.common.base.bean.Pager;
import com.zoomoor.common.base.dao.BaseDao;
import com.zoomoor.common.base.service.BaseSvc;
import com.zoomoor.jy.entity.CustomerAppoit;

/**
 * 描述：CustomerAppoitDao
 * 
 * @author Zhangzhenxing
 * @Date 2015年7月17日 下午6:06:11
 * @version 1.0
 */
public interface CustomerAppoitDao extends BaseDao<CustomerAppoit, Integer> {
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
	public Pager getList(Pager pager, String queryTel, String queryPlatenum,
			Integer queryAppType, Integer querySource, String queryBegintime,
			String queryEndtime, String querySellstart, String querySellend,
			Integer queryStatus);

	/**
	 * 取最大id
	 * */
	Integer maxOrderID();
	
	/**
	 * 查询统计
	 * return key 状态 统计数量
	 * */
	List<Map<Integer, Integer>> getOrderCount();

}
