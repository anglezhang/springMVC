package com.cyw.mammoth.action;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.cyw.common.util.CommonUtil;
import com.cyw.mammoth.bean.Hcodes;
import com.cyw.mammoth.service.CodeCareSvc;
import com.cyw.mammoth.service.ParameterSvc;
/**
 * 通用代码维护控制层
 * @author Administrator
 *
 */
@Controller
@RequestMapping(value="/codeCare")
public class CodeCareAction extends BaseAction {
	
	@Autowired
	private CodeCareSvc codeCareSvc ;
	@Autowired
	private ParameterSvc parameterSvc ;
	/**
	 * 获取通用代码类别列表
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/geTypeList.do")
	public String geTypeList() throws Exception{
		List<Hcodes> list = codeCareSvc.findGETypeList() ;
		return JSON.toJSONString(list);
	}
	/**
	 * 列表
	 * @param modelMap 
	 * @param type SE=特别代码  GE=通用代码
	 * @param flag  子类的标识
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/list.do")
	public String list(ModelMap modelMap , String type , String flag) throws Exception{
		return "syssetting/codeCare/codeCareList" ;
	}
	
	/**
	 * 通用代码列表
	 * @param modelMap 
	 * @param type SE=特别代码  GE=通用代码
	 * @param flag  子类的标识
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value="/sglist.do",method=RequestMethod.POST)
	public String sglist(ModelMap modelMap ,Integer status , String flag) throws Exception{
		JSONObject jsonObj =  new JSONObject();
		if(StringUtils.isNotBlank(flag)){
			jsonObj.put("listJson", JSON.toJSONString(codeCareSvc.findGEListBy(flag,status)));
		}
		jsonObj.put("status", status);
		return jsonObj.toJSONString();
	}
	/**
	 * 修改实体状态
	 * @param backIds
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/updateStatus.do",method=RequestMethod.POST)
	public String updateStatus(String backIds) throws Exception{
		JSONObject jsonObj =  new JSONObject();
		if(!parameterSvc.getNightAuditState()){
			codeCareSvc.updateStatusById((StringUtils.isNotBlank(backIds) ? CommonUtil.array_unique(backIds.split(",")) : null)  , 0 );
			jsonObj.put("success", true);
		}else{
			jsonObj.put("success", false);
		}
		return jsonObj.toJSONString();
	}
	/**
	 * 批量新增，修改，删除实体
	 * @param params 实体json
	 * @param delIds 删除的ids
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/saveOrUpdateOrDel.do",method=RequestMethod.POST)
	public String saveOrUpdateOrDel(String params , String delIds) throws Exception{
		JSONObject jsonObj =  new JSONObject();
		try {
			if(!parameterSvc.getNightAuditState()){
				List<Hcodes> hcodes = JSON.parseArray(params, Hcodes.class) ;
				codeCareSvc.saveOrUpdateOrDel(hcodes , delIds);
				jsonObj.put("success", true);
			}else{
				jsonObj.put("success", false);
				jsonObj.put("msg", "夜审中");
			}
		} catch (Exception e) {
			jsonObj.put("success", false);
			jsonObj.put("msg", "出现异常");
			e.printStackTrace();
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
	public String findBy(Integer id , String codeId){
		JSONObject jsonObj =  new JSONObject();
		Hcodes entity = codeCareSvc.get("codeId",codeId) ;
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
