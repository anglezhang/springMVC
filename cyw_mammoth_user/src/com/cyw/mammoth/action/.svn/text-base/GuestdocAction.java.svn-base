package com.cyw.mammoth.action;

import java.io.InputStream;
import java.io.OutputStream;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.cyw.common.util.DateUtil;
import com.cyw.mammoth.auth.AppBaseCfg;
import com.cyw.mammoth.bean.BPaied;
import com.cyw.mammoth.bean.Bills;
import com.cyw.mammoth.bean.BookRoom;
import com.cyw.mammoth.bean.GrpDoc;
import com.cyw.mammoth.bean.GstCreditAuth;
import com.cyw.mammoth.bean.GstPriceList;
import com.cyw.mammoth.bean.Guestdoc;
import com.cyw.mammoth.bean.Hcodes;
import com.cyw.mammoth.bean.Hconsume;
import com.cyw.mammoth.bean.Hcountry;
import com.cyw.mammoth.bean.Hexchange;
import com.cyw.mammoth.bean.Hfolk;
import com.cyw.mammoth.bean.HgstOri;
import com.cyw.mammoth.bean.HgstOriType;
import com.cyw.mammoth.bean.Hsettle;
import com.cyw.mammoth.bean.Noguest;
import com.cyw.mammoth.bean.Operator;
import com.cyw.mammoth.bean.Parameter;
import com.cyw.mammoth.service.BPaiedSvc;
import com.cyw.mammoth.service.BillsSvc;
import com.cyw.mammoth.service.BookRoomSvc;
import com.cyw.mammoth.service.GrpDocSvc;
import com.cyw.mammoth.service.GstBillSvc;
import com.cyw.mammoth.service.GstCreditAuthSvc;
import com.cyw.mammoth.service.GstPriceListSvc;
import com.cyw.mammoth.service.GuestdocSvc;
import com.cyw.mammoth.service.HcodesSvc;
import com.cyw.mammoth.service.HconsumeSvc;
import com.cyw.mammoth.service.HcountrySvc;
import com.cyw.mammoth.service.HexchangeSvc;
import com.cyw.mammoth.service.HfolkSvc;
import com.cyw.mammoth.service.HgstOriSvc;
import com.cyw.mammoth.service.HgstOriTypeSvc;
import com.cyw.mammoth.service.HsettleSvc;
import com.cyw.mammoth.service.NoguestSvc;
import com.cyw.mammoth.service.ParameterSvc;
import com.cyw.mammoth.vo.AjaxJson;
import com.cyw.mammoth.vo.GroupSearchVO;
import com.cyw.mammoth.vo.GuestSearchVO;
import com.cyw.mammoth.vo.GuestSourceVO;
import com.google.gson.Gson;

@SuppressWarnings({ "unchecked", "rawtypes" }) 
@Controller
@RequestMapping("/guest")
public class GuestdocAction extends BaseAction {

	@Autowired
	private GuestdocSvc guestdocSvc;
	
	@Autowired
	private HcodesSvc hcodesSvc;
	
	@Autowired
	private GstPriceListSvc gstPriceListSvc;
	
	@Autowired
	private ParameterSvc parameterSvc;
	
	@Autowired
	private HconsumeSvc hconsumeSvc;
	
	@Autowired
	private BPaiedSvc bPaiedSvc;
	
	@Autowired
	private HexchangeSvc hexchangeSvcl;
	
	@Autowired
	private HsettleSvc hsettleSvc;
	
	@Autowired
	private BillsSvc billsSvc;
	
	@Autowired
	private GstCreditAuthSvc gstCreditAuthSvc;
	
	@Autowired
	private GrpDocSvc grpDocSvc;
	
	@Autowired
	private NoguestSvc NoguestSvc;
	
	@Autowired
	private HgstOriSvc hgstOriSvc ;
	
	@Autowired
	private HgstOriTypeSvc hgstOriTypeSvc ;
	
	private BookRoomSvc bookRoomSvc;
	@Autowired
	public void setBookRoomSvc(BookRoomSvc bookRoomSvc) {
		this.bookRoomSvc = bookRoomSvc;
	}
	
	
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
	 * @描述 账务SVC
	 * */
	@Autowired
	private GstBillSvc gstbillSvc;
	
	/**
	 *  币种代码
	 */
	@Autowired
	private HexchangeSvc hexchangeSvc;	
	/**
	 * 根据房间号得到客户信息列表 
	 * 模块：房态-换房续住
	 * @param request
	 * @param response
	 * @param roomId 房间号
	 * @return list 客户列表
	 * @see GuestdocAction.java
	 * 
	 */
	@RequestMapping("list.do")
	@ResponseBody
	public String getGuestListByRoomId(HttpServletRequest request,
			HttpServletResponse response, String roomId,String withId,String chkStat) {
//		if (roomId == null || "".equals(roomId))
//			return "";
		// 获取房间客户列表
		GuestSearchVO searchVO = new GuestSearchVO();
		if(StringUtils.isNotEmpty(roomId)){
			if(roomId.startsWith("*")){
				roomId = roomId.substring(1);
			}
			searchVO.setRoomId(roomId);
		}
		if(StringUtils.isNotEmpty(withId)){
			searchVO.setWithId(withId);
		}
		if(StringUtils.isNotEmpty(chkStat)){
			searchVO.setChkStat(chkStat);
		}
		List list = guestdocSvc.getGuestDocList(searchVO);
		return JSON.toJSONStringWithDateFormat(list,"yyyy-MM-dd");
	}

	
	/**
	 * 获取住客资料 
	 * 模块：住客
	 * @param request
	 * @param response
	 * @param searchVO
	 * @return
	 * @see GuestdocAction.java
	 */
	@RequestMapping("gstIndex.do")
	public String getList(HttpServletRequest request,
			HttpServletResponse response, ModelMap model, GuestSearchVO searchVO) {
//		List list = guestdocSvc.getGuestDocList(searchVO);
//		Gson gson = new Gson();
//		String data = gson.toJson(list);
//		model.addAttribute("data", data);
		return "guest/guest";
	}
	
	/**
	 * 住客资料查询
	 * 模块：住客
	 * @param request
	 * @param response
	 * @param model
	 * @param searchVO
	 * @return
	 * @see GuestdocAction.java
	 */
	@RequestMapping("searchGuest.do")
	@ResponseBody
	public String searchGuestInfo(HttpServletRequest request,
			HttpServletResponse response, ModelMap model, GuestSearchVO searchVO){
		List list = null;
		try {
			list = guestdocSvc.getGuestDocList(searchVO);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return JSON.toJSONStringWithDateFormat(list,"yyyy-MM-dd");
	}
	
	/**
	 * 获取团队资料
	 * 模块：住客
	 * @param request
	 * @param response
	 * @param model
	 * @param searchVO
	 * @return
	 * @see GuestdocAction.java
	 */
	@RequestMapping("groupIndex.do")
	public String getGroupList(HttpServletRequest request,
			HttpServletResponse response, Model model, GroupSearchVO searchVO) {
//		List list = guestdocSvc.getg
//		model.addAttribute("data", list);
//		Gson gson = new Gson();
		setGuestSelectInfo(model);
		return "guest/group";
	}
	
	/**
	 *@描述 设置界面下拉框信息
	 * */
	private void setGuestSelectInfo(Model model) {
		try {
			
			List<Hcodes> crdlist = hcodesSvc.getListByCodes("006");//证件类型
			List<Hcodes> genderlist = hcodesSvc.getListByCodes("026");//性别
			List<Hcodes> prolist = hcodesSvc.getListByCodes("008");//省份
			List<Hcodes> gstTypelist = hcodesSvc.getListByCodes("004");//客人分类
			List<Hfolk> hfolklist = hfolkSvc.findListBy(0);//民族
			List<Hcountry> countrylist = hcountrySvc.findListBy(0);//国家查询
			List<Hsettle> hsettls = hsettleSvc.getList("status", 0);//支付方式
			List<GuestSourceVO> gstSrcList = hcodesSvc.getGuestSource();//客人来源
			List<Hexchange> exchanges = hexchangeSvc.getList("status",0);//客人来源
			//List roomBills = guestdocSvc.getRoomBillInfo(roomId);
			String depositCode = parameterSvc.get(3).getPara5();//押金代码
			String moneyId = parameterSvc.get(2).getPara5();//本位币代码
			String moneyPayId = parameterSvc.get(1).getPara5();//本位币付款方式代码
			String forALongCode = parameterSvc.get(4).getPara5();//半日组代码
			Double chargeAccCode = parameterSvc.get(1).getPara4();//记账精度
			Double settleAccCode = parameterSvc.get(2).getPara4();//结账精度
			String refundCode = parameterSvc.get(6).getPara5();
			List<Hconsume> consumes = hconsumeSvc.getList("status", 0);
			//取当前酒店日期
			Date hotelDate = parameterSvc.GetHotelDate();
			model.addAttribute("hotelDate", hotelDate.getTime());
			model.addAttribute("hfolklist", hfolklist);
			model.addAttribute("countrylist", countrylist);
			model.addAttribute("hsettls", hsettls);
			model.addAttribute("gstSrcList", gstSrcList);
			model.addAttribute("exchanges", exchanges);
			model.addAttribute("crdlist", crdlist);
			model.addAttribute("genderlist", genderlist);
			model.addAttribute("prolist", prolist);
			model.addAttribute("gstTypelist", gstTypelist);
			model.addAttribute("depositCode",depositCode.trim());
			model.addAttribute("moneyId",moneyId);
			model.addAttribute("moneyPayId",moneyPayId);
			model.addAttribute("forALongCode",forALongCode);
			model.addAttribute("chargeAccCode",chargeAccCode);
			model.addAttribute("settleAccCode",settleAccCode);
			model.addAttribute("consumes", consumes);
			//model.addAttribute("roomBills", roomBills);
			model.addAttribute("refundCode", refundCode);
			System.out.println(moneyId+"===="+moneyId.length());
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error("初始化列表报错");
		}
	}
	/**
	 * 新增修改客人时校验是否存在付款人
	 * <功能详细描述>
	 * @param request
	 * @param response
	 * @param model
	 * @param searchVO
	 * @param check_id
	 * @return
	 * @see GuestdocAction.java
	 */
	@RequestMapping("checkPaymanFlag.do")
	@ResponseBody
	public String checkPaymanFlag(HttpServletRequest request,
			HttpServletResponse response, ModelMap model, GuestSearchVO searchVO,String check_id) {
		List list = guestdocSvc.getGuestDocList(searchVO);
		AjaxJson ajaxJson = new AjaxJson();
		if(list!=null && list.size()>0){
			Map map = null;
			for(int i=0;i<list.size();i++){
				map = (Map)list.get(i);
				String paymanflag = (String)map.get("payman_flag");
				if("1".equals(paymanflag)){
					if(StringUtils.isEmpty(check_id) || !check_id.equals((String)map.get("check_id"))){
						ajaxJson.setSuccess(false);
						break;
					}
				}
			}
		}
		return JSON.toJSONString(ajaxJson);
	}
	
	/**
	 * 团队资料查询
	 * 模块：住客
	 * @param request
	 * @param response
	 * @param model
	 * @param searchVO
	 * @return
	 * @see GuestdocAction.java
	 */
	@RequestMapping("searchGroup.do")
	@ResponseBody
	public String searchGroupInfo(HttpServletRequest request,
			HttpServletResponse response, ModelMap model, GroupSearchVO searchVO){
		List list = null;
		try {
			 list = guestdocSvc.getGroupList(searchVO);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return JSON.toJSONStringWithDateFormat(list,"yyyy-MM-dd");
	}
	
	
	/**
	 * 获取客单详情
	 * <功能详细描述>
	 * @param request
	 * @param response
	 * @param check_id
	 * @param type
	 * @return
	 * @see GuestdocAction.java
	 */
	@RequestMapping("guestDocDetail.do")
	@ResponseBody
	public String getGuestDocDetail(HttpServletRequest request,
			HttpServletResponse response,String check_id,String with_id,String room_id){
		Map resultMap = new HashMap();
		Map map = null;
		List roomList = null;
		List guestList = null;
		try {
			//住客个人资料
			if(StringUtils.isNotEmpty(check_id)){
				map = guestdocSvc.getGuestDocDetail(check_id);
			}
			//住客详情中房间列表
			if(StringUtils.isNotEmpty(with_id)){
				roomList = guestdocSvc.getRoomsList(with_id);
			}
			//房间客户信息
			GuestSearchVO searchVO = new GuestSearchVO();
			if(StringUtils.isNotEmpty(room_id)){
				searchVO.setRoomId(room_id);
			}
			if(StringUtils.isNotEmpty(with_id)){
				searchVO.setWithId(with_id);
			}
			guestList = guestdocSvc.getGuestDocList(searchVO);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		//最终结果
		resultMap.put("detail",map);
		resultMap.put("rooms", roomList);
		resultMap.put("guests", guestList);
		return JSON.toJSONStringWithDateFormat(resultMap,"yyyy-MM-dd");
	}
	
	/**
	 * 新增客人
	 * <功能详细描述>
	 * @param request
	 * @param response
	 * @param guestdoc
	 * @param Alimit
	 * @param Blimit
	 * @return
	 * @see GuestdocAction.java
	 */
	@RequestMapping("saveGuest.do")
	@ResponseBody
	public String addGuest(HttpServletRequest request,
			HttpServletResponse response, Guestdoc guestdoc,Double Alimit,Double Blimit) {
		int checkid = 0;
		AjaxJson bean = new AjaxJson();
		Gson gson = new Gson();
		String operId = AppBaseCfg.getOperator().getOperId();
		try {
			guestdoc.setLimit(0.0);
			guestdoc.setChkExt("0");
			guestdoc.setRoomPrice(0.0);
			guestdoc.setChkOperid(operId);
			checkid = guestdocSvc.saveGuest(guestdoc,Alimit,Blimit);
			bean.setObj(new Integer(checkid));
			bean.setMsg("新增成功");
			bean.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
			bean.setMsg("新增失败");
			bean.setSuccess(false);
			return gson.toJson(bean);
		}
		return gson.toJson(bean);
	}
	
	/**
	 * 更改住客信息
	 * <功能详细描述>
	 * @param request
	 * @param response
	 * @param guestdoc
	 * @param Alimit
	 * @param Blimit
	 * @return
	 * @see GuestdocAction.java
	 */
	@RequestMapping("updateGuest.do")
	@ResponseBody
	public String updateGuest(HttpServletRequest request,
			HttpServletResponse response,Guestdoc guestdoc,Double Alimit,Double Blimit){
		AjaxJson bean = new AjaxJson();
		try{
			guestdocSvc.updateGuestdoc(guestdoc,Alimit,Blimit);
			bean.setMsg("修改成功");
			bean.setSuccess(true);
		}catch (Exception e) {
			e.printStackTrace();
			bean.setMsg("修改失败");
			bean.setSuccess(false);
			return JSON.toJSONString(bean);
		}
		return JSON.toJSONString(bean);
	}
	
	/**
	 * 查询代码表数据
	 * <功能详细描述>
	 * @param request
	 * @param response
	 * @param code
	 * @return
	 * @see GuestdocAction.java
	 */
	@RequestMapping("getCodes.do")
	@ResponseBody
	public String getCodes(HttpServletRequest request,
			HttpServletResponse response,String code){
		List<Hcodes> list = null;
		try {
			list = hcodesSvc.getListByCodes(code);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return JSON.toJSONString(list);
	}
	
	/**
	 * 房价列表
	 * <功能详细描述>
	 * @param request
	 * @param response
	 * @param check_id
	 * @return
	 * @throws ParseException
	 * @see GuestdocAction.java
	 */
	@RequestMapping("gstprice.do")
	@ResponseBody
	public String getGstPriceList(HttpServletRequest request,
			HttpServletResponse response,Integer check_id) throws ParseException{
		List<GstPriceList> list = null;
		try {
			list = gstPriceListSvc.getList("checkId", check_id);
			if(list!=null){
				String week =null;
				Date d = null;
				for(GstPriceList gp:list){
					d = gp.getHotelDate();
					week = getDay(d);
					gp.setWeek(week);
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return JSON.toJSONString(list);		
	}
	
	/**
	 * 复制客人
	 * <功能详细描述>
	 * @param request
	 * @param response
	 * @param checkId
	 * @return
	 * @see GuestdocAction.java
	 */
	@RequestMapping("copyGuest.do")
	@ResponseBody
	public String copyGuest(HttpServletRequest request,
			HttpServletResponse response,Integer checkId){
		AjaxJson json = new AjaxJson();
		Map map = null;
		try {
			map = guestdocSvc.copyGuest(checkId);
			json.setMsg("复制成功");
			json.setSuccess(true);
			json.setObj(map);
		} catch (Exception e) {
			e.printStackTrace();
			json.setMsg("复制失败");
			json.setSuccess(false);
			return JSON.toJSONString(json);
		}
		return JSON.toJSONString(json);
	}
	
	/**
	 * 获取日期是星期几
	 * <功能详细描述>
	 * @param date
	 * @return
	 * @throws ParseException
	 * @see GuestdocAction.java
	 */
	public static String getDay(Date date) throws ParseException {
		String[] weeks={"周日","周一","周二","周三","周四","周五","周六"};
	    Calendar cal = Calendar.getInstance();
	    cal.setTime(date);
	    return weeks[cal.get(Calendar.DAY_OF_WEEK)-1];
	}
	
	/**
	 * 账目加载
	 * <功能详细描述>
	 * @param request
	 * @param response
	 * @param checkId
	 * @return
	 * @see GuestdocAction.java
	 */
	@RequestMapping("consumes.do")
	@ResponseBody
	public String getConsumes(HttpServletRequest request,
			HttpServletResponse response,Integer checkId){
		Map map = new HashMap();
		List blist = new ArrayList();
		List list = null;
		BPaied bp = null;
		Guestdoc guestdoc = guestdocSvc.get(checkId);
		if(guestdoc==null) return null;
		try {
			bp = bPaiedSvc.get(checkId);
			if (bp != null) {
				list = hconsumeSvc.getConsumeList(bp.getCons());
				String[] cons = bp.getCons().split(",");
				for (int i = 0; i < cons.length; i++) {
					Hconsume consume = hconsumeSvc.get("codeId", cons[i]);
					blist.add(consume);
				}
				map.put("bpaied", bp);
			} else {
				list = hconsumeSvc.getList("status", 0);
				map.put("bpaied", new BPaied());
			}
			map.put("consumes", list);
			map.put("blist", blist);
			map.put("ifBate", guestdoc.getIfBdate());
			map.put("chkStat", guestdoc.getChkStat());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return JSON.toJSONStringWithDateFormat(map, "yyyy-MM-dd");
	}
	
	/**
	 * 设置分账
	 * <功能详细描述>
	 * @param request
	 * @param response
	 * @param bpaied
	 * @param ifBate
	 * @return
	 * @see GuestdocAction.java
	 */
	@RequestMapping("addBpaied.do")
	@ResponseBody
	public String setSplit(HttpServletRequest request,
			HttpServletResponse response,BPaied bpaied,String ifBate){
		AjaxJson json = new AjaxJson();
		try {
			//检查是否设置过B账信息
			String operId = AppBaseCfg.getOperator().getOperId();
			bpaied.setOperId(operId);
			guestdocSvc.setSplit(bpaied, ifBate);
			json.setMsg("保存成功");
			json.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
			json.setMsg("保存失败");
			json.setSuccess(false);
			return JSON.toJSONString(json);
		}
		return JSON.toJSONString(json);
	}
	
	/**
	 * 房价列表修改
	 * <功能详细描述>
	 * @param request
	 * @param response
	 * @param data
	 * @return
	 * @see GuestdocAction.java
	 */
	@RequestMapping("updatePrice.do")
	@ResponseBody
	public String updatePrice(HttpServletRequest request,
			HttpServletResponse response,String data){
		AjaxJson json = new AjaxJson();
		try {
			JSONArray arr = JSONArray.parseArray(data);
			for (int i = 0; i < arr.size(); i++) {
				JSONObject obj = JSONObject.parseObject(arr.get(i).toString());
				gstPriceListSvc.updatePrice(obj.getInteger("id"),
						obj.getDouble("value"));
			}
			json.setMsg("保存成功");
			json.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
			json.setMsg("保存失败");
			json.setSuccess(false);
			return JSON.toJSONString(json);
		}
		return JSON.toJSONString(json);
	}
	
	/**
	 * 客人账目页面信息加载
	 * <功能详细描述>
	 * @param request
	 * @param response
	 * @return
	 * @see GuestdocAction.java
	 */
	@RequestMapping("guestAccountInfo.do")
	@ResponseBody
	public String getGuestAccountInfo(String checkId,String roomId,String withId,
			Integer billType,String billId,String showType,String consId
			,String okFlag,String startDate,String endDate
			,String isInvalid,String noGuest){
		Map resultMap = new HashMap();
		Map map = null;
		List bills = null;
		List guestList = null;
		List roomBills = null;
		try {
			//住客个人资料
			if(StringUtils.isNotEmpty(checkId)){
				map = guestdocSvc.getGuestDocDetail(checkId);
			}
			bills = guestdocSvc.getBills(billType, billId,showType,consId,okFlag,startDate,endDate,isInvalid);
			//房间客户信息
			if(noGuest==null){
				GuestSearchVO searchVO = new GuestSearchVO();
				if(StringUtils.isNotEmpty(roomId)){
					searchVO.setRoomId(roomId);
				}
				if(StringUtils.isNotEmpty(withId)){
					searchVO.setWithId(withId);
				}
				guestList = guestdocSvc.getGuestDocList(searchVO);
				resultMap.put("guests",guestList);
			}
			if(StringUtils.isNotEmpty(roomId)){
				roomBills = guestdocSvc.getRoomBillInfo(roomId);
			}
			resultMap.put("detail",map);
			resultMap.put("bills",bills);
			resultMap.put("roomBills", roomBills);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return JSON.toJSONStringWithDateFormat(resultMap,"yyyy-MM-dd");
	}
	
	/**
	 * 加载A帐，B帐信息
	 * <功能详细描述>
	 * @return
	 * @see GuestdocAction.java
	 */
	@RequestMapping("loadAccounts.do")
	@ResponseBody
	public String getBills(HttpServletRequest request,
			HttpServletResponse response,Integer billType,String billId,String showType,String consId,String okFlag,String startDate,String endDate,String isInvalid){
		List bills = null;
		try {
			bills = guestdocSvc.getBills(billType, billId,showType,consId,okFlag,startDate,endDate,isInvalid);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return JSON.toJSONStringWithDateFormat(bills,"yyyy-MM-dd");
	}
	
	/**
	 * 帐号回车事件
	 * <功能详细描述>
	 * @param request
	 * @param response
	 * @param bill_id
	 * @return
	 * @see GuestdocAction.java
	 */
	@RequestMapping("searchByAccount.do")
	@ResponseBody
	public String searchByAccount(HttpServletRequest request,
			HttpServletResponse response,String bill_id){
		Map resultMap = new HashMap();
		Map map = null;
		List bills = null;
		List roomList = null;
		List<Guestdoc> guestList = null;
		Guestdoc guestdoc = null;
		try {
			// 查询A账信息
			guestdoc = guestdocSvc.get("billaId", Integer.valueOf(bill_id));
			//如果为空则检查B账
			if (guestdoc == null) {
				guestList = guestdocSvc.getList("billbId", Integer.valueOf(bill_id));
				if (guestList == null || guestList.size() == 0) {
					return null;
				} else {
					map = guestdocSvc.getGuestDocDetail(String
							.valueOf(guestList.get(0).getCheckId()));
					bills = guestdocSvc.getBills(2, bill_id, "A", null, "A",
							null, null,null);
					roomList = guestdocSvc.getRoomsList(String
							.valueOf(guestList.get(0).getWithId()));
					resultMap.put("billType", "2");
				}
			} else {
				map = guestdocSvc.getGuestDocDetail(String.valueOf(guestdoc
						.getCheckId()));
				bills = guestdocSvc.getBills(1, bill_id, "A", null, "A", null,
						null,null);
				GuestSearchVO searchVO = new GuestSearchVO();
				searchVO.setRoomId(guestdoc.getRoomId());
				searchVO.setWithId(String.valueOf(guestdoc.getWithId()));
				guestList = guestdocSvc.getGuestDocList(searchVO);
				roomList = guestdocSvc.getRoomsList(String.valueOf(guestdoc
						.getWithId()));
				resultMap.put("billType", "1");
			}
			resultMap.put("detail", map);
			resultMap.put("bills", bills);
			resultMap.put("guests", guestList);
			resultMap.put("rooms", roomList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return JSON.toJSONStringWithDateFormat(resultMap,"yyyy-MM-dd");
	}
	
	/**
	 * 加载照片
	 * <功能详细描述>
	 * @param request
	 * @param resp
	 * @param tableName
	 * @param colName
	 * @param where
	 * @return
	 * @see GuestdocAction.java
	 */
	@RequestMapping("loadImage.do")
	@ResponseBody
	public String searchByAccount(HttpServletRequest request,
			HttpServletResponse resp,String tableName,String colName,String where) {
        // 设置响应头 Content-type类型  
        resp.setContentType("image/jpeg");  
        // 以下三句是用于设置页面不缓存  
        resp.setHeader("Pragma", "No-cache");  
        resp.setHeader("Cache-Control", "No-cache");  
        resp.setDateHeader("Expires", 0);  
        
        InputStream in = null;
        OutputStream out = null;
		try {
			in = parameterSvc.getImageByte(tableName, colName, where);
			out = resp.getOutputStream();
			int ch = 0;
			if (in != null) {
				while ((ch = in.read()) != -1) {
					out.write(ch);
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			try {
				if (out != null) {
					out.close();
				}
				if (in != null) {
					in.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return null;
	}
	
	/**
	 * 入账信息加载
	 * <功能详细描述>
	 * @param request
	 * @param resp
	 * @param check_id
	 * @param room_id
	 * @param with_id
	 * @param billType
	 * @param billId
	 * @return
	 * @see GuestdocAction.java
	 */
	@RequestMapping("getAccountFor.do")
	@ResponseBody
	public String getAccountFor(HttpServletRequest request,
			HttpServletResponse resp,Integer checkId,String roomId,String withId,Integer billType,String billId){
		Map resultMap = new HashMap();
		List guestList = null;
		List<Hexchange> exchanges = null;
		List list = null;
		try {
			//货币
			exchanges = hexchangeSvcl.getList("status", 0);
			//消费点
			list = hconsumeSvc.getList("status", 0);
			// 房间客户信息
			GuestSearchVO searchVO = new GuestSearchVO();
			if (StringUtils.isNotEmpty(roomId)) {
				searchVO.setRoomId(roomId);
			}
			if (StringUtils.isNotEmpty(withId)) {
				searchVO.setWithId(withId);
			}
			guestList = guestdocSvc.getGuestDocList(searchVO);
			resultMap.put("exchanges", exchanges);
			resultMap.put("guests", guestList);
			resultMap.put("consumes", list);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return JSON.toJSONStringWithDateFormat(resultMap, "yyyy-MM-dd");
	}
	
	/**
	 * <一句话功能简述>
	 * <功能详细描述>
	 * @param request
	 * @param resp
	 * @param checkId
	 * @param roomId
	 * @param withId
	 * @param billType
	 * @param billId
	 * @return
	 * @see GuestdocAction.java
	 */
	@RequestMapping("getLongTimeInfo.do")
	@ResponseBody
	public String getLongTimeInfo(HttpServletRequest request,
			HttpServletResponse resp,Integer checkId,String roomId,String withId,String billId){
		Map resultMap = new HashMap();
		List guestList = null;
		Parameter p = null;
		List<Hexchange> exchanges = null;
		List<Hconsume> list = null;
		try {
			p = parameterSvc.get("para5Name", "日租/半日租代码");
			//货币
			exchanges = hexchangeSvcl.getList("status", 0);
			//消费点
			list = hconsumeSvc.getList("status", 0);
			if(list!=null){
				boolean tag = false;
				for(Hconsume consume:list){
					if(consume.getCodeId().trim().equals(p.getPara5().trim())){
						tag = true;
					}
				}
				if(!tag)resultMap.put("msg","日租/半日租代码设置有误");
			}
			BPaied bp = bPaiedSvc.get(checkId);
			String billType = "1";
			if(bp!=null){
				String cons = bp.getCons();
				if(cons!=null){
					String[] consArr = cons.split(",");
					for(String str:consArr){
						if(str.equals(p.getPara5().trim())){
							billType="2";
							break;
						}
					}
				}
			}
			// 房间客户信息
			GuestSearchVO searchVO = new GuestSearchVO();
			if (StringUtils.isNotEmpty(roomId)) {
				searchVO.setRoomId(roomId);
			}
			if (StringUtils.isNotEmpty(withId)) {
				searchVO.setWithId(withId);
			}
			guestList = guestdocSvc.getGuestDocList(searchVO);
			resultMap.put("exchanges", exchanges);
			resultMap.put("guests", guestList);
			resultMap.put("consumes", list);
			resultMap.put("longCode", p.getPara5());
			resultMap.put("billType", billType);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return JSON.toJSONStringWithDateFormat(resultMap, "yyyy-MM-dd");
	}
	/**
	 * 入账保存
	 * <功能详细描述>
	 * @return
	 * @see GuestdocAction.java
	 */
	@RequestMapping("accountForSaveBill.do")
	@ResponseBody
	public String accountForSaveBill(HttpServletRequest request,
			HttpServletResponse resp,String type,String billids,Bills bill,String checkType,Integer checkId){
		//定义返回json类
		AjaxJson json = new AjaxJson();
		try {
			Operator operator=AppBaseCfg.getOperator();
			String billType = bill.getBillType();
			// 判断当前客户的帐号是否存在
			if ("A".equals(type)) {
				json = guestdocSvc.checkBillId(billType, bill.getBillId()
						.toString(),checkType);
			}
			if (!json.isSuccess()) {
				return JSON.toJSONString(json);
			}
			// 保存账单
			bill.setOperId(operator.getOperId());
			guestdocSvc.saveBill(type, billids, bill,checkId);

			// 结果保存
			json.setMsg("保存成功");
			json.setSuccess(true);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			json.setMsg("保存失败");
			json.setSuccess(false);
			return JSON.toJSONString(json);
		}
		return JSON.toJSONString(json);
	}
	
	/**
	 * 加载付款方式
	 * <功能详细描述>
	 * @param request
	 * @param resp
	 * @return
	 * @see GuestdocAction.java
	 */
	@RequestMapping("loadSettleAndMoneyKind.do")
	@ResponseBody
	public String loadSettle(HttpServletRequest request,
			HttpServletResponse resp){
		List<Hsettle> sList = null;
		List<Hexchange> eList = null;
		Map resultMap = new HashMap();
		Parameter p = null;
		try {
			p = parameterSvc.get(3);
			sList = hsettleSvc.getList("status", 0);
			if(sList!=null){
				boolean tag = false;
				for(Hsettle settle:sList){
					if(settle.getCodeId().trim().equals(p.getPara5().trim())){
						tag = true;
					}
				}
				if(!tag)resultMap.put("msg","押金代码设置有误");
			}
			Hsettle hsettle = hsettleSvc.get("codeId",p.getPara5());
			eList = hexchangeSvcl.getList("status", 0);
			resultMap.put("sList", sList);
			resultMap.put("eList", eList);
			resultMap.put("deposit",p.getPara5());
			resultMap.put("exchange", hsettle.getMoneyId());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return JSON.toJSONString(resultMap);
	}
	
	/**
	 * 根据消费点id判断是A账还是B账
	 * <功能详细描述>
	 * @param request
	 * @param resp
	 * @param consId
	 * @param checkId
	 * @return
	 * @see GuestdocAction.java
	 */
	@RequestMapping("getAorBbyConsumeId.do")
	@ResponseBody
	public String getAorBbyConsumeId(HttpServletRequest request,
			HttpServletResponse resp,String consId,Integer checkId){
		AjaxJson ajaxJson = new AjaxJson();
		BPaied bp = bPaiedSvc.get(checkId);
		ajaxJson.setObj("A");
		if(bp!=null){
			String cons = bp.getCons();
			if(cons!=null){
				String[] consArr = cons.split(",");
				for(String str:consArr){
					if(str.equals(consId)){
						ajaxJson.setObj("B");
						break;
					}
				}
			}
		}
		return JSON.toJSONString(ajaxJson);
	}
	
	@RequestMapping("depositSaveBill.do")
	@ResponseBody
	public String depositSaveBill(HttpServletRequest request,
			HttpServletResponse resp,Bills bill,String type,Integer checkId){
		//定义返回json类
		AjaxJson json = new AjaxJson();
		try {
			String billType = bill.getBillType();
			// 判断当前客户的帐号是否存在
			json = guestdocSvc.checkBillId(billType, bill.getBillId()
					.toString(),type);
			if (!json.isSuccess()) {
				return JSON.toJSONString(json);
			}
			Operator operator=AppBaseCfg.getOperator();
			bill.setOperId(operator.getOperId());
			guestdocSvc.depositSaveBill(bill,checkId);
			// 结果保存
			json.setMsg("保存成功");
			json.setSuccess(true);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			json.setMsg("保存失败");
			json.setSuccess(false);
			return JSON.toJSONString(json);
		}
		return JSON.toJSONString(json);
	}
	
	/**
	 * 获取预授权列表
	 * <功能详细描述>
	 * @param request
	 * @param resp
	 * @param checkId
	 * @return
	 * @see GuestdocAction.java
	 */
	@RequestMapping("getPreAuthorization.do")
	@ResponseBody
	public String getPreAuthorization(HttpServletRequest request,
			HttpServletResponse resp,Integer checkId,String status,Integer billId){
		List list = null;
		try {
			list = guestdocSvc.getPreAuthorization(checkId,status,billId);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return JSON.toJSONStringWithDateFormat(list,"yyyy-MM-dd");
	}
	
	/**
	 * 预授权保存
	 * <功能详细描述>
	 * @param request
	 * @param resp
	 * @param creditAuth
	 * @return
	 * @see GuestdocAction.java
	 */
	@RequestMapping("savePreAuth.do")
	@ResponseBody
	public String savePreAuth(HttpServletRequest request,
			HttpServletResponse resp,GstCreditAuth creditAuth){
		AjaxJson ajaxJson = new AjaxJson();
		try{
			Operator operator=AppBaseCfg.getOperator();
			creditAuth.setOperId(operator.getOperId());
			guestdocSvc.savePreAuth(creditAuth);
			ajaxJson.setSuccess(true);
		}catch (Exception e) {
			// TODO: handle exception
			ajaxJson.setMsg("保存失败");
			ajaxJson.setSuccess(false);
			e.printStackTrace();
		}
		return JSON.toJSONString(ajaxJson);
	}
	
	/**
	 * 完成预授权
	 * <功能详细描述>
	 * @param request
	 * @param resp
	 * @param ids
	 * @return
	 * @see GuestdocAction.java
	 */
	@RequestMapping("finishAuth.do")
	@ResponseBody
	public String finishAuth(HttpServletRequest request,
			HttpServletResponse resp,String id,String balance,String finishNo,Integer billId,String billType){
		AjaxJson ajaxJson = new AjaxJson();
		try {
			Operator operator=AppBaseCfg.getOperator();
			guestdocSvc.finishAuth(id,balance,operator.getOperId(),finishNo,billId,billType);
			ajaxJson.setSuccess(true);
		} catch (Exception e) {
			// TODO: handle exception
			ajaxJson.setMsg("保存失败");
			ajaxJson.setSuccess(false);
			e.printStackTrace();
		}
		return JSON.toJSONString(ajaxJson);
	}
	
	/**
	 * 取消预授权
	 * <功能详细描述>
	 * @param request
	 * @param resp
	 * @param ids
	 * @param authStat
	 * @return
	 * @see GuestdocAction.java
	 */
	@RequestMapping("cancleAuth.do")
	@ResponseBody
	public String cancleAuth(HttpServletRequest request,
			HttpServletResponse resp,String id,String balance,String cancelNo,Integer billId){
		AjaxJson ajaxJson = new AjaxJson();
		try {
			Operator operator=AppBaseCfg.getOperator();
			guestdocSvc.cancleAuth(id, balance, cancelNo, operator.getOperId(), billId);
			ajaxJson.setSuccess(true);
		} catch (Exception e) {
			// TODO: handle exception
			ajaxJson.setMsg("取消失败");
			ajaxJson.setSuccess(false);
			e.printStackTrace();
		}
		return JSON.toJSONString(ajaxJson);
	}
	
	@RequestMapping("updateAuthStatus.do")
	@ResponseBody
	public String updateAuthStatus(HttpServletRequest request,
			HttpServletResponse resp,String id,String balance,Integer billId){
		AjaxJson ajaxJson = new AjaxJson();
		try {
			guestdocSvc.updateAuthStatus(id,balance,billId);
			ajaxJson.setSuccess(true);
		} catch (Exception e) {
			// TODO: handle exception
			ajaxJson.setMsg("取消失败");
			ajaxJson.setSuccess(false);
			e.printStackTrace();
		}
		return JSON.toJSONString(ajaxJson);
	}
	
	@RequestMapping("checkAuth.do")
	@ResponseBody
	public String checkAuth(HttpServletRequest request,
			HttpServletResponse resp,Integer checkId){
		AjaxJson ajaxJson = null; 
		try {
			ajaxJson = guestdocSvc.checkAuth(checkId);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return JSON.toJSONString(ajaxJson);
	} 
	/**
	 * 结账
	 * <功能详细描述>
	 * @param request
	 * @param resp
	 * @return
	 * @see GuestdocAction.java
	 */
	@RequestMapping("checkOutAccount.do")
	@ResponseBody
	public String checkOutAccount(HttpServletRequest request,
			HttpServletResponse resp,Integer checkId,String ids,String bills,String oddMoney,String billId,String billType,String roomId,String tipMoney){
		AjaxJson ajaxJson = new AjaxJson(); 
		try {
			Operator operator=AppBaseCfg.getOperator();
			ajaxJson = guestdocSvc.checkOutAccount(checkId,ids,bills,operator.getOperId(),oddMoney,billId,billType,roomId,tipMoney);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return JSON.toJSONString(ajaxJson);
	}
	
	/**
	 * 离店
	 * <功能详细描述>
	 * @param request
	 * @param resp
	 * @param checkId
	 * @param billIds
	 * @param bills
	 * @return
	 * @see GuestdocAction.java
	 */
	@RequestMapping("leaveRoom.do")
	@ResponseBody
	public String leaveRoom(HttpServletRequest request,
			HttpServletResponse resp,String checkIds){
		AjaxJson ajaxJson = new AjaxJson();
		try {
			guestdocSvc.leaveRoom(checkIds);
		} catch (Exception e) {
			// TODO: handle exception
			ajaxJson.setSuccess(false);
			ajaxJson.setMsg("操作失败");
			e.printStackTrace();
		}
		return JSON.toJSONString(ajaxJson);
	}
	
	/**
	 * <一句话功能简述>
	 * <功能详细描述>
	 * @param request
	 * @param resp
	 * @param checkId
	 * @return
	 * @see GuestdocAction.java
	 */
	@RequestMapping("getGroupAccountInfo.do")
	@ResponseBody
	public String getGroupAccountInfo(HttpServletRequest request,
			HttpServletResponse resp,Integer checkId){
		GrpDoc grpDoc = null;
		List<Bills> bills = null;
		Map map = new HashMap();
		try {
			grpDoc = grpDocSvc.get(checkId);
			bills = guestdocSvc.getGroupBills(grpDoc.getBillId());
			map.put("detail", grpDoc);
			map.put("bills", bills);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return JSON.toJSONStringWithDateFormat(map, "yyyy-MM-dd");
	}
	
	/**
	 * <一句话功能简述>
	 * <功能详细描述>
	 * @param request
	 * @param resp
	 * @param checkId
	 * @return
	 * @see GuestdocAction.java
	 */
	@RequestMapping("getNoguestAccountInfo.do")
	@ResponseBody
	public String getFguestAccountInfo(HttpServletRequest request,
			HttpServletResponse resp,Integer id,String showType,String okFlag,String startDate,String endDate,String isInvalid){
		List<Bills> bills = null;
		Map map = new HashMap();
		Map noguest = null;
		try {
			//noguest = NoguestSvc.get(id);
			noguest=  NoguestSvc.getNoguestInfo(id);
			bills = guestdocSvc.getNoguestBills(Integer.valueOf(noguest.get("billId").toString()), showType, okFlag, startDate, endDate, isInvalid);
			map.put("detail", noguest);
			map.put("bills", bills);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return JSON.toJSONStringWithDateFormat(map, "yyyy-MM-dd");
	}
	
	/**
	 * 转账确定
	 * <功能详细描述>
	 * @param request
	 * @param resp
	 * @param topBillId
	 * @param bottomBillId
	 * @param transferData
	 * @return
	 * @see GuestdocAction.java
	 */
	@RequestMapping("transferConfirm.do")
	@ResponseBody
	public String transferConfirm(HttpServletRequest request,
			HttpServletResponse resp,String topBillId,String bottomBillId,String transferData,Integer checkId){
		AjaxJson ajaxJson = new AjaxJson();
		try {
			Operator operator=AppBaseCfg.getOperator();
			guestdocSvc.transferConfirm(topBillId, bottomBillId, transferData,checkId,operator.getOperId());
		} catch (Exception e) {
			// TODO: handle exception
			ajaxJson.setSuccess(false);
			ajaxJson.setMsg("操作失败");
			e.printStackTrace();
		}
		return JSON.toJSONString(ajaxJson);
	}
	
	/**
	 * 退房
	 * <功能详细描述>
	 * @param request
	 * @param resp
	 * @param checkId
	 * @param roomId
	 * @return
	 * @see GuestdocAction.java
	 */
	@RequestMapping("checkOutRoom.do")
	@ResponseBody
	public String checkOutRoom(HttpServletRequest request,
			HttpServletResponse resp,String checkIds,String roomIds){
		AjaxJson ajaxJson = new AjaxJson();
		try {
			Operator operator=AppBaseCfg.getOperator();
			guestdocSvc.checkOutRoom(roomIds,checkIds,operator.getOperId());
		} catch (Exception e) {
			// TODO: handle exception
			ajaxJson.setSuccess(false);
			ajaxJson.setMsg("操作失败");
			e.printStackTrace();
		}
		return JSON.toJSONString(ajaxJson);
	}
	
	/**
	 * 退房
	 * <功能详细描述>
	 * @param request
	 * @param resp
	 * @param checkId
	 * @param roomId
	 * @return
	 * @see GuestdocAction.java
	 */
	@RequestMapping("checkBillsInleaveHotel.do")
	@ResponseBody
	public String checkBillsInleaveHotel(HttpServletRequest request,
			HttpServletResponse resp,String checkIds){
		AjaxJson ajaxJson = new AjaxJson();
		try {
			ajaxJson = guestdocSvc.checkBillsInleaveHotel(checkIds);
		} catch (Exception e) {
			// TODO: handle exception
			ajaxJson.setSuccess(false);
			ajaxJson.setMsg("操作失败");
			e.printStackTrace();
		}
		return JSON.toJSONString(ajaxJson);
	}
	
	/**
	 * 离店确定
	 * <功能详细描述>
	 * @param request
	 * @param resp
	 * @param checkIds
	 * @param roomIds
	 * @return
	 * @see GuestdocAction.java
	 */
	@RequestMapping("leaveHotel.do")
	@ResponseBody
	public String leaveHotel(HttpServletRequest request,
			HttpServletResponse resp,String checkIds,String roomIds){
		AjaxJson ajaxJson = new AjaxJson();
		try {
			guestdocSvc.leaveHotel(checkIds, roomIds);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			ajaxJson.setSuccess(false);
			ajaxJson.setMsg("操作失败");
		}
		return JSON.toJSONString(ajaxJson);
	}
	
	/**
	 * 退房离店检查账目是否结清
	 * <功能详细描述>
	 * @param request
	 * @param resp
	 * @param roomIds
	 * @return
	 * @see GuestdocAction.java
	 */
	@RequestMapping("checkBills.do")
	@ResponseBody
	public String checkBills(HttpServletRequest request,
			HttpServletResponse resp,String roomIds){
		AjaxJson ajaxJson = new AjaxJson();
		try {
			ajaxJson = guestdocSvc.checkBills(roomIds);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			ajaxJson.setSuccess(false);
		}
		return JSON.toJSONString(ajaxJson);
	}
	
	/**
	 * 账目取消
	 * <功能详细描述>
	 * @param request
	 * @param resp
	 * @param billIds
	 * @return
	 * @see GuestdocAction.java
	 */
	@RequestMapping("cancleBill.do")
	@ResponseBody
	public String cancleBill(HttpServletRequest request,
			HttpServletResponse resp,Integer id,String payNum,Integer billId,String okFlag,Integer checkId){
		AjaxJson ajaxJson = new AjaxJson();
		Operator operator=AppBaseCfg.getOperator();
		try {
			ajaxJson = guestdocSvc.cancleBill(id, payNum, billId,operator.getOperId(),okFlag,checkId);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			ajaxJson.setMsg("操作失败");
			ajaxJson.setSuccess(false);
		}
		return JSON.toJSONString(ajaxJson);
	}
	
	@RequestMapping("getGuestDocDetailIn.do")
	@ResponseBody
	public String getGuestDocDetailIn(HttpServletRequest request,
			HttpServletResponse response,String check_id,String with_id,String roomIds){
		Map resultMap = new HashMap();
		Map map = null;
		List roomList = null;
		List guestList = null;
		try {
			Operator operator=AppBaseCfg.getOperator();
			JSONArray array = JSONObject.parseArray(roomIds);
			String roomId = null;
			if (array != null && array.size() > 0) {
				JSONObject jsonObject = JSONObject.parseObject(array
						.getString(0));
				JSONArray array2 = JSONObject.parseArray(jsonObject
						.getString("roomIds"));
				if (array2 != null && array2.size() > 0) {
					JSONObject jsonObject2 = JSONObject.parseObject(array2
							.getString(0));
					roomId = jsonObject2.getString("roomId");
				}
			}
			String newCheckId = guestdocSvc.getGuestDocDetailIn(Integer.valueOf(check_id),array,operator.getOperId(),with_id);
			//住客个人资料
			if(StringUtils.isNotEmpty(check_id)){
				map = guestdocSvc.getGuestDocDetail(newCheckId);
			}
			//住客详情中房间列表
			if(StringUtils.isNotEmpty(with_id)){
				roomList = guestdocSvc.getRoomsList(with_id);
			}
			//房间客户信息
			GuestSearchVO searchVO = new GuestSearchVO();
			if(StringUtils.isNotEmpty(roomId)){
				searchVO.setRoomId(roomId);
			}
			if(StringUtils.isNotEmpty(with_id)){
				searchVO.setWithId(with_id);
			}
			guestList = guestdocSvc.getGuestDocList(searchVO);
			resultMap.put("checkId",newCheckId);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		//最终结果
		resultMap.put("detail",map);
		resultMap.put("rooms", roomList);
		resultMap.put("guests", guestList);
		return JSON.toJSONStringWithDateFormat(resultMap,"yyyy-MM-dd");
	}
	
	@RequestMapping("checkOut.do")
	@ResponseBody
	public String checkOut(HttpServletRequest request,
			HttpServletResponse response,String roomIds,String reachDate,String leaveDate){
		Map map = null;
		List roomList = null;
		List guestList = null;
		String result = null;
		try {
			Operator operator=AppBaseCfg.getOperator();
			map = guestdocSvc.checkOut(roomIds,reachDate,leaveDate,operator.getOperId());
//			String checkId = info.get("checkId").toString();
//			String withId = info.get("withId").toString();
//			String roomId = info.get("roomId").toString();
//			//住客个人资料
//			if(StringUtils.isNotEmpty(checkId)){
//				map = guestdocSvc.getGuestDocDetail(checkId);
//			}
////			//住客详情中房间列表
//			if(StringUtils.isNotEmpty(withId)){
//				roomList = guestdocSvc.getRoomsList(withId);
//			}
////			//房间客户信息
//			GuestSearchVO searchVO = new GuestSearchVO();
//			if(StringUtils.isNotEmpty(roomId)){
//				searchVO.setRoomId(roomId);
//			}
//			if(StringUtils.isNotEmpty(withId)){
//				searchVO.setWithId(withId);
//			}
//			guestList = guestdocSvc.getGuestDocList(searchVO);
//			resultMap.put("checkId",checkId);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		//最终结果
//		resultMap.put("detail",map);
//		resultMap.put("rooms", roomList);
//		resultMap.put("guests", guestList);
		return JSON.toJSONString(map);
	}
	
	/**
	 * 获取本位币代码
	 * <功能详细描述>
	 * @param request
	 * @param resp
	 * @return
	 * @see GuestdocAction.java
	 */
	@RequestMapping("getMoneyId.do")
	@ResponseBody
	public String getMoneyId(HttpServletRequest request,
			HttpServletResponse resp){
		Parameter p = null;
		try {
			p = parameterSvc.get(2);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return p.getPara5();
	}
	
	/**
	 * 获取本位币代码
	 * <功能详细描述>
	 * @param request
	 * @param resp
	 * @return
	 * @see GuestdocAction.java
	 */
	@RequestMapping("getGstOri.do")
	@ResponseBody
	public String getGstOriByType(HttpServletRequest request,
			HttpServletResponse resp,Integer checkType){
		List<HgstOriType> oriTypes = null;
		List hgstOris = null;
		String[] props = {"status","checkType"};
		Integer[] vals = new Integer[2];
		vals[0] = 0;
		vals[1] = checkType;
		StringBuilder sb = new StringBuilder();
		try {
			oriTypes = hgstOriTypeSvc.getList(props, vals);
			if(oriTypes!=null && oriTypes.size()>0){
				for(HgstOriType oriType:oriTypes ){
					sb.append("'"+oriType.getCodeId().trim()+"',");
				}
			}
			hgstOris = guestdocSvc.getGstOriByType(sb.toString().substring(0,sb.toString().length()-1));
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return JSON.toJSONString(hgstOris);
	}
	
	/**
	 * 获取本位币代码
	 * <功能详细描述>
	 * @param request
	 * @param resp
	 * @return
	 * @see GuestdocAction.java
	 */
	@RequestMapping("getGstOriByOricode.do")
	@ResponseBody
	public String getGstOriByOricode(HttpServletRequest request,
			HttpServletResponse resp,String codeId){
		Map map = new HashMap();
		List<HgstOri> hgstOris = null;
		HgstOri ori = null;
		String[] props = {"status","oriKind"};
		Object[] vals = new Object[2];
		vals[0] = 0;
		HgstOriType hgstOriType = null;
		try {
			ori = hgstOriSvc.get("codeId", codeId);
			if(ori!=null){
				vals[1] = ori.getOriKind();
				hgstOriType = hgstOriTypeSvc.get("codeId", ori.getOriKind());
				hgstOris = hgstOriSvc.getList(props, vals);
			}
			map.put("hgstOris", hgstOris);
			map.put("inType", hgstOriType==null ? 0 : hgstOriType.getCheckType());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return JSON.toJSONString(map);
	}
	
	@RequestMapping("getAllConsumes.do")
	@ResponseBody
	public String getAllConsumes(HttpServletRequest request,
			HttpServletResponse resp){
		List<Hconsume> list = null;
		try {
			list = hconsumeSvc.getList("status", 0);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return JSON.toJSONString(list);
	}
	
	@RequestMapping("getGuestPage.do")
	public String getGuestPage(HttpServletRequest request,
			HttpServletResponse resp,String type){
		if("1".equals(type)){
			return "rooms/bookCheckIn/guestdetail";
		}else{
			return "rooms/bookCheckIn/groupdetail";
		}
	}
	/**
	 * 客单详情，房价列表
	 * @param model
	 * @param checkId 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/findRoomPriceListBy.do")
	@ResponseBody
	public String findRoomPriceListBy(ModelMap model,Integer checkId,Integer withId) throws Exception{
		JSONObject jsonObj = new JSONObject() ;
		
		List<?> roomPriceList = guestdocSvc.findRoomPriceListBy(checkId,withId);
		jsonObj.put("roomPriceList", roomPriceList) ;
		jsonObj.put("hotelDate", DateFormatUtils.format(parameterSvc.GetHotelDate(), "yyyy-MM-dd")) ;
		return jsonObj.toJSONString();
	}
	
	/**
	 * 客单详情，房价列表
	 * @param model
	 * @param checkId 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/getInvalidBIlls.do")
	@ResponseBody
	public String getInvalidBIlls(Integer billType,String billId,String showType,String consId,String startDate,String endDate) throws Exception{
		JSONObject jsonObj = new JSONObject() ;
		
		return jsonObj.toJSONString();
	}
	/**
	 * 获取酒店时间
	 * @return
	 */
	@RequestMapping("/getHotelDate.do")
	@ResponseBody
	public AjaxJson getHotelDate(){
		logger.debug("/getHotelDate.do start");
		AjaxJson aj = new AjaxJson();
		try{
			Parameter param = parameterSvc.get(1);
			Date hotleDateDB = param.getPara3();
			String hotelDateStr = DateUtil.convertDate2String(hotleDateDB, DateUtil.defaultDatePattern);
			Map<String,Object> attributeMap = new HashMap<String,Object>();
			attributeMap.put("hotleDate", hotleDateDB);
			attributeMap.put("hotelDateStr", hotelDateStr);
			aj.setAttributes(attributeMap);
			//
			aj.setSuccess(true);
		}catch(Exception e){
			logger.error("获取酒店时间发生错误,",e);
			aj.setSuccess(false);
			aj.setMsg("获取酒店时间发生错误");
		}
		logger.debug("/getHotelDate.do end");
		return aj;
	}
	
}
