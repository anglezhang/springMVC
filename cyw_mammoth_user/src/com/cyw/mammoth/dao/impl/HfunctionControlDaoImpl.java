package com.cyw.mammoth.dao.impl;

import org.springframework.stereotype.Repository;

import com.cyw.common.base.dao.impl.BaseDaoImpl;
import com.cyw.mammoth.bean.HfunctionControl;
import com.cyw.mammoth.dao.HfunctionControlDao;

@Repository
public class HfunctionControlDaoImpl extends BaseDaoImpl<HfunctionControl, Integer> implements
		HfunctionControlDao {

	@Override
	public void deleteByParentId(Integer parentId) {
		getSession().createSQLQuery("delete from hfunction_control where parent_id = ?")
		.setInteger(0, parentId)
		.executeUpdate() ;
		
	}

}
