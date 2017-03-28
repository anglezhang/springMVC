package com.cyw.mammoth.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.hibernate.jdbc.Work;
import org.hibernate.transform.ResultTransformer;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cyw.common.base.dao.impl.BaseDaoImpl;
import com.cyw.common.util.ArrayUtils;
import com.cyw.mammoth.bean.HroomPlan;
import com.cyw.mammoth.bean.HroomPlanList;
import com.cyw.mammoth.dao.HroomPlanDao;
import com.cyw.mammoth.dao.ParameterDao;
import com.cyw.mammoth.vo.HroomPlanListVO;
import com.cyw.mammoth.vo.HroomPlanVo;
@Repository
public class HroomPlanDaoImpl extends BaseDaoImpl<HroomPlan, Integer> implements
		HroomPlanDao {
	
	@Autowired
	private ParameterDao parameterDao ;

	@Override
	public List<HroomPlanVo> findListBy(Integer rmplanId,final Integer status)
			throws Exception {
		final List<HroomPlanVo> list = new ArrayList<HroomPlanVo>();
		final StringBuilder sbd = new StringBuilder() ; 
		sbd.append(" SELECT \n " );
		sbd.append(" 	t.id ,  \n " );
		sbd.append("    t.code_id ,  \n " );
		sbd.append("    t.code_namee ,  \n " );
		sbd.append("    t.code_namec , \n " );
		sbd.append("    t.start_date , \n " );
		sbd.append("    t.end_date , \n " );
		sbd.append("    t.rmplan_type , \n " );
		sbd.append("    t.memo , \n " );
		sbd.append("    t.checked , \n " );
		sbd.append("    t.editable , \n " );
		sbd.append("    t.status  \n " );
		sbd.append(" FROM hroom_plan t  \n " );
		sbd.append(" WHERE t.status = "+status+"  \n " );
		sbd.append(" ORDER BY t.code_id asc \n ") ;
		getSession().doWork(new Work() {
			@Override
			public void execute(Connection connection) throws SQLException {
				
				PreparedStatement pstmt= connection.prepareStatement(sbd.toString()) ;
				ResultSet rs = pstmt.executeQuery() ;
				
				sbd.setLength(0) ;
				sbd.append(" SELECT distinct    \n " );
				sbd.append("     t.id,    \n " );
				sbd.append("     t.rmplan_id,    \n " );
				sbd.append("     t.rmtype_id,    \n " );
				sbd.append("     t.holiday_id,    \n " );
				sbd.append("     t.price,    \n " );
				sbd.append("     t.discount,    \n " );
				sbd.append("     t.status,    \n " );
				sbd.append("     t.memo,    \n " );
				sbd.append("     t1.code_id as roomTypeCode,    \n " );
				sbd.append("     t1.code_namec as roomTypeName,    \n " );
				sbd.append("     t3.code_namec as holidayName     \n " );
				sbd.append(" FROM    \n " );
				sbd.append("     hroom_plan_list t     \n " );
				sbd.append("     INNER JOIN hroom_type t1     \n " );
				sbd.append("         ON t1.code_id = t.rmtype_id     \n " );
				sbd.append("         AND t1.status = 0     \n " );
//				sbd.append("     INNER JOIN holidays t2     \n " );
//				sbd.append("         ON t.holiday_id = t2.holiday_id     \n " );
//				sbd.append("         AND t2.status = 0     \n " );
				sbd.append("     INNER JOIN hcodes t3     \n " );
				sbd.append("         ON t.holiday_id = t3.code_id     \n " );
				sbd.append("         AND t3.status = 0     \n " );
				sbd.append(" WHERE t.status = "+(status == 0 ? 0 : 1)+"  \n " );
				sbd.append(" AND t.rmplan_id = ?     \n " );
				sbd.append(" ORDER BY t.rmtype_id asc \n ") ;
				ResultSet rs_1 = null ;
				while(rs.next()){
					HroomPlanVo hpVo = new HroomPlanVo();
					HroomPlan hp = new HroomPlan();
					hp.setId(rs.getInt("id")) ;
					hp.setCodeId(rs.getString("code_id")) ;
					hp.setCodeNamec(rs.getString("code_namec")) ;
					hp.setCodeNamee(rs.getString("code_namee")) ;
					hp.setEndDate(rs.getTimestamp("end_date")) ;
					hp.setMemo(rs.getString("memo")) ;
					hp.setRmplanType(rs.getInt("rmplan_type")) ;
					hp.setStartDate(rs.getTimestamp("start_date")) ;
					hp.setStatus(rs.getInt("status")) ;
					hp.setChecked(rs.getInt("checked")) ;
					hp.setEditable(rs.getInt("editable")) ;
					// 合约单位
					@SuppressWarnings("rawtypes")
					List list1 = getSession().createQuery("FROM TaDoc where rateCode = ? ").setString(0, rs.getString("code_id")).list();
					if(list1 == null || list1.isEmpty()){
						// guestdoc
						list1 =  getSession().createQuery("FROM Guestdoc where prcSchemeId = ? ").setString(0, rs.getString("code_id")).list();
						if(list1 == null || list1.isEmpty()){
							// grp_doc
							list1 = getSession().createQuery("FROM GrpDoc where prcSchemeId = ? ").setString(0, rs.getString("code_id")).list();
							if(list1 == null || list1.isEmpty()){
								hpVo.setEditFlag("0");
							}else{
								hpVo.setEditFlag("1");
							}
						}else{
							hpVo.setEditFlag("1");
						}
					}else{
						hpVo.setEditFlag("1");
					}
					hpVo.setHroomPlan(hp);
					pstmt= connection.prepareStatement(sbd.toString()) ;
					pstmt.setString(1, rs.getString("code_id")) ;
					rs_1 = pstmt.executeQuery() ;
					List<HroomPlanListVO> hroomPlanListVOs = new ArrayList<HroomPlanListVO>();
					Set<String> hroomPlanListSet = new HashSet<String>() ;
					HroomPlanListVO hplVo = null;
					HroomPlanList hpl = null;
					while(rs_1.next()){
						hplVo = new HroomPlanListVO();
						hpl = new HroomPlanList();
						hpl.setId(rs_1.getInt("id")) ;
						hpl.setDiscount(rs_1.getDouble("discount")) ;
						hpl.setHolidayId(rs_1.getString("holiday_id")) ;
						hpl.setMemo(rs_1.getString("memo")) ;
						hpl.setPrice(rs_1.getDouble("price")) ;
						hpl.setRmplanId(rs_1.getString("rmplan_id")) ;
						hpl.setRmtypeId(rs_1.getString("rmtype_id")) ;
						hpl.setStatus(rs_1.getInt("status")) ;
						
						hplVo.setHroomPlanList(hpl) ;
						hplVo.setFlag("n") ;
						
						hplVo.setHolidayCode(rs_1.getString("holiday_id")) ;
						String holidayName = rs_1.getString("holidayName") ;
						hplVo.setHolidayName(holidayName) ;
						hplVo.setRoomTypeCode(rs_1.getString("roomTypeCode")) ;
						hplVo.setRoomTypeName(rs_1.getString("roomTypeName")) ;
						
						hroomPlanListVOs.add(hplVo);
						hpVo.setHroomPlanListVOs(hroomPlanListVOs) ;
						
						hroomPlanListSet.add(holidayName) ;
						hpVo.setHroomPlanHolidayNames(ArrayUtils.convertStrArrayToString(hroomPlanListSet.toArray(new String[]{}))) ;
					}
					list.add(hpVo) ;
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

	@Override
	public void deleteHroomPlanListBy(String hroomPlanId , Integer status , Integer oldStatus) {
		String[] strArr = hroomPlanId.split(",") ;
		for (String str : strArr) {
			getSession()
			.createSQLQuery(" update t set t.status = "+status+
								" from hroom_plan_list t inner join hroom_plan t1 on t.rmplan_id = t1.code_id and t1.id = "+str + 
									" and t.status = " + oldStatus)
			.executeUpdate() ;
		}
	}

	@Override
	public List<HroomPlanVo> findAvilListBy(Integer status, Date startDate , Integer roomplanType)
			{
		/*StringBuilder sbd = new StringBuilder() ; 
		sbd.append(" FROM HroomPlan t  \n " );
		sbd.append(" WHERE t.status = "+status+"  \n " );
		if(startDate != null)
			sbd.append("  and datediff(day,t.startDate,'"+DateFormatUtils.format(startDate, "yyyy-MM-dd")+"') >=0 "
						+"and datediff(day,t.endDate,'"+DateFormatUtils.format(startDate, "yyyy-MM-dd")+"') <=0  ") ;
		if(roomplanType != null)
			sbd.append("  and t.rmplanType = " + roomplanType) ;
		sbd.append(" ORDER BY t.codeId asc \n ") ;
		return (List<HroomPlan>)getSession().createQuery(sbd.toString()).list();*/
		
		
		
		
		final List<HroomPlanVo> list = new ArrayList<HroomPlanVo>();
		final StringBuilder sbd = new StringBuilder() ; 
		sbd.append(" SELECT \n " );
		sbd.append(" 	t.id ,  \n " );
		sbd.append("    t.code_id ,  \n " );
		sbd.append("    t.code_namec , \n " );
		sbd.append("    t.start_date , \n " );
		sbd.append("    t.end_date , \n " );
		sbd.append("    t.rmplan_type , \n " );
		sbd.append("    t.checked , \n " );
		sbd.append("    t.editable \n " );
		sbd.append(" FROM hroom_plan t  \n " );
		sbd.append(" WHERE t.status = "+status+"  \n " );
		if(startDate != null)
			sbd.append("  and datediff(day,t.start_date,'"+DateFormatUtils.format(startDate, "yyyy-MM-dd")+"') >=0 "
						+"and datediff(day,t.end_date,'"+DateFormatUtils.format(startDate, "yyyy-MM-dd")+"') <=0  ") ;
		if(roomplanType != null)
			sbd.append("  and t.rmplan_type = " + roomplanType) ;
		sbd.append(" ORDER BY t.code_id asc \n ") ;
		getSession().doWork(new Work() {
			@Override
			public void execute(Connection connection) throws SQLException {
				
				PreparedStatement pstmt= connection.prepareStatement(sbd.toString()) ;
				ResultSet rs = pstmt.executeQuery() ;
				
				while(rs.next()){
					HroomPlanVo hpVo = new HroomPlanVo();
					HroomPlan hp = new HroomPlan();
					hp.setId(rs.getInt("id")) ;
					hp.setCodeId(rs.getString("code_id")) ;
					hp.setCodeNamec(rs.getString("code_namec")) ;
					hp.setEndDate(rs.getTimestamp("end_date")) ;
					hp.setRmplanType(rs.getInt("rmplan_type")) ;
					hp.setStartDate(rs.getTimestamp("start_date")) ;
					hp.setChecked(rs.getInt("checked")) ;
					hp.setEditable(rs.getInt("editable")) ;
					hpVo.setHroomPlan(hp) ;
					list.add(hpVo) ;
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
	public List<HroomPlanVo> findAvilListBy(Integer status, Date startDate,
			Integer roomplanType, String roomType, String currentRoomPlanType) {
		final List<HroomPlanVo> list = new ArrayList<HroomPlanVo>();
		final StringBuilder sbd = new StringBuilder() ; 
		sbd.append(" SELECT \n " );
		sbd.append(" 	t.id ,  \n " );
		sbd.append("    t.code_id ,  \n " );
		sbd.append("    t.code_namec , \n " );
		sbd.append("    t.start_date , \n " );
		sbd.append("    t.end_date , \n " );
		sbd.append("    t.rmplan_type , \n " );
		sbd.append("    t.checked , \n " );
		sbd.append("    t.editable \n " );
		sbd.append(" FROM hroom_plan t  \n " );
		sbd.append(" WHERE t.status = "+status+"  \n " );
		if(startDate != null)
			sbd.append("  and datediff(day,t.start_date,'"+DateFormatUtils.format(startDate, "yyyy-MM-dd")+"') >=0 "
						+"and datediff(day,t.end_date,'"+DateFormatUtils.format(startDate, "yyyy-MM-dd")+"') <=0  ") ;
		if(roomplanType != null)
			sbd.append("  and t.rmplan_type = " + roomplanType) ;
		
		if(StringUtils.isNotBlank(roomType)){
			sbd.append("  and t.code_id in ( \n ");
			sbd.append("      SELECT    \n ");
			sbd.append("          t.rmplan_id    \n ");
			sbd.append("      FROM   \n ");
			sbd.append("          hroom_plan_list t    \n ");
			sbd.append("          INNER JOIN holidays t1    \n ");
			sbd.append("              ON t.holiday_id = t1.holiday_id    \n ");
			sbd.append("              AND t.status = 0    \n ");
			sbd.append("              AND t1.holiday_date = '"+DateFormatUtils.format(parameterDao.GetHotelDate(), "yyyy-MM-dd")+"'    \n ");
			sbd.append("              AND t.status = 0    \n ");
			sbd.append("              AND t.rmtype_id = '"+roomType+"'    \n ");
			sbd.append("  )  \n ");
		}
		if(StringUtils.isNotBlank(currentRoomPlanType)){
			sbd.append("  or t.code_id = '"+currentRoomPlanType.trim()+"' \n ");
		}
		sbd.append(" ORDER BY t.code_id asc \n ") ;
		getSession().doWork(new Work() {
			@Override
			public void execute(Connection connection) throws SQLException {
				
				PreparedStatement pstmt= connection.prepareStatement(sbd.toString()) ;
				ResultSet rs = pstmt.executeQuery() ;
				
				while(rs.next()){
					HroomPlanVo hpVo = new HroomPlanVo();
					HroomPlan hp = new HroomPlan();
					hp.setId(rs.getInt("id")) ;
					hp.setCodeId(rs.getString("code_id")) ;
					hp.setCodeNamec(rs.getString("code_namec")) ;
					hp.setEndDate(rs.getTimestamp("end_date")) ;
					hp.setRmplanType(rs.getInt("rmplan_type")) ;
					hp.setStartDate(rs.getTimestamp("start_date")) ;
					hp.setChecked(rs.getInt("checked")) ;
					hp.setEditable(rs.getInt("editable")) ;
					hpVo.setHroomPlan(hp) ;
					list.add(hpVo) ;
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
	public String findHroomPlanPrice(String roomplanCode, String roomType) {
		StringBuilder sbd = new StringBuilder() ; 
		sbd.append("      SELECT    \n ");
		sbd.append("          t.price    \n ");
		sbd.append("      FROM   \n ");
		sbd.append("          hroom_plan_list t    \n ");
		sbd.append("          INNER JOIN holidays t1    \n ");
		sbd.append("              ON t.holiday_id = t1.holiday_id    \n ");
		sbd.append("              AND t.status = 0    \n ");
		sbd.append("              AND t.rmplan_id = '"+roomplanCode+"'    \n ");
		sbd.append("              AND t1.holiday_date = '"+DateFormatUtils.format(parameterDao.GetHotelDate(), "yyyy-MM-dd")+"'    \n ");
		sbd.append("              AND t.status = 0    \n ");
		sbd.append("              AND t.rmtype_id = '"+roomType+"'    \n ");
		List<Map<String, Object>> list = getSession().createSQLQuery(sbd.toString()).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();
		if(list != null && !list.isEmpty()){
			return String.valueOf(list.get(0).get("price"));
		}else{
			return "-1";
		}
	}

}
