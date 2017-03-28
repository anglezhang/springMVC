package com.cyw.mammoth.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.cyw.common.base.dao.impl.BaseDaoImpl;
import com.cyw.mammoth.bean.HsetlKind;
import com.cyw.mammoth.dao.HsetlKindDao;
@Repository
public class HsetlKindDaoImpl extends BaseDaoImpl<HsetlKind, Integer> implements
		HsetlKindDao {
	@SuppressWarnings("unchecked")
	@Override
	public List<HsetlKind> findListBy(Integer status) {
		return (List<HsetlKind>)getSession()
				.createQuery("from HsetlKind t where t.status = ? order by codeId asc")
				.setParameter(0, status)
				.list();
	}
}
