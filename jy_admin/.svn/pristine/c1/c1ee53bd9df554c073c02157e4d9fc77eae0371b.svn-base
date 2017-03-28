package com.zoomoor.jy.fourshop.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.zoomoor.common.base.bean.Pager;
import com.zoomoor.common.web.session.SessionProvider;
import com.zoomoor.jy.entity.CustomerEntrust;
import com.zoomoor.jy.entity.Servicetype;
import com.zoomoor.jy.entity.vo.CusEntrusAmout;
import com.zoomoor.jy.fourshop.service.CustTrustEnSvc;
import com.zoomoor.jy.service.ServiceitemScv;
import com.zoomoor.jy.service.ServicetypeSvc;
import com.zoomoor.jy.util.ResponseMsgUtil;

/**
 * 描述：CusTrustEnSvc.java
 * 
 * @author Zhangzhenxing
 * @Date 2015年8月8日 下午5:08:18
 * @version 1.0
 */
@Controller
public class CusTrustEnSvc {

	private static final Logger log = LoggerFactory
			.getLogger(CusTrustEnSvc.class);

	@Autowired
	private CustTrustEnSvc cusTrSvc;

	@Autowired
	private SessionProvider session;

	/**
	 * 服务类型管理
	 * */
	@Autowired
	private ServicetypeSvc typeSvc;

	/**
	 * 描述:4s店
	 * */
	@RequestMapping("/forushop/list.html")
	public String indexList(HttpServletRequest request,
			HttpServletResponse response, ModelMap model, Pager pager,
			String queryOrderNO, String queryPlatenum, String queryCusName,
			String queryOrderBeginTime, String queryOrderEndTime,
			String queryOperator, String queryStatus) {
		Integer deptID = (Integer) session.getAttribute(request, "USER_4SID");
		if (deptID == null) {
			return "redirect:login4s.html";
		}
		pager = cusTrSvc.getPager(pager, queryOrderNO, queryPlatenum,
				queryCusName, queryOrderBeginTime, queryOrderEndTime,
				queryOperator, queryStatus, deptID);
		// 服务类型
		List<Servicetype> serviceItem = typeSvc.getAll();
		model.addAttribute("serviceItem", serviceItem);
		model.addAttribute("pager", pager);
		model.addAttribute("queryOrderNO", queryOrderNO);
		model.addAttribute("queryPlatenum", queryPlatenum);
		model.addAttribute("queryCusName", queryCusName);
		model.addAttribute("queryOrderBeginTime", queryOrderBeginTime);
		model.addAttribute("queryOrderEndTime", queryOrderEndTime);
		model.addAttribute("queryOperator", queryOperator);
		return "/fourshop/list";
	}

	/**
	 * 描述:查看
	 * */
	@RequestMapping("/forushop/scan.html")
	public String scan(HttpServletRequest request,
			HttpServletResponse response, ModelMap model,
			@RequestParam("entrustId") Integer entrustId) {
		CustomerEntrust cusEntity = cusTrSvc.get(entrustId);
		model.addAttribute("cusEntity", cusEntity);
		return "/fourshop/view";
	}

	/**
	 * 描述:结算界面
	 * */
	@RequestMapping("/fourshop/amout.html")
	public String toAmount(HttpServletRequest request,
			HttpServletResponse response, ModelMap model,
			@RequestParam("entrustId") Integer entrustId) {
		CusEntrusAmout amout = cusTrSvc.getAmont(entrustId);
		model.addAttribute("amout", amout);
		model.addAttribute("entrustId", entrustId);
		return "/fourshop/amout";
	}

	/**
	 * 描述:4s店结算
	 * */
	@RequestMapping("/fourshop/saveamout.html")
	public void saveAmout(HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam("entrustId") Integer entrustId,
			@RequestParam("shopmonney") Double shopmonney) {
		CustomerEntrust entity = cusTrSvc.get(entrustId);
		entity.setShopmonney(shopmonney);
		entity.setCusEntats(1);//修改为封单
		entity.setSettlement(entity.getCusEnstrustNum().replace("FW", "JS"));
		cusTrSvc.save(entity);
		ResponseMsgUtil.operSuccessFullClose(response, "结算成功");

	}
	
	/**
	 * 确认收款界面
	 * */
	@RequestMapping("/fourshop/pay.html")
	public String toPay(HttpServletRequest request,
			HttpServletResponse response, ModelMap model,
			@RequestParam("entrustId") Integer entrustId) {
		CusEntrusAmout amout = cusTrSvc.getAmont(entrustId);
		model.addAttribute("amout", amout);
		model.addAttribute("entrustId", entrustId);
		CustomerEntrust custEntrus = cusTrSvc.get(entrustId);
		model.addAttribute("custEntrus", custEntrus);
		return "/fourshop/pay";
	}
	
	/**
	 * 确认收款
	 * */
	@RequestMapping("/fourshop/savepay.html")
	public void savePay(HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam("entrustId") Integer entrustId,
			@RequestParam("amount") Double amount) {
		CustomerEntrust entity = cusTrSvc.get(entrustId);
		entity.setAmount(amount);
		entity.setCusEntats(3);//修改为已经结清
		cusTrSvc.save(entity);
		ResponseMsgUtil.operSuccessFullClose(response, "收款成功");

	}
}
