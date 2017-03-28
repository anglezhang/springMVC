package com.cyw.mammoth.dao;

import java.util.List;

import com.cyw.common.base.dao.BaseDao;
import com.cyw.mammoth.bean.HroomPlanList;

public interface HroomPlanListDao extends BaseDao<HroomPlanList, Integer> {
	/**
	 * 根据条件查询列表
	 * @param status   状态
	 * @return
	 * @throws Exception
	 */
	List<?> findListBy(Integer status) throws Exception ;
}
