package com.zoomoor.jy.action;

import static com.zoomoor.admin.service.SysAuthenticationSvc.AUTH_USER;

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
import org.springframework.web.bind.annotation.ResponseBody;

import com.zoomoor.admin.entity.SysUser;
import com.zoomoor.admin.service.SysLogSvc;
import com.zoomoor.common.web.ResponseUtils;
import com.zoomoor.common.web.session.SessionProvider;
import com.zoomoor.jy.entity.InfoDepotPosition;
import com.zoomoor.jy.entity.InfoDept;
import com.zoomoor.jy.entity.InfoGoodsType;
import com.zoomoor.jy.service.InfoDepotPositionSvc;

/**   
 * 类名称：InfoDepotPositionAct   
 * 类描述：  仓位管理
 * 创建人：liuweixing
 * 创建时间：2015-7-15 下午3:53:57   
 * 修改人：liuweixing
 * 修改时间：2015-7-15 下午3:53:57   
 * 修改备注：   
 * @version       
 */
@Controller
@SuppressWarnings("unchecked")
public class InfoDepotPositionAct {
	private static final Logger log = LoggerFactory.getLogger(InfoDepotPositionAct.class);
	@Autowired
	private InfoDepotPositionSvc depotpositionSvc;
	@Autowired
	private SessionProvider session;
	/**  
	 * @Title: list  
	 * @Description: 加载类型树 
	 * @return String 
	 * @param backId 返回Id
	 * @throws  
	 */  
	@RequestMapping("/depotposition/list.do")
	public String list(HttpServletRequest request,Integer backId,ModelMap model){
		SysUser sysUser = session.getUserSession(request, AUTH_USER);
		InfoDept infoDept=sysUser.getInfoEmp().getInfoDept();
		/*if(sysUser.getUsername().equals("admin")){
			infoDept=null;
		}*/
		String json = depotpositionSvc.getDepotPositionTreeJson(infoDept);
		model.addAttribute("depotpositionRoot", json);
		if(null!=backId&&!"".equals(backId)){
			InfoDepotPosition depotposition = depotpositionSvc.get(backId);
			model.addAttribute("depotposition", depotposition);
		}
		return "/depotposition/list";
	}
	
	@RequestMapping("/lookup/depotposition.do")
	public String lookUpDepotPosition(HttpServletRequest request,ModelMap model,Integer backId){
		SysUser sysUser = session.getUserSession(request, AUTH_USER);
		InfoDept infoDept=sysUser.getInfoEmp().getInfoDept();
		/*if(sysUser.getUsername().equals("admin")){
			infoDept=null;
		}*/
		String json = depotpositionSvc.getDepotPositionTreeJson(infoDept);
		model.addAttribute("depotpositionRoot", json);
		if(null!=backId&&!"".equals(backId)){
			InfoDepotPosition depotposition = depotpositionSvc.get(backId);
			model.addAttribute("depotposition", depotposition);
		}
		return "depotposition/lookup";
	}
	
	@RequestMapping("/checklookup/depotposition.do")
	public String checklookupDepotPosition(HttpServletRequest request,ModelMap model,Integer backId){
		SysUser sysUser = session.getUserSession(request, AUTH_USER);
		InfoDept infoDept=sysUser.getInfoEmp().getInfoDept();
		String json = depotpositionSvc.getDepotPositionTreeJson(infoDept);
		model.addAttribute("depotpositionRoot", json);
		if(null!=backId&&!"".equals(backId)){
			InfoDepotPosition depotposition = depotpositionSvc.get(backId);
			model.addAttribute("depotposition", depotposition);
		}
		return "depotposition/checklookup";
	}
	/**  
	 * @Title: add  
	 * @Description: 跳转至添加页面
	 * @param parentAuthId
	 * @param model
	 * @return String 
	 * @throws  
	 */  
	@RequestMapping("/depotposition/add.do")
	public String add(Integer upId, ModelMap model) {
		if(upId != null && upId > 0){
			InfoDepotPosition depotposition = depotpositionSvc.get(upId);
			model.addAttribute("depotposition", depotposition);
		}

		return "depotposition/add";
	}
	
	/**  
	 * @Title: save  
	 * @Description: 保存类别信息 
	 * @param request
	 * @param response
	 * @param depotposition
	 * @param upId void 
	 * @throws  
	 */  
	@RequestMapping("/depotposition/save.do")
	public void save(HttpServletRequest request, HttpServletResponse response, InfoDepotPosition depotposition, Integer upId) {
		SysUser sysUser = session.getUserSession(request, AUTH_USER);
		InfoDepotPosition bean = depotpositionSvc.save(depotposition, upId,sysUser);
		
		log.info("save depotposition id={}",bean.getId());
		sysLogSvc.operating(request, "depotposition.log.save", "id=" + bean.getId() + ";name=" + bean.getName());
		
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
	@RequestMapping("/depotposition/edit.do")
	public String edit(Integer groupId, ModelMap model) {
		InfoDepotPosition depotposition = depotpositionSvc.get(groupId);
		model.addAttribute("depotposition", depotposition);
		return "depotposition/edit";
	}
	
	/**  
	 * @Title: update  
	 * @Description: 修改商品类别
	 * @param request
	 * @param response
	 * @param depotposition
	 * @param groupId void 
	 * @throws  
	 */  
	@RequestMapping("/depotposition/update.do")
	public void update(HttpServletRequest request, HttpServletResponse response, InfoDepotPosition depotposition, Integer id) {
		InfoDepotPosition bean = depotpositionSvc.update(depotposition, id);
		log.info("update depotposition id={}", bean.getId());
		sysLogSvc.operating(request, "depotposition.log.update", "id=" + bean.getId() + ";name=" + bean.getName());
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
	@RequestMapping("/depotposition/delete.do")
	public void delete(HttpServletRequest request, HttpServletResponse response, Integer id) {
		InfoDepotPosition bean = depotpositionSvc.get(id);
		bean.setDel(true);
		depotpositionSvc.update(bean);
		log.info("delete depotposition id={}", bean.getId());
		sysLogSvc.operating(request, "depotposition.log.delete", "id=" + bean.getId() + ";name=" + bean.getName());
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
	@RequestMapping("/depotposition/move.do")
	public void move(HttpServletRequest request, HttpServletResponse response, Integer sourceId,Integer targetId,String moveType) {
		InfoDepotPosition sourceType=depotpositionSvc.get(sourceId);
		InfoDepotPosition targetType=depotpositionSvc.get(targetId);
		if(sourceType==null||targetType==null){
			JSONObject json = new JSONObject();
			json.put("statusCode", "300");
			json.put("message", "移动失败，目标信息错误！");
			ResponseUtils.renderJson(response, json.toJSONString());
		}else{
			if("inner".equals(moveType)){
				sourceType.setInfoDepotPosition(targetType);
				depotpositionSvc.update(sourceType);
			}
			if("next".equals(moveType)){
				if(targetType.getInfoDepotPosition()==null){
					sourceType.setInfoDepotPosition(null);	
				}else{
					InfoDepotPosition targetParentType=depotpositionSvc.get(targetType.getInfoDepotPosition().getId());
					sourceType.setInfoDepotPosition(targetParentType);	
				}
				sourceType.setSort(targetType.getSort()+1);
				depotpositionSvc.update(sourceType);
				//xiugai
				updateSort(targetId, sourceType, targetType);
			}
			if("prev".equals(moveType)){
				if(targetType.getInfoDepotPosition()==null){
					sourceType.setInfoDepotPosition(null);
				}else{
					InfoDepotPosition targetParentAuth=depotpositionSvc.get(targetType.getInfoDepotPosition().getId());
					sourceType.setInfoDepotPosition(targetParentAuth);
				}
				sourceType.setSort(targetType.getSort()-1);
				depotpositionSvc.update(sourceType);
				updateSort(sourceId, sourceType, targetType);
				
			}
			JSONObject json = new JSONObject();
			json.put("statusCode", "200");
			json.put("message", "操作成功");
			log.info("move depotposition id={}", sourceId);
			sysLogSvc.operating(request, "depotposition.log.move", "sourceId=" + sourceId + ";targetId=" + targetId);
			ResponseUtils.renderJson(response, json.toJSONString());
		}
		
		
	}
	@RequestMapping("/depotposition/check.ajax")
	public void check(HttpServletRequest request,HttpServletResponse response, String name, Integer groupId,Integer upId) {
		SysUser sysUser = session.getUserSession(request, AUTH_USER);
		InfoDept infoDept=sysUser.getInfoEmp().getInfoDept();
		/*if(sysUser.getUsername().equals("admin")){
			infoDept=null;
		}*/
		String result = "false";
		if(groupId!=null&&groupId>0){
			InfoDepotPosition gt=depotpositionSvc.get(groupId);
			if(gt.getInfoDepotPosition()!=null){
				upId=gt.getInfoDepotPosition().getId();
			}else{
				upId=null;
			}
		}
		List<InfoDepotPosition> list = depotpositionSvc.getListByName(name,upId,infoDept);
		if(list == null || list.size() == 0){
			result = "true";
		}else{
			InfoDepotPosition depotposition = list.get(0);
			if(groupId != null && groupId.equals(depotposition.getId())){
				result = "true";
			}
		}
		ResponseUtils.renderJson(response, result);
	}
	private void updateSort(Integer targetId, InfoDepotPosition sourceType,
			InfoDepotPosition targetType) {
		Integer gid=null;
		if(targetType.getInfoDepotPosition()!=null){
			gid=targetType.getInfoDepotPosition().getId();
		}
		List<InfoDepotPosition> typeList=depotpositionSvc.getChildById(targetId,gid);
		for (int i = 0; i <typeList.size(); i++) {
				if(typeList.get(i).getId()!=sourceType.getId()&&!"".equals(targetType.getId())){
					InfoDepotPosition newType=depotpositionSvc.get(typeList.get(i).getId());	
					newType.setSort(sourceType.getSort()+i+1);
					depotpositionSvc.update(newType);
				}
		}
	}
	/**  
	 * @Title: getPositionFullName  
	 * @Description: 查询仓位全名
	 * @return String 
	 * @throws  
	 */ 
	@ResponseBody
	@RequestMapping("/depotposition/getpositionfullname.ajax")
	public void getPositionFullName(HttpServletRequest request,HttpServletResponse response,Integer pid){
		String fullName=depotpositionSvc.getPositionFullName(pid);
		JSONObject json = new JSONObject();
		json.put("fullName", fullName);
		ResponseUtils.renderJson(response, json.toJSONString());
	}
	@Autowired
	private SysLogSvc sysLogSvc;
}
