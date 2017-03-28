package com.zoomoor.admin.action;

import static com.zoomoor.common.base.bean.Pager.cpn;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zoomoor.admin.entity.SysLog;
import com.zoomoor.admin.service.SysLogSvc;
import com.zoomoor.common.base.bean.Pager;
import com.zoomoor.common.web.ResponseUtils;

@SuppressWarnings("unchecked")
@Controller
public class SysLogAct {
	private static final Logger log = LoggerFactory.getLogger(SysLogAct.class);
	
	@RequestMapping("/syslog/list_operating.do")
	public String listOperating(Pager pager, String queryUsername, String queryTitle, String queryIp, HttpServletRequest request, ModelMap model) {
		pager = sysLogSvc.getPage(SysLog.OPERATING, queryUsername, queryTitle, queryIp, cpn(pager.getPageNum()), pager.getNumPerPage());
		model.addAttribute("pager", pager);
		model.addAttribute("queryUsername", queryUsername);
		model.addAttribute("queryTitle", queryTitle);
		model.addAttribute("queryIp", queryIp);
		return "syslog/list_operating";
	}
	
	@RequestMapping("/syslog/list_login_success.do")
	public String listLoginSuccess(Pager pager, String queryUsername, String queryTitle, String queryIp, HttpServletRequest request, ModelMap model) {
		pager = sysLogSvc.getPage(SysLog.LOGIN_SUCCESS, queryUsername, queryTitle, queryIp, cpn(pager.getPageNum()), pager.getNumPerPage());
		model.addAttribute("pager", pager);
		model.addAttribute("queryUsername", queryUsername);
		model.addAttribute("queryTitle", queryTitle);
		model.addAttribute("queryIp", queryIp);
		return "syslog/list_login_success";
	}

	@RequestMapping("/syslog/list_login_failure.do")
	public String listLoginFailure(Pager pager, String queryUsername, String queryTitle, String queryIp, HttpServletRequest request, ModelMap model) {
		pager = sysLogSvc.getPage(SysLog.LOGIN_FAILURE, null, queryTitle, queryIp, cpn(pager.getPageNum()), pager.getNumPerPage());
		model.addAttribute("pager", pager);
		model.addAttribute("queryUsername", queryUsername);
		model.addAttribute("queryTitle", queryTitle);
		model.addAttribute("queryIp", queryIp);
		return "syslog/list_login_failure";
	}
	
	@RequestMapping("/syslog/delete_operating.do")
	public void deleteOperating(HttpServletRequest request, HttpServletResponse response, Integer[] ids) {
		SysLog[] beans = sysLogSvc.deleteByIds(ids);
		for (SysLog bean : beans) {
			log.info("delete SysLog id={}", bean.getLogId());
		}
		
		JSONObject json = new JSONObject();
		json.put("statusCode", "200");
		json.put("message", "操作成功");
		ResponseUtils.renderJson(response, json.toJSONString());
	}

	@RequestMapping("/syslog/delete_operating_batch.do")
	public String deleteOperatingBatch(Pager pager, String queryUsername, String queryTitle, String queryIp, HttpServletRequest request, Integer days, ModelMap model) {
		sysLogSvc.deleteBatch(SysLog.OPERATING, days);
		return listOperating(pager, queryUsername, queryTitle, queryIp, request, model);
	}
	
	@RequestMapping("/syslog/delete_login_success.do")
	public void deleteLoginSuccess(HttpServletRequest request, HttpServletResponse response, Integer[] ids) {
		SysLog[] beans = sysLogSvc.deleteByIds(ids);
		for (SysLog bean : beans) {
			log.info("delete SysLog id={}", bean.getLogId());
		}
		
		JSONObject json = new JSONObject();
		json.put("statusCode", "200");
		json.put("message", "操作成功");
		ResponseUtils.renderJson(response, json.toJSONString());
	}

	@RequestMapping("/syslog/delete_login_success_batch.do")
	public String deleteLoginSuccessBatch(Pager pager, String queryUsername, String queryTitle, String queryIp, HttpServletRequest request, Integer days, ModelMap model) {
		sysLogSvc.deleteBatch(SysLog.LOGIN_SUCCESS, days);
		return listLoginSuccess(pager, queryUsername, queryTitle, queryIp, request, model);
	}
	
	@RequestMapping("/syslog/delete_login_failure.do")
	public void deleteLoginFailure(HttpServletRequest request, HttpServletResponse response, Integer[] ids) {
		SysLog[] beans = sysLogSvc.deleteByIds(ids);
		for (SysLog bean : beans) {
			log.info("delete SysLog id={}", bean.getLogId());
		}
		
		JSONObject json = new JSONObject();
		json.put("statusCode", "200");
		json.put("message", "操作成功");
		ResponseUtils.renderJson(response, json.toJSONString());
	}

	@RequestMapping("/syslog/delete_login_failure_batch.do")
	public String deleteLoginFailureBatch(Pager pager, String queryUsername, String queryTitle, String queryIp, HttpServletRequest request, Integer days, ModelMap model) {
		sysLogSvc.deleteBatch(SysLog.LOGIN_FAILURE, days);
		return listLoginFailure(pager, queryUsername, queryTitle, queryIp, request, model);
	}
	
	@Autowired
	private SysLogSvc sysLogSvc;
}