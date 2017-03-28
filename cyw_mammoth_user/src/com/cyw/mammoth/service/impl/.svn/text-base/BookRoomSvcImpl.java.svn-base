package com.cyw.mammoth.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.cyw.common.base.service.impl.BaseSvcImpl;
import com.cyw.mammoth.bean.BookRoom;
import com.cyw.mammoth.bean.GrpDoc;
import com.cyw.mammoth.bean.Guestdoc;
import com.cyw.mammoth.bean.RoomNum;
import com.cyw.mammoth.dao.BookRoomDao;
import com.cyw.mammoth.dao.GrpDocDao;
import com.cyw.mammoth.dao.GuestdocDao;
import com.cyw.mammoth.dao.RoomNumDao;
import com.cyw.mammoth.service.BookRoomSvc;
@Service
public class BookRoomSvcImpl extends BaseSvcImpl<BookRoom, Integer> implements BookRoomSvc {
	@Autowired
	private BookRoomDao dao;
	@Autowired
	private RoomNumDao roomNumDao;
	@Autowired
	private GuestdocDao guestDao;
	@Autowired
	private GrpDocDao grpDocDao;
	@Autowired
	private org.springframework.jdbc.core.JdbcTemplate jdbcTemplate;
	
	@Autowired
	public void setBaseDao(BookRoomDao dao) {
		super.setBaseDao(dao);
	}

	@Override
	public Integer getNumByCheckId(Integer checkId) {
		return dao.getNumByCheckId(checkId);
	}

	@Override
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED,isolation=Isolation.DEFAULT,rollbackFor=Exception.class)
	public void deleteBookRoom(Integer bookId) {
		
		BookRoom bookRoom = dao.get(bookId);
		if(null!=bookRoom){
			List<RoomNum> roomNums= roomNumDao.getList("bookId", bookId);
			for(RoomNum entity:roomNums){
				roomNumDao.delete(entity);
			}
		}
	}

	@Override
	public void cacelBookRoom(Integer check_id, String cacelObject, String oper) {
		Date date = new Date();
		//先取消预定人
		if(cacelObject.equals("S")){
			Guestdoc guestDoc = guestDao.get(check_id);
			guestDoc.setBookStat("C");
			guestDoc.setLastOper(oper);
			guestDoc.setLastTime(date);
			guestDao.update(guestDoc);
		}else{
			GrpDoc grpDoc = grpDocDao.get(check_id);
			grpDoc.setBookStat("C");
			grpDoc.setLastOper(oper);
			grpDoc.setLastTime(date);
			grpDocDao.update(grpDoc);
		}
		//预定信息取消
		List<BookRoom> bookRoomList = dao.getList("checkId", check_id);
		if(bookRoomList != null && bookRoomList.size() > 0){
			for (BookRoom bookRoom : bookRoomList) {
				bookRoom.setStatus(1);
				bookRoom.setCancelNum(bookRoom.getSaveNum());
				bookRoom.setSaveNum(0);
				dao.update(bookRoom);
				
				//取消留房信息
				if(bookRoom.getCancelNum() > 0){
					List<RoomNum> roomNumList = roomNumDao.getList("bookId", bookRoom.getId());
					for (RoomNum roomNum : roomNumList) {
						String sql = "update room_num set status = 1 where id = " + roomNum.getId();
						jdbcTemplate.update(sql);
					}
				}
			}
		}
	}

}
