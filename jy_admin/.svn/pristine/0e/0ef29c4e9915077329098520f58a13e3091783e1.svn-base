package com.zoomoor.jy.dao.impl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Query;
import org.springframework.orm.hibernate3.SessionFactoryUtils;
import org.springframework.stereotype.Repository;

import com.zoomoor.common.base.bean.Finder;
import com.zoomoor.common.base.bean.Pager;
import com.zoomoor.common.base.dao.impl.BaseDaoImpl;
import com.zoomoor.common.util.ArrayUtils;
import com.zoomoor.jy.dao.AllocationDao;
import com.zoomoor.jy.entity.Allocation;
@Repository
public class AllocationDaoImpl extends BaseDaoImpl<Allocation, Integer> implements
		AllocationDao {
	
	public Integer getMaxByCurrent() {
		String hql ="select max(bean.dateAllocationNo)+1 from Allocation bean where date_format(bean.swDate,'%Y-%m-%d')=date_format(NOW(),'%Y-%m-%d')";
		Query query = getSession().createQuery(hql);
		Number num=(Number)query.uniqueResult();
		if(num==null){num=1;}
		return num.intValue();
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Pager getPage(String queryAllocationNo,String queryEmpName,Integer queryDeptId,String queryStartDate,String queryEndDate,int pageNo, int pageSize) {
		Finder f = Finder.create("from Allocation bean where 1=1 and bean.del=0 ");
		if(StringUtils.isNotEmpty(queryAllocationNo)){
			f.append(" and  bean.allocationNo=:queryAllocationNo");
			f.setParam("queryAllocationNo",queryAllocationNo);
		}
		
		if(queryDeptId!=null&&queryDeptId>0){
			f.append(" and  bean.deptId=:queryDeptId");
			f.setParam("queryDeptId",queryDeptId);
		}
		if(StringUtils.isNotEmpty(queryEmpName)){
			String sql="select u.user_id from sys_user u where u.user_id in(select e.emp_id from info_emp e where e.emp_name like '%"+queryEmpName+"%')";
			List list=this.getSession().createSQLQuery(sql).list();
			String userids="";
			if(list.size()>0){
				for (int i = 0; i < list.size(); i++) {
					userids+=list.get(i)+",";
				}
			}
			if(!"".equals(userids)){
				userids=userids.substring(0, userids.length()-1);
			}
			f.append(" and  bean.userId in('"+userids+"')");
		}
		
		if(StringUtils.isNotEmpty(queryStartDate)){
			f.append(" and date_format(bean.swDate,'%Y-%m-%d')>=:queryStartDate ");
			f.setParam("queryStartDate", queryStartDate);
		}
		if(StringUtils.isNotEmpty(queryEndDate)){
			f.append(" and date_format(bean.swDate,'%Y-%m-%d')<=:queryEndDate");
			f.setParam("queryEndDate", queryEndDate);
		}
		
		f.append(" order by  bean.swDate desc");
		return find(f, pageNo, pageSize);
	}

	@Override
	public List getAllocationForPrint(String allocationNo) {
		List list=null;
		Connection conn_allocation=null;
		try {
			conn_allocation = (Connection) SessionFactoryUtils.getDataSource(sessionFactory).getConnection();
			CallableStatement call_allocation = (CallableStatement) conn_allocation.prepareCall("{call u_GetDepotDBPrint(?)}");
			call_allocation.setString(1, allocationNo);
			ResultSet rs_allocation=null;
			rs_allocation = call_allocation.executeQuery();
			list = ArrayUtils.resultSetToList(rs_allocation);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				conn_allocation.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}

}
