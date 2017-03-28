package com.cyw.mammoth.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cyw.common.base.service.impl.BaseSvcImpl;
import com.cyw.common.util.DateUtils;
import com.cyw.mammoth.bean.Hbuilding;
import com.cyw.mammoth.bean.HroomType;
import com.cyw.mammoth.bean.RoomNum;
import com.cyw.mammoth.bean.Rooms;
import com.cyw.mammoth.dao.HbuildingDao;
import com.cyw.mammoth.dao.HroomTypeDao;
import com.cyw.mammoth.dao.ParameterDao;
import com.cyw.mammoth.dao.RoomNumDao;
import com.cyw.mammoth.dao.RoomsDao;
import com.cyw.mammoth.service.RoomsSvc;
import com.cyw.mammoth.vo.HroomTypeVo;
import com.cyw.mammoth.vo.RoomStatusVo;
import com.cyw.mammoth.vo.RoomsSearchVo;
import com.cyw.mammoth.vo.RoomsTypeSearchVo;

@Service
public class RoomsSvcImpl extends BaseSvcImpl<Rooms, Integer> implements
		RoomsSvc {
	@Autowired
	private RoomsDao roomsDao;

	/**
	 * 参数
	 * */
	@Autowired
	private ParameterDao paramDao;

	/**
	 * 房间类型DAO
	 * */
	@Autowired
	private HroomTypeDao roomTypeDao;

	/**
	 * 建筑物DAO
	 * */
	@Autowired
	private HbuildingDao bulidDao;
	
	/**
	 * roomnumDAO
	 * */
	@Autowired
	private RoomNumDao roomNumDao;

	private Map<String, String> roomTypeNames;// 房间类型名称
	private Map<String, String> buildNames;// 建筑物名称

	@Autowired
	public void setBaseDao(RoomsDao roomsDao) {
		super.setBaseDao(roomsDao);
	}

	@Override
	public List<Rooms> getRoomsList(String currStat) {
		return roomsDao.getRoomsList(currStat);
	}

	@Override
	public List<Rooms> getRoomsList(Map map) {
		return roomsDao.getRoomsList(map);
	}

	@Override
	public List<Rooms> getRooms() {
		return roomsDao.getRooms();
	}

	@Override
	public List getRoomsListTypeState(RoomsSearchVo roomsSearchVo) {
		return roomsDao.getRoomsStat(roomsSearchVo);
	}

	@Override
	public List getNowRoomsTypeNum(RoomsTypeSearchVo RoomsTypeSearchVo) {
		return roomsDao.getNowRoomsTypeNum(RoomsTypeSearchVo);
	}

	@Override
	public List<String> getAllFloorNo() {
		return roomsDao.getAllFloorNo();
	}

	@Override
	public Map<String, List<Rooms>> getRemainRooms(String checkId,
			RoomsSearchVo roomsSearchVo, boolean ipflag) {
		return roomsDao.getRemainRooms(checkId, roomsSearchVo, ipflag);

	}

	@Override
	public List<Rooms> getRoomsByCheckId(String checkId, String reachDate,
			String leaveDate) {
		// TODO Auto-generated method stub
		return roomsDao.getRoomsByCheckId(checkId, reachDate, leaveDate);
	}

	@Override
	public List<String> getFloorNoByBuildId(String buildId) {
		return roomsDao.getFloorNoByBuildId(buildId);
	}

	@Override
	public boolean setUnclear(String roomIds) {
		// 修改状态为不洁
		String[] roomIDS = roomIds.split(",");
		List<Rooms> list = roomsDao.getList("roomId", roomIDS);
		for (Rooms r : list) {
			// 将第二位改为D
			String currStat = r.getCurrStat();
			String first = currStat.substring(0, 1);
			boolean isIp = paramDao.GetIPFlag();
			if (isIp)
				currStat = first + "D" + "P";
			else
				currStat = first + "D";
			r.setCurrStat(currStat);
			roomsDao.update(r);
		}
		return true;
	}

	@Override
	public boolean setClearUncheck(String roomIds) {
		String[] roomIDS = roomIds.split(",");
		List<Rooms> list = roomsDao.getList("roomId", roomIDS);
		for (Rooms r : list) {
			// 将第二位改为D
			boolean isIp = paramDao.GetIPFlag();
			if (isIp)
				r.setCurrStat("VCP");
			else
				r.setCurrStat("VC");
			roomsDao.update(r);
		}
		return true;
	}

	@Override
	public boolean setClearheck(String roomIds) {
		String[] roomIDS = roomIds.split(",");
		List<Rooms> list = roomsDao.getList("roomId", roomIDS);
		for (Rooms r : list) {
			boolean isIp = paramDao.GetIPFlag();
			if (isIp)
				r.setCurrStat("VCI");
			else
				r.setCurrStat("VC");
			roomsDao.update(r);
		}
		return true;
	}

	@Override
	public List<HroomTypeVo> getRoomCountByType(String startDate, String endDate,String buildCode) {

		return roomsDao.getRoomCountByType(startDate, endDate,buildCode);
	}

	@Override
	public List<Rooms> getSelRoomsInf(String roomIds) {
		String[] roomIdGrp = null;
		List<Rooms> result = null;
		if (StringUtils.isNotEmpty(roomIds)) {
			roomIdGrp = roomIds.split(",");
			result = roomsDao.getList("roomId", roomIdGrp);
		}
		if (result != null) {
			for (Rooms r : result) {
				String typeId = r.getRoomType();// 类型ID
				String buildId = r.getBuildId();
				if (StringUtils.isNotEmpty(typeId)) {
					String typeName = getTypeName(r.getRoomType());
					r.setRoomTypeName(typeName);
				}
				if (StringUtils.isNotEmpty(buildId)) {
					String buildName = getBulidName(buildId);
					r.setBuildName(buildName);
				}

			}
		}
		return result;
	}

	/**
	 * @描述 ：取类型名称
	 * @param typeId房间类型ID
	 * @return typeName
	 * */
	private String getTypeName(String typeId) {
		if (roomTypeNames == null || roomTypeNames.isEmpty()) {
			roomTypeNames = new HashMap<String, String>();
			List<HroomType> list = roomTypeDao.getAll();
			for (HroomType type : list) {
				String key = type.getCodeId();
				String value = type.getCodeNamec();
				if (StringUtils.isNotEmpty(key)
						&& StringUtils.isNotEmpty(value)) {
					roomTypeNames.put(key, value);
				}
			}
			return roomTypeNames.get(typeId);
		} else
			return roomTypeNames.get(typeId);
	}

	/**
	 * @描述：获取房间建筑物名称
	 * @param buildId
	 * @return buildName
	 * */
	private String getBulidName(String buildId) {
		if (buildNames == null || buildNames.isEmpty()) {
			buildNames = new HashMap<String, String>();
			List<Hbuilding> list = bulidDao.getAll();
			for (Hbuilding build : list) {
				String key = build.getCodeId();
				String value = build.getCodeNamec();
				if (StringUtils.isNotEmpty(key)
						&& StringUtils.isNotEmpty(value)) {
					buildNames.put(key, value);
				}
			}
			return buildNames.get(buildId);
		} else
			return buildNames.get(buildId);
	}

	@Override
	public List<RoomStatusVo> getManagersRooms(RoomsSearchVo roomsSearchvo) {

		return roomsDao.getManagersRooms(roomsSearchvo);
	}

	@Override
	public List<RoomStatusVo> getFastRooms(RoomsSearchVo roomsSearchvo,
			String roomId) {
		List<RoomStatusVo> rooms = getManagersRooms(roomsSearchvo);
		List<RoomStatusVo> room = new ArrayList<RoomStatusVo>();
		// 房间集合
		if (rooms.size() > 0) {
			int begin = rooms.size() / 2;
			// 两分法 加快迭代效率
			for (int i = 0; i < begin; i++) {
				RoomStatusVo roomVo = rooms.get(i);
				if (roomId.equalsIgnoreCase(roomVo.getRoomId())) {
					room.add(roomVo);
					return room;
				}
			}
			for (int i = rooms.size() - 1; i >= begin; i--) {
				RoomStatusVo roomVo = rooms.get(i);
				if (roomId.equalsIgnoreCase(roomVo.getRoomId())) {
					room.add(roomVo);
					return room;
				}
			}
		}
		return null;
	}

	@Override
	public List<RoomStatusVo> getFixFrozenRooms(RoomsSearchVo roomsSearchvo) {
		// TODO Auto-generated method stub
		return roomsDao.getFixFrozenRooms(roomsSearchvo);
	}

	@Override
	public Map<String, Object> getLeaveRoomInfo(String roomId,
			String startDate, String endDate) {
		
		return roomsDao.getLeaveRoomInfo(roomId, startDate, endDate);
	}

	@Override
	public Map<String, Object> getWillComeRoomInfo(String roomId,
			String startDate) {
		return roomsDao.getWillComeRoomInfo(roomId, startDate);
	}

	@Override
	public List<RoomStatusVo> fiterRooms(RoomsSearchVo roomsSearchVo,
			List<RoomStatusVo> list) {
		if(list==null){
			return null;
		}
		//不洁 未查
		List<RoomStatusVo> result = new ArrayList<RoomStatusVo>();
		String guestS = roomsSearchVo.getGuestStatus();//客态
		String roomStat = roomsSearchVo.getRoomStat();//房态
		if(StringUtils.isEmpty(guestS) && StringUtils.isEmpty(roomStat)){
			return list;
		}
		Long right = 0l;
		if(StringUtils.isNotEmpty(roomStat)){
			String[] roomStats = roomStat.split(",");
			for(String rs:roomStats){
				//OOI:1 OOO:2 OC: 3 VC 4
				long r = 0l;
				if(rs.equals("OOI")){
					r = 1l<<1;
				}
				if(rs.equals("OOO")){
					r = 1l<<2;
				}
				if(rs.equals("O")){
					r = 1l<<3;
				}
				if(rs.equals("V")){
					r = 1l<<4;
				}
				right += r;
			}
		}
		
		//循环
		for(RoomStatusVo vo:list){
			if(vo.getIsFloor()){
				result.add(vo);
			}
			else
			{
				//根据房态筛选
				if(fiterGuestStat(vo, guestS) 
					|| fiterRoomStatu(vo, right,guestS)){
					result.add(vo);
				}
			}
		}
		result = roomsDao.checkRoomStats(result);
		return result;
	}
	
	/**
	 * @描述 通过房态筛选
	 * @param vo 房间对象
	 * @param roomStat 房态
	 * @retrun 通过筛选 true 否则 false
	 * */
	private boolean fiterRoomStatu(RoomStatusVo vo,Long right,String guestS){
		if(StringUtils.isNotEmpty(guestS) && (vo.getRoomStat().contains("OC") 
				|| vo.getRoomStat().contains("OD"))){
			return fiterGuestStat(vo,guestS);
		}else if(StringUtils.isNotEmpty(guestS) && 
				(vo.getRoomStat().equalsIgnoreCase("OOI") || 
						vo.getRoomStat().equalsIgnoreCase("OOO") || 
						vo.getRoomStat().contains("V"))){
			return false;
		}
		if(right==0l){
			return true;
		}
		if((right&vo.getRoomStatRight()) != 0){
			return true;
		}
		return false;
	}
	
	/**
	 * @描述 客态筛选
	 * @param vo 房间对象
	 * @param guestS 客态
	 * @retrun 通过筛选 true 否则 false
	 * */
	private boolean fiterGuestStat(RoomStatusVo vo,String guestS){
		List<String> guestStat = vo.getGuestStat();
		if(guestStat==null){
			return false;
		}
		if(guestStat.contains(guestS)){
			return true;
		}
		return false;
	}
}
