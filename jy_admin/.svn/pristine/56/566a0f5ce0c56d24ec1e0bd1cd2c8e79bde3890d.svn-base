package com.zoomoor.jy.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.zoomoor.common.base.dao.impl.BaseDaoImpl;
import com.zoomoor.jy.dao.MoneyUserMappingDao;
import com.zoomoor.jy.entity.MoneyUserMapping;
@Repository
public class MoneyUserMappingDaoImpl extends BaseDaoImpl<MoneyUserMapping, Integer> implements
		MoneyUserMappingDao{

	@Override
	public List<MoneyUserMapping> getMappingByRoleId(Integer rid, String type) {
		String hql="from MoneyUserMapping m where m.sysRole.roleId="+rid+" and m.moneyAuth.authKey='"+type+"'";
		return  createQueryList(hql);
	}

	@Override
	public void deleteByRoleId(Integer roleId) {
		String hql =" delete from MoneyUserMapping bean where bean.sysRole.roleId="+roleId;
		this.getSession().createQuery(hql).executeUpdate();
	}

}
