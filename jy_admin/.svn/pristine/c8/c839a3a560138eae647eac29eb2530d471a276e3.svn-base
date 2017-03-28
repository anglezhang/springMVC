package com.zoomoor.jy.service.impl;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zoomoor.common.base.service.impl.BaseSvcImpl;
import com.zoomoor.jy.dao.ConfigEmpMappingDao;
import com.zoomoor.jy.entity.ConfigEmpMapping;
import com.zoomoor.jy.service.ConfigEmpMappingSvc;

/**
 * 描述：ConfigEmpMappingSvcImpl.java
 * @author Zhangzhenxing
 * @Date 2015年8月11日 上午10:16:54
 * @version 1.0
 */
@Service
public class ConfigEmpMappingSvcImpl extends BaseSvcImpl<ConfigEmpMapping, Integer> implements ConfigEmpMappingSvc{
	
	@Resource
	private ConfigEmpMappingDao dao;
	
	@Autowired
	public void setBaseDao(ConfigEmpMappingDao dao){
		super.setBaseDao(dao);
	}

}
