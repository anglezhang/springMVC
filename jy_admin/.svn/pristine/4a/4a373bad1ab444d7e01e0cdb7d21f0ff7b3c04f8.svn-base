package com.zoomoor.jy.action;

import static com.zoomoor.admin.service.SysAuthenticationSvc.AUTH_USER;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
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
import com.zoomoor.admin.service.SysAuthenticationSvc;
import com.zoomoor.common.web.ResponseUtils;
import com.zoomoor.common.web.session.SessionProvider;
import com.zoomoor.jy.entity.DepotBill;
import com.zoomoor.jy.entity.InfoDepotPosition;
import com.zoomoor.jy.entity.InfoDept;
import com.zoomoor.jy.entity.InfoEmp;
import com.zoomoor.jy.entity.InfoGoods;
import com.zoomoor.jy.entity.InfoSummary;
import com.zoomoor.jy.entity.ParamConfig;
import com.zoomoor.jy.entity.vo.DepotVo;
import com.zoomoor.jy.service.AllocationApplySvc;
import com.zoomoor.jy.service.DepotBillSvc;
import com.zoomoor.jy.service.InfoDepotPositionSvc;
import com.zoomoor.jy.service.InfoDeptSvc;
import com.zoomoor.jy.service.InfoEmpSvc;
import com.zoomoor.jy.service.InfoGoodsSvc;
import com.zoomoor.jy.service.InfoSummarySvc;
import com.zoomoor.jy.service.ParamConfigSvc;

@Controller
public class DepotBillAct {
	private static final Logger log = LoggerFactory.getLogger(DepotBillAct.class);
	@Autowired
	private InfoSummarySvc summarySvc;
	@Autowired
	private SessionProvider session;
	@Autowired
	private DepotBillSvc billSvc;
	@Autowired
	private InfoEmpSvc empSvc;
	@Autowired
	private AllocationApplySvc applySvc;
	/**  
	 * @Title: manager  
	 * @Description: 打开出入库管理操作界面
	 * @return String 
	 * @throws  
	 */  
	@RequestMapping("depot/manager.do")
	public String manager(HttpServletRequest request,ModelMap model,Boolean SType,Integer subName,Integer isalone){
		SysUser sysUser = session.getUserSession(request, AUTH_USER);
		if(SType==null){SType=false;}
		List  summaryList = summarySvc.getListByUserId(SType,sysUser.getUserId());
		model.addAttribute("summaryList", summaryList);
		model.addAttribute("sysUser", sysUser);
		String[] prom={"del","infoDept.deptId"};
		Object[] objetm={false,sysUser.getInfoEmp().getInfoDept().getDeptId()};
		List<InfoEmp> empList=empSvc.getList(prom, objetm);
		model.addAttribute("empList", empList);
		model.addAttribute("SType", SType);
		model.addAttribute("summaryId", subName);
		model.addAttribute("isalone", isalone);
		return "depot/manager";
	}
	/**  
	 * @Title: getSummaryList  
	 * @Description: 异步查询摘要
	 * @param request
	 * @param response
	 * @param stype
	 * @return List 
	 * @throws  
	 */  
	@SuppressWarnings("rawtypes")
	@ResponseBody
	@RequestMapping("/depot/getsummary.ajax")
	public List getSummaryList(HttpServletRequest request,HttpServletResponse response,Boolean stype){
		SysUser sysUser = session.getUserSession(request, AUTH_USER);
		List summaryList = summarySvc.getListByUserId(stype,sysUser.getUserId());
		return summaryList;
	}
	/**  
	 * @Title: getServiceItem  
	 * @Description: 异步查询服务信息
	 * @param request
	 * @param response
	 * @param tagId
	 * @return List 
	 * @throws  
	 */  
	@SuppressWarnings("rawtypes")
	@ResponseBody
	@RequestMapping("/depot/getServiceItem.ajax")
	public List getServiceItem(HttpServletRequest request,HttpServletResponse response,Integer tagId){
		SysUser sysUser = session.getUserSession(request, AUTH_USER);
		List serviceList = billSvc.getServiceItemByTagId(tagId);
		return serviceList;
	}
	
	/**  
	 * @Title: getGoodsInfoByServiceId  
	 * @Description:异步加载配件退库信息
	 * @param request
	 * @param response
	 * @param serviceCode
	 * @return List 
	 * @throws  
	 */  
	@SuppressWarnings("rawtypes")
	@ResponseBody
	@RequestMapping("/depot/getgoodsinfo.ajax")
	public List getGoodsInfoByServiceId(HttpServletRequest request,HttpServletResponse response,String serviceCode){
		SysUser sysUser = session.getUserSession(request, AUTH_USER);
		List serviceGoodesList = billSvc.getBillListById(6, serviceCode,sysUser.getUserId());
		return serviceGoodesList;
	}
	
	
	
	
	/**  
	 * @Title: openPosition  
	 * @Description: 打开入库设置 
	 * @param model
	 * @param tagId
	 * @return String 
	 * @throws  
	 */  
	@RequestMapping("/depot/openposition.action")
	public String openPosition(ModelMap model,Integer tagId,Double num,Integer summaryId,Integer inx,Integer comeId){
		model.addAttribute("tagId", tagId);
		model.addAttribute("num", num);
		model.addAttribute("summaryId", summaryId);
		model.addAttribute("inx", inx);
		model.addAttribute("comeId", comeId);
		return "depot/position";
	}
	/**  
	 * @Title: openBill  
	 * @Description: 打开出库设置
	 * @param request
	 * @param model
	 * @param goodsId
	 * @param tagId
	 * @param iswx
	 * @return String 
	 * @throws  
	 */  
	@RequestMapping("/depot/openbill.action")
	public String openBill(HttpServletRequest request,ModelMap model,Integer goodsId,Integer tagId,Integer comeId,Integer iswx,Double num,Integer summaryId,Integer inx){
		SysUser sysUser = session.getUserSession(request, AUTH_USER);
		Integer deptId=sysUser.getInfoEmp().getInfoDept().getDeptId();
		List<DepotVo> list=applySvc.getDepotNum(deptId, 0, 0, goodsId, 0,1,1);
		model.addAttribute("list", list);
		model.addAttribute("goodsId", goodsId);
		if(summaryId!=null&&summaryId==11){
			tagId=comeId;
		}
		
		model.addAttribute("tagId", tagId);
		model.addAttribute("iswx", iswx);
		model.addAttribute("num", num);
		model.addAttribute("summaryId", summaryId);
		model.addAttribute("inx", inx);
		return "depot/listbill";
	}
	
	
	/**  
	 * @Title: saveposition  
	 * @Description: 设置仓位
	 * @param request
	 * @param response
	 * @param tagId
	 * @param bill void 
	 * @throws  
	 */  
	@SuppressWarnings("unchecked")
	@RequestMapping("/depot/saveposition.do")
	public void saveposition(HttpServletRequest request, HttpServletResponse response,Integer tagId,Integer comeId,
			DepotBill bill,Integer goodsId,Integer iswx,Integer inx,Integer summaryId,Double num){
		Double countNum=0.0;
		boolean iserror=true;
		JSONArray arr = new JSONArray();
		JSONObject json = new JSONObject();
		if(bill.getInfoDepotPosition()!=null){
			for(InfoDepotPosition position:bill.getInfoDepotPosition()){
				if(position.getNum()!=null){
					iserror=false;
					break;
				}
			}
			if(iserror){
				json.put("statusCode", "300");
				json.put("message","请选择数量！");
			}else{
				for(InfoDepotPosition position:bill.getInfoDepotPosition()){
					if(position.getNum()!=null){
						countNum+=position.getNum();
						JSONObject poditionJSon = new JSONObject();
						poditionJSon.put("positionId", position.getId());
						poditionJSon.put("num", position.getNum());
						poditionJSon.put("rowId", position.getRowId());
						if(summaryId!=null&&summaryId==11){
							poditionJSon.put("tagId", 0);
							
						}else{
							poditionJSon.put("tagId", tagId);
						}
						if(position.getComeId()!=null){
							comeId=position.getComeId();
						}
						poditionJSon.put("comeId", comeId==null?0:comeId);
						arr.add(poditionJSon);
					}
					
				}
				if(summaryId!=null&&(summaryId==4||summaryId==8)){
					if(countNum!=num){
						json.put("statusCode", "300");
						json.put("message","数量只能等于【"+num+"】");
					}else{
						json.put("statusCode", "200");
						json.put("message","设置成功！");
					}
				}else{
					json.put("statusCode", "200");
					json.put("message","设置成功！");
				}
				if(iswx!=null&&iswx>0){
					tagId=goodsId;
				}
				if(summaryId!=null&&summaryId==6){
					tagId=inx;
					countNum=-countNum;
				}
				if(summaryId!=null&&(summaryId==3||summaryId==7)){
					tagId=inx;
				}
				json.put("tagId",tagId);
				json.put("countNum", countNum);
				json.put("arr", arr);
				json.put("callbackType", "closeCurrent");
			}
		}
		ResponseUtils.renderJson(response, json.toJSONString());
	}
	
	/**  
	 * @Title: save  
	 * @Description: 保存出入库信息
	 * @param request
	 * @param response
	 * @param model void 
	 * @throws  
	 */  
	@SuppressWarnings({ "unchecked", "unused", "static-access" })
	@RequestMapping("/depot/depotsave.do")
	public void save(HttpServletRequest request, HttpServletResponse response,
			ModelMap model,DepotBill bill,Integer summaryId) {
		SysUser user = session.getUserSession(request,SysAuthenticationSvc.AUTH_USER);
		//拼接主信息
		bill.setEmpId(bill.getEmpId()==null?0:bill.getEmpId());
		bill.setMemo(bill.getMemo()==null?"":bill.getMemo());
		String mainStr="<main id=\""+bill.getBillId()+"\" dep_id=\""+user.getInfoEmp().getInfoDept().getDeptId()+"\" summary_id=\""+summaryId+"\" emp_id=\""+bill.getEmpId()+"\" memo=\""+bill.getMemo()+"\" />";
		//拼接list表信息
		String listStr="";
		//拼接listrow表信息
		String listRowStr="";
		if(bill.getDeptvo()!=null){
			for(DepotVo dv:bill.getDeptvo()){
				if(dv.getTagId()!=null){
					dv.setComeId(dv.getComeId()==null?0:dv.getComeId());
					dv.setMemo(dv.getMemo()==null?"":dv.getMemo());
					dv.setIsIncludeTax(dv.getIsIncludeTax()==null?0:dv.getIsIncludeTax());
					listStr+="<list list_id=\"0\" list_come_id=\""+dv.getComeId()+"\" taget_id=\""+dv.getTagId()+"\" list_goods_id=\""+dv.getGoodsId()+"\" list_num=\""+dv.getCountNum()+"\" list_unit_id=\""+dv.getUnitId()+"\" isIncludeTax=\""+dv.getIsIncludeTax()+"\" list_memo=\""+dv.getMemo()+"\" />";
					if(summaryId==3){
						//修改调拨状态
						applySvc.updateAllocationApplyByTagId(dv.getTagId());
					}
				}
			}
		}
		if(bill.getPositions()!=null){
			for(String pos:bill.getPositions()){
				net.sf.json.JSONObject object= new net.sf.json.JSONObject();
				if(pos!=null&&!"".equals(pos)){
					net.sf.json.JSONObject obj=object.fromObject(pos);
					net.sf.json.JSONArray jsonArray =(net.sf.json.JSONArray) obj.get("billrow"); 
					for(int i=0;i<jsonArray.size();i++){
						listRowStr+="<row id=\""+jsonArray.getJSONObject(i).get("rowId")+"\" come_id=\""+jsonArray.getJSONObject(i).get("comeId")+"\" tag_id=\""+jsonArray.getJSONObject(i).get("tagId")+"\" num=\""+jsonArray.getJSONObject(i).get("num")+"\" position_id=\""+jsonArray.getJSONObject(i).get("positionId")+"\" />";
					}
				}
				
			}
			
		}
		/*System.out.println(mainStr);
		System.out.println(listStr);
		System.out.println(listRowStr);*/
		
/*		String listStr="<list list_id=\"0\" list_come_id=\"0\" taget_id=\"100\" list_goods_id=\"2\" list_num=\"3\" list_unit_id=\"1\" isIncludeTax=\"1\" list_memo=\"list备注1\" />"+
						"<list list_id=\"0\" list_come_id=\"0\" taget_id=\"99\" list_goods_id=\"7\" list_num=\"21\" list_unit_id=\"1\" isIncludeTax=\"0\" list_memo=\"list备注2\" />"
						+"<list list_id=\"0\" list_come_id=\"0\" taget_id=\"82\" list_goods_id=\"2\" list_num=\"58\" list_unit_id=\"1\" isIncludeTax=\"1\" list_memo=\"list备注3\" />";*/
		/*String listRowStr="<row id=\"0\" come_id=\"0\" tag_id=\"100\" num=\"3\" position_id=\"1\" />"+
							"<row id=\"0\" come_id=\"0\" tag_id=\"99\" num=\"20\" position_id=\"1\" />"+
							"<row id=\"0\" come_id=\"0\" tag_id=\"99\" num=\"1\" position_id=\"2\" />"+
							"<row id=\"0\" come_id=\"0\" tag_id=\"82\" num=\"8\" position_id=\"1\" />"+
							"<row id=\"0\" come_id=\"0\" tag_id=\"82\" num=\"25\" position_id=\"2\" />"+
							"<row id=\"0\" come_id=\"0\" tag_id=\"82\" num=\"25\" position_id=\"3\" />";*/
		
		List list=summarySvc.saveDepot(mainStr,listStr,listRowStr,user.getUserId());
		Map map=null;
		if(list!=null){
			 map = (Map)list.get(0);
		}
		JSONObject json = new JSONObject();
		if(map.get("message").toString().contains("OK")){
			json.put("statusCode", "200");
			//+" 单号为："+map.get("b_no");
			json.put("message",map.get("message"));
			json.put("b_no",map.get("b_no"));
			json.put("summaryId",summaryId);
			
		}else{
			json.put("statusCode", "300");
			//+" 单号为："+map.get("b_no");
			json.put("message",map.get("message"));
		}
		
		
		ResponseUtils.renderJson(response, json.toJSONString());
	}
	/**  
	 * @Title: openSearch  
	 * @Description: 根据摘要检索业务单号
	 * @param request
	 * @param model
	 * @param queryBusinessNo
	 * @param summaryId
	 * @return String 
	 * @throws  
	 */  
	@SuppressWarnings("rawtypes")
	@RequestMapping("/depot/opensearch.action")
	public String openSearch(HttpServletRequest request,ModelMap model,String queryBusinessNo,Integer summaryId){
		SysUser sysUser = session.getUserSession(request, AUTH_USER);
		if(summaryId==11&& (queryBusinessNo==null||"".equals(queryBusinessNo))){
			model.addAttribute("summaryId", summaryId);
			return "depot/listno";
		}
		List list=billSvc.getBillListById(summaryId,queryBusinessNo,sysUser.getUserId());
		model.addAttribute("queryBusinessNo", queryBusinessNo);
		model.addAttribute("summaryId", summaryId);
		model.addAttribute("list", list);
		return "depot/listno";
	}
	/**  
	 * @Title: searchBill  
	 * @Description:  库存查询 
	 * @param request
	 * @param model
	 * @param goodsId
	 * @param tagId
	 * @param iswx
	 * @param num
	 * @param summaryId
	 * @param inx
	 * @return String 
	 * @throws  
	 */  
	@RequestMapping("/depot/searchbill.do")
	public String searchBill(HttpServletRequest request,Integer queryDeptId, DepotBill bill,String queryGoodsCode,String queryGoodsName,
			String queryGoodsBrand,Integer isshop,ModelMap model){
		SysUser sysUser = session.getUserSession(request, AUTH_USER);
		Integer positionId=0;
		Integer brandId=0;
		Integer typeId=0;
		if(bill.getDepotPosition()!=null){positionId=bill.getDepotPosition().getId();}
		if(bill.getInfoBrand()!=null){brandId=bill.getInfoBrand().getId();}
		if(bill.getInfoGoodsType()!=null){typeId=bill.getInfoGoodsType().getGroupId();}
		if(queryDeptId==null) queryDeptId=0;
		if(positionId==null) positionId=0;
		if(brandId==null) brandId=0;
		if(typeId==null) typeId=0;
		//是否是门店查询
		if(isshop!=1){
			String[] prom={"del","isShop"};
			Object[] objetm={false,true};
			List<InfoDept>  deptList = deptSvc.getList(prom, objetm);
			model.addAttribute("deptList", deptList);
		}else{
			queryDeptId=sysUser.getInfoEmp().getInfoDept().getDeptId();	
		}
		model.addAttribute("isshop", isshop);
		model.addAttribute("queryDeptId", queryDeptId);
		List list=applySvc.getDepotCount(queryDeptId,positionId,brandId,
				typeId,queryGoodsCode==null?"":queryGoodsCode,queryGoodsName==null?"":queryGoodsName,queryGoodsBrand==null?"":queryGoodsBrand);
		model.addAttribute("list", list);
		model.addAttribute("queryGoodsName", queryGoodsName);
		model.addAttribute("queryGoodsCode", queryGoodsCode);
		model.addAttribute("bill", bill);
		model.addAttribute("queryGoodsBrand", queryGoodsBrand);
		return "depot/searchbill";
	}
	
	@RequestMapping("/depot/searchview.do")
	public String searchView(HttpServletRequest request,Integer goodsId,Integer deptId,Integer position,Double balance,ModelMap model ){
		List list=billSvc.getGoodsInfoExt(goodsId);
		InfoDept dept= deptSvc.get(deptId);
		String positionName=positionSvc.getPositionFullName(position);
		Map map =(Map) list.get(0);
		model.addAttribute("price_no_tax", map.get("price_no_tax"));
		model.addAttribute("pur_order_date", map.get("pur_order_date"));
		model.addAttribute("order_price", map.get("order_price"));
		model.addAttribute("discount", map.get("discount"));
		model.addAttribute("add_I", map.get("add_I"));
		InfoGoods infoGoods= goodsSvc.get(goodsId);
		model.addAttribute("infoGoods", infoGoods);
		model.addAttribute("deptName", dept.getName());
		model.addAttribute("position", positionName);
		model.addAttribute("balance", balance);
		
		return "depot/searchview";
	}
	/**  
	 * @Title: depotDetail  
	 * @Description: 仓库明细
	 * @param pager
	 * @param queryDeptId
	 * @param queryBno
	 * @param queryStartDate
	 * @param queryEndDate
	 * @param model
	 * @return String 
	 * @throws  
	 */  
	@RequestMapping("/depot/depotDetail.do")
	public String depotDetail(HttpServletRequest request,Integer queryDeptId,String queryStartDate,String queryEndDate,Integer summaryId,Integer  iszb, String queryBno,ModelMap model){
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
		if(summaryId==null)summaryId=0;
		if(queryBno==null)queryBno="";
		SimpleDateFormat sf= new SimpleDateFormat("yyyy-MM-dd");
		if(queryStartDate==null){
			queryStartDate=sf.format(new Date()).toString();
		}
		if(queryEndDate==null){
			queryEndDate=sf.format(new Date()).toString();
			
		}
		List list = billSvc.getDepotDetailList(queryDeptId,queryStartDate,queryEndDate,summaryId,queryBno);
		model.addAttribute("list",list);
		String[] prom={"del","isShop"};
		Object[] objetm={false,true};
		List<InfoDept>  deptList = deptSvc.getList(prom, objetm);
		model.addAttribute("deptList", deptList);
		model.addAttribute("queryDeptId",queryDeptId);
		model.addAttribute("queryStartDate",queryStartDate);
		model.addAttribute("queryEndDate",queryEndDate);
		model.addAttribute("queryBno",queryBno);
		model.addAttribute("summaryId",summaryId);
		List<InfoSummary> summaryList=summarySvc.getList("del", false);
		model.addAttribute("summaryList",summaryList);
		model.addAttribute("iszb",iszb);
		return "depot/depotDetail";
		
	}
	
	/**  
	 * @Title: print  
	 * @Description: 打印 
	 * @param model
	 * @param queryDeptId
	 * @param summaryId
	 * @param queryBno
	 * @return String 
	 * @throws  
	 */  
	@RequestMapping("/depot/print/{queryDeptId}/{summaryId}/{queryBno}")
	public String print(ModelMap model, @PathVariable Integer queryDeptId,@PathVariable Integer summaryId, @PathVariable String queryBno){
		List list = billSvc.getDepotBillPrint(queryDeptId,queryBno);
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		model.addAttribute("printDate", sdf.format(new Date()));
		model.addAttribute("list", list);
		if(summaryId==1){
			Double hsje=0.0;
			Double bhsje=0.0;
			if(list!=null){
				for(int i=0;i<list.size();i++){
					Map map =(Map) list.get(i);
					hsje+=Double.parseDouble(map.get("order_money_incTax").toString());
					bhsje+=Double.parseDouble(map.get("order_money").toString());
				}
			}
			model.addAttribute("hsje", hsje);
			model.addAttribute("bhsje", bhsje);
			model.addAttribute("se", hsje-bhsje);
			return "depot/print/cginprint";
		}
		if(summaryId==3){
			//调拨入库
			Double ckcount=0.0;
			if(list!=null){
				for(int i=0;i<list.size();i++){
					Map map =(Map) list.get(i);
					ckcount+=Double.parseDouble(map.get("num").toString());
				}
			}
			model.addAttribute("ckcount", ckcount);
			return "depot/print/dbinprint";
		}
		if(summaryId==4){
			return "盘盈";
		}
		if(summaryId==8){
			return "盘亏";
		}
		if(summaryId==5){
			//维修发料
			Double ckcount=0.0;
			Double moneycount=0.0;
			if(list!=null){
				for(int i=0;i<list.size();i++){
					Map map =(Map) list.get(i);
					ckcount+=Double.parseDouble(map.get("num").toString());
					moneycount+=Double.parseDouble(map.get("order_price").toString());
				}
			}
			model.addAttribute("ckcount", ckcount);
			model.addAttribute("moneycount", moneycount);
			return "depot/print/wxflprint";
		}
		if(summaryId==6){
			return "维修发料退库";
		}
		if(summaryId==7){
			//调拨出库
			Double ckcount=0.0;
			if(list!=null){
				for(int i=0;i<list.size();i++){
					Map map =(Map) list.get(i);
					ckcount+=Double.parseDouble(map.get("num").toString());
				}
			}
			model.addAttribute("ckcount", ckcount);
			return "depot/print/dboutprint";
		}
		if(summaryId==11){
			//配件领用
			Double ckcount=0.0;
			if(list!=null){
				for(int i=0;i<list.size();i++){
					Map map =(Map) list.get(i);
					ckcount+=Double.parseDouble(map.get("num").toString());
				}
			}
			model.addAttribute("ckcount", ckcount);
			return "depot/print/lyprint";
		}
		return "";
	}
	/**  
	 * @Title: confirmPrint  
	 * @Description: 确认打印
	 * @param summaryId
	 * @param bNo
	 * @param model
	 * @return String 
	 * @throws  
	 */  
	@RequestMapping("/depot/confirmprint.action")
	public String confirmPrint(HttpServletRequest request,Integer summaryId,String bNo,ModelMap model){
		model.addAttribute("bNo", bNo);
		model.addAttribute("summaryId", summaryId);
		InfoSummary summary=summarySvc.get(summaryId);
		model.addAttribute("summary", summary);
		SysUser sysUser = session.getUserSession(request, AUTH_USER);
		model.addAttribute("deptId", sysUser.getInfoEmp().getInfoDept().getDeptId());
		return "depot/confirmprint";
	}
	
	
	
	
	
	
	
	
	
	@Autowired
	private InfoDeptSvc deptSvc;
	@Autowired
	private InfoGoodsSvc goodsSvc;
	@Autowired
	private  InfoDepotPositionSvc positionSvc;
	@Autowired
	private ParamConfigSvc configSvc;
}
