package com.zoomoor.jy.action;

import java.util.Date;
import java.util.List;

import javax.jws.WebParam.Mode;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sun.org.apache.xpath.internal.operations.Mod;
import com.zoomoor.admin.entity.SysUser;
import com.zoomoor.admin.service.SysAuthenticationSvc;
import com.zoomoor.common.web.session.SessionProvider;
import com.zoomoor.jy.entity.CustomeOrderstatus;
import com.zoomoor.jy.entity.CustomerEntrust;
import com.zoomoor.jy.entity.CustomerEntrustSub;
import com.zoomoor.jy.entity.CustomerFixaddr;
import com.zoomoor.jy.entity.InfoEmp;
import com.zoomoor.jy.entity.InfoGoods;
import com.zoomoor.jy.entity.vo.CusEntrusAmout;
import com.zoomoor.jy.entity.vo.CusEntrustGoodsVo;
import com.zoomoor.jy.service.CustomeOrderstatusSvc;
import com.zoomoor.jy.service.CustomerEntrustSubSvc;
import com.zoomoor.jy.service.CustomerEntrustSvc;
import com.zoomoor.jy.service.CustomerFixaddrSvc;
import com.zoomoor.jy.service.InfoEmpSvc;
import com.zoomoor.jy.util.MathUtil;

/**
 * 描述：打印
 * 
 * @author Zhangzhenxing
 * @Date 2015年8月11日 下午5:50:59
 * @version 1.0
 */
@Controller
public class FourShopPrint {

	/**
	 * 客户委托单管理
	 * */
	@Autowired
	private CustomerEntrustSvc cusSvc;
	
	/**
	 * 4s点地址
	 * */
	@Autowired
	private CustomerFixaddrSvc fixAddrSvc;
	
	/**
	 * session 对象
	 * */
	@Autowired 
	private SessionProvider session;
	
	/**
	 * 员工信息管理
	 * */
	@Autowired
	private InfoEmpSvc infoEmpSvc;
	
	/**
	 * 委托单操作记录
	 * */
	@Autowired
	private CustomeOrderstatusSvc subSvc;
	
	/**
	 * 打印服务委托单
	 * */
	@RequestMapping("service/customerorder/print.do")
	public String customeEntrustPrint(HttpServletRequest request,
			HttpServletResponse response, ModelMap model, Integer entrustId) {
		CustomerEntrust customerEntrust = cusSvc.get(entrustId);
		model.addAttribute("customerEntrust", customerEntrust);
		Integer fixplace = customerEntrust.getFixplace();
		if(fixplace != 0){
			CustomerFixaddr fixAddr = fixAddrSvc.get(fixplace);
			model.addAttribute("fixAddr", fixAddr);
		}
		//服务接待人
		Integer receptionist = customerEntrust.getReceptionist();//服务接待人
		InfoEmp infoEmp = infoEmpSvc.get(receptionist);
		model.addAttribute("infoEmp", infoEmp);
		CusEntrusAmout amout = cusSvc.getAmont(entrustId);
		model.addAttribute("amout", amout);
		Date date = new Date(System.currentTimeMillis());
		String time = MathUtil.fmtDate(date, "yyyy-MM-dd HH:mm:ss");
		model.addAttribute("time", time);
		//制单人
		SysUser user = session.getUserSession(request,
				SysAuthenticationSvc.AUTH_USER);
		model.addAttribute("user", user);
		return "print/customeTrust";
	}
	
	/**
	 * 描述:打印结算单
	 * */
	@RequestMapping("/service/finance/print.do")
	public String settlePrint(HttpServletRequest request,
			HttpServletResponse response, ModelMap model, Integer entrustId){
		//配件列表
		//收款时间
		CustomerEntrust customerEntrust = cusSvc.get(entrustId);
		model.addAttribute("customerEntrust", customerEntrust);
		//操作人
		SysUser user = session.getUserSession(request,
				SysAuthenticationSvc.AUTH_USER);
		model.addAttribute("user", user);
		
		//操作时间
		Date date = new Date(System.currentTimeMillis());
		String time = MathUtil.fmtDate(date, "yyyy-MM-dd HH:mm:ss");
		model.addAttribute("time", time);
		//结算信息
		CusEntrusAmout amout = cusSvc.getAmont(entrustId);
		model.addAttribute("amout", amout);
		//所使用物料信息
		List<CusEntrustGoodsVo> goods = cusSvc.getGoodsByEntrustId(entrustId);
		model.addAttribute("goods", goods);
		//所用物料总价
		Double cumPrice = cusSvc.getEntrustPrice(entrustId);
		model.addAttribute("cumPrice", cumPrice);
		//结算时间
		
		Object[] objs = {customerEntrust,3};
		CustomeOrderstatus order = subSvc.getOrder(entrustId, 3);
		model.addAttribute("order", order);
		return "print/settle";
	}

}
