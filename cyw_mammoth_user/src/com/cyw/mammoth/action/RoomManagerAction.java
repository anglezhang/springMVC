package com.cyw.mammoth.action;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.cyw.common.util.DateUtils;
import com.cyw.mammoth.bean.Hbuilding;
import com.cyw.mammoth.service.GuestdocSvc;
import com.cyw.mammoth.service.HbuildingSvc;
import com.cyw.mammoth.service.ParameterSvc;
import com.cyw.mammoth.service.RoomsSvc;
import com.cyw.mammoth.vo.RoomStatusVo;
import com.cyw.mammoth.vo.RoomsSearchVo;

/**
 * @Comments:  房态管理
 * @author zhangzhenxing(zhangzhenxing@cyw.so)
 * @date 上午9:45:12
 * @version 1.0
 */
@Controller
@RequestMapping(value = "/roomsManager")
public class RoomManagerAction extends BaseAction{
	
	/**
	 * @描述 取公共服务参数
	 * */
	@Autowired
	private ParameterSvc parameterSvc;
	
	/**
	 * @描述 房间服务
	 * */
	@Autowired
	private RoomsSvc roomSvc;
	
	/**
	 * @描述 建筑物管理
	 * */
	@Autowired
	private HbuildingSvc hbuidingSvc;
	
	/**
	 * @描述 住客资料svc
	 * */
	@Autowired
	private GuestdocSvc guestDocSvc;
	
	
	/**
	 * @author zhangzhenxing
	 * @描述 房态管理初始
	 * */
	@RequestMapping(value="/manager.do")
	public String roomStatManager(Model model,RoomsSearchVo roomsSearchVo){
		Date startDate = parameterSvc.GetHotelDate();
		model.addAttribute("startDate",startDate);
		roomsSearchVo.setStartdate(DateUtils.getDate(startDate,"yyyy-MM-dd HH:mm:ss"));
		model.addAttribute("roomsSearchVo", roomsSearchVo);
		
		List<RoomStatusVo> list = roomSvc.getManagersRooms(roomsSearchVo);
		model.addAttribute("roomsStats", list);
		//楼名 取正常状态的

		List<Hbuilding> hList = hbuidingSvc.getList("status", 0);
		model.addAttribute("hList", hList);
		return "roomsManager/roomstat";
	}
	
	/**
	 * @描述 ：输入房号 敲击回车 快速查询
	 * */
	@RequestMapping(value="/fastsearch.do")
	public String fastsearch(Model model,@RequestParam("roomId") String roomId,RoomsSearchVo roomsSearchVo){
		Date startDate = parameterSvc.GetHotelDate();
		RoomsSearchVo searchVo = new RoomsSearchVo();
		
		model.addAttribute("roomsSearchVo", roomsSearchVo);
		searchVo.setStartdate(DateUtils.getDate(startDate,"yyyy-MM-dd HH:mm:ss"));
		List<RoomStatusVo> list = roomSvc.getFastRooms(searchVo, roomId);
		model.addAttribute("roomsStats", list);
		
		List<Hbuilding> hList = hbuidingSvc.getList("status", 0);
		model.addAttribute("hList", hList);
		model.addAttribute("roomId", roomId);
		return "roomsManager/roomstat";
	}
	
	/**
	 * @描述 打开不可售原因界面
	 * */
	@RequestMapping(value="/openNoSalePannel.do")
	public String openNoSalePannel(Model model,@RequestParam("roomId") String roomId
			,@RequestParam("startDate") String startDate
			,@RequestParam("endDate") String endDate
			,@RequestParam("typeName") String typeName){
		List<Map<String, String>> list = guestDocSvc.getRoomNoSaleInfo(roomId, startDate, endDate);
		model.addAttribute("list", list);
		model.addAttribute("roomId", roomId);
		model.addAttribute("typeName", typeName);
		return "rooms/room_noneSale";
	}
}
