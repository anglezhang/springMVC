package com.zoomoor.jy.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.zoomoor.common.base.bean.Finder;
import com.zoomoor.common.base.bean.Pager;
import com.zoomoor.common.base.dao.impl.BaseDaoImpl;
import com.zoomoor.jy.dao.CustomerAppoitDao;
import com.zoomoor.jy.entity.CustomerAppoit;

/**
 * 描述：CustomerAppoitDaoImpl.java
 * 
 * @author Zhangzhenxing
 * @Date 2015年7月17日 下午6:06:54
 * @version 1.0
 */
@Repository
public class CustomerAppoitDaoImpl extends BaseDaoImpl<CustomerAppoit, Integer>
		implements CustomerAppoitDao {
	/**
	 * 描述：该方法作用
	 * 
	 * @author zhangZhenxing
	 * @Date 2015年7月17日
	 */
	@Override
	public Pager getList(Pager pager, String queryTel, String queryPlatenum,
			Integer queryAppType, Integer querySource, String queryBegintime,
			String queryEndtime, String querySellstart, String querySellend,
			Integer queryStatus) {
		Finder f = Finder
				.create("FROM CustomerAppoit bean WHERE 1=1 and bean.del=0 ");
		// 判断是联系电话还是车主姓名
		if (StringUtils.isNotEmpty(queryTel)) {
			String reg = "^[0-9]*$";
			if (!queryTel.matches(reg)) {
				f.append(" and bean.infoCustomer.customerName like :queryTel ");
				f.setParam("queryTel", "%" + queryTel + "%");
			} else {
				f.append(" and bean.infoCustomer.tel like :queryTel ");
				f.setParam("queryTel", "%" + queryTel + "%");
			}
		}
		// 车牌号
		if (StringUtils.isNotEmpty(queryPlatenum)) {
			f.append(" and bean.infoCar.platenum like :queryPlatenum ");
			f.setParam("queryPlatenum", "%" + queryPlatenum + "%");
		}
		// 预约类型
		if (queryAppType != null) {
			f.append(" and bean.appoittype  LIKE :queryAppType ");
			f.setParam("queryAppType", queryAppType.toString());
		}
		// 预约来源
		if (querySource != null) {
			f.append(" and bean.source =:querySource ");
			f.setParam("querySource", querySource);
		}
		if (StringUtils.isNotEmpty(queryBegintime)
				&& StringUtils.isNotEmpty(queryEndtime)) {
			f.append("and (date_format(bean.createtime,'%Y-%m-%d %H:%i:%S') between :queryBegintime and :queryEndtime )");
			f.setParam("queryBegintime", queryBegintime);
			f.setParam("queryEndtime", queryEndtime);
		} else {
			// 查询开始时间
			if (StringUtils.isNotEmpty(queryBegintime)) {
				f.append(" and date_format(bean.createtime,'%Y-%m-%d %H:%i:%S')>=:queryBegintime");
				f.setParam("queryBegintime", queryBegintime);
			}
			// 查询结束时间
			if (StringUtils.isNotEmpty(queryEndtime)) {
				f.append(" and date_format(bean.createtime,'%Y-%m-%d %H:%i:%S')<=:queryEndtime");
				f.setParam("queryEndtime", queryBegintime);
			}
		}
		if (StringUtils.isNotEmpty(querySellstart)
				&& StringUtils.isNotEmpty(querySellend)) {
			f.append("and (date_format(bean.appoittime,'%Y-%m-%d %H:%i:%S') between :querySellstart and :querySellend )");
			f.setParam("querySellstart", querySellstart);
			f.setParam("querySellend", querySellend);
		} else {
			// 查询开始时间
			if (StringUtils.isNotEmpty(querySellstart)) {
				f.append(" and date_format(bean.appoittime,'%Y-%m-%d %H:%i:%S')>=:querySellstart");
				f.setParam("querySellstart", querySellstart);
			}
			// 查询结束时间
			if (StringUtils.isNotEmpty(querySellend)) {
				f.append(" and  date_format(bean.appoittime,'%Y-%m-%d %H:%i:%S')<=:querySellend");
				f.setParam("querySellend", querySellend);
			}
		}
		if (queryStatus != null) {
			f.append(" and bean.cus=:queryStatus");
			f.setParam("queryStatus", queryStatus);
		}
		f.append(" order by bean.cusappoitId desc ");
		return find(f, pager.getPageNum(), pager.getNumPerPage());
	}

	/**
	 * 描述：该方法作用
	 * 
	 * @author zhangZhenxing
	 * @Date 2015年7月30日
	 */
	@Override
	public Integer maxOrderID() {
		String queryMaxSql = "SELECT MAX(cusappoit_id) as max FROM customer_appoit";
		Query query = this.getSession().createSQLQuery(queryMaxSql);
		Integer max = (Integer) query.list().get(0);
		return max;
	}

	/**
	 * 描述：该方法作用
	 * 
	 * @author zhangZhenxing
	 * @Date 2015年8月15日
	 */
	@Override
	public List<Map<Integer, Integer>> getOrderCount() {
		String querySQL = "SELECT cus,count(cusappoit_id) as count  FROM "
				+ "customer_appoit WHERE 1=1 AND del=0 GROUP BY" + " cus ORDER BY cus";
		List<Map<Integer, Integer>> maps = new ArrayList<Map<Integer, Integer>>();
		Query query = this.getSession().createSQLQuery(querySQL);
		List<Integer[]> list = query.list();
		for (int i = 0; i < list.size(); i++) {
			Object[] countObj = list.get(i);
			Map<Integer, Integer> map = new HashMap<Integer, Integer>();
			map.put(Integer.parseInt(countObj[0].toString()),
					Integer.parseInt(countObj[1].toString()));
			maps.add(map);
		}
		return maps;
	}

}
