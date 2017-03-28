package com.cyw.mammoth.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cyw.common.base.service.impl.BaseSvcImpl;
import com.cyw.mammoth.auth.AppBaseCfg;
import com.cyw.mammoth.bean.Rooms;
import com.cyw.mammoth.dao.BookCheckInDao;
import com.cyw.mammoth.dao.ParameterDao;
import com.cyw.mammoth.service.BookCheckInSvc;
import com.cyw.mammoth.vo.BookRoomCheckInVO;

@Service
public class BookCheckInSvcImpl extends BaseSvcImpl<Rooms, Integer> implements BookCheckInSvc {

	@Autowired
	private BookCheckInDao bookCheckInDao ;
	@Autowired
	private ParameterDao parameterDao ;
	
	@Autowired
	public void setBaseDao(BookCheckInDao dao) {
		super.setBaseDao(dao);
	}
	@Override
	public List<?> findRoomsBy(BookRoomCheckInVO bookRoomCheckInVO ) throws Exception {
		// true 含ip
		@SuppressWarnings("unused")
		boolean bool = parameterDao.GetIPFlag() ;
		//空净房（VCI  VC）
		//bookRoomCheckInVO.setStat(bool ? "'VCI','VDP','VCP'" : "'VC','VD'") ;
		bookRoomCheckInVO.setStat("'VCI','VDP','VCP','VC','VD'") ;
		return bookCheckInDao.findRoomsBy(bookRoomCheckInVO);
	}
	@Override
	public Integer confirmAllReach(BookRoomCheckInVO bookRoomCheckInVO) {
		return bookCheckInDao.confirmAllReach(bookRoomCheckInVO,AppBaseCfg.getOperator());
	}

}
