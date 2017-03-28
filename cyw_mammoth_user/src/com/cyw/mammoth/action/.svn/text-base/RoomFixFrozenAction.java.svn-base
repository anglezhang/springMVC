package com.cyw.mammoth.action;

import java.util.Calendar;
import java.util.Date;
import java.util.List;






import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.cyw.common.util.DateUtils;
import com.cyw.mammoth.bean.Hbuilding;
import com.cyw.mammoth.bean.Hcodes;
import com.cyw.mammoth.bean.RoomNum;
import com.cyw.mammoth.service.HbuildingSvc;
import com.cyw.mammoth.service.HcodesSvc;
import com.cyw.mammoth.service.ParameterSvc;
import com.cyw.mammoth.service.RoomNumSvc;
import com.cyw.mammoth.service.RoomsSvc;
import com.cyw.mammoth.vo.RoomStatusVo;
import com.cyw.mammoth.vo.RoomsSearchVo;
import com.sun.java.swing.plaf.motif.resources.motif;

/**
 * @Comments: 维修冻结action
 * @author zhangzhenxing(zhangzhenxing@cyw.so)
 * @date 下午5:42:08
 * @version 1.0
 */

@Controller
@RequestMapping(value = "/roomFixFrozen")
public class RoomFixFrozenAction {
	/**
	 * @描述 共工参数svc
	 * */
	@Autowired
	private ParameterSvc parameterSvc;

	/**
	 * @描述 room svc
	 * */
	@Autowired
	private RoomsSvc roomSvc;

	/**
	 * @描述 建筑物 scv
	 * */
	@Autowired
	private HbuildingSvc hbuidingSvc;

	/**
	 * @描述 插入房态明细
	 * */
	@Autowired
	private RoomNumSvc roomNumSvc;
	/**
	 * @描述 代码查询
	 * */
	@Autowired
	private HcodesSvc hcodesSvc;

	@RequestMapping(value = "/fixFrozen.do")
	public String fixFrozen(Model model, RoomsSearchVo roomsSearchVo) {
		// 当前时间 和当前时间+1天
		Date startDate = parameterSvc.GetHotelDate();
		model.addAttribute("startDate", startDate);
		// 如果查询条件是空，则将查询条件设置为默认值
		if (roomsSearchVo.getStartdate() == null) {
			roomsSearchVo = new RoomsSearchVo();
			Calendar calendar = Calendar.getInstance();
			calendar.setTimeInMillis(startDate.getTime() + 24*60*60*1000);
			roomsSearchVo.setStartdate(DateUtils.getDate(startDate,"yyyy-MM-dd HH:mm:ss"));
			roomsSearchVo.setEnddate(DateUtils.getDate(calendar.getTime(), "yyyy-MM-dd HH:mm:ss"));
			roomsSearchVo.setIsAll(false);
		}
		List<RoomStatusVo> list = roomSvc.getFixFrozenRooms(roomsSearchVo);
		model.addAttribute("roomsStats", list);
		model.addAttribute("roomsSearchVo", roomsSearchVo);
		List<Hbuilding> hList = hbuidingSvc.getList("status", 0);
		model.addAttribute("hList", hList);
		return "fixAndFrozen/fixAndFrozen";
	}

	/**
	 * @描述 设为维修
	 * @param roomIds
	 *            房间ID
	 * @param starDate
	 *            开始日期
	 * @param endDate
	 *            结束日期
	 * */
	@RequestMapping(value = "/setFix/{roomIds}/{starDate}/{endDate}/{type}")
	public String setFixPage(Model model, @PathVariable String roomIds,
			@PathVariable String starDate, @PathVariable String endDate,
			@PathVariable String type) {
		// 保存 id 获取维修原因
		String[] ids = roomIds.split(",");
		model.addAttribute("ids", ids);
		model.addAttribute("starDate", starDate);
		model.addAttribute("endDate", endDate);
		List<Hcodes> codes = null;
		if("2".equalsIgnoreCase(type)){
			codes = hcodesSvc.getListByCodes("014");// 维修原因
			model.addAttribute("reason", "维修原因");
		}else if("3".equalsIgnoreCase(type)){
			codes = hcodesSvc.getListByCodes("015");// 冻结原因
			model.addAttribute("reason", "冻结原因");
		}
		model.addAttribute("type", type);
		model.addAttribute("codes", codes);
		return "fixAndFrozen/setFix";
	}

	/**
	 * @描述 将房间状态设置维修冻结
	 * */
	@RequestMapping(value = "/setFixRoomNum.do")
	@ResponseBody
	public String setFixRoomNum(@RequestParam("roomIds") String roomIds,
			@RequestParam("strat") String starDate,
			@RequestParam("end") String endDate,
			@RequestParam("reanson") String reason
			,@RequestParam("type") String type) {
		if (roomNumSvc.setFix(roomIds, starDate, endDate, reason,type)) {
			return "true";
		}
		return "false";
	}
	
	/**
	 * @描述 维修冻结详情
	 * */
	@RequestMapping(value="getFixFrozenInfo/{roomId}/{type}/{active}/{startDate}/{endDate}")
	public String getFixFrozenInfo(Model model,@PathVariable String roomId
			,@PathVariable String type,@PathVariable String active
			,@PathVariable String startDate
			,@PathVariable String endDate){
		List<RoomNum> list = roomNumSvc.getFixFrozenInf(roomId, type, active, startDate, endDate,model);
		String guestJson = JSON.toJSONStringWithDateFormat(list, "yyyy-MM-dd HH:mm:ss", SerializerFeature.WriteDateUseDateFormat);
		if("null".equals(roomId)){
			roomId = "";
		}
		Date beginDate = parameterSvc.GetHotelDate();
		model.addAttribute("beginDate", beginDate);
		model.addAttribute("roomId", roomId);
		model.addAttribute("list", guestJson);
		model.addAttribute("startDate", startDate);
		model.addAttribute("endDate", endDate);
		return "fixAndFrozen/fixFrozenInf";
	}
	
	/**
	 * @描述 更新gird
	 * */
	@RequestMapping(value="updateFixFrozenInfo/{roomId}/{type}/{active}/{startDate}/{endDate}")
	@ResponseBody
	public JSONArray updateFixFrozenInfo(Model model,@PathVariable String roomId
			,@PathVariable String type,@PathVariable String active
			,@PathVariable String startDate
			,@PathVariable String endDate){
		List<RoomNum> list = roomNumSvc.getFixFrozenInf(roomId, type, active, startDate, endDate,model);
		String guestJson = JSON.toJSONStringWithDateFormat(list, "yyyy-MM-dd HH:mm:ss", SerializerFeature.WriteDateUseDateFormat);
		return JSON.parseArray(guestJson);
	}
		
	/**
	 * @描述 取消维修冻结 
	 * */
	@RequestMapping(value="cancleFixfronze.do")
	@ResponseBody
	public String cancleFixfronze(List<RoomNum> rooms,String cancleIds){
//		RoomNum roomNum = roomNumSvc.get(id);
//		roomNum.setStatus(1);
//		roomNumSvc.update(roomNum);
		return "true";
	}
	
	/**
	 * @描述 修改维修冻结
	 * */
	@RequestMapping(value="editFixfrozen/{cancelIds}")
	@ResponseBody
	public String editFixfrozen(String roomNum,@PathVariable String cancelIds){
		JSONArray array = new JSONArray();
		if(StringUtils.isNotEmpty(roomNum)){
			array = JSONObject.parseArray(roomNum);
		}
		if(roomNumSvc.editFixfrozen(array, cancelIds)){
			return "true";
		}
		return "false";
	}
	
	/**
	 * @描述
	 * @param type维修 or冻结 2维修,3冻结
	 * */
	@RequestMapping(value="getFixReason/{type}")
	@ResponseBody
	public List<Hcodes> getFixReason(@PathVariable String type){
		List<Hcodes> codes = null;
		if("2".equalsIgnoreCase(type)){
			codes = hcodesSvc.getListByCodes("014");// 维修原因
			
		}else if("3".equalsIgnoreCase(type)){
			codes = hcodesSvc.getListByCodes("015");// 冻结原因
		}
		return codes;
	}
}
