package com.cyw.mammoth.dao.impl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


import org.hibernate.Query;
import org.hibernate.jdbc.Work;
import org.springframework.orm.hibernate3.SessionFactoryUtils;
import org.springframework.stereotype.Repository;

import com.cyw.common.base.dao.impl.BaseDaoImpl;
import com.cyw.mammoth.bean.Hbuilding;
import com.cyw.mammoth.dao.HbuildingDao;
import com.cyw.mammoth.vo.HbuildingVo;
@Repository
public class HbuildingDaoImpl extends BaseDaoImpl<Hbuilding, Integer> implements
		HbuildingDao {

	@Override
	public List<HbuildingVo> getBuildList(final Integer status) {
		final List<HbuildingVo> list = new ArrayList<HbuildingVo>();
		final StringBuilder sbd = new StringBuilder() ; 
		sbd.append(" SELECT   \n") ;
		sbd.append("     t.id,  \n") ;
		sbd.append("     t.code_id,  \n") ;
		sbd.append("     t.code_namee,  \n") ;
		sbd.append("     t.code_namec,  \n") ;
		sbd.append("     t.status,  \n") ;
		sbd.append("     t.min_num,  \n") ;
		sbd.append("     t.max_num,  \n") ;
		sbd.append("     t.code_kind,  \n") ;
		sbd.append("     t.update_date,  \n") ;
		sbd.append("     t.vilhotel_id,  \n") ;
		sbd.append("     t.chain_id,  \n") ;
		sbd.append("     t.oper_id,  \n") ;
		sbd.append("     (SELECT         \n") ;
		sbd.append("         COUNT(t10.id)         \n") ;
		sbd.append("     FROM        \n") ;
		sbd.append("         hroom_type t10         \n") ;
		sbd.append("     WHERE t10.building_id = t.code_id) AS referCount,        \n") ;
		sbd.append("     COUNT(t1.id) AS rmNum   \n") ;
		sbd.append(" FROM  \n") ;
		sbd.append("     hbuilding t   \n") ;
		sbd.append("     LEFT JOIN hroom_type t1   \n") ;
		sbd.append("         ON t.code_id = t1.building_id   \n") ;
		sbd.append("         AND t1.status = 0   \n") ;
		sbd.append(" WHERE 1 = 1   \n") ;
		sbd.append("     AND t.status = ?   \n") ;
		sbd.append(" GROUP BY t.code_id,  \n") ;
		sbd.append("     t.id,  \n") ;
		sbd.append("     t.code_namee,  \n") ;
		sbd.append("     t.code_namec,  \n") ;
		sbd.append("     t.status,  \n") ;
		sbd.append("     t.min_num,  \n") ;
		sbd.append("     t.max_num,  \n") ;
		sbd.append("     t.code_kind,  \n") ;
		sbd.append("     t.update_date,  \n") ;
		sbd.append("     t.vilhotel_id,  \n") ;
		sbd.append("     t.chain_id,  \n") ;
		sbd.append("     t.oper_id   \n") ;
		sbd.append(" ORDER BY t.code_id ASC \n") ;
		getSession().doWork(new Work() {
			@Override
			public void execute(Connection connection) throws SQLException {
				
				PreparedStatement pstmt= connection.prepareStatement(sbd.toString()) ;
				pstmt.setInt(1, status) ;
				ResultSet rs = pstmt.executeQuery() ;
				while(rs.next()){
					HbuildingVo htvo = new HbuildingVo() ;
					Hbuilding ht = new Hbuilding() ;
					ht.setId(rs.getInt("id")) ;
					ht.setCodeId(rs.getString("code_id")) ;
					ht.setCodeNamee(rs.getString("code_namee")) ;
					ht.setCodeNamec(rs.getString("code_namec")) ;
					ht.setStatus(rs.getInt("status")) ;
					ht.setMinNum(rs.getInt("min_num")) ;
					ht.setMaxNum(rs.getInt("max_num")) ;
					ht.setCodeKind(rs.getInt("code_kind")) ;
					ht.setVilhotelId(rs.getString("vilhotel_id")) ;
					ht.setChainId(rs.getString("chain_id")) ;
					ht.setUpdateDate(rs.getDate("update_date"));
					ht.setOperId(rs.getString("oper_id")) ;
					htvo.setHbuilding(ht) ;
					htvo.setRoomTypeNum(rs.getInt("rmNum"));
					htvo.setReferCount(rs.getInt("referCount")) ;
					list.add(htvo) ;
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
	public List getPrepareCall() {
		List list=null;
		Connection conn_order=null;
		try {
			conn_order = (Connection) SessionFactoryUtils.getDataSource(sessionFactory).getConnection();
			CallableStatement call_order = (CallableStatement) conn_order.prepareCall("{call GetHotelDate(?)}");
			call_order.registerOutParameter(1,java.sql.Types.DATE);
			call_order.setDate(1, new java.sql.Date(new Date().getTime()));
			ResultSet rs_order=null;
			//rs_order = call_order.executeQuery();
			call_order.execute();
			//call_order.getUpdateCount();
			//list = ArrayUtils.resultSetToList(rs_order);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				conn_order.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}

	@Override
	public void updateStatus(Integer id) {
		Query query = getSession().createQuery("update Hbuilding set status = 1 where id = ? ") ;
		query.setParameter(0, id) ;
		query.executeUpdate() ;
	}

}
