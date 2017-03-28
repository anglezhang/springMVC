package com.cyw.mammoth.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.cyw.common.base.dao.impl.BaseDaoImpl;
import com.cyw.mammoth.bean.HgstOriType;
import com.cyw.mammoth.dao.HgstOriTypeDao;

@Repository
public class HgstOriTypeDaoImpl extends BaseDaoImpl<HgstOriType,Integer> implements HgstOriTypeDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<HgstOriType> findListBy(Integer status) {
		return (List<HgstOriType>)getSession()
				.createQuery("from HgstOriType t where t.status = ? order by codeId asc")
				.setParameter(0, status)
				.list();
	}

}
