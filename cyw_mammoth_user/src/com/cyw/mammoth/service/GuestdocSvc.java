package com.cyw.mammoth.service;

import java.util.List;
import java.util.Map;

import org.springframework.ui.Model;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.cyw.common.base.service.BaseSvc;
import com.cyw.mammoth.bean.BPaied;
import com.cyw.mammoth.bean.Bills;
import com.cyw.mammoth.bean.BookRoom;
import com.cyw.mammoth.bean.GstCreditAuth;
import com.cyw.mammoth.bean.Guestdoc;
import com.cyw.mammoth.bean.Operator;
import com.cyw.mammoth.dao.GuestdocDao;
import com.cyw.mammoth.vo.AjaxJson;
import com.cyw.mammoth.vo.BookRoomCheckInVO;
import com.cyw.mammoth.vo.BookRoomSearchVo;
import com.cyw.mammoth.vo.GroupSearchVO;
import com.cyw.mammoth.vo.GuestDetailVo;
import com.cyw.mammoth.vo.GuestDocModel;
import com.cyw.mammoth.vo.GuestSearchVO;
import com.cyw.mammoth.vo.WebRoomSearchVO;
/**
 * <一句话功能简述>
 * <功能详细描述>
 * 
 * 
 * @author  litiangang@cyw.so
 * @version  v-1.0
 * @see  GuestdocSvc.java
 * @since  cyw-1.0
 */
public interface GuestdocSvc extends BaseSvc<Guestdoc, Integer> {
	
	/** 
	 * 查询预定列表
	 * @param searchVo
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	public List<GuestDocModel> getBookRoomList(BookRoomSearchVo searchVo);
	
	/**
	 * 查询预定入住列表
	 * @param searchVo
	 * @return
	 */
	public List<GuestDocModel> getBookAccommodate(BookRoomSearchVo searchVo);
	
	
	/** 
	 * 保存预定信息
	 * @param guestdoc
	 * @return
	 * @see saveForBookRoom
	 */
	public Guestdoc saveForBookRoom(Guestdoc guestdoc,List<BookRoom> brLists,String roomIds,String currBookRoomId);
	
	/** 
	 * 修改预定信息
	 * @param guestdoc
	 * @param checkId
	 * @return
	 * @see updateForBookRoom
	 */
	public Guestdoc updateForBookRoom(Guestdoc guestdoc,List<BookRoom> brLists,String roomIds,String currBookRoomId);
	
	public Guestdoc updateForBookRoom2(Guestdoc guestdoc,List<BookRoom> brLists,String roomIds,String currBookRoomId);
	
	

	public Guestdoc getGuestdoc(String roomId,String chkState,String chkExt); 
	
	/**
	 * 根据房号获取住客信息
	 * <功能详细描述>
	 * @param roomId
	 * @return
	 * @see GuestdocSvc.java
	 */
	public List getGuestDocListByRoomId(String roomId);
	
	
	/**
	 * 获取住客资料列表
	 * <功能详细描述>
	 * @return
	 * @see GuestdocSvc.java
	 */
	public List getGuestDocList(GuestSearchVO searchVO);
	
	/**
	 * 获取团队资料列表
	 * <功能详细描述>
	 * @param searchVO
	 * @return
	 * @see GuestdocSvc.java
	 */
	public List getGroupList(GroupSearchVO searchVO);
	
	
	public boolean getBookListByNo(String bookNo);
	
	/**
	 * 根据房号获取其所在团队其他房号信息
	 * <功能详细描述>
	 * @param roomId
	 * @return
	 * @see GuestdocSvc.java
	 */ 
	public List getGroupMembersByRoomId(String roomId);
	
	/**
	 * 获取所有网房
	 * <功能详细描述>
	 * @return
	 * @see GuestdocSvc.java
	 */
	public List getWebRooms(WebRoomSearchVO searchVO);
	
	/**
	 * 网房条件查询
	 * <功能详细描述>
	 * @param searchVO
	 * @return
	 * @see GuestdocSvc.java
	 */
	public List searchWebRooms(WebRoomSearchVO searchVO);
	
	/**
	 * 设为网房
	 * <功能详细描述>
	 * @see GuestdocSvc.java
	 */
	public boolean setOrCancleWebRoom(String roomId,String flag);
	
	/**
	 * 住客详情
	 * <功能详细描述>
	 * @param checkId
	 * @return
	 * @see GuestdocSvc.java
	 */
	public Map getGuestDocDetail(String checkId);
	
	/**
	 * 客单详情房间列表
	 * <功能详细描述>
	 * @param with_id
	 * @return
	 * @see GuestdocSvc.java
	 */
	public List getRoomsList(String with_id);
	
	/**
	 * 客单详情房价列表
	 * <功能详细描述>
	 * @param check_id
	 * @return
	 * @see GuestdocSvc.java
	 */
	public List getGstPriceList(String check_id);
	
	
	/**
	 * 新增及修改客人信息
	 * <功能详细描述>
	 * @param guestdoc
	 * @param Alimit
	 * @param Blimit
	 * @see GuestdocSvc.java
	 */
	public int saveGuest(Guestdoc guestdoc,Double Alimit,Double Blimit);
	
	/**
	 * 客人信息修改
	 * <功能详细描述>
	 * @param check_id
	 * @return
	 * @see GuestdocSvc.java
	 */
	public void updateGuestdoc(Guestdoc guestdoc,Double Alimit,Double Blimit);
	
	/**
	 * 复制客人
	 * <功能详细描述>
	 * @param checkId
	 * @see GuestdocSvc.java
	 */
	public Map copyGuest(Integer checkId);
	
	/**
	 * 其他信息修改
	 * <功能详细描述>
	 * @param guestdoc
	 * @see GuestdocSvc.java
	 */
	public void updateOther(Guestdoc guestdoc);
	
	/**
	 * 修改是否延长续住
	 * <功能详细描述>
	 * @param checkId
	 * @param ifBate
	 * @see GuestdocSvc.java
	 */
	public void updeteGuestIfbate(Integer checkId,String ifBate);
	
	public void updataChangeGrpDoc(JSONArray changeDataObj,JSONObject changLftObj, JSONObject changRightObj);
	
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
	 * @see GuestdocSvc.java
	 */
	public List checkToLive(String billb_id);
	
	/** 更新换房操作
	 * <功能详细描述>
	 * @param map
	 * @see GuestdocSvc.java
	 */
	public void updateWardsToLive(Map map);
	
	/**
	 * @描述 查询账务信息
	 * @param biilID 帐号ID
	 * @param billType账务类型 A账 B账
	 * @param showType 明细（A）、汇总（B）、分类（C）
	 * @param okFlag okFlag// 未结（N）、已结(Y)、全部
	 * @param startDate 开始日期
	 * @param endDate 结束日期
	 * */
	public List getBills(Integer billId,String billType,String showType,String consId,String okFlag,String startDate,String endDate,String isInvalid);
	
	/** 根据预离时间查询客人信息
	 * <功能详细描述>
	 * @param nowDate
	 * @return
	 * @see GuestdocSvc.java
	 */
	public List getselectRoomToleave(String nowDate);
	
	/** 保存续住信息
	 * <功能详细描述>
	 * @param jsonArray
	 * @return
	 * @see GuestdocSvc.java
	 */
	public void updateRoomToleave(JSONArray jsonArray);
	
	public List loadConsumesByBillType(String billId);
	
	/**
	 * 入账保存
	 * <功能详细描述>
	 * @param type
	 * @param billids
	 * @param bill
	 * @see GuestdocSvc.java
	 */
	public void saveBill(String type,String billids,Bills bill,Integer checkId);
	
	/**
	 * @描述：查询 联房信息
	 * @param check_id guestdoc 主键
	 * */
	Map<String, Object> getUnionGuesInf(Integer checkId);
	
	/**
	 * 检查帐号是否存在
	 * <功能详细描述>
	 * @param billType
	 * @param billids
	 * @return
	 * @see GuestdocSvc.java
	 */
	public AjaxJson checkBillId(String billType,String billids,String type);
	
	/**
	 * @Date 2015-11-06
	 * @描述：查询不可用空房信息
	 * @param roomId
	 *            房号
	 * @param startDate
	 *            查询开始日期
	 * @param endDate
	 *            截至日期
	 * @return 不可用信息
	 * */
	List<Map<String, Object>> getNullRoomInf(String roomId,String startDate,String endDate);
	
	
	/**
	 * 收款保存
	 * <功能详细描述>
	 * @param bills
	 * @see GuestdocSvc.java
	 */
	public void depositSaveBill(Bills bills,Integer checkId);
	
	public void setSplit(BPaied bpaied,String ifBate);
	
	/**
	 * 获取预授权列表
	 * <功能详细描述>
	 * @param checkId
	 * @param status
	 * @return
	 * @see GuestdocSvc.java
	 */
	public List getPreAuthorization(Integer checkId,String status,Integer billId);
	
	/**
	 * 保存预授权
	 * <功能详细描述>
	 * @param creditAuth
	 * @see GuestdocSvc.java
	 */
	public void savePreAuth(GstCreditAuth creditAuth);
	
	/**
	 * 完成预授权
	 * <功能详细描述>
	 * @param ids
	 * @see GuestdocSvc.java
	 */
	public void finishAuth(String id,String balance,String operId,String finishNo,Integer billId,String billType);
	
	/**
	 * 取消预授权
	 * <功能详细描述>
	 * @param ids
	 * @see GuestdocSvc.java
	 */
	public void cancleAuth(String id,String balance,String cancelNo,String operId,Integer billId);
	
	/**
	 * 取消预授权
	 * <功能详细描述>
	 * @param ids
	 * @see GuestdocSvc.java
	 */
	public void updateAuthStatus(String ids,String balance,Integer billId);
	
	/**
	 * 结账前判断是否有未结的预授权
	 * <功能详细描述>
	 * @param checkId
	 * @return
	 * @see GuestdocSvc.java
	 */
	public AjaxJson checkAuth(Integer checkId);
	/**
	 * 结账
	 * <功能详细描述>
	 * @param checkId
	 * @see GuestdocSvc.java
	 */
	public AjaxJson checkOutAccount(Integer checkId,String billIds,String bills,String operId,String oddMoney,String billId,String billType,String roomId,String tipMoney);
	
	/**
	 * 离店
	 * <功能详细描述>
	 * @param checkIds
	 * @see GuestdocSvc.java
	 */
	public void leaveRoom(String checkIds);

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
	List<?> findGroupBookCheckInList(BookRoomSearchVo bookRoomSearchVo) throws Exception;
	
	/**
	 * 根据帐号获取团队账目信息
	 * <功能详细描述>
	 * @param billId
	 * @return
	 * @see GuestdocSvc.java
	 */
	public List getGroupBills(Integer billId);
	
	/**
	 * 根据帐号获取非住客账目信息
	 * <功能详细描述>
	 * @param billId
	 * @return
	 * @see GuestdocSvc.java
	 */
	public List getNoguestBills(Integer billId,String showType,String okFlag,String startDate,String endDate,String isInvalid);
	
	public void transferConfirm(String topBillId,String bottomBillId,String transferData,Integer checkId,String operId);
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
	 * 退房
	 * <功能详细描述>
	 * @param checkId
	 * @param roomId
	 * @see GuestdocSvc.java
	 */
	public void checkOutRoom(String roomId,String checkIds,String operId);
	
	public AjaxJson checkBills(String roomIds);
	
	public AjaxJson cancleBill(Integer id,String payNum,Integer billId,String operId,String okFlag,Integer checkId);
	/**
	 * 房态--预定入住--根据checkId和预定号（book_id） 查询房态区留房信息列表
	 * @param bookRoomCheckInVO
	 * @return
	 * @throws Exception
	 */
	List<?> findBookRoomsListBy(BookRoomCheckInVO bookRoomCheckInVO) throws Exception;
	
	/**
	 * 换房保存（重新构建updateWardsToLive接口）
	 * @param data
	 * @param objA
	 * @param objB
	 */
	public void changeRoom(JSONArray data, JSONObject objA, JSONObject objB, Operator oper);
	
	/**
	 * 保存续住信息
	 * @param data
	 * @param if_bdate_flag
	 */
	public void saveRoomToleave(JSONArray data, boolean if_bdate_flag, Operator oper) throws Exception;
	
	public AjaxJson checkBillsInleaveHotel(String checkIds);
	
	public void leaveHotel(String checkIds,String roomIds);
	
	/**
	 * 办理入住
	 * <功能详细描述>
	 * @param checkId
	 * @param array
	 * @param operId
	 * @param withId
	 * @return
	 * @see GuestdocSvc.java
	 */
	public String getGuestDocDetailIn(Integer checkId,JSONArray array,String operId,String withId);
	
//	public int getNewCheckid();
	
	public Map checkOut(String roomIds,String reachDate,String leaveDate,String operId);
	
	/**
	 * @描述 获取客户信息
	 * @param checkId 客户信息ID
	 * */
	Map<String, Object> getGuestDocInf(Integer checkId);
	
	/**
	 * @描述 获取客户欠费信息
	 * @param RoomId
	 * */
	Map<String , Object> getArrearsByRoomId(String roomId);
	
	public List getGstOriByType(String codeIds);
	/**
	 * 客单详情，房价列表
	 * @param checkId
	 * @param withId
	 * @return
	 */
	public List<?> findRoomPriceListBy(Integer checkId , Integer withId);
	
	/**
	 * @描述 办理入住逻辑处理
	 * @param model spring-mvc 缓存对象
	 * @param roomIds 房间ID
	 * @param map 住客xinx
	 * */
	public void saveCheckIn(Model model,String roomIds,Map map);
	
	/**
	 * 获取客单详情
	 * @param checkId客户信息 checkID
	 * @return 客户信息vo
	 * */
	public GuestDetailVo getGuestDetail(Integer checkId);
	
	/**
	 * @描述 根据房间号获取与该房间联房的其他房间号列表
	 * @param entity 当前房间住客信息
	 * */
	public String[] getRoomIds(Guestdoc entity);
	
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
	 * @see GuestdocSvc.java
	 */
	public List getInvalidBIlls(Integer billType,String billId,String showType,String consId,String startDate,String endDate);
	
	/**
	 * @描述 获取账务信息
	 * @param roomIds 房屋ID
	 * */
	List<Map<String, String>> getRoomBillInfo(String roomIds);
	
	/**
	 * @描述 货物不可售详情
	 * @param roomId 房间号
	 * @param startDate 开始日期
	 * @param endDate 截止日期
	 * */
	List<Map<String, String>> getRoomNoSaleInfo(String roomId,String startDate,String endDate);
	
	/**
	 * @描述 散客预定入住
	 * @param roomId 房间ID
	 * @param withId 同来人标识
	 * @param bookIdArr 预定标记
	 * @param checkId 客户主键
	 * @param bookId 预定id
	 * */
	String reserveCheckIn(Model model, String roomIds, Integer withId,Integer checkId,Integer bookId);
	
	/**
	 * @描述 团队预定入住
	 * @param roomId房间ID
	 * @param withId 同来人标识
	 * @param withId 同来人标识
	 * @param bookIdArr 预定标记
	 * @param checkId 客户主键
	 * @param bookId 预定id
	 * */
	String grpCheckIn(Model model, String roomIds, Integer withId,Integer checkId,Integer bookId);
}
