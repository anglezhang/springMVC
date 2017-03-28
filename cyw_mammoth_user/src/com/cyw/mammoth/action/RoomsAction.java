package com.cyw.mammoth.action;

import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cyw.common.util.Constant;
import com.cyw.common.util.DateUtils;
import com.cyw.mammoth.bean.Guestdoc;
import com.cyw.mammoth.bean.HroomCharacters;
import com.cyw.mammoth.bean.HroomType;
import com.cyw.mammoth.bean.Parameter;
import com.cyw.mammoth.bean.Rooms;
import com.cyw.mammoth.service.GuestdocSvc;
import com.cyw.mammoth.service.HbuildingSvc;
import com.cyw.mammoth.service.HroomCharactersSvc;
import com.cyw.mammoth.service.HroomTypeSvc;
import com.cyw.mammoth.service.ParameterSvc;
import com.cyw.mammoth.service.RoomNumSvc;
import com.cyw.mammoth.service.RoomsSvc;
import com.cyw.mammoth.vo.HroomTypeVo;
import com.cyw.mammoth.vo.RoomsSearchVo;
import com.cyw.mammoth.vo.RoomsTypeSearchVo;

/**
 * 房间管理 <功能详细描述>
 * 
 * @author zhangzhenxing
 * @version v1.0
 * @see RoomsAction.java
 * @since cyw-1.0
 */
@Controller
@RequestMapping(value = "/rooms")
public class RoomsAction {
	private static final Logger log = LoggerFactory
			.getLogger(RoomsAction.class);

	/**
	 * @描述 rooms服务
	 * */
	@Autowired
	private RoomsSvc roomSvc;

	/**
	 * @描述：房间类型服务
	 * */
	@Autowired
	private HroomCharactersSvc hroomCharSvc;

	/**
	 * @描述：客户资料服务
	 * */
	@Autowired
	private GuestdocSvc guestDocSvc;
	
	/**
	 * @描述 公共参数服务类Svc
	 * */
	@Autowired
	private ParameterSvc paramterSvc;
	/**
	 * @描述：获取建筑物楼层
	 * */
	@RequestMapping(value = "/getfloors/{buildId}")
	@ResponseBody
	public List<String> getfloors(Model model, @PathVariable String buildId) {
		List<String> floorNos = roomSvc.getFloorNoByBuildId(buildId);
		return floorNos;
	}

	/**
	 * @描述：将房屋设为不洁
	 * */
	@RequestMapping(value = "/setUnclear/{roomIds}")
	@ResponseBody
	public String setUnclear(Model model, @PathVariable String roomIds) {
		log.info("房间id为:" + roomIds + " 被设为不洁");
		if (roomSvc.setUnclear(roomIds))
			return "true";
		return "false";
	}

	/**
	 * @描述 将房屋状态设为清洁未检查
	 * */
	@RequestMapping(value = "/setClearUncheck/{roomIds}")
	@ResponseBody
	public String setClearUnCheck(Model model, @PathVariable String roomIds) {
		log.info("房间id为:" + roomIds + " 清洁未检查");
		if (roomSvc.setClearUncheck(roomIds))
			return "true";
		return "false";
	}

	/**
	 * @描述 将房屋状态设置为 清洁已查
	 * */
	@RequestMapping(value = "/setClearCheck/{roomIds}")
	@ResponseBody
	public String setClearheck(Model model, @PathVariable String roomIds) {
		log.info("房间id为:" + roomIds + " 清洁已查");
		if (roomSvc.setClearheck(roomIds))
			return "true";
		return "false";
	}

	/**
	 * @描述：查询 当前可售房类型数量
	 * @param startDate
	 *            开始日期
	 * @param endDate
	 *            截至日期
	 * */
	@RequestMapping(value = "/getRoomType.do")
	public String getRoomType(Model model,
			@RequestParam("startDate") String startDate,
			@RequestParam("endDate") String endDate,
			@RequestParam("floorNo") String buildCode) {
		List<HroomTypeVo> list = roomSvc.getRoomCountByType(startDate, endDate,buildCode);
		model.addAttribute("list", list);
		return "rooms/room_type";
	}

	/**
	 * @描述：查询房类特征
	 * */
	@RequestMapping(value = "/getFeatures.do")
	public String getFeatures(Model model) {
		List<HroomCharacters> list = hroomCharSvc.getList("status", 0);
		model.addAttribute("list", list);
		return "rooms/room_feature";
	}

	/**
	 * @Date 2015-11-06
	 * @描述：查询客户信息
	 * */
	@RequestMapping(value = "/getGuestDocInf.do")
	@ResponseBody
	public Map<String, Object> getGuestDocInf(@RequestParam("checkId") Integer checkId) {
		if (checkId != null) {
			return guestDocSvc.getGuestDocInf(checkId);
		}
		return null;
	}

	/**
	 * @Date 2015-11-06
	 * @描述：查询客户联房信息
	 * @return 联房的信息 ROOM_ID 是否主房(集合) 团队信息
	 * */
	@RequestMapping(value = "/getUnionInf.do")
	@ResponseBody
	public Map<String, Object> getUnionInf(
			@RequestParam("checkId") Integer checkId) {
		if (checkId != null) {
			return guestDocSvc.getUnionGuesInf(checkId);
		}
		return null;
	}

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
	@RequestMapping(value = "/getNullRoomInf.do")
	@ResponseBody
	public List<Map<String, Object>> getNullRoomInf(
			@RequestParam("roomId") String roomId,
			@RequestParam("startDate") String startDate,
			@RequestParam("endDate") String endDate) {
		if (!StringUtils.isEmpty(roomId) && !StringUtils.isEmpty(startDate)
				&& !StringUtils.isEmpty(endDate)) {
			return guestDocSvc.getNullRoomInf(roomId, startDate, endDate);
		}
		return null;
	}

	/**
	 * @描述：选中房间信息列表
	 * @param roomsIds
	 * */
	@RequestMapping(value = "/getSelRoomsInf/{roomsIds}")
	public String getSelRoomsInf(Model model, @PathVariable String roomsIds) {
		List<Rooms> list = roomSvc.getSelRoomsInf(roomsIds);
		model.addAttribute("list", list);
		return "rooms/room_selected";
	}

	/**
	 * @描述 查询客户留房信息
	 * */
	@RequestMapping(value = "/getLeaveRoomInfo.do")
	@ResponseBody
	public Map<String, Object> getLeaveRoomInfo(@RequestParam String roomId,
			@RequestParam String startDate, @RequestParam String endDate) {
		
		return roomSvc.getLeaveRoomInfo(roomId, startDate, endDate);
	}
	
	/**
	 * @描述 查询客户抵店信息
	 * */
	@RequestMapping(value = "/willcomeroominf.do")
	@ResponseBody
	public Map<String, Object> getWillComeRoomInfo(@RequestParam String roomId,
			@RequestParam String startDate){
		return roomSvc.getWillComeRoomInfo(roomId, startDate);
	}
	
	/**
	 * @描述 获取预离信息
	 * */
	@RequestMapping(value="/getWillLeaveRoomInfo.do")
	@ResponseBody
	public Map<String, Object> getWillLeaveRoomInfo(@RequestParam String roomId){
		Parameter param = paramterSvc.get(5);
		Map<String, Object> inf = new HashMap<String, Object>();
		if(param==null){
			log.info("参数半日租 全日租设置错误");
			return null;
		}
		String time = param.getPara3Name();
		if(time.length()!=11){
			log.info("参数半日租 全日租设置错误");
			return null;
		}
		//全日租时间 半日租时间
		String startTime = time.substring(0, 5);
		String endTime = time.substring(6, 11);
		Date now = new Date(System.currentTimeMillis());
		String startDateTime = DateUtils.dateFormat2.format(now) +  " " + startTime + ":00";
		String endDateTime = DateUtils.dateFormat2.format(now) + " " + endTime + ":00";
		try {
			
			Date startDate = DateUtils.dateFormat.parse(startDateTime);
			Date endDate = DateUtils.dateFormat.parse(endDateTime);
			if(now.getTime() > startDate.getTime() && now.getTime() < endDate.getTime()){
				//半日租
				inf.put("INFO", "需加收半日租");
			}else if(now.getTime() > endDate.getTime()){
				//全日租
				inf.put("INFO", "需加收全日租");
			}
			inf.put("TIME", new Date(startDate.getTime()));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.error("日期格式化错误");
		}
		return inf;
	}
	
	/**
	 * @描述 获取欠费信息
	 * */
	@RequestMapping(value="getArrears.do")
	@ResponseBody
	public Map<String, Object> getArrears(@RequestParam String roomId){
		
		return guestDocSvc.getArrearsByRoomId(roomId);
	}
}
