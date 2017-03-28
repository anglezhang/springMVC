package com.cyw.mammoth.service;

import java.util.List;

import com.cyw.common.base.service.BaseSvc;
import com.cyw.mammoth.bean.Hcodes;

public interface CodeCareSvc extends BaseSvc<Hcodes, Integer> {
	/**
	 * 获取通用代码分类列表
	 * @return
	 * @throws Exception
	 */
	List<Hcodes> findGETypeList() throws Exception ;
	/**
	 * 获取特别代码子类列表
	 * @param flag 子类标识
	 * @param status  状态 0有效 1无效
	 * @return
	 */
	List<?> findSEListBy(String flag,Integer status) throws Exception ;
	/**
	 * 获取通用代码子类列表
	 * @param flag   分类标识
	 * @param status 0有效  1无效
	 * @return
	 */
	List<Hcodes> findGEListBy(String flag,Integer status) throws Exception ;
	/**
	 * 修改实体状态
	 * @param strings 修改实体ids
	 * @param status  状态 0有效 1无效
	 * @throws Exception
	 */
	void updateStatusById(String[] ids, Integer status)  throws Exception;
	/**
	 * 批量保存，修改，删除实体
	 * @param hcodes  实体对象集合
	 * @param delIds  删除ids
	 * @throws Exception
	 */
	void saveOrUpdateOrDel(List<Hcodes> hcodes, String delIds)  throws Exception;
	/**
	 * 根据标识查询对应的子项目
	 * @param flag  分类标识
	 * @return
	 */
	List<Hcodes> findAllListBy(String flag);

}
