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
import com.cyw.mammoth.bean.Hexchange;
import com.cyw.mammoth.service.HexchangeSvc;
import com.cyw.mammoth.service.ParameterSvc;
/**
 * 特别代码--汇率 控制层
 * @author Administrator
 *
 */
@Controller
@RequestMapping(value="hexchange")
public class HexchangeAction extends BaseAction {

	@Autowired
	private HexchangeSvc hexchangeSvc ;
	@Autowired
	private ParameterSvc parameterSvc ;
	
	/**
	 * 汇率特别代码列表
	 * @param modelMap 
	 * @param status 状态  0有效 1无效 
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value="/list.do",method=RequestMethod.POST)
	public String list(ModelMap modelMap ,Integer status) throws Exception{
		JSONObject jsonObj =  new JSONObject();
		List<Hexchange> list = hexchangeSvc.getList("status", status);
		jsonObj.put("listJson", JSON.toJSONString(list));
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
			hexchangeSvc.updateStatusById((StringUtils.isNotBlank(backIds) ? CommonUtil.array_unique(backIds.split(",")) : null)  , 0 );
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
				List<Hexchange> hexchanges = JSON.parseArray(params, Hexchange.class) ;
				hexchangeSvc.saveOrUpdateOrDel(hexchanges , delIds);
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
		Hexchange entity = hexchangeSvc.get("codeId",codeId) ;
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
