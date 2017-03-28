package com.zoomoor.jy.service;

import java.util.List;

import com.zoomoor.admin.entity.SysAuth;
import com.zoomoor.common.base.service.BaseSvc;
import com.zoomoor.jy.entity.InfoGoodsType;

public interface InfoGoodsTypeSvc extends BaseSvc<InfoGoodsType, Integer> {
	
	public String getGoodsTypeTreeJson();
	public InfoGoodsType save(InfoGoodsType goodsType, Integer upId);
	public InfoGoodsType update(InfoGoodsType goodsType, Integer groupId);
	
	public List<InfoGoodsType> getListByName(String typeName,Integer upid);
	
	public List<InfoGoodsType> getChildById(Integer authid,Integer tpid);

}
