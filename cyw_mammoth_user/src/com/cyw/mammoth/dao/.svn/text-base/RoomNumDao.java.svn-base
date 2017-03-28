package com.cyw.mammoth.dao;

import java.util.Date;
import java.util.List;

import org.hibernate.HibernateException;

import com.cyw.common.base.dao.BaseDao;
import com.cyw.mammoth.bean.RoomNum;

public interface RoomNumDao extends BaseDao<RoomNum, Integer> {
	public void updateById (RoomNum bean) throws HibernateException;
	
	/**
	 * @Date 2015-11-06
	 * @描述：查询不可用空房信息
	 * @param roomId
	 *            房号
	 * @param startDate
	 *            查询开始日期
	 * @param endDate
	 *            截至日期
	 * @return 不可用信息
	 * */
	List<RoomNum> getNullRoomInf(String roomId,
			String startDate, String endDate);
	
	/**
	 * 删除当前订单当前方形之前留过房的集合
	 * @param checkId
	 * @param roomTypeId
	 * @return
	 */
	public int updateBookRoomByChekcIdAndRoomType(Integer checkId,String roomTypeId,String roomIds,String currBookRoomId,String reachDate,String LeaveDate);
	
	public void beginTranBegin();
	public void beginTranCommit();
	
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
	List<RoomNum> getFixFrozenInf(String roomId, String type,
			String active, String startDate, String endDate);
	
	/**
	 * 根据房间号和日期，查询从当前日期到输入日期间有没有留房、维修房、冻结房、网房、网房预定、网房留房
	 * @param room_id
	 * @param date
	 * @return
	 */
	public List getRoomNumByRoomAndDate(String room_id, Date startDate, Date endDate);
	
	/**
	 * 查询在住状态的房间存量信息
	 * @param room_id
	 * @return
	 */
	public List<RoomNum> getRoomNumByRoomId(String room_id);
	
	/**
	 * 查询房间存量统计信息
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public List getRoomNumByDate(String startDate, String endDate);
	
}
