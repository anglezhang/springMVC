package com.cyw.mammoth.service;

import java.util.List;

import com.cyw.common.base.service.BaseSvc;
import com.cyw.mammoth.bean.Rooms;
import com.cyw.mammoth.vo.RoomDefineVo;

public interface HroomDefineSvc extends BaseSvc<Rooms, Integer> {
	/**
	 * 获取房间列表
	 * @param status 0有效  1无效
	 * @return
	 */
	List<RoomDefineVo> findListBy(Integer status);
	/**
	 * 修改房间状态
	 * @param ids 删除房间的id数组
	 * @param status 0有效  1无效
	 */
	void updateStatusById(String[] ids , Integer status) ;
	/**
	 * 更新房类数量
	 * @param buildId   建筑物代码
	 * @param roomType  房类
	 * @param flag      0 增加数量  1减少数量
	 */
	void updRoomTypeNumBy(String buildId, String roomType, Integer flag);
	/**
	 * 保存，修改，删除实体
	 * @param roomDefineVos  房间VO
	 * @param delIds         被删除的ids
	 * @param operId         sessionUser id
	 */
	void saveOrUpdateOrDel(List<Object> roomDefineVos, String delIds , String operId);

}
