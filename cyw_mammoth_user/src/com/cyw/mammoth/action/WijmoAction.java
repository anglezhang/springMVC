package com.cyw.mammoth.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.cyw.mammoth.bean.Rooms;
import com.cyw.mammoth.service.RoomsSvc;
import com.cyw.mammoth.vo.AjaxJson;
import com.cyw.mammoth.vo.RoomType;
import com.google.gson.Gson;

@Controller
public class WijmoAction {
	
	@Autowired
	private RoomsSvc roomsSvc;
	
	@RequestMapping("/wijmoAction/index.do")
	public String gowijmoIndex(ModelMap model){
		List<Rooms> list = roomsSvc.getAll();
		model.addAttribute("guestList", list);
		Gson gson= new Gson();
		String object=gson.toJson(list);
		model.addAttribute("object", object);
		
		List<RoomType> childLists = new ArrayList<RoomType>();
		RoomType rt ;
		//创造字表对象
		for(int i=1;i<10;i++){
			rt = new RoomType();
			rt.setId("00"+i);
			rt.setTypeName("类型"+i);
			childLists.add(rt);
		}
		String childData = JSON.toJSONString(childLists);
		model.addAttribute("childData", childData);
		
		return "testWijmo/test";
	}
	
	@ResponseBody
	@RequestMapping("/wijmoAction/getAllList.do")
	public List<Rooms> getAllList(){
		List<Rooms> list = roomsSvc.getAll();
		return list;
	}

	public AjaxJson add(){
		AjaxJson j = new AjaxJson();
		
		return j;
	}
	
}
