package com.cyw.mammoth.service;

import java.util.List;

import com.cyw.common.base.service.BaseSvc;
import com.cyw.mammoth.bean.Hbuilding;
import com.cyw.mammoth.vo.HbuildingVo;

public interface HbuildingSvc extends BaseSvc<Hbuilding, Integer> {
	/**
	 * 获取建筑物列表 包含建筑物下房型数量
	 * @param status   状态（0有效  1无效）
	 * @return
	 */
	List<HbuildingVo> getBuildList(Integer status);
	/**
	 * 根据id数组修改对应的实体的状态
	 * @param ids 删除id数组
	 * @param status 0 有效 1无效
	 */
	void updateStatusById(String[] ids, Integer status);
	
	List<?> getPrepareCall();
	/**
	 * 新增，修改，删除实体
	 * @param hbuildings 实体集合
	 * @param delIds     被删除ids
	 * @param operId     session userId
	 */
	void saveOrUpdateOrDel(List<Hbuilding> hbuildings, String delIds ,String operId);
	
}
