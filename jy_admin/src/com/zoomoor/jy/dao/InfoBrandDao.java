package com.zoomoor.jy.dao;

import java.util.List;

import com.zoomoor.admin.entity.SysAuth;
import com.zoomoor.common.base.dao.BaseDao;
import com.zoomoor.jy.entity.InfoBrand;
import com.zoomoor.jy.entity.InfoGoodsType;

public interface InfoBrandDao extends BaseDao<InfoBrand, Integer> {
	public List<InfoBrand> getListByName(String typeName,Integer upId);
	public List<InfoBrand> getChildById(Integer authid,Integer tpid);
	public List<InfoBrand>  getListById(Integer id);
	
}
