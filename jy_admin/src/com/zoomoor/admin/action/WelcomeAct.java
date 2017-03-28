package com.zoomoor.admin.action;

import static com.zoomoor.admin.service.SysAuthenticationSvc.AUTH_USER;

import java.util.List;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSONArray;
import com.zoomoor.admin.entity.SysAuth;
import com.zoomoor.admin.entity.SysUser;
import com.zoomoor.admin.service.SysAuthSvc;
import com.zoomoor.common.web.ResponseUtils;
import com.zoomoor.common.web.session.SessionProvider;

@SuppressWarnings("unchecked")
@Controller
public class WelcomeAct {

	@RequestMapping("/index.do")
	public String index(HttpServletRequest request, ModelMap model) {
		SysUser sysUser = session.getUserSession(request, AUTH_USER);
		model.addAttribute("sysUser", sysUser);
		
		Properties props = System.getProperties();
		Runtime runtime = Runtime.getRuntime();
		long freeMemoery = runtime.freeMemory();
		long totalMemory = runtime.totalMemory();
		long usedMemory = totalMemory - freeMemoery;
		long maxMemory = runtime.maxMemory();
		long useableMemory = maxMemory - totalMemory + freeMemoery;
		model.addAttribute("props", props);
		model.addAttribute("freeMemoery", freeMemoery);
		model.addAttribute("totalMemory", totalMemory);
		model.addAttribute("usedMemory", usedMemory);
		model.addAttribute("maxMemory", maxMemory);
		model.addAttribute("useableMemory", useableMemory);
		
		return "index";
	}

//	@RequestMapping("/map.do")
//	public String map() {
//		return "map";
//	}
//
//	@RequestMapping("/top.do")
//	public String top(HttpServletRequest request, ModelMap model) {
//		return "top";
//	}
//
//
//	@RequestMapping("/main.do")
//	public String main() {
//		return "main";
//	}
//
//	@RequestMapping("/left.do")
//	public String left(HttpServletRequest request, ModelMap model) {
//		return "left";
//	}

	@RequestMapping("/systree.do")
	public void systree(HttpServletResponse response, Integer id) {
		String json = "{}";
		
		List<SysAuth> auth = sysAuthSvc.queryAuthById(id, null);
		if(auth != null && auth.size() > 0){
			json = getSysTreeJson(auth);
		}
		
		ResponseUtils.renderJson(response, json);
	}
	
	@RequestMapping("/usertree.do")
	public void usertree(HttpServletRequest request, HttpServletResponse response, Integer id) {
		String json = "{}";
		
		List<SysAuth> userAuth = sysAuthSvc.queryUserAuth(request, id);
		if(userAuth != null && userAuth.size() > 0){
			json = getUserTreeJson(request, userAuth);
		}
			ResponseUtils.renderJson(response, json);
	}

//	@RequestMapping("/right.do")
//	public String right(HttpServletRequest request, ModelMap model) {
//		return "right";
//	}
//	
//	@RequiresPermissions("foot")
//	@RequestMapping("/foot.do")
//	public String foot(HttpServletRequest request, ModelMap model) {
//		return "foot";
//	}
	
	public String getSysTreeJson(List<SysAuth> userAuth){
		JSONArray array = new JSONArray();
		for(SysAuth auth : userAuth){
			JSONObject object = new JSONObject();
			object.put("id", auth.getAuthId());
			object.put("name", auth.getAuthName());
			if(auth.getSysAuths() != null && auth.getSysAuths().size() > 0){
				object.put("isParent", true);
			}
			
			array.add(object);
		}
		
		return array.toString();
	}
	
	public String getUserTreeJson(HttpServletRequest request, List<SysAuth> userAuth){
		JSONArray array = new JSONArray();
		String path = request.getContextPath();
		for(SysAuth auth : userAuth){
		   if(!auth.getDel()){		
			   JSONObject object = new JSONObject();
				object.put("id", auth.getAuthId());
				object.put("name", auth.getAuthName());
				if(StringUtils.isNotBlank(auth.getAuthUrl())){
					object.put("url", path + auth.getAuthUrl());
					object.put("rel", auth.getAuthId().toString());
				}
				
				if(auth.getSysAuths() != null && auth.getSysAuths().size() > 0){
					boolean flag = false;
					for(SysAuth childAuth : auth.getSysAuths()){
						if(childAuth.getAuthType() == 1){
							flag = true;
						}
					}
					if(flag){
						object.put("isParent", true);
					}
				}
				
				array.add(object);
			}
		}
		return array.toString();
	}
	
	@Autowired
	private SysAuthSvc sysAuthSvc;
	
	private SessionProvider session;
//	private SysAuthenticationSvc sysAuthenticationSvc;
//	private SysUserSvc sysUserSvc;
//
	@Autowired
	public void setSession(SessionProvider session) {
		this.session = session;
	}
//
//	@Autowired
//	public void setSysUserSvc(SysUserSvc sysUserSvc) {
//		this.sysUserSvc = sysUserSvc;
//	}
//
//	@Autowired
//	public void SysAuthenticationSvc(SysAuthenticationSvc sysAuthenticationSvc) {
//		this.sysAuthenticationSvc = sysAuthenticationSvc;
//	}
}