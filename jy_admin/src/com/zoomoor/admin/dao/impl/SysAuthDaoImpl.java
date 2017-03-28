package com.zoomoor.admin.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.zoomoor.admin.dao.SysAuthDao;
import com.zoomoor.admin.entity.SysAuth;
import com.zoomoor.common.base.bean.Finder;
import com.zoomoor.common.base.dao.impl.BaseDaoImpl;

@SuppressWarnings("unchecked")
@Repository
public class SysAuthDaoImpl extends BaseDaoImpl<SysAuth, Integer> implements SysAuthDao {
	
	@Override
	public List<SysAuth> queryAuthRoot() {
		String hql = "from SysAuth auth where auth.sysAuth.authId is null order by priority asc";
		return this.createQueryList(hql);
	}

	@Override
	public List<SysAuth> queryAuthById(Integer id, Integer authType) {
		Finder f = Finder.create(" from SysAuth auth where 1 = 1 ");
		if(authType != null && authType > 0){
			f.append(" and auth.authType = :authType ").setParam("authType", authType);
		}
		
		if(id != null && id > 0){
			f.append(" and auth.sysAuth.authId = :authId ").setParam("authId", id);
		}else{
			f.append(" and auth.sysAuth.authId is null ");
		}
		f.append(" order by priority asc ");
		
		return this.find(f);
	}

	@Override
	public List<SysAuth> getAll() {
		String hql = "from SysAuth auth order by priority asc";
		return getSession().createQuery(hql).list();
	}
	
	@Override
	public SysAuth deleteById(Integer id) {
		SysAuth entity = super.get(id);
		if (entity != null) {
			getSession().delete(entity);
		}
		return entity;
	}

	@Override
	public List<SysAuth> getChildById( Integer tid, Integer tpid) {
		String hql = "from SysAuth auth  where auth.sysAuth.authId="+tpid;
		hql+=" order by priority asc";
		return this.createQueryList(hql);
	}
}