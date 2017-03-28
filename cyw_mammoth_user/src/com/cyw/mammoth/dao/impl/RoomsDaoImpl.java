package com.cyw.mammoth.dao.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.aspectj.apache.bcel.generic.Type;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cyw.common.base.dao.impl.BaseDaoImpl;
import com.cyw.common.util.StrUtils;
import com.cyw.mammoth.bean.GstBill;
import com.cyw.mammoth.bean.Guestdoc;
import com.cyw.mammoth.bean.HroomType;
import com.cyw.mammoth.bean.Rooms;
import com.cyw.mammoth.cywenum.RoomStat;
import com.cyw.mammoth.dao.GstBillDao;
import com.cyw.mammoth.dao.GuestdocDao;
import com.cyw.mammoth.dao.RoomsDao;
import com.cyw.mammoth.vo.HroomTypeVo;
import com.cyw.mammoth.vo.RoomStatusVo;
import com.cyw.mammoth.vo.RoomsSearchVo;
import com.cyw.mammoth.vo.RoomsTypeSearchVo;
import com.sun.java.swing.plaf.windows.resources.windows;

@SuppressWarnings({ "unchecked", "rawtypes" })
@Repository
public class RoomsDaoImpl extends BaseDaoImpl<Rooms, Integer> implements
		RoomsDao {

	private Logger log = LoggerFactory.getLogger(RoomsDaoImpl.class);
	private static Map<String, String> hroomType;// 房类资料 (key code_id,value
	// codenamec)
	/**
	 * @描述 住客账务信息DAO
	 * */
	@Autowired
	private GstBillDao gstBiilDao;

	/**
	 * @描述 guestDoc 客户资料
	 * */
	@Autowired
	private GuestdocDao guestDao;

	@Override
	public List<Rooms> getRoomsList(String currStat) {
		String hq = "from Rooms bean where 1=1";
		if (StringUtils.isNotEmpty(currStat)) {
			currStat = "0";
		} else {
			hq += " and bean.currStat='" + currStat + "' order by bean.floorNo";
		}
		return this.createQueryList(hq);
	}

	/**
	 * map参数需要查看调用此方法的action，put多少，这里get多少
	 */
	@Override
	public List<Rooms> getRoomsList(Map map) {
		List<Rooms> roomsList = new ArrayList<Rooms>();
		// 时间区间内的可用房
		StringBuffer sb = new StringBuffer();
		sb.append("select * from rooms where 1=1 ");
		sb.append(" and room_id not in ");
		sb.append(" (");
		sb.append(" select distinct(room_id) from rooms_diary where 1=1 ");
		if (StringUtils.isNotEmpty(map.get("reachDate").toString())) {
			sb.append(" and datediff(day,hotel_date,'"
					+ map.get("reachDate").toString() + "')<=0");
		}
		if (StringUtils.isNotEmpty(map.get("leaveDate").toString())) {
			sb.append(" and datediff(day,hotel_date,'"
					+ map.get("leaveDate").toString() + "') >=0");
		}
		sb.append(" and flag in(0,1,2,3,4,5,6) ");
		sb.append(")");
		if (StringUtils.isNotEmpty(map.get("roomTypeId").toString())) {
			sb.append(" and room_type='" + map.get("roomTypeId").toString()
					+ "' ");
		}
		if (StringUtils.isNotEmpty(map.get("roomStat").toString())) {
			sb.append(" and curr_stat='" + map.get("roomStat").toString() + "'");
		}
		if (map.get("buildingName") != null
				&& StringUtils.isNotEmpty(map.get("buildingName").toString())) {
			sb.append(" and build_id = '" + map.get("buildingName").toString()
					+ "' ");
		}
		if (map.get("floorNo") != null
				&& StringUtils.isNotEmpty(map.get("floorNo").toString())) {
			sb.append(" and floor_no = '" + map.get("floorNo").toString()
					+ "' ");
		}
		if(map.get("roomCharacter") != null && StringUtils.isNotEmpty(map.get("roomCharacter").toString()) && !map.get("roomCharacter").toString().equals("0")){
			sb.append(" and (room_character & "+map.get("roomCharacter")+") = "+map.get("roomCharacter")+" ");
		}

		System.out.println(sb.toString());
		roomsList.addAll(this.getSession().createSQLQuery(sb.toString())
				.addEntity(Rooms.class).list());

		// 当前订单已经选中的房间
		StringBuffer choicedRoomSql = new StringBuffer();
		choicedRoomSql.append("select * from rooms where 1=1 ");
		choicedRoomSql.append(" and room_id in ");
		choicedRoomSql.append(" ( ");
		choicedRoomSql
				.append(" select distinct(room_id) from room_num r , book_room b where 1=1 ");
		if (null != map.get("reachDate")
				&& StringUtils.isNotEmpty(map.get("reachDate").toString())) {
			choicedRoomSql.append(" and datediff(day,r.reach_date,'"
					+ map.get("reachDate").toString() + "')<=0");
		}
		if (null != map.get("leaveDate")
				&& StringUtils.isNotEmpty(map.get("leaveDate").toString())) {
			choicedRoomSql.append(" and datediff(day,r.leave_date,'"
					+ map.get("leaveDate").toString() + "') >=0");
		}
		if (null != map.get("checkId")
				&& StringUtils.isNotEmpty(map.get("checkId").toString())) {
			choicedRoomSql.append(" and r.check_id='"
					+ map.get("checkId").toString() + "' ");
		}

		choicedRoomSql.append(" and r.flag = 0 ");
		choicedRoomSql.append(" and r.status = 0 ");
		if (null != map.get("bookId")
				&& StrUtils.isValidString(map.get("bookId").toString())) {
			choicedRoomSql.append(" and r.book_id='"
					+ map.get("bookId").toString() + "' ");
		}
		choicedRoomSql.append(" and r.check_id = b.check_id ");
		if (StringUtils.isNotEmpty(map.get("roomTypeId").toString())) {
			choicedRoomSql.append(" and b.roomtype_id='"
					+ map.get("roomTypeId").toString() + "' ");
		}
		choicedRoomSql.append(" ) ");
		roomsList.addAll(this.getSession()
				.createSQLQuery(choicedRoomSql.toString())
				.addEntity(Rooms.class).list());
		System.out.println(choicedRoomSql.toString());
		return roomsList;
	}

	@Override
	public List<Rooms> getRoomsByCheckId(String checkId, String reachDate,
			String leaveDate) {
		String sql = "select * from rooms r where r.room_id in (select room_id from room_num rn where rn.status=0 and rn.check_id ='"
				+ checkId
				+ "' and datediff(day,rn.reach_date,'"
				+ reachDate
				+ "')<=0  and datediff(day,rn.leave_date,'"
				+ leaveDate
				+ "') >=0)";
		return this.getSession().createSQLQuery(sql).addEntity(Rooms.class)
				.list();
	}

	@Override
	public List<Rooms> getRooms() {
		String hq = "select floorNo from Rooms bean group by floorNo";
		return this.createQueryList(hq);
	}

	@Override
	public List getRoomsListTypeState(RoomsSearchVo roomsSearchVo) {
		String hq = "select distinct a.room_id,a.curr_stat,a.build_id,a.room_type,a.floor_no,a.room_character from rooms a,rooms_diary b where 1=1";
		StringBuilder sb = new StringBuilder(hq);
		if (StringUtils.isNotEmpty(roomsSearchVo.getRadioroom())) {// 可买房、全部
			sb.append(" and a.curr_stat like '%" + roomsSearchVo.getRadioroom()
					+ "%'");
		}
		if (StringUtils.isNotEmpty(roomsSearchVo.getLouming())
				&& !"all".equals(roomsSearchVo.getLouming())) {// 楼名
			sb.append(" and a.build_id = '" + roomsSearchVo.getLouming() + "'");
		}
		if (StringUtils.isNotEmpty(roomsSearchVo.getLouceng())
				&& !"all".equals(roomsSearchVo.getLouceng())) {// 楼层
			sb.append(" and a.floor_no = '" + roomsSearchVo.getLouceng() + "'");
		}
		if (roomsSearchVo.getFangxin().length() != 2) {// 房型
			sb.append(" and a.room_type in (" + roomsSearchVo.getFangxin()
					+ ")");
		}
		if (StringUtils.isNotEmpty(roomsSearchVo.getFangjiantezheng())) {// 特征
			String roomCharacter = roomsSearchVo.getFangjiantezheng();
			String[] sp = roomCharacter.split(",");
			for (int i = 0; i < sp.length; i++) {
				int num = Integer.parseInt(sp[i]);
				sb.append(" and substring(a.room_character," + num + ",1)='1'");
			}
		}
		if (StringUtils.isNotEmpty(roomsSearchVo.getStartdate())
				&& StringUtils.isNotEmpty(roomsSearchVo.getEnddate())) {// 预定入住时间
			sb.append(" and DATEDIFF ( day , b.hotel_date , "
					+ roomsSearchVo.getStartdate()
					+ " )<=0 and DATEDIFF ( day , hotel_date , "
					+ roomsSearchVo.getEnddate() + " )>=0");
		}
		Query query = this.createTransformSqlQuery(sb.toString());
		return query.list();
	}

	/**
	 * 散客预定留房查询
	 * 
	 * @param roomsSearchVo
	 *            查询条件
	 * @param ipflag
	 *            是否含ip，true：含ip false：不含ip 特征未做处理，需要用到位运算
	 * @return
	 */
	@SuppressWarnings({ "unused", "unchecked", "rawtypes" })
	@Override
	public Map<String, List<Rooms>> getRemainRooms(String checkId,
			RoomsSearchVo roomsSearchVo, boolean ipflag) {
		Map<String, List<Rooms>> map = new HashMap<String, List<Rooms>>();
		StringBuffer sb = new StringBuffer();
		sb.append("select * from rooms r where r.room_id not in (select room_id from rooms_diary)and r.room_type='"
				+ roomsSearchVo.getFangxin()
				+ "' and  r.curr_stat ='"
				+ roomsSearchVo.getRadioroom() + "' ");
		if (StringUtils.isNotEmpty(roomsSearchVo.getLouceng())
				&& !roomsSearchVo.getLouceng().equals("-1")) {
			sb.append(" and r.floor_no = '" + roomsSearchVo.getLouceng() + "'");
		}
		if (StringUtils.isNotEmpty(roomsSearchVo.getLouming())
				&& !roomsSearchVo.getLouming().equals("-1")) {
			sb.append(" and r.build_id='" + roomsSearchVo.getLouming() + "'");
		}
		List<Rooms> rooms1 = this.getSession().createSQLQuery(sb.toString())
				.addEntity(Rooms.class).list();
		map.put("rooms1", rooms1);
		if (StringUtils.isNotEmpty(checkId)) {
			String sql2 = "select * from rooms r where r.room_id in (select room_id from rooms_diary where room_id in(select room_id from room_num where check_id="
					+ checkId
					+ " and DATEDIFF(DAY, getdate(), '"
					+ roomsSearchVo.getStartdate()
					+ "')<=0 and DATEDIFF(DAY,getdate(),'"
					+ roomsSearchVo.getEnddate() + "')>=0))";
			List<Rooms> rooms2 = this.getSession().createSQLQuery(sql2)
					.addEntity(Rooms.class).list();
			map.put("rooms2", rooms2);
		}
		return map;
	}

	/**
	 * 获取当前房态的房间列表 * {@inheritDoc}
	 */

	@Override
	public List getNowRoomsTypeNum(RoomsTypeSearchVo RoomsTypeSearchVo) {
		String sql = "select * from rooms a,room_diary b where 1=1";
		StringBuilder sb = new StringBuilder(sql);
		if (StringUtils.isNotEmpty(RoomsTypeSearchVo.getKongfang())) {// 如果选择空房
			sb.append(" and a.curr_stat like '%"
					+ RoomsTypeSearchVo.getKongfang() + "%'");
		}
		if (StringUtils.isNotEmpty(RoomsTypeSearchVo.getZaizhufang())) {// 如果是在住房
			sb.append(" and a.curr_stat like '%"
					+ RoomsTypeSearchVo.getZaizhufang()
					+ "%' <> 'OOO' and a.curr_stat <> 'OOI'");
		}
		if (StringUtils.isNotEmpty(RoomsTypeSearchVo.getWeixiufang())) {// 如果是维修房
			sb.append(" and a.curr_stat = '"
					+ RoomsTypeSearchVo.getWeixiufang() + "'");
		}
		if (StringUtils.isNotEmpty(RoomsTypeSearchVo.getDongjiefang())) {// 如果是冻结房
			sb.append(" and a.curr_stat = '"
					+ RoomsTypeSearchVo.getDongjiefang() + "'");
		}
		if (StringUtils.isNotEmpty(RoomsTypeSearchVo.getBujie())) {// 如果是不洁房
			sb.append(" and a.curr_stat like '%" + RoomsTypeSearchVo.getBujie()
					+ "%'");
		}
		if (StringUtils.isNotEmpty(RoomsTypeSearchVo.getWeicha())) {// 如果是未查房
			sb.append(" and a.curr_stat like '%"
					+ RoomsTypeSearchVo.getWeicha() + "%'");
		}
		if (StringUtils.isNotEmpty(RoomsTypeSearchVo.getZiyong())) {// 如果是自用房
			sb.append(" and ");
		}
		if (StringUtils.isNotEmpty(RoomsTypeSearchVo.getMianfei())) {// 如果是免费房

		}
		if (StringUtils.isNotEmpty(RoomsTypeSearchVo.getZhongdian())) {// 钟点房

		}
		if (StringUtils.isNotEmpty(RoomsTypeSearchVo.getYuli())) {// 预离房

		}
		if (StringUtils.isNotEmpty(RoomsTypeSearchVo.getYudi())) {// 预抵房

		}
		if (StringUtils.isNotEmpty(RoomsTypeSearchVo.getLiufang())) {// 留房

		}
		if (StringUtils.isNotEmpty(RoomsTypeSearchVo.getQianfei())) {// 欠费房

		}
		if (StringUtils.isNotEmpty(RoomsTypeSearchVo.getLianfang())) {// 联房

		}
		if (StringUtils.isNotEmpty(RoomsTypeSearchVo.getTuandui())) {// 团队房

		}
		if (StringUtils.isNotEmpty(RoomsTypeSearchVo.getNowlouming())
				&& !"all".equals(RoomsTypeSearchVo.getNowlouming())) {// 楼名

		}
		if (StringUtils.isNotEmpty(RoomsTypeSearchVo.getNowlouceng())
				&& !"all".equals(RoomsTypeSearchVo.getNowlouceng())) {// 楼层

		}
		if (RoomsTypeSearchVo.getNowfangxing().length() != 2) {// 房型

		}
		if (StringUtils.isNotEmpty(RoomsTypeSearchVo.getNowtezheng())) {// 特征
			String roomCharacter = RoomsTypeSearchVo.getNowtezheng();
			String[] sp = roomCharacter.split(",");
			for (int i = 0; i < sp.length; i++) {
				int num = Integer.parseInt(sp[i]);
				sb.append(" and substring(a.room_character," + num + ",1)='1'");
			}
		}
		if (StringUtils.isNotEmpty(RoomsTypeSearchVo.getDqftstartDate())
				&& StringUtils.isNotEmpty(RoomsTypeSearchVo.getDaftendDate())) {// 预定日期

		}
		Query query = this.createTransformSqlQuery(sb.toString());
		return query.list();
	}

	@Override
	public List<String> getAllFloorNo() {
		String sql = "select DISTINCT(floor_no) from rooms;";
		List<String> lists = this.getSession().createSQLQuery(sql)
				.addScalar("floor_no").list();
		return lists;
	}

	@Override
	public List<RoomStatusVo> getRoomsStat(RoomsSearchVo roomsSearchVo) {
		List<RoomStatusVo> roomsStat = null;
		// 根据rooms查询房间号
		// 根据rooms_diary查询房态 rooms_diary flag查询客态
		StringBuffer queryRoomsSQL = new StringBuffer(
				"SELECT r.room_id,r.room_type,r.build_id,r.floor_no");
		queryRoomsSQL
				.append(",r.curr_stat,r.room_character,rd.count,rod.room_stat FROM rooms r ");
		queryRoomsSQL.append("LEFT JOIN (");
		queryRoomsSQL
				.append("SELECT count(room_id) AS count,room_id FROM rooms_diary WHERE 1=1 ");
		queryRoomsSQL.append("AND datediff(day,hotel_date,'");
		queryRoomsSQL.append(roomsSearchVo.getStartdate() + "')<=0 ");
		queryRoomsSQL.append("  AND datediff(day,hotel_date,'");
		queryRoomsSQL.append(roomsSearchVo.getEnddate() + "')>=0");
		queryRoomsSQL.append(" GROUP BY room_id ) rd ON r.room_id=rd.room_id ");
		queryRoomsSQL
				.append("LEFT JOIN (SELECT room_id,room_stat FROM rooms_diary ");
		queryRoomsSQL
				.append("WHERE CONVERT(varchar(12),hotel_date,120) LIKE '");
		queryRoomsSQL.append(roomsSearchVo.getStartdate() + "%') ");
		queryRoomsSQL.append("rod ON r.room_id=rod.room_id");
		queryRoomsSQL.append(" WHERE r.status=0 AND 1=1 ");

		// 楼名
		if (StringUtils.isNotEmpty(roomsSearchVo.getLouming())) {
			queryRoomsSQL.append(" AND r.build_id='"
					+ roomsSearchVo.getLouming() + "' ");
		}
		// 楼层多选
		if (StringUtils.isNotEmpty(roomsSearchVo.getLouceng())) {
			queryRoomsSQL.append(" AND r.floor_no in("
					+ roomsSearchVo.getLouceng() + ") ");
		}
		// 房类
		if (StringUtils.isNotEmpty(roomsSearchVo.getFangxin())) {
			String fangxin = roomsSearchVo.getFangxin().trim();
			if (!fangxin.contains(",")) {
				queryRoomsSQL.append(" AND r.room_type in('" + fangxin + "') ");
			} else {

				String[] fangxinArray = fangxin.split(",");
				StringBuffer sb = new StringBuffer();
				for (String str : fangxinArray) {
					sb.append("'" + str + "',");
				}
				queryRoomsSQL.append(" AND r.room_type in("
						+ sb.substring(0, sb.length() - 1).toString() + ") ");
			}

		}
		// 清洁状态
		if (StringUtils.isNotEmpty(roomsSearchVo.getClearStatu())) {
			String clearStatu = roomsSearchVo.getClearStatu();
			if(clearStatu.contains(",")){
				String[] clears = clearStatu.split(",");
				queryRoomsSQL.append(" AND (r.curr_stat like '_"
						+ clears[0] + "%' or r.curr_stat like '_" + 
						clears[1] + "%') ");
			}else{
				queryRoomsSQL.append(" AND r.curr_stat like '_"
						+ clearStatu + "%' ");
			}
			
		}
		// 房态是可售 则有 是全部 则无
		if (!roomsSearchVo.getIsAll()) {
			queryRoomsSQL.append(" AND r.curr_stat like 'V%'");
		}
		// 可用全部房态 首位 是V N O
		if (StringUtils.isEmpty(roomsSearchVo.getRadioroom())) {
			queryRoomsSQL
					.append(" AND (r.curr_stat like 'V%' or r.curr_stat like 'N%' or r.curr_stat like 'O%') ");
		}
		// 房间特征
		queryRoomsSQL.append(" ORDER BY r.floor_no ASC ,r.room_id ASC");
		List<Object[]> list = this.getSession()
				.createSQLQuery(queryRoomsSQL.toString()).list();
		if (list.size() > 0)
			roomsStat = new ArrayList<RoomStatusVo>();
		String floorFlag = "-100";// 楼层标志
		Map<String, Object[]> guestInfos = getGuestInfo();// 客户
		Map<Integer, Integer> withRoomId = getWithRoomInfo();// with_id信息
		Map<String, String> leaveRoomInfo = getLeaveRoomInfo(roomsSearchVo);//留房信息
		Map<String, String> roomSourceInfo = getRoomSrcouceInfo();//房间客户来源信息
		// 获取房间在rooms_diary中的数量
		for (Object[] rsObj : list) {
			// 根据楼层标识 判断是楼层还是具体房间
			String thisFloorFlag = rsObj[3].toString().trim();// 本次楼层
			if (thisFloorFlag.equalsIgnoreCase(floorFlag)) {
				setRoomStat(roomsSearchVo, rsObj, list, roomsStat, guestInfos,
						withRoomId, leaveRoomInfo,roomSourceInfo);
			} else {
				floorFlag = thisFloorFlag;
				RoomStatusVo vo = new RoomStatusVo();
				vo.setIsFloor(true);
				vo.setFloorNo(thisFloorFlag);
				roomsStat.add(vo);
				setRoomStat(roomsSearchVo, rsObj, list, roomsStat, guestInfos,
						withRoomId, leaveRoomInfo,roomSourceInfo);
			}

		}
		// 根据房间特征 筛选
		if (StringUtils.isNotEmpty(roomsSearchVo.getFangjiantezhengID())) {
			roomsStat = fiterRoomCharater(roomsSearchVo, roomsStat);// 根据房型条件过滤
		}
		// 过滤多余楼层
		checkRoomStats(roomsStat);
		return roomsStat;
	}

	/**
	 * @描述 获取在住房客户信息
	 * @return Map<key,value> key : room_id value : 房间信息
	 * */
	private Map<String, Object[]> getGuestInfo() {
		// 查询在住房 客户信息
		String queryGuestIngSQL = "SELECT  g.grp_chkid,g.with_id,g.sex,check_id,"
				+ "g.leave_date,gr.pcount,g.gst_ori_id ,gr.room_id FROM guestdoc g "
				+ "LEFT JOIN  "
				+ "(SELECT SUM(payman_flag) as pcount,room_id from guestdoc where  chk_stat='I' GROUP BY room_id) "
				+ " gr ON gr.room_id=g.room_id "
				+ " WHERE 1=1 and gr.room_id IS NOT NULL "
				+ " AND g.chk_stat='I' AND g.chk_ext=1 "
				+ " ORDER BY g.room_id ";
		List<Object[]> list = this.getSession()
				.createSQLQuery(queryGuestIngSQL).list();
		Map<String, Object[]> result = new HashMap<String, Object[]>();
		for (Object[] obj : list) {
			if (obj[7] != null) {
				String roomId = obj[7].toString().trim();
				result.put(roomId, obj);
			}
		}
		return result;
	}

	/**
	 * @描述 获取联 or 团房信息
	 * */
	private Map<Integer, Integer> getWithRoomInfo() {
		String querySQL = "SELECT  count(room_id),with_id FROM guestdoc"
				+ " WHERE room_id IS NOT NULL and chk_stat='I' and chk_ext=1 GROUP BY "
				+ "with_id";
		List<Object[]> list = this.getSession().createSQLQuery(querySQL).list();
		Map<Integer, Integer> withRoomInfos = new HashMap<Integer, Integer>();
		for (Object[] obj : list) {
			Integer count = Integer.parseInt(obj[0].toString());
			Integer withId = Integer.parseInt(obj[1].toString());
			withRoomInfos.put(withId, count);
		}
		return withRoomInfos;
	}

	/**
	 * @描述 客户账务信息查询 以房间为单位进行查询
	 * @return 客户账务信息
	 * */
	private Map<String, Object[]> getGuestBillInfo() {
		String querySQL = "SELECT COUNT(gabill.billa_id) abill,gabill.room_id,"
				+ "COUNT(gbbill.billb_id) as bbill,gbbill.room_id FROM gst_bill gb "
				+ "";
		return null;
	}

	/**
	 * @描述 获取房源信息
	 * @author 张振兴
	 * @Date 2015-12-09
	 * */
	private Map<String,String> getRoomSrcouceInfo(){
		String querySQL = "SELECT hot.check_type,g.room_id from hgst_ori ho "
				+ " LEFT JOIN hgst_ori_type hot ON ho.ori_kind=hot.code_id "
				+ " LEFT JOIN guestdoc g on g.gst_ori_id=ho.code_id "
				+ " WHERE 1=1 and g.chk_stat='I' and g.chk_ext=1"
				+ " ORDER BY g.room_id ";
		Map<String, String> result = new HashMap<String, String>();
		List<Object[]> list = this.getSession().createSQLQuery(querySQL).list();
		for(Object[] obj:list){
			String roomId = obj[1].toString().trim();
			String type = obj[0].toString().trim();
			result.put(roomId, type);
		}
		return result;
	}
	/**
	 * @描述 根据时间端 查询在住房的 预抵 欲离 信息
	 * @author 张振兴
	 * @Date 2015-12-09
	 * @param roomsSearchVo
	 *            查询条件
	 * @return 返回客户信息 room_id hotel_date 酒店日期
	 * */
	private Map<String, String> getLeaveRoomInfo(RoomsSearchVo roomsSearchVo) {
		String queryLeaveSQL = "SELECT room_id,hotel_date FROM rooms_diary "
				+ " WHERE 1=1  " + " AND datediff(day,hotel_date,'"
				+ roomsSearchVo.getStartdate()
				+ "')<=0 AND  datediff(day,hotel_date,'"
				+ roomsSearchVo.getEnddate() + "')>=0 AND flag=0 "
				+ " GROUP BY room_id,hotel_date " + " ORDER BY hotel_date ASC";
		Map<String, String> result = new HashMap<String, String>();
		List<Object[]> list = this.getSession().createSQLQuery(queryLeaveSQL)
				.list();
		for (Object[] obj : list) {
			String roomId = obj[0].toString().trim();
			String hotelDate = obj[1].toString().trim();
			result.put(roomId, hotelDate);
		}
		return result;
	}

	/**
	 * @描述：设置当前房间对象状态
	 * @param roomsSearchVo
	 *            搜索对象
	 * @param rsObj
	 *            查询单个结果 rooms table的(下标按次序)
	 *            room_id,room_type,build_id,floor_no,curr_stat
	 * @param list
	 *            查询结果集
	 * @param roomsStat
	 *            返回对象集合
	 * */
	private void setRoomStat(RoomsSearchVo roomsSearchVo, Object[] rsObj,
			List<Object[]> list, List<RoomStatusVo> roomsStat,
			Map<String, Object[]> guestInfos, Map<Integer, Integer> withRoomId,
			Map<String, String> leaveRoomInfo,Map<String, String> roomSourceInfo) {

		RoomStatusVo vo = new RoomStatusVo();
		vo.setIsFloor(false);
		vo.setRoomId(rsObj[0].toString().trim());
		vo.setRoomType(getDataDic(rsObj[1].toString().trim()));
		vo.setRoomTypeCode(rsObj[1].toString().trim());
		vo.setBuildId(rsObj[2].toString().trim());
		vo.setFloorNo(rsObj[3].toString().trim());
		String roomChar = rsObj[5].toString().trim();
		// 清洁状态 第四位
		String roomStat;
		if (rsObj[4].toString().length() == 2) {
			roomStat = rsObj[4].toString().substring(1, 2);
		} else {
			roomStat = rsObj[4].toString().substring(1, 3);
		}

		if (StringUtils.isNotEmpty(roomChar)) {
			vo.setRoomCharacter(Integer.parseInt(roomChar));
		}
		// 查询全部
		if (roomsSearchVo.getIsAll()) {
			// 如果没有记录则为空房，如果有记录 房态取距离现在最近的
			// 如果是空房 则无客态记录 客态是 在住房 网房 有 抵和留
			// 如果现在是在住 将来是空 则显示 在住
			// 默认是空房 还是全部
			// TODO 房态
			if (rsObj[6] == null) {
				vo.setRoomStat(rsObj[4].toString().trim());
			} else {
				if (rsObj[7] != null) {
					String rsStat = rsObj[7].toString();
					if ("OOO".equals(rsStat) || "OOI".equals(rsStat)) {// 维修冻结
						vo.setRoomStat(rsObj[7].toString().trim());
					} else {// 在住房 或者 维修冻结
						vo.setRoomStat(rsObj[7].toString().trim()
								.substring(0, 1)
								+ roomStat);
					}
				} else {
					vo.setRoomStat(rsObj[4].toString().trim());
				}

			}
			setGuestStats(roomsSearchVo, vo, rsObj, guestInfos, withRoomId,
					leaveRoomInfo,roomSourceInfo);
			roomsStat.add(vo);
		} else {
			// 查询可用房 rooms_diary记录为0 则可用 或者时间段内有留房
			// 根据时间段查询 rooms_diary 获取房态和客态
			// 使用count 函数 结果肯定是一条
			if (rsObj[6] == null) {
				vo.setRoomStat(rsObj[4].toString().trim());
				// 设置客态
				roomsStat.add(vo);
			}
		}

	}

	/**
	 * @描述:查询数据字典 根据对应key 返回对应value
	 * */
	private String getDataDic(String key) {
		if (hroomType == null) {
			hroomType = new HashMap<String, String>();
			String querySql = "SELECT code_id,code_namec FROM hroom_type ORDER BY code_id DESC";
			List<Object[]> list = this.getSession().createSQLQuery(querySql)
					.list();
			for (Object[] rsObj : list) {
				hroomType.put(rsObj[0].toString().trim(), rsObj[1].toString()
						.trim());
			}
		}
		return hroomType.get(key);
	}

	/**
	 * @描述：客态 抵、留、离查询
	 * @param roomsSearchVo
	 *            搜索条件
	 * @param vo
	 *            房态图对象
	 * @param rsObj
	 *            查询返回对象
	 * */
	private void setGuestStats(RoomsSearchVo roomsSearchVo, RoomStatusVo vo,
			Object[] rsObj, Map<String, Object[]> guestInfos,
			Map<Integer, Integer> withRoomId, Map<String, String> leaveRoomInfo
			,Map<String, String> roomSourceInfo) {
		// 如果维修 OOO or 在住房不判断OOI
		String roomStat = vo.getRoomStat().substring(0, 2);// 截取前2位
		List<String> guestStat = new ArrayList<String>();// 客态
		if ("OO".equalsIgnoreCase(roomStat)) {
			return;
			// 如果是空房V 判断是否可用
		} else if (roomStat.substring(0, 1).equalsIgnoreCase("V")) {
			if (rsObj[6] != null) {
				vo.setIsUsefull(false);
			} else {
				vo.setIsUsefull(true);
			}
			// 在住房 客态
		} else if ("OC".equalsIgnoreCase(roomStat)
				|| "OD".equalsIgnoreCase(roomStat)) {
			// 1.联房 团 判断 当前客人资料 在住房间资料
			try {
				Object[] guestInf = guestInfos.get(vo.getRoomId());
				if (guestInf != null) {
					Integer whitId = guestInf[1] == null ? 0 : Integer
							.parseInt(guestInf[1].toString().trim());
					;// withID
					Integer grp_checkId = guestInf[0] == null ? 0 : Integer
							.parseInt(guestInf[0].toString().trim());// 团代
																		// check_id
					Boolean payManFlag = Integer.parseInt(guestInf[5]
							.toString()) > 0 ? true : false;
					if (StringUtils.isNotEmpty(guestInf[2].toString())) {
						vo.setGender(guestInf[2].toString().trim());
					}
					vo.setGrpCheckId(grp_checkId.toString());
					vo.setWithID(whitId.toString());
					// 团chekc_id 不为0 则为团
					if (grp_checkId != 0) {
						guestStat.add(RoomStat.TUAN.toString());
					} else if (grp_checkId == 0 && whitId != 0) {
						Integer withCount = withRoomId.get(whitId);
						if (withCount != null && withCount > 1) {
							guestStat.add(RoomStat.LIAN.toString());
						}
					}
					// 免费房 自留房 先暂停
					// 欠费 计算 A账务 B账务
					if (whitId != null) {
						String queryBillSql = "SELECT count(*),"
								+ "(SELECT count(*) FROM gst_bill gb "
								+ "LEFT OUTER JOIN guestdoc gdoc  ON gdoc.billb_id=gb.bill_id "
								+ " WHERE gdoc.payman_flag=1 AND gdoc.with_id="
								+ whitId
								+ " AND gdoc.room_id='"
								+ vo.getRoomId()
								+ "' AND (gb.borrow-gb.lent-gb.auth_balance-gb.limit)<0 )  FROM gst_bill gb  "
								+ "LEFT OUTER JOIN guestdoc gdoc ON  gdoc.billa_id=gb.bill_id "
								+ "WHERE gdoc.with_id="
								+ whitId
								+ " AND gdoc.room_id='"
								+ vo.getRoomId()
								+ "' AND (gb.borrow-gb.lent-gb.auth_balance-gb.limit)<0";
						List<Object[]> bills = this.getSession()
								.createSQLQuery(queryBillSql).list();
						Object[] bill = bills.get(0);
						Double Abill = 0.0d;
						if (bill[0] != null) {
							Abill = Double.parseDouble(bill[0].toString());
						}
						Double Bbill = 0.0d;
						if (bill[1] != null) {
							Bbill = Double.parseDouble(bill[1].toString());
						}
						// 是否付费人 1 是 0 否
						if (payManFlag) {
							// 如果存在付费人 既查看A账 也查看B账
							if (Abill > 0 && Bbill > 0) {
								guestStat.add(RoomStat.QIAN.toString());
							}
						} else {
							if (Abill > 0) {
								guestStat.add(RoomStat.QIAN.toString());
							}
						}
					}
					// 预离 如果是今天 离开则为预离
					if(guestInf[4]!=null){
						String leaveDate = guestInf[4].toString().trim()
								.substring(0, 10);
						if (leaveDate.equals(roomsSearchVo.getStartdate())) {
							guestStat.add(RoomStat.LI.toString());
						}
					}
					// 添加客态信息
					Integer checkId = Integer.parseInt(guestInf[3].toString()
							.trim());
					vo.setCheckId(checkId);
					vo.setGuestStat(guestStat);
					String gstOriId = guestInf[6]==null?null:guestInf[6].toString();//客人来源
					// 客源查询
					if (gstOriId != null) {
						String srcType = roomSourceInfo.get(vo.getRoomId());
						if (srcType!=null) {
							Integer checkType = Integer.parseInt(srcType);
							switch (checkType) {
							case 3:// 钟点房
								guestStat.add(RoomStat.ZHONG.toString());
								break;
							case 4:// 免费
								guestStat.add(RoomStat.MIAN.toString());
								break;
							case 5:// 自用
								guestStat.add(RoomStat.ZI.toString());
								break;
							}
						}
					}
					// 2.留房
					String hotelDate = leaveRoomInfo.get(vo.getRoomId());
					// 当天留房 则为抵 否则为留
					if (hotelDate != null) {
						hotelDate = hotelDate.substring(0, 10);
						if (roomsSearchVo.getStartdate().equals(hotelDate)) {
							guestStat.add(RoomStat.DI.toString());
						} else {
							guestStat.add(RoomStat.LIU.toString());
						}
					}
					vo.setGuestStat(guestStat);
				}
				
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				log.error("设置客户信息出错 roomId " + vo.getRoomId());
			}
			

		}
	}

	/**
	 * @描述：剔除没有房间的楼层 和楼层相等的楼层
	 * */
	@Override
	public List<RoomStatusVo> checkRoomStats(List<RoomStatusVo> list) {
		if (list == null)
			return list;
		List<RoomStatusVo> floors = new ArrayList<RoomStatusVo>();// 楼层集合
		for (RoomStatusVo vo : list) {
			if (vo.getIsFloor()) {
				floors.add(vo);
			}
		}
		// 剔除没有房间的楼层
		for (RoomStatusVo floor : floors) {
			int count = 0;// 楼层房间数目统计
			for (RoomStatusVo vo : list) {
				if (!vo.getIsFloor()) {
					if (vo.getFloorNo().equalsIgnoreCase(floor.getFloorNo()))
						count++;
				}
			}
			if (count == 0) {
				list.remove(floor);
			}
		}

		return list;
	}

	/**
	 * @描述：根据房间特性过滤
	 * @param roomsSearchVo
	 *            搜索条件
	 * */
	private List<RoomStatusVo> fiterRoomCharater(RoomsSearchVo roomsSearchVo,
			List<RoomStatusVo> vos) {
		String roomChar = roomsSearchVo.getFangjiantezhengID();
		List<RoomStatusVo> resultVos = new ArrayList<RoomStatusVo>();
		Long roomRight = 0l;
		// 计算权值
		if (StringUtils.isNotBlank(roomChar)) {
			if (roomChar.contains(",")) {
				String[] chars = roomChar.split(",");
				for (String str : chars) {
					Integer value = Integer.parseInt(str);
					roomRight += 1L << value;
				}
			} else {
				roomRight = 1l << Integer.parseInt(roomChar);
			}
		}
		//非空判断
		if(vos!=null){
			for (RoomStatusVo vo : vos) {
				if (!vo.getIsFloor()) {
					Integer roomCharacter = vo.getRoomCharacter();
					long result = roomRight & roomCharacter;
					if (result == roomRight) {
						resultVos.add(vo);
					}
				} else {
					resultVos.add(vo);
				}
			}
		}
		return resultVos;
	}

	@Override
	public List<String> getFloorNoByBuildId(String buildId) {
		StringBuffer querySQL = new StringBuffer(
				"SELECT DISTINCT r.floor_no from rooms r "
						+ " JOIN hbuilding h ON r.build_id=h.code_id ");
		if (!StringUtils.isEmpty(buildId)) {
			querySQL.append(" WHERE h.code_id='" + buildId + "' ");
		}
		querySQL.append(" ORDER BY r.floor_no ASC");
		return this.getSession().createSQLQuery(querySQL.toString()).list();
	}

	@Override
	public List<HroomTypeVo> getRoomCountByType(String startDate,
			String endDate, String buildCode) {
		StringBuffer querySQL = new StringBuffer();
		List<HroomTypeVo> result = new ArrayList<HroomTypeVo>();
		// 统计可用房
		querySQL.append("SELECT COUNT(r.room_type) AS c,t.code_namee,t.code_namec,t.code_id FROM rooms r ");
		querySQL.append(" LEFT JOIN hroom_type t ON r.room_type=t.code_id ");
		querySQL.append(" WHERE 1=1  AND r.room_id NOT IN");
		querySQL.append("(SELECT DISTINCT room_id FROM rooms_diary ");
		querySQL.append(" WHERE  1=1 ");
		if (StringUtils.isNotEmpty(startDate)) {
			querySQL.append(" AND datediff(day,hotel_date,'" + startDate
					+ "')<=0 ");
		}
		if (StringUtils.isNotEmpty(endDate)) {
			querySQL.append("  AND datediff(day,hotel_date,'" + endDate
					+ "')>=0 ");
		}

		querySQL.append(")  ");
		if (StringUtils.isNotEmpty(buildCode)) {
			querySQL.append("  AND r.build_id='" + buildCode + "' ");
		}
		querySQL.append(" GROUP BY t.code_id,t.code_namee,t.code_namec");
		// 查询全部
		StringBuffer queryAll = new StringBuffer(
				"SELECT 0 AS c ,code_namee,code_namec,code_id FROM hroom_type WHERE 1=1");
		// 建筑物判断
		if (StringUtils.isNotEmpty(buildCode)) {
			queryAll.append("  AND building_id='" + buildCode + "' ");
		}
		List<Object[]> list = this.getSession()
				.createSQLQuery(querySQL.toString()).list();
		StringBuffer types = new StringBuffer();
		for (Object[] obj : list) {
			HroomTypeVo typeVo = new HroomTypeVo();
			typeVo.setCount(Integer.parseInt(obj[0].toString().trim()));
			typeVo.setCode(obj[1].toString().trim());
			typeVo.setName(obj[2].toString().trim());
			typeVo.setId(obj[3].toString().trim());
			result.add(typeVo);
			types.append("'" + obj[3].toString().trim() + "',");
		}
		if (StringUtils.isNotEmpty(types.toString())) {

			queryAll.append(" AND code_id NOT IN ("
					+ types.toString().substring(0,
							types.toString().length() - 1) + ")");
		}

		List<Object[]> allList = this.getSession()
				.createSQLQuery(queryAll.toString()).list();
		// 添加剩余类型
		for (Object[] obj : allList) {
			HroomTypeVo typeVo = new HroomTypeVo();
			typeVo.setCount(Integer.parseInt(obj[0].toString().trim()));
			typeVo.setCode(obj[1].toString().trim());
			typeVo.setName(obj[2].toString().trim());
			typeVo.setId(obj[3].toString().trim());
			result.add(typeVo);
		}
		return result;
	}

	@Override
	public List<RoomStatusVo> getManagersRooms(RoomsSearchVo roomsSearchvo) {
		List<RoomStatusVo> rooms = new ArrayList<RoomStatusVo>();
		// 查询当前房间状态
		StringBuffer querySQL = new StringBuffer(
				"SELECT room_id,floor_no,code_namec,room_stat,room_character,build_id,room_type FROM ("
						+ "SELECT r.room_id,r.floor_no,h.code_namec"
						+ ",SUBSTRING(rd.room_stat,1,1)+SUBSTRING(r.curr_stat,2,2) as room_stat"
						+ ",room_character,build_id,room_type from rooms r LEFT JOIN "
						+ "rooms_diary rd ON "
						+ "rd.room_id=r.room_id "
						+ "LEFT JOIN hroom_type h "
						+ "ON r.Room_type=h.code_id "
						+ "WHERE DATEDIFF(day,rd.hotel_date,'"
						+ roomsSearchvo.getStartdate()
						+ "')<=0 "
						+ "AND datediff(day,rd.hotel_date,'"
						+ roomsSearchvo.getStartdate()
						+ "')>=0 AND r.status=0 and rd.flag=1 "
						+ " UNION ALL "
						+ "SELECT r.room_id,r.floor_no,h.code_namec,r.curr_stat as room_stat,room_character,build_id,room_type FROM  rooms r LEFT JOIN "
						+ "hroom_type h "
						+ "ON r.Room_type=h.code_id "
						+ "WHERE r.status=0 AND r.room_id NOT IN (select r.room_id from rooms r LEFT JOIN "
						+ "rooms_diary rd ON "
						+ "rd.room_id=r.room_id "
						+ " WHERE DATEDIFF(day,rd.hotel_date,'"
						+ roomsSearchvo.getStartdate()
						+ "')<=0 "
						+ " AND datediff(day,rd.hotel_date,'"
						+ roomsSearchvo.getStartdate()
						+ "')>=0 )  ) rs  WHERE 1=1 ");
		// 建筑物筛选
		if (StringUtils.isNotEmpty(roomsSearchvo.getLouming())) {
			querySQL.append(" AND build_id='" + roomsSearchvo.getLouming()
					+ "' ");
		}
		// 楼层筛选
		if (StringUtils.isNotEmpty(roomsSearchvo.getLouceng())) {
			String louceng = roomsSearchvo.getLouceng();
			if (!louceng.contains(",")) {
				querySQL.append(" AND floor_no in('"
						+ roomsSearchvo.getLouceng() + "') ");
			} else {
				String[] loucengArray = louceng.split(",");
				StringBuffer sb = new StringBuffer();
				for (String str : loucengArray) {
					sb.append("'" + str + "',");
				}
				querySQL.append(" AND floor_no in("
						+ sb.substring(0, sb.length() - 1).toString() + ") ");
			}

		}
		// 房间类型
		if (StringUtils.isNotEmpty(roomsSearchvo.getFangxin())) {
			String fangxin = roomsSearchvo.getFangxin().trim();
			if (!fangxin.contains(",")) {
				querySQL.append(" AND room_type in('" + fangxin + "') ");
			} else {

				String[] fangxinArray = fangxin.split(",");
				StringBuffer sb = new StringBuffer();
				for (String str : fangxinArray) {
					sb.append("'" + str + "',");
				}
				querySQL.append(" AND room_type in("
						+ sb.substring(0, sb.length() - 1).toString() + ") ");
			}

		}
		// 空房 or 在住房
		if (StringUtils.isNotEmpty(roomsSearchvo.getRoomStatu())) {
			if (!"V,O".equalsIgnoreCase(roomsSearchvo.getRoomStatu())) {
				querySQL.append(" AND room_stat like '"
						+ roomsSearchvo.getRoomStatu() + "%' ");
			}
		}
		// 清洁状态
		if (StringUtils.isNotEmpty(roomsSearchvo.getClearStatu())) {
			String clearStatu = roomsSearchvo.getClearStatu();
			if(clearStatu.contains(",")){
				String[] clears = clearStatu.split(",");
				querySQL.append(" AND (room_stat like '_"
						+ clears[0] + "%' or room_stat like '_" + 
						clears[1] + ") ");
			}else{
				querySQL.append(" AND room_stat like '_"
						+ clearStatu + "%' ");
			}
			
		}
		// 楼层 房态 房间类型 特征
		querySQL.append(" ORDER BY floor_no ASC ,room_id ASC");
		List<Object[]> results = this.getSession()
				.createSQLQuery(querySQL.toString()).list();
		// 迭代处理结果集
		String floorFlag = "-100";// 楼层标志
		for (Object[] obj : results) {
			String thisFloorFlag = obj[1].toString().trim();// 本次楼层
			if (thisFloorFlag.equalsIgnoreCase(floorFlag)) {
				setManagerRoomPram(obj, rooms);
			} else {
				floorFlag = thisFloorFlag;
				RoomStatusVo vo = new RoomStatusVo();
				vo.setIsFloor(true);
				vo.setFloorNo(thisFloorFlag);
				rooms.add(vo);
				// 添加房屋
				setManagerRoomPram(obj, rooms);
			}

		}
		if (StringUtils.isNotEmpty(roomsSearchvo.getFangjiantezhengID())) {
			rooms = fiterRoomCharater(roomsSearchvo, rooms);// 根据房型条件过滤
		}
		// 过滤多余楼层
		checkRoomStats(rooms);
		return rooms;
	}

	/**
	 * @描述 设置房态管理 房屋属性
	 * */
	private void setManagerRoomPram(Object[] obj, List<RoomStatusVo> rooms) {
		RoomStatusVo room = new RoomStatusVo();
		room.setRoomId(obj[0].toString().trim());
		room.setFloorNo(obj[1].toString().trim());
		room.setRoomType(obj[2].toString().trim());
		// 空房和赃房 判断
		room.setRoomStat(obj[3].toString().trim());
		room.setRoomCharacter(Integer.parseInt(obj[4].toString().trim()));
		room.setIsFloor(false);
		rooms.add(room);
	}

	@Override
	public List<RoomStatusVo> getFixFrozenRooms(RoomsSearchVo roomsSearchvo) {
		StringBuffer querySQL = new StringBuffer(
				"SELECT room_id,floor_no,code_namec,case curr_stat when 'OCI' then 'VCI' when"
				+ " 'ODP' then 'VCI' when 'OCP' then 'VCI' else curr_stat end"
						+ ",room_character,build_id,room_type FROM (");
		querySQL.append("SELECT r.room_id,r.floor_no,r.curr_stat,"
				+ "h.code_namec,r.room_character,r.build_id,r.room_type FROM rooms r ");
		querySQL.append("LEFT JOIN hroom_type h ON r.room_type=h.code_id ");
		querySQL.append("WHERE 1=1 and r.room_id NOT IN  ");
		querySQL.append("(SELECT room_id FROM rooms_diary where 1=1");
		querySQL.append(" AND DATEDIFF(day,hotel_date,'");
		querySQL.append(roomsSearchvo.getStartdate()
				+ "')<=0 AND DATEDIFF(day,hotel_date,'");
		querySQL.append(roomsSearchvo.getEnddate() + "')>=0 ) UNION ALL ");
		querySQL.append("SELECT DISTINCT r.room_id,r.floor_no,rd.room_stat AS curr_stat"
				+ ",h.code_namec,r.room_character,r.build_id,r.room_type FROM rooms r ");
		querySQL.append("LEFT JOIN rooms_diary rd ON rd.room_id=r.room_id ");
		querySQL.append("LEFT JOIN hroom_type h ON r.room_type=h.code_id ");
		querySQL.append("WHERE (rd.room_stat='OOO' OR rd.room_stat='OOI') AND ");
		querySQL.append(" DATEDIFF(day,rd.hotel_date,'");
		querySQL.append(roomsSearchvo.getStartdate()
				+ "') <=0 AND DATEDIFF(day,hotel_date,'");
		querySQL.append(roomsSearchvo.getEnddate() + "')>=0 )  rs WHERE 1=1 ");
		// 建筑物筛选
		if (StringUtils.isNotEmpty(roomsSearchvo.getLouming())) {
			querySQL.append(" AND build_id='" + roomsSearchvo.getLouming()
					+ "' ");
		}
		// 楼层筛选
		if (StringUtils.isNotEmpty(roomsSearchvo.getLouceng())) {
			String louceng = roomsSearchvo.getLouceng();
			if (!louceng.contains(",")) {
				querySQL.append(" AND floor_no in('"
						+ roomsSearchvo.getLouceng() + "') ");
			} else {
				String[] loucengArray = louceng.split(",");
				StringBuffer sb = new StringBuffer();
				for (String str : loucengArray) {
					sb.append("'" + str + "',");
				}
				querySQL.append(" AND floor_no in("
						+ sb.substring(0, sb.length() - 1).toString() + ") ");
			}

		}
		// 房间类型
		if (StringUtils.isNotEmpty(roomsSearchvo.getFangxin())) {
			String fangxin = roomsSearchvo.getFangxin().trim();
			if (!fangxin.contains(",")) {
				querySQL.append(" AND room_type in('" + fangxin + "') ");
			} else {

				String[] fangxinArray = fangxin.split(",");
				StringBuffer sb = new StringBuffer();
				for (String str : fangxinArray) {
					sb.append("'" + str + "',");
				}
				querySQL.append(" AND room_type in("
						+ sb.substring(0, sb.length() - 1).toString() + ") ");
			}

		}
		// 为空时候查询 维修 冻结 和空房
		if (StringUtils.isEmpty(roomsSearchvo.getRoomStatu())) {
			querySQL.append(" AND (curr_stat='OOO' or curr_stat='OOI' or curr_stat like 'V%') ");
		}
		// 维修 冻结 全部
		if (StringUtils.isNotEmpty(roomsSearchvo.getRoomStatu())) {
			if ("%".equals(roomsSearchvo.getRoomStatu())) {
//				querySQL.append(" AND (curr_stat='OOO' or curr_stat='OOI' or curr_stat like 'V%') ");
			} else {
				querySQL.append(" AND curr_stat = '"
						+ roomsSearchvo.getRoomStatu() + "'");
			}

		}
		querySQL.append(" ORDER BY floor_no ASC ,room_id ASC");
		List<Object[]> results = this.getSession()
				.createSQLQuery(querySQL.toString()).list();
		List<RoomStatusVo> rooms = new ArrayList<RoomStatusVo>();
		// 迭代处理结果集
		String floorFlag = "-100";// 楼层标志
		for (Object[] obj : results) {
			String thisFloorFlag = obj[1].toString().trim();// 本次楼层
			if (thisFloorFlag.equalsIgnoreCase(floorFlag)) {
				setManagerRoomPram(obj, rooms);
			} else {
				floorFlag = thisFloorFlag;
				RoomStatusVo vo = new RoomStatusVo();
				vo.setIsFloor(true);
				vo.setFloorNo(thisFloorFlag);
				rooms.add(vo);
				// 添加房屋
				setManagerRoomPram(obj, rooms);
			}

		}
		if (StringUtils.isNotEmpty(roomsSearchvo.getFangjiantezhengID())) {
			rooms = fiterRoomCharater(roomsSearchvo, rooms);// 根据房型条件过滤
		}
		// 过滤多余楼层
		checkRoomStats(rooms);
		return rooms;
	}

	@Override
	public Map<String, Object> getLeaveRoomInfo(String roomId,
			String startDate, String endDate) {
		Map<String, Object> result = null;
		// 非在住客户 chk_stat=null 抵店日期为今天 房号
		String querySQL = "SELECT gst_namec,mobile,reach_date,leave_date FROM guestdoc "
				+ "WHERE room_id='"
				+ roomId
				+ "' AND "
				+ " DATEDIFF(day, reach_date,'"
				+ startDate
				+ "')>=0 AND DATEDIFF(day,leave_date'"
				+ endDate
				+ "') <=0 "
				+ " AND chk_stat is null";
		List<Object[]> list = this.getSession().createSQLQuery(querySQL).list();
		if (list.size() == 1) {
			Object[] obj = list.get(0);
			result = new HashMap<String, Object>();
			result.put("GUESTNAME", obj[0].toString());
			result.put("GUESTMOBILE", obj[1].toString());
			result.put("BEGINTIME", obj[2]);
			result.put("ENDTIME", obj[3]);
		}
		return result;
	}

	@Override
	public Map<String, Object> getWillComeRoomInfo(String roomId,
			String startDate) {
		Map<String, Object> result = null;
		String querySQL = "SELECT gst_namec,mobile,reach_date,leave_date FROM guestdoc "
				+ "WHERE room_id='"
				+ roomId
				+ "' AND "
				+ " DATEDIFF(day, reach_date,'"
				+ startDate
				+ "')=0 "
				+ " AND chk_stat is null";
		List<Object[]> list = this.getSession().createSQLQuery(querySQL).list();
		if (list.size() == 1) {
			Object[] obj = list.get(0);
			result = new HashMap<String, Object>();
			result.put("GUESTNAME", obj[0].toString());
			result.put("GUESTMOBILE", obj[1].toString());
			result.put("TIME", obj[2]);
		}
		return result;
	}

	@Override
	public List getCurrAvailableForSaleRooms() {
		StringBuilder builder = new StringBuilder();
		builder.append("select x.curr_stat, (select code_id + ':' + code_namec from hroom_type where code_id = x.room_type) room_type, x.floor_no, ");
		builder.append("convert(varchar(1024), stuff(");
		builder.append("(select ',' + c.room_id ");
		builder.append("	from rooms c ");
		builder.append("	where c.curr_stat = x.curr_stat ");
		builder.append("	and c.status = 0 ");
		builder.append("	and c.room_type = x.room_type ");
		builder.append("	and c.floor_no = x.floor_no and ");
		builder.append("	not exists (select * from rooms_diary where room_id = c.room_id and datediff(day, hotel_date, getdate()) = 0) for xml path('')), 1, 1, '')) room_ids ");
		builder.append("from ");
		builder.append("	(select a.curr_stat, a.room_type, a.floor_no, a.room_id ");
		builder.append("	from rooms a ");
		builder.append("	where a.status = 0 and not exists (select * from rooms_diary where room_id = a.room_id and datediff(day, hotel_date, getdate()) = 0)) x ");
		builder.append("group by x.curr_stat, x.room_type, x.floor_no ");
		builder.append("order by x.curr_stat, x.room_type, x.floor_no");
		Query query = this.createTransformSqlQuery(builder.toString());
		return query.list();
	}

	@Override
	public boolean isExistsRooms(String room_id) {
		StringBuffer hql = new StringBuffer();
		hql.append("from Rooms as model where model.status = ? and model.roomId = ? ");
		Query query = getSession().createQuery(hql.toString());
		query.setParameter(0, 0);
		query.setParameter(1, room_id);
		return query.list().size() > 0;
	}
}
