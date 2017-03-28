package com.zoomoor.jy.service.impl;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zoomoor.common.base.bean.Pager;
import com.zoomoor.common.base.service.impl.BaseSvcImpl;
import com.zoomoor.jy.dao.CarCustmerMappingDao;
import com.zoomoor.jy.entity.CarCustmerMapping;
import com.zoomoor.jy.service.CarCustmerMappingSvc;

/**
 * 描述：CarCustmerMappingSvcImpl.java
 * @author Zhangzhenxing
 * @Date 2015年7月30日 上午8:40:36
 * @version 1.0
 */
@Service
public class CarCustmerMappingSvcImpl extends BaseSvcImpl<CarCustmerMapping, Integer> implements CarCustmerMappingSvc{
	
	@Resource
	private CarCustmerMappingDao dao;
	
	@Autowired
	public void setBaseDao(CarCustmerMappingDao dao) {
		super.setBaseDao(dao);
	}

	/**
	* 描述：lookup car分页对象
	* @author zhangZhenxing
	* @Date 2015年7月30日
	*/
	@Override
	public Pager getCarLookPager(Pager pager, String carNO, Integer customerId) {
		
		return null;
	}
}
