package com.zoomoor.jy.action;

import static com.zoomoor.admin.service.SysAuthenticationSvc.AUTH_USER;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zoomoor.admin.entity.SysUser;
import com.zoomoor.common.base.bean.Pager;
import com.zoomoor.common.web.ResponseUtils;
import com.zoomoor.common.web.session.SessionProvider;
import com.zoomoor.jy.entity.AllocationApply;
import com.zoomoor.jy.entity.InfoDept;
import com.zoomoor.jy.entity.vo.AllocationApplyVo;
import com.zoomoor.jy.service.DepotMothSvc;

/**   
 * 类名称：DepotMonthSvc   
 * 类描述： 月结   
 * 创建人：liuweixing
 * 创建时间：2015-8-19 下午3:10:00   
 * 修改人：liuweixing
 * 修改时间：2015-8-19 下午3:10:00   
 * 修改备注：   
 * @version       
 */ 
@Controller
public class DepotMonthAct {
	@Autowired
	private DepotMothSvc mothSvc;
	@Autowired
	private SessionProvider session;
	/**  
	 * @Title: monthList  
	 * @Description: 月结
	 * @return String 
	 * @throws  
	 */  
	@RequestMapping("/depot/monthlist.action")
	public String monthList(HttpServletRequest request,Pager pager,ModelMap model){
		SysUser sysUser = session.getUserSession(request, AUTH_USER);
		List list=mothSvc.getMonthList(sysUser.getInfoEmp().getInfoDept().getDeptId());
		model.addAttribute("list", list);
		model.addAttribute("deptName", sysUser.getInfoEmp().getInfoDept().getName());
		return "/depot/month/mlist";
	}
	/**  
	 * @Title: monthCheck  
	 * @Description: 核对账目
	 * @param request
	 * @param pager
	 * @param model
	 * @return String 
	 * @throws  
	 */  
	@RequestMapping("/depot/monthcheck.action")
	public String monthCheck(HttpServletRequest request,Pager pager,ModelMap model,String maxMonth,String curMonth){
		SysUser sysUser = session.getUserSession(request, AUTH_USER);
		List checklist=mothSvc.getMonthCheck(sysUser.getInfoEmp().getInfoDept().getDeptId(),curMonth);
		model.addAttribute("checklist", checklist);
		model.addAttribute("deptName", sysUser.getInfoEmp().getInfoDept().getName());
		model.addAttribute("maxMonth", maxMonth);
		model.addAttribute("curMonth", curMonth);
		return "/depot/month/clist";
	}
	
	@RequestMapping("/depot/checkbalance.action")
	public void checkBalance(HttpServletRequest request, HttpServletResponse response){
		SysUser sysUser = session.getUserSession(request, AUTH_USER);
		mothSvc.checkBalance(sysUser.getInfoEmp().getInfoDept().getDeptId());
		JSONObject json = new JSONObject();
		json.put("statusCode", "200");
		json.put("message", "操作成功");
		ResponseUtils.renderJson(response, json.toJSONString());
	}
	
	
	
	
	
	
	
}
