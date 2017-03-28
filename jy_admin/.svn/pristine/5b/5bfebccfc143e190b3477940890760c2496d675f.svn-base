package com.zoomoor.jy.dao.impl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Hibernate;
import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;
import org.springframework.orm.hibernate3.SessionFactoryUtils;
import org.springframework.stereotype.Repository;

import com.zoomoor.common.base.bean.Finder;
import com.zoomoor.common.base.bean.Pager;
import com.zoomoor.common.base.dao.impl.BaseDaoImpl;
import com.zoomoor.common.util.ArrayUtils;
import com.zoomoor.jy.dao.AllocationApplyDao;
import com.zoomoor.jy.entity.AllocationApply;
import com.zoomoor.jy.entity.vo.AllocationCountVo;
@Repository
public class AllocationApplyDaoImpl extends BaseDaoImpl<AllocationApply, Integer> implements
		AllocationApplyDao {

	@Override
	public Pager getPage(String queryGoodsName, String queryGoodsBrand,String queryGoodsCode,String unitDate,
			Integer queryStatus, String queryStartDate, String queryEndDate,
			Integer typeId, Integer deptId,int pageNo, int pageSize) {
		Finder f = Finder.create("from AllocationApply bean where 1=1 and bean.del=0 ");
		if(StringUtils.isNotEmpty(queryGoodsName)){
			f.append(" and  bean.infoGoods.name like :queryGoodsName");
			f.setParam("queryGoodsName","%"+queryGoodsName+"%");
		}
		if(StringUtils.isNotEmpty(queryGoodsBrand)){
			f.append(" and  bean.infoGoods.goodsBrand like :queryGoodsBrand");
			f.setParam("queryGoodsBrand","%"+queryGoodsBrand+"%");
		}
		if(StringUtils.isNotEmpty(queryGoodsCode)){
			f.append(" and  bean.infoGoods.goodsCode like :queryGoodsCode");
			f.setParam("queryGoodsCode","%"+queryGoodsCode+"%");
		}
		if(queryStatus!=null){
			f.append(" and  bean.status=:queryStatus");
			f.setParam("queryStatus",queryStatus);
		}
		if(StringUtils.isNotEmpty(queryStartDate)){
			f.append(" and date_format(bean.unitDate,'%Y-%m-%d')>=:queryStartDate ");
			f.setParam("queryStartDate", queryStartDate);
		}
		if(StringUtils.isNotEmpty(queryEndDate)){
			f.append(" and date_format(bean.unitDate,'%Y-%m-%d')<=:queryEndDate");
			f.setParam("queryEndDate", queryEndDate);
		}
		if(StringUtils.isNotEmpty(unitDate)){
			f.append(" and date_format(bean.unitDate,'%Y-%m-%d')=:unitDate");
			f.setParam("unitDate", unitDate);
		}
		if(typeId!=null&&typeId>0){
			f.append(" and  bean.infoGoods.infoGoodsType.groupId=:typeId");
			f.setParam("typeId",typeId);
		}
		if(deptId!=null&&deptId>0){
			f.append(" and  bean.infoDept.deptId=:deptId");
			f.setParam("deptId",deptId);
		}
		f.append(" order by bean.status asc, bean.unitDate desc");
		return find(f, pageNo, pageSize);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<AllocationCountVo> getCountPage(String queryStartDate, String queryEndDate,Integer deptId,Integer currentIndex) {
		String sql="select d.dept_id as countDeptId,d.name as countDeptName,DATE_FORMAT(a.unit_date,'%Y-%m-%d') as countDate," +
				"count(a.goods_id) as countNum ,sum(g.cost_price) as countMoney from allocation_apply a,info_dept d,info_goods g  " +
				"where a.dep_id=d.dept_id and a.goods_id=g.goods_id and a.del=0 ";
		if(deptId!=null){
			sql+=" and d.dept_id="+deptId;
		}
		if(StringUtils.isNotEmpty(queryStartDate)){
			sql+=" and date_format(a.unit_date,'%Y-%m-%d')>='"+queryStartDate+"'";
		}
		if(StringUtils.isNotEmpty(queryEndDate)){
			sql+=" and date_format(a.unit_date,'%Y-%m-%d')<='"+queryEndDate+"'";
		}
		sql+=" GROUP BY a.dep_id,DATE_FORMAT(a.unit_date,'%Y-%m-%d')";
		
		sql+=" order by a.unit_date desc " ;
		SQLQuery query = this.getSession().createSQLQuery(sql);
		query.addScalar("countDeptId", Hibernate.INTEGER).addScalar("countDeptName",Hibernate.STRING).addScalar("countDate",Hibernate.DATE).
		addScalar("countNum",Hibernate.INTEGER).addScalar("countMoney", Hibernate.DOUBLE);
		query.setResultTransformer(Transformers.aliasToBean(AllocationCountVo.class));
		List<AllocationCountVo> list=query.list();
		return list;
	}

	@Override
	public List<AllocationCountVo> getCountGoodsList(String queryGoodsName,
			String queryGoodsCode,Integer currentIndex) {
		String sql="SELECT g.goods_id as countGoodsId,g.goods_code as countGoodsCode , g.name as countGoodsName,g.standard as countStandard,g.type_show as countTypeShow,sum(a.num) as countNum ,sum(g.cost_price) as countMoney" +
				" from  allocation_apply a,info_goods g where a.goods_id=g.goods_id";
		if(StringUtils.isNotEmpty(queryGoodsCode)){
			sql+=" and g.goods_code like '%"+queryGoodsCode+"%'";
		}
		if(StringUtils.isNotEmpty(queryGoodsName)){
			sql+=" and g.name like '%"+queryGoodsName+"%'";
		}
		sql+=" GROUP BY a.goods_id";
		SQLQuery query = this.getSession().createSQLQuery(sql);
		query.addScalar("countGoodsId", Hibernate.INTEGER).addScalar("countGoodsName",Hibernate.STRING).addScalar("countStandard",Hibernate.STRING)
		.addScalar("countTypeShow", Hibernate.STRING).addScalar("countNum",Hibernate.INTEGER)
		.addScalar("countMoney", Hibernate.DOUBLE).addScalar("countGoodsCode",Hibernate.STRING );
		query.setResultTransformer(Transformers.aliasToBean(AllocationCountVo.class));
		List<AllocationCountVo> list=query.list();
		return list;
	}

	@Override
	public List<AllocationApply> getListByDeptId(Integer deptId) {
		String hql="from AllocationApply bean where 1=1 and bean.del=0";
		if(deptId!=null&&deptId>0){
			hql+=" and  bean.infoDept.deptId="+deptId+"and bean.status=0";
		}
		return createQueryList(hql);
	}

	@Override
	public List getDepotNum(Integer deptId,Integer supId,Integer positionId,Integer goodsId,Integer showDept,Integer showSup,Integer showPosition) {
		List list=null;
		Connection conn_num=null;
	try {
		conn_num= (Connection) SessionFactoryUtils.getDataSource(sessionFactory).getConnection();
		CallableStatement call_num=(CallableStatement) conn_num.prepareCall("{call u_GetStock(?,?,?,?,?,?,?)}");
		call_num.setInt(1, deptId);
		call_num.setInt(2, supId);
		call_num.setInt(3, positionId);
		call_num.setInt(4, goodsId);
		call_num.setInt(5, showDept);
		call_num.setInt(6, showSup);
		call_num.setInt(7, showPosition);
		ResultSet rs_num=null;
		rs_num=call_num.executeQuery();	
		list=ArrayUtils.resultSetToList(rs_num);
	} catch (SQLException e) {
		e.printStackTrace();
	}finally{
		try {
			conn_num.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}	
		return list;
	}
	@Override
	public AllocationApply getApplyNumById(Integer todeptId, Integer goodsId,
			Integer id) {
		String hql="from AllocationApply bean where bean.del=0 and bean.infoDept.deptId="+todeptId+
				" and bean.infoGoods.goodsId="+goodsId+" and bean.allocation.id="+id;
		List<AllocationApply> allocationApplyList=createQueryList(hql);
		if(allocationApplyList!=null&&allocationApplyList.size()>0){
			return allocationApplyList.get(0);
		}else{
			return null;
		}
		
	}

	@Override
	public List getDepotListBySummaryId(Integer tagId) {
		String sql="Select l.allocation_id from allocation_list l where  l.sw_id="+tagId+" and l.del=0 ";
		return this.getSession().createSQLQuery(sql).list();
	}

	@Override
	public void updateAllocationApplyByTagId(Integer tagId) {
		String sql="UPDATE allocation_apply SET STATUS=2 where allocation_id in (Select l.allocation_id from allocation_list l where  l.sw_id="+tagId+" and l.del=0)";
		this.getSession().createSQLQuery(sql).executeUpdate();
	}

	@Override
	public List getDepotView(Integer goodsId) {
		List list=null;
		Connection conn_num=null;
	try {
		conn_num= (Connection) SessionFactoryUtils.getDataSource(sessionFactory).getConnection();
		CallableStatement call_num=(CallableStatement) conn_num.prepareCall("{call u_GetGoodsOrderInfo(?)}");
		call_num.setInt(1, goodsId);
		ResultSet rs_num=null;
		rs_num=call_num.executeQuery();	
		list=ArrayUtils.resultSetToList(rs_num);
	} catch (SQLException e) {
		e.printStackTrace();
	}finally{
		try {
			conn_num.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}	
		return list;
	}

	@Override
	public List getDepotCount(Integer deptId,Integer positionId,Integer brandId,Integer typeId, String showDept,
			String showSup, String showPosition) {
		List list=null;
		Connection conn_num=null;
	try {
		conn_num= (Connection) SessionFactoryUtils.getDataSource(sessionFactory).getConnection();
		CallableStatement call_num=(CallableStatement) conn_num.prepareCall("{call u_GetDepotStockRep(?,?,?,?,?,?,?)}");
		call_num.setInt(1, deptId);
		call_num.setInt(2, positionId);
		call_num.setInt(3, brandId);
		call_num.setInt(4, typeId);
		call_num.setString(5, showDept);
		call_num.setString(6, showSup);
		call_num.setString(7, showPosition);
		ResultSet rs_num=null;
		rs_num=call_num.executeQuery();	
		list=ArrayUtils.resultSetToList(rs_num);
	} catch (SQLException e) {
		e.printStackTrace();
	}finally{
		try {
			conn_num.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}	
		return list;
	}

	@Override
	public List getDepotNumByGoodsId(Integer goodsId) {
		List list=null;
		Connection conn_num=null;
	try {
		conn_num= (Connection) SessionFactoryUtils.getDataSource(sessionFactory).getConnection();
		CallableStatement call_num=(CallableStatement) conn_num.prepareCall("{call u_GetStockAllByGoodsId(?)}");
		call_num.setInt(1, goodsId);
		ResultSet rs_num=null;
		rs_num=call_num.executeQuery();	
		list=ArrayUtils.resultSetToList(rs_num);
	} catch (SQLException e) {
		e.printStackTrace();
	}finally{
		try {
			conn_num.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}	
		return list;
	}
}
