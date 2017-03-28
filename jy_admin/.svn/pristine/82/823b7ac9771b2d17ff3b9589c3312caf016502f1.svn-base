package com.zoomoor.jy.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.zoomoor.common.base.bean.Pager;
import com.zoomoor.common.base.service.impl.BaseSvcImpl;
import com.zoomoor.jy.dao.InfoDeptDao;
import com.zoomoor.jy.entity.InfoDept;
import com.zoomoor.jy.entity.InfoGoodsType;
import com.zoomoor.jy.service.InfoDeptSvc;
@Service
public class InfoDeptSvcImpl extends BaseSvcImpl<InfoDept, Integer> implements InfoDeptSvc {
	@Resource
	private InfoDeptDao dao;

	@Autowired
	public void setBaseDao(InfoDeptDao dao) {
		super.setBaseDao(dao);
	}

	@SuppressWarnings("unchecked")
	@Override
	public String getInfoDeptTreeJson() {
		JSONArray array = new JSONArray();
		List<InfoDept> list = dao.getListByName(null,null);
		JSONObject root = new JSONObject();
		for(InfoDept dept : list){
			JSONObject object = new JSONObject();
			object.put("id", dept.getDeptId());
			if(dept.getInfoDept()!= null && dept.getInfoDept().getDeptId()> 0){
				object.put("pId", dept.getInfoDept().getDeptId());
			}else{
				object.put("pId", 0);
			}
			object.put("name", dept.getName());
			array.add(object);
		}
		root.put("id", 0);
		root.put("name", "部门门店");
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
	public InfoDept save(InfoDept infodept, Integer upId) {
		if(upId != null && upId > 0){
			InfoDept parentDept= dao.get(upId);
			infodept.setInfoDept(parentDept);
		}
		infodept.setDel(false);
		dao.save(infodept);
		return infodept;
	}

	@Override
	public InfoDept update(InfoDept infodept, Integer deptId) {
		InfoDept newDept=dao.get(deptId);
		newDept.setName(infodept.getName());
		newDept.setSort(infodept.getSort());
		newDept.setInfoDeptSub(infodept.getInfoDeptSub());
		newDept.setIsShop(infodept.getIsShop());
		dao.update(newDept);
		return newDept;
	}

	@Override
	public List<InfoDept> getListByName(String name, Integer deptid) {
		return dao.getListByName(name,deptid);
	}

	@Override
	public List<InfoDept> getChildById(Integer authid, Integer tpid) {
		return dao.getChildById(authid, tpid);
	}

	/**
	* 描述：该方法作用
	* @author zhangZhenxing
	* @Date 2015年7月27日
	*/
	@Override
	public Pager getLookupPager(Pager pager,String queryDeptName, String queryUpDeptName) {
		
		return dao.getLookupPager(pager, queryDeptName, queryUpDeptName);
	}

	@Override
	public List<InfoDept> getListById(Integer deptId) {
		// TODO Auto-generated method stub
		return dao.getListById(deptId);
	}
}
