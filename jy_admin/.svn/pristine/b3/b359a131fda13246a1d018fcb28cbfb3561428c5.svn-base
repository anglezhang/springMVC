package com.zoomoor.jy.service;

import com.zoomoor.common.base.bean.Pager;
import com.zoomoor.common.base.service.BaseSvc;
import com.zoomoor.jy.entity.InfoCar;
import com.zoomoor.jy.entity.InfoCustome;

/**
 * 描述：InfoCarSvc车辆信息
 * @author Zhangzhenxing
 * @Date 2015年7月24日 下午2:08:40
 * @version 1.0
 */
public interface InfoCarSvc extends BaseSvc<InfoCar, Integer>{
	
	/**
	 * 描述：获取分页对象
	 * @param carNO 车牌号
	 * @param customerId 客户信息ID
	 * */
	Pager getLookPager(Pager pager,String carNO,Integer customerId,String customerName);
	
	/**
	 * 描述：保存车辆信息
	 * @param brandName 车系名称
	 * */
	Integer saveCarInf(InfoCar infoCar,String brandName);
	
	/**
	 * 车辆信息删除
	 * */
	void deleteLookup(Integer carId);
	
	/**
	 * 车辆信息更新
	 * @param 车辆信息实体(游离态)
	 * @param brandName 车系名称
	 * */
	void lookUpdate(InfoCar infoCar,String brandName);

}
