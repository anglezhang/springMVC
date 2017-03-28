package com.zoomoor.jy.dao.impl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.orm.hibernate3.SessionFactoryUtils;
import org.springframework.stereotype.Repository;

import com.zoomoor.common.base.dao.impl.BaseDaoImpl;
import com.zoomoor.common.util.ArrayUtils;
import com.zoomoor.jy.dao.InfoSummaryDao;
import com.zoomoor.jy.entity.InfoDept;
import com.zoomoor.jy.entity.InfoGoods;
import com.zoomoor.jy.entity.InfoSummary;
import com.zoomoor.jy.entity.vo.DepotVo;
@Repository
public class InfoSummaryDaoImpl extends BaseDaoImpl<InfoSummary, Integer> implements
		InfoSummaryDao {

	@Override
	public List getListByUserId(Boolean stype, Integer userId) {
		List list=null;
		Connection conn=null;
		try {
			conn = (Connection) SessionFactoryUtils.getDataSource(sessionFactory).getConnection();
			CallableStatement call = (CallableStatement) conn.prepareCall("{call u_GetSummaryList(?,?)}");
			call.setBoolean(1, stype);
			call.setInt(2, userId);
			ResultSet rs = null;
			rs = call.executeQuery();
			list = ArrayUtils.resultSetToList(rs);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}

}
