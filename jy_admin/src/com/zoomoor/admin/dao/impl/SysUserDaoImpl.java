package com.zoomoor.admin.dao.impl;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import com.zoomoor.admin.dao.SysUserDao;
import com.zoomoor.admin.entity.SysUser;
import com.zoomoor.common.base.bean.Finder;
import com.zoomoor.common.base.bean.Pager;
import com.zoomoor.common.base.dao.impl.BaseDaoImpl;

@Repository
public class SysUserDaoImpl extends BaseDaoImpl<SysUser, Integer> implements SysUserDao {

	@Override
	public SysUser deleteById(Integer id) {
		SysUser entity = super.get(id);
		if (entity != null) {
			getSession().delete(entity);
		}
		return entity;
	}

	@Override
	public Pager getPage(Pager pager, String queryUsername,
			String queryRealname, int pagerNum, int numPerPage) {
		Finder f = Finder.create("from SysUser bean where 1=1 and del=0");
		if(StringUtils.isNotEmpty(queryUsername)){
			f.append(" and bean.username like :queryUsername ");
			f.setParam("queryUsername", "%" + queryUsername + "%");
		}
		if(StringUtils.isNotEmpty(queryRealname)){
			f.append(" and bean.infoEmp.empName like :queryRealname ");
			f.setParam("queryRealname", "%" + queryRealname + "%");
		}
		f.append(" order by bean.userId desc");
		return find(f, pagerNum, numPerPage);
	}
}