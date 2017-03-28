package com.zoomoor.jy.action;

import static com.zoomoor.admin.service.SysAuthenticationSvc.AUTH_USER;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.zoomoor.admin.entity.SysUser;
import com.zoomoor.common.base.bean.Pager;
import com.zoomoor.common.web.session.SessionProvider;
import com.zoomoor.jy.entity.CustomeOrderstatus;
import com.zoomoor.jy.entity.CustomerEntrust;
import com.zoomoor.jy.entity.CustomerFixaddr;
import com.zoomoor.jy.entity.InfoCustome;
import com.zoomoor.jy.entity.InfoEmp;
import com.zoomoor.jy.entity.Servicetype;
import com.zoomoor.jy.entity.vo.CusEntrusAmout;
import com.zoomoor.jy.service.CustomeOrderstatusSvc;
import com.zoomoor.jy.service.CustomerEntrustSvc;
import com.zoomoor.jy.service.CustomerFixaddrSvc;
import com.zoomoor.jy.service.InfoCustomeSvc;
import com.zoomoor.jy.service.InfoEmpSvc;
import com.zoomoor.jy.service.ServicetypeSvc;
import com.zoomoor.jy.util.MathUtil;
import com.zoomoor.jy.util.ResponseMsgUtil;

/**
 * 描述：FinanceAct财务管理
 * 
 * @author Zhangzhenxing
 * @Date 2015年8月13日 上午11:53:09
 * @version 1.0
 */
@Controller
@Transactional
public class FinanceAct {

	private static final Logger log = LoggerFactory.getLogger(FinanceAct.class);

	/**
	 * 委托单管理服务
	 * */
	@Autowired
	private CustomerEntrustSvc customerSvc;

	/**
	 * session管理
	 * */
	@Autowired
	private SessionProvider session;

	/**
	 * 服务类型
	 * */
	@Autowired
	private ServicetypeSvc serviceSvc;

	/**
	 * 员工管理服务
	 * */
	@Autowired
	private InfoEmpSvc empSvc;

	/**
	 * 4s点管理地址类
	 * */
	@Autowired
	private CustomerFixaddrSvc fixaddrSvc;
	
	/**
	 * 客户委托单状态
	 * */
	@Autowired
	private CustomeOrderstatusSvc orderSvc;
	
	/**
	 * 客户管理
	 * */
	@Autowired
	private InfoCustomeSvc customer;

	/**
	 * 描述：结算管理
	 * 
	 * @param queryCarInf
	 *            车主信息
	 * @param queryPlatenum
	 *            车牌号
	 * @param queryStatu
	 *            结算状态
	 * @param queryFixplace
	 *            维修地点
	 * @param serviceType
	 *            服务类型
	 * @param queryTime
	 *            开单时间
	 * */
	@RequestMapping("/finance/cusenlist.do")
	public String getCustomeEntrust(HttpServletRequest request,
			HttpServletResponse response, ModelMap model, Pager pager,
			String queryCarInf, String queryPlatenum, Integer queryStatu,
			Integer queryFixplace, Integer serviceType, String queryBeginTime,
			String queryEndTime) {
		SysUser user = session.getUserSession(request, AUTH_USER);
		Integer deptId = user.getInfoEmp().getInfoDept().getDeptId();
		// 分页
		pager = customerSvc.getSettlementPager(pager, queryCarInf,
				queryPlatenum, queryStatu, queryFixplace, serviceType,
				queryBeginTime, queryEndTime, deptId);
		model.addAttribute("pager", pager);
		// 统计
//		List<Map<Integer, Integer>> countMap = customerSvc
//				.getSettlementCount(deptId);
		model.addAttribute("countMap", "countMap");
		// 服务类型
		List<Servicetype> services = serviceSvc.getAll();
		model.addAttribute("services", services);
		// 服务接待人
		List<InfoEmp> emps = empSvc.getEmpByType(null, user.getInfoEmp()
				.getInfoDept().getDeptId());
		model.addAttribute("emps", emps);
		// 服务地址
		List<CustomerFixaddr> addrList = fixaddrSvc.getAddrsList(null);
		model.addAttribute("addrList", addrList);
		// 查询条件
		model.addAttribute("queryCarInf", queryCarInf);
		model.addAttribute("queryPlatenum", queryPlatenum);
		model.addAttribute("queryCarInf", queryCarInf);
		model.addAttribute("queryStatu", queryStatu);
		model.addAttribute("queryFixplace", queryFixplace);
		model.addAttribute("queryCarInf", queryCarInf);
		model.addAttribute("serviceType", serviceType);
		model.addAttribute("queryBeginTime", queryBeginTime);
		model.addAttribute("queryEndTime", queryEndTime);
		model.addAttribute("user", user);
		return "finance/cusenlist";
	}

	/**
	 * 描述:挂账界面
	 * */
	@RequestMapping("/settlement/bill.do")
	public String setbillSetle(HttpServletRequest request,
			HttpServletResponse response, ModelMap model,
			@RequestParam("entrustId") Integer entrustId) {
		createSetVal(request, response, model, entrustId);
		return "finance/billsettle";
	}

	/**
	 * 描述:结清界面
	 * */
	@RequestMapping("/settlement/settle.do")
	public String setSettle(HttpServletRequest request,
			HttpServletResponse response, ModelMap model,
			@RequestParam("entrustId") Integer entrustId) {
		createSetVal(request, response, model, entrustId);
		return "finance/settle";
	}

	/**
	 * 结清保存
	 * */
	@RequestMapping("/settlement/settlesave.do")
	public void setSettleSave(HttpServletRequest request,
			HttpServletResponse response, ModelMap model,
			@RequestParam("entrustId") Integer entrustId,
			@RequestParam("reallymoney") Double reallymoney) {
		SysUser user = session.getUserSession(request, AUTH_USER);
		customerSvc.setSettleSave(entrustId, reallymoney, user.getInfoEmp().getEmpId());
		log.info("entrustId=" + entrustId + "结清设置");
		ResponseMsgUtil.operSuccessFullClose(response, "结清成功");
	}
	
	/**
	 * 设置挂账
	 * */
	@RequestMapping("/settlement/billsave.do")
	public void setBillsave(HttpServletRequest request,
			HttpServletResponse response, ModelMap model,
			@RequestParam("entrustId") Integer entrustId,
			@RequestParam("billcustomerID") Integer billcustomerID){
		SysUser user = session.getUserSession(request, AUTH_USER);
		customerSvc.setBillsave(entrustId, billcustomerID, user.getInfoEmp().getEmpId());
		log.info("entrustId=" + entrustId + "挂账设置");
		ResponseMsgUtil.operSuccessFullClose(response, "挂账成功");
	}
	
	/**
	 * 描述:设置挂账结清
	 * */
	@RequestMapping("/settlement/billsettle.do")
	public String setBill(HttpServletRequest request,
			HttpServletResponse response, ModelMap model,
			@RequestParam("entrustId") Integer entrustId){
		createSetVal(request, response, model, entrustId);
		CustomeOrderstatus order = orderSvc.getOrder(entrustId, 2);
		model.addAttribute("order", order);
		InfoCustome cus = customer.get(order.getTagId());
		model.addAttribute("cus", cus);
		model.addAttribute("entrustId", entrustId);
		return "finance/bill";
	}
	
	/**
	 * 描述:挂账结清保存
	 * */
	@RequestMapping("/settlement/billsettlesave.do")
	public void billsettlesave(HttpServletRequest request,
			HttpServletResponse response, @RequestParam("entrustId") Integer entrustId
			,@RequestParam("reallymoney")Double reallymoney){
		SysUser user = session.getUserSession(request, AUTH_USER);
		customerSvc.billsettlesave(entrustId, reallymoney, user.getInfoEmp().getEmpId());
		ResponseMsgUtil.operSuccessFullClose(response, "挂账结清成功");
	}
	/**
	 * 描述:缓存各类值
	 * */
	private void  createSetVal(HttpServletRequest request,
			HttpServletResponse response, ModelMap model,
			@RequestParam("entrustId") Integer entrustId){
		CusEntrusAmout amout = customerSvc.getAmont(entrustId);
		SysUser user = session.getUserSession(request, AUTH_USER);
		//应收费用=折扣系数*总费用
		Double payAmout = amout.getDiscount()*amout.getPayAmout();
		//优惠金额= 总费用-应收费用
		Double lessAmout = amout.getPayAmout() - payAmout;
		amout.setSaleAmout(MathUtil.toFixed(lessAmout, 2));
		payAmout = MathUtil.toFixed(payAmout, 2);
		
		model.addAttribute("payAmout", payAmout);
		model.addAttribute("user", user);
		Date now = new Date(System.currentTimeMillis());
		model.addAttribute("now", MathUtil.fmtDate(now, "yyyy-MM-dd HH:mm:ss"));
		model.addAttribute("amout", amout);
		model.addAttribute("entrustId", entrustId);
	}

}
