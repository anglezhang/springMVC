package com.cyw.mammoth.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.cyw.common.base.dao.impl.BaseDaoImpl;
import com.cyw.mammoth.bean.RoomsDiary;
import com.cyw.mammoth.dao.RoomsDiaryDao;
@Repository
public class RoomsDiaryDaoImpl extends BaseDaoImpl<RoomsDiary, Integer> implements
		RoomsDiaryDao {

	@Override
	public void deleteRoomsDiary(Integer roomChkid,Integer checkId,String roomId) {
		// TODO Auto-generated method stub
		String sql = "delete rooms_diary where room_chkid = "+roomChkid;
		String updateSql = "update room_num set status=1 where status=0 and  check_id = "+checkId+" and room_id='"+roomId+"'";
		this.getSession().createSQLQuery(sql).executeUpdate();
		this.getSession().createSQLQuery(updateSql).executeUpdate();
	}

	@SuppressWarnings("unchecked")
	@Override
	public int getDiaryCount(String roomIds, String startDate, String endDate) {
		String[]  ids = null;
		if(roomIds != null){
			String sql = "SELECT count(*) as count FROM rooms_diary "
					+ " WHERE DATEDIFF(day,hotel_date,'"
					+ startDate + "')<=0 AND "
					+ " DATEDIFF(day,hotel_date,'"
					+ endDate + "')>=0 AND room_id IN(" 
					+ roomIds + ")";
			List<Integer> counts = this.getSession().createSQLQuery(sql).list();
			int count = counts.get(0);
			return count;
		}
		return 0;
	}

}
