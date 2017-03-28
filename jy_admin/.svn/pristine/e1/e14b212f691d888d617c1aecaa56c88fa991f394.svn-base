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
import com.zoomoor.jy.dao.InfoGoodsTypeDao;
import com.zoomoor.jy.entity.InfoGoodsType;
import com.zoomoor.jy.service.InfoGoodsTypeSvc;
@Service
@SuppressWarnings("unchecked")
public class InfoGoodsTypeSvcImpl extends BaseSvcImpl<InfoGoodsType, Integer> implements
		InfoGoodsTypeSvc {
	@Resource
	private InfoGoodsTypeDao dao;

	@Autowired
	public void setBaseDao(InfoGoodsTypeDao dao) {
		super.setBaseDao(dao);
	}

	
	@Override
	public String getGoodsTypeTreeJson() {
		JSONArray array = new JSONArray();
		List<InfoGoodsType> list = dao.getListByName(null,null);
		JSONObject root = new JSONObject();
		for(InfoGoodsType type : list){
			JSONObject object = new JSONObject();
			object.put("id", type.getGroupId());
			if(type.getInfoGoodsType()!= null && type.getInfoGoodsType().getGroupId()> 0){
				object.put("pId", type.getInfoGoodsType().getGroupId());
			}else{
				object.put("pId", 0);
			}
			object.put("name", type.getTypeName());
			
			array.add(object);
		}
		root.put("id", 0);
		root.put("name", "配件类别");
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
	public InfoGoodsType save(InfoGoodsType goodsType, Integer upId) {
		if(upId != null && upId > 0){
			InfoGoodsType parentGoodsType= dao.get(upId);
			goodsType.setInfoGoodsType(parentGoodsType);
		}
		goodsType.setDel(false);
		dao.save(goodsType);
		return goodsType;
	}

	@Override
	public InfoGoodsType update(InfoGoodsType goodsType, Integer groupId) {
		InfoGoodsType newgoodsType=dao.get(groupId);
		newgoodsType.setTypeName(goodsType.getTypeName());
		newgoodsType.setSort(goodsType.getSort());
		dao.update(newgoodsType);
		return newgoodsType;
	}

	@Override
	public List<InfoGoodsType> getListByName(String typeName,Integer upid) {
		return dao.getListByName(typeName,upid);
	}

	@Override
	public List<InfoGoodsType> getChildById(Integer authid, Integer tpid) {
		List<InfoGoodsType> list=dao.getChildById(authid, tpid);
		InfoGoodsType ntype=dao.get(authid);
		
		List<InfoGoodsType> alist= new ArrayList<InfoGoodsType>();
		int j=0;
		for (int i = 0; i < list.size(); i++) {
			if(list.get(i).getTypeName().equals(ntype.getTypeName())){
				j=i;
				break;
			}
		}
		for (int i = j+1; i < list.size(); i++) {
				alist.add(list.get(i));
		}
		return alist;
	}
}
