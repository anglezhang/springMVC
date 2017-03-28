package com.zoomoor.jy.service;

import java.util.List;

import com.zoomoor.common.base.service.BaseSvc;
import com.zoomoor.jy.entity.CustomerFixaddr;

/**
 * 描述：CustomerFixaddrSvc.java
 * @author Zhangzhenxing
 * @Date 2015年8月4日 上午9:52:43
 * @version 1.0
 */
public interface CustomerFixaddrSvc extends BaseSvc<CustomerFixaddr, Integer>{
	
	/**
	 * @param addrName地址名称
	 * */
	List<CustomerFixaddr> getAddrsList(String addrName);

}
