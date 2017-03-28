package com.zoomoor.jy.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zoomoor.common.base.service.impl.BaseSvcImpl;
import com.zoomoor.jy.dao.MoneyUserMappingDao;
import com.zoomoor.jy.entity.MoneyUserMapping;
import com.zoomoor.jy.service.MoneyUserMappingSvc;
@Service
public class MoneyUserMappingSvcImpl extends BaseSvcImpl<MoneyUserMapping, Integer> implements
		MoneyUserMappingSvc {
	@Resource
	private MoneyUserMappingDao dao;

	@Autowired
	public void setBaseDao(MoneyUserMappingDao dao) {
		super.setBaseDao(dao);
	}

	@Override
	public List<MoneyUserMapping> getMappingByRoleId(Integer rid, String type) {
		
		return dao.getMappingByRoleId(rid,type);
	}

	@Override
	public void deleteByRoleId(Integer roleId) {
		dao.deleteByRoleId(roleId);
		
	}
}
