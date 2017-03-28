package com.cyw.mammoth.action;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
/**
 * 钟点房设置
 * @author Administrator
 *
 */
@Controller
@RequestMapping(value="/clockRoom")
public class ClockRoomAction extends BaseAction {

	
	@RequestMapping(value="/list.do")
	public String list(ModelMap model,Integer status){
		model.addAttribute("status", status);
		model.addAttribute("hoverType",5);
		model.addAttribute("firstHoverType", 2) ;
		model.addAttribute("listJson", null);
		return "syssetting/room/clockRoom_set";
	}
	
}
