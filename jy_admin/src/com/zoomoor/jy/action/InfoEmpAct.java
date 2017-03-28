package com.zoomoor.jy.action;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.zoomoor.admin.dao.SysUserDao;
import com.zoomoor.admin.entity.SysUser;
import com.zoomoor.common.base.bean.Pager;
import com.zoomoor.jy.entity.ConfigEmpMapping;
import com.zoomoor.jy.entity.InfoDept;
import com.zoomoor.jy.entity.InfoEmp;
import com.zoomoor.jy.entity.ParamConfig;
import com.zoomoor.jy.service.ConfigEmpMappingSvc;
import com.zoomoor.jy.service.InfoDeptSvc;
import com.zoomoor.jy.service.InfoEmpSvc;
import com.zoomoor.jy.service.ParamConfigSvc;
import com.zoomoor.jy.util.ResponseMsgUtil;

/**
 * 描述：员工管理
 * 
 * @author Zhangzhenxing
 * @Date 2015年8月7日 上午9:43:31
 * @version 1.0
 */
@Controller
public class InfoEmpAct {

	/**
	 * 员工管理服务
	 * */
	@Autowired
	private InfoEmpSvc svc;

	/**
	 * 部门服务
	 * */
	@Autowired
	private InfoDeptSvc deptSvc;

	/**
	 * 系统用户管理
	 * */
	@Autowired
	private SysUserDao userDao;
	
	/**
	 * 员工技能关系
	 * */
	@Autowired
	private ConfigEmpMappingSvc cifMapSvc;

	/**
	 * 数据字典
	 * */
	@Autowired
	private ParamConfigSvc configSvc;

	private static final int PROFESSIONAL_SKILLS = 3;// 专业技能

	private static final int DRIVERING = 5;// 驾照类型

	private static final int POST = 2;// 岗位

	/**
	 * 描述:选择员工信息
	 * 
	 * @param queryName
	 *            员工姓名
	 * @param queryIdCode
	 *            员工省份证号
	 * */
	@RequestMapping("infoemp/lookup.do")
	public String chooseEmp(HttpServletRequest request,
			HttpServletResponse response, Pager pager, ModelMap model,
			String queryName, String queryIdCode, Integer islook) {
		pager = svc.getLookPager(pager, queryName, queryIdCode, islook);
		model.addAttribute("queryName", queryName);
		model.addAttribute("queryIdCode", queryIdCode);
		model.addAttribute("pager", pager);
		model.addAttribute("islook", islook);
		return "infoemp/lookup";
	}

	/**
	 * 列表
	 * */
	@RequestMapping("/emplyee/list.do")
	public String list(HttpServletRequest request,
			HttpServletResponse response, Pager pager, ModelMap model,
			Integer queryDept, String queryEmpCode, String queryEmpName,
			Integer queryGender, String queryPosition, Integer queryStatus) {
		List<InfoDept> deptS = deptSvc.getListByName(null, null);
		pager = svc.getPager(pager, queryDept, queryEmpCode, queryEmpName,
				queryGender, queryPosition, queryStatus);
		model.addAttribute("deptS", deptS);
		model.addAttribute("pager", pager);
		model.addAttribute("queryDept", queryDept);
		model.addAttribute("queryEmpCode", queryEmpCode);
		model.addAttribute("queryEmpName", queryEmpName);
		model.addAttribute("queryGender", queryGender);
		model.addAttribute("queryPosition", queryPosition);
		model.addAttribute("queryStatus", queryStatus);
		return "infoemp/list";
	}

	/**
	 * 描述:添加员工
	 * */
	@RequestMapping("/emplyee/add.do")
	public String add(HttpServletRequest request, HttpServletResponse response,
			ModelMap model) {
		createParam(model);
		return "infoemp/add";
	}

	/**
	 * 描述:保存
	 * */
	@RequestMapping("/emplyeeinf/empsave.do")
	public void save(HttpServletRequest request, HttpServletResponse response,
			InfoEmp infoEmp) {
		//员工编号唯一性验证
		String empCode = infoEmp.getEmpCode();
		InfoEmp entity = svc.get("empCode", empCode);
		if(entity != null){
			ResponseMsgUtil.operFaild(response, "员工编号必须唯一");
			return;
		}
		Date now = new Date(System.currentTimeMillis());
		ParamConfig driParam = infoEmp.getDrivinglicense();
		if (driParam != null) {
			Integer unitID = driParam.getUnitId();
			if (unitID!=null&&unitID != -1) {
				ParamConfig cifEntity = configSvc.get(unitID);
				infoEmp.setDrivinglicense(cifEntity);
			}else{
				infoEmp.setDrivinglicense(null);
			}
		}

		infoEmp.setCreatetime(now);
		infoEmp.setDel(false);
		Integer entityID = svc.save(infoEmp);
		infoEmp.setEmpId(entityID);
		// 保存技能关系
		svc.saveEmplyeer(infoEmp);
		ResponseMsgUtil.operSuccessFullClose(response, "保存成功");
	}

	/**
	 * 描述：删除
	 * */
	@RequestMapping("/emplyee/delete.do")
	public void delete(HttpServletRequest request,
			HttpServletResponse response, @RequestParam("empId") Integer empId) {
		String[] prom={"del","infoEmp.empId"};
		Object[] objetm={false,empId};
		List<SysUser> users = userDao.getList(prom, objetm);
		if (users != null && users.size() > 0) {
			ResponseMsgUtil.operFaild(response, "该员工下挂帐号，不能删除");
		} else if (users == null || users.size() == 0) {
			InfoEmp entity = svc.get(empId);
			entity.setDel(true);
			svc.update(entity);
			ResponseMsgUtil.operSuccessFull(response, "删除成功");
		}
	}

	/**
	 * 描述：查看
	 * */
	@RequestMapping("/emplyee/edit.do")
	public String edit(HttpServletRequest request,
			HttpServletResponse response,ModelMap model, @RequestParam("empId")Integer empId){
		InfoEmp infEmp = svc.get(empId);
		model.addAttribute("infEmp", infEmp);
		//当前员工具备技能
		List<ConfigEmpMapping> cfgEmpMaps = cifMapSvc.getList("infoEmp.empId", empId);
		model.addAttribute("cfgEmpMaps", cfgEmpMaps);
		//添加数据字典信息
		createParam(model);
		return "infoemp/edit";
	}
	
	/**
	 * 描述:更新员工对象
	 * */
	@RequestMapping("emplyeeinf/empupdate.do")
	public void update(HttpServletRequest request,
			HttpServletResponse response,InfoEmp infoEmp){
		svc.updateInfoEmp(infoEmp);
		svc.saveEmplyeer(infoEmp);
		ResponseMsgUtil.operSuccessFullClose(response, "更新成功");
	}
	
	/**
	 * 描述:查看
	 * */
	@RequestMapping("emplyee/scan.do")
	public String view(HttpServletRequest request,
			HttpServletResponse response,ModelMap model, @RequestParam("empId")Integer empId){
		InfoEmp infEmp = svc.get(empId);
		model.addAttribute("infEmp", infEmp);
		//当前员工具备技能
		List<ConfigEmpMapping> cfgEmpMaps = cifMapSvc.getList("infoEmp.empId", empId);
		model.addAttribute("cfgEmpMaps", cfgEmpMaps);
		//添加数据字典信息
		createParam(model);
		return "infoemp/view";
	}
	
	/**
	 * 描述:离职
	 * */
	@RequestMapping("/emplyee/out.do")
	public void outToDo(HttpServletRequest request,
			HttpServletResponse response, @RequestParam("empId") Integer empId){
		
		List<SysUser> users = userDao.getList("infoEmp.empId", empId);
		if (users != null && users.size() > 0) {
			ResponseMsgUtil.operFaild(response, "该员工下挂帐号，不能设置离职");
		} else if (users == null || users.size() == 0) {
			InfoEmp entity = svc.get(empId);
			entity.setStatus(2);//设置已经离职
			entity.setOutDate(new Date(System.currentTimeMillis()));
			svc.update(entity);
			ResponseMsgUtil.operSuccessFull(response, "离职设置成功");
		}
		
	}
	
	/**
	 * 描述:创建数据字典的下拉列表
	 * */
	public void createParam(ModelMap model){
		// 驾照类型
		String[] promone = { "del", "paramType" };
		Object[] objetmone = { false, DRIVERING };
		List<ParamConfig> drivingConfigList = configSvc.getList(promone,
				objetmone);
		// 专业技能
		String[] promtwo = { "del", "paramType" };
		Object[] objetmtwo = { false, PROFESSIONAL_SKILLS };
		List<ParamConfig> professConfigList = configSvc.getList(promtwo,
				objetmtwo);
		// 岗位
		String[] promthree = { "del", "paramType" };
		Object[] objetthree = { false, POST };
		List<ParamConfig> postConfigList = configSvc.getList(promthree,
				objetthree);
		List<InfoDept> deptS = deptSvc.getListByName(null, null);
		model.addAttribute("deptS", deptS);
		model.addAttribute("drivingConfigList", drivingConfigList);
		model.addAttribute("professConfigList", professConfigList);
		model.addAttribute("postConfigList", postConfigList);
	}
}
