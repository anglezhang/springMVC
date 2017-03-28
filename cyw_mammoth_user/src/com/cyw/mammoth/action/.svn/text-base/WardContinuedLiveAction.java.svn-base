package com.cyw.mammoth.action;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.cyw.common.util.DateTime;
import com.cyw.common.util.DateUtils;
import com.cyw.mammoth.auth.AppBaseCfg;
import com.cyw.mammoth.bean.GrpDoc;
import com.cyw.mammoth.bean.Operator;
import com.cyw.mammoth.dao.GrpDocDao;
import com.cyw.mammoth.dao.ParameterDao;
import com.cyw.mammoth.dao.RoomNumDao;
import com.cyw.mammoth.dao.RoomsDao;
import com.cyw.mammoth.service.GrpDocSvc;
import com.cyw.mammoth.service.GuestdocSvc;
import com.cyw.mammoth.service.ParameterSvc;
import com.google.gson.Gson;

/**
 * 换房/续住业务类
 * <功能详细描述>
 * 
 * @author  litiangang@cyw.so
 * @version  v-1.0
 * @see  WardContinuedLiveAction.java
 * @since  cyw-1.0
 */
@SuppressWarnings({ "unchecked", "rawtypes" }) 
@Controller
public class WardContinuedLiveAction {

	@Autowired
	private GuestdocSvc guestdocSvc;
	@Autowired
	private GrpDocSvc grpDocSvc;
	@Autowired
	private GrpDocDao grpDocDao;
	@Autowired
	private ParameterSvc parameterSvc;
	@Autowired
	private RoomNumDao roomNumDao;
	@Autowired
	private ParameterDao parameterDao;
	@Autowired
	private RoomsDao roomsDao;
	/** 点击换房/续住跳转
	 * <功能详细描述>
	 * @return
	 * @see WardContinuedLiveAction.java
	 */
	@RequestMapping("/rooms/wardtolive.do")
	public String index(){
		return "rooms/wards_continuedtolive";
	}
	
	/** 输入房号点击回车搜索
	 * <功能详细描述>
	 * @return
	 * @see WardContinuedLiveAction.java
	 */
	@RequestMapping("/rooms/selectwardrooms.do")
	public @ResponseBody String SelectWardRooms(@RequestParam Map dataMap){
		Gson gson = new Gson();
		String room_id = dataMap.get("rooms_id")+"";
		List list = guestdocSvc.selectRoomsWardToLive(room_id);
		
		return gson.toJson(list);
	}
	
	@RequestMapping("/rooms/changeRoom.do")
	public @ResponseBody Map changeRoom(@RequestParam String changeData,String roomAData,String roomBData){
		Operator oper = AppBaseCfg.getOperator();
		//返回值
		Map resultMap = new HashMap();
		resultMap.put("isSuccess", true);
		//夜审判断
		boolean flag = parameterSvc.getNightAuditState();
		//flag为true正在夜审
		if(flag){
			resultMap.put("isSuccess", false);
			resultMap.put("msg", "正在夜审不能进行续住操作！");
			return resultMap;
		}
		
		//map缓存flag
		Map<String, String> roomNumFlag = new HashMap<String, String>();
		roomNumFlag.put("0", "留房");
		roomNumFlag.put("1", "住房");
		roomNumFlag.put("2", "维修房");
		roomNumFlag.put("3", "冻结房");
		roomNumFlag.put("4", "网房");
		roomNumFlag.put("5", "网房预定");
		roomNumFlag.put("6", "网房留房");
		//去酒店日期
		SimpleDateFormat dateFormat2 = new SimpleDateFormat("yyyy-MM-dd");
		Date currentDate = parameterDao.GetHotelDate();
		currentDate = currentDate == null ? DateUtils.getCurrSysDate() : currentDate;
		try {
			currentDate = dateFormat2.parse(dateFormat2.format(currentDate));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		
		//需要保存的数据
		JSONArray data = JSONArray.parseArray(changeData);
		
		//循环，若数据的room_id改变，则验证改变后的房间是否可用
		for (Object object : data) {
			JSONObject dataObj = (JSONObject)object;
			//如果酒店日期大于等于离开日期，不能转
			Date leaveDate = new Date();
			try {
				leaveDate = dateFormat2.parse(dataObj.getString("leave_date").trim());
			
				if(currentDate.compareTo(leaveDate) > 0){
					resultMap.put("msg", "住客" + dataObj.getString("gst_namec") + "已经离店。");
					resultMap.put("isSuccess", false);
					return resultMap;
				}
			} catch (ParseException e) {
				resultMap.put("msg", "离店日期格式不正确。");
				resultMap.put("isSuccess", false);
				return resultMap;
			}
			String room_id = dataObj.getString("room_id").trim();
			if(!room_id.trim().equals(dataObj.getString("room_id_old").trim())){
				
				List list = roomNumDao.getRoomNumByRoomAndDate(room_id, currentDate, leaveDate);
				if(list.size() > 0){
					StringBuilder builder = new StringBuilder("");
					builder.append("房间号：");
					builder.append(room_id);
					for (int i = 0; i < list.size(); i++) {
						Map objs = (Map)list.get(i);
						builder.append("，在");
						builder.append(objs.get("reach_date").toString());
						builder.append("至");
						builder.append(objs.get("leave_date").toString());
						builder.append("期间有");
						builder.append(roomNumFlag.get(objs.get("flag").toString()));
					}
					builder.append("，不能换房。");
					
					resultMap.put("msg", builder.toString());
					resultMap.put("isSuccess", false);
					return  resultMap;
				}
			}
		}
		JSONObject roomA = (JSONObject)JSONArray.parseArray(roomAData).get(0);
		JSONObject roomB = (JSONObject)JSONArray.parseArray(roomBData).get(0);
		
		guestdocSvc.changeRoom(data, roomA, roomB, AppBaseCfg.getOperator());
		return resultMap;
	}
	@SuppressWarnings({ "unchecked", "rawtypes" })   
	@RequestMapping("/rooms/updatewardstolive.do")
	public @ResponseBody Map UpdateWardsToLive(@RequestParam Map map){
		Map dataMap = new HashMap();
		try {
			guestdocSvc.updateWardsToLive(map);
			dataMap.put("isSuccess", true);
		} catch (Exception e) {
			dataMap.put("isSuccess", false);
			dataMap.put("msg", "换房失败");
			e.printStackTrace();
		}
		
		return dataMap;
	}
	/** 根据billb_id查询房号看付费人下有多少间房间
	 * <功能详细描述>
	 * @param billb_id
	 * @return
	 * @see WardContinuedLiveAction.java
	 */
	@SuppressWarnings({ "rawtypes" }) 
	@RequestMapping("/rooms/checktolive.do")
	public @ResponseBody String CheckToLive(@RequestParam String billb_id){
		Gson gson = new Gson();
		List list = guestdocSvc.checkToLive(billb_id);
		return gson.toJson(list);
	}
	
	/** 续住操作，根据页面上输入的条件查询房间
	 * <功能详细描述>
	 * @param map
	 * @return
	 * @see WardContinuedLiveAction.java
	 */
	@RequestMapping("/rooms/selectroomtolive.do")
	public @ResponseBody String selectRoomToLive(@RequestParam Map dmap){
		Gson gson = new Gson();
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("issuccess", true);
		//所输入的房间号码
		String room_idP = dmap.get("room_idP") == null ? "" : dmap.get("room_idP").toString();
		if(!room_idP.equals("")){
			if(!roomsDao.isExistsRooms(room_idP)){
				resultMap.put("issuccess", false);
				resultMap.put("msg", "系统中不存在房号为："+room_idP+"的在房间信息。");
				return gson.toJson(resultMap);
			}
		}
		//团代码或团名称
		String grp_id_nameP = dmap.get("grp_id_nameP") == null ? "" : dmap.get("grp_id_nameP").toString();
		if(!grp_id_nameP.equals("") && !grp_id_nameP.equals("0")){
			List<GrpDoc> grpDocList = grpDocDao.getGrpDocListByNameOrId(grp_id_nameP);
			if(grpDocList == null || grpDocList.size() == 0){
				resultMap.put("issuccess", false);
				resultMap.put("msg", "系统中不存在团代码/团名为："+grp_id_nameP+"的在住团信息。");
				return gson.toJson(resultMap);
			}
		}
		List list = grpDocSvc.getSelectRoomToLive(dmap);
		resultMap.put("datalist", list);
		return gson.toJson(resultMap);
	}
	/** 全部预离查询
	 * <功能详细描述>
	 * @param nowDate
	 * @return
	 * @see WardContinuedLiveAction.java
	 */
//	@RequestMapping("/rooms/selectRoomToleave.do")
//	public @ResponseBody String selectRoomToleave(@RequestParam String nowDate){
//		Gson gson = new Gson();
////		List dList = new ArrayList();
//		List list = guestdocSvc.getselectRoomToleave(nowDate);
////		for (int i = 0; i < list.size(); i++) {
////			Map map = (Map)list.get(i);
////			//如果chk_ext为1证明是为主人
////			if("1".equals(map.get("chk_ext"))){
////				map.put("chk_ext", "主人");
////			}else{
////				map.put("chk_ext", "同住");
////			}
////			dList.add(map);
////		}
//		return gson.toJson(list);
//	}
	
	/** 续住保存操作
	 * <功能详细描述>
	 * @param map
	 * @return
	 * @see WardContinuedLiveAction.java
	 */
	@RequestMapping("/rooms/saveRoomToleave.do")
	public @ResponseBody Map saveRoomToleave(@RequestParam Map dmap){
		//返回值
		Map resultMap = new HashMap();
		try {
			resultMap.put("isSuccess", true);
			//夜审判断
			boolean flag = parameterSvc.getNightAuditState();
			if(flag){
				resultMap.put("isSuccess", false);
				resultMap.put("msg", "正在夜审不能进行续住操作！");
				return resultMap;
			}
			//map缓存flag
			Map<String, String> roomNumFlag = new HashMap<String, String>();
			roomNumFlag.put("0", "留房");
			roomNumFlag.put("1", "住房");
			roomNumFlag.put("2", "维修房");
			roomNumFlag.put("3", "冻结房");
			roomNumFlag.put("4", "网房");
			roomNumFlag.put("5", "网房预定");
			roomNumFlag.put("6", "网房留房");
			//去酒店日期
			SimpleDateFormat dateFormat2 = new SimpleDateFormat("yyyy-MM-dd");
			Date currentDate = parameterDao.GetHotelDate();
			currentDate = currentDate == null ? DateUtils.getCurrSysDate() : currentDate;
			currentDate = dateFormat2.parse(dateFormat2.format(currentDate));
			
			
			//需要保存的数据
			JSONArray jsonArray = JSONArray.parseArray(dmap.get("dataView").toString());
			
			//循环，若数据的room_id改变，则验证改变后的房间是否可用
			for (Object object : jsonArray) {
				JSONObject dataObj = (JSONObject)object;
				//如果酒店日期大于等于离开日期，不能转
				Date leaveDate = new Date();
				Date leaveDateOld = new Date();
				leaveDate = dateFormat2.parse(dataObj.getString("leave_date").trim());
				leaveDateOld = dateFormat2.parse(dataObj.getString("leave_date_old").trim());
			
				if(currentDate.compareTo(leaveDate) > 0){
					resultMap.put("msg", "住客"+dataObj.getString("gst_namec")+"的新离店日期【"+dataObj.getString("leave_date")+"】不能早于酒店日期【"+dateFormat2.format(currentDate)+"】。");
					resultMap.put("isSuccess", false);
					return resultMap;
				}
				String room_id = dataObj.getString("room_id").trim();
				if(leaveDateOld.compareTo(leaveDate) < 0){
					Calendar calendar = Calendar.getInstance();
					calendar.setTime(leaveDateOld);
					calendar.add(Calendar.DAY_OF_YEAR, 1);
					List list = roomNumDao.getRoomNumByRoomAndDate(room_id, calendar.getTime(), leaveDate);
					if(list.size() > 0){
						StringBuilder builder = new StringBuilder("");
						builder.append("房间号：");
						builder.append(room_id);
						for (int i = 0; i < list.size(); i++) {
							Map objs = (Map)list.get(i);
							builder.append("，在");
							builder.append(objs.get("reach_date").toString());
							builder.append("至");
							builder.append(objs.get("leave_date").toString());
							builder.append("期间有");
							builder.append(roomNumFlag.get(objs.get("flag").toString()));
						}
						builder.append("，不能续住。");
						
						resultMap.put("msg", builder.toString());
						resultMap.put("isSuccess", false);
						return  resultMap;
					}
				}
			}
			
			boolean if_bdate_flag = dmap.get("if_bdate_flag").equals("true") ? true : false;
			guestdocSvc.saveRoomToleave(jsonArray, if_bdate_flag, AppBaseCfg.getOperator());
			
			resultMap.put("isSuccess", true);
		} catch (ParseException e) {
			resultMap.put("isSuccess", false);
			resultMap.put("msg", "系统内部错误。");
			e.printStackTrace();
		} catch (Exception e) {
			resultMap.put("isSuccess", false);
			resultMap.put("msg", "系统内部错误。");
			e.printStackTrace();
		}
		return resultMap;
	}
//	public @ResponseBody Map saveRoomToleave(@RequestParam Map dmap){
//		Map map = new HashMap();
//		boolean flag;
//		flag = parameterSvc.getNightAuditState();
//		//flag为true正在夜审
//		if(!flag){
//			JSONArray jsonArray = (JSONArray) JSONObject.parse(dmap.get("dataView")+"");
//			guestdocSvc.updateRoomToleave(jsonArray);
//			map.put("isSuccess", true);
//		}else{
//			map.put("isSuccess", false);
//			map.put("msg", "正在夜审不能进行续住操作！");
//		}
//		return map;
//	}
	
	@RequestMapping("/rooms/hoteldate.do")
	 public @ResponseBody String GetHotelDate(){
		Date s=parameterSvc.GetHotelDate();
		SimpleDateFormat dateFormat2 = new SimpleDateFormat("yyyy-MM-dd");
		return dateFormat2.format(s);
	 }
}
