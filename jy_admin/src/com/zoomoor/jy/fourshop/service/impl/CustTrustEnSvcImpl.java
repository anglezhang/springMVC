package com.zoomoor.jy.fourshop.service.impl;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zoomoor.common.base.bean.Pager;
import com.zoomoor.common.base.service.impl.BaseSvcImpl;
import com.zoomoor.jy.dao.CustomerEntrustDao;
import com.zoomoor.jy.dao.InfoGoodsDao;
import com.zoomoor.jy.entity.CustomerEntrust;
import com.zoomoor.jy.entity.vo.CusEntrusAmout;
import com.zoomoor.jy.fourshop.service.CustTrustEnSvc;

/**
 * 描述：CustTrustEnSvcImpl.java
 * 
 * @author Zhangzhenxing
 * @Date 2015年8月10日 上午9:02:42
 * @version 1.0
 */
@Service
public class CustTrustEnSvcImpl extends BaseSvcImpl<CustomerEntrust, Integer>
		implements CustTrustEnSvc {

	@Resource
	private CustomerEntrustDao dao;
	
	@Resource
	private InfoGoodsDao goodsDao;

	@Autowired
	public void setBaseDao(CustomerEntrustDao dao) {
		super.setBaseDao(dao);
	}

	@Override
	public Pager getPager(Pager pager, String queryOrderNO,
			String queryPlatenum, String queryCusName,
			String queryOrderBeginTime, String queryOrderEndTime,
			String queryOperator,String queryStatus,Integer deptId) {
		
		return dao.getFourshopPager(pager, queryOrderNO, queryPlatenum,
				queryCusName, queryOrderBeginTime, queryOrderEndTime,
				queryOperator,queryStatus, deptId);
	}

	@Override
	public boolean setShopMonney(Integer entrustId, Double shopmonney) {
		/**
		 * 描述：该方法作用
		 * 
		 * @author zhangZhenxing
		 * @Date 2015年8月10日
		 */
		return false;
	}

	/**
	* 描述：该方法作用
	* @author zhangZhenxing
	* @Date 2015年8月10日
	*/
	@Override
	public CusEntrusAmout getAmont(Integer entrustId) {
		CusEntrusAmout entity = new CusEntrusAmout();
		// 设置值
		CustomerEntrust cusEntrust = dao.get(entrustId);
		entity.setDiscount(cusEntrust.getDiscount());
		entity.setGoodsAmout(goodsDao.getGoodsPriceCount(entrustId));
		entity.setServiceCount(dao.serviceCount(entrustId));
		entity.setWorkHourAmout(dao.workHourAmout(entrustId));
		entity.setPayAmout(entity.getGoodsAmout() + entity.getWorkHourAmout());
		return entity;
	}

}
