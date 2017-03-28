package com.cyw.mammoth.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cyw.common.base.service.impl.BaseSvcImpl;
import com.cyw.mammoth.bean.BookRoomCheckin;
import com.cyw.mammoth.dao.BookCheckInDao;
import com.cyw.mammoth.dao.BookRoomCheckInDao;
import com.cyw.mammoth.dao.ParameterDao;
import com.cyw.mammoth.service.BookRoomCheckInSvc;
import com.cyw.mammoth.vo.BookRoomCheckInVO;

@Service
public class BookRoomCheckInSvcImpl extends BaseSvcImpl<BookRoomCheckin, Integer> implements BookRoomCheckInSvc {

	@Autowired
	private BookRoomCheckInDao bookRoomCheckInDao;
	
	@Autowired
	public void setBaseDao(BookRoomCheckInDao dao) {
		super.setBaseDao(bookRoomCheckInDao);
	}

}
