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
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.cyw.common.util.CommonUtil;
import com.cyw.mammoth.bean.HroomPlan;
import com.cyw.mammoth.bean.HroomType;
import com.cyw.mammoth.cywenum.HcodesEnumType;
import com.cyw.mammoth.service.CodeCareSvc;
import com.cyw.mammoth.service.HroomPlanSvc;
import com.cyw.mammoth.service.HroomTypeSvc;
import com.cyw.mammoth.service.ParameterSvc;
import com.cyw.mammoth.vo.HroomPlanVo;

@Controller
@RequestMapping(value="/hroomPlan")
public class HroomPlanAction extends BaseAction{
	
	@Autowired
	private HroomPlanSvc hroomPlanSvc ;
	@Autowired
	private ParameterSvc parameterSvc;
	@Autowired
	private HroomTypeSvc hroomTypeSvc ;
	@Autowired
	private CodeCareSvc codeCareSvc ;
	
	
	@RequestMapping("/list.do")
	public String list(ModelMap model , Integer status ) throws Exception{
		List<HroomPlanVo> list = hroomPlanSvc.findListBy(status);
		model.addAttribute("hoverType",7);
		model.addAttribute("firstHoverType", 2) ;
		model.addAttribute("status", status);
		model.addAttribute("listJson", JSON.toJSONString(list));
		
		return "syssetting/room/roomPlan";
	}
	@RequestMapping("/hroomPlanList.do")
	public String hroomPlanList(ModelMap model , boolean editFlag) throws Exception{
		
		// 房型代码
		Map<String, Object> map ;
		List<HroomType> hroomTypes = hroomTypeSvc.getList("status", 0);
		List<Map<String, Object>> hroomTypeIds = new ArrayList<Map<String, Object>>();
		for(HroomType hroomType : hroomTypes){
			map = new HashMap<String, Object>();
			map.put("hroomTypeCodeId", hroomType.getCodeId()) ;
			map.put("hroomTypeCcodeName", hroomType.getCodeId()+"："+hroomType.getCodeNamec()) ;
			hroomTypeIds.add(map);
		}
		model.addAttribute("hroomTypeIds", JSON.toJSONString(hroomTypeIds));
		// 节假日代码
		model.addAttribute("hcodes", JSON.toJSONString(codeCareSvc.findAllListBy(HcodesEnumType.HCODE_HOLIDAYS.getValue())));
		/*List<Map<String, Object>> list11= holidaysSvc.findListBy(null, null) ;
		model.addAttribute("hcodes", JSON.toJSONString(list11));*/
		model.addAttribute("editFlag", editFlag);
		return "syssetting/room/roomPlanList";
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
		List<HroomPlanVo> vos = JSON.parseArray(params,HroomPlanVo.class);
		
		if(!parameterSvc.getNightAuditState()){
			hroomPlanSvc.saveOrUpdateOrDel(vos , delIds , getSessionUser().getOperId());
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
		hroomPlanSvc.updateStatusById((StringUtils.isNotBlank(backIds) ? CommonUtil.array_unique(backIds.split(",")) : null)  , 0 );
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
		HroomPlan entity = hroomPlanSvc.get("codeId",codeId) ;
		if(StringUtils.isBlank(id)){
			if(entity !=null){
				jsonObj.put("success", false);
			}else{
				jsonObj.put("success", true);
			}
		}else{
			if(entity !=null && !id.equals(String.valueOf(entity.getId()))){
				jsonObj.put("success", false);
			}else{
				jsonObj.put("success", true);
			}
		}
		return jsonObj.toJSONString();
	}
	@ResponseBody
	@RequestMapping("/hroomPlanData.do")
	public List<HroomPlanVo> hroomPlanData(ModelMap model , Integer roomplanType, String rateCode , String roomId) throws Exception{
		List<HroomPlanVo> list = null;
		if(StringUtils.isNotBlank(rateCode)){
			HroomPlan hrp = hroomPlanSvc.get("codeId", rateCode) ;
			list = hroomPlanSvc.findAvilListBy(0, null, hrp.getRmplanType(), roomId, null) ;
		}else{
			list = hroomPlanSvc.findAvilListBy(0, null, roomplanType, roomId, null) ;
		}
		return list;
	}
	@ResponseBody
	@RequestMapping("/findHroomPlanPrice.do")
	public String findHroomPlanPrice(ModelMap model , String roomplanCode , String roomId) throws Exception{
			
		return hroomPlanSvc.findHroomPlanPrice(roomplanCode, roomId) ;
		
	}
}
