package com.cyw.mammoth.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Hibernate;
import org.hibernate.jdbc.Work;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cyw.common.base.dao.impl.BaseDaoImpl;
import com.cyw.common.util.ArrayUtils;
import com.cyw.mammoth.bean.Operator;
import com.cyw.mammoth.bean.RoomNum;
import com.cyw.mammoth.bean.Rooms;
import com.cyw.mammoth.dao.BookCheckInDao;
import com.cyw.mammoth.dao.BookRoomDao;
import com.cyw.mammoth.vo.BookRoomCheckInVO;
/**
 * 散客或者团队预定入住数据操作层实现类
 * @author Administrator
 *
 */
@Repository
public class BookCheckInDaoImpl extends BaseDaoImpl<Rooms, Integer> implements
		BookCheckInDao {

	@Autowired
	private BookRoomDao bookRoomDao ;
	
	@Override
	public List<?> findRoomsBy(final BookRoomCheckInVO bookRoomCheckInVO) {
		final List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		final StringBuilder sbd = new StringBuilder() ; 
		sbd.append(" SELECT     \n ") ;
		sbd.append("     t0.room_id,    \n ") ;
		sbd.append("     t0.room_type,    \n ") ;
		sbd.append("     t0.floor_no,    \n ") ;
		sbd.append("     t0.build_id,    \n ") ;
		sbd.append("     t0.room_character,    \n ") ;
		sbd.append("     t0.curr_stat,    \n ") ;
		sbd.append("     t0.roomTypeName,    \n ") ;
		sbd.append("     t0.ifSaveRoom     \n ") ;
		sbd.append(" FROM (   \n ") ;
		sbd.append(" SELECT    \n ") ;
		sbd.append("     t.room_id,    \n ") ;
		sbd.append("     t.room_type,    \n ") ;
		sbd.append("     t.floor_no,    \n ") ;
		sbd.append("     t.build_id,    \n ") ;
		sbd.append("     t.room_character,    \n ") ;
		sbd.append("     t.curr_stat,    \n ") ;
		sbd.append("     t1.code_namec as roomTypeName,    \n ") ;
		sbd.append("     '1' AS ifSaveRoom     \n ") ;
		sbd.append(" FROM    \n ") ;
		sbd.append("     rooms t     \n ") ;
		sbd.append(" INNER JOIN hroom_type t1     \n ") ;
		sbd.append("     ON t.room_type = t1.code_id   \n ") ;
		sbd.append("     AND t1.status = 0  \n ") ;
		sbd.append(" WHERE t.curr_stat IN ('VCI','VDP','VCP','VC','VD')     \n ") ;
		sbd.append("     AND t.status = 0     \n ") ;
		sbd.append("     AND t.room_id NOT IN     \n ") ;
		// 排除该时间段内占用的房子
		sbd.append("     (SELECT     \n ") ;
		sbd.append("         t10.room_id     \n ") ;
		sbd.append("     FROM    \n ") ;
		sbd.append("         rooms_diary t10     \n ") ;
		sbd.append("     WHERE DATEDIFF(DAY, t10.hotel_date, ?) <= 0     \n ") ;
		sbd.append("         AND DATEDIFF(DAY, t10.hotel_date, ?) >= 0     \n ") ;
		sbd.append("     UNION     \n ") ;
		sbd.append("     ALL      \n ") ;
		// 排除已留房记录
		sbd.append("     SELECT      \n ") ;
		sbd.append("         t.room_id      \n ") ;
		sbd.append("     FROM     \n ") ;
		sbd.append("         room_num t      \n ") ;
		sbd.append("     WHERE t.flag = 0 AND t.flag = 1     \n ") ;
		sbd.append("         AND t.check_id = ?      \n ") ;
		sbd.append("         AND t.book_id = ?      \n ") ;
		sbd.append("     )     \n ") ;
		sbd.append(" UNION     \n ") ;
		sbd.append(" SELECT     \n ") ;
		sbd.append(" t2.room_id,    \n ") ;
		sbd.append(" t2.room_type,    \n ") ;
		sbd.append(" t2.floor_no,    \n ") ;
		sbd.append(" t2.build_id,    \n ") ;
		sbd.append(" t2.room_character,    \n ") ;
		sbd.append(" t2.curr_stat,    \n ") ;
		sbd.append(" t3.code_namec as roomTypeName,    \n ") ;
		sbd.append(" CASE    \n ") ;
		sbd.append("     WHEN t.flag = 0     \n ") ;
		sbd.append("     THEN '0'     \n ") ;
		sbd.append("     WHEN t.flag = 1     \n ") ;
		sbd.append("     THEN '2'     \n ") ;
		sbd.append("     ELSE '2'     \n ") ;
		sbd.append(" END AS ifSaveRoom     \n ") ;
		sbd.append(" FROM    \n ") ;
		sbd.append(" room_num t     \n ") ;
		sbd.append(" INNER JOIN rooms t2     \n ") ;
		sbd.append("     ON t.room_id = t2.room_id     \n ") ;
		sbd.append("     AND t2.status = 0     \n ") ;
		sbd.append("     AND t.status = 0     \n ") ;
		sbd.append(" INNER JOIN hroom_type t3     \n ") ;
		sbd.append("     ON t2.room_type = t3.code_id    \n ") ;
		sbd.append("     AND t3.status = 0      \n ") ;
		sbd.append(" WHERE t.check_id = ?      \n ") ;
		sbd.append("     AND t.book_id = ?      \n ") ;
		sbd.append(" ) t0     \n ") ;
		sbd.append(" WHERE 1=1     \n ") ;
		if(StringUtils.isNotBlank(bookRoomCheckInVO.getBuildId()))
			sbd.append(" AND t0.build_id ="+bookRoomCheckInVO.getBuildId()+"     \n ") ;
		if(StringUtils.isNotBlank(bookRoomCheckInVO.getFloorNo()))
			sbd.append(" AND t0.floor_no ="+bookRoomCheckInVO.getFloorNo()+"     \n ") ;
		if(StringUtils.isNotBlank(bookRoomCheckInVO.getRoomCharaterTotalVal())){
			String rctVal = bookRoomCheckInVO.getRoomCharaterTotalVal() ;
			Integer[] rctIntVal = ArrayUtils.convertStrArrayToInt(rctVal.split(","));
			StringBuilder sbd1 = new StringBuilder() ; 
			for (Integer ch : rctIntVal) {
				sbd1.append(" ((t0.room_character & "+(1L << ch)+" ) != 0 ) AND") ;
			}
			sbd.append(" AND (" +sbd1.substring(0, sbd1.length()-3)+" )   \n") ;
		}
		if(StringUtils.isNotBlank(bookRoomCheckInVO.getRoomType()))
			sbd.append(" AND t0.room_type in ('"+bookRoomCheckInVO.getRoomType().replace(",", "','")+"')      \n ") ;
		
		sbd.append(" ORDER BY t0.floor_no asc ,t0.room_id asc  \n ") ;
		getSession().doWork(new Work() {
			@Override
			public void execute(Connection connection) throws SQLException {
				
				PreparedStatement pstmt= connection.prepareStatement(sbd.toString()) ;
				pstmt.setString(1, bookRoomCheckInVO.getReachDate()) ;
				pstmt.setString(2, bookRoomCheckInVO.getLeaveDate()) ;
				pstmt.setString(3, bookRoomCheckInVO.getCheckId()) ;
				pstmt.setInt(4, bookRoomCheckInVO.getBookId()) ;
				pstmt.setString(5, bookRoomCheckInVO.getCheckId()) ;
				pstmt.setInt(6, bookRoomCheckInVO.getBookId()) ;
				ResultSet rs = pstmt.executeQuery() ;
				while(rs.next()){
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("roomId", rs.getString("room_id")) ;
					map.put("roomType", rs.getString("room_type")) ;
					map.put("ifSaveRoom", rs.getString("ifSaveRoom")) ;
					map.put("floorNo", rs.getString("floor_no")) ;
					map.put("currStat", rs.getString("curr_stat")) ;
					map.put("roomTypeName", rs.getString("roomTypeName")) ;
					map.put("buildId", rs.getString("build_id")) ;
					list.add(map) ;
				}
				if(rs != null){
		            try {
		                rs.close();
		                rs=null;
		            } catch (Exception ex) {
		                rs=null;
		            }
		        } 
		        if(pstmt != null){
		            try {
		            	pstmt.close();
		            	pstmt=null;
		            } catch (Exception ex) {
		            	pstmt=null;
		            }
		        }
			}
		});
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Integer confirmAllReach(BookRoomCheckInVO bookRoomCheckInVO, Operator operator) {
		StringBuilder sbd = new StringBuilder() ;
		if(bookRoomCheckInVO.getBookId() != null ){
			// 获取需要更新的room_num数据
			List<RoomNum> list_roomNum = (List<RoomNum>)getSession().createQuery("from RoomNum where checkId = ? and bookId = ? and flag = 0  ")
					.setParameter(0, Integer.valueOf(bookRoomCheckInVO.getCheckId()))
					.setParameter(1, bookRoomCheckInVO.getBookId())
					.list() ;
			// 循环更新room_num数据
			if(list_roomNum != null && !list_roomNum.isEmpty()){
				for (RoomNum roomNum : list_roomNum) {
					sbd.setLength(0);
					// 取消留房
					sbd.append(" update room_num set status = 1, oper_id = ?  , oper_time = ? where check_id = ? and book_id = ? and id = ? and flag = 0 ") ;
					getSession().createSQLQuery(sbd.toString())
					.setParameter(0, operator.getOperId())
					.setParameter(1, Calendar.getInstance().getTime() , Hibernate.TIMESTAMP)
					.setParameter(2, Integer.valueOf(bookRoomCheckInVO.getCheckId()))
					.setParameter(3, bookRoomCheckInVO.getBookId())
					.setParameter(4, roomNum.getId())
					.executeUpdate() ;
				}
			}
			
			// book_room 全部抵达
			sbd.setLength(0);
			sbd.append(" update BookRoom set status = 2 , saveNum = 0 , updateTimes = (updateTimes+1) , modifyOper = ? , modifyTime = ? where checkId = ? and bookRoomId = ?") ;
			getSession().createQuery(sbd.toString())
			.setParameter(0, operator.getOperId())
			.setParameter(1, Calendar.getInstance().getTime() , Hibernate.TIMESTAMP)
			.setParameter(2, Integer.valueOf(bookRoomCheckInVO.getCheckId()))
			.setParameter(3, bookRoomCheckInVO.getBookId())
			.executeUpdate() ;
			// 如果最后一条全部抵达时  更新guestdoc表状态
			List<?> list = bookRoomDao.getList(new String[]{"status" , "checkId"}, new Object[]{0,Integer.valueOf(bookRoomCheckInVO.getCheckId())}) ;
			// guestdoc 全部抵达
			sbd.setLength(0);
			sbd.append(" update Guestdoc set bookStat = ?,lastOper=?,lastTime = ? where checkId = ?") ;
			getSession().createQuery(sbd.toString())
			.setParameter(0, (list == null || list.size() == 0) ? "A" : "R")
			.setParameter(1, operator.getOperId())
			.setParameter(2, Calendar.getInstance().getTime() , Hibernate.TIMESTAMP)
			.setParameter(3, Integer.valueOf(bookRoomCheckInVO.getCheckId()))
			.executeUpdate() ;
			// 团队全部抵达
			if(bookRoomCheckInVO.getGrpChkid() != null){
				// 如果最后一条全部抵达时  更新GrpDoc表状态
				List<?> list1 = bookRoomDao.getList(new String[]{"status" , "checkId"}, new Object[]{0,Integer.valueOf(bookRoomCheckInVO.getGrpChkid())}) ;
				// grp_doc 全部抵达
				sbd.setLength(0);
				sbd.append(" update GrpDoc set bookStat = ?,lastOper=?,lastTime = ? where checkId = ?") ;
				getSession().createQuery(sbd.toString())
				.setParameter(0, (list1 == null || list1.size() == 0) ? "A" : "R")
				.setParameter(1, operator.getOperId())
				.setParameter(2, Calendar.getInstance().getTime() , Hibernate.TIMESTAMP)
				.setParameter(3, Integer.valueOf(bookRoomCheckInVO.getGrpChkid()))
				.executeUpdate() ;
			}
		}else{
			List<RoomNum> list_roomNum = (List<RoomNum>)getSession().createQuery("from RoomNum where checkId = ? and flag = 0  ")
					.setParameter(0, Integer.valueOf(bookRoomCheckInVO.getCheckId()))
					.list() ;
			if(list_roomNum != null && !list_roomNum.isEmpty()){
				for (RoomNum roomNum : list_roomNum) {
					sbd.setLength(0);
					// 取消留房
					sbd.append(" update room_num set status = 1, oper_id = ?  , oper_time = ? where check_id = ? and book_id = ? and id = ?  and flag = 0 ") ;
					getSession().createSQLQuery(sbd.toString())
					.setParameter(0, operator.getOperId())
					.setParameter(1, Calendar.getInstance().getTime() , Hibernate.TIMESTAMP)
					.setParameter(2, Integer.valueOf(bookRoomCheckInVO.getCheckId()))
					.setParameter(3, roomNum.getBookId())
					.setParameter(4, roomNum.getId())
					.executeUpdate() ;
				}
			}
			
			// book_room 全部抵达
			sbd.setLength(0);
			sbd.append(" update BookRoom set status = 2 , saveNum = 0 , updateTimes = (updateTimes+1) , modifyOper = ? , modifyTime = ? where checkId = ? ") ;
			getSession().createQuery(sbd.toString())
			.setParameter(0, operator.getOperId())
			.setParameter(1, Calendar.getInstance().getTime() , Hibernate.TIMESTAMP)
			.setParameter(2, Integer.valueOf(bookRoomCheckInVO.getCheckId()))
			.executeUpdate() ;
			
			// guestdoc 全部抵达
			sbd.setLength(0);
			sbd.append(" update Guestdoc set bookStat = 'A',lastOper=?,lastTime = ? where checkId = ?") ;
			getSession().createQuery(sbd.toString())
			.setParameter(0, operator.getOperId())
			.setParameter(1, Calendar.getInstance().getTime() , Hibernate.TIMESTAMP)
			.setParameter(2, Integer.valueOf(bookRoomCheckInVO.getCheckId()))
			.executeUpdate() ;
			
			// 团队全部抵达
			if(bookRoomCheckInVO.getGrpChkid() != null){
				// grp_doc 全部抵达
				sbd.setLength(0);
				sbd.append(" update GrpDoc set bookStat ='A',lastOper=?,lastTime = ? where checkId = ?") ;
				getSession().createQuery(sbd.toString())
				.setParameter(0, operator.getOperId())
				.setParameter(1, Calendar.getInstance().getTime() , Hibernate.TIMESTAMP)
				.setParameter(2, Integer.valueOf(bookRoomCheckInVO.getGrpChkid()))
				.executeUpdate() ;
			}
		}
		return 1 ;
	}
}

