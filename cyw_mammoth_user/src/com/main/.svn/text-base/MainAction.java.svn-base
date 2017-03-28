package com.main;


import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.cyw.common.util.DateUtils;
import com.cyw.mammoth.bean.Hbuilding;
import com.cyw.mammoth.service.GuestdocSvc;
import com.cyw.mammoth.service.HbuildingSvc;
import com.cyw.mammoth.service.HroomCharactersSvc;
import com.cyw.mammoth.service.HroomTypeSvc;
import com.cyw.mammoth.service.ParameterSvc;
import com.cyw.mammoth.service.RoomsSvc;
import com.cyw.mammoth.vo.RoomStatusVo;
import com.cyw.mammoth.vo.RoomsSearchVo;

@Controller
public class MainAction {
	private static final Logger log = LoggerFactory.getLogger(MainAction.class);
	@Autowired
	private RoomsSvc roomsSvc;// roomSvc房态服务
	@Autowired
	private ParameterSvc paramterSvc;// 公共参数类服务
	@Autowired
	private HroomTypeSvc hroomTypeSvc;
	@Autowired
	private GuestdocSvc guestdocSvc;
	@Autowired
	private HbuildingSvc hbuidingSvc;
	@Autowired
	private HroomCharactersSvc hroomCharactersSvc;

	@RequestMapping("main")
	public String main() {
		List list = roomsSvc.getAll();
		// System.out.println(list);
		log.info("进入首页！");
		return "redirect:/userCenter.do";
	}

	/**
	 * 跳转方法 设置跳转页面
	 * 
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	@RequestMapping("setting.do")
	public String setting() {
		return "redirect:/hroomPlan/list.do";
	}

	/**
	 * 跳转方法 房态图面
	 * 
	 * @param roomStatusvo房态条件
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	@SuppressWarnings({ "unused", "unchecked", "rawtypes" })
	@RequestMapping("rooms.do")
	public String rooms(Model model, RoomsSearchVo roomsSearchVo) {
		Long beginTime = System.currentTimeMillis();
		Date startDate = paramterSvc.GetHotelDate();
		model.addAttribute("startDate", startDate);
		// 如果查询条件是空，则将查询条件设置为默认值
		if (roomsSearchVo.getStartdate() == null) {
			roomsSearchVo = new RoomsSearchVo();
			// 如果没有设置 则默认为今天
			Calendar calendar = Calendar.getInstance();
			calendar.setTimeInMillis(startDate.getTime() + 24 * 60 * 60 * 1000);
			roomsSearchVo.setStartdate(DateUtils.getDate(startDate,
					"yyyy-MM-dd"));
			roomsSearchVo.setEnddate(DateUtils.getDate(calendar.getTime(),
					"yyyy-MM-dd"));
			roomsSearchVo.setIsAll(false);
		}
		//默认是入住登记查询
		Integer type = roomsSearchVo.getType();
		if(type==null){
			roomsSearchVo.setType(0);
			type=0;
		}
		if(type==1){
			roomsSearchVo.setIsAll(true);
		}
		// 是否含IP
		Boolean isIp = paramterSvc.GetIPFlag();
		roomsSearchVo.setIsIp(isIp);
		if (isIp)
			roomsSearchVo.setRadioroom("VCI");
		else
			roomsSearchVo.setRadioroom("VC");

		List<RoomStatusVo> list = roomsSvc.getRoomsListTypeState(roomsSearchVo);
		if(type==1){
			list = roomsSvc.fiterRooms(roomsSearchVo, list);
		}
		model.addAttribute("roomsStats", list);
		model.addAttribute("roomsSearchVo", roomsSearchVo);
		// 楼名 取正常状态的

		List<Hbuilding> hList = hbuidingSvc.getList("status", 0);

		model.addAttribute("hList", hList);
		// 是否含ip
		model.addAttribute("isIP", isIp);
		// 特征
		Long endTime = System.currentTimeMillis();
		log.info("执行时间=" + (endTime - beginTime));
		return "rooms/room_status";
	}
	@RequestMapping("userCenter.do")
	public String userCenter(Model model){
		return "user_center";
	} 

}
