package com.zoomoor.jy.service;

import java.util.List;

import com.zoomoor.common.base.service.BaseSvc;
import com.zoomoor.jy.entity.InfoBrand;

public interface InfoBrandSvc extends BaseSvc<InfoBrand, Integer> {
	
	public String getGoodsTypeTreeJson();
	public InfoBrand save(InfoBrand brand, Integer upId);
	public InfoBrand update(InfoBrand brand, Integer groupId);
	
	public List<InfoBrand> getListByName(String name,Integer upId);
	
	public List<InfoBrand> getChildById(Integer authid,Integer tpid);
	
	public List<InfoBrand>  getListById(Integer id);

}
