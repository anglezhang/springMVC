package com.cyw.mammoth.action;

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
import com.cyw.mammoth.bean.HroomCharacters;
import com.cyw.mammoth.service.HroomCharactersSvc;
import com.cyw.mammoth.service.ParameterSvc;
import com.cyw.mammoth.vo.HroomCharactersVO;

/**
 * @author Administrator
 * 房间特征管理
 */
@Controller
@RequestMapping("/hroomCharacters")
public class HroomCharactersAction extends BaseAction  {

	@Autowired
	private HroomCharactersSvc hroomCharactersSvc ;
	@Autowired
	private ParameterSvc parameterSvc ;
	/** 房间特征定义列表
	 * @return
	 * @see HroomCharactersAction.java
	 */
	@RequestMapping("/list.do")
	public String index(ModelMap model,Integer status){
		List<HroomCharactersVO> list = hroomCharactersSvc.findListBy(status) ;
		model.addAttribute("hoverType",3);
		model.addAttribute("status",status);
		model.addAttribute("listJson", JSON.toJSONString(list));
		return "syssetting/room/rooms_character_set";
	}
	@ResponseBody
	@RequestMapping("/saveOrUpdateOrDel.do")
	public String saveOrUpdateOrDel(String params , String delIds){
		JSONObject jsonObj =  new JSONObject();
		if(parameterSvc.getNightAuditState()){
			jsonObj.put("success", false);
		}else{
			List<HroomCharacters> hroomCharacterss = JSON.parseArray(params, HroomCharacters.class) ;
			hroomCharactersSvc.saveOrUpdateOrDel(hroomCharacterss,delIds);
			jsonObj.put("success", true);
		}
		return jsonObj.toJSONString();
	}
	@ResponseBody
	@RequestMapping("/updateStatus.do")
	public String updateStatus(String backIds){
		JSONObject jsonObj =  new JSONObject();
		if(parameterSvc.getNightAuditState()){
			jsonObj.put("success", false);
		}else{
			hroomCharactersSvc.updateStatusById((StringUtils.isNotBlank(backIds) ? CommonUtil.array_unique(backIds.split(",")) : null)  , 0 );
			jsonObj.put("success", true);
		}
		return jsonObj.toJSONString();
	}
	
	@ResponseBody
	@RequestMapping("/findListBy.do")
	public String findListBy() throws Exception{
		return JSON.toJSONString(hroomCharactersSvc.getList("status", 0));
	}
	/**
	 * 查询对象是否存在
	 * @param params 实体json
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/findBy.do")
	public String findBy(Integer id , Integer codeId){
		JSONObject jsonObj =  new JSONObject();
		HroomCharacters entity = hroomCharactersSvc.get("placeholderId",codeId) ;
		if(id == null){
			if(entity !=null){
				jsonObj.put("success", false);
			}else{
				jsonObj.put("success", true);
			}
		}else{
			if(entity !=null && id.intValue() !=entity.getId().intValue()){
				jsonObj.put("success", false);
			}else{
				jsonObj.put("success", true);
			}
		}
		return jsonObj.toJSONString();
	}
	
}