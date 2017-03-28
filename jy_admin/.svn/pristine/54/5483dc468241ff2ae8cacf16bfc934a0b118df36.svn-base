package com.zoomoor.jy.dao.impl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.orm.hibernate3.SessionFactoryUtils;
import org.springframework.stereotype.Repository;

import com.zoomoor.common.base.bean.Finder;
import com.zoomoor.common.base.bean.Pager;
import com.zoomoor.common.base.dao.impl.BaseDaoImpl;
import com.zoomoor.common.util.ArrayUtils;
import com.zoomoor.jy.dao.DepotCheckDao;
import com.zoomoor.jy.entity.DepotCheck;
@Repository
public class DepotCheckDaoImpl extends BaseDaoImpl<DepotCheck, Integer> implements
		DepotCheckDao {

	@Override
	public List getCheckListByDeptId(Integer deptId, String positions) {
		List list=null;
		Connection conn_check_list=null;
		try {
			conn_check_list = (Connection) SessionFactoryUtils.getDataSource(sessionFactory).getConnection();
			CallableStatement call_check_list = (CallableStatement) conn_check_list.prepareCall("{call u_DepotCheck_GetTempList(?,?)}");
			call_check_list.setInt(1, deptId);
			call_check_list.setString(2, positions);
			ResultSet rs_check_list=null;
			rs_check_list = call_check_list.executeQuery();
			list = ArrayUtils.resultSetToList(rs_check_list);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				conn_check_list.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}

	@Override
	public List saveCheck(Integer deptId, String docStr) {
		List list=null;
		Connection conn_check_save=null;
		try {
			conn_check_save = (Connection) SessionFactoryUtils.getDataSource(sessionFactory).getConnection();
			CallableStatement call_check_save = (CallableStatement) conn_check_save.prepareCall("{call u_DepotCheck_Create(?,?)}");
			call_check_save.setInt(1, deptId);
			call_check_save.setString(2, docStr);
			ResultSet rs_check_save=null;
			rs_check_save = call_check_save.executeQuery();
			list = ArrayUtils.resultSetToList(rs_check_save);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				conn_check_save.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}

	@Override
	public List getCheckListByCheckNo(Integer deptId, String checkNo) {
		List list=null;
		Connection conn_check_enter=null;
		try {
			conn_check_enter = (Connection) SessionFactoryUtils.getDataSource(sessionFactory).getConnection();
			CallableStatement call_check_enter = (CallableStatement) conn_check_enter.prepareCall("{call u_DepotCheck_GetList(?,?)}");
			call_check_enter.setInt(1, deptId);
			call_check_enter.setString(2, checkNo);
			ResultSet rs_check_enter=null;
			rs_check_enter = call_check_enter.executeQuery();
			list = ArrayUtils.resultSetToList(rs_check_enter);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				conn_check_enter.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}

	@Override
	public List enterCheck(Integer deptId, String docStr) {
		List list=null;
		Connection conn_save_enter=null;
		try {
			conn_save_enter = (Connection) SessionFactoryUtils.getDataSource(sessionFactory).getConnection();
			CallableStatement call_save_enter = (CallableStatement) conn_save_enter.prepareCall("{call u_DepotCheck_Save(?,?)}");
			call_save_enter.setInt(1, deptId);
			call_save_enter.setString(2, docStr);
			ResultSet rs_save_enter=null;
			rs_save_enter = call_save_enter.executeQuery();
			list = ArrayUtils.resultSetToList(rs_save_enter);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				conn_save_enter.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}

	@Override
	public List getCheckListForResult(Integer deptId,String checkNo) {
		List list=null;
		Connection conn_check_result=null;
		try {
			conn_check_result = (Connection) SessionFactoryUtils.getDataSource(sessionFactory).getConnection();
			CallableStatement call_check_result = (CallableStatement) conn_check_result.prepareCall("{call u_DepotCheck_GetBalance(?,?)}");
			call_check_result.setInt(1, deptId);
			call_check_result.setString(2, checkNo);
			ResultSet rs_check_result=null;
			rs_check_result = call_check_result.executeQuery();
			list = ArrayUtils.resultSetToList(rs_check_result);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				conn_check_result.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}

	@Override
	public List createResultCheck(Integer deptId, String checkNo,
			Integer empId, String memo,Integer userId) {
		List list=null;
		Connection conn_create_result=null;
		try {
			conn_create_result = (Connection) SessionFactoryUtils.getDataSource(sessionFactory).getConnection();
			CallableStatement call_create_result = (CallableStatement) conn_create_result.prepareCall("{call u_DepotCheck_CreateDepotBill(?,?,?,?,?)}");
			call_create_result.setInt(1, deptId);
			call_create_result.setString(2, checkNo);
			call_create_result.setInt(3, empId);
			call_create_result.setString(4, memo);
			call_create_result.setInt(5, userId);
			ResultSet rs_create_result=null;
			rs_create_result = call_create_result.executeQuery();
			list = ArrayUtils.resultSetToList(rs_create_result);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				conn_create_result.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}

	@Override
	public Pager getPage(String queryAllocationNo,String queryEmpName,String queryStartDate,String queryEndDate,Integer status,Integer queryDeptId,int pageNo, int pageSize){
		Finder f = Finder.create("from DepotCheck bean where 1=1 and bean.del=0");
		if(StringUtils.isNotEmpty(queryAllocationNo)){
			f.append(" and  bean.BNo like:queryAllocationNo");
			f.setParam("queryAllocationNo","%"+queryAllocationNo+"%" );
		}
		/*if(StringUtils.isNotEmpty(queryEmpName)){
			f.append(" and  bean.empName like:queryEmpName");
			f.setParam("queryEmpName","%"+queryEmpName+"%" );
		}*/
		if(StringUtils.isNotEmpty(queryStartDate)){
			f.append(" and date_format(bean.checkDate,'%Y-%m-%d')>=:queryStartDate ");
			f.setParam("queryStartDate", queryStartDate);
		}
		if(StringUtils.isNotEmpty(queryEndDate)){
			f.append(" and date_format(bean.checkDate,'%Y-%m-%d')<=:queryEndDate");
			f.setParam("queryEndDate", queryEndDate);
		}
		if(queryDeptId!=null&&queryDeptId>0){
			f.append(" and bean.depId=:queryDeptId");
			f.setParam("queryDeptId",queryDeptId );
		}
		f.append(" order by bean.checkDate desc");
		return find(f, pageNo, pageSize);
	}

	@Override
	public List<DepotCheck> getDepotListByNo(Integer deptId,String checkNo) {
		String hql="from DepotCheck bean where 1=1 and bean.del=0 ";
		if(StringUtils.isNotEmpty(checkNo)){
			hql+=" and  bean.BNo like %'"+checkNo+"'%";
		}
		if(deptId!=null&&deptId>0){
			hql+=" and bean.depId="+deptId;
		}
		hql+=" group by bean.BNo ";
		hql+=" order by bean.checkDate desc";
		return this.createQueryList(hql);
	}

}
