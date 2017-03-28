package com.zoomoor.jy.dao.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.zoomoor.common.base.bean.Finder;
import com.zoomoor.common.base.bean.Pager;
import com.zoomoor.common.base.dao.impl.BaseDaoImpl;
import com.zoomoor.jy.dao.InfoCustomeDao;
import com.zoomoor.jy.entity.InfoCustome;
import com.zoomoor.jy.service.InfoCustomeSvc;

/**
 * 描述：InfoCustomeDaoImpl.java
 * 
 * @author Zhangzhenxing
 * @Date 2015年7月23日 下午4:23:56
 * @version 1.0
 */
@Repository
public class InfoCustomeDaoImpl extends BaseDaoImpl<InfoCustome, Integer>
		implements InfoCustomeDao {

	/**
	 * 描述：该方法作用
	 * 
	 * @author zhangZhenxing
	 * @Date 2015年7月23日
	 */
	@Override
	public Pager getLookupPager(Pager pager, String querytName,
			String queryTel, String CarNO, Integer carId) {
		Finder f = Finder
				.create("FROM InfoCustome bean WHERE 1=1 and bean.del=0 ");
		if (StringUtils.isNotEmpty(querytName)) {
			f.append(" and bean.customerName like :querytName ");
			f.setParam("querytName", "%" + querytName + "%");
		}
		if (StringUtils.isNotEmpty(queryTel)) {
			f.append(" and bean.tel like :queryTel ");
			f.setParam("queryTel", "%" + queryTel + "%");
		}
		if (StringUtils.isNotEmpty(CarNO)) {
			String sql = "SELECT ccm.customer_id from car_custmer_mapping ccm "
					+ " LEFT JOIN info_car inf on inf.car_id=ccm.car_id "
					+ " where inf.platenum like '%" + CarNO + "%'";
			Query query = this.getSession().createSQLQuery(sql);
			List<Integer> customerIds = query.list();
			if (customerIds.size() > 0) {
				StringBuffer appendSQL = new StringBuffer("and ( ");
				for (Integer obj : customerIds) {
					appendSQL.append(" bean.customerId=" + obj + " or ");
				}
				appendSQL.append(" or 1=2 ) ");
			}

		}
		if (carId != null) {
			String sql = "SELECT customer_id FROM car_custmer_mapping WHERE car_id="
					+ carId;
			Query query = this.getSession().createSQLQuery(sql);
			List<Integer> customerIds = query.list();
			if (customerIds.size() > 0) {
				StringBuffer appendSQL = new StringBuffer("and bean.customerId in ( ");
				for (Integer obj : customerIds) {
					appendSQL.append(obj + ",");
				}
				appendSQL.append(" -1 ) ");
				f.append(appendSQL.toString());
			}
			
		}
		f.append(" order by bean.customerId desc");
		return find(f, pager.getPageNum(), pager.getNumPerPage());
	}

}
