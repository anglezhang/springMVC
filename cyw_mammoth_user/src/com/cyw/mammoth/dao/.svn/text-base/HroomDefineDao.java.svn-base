package com.cyw.mammoth.dao;


import java.util.List;

import com.cyw.common.base.dao.BaseDao;
import com.cyw.mammoth.bean.Rooms;
import com.cyw.mammoth.vo.RoomDefineVo;


public interface HroomDefineDao extends BaseDao<Rooms, Integer> {

	/**
	 * 获取房间列表
	 * @param status 0有效 1无效
	 * @return
	 */
	List<RoomDefineVo> findListBy(Integer status);
	/**
	 * 更新房类数量
	 * @param buildId   建筑物代码
	 * @param roomType  房类
	 * @param flag      0 增加数量  1减少数量
	 */
	void updRoomTypeNumBy(String buildId, String roomType, Integer flag);
}
