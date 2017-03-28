package com.zoomoor.admin.dao.impl;

import java.util.Date;

import org.springframework.stereotype.Repository;

import com.zoomoor.admin.dao.SysAuthenticationDao;
import com.zoomoor.admin.entity.SysAuthentication;
import com.zoomoor.common.base.dao.impl.BaseDaoImpl;

@Repository
public class SysAuthenticationDaoImpl extends BaseDaoImpl<SysAuthentication, String> implements SysAuthenticationDao {
	public int deleteExpire(Date d) {
		String hql = "delete SysAuthentication bean where bean.updateTime <= :d";
		return getSession().createQuery(hql).setDate("d", d).executeUpdate();
	}

//	public SysAuthentication getByUserId(Long userId) {
//		String hql = "from SysAuthentication bean where bean.uid=?";
//		return (SysAuthentication) getSession().findUnique(hql, userId);
//	}
//
//	public Pager getPage(int pageNo, int pageSize) {
//		Criteria crit = createCriteria();
//		Pager page = findByCriteria(crit, pageNo, pageSize);
//		return page;
//	}

	public SysAuthentication findById(String id) {
		SysAuthentication entity = get(id);
		return entity;
	}

	public SysAuthentication deleteById(String id) {
		SysAuthentication entity = super.get(id);
		if (entity != null) {
			getSession().delete(entity);
		}
		return entity;
	}
}