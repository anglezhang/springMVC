package com.zoomoor.jy.service;

import java.util.List;

import com.zoomoor.admin.entity.SysUser;
import com.zoomoor.common.base.service.BaseSvc;
import com.zoomoor.jy.entity.InfoDepotPosition;
import com.zoomoor.jy.entity.InfoDept;

public interface InfoDepotPositionSvc extends BaseSvc<InfoDepotPosition, Integer> {

	public String getDepotPositionTreeJson(InfoDept infoDept);
	public InfoDepotPosition save(InfoDepotPosition position, Integer upId,SysUser sysUser);
	public InfoDepotPosition update(InfoDepotPosition position, Integer groupId);
	
	public List<InfoDepotPosition> getListByName(String typeName,Integer upid,InfoDept infoDept);
	
	public List<InfoDepotPosition> getChildById(Integer authid,Integer tpid);
	
	
	public String getPositionFullName(Integer pid);

}
