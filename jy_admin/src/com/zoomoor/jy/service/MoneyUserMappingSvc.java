package com.zoomoor.jy.service;

import java.util.List;

import com.zoomoor.common.base.service.BaseSvc;
import com.zoomoor.jy.entity.MoneyUserMapping;

public interface MoneyUserMappingSvc extends BaseSvc<MoneyUserMapping, Integer> {
	
	public List<MoneyUserMapping> getMappingByRoleId(Integer rid,String type);
	public void deleteByRoleId(Integer roleId);
}
