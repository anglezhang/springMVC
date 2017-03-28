package com.cyw.mammoth.action;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.cyw.common.util.DateUtils;
import com.cyw.common.util.StrUtils;
import com.cyw.mammoth.auth.ShiroDbAuthRealm.ShiroUser;
import com.cyw.mammoth.bean.Hconsume;
import com.cyw.mammoth.bean.Hexchange;
import com.cyw.mammoth.bean.Hsettle;
import com.cyw.mammoth.bean.Noguest;
import com.cyw.mammoth.bean.TaDoc;
import com.cyw.mammoth.service.GuestdocSvc;
import com.cyw.mammoth.service.HcodesSvc;
import com.cyw.mammoth.service.HconsumeSvc;
import com.cyw.mammoth.service.HcountrySvc;
import com.cyw.mammoth.service.HexchangeSvc;
import com.cyw.mammoth.service.HfolkSvc;
import com.cyw.mammoth.service.HsettleSvc;
import com.cyw.mammoth.service.NoguestSvc;
import com.cyw.mammoth.service.ParameterSvc;
import com.cyw.mammoth.service.TADocSvc;
import com.cyw.mammoth.vo.AjaxJson;
import com.cyw.mammoth.vo.NoguestSelectVO;
import com.google.gson.Gson;

@SuppressWarnings({ "rawtypes" }) 
@Controller
public class NoGuestAction {
	
	@Autowired
	private HcodesSvc hcodesSvc;
	@Autowired
	private NoguestSvc NoguestSvc;
	@Autowired
	private ParameterSvc parameterSvc;
	@Autowired
	private HsettleSvc hsettleSvc;
	@Autowired
	private TADocSvc  tADocSvc;
	@Autowired
	private GuestdocSvc guestdocSvc;
	/**
	 *  币种代码
	 */
	@Autowired
	private HexchangeSvc hexchangeSvc;
	
	/**
	 * 消费点代码
	 */
	@Autowired
	private HconsumeSvc hconsumeSvc;
	
	/**
	 * @描述 特殊代码查询
	 * */
	@Autowired
	private HfolkSvc hfolkSvc;
	
	/**
	 * @描述 国家查询
	 * */
	@Autowired
	private HcountrySvc hcountrySvc;
	
	
	/**
	 * 获取非住客资料 
	 * 模块：非住客
	 * @param model
	 * @return
	 * @see NoGuestAction.java
	 */
	@RequestMapping("/noguest/noguestIndex.do")
	public String getNoguestList(ModelMap model) {
//		List<Hcodes> ListPayCode = hcodesSvc.getListByCodes("002");
		try {
			//付款方式
			List<Hsettle> ListPayCode = hsettleSvc.findListBy(0);
			model.addAttribute("listPay",ListPayCode);
			//end
			List<Hsettle> hsettls = hsettleSvc.getList("status", 0);//支付方式
			String moneyId = parameterSvc.get(2).getPara5();//本位币代码
			String moneyPayId = parameterSvc.get(1).getPara5();//本位币付款方式代码
			Double chargeAccCode = parameterSvc.get(1).getPara4();//记账精度
			Double settleAccCode = parameterSvc.get(2).getPara4();//结账精度
			String refundCode = parameterSvc.get(6).getPara5();
			List<Hconsume> consumes = hconsumeSvc.getList("status", 0);
			List<Hexchange> exchanges = hexchangeSvc.getList("status",0);//币种
			//取当前酒店日期
			Date hotelDate = parameterSvc.GetHotelDate();
			model.addAttribute("hotelDate", hotelDate.getTime());
			model.addAttribute("hsettls", hsettls);
			model.addAttribute("moneyId",moneyId);
			model.addAttribute("moneyPayId",moneyPayId);
			model.addAttribute("chargeAccCode",chargeAccCode);
			model.addAttribute("settleAccCode",settleAccCode);
			model.addAttribute("consumes", consumes);
			model.addAttribute("refundCode", refundCode);
			model.addAttribute("exchanges", exchanges);
			model.addAttribute("listPay",ListPayCode);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "noguest/noguest";
	}
	
	/** 根据comp_type查询合约单位
	 * <功能详细描述>
	 * @param comp_type
	 * @return
	 * @see NoGuestAction.java
	 */
	@RequestMapping("/noguest/selectcompany.do")
	public @ResponseBody String getCompanyData(@RequestParam String comp_type){
		Gson gson = new Gson();
		List dList = NoguestSvc.getCompanyData(comp_type);
		return gson.toJson(dList);
	}
	
	/** 非住客新增功能
	 * <功能详细描述>
	 * @param noguest
	 * @return
	 * @see NoGuestAction.java
	 */
	@RequestMapping("/noguest/insertnoguest.do")
	@SuppressWarnings({ "unchecked" })   
	public @ResponseBody AjaxJson insertnoguest(Noguest noguest){
		AjaxJson aj = new AjaxJson();
		Subject currentUser = SecurityUtils.getSubject();
		ShiroUser shiroUser = (ShiroUser) currentUser.getPrincipal();
		noguest.setCreaId(shiroUser.getLoginName());//创建人
		noguest.setUpdateTimes(0);//
		noguest.setCreaTime(new Date());//创建时间
		noguest.setModiId(shiroUser.getLoginName());//最后修改人
		noguest.setModiTime(new Date());//最后修改时间
		noguest.setCreditId("111111111111111111111111");
		noguest.setLent(0.00);
		noguest.setStatus(0);
		noguest.setModiId("1111111111");
		noguest.setLastCashier("1111111111");
		noguest.setPayDate(DateUtils.getStartDate(noguest.getPayDate()));
		if(noguest.getHotelFlag()!=null){
			noguest.setHotelFlag(true);//酒店自用标志
		}else{
			noguest.setHotelFlag(false);//酒店自用标志
		}
		boolean flag;
		flag = parameterSvc.getNightAuditState();
		if(!flag){
			int id= NoguestSvc.insertNoGuest(noguest);
			aj.setSuccess(true);
			aj.setMsg("非住客新增成功");
			aj.setObj(id);
		}else{
			aj.setSuccess(false);
			aj.setMsg("非住客新增失败");
		}
		return aj;
	}
	
	
	/** 根据条件查询非住客资料
	 * <功能详细描述>
	 * @param map
	 * @return
	 * @see NoGuestAction.java
	 */
	@RequestMapping("/noguest/selectnoguest.do")
	public @ResponseBody String selectDataNoguest(@RequestParam Map map){
		Gson gson = new Gson();
		List list = NoguestSvc.getSelectNoguest(map);
		return gson.toJson(list);
	}
	
	/** 來自於頁面的條件查詢
	 * <功能详细描述>
	 * @param noguestSelectVO
	 * @return
	 * @see NoGuestAction.java
	 */
	@RequestMapping("/noguest/selectNoguestCond.do")
	public @ResponseBody String selectNoguestCond(NoguestSelectVO noguestSelectVO){
		Gson gson = new Gson();
		List list = NoguestSvc.getNoguestList(noguestSelectVO);
		return gson.toJson(list);
	}
	
	/** 根据主键ID查询非住客详情
	 * <功能详细描述>
	 * @param id
	 * @return
	 * @see NoGuestAction.java
	 */
	@SuppressWarnings({"unchecked" })   
	@RequestMapping("/noguest/selectSignleNoguest.do")
	@ResponseBody
	public String selectSignleNoguest(@RequestParam int noguestId){
		AjaxJson aj = new AjaxJson();
		Map entity=  NoguestSvc.getNoguestInfo(noguestId);
		if(entity!=null){
			entity.put("nogstId",entity.get("nogstId").toString().trim());
			if(StrUtils.isValidString(entity.get("compId").toString())){
				TaDoc taDoc = tADocSvc.get(Integer.parseInt(entity.get("compId").toString().trim()));
				if(null!=taDoc){
					entity.put("unitNamec", taDoc.getNamec());
				}
			}
			aj.setSuccess(true);
			aj.setObj(entity);
			aj.setMsg("指定的数据记录不存在");
		}else{
			aj.setSuccess(true);
			aj.setMsg("指定的数据记录不存在");
		}
		return new Gson().toJson(aj);
	}
	
	/** 根据主键ID查询非住客账目
	 * <功能详细描述>
	 * @param id
	 * @return
	 * @see NoGuestAction.java
	 */
	@SuppressWarnings({"unchecked" })   
	@RequestMapping("/noguest/loadNoguestAccount.do")
	@ResponseBody
	public String loadNoguestAccount(@RequestParam int noguestId){
		Map map = new HashMap();
		Map entity=  NoguestSvc.getNoguestInfo(noguestId);
		List bills = guestdocSvc.getNoguestBills(Integer.valueOf(entity.get("billId").toString()), "A","N", null,null,null);
		if(entity!=null){
			entity.put("nogstId",entity.get("nogstId").toString().trim());
			if(StrUtils.isValidString(entity.get("compId").toString())){
				TaDoc taDoc = tADocSvc.get(Integer.parseInt(entity.get("compId").toString().trim()));
				if(null!=taDoc){
					entity.put("unitNamec", taDoc.getNamec());
				}
			}
			map.put("entity", entity);
			map.put("bills", bills);
		}
		return JSON.toJSONStringWithDateFormat(map, "yyyy-MM-dd");
	}
	
	/** 非住客单条数据维护
	 * <功能详细描述>
	 * @param noguest
	 * @return
	 * @see NoGuestAction.java
	 */
	@SuppressWarnings({ "unchecked" })   
	@RequestMapping("/noguest/updateSigleNoguest.do")
	public @ResponseBody Map updateSigleNoguest(Noguest noguest){
		Map dataMap = new HashMap();
		Noguest noguestoldData = NoguestSvc.get(noguest.getId());
		noguest.setBillId(noguestoldData.getBillId());
		noguest.setBorrow(noguestoldData.getBorrow());
		noguest.setLent(noguestoldData.getLent());
		noguest.setLastCashier(noguestoldData.getLastCashier());
		noguest.setCreaTime(noguestoldData.getCreaTime());
		noguest.setCreaId(noguestoldData.getCreaId());
		noguest.setCreditId(noguestoldData.getCreditId());
		noguest.setStatus(noguestoldData.getStatus());
		noguest.setUpdateTimes(noguestoldData.getUpdateTimes()+1);
		if(noguest.getHotelFlag()==null){
			noguest.setHotelFlag(false);
		}
		Subject currentUser = SecurityUtils.getSubject();
		ShiroUser shiroUser = (ShiroUser) currentUser.getPrincipal();
		boolean flag;
		flag = parameterSvc.getNightAuditState();
		if(!flag){
			noguest.setModiId(shiroUser.getLoginName());
			noguest.setModiTime(new Date());
			NoguestSvc.updateSigleNoguest(noguest);
			dataMap.put("isSuccess", true);
			dataMap.put("msg", "非住客维护成功");
		}else{
			dataMap.put("isSuccess", false);
			dataMap.put("msg", "正在夜审不能进行非住客维护操作！");
		}
		return dataMap;
	}
	
	/** 页面点击放弃，将状态(status)改为1,无效
	 * <功能详细描述>
	 * @param id
	 * @return
	 * @see NoGuestAction.java
	 */
	@SuppressWarnings({ "unchecked" })  
	@RequestMapping("/noguest/updateStatusCancle.do")
	public @ResponseBody Map updateStatusCancle(int id){
		Map dataMap = new HashMap();
		boolean flag;
		flag = parameterSvc.getNightAuditState();
		if(!flag){
			NoguestSvc.updateStatusCancle(id);
			dataMap.put("isSuccess", true);
			dataMap.put("msg", "非住客放弃操作成功");
		}else{
			dataMap.put("isSuccess", false);
			dataMap.put("msg", "正在夜审不能进行非住客放弃操作！");
		}
		
		return dataMap;
	}
	
	/**
	 * 加载A帐，B帐信息
	 * <功能详细描述>
	 * @return
	 * @see GuestdocAction.java
	 */
	@RequestMapping("/noguest/loadNoguestAccounts.do")
	@ResponseBody
	public String getBills(HttpServletRequest request,
			HttpServletResponse response,String billId,String showType,String okFlag,String startDate,String endDate,String isInvalid){
		List bills = null;
		try {
			bills = guestdocSvc.getNoguestBills(Integer.valueOf(billId), showType,okFlag, startDate,endDate,isInvalid);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return JSON.toJSONStringWithDateFormat(bills,"yyyy-MM-dd");
	}
}
