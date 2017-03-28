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
import com.zoomoor.jy.service.InfoBrandSvc;

/**   
 * 类名称：InfoBrandAct   
 * 类描述：   品牌车系管理
 * 创建人：liuweixing
 * 创建时间：2015-7-15 下午3:53:57   
 * 修改人：liuweixing
 * 修改时间：2015-7-15 下午3:53:57   
 * 修改备注：   
 * @version       
 */
@Controller
public class InfoBrandAct {
	private static final Logger log = LoggerFactory.getLogger(InfoBrandAct.class);
	@Autowired
	private InfoBrandSvc brandSvc;
	/**  
	 * @Title: list  
	 * @Description: 加载类型树 
	 * @return String 
	 * @param backId 返回Id
	 * @throws  
	 */  
	@RequestMapping("/brand/list.do")
	public String list(Integer backId,ModelMap model){
		String json = brandSvc.getGoodsTypeTreeJson();
		model.addAttribute("brandRoot", json);
		if(null!=backId&&!"".equals(backId)){
			InfoBrand brand = brandSvc.get(backId);
			model.addAttribute("brand", brand);
		}
		return "/brand/list";
	}
	/**  
	 * @Title: add  
	 * @Description: 跳转至添加页面
	 * @param parentAuthId
	 * @param model
	 * @return String 
	 * @throws  
	 */  
	@RequestMapping("/brand/add.do")
	public String add(Integer upId, ModelMap model) {
		if(upId != null && upId > 0){
			InfoBrand brand = brandSvc.get(upId);
			model.addAttribute("brand", brand);
		}

		return "brand/add";
	}
	
	/**  
	 * @Title: save  
	 * @Description: 保存品牌信息 
	 * @param request
	 * @param response
	 * @param goodsType
	 * @param upId void 
	 * @throws  
	 */  
	@RequestMapping("/brand/save.do")
	public void save(HttpServletRequest request, HttpServletResponse response, InfoBrand brand, Integer upId) {
		InfoBrand bean = brandSvc.save(brand, upId);
		
		log.info("save brand id={}",bean.getId());
		sysLogSvc.operating(request, "brand.log.save", "id=" + bean.getId() + ";name=" + bean.getName());
		
		JSONObject json = new JSONObject();
		
		if(bean.getId() > 0){
			json.put("statusCode", "200");
			json.put("message", "操作成功");
			json.put("callbackType", "closeCurrent");
			json.put("backId",bean.getId());
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
	@RequestMapping("/brand/edit.do")
	public String edit(Integer groupId, ModelMap model) {
		InfoBrand brand = brandSvc.get(groupId);
		model.addAttribute("brand", brand);
		return "brand/edit";
	}
	
	/**  
	 * @Title: update  
	 * @Description: 修改品牌信息
	 * @param request
	 * @param response
	 * @param goodsType
	 * @param groupId void 
	 * @throws  
	 */  
	@RequestMapping("/brand/update.do")
	public void update(HttpServletRequest request, HttpServletResponse response, InfoBrand brand, Integer id) {
		InfoBrand bean = brandSvc.update(brand, id);
		log.info("update brand id={}", bean.getId());
		sysLogSvc.operating(request, "brand.log.update", "id=" + bean.getId() + ";name=" + bean.getName());
		JSONObject json = new JSONObject();
		json.put("statusCode", "200");
		json.put("message", "操作成功");
		json.put("callbackType", "closeCurrent");
		ResponseUtils.renderJson(response, json.toJSONString());
	}
	
	
	/**  
	 * @Title: delete  
	 * @Description: 删除品牌信息
	 * @param request
	 * @param response
	 * @param ids void 
	 * @throws  
	 */  
	@RequestMapping("/brand/delete.do")
	public void delete(HttpServletRequest request, HttpServletResponse response, Integer id) {
		InfoBrand bean = brandSvc.get(id);
		bean.setDel(true);
		brandSvc.update(bean);
		log.info("delete brand id={}", bean.getId());
		sysLogSvc.operating(request, "brand.log.delete", "id=" + bean.getId() + ";name=" + bean.getName());
		JSONObject json = new JSONObject();
		json.put("statusCode", "200");
		json.put("message", "操作成功");
		ResponseUtils.renderJson(response, json.toJSONString());
	}
	
	/**  
	 * @Title: addty  
	 * @Description: 添加通用
	 * @param request
	 * @param response
	 * @param id void 
	 * @throws  
	 */  
	@RequestMapping("/brand/addty.do")
	public void addty(HttpServletRequest request, HttpServletResponse response, Integer id) {
		JSONObject json = new JSONObject();
		//父节点
		InfoBrand bean = brandSvc.get(id);
		List<InfoBrand> list = brandSvc.getListById(id);
		if(list!=null&&list.size()>0){
			json.put("statusCode", "300");
			json.put("message", "该节点下已有名称为【"+list.get(0).getName()+"】的通用类型");
			ResponseUtils.renderJson(response, json.toJSONString());
		}else{
			InfoBrand childbean= new InfoBrand();
			childbean.setBLevel(1);
			childbean.setDel(false);
			childbean.setInfoBrand(bean);
			childbean.setName("【通用】");
			childbean.setSort(10);
			brandSvc.save(childbean);
			json.put("statusCode", "200");
			json.put("message", "操作成功");
			ResponseUtils.renderJson(response, json.toJSONString());
		}
		
	}
	
	
	
	
	
	
	
	/**
	 *@Title:  move
	 *@Description: 移动品牌
	 *@Author: liuweixing
	 *@Since: 2014-11-27下午3:23:23
	 *@param request
	 *@param response
	 *@param sourceId
	 *@param targetId
	*/
	@RequestMapping("/brand/move.do")
	public void move(HttpServletRequest request, HttpServletResponse response, Integer sourceId,Integer targetId,String moveType) {
		InfoBrand sourceBrand=brandSvc.get(sourceId);
		InfoBrand targetBrand=brandSvc.get(targetId);
		if(targetBrand==null||sourceBrand==null){
			JSONObject json = new JSONObject();
			json.put("statusCode", "300");
			json.put("message", "移动失败，目标信息错误！");
			ResponseUtils.renderJson(response, json.toJSONString());
		}else{
			if("inner".equals(moveType)){
				sourceBrand.setInfoBrand(targetBrand);
				brandSvc.update(sourceBrand);
			}
			if("next".equals(moveType)){
				if(targetBrand.getInfoBrand()==null){
					sourceBrand.setInfoBrand(null);	
				}else{
					InfoBrand targetParentbrand=brandSvc.get(targetBrand.getInfoBrand().getId());
					sourceBrand.setInfoBrand(targetParentbrand);	
				}
				sourceBrand.setSort(targetBrand.getSort()+1);
				brandSvc.update(sourceBrand);
				//xiugai
				updateSort(targetId, sourceBrand, targetBrand);
			}
			if("prev".equals(moveType)){
				if(targetBrand.getInfoBrand()==null){
					sourceBrand.setInfoBrand(null);
				}else{
					InfoBrand targetParentBrand=brandSvc.get(targetBrand.getInfoBrand().getId());
					sourceBrand.setInfoBrand(targetParentBrand);
				}
				sourceBrand.setSort(sourceBrand.getSort()-1);
				brandSvc.update(sourceBrand);
				updateSort(sourceId, sourceBrand, targetBrand);
				
			}
			JSONObject json = new JSONObject();
			json.put("statusCode", "200");
			json.put("message", "操作成功");
			log.info("move brand id={}", sourceId);
			sysLogSvc.operating(request, "brand.log.move", "sourceId=" + sourceId + ";targetId=" + targetId);
			ResponseUtils.renderJson(response, json.toJSONString());
		}
		
		
	}
	@RequestMapping("/brand/check.ajax")
	public void check(HttpServletResponse response, String name, Integer groupId,Integer upId) {
		String result = "false";
		if(groupId!=null&&groupId>0){
			InfoBrand gbrand=brandSvc.get(groupId);
			if(gbrand.getInfoBrand()!=null){
				upId=gbrand.getInfoBrand().getId();
			}else{
				upId=null;
			}
		}
		List<InfoBrand> list = brandSvc.getListByName(name,upId);
		if(list == null || list.size() == 0){
			result = "true";
		}else{
			InfoBrand brand = list.get(0);
			if(groupId != null && groupId.equals(brand.getId())){
				result = "true";
			}
		}
		ResponseUtils.renderJson(response, result);
	}
	private void updateSort(Integer targetId, InfoBrand sourceBrand,
			InfoBrand targetBrand) {
		Integer gid=null;
		if(targetBrand.getInfoBrand()!=null){
			gid=targetBrand.getInfoBrand().getId();
		}
		List<InfoBrand> typeList=brandSvc.getChildById(targetId,gid);
		for (int i = 0; i <typeList.size(); i++) {
				if(typeList.get(i).getId()!=sourceBrand.getId()&&!"".equals(sourceBrand.getId())){
					InfoBrand newBrand=brandSvc.get(typeList.get(i).getId());	
					newBrand.setSort(sourceBrand.getSort()+i+1);
					brandSvc.update(newBrand);
				}
		}
	}
	@RequestMapping("/lookup/brand.do")
	public String lookUpBrand(ModelMap model,Integer backId){
		String json = brandSvc.getGoodsTypeTreeJson();
		model.addAttribute("brandRoot", json);
		if(null!=backId&&!"".equals(backId)){
			InfoBrand infobrand = brandSvc.get(backId);
			model.addAttribute("infobrand", infobrand);
		}
		return "brand/lookup";
	}
	@Autowired
	private SysLogSvc sysLogSvc;
}
