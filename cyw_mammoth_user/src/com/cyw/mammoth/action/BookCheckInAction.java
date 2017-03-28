package com.cyw.mammoth.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.cyw.common.util.ArrayUtils;
import com.cyw.mammoth.bean.Hbuilding;
import com.cyw.mammoth.bean.Parameter;
import com.cyw.mammoth.service.BookCheckInSvc;
import com.cyw.mammoth.service.BookRoomSvc;
import com.cyw.mammoth.service.GuestdocSvc;
import com.cyw.mammoth.service.HbuildingSvc;
import com.cyw.mammoth.service.ParameterSvc;
import com.cyw.mammoth.service.RoomsSvc;
import com.cyw.mammoth.vo.BookRoomCheckInVO;
import com.cyw.mammoth.vo.BookRoomSearchVo;

/**
 * 房态--预定入住
 * @author Administrator
 */
@Controller
@RequestMapping("/bookCheckIn")
public class BookCheckInAction {
	
	@Autowired
	private GuestdocSvc guestdocSvc;
	@Autowired
	private ParameterSvc parameterSvc ;
	@Autowired
	private BookCheckInSvc bookCheckInSvc ;
	@Autowired
	private HbuildingSvc hbuildingSvc ;
	@Autowired
	private RoomsSvc roomsSvc ;
	@Autowired
	private BookRoomSvc bookRoomSvc ;
	
	@RequestMapping("/list.do")
	public String list(ModelMap model){
		model.addAttribute("hotelDate", DateFormatUtils.format(parameterSvc.GetHotelDate(), "yyyy-MM-dd")) ;
		Parameter paramter = parameterSvc.get(7);//1 true 0 false
		Boolean scanCard = paramter.getPara1Flag();
		Boolean hairRoomCard = paramter.getPara2Flag();
		model.addAttribute("scanCard", scanCard);
		model.addAttribute("hairRoomCard", hairRoomCard);
		return "rooms/bookCheckIn/indexList";
	}
	@RequestMapping("/checkInInput.do")
	public String checkInInput(ModelMap model){
		return "rooms/bookCheckIn/checkInInput";
	}
	/**
	 * 查询所有散客预定单
	 * @return
	 */
	@RequestMapping("/findFitOrderList.do")
	@ResponseBody
	public String findFitOrderList(ModelMap model,BookRoomSearchVo bookRoomSearchVo) throws Exception{
		JSONObject jsonObj = new JSONObject() ;
		model.addAttribute("bookRoomSearchVo", bookRoomSearchVo);
		jsonObj.put("flag", "fit") ;
		jsonObj.put("listJson", guestdocSvc.findFitBookCheckInList(bookRoomSearchVo)) ;
		return jsonObj.toJSONString();
	}
	/**
	 * 查询所有团体预定单
	 * @return
	 */
	@RequestMapping("/findGroupOrderList.do")
	@ResponseBody
	public String findGroupOrderList(ModelMap model,BookRoomSearchVo bookRoomSearchVo) throws Exception{
		JSONObject jsonObj = new JSONObject() ;
		model.addAttribute("bookRoomSearchVo", bookRoomSearchVo);
		jsonObj.put("flag", "group") ;
		jsonObj.put("listJson", guestdocSvc.findGroupBookCheckInList(bookRoomSearchVo)) ;
		return jsonObj.toJSONString();
	}
	/**
	 * 散客入住登记
	 * @param model
	 * @param bookRoomCheckInVO
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/fitCheckIn.do")
	@ResponseBody
	public String fitCheckIn(ModelMap model,BookRoomCheckInVO bookRoomCheckInVO) throws Exception{
		JSONObject jsonObj = new JSONObject() ;
		jsonObj.put("bookRoomSearchVo", bookRoomCheckInVO);
		//办理入住页面-- 预留房型信息列表
		List<?> bookCheckInInfoList = guestdocSvc.findFitBookCheckInInfoListBy(bookRoomCheckInVO) ;
		jsonObj.put("bookCheckInInfoListJson", bookCheckInInfoList) ;
		//办理入住页面-- 房态区信息列表 (对应预留信息列表第一条记录的房态信息)
		if(bookRoomCheckInVO.getBookId() ==null && bookCheckInInfoList!=null && bookCheckInInfoList.size() > 0){
			Map<String, Object> map = (Map<String, Object>)bookCheckInInfoList.get(0) ;
			Object bookRoomIdObj = map.get("bookRoomId") ;
			Object leaveDateObj =map.get("leaveDate") ;
			Object reachDateObj =map.get("reachDate") ;
			BookRoomCheckInVO brcVO = bookRoomCheckInVO ;
			brcVO.setReachDate(String.valueOf(reachDateObj)) ;
			brcVO.setLeaveDate(String.valueOf(leaveDateObj)) ;
			brcVO.setBookId(Integer.valueOf(String.valueOf(bookRoomIdObj))) ;
			List<?> list = bookCheckInSvc.findRoomsBy(brcVO) ;
			jsonObj.put("listJson", list) ;
		}
		// 留房信息列表(bookId and checkId)
		jsonObj.put("saveRoomListJson", guestdocSvc.findBookRoomsListBy(bookRoomCheckInVO)) ;
		//办理入住页面-- 已入住人员信息列表
		jsonObj.put("alreadyCheckInPersonList", guestdocSvc.findAlreadyCheckInPersonListBy(bookRoomCheckInVO)) ;
		
		// 分装建筑物及楼层信息列表
		List<Hbuilding> list_buildings = hbuildingSvc.getList("status", 0) ; 
		List<Map<String, Object>> list_maps = new ArrayList<Map<String,Object>>(); 
		Map<String, Object> map = null ;
		List<String> floorNos = null ;
		for (Hbuilding hbuilding : list_buildings) {
			map = new HashMap<String, Object>();
			floorNos = roomsSvc.getFloorNoByBuildId(hbuilding.getCodeId());
			if(floorNos == null || floorNos.size() == 0)
				continue ;
			map.put("id", hbuilding.getId()) ;
			map.put("codeId", hbuilding.getCodeId()) ;
			map.put("codeNamec", hbuilding.getCodeNamec()) ;
			map.put("codeNamee", hbuilding.getCodeNamee()) ;
			map.put("floors", ArrayUtils.convertStrArrayToString(floorNos.toArray(new String[]{}))) ;
			list_maps.add(map) ;
		}
		jsonObj.put("buildListJson", list_maps) ;
		List<?> bookRoomList = bookRoomSvc.getList("checkId", Integer.valueOf(bookRoomCheckInVO.getCheckId())) ;
		jsonObj.put("bookRoomListSize", (bookRoomList!=null && !bookRoomList.isEmpty()) ? bookRoomList.size() : 0) ;
		return jsonObj.toJSONString();
	}
	/**
	 *团体入住登记
	 * @param model
	 * @param bookRoomCheckInVO
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/groupCheckIn.do")
	@ResponseBody
	public String groupCheckIn(ModelMap model,BookRoomCheckInVO bookRoomCheckInVO) throws Exception{
		JSONObject jsonObj = new JSONObject() ;
		jsonObj.put("bookRoomSearchVo", bookRoomCheckInVO);
		//办理入住页面-- 预留房型信息列表
		List<?> bookCheckInInfoList = guestdocSvc.findFitBookCheckInInfoListBy(bookRoomCheckInVO) ;
		jsonObj.put("bookCheckInInfoListJson", bookCheckInInfoList) ;
		//办理入住页面-- 房态区信息列表 (对应预留信息列表第一条记录的房态信息)
		if(bookRoomCheckInVO.getBookId() ==null && bookCheckInInfoList!=null && bookCheckInInfoList.size() > 0){
			@SuppressWarnings("unchecked")
			Map<String, Object> map = (Map<String, Object>)bookCheckInInfoList.get(0) ;
			Object bookRoomIdObj = map.get("bookRoomId") ;
			Object leaveDateObj =map.get("leaveDate") ;
			Object reachDateObj =map.get("reachDate") ;
			BookRoomCheckInVO brcVO = bookRoomCheckInVO ;
			brcVO.setReachDate(String.valueOf(reachDateObj)) ;
			brcVO.setLeaveDate(String.valueOf(leaveDateObj)) ;
			brcVO.setBookId(Integer.valueOf(String.valueOf(bookRoomIdObj))) ;
			List<?> list = bookCheckInSvc.findRoomsBy(brcVO) ;
			jsonObj.put("listJson", list) ;
		}
		// 留房信息列表(bookId and checkId)
		jsonObj.put("saveRoomListJson", guestdocSvc.findBookRoomsListBy(bookRoomCheckInVO)) ;
		//办理入住页面-- 已入住人员信息列表
		jsonObj.put("alreadyCheckInPersonList", guestdocSvc.findAlreadyCheckInPersonListBy(bookRoomCheckInVO)) ;
		
		// 分装建筑物及楼层信息列表
		List<Hbuilding> list_buildings = hbuildingSvc.getList("status", 0) ; 
		List<Map<String, Object>> list_maps = new ArrayList<Map<String,Object>>(); 
		Map<String, Object> map = null ;
		List<String> floorNos = null ;
		for (Hbuilding hbuilding : list_buildings) {
			map = new HashMap<String, Object>();
			floorNos = roomsSvc.getFloorNoByBuildId(String.valueOf(hbuilding.getCodeId()));
			if(floorNos == null || floorNos.size() == 0)
				continue ;
			map.put("id", hbuilding.getId()) ;
			map.put("codeId", hbuilding.getCodeId()) ;
			map.put("codeNamec", hbuilding.getCodeNamec()) ;
			map.put("codeNamee", hbuilding.getCodeNamee()) ;
			map.put("floors", ArrayUtils.convertStrArrayToString(floorNos.toArray(new String[]{}))) ;
			list_maps.add(map) ;
		}
		jsonObj.put("buildListJson", list_maps) ;
		List<?> bookRoomList = bookRoomSvc.getList("checkId", Integer.valueOf(bookRoomCheckInVO.getCheckId())) ;
		jsonObj.put("bookRoomListSize", (bookRoomList!=null && !bookRoomList.isEmpty()) ? bookRoomList.size() : 0) ;
		return jsonObj.toJSONString();
	}
	/**
	 * 办理入住页面--条件查询--获取房态区房间列表
	 * @param model
	 * @param bookRoomCheckInVO
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/findRoomsBy.do")
	@ResponseBody
	public String findRoomsBy(ModelMap model,BookRoomCheckInVO bookRoomCheckInVO) throws Exception{
		JSONObject jsonObj = new JSONObject() ;
		jsonObj.put("bookRoomSearchVo", bookRoomCheckInVO);
		//办理入住页面-- 预留房型信息列表
		List<?> bookCheckInInfoList = guestdocSvc.findFitBookCheckInInfoListBy(bookRoomCheckInVO) ;
		jsonObj.put("bookCheckInInfoListJson", bookCheckInInfoList) ;
		List<?> list = bookCheckInSvc.findRoomsBy(bookRoomCheckInVO) ;
		// 房态区信息列表
		jsonObj.put("listJson", list) ;
		// 留房信息列表(bookId and checkId)
		jsonObj.put("saveRoomListJson", guestdocSvc.findBookRoomsListBy(bookRoomCheckInVO)) ;
		return jsonObj.toJSONString();
	}
	
	/**
	 * 确认全部抵达
	 * @param model
	 * @param bookRoomCheckInVO
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/confirmAllReach.do")
	@ResponseBody
	public String confirmAllReach(ModelMap model,BookRoomCheckInVO bookRoomCheckInVO) throws Exception{
		JSONObject jsonObj = new JSONObject() ;
		bookCheckInSvc.confirmAllReach(bookRoomCheckInVO) ;
		jsonObj.put("success",true) ;
		return jsonObj.toJSONString();
	}
}
