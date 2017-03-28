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
import com.cyw.mammoth.bean.HroomCharacters;
import com.cyw.mammoth.bean.Rooms;
import com.cyw.mammoth.dao.HroomCharactersDao;
import com.cyw.mammoth.vo.HroomCharactersVO;
/**
 * 房间特征dao 实现类
 * @author Administrator
 *
 */
@Repository
public class HroomCharactersDaoImpl extends BaseDaoImpl<HroomCharacters, Integer> implements
		HroomCharactersDao {
	
	@SuppressWarnings("unchecked")
	@Override
	public List<HroomCharactersVO> findListBy(final Integer status) {
		final List<HroomCharactersVO> list = new ArrayList<HroomCharactersVO>();
		final List<Rooms> rooms = (List<Rooms>)getSession().createQuery("from Rooms ").list();
		final StringBuilder sbd = new StringBuilder() ;
		sbd.append(" SELECT    \n") ;
		sbd.append("     t.id,   \n") ;
		sbd.append("     t.code_namee,   \n") ;
		sbd.append("     t.code_namec,   \n") ;
		sbd.append("     t.code_kind,   \n") ;
		sbd.append("     t.status,   \n") ;
		sbd.append("     t.vilhotel_id,   \n") ;
		sbd.append("     t.chain_id,   \n") ;
		sbd.append("     t.placeholder_id    \n") ;
		sbd.append(" FROM   \n") ;
		sbd.append("     hroom_characters t    \n") ;
		sbd.append(" WHERE t.status = ?    \n") ;
		sbd.append(" ORDER BY t.placeholder_id ASC    \n") ;
		getSession().doWork(new Work() {
			@Override
			public void execute(Connection connection) throws SQLException {
				
				PreparedStatement pstmt= connection.prepareStatement(sbd.toString()) ;
				pstmt.setInt(1, status) ;
				ResultSet rs = pstmt.executeQuery() ;
				while(rs.next()){
					HroomCharactersVO hcvo = new HroomCharactersVO() ;
					HroomCharacters hc = new HroomCharacters() ;
					hc.setId(rs.getInt("id")) ;
					hc.setPlaceholderId(rs.getInt("placeholder_id")) ;
					hc.setCodeNamee(rs.getString("code_namee")) ;
					hc.setCodeNamec(rs.getString("code_namec")) ;
					hc.setCodeKind(rs.getInt("code_kind")) ;
					hc.setStatus(rs.getInt("status")) ;
					hc.setVilhotelId(rs.getString("vilhotel_id")) ;
					hc.setChainId(rs.getString("chain_id")) ;
					hcvo.setHroomCharacters(hc) ;
					for (Rooms room : rooms) {
						if((room.getRoomCharacter() & (1L<<hc.getPlaceholderId())) > 0){
							hcvo.setReferCount(1) ;
							break ;
						}
					}
					hcvo.setReferCount(hcvo.getReferCount()==null ? 0 :hcvo.getReferCount());
					list.add(hcvo) ;
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
