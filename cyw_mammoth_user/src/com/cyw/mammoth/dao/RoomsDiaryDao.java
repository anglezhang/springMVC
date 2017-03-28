package com.cyw.mammoth.dao;

import com.cyw.common.base.dao.BaseDao;
import com.cyw.mammoth.bean.RoomsDiary;

public interface RoomsDiaryDao extends BaseDao<RoomsDiary, Integer> {
	
	public void deleteRoomsDiary(Integer roomChkid,Integer checkId,String roomId);
	
	/**
	 * @描述 在该日期范围内房间是否占用
	 * @param roomIds 房间ID 多个房间用","隔开
	 * @param startDate 开始日期
	 * @param endDate 结束日期
	 * @return count 大于0 统计roomIds 在rooms_diary中有多少条数据 大于0 存在数据 等于0不存在
	 * */
	int getDiaryCount(String roomIds,String startDate, String endDate);
}
