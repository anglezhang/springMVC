package com.zoomoor.admin.service.impl;

import static com.zoomoor.admin.service.SysAuthenticationSvc.AUTH_LIST;
import static com.zoomoor.admin.service.SysAuthenticationSvc.AUTH_SUPER;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONArray;
import com.zoomoor.admin.dao.SysAuthDao;
import com.zoomoor.admin.entity.SysAuth;
import com.zoomoor.admin.entity.SysRole;
import com.zoomoor.admin.service.SysAuthSvc;
import com.zoomoor.common.base.service.impl.BaseSvcImpl;
import com.zoomoor.common.web.session.SessionProvider;

@SuppressWarnings("unchecked")
@Service
@Transactional
public class SysAuthSvcImpl extends BaseSvcImpl<SysAuth, Integer> implements SysAuthSvc {
	
	@Resource
	private SysAuthDao dao;

	@Autowired
	public void setBaseDao(SysAuthDao dao) {
		super.setBaseDao(dao);
	}
	
	private SessionProvider session;

	@Autowired
	public void setSession(SessionProvider session) {
		this.session = session;
	}

	@Override
	public List<SysAuth> queryAuthRoot() {
		return dao.queryAuthRoot();
	}
	
	@Override
	public List<SysAuth> queryAuthById(Integer id, Integer authType) {
		return dao.queryAuthById(id, authType);
	}

	@Override
	public List<SysAuth> queryUserAuth(HttpServletRequest request, Integer id) {
		List<SysAuth> list = queryAuthById(id, 1);
		if(session.getIsSuperSession(request, AUTH_SUPER)){
			return list;
		}else{
			HashSet<SysAuth> userAuths = session.getAuthSession(request, AUTH_LIST);
			if(userAuths == null){
				return null;
			}
			
			List<SysAuth> userlist = new ArrayList<SysAuth>();
			
			for (SysAuth auth : list){
				boolean isp = false; //当前权限是否拥有
				
				for (SysAuth userAuth : userAuths){
					if(userAuth.getAuthId().equals(auth.getAuthId())){
						isp = true;
					}
				}
				
				if(isp){
					boolean isc = false;  //当前权限的子权限是否拥有
					
					for (SysAuth cauth : auth.getSysAuths()){
						for (SysAuth user : userAuths){
							if(user.getAuthId().equals(cauth.getAuthId())){
								isc = true;
							}
						}
					}
					
					if(!isc){
						auth.setSysAuths(null);
					}
					
					userlist.add(auth);
				}
			}
			
			return userlist;
		}
	}

	@Override
	public SysAuth update(SysAuth sysAuth, Integer authId) {
		SysAuth entity = get(authId);
		entity.setAuthName(sysAuth.getAuthName());
		entity.setAuthCode(sysAuth.getAuthCode());
		entity.setAuthUrl(sysAuth.getAuthUrl());
		entity.setPriority(sysAuth.getPriority());
		entity.setAuthType(sysAuth.getAuthType());
		entity.setAuthDesc(sysAuth.getAuthDesc());
		
		return entity;
	}
  
	public String getSysTreeJson(SysRole sysRole){
		JSONArray array = new JSONArray();
		
		List<SysAuth> list = dao.getList("del", false);
		
		Set<SysAuth> roleAuth = new HashSet<SysAuth>();
		
		if(sysRole != null){
			roleAuth = sysRole.getSysAuths();
		}
		
		JSONObject root = new JSONObject();
		
		for(SysAuth auth : list){
			JSONObject object = new JSONObject();
			object.put("id", auth.getAuthId());
//			object.put("target", "authId");
//			object.put("rel", auth.getAuthId());
			if(auth.getSysAuth() != null && auth.getSysAuth().getAuthId() > 0){
				object.put("pId", auth.getSysAuth().getAuthId());
			}else{
				object.put("pId", 0);
			}

			object.put("name", auth.getAuthName());
//			if(auth.getSysAuths() != null && auth.getSysAuths().size() > 0){
//				object.put("open", true);
//			}
			
			if(roleAuth != null &&  roleAuth.size() > 0){
				for(SysAuth userAuth : roleAuth){
					if(userAuth.getAuthId().equals(auth.getAuthId())){
						object.put("checked", true);
						root.put("checked", true);
						break;
					}
				}
			}
			
			array.add(object);
		}
		
		
		root.put("id", 0);
		root.put("name", "所有权限");
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
//		json = json.replaceAll("\"target\"", "target");
//		json = json.replaceAll("\"rel\"", "rel");
		
		return json;
	}

	@Override
	public SysAuth save(SysAuth entity, Integer parentAuthId) {
		if(parentAuthId != null && parentAuthId > 0){
			SysAuth parentSysAuth = dao.get(parentAuthId);
			entity.setSysAuth(parentSysAuth);
		}
		entity.setDel(false);
		dao.save(entity);
		
		return entity;
	}

	@Override
	public SysAuth deleteById(Integer id) {
		SysAuth bean = dao.get(id);
		bean.setDel(true);
		dao.update(bean);
		return bean;
	}

	@Override
	public SysAuth[] deleteByIds(Integer[] ids) {
		SysAuth[] beans = new SysAuth[ids.length];
		for (int i = 0, len = ids.length; i < len; i++) {
			beans[i] = deleteById(ids[i]);
		}
		return beans;
	}

	@Override
	public List<SysAuth> getChildById(Integer authid, Integer tpid) {
		List<SysAuth> list=dao.getChildById(authid, tpid);
		SysAuth nauth=dao.get(authid);
		
		List<SysAuth> alist= new ArrayList<SysAuth>();
		int j=0;
		for (int i = 0; i < list.size(); i++) {
			if(list.get(i).getAuthName().equals(nauth.getAuthName())){
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