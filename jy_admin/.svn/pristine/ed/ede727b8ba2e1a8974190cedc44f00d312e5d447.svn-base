package com.zoomoor.jy.action;

import static com.zoomoor.admin.service.SysAuthenticationSvc.AUTH_USER;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zoomoor.admin.entity.SysUser;
import com.zoomoor.admin.service.SysLogSvc;
import com.zoomoor.admin.service.SysUserSvc;
import com.zoomoor.common.base.bean.Pager;
import com.zoomoor.common.web.ResponseUtils;
import com.zoomoor.common.web.session.SessionProvider;
import com.zoomoor.jy.entity.AllocationApply;
import com.zoomoor.jy.entity.InfoDept;
import com.zoomoor.jy.entity.InfoEmp;
import com.zoomoor.jy.entity.InfoGoods;
import com.zoomoor.jy.entity.ParamConfig;
import com.zoomoor.jy.entity.vo.AllocationCountVo;
import com.zoomoor.jy.service.AllocationApplySvc;
import com.zoomoor.jy.service.InfoDeptSvc;
import com.zoomoor.jy.service.InfoEmpSvc;
import com.zoomoor.jy.service.InfoGoodsSvc;
import com.zoomoor.jy.service.ParamConfigSvc;

/**   
 * 类名称：AllocationApplyAct   
 * 类描述：   门店要货申请
 * 创建人：liuweixing
 * 创建时间：2015-7-26 下午4:54:51   
 * 修改人：liuweixing
 * 修改时间：2015-7-26 下午4:54:51   
 * 修改备注：   
 * @version       
 */ 
@Controller
@Transactional
public class AllocationApplyAct {
	private static final Logger log = LoggerFactory.getLogger(AllocationApplyAct.class);
	@Autowired
	private SysLogSvc sysLogSvc;
	@Autowired
	private SessionProvider session;
	@Autowired
	private ParamConfigSvc configSvc;
	@Autowired
	private AllocationApplySvc applySvc;
	@Autowired
	private InfoEmpSvc empSvc;
	@Autowired
	private InfoGoodsSvc goodsSvc;
	@Autowired
	private InfoDeptSvc deptSvc;
	
	@Autowired
	private SysUserSvc userSvc;
	
	/**  
	 * @Title: list  
	 * @Description: 加载要货列表店铺总览
	 * @param pager
	 * @param request
	 * @param queryGoodsName
	 * @param queryGoodsBrand
	 * @param queryStatus
	 * @param queryStartDate
	 * @param queryEndDate
	 * @param infoGoods
	 * @param applytype
	 * @param deptId
	 * @param model
	 * @return String 
	 * @throws  
	 */  
	@RequestMapping("/allocationapply/list.do")
	public String list(HttpServletRequest request,String queryStartDate,String queryEndDate,Integer deptId,Integer currentIndex, ModelMap model){
		List<AllocationCountVo> volist = applySvc.getCountPage(queryStartDate,queryEndDate,deptId,currentIndex);
		model.addAttribute("volist", volist);
		model.addAttribute("queryStartDate", queryStartDate);
		model.addAttribute("queryEndDate", queryEndDate);
		model.addAttribute("deptId", deptId);
		model.addAttribute("currentIndex", currentIndex);
		String[] prom={"del","isShop"};
		Object[] objetm={false,true};
		List<InfoDept>  deptList = deptSvc.getList(prom, objetm);
		model.addAttribute("deptList", deptList);
		return "allocationapply/list";
	}
	/**  
	 * @Title: goodslist  
	 * @Description: 加载配件总览 
	 * @param request
	 * @param queryStartDate
	 * @param queryEndDate
	 * @param deptId
	 * @param currentIndex
	 * @param model
	 * @return String 
	 * @throws  
	 */  
	@RequestMapping("/allocationapply/goodslist.do")
	public String goodslist(HttpServletRequest request,String queryGoodsName,String queryGoodsCode, Integer currentIndex ,ModelMap model){
		List<AllocationCountVo> goodslist = applySvc.getCountGoodsList(queryGoodsName,queryGoodsCode,currentIndex);
		model.addAttribute("goodslist", goodslist);
		model.addAttribute("queryGoodsName", queryGoodsName);
		model.addAttribute("queryGoodsCode", queryGoodsCode);
		model.addAttribute("currentIndex", currentIndex);
		return "allocationapply/list";
	}
	
	/**  
	 * @Title: list  
	 * @Description: 加载要货明细
	 * @param pager
	 * @param queryGoodsName
	 * @param queryGoodsBrand
	 * @param queryStatus
	 * @param queryStartDate
	 * @param queryEndDate
	 * @param infoGoods
	 * @param model
	 * @return String 
	 * @throws  
	 */  
	@RequestMapping("/allocationapply/listdetail.action")
	public String listdetail(Pager pager,HttpServletRequest request,String queryGoodsName,String queryGoodsBrand,String queryGoodsCode, String unitDate,
			Integer queryStatus,String queryStartDate,String queryEndDate,InfoGoods infoGoods,Integer applytype,Integer deptId, ModelMap model){
		SysUser sysUser = session.getUserSession(request, AUTH_USER);
		if(deptId==null){
			deptId=sysUser.getInfoEmp().getInfoDept().getDeptId();
			if(sysUser.getUsername().equals("admin")||applytype!=null){
				deptId=null;
			}
		}
		Integer typeId=null;
		if(infoGoods.getInfoGoodsType()!=null){
			typeId=infoGoods.getInfoGoodsType().getGroupId();
			model.addAttribute("queryTypeId", infoGoods.getInfoGoodsType().getGroupId());
			model.addAttribute("queryTypeName", infoGoods.getInfoGoodsType().getTypeName());
		}
		pager = applySvc.getPage(queryGoodsName,queryGoodsBrand,queryGoodsCode,unitDate,queryStatus,queryStartDate,queryEndDate,typeId,deptId,pager.getPageNum(), pager.getNumPerPage());
		model.addAttribute("pager", pager);
		String[] prom={"del","isShop"};
		Object[] objetm={false,true};
		List<InfoDept>  deptList = deptSvc.getList(prom, objetm);
		model.addAttribute("deptList", deptList);
		model.addAttribute("queryGoodsName", queryGoodsName);
		model.addAttribute("queryGoodsBrand", queryGoodsBrand);
		model.addAttribute("queryStatus", queryStatus);
		model.addAttribute("queryStartDate", queryStartDate);
		model.addAttribute("queryEndDate", queryEndDate);
		model.addAttribute("applytype", applytype);
		model.addAttribute("deptId", deptId);
		model.addAttribute("unitDate", unitDate);
		model.addAttribute("queryGoodsCode", queryGoodsCode);
		return "allocationapply/listdetail";
	}
	/**  
	 * @Title: add  
	 * @Description: 跳转至添加页 
	 * @param request
	 * @param model
	 * @return String 
	 * @throws  
	 */  
	@RequestMapping("/allocationapply/add.do")
	public String add( HttpServletRequest request,ModelMap model){
		SysUser sysUser = session.getUserSession(request, AUTH_USER);
		model.addAttribute("sysUser", sysUser);
		return "allocationapply/add";
	}
	/**  
	 * @Title: save  
	 * @Description:  添加 
	 * @param request
	 * @param response
	 * @param apply void 
	 * @throws  
	 */  
	@SuppressWarnings("unchecked")
	@RequestMapping("/allocationapply/save.do")
	public void save(HttpServletRequest request, HttpServletResponse response, AllocationApply apply,Integer empid){
		JSONObject json = new JSONObject();
		SysUser sysUser = session.getUserSession(request, AUTH_USER);
		if(apply.getInfoGoodList()!=null){
			for(InfoGoods goods:apply.getInfoGoodList()){
				ParamConfig param=configSvc.get(goods.getParamConfig().getUnitId());
				InfoGoods infoGoods= goodsSvc.get(goods.getGoodsId());
				InfoEmp emp=empSvc.get(sysUser.getInfoEmp().getEmpId());
				AllocationApply newApply= new AllocationApply();
				if(emp!=null&&emp.getInfoDept()!=null){
					newApply.setInfoDept(emp.getInfoDept());
				}
				newApply.setUserId(sysUser.getUserId());
				newApply.setInfoGoods(infoGoods);
				newApply.setParamConfig(param);
				newApply.setNum(goods.getNum());
				newApply.setDel(false);
				newApply.setUnitDate(new Date());
				newApply.setStatus(0);
				newApply.setTakeDate(apply.getTakeDate());
				Integer applyId = applySvc.save(newApply);
				log.info("save apply id={}",applyId);
				sysLogSvc.operating(request, "apply.log.save", "id=" + applyId);
			}
		}
		json.put("statusCode", "200");
		json.put("message", "操作成功");
		json.put("callbackType", "closeCurrent");
		ResponseUtils.renderJson(response, json.toJSONString());
	}
	/**  
	 * @Title: view  
	 * @Description: 查看详情 
	 * @param request
	 * @param model
	 * @param id
	 * @return String 
	 * @throws  
	 */  
	@RequestMapping("/allocationapply/view.do")
	public String view(HttpServletRequest request,ModelMap model,Integer id){
		AllocationApply newApply=applySvc.get(id);
		SysUser user=userSvc.get(newApply.getUserId());
		if(user!=null&&user.getInfoEmp()!=null){
			model.addAttribute("empName", user.getInfoEmp().getEmpName());
		}
		model.addAttribute("newApply", newApply);
		return "allocationapply/view";
		
	}
	/**  
	 * @Title: lookFeedBack  
	 * @Description: 查看反馈
	 * @param request
	 * @param model
	 * @param id
	 * @return String 
	 * @throws  
	 */  
	@RequestMapping("/allocationapply/lookfeedback.do")
	public String lookFeedBack(HttpServletRequest request,ModelMap model,Integer id){
		AllocationApply newApply=applySvc.get(id);
		model.addAttribute("newApply", newApply);
		return "allocationapply/feedback";
		
	}
	@SuppressWarnings("unchecked")
	@RequestMapping("/allocationapply/feedback.do")
	public void feedback(HttpServletRequest request, HttpServletResponse response,Integer[] ids){
		JSONObject json = new JSONObject();
		json.put("statusCode", "200");
		String strids="";
		for(Integer id:ids){
			strids+=id+",";
		}
		json.put("strids", strids.substring(0,strids.length()-1));
		ResponseUtils.renderJson(response, json.toJSONString());
	}
	/**  
	 * @Title: openback  
	 * @Description: 打开反馈输入页面 
	 * @param request
	 * @param model
	 * @param ids
	 * @return String 
	 * @throws  
	 */  
	@RequestMapping("/allocationapply/openback.do")
	public String openback(HttpServletRequest request,ModelMap model,String ids){
		model.addAttribute("ids", ids);
		return "allocationapply/addfeed";
		
	}
	
	/**  
	 * @Title: savefeed  
	 * @Description: 保存反馈 
	 * @param request
	 * @param response
	 * @param ids void 
	 * @throws  
	 */  
	@SuppressWarnings("unchecked")
	@RequestMapping("/allocationapply/savefeed.do")
	public void savefeed(HttpServletRequest request, HttpServletResponse response,String ids,String feedback){
		String[] strids=ids.split(",");
		for(String str:strids){
			AllocationApply newApply=applySvc.get(Integer.parseInt(str));
			newApply.setFeedback(feedback);
			applySvc.update(newApply);
		}
		JSONObject json = new JSONObject();
		json.put("statusCode", "200");
		json.put("message", "操作成功");
		json.put("callbackType", "closeCurrent");
		ResponseUtils.renderJson(response, json.toJSONString());
	}
	
}
