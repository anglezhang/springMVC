package com.cyw.mammoth.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.cyw.common.util.CommonUtil;
import com.cyw.mammoth.auth.AppBaseCfg;
import com.cyw.mammoth.auth.AppBaseCfg.HfunctioCtrlType;
import com.cyw.mammoth.auth.AppBaseCfg.HfunctioGroup;
import com.cyw.mammoth.service.HfunctionSvc;
import com.cyw.mammoth.service.ParameterSvc;
import com.cyw.mammoth.vo.HfunctionVO;

/**
 * @author Administrator
 * 系统功能管理
 */
@Controller
@RequestMapping("/hfunction/*")
public class HfunctionAction {
	@SuppressWarnings("unused")
	private static final Logger log = LoggerFactory.getLogger(HfunctionAction.class);
	
	@Autowired
	private HfunctionSvc hfunctionSvc;
	@Autowired
	private ParameterSvc parameterSvc ;
    
    /** 
	 * 跳转至用戶管理界面
	 * @param 用戶管理
	 * @param searchVo
	 * @return
     * @throws Exception 
	 */
	@RequestMapping("list.do")
	public ModelAndView list(ModelMap model,Integer status) throws Exception{
		ModelAndView mav=new ModelAndView("syssetting/permission/hfunction/hfunction_list");
		List<HfunctionVO> list = hfunctionSvc.findListBy(status);
		mav.addObject("hoverType", 1);
		mav.addObject("status", status);
		mav.addObject("listJson", JSON.toJSONString(list));
		return mav;
	}
	@RequestMapping("hfunctionControlList.do")
	public ModelAndView hfunctionControlList(ModelMap model) throws Exception{
		ModelAndView mav=new ModelAndView("syssetting/permission/hfunction/hfunction_control_list");
		Map<String, Object> map ;
		List<HfunctioCtrlType> controlTypeList = AppBaseCfg.hfunctionCtrlTypeListy;
		List<Map<String, Object>> controlTypeMaps = new ArrayList<Map<String, Object>>();
		for(HfunctioCtrlType hfunctioCtrlType : controlTypeList){
			map = new HashMap<String, Object>();
			map.put("codeId", hfunctioCtrlType.getCode()) ;
			map.put("codeName", hfunctioCtrlType.getName()) ;
			controlTypeMaps.add(map);
		}
		mav.addObject("controlDataMap", JSON.toJSONString(controlTypeMaps));
		
		List<HfunctioGroup> functionTypeList = AppBaseCfg.hfunctioGroupListy;
		List<Map<String, Object>> controlTypeMapsTypeMaps = new ArrayList<Map<String, Object>>();
		for(HfunctioGroup hfunctioGroup : functionTypeList){
			map = new HashMap<String, Object>();
			map.put("codeId", hfunctioGroup.getCode()) ;
			map.put("codeName", hfunctioGroup.getName()) ;
			controlTypeMapsTypeMaps.add(map);
		}
		mav.addObject("functionTypeDataMap", JSON.toJSONString(controlTypeMapsTypeMaps));
		return mav;
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
		List<HfunctionVO> vos = JSON.parseArray(params,HfunctionVO.class);
		
		if(!parameterSvc.getNightAuditState()){
			hfunctionSvc.saveOrUpdateOrDel(vos , delIds);
			jsonObj.put("success", true);
		}else{
			jsonObj.put("success", false);
		}
		return jsonObj.toJSONString();
	}
	
	@ResponseBody
	@RequestMapping("/updateStatus.do")
	public String updateStatus(String backIds) throws Exception{
		JSONObject jsonObj =  new JSONObject();
		hfunctionSvc.updateStatusById((StringUtils.isNotBlank(backIds) ? CommonUtil.array_unique(backIds.split(",")) : null)  , 0 );
		jsonObj.put("success", true);
		return jsonObj.toJSONString();
	}
}
