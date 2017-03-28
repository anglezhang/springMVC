package com.cyw.mammoth.action;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.cyw.mammoth.bean.Fguest;
import com.cyw.mammoth.service.FguestSvc;
import com.cyw.mammoth.vo.FguestInfoVO;
import com.cyw.mammoth.vo.FguestSearchVo;
import com.cyw.mammoth.vo.FguestVo;

/**
 * @Comments:  熟客查询功能Action
 * @author zhangzhenxing(zhangzhenxing@cyw.so)
 * @date 下午2:47:49
 * @version 1.0
 */
@Controller
@RequestMapping(value = "/fguest")
public class FguestSearchAction extends BaseAction{
	
	@Autowired
	private FguestSvc fguestSvc;
	/**
	 * @描述 熟客查询
	 * */
	@RequestMapping(value="/searchFguest.do")
	public String searchFguest(Model model){
		return "rooms/room_fguest";
	}
	
	/**
	 * @描述 获取熟客信息
	 * */
	@RequestMapping(value="/getFguest.do")
	@ResponseBody
	public List<FguestInfoVO> getFguest(FguestSearchVo fguestVo){
		List<FguestInfoVO> list = fguestSvc.getFguestList(fguestVo);
		return list;
	}
}
