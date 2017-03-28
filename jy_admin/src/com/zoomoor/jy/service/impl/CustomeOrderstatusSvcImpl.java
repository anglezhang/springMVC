package com.zoomoor.jy.service.impl;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zoomoor.common.base.service.impl.BaseSvcImpl;
import com.zoomoor.jy.dao.CustomeOrderstatusDao;
import com.zoomoor.jy.entity.CustomeOrderstatus;
import com.zoomoor.jy.service.CustomeOrderstatusSvc;

/**
 * 描述：CustomeOrderstatusSvcImpl.java
 * @author Zhangzhenxing
 * @Date 2015年8月13日 下午5:57:27
 * @version 1.0
 */
@Service
public class CustomeOrderstatusSvcImpl extends BaseSvcImpl<CustomeOrderstatus, Integer> implements CustomeOrderstatusSvc{
	
	@Resource
	private CustomeOrderstatusDao dao;
	
	@Autowired
	public void setBaseDao(CustomeOrderstatusDao dao){
		super.setBaseDao(dao);
	}

	/**
	* 描述：该方法作用
	* @author zhangZhenxing
	* @Date 2015年8月13日
	*/
	@Override
	public CustomeOrderstatus getOrder(Integer entrustId, Integer statusKey) {
		String[] params = {"customerEntrust.entrustId","statusKey"};
		Integer[] value = {entrustId,statusKey};
		return dao.get(params,value);
	}
}
