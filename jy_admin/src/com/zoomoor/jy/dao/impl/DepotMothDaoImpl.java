package com.zoomoor.jy.dao.impl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.orm.hibernate3.SessionFactoryUtils;
import org.springframework.stereotype.Repository;

import com.zoomoor.common.base.dao.impl.BaseDaoImpl;
import com.zoomoor.common.util.ArrayUtils;
import com.zoomoor.jy.dao.DepotMothDao;
import com.zoomoor.jy.entity.DepotMoth;
@Repository
public class DepotMothDaoImpl extends BaseDaoImpl<DepotMoth, Integer> implements
		DepotMothDao {

	@Override
	public List getMonthList(Integer depId) {
		List list=null;
		Connection conn_month_list=null;
		try {
			conn_month_list = (Connection) SessionFactoryUtils.getDataSource(sessionFactory).getConnection();
			CallableStatement call_month_list = (CallableStatement) conn_month_list.prepareCall("{call u_DepotClosingTheAccounts_Begin(?)}");
			call_month_list.setInt(1, depId);
			ResultSet rs_month_list=null;
			rs_month_list = call_month_list.executeQuery();
			list = ArrayUtils.resultSetToList(rs_month_list);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				conn_month_list.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}

	@Override
	public List getMonthCheck(Integer depId, String curMonth) {
		List list=null;
		Connection conn_check_list=null;
		try {
			conn_check_list = (Connection) SessionFactoryUtils.getDataSource(sessionFactory).getConnection();
			CallableStatement call_check_list = (CallableStatement) conn_check_list.prepareCall("{call u_DepotClosingTheAccounts_Check(?,?)}");
			call_check_list.setInt(1, depId);
			call_check_list.setString(2, curMonth);
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
	public void checkBalance(Integer depId) {
		List list=null;
		Connection conn_balance_list=null;
		try {
			conn_balance_list = (Connection) SessionFactoryUtils.getDataSource(sessionFactory).getConnection();
			CallableStatement call_balance_list = (CallableStatement) conn_balance_list.prepareCall("{call u_DepotClosingTheAccounts_Do(?)}");
			call_balance_list.setInt(1, depId);
			call_balance_list.executeQuery();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				conn_balance_list.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}

}
