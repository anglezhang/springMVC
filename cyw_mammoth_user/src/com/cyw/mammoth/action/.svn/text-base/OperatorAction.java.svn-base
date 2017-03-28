package com.cyw.mammoth.action;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.cyw.common.util.CommonUtil;
import com.cyw.common.util.StrUtils;
import com.cyw.mammoth.auth.AppBaseCfg;
import com.cyw.mammoth.auth.ShiroDbAuthRealm.ShiroUser;
import com.cyw.mammoth.bean.Hcodes;
import com.cyw.mammoth.bean.Hexchange;
import com.cyw.mammoth.bean.HfunctionWorkgroupList;
import com.cyw.mammoth.bean.Operator;
import com.cyw.mammoth.bean.ShiftLog;
import com.cyw.mammoth.bean.WorkGroup;
import com.cyw.mammoth.cywenum.HcodesEnumType;
import com.cyw.mammoth.service.CodeCareSvc;
import com.cyw.mammoth.service.HcodesSvc;
import com.cyw.mammoth.service.OperatorSvc;
import com.cyw.mammoth.service.WorkGroupSvc;
import com.cyw.mammoth.service.ShiftLogSvc;
import com.cyw.mammoth.vo.AjaxJson;
import com.cyw.mammoth.vo.OperatorListVO;
import com.cyw.mammoth.vo.OperatorVo;
import com.cyw.mammoth.vo.WorkGroupVO;

/**
 * Operation action
 * 
 * @author wexl@63.com
 *
 */
@Controller
public class OperatorAction extends BaseAction {

	/**
	 * 日志对象
	 */
	protected Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired 
	private WorkGroupSvc workGroupSvc ;
	 
	private OperatorSvc operatorSvc;

	@Autowired
	public void setOperatorSvc(OperatorSvc operatorSvc) {
		this.operatorSvc = operatorSvc;
	}

	private ShiftLogSvc shiftLogSvc;

	@Autowired
	public void setShiftLogSvc(ShiftLogSvc shiftLogSvc) {
		this.shiftLogSvc = shiftLogSvc;
	}

	@Autowired
	private CodeCareSvc codeCareSvc;

	@RequestMapping("/operator/list.do")
	public String list(ModelMap model, Integer status) throws Exception {
		/*List<WorkGroup> workGroupList = workGroupSvc.getList("status",0);
		//注入不受控制的超管组
		WorkGroup superAdminGroup = new WorkGroup();
		superAdminGroup.setGroupId("0");
		superAdminGroup.setGroupName("超管");
		workGroupList.add(superAdminGroup);
		model.addAttribute("workGroupList", workGroupList);
		model.addAttribute("hoverType", 3);
		model.addAttribute("status", status);*/
		model.addAttribute("hoverType",3);
		model.addAttribute("status", status);
		List<OperatorListVO> list = operatorSvc.findListBy(status) ;
		model.addAttribute("listJson", JSON.toJSONString(list));

		// 消费点类型
		List<WorkGroup> wgs= workGroupSvc.getList("status", 0) ;
		List<Map<String, Object>> groupIds = new ArrayList<Map<String, Object>>();
		Map<String, Object> map ;
		for(WorkGroup wg : wgs){
			map = new HashMap<String, Object>();
			map.put("codeId", wg.getGroupId()) ;
			map.put("codeName", wg.getGroupName()) ;
			groupIds.add(map);
		}
		model.addAttribute("groupIds", JSON.toJSONString(groupIds));
		
		
		return "syssetting/permission/operator/operator_list";
	}

	/**
	 * 跳转至用戶管理界面
	 * 
	 * @param 用戶管理
	 * @param searchVo
	 * @return
	 */
	@RequestMapping("/operator/operatorIndex.do")
	public ModelAndView operatorIndex(ModelMap model, OperatorVo searchVo) {
		ModelAndView mav = new ModelAndView("operator/operator");
		model.addAttribute("firstHoverType", 4);
		mav.addObject("firstHoverType", 4);
		return mav;
	}

	/**
	 * 查询
	 * 
	 * @param searchVo
	 * @return
	 */
	@RequestMapping("/operator/ajaxList.do")
	@ResponseBody
	public List<Operator> list(OperatorVo searchVo) {
		Operator op = AppBaseCfg.getOperator();
		List<Operator> list = null;
		try {
			list = operatorSvc.search(searchVo);

		} catch (Exception e) {
			logger.error("", e);
		}
		return list;
	}

	/**
	 * 保存數據
	 * 
	 * @param 用戶管理
	 * @param searchVo
	 * @return
	 */
	@RequestMapping("/operator/ajaxSave.do")
	@ResponseBody
	public AjaxJson saveOperator(Operator searchVo) {
		AjaxJson aj = new AjaxJson();
		searchVo.setOperId(searchVo.getOperId().trim());
		searchVo.setLastDate(new Date());
		searchVo.setPassLimit((short) -1);
		searchVo.setUpdateDate(new Date());
		searchVo.setUpdateId("0");
		searchVo.setStatus(0);
		if(AppBaseCfg.filterUserNames.contains(searchVo.getOperName().trim())){
			aj.setSuccess(false);
			aj.setMsg("操作员名称中包含铭感词，不允许使用.");
			return aj;
		}
		try {
			operatorSvc.evict(searchVo);
			Operator op = operatorSvc.merge(searchVo);
			operatorSvc.flush();
			aj.setSuccess(true);
			aj.setMsg("保存成功");
			aj.setObj(op.getOperId());
		} catch (Exception e) {
			logger.error("", e);
			aj.setSuccess(false);
			aj.setMsg("保存失败");
		}

		return aj;
	}

	/**
	 * 保存數據
	 * 
	 * @param 用戶管理
	 * @param searchVo
	 * @return
	 */
	@RequestMapping("/operator/getOperator.do")
	@ResponseBody
	public AjaxJson getOperator(String id) {
		AjaxJson aj = new AjaxJson();
		Operator operator = null;
		try {
			operator = operatorSvc.get(id);

		} catch (Exception e) {
			logger.error("", e);
			aj.setSuccess(false);
			aj.setMsg("数据检索失败");
			return aj;
		}
		if (null == operator) {
			aj.setSuccess(false);
			aj.setMsg("指定的数据记录不存在");
			return aj;
		} else {
			aj.setSuccess(true);
			aj.setMsg("");
			aj.setObj(operator);
			Map<String, Object> map =new HashMap<String, Object>();
			List<WorkGroup> workGroupList = workGroupSvc.getList("status",0);
			//注入不受控制的超管组
			WorkGroup superAdminGroup = new WorkGroup();
			superAdminGroup.setGroupId("0");
			superAdminGroup.setGroupName("超管");
			workGroupList.add(superAdminGroup);
			map.put("workGroupList", workGroupList);
			aj.setAttributes(map) ;
			return aj;
		}
	}

	/**
	 * 保存數據
	 * 
	 * @param 用戶管理
	 * @param searchVo
	 * @return
	 */
	@RequestMapping("/operator/logicDelOperator.do")
	@ResponseBody
	public AjaxJson logicDelOperator(String id) {
		AjaxJson aj = new AjaxJson();
		Operator operator = null;
		try {
			operator = operatorSvc.get(id);
			if (null == operator) {
				aj.setSuccess(false);
				aj.setMsg("指定的数据记录不存在");
				return aj;
			}
			operator.setStatus(1);
			operatorSvc.evict(operator);
			operatorSvc.saveOrUpdate(operator);
			operatorSvc.flush();
			aj.setSuccess(true);
			aj.setMsg("取消成功");

		} catch (Exception e) {
			logger.error("", e);
			aj.setSuccess(false);
			aj.setMsg("保存失败");
		}
		return aj;
	}

	public enum OperatorRoles {
		User("用户", "1"), Admin("管理员", "3");
		public String name;
		public String code;

		private OperatorRoles(String name, String code) {
			this.name = name;
			this.code = code;
		}
	}

	@RequestMapping("/syssetting/showChangeWorkPup.do")
	public ModelAndView showChangeWorkPup(ModelMap model){
		ModelAndView mav=new ModelAndView("syssetting/changeWorkPup");
		try {
			List<Hcodes> hcodesList=codeCareSvc.findGEListBy(HcodesEnumType.HCODE_HANDOVER.getValue(), 0);
			for(Hcodes hc:hcodesList){
				hc.setCodeId(hc.getCodeId().trim());
				hc.setCodeNamec(hc.getCodeNamec().trim());
			}
			model.addAttribute("operName", AppBaseCfg.getOperator().getOperName());
			//model.addAttribute("operName", AppBaseCfg.getOper().getOperName());
			String shfitLogId=AppBaseCfg.getOperator().getShiftLog().getShiftId();
			Hcodes shiftLogCode=codeCareSvc.get("codeId", HcodesEnumType.HCODE_HANDOVER+shfitLogId);
			String  banciName = null==shiftLogCode?"":shiftLogCode.getCodeNamec();
			model.addAttribute("currBanciName", banciName);
			model.addAttribute("shiftLogId", shfitLogId);
			model.addAttribute("banciList", hcodesList);
		} catch (Exception e) {
			logger.error("获取班次信息发生错误,",e);
		}
		return mav;
	}

	@RequestMapping("/syssetting/doChangeWorkPup.do")
	@ResponseBody
	public AjaxJson doChangeWorkPup(String shiftId) {
		AjaxJson aj = new AjaxJson();
		if(!StrUtils.isValidString(shiftId)){
			aj.setSuccess(false);
			aj.setMsg("班次不能为空");
			return aj;
		}
		try {
			//更新班次信息
			String operId=AppBaseCfg.getOperator().getOperId();
			Subject currentUser = SecurityUtils.getSubject();
			ShiroUser shiroUser = (ShiroUser) currentUser.getPrincipal();
			ShiftLog currShiftLog = operatorSvc.getOperShiftLog(shiroUser.getLoginName());
			ShiftLog tmpShiftLog = shiftLogSvc.get(currShiftLog.getId());
			tmpShiftLog.setShiftId(shiftId);
			tmpShiftLog.setOperTime(new Timestamp(new Date().getTime()));
			shiftLogSvc.update(tmpShiftLog);
			aj.setSuccess(true);
			//刷新班次信息
			//shiftLog = shiftLogSvc.get(nshfiId);
			//AppBaseCfg.getOperator().setShiftLog(shiftLog);
		} catch (Exception e) {
			aj.setSuccess(false);
			aj.setMsg("交接班失败");
			logger.error("交接班失败", e);
		}

		return aj;
	}
	@RequestMapping("/operator/changePwd.do")
	@ResponseBody
	public AjaxJson changePwd(String oldPwd,String newPwd){
		AjaxJson aj = new AjaxJson();
		Subject currentUser = SecurityUtils.getSubject();
		ShiroUser shiroUser = (ShiroUser) currentUser.getPrincipal();
		String operId=shiroUser.getLoginName();
		if(!StrUtils.isValidString(oldPwd)){
			aj.setSuccess(false);
			aj.setMsg("原始密码不能为空.");
			return aj;
		}
		if(!StrUtils.isValidString(newPwd)){
			aj.setSuccess(false);
			aj.setMsg("新密码不能为空.");
			return aj;
		}
		Operator operator= operatorSvc.get(new String[]{"operId","passwd"}, new Object[]{operId,oldPwd});
		if(null==operator){
			aj.setSuccess(false);
			aj.setMsg("原始密码不正确.");
		}else{
			operator.setPasswd(newPwd);
			operatorSvc.merge(operator);
			operatorSvc.flush();
			aj.setSuccess(true);
		}
		return aj;
	}
	/**
	 * 
	 * @param params   编辑的实体json
	 * @param editFlag 编辑状态   'u' 修改   'a' 新增 
	 * @param delIds   被删除的ids
	 * @return
	 * @throws Exception 
	 */
	@ResponseBody
	@RequestMapping("/operator/saveOrUpdateOrDel.do")
	public String saveOrUpdateOrDel(String params, String delIds) throws Exception{
		JSONObject jsonObj =  new JSONObject();
		List<Operator> roomDefineVos = JSON.parseArray(params , Operator.class) ;
		operatorSvc.saveOrUpdateOrDel(roomDefineVos , delIds);
		jsonObj.put("success", true);
		return jsonObj.toJSONString();
	}
	
	@ResponseBody
	@RequestMapping("/operator/updateStatus.do")
	public String updateStatus(String backIds) throws Exception{
		JSONObject jsonObj =  new JSONObject();
		operatorSvc.updateStatusById((StringUtils.isNotBlank(backIds) ? CommonUtil.array_unique(backIds.split(",")) : null)  , 0 );
		jsonObj.put("success", true);
		return jsonObj.toJSONString();
	}
	/**
	 * 查询对象是否存在
	 * @param params 实体json
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/operator/findBy.do")
	public String findBy(String id , String codeId){
		JSONObject jsonObj =  new JSONObject();
		Operator entity = operatorSvc.get("operId",codeId) ;
		if(StringUtils.isBlank(id)){
			if(entity !=null){
				jsonObj.put("success", false);
			}else{
				jsonObj.put("success", true);
			}
		}else{
			if(entity !=null && !id.equals(entity.getOperId())){
				jsonObj.put("success", false);
			}else{
				jsonObj.put("success", true);
			}
		}
		return jsonObj.toJSONString();
	}
}
