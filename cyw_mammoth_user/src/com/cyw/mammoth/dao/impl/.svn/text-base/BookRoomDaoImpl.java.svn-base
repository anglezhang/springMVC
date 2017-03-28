package com.cyw.mammoth.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.cyw.common.base.dao.impl.BaseDaoImpl;
import com.cyw.mammoth.bean.BookRoom;
import com.cyw.mammoth.dao.BookRoomDao;
@Repository
public class BookRoomDaoImpl extends BaseDaoImpl<BookRoom, Integer> implements BookRoomDao {

	@Override
	public Integer getNumByCheckId(Integer checkId) {
		String hql ="select sum(bean.bookNum) from BookRoom bean where bean.checkId="+checkId;
		Query query = getSession().createQuery(hql);
		Number num=(Number)query.uniqueResult();
		if(num==null){num=0;}
		return num.intValue();
	}

}
