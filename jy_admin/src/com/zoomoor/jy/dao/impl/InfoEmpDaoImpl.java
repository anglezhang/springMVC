package com.zoomoor.jy.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.zoomoor.common.base.bean.Finder;
import com.zoomoor.common.base.bean.Pager;
import com.zoomoor.common.base.dao.impl.BaseDaoImpl;
import com.zoomoor.jy.dao.InfoEmpDao;
import com.zoomoor.jy.entity.InfoEmp;
@Repository
public class InfoEmpDaoImpl extends BaseDaoImpl<InfoEmp, Integer> implements InfoEmpDao {

	/**
	* 描述：该方法作用
	* @author zhangZhenxing
	* @Date 2015年8月3日
	*/
	@Override
	public List<InfoEmp> getEmpByType(String type, Integer deptId) {
		Finder f = Finder.create("FROM InfoEmp bean WHERE 1=1 and bean.status=1 and bean.del=0 ");//在职人员
//		if(StringUtils.isNotEmpty(type)){
//			List<Integer> empIDs = new ArrayList<Integer>();
//			String sql = "SELECT cem.emp_id FROM config_emp_mapping "
//					+ "cem LEFT JOIN param_config "
//					+ " pc on pc.unit_id=cem.unit_id"
//					+ " WHERE cem.del=0 and "
//					+ "pc.name LIKE '%" 
//					+ type + "%'";
//			Query query = this.getSession().createSQLQuery(sql);
//			empIDs = query.list();
//			if(empIDs.size() > 0){
//				StringBuffer appSQL = new  StringBuffer(" and bean.empId in(");
//				for(Integer empid:empIDs){
//					appSQL.append(empid + ",");
//				}
//				appSQL.append(" -1 )");
//				f.append(appSQL.toString());
//			}
//		}
		if(!"".equals(deptId) && deptId != null){
			f.append(" and bean.infoDept.deptId =:deptId");
			f.setParam("deptId", deptId);
		}
		return find(f);
	}

	/**
	* 描述：该方法作用
	* @author zhangZhenxing
	* @Date 2015年8月7日
	*/
	@Override
	public Pager getPager(Pager pager,Integer deptId, String queryEmpCode,
			String queryName, Integer gender, String position, Integer status,
			String queryIdCode,Integer islook) {
		Finder f = Finder.create("FROM InfoEmp bean WHERE 1=1  AND bean.del=0 ");//在职人员
		if(deptId !=null && deptId > 0){
			f.append(" AND bean.infoDept.deptId =:deptId ");
			f.setParam("deptId", deptId);
		}
		if(StringUtils.isNotEmpty(queryEmpCode)){
			f.append(" AND bean.empCode like :queryEmpCode");
			f.setParam("queryEmpCode", "%" + queryEmpCode + "%");
		}
		if(StringUtils.isNotEmpty(queryName)){
			f.append(" AND bean.empName like:queryName");
			f.setParam("queryName", "%" + queryName + "%");
		}
		if(gender!=null){
			f.append(" AND bean.gender =:gender ");
			if(gender==1){
				f.setParam("gender", true);
			}else
				f.setParam("gender", false);
			
		}
		//lookUp只查询未被绑定的员工
		if(islook!=null&&islook>0){
			f.append(" and bean.empId not in "
					+ "(select suer.infoEmp.empId from SysUser suer where suer.isDisabled=0 and del=0"
					+ " and suer.infoEmp.empId is not null ) ");	
			f.append(" and bean.status=1 ");//在职人员，未绑定人员
		}
		if(position!=null && !"".equals(position)){
			f.append(" AND bean.position like :position ");
			f.setParam("position", "%" + position + "$");
		}
		if(status!=null){
			f.append(" AND bean.status =:status ");
			f.setParam("status", status);
		}
		if(StringUtils.isNotEmpty(queryIdCode)){
			f.append(" AND bean.idCard queryIdCode");
			f.setParam("queryIdCode", "%" + queryIdCode + "%");
		}
		f.append(" ORDER BY bean.createtime DESC");
		return find(f, pager.getPageNum(), pager.getNumPerPage());
	}

	@Override
	public List getMappingList(Integer unitId) {
		String sql=" select *from config_emp_mapping where unit_idselect *from config_emp_mapping where unit_id="+unitId;
		return this.getSession().createSQLQuery(sql).list();
	}

}
