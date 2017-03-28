package com.zoomoor.jy.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zoomoor.admin.service.SysLogSvc;
import com.zoomoor.common.web.ResponseUtils;
import com.zoomoor.jy.entity.InfoBrand;
import com.zoomoor.jy.entity.InfoGoodsType;
import com.zoomoor.jy.service.InfoGoodsTypeSvc;

/**   
 * 类名称：InfoGoodsTypeAct   
 * 类描述：   物料类别信息
 * 创建人：liuweixing
 * 创建时间：2015-7-15 下午3:53:57   
 * 修改人：liuweixing
 * 修改时间：2015-7-15 下午3:53:57   
 * 修改备注：   
 * @version       
 */
@Controller
public class InfoGoodsTypeAct {
	private static final Logger log = LoggerFactory.getLogger(InfoGoodsTypeAct.class);
	@Autowired
	private InfoGoodsTypeSvc goodsTypeSvc;
	/**  
	 * @Title: list  
	 * @Description: 加载类型树 
	 * @return String 
	 * @param backId 返回Id
	 * @throws  
	 */  
	@RequestMapping("/goodsType/list.do")
	public String list(Integer backId,ModelMap model){
		String json = goodsTypeSvc.getGoodsTypeTreeJson();
		model.addAttribute("goodsTypeRoot", json);
		if(null!=backId&&!"".equals(backId)){
			InfoGoodsType goodsType = goodsTypeSvc.get(backId);
			model.addAttribute("goodsType", goodsType);
		}
		return "/goodstype/list";
	}
	
	@RequestMapping("/lookup/goodsType.do")
	public String lookUpGoodsType(ModelMap model,Integer backId){
		String json = goodsTypeSvc.getGoodsTypeTreeJson();
		model.addAttribute("goodsTypeRoot", json);
		if(null!=backId&&!"".equals(backId)){
			InfoGoodsType goodsType = goodsTypeSvc.get(backId);
			model.addAttribute("goodsType", goodsType);
		}
		return "goodstype/lookup";
	}
	/**  
	 * @Title: add  
	 * @Description: 跳转至添加页面
	 * @param parentAuthId
	 * @param model
	 * @return String 
	 * @throws  
	 */  
	@RequestMapping("/goodsType/add.do")
	public String add(Integer upId, ModelMap model) {
		if(upId != null && upId > 0){
			InfoGoodsType goodsType = goodsTypeSvc.get(upId);
			model.addAttribute("goodsType", goodsType);
		}

		return "goodstype/add";
	}
	
	/**  
	 * @Title: save  
	 * @Description: 保存类别信息 
	 * @param request
	 * @param response
	 * @param goodsType
	 * @param upId void 
	 * @throws  
	 */  
	@RequestMapping("/goodsType/save.do")
	public void save(HttpServletRequest request, HttpServletResponse response, InfoGoodsType goodsType, Integer upId) {
		InfoGoodsType bean = goodsTypeSvc.save(goodsType, upId);
		
		log.info("save goodsType id={}",bean.getGroupId());
		sysLogSvc.operating(request, "goodsType.log.save", "id=" + bean.getGroupId() + ";name=" + bean.getTypeName());
		
		JSONObject json = new JSONObject();
		
		if(bean.getGroupId() > 0){
			json.put("statusCode", "200");
			json.put("message", "操作成功");
			json.put("callbackType", "closeCurrent");
			json.put("backId",bean.getGroupId());
		}
		
		ResponseUtils.renderJson(response, json.toJSONString());
	}
	/**  
	 * @Title: edit  
	 * @Description: 跳转至修改页面 
	 * @param groupId
	 * @param model
	 * @return String 
	 * @throws  
	 */  
	@RequestMapping("/goodsType/edit.do")
	public String edit(Integer groupId, ModelMap model) {
		InfoGoodsType goodsType = goodsTypeSvc.get(groupId);
		model.addAttribute("goodsType", goodsType);
		return "goodstype/edit";
	}
	
	/**  
	 * @Title: update  
	 * @Description: 修改商品类别
	 * @param request
	 * @param response
	 * @param goodsType
	 * @param groupId void 
	 * @throws  
	 */  
	@RequestMapping("/goodsType/update.do")
	public void update(HttpServletRequest request, HttpServletResponse response, InfoGoodsType goodsType, Integer groupId) {
		InfoGoodsType bean = goodsTypeSvc.update(goodsType, groupId);
		log.info("update goodsType id={}", bean.getGroupId());
		sysLogSvc.operating(request, "goodsType.log.update", "id=" + bean.getGroupId() + ";name=" + bean.getTypeName());
		JSONObject json = new JSONObject();
		json.put("statusCode", "200");
		json.put("message", "操作成功");
		json.put("callbackType", "closeCurrent");
		ResponseUtils.renderJson(response, json.toJSONString());
	}
	
	
	/**  
	 * @Title: delete  
	 * @Description: 删除商品类别
	 * @param request
	 * @param response
	 * @param ids void 
	 * @throws  
	 */  
	@RequestMapping("/goodsType/delete.do")
	public void delete(HttpServletRequest request, HttpServletResponse response, Integer id) {
		InfoGoodsType bean = goodsTypeSvc.get(id);
		bean.setDel(true);
		goodsTypeSvc.update(bean);
		log.info("delete goodsType id={}", bean.getGroupId());
		sysLogSvc.operating(request, "goodsType.log.delete", "id=" + bean.getGroupId() + ";name=" + bean.getTypeName());
		JSONObject json = new JSONObject();
		json.put("statusCode", "200");
		json.put("message", "操作成功");
		ResponseUtils.renderJson(response, json.toJSONString());
	}
	/**
	 *@Title:  move
	 *@Description: 移动权限 
	 *@Author: liuweixing
	 *@Since: 2014-11-27下午3:23:23
	 *@param request
	 *@param response
	 *@param sourceId
	 *@param targetId
	*/
	@RequestMapping("/goodsType/move.do")
	public void move(HttpServletRequest request, HttpServletResponse response, Integer sourceId,Integer targetId,String moveType) {
		InfoGoodsType sourceType=goodsTypeSvc.get(sourceId);
		InfoGoodsType targetType=goodsTypeSvc.get(targetId);
		if(sourceType==null||targetType==null){
			JSONObject json = new JSONObject();
			json.put("statusCode", "300");
			json.put("message", "移动失败，目标信息错误！");
			ResponseUtils.renderJson(response, json.toJSONString());
		}else{
			if("inner".equals(moveType)){
				sourceType.setInfoGoodsType(targetType);
				goodsTypeSvc.update(sourceType);
			}
			if("next".equals(moveType)){
				if(targetType.getInfoGoodsType()==null){
					sourceType.setInfoGoodsType(null);	
				}else{
					InfoGoodsType targetParentType=goodsTypeSvc.get(targetType.getInfoGoodsType().getGroupId());
					sourceType.setInfoGoodsType(targetParentType);	
				}
				sourceType.setSort(targetType.getSort()+1);
				goodsTypeSvc.update(sourceType);
				//xiugai
				updateSort(targetId, sourceType, targetType);
			}
			if("prev".equals(moveType)){
				if(targetType.getInfoGoodsType()==null){
					sourceType.setInfoGoodsType(null);
				}else{
					InfoGoodsType targetParentAuth=goodsTypeSvc.get(targetType.getInfoGoodsType().getGroupId());
					sourceType.setInfoGoodsType(targetParentAuth);
				}
				sourceType.setSort(targetType.getSort()-1);
				goodsTypeSvc.update(sourceType);
				updateSort(sourceId, sourceType, targetType);
				
			}
			JSONObject json = new JSONObject();
			json.put("statusCode", "200");
			json.put("message", "操作成功");
			log.info("move goodsType id={}", sourceId);
			sysLogSvc.operating(request, "goodsType.log.move", "sourceId=" + sourceId + ";targetId=" + targetId);
			ResponseUtils.renderJson(response, json.toJSONString());
		}
		
		
	}
	@RequestMapping("/goodsType/check.ajax")
	public void check(HttpServletResponse response, String typeName, Integer groupId,Integer upId) {
		String result = "false";
		if(groupId!=null&&groupId>0){
			InfoGoodsType gt=goodsTypeSvc.get(groupId);
			if(gt.getInfoGoodsType()!=null){
				upId=gt.getInfoGoodsType().getGroupId();
			}else{
				upId=null;
			}
		}
		List<InfoGoodsType> list = goodsTypeSvc.getListByName(typeName,upId);
		if(list == null || list.size() == 0){
			result = "true";
		}else{
			InfoGoodsType goodstype = list.get(0);
			if(groupId != null && groupId.equals(goodstype.getGroupId())){
				result = "true";
			}
		}
		ResponseUtils.renderJson(response, result);
	}
	private void updateSort(Integer targetId, InfoGoodsType sourceType,
			InfoGoodsType targetType) {
		Integer gid=null;
		if(targetType.getInfoGoodsType()!=null){
			gid=targetType.getInfoGoodsType().getGroupId();
		}
		List<InfoGoodsType> typeList=goodsTypeSvc.getChildById(targetId,gid);
		for (int i = 0; i <typeList.size(); i++) {
				if(typeList.get(i).getGroupId()!=sourceType.getGroupId()&&!"".equals(targetType.getGroupId())){
					InfoGoodsType newType=goodsTypeSvc.get(typeList.get(i).getGroupId());	
					newType.setSort(sourceType.getSort()+i+1);
					goodsTypeSvc.update(newType);
				}
		}
	}
	@Autowired
	private SysLogSvc sysLogSvc;
}
