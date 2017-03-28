package com.zoomoor.jy.dao.impl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.orm.hibernate3.SessionFactoryUtils;
import org.springframework.stereotype.Repository;

import com.zoomoor.common.base.bean.Finder;
import com.zoomoor.common.base.bean.Pager;
import com.zoomoor.common.base.dao.impl.BaseDaoImpl;
import com.zoomoor.common.util.ArrayUtils;
import com.zoomoor.jy.dao.CustomerEntrustDao;
import com.zoomoor.jy.entity.CustomerEntrust;

/**
 * 描述：CustomerEntrustDaoImpl.java
 * 
 * @author Zhangzhenxing
 * @Date 2015年7月31日 下午2:03:37
 * @version 1.0
 */
@Repository
public class CustomerEntrustDaoImpl extends
		BaseDaoImpl<CustomerEntrust, Integer> implements CustomerEntrustDao {

	private static final Logger log = LoggerFactory
			.getLogger(CustomerEntrustDao.class);

	/**
	 * 描述：该方法作用
	 * 
	 * @author zhangZhenxing
	 * @Date 2015年8月3日
	 */
	@Override
	public void saveCustomerEntrusSave(String mainTale, String rowsList) {
		Connection conn = null;
		try {
			conn = (Connection) SessionFactoryUtils.getDataSource(
					sessionFactory).getConnection();
			if (StringUtils.isNotEmpty(mainTale)
					&& StringUtils.isNotEmpty(rowsList)) {
				CallableStatement call = (CallableStatement) conn
						.prepareCall("{CALL u_Customer_entrust_Save(?,?)}");
				call.setString(1, mainTale);
				call.setString(2, rowsList);
				// ResultSet rs = call.executeQuery();
				call.execute();
				ResultSet rs = call.getResultSet();
				while (rs.next()) {
					String msg = rs.getString("message");
					String bNo = rs.getString("b_no");
					log.info("saveCustomerEntrusSave msg=" + msg + ",bNO="
							+ bNo + " mainTale=" + mainTale + ",rowslist="
							+ rowsList);
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			log.error("saveCustomerEntrusSave SQLException");
			e.printStackTrace();
		} finally {
			closeConnection(conn);
		}

	}

	/**
	 * 描述：该方法作用
	 * 
	 * @author zhangZhenxing
	 * @Date 2015年8月4日
	 */
	@Override
	public Pager getPager(Pager pager, String queryOrderNO,
			String queryPlatenum, String queryCusName, String queryStatus,
			String queryFixplace, String queryAppType,
			String queryOrderBeginTime, String queryOrderEndTime,
			String queryOperator, String queryFixMan, Integer deptId) {
		Finder finder = createFinder(queryOrderNO, queryPlatenum, queryCusName,
				queryStatus, queryFixplace, queryAppType, queryOrderBeginTime,
				queryOrderEndTime, queryOperator, queryFixMan);

		if (deptId != null) {
			finder.append(" AND bean.infoDept.deptId =:deptId");
			finder.setParam("deptId", deptId);
		}
		finder.append(" order by bean.createtime desc");
		return find(finder, pager.getPageNum(), pager.getNumPerPage());
	}

	/**
	 * 描述：删除子委托单
	 * 
	 * @author zhangZhenxing
	 * @Date 2015年8月5日
	 */
	@Override
	public void deleteSunCusEntrust(Integer cusEntrustId) {
		String sql = "DELETE from customer_entrust_sub " + "WHERE entrust_id="
				+ cusEntrustId;
		Connection conn = null;
		try {
			conn = (Connection) SessionFactoryUtils.getDataSource(
					sessionFactory).getConnection();
			if (cusEntrustId != null) {
				PreparedStatement pstmt = conn.prepareStatement(sql);
				pstmt.execute();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeConnection(conn);
		}
	}

	/**
	 * 描述：该方法作用
	 * 
	 * @author zhangZhenxing
	 * @Date 2015年8月5日
	 */
	@Override
	public boolean amount(Integer entrustId, Integer accountmanID,
			Double workHourAmout, Double goodsAmout, String jsdh) {
		/*
		 * 描述:结算 1.本委托单出库的配件 折扣后费用 2.本委托单的工时费 修改本委托单状态：customer_entrust
		 * 记录仓库单据depot_bill id 记录委托单状态：custome_orderstatus
		 */
		String querySQL = "SELECT e.entrust_id, b.bill_id,bl.detail_id, g.cost_price,"
				+ "g.price,bl.num, g.discount "
				+ " FROM customer_entrust e "
				+ " INNER JOIN depot_bill_list bl ON bl.del=0 AND e.entrust_id=bl.tag_id "
				+ " INNER JOIN depot_bill b ON b.del=0 AND  "
				+ "  b.summary_id=5 AND e.dept_id=b.dep_id AND bl.bill_id=b.bill_id "
				+ " INNER JOIN info_goods g ON g.del=0 AND bl.goods_id=g.goods_id "
				+ " WHERE e.del=0 AND e.entrust_id=?";
		boolean isOk = false;// 提交 true 成功 false 失败
		Connection conn = null;
		try {

			conn = (Connection) SessionFactoryUtils.getDataSource(
					sessionFactory).getConnection();
			PreparedStatement pstmt = conn.prepareStatement(querySQL);
			pstmt.setInt(1, entrustId);
			ResultSet queryRs = pstmt.executeQuery();
			// 合计金额
			Double allAmout = goodsAmout + workHourAmout;
			String updateSQL = "UPDATE customer_entrust SET accountman="
					+ accountmanID + "," + "amount=" + allAmout
					+ ",settlement='" + jsdh
					+ "',order_status=1,amounttime=now() WHERE "
					+ "entrust_id=" + entrustId + ";";
			conn.setAutoCommit(false);// 设置自动提交
			PreparedStatement excutepstmt = conn.prepareStatement(updateSQL);
			excutepstmt.execute(updateSQL);
			// 创建委托单物料记录
			while (queryRs.next()) {
				StringBuffer excuteSQL = new StringBuffer();
				excuteSQL
						.append(" INSERT INTO customer_entrust_goods "
								+ "	(entrust_id,bill_id,detail_id,cost_price,price,quantum,discount,creattime) "
								+ "VALUES(" + queryRs.getInt("entrust_id")
								+ "," + queryRs.getInt("bill_id") + ","
								+ queryRs.getInt("detail_id") + ","
								+ queryRs.getDouble("cost_price") + ","
								+ queryRs.getDouble("price") + ","
								+ queryRs.getDouble("num") + ","
								+ queryRs.getInt("discount") + ",now()); ");
				excutepstmt.execute(excuteSQL.toString());
			}
			conn.commit();
			isOk = true;

		} catch (SQLException e) {
			log.error("saveCustomerEntrusSave SQLException");
			e.printStackTrace();
			isOk = false;
			conn.rollback();
		} finally {
			closeConnection(conn);
			return isOk;
		}

	}

	/**
	 * 描述:关闭连接
	 * */
	private void closeConnection(Connection conn) {
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				log.error("connnection close error");
				e.printStackTrace();
			}
		}
	}

	/**
	 * 描述：该方法作用
	 * 
	 * @author zhangZhenxing
	 * @Date 2015年8月5日
	 */
	@Override
	public Integer serviceCount(Integer entrustId) {
		Connection conn = null;
		Integer amout = 0;
		try {

			conn = (Connection) SessionFactoryUtils.getDataSource(
					sessionFactory).getConnection();
			String countSQL = "SELECT " + "count(s.item_id) as count "
					+ "FROM " + "	customer_entrust_sub sub "
					+ "INNER JOIN serviceitem s ON sub.item_id = s.item_id "
					+ "WHERE sub.entrust_id=?";
			PreparedStatement pstmt = conn.prepareStatement(countSQL);
			pstmt.setInt(1, entrustId);
			// 查询结果集
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				amout += rs.getInt("count");
			}

		} catch (SQLException e) {
			log.error("serviceCount error");
			e.printStackTrace();
		} finally {
			closeConnection(conn);
			return amout;
		}

	}

	@Override
	public Double workHourAmout(Integer entrustId) {
		Connection conn = null;
		Double amout = 0.0d;
		try {

			String queryAmout = "SELECT "
					+ "	SUM(s.workhourmoney * s.discount) AS amount "
					+ "		FROM " + " customer_entrust_sub sub "
					+ " INNER JOIN serviceitem s ON sub.item_id = s.item_id"
					+ " WHERE sub.entrust_id=?";
			conn = (Connection) SessionFactoryUtils.getDataSource(
					sessionFactory).getConnection();
			log.info("querySQL = " + queryAmout);
			PreparedStatement pstmt = conn.prepareStatement(queryAmout);
			pstmt.setInt(1, entrustId);
			ResultSet amoutRs = pstmt.executeQuery();
			while (amoutRs.next()) {
				amout += amoutRs.getDouble("amount");
			}
			return amout;
		} catch (SQLException e) {
			log.error("serviceAmount error");
			e.printStackTrace();
		} finally {
			closeConnection(conn);
			return amout;
		}
	}

	/**
	 * 描述：该方法作用
	 * 
	 * @author zhangZhenxing
	 * @Date 2015年8月10日
	 */
	@Override
	public Pager getFourshopPager(Pager pager, String queryOrderNO,
			String queryPlatenum, String queryCusName,
			String queryOrderBeginTime, String queryOrderEndTime,
			String queryOperator, String queryStatus, Integer deptId) {
		Finder finder = createFinder(queryOrderNO, queryPlatenum, queryCusName,
				queryStatus, null, null, queryOrderBeginTime,
				queryOrderEndTime, queryOperator, null);
		if (deptId != null) {
			finder.append(" AND  bean.infoDept.deptId =:deptId ");
			finder.setParam("deptId", deptId);
		}

		finder.append(" order by bean.createtime desc");
		return find(finder, pager.getPageNum(), pager.getNumPerPage());
	}

	private Finder createFinder(String queryOrderNO, String queryPlatenum,
			String queryCusName, String queryStatus, String queryFixplace,
			String queryAppType, String queryOrderBeginTime,
			String queryOrderEndTime, String queryOperator, String queryFixMan) {
		Finder finder = Finder
				.create("FROM CustomerEntrust bean WHERE 1=1 and bean.del=0 ");
		// 车牌号
		if (StringUtils.isNotEmpty(queryPlatenum)) {
			finder.append(" AND bean.carCusMap.infoCar.platenum like :queryPlatenum ");
			finder.setParam("queryPlatenum", "%" + queryPlatenum + "%");
		}
		// 委托单号
		if (StringUtils.isNotEmpty(queryOrderNO)) {
			finder.append(" AND bean.cusEnstrustNum like :queryOrderNO ");
			finder.setParam("queryOrderNO", "%" + queryOrderNO + "%");
		}
		// 客户姓名
		if (StringUtils.isNotEmpty(queryCusName)) {
			finder.append(" AND bean.carCusMap.infoCustome.customerName like :queryCusName ");
			finder.setParam("queryCusName", "%" + queryCusName + "%");
		}
		// 订单状态
		if (StringUtils.isNotEmpty(queryStatus) && !"-1".equals(queryStatus)) {
			finder.append(" AND bean.cusEntats  =:queryStatus ");
			finder.setParam("queryStatus", Integer.parseInt(queryStatus));
		}
		// 维修地点 fixplace
		if (StringUtils.isNotEmpty(queryFixplace)) {
			finder.append(" AND bean.fixplace  =:queryFixplace ");
			finder.setParam("queryFixplace", Integer.parseInt(queryFixplace));
		}
		// 委托服务类型
		if (StringUtils.isNotEmpty(queryAppType)) {
			// String sql = "SELECT  DISTINCT subtab.entrust_id FROM"
			// + " customer_entrust_sub  subtab LEFT JOIN serviceitem "
			// + "s ON s.item_id=subtab.item_id where s.item_id="
			// + Integer.parseInt(queryAppType);
			String sql = "SELECT sub.customerEntrust.entrustId FROM CustomerEntrustSub sub  LEFT JOIN "
					+ " sub.serviceitem WHERE sub.serviceitem.itemId="
					+ Integer.parseInt(queryAppType);
			finder.append(" AND bean.entrustId in (" + sql + ") ");
			// Query query = this.getSession().createSQLQuery(sql);
			// List<Integer> entrustIds = query.list();
			// if (entrustIds.size() > 0) {
			// StringBuffer sb = new StringBuffer("and bean.entrustId in (");
			// for (Integer entrustID : entrustIds) {
			// sb.append(entrustID + ",");
			// }
			// sb.append("-1 )");
			// finder.append(sb.toString());
			// }

		}
		if (StringUtils.isNotEmpty(queryOrderBeginTime)
				&& StringUtils.isNotEmpty(queryOrderEndTime)) {
			finder.append(" and (date_format(bean.createtime,'%Y-%m-%d %H:%i:%S') "
					+ "between :queryBegintime and :queryEndtime )");
			finder.setParam("queryBegintime", queryOrderBeginTime);
			finder.setParam("queryEndtime", queryOrderEndTime);
		} else {
			// 查询开始时间
			if (StringUtils.isNotEmpty(queryOrderBeginTime)) {
				finder.append(" and date_format(bean.createtime,"
						+ "'%Y-%m-%d %H:%i:%S')>=:queryBegintime");
				finder.setParam("queryBegintime", queryOrderBeginTime);
			}
			// 查询结束时间
			if (StringUtils.isNotEmpty(queryOrderEndTime)) {
				finder.append(" and date_format(bean.createtime,"
						+ "'%Y-%m-%d %H:%i:%S')<=:queryEndtime");
				finder.setParam("queryEndtime", queryOrderEndTime);
			}
		}
		// 接待人
		if (StringUtils.isNotEmpty(queryOperator)) {
			String sql = "SELECT emp_id from info_emp where emp_name like '%"
					+ queryOperator + "%'";
			Query query = this.getSession().createSQLQuery(sql);
			List<Integer> userIds = query.list();
			if (userIds.size() > 0) {
				StringBuffer sb = new StringBuffer(
						" and bean.receptionist in (");
				for (Integer repId : userIds) {
					sb.append(repId + ",");
				}
				sb.append(" -1 ) ");
				finder.append(sb.toString());
			}

		}
		// 维修者
		if (StringUtils.isNotEmpty(queryFixMan)) {
			String sql = "SELECT emp_id from info_emp where emp_name like '%"
					+ queryFixMan + "%'";
			Query query = this.getSession().createSQLQuery(sql);
			List<Integer> userIds = query.list();
			if (userIds.size() > 0) {
				StringBuffer sb = new StringBuffer(" and bean.fixer in (");
				for (Integer repId : userIds) {
					sb.append(repId + ",");
				}
				sb.append(" -1 ) ");
				finder.append(sb.toString());
			}
		}
		return finder;
	}

	/**
	 * 描述：该方法作用
	 * 
	 * @author zhangZhenxing
	 * @Date 2015年8月13日
	 */
	@Override
	public Pager getSettlementPager(Pager pager, String queryCarInf,
			String queryPlatenum, Integer queryStatu, Integer queryFixplace,
			Integer serviceType, String queryBeginTime, String queryEndTime,
			Integer deptId) {
		Finder finder = Finder
				.create("FROM CustomerEntrust bean WHERE 1=1 and bean.del=0 and bean.cusEntats!=0 ");
		if (StringUtils.isNotEmpty(queryCarInf)) {
			finder.append(" AND ( bean.carCusMap.infoCustome.customerName like '%"
					+ queryCarInf
					+ "%' or bean.carCusMap.infoCustome.tel like '%"
					+ queryCarInf + "%' ) ");
		}
		// 车牌号
		if (StringUtils.isNotEmpty(queryPlatenum)) {
			finder.append(" AND bean.carCusMap.infoCar.platenum like :queryPlatenum");
			finder.setParam("queryPlatenum", "%" + queryPlatenum + "%");
		}
		// 状态
		if (queryStatu != null) {
			finder.append(" AND bean.cusEntats = :queryStatu");
			finder.setParam("queryStatu", queryStatu);
		}
		// 维修地点
		if (queryFixplace != null) {
			finder.append(" AND bean.fixplace = :queryFixplace");
			finder.setParam("queryFixplace", queryFixplace);
		}
		// 服务类型
		if (serviceType != null) {
			// String sql = "SELECT  DISTINCT subtab.entrust_id FROM"
			// + " customer_entrust_sub  subtab LEFT JOIN serviceitem "
			// + "s ON s.item_id=subtab.item_id where s.item_id="
			// + serviceType;
			// Query query = this.getSession().createSQLQuery(sql);
			// List<Integer> entrustIds = query.list();
			// if (entrustIds.size() > 0) {
			// StringBuffer sb = new StringBuffer(" and bean.entrustId in (");
			// for (Integer entrustID : entrustIds) {
			// sb.append(entrustID + ",");
			// }
			// sb.append("-1 )");
			// finder.append(sb.toString());
			// }
			// finder.append(" and bean.entrustId in (" + sql.toString() +
			// ") ");
			String sql = "SELECT sub.customerEntrust.entrustId FROM CustomerEntrustSub sub  LEFT JOIN "
					+ " sub.serviceitem WHERE sub.serviceitem.itemId="
					+ serviceType;
			finder.append(" AND bean.entrustId in (" + sql + ") ");
		}
		if (StringUtils.isNotEmpty(queryBeginTime)
				&& StringUtils.isNotEmpty(queryEndTime)) {
			finder.append(" and (date_format(bean.createtime,'%Y-%m-%d %H:%i:%S') "
					+ "between :queryBegintime and :queryEndtime )");
			finder.setParam("queryBegintime", queryBeginTime);
			finder.setParam("queryEndtime", queryEndTime);
		} else {
			// 查询开始时间
			if (StringUtils.isNotEmpty(queryBeginTime)) {
				finder.append(" and date_format(bean.createtime,"
						+ "'%Y-%m-%d %H:%i:%S')>=:queryBegintime");
				finder.setParam("queryBegintime", queryBeginTime);
			}
			// 查询结束时间
			if (StringUtils.isNotEmpty(queryEndTime)) {
				finder.append(" and date_format(bean.createtime,"
						+ "'%Y-%m-%d %H:%i:%S')<=:queryEndtime");
				finder.setParam("queryEndtime", queryEndTime);
			}
		}
		if (deptId != null) {
			finder.append(" AND bean.infoDept.deptId =:deptId");
			finder.setParam("deptId", deptId);
		}
		finder.append(" order by bean.createtime desc");
		return find(finder, pager.getPageNum(), pager.getNumPerPage());
	}

	/**
	 * 描述：该方法作用
	 * 
	 * @author zhangZhenxing
	 * @Date 2015年8月13日
	 */
	@Override
	public List<Map<Integer, Integer>> getCustomCount(Integer deptId,
			Integer fixplace) {
		Connection conn = null;
		List<Map<Integer, Integer>> result = null;
		try {
			conn = (Connection) SessionFactoryUtils.getDataSource(
					sessionFactory).getConnection();
			StringBuffer sql = new StringBuffer(
					"SELECT count(entrust_id) as count,order_status");
			sql.append(" from customer_entrust where dept_id=" + deptId);
			// + " and del=0 and fixplace="
			// + fixplace +" group  by order_status ";
			if (fixplace != -1) {
				sql.append(" and del=0 and fixplace=" + fixplace + "  ");
			} else {
				sql.append(" and del=0  ");
			}
			sql.append(" group  by order_status ");
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			ResultSet countRS = pstmt.executeQuery();
			result = new ArrayList<Map<Integer, Integer>>();
			while (countRS.next()) {
				Map<Integer, Integer> map = new HashMap<Integer, Integer>();
				map.put(countRS.getInt("order_status"), countRS.getInt("count"));
				result.add(map);
			}
		} catch (SQLException e) {
			log.error("统计状态数量失败");
			e.printStackTrace();
		} finally {
			closeConnection(conn);
			return result;
		}

	}

	/**
	 * 描述：该方法作用
	 * 
	 * @author zhangZhenxing
	 * @Date 2015年8月17日
	 */
	@Override
	public List getCustomerList(Integer deptId, String queryOrderNO,
			String queryPlatenum, String queryCusName, Integer queryStatus,
			Integer queryFixplace, String queryOrderBeginTime,
			String queryOrderEndTime, String queryOperator, String queryFixMan) {
		Connection conn = null;
		List list = null;
		try {
			conn = (Connection) SessionFactoryUtils.getDataSource(
					sessionFactory).getConnection();
			CallableStatement call = (CallableStatement) conn
					.prepareCall("{CALL u_Customer_entrust_GetList(?,?,?,?,?,?,?,?,?,?)}");
			call.setInt(1, deptId);
			// 委托单号
			if (StringUtils.isEmpty(queryOrderNO)) {
				queryOrderNO = "";
			}
			call.setString(2, queryOrderNO);
			// 车牌号
			if (StringUtils.isEmpty(queryPlatenum)) {
				queryPlatenum = "";
			}
			call.setString(3, queryPlatenum);
			// 客户姓名
			if (StringUtils.isEmpty(queryCusName)) {
				queryCusName = "";
			}
			call.setString(4, queryCusName);
			// 状态
			if (queryStatus == null) {
				queryStatus = -1;
			}
			call.setInt(5, queryStatus);
			// 服务地址
			if (queryFixplace == null) {
				queryFixplace = 0;// 0为本店
			}
			call.setInt(6, queryFixplace);
			// 开始时间
			if (StringUtils.isEmpty(queryOrderBeginTime)) {
				queryOrderBeginTime = "";
			}
			call.setString(7, queryOrderBeginTime);
			// 结束时间
			if (StringUtils.isEmpty(queryOrderEndTime)) {
				queryOrderEndTime = "";
			}
			call.setString(8, queryOrderEndTime);
			// 维修人员和接待人员不是模糊查询
			call.setInt(9, 0);
			call.setInt(10, 0);
			ResultSet rs = call.executeQuery();
			list = ArrayUtils.resultSetToList(rs);

		} catch (SQLException e) {
			log.error("getCustomerList 失败");
		} finally {
			closeConnection(conn);
			return list;
		}

	}

	/**
	 * 描述：该方法作用
	 * 
	 * @author zhangZhenxing
	 * @Date 2015年8月21日
	 */
	@Override
	public String getLastComeTime(Integer cusappoitId) {
		String lastTime = null;
		String sql = "SELECT cu.createtime FROM customer_entrust cu LEFT JOIN "
				+ " car_custmer_mapping map ON map.vehicleContac_id=cu.vehicleContac_id "
				+ " WHERE 1=1 AND map.vehicleContac_id IN (SELECT vehicleContac_id "
				+ " FROM customer_entrust WHERE cusappoit_id=" + cusappoitId + ") "
				+ " ORDER BY cu.createtime ASC limit 1 ";
		Query query = this.getSession().createSQLQuery(sql);
		if(query.list()!=null && query.list().size() > 0){
			Object obj = query.list().get(0);
			lastTime = obj.toString();
		}
		return lastTime;
	}

}
