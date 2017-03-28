package com.cyw.mammoth.action;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.cyw.common.util.CommonUtil;
import com.cyw.mammoth.bean.Holidays;
import com.cyw.mammoth.cywenum.HcodesEnumType;
import com.cyw.mammoth.service.CodeCareSvc;
import com.cyw.mammoth.service.HolidaysSvc;
import com.cyw.mammoth.service.ParameterSvc;

/**
 * @author Administrator
 * 节假日设置管理
 */
@Controller
@RequestMapping(value="/holidays")
public class HolidaysAction extends BaseAction {
	
	@Autowired
	private HolidaysSvc holidaysSvc ;
	@Autowired
	private ParameterSvc parameterSvc ;
	@Autowired
	private CodeCareSvc codeCareSvc ;
	 
	
	/**
	 * 查询列表
	 * @param model
	 * @param status
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value="/list.do")
	public String list(ModelMap model,Integer status) throws Exception{
		List<Holidays> holidaysList=holidaysSvc.findListBy(status);
		model.addAttribute("status", status);
		model.addAttribute("hoverType",6);
		model.addAttribute("firstHoverType", 2) ;
		model.addAttribute("listJson", JSON.toJSONString(holidaysList));
		
		// 节假日代码
		model.addAttribute("hcodes", JSON.toJSONString(codeCareSvc.findAllListBy(HcodesEnumType.HCODE_HOLIDAYS.getValue())));
		// 星期
		model.addAttribute("week", Arrays.asList(parameterSvc.findWeek().getPara2Name().split(",")));
		return "syssetting/room/holidays_set";
	}
	/**
	 * 修改实体状态
	 * @param backIds
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/updateStatus.do")
	public String updateStatus(String backIds){
		JSONObject jsonObj =  new JSONObject();
		holidaysSvc.updateStatusById((StringUtils.isNotBlank(backIds) ? CommonUtil.array_unique(backIds.split(",")) : null)  , 0 );
		jsonObj.put("success", true);
		return jsonObj.toJSONString();
	}
	/**
	 * 批量新增，修改，删除实体
	 * @param params 实体json
	 * @param delIds 删除的ids
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/saveOrUpdateOrDel.do")
	public String saveOrUpdateOrDel(String params , String delIds , String week){
		JSONObject jsonObj =  new JSONObject();
		if(!parameterSvc.getNightAuditState()){
			List<Holidays> holiday = JSON.parseArray(params, Holidays.class) ;
			holidaysSvc.saveOrUpdateOrDel(holiday , delIds , week , getSessionUser().getOperId());
			jsonObj.put("success", true);
		}else{
			jsonObj.put("success", false);
		}
		return jsonObj.toJSONString();
	}
	/**
	 * 查询对象是否存在
	 * @param params 实体json
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/findBy.do")
	public String findBy(Integer id , String holidayDate){
		JSONObject jsonObj =  new JSONObject();
		Holidays hday = holidaysSvc.get(new String[]{"holidayDate" , "status"}, new Object[]{holidayDate , 0}) ;
		if(id == null){
			if(hday !=null){
				jsonObj.put("success", false);
			}else{
				jsonObj.put("success", true);
			}
		}else{
			if(hday !=null && id.intValue() !=hday.getId().intValue()){
				jsonObj.put("success", false);
			}else{
				jsonObj.put("success", true);
			}
		}
		return jsonObj.toJSONString();
	}
	
	
}
