package com.cyw.mammoth.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.jdbc.Work;
import org.springframework.stereotype.Repository;

import com.cyw.common.base.dao.impl.BaseDaoImpl;
import com.cyw.mammoth.bean.HroomType;
import com.cyw.mammoth.dao.HroomTypeDao;
import com.cyw.mammoth.vo.RoomTypeVO;
@Repository
public class HroomTypeDaoImpl extends BaseDaoImpl<HroomType, Integer> implements
		HroomTypeDao {

	@Override
	public List<RoomTypeVO> findListBy(final Integer status) {
		final List<RoomTypeVO> list = new ArrayList<RoomTypeVO>();
		getSession().doWork(new Work() {
			@Override
			public void execute(Connection connection) throws SQLException {
				
				StringBuilder sbd = new StringBuilder() ; 
				sbd.append(" SELECT    \n") ;
				sbd.append("     t.id,   \n") ;
				sbd.append("     t.code_id,   \n") ;
				sbd.append("     t.code_namee,   \n") ;
				sbd.append("     t.code_namec,   \n") ;
				sbd.append("     t.status,   \n") ;
				sbd.append("     t.price,   \n") ;
				sbd.append("     t.building_id,   \n") ;
				sbd.append("     t.code_kind,   \n") ;
				sbd.append("     t.num,   \n") ;
				sbd.append("     t.vilhotel_id,   \n") ;
				sbd.append("     t.chain_id,   \n") ;
				sbd.append("     (SELECT    \n") ;
				sbd.append("         COUNT(*)    \n") ;
				sbd.append("     FROM   \n") ;
				sbd.append("         rooms t10    \n") ;
				sbd.append("     WHERE t10.room_type = t.code_id) AS referCount,   \n") ;
				sbd.append("     t1.code_namec AS buildingName    \n") ;
				sbd.append(" FROM   \n") ;
				sbd.append("     hroom_type t    \n") ;
				sbd.append("     INNER JOIN hbuilding t1    \n") ;
				sbd.append("         ON t.building_id = t1.code_id    \n") ;
				sbd.append("         AND t1.status = 0    \n") ;
				sbd.append(" WHERE 1 = 1    \n") ;
				sbd.append("     AND t.status = ?    \n") ;
				sbd.append(" ORDER BY t.code_id ASC    \n") ;
				PreparedStatement pstmt= connection.prepareStatement(sbd.toString()) ;
				pstmt.setInt(1, status) ;
				ResultSet rs = pstmt.executeQuery() ;
				while(rs.next()){
					RoomTypeVO rtv = new RoomTypeVO() ;
					HroomType ht = new HroomType() ;
					ht.setId(rs.getInt("id")) ;
					ht.setCodeId(rs.getString("code_id")) ;
					ht.setCodeNamee(rs.getString("code_namee")) ;
					ht.setCodeNamec(rs.getString("code_namec")) ;
					ht.setStatus(rs.getInt("status")) ;
					ht.setPrice(rs.getDouble("price")) ;
					ht.setBuildingId(rs.getString("building_id")) ;
					ht.setCodeKind(rs.getInt("code_kind")) ;
					ht.setNum(rs.getShort("num")) ;
					ht.setVilhotelId(rs.getString("vilhotel_id")) ;
					ht.setChainId(rs.getString("chain_id")) ;
					
					rtv.setHroomType(ht);
					rtv.setReferCount(rs.getInt("referCount"));
					list.add(rtv) ;
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
}
