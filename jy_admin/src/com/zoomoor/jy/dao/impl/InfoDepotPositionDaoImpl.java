package com.zoomoor.jy.dao.impl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.orm.hibernate3.SessionFactoryUtils;
import org.springframework.stereotype.Repository;

import com.zoomoor.common.base.dao.impl.BaseDaoImpl;
import com.zoomoor.common.util.ArrayUtils;
import com.zoomoor.jy.dao.InfoDepotPositionDao;
import com.zoomoor.jy.entity.InfoDepotPosition;
import com.zoomoor.jy.entity.InfoDept;
import com.zoomoor.jy.entity.InfoGoodsType;
@Repository
public class InfoDepotPositionDaoImpl extends BaseDaoImpl<InfoDepotPosition, Integer> implements
		InfoDepotPositionDao {

	@Override
	public List<InfoDepotPosition> getListByName(String name,Integer upid,InfoDept infoDept) {
		String hql="from InfoDepotPosition bean where bean.del=0 ";
		if(StringUtils.isNotEmpty(name)){
			hql+=" and bean.name=? and bean.infoDepotPosition.id="+upid;
			return createQueryList(hql, name);
		}
		if(infoDept!=null){
			hql+=" and bean.infoDept.deptId="+infoDept.getDeptId();	
		}
		hql+=" order by bean.sort asc ";
		return createQueryList(hql);
	}

	@Override
	public List<InfoDepotPosition> getChildById(Integer authid, Integer tpid) {
		String hql = "from InfoDepotPosition bean  where bean.infoDepotPosition.id="+tpid;
		hql+=" order by sort asc";
		return this.createQueryList(hql);
	}

	@Override
	public String getPositionFullName(Integer pid) {
		String sql="select GetPositionFullName("+pid+")";
		List list=this.getSession().createSQLQuery(sql).list();
		return list.get(0).toString();
	}


}
