package com.cyw.mammoth.dao;

import java.util.List;

import com.cyw.common.base.dao.BaseDao;
import com.cyw.mammoth.bean.WorkGroup;
import com.cyw.mammoth.vo.HfunctionVO;
import com.cyw.mammoth.vo.WorkGroupVO;

public interface WorkGroupDao extends BaseDao<WorkGroup, Integer> {

	/**
	 * 根据功能类型和工作组id查询功能控件列表
	 * @param funcType
	 * @param groupId
	 * @return
	 */
	List<HfunctionVO> findFunctionListBy(String funcType, String groupId);
	/**
	 * 根据条件查询列表
	 * @param status
	 * @return
	 */
	List<WorkGroupVO> findListBy(Integer status);

}
