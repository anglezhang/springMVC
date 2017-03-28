package com.zoomoor.jy.action;

import static com.zoomoor.admin.service.SysAuthenticationSvc.AUTH_USER;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.annotations.Target;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zoomoor.admin.entity.SysUser;
import com.zoomoor.admin.service.SysUserSvc;
import com.zoomoor.common.base.bean.Pager;
import com.zoomoor.common.web.ResponseUtils;
import com.zoomoor.common.web.session.SessionProvider;
import com.zoomoor.jy.entity.Allocation;
import com.zoomoor.jy.entity.AllocationApply;
import com.zoomoor.jy.entity.AllocationList;
import com.zoomoor.jy.entity.InfoDept;
import com.zoomoor.jy.entity.InfoGoods;
import com.zoomoor.jy.entity.ParamConfig;
import com.zoomoor.jy.entity.vo.AllocationApplyVo;
import com.zoomoor.jy.entity.vo.DepotVo;
import com.zoomoor.jy.service.AllocationApplySvc;
import com.zoomoor.jy.service.AllocationListSvc;
import com.zoomoor.jy.service.AllocationSvc;
import com.zoomoor.jy.service.InfoDeptSvc;
import com.zoomoor.jy.service.InfoGoodsSvc;
import com.zoomoor.jy.service.ParamConfigSvc;
import com.zoomoor.jy.util.MathUtil;


/**   
 * 类名称：AllocationAct   
 * 类描述：   门店要货调拨
 * 创建人：liuweixing
 * 创建时间：2015-7-29 下午7:20:39   
 * 修改人：liuweixing
 * 修改时间：2015-7-29 下午7:20:39   
 * 修改备注：   
 * @version       
 */ 
@Controller
@Transactional
@SuppressWarnings("unchecked")
public class AllocationAct {
	private static final Logger log = LoggerFactory.getLogger(AllocationAct.class);
	@Autowired
	private SessionProvider session;
	@Autowired
	private AllocationApplySvc applySvc;
	@Autowired
	private InfoDeptSvc deptSvc;
	@Autowired
	private AllocationSvc allocationSvc;
	@Autowired
	private AllocationListSvc allocationListSvc;
	
	@Autowired
	private InfoDeptSvc infoDeptSvc;
	@Autowired
	private SysUserSvc userSvc;
	@Autowired
	private InfoGoodsSvc goodsSvc;
	@Autowired
	private ParamConfigSvc configSvc;
	/**  
	 * 
	 * @Title: list  
	 * @Description:加载调拨列表
	 * @param pager
	 * @param model
	 * @param queryAllocationNo
	 * @param queryEmpName
	 * @param queryDeptId
	 * @param queryStartDate
	 * @param queryEndDate
	 * @return String 
	 * @throws  
	 */ 
	@RequestMapping("/allocation/list.do")
	public String list(HttpServletRequest request,Pager pager,ModelMap model,String queryAllocationNo,String queryEmpName,Integer queryDeptId, Integer iszb, String queryStartDate,String queryEndDate){
		//查询总部ID
		ParamConfig param=configSvc.get("paramType",7);
		SysUser sysUser = session.getUserSession(request, AUTH_USER);
		if(queryDeptId==null) {
			if(param.getName().equals(sysUser.getInfoEmp().getInfoDept().getDeptId().toString())){
				queryDeptId=0;
			}else{
				queryDeptId=sysUser.getInfoEmp().getInfoDept().getDeptId();
				iszb=0;
			}
			
		}
		pager = allocationSvc.getPage(queryAllocationNo,queryEmpName,queryDeptId,queryStartDate,queryEndDate,pager.getPageNum(), pager.getNumPerPage());
		model.addAttribute("pager", pager);
		String[] prom={"del","isShop"};
		Object[] objetm={false,true};
		List<InfoDept>  deptList = deptSvc.getList(prom, objetm);
		model.addAttribute("deptList", deptList);
		model.addAttribute("queryAllocationNo", queryAllocationNo);
		model.addAttribute("queryEmpName", queryEmpName);
		model.addAttribute("queryDeptId", queryDeptId);
		model.addAttribute("queryStartDate", queryStartDate);
		model.addAttribute("queryEndDate", queryEndDate);
		model.addAttribute("iszb",iszb);
		return "allocation/list";
	}
	@RequestMapping("/allocation/view.do")
	public String view(ModelMap model,Integer id){
		List<AllocationList> allocationList=allocationListSvc.getAllocationListById(id);
		model.addAttribute("allocationList", allocationList);
		Allocation allocation=allocationSvc.get(id);
		model.addAttribute("allocation", allocation);
		InfoDept infodept= infoDeptSvc.get(allocation.getDeptId());
		model.addAttribute("todeptName", infodept.getName());
		SysUser user=userSvc.get(allocation.getUserId());
		model.addAttribute("userName", user.getInfoEmp().getEmpName());
		return "allocation/view";
	}
	/**  
	 * @Title: createallocation  
	 * @Description: 加载调拨信息 
	 * @param request
	 * @param model
	 * @param applyIds
	 * @param deptId
	 * @return String 
	 * @throws  
	 */  
	@RequestMapping("/allocation/add.do")
	public String createAllocation(HttpServletRequest request,ModelMap model,String applyIds,Integer deptId,Integer seachType,Integer applytype){
		if(applyIds!=null &&!"".equals(applyIds)){
			//根据调拨申请查询调拨信息
			List<AllocationApply> applyList=applySvc.getListByIds(applyIds);
			model.addAttribute("applyList", applyList);
		}else if(deptId!=null&&deptId>0){
			//根据店铺ID查询调拨信息
			List<AllocationApply> applyList=applySvc.getListByDeptId(deptId);
			model.addAttribute("applyList", applyList);
		}
		if(deptId!=null&&deptId>0){
			InfoDept infodept=deptSvc.get(deptId);
			model.addAttribute("infodept", infodept);
		}
		String[] prom={"del","isShop"};
		Object[] objetm={false,true};
		List<InfoDept>  deptList = deptSvc.getList(prom, objetm);
		model.addAttribute("deptList", deptList);
		model.addAttribute("seachType", seachType);
		SysUser sysUser = session.getUserSession(request, AUTH_USER);
		model.addAttribute("sysUser", sysUser);
		model.addAttribute("applytype", applytype);
		return "allocation/add";
		
	}
	/**  
	 * @Title: siteApply  
	 * @Description: 打开调拨设置 
	 * @param request
	 * @param model
	 * @param applyIds
	 * @param deptId
	 * @return String 
	 * @throws  
	 */  
	@RequestMapping("/allocation/siteapply.action")
	public String siteApply(HttpServletRequest request,ModelMap model,String applyId,Integer goodsId,Integer deptId){
		if(MathUtil.isInteger(applyId)){
			if(Integer.parseInt(applyId)>0){
				AllocationApply applySource=applySvc.get(Integer.parseInt(applyId));
				model.addAttribute("applySource", applySource);
			}
		}else{
			model.addAttribute("zdapplyId", applyId);
		}
		model.addAttribute("goodsId", goodsId);
		//查询当前配件库存
		List<DepotVo> list=applySvc.getDepotNumByGoodsId(goodsId);
		model.addAttribute("depotList", list);
		model.addAttribute("deptId", deptId);
		return "allocation/siteapply";
		
	}
	
	/**  
	 * @Title: saveApply  
	 * @Description: 保存调拨设置
	 * @param request
	 * @param response
	 * @param ids void 
	 * @throws  
	 */  
	
	@RequestMapping("/allocation/saveapply.do")
	public void saveApply(HttpServletRequest request, HttpServletResponse response,AllocationApply apply,String zdapplyId
			,Integer goodsId){
		AllocationApplyVo[] applyVo=apply.getAllocationApplyVo();
		JSONArray arr = new JSONArray();
		String deptName="";
		Double num=0.0;
		if(applyVo!=null){
			for(AllocationApplyVo aav:applyVo){
				if(aav.getNum()!=null){
					InfoDept dept=deptSvc.get(aav.getDeptId());
					deptName+=dept.getName()+"/";
					num+=aav.getNum();
					JSONObject applyVJSon = new JSONObject();
					applyVJSon.put("deptId", aav.getDeptId());
					applyVJSon.put("num", aav.getNum());
					arr.add(applyVJSon);
				}
			}
		}
		JSONObject json = new JSONObject();
		json.put("statusCode", "200");
		json.put("message", "操作成功");
		json.put("callbackType", "closeCurrent");
		if(zdapplyId!=null&&!"".equals(zdapplyId)){
			json.put("applyId",zdapplyId);
		}else{
			json.put("applyId",apply.getApplyId());
		}
		/*log.info("save apply id={}",applyId);
		sysLogSvc.operating(request, "apply.log.save", "id=" + applyId);
		*/
		
		
		json.put("goodsId",goodsId);
		json.put("deptName",deptName.substring(0,deptName.length()-1));
		json.put("num",num);
		json.put("arr",arr);
		ResponseUtils.renderJson(response, json.toJSONString());
	}
	/**  
	 * @Title: save  
	 * @Description: 保存调拨
	 * @param request
	 * @param response
	 * @param apply void 
	 * @throws  
	 */  
	@SuppressWarnings({ "static-access"})
	@RequestMapping("/allocation/save.do")
	public void save(HttpServletRequest request, HttpServletResponse response,AllocationApply  apply, String[] applyList, String memo,Integer deptId){
			SysUser sysUser = session.getUserSession(request, AUTH_USER);
			//保存调拨单
			Allocation allocation = allocationSvc.saveAllocation(memo, deptId, sysUser);
			for(String app:applyList){
				net.sf.json.JSONObject object= new net.sf.json.JSONObject();
				if(app!=null&&!"".equals(app)){
					net.sf.json.JSONObject obj=object.fromObject(app);
					net.sf.json.JSONArray jsonArray =(net.sf.json.JSONArray) obj.get("child"); 
					String applyId=obj.get("applyId").toString();
					Integer goodsId=Integer.parseInt(obj.get("goodsId").toString());
					InfoGoods goods=goodsSvc.get(goodsId);
					Integer unitId=0;
					if(goods!=null){unitId=goods.getParamConfig().getUnitId();}
					String deptNameSource=obj.get("deptName").toString();
					for(int i=0;i<jsonArray.size();i++){
						Integer formdeptId=Integer.parseInt(jsonArray.getJSONObject(i).get("deptId").toString());
						Double num=Double.parseDouble(jsonArray.getJSONObject(i).get("num").toString());
						//保存调拨明细
						allocationListSvc.saveAllocationList(deptId, allocation, goodsId, unitId,	deptNameSource, formdeptId, num,applyId);
					}
					if(MathUtil.isInteger(applyId)){
						AllocationApply aa=applySvc.get(Integer.parseInt(applyId));
						aa.setAllocation(allocation);
						aa.setStatus(1);
						applySvc.update(aa);
					}
					
				}
			}
		JSONObject json = new JSONObject();
		json.put("statusCode", "200");
		json.put("message", "操作成功");
		json.put("callbackType", "closeCurrent");
		ResponseUtils.renderJson(response, json.toJSONString());
	}
	/**  
	 * @Title: print  
	 * @Description: 打印
	 * @param allocationNo
	 * @return String 
	 * @throws  
	 */  
	@RequestMapping("/allocation/print/{allocationNo}")
	public String print(HttpServletRequest request,@PathVariable String allocationNo,ModelMap model){
		List  list = allocationSvc.getAllocationForPrint(allocationNo);
		model.addAttribute("list", list);
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		model.addAttribute("printDate", sdf.format(new Date()));
		SysUser sysUser = session.getUserSession(request, AUTH_USER);
		model.addAttribute("sysUser", sysUser);
		return "allocation/print";
	}
	
	
	
	
}
