package com.zoomoor.jy.action;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zoomoor.admin.entity.SysUser;
import com.zoomoor.admin.service.SysAuthenticationSvc;
import com.zoomoor.common.base.bean.Pager;
import com.zoomoor.common.web.session.SessionProvider;
import com.zoomoor.jy.dao.InfoGoodsDao;
import com.zoomoor.jy.entity.CustomerAppoit;
import com.zoomoor.jy.entity.CustomerEntrust;
import com.zoomoor.jy.entity.CustomerFixaddr;
import com.zoomoor.jy.entity.InfoCar;
import com.zoomoor.jy.entity.InfoCustome;
import com.zoomoor.jy.entity.InfoEmp;
import com.zoomoor.jy.entity.InfoGoods;
import com.zoomoor.jy.entity.Servicetype;
import com.zoomoor.jy.entity.vo.CusEntrusAmout;
import com.zoomoor.jy.service.CustomerAppoitSvc;
import com.zoomoor.jy.service.CustomerEntrustSubSvc;
import com.zoomoor.jy.service.CustomerEntrustSvc;
import com.zoomoor.jy.service.CustomerFixaddrSvc;
import com.zoomoor.jy.service.InfoEmpSvc;
import com.zoomoor.jy.service.ServiceitemScv;
import com.zoomoor.jy.service.ServicetypeSvc;
import com.zoomoor.jy.util.ResponseMsgUtil;

/**
 * 描述：客户委托单
 * 
 * @author Zhangzhenxing
 * @Date 2015年7月31日 下午1:58:00
 * @version 1.0
 */
@Controller
@Transactional
public class CustomEntrustAct {

	private static final Logger log = LoggerFactory
			.getLogger(CustomEntrustAct.class);

	/**
	 * 委托订单管理
	 * */
	@Autowired
	private CustomerEntrustSvc cusEntrSvc;

	/**
	 * 委托订单子表
	 * */
	@Autowired
	CustomerEntrustSubSvc cusEntrsubSvc;

	/**
	 * 预约单管理服务
	 * */
	@Autowired
	private CustomerAppoitSvc cusAppSvc;

	@Autowired
	private ServiceitemScv serSvc;

	/**
	 * 用户session
	 * */
	@Autowired
	private SessionProvider session;
	
	/**
	 *物料查询DAO
	 * */
	@Autowired
	private InfoGoodsDao goodsDao;

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
	 * 服务类型管理
	 * */
	@Autowired
	private ServicetypeSvc typeSvc;

	/**
	 * 描述:服务委托单列表
	 * 
	 * @param currenIndex
	 *            当前页签下标
	 * @param queryPlatenum
	 *            车牌号
	 * @param queryCusName
	 *            车主姓名
	 * @param querySpeed
	 *            服务进度
	 * @param queryFixplace
	 *            服务位置
	 * @param queryServiceType
	 *            服务类型
	 * @param queryOrderTime
	 *            开单时间
	 * @param queryOperator
	 *            开单人
	 * @param queryFixMan
	 *            维修工
	 * */
	@RequestMapping("service/customerorder/list.do")
	public String list(HttpServletRequest request,
			HttpServletResponse response, Pager pager, ModelMap model,
			String queryOrderNO, String queryPlatenum, String queryCusName,
			String queryStatus, String queryFixplace, String queryAppType,
			String queryOrderBeginTime, String queryOrderEndTime,
			String queryOperator, String queryFixMan) {
		// 当前登录人所在店铺
		addModel(request, model);
		// 若tab下标为空 设置默认值为0
		// 得带数量统计
		Integer fixplace = 0;
		if (queryFixplace != null && !"".equals(queryFixplace)) {
			fixplace = Integer.parseInt(queryFixplace);
		}
		// 查询
		SysUser user = session.getUserSession(request,
				SysAuthenticationSvc.AUTH_USER);
		Integer empId = user.getInfoEmp().getInfoDept().getDeptId();

		pager = cusEntrSvc.getPager(pager, queryOrderNO, queryPlatenum,
				queryCusName, queryStatus, String.valueOf(fixplace),
				queryAppType, queryOrderBeginTime, queryOrderEndTime,
				queryOperator, queryFixMan, empId);
		// 测试代码 user=null
		model.addAttribute("pager", pager);
		Integer qStatus = 0;
		if (StringUtils.isNotEmpty(queryStatus)) {
			qStatus = Integer.parseInt(queryStatus);
		}
		Integer fixPlcae = 0;
		if (StringUtils.isNotEmpty(queryFixplace)) {
			fixplace = Integer.parseInt(queryFixplace);
		}
		// List list = cusEntrSvc.getCustomerList(empId, queryOrderNO,
		// queryPlatenum, queryCusName, qStatus, fixPlcae,
		// queryOrderBeginTime, queryOrderEndTime, queryOperator,
		// queryFixMan);
		// model.addAttribute("list", list);

		List<Map<Integer, Integer>> countMap = cusEntrSvc.getCustomeCount(
				empId, fixplace);
		Integer countAll = 0;
		// 订单状态 0 1 2 3 进行中 结算 挂账 已结清
		Integer[] status = { 0, 1, 2, 3 };
		if (countMap != null && countMap.size() > 0) {
			Set<Integer> keys = null;
			Map<Integer, Integer> map = null;
			for (int i = 0, j = 0; i < status.length; i++) {
				if (j < countMap.size()) {
					map = countMap.get(j);
					keys = map.keySet();
				}
				Integer count = 0;
				// 迭代出统计key
				Integer key = keys.iterator().next();
				if (key == status[i]) {
					count = map.get(key);
					j++;
				}
				model.addAttribute("count" + i, count);
				countAll += count;
			}
		}

		model.addAttribute("countAll", countAll);

		List<InfoEmp> emps = empSvc.getEmpByType(null, user.getInfoEmp()
				.getInfoDept().getDeptId());
		model.addAttribute("emps", emps);
		List<Servicetype> serviceItem = typeSvc.getAll();
		model.addAttribute("serviceItem", serviceItem);
		// 缓存查询字段
		model.addAttribute("queryOrderNO", queryOrderNO);
		model.addAttribute("queryPlatenum", queryPlatenum);
		model.addAttribute("queryCusName", queryCusName);
		model.addAttribute("queryStatus", queryStatus);
		model.addAttribute("queryFixplace", queryFixplace);
		model.addAttribute("queryAppType", queryAppType);
		model.addAttribute("queryOrderBeginTime", queryOrderBeginTime);
		model.addAttribute("queryOrderEndTime", queryOrderEndTime);
		model.addAttribute("queryOperator", queryOperator);
		model.addAttribute("queryFixMan", queryFixMan);
		model.addAttribute("queryOperator", queryOperator);
		return "customerorder/list";
	}

	/**
	 * 预约订单查询
	 * 
	 * @param currenIndex
	 *            当前下标
	 * @param queryTel
	 *            客户信息(电话或姓名)
	 * @param queryPlatenum
	 *            车牌
	 * @param queryAppType
	 *            服务类型
	 * @param querySource
	 *            来源
	 * @param queryBegintime
	 *            开单开始时间
	 * @param queryEndtime
	 *            开单结束时间
	 * @param querySellstart
	 *            预约开始时间
	 * @param querySellend
	 *            预约结束时间
	 * @param queryStatus
	 *            预约状态 说明:待确认 0 已确认 待处理 1 已处理履约 2 订单取消4
	 * */
	@RequestMapping("service/order/list.do")
	public String orderList(HttpServletRequest request,
			HttpServletResponse response, Pager pager, ModelMap model,
			Integer currenIndex, String queryTel, String queryPlatenum,
			Integer queryAppType, Integer querySource, String queryBegintime,
			String queryEndtime, String querySellstart, String querySellend,
			Integer queryStatus) {
		// 分页查询 pagerAll pagercancel pagerUnhander pagerIshander
		pager = cusAppSvc.getPager(pager, queryTel, queryPlatenum,
				queryAppType, querySource, queryBegintime, queryEndtime,
				querySellstart, querySellend, queryStatus);
		// 数量统计
		List<Map<Integer, Integer>> mapCount = cusAppSvc.getCountCus();
		Integer counAll = 0;
		Integer[] status = { 0, 1, 2, 4 };
		if (mapCount != null && mapCount.size() > 0) {
			Set<Integer> keys = null;
			Map<Integer, Integer> map = null;
			for (int i = 0, j = 0; i < status.length; i++) {
				if (j < mapCount.size()) {
					map = mapCount.get(j);
					keys = map.keySet();
				}
				Integer count = 0;
				// 迭代出统计key
				Integer key = keys.iterator().next();
				if (key == status[i]) {
					count = map.get(key);
					j++;
				}
				model.addAttribute("count" + i, count);
				counAll += count;
			}
		}
		List<Servicetype> serviceItem = typeSvc.getAll();
		model.addAttribute("serviceItem", serviceItem);
		model.addAttribute("counAll", counAll);
		// 保存数据
		model.addAttribute("currenIndex", currenIndex);
		model.addAttribute("queryTel", queryTel);
		model.addAttribute("queryPlatenum", queryPlatenum);
		model.addAttribute("queryAppType", queryAppType);
		model.addAttribute("querySource", querySource);
		model.addAttribute("queryBegintime", queryBegintime);
		model.addAttribute("queryEndtime", queryEndtime);
		model.addAttribute("querySellstart", querySellstart);
		model.addAttribute("querySellend", querySellend);
		model.addAttribute("queryStatus", queryStatus);
		// 分页数据
		model.addAttribute("pager", pager);

		// 数量统计
		return "customerorder/order/list";
	}

	/**
	 * 创建服务订单
	 * 
	 * @param cusappoitId
	 *            预约单号
	 * */
	@RequestMapping("/customer/createorder.do")
	public String createOrder(HttpServletRequest request,
			HttpServletResponse response, ModelMap model, Integer cusappoitId) {
		if (cusappoitId != null) {
			CustomerAppoit customer = cusAppSvc.get(cusappoitId);
			model.addAttribute("customer", customer);
		}
		// 当前时间
		model.addAttribute("createtime", new Date(System.currentTimeMillis()));
		// 当前登录人所在店铺
		SysUser user = session.getUserSession(request,
				SysAuthenticationSvc.AUTH_USER);
		addModel(request, model);
		// 服务接待人列表
		List<InfoEmp> emps = empSvc.getEmpByType(null, user.getInfoEmp()
				.getInfoDept().getDeptId());
		model.addAttribute("emps", emps);
		return "customerorder/add";
	}

	/**
	 * 描述:选中服务项目lookup
	 * */
	// @RequestMapping("serviceItem/lookup.do")
	public String serviceItemLookup(HttpServletRequest request, Pager pager,
			ModelMap model, String queryItemCode, String queryItemName,
			String itemIdS, Integer carId) {
		if (carId == null)
			carId = -1;
		pager = serSvc.buildList(pager, queryItemCode, queryItemName, itemIdS,
				carId);
		model.addAttribute("queryItemCode", queryItemCode);
		model.addAttribute("queryItemName", queryItemName);
		model.addAttribute("itemIdS", itemIdS);
		model.addAttribute("pager", pager);
		model.addAttribute("carId", carId);
		return "customerorder/servicelookup";
	}

	/**
	 * 描述:创建服务单筛选服务列表
	 * */
	@RequestMapping("serviceItem/lookup.do")
	public String serviceListLookup(HttpServletRequest request, ModelMap model,
			String queryItemCode, String queryItemName, String itemIdS,
			Integer carId) {
		List list = serSvc.getServiceItem(queryItemCode, queryItemName,
				itemIdS, carId);
		model.addAttribute("queryItemCode", queryItemCode);
		model.addAttribute("queryItemName", queryItemName);
		model.addAttribute("itemIdS", itemIdS);
		model.addAttribute("list", list);
		model.addAttribute("carId", carId);
		return "customerorder/listservicelookup";
	}

	/**
	 * 保存委托单
	 * */
	@RequestMapping("serviceorder/savecreate.do")
	public void saveCustom(HttpServletRequest request,
			HttpServletResponse response, CustomerEntrust customEn,
			InfoCustome infoCus, InfoCar infoCar) {
		cusEntrSvc.saveCustomEntrus(customEn, infoCus, infoCar);
		ResponseMsgUtil.operSuccessFullClose(response, "创建委托单成功");
	}

	@RequestMapping("service/customerorder/edit.do")
	public String cusEdit(HttpServletRequest request,
			HttpServletResponse response, ModelMap model,
			@RequestParam("entrustId") Integer entrustId) {
		SysUser user = session.getUserSession(request,
				SysAuthenticationSvc.AUTH_USER);
		addModel(request, model);
		// 服务接待人列表
		List<InfoEmp> emps = empSvc.getEmpByType(null, user.getInfoEmp()
				.getInfoDept().getDeptId());
		model.addAttribute("emps", emps);

		addCustomerEntrust(model, entrustId);
		return "customerorder/edit";
	}

	/**
	 * 描述:更新
	 * */
	@RequestMapping("serviceorder/saveupdate.do")
	public void cusUpdate(HttpServletRequest request,
			HttpServletResponse response, CustomerEntrust customEn,
			InfoCustome infoCus, InfoCar infoCar) {
		cusEntrSvc.updateCustomEntrus(customEn, infoCus, infoCar);
		ResponseMsgUtil.operSuccessFullClose(response, "修改成功");
	}

	/**
	 * 描述:查看
	 * */
	@RequestMapping("service/customerorder/scan.do")
	public String scanCus(HttpServletRequest request,
			HttpServletResponse response, ModelMap model,
			@RequestParam("entrustId") Integer entrustId) {
		SysUser user = session.getUserSession(request,
				SysAuthenticationSvc.AUTH_USER);
		addModel(request, model);
		// 服务接待人列表
		if(user != null){
			List<InfoEmp> emps = empSvc.getEmpByType(null, user.getInfoEmp()
					.getInfoDept().getDeptId());
			model.addAttribute("emps", emps);
		}
		addCustomerEntrust(model, entrustId);
		return "customerorder/view";
	}

	/**
	 * 删除委托单:只能删除未封单的委托单
	 * */
	@RequestMapping("service/customerorder/delete.do")
	public void deleteCustomerEntrusOrder(HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam("entrustId") Integer entrustId) {
		log.info("委托单ID = " + entrustId + "逻辑删除");
		cusEntrSvc.deleteByOrderID(entrustId);
		ResponseMsgUtil.operSuccessFull(response, "删除成功");
	}

	/**
	 * 描述:结算
	 * */
	@RequestMapping("service/customerorder/amout.do")
	public String amoutView(HttpServletRequest request,
			HttpServletResponse response, ModelMap model,
			@RequestParam("entrustId") Integer entrustId) {
		CusEntrusAmout amout = cusEntrSvc.getAmont(entrustId);
		SysUser user = session.getUserSession(request,
				SysAuthenticationSvc.AUTH_USER);
		model.addAttribute("accountmanID", user.getInfoEmp().getEmpId());
		model.addAttribute("amout", amout);
		model.addAttribute("entrustId", entrustId);
		return "customerorder/amout";
	}

	/**
	 * 描述:封单结算
	 * */
	@RequestMapping("service/customerorder/goamout.do")
	public void amoutMath(HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam("entrustId") Integer entrustId,
			@RequestParam("workHourAmout") Double workHourAmout,
			@RequestParam("goodsAmout") Double goodsAmout,
			@RequestParam("accountmanID") Integer accountmanID) {
		boolean isOk = cusEntrSvc.amount(entrustId, accountmanID,
				workHourAmout, goodsAmout);
		log.info("ID = " + entrustId + "结算");
		if (isOk) {
			ResponseMsgUtil.operSuccessFullClose(response, "结算成功");
		} else
			ResponseMsgUtil.operFaildClose(response, "结算失败");
	}

	/**
	 * 设置折扣系数
	 * */
	@RequestMapping("service/customerorder/setdiscount.do")
	public String setDiscount(HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam("entrustId") Integer entrustId, ModelMap model) {
		CustomerEntrust cusEntrust = cusEntrSvc.get(entrustId);
		model.addAttribute("cusEntrust", cusEntrust);
		SysUser user = session.getUserSession(request,
				SysAuthenticationSvc.AUTH_USER);
		model.addAttribute("discounter", user);
		return "customerorder/setsale";

	}

	/**
	 * 保存折扣、折扣后金额、打折人
	 * */
	@RequestMapping("service/customerorder/discountsave.do")
	public void saveDiscount(HttpServletRequest request,
			HttpServletResponse response, CustomerEntrust cusEntrust) {
		// 持久对象
		CustomerEntrust cusEntity = cusEntrSvc.get(cusEntrust.getEntrustId());
		Integer discounter = cusEntrust.getDiscounter();// 折扣人
		Double discount = cusEntrust.getDiscount();// 折扣
		Double amount = cusEntity.getAmount();// 折扣后金额
		cusEntity.setAmount(amount);
		cusEntity.setDiscount(discount);
		cusEntity.setDiscounter(discounter);
		cusEntrSvc.save(cusEntity);
		ResponseMsgUtil.operSuccessFullClose(response, "折扣系数设置成功");
	}

	/**
	 * 描述：ajax货物列表 根据当前所选择服务项目查询服务项目多对应货物信息
	 * */
	@SuppressWarnings("rawtypes")
	@ResponseBody
	@RequestMapping("/customerorder/servicegoods.ajax")
	public List<InfoGoods> getServiceGoods(HttpServletRequest request,
			HttpServletResponse response, String itemsIds) {
		return null;
	}

	/**
	 * 将当当前门店和4s点加入ModelMap
	 * */
	private void addModel(HttpServletRequest request, ModelMap model) {
		SysUser user = session.getUserSession(request,
				SysAuthenticationSvc.AUTH_USER);
		if(user != null){
			model.addAttribute("user", user);
			model.addAttribute("dept", user.getInfoEmp().getInfoDept());
		}
		List<CustomerFixaddr> addrList = fixaddrSvc.getAddrsList(null);
		model.addAttribute("addrList", addrList);
	}

	/**
	 * 将委托对象装入缓存
	 * */
	private void addCustomerEntrust(ModelMap model, Integer entrustId) {
		if (entrustId != null) {
			CustomerEntrust cusEntity = cusEntrSvc.get(entrustId);
			model.addAttribute("cusEntity", cusEntity);
			Integer cusappoitId = cusEntity.getCusappoitId();
			if (cusappoitId != null && cusappoitId != 0) {
				CustomerAppoit cusAppentity = cusAppSvc.get(cusappoitId);
				model.addAttribute("cusAppentity", cusAppentity);
			}
		} else
			log.info("该次操作 entrustId 为空");

	}
}
