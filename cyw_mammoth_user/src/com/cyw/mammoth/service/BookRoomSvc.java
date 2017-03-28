package com.cyw.mammoth.service;


import com.cyw.common.base.service.BaseSvc;
import com.cyw.mammoth.bean.BookRoom;

public interface BookRoomSvc extends BaseSvc<BookRoom, Integer> {
	public Integer getNumByCheckId(Integer checkId);
	public void deleteBookRoom(Integer bookId);
	
	/**
	 * 根据预定人登记号取消预定
	 * @param check_id 预定人登记号
	 * @param cacelObject 取消对象（T团预定，S散预定）
	 * @param oper 操作人
	 */
	public void cacelBookRoom(Integer check_id, String cacelObject, String oper);
}
