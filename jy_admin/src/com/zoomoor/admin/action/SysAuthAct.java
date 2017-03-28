package com.zoomoor.admin.action;

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

import com.zoomoor.admin.entity.SysAuth;
import com.zoomoor.admin.service.SysAuthSvc;
import com.zoomoor.admin.service.SysLogSvc;
import com.zoomoor.common.web.ResponseUtils;

@SuppressWarnings("unchecked")
@Controller
public class SysAuthAct {
	private static final Logger log = LoggerFactory.getLogger(SysAuthAct.class);
	
	@RequestMapping("/sysauth/list.do")
	public String list(Integer backId,ModelMap model) {
		String json = sysAuthSvc.getSysTreeJson(null);
		model.addAttribute("sysAuthRoot", json);
		if(null!=backId&&!"".equals(backId)){
			SysAuth sysAuth = sysAuthSvc.get(backId);
			model.addAttribute("sysAuth", sysAuth);
		}
		return "sysauth/list";
	}
	
	@RequestMapping("/sysauth/add.do")
	public String add(Integer parentAuthId, ModelMap model) {
		if(parentAuthId != null && parentAuthId > 0){
			SysAuth parentSysAuth = sysAuthSvc.get(parentAuthId);
			model.addAttribute("parentSysAuth", parentSysAuth);
		}

		return "sysauth/add";
	}
	
	@RequestMapping("/sysauth/save.do")
	public void save(HttpServletRequest request, HttpServletResponse response, SysAuth sysAuth, Integer parentAuthId) {
		SysAuth bean = sysAuthSvc.save(sysAuth, parentAuthId);
		
		log.info("save SysAuth id={}", bean.getAuthId());
		sysLogSvc.operating(request, "sysAuth.log.save", "id=" + bean.getAuthId() + ";name=" + bean.getAuthName());
		
		JSONObject json = new JSONObject();
		
		if(bean.getAuthId() > 0){
			json.put("statusCode", "200");
			json.put("message", "操作成功");
			json.put("callbackType", "closeCurrent");
			json.put("backId",bean.getAuthId());
		}
		
		ResponseUtils.renderJson(response, json.toJSONString());
	}
	
	@RequestMapping("/sysauth/edit.do")
	public String edit(Integer authId, ModelMap model) {
		SysAuth sysAuth = sysAuthSvc.get(authId);
		model.addAttribute("sysAuth", sysAuth);
		return "sysauth/edit";
	}
	
	@RequestMapping("/sysauth/update.do")
	public void update(HttpServletRequest request, HttpServletResponse response, SysAuth sysAuth, Integer authId) {
		SysAuth bean = sysAuthSvc.update(sysAuth, authId);
		log.info("update SysAuth id={}", bean.getAuthId());
		sysLogSvc.operating(request, "sysAuth.log.update", "id=" + bean.getAuthId() + ";name=" + bean.getAuthName());
		JSONObject json = new JSONObject();
		json.put("statusCode", "200");
		json.put("message", "操作成功");
		json.put("callbackType", "closeCurrent");
		ResponseUtils.renderJson(response, json.toJSONString());
	}
	
	
	@RequestMapping("/sysauth/delete.do")
	public void delete(HttpServletRequest request, HttpServletResponse response, Integer[] ids) {
		SysAuth[] beans = sysAuthSvc.deleteByIds(ids);
		
		for (SysAuth bean : beans) {
			log.info("delete SysAuth id={}", bean.getAuthId());
			sysLogSvc.operating(request, "sysAuth.log.delete", "id=" + bean.getAuthId() + ";name=" + bean.getAuthName());
		}
		
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
	@RequestMapping("/sysauth/move.do")
	public void move(HttpServletRequest request, HttpServletResponse response, Integer sourceId,Integer targetId,String moveType) {
		SysAuth sourceAuth=sysAuthSvc.get(sourceId);
		SysAuth targetAuth=sysAuthSvc.get(targetId);
		if(targetAuth==null||sourceAuth==null){
			JSONObject json = new JSONObject();
			json.put("statusCode", "300");
			json.put("message", "移动失败，目标信息错误！");
			ResponseUtils.renderJson(response, json.toJSONString());
		}else{
			if("inner".equals(moveType)){
				sourceAuth.setSysAuth(targetAuth);
				sysAuthSvc.update(sourceAuth);
			}
			if("next".equals(moveType)){
				if(targetAuth.getSysAuth()==null){
					sourceAuth.setSysAuth(null);
				}else{
					SysAuth targetParentAuth=sysAuthSvc.get(targetAuth.getSysAuth().getAuthId());
					sourceAuth.setSysAuth(targetParentAuth);
				}
				sourceAuth.setPriority(targetAuth.getPriority()+1);
				sysAuthSvc.update(sourceAuth);
				//xiugai
				updateSort(targetId, sourceAuth, targetAuth);
			}
			if("prev".equals(moveType)){
				if(targetAuth.getSysAuth()==null){
					sourceAuth.setSysAuth(null);
				}else{
					SysAuth targetParentAuth=sysAuthSvc.get(targetAuth.getSysAuth().getAuthId());
					sourceAuth.setSysAuth(targetParentAuth);
				}
				sourceAuth.setPriority(targetAuth.getPriority()-1);
				sysAuthSvc.update(sourceAuth);
				updateSort(sourceId, sourceAuth, targetAuth);
				
			}
			JSONObject json = new JSONObject();
			json.put("statusCode", "200");
			json.put("message", "操作成功");
			log.info("move SysAuth id={}", sourceId);
			sysLogSvc.operating(request, "sysAuth.log.move", "sourceId=" + sourceId + ";targetId=" + targetId);
			ResponseUtils.renderJson(response, json.toJSONString());
		}
		
		
	}
	@RequestMapping("/sysauth/check.ajax")
	public void check(HttpServletResponse response, String authCode, Integer authId) {
		String result = "false";
		String[] prom={"del","authCode"};
		Object[] objetm={false,authCode};
		List<SysAuth> list = sysAuthSvc.getList(prom, objetm);
		if(list == null || list.size() == 0){
			result = "true";
		}else{
			SysAuth sysAuth = list.get(0);
			if(authId != null && authId.equals(sysAuth.getAuthId())){
				result = "true";
			}
		}
		ResponseUtils.renderJson(response, result);
	}
	private void updateSort(Integer targetId, SysAuth sourceAuth,
			SysAuth targetAuth) {
		Integer aid=null;
		if(targetAuth.getSysAuth()!=null){
			aid=targetAuth.getSysAuth().getAuthId();
		}
		
		List<SysAuth> sysAuthList=sysAuthSvc.getChildById(targetId,aid);
		for (int i = 0; i <sysAuthList.size(); i++) {
				if(sysAuthList.get(i).getAuthId()!=sourceAuth.getAuthId()&&!"".equals(sourceAuth.getAuthId())){
					SysAuth newAuth=sysAuthSvc.get(sysAuthList.get(i).getAuthId());	
					newAuth.setPriority(sourceAuth.getPriority()+i+1);
					sysAuthSvc.update(newAuth);
				}
		}
	}
	@Autowired
	private SysAuthSvc sysAuthSvc;
	
	@Autowired
	private SysLogSvc sysLogSvc;
}