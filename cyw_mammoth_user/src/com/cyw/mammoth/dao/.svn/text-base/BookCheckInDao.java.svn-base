package com.cyw.mammoth.dao;

import java.util.List;

import com.cyw.common.base.dao.BaseDao;
import com.cyw.mammoth.bean.Operator;
import com.cyw.mammoth.bean.Rooms;
import com.cyw.mammoth.vo.BookRoomCheckInVO;
/**
 * 散客或者团队预定入住数据操作层接口
 * @author Administrator
 *
 */
public interface BookCheckInDao extends BaseDao<Rooms, Integer> {

	/**
	 * 根据订单查询该订单的留房和当前可售房
	 * @param bookRoomCheckInVO 订单号
	 * @return
	 */
	List<?> findRoomsBy(BookRoomCheckInVO bookRoomCheckInVO);
	/**
	 * 确认全部抵达
	 * @param bookRoomCheckInVO   条件VO
	 * @param operator            当前用户
	 * @return
	 */
	Integer confirmAllReach(BookRoomCheckInVO bookRoomCheckInVO , Operator operator);

}
