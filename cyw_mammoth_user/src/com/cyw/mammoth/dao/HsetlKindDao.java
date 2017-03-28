package com.cyw.mammoth.dao;

import java.util.List;

import com.cyw.common.base.dao.BaseDao;
import com.cyw.mammoth.bean.HsetlKind;

public interface HsetlKindDao extends BaseDao<HsetlKind, Integer> {
	/**
	* 根据条件查询列表
	* @param status 状态 0有效  1无效
	* @return
	* @author Sixp
	 */
	List<HsetlKind> findListBy(Integer status);
}
