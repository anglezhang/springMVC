package com.zoomoor.jy.dao;

import java.util.List;

import com.zoomoor.common.base.bean.Pager;
import com.zoomoor.common.base.dao.BaseDao;
import com.zoomoor.jy.entity.ParamConfig;


public interface ParamConfigDao extends BaseDao<ParamConfig, Integer> {
	public Pager getPage(int pageNo, int pageSize);
	public ParamConfig deleteById(Integer id);
	public List<ParamConfig> getListByName(String name,Integer paramType);
}
