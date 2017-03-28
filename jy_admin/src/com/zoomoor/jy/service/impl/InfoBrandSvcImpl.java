package com.zoomoor.jy.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.zoomoor.common.base.service.impl.BaseSvcImpl;
import com.zoomoor.jy.dao.InfoBrandDao;
import com.zoomoor.jy.entity.InfoBrand;
import com.zoomoor.jy.entity.InfoGoodsType;
import com.zoomoor.jy.service.InfoBrandSvc;
@Service
public class InfoBrandSvcImpl extends BaseSvcImpl<InfoBrand, Integer> implements InfoBrandSvc {
	@Resource
	private InfoBrandDao dao;

	@Autowired
	public void setBaseDao(InfoBrandDao dao) {
		super.setBaseDao(dao);
	}

	@Override
	public String getGoodsTypeTreeJson() {
		JSONArray array = new JSONArray();
		List<InfoBrand> list = dao.getListByName(null,null);
		
		Set<InfoBrand> goodsType = new HashSet<InfoBrand>();
		
		JSONObject root = new JSONObject();
		
		for(InfoBrand brand : list){
			JSONObject object = new JSONObject();
			object.put("id", brand.getId());
			if(brand.getInfoBrand()!= null && brand.getInfoBrand().getId()> 0){
				object.put("pId",brand.getInfoBrand().getId());
			}else{
				object.put("pId", 0);
			}
			object.put("name", brand.getName());
			
			array.add(object);
		}
		root.put("id", 0);
		root.put("name", "品牌车系");
		//设置根节点是否可选
		root.put("nocheck", true);
		if(list != null && list.size() > 0){
			root.put("open", true);
		}
		array.add(root);
		
		String json = array.toString();
		json = json.replaceAll("\"id\"", "id");
		json = json.replaceAll("\"pId\"", "pId");
		json = json.replaceAll("\"name\"", "name");
		json = json.replaceAll("\"open\"", "open");
		json = json.replaceAll("\"checked\"", "checked");
		
		return json;
	}

	@Override
	public InfoBrand save(InfoBrand brand, Integer upId) {
		if(upId != null && upId > 0){
			InfoBrand parentBrand= dao.get(upId);
			brand.setInfoBrand(parentBrand);
		}
		brand.setDel(false);
		dao.save(brand);
		return brand;
	}

	@Override
	public InfoBrand update(InfoBrand brand, Integer id) {
		InfoBrand newBrand=dao.get(id);
		newBrand.setName(brand.getName());
		newBrand.setSort(brand.getSort());
		newBrand.setUrl(brand.getUrl());
		dao.update(newBrand);
		return newBrand;
	}

	@Override
	public List<InfoBrand> getListByName(String typeName,Integer upId) {
		return dao.getListByName(typeName,upId);
	}

	@Override
	public List<InfoBrand> getChildById(Integer authid, Integer tpid) {
		List<InfoBrand> list=dao.getChildById(authid, tpid);
		InfoBrand nBrand=dao.get(authid);
		
		List<InfoBrand> alist= new ArrayList<InfoBrand>();
		int j=0;
		for (int i = 0; i < list.size(); i++) {
			if(list.get(i).getName().equals(nBrand.getName())){
				j=i;
				break;
			}
		}
		for (int i = j+1; i < list.size(); i++) {
				alist.add(list.get(i));
		}
		return alist;
	}

	@Override
	public List<InfoBrand> getListById(Integer id) {
		// TODO Auto-generated method stub
		return dao.getListById(id);
	}
}
