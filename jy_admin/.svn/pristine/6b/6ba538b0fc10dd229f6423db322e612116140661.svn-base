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

import com.zoomoor.admin.entity.SysRole;
import com.zoomoor.admin.service.SysAuthSvc;
import com.zoomoor.admin.service.SysLogSvc;
import com.zoomoor.admin.service.SysRoleSvc;
import com.zoomoor.common.base.bean.Pager;
import com.zoomoor.common.web.ResponseUtils;
import com.zoomoor.jy.entity.MoneyAuth;
import com.zoomoor.jy.entity.MoneyUserMapping;
import com.zoomoor.jy.service.MoneyAuthSvc;
import com.zoomoor.jy.service.MoneyUserMappingSvc;

@SuppressWarnings("unchecked")
@Controller
public class SysRoleAct {
	private static final Logger log = LoggerFactory.getLogger(SysRoleAct.class);
	@Autowired
	private MoneyAuthSvc moneyAuthSvc;
	@Autowired
	private MoneyUserMappingSvc moneyUserMappingSvc;
	@RequestMapping("/sysrole/list.do")
	public String list(Pager pager, ModelMap model) {
		pager = sysRoleSvc.getPage(pager,pager.getPageNum(), pager.getNumPerPage());
		model.addAttribute("pager", pager);
		return "sysrole/list";
	}
	
	@RequestMapping("/sysrole/add.do")
	public String add(ModelMap model) {
		String json = sysAuthSvc.getSysTreeJson(null);
		model.addAttribute("sysAuthRoot", json);
		String[] prom={"del","authKey"};
		Object[] objetm={false,"m"};
		List<MoneyAuth> moneyAuthListm=moneyAuthSvc.getList(prom,objetm);
		model.addAttribute("moneyAuthListm", moneyAuthListm);
		String[] pror={"del","authKey"};
		Object[] objetr={false,"r"};
		List<MoneyAuth> moneyAuthListr=moneyAuthSvc.getList(pror,objetr);
		model.addAttribute("moneyAuthListr", moneyAuthListr);
		return "sysrole/add";
	}
	
	@RequestMapping("/sysrole/save.do")
	public void save(HttpServletRequest request, HttpServletResponse response, SysRole sysRole, Integer[] authIds,Integer moneyAuth_M,Integer[] moneyAuth_R) {
		SysRole bean = sysRoleSvc.save(sysRole, authIds);
		//保存审批金额权限
		if(moneyAuth_M!=null&&moneyAuth_M>0){
			MoneyAuth MmoneyAuth=moneyAuthSvc.get(moneyAuth_M);
			MoneyUserMapping muming= new MoneyUserMapping();
			muming.setMoneyAuth(MmoneyAuth);
			muming.setSysRole(bean);
			moneyUserMappingSvc.save(muming);
		}
		//保存折扣权限
		if(moneyAuth_R!=null){
			for (Integer moneyAuthR:moneyAuth_R) {
				MoneyAuth MmoneyAuthR=moneyAuthSvc.get(moneyAuthR);
				MoneyUserMapping muming= new MoneyUserMapping();
				muming.setMoneyAuth(MmoneyAuthR);
				muming.setSysRole(bean);
				moneyUserMappingSvc.save(muming);
			}
		}
		log.info("save SysRole id={}", bean.getRoleId());
		sysLogSvc.operating(request, "sysRole.log.save", "id=" + bean.getRoleId() + ";name=" + bean.getRoleName());
		JSONObject json = new JSONObject();
		if(bean.getRoleId() > 0){
			json.put("statusCode", "200");
			json.put("message", "操作成功");
			json.put("callbackType", "closeCurrent");
		}
		
		ResponseUtils.renderJson(response, json.toJSONString());
	}
	
	@RequestMapping("/sysrole/edit.do")
	public String edit(Integer id, ModelMap model) {
		SysRole sysRole = sysRoleSvc.get(id);
		model.addAttribute("sysRole", sysRole);
		String json = sysAuthSvc.getSysTreeJson(sysRole);
		model.addAttribute("sysAuthRoot", json);
		String[] prom={"del","authKey"};
		Object[] objetm={false,"m"};
		List<MoneyAuth> moneyAuthListm=moneyAuthSvc.getList(prom,objetm);
		model.addAttribute("moneyAuthListm", moneyAuthListm);
		String[] pror={"del","authKey"};
		Object[] objetr={false,"r"};
		List<MoneyAuth> moneyAuthListr=moneyAuthSvc.getList(pror,objetr);
		//折扣权限
		moneyAuthListr=moneyAuthSvc.getMoneyAuthListByIsChecked(moneyAuthListr,id);
		model.addAttribute("moneyAuthListr", moneyAuthListr);
		//审批金额权限
		List<MoneyUserMapping> mappingMList=moneyUserMappingSvc.getMappingByRoleId(id,"m");
		if(mappingMList.size()>0){
			MoneyUserMapping mappingM=mappingMList.get(0);
			model.addAttribute("mappingM", mappingM);
		}else{
			model.addAttribute("mappingM", null);
		}
		return "sysrole/edit";
	}
	
	@RequestMapping("/sysrole/update.do")
	public void update(HttpServletRequest request, HttpServletResponse response, SysRole sysRole, Integer roleId, Integer[] authIds,Integer moneyAuth_M,Integer[] moneyAuth_R) {
		SysRole bean = sysRoleSvc.update(sysRole, roleId, authIds);
		//先删除中间表信息
		moneyUserMappingSvc.deleteByRoleId(roleId);
		
		//保存审批金额权限
		if(moneyAuth_M!=null&&moneyAuth_M>0){
			MoneyAuth MmoneyAuth=moneyAuthSvc.get(moneyAuth_M);
			MoneyUserMapping muming= new MoneyUserMapping();
			muming.setMoneyAuth(MmoneyAuth);
			muming.setSysRole(bean);
			moneyUserMappingSvc.save(muming);
		}
		//保存折扣权限
		if(moneyAuth_R!=null){
			for (Integer moneyAuthR:moneyAuth_R) {
				MoneyAuth MmoneyAuthR=moneyAuthSvc.get(moneyAuthR);
				MoneyUserMapping muming= new MoneyUserMapping();
				muming.setMoneyAuth(MmoneyAuthR);
				muming.setSysRole(bean);
				moneyUserMappingSvc.save(muming);
			}
		}
		log.info("update SysRole id={}", bean.getRoleId());
		sysLogSvc.operating(request, "sysRole.log.update", "id=" + bean.getRoleId() + ";name=" + bean.getRoleName());
		
		JSONObject json = new JSONObject();
		json.put("statusCode", "200");
		json.put("message", "操作成功");
		json.put("callbackType", "closeCurrent");
		
		ResponseUtils.renderJson(response, json.toJSONString());
	}
	
	
	@RequestMapping("/sysrole/delete.do")
	public void delete(HttpServletRequest request, HttpServletResponse response, Integer[] ids) {
		//查询角色下是否有用户
		Boolean isUser = sysRoleSvc.getUserByRoleIds(ids);
		JSONObject json = new JSONObject();
		if(isUser){
			json.put("statusCode", "300");
			json.put("message", "该角色下有用户不能删除");
			
		}else{

			SysRole[] beans = sysRoleSvc.deleteByIds(ids);
			
			for (SysRole bean : beans) {
				log.info("delete SysRole id={}", bean.getRoleId());
				sysLogSvc.operating(request, "sysRole.log.delete", "id=" + bean.getRoleId() + ";name=" + bean.getRoleName());
			}
			json.put("statusCode", "200");
			json.put("message", "操作成功");
		}
		ResponseUtils.renderJson(response, json.toJSONString());
	}
	
	@RequestMapping("/sysrole/check.ajax")
	public void check(HttpServletResponse response, String roleName, Integer roleId) {
		String result = "false";
		String[] prom={"del","roleName"};
		Object[] objetm={false,roleName};
		List<SysRole> list = sysRoleSvc.getList(prom, objetm);
		if(list == null || list.size() == 0){
			result = "true";
		}else{
			SysRole sysRole = list.get(0);
			if(roleId != null && roleId.equals(sysRole.getRoleId())){
				result = "true";
			}
		}
		ResponseUtils.renderJson(response, result);
	}
	
	@Autowired
	private SysRoleSvc sysRoleSvc;
	@Autowired
	private SysAuthSvc sysAuthSvc;
	@Autowired
	private SysLogSvc sysLogSvc;
}
