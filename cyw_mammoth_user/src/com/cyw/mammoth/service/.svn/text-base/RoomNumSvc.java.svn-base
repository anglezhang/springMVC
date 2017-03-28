package com.cyw.mammoth.service;

import java.util.Date;
import java.util.List;

import org.hibernate.HibernateException;
import org.springframework.ui.Model;

import com.alibaba.fastjson.JSONArray;
import com.cyw.common.base.service.BaseSvc;
import com.cyw.mammoth.bean.RoomNum;

public interface RoomNumSvc extends BaseSvc<RoomNum, Integer> {
	public void updateById(RoomNum bean) throws HibernateException;

	public int updateBookRoomByChekcIdAndRoomType(Integer checkId,
			String roomTypeId, String roomIds, String currBookRoomId,
			String reachDate, String leaveDate);

	/**
	 * @描述 设置维修
	 * */
	boolean setFix(String roomIds, String startDate, String endDate,
			String reason, String type);

	/**
	 * @描述 ：获取维修冻结
	 * @param roomId
	 *            房号
	 * @param type
	 *            类型 2维修;3冻结
	 * @param active
	 *            0有效 1 无效
	 * @param startDate
	 *            开始日期
	 * @param endDate
	 *            结束日期
	 * */
	List<RoomNum> getFixFrozenInf(String roomId, String type, String active,
			String startDate, String endDate,Model model);
	
	/**
	 * @描述 修改房间维修冻结状态
	 * */
	boolean editFixfrozen(JSONArray array,String cancelIds);
	
	public void updateBookBoomNums(Integer checkId, Integer bookId, String roomTypeId,String roomIds, Date reachDate,Date leaveDate, List<RoomNum> roomNums);
}
