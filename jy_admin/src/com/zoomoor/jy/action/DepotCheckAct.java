package com.zoomoor.jy.action;

import static com.zoomoor.admin.service.SysAuthenticationSvc.AUTH_USER;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zoomoor.admin.entity.SysUser;
import com.zoomoor.admin.service.SysLogSvc;
import com.zoomoor.common.base.bean.Pager;
import com.zoomoor.common.web.ResponseUtils;
import com.zoomoor.common.web.session.SessionProvider;
import com.zoomoor.jy.entity.DepotCheck;
import com.zoomoor.jy.entity.InfoDept;
import com.zoomoor.jy.entity.InfoEmp;
import com.zoomoor.jy.entity.ParamConfig;
import com.zoomoor.jy.entity.vo.DepotVo;
import com.zoomoor.jy.service.DepotCheckSvc;
import com.zoomoor.jy.service.InfoDeptSvc;
import com.zoomoor.jy.service.InfoEmpSvc;
import com.zoomoor.jy.service.ParamConfigSvc;

/**   
 * 类名称：DepotCheckAct   
 * 类描述：   盘点
 * 创建人：liuweixing
 * 创建时间：2015-8-7 下午1:07:24   
 * 修改人：liuweixing
 * 修改时间：2015-8-7 下午1:07:24   
 * 修改备注：   
 * @version       
 */ 
@SuppressWarnings({ "unused", "rawtypes" })
@Controller
public class DepotCheckAct {
	private static final Logger log = LoggerFactory.getLogger(DepotCheckAct.class);
	@Autowired
	private InfoDeptSvc deptSvc;
	@Autowired
	private SessionProvider session;
	@Autowired
	private InfoEmpSvc empSvc;
	@Autowired
	private DepotCheckSvc checkSvc;
	@Autowired
	private SysLogSvc sysLogSvc;
	@Autowired
	private ParamConfigSvc configSvc;
	/**  
	 * @Title: list  
	 * @Description: 盘点总览
	 * @param pager
	 * @param queryAllocationNo
	 * @param queryEmpName
	 * @param queryStartDate
	 * @param queryEndDate
	 * @param status
	 * @param model
	 * @return String 
	 * @throws  
	 */  
	@RequestMapping("/depot/summarylist.do")
	public String list(HttpServletRequest request,Pager pager,String queryAllocationNo,String queryEmpName,String queryStartDate, Integer iszb,String queryEndDate,Integer status,ModelMap model){
		//查询总部ID
		ParamConfig param=configSvc.get("paramType",7);
		SysUser sysUser = session.getUserSession(request, AUTH_USER);
		Integer queryDeptId=0;
		if(param.getName().equals(sysUser.getInfoEmp().getInfoDept().getDeptId().toString())){
			queryDeptId=0;
		}else{
			queryDeptId=sysUser.getInfoEmp().getInfoDept().getDeptId();
			iszb=0;
		}
		pager = checkSvc.getPage(queryAllocationNo,queryEmpName,queryStartDate,queryEndDate,status,queryDeptId,pager.getPageNum(), pager.getNumPerPage());
		model.addAttribute("pager", pager);
		model.addAttribute("queryEmpName", queryEmpName);
		model.addAttribute("queryAllocationNo", queryAllocationNo);
		model.addAttribute("queryStartDate", queryStartDate);
		model.addAttribute("queryEndDate", queryEndDate);
		model.addAttribute("status", status);
		model.addAttribute("iszb",iszb);
		return "/depot/check/summarylist";
	}
	/**  
	 * @Title: checkList  
	 * @Description:加载需要盘点的列表 
	 * @param request
	 * @param deptId
	 * @param depotCheck
	 * @param model
	 * @return String 
	 * @throws  
	 */  
	@RequestMapping("/depot/checklist.do")
	public String  checkList(HttpServletRequest request,Integer deptId,DepotCheck depotCheck,ModelMap model){
    	SysUser sysUser = session.getUserSession(request, AUTH_USER);
    /*	String[] prom={"del","isShop","deptId"};
		Object[] objetm={false,true,sysUser.getInfoEmp().getInfoDept().getDeptId()};*/
		List<InfoDept>  deptList = deptSvc.getListById(sysUser.getInfoEmp().getInfoDept().getDeptId());
		model.addAttribute("deptList", deptList);
		String positions="";
		String posname="";
		if(deptId!=null&&deptId>0){
			if(depotCheck.getDepotCheckVo()!=null){
				positions=depotCheck.getDepotCheckVo().getPositions();
				posname=depotCheck.getDepotCheckVo().getPosName();
			}
			List depotCheckList=checkSvc.getCheckListByDeptId(deptId,positions);
			model.addAttribute("deptId", deptId);
			model.addAttribute("posname", posname);
			model.addAttribute("posids", positions);
			model.addAttribute("depotCheckList", depotCheckList);
			
		}
		return "/depot/check/list";
    }
    
		/**  
		 * @Title: depotchecksave  
		 * @Description: 保存盘点信息
		 * @param request
		 * @param response
		 * @param depotCheck void 
		 * @throws  
		 */  
		@RequestMapping("/depot/depotchecksave.do")
		public void depotchecksave(HttpServletRequest request, HttpServletResponse response,DepotCheck depotCheck) {
			SysUser sysUser = session.getUserSession(request, AUTH_USER);
			/*log.info("save brand id={}",bean.getId());
			sysLogSvc.operating(request, "brand.log.save", "id=" + bean.getId() + ";name=" + bean.getName());
			*/
			/*SET @doc='<list come_id="1" goods_id="7" position_id="7" acc_num="10" /><list come_id="3" goods_id="2" position_id="2" acc_num="20" />';
*/
			String docstr="";
			if(depotCheck.getDepotVo()!=null){
				for(DepotVo dv:depotCheck.getDepotVo()){
					docstr+="<list come_id='"+dv.getComeId()+"' goods_id='"+dv.getGoodsId()
							+"' position_id='"+dv.getPositionId()+"' acc_num='"+dv.getAccNum()+"'/>";
				}
				
			}
			JSONObject json = new JSONObject();
			List list=checkSvc.saveCheck(sysUser.getInfoEmp().getInfoDept().getDeptId(),docstr);
			Map map=null;
			if(list!=null){
				 map = (Map)list.get(0);
			}
			if(map.get("message").toString().contains("OK")){
				json.put("statusCode", "200");
				//+" 单号为："+map.get("b_no");
				json.put("message",map.get("message"));
				
			}else{
				json.put("statusCode", "300");
				//+" 单号为："+map.get("b_no");
				json.put("message",map.get("message"));
			}
			ResponseUtils.renderJson(response, json.toJSONString());
		}
    
		/**  
		 * @Title: checkEntering  
		 * @Description: 盘点录入
		 * @param request
		 * @param checkNo
		 * @param model
		 * @return String 
		 * @throws  
		 */  
		@RequestMapping("/depot/checkentering.do")
		public String  checkEntering(HttpServletRequest request,DepotCheck depotCheck,String checkNo,Integer lookType,ModelMap model){
	    		SysUser sysUser = session.getUserSession(request, AUTH_USER);
	    		String BNo="";
	    		if(checkNo!=null){
	    			BNo=checkNo;
	    		}else{
		    		if(depotCheck.getDepotCheckVo()!=null){
		    			BNo=depotCheck.getDepotCheckVo().getCheckNo();
		    		}
	    		}
	    		//查询总部ID
				ParamConfig param=configSvc.get("paramType",7);
				Integer queryDeptId=0;
				if(param.getName().equals(sysUser.getInfoEmp().getInfoDept().getDeptId().toString())){
					queryDeptId=0;
				}else{
					queryDeptId=sysUser.getInfoEmp().getInfoDept().getDeptId();
				}
				List depotCheckEnterList=checkSvc.getCheckListByCheckNo(queryDeptId,BNo);
				model.addAttribute("checkNo", BNo);
				model.addAttribute("depotCheckEnterList", depotCheckEnterList);
				model.addAttribute("lookType", lookType);
			return "/depot/check/enterlist";
	    }
			/**  
			 * @Title: depotchecksave  
			 * @Description: 录入盘点信息
			 * @param request
			 * @param response
			 * @param depotCheck void 
			 * @throws  
			 */  
			@RequestMapping("/depot/depotcheckenter.do")
			public void depotCheckEnter(HttpServletRequest request, HttpServletResponse response,DepotCheck depotCheck) {
				SysUser sysUser = session.getUserSession(request, AUTH_USER);
				/*log.info("save brand id={}",bean.getId());
				sysLogSvc.operating(request, "brand.log.save", "id=" + bean.getId() + ";name=" + bean.getName());
				*/
				/*SET @doc='<list come_id="1" goods_id="7" position_id="7" acc_num="10" /><list come_id="3" goods_id="2" position_id="2" acc_num="20" />';
				 */
				String docstr="";
				if(depotCheck.getDepotVo()!=null){
					for(DepotVo dv:depotCheck.getDepotVo()){
						docstr+="<list check_id='"+dv.getCheckId()+"'check_num='"+dv.getCheckNum()+"'/>";
					}
					
				}
				JSONObject json = new JSONObject();
				List list=checkSvc.enterCheck(sysUser.getInfoEmp().getInfoDept().getDeptId(),docstr);
				Map map=null;
				if(list!=null){
					 map = (Map)list.get(0);
				}
				if(map.get("message").toString().contains("OK")){
					json.put("statusCode", "200");
					//+" 单号为："+map.get("b_no");
					json.put("message",map.get("message"));
					
				}else{
					json.put("statusCode", "300");
					//+" 单号为："+map.get("b_no");
					json.put("message",map.get("message"));
				}
				ResponseUtils.renderJson(response, json.toJSONString());
			}
			
			/**  
			 * @Title: checkResult  
			 * @Description: 显示盘点结果 
			 * @param request
			 * @param checkNo
			 * @param model
			 * @return String 
			 * @throws  
			 */  
			@RequestMapping("/depot/checkresult.do")
			public String  checkResult(HttpServletRequest request,DepotCheck depotCheck,ModelMap model){
	    		SysUser sysUser = session.getUserSession(request, AUTH_USER);
	    		String checkNo="";
	    		if(depotCheck.getDepotCheckVo()!=null){
	    			checkNo=depotCheck.getDepotCheckVo().getCheckNo();
	    		}
				//查询总部ID
				ParamConfig param=configSvc.get("paramType",7);
				Integer queryDeptId=0;
				if(param.getName().equals(sysUser.getInfoEmp().getInfoDept().getDeptId().toString())){
					queryDeptId=0;
				}else{
					queryDeptId=sysUser.getInfoEmp().getInfoDept().getDeptId();
				}
	    		
	    		
				List depotCheckResultList=checkSvc.getCheckListForResult(queryDeptId,checkNo);
				//盘盈/亏
				model.addAttribute("checkNo", checkNo);
				model.addAttribute("depotCheckResultList", depotCheckResultList);
				String[] eprom={"del","infoDept.deptId"};
				Object[] eobjetm={false,sysUser.getInfoEmp().getInfoDept().getDeptId()};
				List<InfoEmp> empList=empSvc.getList(eprom, eobjetm);
				model.addAttribute("empList", empList);
				model.addAttribute("sysUser", sysUser);
				return "/depot/check/resultlist";
		    }
			
			
			/**  
			 * @Title: depotCheckResult  
			 * @Description: 入账 
			 * @param request
			 * @param response
			 * @param depotCheck void 
			 * @throws  
			 */  
			@RequestMapping("/depot/depotcheckresult.do")
			public void depotCheckResult(HttpServletRequest request, HttpServletResponse response,String checkNo,String memo,Integer empId) {
				SysUser sysUser = session.getUserSession(request, AUTH_USER);
				/*log.info("save brand id={}",bean.getId());
				sysLogSvc.operating(request, "brand.log.save", "id=" + bean.getId() + ";name=" + bean.getName());
				*/
				/*SET @doc='<list come_id="1" goods_id="7" position_id="7" acc_num="10" /><list come_id="3" goods_id="2" position_id="2" acc_num="20" />';
				 */
				JSONObject json = new JSONObject();
				List list=checkSvc.createResultCheck(sysUser.getInfoEmp().getInfoDept().getDeptId(),checkNo,empId,memo,sysUser.getUserId());
				Map map=null;
				if(list!=null){
					 map = (Map)list.get(0);
				}
				if(map.get("message").toString().contains("OK")){
					json.put("statusCode", "200");
					//+" 单号为："+map.get("b_no");
					json.put("message",map.get("message"));
					
				}else{
					json.put("statusCode", "300");
					//+" 单号为："+map.get("b_no");
					json.put("message",map.get("message"));
				}
				ResponseUtils.renderJson(response, json.toJSONString());
			}	
			/**  
			 * @Title: lookUpCheckNo  
			 * @Description: 加载盘点单列表
			 * @param checkNo
			 * @param model
			 * @return List 
			 * @throws  
			 */  
			@RequestMapping("/checklookup/checkno.ajax")
			public String lookUpCheckNo(HttpServletRequest request,String checkNo,Integer isenter,ModelMap model){
				SysUser sysUser = session.getUserSession(request, AUTH_USER);
				//查询总部ID
				ParamConfig param=configSvc.get("paramType",7);
				Integer queryDeptId=0;
				if(param.getName().equals(sysUser.getInfoEmp().getInfoDept().getDeptId().toString())&&isenter==null){
					queryDeptId=0;
				}else{
					queryDeptId=sysUser.getInfoEmp().getInfoDept().getDeptId();
				}
				List<DepotCheck> list=checkSvc.getDepotListByNo(queryDeptId,checkNo);
				List<DepotCheck> newlist= new ArrayList<DepotCheck>();
				for(DepotCheck depot:list){
					InfoDept dep= deptSvc.get(depot.getDepId());
					depot.setDeptName(dep.getName());
					newlist.add(depot);
				}
				model.addAttribute("list", newlist);
				model.addAttribute("checkNo", checkNo);
				model.addAttribute("isenter", isenter);
				return "/depot/check/lookup";
				
			}
			
			
}
