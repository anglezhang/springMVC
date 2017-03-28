package com.zoomoor.jy.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.zoomoor.common.base.bean.Pager;
import com.zoomoor.jy.entity.InfoCustome;
import com.zoomoor.jy.service.InfoCustomeSvc;
import com.zoomoor.jy.util.ResponseMsgUtil;

/**
 * 描述：管理客户信息 客户信息的修改、删除(新增在AppOrderAct.java中)
 * 
 * @author Zhangzhenxing
 * @Date 2015年7月28日 上午9:48:57
 * @version 1.0
 */
@Controller
public class InfoCustomerAct {
	/**
	 * 客户服务管理类
	 * */
	@Autowired
	private InfoCustomeSvc customerSvc;

	/**
	 * @Title: list
	 * @Description: 用户列表
	 * @param model
	 * @return String
	 * @throws
	 */
	@RequestMapping("/customer/list.do")
	public String list(HttpServletRequest request,
			HttpServletResponse response, Pager pager, ModelMap model,
			String queryName, String querytelephone) {
		pager = customerSvc.getLookupPager(pager, queryName, querytelephone, null,null);
		model.addAttribute("pager", pager);
		model.addAttribute("queryName", queryName);
		model.addAttribute("querytelephone", querytelephone);
		return "apporder/customer/list";

	}

	/**
	 * 描述:删除客户信息
	 * */
	@RequestMapping("/customer/delete.do")
	public void lookupDelete(HttpServletRequest request,
			HttpServletResponse response, ModelMap model,
			@RequestParam("customerId") Integer customerId) {
		customerSvc.deleteCustomerById(customerId);
		ResponseMsgUtil.operSuccessFull(response, "删除成功");
	}

	/**
	 * 描述:修改
	 * */
	@RequestMapping("/customer/edit.do")
	public String lookupEdit(HttpServletRequest request,
			HttpServletResponse response, ModelMap model,
			@RequestParam("customerId") Integer customerId,
			@RequestParam("dialogId") String dialogId) {
		InfoCustome infoCustome = customerSvc.get(customerId);
		model.addAttribute("infoCustome", infoCustome);
		model.addAttribute("dialogId", dialogId);
		return "apporder/customer/edit";
	}

	/**
	 * 描述:更新
	 * */
	@RequestMapping("/customer/update.do")
	public void looupUpdate(HttpServletRequest request,
			HttpServletResponse response, InfoCustome infoCustome) {
		customerSvc.updateByCustome(infoCustome);
		ResponseMsgUtil.operSuccessFull(response, "修改成功");
	}

}
