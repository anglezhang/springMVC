package com.cyw.mammoth.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.jdbc.Work;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cyw.common.base.dao.impl.BaseDaoImpl;
import com.cyw.mammoth.bean.HroomCharacters;
import com.cyw.mammoth.bean.Rooms;
import com.cyw.mammoth.dao.HroomCharactersDao;
import com.cyw.mammoth.dao.HroomDefineDao;
import com.cyw.mammoth.vo.RoomDefineVo;
@Repository
public class HroomDefineDaoImpl extends BaseDaoImpl<Rooms,Integer> implements
		HroomDefineDao {

	@Autowired
	private HroomCharactersDao hroomCharactersDao ;
	@Override
	public List<RoomDefineVo> findListBy(final Integer status) {
		final List<RoomDefineVo> list = new ArrayList<RoomDefineVo>() ;
		
		final List<HroomCharacters> hcs = hroomCharactersDao.getList("status", 0) ;
		
		getSession().doWork(new Work() {
			@Override
			public void execute(Connection connection) throws SQLException {
				
				StringBuilder sbd = new StringBuilder() ; 
				sbd.append(" SELECT       \n" ) ;
				sbd.append(" t1.room_id,   \n" ) ;
				sbd.append(" t1.room_type,   \n" ) ;
				sbd.append(" t1.build_id,   \n" ) ;
				sbd.append(" t1.floor_no,   \n" ) ;
				sbd.append(" t1.room_character,   \n" ) ;
				sbd.append(" t1.curr_stat,   \n" ) ;
				sbd.append(" t1.modi_stat,   \n" ) ;
				sbd.append(" t1.modi_time,   \n" ) ;
				sbd.append(" t1.modi_operid,   \n" ) ;
				sbd.append(" t1.description,   \n" ) ;
				sbd.append(" t1.status,   \n" ) ;
				sbd.append(" t1.vilhotel_id,   \n" ) ;
				sbd.append(" t1.chain_id,   \n" ) ;
				sbd.append(" t2.min_num AS buildMinNum,   \n" ) ;
				sbd.append(" t2.max_num AS buildMaxNum,   \n" ) ;
				sbd.append(" t2.code_namec AS buildingName,   \n" ) ;
				sbd.append(" t3.code_id AS roomTypeCode,   \n" ) ;
				sbd.append(" t3.code_namec AS roomTypeName  \n" ) ;
				sbd.append(" FROM rooms  t1   \n" ) ;
				sbd.append(" INNER JOIN hbuilding t2  \n" ) ;
				sbd.append(" ON t1.build_id = t2.code_id   \n" ) ;
				sbd.append(" AND t2.status = 0   \n" ) ;
				sbd.append(" INNER JOIN hroom_type t3   \n" ) ;
				sbd.append(" ON t1.room_type = t3.code_id   \n" ) ;
				sbd.append(" AND t3.status = 0  \n" ) ;
				sbd.append(" where 1=1    \n" ) ;
				sbd.append(" 	AND t1.status = "+status+"\n" ) ;
				sbd.append(" order by t1.build_id asc , t1.room_id asc \n" ) ;
				PreparedStatement pstmt= connection.prepareStatement(sbd.toString()) ;
				//pstmt.setString(1, 1);
				ResultSet rs = pstmt.executeQuery() ;
				while(rs.next()){
					RoomDefineVo roomDefineVo = new RoomDefineVo() ;
					Rooms room = new Rooms() ;
					room.setBuildId(rs.getString("build_id")) ;
					room.setChainId(rs.getString("chain_id")) ;
					room.setCurrStat(rs.getString("curr_stat"));
					room.setDescription(rs.getString("description")) ;
					room.setFloorNo(rs.getString("floor_no")) ;
					room.setModiOperid(rs.getString("modi_operid") );
					room.setModiStat(rs.getString("modi_stat")) ;
					room.setModiTime(rs.getDate("modi_time")) ;
					Long rc = rs.getLong("room_character") ;
					room.setRoomCharacter(rc) ;
					room.setRoomId(rs.getString("room_id")) ;
					room.setRoomType(rs.getString("room_type")) ;
					room.setStatus(rs.getInt("status")) ;
					room.setVilhotelId(rs.getString("vilhotel_id")) ;
					
					roomDefineVo.setRoom(room);
					if(rc > 0 ){
						StringBuilder rcnames = new StringBuilder() ;
						for (HroomCharacters hc : hcs) {
							if(((1L << hc.getPlaceholderId()) & rc) != 0)
								rcnames.append(hc.getCodeNamec()+",");
						}
						if(rcnames.length() > 0)
							roomDefineVo.setRoomCharaterNames(rcnames.substring(0, rcnames.length()-1)) ;
						else{
							roomDefineVo.setRoomCharaterNames("无") ;
						}
					}else{
						roomDefineVo.setRoomCharaterNames("无") ;
					}
					roomDefineVo.setBuildingName(rs.getString("buildingName")) ;
					roomDefineVo.setRoomTypeName(rs.getString("roomTypeName"));
					roomDefineVo.setRoomTypeCode(rs.getString("roomTypeCode"));
					roomDefineVo.setBuildMaxNum(rs.getInt("buildMaxNum"));
					roomDefineVo.setBuildMinNum(rs.getInt("buildMinNum"));
					list.add(roomDefineVo) ;
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

	@Override
	public void updRoomTypeNumBy(final String buildId,final String roomType, final Integer flag) {
		getSession().doWork(new Work() {
			@Override
			public void execute(Connection con) throws SQLException {
				StringBuilder sbd = new StringBuilder() ; 
				// 增 1
				if(flag == 0){
					sbd.append("update hroom_type set num = num + 1 where building_id = ? and code_id = ? and status = 0 " ) ;
				}
				// 减 1
				else if(flag == 1){
					sbd.append("update hroom_type set num = case num when 0 then num else num-1 end where building_id = ? and code_id = ? and status = 0 " ) ;
				}
				PreparedStatement ps= con.prepareStatement(sbd.toString()) ;
				ps.setString(1, buildId);
				ps.setString(2, roomType);
				int i= ps.executeUpdate() ;
		        if(ps != null){
		            try {
		            	ps.close();
		            	ps=null;
		            } catch (Exception ex) {
		            	ps=null;
		            }
		        }
			}
		});
	}

}
