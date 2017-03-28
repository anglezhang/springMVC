package com.zoomoor.jy.dao.impl;

import java.math.BigInteger;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.zoomoor.common.base.dao.impl.BaseDaoImpl;
import com.zoomoor.jy.dao.CarCustmerMappingDao;
import com.zoomoor.jy.entity.CarCustmerMapping;

/**
 * 描述：CarCustmerMappingDaoImpl.java
 * 
 * @author Zhangzhenxing
 * @Date 2015年7月29日 下午7:35:59
 * @version 1.0
 */
@Repository
public class CarCustmerMappingDaoImpl extends
		BaseDaoImpl<CarCustmerMapping, Integer> implements CarCustmerMappingDao {

	/**
	 * 描述：该方法作用
	 * 
	 * @author zhangZhenxing
	 * @Date 2015年7月30日
	 */
	@Override
	public BigInteger mappingCount(Integer carId, Integer customerId) {
		String sql = "select count(*) from car_custmer_mapping where customer_id="
				+ customerId + " and car_id=" + carId;
		Query query = this.getSession().createSQLQuery(sql);
		BigInteger count = (BigInteger) query.list().get(0);
		return count;
	}

}
