package com.cyw.mammoth.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.jdbc.Work;
import org.hibernate.transform.Transformers;
import org.hibernate.type.Type;
import org.springframework.stereotype.Repository;

import com.cyw.common.base.dao.impl.BaseDaoImpl;
import com.cyw.mammoth.auth.AppBaseCfg;
import com.cyw.mammoth.auth.AppBaseCfg.HfunctionCtrlEnum;
import com.cyw.mammoth.bean.Hfunction;
import com.cyw.mammoth.bean.HfunctionControl;
import com.cyw.mammoth.bean.WorkGroup;
import com.cyw.mammoth.dao.WorkGroupDao;
import com.cyw.mammoth.vo.HfunctionControlVO;
import com.cyw.mammoth.vo.HfunctionVO;
import com.cyw.mammoth.vo.WorkGroupVO;
@Repository
public class WorkGroupDaoImpl extends BaseDaoImpl<WorkGroup, Integer> implements
		WorkGroupDao {

	@Override
	public List<HfunctionVO> findFunctionListBy(final String funcType, final String groupId) {
		final List<HfunctionVO> list = new ArrayList<HfunctionVO>();
		final StringBuilder sbd = new StringBuilder() ; 

		sbd.append(" SELECT DISTINCT \n " );
		sbd.append("     t.id,    \n " );
		sbd.append("     t.function_id,    \n " );
		sbd.append("     t.function_name,    \n " );
		sbd.append("     t.ctrl_type \n " );
		sbd.append(" FROM hfunction t  \n " );
		sbd.append(" ORDER BY t.id asc \n ") ;
		getSession().doWork(new Work() {
			@Override
			public void execute(Connection connection) throws SQLException {
				
				PreparedStatement pstmt= connection.prepareStatement(sbd.toString()) ;
				ResultSet rs = pstmt.executeQuery() ;
				sbd.setLength(0);
				sbd.append(" SELECT DISTINCT     \n ") ;
				sbd.append("     t1.id,     \n ") ;
				sbd.append("     t1.function_id,     \n ") ;
				sbd.append("     t1.function_name,     \n ") ;
				sbd.append("     t1.f_group,     \n ") ;
				sbd.append("     t1.ctrl_type,     \n ") ;
				sbd.append("     t1.parent_id,     \n ") ;
				sbd.append("     t.id as relId      \n ") ;
				sbd.append("     FROM       \n ") ;
				sbd.append("         hfunction_control t1        \n ") ;
				sbd.append("     LEFT JOIN hfunction_workgroup_list t      \n ") ;
				sbd.append("         ON t1.function_id = t.hfunction_id      \n ") ;
				sbd.append(" 			AND t.workgroup_id = ?      \n ") ;
				sbd.append("         WHERE t1.f_group = ?  AND t1.parent_id = ?   \n ") ;
				ResultSet rs_1 = null ;
				while(rs.next()){
					HfunctionVO hfVo = new HfunctionVO();
					Hfunction hf = new Hfunction();
					hf.setId(rs.getInt("id")) ;
					hf.setFunctionId(rs.getString("function_id")) ;
					hf.setFunctionName(rs.getString("function_name")) ;
					hf.setCtrlType(rs.getString("ctrl_type")) ;
					
					pstmt= connection.prepareStatement(sbd.toString()) ;
					pstmt.setString(1, groupId) ;
					pstmt.setString(2, funcType) ;
					pstmt.setInt(3, rs.getInt("id")) ;
					rs_1 = pstmt.executeQuery() ;
					List<HfunctionControlVO> hfunctionControlVOs = new ArrayList<HfunctionControlVO>();
					HfunctionControlVO hfcVo = null;
					HfunctionControl hfc = null;
					while(rs_1.next()){
						hfcVo = new HfunctionControlVO();
						hfc = new HfunctionControl();
						Integer relId = rs_1.getInt("relId") ;
						
						hfc.setId(rs_1.getInt("id")) ;
						hfc.setFunctionId(rs_1.getString("function_id")) ;
						hfc.setFunctionName(rs_1.getString("function_name")) ;
						hfc.setFgroup(rs_1.getString("f_group")) ;
						String code = rs_1.getString("ctrl_type") ;
						hfc.setCtrlType(code) ;
						hfc.setParentId(rs_1.getInt("parent_id")) ;
						
						hfcVo.setHfunctionControl(hfc);
						hfcVo.setCtrlTypeName(geteNameByCode(code)) ;
						if(relId == null || relId == 0){
							hfcVo.setFlag("1") ;
						}
						else{
							hfcVo.setFlag("0") ;
						}
						hfunctionControlVOs.add(hfcVo);
						hfVo.setHfunctionControlVOs(hfunctionControlVOs) ;
					}
					
					hfVo.setHfunction(hf);
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
	private String geteNameByCode(String code){
		HfunctionCtrlEnum[] hce = AppBaseCfg.HfunctionCtrlEnum.values() ;
		String name = "" ;
		for (HfunctionCtrlEnum hc : hce) {
			if(hc.code.equals(code)){
				name = hc.name ;
				break;
			}
		}
		return name ;
	}
	@Override
	public List<WorkGroupVO> findListBy(final Integer status) {
		final List<WorkGroupVO> list = new ArrayList<WorkGroupVO>() ;
		final StringBuilder sbd = new StringBuilder() ;
		sbd.append(" SELECT DISTINCT      ") ;
		sbd.append("     workGroup.group_id,     ") ;
		sbd.append("     workGroup.group_name,     ") ;
		sbd.append("     workGroup.status,     ") ;
		sbd.append("     workGroup.memo,     ") ;
		sbd.append("     workGroup.vilhotel_id,     ") ;
		sbd.append("     workGroup.chain_id,     ") ;
		sbd.append("     COUNT(operator.oper_id) AS relFlag      ") ;
		sbd.append(" FROM     ") ;
		sbd.append("     work_group workGroup      ") ;
		sbd.append("     LEFT JOIN operator operator      ") ;
		sbd.append("         ON workGroup.group_id = operator.group_id      ") ;
		sbd.append(" WHERE workGroup.status = ?     ") ;
		sbd.append(" GROUP BY workGroup.group_id,     ") ;
		sbd.append("     workGroup.group_name,     ") ;
		sbd.append("     workGroup.status,     ") ;
		sbd.append("     workGroup.memo,     ") ;
		sbd.append("     workGroup.vilhotel_id,     ") ;
		sbd.append("     workGroup.chain_id      ") ;
		getSession().doWork(new Work() {
			@Override
			public void execute(Connection connection) throws SQLException {
				PreparedStatement pstmt= connection.prepareStatement(sbd.toString()) ;
				pstmt.setInt(1, status) ;
				ResultSet rs = pstmt.executeQuery() ;
				while(rs.next()){
					WorkGroupVO wgVo = new WorkGroupVO();
					WorkGroup wg = new WorkGroup();
					wg.setGroupId(rs.getString("group_id")) ;
					wg.setGroupName(rs.getString("group_name")) ;
					wg.setMemo(rs.getString("memo")) ;
					wg.setStatus(rs.getInt("status")) ;
					wg.setVilhotelId(rs.getString("vilhotel_id")) ;
					wg.setChainId(rs.getString("chain_id")) ;
					wgVo.setRelFlag(rs.getInt("relFlag"));
					wgVo.setWorkGroup(wg) ;
					list.add(wgVo) ;
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
		
		/*return (List<WorkGroupVO>)getSession()
				.createSQLQuery(sbd.toString()).addEntity("workGroup" , WorkGroup.class)
				.addSynchronizedQuerySpace("relFlag")
				.setInteger(0, status)
				.list();*/
	}
}
