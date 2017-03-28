package com.cyw.mammoth.dao;

import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONArray;
import com.cyw.common.base.dao.BaseDao;
import com.cyw.mammoth.bean.Bills;
import com.cyw.mammoth.bean.Guestdoc;
import com.cyw.mammoth.vo.BookRoomCheckInVO;
import com.cyw.mammoth.vo.BookRoomSearchVo;
import com.cyw.mammoth.vo.GroupSearchVO;
import com.cyw.mammoth.vo.GuestDetailVo;
import com.cyw.mammoth.vo.GuestSearchVO;
import com.cyw.mammoth.vo.NoguestSearchVO;
import com.cyw.mammoth.vo.WebRoomSearchVO;

/**
 * 客人信息处理DAO
 * <功能详细描述>
 * 
 * @author  litiangang@cyw.so
 * @version  v-1.0
 * @see  GuestdocDao.java
 * @since  cyw-1.0
 */
public interface GuestdocDao extends BaseDao<Guestdoc, Integer> {
	
	public List<Guestdoc> getBookRoomList(BookRoomSearchVo searchVo);
	
	public List<Guestdoc> getBookAccommodate(BookRoomSearchVo searchVo);
	//根据房号查询住客的信息
	public Guestdoc getGuestdoc(String roodId,String chkState,String chkExt);
	
	/**
	 * 更改房间状态
	 * <功能详细描述>
	 * @param roomid
	 * @see GuestdocDao.java
	 */
	public void updateRoomState(String roomid,String state);
	
	/**
	 * 获取住客资料列表
	 * <功能详细描述>
	 * @param searchVO
	 * @return
	 * @see GuestdocDao.java
	 */
	public List getGuestDocList(GuestSearchVO searchVO);
	
	/**
	 * 获取团队资料列表
	 * <功能详细描述>
	 * @return
	 * @see GuestdocDao.java
	 */
	public List getGroupList(GroupSearchVO searchVO);
	
	/**
	 * 根据房号获取同团队其他人员信息
	 * <功能详细描述>
	 * @param roomId
	 * @return
	 * @see GuestdocDao.java
	 */
	public List getGroupMembersByRoomId(String roomId);
	
	/**
	 * 获取所有网房
	 * <功能详细描述>
	 * @return
	 * @see GuestdocDao.java
	 */
	public List getWebRooms(WebRoomSearchVO searchVO);
	
	/**
	 * 网房条件查询
	 * <功能详细描述>
	 * @param searchVO
	 * @return
	 * @see GuestdocDao.java
	 */
	public List searchWebRooms(WebRoomSearchVO searchVO);
	
	/**
	 * 设为网房
	 * <功能详细描述>
	 * @param roomId
	 * @see GuestdocDao.java
	 */
	public int setOrCancleWebRoom(String roomId,String flag);
	
	/**
	 * 获取非住客列表
	 * <功能详细描述>
	 * @param searchVO
	 * @return
	 * @see GuestdocDao.java
	 */
	public List getNoguestList(NoguestSearchVO searchVO);
	
	/**
	 * 客户详情
	 * <功能详细描述>
	 * @param checkId
	 * @return
	 * @see GuestdocDao.java
	 */
	public Map getGuestDocDetail(String checkId);
	
	/**
	 * 房间列表
	 * <功能详细描述>
	 * @param with_id
	 * @return
	 * @see GuestdocDao.java
	 */
	public List getRoomsList(String with_id);
	
	/**
	 * 房价列表
	 * <功能详细描述>
	 * @param check_id
	 * @return
	 * @see GuestdocDao.java
	 */
	public List getGstPriceList(String check_id);
	
	/**
	 * 修改客户
	 * <功能详细描述>
	 * @param guestdoc
	 * @see GuestdocDao.java
	 */
	public void updateGuestdoc(Guestdoc guestdoc);
	
	/**
	 * 保存客户
	 * <功能详细描述>
	 * @param guestdoc
	 * @see GuestdocDao.java
	 */
	public void saveGuestdoc(Guestdoc guestdoc);
	
	/**
	 * 复制客人
	 * <功能详细描述>
	 * @param checkId
	 * @return
	 * @see GuestdocDao.java
	 */
	public Guestdoc copyGuest(Integer checkId);
	
	/**
	 * 更改限额
	 * <功能详细描述>
	 * @param limit
	 * @param billId
	 * @see GuestdocDao.java
	 */
	public void updateLimit(Double limit, Integer billId);
	
	/**
	 * 新增限额
	 * <功能详细描述>
	 * @param limit
	 * @param billId
	 * @see GuestdocDao.java
	 */
	public void addLimit(Double limit, Integer billId);
	
	/**
	 * 修改其他信息
	 * <功能详细描述>
	 * @param guestdoc
	 * @see GuestdocDao.java
	 */
	public void updateOther(Guestdoc guestdoc);
	
	/**
	 * 修改客户是否延时续住
	 * <功能详细描述>
	 * @param checkId
	 * @param ifBate
	 * @see GuestdocDao.java
	 */
	public void updeteGuestIfbate(Integer checkId, String ifBate);
	
	/** 
	 * 换房续住根据房号查询
	 * @param room_id
	 * @return
	 * @see GuestdocDao.java
	 */
	public List selectRoomsWardToLive(String room_id);
	
	/** 根据billb_id查询房号看付费人下有多少间房间
	 * <功能详细描述>
	 * @param billb_id
	 * @return
	 * @see GuestdocDao.java
	 */
	public List checkToLive(String billb_id);
	
	/** 更新换房操作
	 * <功能详细描述>
	 * @param map
	 * @see GuestdocDao.java
	 */
	public void updateWardsToLive(Map map);
	
	/**
	 * 获取账目信息
	 * <功能详细描述>
	 * @param billId
	 * @param billType
	 * @param showType
	 * @param consId
	 * @param okFlag
	 * @param startDate
	 * @param endDate
	 * @return
	 * @see GuestdocDao.java
	 */
	public List getBills(Integer billId, String billType,String showType,String consId,String okFlag,String startDate,String endDate,String isInvalid);
	
	/** 根据预离时间查询客人信息
	 * <功能详细描述>
	 * @param nowDate
	 * @return
	 * @see GuestdocDao.java
	 */
	public List getselectRoomToleave(String nowDate);
	/** 保存续住信息
	 * <功能详细描述>
	 * @param jsonArray
	 * @return
	 * @see GuestdocDao.java
	 */
	public void updateRoomToleave(JSONArray jsonArray);
	
	/**
	 * 根据账目类型加载
	 * <功能详细描述>
	 * @param billId
	 * @return
	 * @see GuestdocDao.java
	 */
	public List loadConsumesByBillType(String billId);
	
	public void saveOrUpdateGstBill(Bills bill);
	
	public void saveOrUpdateGstBill2(Bills bill);

	
	/**
	 * @描述 ：根据当前房间id 时间段 查询房间不可用信息
	 * @param roomId 房间ID
	 * @param startDate 开始时间
	 * @param endDate 截至时间
	 * */
	List<Map<String, Object>> getCannotUseRoom(String roomId,
			String startDate, String endDate);

	
	public List getPreAuthorization(Integer checkId,String status,Integer billId);
	
	/**
	 * 获取bills表paynum最大值
	 * <功能详细描述>
	 * @return
	 * @see GuestdocDao.java
	 */
	public int getMaxPayNum();
	/**
	 * 房态--预定入住--散客
	 * @param bookRoomSearchVo 查询条件VO
	 * @return
	 * @throws Exception
	 */
	List<?> findFitBookCheckInList(BookRoomSearchVo bookRoomSearchVo) throws Exception ;
	/**
	 * 房态--预定入住--团体
	 * @param bookRoomSearchVo 查询条件VO
	 * @return
	 * @throws Exception
	 */
	List<?> findGroupBookCheckInList(BookRoomSearchVo bookRoomSearchVo)
			throws Exception;
	
	/**
	 * 根据帐号获取团队账目信息
	 * <功能详细描述>
	 * @param billId
	 * @return
	 * @see GuestdocDao.java
	 */
	public List getGroupBills(Integer billId);
	
	/**
	 * 根据帐号获取非住客账目信息
	 * <功能详细描述>
	 * @param billId
	 * @return
	 * @see GuestdocDao.java
	 */
	public List getNoguestBills(Integer billId,String showType,String okFlag,String startDate,String endDate,String isInvalid);
	/**
	 * 房态--预定入住--根据checkId 查询预定信息列表
	 * @param bookRoomCheckInVO 预定查询信息VO
	 * @return
	 * @throws Exception
	 */
	List<?> findFitBookCheckInInfoListBy(BookRoomCheckInVO bookRoomCheckInVO) throws Exception;
	/**
	 * 房态--预定入住--根据checkId和订单好 查询已入住人员信息列表
	 * @param bookRoomCheckInVO 预定查询信息VO
	 * @return
	 * @throws Exception
	 */
	List<?> findAlreadyCheckInPersonListBy(BookRoomCheckInVO bookRoomCheckInVO) throws Exception;
	/**
	 * 房态--预定入住--根据checkId和预定号（book_id） 查询房态区留房信息列表
	 * @param bookRoomCheckInVO
	 * @return
	 * @throws Exception
	 */
	List<?> findBookRoomsListBy(BookRoomCheckInVO bookRoomCheckInVO)
			throws Exception;
	
	/**
	 * @描述 获取客户信息
	 * @param checkId 客户id
	 * */
	Map<String, Object> getGuestDocInf(Integer checkId);
	
	/**
	 * @描述 获取欠费信息
	 * */
	Map<String, Object> getArrearsByRoomId(String roomId); 
	
	/**
	 * 根据来源类别获取客人来源
	 * <功能详细描述>
	 * @param codeIds
	 * @return
	 * @see GuestdocDao.java
	 */
	public List getGstOriByType(String codeIds);
	
	/**
	 *@描述 判断是否付费房间
	 *@param roomId 房间号码
	 * */
	Boolean isPayRoom(String roomId);

	/**
	 * 客单详情，房价列表
	 * @param checkId
	 * @param withId
	 * @return
	 */
	public List<?> findRoomPriceListBy(Integer checkId , Integer withId);
	
	/**
	 * 获取客单详情
	 * <功能详细描述>
	 * @param checkId
	 * @return
	 * @see GuestdocDao.java
	 */
	public GuestDetailVo getGuestDetailByCheckId(Integer checkId);
	
	/**
	 * 获取无效单
	 * <功能详细描述>
	 * @param billType
	 * @param billId
	 * @param showType
	 * @param consId
	 * @param startDate
	 * @param endDate
	 * @return
	 * @see GuestdocDao.java
	 */
	public List getInvalidBIlls(Integer billType,String billId,String showType,String consId,String startDate,String endDate);
	
	/**
	 * @描述 获取账务信息
	 * @param roomId 房间ID
	 * */
	public Double getRoomBillInfo(String roomId);
}
