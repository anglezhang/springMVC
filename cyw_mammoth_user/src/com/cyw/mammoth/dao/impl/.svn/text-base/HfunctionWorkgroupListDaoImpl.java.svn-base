package com.cyw.mammoth.dao.impl;

import org.springframework.stereotype.Repository;

import com.cyw.common.base.dao.impl.BaseDaoImpl;
import com.cyw.mammoth.bean.HfunctionWorkgroupList;
import com.cyw.mammoth.dao.HfunctionWorkgroupListDao;

@Repository
public class HfunctionWorkgroupListDaoImpl extends BaseDaoImpl<HfunctionWorkgroupList, Integer> implements
		HfunctionWorkgroupListDao {

	@Override
	public void deleteBy(String workGroupId , String hfunctionGroup) {
		getSession()
			.createSQLQuery("delete from hfunction_workgroup_list where workgroup_id = ? and hfunction_group = ? ")
			.setString(0, workGroupId)
			.setString(1, hfunctionGroup)
			.executeUpdate() ;
	}
}
