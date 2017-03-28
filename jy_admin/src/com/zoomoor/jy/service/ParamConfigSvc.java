package com.zoomoor.jy.service;

import java.util.List;

import com.zoomoor.common.base.bean.Pager;
import com.zoomoor.common.base.service.BaseSvc;
import com.zoomoor.jy.entity.ParamConfig;

public interface ParamConfigSvc extends BaseSvc<ParamConfig, Integer> {
	public Pager getPage(int pageNo, int pageSize);
	public ParamConfig[] deleteByIds(Integer[] ids);
	public ParamConfig deleteById(Integer id);
	public List<ParamConfig> getListByName(String name,Integer paramType);
	public Boolean isUser(ParamConfig config);
	
}
