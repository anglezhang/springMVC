package com.cyw.mammoth.dao;

import java.util.List;

import com.cyw.common.base.dao.BaseDao;
import com.cyw.mammoth.bean.Hbuilding;
import com.cyw.mammoth.vo.HbuildingVo;

public interface HbuildingDao extends BaseDao<Hbuilding, Integer> {
	
	/**
	 * 查询建筑物列表，包含建筑物下房型数量
	 * @param status     状态
	 * @return
	 */
	public List<HbuildingVo> getBuildList(Integer status);
	
	public List getPrepareCall();

	public void updateStatus(Integer id);
}
