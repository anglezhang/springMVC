package com.cyw.mammoth.dao.impl;

import org.springframework.stereotype.Repository;

import com.cyw.common.base.dao.impl.BaseDaoImpl;
import com.cyw.mammoth.bean.BookRoomCheckin;
import com.cyw.mammoth.dao.BookRoomCheckInDao;

@Repository
public class BookRoomCheckInDaoImpl extends BaseDaoImpl<BookRoomCheckin, Integer>
		implements BookRoomCheckInDao {

}

