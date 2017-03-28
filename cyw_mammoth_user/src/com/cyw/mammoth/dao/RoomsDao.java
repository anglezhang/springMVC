package com.cyw.mammoth.dao;

import java.util.List;
import java.util.Map;

import com.cyw.common.base.dao.BaseDao;
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
 * @see RoomsDao.java
 * @since cyw-1.0
 */
public interface RoomsDao extends BaseDao<Rooms, Integer> {

	public List<Rooms> getRoomsList(String currStat);

	/**
	 * 查询房态信息
	 * 
	 * @param room
	 * @return
	 * @author yaochenglong
	 */
	public List<Rooms> getRoomsList(Map map);

	/**
	 * 查询房态图（房态图使用）
	 * 
	 * @param roomsSearchVo查询条件
	 * @return list 房态集合
	 * @author zhangzhenxing
	 * */
	public List<RoomStatusVo> getRoomsStat(RoomsSearchVo roomsSearchVo);

	public List<Rooms> getRooms();

	/**
	 * 获取入住登记可住房列表 <功能详细描述>
	 * 
	 * @param roomsSearchVo
	 * @return
	 * @see RoomsDao.java
	 */
	public List getRoomsListTypeState(RoomsSearchVo roomsSearchVo);

	/**
	 * 获取当前房态的房间列表 <功能详细描述>
	 * 
	 * @return
	 * @see RoomsDao.java
	 */
	public List getNowRoomsTypeNum(RoomsTypeSearchVo RoomsTypeSearchVo);

	/**
	 * 获取所有楼层信息
	 * 
	 * @return
	 */
	public List<String> getAllFloorNo();

	/**
	 * 散客预定留房查询
	 * 
	 * @param roomsSearchVo
	 *            查询条件
	 * @param ipflag
	 *            是否含ip，true：含ip false：不含ip
	 * @return
	 */
	public Map<String, List<Rooms>> getRemainRooms(String checkId,
			RoomsSearchVo roomsSearchVo, boolean ipflag);

	/**
	 * 查询该订单号下所有的留房
	 * 
	 * @param checkId
	 * @return
	 */
	public List<Rooms> getRoomsByCheckId(String checkId, String reachDate,
			String leaveDate);

	/**
	 * @描述：查询建筑物下所有楼层
	 * @param buildId
	 *            建筑物ID
	 * */
	List<String> getFloorNoByBuildId(String buildId);
	
	/**
	 * @描述 按照房间类型统计房间数量
	 * */
	List<HroomTypeVo> getRoomCountByType(String startDate, String endDate,String buildCode);
	
	/**
	 * @描述：房类管理查询
	 * */
	List<RoomStatusVo> getManagersRooms(RoomsSearchVo roomsSearchvo);
	
	/**
	 * @描述：维修冻结房态色块列表
	 * @param roomsSearchvo 搜索条件
	 * @return 色块列表
	 * */
	List<RoomStatusVo> getFixFrozenRooms(RoomsSearchVo roomsSearchvo);
	
	/**
	 * @描述 获取客态留房信息
	 * @param roomId 房间ID
	 * @param startDate 开始日期
	 * @param endDate 截至日期
	 * @return 留房信息
	 * */
	Map<String, Object> getLeaveRoomInfo(String roomId,
			String startDate, String endDate);
	
	/**
	 * @描述 获取预抵信息
	 * @param roomId房间号
	 * @param startDate 开始日期
	 * */
	Map<String, Object> getWillComeRoomInfo(String roomId,
			String startDate);
	

	/**
	 * 查询当前可售房源统计信息
	 * @return
	 */
	public List getCurrAvailableForSaleRooms();
	
	/**
	 * 根据房间号判断房间是否存在
	 * @param room_id
	 * @return
	 */
	public boolean isExistsRooms(String room_id);
	
	/**
	 * @描述 过滤多余楼层
	 * */
	public List<RoomStatusVo> checkRoomStats(List<RoomStatusVo> list);
}
