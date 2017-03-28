package com.zoomoor.jy.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.zoomoor.admin.entity.SysUser;
import com.zoomoor.common.base.service.impl.BaseSvcImpl;
import com.zoomoor.jy.dao.InfoDepotPositionDao;
import com.zoomoor.jy.dao.InfoDeptDao;
import com.zoomoor.jy.entity.InfoDepotPosition;
import com.zoomoor.jy.entity.InfoDept;
import com.zoomoor.jy.service.InfoDepotPositionSvc;
@Service
@SuppressWarnings("unchecked")
public class InfoDepotPositionSvcImpl extends BaseSvcImpl<InfoDepotPosition, Integer> implements
		InfoDepotPositionSvc {
	@Resource
	private InfoDepotPositionDao dao;
	
	@Autowired
	public void setBaseDao(InfoDepotPositionDao dao){
		super.setBaseDao(dao);
	}
	@Resource
	private InfoDeptDao deptDao;
	
	@Override
	public String getDepotPositionTreeJson(InfoDept infoDept) {
		JSONArray array = new JSONArray();
		List<InfoDepotPosition> list = dao.getListByName(null,null,infoDept);
		JSONObject root = new JSONObject();
		for(InfoDepotPosition position : list){
			JSONObject object = new JSONObject();
			object.put("id", position.getId());
			if(position.getInfoDepotPosition()!= null && position.getInfoDepotPosition().getId()> 0){
				object.put("pId", position.getInfoDepotPosition().getId());
			}else{
				object.put("pId", 0);
			}
			/*//判断是否有子节点
			String[] prom={"del","infoDepotPosition.id"};
			Object[] objetm={false,position.getId()};
			List<InfoDepotPosition> positionList= dao.getList(prom, objetm);
			if(positionList!=null&&positionList.size()>0)
			{		
				object.put("nocheck", true);
			
			}*/
			object.put("name", position.getName());
			array.add(object);
		}
		root.put("id", 0);
		root.put("name", "仓位");
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
		json = json.replaceAll("\"nocheck\"", "nocheck");
		
		return json;
	}

	@Override
	public InfoDepotPosition save(InfoDepotPosition posotion, Integer upId,SysUser sysUser) {
		if(upId != null && upId > 0){
			InfoDepotPosition parentPosition= dao.get(upId);
			posotion.setInfoDepotPosition(parentPosition);
		}
		if(sysUser.getInfoEmp()!=null){
			if(sysUser.getInfoEmp().getInfoDept()!=null){
				InfoDept dept=deptDao.get(sysUser.getInfoEmp().getInfoDept().getDeptId());
				posotion.setInfoDept(dept);
			}
		}
		posotion.setSort(10);
		posotion.setDel(false);
		dao.save(posotion);
		return posotion;
	}

	@Override
	public InfoDepotPosition update(InfoDepotPosition position, Integer groupId) {
		InfoDepotPosition newPosition=dao.get(groupId);
		newPosition.setName(position.getName());
		newPosition.setContent(position.getContent());
		dao.update(newPosition);
		return newPosition;
	}

	@Override
	public List<InfoDepotPosition> getListByName(String typeName,Integer upid,InfoDept infoDept) {
		return dao.getListByName(typeName,upid,infoDept);
	}

	@Override
	public List<InfoDepotPosition> getChildById(Integer authid, Integer tpid) {
		List<InfoDepotPosition> list=dao.getChildById(authid, tpid);
		InfoDepotPosition nposition=dao.get(authid);
		
		List<InfoDepotPosition> alist= new ArrayList<InfoDepotPosition>();
		int j=0;
		for (int i = 0; i < list.size(); i++) {
			if(list.get(i).getName().equals(nposition.getName())){
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
	public String getPositionFullName(Integer pid) {
		// TODO Auto-generated method stub
		return dao.getPositionFullName(pid);
	}
}
