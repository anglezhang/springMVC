package com.cyw.mammoth.dao;

import java.util.List;

import com.cyw.common.base.dao.BaseDao;
import com.cyw.mammoth.bean.BookRoom;

public interface BookRoomDao extends BaseDao<BookRoom, Integer> {
	public Integer getNumByCheckId(Integer checkId);
}
