package com.cyw.mammoth.action;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.cyw.common.util.CommonUtil;
import com.cyw.mammoth.bean.Hbuilding;
import com.cyw.mammoth.service.HbuildingSvc;
import com.cyw.mammoth.service.ParameterSvc;
import com.cyw.mammoth.vo.HbuildingVo;


/**
 * 建筑物定义
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/building")
public class HbuildingAction extends BaseAction {
	@Autowired
	private HbuildingSvc buildingSvc;
	@Autowired
	private ParameterSvc parameterSvc ;
	/** 
	 * 加载建筑物列表
	 * @return
	 */
	@RequestMapping("/list.do")
	public String list(ModelMap model,Integer status,Integer hoverType){
		List<HbuildingVo> buildList=(List<HbuildingVo>) buildingSvc.getBuildList(status==null?0:status);
		model.addAttribute("buildList", buildList);
		model.addAttribute("status", status);
		model.addAttribute("hoverType",1);
		model.addAttribute("buildListJson", JSON.toJSONString(buildList));
		return "syssetting/room/building";
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
		buildingSvc.updateStatusById((StringUtils.isNotBlank(backIds) ? CommonUtil.array_unique(backIds.split(",")) : null)  , 0 );
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
	public String saveOrUpdateOrDel(String params , String delIds){
		JSONObject jsonObj =  new JSONObject();
		if(!parameterSvc.getNightAuditState()){
			List<Hbuilding> hbuildings = JSON.parseArray(params, Hbuilding.class) ;
			buildingSvc.saveOrUpdateOrDel(hbuildings , delIds , getSessionUser().getOperId());
			jsonObj.put("success", true);
		}else{
			jsonObj.put("success", false);
		}
		return jsonObj.toJSONString();
	}
	@ResponseBody
	@RequestMapping("/findById.do")
	public String findById(@RequestParam Integer id){
		JSONObject jsonObj =  new JSONObject();
		Hbuilding hbuilding = buildingSvc.get(id) ;
		if(hbuilding !=null){
			jsonObj.put("success", true);
			jsonObj.put("val", hbuilding);
		}else{
			jsonObj.put("success", false);
			jsonObj.put("msg", "数据不存在");
		}
		return jsonObj.toJSONString();
	}
	/** 
	 * 删除建筑物信息
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	@ResponseBody
	@RequestMapping("/del.do")
	public String del(String[] ids){
		JSONObject jsonObj =  new JSONObject();
		buildingSvc.updateStatusById(ids,1);
		jsonObj.put("success", true);
		return jsonObj.toJSONString();
	}
	@RequestMapping("/call.do")
	public String getCallTest(ModelMap model){
		List<?> list=buildingSvc.getPrepareCall();
		model.addAttribute("list", list);
		return "syssetting/call";
	}
	/**
	 * 查询对象是否存在
	 * @param params 实体json
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/findBy.do")
	public String findBy(Integer id , String codeId){
		JSONObject jsonObj =  new JSONObject();
		Hbuilding entity = buildingSvc.get("codeId",codeId) ;
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
