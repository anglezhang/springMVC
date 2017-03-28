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
import com.cyw.mammoth.bean.Hbuilding;
import com.cyw.mammoth.bean.HroomType;
import com.cyw.mammoth.service.HbuildingSvc;
import com.cyw.mammoth.service.HroomTypeSvc;
import com.cyw.mammoth.service.ParameterSvc;
import com.cyw.mammoth.service.RoomsSvc;
import com.cyw.mammoth.vo.RoomTypeVO;

/**
 * @author Administrator
 * 房间类型管理
 */
@Controller
@RequestMapping("/hroomType")
public class HroomTypeAction extends BaseAction{
	@Autowired
	private HroomTypeSvc hroomTypeSvc ;
	@Autowired
	private RoomsSvc roomsSvc ;
	@Autowired
	private HbuildingSvc buildingSvc;
	@Autowired
	private ParameterSvc parameterSvc ;
	/** 房间类型定义列表
	 * @return
	 * @see HroomTypeAction.java
	 */
	@RequestMapping("/list.do")
	public String list(ModelMap model,Integer status){
		
		List<RoomTypeVO> list = hroomTypeSvc.findListBy(status);
		model.addAttribute("hoverType",2);
		model.addAttribute("status", status);
		model.addAttribute("listJson", JSON.toJSONString(list));
		
		// 建筑物代码
		List<Hbuilding> hbuildings = buildingSvc.getList("status" , 0) ;
		List<Map<String, Object>> buildingId = new ArrayList<Map<String, Object>>();
		Map<String, Object> map ;
		for(Hbuilding hbuilding : hbuildings){
			map = new HashMap<String, Object>();
			map.put("codeId", hbuilding.getCodeId()) ;
			map.put("codeName", hbuilding.getCodeId()+"："+hbuilding.getCodeNamec()) ;
			buildingId.add(map);
		}
		model.addAttribute("buildingIds", JSON.toJSONString(buildingId));
		
		
		return "syssetting/room/rooms_type_set";
	}
	@ResponseBody
	@RequestMapping("/saveOrUpdateOrDel.do")
	public String saveOrUpdateOrDel(String params , String delIds){
		JSONObject jsonObj =  new JSONObject();
		if(!parameterSvc.getNightAuditState()){
			List<HroomType> hroomTypes = JSON.parseArray(params, HroomType.class) ;
			hroomTypeSvc.saveOrUpdateOrDel(hroomTypes , delIds);
			jsonObj.put("success", true);
		}else{
			jsonObj.put("success", false);
		}
		return jsonObj.toJSONString();
	}
	
	@ResponseBody
	@RequestMapping("/updateStatus.do")
	public String updateStatus(String backIds){
		JSONObject jsonObj =  new JSONObject();
		if(!parameterSvc.getNightAuditState()){
			hroomTypeSvc.updateStatusById((StringUtils.isNotBlank(backIds) ? CommonUtil.array_unique(backIds.split(",")) : null)  , 0 );
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
	public String findBy(Integer id , String codeId){
		JSONObject jsonObj =  new JSONObject();
		HroomType entity = hroomTypeSvc.get("codeId",codeId) ;
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
	@ResponseBody
	@RequestMapping("/findListBy.do")
	public String findListBy(String buildId) throws Exception{
		return JSON.toJSONString(hroomTypeSvc.getList(new String[]{"status","buildingId"},new Object[]{0,buildId}));
	}
	@ResponseBody
	@RequestMapping("/findAvailableRoomTypeListBy.do")
	public String findAvailableRoomTypeListBy(String startDate , String endDate,String buildId) throws Exception{
		return JSON.toJSONString(roomsSvc.getRoomCountByType(startDate, endDate,buildId));
	}
}
