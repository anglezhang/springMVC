package com.cyw.mammoth.service;

import java.util.List;

import com.cyw.common.base.service.BaseSvc;
import com.cyw.mammoth.bean.Rooms;
import com.cyw.mammoth.vo.BookRoomCheckInVO;

/**
 * 房态--预定入住--业务层
 * @author Administrator
 *
 */
public interface BookCheckInSvc extends BaseSvc<Rooms, Integer>{
	/**
	 * 根据订单查询该订单的留房和当前可售房
	 * @param bookRoomCheckInVO 
	 * @return
	 * @throws Exception
	 */
	List<?> findRoomsBy(BookRoomCheckInVO bookRoomCheckInVO) throws Exception;
	/**
	 * 确认全部抵达
	 * @param bookRoomCheckInVO
	 * @return
	 */
	Integer confirmAllReach(BookRoomCheckInVO bookRoomCheckInVO);

}
