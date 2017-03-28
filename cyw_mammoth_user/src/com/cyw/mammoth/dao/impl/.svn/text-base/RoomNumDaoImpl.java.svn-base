package com.cyw.mammoth.dao.impl;

import java.lang.reflect.Method;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import com.cyw.common.base.bean.Finder;
import com.cyw.common.base.dao.impl.BaseDaoImpl;
import com.cyw.mammoth.bean.RoomNum;
import com.cyw.mammoth.dao.RoomNumDao;

@SuppressWarnings({ "unchecked", "rawtypes" }) 
@Repository
public class RoomNumDaoImpl extends BaseDaoImpl<RoomNum, Integer> implements
		RoomNumDao {

	@Override
	public void updateById(RoomNum bean) throws HibernateException {
		String errorMessage = "";
		String sql = "update room_num  set room_id=" + bean.getCheckId()
				+ ", leave_date='" + bean.getLeaveDate() + "' where id="
				+ bean.getId();
		SQLQuery query = getSession().createSQLQuery(sql);
		query.executeUpdate();
		/*
		 * try { query.executeUpdate(); } catch (HibernateException e) {
		 * errorMessage
		 * ="错误信息:"+e.getCause().getMessage()+"错误代码:"+((SQLException)
		 * e.getCause()).getErrorCode(); System.err.println(errorMessage); }
		 */
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<RoomNum> getNullRoomInf(String roomId, String startDate,
			String endDate) {
		Finder f = Finder.create("FROM RoomNum bean WHERE 1=1 ");
		if (StringUtils.isNotEmpty(roomId)) {
			f.append(" AND  bean.roomId=:roomId");
			f.setParam("roomId", roomId);
		}
		if (StringUtils.isNotEmpty(startDate)) {
			f.append(" AND datediff(day,bean.reachDate,:startDate)<=0 ");
			f.setParam("startDate", startDate);
		}
		if (StringUtils.isNotEmpty(endDate)) {
			f.append(" AND datediff(day,bean.leaveDate,:endDate)<=0 ");
			f.setParam("endDate", endDate);
		}
		return find(f);
	}

	@Override
	public int updateBookRoomByChekcIdAndRoomType(Integer checkId,
			String roomTypeId, String roomIds, String currBookRoomId,
			String reachDate, String leaveDate) {

		if (StringUtils.isNotEmpty(roomIds)) {
			StringBuffer sqlBuffer = new StringBuffer();
			sqlBuffer
					.append("update room_num set status='1' where check_Id = '"
							+ checkId + "' and book_id ='" + currBookRoomId
							+ "' and room_id in ");
			sqlBuffer.append("( ");
			sqlBuffer.append(" select room_id from rooms where room_type='"
					+ roomTypeId + "' ");
			sqlBuffer.append(")");
			// sqlBuffer.append(" and room_id in("+roomIds+")");
			StringBuffer sqlBuffer2 = new StringBuffer();
			sqlBuffer2.append("delete rooms_diary where room_id in (" + roomIds
					+ ") and  datediff(day,hotel_date,'" + reachDate
					+ "')<=0 and datediff(day,hotel_date,'" + leaveDate
					+ "') >=0 ");
			this.getSession().createSQLQuery(sqlBuffer2.toString())
					.executeUpdate();
			return this.getSession().createSQLQuery(sqlBuffer.toString())
					.executeUpdate();
		}
		return -1;

	}

	public void beginTranBegin() {
		this.getSession().beginTransaction().begin();
	}

	public void beginTranCommit() {
		this.getSession().beginTransaction().commit();
	}

	@Override
	public List<RoomNum> getFixFrozenInf(String roomId, String type,
			String active, String startDate, String endDate) {
		List<RoomNum> other = new ArrayList<RoomNum>();
		//新查询语句
		StringBuffer querySQL = new StringBuffer();
		querySQL.append("SELECT id,room_chkid as roomChkid ,check_id as checkId,"
				+ "room_id as roomId,reach_date as reachDate,leave_date as leaveDate,flag,status,keep_flag as keepFlag"
				+ ",oper_id as operId,oper_time as operTime,note,book_id as bookId,room_price as roomPrice"
				+ ",vilhotel_id as vilhotelId,chain_id as chainId FROM room_num WHERE 1=1 ");
		//判断roomId 是否为空
		if(StringUtils.isNotEmpty(roomId)){
			querySQL.append(" AND room_id <> '" + roomId + "' ");
			querySQL = getFixFrozenFinder(querySQL, type, active, startDate, endDate);
			other = this.getSession()
					.createSQLQuery(querySQL.toString())
					.setResultTransformer(Transformers.aliasToBean(RoomNum.class))
					.list();
		}
		
		StringBuffer thisSql = new StringBuffer();
		thisSql.append("SELECT id,room_chkid as roomChkid ,check_id as checkId,"
				+ "room_id as roomId,reach_date as reachDate,leave_date as leaveDate,flag,status,keep_flag as keepFlag"
				+ ",oper_id as operId,oper_time as operTime,note,book_id as bookId,room_price as roomPrice"
				+ ",vilhotel_id as vilhotelId,chain_id as chainId FROM room_num WHERE 1=1 ");
		if(StringUtils.isNotEmpty(roomId)){
			thisSql.append(" AND room_id='" + roomId + "' ");
		}
		thisSql = getFixFrozenFinder(thisSql, type, active, startDate, endDate);
		List<RoomNum> thisArray = this.getSession()
				.createSQLQuery(thisSql.toString())
				.setResultTransformer(Transformers.aliasToBean(RoomNum.class))
				.list();
		thisArray.addAll(other);
		return thisArray;
	}

	/**
	 * 描述 获取finder
	 * */
	private StringBuffer getFixFrozenFinder(StringBuffer querySQL, String type,
			String active, String startDate, String endDate) {
		// 判断类型
		if (StringUtils.isNotEmpty(type)) {
			querySQL.append(" AND flag=" + type);
		}
		// 有效无效
		if (StringUtils.isNotEmpty(active)) {
			querySQL.append(" AND status=" + active);
		}
		// 开始日期 结束日期
		if("0".equals(active)){
			querySQL.append(" AND id in( SELECT distinct room_chkid from rooms_diary WHERE "
					+ "DATEDIFF(day,hotel_date,'"
					+ startDate
					+ "')<=0 and DATEDIFF(day,hotel_date,'"
					+ endDate
					+ "')>=0 "
					+ ") ");
		}
		

		querySQL.append(" ORDER BY reach_date ASC");
		return querySQL;
	}

	@Override
	public List getRoomNumByRoomAndDate(String room_id, Date startDate, Date endDate) {
		StringBuilder builder = new StringBuilder();
		builder.append("select a.id, a.room_id, convert(varchar(21), a.reach_date, 23) reach_date,  convert(varchar(21), a.leave_date, 23) leave_date, flag ");
		builder.append("from room_num a ");
		builder.append("where a.room_id = ? and a.flag <> 1 and a.status = '0' ");
		builder.append("and ((datediff(day, ?, a.leave_date) >= 0 and datediff(day, ?, a.leave_date) <= 0) or (datediff(day, ?, a.reach_date) >= 0 and datediff(day, ?, a.reach_date) <= 0) or (datediff(day, ?, a.reach_date) <= 0 and datediff(day, ?, a.leave_date) >= 0) or (datediff(day, ?, a.reach_date) >= 0 and datediff(day, ?, a.leave_date) <= 0))");
		Query query = this.createTransformSqlQuery(builder.toString());
		query.setParameter(0, room_id);
		query.setParameter(1, startDate);
		query.setParameter(2, endDate);
		query.setParameter(3, startDate);
		query.setParameter(4, endDate);
		query.setParameter(5, startDate);
		query.setParameter(6, endDate);
		query.setParameter(7, startDate);
		query.setParameter(8, endDate);
		return query.list();
	}

	@Override
	public List<RoomNum> getRoomNumByRoomId(String room_id) {
		if(StringUtils.isEmpty(room_id)){
			return null;
		}
		Finder f = Finder.create("FROM RoomNum bean WHERE 1=1 ");
		f.append("AND roomId = '"+room_id+"' ");
		f.append("AND status = 0 AND flag = 1 ");
		return this.find(f);
	}

	@Override
	public List getRoomNumByDate(String startDate, String endDate) {
		StringBuilder builder = new StringBuilder();
		builder.append("select convert(varchar(21), x.date_day, 23) date_day, datename(weekday, x.date_day) date_weak, ");
		builder.append("x.code_id, x.code_namee, x.code_namec, ");
		builder.append("x.num - isnull(y.use_num, 0) - isnull(z.book_num, 0) nouse_num, ");
		builder.append("(isnull(y.use_num, 0) + isnull(z.book_num, 0)) use_num ");
		builder.append("from (select a.date_day, b.code_id, b.code_namee, b.code_namec, b.num ");
		builder.append("	from (select dateadd(dd, number, '" + startDate + "') date_day from master..spt_values where type='p' and number <= datediff(dd,'" + startDate + "','" + endDate + "')) a ");
		builder.append("	join hroom_type b on b.status = 0) x ");
		builder.append("left join (select d.room_type, c.hotel_date, count(c.room_id) use_num from rooms_diary c left join rooms d on d.room_id = c.room_id where datediff(dd, c.hotel_date, '" + endDate + "') >= 0 and datediff(dd, '" + startDate + "', c.hotel_date) >= 0 and c.flag in (1,2,3,4) group by d.room_type, c.hotel_date) y on datediff(day, x.date_day, y.hotel_date) = 0 and x.code_id = y.room_type ");
		builder.append("left join (select e.roomtype_id, e.hotel_date, ");
		builder.append("	sum(case when e.reach_num = 0 then e.book_num when e.reach_num > 0 and e.book_num > e.reach_num + e.save_num then e.book_num - e.reach_num else e.save_num end) book_num ");
		builder.append("	from book_room_diary e ");
		builder.append("	where datediff(dd, e.hotel_date, '" + endDate + "') >= 0 and datediff(dd, '" + startDate + "', e.hotel_date) >= 0 ");
		builder.append("	group by e.roomtype_id, e.hotel_date) z on datediff(day, x.date_day, z.hotel_date) = 0 and x.code_id = z.roomtype_id");
		Query query = this.createTransformSqlQuery(builder.toString());
		return query.list();
	}
}
