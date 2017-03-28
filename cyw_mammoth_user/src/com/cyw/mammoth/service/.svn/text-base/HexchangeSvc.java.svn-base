package com.cyw.mammoth.service;

import java.util.List;

import com.cyw.common.base.service.BaseSvc;
import com.cyw.mammoth.bean.Hexchange;

public interface HexchangeSvc extends BaseSvc<Hexchange, Integer> {

	/**
	 * 修改实体状态
	 * @param strings 修改实体ids
	 * @param status  状态 0有效 1无效
	 * @throws Exception
	 * @author Sixp
	 */
	void updateStatusById(String[] ids, Integer status)  throws Exception;
	/**
	 * 批量保存，修改，删除实体
	 * @param hconsumes  实体对象集合
	 * @param delIds  删除ids
	 * @throws Exception
	 * @author Sixp
	 */
	void saveOrUpdateOrDel(List<Hexchange> hexchanges, String delIds)  throws Exception;

}
