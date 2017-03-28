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
import com.cyw.mammoth.bean.Rooms;
import com.cyw.mammoth.service.HbuildingSvc;
import com.cyw.mammoth.service.HroomDefineSvc;
import com.cyw.mammoth.service.HroomTypeSvc;
import com.cyw.mammoth.service.RoomsSvc;
import com.cyw.mammoth.vo.RoomDefineVo;

/**
 * 房间定义 控制器
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/hroomDefine")
public class HroomDefineAction extends BaseAction {

	@Autowired
	private HroomDefineSvc hroomDefineSvc ;
	@Autowired
	private HbuildingSvc buildingSvc;
	@Autowired
	private HroomTypeSvc hroomTypeSvc ;
	@Autowired
	private RoomsSvc roomSvc ;
	
	@RequestMapping("/list.do")
	public String list(ModelMap model , Integer status ){
		List<RoomDefineVo> list = hroomDefineSvc.findListBy(status);
		model.addAttribute("hoverType", 4);
		model.addAttribute("status", status);
		model.addAttribute("listJson", JSON.toJSONString(list));
		
		
		// 建筑物代码
		List<Hbuilding> hbuildings = buildingSvc.getList("status" , 0) ;
		List<Map<String, Object>> buildingIds = new ArrayList<Map<String, Object>>();
		Map<String, Object> map ;
		for(Hbuilding hbuilding : hbuildings){
			map = new HashMap<String, Object>();
			/*map.put("buildingCodeId", hbuilding.getCodeId()) ;
			map.put("buildingCodeName", hbuilding.getCodeNamec()) ;*/
			map.put(hbuilding.getCodeId(), hbuilding.getCodeNamec()+"-=-"+hbuilding.getMinNum()+"-=-"+hbuilding.getMaxNum()) ;
			buildingIds.add(map);
		}
		model.addAttribute("buildingIds", JSON.toJSONString(buildingIds));
		// 房型代码
		List<HroomType> hroomTypes = hroomTypeSvc.getList("status", 0);
		List<Map<String, Object>> hroomTypeIds = new ArrayList<Map<String, Object>>();
		for(HroomType hroomType : hroomTypes){
			map = new HashMap<String, Object>();
			map.put("hroomTypeCodeId", hroomType.getBuildingId()+"--"+hroomType.getCodeId()) ;
			map.put("hroomTypeCcodeName", hroomType.getCodeId()+"："+hroomType.getCodeNamec()) ;
			hroomTypeIds.add(map);
		}
		model.addAttribute("hroomTypeIds", JSON.toJSONString(hroomTypeIds));
		
		return "syssetting/room/roomDefine";
	}
	/**
	 * 
	 * @param params   编辑的实体json
	 * @param editFlag 编辑状态   'u' 修改   'a' 新增 
	 * @param delIds   被删除的ids
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/saveOrUpdateOrDel.do")
	public String saveOrUpdateOrDel(String params, String delIds){
		JSONObject jsonObj =  new JSONObject();
		List<Object> roomDefineVos = JSON.parseArray(params) ;
		hroomDefineSvc.saveOrUpdateOrDel(roomDefineVos , delIds , getSessionUser().getOperId());
		jsonObj.put("success", true);
		return jsonObj.toJSONString();
	}
	
	@ResponseBody
	@RequestMapping("/updateStatus.do")
	public String updateStatus(String backIds){
		JSONObject jsonObj =  new JSONObject();
		hroomDefineSvc.updateStatusById((StringUtils.isNotBlank(backIds) ? CommonUtil.array_unique(backIds.split(",")) : null)  , 0 );
		jsonObj.put("success", true);
		return jsonObj.toJSONString();
	}
	
	@ResponseBody
	@RequestMapping("/update.do")
	public String update(Rooms room){
		JSONObject jsonObj =  new JSONObject();
		Rooms entity = hroomDefineSvc.get("roomId", room.getRoomId()) ;
		entity.setRoomCharacter(room.getRoomCharacter()) ;
		hroomDefineSvc.update(entity);
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
		Rooms entity = hroomDefineSvc.get("roomId",id) ;
		if(StringUtils.isBlank(id)){
			if(entity !=null){
				jsonObj.put("success", false);
			}else{
				jsonObj.put("success", true);
			}
		}else{
			if(entity !=null && !id.equals(entity.getRoomId())){
				jsonObj.put("success", false);
			}else{
				jsonObj.put("success", true);
			}
		}
		return jsonObj.toJSONString();
	}
	/**
	 * 根据房间id串查询房间列表
	 * @param roomIds
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/findRoomsBy.do")
	public String findRoomsBy(String roomIds){
		JSONObject jsonObj =  new JSONObject();
		if(StringUtils.isNotBlank(roomIds)){
			jsonObj.put("listJson", roomSvc.getSelRoomsInf(roomIds));
		}
		return jsonObj.toJSONString();
	}
}
