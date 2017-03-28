package com.cyw.mammoth.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.cyw.common.base.dao.impl.BaseDaoImpl;
import com.cyw.mammoth.bean.Hsettle;
import com.cyw.mammoth.dao.HsettleDao;
@Repository
public class HsettleDaoImpl extends BaseDaoImpl<Hsettle, Integer> implements HsettleDao {
	@SuppressWarnings("unchecked")
	@Override
	public List<Hsettle> findListBy(Integer status) {
		return (List<Hsettle>)getSession()
				.createQuery("from Hsettle t where t.status = ? order by codeId asc")
				.setParameter(0, status)
				.list();
	}
}
