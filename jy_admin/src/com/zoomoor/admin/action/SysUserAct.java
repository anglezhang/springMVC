package com.zoomoor.admin.action;

import static com.zoomoor.admin.service.SysAuthenticationSvc.AUTH_USER;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zoomoor.admin.entity.SysRole;
import com.zoomoor.admin.entity.SysUser;
import com.zoomoor.admin.service.SysLogSvc;
import com.zoomoor.admin.service.SysRoleSvc;
import com.zoomoor.admin.service.SysUserSvc;
import com.zoomoor.common.base.bean.Pager;
import com.zoomoor.common.security.encoder.PwdEncoder;
import com.zoomoor.common.web.RequestUtils;
import com.zoomoor.common.web.ResponseUtils;
import com.zoomoor.common.web.session.SessionProvider;

@SuppressWarnings("unchecked")
@Controller
public class SysUserAct {
	private static final Logger log = LoggerFactory.getLogger(SysUserAct.class);
	
	@RequestMapping("/sysuser/list.do")
	public String list(Pager pager, String queryUsername, String queryRealname, ModelMap model) {
		pager = sysUserSvc.getPage(pager, queryUsername, queryRealname, pager.getPageNum(), pager.getNumPerPage());
		model.addAttribute("queryUsername", queryUsername);
		model.addAttribute("queryRealname", queryRealname);
		model.addAttribute("pager", pager);
		return "sysuser/list";
	}
	
	@RequestMapping("/sysuser/add.do")
	public String add(ModelMap model) {
		List<SysRole> sysRoleList = sysRoleSvc.getAll();
		model.addAttribute("sysRoleList", sysRoleList);
		return "sysuser/add";
	}
	

	@RequestMapping("/sysuser/save.do")
	public void save(HttpServletRequest request, HttpServletResponse response, SysUser sysUser, Integer[] roleIds) {
		String ip = RequestUtils.getIpAddr(request);
		
		SysUser bean = sysUserSvc.save(sysUser, ip, roleIds);
		
		log.info("save SysUser id={}", bean.getUserId());
		sysLogSvc.operating(request, "sysUser.log.save", "id=" + bean.getUserId() + ";username=" + bean.getUsername());
		
		JSONObject json = new JSONObject();
		if(bean.getUserId() > 0){
			json.put("statusCode", "200");
			json.put("message", "操作成功");
			json.put("callbackType", "closeCurrent");
		}
		
		ResponseUtils.renderJson(response, json.toJSONString());
	}
	
	@RequestMapping("/sysuser/edit.do")
	public String edit(Integer id, ModelMap model) {
		SysUser sysUser = sysUserSvc.get(id);
		List<SysRole> sysRoleList = sysRoleSvc.getAll();
		model.addAttribute("sysRoleList", sysRoleList);
		model.addAttribute("sysUser", sysUser);
		return "sysuser/edit";
	}
	@RequestMapping("/sysuser/update.do")
	public void update(HttpServletRequest request, HttpServletResponse response, SysUser sysUser, Integer userId, Integer[] roleIds) {
		SysUser bean = sysUserSvc.update(sysUser, userId, roleIds);
		
		log.info("update SysUser id={}", userId);
		sysLogSvc.operating(request, "sysUser.log.update", "id=" + bean.getUserId() + ";username=" + bean.getUsername());
		
		JSONObject json = new JSONObject();
		if(bean.getUserId() > 0){
			json.put("statusCode", "200");
			json.put("message", "操作成功");
		}else{
			json.put("statusCode", "300");
			json.put("message", "操作失败");
		}
		
		json.put("callbackType", "closeCurrent");
		ResponseUtils.renderJson(response, json.toJSONString());
	}
	/**  
	 * @Title: editPassword  
	 * @Description: 修改密码
	 * @param id
	 * @param model
	 * @return String 
	 * @throws  
	 */  
	@RequestMapping("/sysuser/editpassword.do")
	public String editPassword(HttpServletRequest request,  ModelMap model) {
		SysUser sysUser = session.getUserSession(request, AUTH_USER);
		model.addAttribute("sysUser", sysUser);
		return "sysuser/editpassword";
	}
	/**  
	 * @Title: updatePassword  
	 * @Description: 修改密码
	 * @param request
	 * @param response
	 * @param sysUser
	 * @param userId
	 * @param roleIds void 
	 * @throws  
	 */  
	@RequestMapping("/sysuser/updatepassword.do")
	public void updatePassword(HttpServletRequest request, HttpServletResponse response, Integer userId,String oldpassword,String password) {
		String[] prom={"userId","password"};
		Object[] objetm={userId,pwdEncoder.encodePassword(oldpassword)};
		SysUser bean = sysUserSvc.get(prom,objetm);
		JSONObject json = new JSONObject();
		if(bean==null){
			json.put("statusCode", "300");
			json.put("message", "旧密码不正确，修改失败");
			
		}else{
			bean.setPassword(pwdEncoder.encodePassword(password));
			sysUserSvc.update(bean);
			log.info("updatepassword SysUser id={}", userId);
			sysLogSvc.operating(request, "sysUser.log.updatepassword", "id=" + bean.getUserId() + ";username=" + bean.getUsername());
			json.put("statusCode", "200");
			json.put("message", "密码修改成功，下次登录生效");
		}
		ResponseUtils.renderJson(response, json.toJSONString());
	}
	
	
	@RequestMapping("/sysuser/delete.do")
	public void delete(HttpServletRequest request, HttpServletResponse response, Integer[] ids) {
		SysUser[] beans = sysUserSvc.deleteByIds(ids);
		for (SysUser bean : beans) {
			log.info("delete SysUser id={}", bean.getUserId());
			sysLogSvc.operating(request, "sysUser.log.delete", "id=" + bean.getUserId() + ";username=" + bean.getUsername());
		}
		
		JSONObject json = new JSONObject();
		json.put("statusCode", "200");
		json.put("message", "操作成功");
		ResponseUtils.renderJson(response, json.toJSONString());
	}
	
	@RequestMapping("/sysuser/check.ajax")
	public void check(HttpServletResponse response, String username) {
		String result = "false";
		String[] prom={"del","username"};
		Object[] objetm={false,username};
		List<SysUser> list = sysUserSvc.getList(prom, objetm);
		if(list == null || list.size() == 0){
			result = "true";
		}
		ResponseUtils.renderJson(response, result);
	}
	
	@Autowired
	private SysUserSvc sysUserSvc;
	
	@Autowired
	private SysRoleSvc sysRoleSvc;
	
	@Autowired
	private SysLogSvc sysLogSvc;
	@Autowired
	private SessionProvider session;
	@Resource
	private PwdEncoder pwdEncoder;
}
