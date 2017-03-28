package com.cyw.mammoth.service;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestParam;

import com.cyw.common.base.service.BaseSvc;
import com.cyw.mammoth.bean.Rooms;
import com.cyw.mammoth.vo.HroomTypeVo;
import com.cyw.mammoth.vo.RoomStatusVo;
import com.cyw.mammoth.vo.RoomsSearchVo;
import com.cyw.mammoth.vo.RoomsTypeSearchVo;

/**
 * <一句话功能简述> <功能详细描述>
 * 
 * @author litiangang@cyw.so
 * @version v-1.0
 * @see RoomsSvc.java
 * @since cyw-1.0
 */
public interface RoomsSvc extends BaseSvc<Rooms, Integer> {

	public List<Rooms> getRoomsList(String currStat);

	public List<Rooms> getRoomsList(Map map);

	public List<Rooms> getRooms();

	/**
	 * @描述：查询所属建筑物楼层
	 * */
	List<String> getFloorNoByBuildId(String buildId);

	/**
	 * @描述:根据查询条件反悔房态结果集 默认值：楼名是全部，楼层是全部，房型为空 表全部 ，开始日期为今天 结束日期为明天
	 * @param roomsSearchVo
	 *            查询条件对象结果集
	 * */
	public List getRoomsListTypeState(RoomsSearchVo roomsSearchVo);

	public List getNowRoomsTypeNum(RoomsTypeSearchVo RoomsTypeSearchVo);

	/**
	 * 获取所有楼层
	 * 
	 * @return
	 * @author yaochenglong
	 */
	public List<String> getAllFloorNo();

	public Map<String, List<Rooms>> getRemainRooms(String checkId,
			RoomsSearchVo roomsSearchVo, boolean ipflag);

	/**
	 * 获取当前订单号下选定的所有留房房间
	 * 
	 * @param checkId
	 * @param remainReachDate
	 *            抵店时间
	 * @param remainLeaveDate
	 *            离店时间
	 * @return
	 * @author yaochenglong
	 */
	public List<Rooms> getRoomsByCheckId(String checkId,
			String remainReachDate, String remainLeaveDate);

	/**
	 * @描述：将状态置为不结
	 * @param roomIds
	 * */
	boolean setUnclear(String roomIds);

	/**
	 * @描述：将状态置清洁未查
	 * @param roomIds
	 *            要改变状态的ID 多个ID 用","隔开
	 * */
	boolean setClearUncheck(String roomIds);

	/**
	 * @描述：将状态置清洁已查
	 * @param roomIds
	 *            要改变状态的ID 多个ID 用","隔开
	 * */
	boolean setClearheck(String roomIds);

	/**
	 * @描述：按照房间类型 统计时间段内 可用房间数量
	 * @param startDate
	 * @param buildCode 楼层ID
	 * */
	List<HroomTypeVo> getRoomCountByType(String startDate, String endDate,String buildCode);

	/**
	 * @描述 ：获取房间信息
	 * */
	List<Rooms> getSelRoomsInf(String roomIds);

	/**
	 * @描述：房态管理房态列表
	 * @param roomsSearchvo
	 *            搜索条件
	 * */
	List<RoomStatusVo> getManagersRooms(RoomsSearchVo roomsSearchvo);

	/**
	 * @描述 用回车键 直接查询房态
	 * @param roomsSearchvo
	 * @param roomId
	 *            房间ID
	 * */
	List<RoomStatusVo> getFastRooms(RoomsSearchVo roomsSearchvo, String roomId);

	/**
	 * @描述：维修冻结房态色块列表
	 * @param roomsSearchvo
	 *            搜索条件
	 * @return 色块列表
	 * */
	List<RoomStatusVo> getFixFrozenRooms(RoomsSearchVo roomsSearchvo);

	/**
	 * @描述 获取房屋预留信息
	 * @param roomId
	 *            房屋ID
	 * @param startDate
	 *            开始日期
	 * @param endDate
	 *            截至日期
	 * */
	Map<String, Object> getLeaveRoomInfo(String roomId, String startDate,
			String endDate);

	/**
	 * @描述 获取房屋预抵信息
	 * @param roomId
	 *            房屋ID
	 * @param startDate
	 *            开始日期
	 * */
	Map<String, Object> getWillComeRoomInfo(String roomId, String startDate);
	
	/**
	 * @描述 根据搜索条件过滤房间列表
	 * @param roomsSearchVo 搜索对象
	 * @param list 房态对象列表
	 * */
	List<RoomStatusVo> fiterRooms(RoomsSearchVo roomsSearchVo,List<RoomStatusVo> list);

}
