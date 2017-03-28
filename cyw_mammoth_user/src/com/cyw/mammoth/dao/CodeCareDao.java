package com.cyw.mammoth.dao;

import java.util.List;

import com.cyw.common.base.dao.BaseDao;
import com.cyw.mammoth.bean.Hcodes;

public interface CodeCareDao extends BaseDao<Hcodes, Integer> {
	
	/**
	 * 获取通用代码分类列表
	 * @return
	 * @throws Exception
	 */
	List<Hcodes> findGETypeList() throws Exception ;

	/**
	 * 获取通用代码子类列表
	 * @param flag   分类标识
	 * @param status 0有效  1无效
	 * @return
	 */
	List<Hcodes> findGEListBy(String flag , Integer status);
	/**
	 * 根据标识查询对应的子项目
	 * @param flag  分类标识
	 * @return
	 */
	List<Hcodes> findAllListBy(String flag);

}
