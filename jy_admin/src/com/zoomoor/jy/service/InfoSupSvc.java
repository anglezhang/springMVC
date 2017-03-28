package com.zoomoor.jy.service;

import java.util.List;

import com.zoomoor.common.base.bean.Pager;
import com.zoomoor.common.base.service.BaseSvc;
import com.zoomoor.jy.entity.InfoSup;

public interface InfoSupSvc extends BaseSvc<InfoSup, Integer> {
	public Pager getPage(String queryName,String queryCode,String queryLinkMan,String queryPhone,int pageNo, int pageSize);
	public InfoSup[] deleteByIds(Integer[] ids);
	public InfoSup deleteById(Integer id);
	
	public InfoSup updateById(InfoSup infosub,Integer id);
	
	public List<InfoSup> getListByCode(String code);
}
