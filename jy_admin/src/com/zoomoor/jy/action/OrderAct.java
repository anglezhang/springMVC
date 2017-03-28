package com.zoomoor.jy.action;

import static com.zoomoor.admin.service.SysAuthenticationSvc.AUTH_USER;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zoomoor.admin.entity.SysUser;
import com.zoomoor.admin.service.SysLogSvc;
import com.zoomoor.common.base.bean.Pager;
import com.zoomoor.common.web.ResponseUtils;
import com.zoomoor.common.web.session.SessionProvider;
import com.zoomoor.jy.entity.OrderAudit;
import com.zoomoor.jy.entity.OrderList;
import com.zoomoor.jy.entity.OrderPurchase;
import com.zoomoor.jy.entity.ParamConfig;
import com.zoomoor.jy.service.OrderAuditSvc;
import com.zoomoor.jy.service.OrderListSvc;
import com.zoomoor.jy.service.OrderSvc;
import com.zoomoor.jy.service.ParamConfigSvc;
import com.zoomoor.jy.util.ExportUtil;

@SuppressWarnings("unchecked")
@Controller
public class OrderAct {
	private static final Logger log = LoggerFactory.getLogger(OrderAct.class);
	@Autowired
	private SysLogSvc sysLogSvc;
	@Autowired
	private OrderSvc orderSvc;
	@Autowired
	private SessionProvider session;
	@Autowired
	private ParamConfigSvc configSvc;
	@Autowired
	private OrderListSvc orderListSvc;
	@Autowired
	private OrderAuditSvc auditSvc;

	@RequestMapping("/order/list.do")
	public String list(HttpServletRequest request, Pager pager,
			String queryOrderNo, String queryEmpName, String querySubName,
			Integer queryStatus, String queryStartDate, String queryEndDate,
			Integer status, Integer auditType, ModelMap model) {
		pager = orderSvc.getPage(queryOrderNo, queryEmpName, querySubName,
				queryStatus, auditType, queryStartDate, queryEndDate,
				pager.getPageNum(), pager.getNumPerPage());
		model.addAttribute("status", status);
		model.addAttribute("pager", pager);
		model.addAttribute("queryOrderNo", queryOrderNo);
		model.addAttribute("queryEmpName", queryEmpName);
		model.addAttribute("querySubName", querySubName);
		model.addAttribute("queryStatus", queryStatus);
		model.addAttribute("queryStartDate", queryStartDate);
		model.addAttribute("queryEndDate", queryEndDate);
		model.addAttribute("auditType", auditType);
		SysUser sysUser = session.getUserSession(request, AUTH_USER);
		model.addAttribute("sysUser", sysUser);
		return "order/list";
	}

	/**
	 * @Title: add
	 * @Description: 跳转至采购订单创建
	 * @return String
	 * @throws
	 */
	@RequestMapping("/order/add.do")
	public String add(HttpServletRequest request, ModelMap model) {
		SysUser sysUser = session.getUserSession(request, AUTH_USER);
		model.addAttribute("sysUser", sysUser);
		// 查询付款方式
		String[] prom = { "del", "paramType" };
		Object[] objetm = { false, 4 };
		List<ParamConfig> paramConfigList = configSvc.getList(prom, objetm);
		model.addAttribute("paramConfigList", paramConfigList);
		// 查询单位
		String[] unitprom = { "del", "paramType" };
		Object[] unitobjetm = { false, 1 };
		List<ParamConfig> unitparam = configSvc.getList(unitprom, unitobjetm);
		model.addAttribute("unitparam", unitparam);
		return "order/add";
	}

	/**
	 * @throws Exception
	 * @throws IOException
	 * @throws FileNotFoundException
	 * @Title: export
	 * @Description: 导出excl
	 * @return String
	 * @throws
	 */
	@ResponseBody
	@RequestMapping("/order/export.do")
	public String export(Pager pager, HttpServletRequest request,
			HttpServletResponse response) throws FileNotFoundException,
			IOException, Exception {
		pager = orderSvc.getPage(null, null, null, null, null, null, null,
				pager.getPageNum(), pager.getNumPerPage());
		String sheetName = "采购单";
		String fileName = "采购单表格";
		// 存放内容的list
		List<List<String>> rowslists = new ArrayList<List<String>>();
		String[] cells = new String[8];
		cells[0] = "采购单号";
		cells[1] = "操作人";
		cells[2] = "开单日期";
		cells[3] = "供应商名称";
		cells[4] = "总金额";
		cells[5] = "采购状态";
		cells[6] = "实际入库总额";
		cells[7] = "备注";
		if (pager.getList() != null && pager.getList().size() > 0) {
			List<OrderPurchase> orderList = pager.getList();
			for (OrderPurchase order : orderList) {
				List<String> list = new ArrayList<String>();
				list.add(order.getPurOrderNo());
				list.add(order.getInfoEmp().getEmpName());
				list.add(order.getPurOrderDate().toString());
				list.add(order.getInfoSup().getName());
				list.add(order.getCountPrice().toString());
				String auditstr = "";
				if (order.getAuditType() != null) {
					if (order.getAuditType() == 1) {
						auditstr = "采购审核";

					}
					if (order.getAuditType() == 2) {
						auditstr = "财务审核";

					}
					if (order.getAuditType() == 3) {
						auditstr = "总经理审核";

					}
				}
				String statustr = "";
				if (order.getStatus() == 0) {
					statustr = "未审核";

				}
				if (order.getStatus() == 1) {
					statustr = "通过";

				}
				if (order.getStatus() == 2) {
					statustr = "不通过";

				}
				list.add(auditstr + statustr);
				list.add(order.getDepotNum() == null ? "0" : order
						.getDepotNum().toString());
				list.add(order.getMemo());
				rowslists.add(list);
			}
		}
		ExportUtil.ExportXls(request, response, sheetName, fileName, cells,
				rowslists);
		return null;
	}

	/**
	 * @Title: save
	 * @Description: 添加
	 * @param request
	 * @param response
	 * @param order
	 * @param orderList
	 *            void
	 * @throws
	 */
	@RequestMapping("/order/save.do")
	public void save(HttpServletRequest request, HttpServletResponse response,
			OrderPurchase order) {
		SysUser sysUser = session.getUserSession(request, AUTH_USER);
		Integer deptId = sysUser.getInfoEmp().getInfoDept().getDeptId();
		Integer orderId = orderSvc.saveOrder(order, deptId);
		log.info("save order id={}", orderId);
		sysLogSvc.operating(request, "order.log.save", "id=" + orderId
				+ ";order_no=" + order.getPurOrderNo());
		JSONObject json = new JSONObject();
		if (orderId > 0) {
			json.put("statusCode", "200");
			json.put("message", "操作成功");
			json.put("callbackType", "closeCurrent");
		}
		ResponseUtils.renderJson(response, json.toJSONString());
	}

	/**
	 * @Title: delete
	 * @Description: 删除
	 * @param request
	 * @param response
	 * @param orderId
	 *            void
	 * @throws
	 */
	@RequestMapping("/order/delete.do")
	public void delete(HttpServletRequest request,
			HttpServletResponse response, Integer orderId) {
		OrderPurchase orderPur = orderSvc.get(orderId);
		orderPur.setDel(true);
		orderSvc.update(orderPur);
		orderListSvc.updateById(orderId);
		log.info("delete order id={}", orderId);
		sysLogSvc.operating(request, "order.log.delete", "id=" + orderId
				+ ";order_no=" + orderPur.getPurOrderNo());
		JSONObject json = new JSONObject();
		if (orderId > 0) {
			json.put("statusCode", "200");
			json.put("message", "操作成功");
		}
		ResponseUtils.renderJson(response, json.toJSONString());
	}

	/**
	 * @Title: add
	 * @Description: 跳转至采购订单修改
	 * @return String
	 * @throws
	 */
	@RequestMapping("/order/edit.do")
	public String edit(HttpServletRequest request, Integer orderId,
			ModelMap model) {
		SysUser sysUser = session.getUserSession(request, AUTH_USER);
		model.addAttribute("sysUser", sysUser);
		OrderPurchase orderPur = orderSvc.get(orderId);
		model.addAttribute("orderPur", orderPur);
		// 查询付款方式
		String[] prom = { "del", "paramType" };
		Object[] objetm = { false, 4 };
		List<ParamConfig> paramConfigList = configSvc.getList(prom, objetm);
		model.addAttribute("paramConfigList", paramConfigList);
		String[] orderpro = { "del", "orderPurchase.purOrderId" };
		Object[] orderobjet = { false, orderId };
		List<OrderList> orderList = orderListSvc.getList(orderpro, orderobjet);
		List<OrderList> neworderList = new ArrayList<OrderList>();
		for (OrderList order : orderList) {
			ParamConfig pc = configSvc.get(order.getUnit());
			order.setUnitName(pc.getName());
			// 不含税金额
			order.setnTaxRateCount(order.getOrderPrice() * order.getOrderNum());
			// 含税金额
			order.setYtaxRateCount(order.getnTaxRateCount()
					* (1 + order.getTaxRate() / 100));
			// 税额
			order.setTaxRateCount(order.getYtaxRateCount()
					- order.getnTaxRateCount());
			// 含税单价
			order.setTaxtRatePrice(order.getOrderPrice()
					* (1 + order.getTaxRate() / 100));
			neworderList.add(order);
		}
		model.addAttribute("orderList", neworderList);
		// 查询单位
		String[] unitprom = { "del", "paramType" };
		Object[] unitobjetm = { false, 1 };
		List<ParamConfig> unitparam = configSvc.getList(unitprom, unitobjetm);
		model.addAttribute("unitparam", unitparam);
		return "order/edit";
	}

	/**
	 * @Title: update
	 * @Description: 修改订单信息
	 * @param request
	 * @param response
	 * @param order
	 * @param orderId
	 *            void
	 * @throws
	 */
	@RequestMapping("/order/update.do")
	public void update(HttpServletRequest request,
			HttpServletResponse response, OrderPurchase order, Integer orderId) {
		// 先删除 后添加
		orderListSvc.deleteById(orderId);
		orderSvc.updateOrder(order, orderId);
		log.info("update order id={}", orderId);
		sysLogSvc.operating(request, "order.log.update", "id=" + orderId
				+ ";order_no=" + order.getPurOrderNo());
		JSONObject json = new JSONObject();
		if (orderId > 0) {
			json.put("statusCode", "200");
			json.put("message", "操作成功");
			json.put("callbackType", "closeCurrent");
		}
		ResponseUtils.renderJson(response, json.toJSONString());
	}

	/**
	 * @Title: add
	 * @Description: 跳转至采购订单修改
	 * @return String
	 * @throws
	 */
	@RequestMapping("/order/view.do")
	public String view(HttpServletRequest request, Integer orderId,
			Integer status, Integer auditType, ModelMap model) {
		SysUser sysUser = session.getUserSession(request, AUTH_USER);
		model.addAttribute("sysUser", sysUser);
		OrderPurchase orderPur = orderSvc.get(orderId);
		model.addAttribute("orderPur", orderPur);
		// 查询付款方式
		String[] prom = { "del", "paramType" };
		Object[] objetm = { false, 4 };
		List<ParamConfig> paramConfigList = configSvc.getList(prom, objetm);
		model.addAttribute("paramConfigList", paramConfigList);
		String[] orderpro = { "del", "orderPurchase.purOrderId" };
		Object[] orderobjet = { false, orderId };
		List<OrderList> orderList = orderListSvc.getList(orderpro, orderobjet);
		List<OrderList> neworderList = new ArrayList<OrderList>();
		for (OrderList order : orderList) {
			ParamConfig pc = configSvc.get(order.getUnit());
			order.setUnitName(pc.getName());
			// 不含税金额
			order.setnTaxRateCount(order.getOrderPrice() * order.getOrderNum());
			// 含税金额
			order.setYtaxRateCount(order.getnTaxRateCount()
					* (1 + order.getTaxRate() / 100));
			// 税额
			order.setTaxRateCount(order.getYtaxRateCount()
					- order.getnTaxRateCount());
			// 含税单价
			order.setTaxtRatePrice(order.getOrderPrice()
					* (1 + order.getTaxRate() / 100));
			neworderList.add(order);
		}
		model.addAttribute("orderList", neworderList);
		List<OrderAudit> auditList = auditSvc.getList(
				"orderPurchase.purOrderId", orderId);
		String cgaudit = "";
		String cwaudit = "";
		model.addAttribute("auditList", auditList);
		if (auditList != null && auditList.size() > 0) {
			for (OrderAudit audit : auditList) {
				if (audit.getAuditType() == 1) {
					cgaudit = audit.getMemo();
				}
				if (audit.getAuditType() == 2) {
					cwaudit = audit.getMemo();
				}

			}

		}
		model.addAttribute("cgaudit", cgaudit);
		model.addAttribute("cwaudit", cwaudit);
		model.addAttribute("status", status);
		model.addAttribute("auditType", auditType);
		// 查询付款方式
		String[] unitprom = { "del", "paramType" };
		Object[] unitobjetm = { false, 1 };
		List<ParamConfig> unitConfigList = configSvc.getList(unitprom,
				unitobjetm);
		model.addAttribute("unitConfigList", unitConfigList);
		return "order/view";
	}

	/**
	 * @Title: audit
	 * @Description: 审核
	 * @param request
	 * @param response
	 * @param orderAudit
	 * @param orderId
	 *            void
	 * @throws
	 */
	@RequestMapping("/order/audit.do")
	public void audit(HttpServletRequest request, HttpServletResponse response,
			OrderAudit orderAudit, Integer orderId) {
		OrderPurchase porder = orderSvc.get(orderId);
		Double countPrice = orderListSvc.getSumOrder(porder.getPurOrderId());
		porder.setCountPrice(countPrice == null ? 0 : countPrice);
		SysUser sysUser = session.getUserSession(request, AUTH_USER);
		// 根据用户查询角色信息判断是否有审核权限
		Boolean isAudit = false;
		if (sysUser.getUsername().equals("admin")) {
			isAudit = true;
		} else {
			isAudit = orderSvc.verAudit(countPrice, sysUser);
		}
		JSONObject json = new JSONObject();
		if (isAudit) {
			auditSvc.save(porder, orderAudit, sysUser);
			porder.setStatus(orderAudit.getStatus());
			porder.setAuditType(orderAudit.getAuditType());
			orderSvc.update(porder);
			log.info("audit order id={}", orderId);
			sysLogSvc
					.operating(request, "order.log.audit", "id=" + orderId
							+ ";result="
							+ (orderAudit.getStatus() == 1 ? "通过" : "不通过"));
			if (orderId > 0) {
				json.put("statusCode", "200");
				json.put("message", "操作成功");
				json.put("callbackType", "closeCurrent");
			}
		} else {
			json.put("statusCode", "300");
			json.put("message", "对不起，您没有审核权限！");
		}
		ResponseUtils.renderJson(response, json.toJSONString());
	}

	/**
	 * @Title: print
	 * @Description: 打印采购单
	 * @param request
	 * @param model
	 * @param orderId
	 * @return String
	 * @throws
	 */
	@RequestMapping("/order/print/{orderId}")
	public String print(HttpServletRequest request, ModelMap model,
			@PathVariable Integer orderId) {
		SysUser sysUser = session.getUserSession(request, AUTH_USER);
		model.addAttribute("sysUser", sysUser);
		OrderPurchase orderPur = orderSvc.get(orderId);
		model.addAttribute("orderPur", orderPur);
		// 查询付款方式
		String[] prom = { "del", "paramType" };
		Object[] objetm = { false, 4 };
		List<ParamConfig> paramConfigList = configSvc.getList(prom, objetm);
		model.addAttribute("paramConfigList", paramConfigList);
		String[] orderpro = { "del", "orderPurchase.purOrderId" };
		Object[] orderobjet = { false, orderId };
		List<OrderList> orderList = orderListSvc.getList(orderpro, orderobjet);
		List<OrderList> neworderList = new ArrayList<OrderList>();
		Double ntaxCount = 0.0;
		Double ytaxCount = 0.0;
		for (OrderList order : orderList) {
			ParamConfig pc = configSvc.get(order.getUnit());
			order.setUnitName(pc.getName());
			// 不含税金额
			order.setnTaxRateCount(order.getOrderPrice() * order.getOrderNum());
			// 含税金额
			order.setYtaxRateCount(order.getnTaxRateCount()
					* (1 + order.getTaxRate() / 100));
			// 税额
			order.setTaxRateCount(order.getYtaxRateCount()
					- order.getnTaxRateCount());
			// 含税单价
			order.setTaxtRatePrice(order.getOrderPrice()
					* (1 + order.getTaxRate() / 100));
			neworderList.add(order);
			ntaxCount += order.getnTaxRateCount();
			ytaxCount += order.getYtaxRateCount();

		}
		model.addAttribute("ntaxCount", ntaxCount);
		model.addAttribute("ytaxCount", ytaxCount);
		model.addAttribute("seCount", ytaxCount - ntaxCount);

		model.addAttribute("orderList", neworderList);
		List<OrderAudit> auditList = auditSvc.getList(
				"orderPurchase.purOrderId", orderId);
		String cgaudit = "";
		String cwaudit = "";
		model.addAttribute("auditList", auditList);
		if (auditList != null && auditList.size() > 0) {
			for (OrderAudit audit : auditList) {
				if (audit.getAuditType() == 1) {
					cgaudit = audit.getMemo();
				}
				if (audit.getAuditType() == 2) {
					cwaudit = audit.getMemo();
				}

			}

		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		model.addAttribute("cgaudit", cgaudit);
		model.addAttribute("cwaudit", cwaudit);
		model.addAttribute("printDate", sdf.format(new Date()));
		// 查询付款方式
		String[] unitprom = { "del", "paramType" };
		Object[] unitobjetm = { false, 1 };
		List<ParamConfig> unitConfigList = configSvc.getList(unitprom,
				unitobjetm);
		model.addAttribute("unitConfigList", unitConfigList);
		return "order/orderprint";
	}

}
