package com.zoomoor.jy.dao;

import java.util.List;

import com.zoomoor.admin.entity.SysAuth;
import com.zoomoor.common.base.dao.BaseDao;
import com.zoomoor.jy.entity.InfoGoodsType;

public interface InfoGoodsTypeDao extends BaseDao<InfoGoodsType, Integer> {
	public List<InfoGoodsType> getListByName(String typeName,Integer upid);
	public List<InfoGoodsType> getChildById(Integer authid,Integer tpid);
}
