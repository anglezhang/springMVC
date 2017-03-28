package com.zoomoor.jy.dao;

import java.util.List;

import com.zoomoor.common.base.dao.BaseDao;
import com.zoomoor.jy.entity.InfoDepotPosition;
import com.zoomoor.jy.entity.InfoDept;

public interface InfoDepotPositionDao extends BaseDao<InfoDepotPosition, Integer> {
	public List<InfoDepotPosition> getListByName(String name,Integer upid,InfoDept infoDept);
	public List<InfoDepotPosition> getChildById(Integer authid,Integer tpid);
	public String getPositionFullName(Integer pid);

}
