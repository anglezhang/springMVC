package com.zoomoor.jy.dao.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.zoomoor.common.base.bean.Finder;
import com.zoomoor.common.base.bean.Pager;
import com.zoomoor.common.base.dao.impl.BaseDaoImpl;
import com.zoomoor.jy.dao.OrderDao;
import com.zoomoor.jy.entity.OrderPurchase;
@Repository
public class OrderDaoImpl extends BaseDaoImpl<OrderPurchase, Integer> implements OrderDao {

	@SuppressWarnings("rawtypes")
	@Override
	public Pager getPage(String queryOrderNo, String queryEmpName,
			String querySubName, Integer queryStatus,Integer auditType, String queryStartDate,
			String queryEndDate, int pageNo, int pageSize) {
		Finder f = Finder.create("from OrderPurchase bean ");
		if(auditType!=null&&auditType>1){
			String hql="select o.orderPurchase.purOrderId  from OrderAudit o where o.status=1 and   o.auditType="+(auditType-1);
			List list=createQueryList(hql);
			String strsql="";
			if(list!=null&&list.size()>0){
				for(int i=0;i<list.size();i++){
					strsql+=list.get(i)+",";
				}
			}else{
				strsql=0+",";
			}
			strsql=strsql.substring(0,strsql.length()-1);
			f.append(" where bean.purOrderId in("+strsql+")");
		}else{
			f.append(" where 1=1 and bean.del=0 ");
		}
		if(StringUtils.isNotEmpty(queryOrderNo)){
			f.append(" and  bean.purOrderNo like:queryOrderNo");
			f.setParam("queryOrderNo","%"+queryOrderNo+"%" );
		}
		if(StringUtils.isNotEmpty(queryEmpName)){
			f.append(" and  bean.infoEmp.empName like:queryEmpName");
			f.setParam("queryEmpName","%"+queryEmpName+"%" );
		}
		if(StringUtils.isNotEmpty(querySubName)){
			f.append(" and  bean.infoSup.name like:querySubName");
			f.setParam("querySubName","%"+querySubName+"%" );
		}
		if(queryStatus!=null){
			f.append(" and  bean.status=:queryStatus");
			f.setParam("queryStatus",queryStatus);
		}
		if(StringUtils.isNotEmpty(queryStartDate)){
			f.append(" and date_format(bean.purOrderDate,'%Y-%m-%d')>=:queryStartDate ");
			f.setParam("queryStartDate", queryStartDate);
		}
		if(StringUtils.isNotEmpty(queryEndDate)){
			f.append(" and date_format(bean.purOrderDate,'%Y-%m-%d')<=:queryEndDate");
			f.setParam("queryEndDate", queryEndDate);
		}
		
		f.append(" order by bean.purOrderId desc");
		return find(f, pageNo, pageSize);
		
	}

	@Override
	public Integer getMaxByCurrent() {
		String hql ="select max(bean.dateOrderNo)+1 from OrderPurchase bean where date_format(bean.purOrderDate,'%Y-%m-%d')=date_format(NOW(),'%Y-%m-%d')";
		Query query = getSession().createQuery(hql);
		Number num=(Number)query.uniqueResult();
		if(num==null){num=1;}
		return num.intValue();
	}

	@Override
	public Double getMaxBySysUserId(Integer userId) {
		String sql="select max(ma.quota) from money_auth ma where ma.id in(select mum.id from money_user_mapping mum where mum.role_id in (SELECT ur.role_id from sys_user_role ur where ur.user_id="+userId+")) and ma.auth_key='m'";
		Query query = getSession().createSQLQuery(sql);
		Number num=(Number)query.uniqueResult();
		if(num==null){num=0;}
		return num.doubleValue();
	}

}
