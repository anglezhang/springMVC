package com.cyw.mammoth.dao;

import java.util.List;

import com.cyw.common.base.dao.BaseDao;
import com.cyw.mammoth.bean.Hsettle;

public interface HsettleDao extends BaseDao<Hsettle, Integer> {
	/**
	* 根据条件查询列表
	* @param status 状态 0有效  1无效
	* @return
	* @author Sixp
	 */
	List<Hsettle> findListBy(Integer status);
}
