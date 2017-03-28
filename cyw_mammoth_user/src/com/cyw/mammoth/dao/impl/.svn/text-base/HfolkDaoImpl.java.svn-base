package com.cyw.mammoth.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.cyw.common.base.dao.impl.BaseDaoImpl;
import com.cyw.mammoth.bean.Hfolk;
import com.cyw.mammoth.dao.HfolkDao;
@Repository
public class HfolkDaoImpl extends BaseDaoImpl<Hfolk, Integer> implements HfolkDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<Hfolk> findListBy(Integer status) {
		return (List<Hfolk>)getSession()
				.createQuery("from Hfolk t where t.status = ? order by codeId asc")
				.setParameter(0, status)
				.list();
	}
}
