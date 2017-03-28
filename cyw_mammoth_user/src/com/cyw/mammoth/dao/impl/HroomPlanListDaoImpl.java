package com.cyw.mammoth.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.cyw.common.base.dao.impl.BaseDaoImpl;
import com.cyw.mammoth.bean.HroomPlanList;
import com.cyw.mammoth.dao.HroomPlanListDao;
@Repository
public class HroomPlanListDaoImpl extends BaseDaoImpl<HroomPlanList, Integer> implements
		HroomPlanListDao {

	@Override
	public List<?> findListBy(Integer status) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
