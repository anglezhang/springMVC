package com.zoomoor.jy.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.zoomoor.common.base.bean.Finder;
import com.zoomoor.common.base.bean.Pager;
import com.zoomoor.common.base.dao.impl.BaseDaoImpl;
import com.zoomoor.jy.dao.InfoCarDao;
import com.zoomoor.jy.entity.InfoCar;

/**
 * 描述：InfoCarSvcDaoImpl.java
 * 
 * @author Zhangzhenxing
 * @Date 2015年7月24日 下午2:15:55
 * @version 1.0
 */
@Repository
public class InfoCarDaoImpl extends BaseDaoImpl<InfoCar, Integer> implements
		InfoCarDao {

	/**
	 * 描述：该方法作用
	 * 
	 * @author zhangZhenxing
	 * @Date 2015年7月24日
	 */
	@Override
	public Pager getLookPager(Pager pager, String carNo, Integer customerId,
			String customerName) {
		Finder f = Finder.create("FROM InfoCar bean WHERE 1=1 and bean.del=0 ");
		if (StringUtils.isNotEmpty(carNo)) {
			f.append(" and bean.platenum like :carNo ");
			f.setParam("carNo", "%" + carNo + "%");
		}
		// 根据客户id
		List<Integer> carIDS = new ArrayList<Integer>();
		if (customerId != null && customerId != 0) {
			String sql = "SELECT car_id FROM car_custmer_mapping where customer_id="
					+ customerId;
			Query query = this.getSession().createSQLQuery(sql);
			carIDS = query.list();
			
		}
		// 根据客户姓名查询
		if (StringUtils.isNotEmpty(customerName)) {
			String sql = "SELECT car_id FROM car_custmer_mapping cm"
					+ " LEFT JOIN info_custome cu on cm.customer_id=cu.customer_id"
					+ " WHERE cu.customer_name LIKE '%" 
					+ customerName + "%'";
			Query query = this.getSession().createSQLQuery(sql);
			carIDS.addAll(query.list());
		}
		//拼接SQL
		StringBuffer appSql = new StringBuffer();
		if (carIDS.size() > 0) {
			appSql.append(" and (");
			for (Integer carID : carIDS) {
				appSql.append(" bean.carId = " + carID + " or ");
			}
			appSql.append(" 1=2 ) ");
			f.append(appSql.toString());
		}else{
			if((customerId != null && customerId != 0) || StringUtils.isNotEmpty(customerName)){
				f.append(" and 1=2 ");
			}
		}
		f.append(" order by bean.carId desc ");
		return find(f, pager.getPageNum(), pager.getNumPerPage());
	}

}
