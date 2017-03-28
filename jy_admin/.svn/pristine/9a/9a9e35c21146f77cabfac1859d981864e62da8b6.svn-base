package com.zoomoor.jy.dao;

import java.math.BigInteger;

import com.zoomoor.common.base.dao.BaseDao;
import com.zoomoor.jy.entity.CarCustmerMapping;

/**
 * 描述：CarCustmerMappingDao.java
 * @author Zhangzhenxing
 * @Date 2015年7月29日 下午7:35:11
 * @version 1.0
 */
public interface CarCustmerMappingDao extends BaseDao<CarCustmerMapping, Integer>{
	
	/**
	 * 描述:统计客户与车辆关系是否存在
	 * @param carId 车主键
	 * @param customerId 客户信息主键
	 * @return 统计个数
	 * */
	BigInteger mappingCount(Integer carId,Integer customerId);

}
