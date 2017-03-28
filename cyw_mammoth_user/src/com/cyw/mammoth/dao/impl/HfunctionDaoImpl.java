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
import com.cyw.mammoth.bean.Hfunction;
import com.cyw.mammoth.bean.HfunctionControl;
import com.cyw.mammoth.dao.HfunctionDao;
import com.cyw.mammoth.vo.HfunctionControlVO;
import com.cyw.mammoth.vo.HfunctionVO;
@Repository
public class HfunctionDaoImpl extends BaseDaoImpl<Hfunction, Integer> implements HfunctionDao {

	@Override
	public List<HfunctionVO> findListBy(final Integer status) {
		final List<HfunctionVO> list = new ArrayList<HfunctionVO>();
		final StringBuilder sbd = new StringBuilder() ; 
		sbd.append(" SELECT  \n " );
		sbd.append("     t.id,    \n " );
		sbd.append("     t.function_id,    \n " );
		sbd.append("     t.function_name,    \n " );
		sbd.append("     t.ctrl_type,    \n " );
		sbd.append("     t.cy_url ,  \n " );
		sbd.append("     t.memo,    \n " );
		sbd.append("     (SELECT      " );
		sbd.append("         CASE     " );
		sbd.append("             WHEN COUNT(hfunctionWorkGroupList.id) = 0      " );
		sbd.append("             THEN '1'      " );
		sbd.append("             ELSE '0'      " );
		sbd.append("         END AS relFlag      " );
		sbd.append("     FROM     " );
		sbd.append("         hfunction_control AS hfunctionControl      " );
		sbd.append("         LEFT JOIN hfunction_workgroup_list AS hfunctionWorkGroupList      " );
		sbd.append("             ON hfunctionControl.function_id = hfunctionWorkGroupList.hfunction_id      " );
		sbd.append("     WHERE hfunctionControl.parent_id = t.id) AS relFlag  " );
		sbd.append(" FROM hfunction t  \n " );
		sbd.append(" ORDER BY t.id asc \n ") ;
		getSession().doWork(new Work() {
			@Override
			public void execute(Connection connection) throws SQLException {
				
				PreparedStatement pstmt= connection.prepareStatement(sbd.toString()) ;
				ResultSet rs = pstmt.executeQuery() ;
				sbd.setLength(0) ;
				sbd.append(" SELECT  \n " );
				sbd.append("     t.id,    \n " );
				sbd.append("     t.function_id,    \n " );
				sbd.append("     t.function_name,    \n " );
				sbd.append("     t.f_group,    \n " );
				sbd.append("     t.parent_id,    \n " );
				sbd.append("     t.ctrl_type,    \n " );
				sbd.append("     t.memo,    \n " );
				sbd.append("     (SELECT COUNT(t1.id) FROM hfunction_workgroup_list t1 where t1.hfunction_id = t.function_id) as refCount     \n " );
				sbd.append(" FROM hfunction_control t  \n " );
				sbd.append(" WHERE t.parent_id = ?   \n " );
				sbd.append(" ORDER BY t.id asc \n ") ;
				ResultSet rs_1 = null ;
				while(rs.next()){
					HfunctionVO hfVo = new HfunctionVO();
					Hfunction hf = new Hfunction();
					hf.setId(rs.getInt("id")) ;
					hf.setFunctionId(rs.getString("function_id")) ;
					hf.setFunctionName(rs.getString("function_name")) ;
					hf.setCtrlType(rs.getString("ctrl_type")) ;
					hf.setCyUrl(rs.getString("cy_url")) ;
					hf.setMemo(rs.getString("memo")) ;
					hfVo.setEditFlag(rs.getString("relFlag"));
					hfVo.setHfunction(hf);
					
					pstmt= connection.prepareStatement(sbd.toString()) ;
					pstmt.setInt(1, rs.getInt("id")) ;
					rs_1 = pstmt.executeQuery() ;
					List<HfunctionControlVO> hfunctionControlVOs = new ArrayList<HfunctionControlVO>();
					HfunctionControlVO hfcVo = null;
					HfunctionControl hfc = null;
					while(rs_1.next()){
						hfcVo = new HfunctionControlVO();
						hfc = new HfunctionControl();
						hfc.setId(rs_1.getInt("id")) ;
						hfc.setFunctionId(rs_1.getString("function_id")) ;
						hfc.setFunctionName(rs_1.getString("function_name")) ;
						hfc.setFgroup(rs_1.getString("f_group")) ;
						hfc.setParentId(rs_1.getInt("parent_id")) ;
						hfc.setCtrlType(rs_1.getString("ctrl_type")) ;
						hfc.setMemo(rs_1.getString("memo")) ;
						
						hfcVo.setHfunctionControl(hfc);
						hfcVo.setRefCount(rs_1.getInt("refCount")) ;
						hfcVo.setFlag("n") ;
						
						hfunctionControlVOs.add(hfcVo);
						hfVo.setHfunctionControlVOs(hfunctionControlVOs) ;
					}
					list.add(hfVo) ;
				}
				if(rs_1 != null){
		            try {
		            	rs_1.close();
		            	rs_1=null;
		            } catch (Exception ex) {
		            	rs_1=null;
		            }
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
