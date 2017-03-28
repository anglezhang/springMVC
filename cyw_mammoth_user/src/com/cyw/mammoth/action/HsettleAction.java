package com.cyw.mammoth.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.cyw.mammoth.bean.Hexchange;
import com.cyw.mammoth.bean.HsetlKind;
import com.cyw.mammoth.bean.Hsettle;
import com.cyw.mammoth.service.HexchangeSvc;
import com.cyw.mammoth.service.HsetlKindSvc;
import com.cyw.mammoth.service.HsettleSvc;
import com.cyw.mammoth.service.ParameterSvc;

@Controller
@RequestMapping(value="/hsettle")
public class HsettleAction extends BaseAction  {
	
	@Autowired
	private HsettleSvc hsettleSvc ;
	@Autowired
	private HsetlKindSvc hsetlKindSvc ;
	@Autowired
	private HexchangeSvc hexchangeSvc ;

	@Autowired
	private ParameterSvc parameterSvc ;
	/**
	 * 代码列表
	 * @param modelMap 
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value="/list.do",method=RequestMethod.POST)
	public String list(ModelMap modelMap ,Integer status) throws Exception{
		JSONObject jsonObj =  new JSONObject();
		JSONObject jsonObjVal =  new JSONObject();
		List<Hsettle> list = hsettleSvc.findListBy(status);
		jsonObjVal.put("listJson", JSON.toJSONString(list));
		jsonObj.put("status", status);
		
		// 付款方式类型
		List<HsetlKind> hsetlKinds = hsetlKindSvc.getList("status" , 0) ;
		List<Map<String, Object>> hsetlKindCode = new ArrayList<Map<String, Object>>();
		Map<String, Object> map ;
		for(HsetlKind hsetlKind : hsetlKinds){
			map = new HashMap<String, Object>();
			map.put("codeId", hsetlKind.getCodeId()) ;
			map.put("codeName", hsetlKind.getCodeNamec()) ;
			hsetlKindCode.add(map);
		}
		jsonObjVal.put("hsetlKinds", JSON.toJSONString(hsetlKindCode));
		
		// 币种代码
		List<Hexchange> hexchanges = hexchangeSvc.getList("status" , 0) ;
		List<Map<String, Object>> hexchangeCode = new ArrayList<Map<String, Object>>();
		for(Hexchange hexchange : hexchanges){
			map = new HashMap<String, Object>();
			map.put("codeId", hexchange.getCodeId()) ;
			map.put("codeName", hexchange.getCodeNamec()) ;
			hexchangeCode.add(map);
		}
		jsonObjVal.put("hexchanges", JSON.toJSONString(hexchangeCode));
		
		jsonObj.put("listJson", JSON.toJSONString(jsonObjVal));
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
			hsettleSvc.updateStatusById((StringUtils.isNotBlank(backIds) ? CommonUtil.array_unique(backIds.split(",")) : null)  , 0 );
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
				List<Hsettle> hsetltles = JSON.parseArray(params, Hsettle.class) ;
				hsettleSvc.saveOrUpdateOrDel(hsetltles , delIds);
				jsonObj.put("success", true);
				jsonObj.put("msg", "操作成功");
			}else{
				jsonObj.put("success", false);
				jsonObj.put("msg", "夜审中");
			}
		} catch (Exception e) {
			e.printStackTrace();
			jsonObj.put("success", false);
			jsonObj.put("msg", "出现异常");
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
		Hsettle entity = hsettleSvc.get("codeId",codeId) ;
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
