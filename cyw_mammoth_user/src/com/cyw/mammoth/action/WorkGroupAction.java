package com.cyw.mammoth.action;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.cyw.common.util.CommonUtil;
import com.cyw.mammoth.auth.AppBaseCfg;
import com.cyw.mammoth.bean.HfunctionWorkgroupList;
import com.cyw.mammoth.bean.WorkGroup;
import com.cyw.mammoth.service.WorkGroupSvc;
import com.cyw.mammoth.vo.HfunctionVO;
import com.cyw.mammoth.vo.WorkGroupVO;

@Controller
@RequestMapping(value="/workGroup")
public class WorkGroupAction {
	
	@Autowired
	private WorkGroupSvc workGroupSvc ;

	@RequestMapping("/list.do")
	public String list(ModelMap model , Integer status ) throws Exception{
		model.addAttribute("hoverType",2);
		model.addAttribute("status", status);
		List<WorkGroupVO> list = workGroupSvc.findListBy(status) ;
		model.addAttribute("listJson", JSON.toJSONString(list));
		return "syssetting/permission/workGroup/workGroup_list";
	}
	
	@RequestMapping("workGroupAuthList.do")
	public ModelAndView workGroupAuthList(ModelMap model , String groupId) throws Exception{
		ModelAndView mav=new ModelAndView("syssetting/permission/workGroup/workGroup_auth");
		mav.addObject("functionTypeDataList", JSON.toJSONString(AppBaseCfg.hfunctioGroupListy));
		mav.addObject("groupId", groupId);
		return mav;
	}
	
	@ResponseBody
	@RequestMapping(value="/findFunctionListBy.do",method=RequestMethod.POST)
	public String findFunctionListBy(ModelMap modelMap ,String funcType , String groupId) throws Exception{
		JSONObject jsonObj =  new JSONObject();
		List<HfunctionVO> list = workGroupSvc.findFunctionListBy(funcType , groupId) ;
		jsonObj.put("listJson", JSON.toJSONString(list));
		jsonObj.put("success", true);
		return jsonObj.toJSONString();
	}
	
	/**
	 * 
	 * @param params   编辑的实体json
	 * @param editFlag 编辑状态   'u' 修改   'a' 新增 
	 * @param delIds   被删除的ids
	 * @return
	 * @throws Exception 
	 */
	@ResponseBody
	@RequestMapping("/saveOrUpdateOrDel.do")
	public String saveOrUpdateOrDel(String params, String delIds) throws Exception{
		JSONObject jsonObj =  new JSONObject();
		List<WorkGroup> roomDefineVos = JSON.parseArray(params , WorkGroup.class) ;
		workGroupSvc.saveOrUpdateOrDel(roomDefineVos , delIds);
		jsonObj.put("success", true);
		return jsonObj.toJSONString();
	}
	@ResponseBody
	@RequestMapping("/saveWorkGroupAuth.do")
	public String saveWorkGroupAuth(String wgAuthJson , String workGroupId , String hfunctionGroup) throws Exception{
		JSONObject jsonObj =  new JSONObject();
		List<HfunctionWorkgroupList> roomDefineVos = JSON.parseArray(wgAuthJson , HfunctionWorkgroupList.class) ;
		workGroupSvc.saveWorkGroupAuth(roomDefineVos , workGroupId , hfunctionGroup);
		jsonObj.put("success", true);
		return jsonObj.toJSONString();
	}
	
	@ResponseBody
	@RequestMapping("/updateStatus.do")
	public String updateStatus(String backIds) throws Exception{
		JSONObject jsonObj =  new JSONObject();
		workGroupSvc.updateStatusById((StringUtils.isNotBlank(backIds) ? CommonUtil.array_unique(backIds.split(",")) : null)  , 0 );
		jsonObj.put("success", true);
		return jsonObj.toJSONString();
	}
	/**
	 * 查询对象是否存在
	 * @param params 实体json
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/findBy.do")
	public String findBy(String id , String codeId){
		JSONObject jsonObj =  new JSONObject();
		WorkGroup entity = workGroupSvc.get("groupId",codeId) ;
		if(StringUtils.isBlank(id)){
			if(entity !=null){
				jsonObj.put("success", false);
			}else{
				jsonObj.put("success", true);
			}
		}else{
			if(entity !=null && !id.equals(entity.getGroupId())){
				jsonObj.put("success", false);
			}else{
				jsonObj.put("success", true);
			}
		}
		return jsonObj.toJSONString();
	}
	
}
