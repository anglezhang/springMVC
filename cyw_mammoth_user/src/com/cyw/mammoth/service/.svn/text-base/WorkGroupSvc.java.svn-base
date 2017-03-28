package com.cyw.mammoth.service;

import java.util.List;

import com.cyw.common.base.service.BaseSvc;
import com.cyw.mammoth.bean.HfunctionWorkgroupList;
import com.cyw.mammoth.bean.WorkGroup;
import com.cyw.mammoth.vo.HfunctionVO;
import com.cyw.mammoth.vo.WorkGroupVO;

public interface WorkGroupSvc extends BaseSvc<WorkGroup, Integer> {

	List<HfunctionVO> findFunctionListBy(String funcType, String groupId) throws Exception;
	/**
	 * 根据id数组删除对应的实体（逻辑）
	 * @param ids 删除id数组
	 * @param status 0 有效 1 无效
	 */
	void updateStatusById(String[] ids,Integer status) throws Exception;
	/**
	 * 新增，修改，删除实体
	 * @param workGroups 实体集合
	 * @param delIds           被删除的ids
	 */
	void saveOrUpdateOrDel(List<WorkGroup> workGroups, String delIds) throws Exception;
	/**
	 * 保存授权
	 * @param hfunctionWorkgroupLists
	 * @param workGroupId
	 * @param hfunctionGroup
	 * @throws Exception 
	 */
	void saveWorkGroupAuth(List<HfunctionWorkgroupList> hfunctionWorkgroupLists , String workGroupId , String hfunctionGroup) throws Exception;
	/**
	 * 根据条件查询列表
	 * @param status
	 * @return
	 * @throws Exception
	 */
	List<WorkGroupVO> findListBy(Integer status) throws Exception;
}
