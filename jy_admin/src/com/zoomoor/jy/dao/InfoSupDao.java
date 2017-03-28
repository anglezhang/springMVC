package com.zoomoor.jy.dao;

import java.util.List;

import com.zoomoor.common.base.bean.Pager;
import com.zoomoor.common.base.dao.BaseDao;
import com.zoomoor.jy.entity.InfoSup;

public interface InfoSupDao extends BaseDao<InfoSup, Integer> {
	public Pager getPage(String queryName,String queryCode,String queryLinkMan,String queryPhone,int pageNo, int pageSize);
	public List<InfoSup> getListByCode(String code);
}
