package com.cyw.mammoth.service;

import java.util.List;

import com.cyw.common.base.service.BaseSvc;
import com.cyw.mammoth.bean.Hfolk;

public interface HfolkSvc extends BaseSvc<Hfolk, Integer> {
	/**
	 * 根据条件查询列表
	* @param status 状态 0 有效 1无效
	* @return
	* @author Sixp
	 */
    List<Hfolk> findListBy(Integer status) throws Exception;
	/**
	 * 修改实体状态
	 * @param strings 修改实体ids
	 * @param status  状态 0有效 1无效
	 * @throws Exception
	 * @author Sixp
	 */
	void updateStatusById(String[] ids, Integer status) throws Exception;
	/**
	 * 批量保存，修改，删除实体
	 * @param hfolks  实体对象集合
	 * @param delIds  删除ids
	 * @throws Exception
	 * @author Sixp
	 */
	void saveOrUpdateOrDel(List<Hfolk> hfolks, String delIds)  throws Exception;
}
