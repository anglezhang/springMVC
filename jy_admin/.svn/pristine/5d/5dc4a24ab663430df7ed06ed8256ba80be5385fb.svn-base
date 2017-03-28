package com.zoomoor.jy.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.zoomoor.common.base.service.impl.BaseSvcImpl;
import com.zoomoor.jy.dao.CustomerFixaddrDao;
import com.zoomoor.jy.entity.CustomerFixaddr;
import com.zoomoor.jy.service.CustomerFixaddrSvc;

/**
 * 描述：CustomerFixaddrSvcImpl.java
 * @author Zhangzhenxing
 * @Date 2015年8月4日 上午9:55:02
 * @version 1.0
 */
@Service
public class CustomerFixaddrSvcImpl extends BaseSvcImpl<CustomerFixaddr, Integer> implements CustomerFixaddrSvc{

	@Resource
	private CustomerFixaddrDao dao;
	
	@Autowired
	public void setBaseDao(CustomerFixaddrDao dao) {
		super.setBaseDao(dao);
	}
	
	/**
	* 描述：该方法作用
	* @author zhangZhenxing
	* @Date 2015年8月4日
	*/
	@Override
	public List<CustomerFixaddr> getAddrsList(String addrName) {
		
		return dao.getAddrsList(addrName);
	}

}
