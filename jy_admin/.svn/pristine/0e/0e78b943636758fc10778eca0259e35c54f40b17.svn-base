package com.zoomoor.jy.service.impl;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.zoomoor.common.base.service.impl.BaseSvcImpl;
import com.zoomoor.jy.dao.CustomerEntrustSubDao;
import com.zoomoor.jy.entity.CustomerEntrustSub;
import com.zoomoor.jy.service.CustomerEntrustSubSvc;

/**
 * 描述：CustomerEntrustSubSvcImpl.java
 * @author Zhangzhenxing
 * @Date 2015年8月5日 上午10:13:12
 * @version 1.0
 */
@Service
public class CustomerEntrustSubSvcImpl extends BaseSvcImpl<CustomerEntrustSub, Integer> implements CustomerEntrustSubSvc{

	@Resource
	private CustomerEntrustSubDao dao;
	
	@Autowired
	public void setBaseDao(CustomerEntrustSubDao dao) {
		super.setBaseDao(dao);
	}
}
