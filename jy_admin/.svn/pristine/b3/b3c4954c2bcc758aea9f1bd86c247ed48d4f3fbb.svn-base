package com.zoomoor.admin.service.impl;

import java.util.HashSet;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zoomoor.admin.dao.SysRoleDao;
import com.zoomoor.admin.entity.SysAuth;
import com.zoomoor.admin.entity.SysRole;
import com.zoomoor.admin.service.SysAuthSvc;
import com.zoomoor.admin.service.SysRoleSvc;
import com.zoomoor.common.base.bean.Pager;
import com.zoomoor.common.base.service.impl.BaseSvcImpl;

@Service
@Transactional
public class SysRoleSvcImpl extends BaseSvcImpl<SysRole, Integer> implements SysRoleSvc {

	@Resource
	private SysRoleDao dao;
	
	@Autowired
	public void setBaseDao(SysRoleDao dao) {
		super.setBaseDao(dao);
	}
	
	@Resource
	private SysAuthSvc sysAuthSvc;
	
	@Autowired
	public void setSysAuthSvc(SysAuthSvc sysAuthSvc) {
		this.sysAuthSvc = sysAuthSvc;
	}

	@Override
	public Set<SysRole> getRoleSetById(Integer[] roleIds) {
		Set<SysRole> roleSet = new HashSet<SysRole>();
		if (roleIds != null) {
			for (Integer id : roleIds) {
				roleSet.add(dao.get(id));
			}
		}
		return roleSet;
	}

	@Override
	public SysRole update(SysRole sysRole, Integer roleId, Integer[] authIds) {
		SysRole entity = get(roleId);
		entity.setRoleName(sysRole.getRoleName());
		entity.setIsSuper(sysRole.getIsSuper());
		
		if(sysRole.getIsSuper() != 1){
			Set<SysAuth> sysAuths = new HashSet<SysAuth>();
			if (authIds != null) {
				for (Integer id : authIds) {
					SysAuth sysAuth = sysAuthSvc.get(id);
					sysAuths.add(sysAuth);
					if(sysAuth.getSysAuth() != null){
						sysAuths.add(sysAuth.getSysAuth());
						if(sysAuth.getSysAuth().getSysAuth() != null){
							sysAuths.add(sysAuth.getSysAuth().getSysAuth());
						}
					}
				}
			}
			entity.setSysAuths(sysAuths);
		}else{
			entity.setSysAuths(null);
		}
		
		return entity;
	}

	@Override
	public SysRole save(SysRole entity, Integer[] authIds) {
		if(entity.getIsSuper() != 1){
			Set<SysAuth> sysAuths = new HashSet<SysAuth>();
			if (authIds != null) {
				for (Integer id : authIds) {
					SysAuth sysAuth = sysAuthSvc.get(id);
					sysAuths.add(sysAuth);
					if(sysAuth.getSysAuth() != null){
						sysAuths.add(sysAuth.getSysAuth());
						if(sysAuth.getSysAuth().getSysAuth() != null){
							sysAuths.add(sysAuth.getSysAuth().getSysAuth());
						}
					}
				}
			}
			entity.setSysAuths(sysAuths);
		}
		entity.setDel(false);
		dao.save(entity);
		return entity;
	}

	@Override
	public SysRole deleteById(Integer id) {
		SysRole bean = dao.get(id);
		bean.setDel(true);
		dao.update(bean);
		return bean;
	}

	@Override
	public SysRole[] deleteByIds(Integer[] ids) {
		SysRole[] beans = new SysRole[ids.length];
		for (int i = 0, len = ids.length; i < len; i++) {
			beans[i] = deleteById(ids[i]);
		}
		return beans;
	}

	@Override
	public Boolean getUserByRoleIds(Integer[] ids) {
		// TODO Auto-generated method stub
		return dao.getUserByRoleIds(ids);
	}

	@Override
	public Pager getPage(Pager pager, int pagerNum, int numPerPage) {
		// TODO Auto-generated method stub
		return dao.getPage(pager, pagerNum, numPerPage);
	}
}