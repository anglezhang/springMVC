package com.cyw.mammoth.service;

import java.util.List;

import com.cyw.common.base.service.BaseSvc;
import com.cyw.mammoth.bean.Hfunction;
import com.cyw.mammoth.vo.HfunctionVO;

public interface HfunctionSvc extends BaseSvc<Hfunction, Integer> {

	/**
	 * 根据条件查询列表
	 * @param status
	 * @return
	 */
	List<HfunctionVO> findListBy(Integer status) throws Exception;
	/**
	 * 根据id数组修改对应的实体的状态
	 * @param ids 删除id数组
	 * @param status 0 有效 1无效
	 */
	void updateStatusById(String[] ids, Integer status) throws Exception;
	/**
	 * 新增，修改，删除实体
	 * @param hfunctions 实体集合
	 * @param delIds     被删除ids
	 * @param operId     session userId
	 */
	void saveOrUpdateOrDel(List<HfunctionVO> hfunctions, String delIds) throws Exception;

}
