package com.cyw.mammoth.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.cyw.common.base.dao.impl.BaseDaoImpl;
import com.cyw.mammoth.bean.Hcountry;
import com.cyw.mammoth.dao.HcountryDao;
@Repository
public class HcountryDaoImpl extends BaseDaoImpl<Hcountry, Integer> implements HcountryDao {
	@SuppressWarnings("unchecked")
	@Override
	public List<Hcountry> findListBy(Integer status) {
		return (List<Hcountry>)getSession()
				.createQuery("from Hcountry t where t.status = ? order by codeId asc")
				.setParameter(0, status)
				.list();
	}
}
