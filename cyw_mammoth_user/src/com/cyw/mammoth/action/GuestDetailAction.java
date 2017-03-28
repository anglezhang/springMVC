package com.cyw.mammoth.action;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.map.LinkedMap;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.cyw.mammoth.auth.AppBaseCfg;
import com.cyw.mammoth.bean.BPaied;
import com.cyw.mammoth.bean.GstBill;
import com.cyw.mammoth.bean.Guestdoc;
import com.cyw.mammoth.bean.Hcodes;
import com.cyw.mammoth.bean.Hconsume;
import com.cyw.mammoth.bean.Hcountry;
import com.cyw.mammoth.bean.Hexchange;
import com.cyw.mammoth.bean.Hfolk;
import com.cyw.mammoth.bean.HroomPlan;
import com.cyw.mammoth.bean.HroomType;
import com.cyw.mammoth.bean.Hsettle;
import com.cyw.mammoth.bean.Operator;
import com.cyw.mammoth.bean.Parameter;
import com.cyw.mammoth.bean.Rooms;
import com.cyw.mammoth.bean.TaDoc;
import com.cyw.mammoth.service.BPaiedSvc;
import com.cyw.mammoth.service.GrpDocSvc;
import com.cyw.mammoth.service.GstBillSvc;
import com.cyw.mammoth.service.GuestdocSvc;
import com.cyw.mammoth.service.HcodesSvc;
import com.cyw.mammoth.service.HconsumeSvc;
import com.cyw.mammoth.service.HcountrySvc;
import com.cyw.mammoth.service.HexchangeSvc;
import com.cyw.mammoth.service.HfolkSvc;
import com.cyw.mammoth.service.HroomDefineSvc;
import com.cyw.mammoth.service.HroomPlanSvc;
import com.cyw.mammoth.service.HroomTypeSvc;
import com.cyw.mammoth.service.HsettleSvc;
import com.cyw.mammoth.service.ParameterSvc;
import com.cyw.mammoth.service.RoomsSvc;
import com.cyw.mammoth.service.TADocSvc;
import com.cyw.mammoth.vo.GuestDetailVo;
import com.cyw.mammoth.vo.GuestSearchVO;
import com.cyw.mammoth.vo.GuestSourceVO;

/**
 * @Comments:  住客详情action
 * @author zhangzhenxing(zhangzhenxing@cyw.so)
 * @date 上午11:26:23
 * @version 1.0
 */
@RequestMapping(value = "/guestdetail")
@Controller
public class GuestDetailAction  extends BaseAction{
	
	/**
	 * @描述 客户svc
	 * */
	@Autowired
	private GuestdocSvc guestdocSvc;
	
	/**
	 * @描述 代码服务类
	 * */
	@Autowired
	private HcodesSvc hcodesSvc;
	
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
	 * @描述 支付方式代码
	 * */
	@Autowired
	private HsettleSvc hsettleSvc;
	
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
	 * 消费点代码
	 */
	@Autowired
	private HconsumeSvc hconsumeSvc;
	
	/**
	 * @描述 公共参数
	 * */
	@Autowired
	private ParameterSvc parameterSvc;
	
	/**
	 * @描述 客户账务bill
	 * */
	@Autowired
	private GrpDocSvc grpDocSvc;
	
	/**
	 * @描述 分账设置
	 * */
	@Autowired
	private BPaiedSvc bPaiedSvc;
	
	/**
	 * @描述 房间服务
	 * */
	@Autowired
	private RoomsSvc roomSvc;
	
	/**
	 * @描述 房间类型
	 * */
	@Autowired
	private HroomTypeSvc hroomTypeSvc;	
	/**
	 * @描述 房价方案
	 * */
	@Autowired
	private HroomPlanSvc hroomPlanSvc;	
	/**
	 * @描述 合约单位
	 * */
	@Autowired
	private TADocSvc taDocSvc ;
	
	@RequestMapping(value="/getGuestdetail.do")
	public String getGuestDetail(Model model,@RequestParam("roomIds")String roomIds
			,@RequestParam("reachDate")String reachDate,@RequestParam("leaveDate")String leaveDate){
		//办理入住
		Operator operator=AppBaseCfg.getOperator();
		Map map = guestdocSvc.checkOut(roomIds, reachDate, leaveDate, operator.getOperId());
		guestdocSvc.saveCheckIn(model,roomIds, map);
		model.addAttribute("scanidInf", map.get("info").toString().trim());
		setGuestSelectInfo(model,roomIds);
		String firstRoomId = "" ;
		if(roomIds.contains(",")){
			String[] rooms = roomIds.split(",");
			firstRoomId = rooms[0] ;
			model.addAttribute("roomIds", rooms);
			model.addAttribute("checkRoom", rooms[0]);
		}else{
			firstRoomId = roomIds ;
			model.addAttribute("roomIds", roomIds);
			model.addAttribute("checkRoom", roomIds);
		}
		//登记入住 0 住客详情 1
		model.addAttribute("pageType", 0);
		
		
		/***销售员和房假方案***/
		// 销售员
		model.addAttribute("saler", "");
		// 默认入住类型为 普通（1）
		model.addAttribute("guestdetail_roomplanType", 1);
		model.addAttribute("guestdetail_prcSchemeId", hroomPlanSvc.findAvilListBy(0, null, 1,firstRoomId,null));
		/***销售员和房假方案***/
		return "guestdetail/guestdetail";
	}
	
	/**
	 *@描述 设置界面下拉框信息
	 * */
	private void setGuestSelectInfo(Model model,String roomId) {
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
			List roomBills = guestdocSvc.getRoomBillInfo(roomId);
			String depositCode = parameterSvc.get(3).getPara5();//押金代码
			String moneyId = parameterSvc.get(2).getPara5();//本位币代码
			String moneyPayId = parameterSvc.get(1).getPara5();//本位币付款方式代码
			String forALongCode = parameterSvc.get(4).getPara5();//半日组代码
			Double chargeAccCode = parameterSvc.get(1).getPara4();//记账精度
			Double settleAccCode = parameterSvc.get(2).getPara4();//结账精度
			String refundCode = parameterSvc.get(6).getPara5();
			List<Hconsume> consumes = hconsumeSvc.getList("status", 0);
			//是否去身份证  房卡
			Parameter paramter = parameterSvc.get(7);//1 true 0 false
			Boolean scanCard = paramter.getPara1Flag();
			Boolean hairRoomCard = paramter.getPara2Flag();
			model.addAttribute("scanCard", scanCard);
			model.addAttribute("hairRoomCard", hairRoomCard);
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
			model.addAttribute("roomBills", roomBills);
			model.addAttribute("refundCode", refundCode);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error("初始化列表报错");
		}
	}
	
	/**
	 * @throws Exception 
	 * @描述 ：在住房的客户信息
	 * */
	@RequestMapping(value="/getLodgerGuestInfo.do")
	public String getRoomGuestInfo(Model model,String roomIds){
		//setGuestSelectInfo(model,roomIds);
		//根据room查询当前房间主客户信息的guestDoc 根据roomId 主人 
		String[] perties = {"roomId","chkExt","chkStat"};
		String[] values = {roomIds,"1","I"};
		Guestdoc entity;
		try {
			entity = guestdocSvc.get(perties, values);
			Map<String, String> map = new HashMap<String, String>();
			map.put("withId", entity.getWithId().toString());
			String info = entity.getCheckId() + "," + roomIds;
			map.put("info", info);
			//下拉框设置
			setGuestSelectInfo(model,roomIds);
			//查询住客相关信息
			guestdocSvc.saveCheckIn(model, roomIds, map);
			//查询该房间的联房或者团房的其他信息
			String[] roomsID = guestdocSvc.getRoomIds(entity);
			model.addAttribute("roomIds", roomsID);
			model.addAttribute("checkRoom", roomIds);
			model.addAttribute("pageType", 1);
			/***销售员和房假方案***/
			if(StringUtils.isNotBlank(entity.getCompId())){
				TaDoc tadoc= taDocSvc.get(Integer.parseInt(entity.getCompId().trim()));
				if(null!=tadoc){
					// 销售员
					Hcodes hc = hcodesSvc.get("codeId", tadoc.getSalemanId()) ;
					model.addAttribute("saler", hc.getCodeNamec());
				}
			}
			if(StringUtils.isNotBlank(entity.getPrcSchemeId())){
				HroomPlan hrp = hroomPlanSvc.get("codeId", entity.getPrcSchemeId()) ;
				model.addAttribute("guestdetail_rateCode", entity.getPrcSchemeId());
				model.addAttribute("guestdetail_roomplanType", hrp.getRmplanType());
				model.addAttribute("guestdetail_prcSchemeId", hroomPlanSvc.findAvilListBy(0, null, hrp.getRmplanType(),entity.getRoomId() , entity.getPrcSchemeId()));
			}else{
				// 默认入住类型为 普通（1）
				model.addAttribute("guestdetail_roomplanType", 1);
				model.addAttribute("guestdetail_prcSchemeId", hroomPlanSvc.findAvilListBy(0, null, 1,entity.getRoomId(), null));
			}
			// 是否启用房价方案
			model.addAttribute("isRoomPlan", entity.getIsRoomPlan());
			/***销售员和房假方案***/
			return "guestdetail/guestdetail";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error("roomIds=" + roomIds + "数据存在问题");
		}
		return null;
	}
	
	/**
	 * @描述 更新客户信息
	 * */
	@RequestMapping(value="/updateGuest.do")
	@ResponseBody
	public JSONObject updateGuest(Guestdoc guestdoc,Double Alimit,Double Blimit){
		JSONObject result = new JSONObject();
		try{
			guestdocSvc.updateGuestdoc(guestdoc,Alimit,Blimit);
			Guestdoc entity = guestdocSvc.get(guestdoc.getCheckId());
			GuestSearchVO searchVO = new GuestSearchVO();
			searchVO.setRoomId(entity.getRoomId());
			searchVO.setWithId(entity.getWithId().toString());
			//数据列表
			List guestList = guestdocSvc.getGuestDocList(searchVO);
			GuestDetailVo guestDetalVo = guestdocSvc.getGuestDetail(entity.getCheckId());
			result.put("guestDoc", JSON.toJSON(guestDetalVo));
			result.put("guestList", JSON.toJSON(guestList));
			result.put("msg", "修改成功");
			result.put("success", true);
		}catch (Exception e) {
			e.printStackTrace();
			result.put("msg", "修改失败");
			result.put("success", false);
		}
		return result;
	} 
	
	@RequestMapping(value="/saveGuest.do")
	@ResponseBody
	public JSONObject saveNewGuestInfo(Guestdoc guestdoc,Double Alimit,Double Blimit){
		JSONObject result = new JSONObject();
		try{
			String operId = AppBaseCfg.getOperator().getOperId();
			guestdoc.setLimit(0.0);
			guestdoc.setChkExt("0");
			guestdoc.setRoomPrice(0.0);
			guestdoc.setChkOperid(operId);
			//根据主键取实体
			Integer checkid = guestdocSvc.saveGuest(guestdoc,Alimit,Blimit);
			Guestdoc entity = guestdocSvc.get(checkid);
			GuestSearchVO searchVO = new GuestSearchVO();
			searchVO.setRoomId(entity.getRoomId());
			searchVO.setWithId(entity.getWithId().toString());
			//数据列表
			GuestDetailVo guestDetalVo = guestdocSvc.getGuestDetail(entity.getCheckId());
			List guestList = guestdocSvc.getGuestDocList(searchVO);
			result.put("guestDoc", JSON.toJSON(guestDetalVo));
			result.put("guestList", JSON.toJSON(guestList));
			result.put("msg", "新增成功");
			result.put("success", true);
		}catch(Exception e){
			e.printStackTrace();
			result.put("msg", "新增成功");
			result.put("success", false);
		}
		return result;
	}
	
	/**
	 * @描述 获取该房间用户信息
	 * */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value="/getRoomGuestInfo.do")
	@ResponseBody
	public JSONObject getRoomGuestInfo(String roomId){
		JSONObject resutl = new JSONObject();
		String[] perties = {"roomId","chkStat","chkExt"};
		String[] values = {roomId,"I","1"};
		Guestdoc entity = guestdocSvc.get(perties,values);
		//获取 房间信息列表
		GuestSearchVO searchVO = new GuestSearchVO();
		searchVO.setRoomId(roomId);
		searchVO.setWithId(entity.getWithId().toString());//同一房间
		GuestDetailVo guestDetalVo = guestdocSvc.getGuestDetail(entity.getCheckId());
		List guestList = guestdocSvc.getGuestDocList(searchVO);
		resutl.put("guestList", JSON.toJSON(guestList));
		resutl.put("guestDoc", JSON.toJSON(guestDetalVo));
		return resutl;
	}
	
	/**
	 * @描述 打开房价列表
	 * @param checkId guestdoc 主键
	 * @param withId 联房id
	 * */
	@RequestMapping(value="/getPriceList.do")
	public String getPriceList(Model model,Integer withId,Integer checkId,String roomId){
		//查询房间信息列表
		List<?> roomPriceList = guestdocSvc.findRoomPriceListBy(checkId,withId);
		model.addAttribute("roomPriceList", roomPriceList);
		model.addAttribute("hotelDate",  DateFormatUtils.format(parameterSvc.GetHotelDate(), "yyyy-MM-dd"));
		model.addAttribute("checkId", checkId);
		//房屋类型和房价
		Guestdoc entity = guestdocSvc.get(checkId);
		model.addAttribute("roomId", entity.getRoomId());
		Rooms room = roomSvc.get("roomId", entity.getRoomId());
		String roomType = room.getRoomType();
		HroomType roomsType = hroomTypeSvc.get("codeId", roomType);
		String typeName = roomsType.getCodeNamec();
		model.addAttribute("typeName", typeName);
		DecimalFormat df = new DecimalFormat("0.00");
		model.addAttribute("roomPrice", df.format(roomsType.getPrice()));
		return "guestdetail/priceList";
	}
	
	/**
	 * @描述 获取客户信息
	 * */
	@RequestMapping(value="/getGuestDocDetal.do")
	@ResponseBody
	public String getGuestDocDetal(Integer checkId,String startDate
			,String endDate,Integer billType,String showType
			,String okFlag,String isInvalid){
		JSONObject resutl = new JSONObject();
		//获取客户信息
		if(checkId==null){
			resutl.put("msg", "03");
			return JSON.toJSONString(resutl);
		}
		Guestdoc guestDoc = guestdocSvc.get(checkId);
		if(guestDoc!=null){
			resutl.put("msg", "01");
			resutl.put("guestDoc", JSON.toJSON(guestDoc));
			String payId = guestDoc.getPayId();
			Hsettle hset = hsettleSvc.get("codeId", payId);
			//A账 B账
			Integer aBillId = guestDoc.getBillaId();
			Integer bBiilId = guestDoc.getBillbId();
			GstBill aBill = gstbillSvc.get(aBillId);
			GstBill bBill = gstbillSvc.get(bBiilId);
			List girdData = new ArrayList();;//flexGrid列表数据
			if(hset!=null){
				guestDoc.setPayId(hset.getCodeNamec());
				resutl.put("payType", hset.getCodeNamec());
			}
			if(aBill!=null){
				resutl.put("aBill", JSON.toJSON(aBill));
				resutl.put("bBill", JSON.toJSON(bBill));
			}
			//账务列表信息查询
			if("1".equals(billType.toString())){
				girdData = guestdocSvc.getBills(billType, aBillId.toString()
						, showType, null, okFlag, startDate, endDate,isInvalid);
			}else{
				girdData = guestdocSvc.getBills(billType, bBiilId.toString()
						, showType, null, okFlag, startDate, endDate,isInvalid);
			}
			resutl.put("girdData", girdData);
		}else{
			resutl.put("msg", "02");
		}
		return JSON.toJSONStringWithDateFormat(resutl,"yyyy-MM-dd");
	}
	
	/**
	 * 打开客户其他信息界面(外宾)
	 * */
	@RequestMapping(value="/getOtherGuestdocInfo.do")
	public String getOtherGuestdocInfo(Model model,Guestdoc guestdoc){
		try {
			List<Hcodes> visaTypes = hcodesSvc.getListByCodes("025");//签证类型
			List<Hcodes> visaDepartment = hcodesSvc.getListByCodes("010");//签证机关
			List<Hcountry> countrylist = hcountrySvc.findListBy(0);//国家查询
			List<Hcodes> genderlist = hcodesSvc.getListByCodes("026");//性别
			List<Hcodes> crdlist = hcodesSvc.getListByCodes("006");//证件类型
			List<Hcodes> partOfEntry = hcodesSvc.getListByCodes("012");//入境口岸
			Guestdoc entity = guestdocSvc.get(guestdoc.getCheckId());
			model.addAttribute("visaTypes", visaTypes);
			model.addAttribute("visaDepartment", visaDepartment);
			model.addAttribute("countrylist", countrylist);
			model.addAttribute("genderlist", genderlist);
			model.addAttribute("crdlist", crdlist);
			model.addAttribute("partOfEntry", partOfEntry);
			model.addAttribute("guestdoc", guestdoc);
			model.addAttribute("entity", entity);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error("国家码查询出错");
		}
		return "guestdetail/otherGuestInfo";
	}
	
	/**
	 * @描述 分账设置
	 * */
	@RequestMapping(value="/getSubBill.do")
	public String getSubBill(Model model,Integer checkId){
		List list = null;
		List blist = new ArrayList();
		BPaied bp = null;
		try {
			Guestdoc guestDoc = guestdocSvc.get(checkId);
			model.addAttribute("grpdoc",guestDoc);
			bp = bPaiedSvc.get(checkId);
			if (bp != null) {
				list = hconsumeSvc.getConsumeList(bp.getCons());
				String[] cons = bp.getCons().split(",");
				for (int i = 0; i < cons.length; i++) {
					Hconsume consume = hconsumeSvc.get("codeId", cons[i]);
					blist.add(consume);
				}
			}else{
				list = hconsumeSvc.getList("status", 0);
				bp = new BPaied();
				bp.setCheckId(checkId);
			}
		model.addAttribute("list", list);//A账务
		model.addAttribute("blist", blist);//B账务
		model.addAttribute("bp", bp);//客户账务信息
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "guestdetail/guestBillSet";
	}
	
	@RequestMapping(value="/saveBpaiedInfo.do")
	@ResponseBody
	public JSONObject saveBpaiedInfo(BPaied bpaied,String ifBate){
		JSONObject result = new JSONObject();
		
		try {
			String operId = AppBaseCfg.getOperator().getOperId();
			bpaied.setOperId(operId);
			guestdocSvc.setSplit(bpaied, ifBate);
			result.put("msg", "ok");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			result.put("msg", "error");
		}
		return result;
	}
	
	/**
	 * @描述 获取住客信息
	 * */
	@RequestMapping(value="/getGuestdocInfo.do")
	public String getGuestdocInfo(Model model,Integer checkId){
		Guestdoc guestDoc = guestdocSvc.get(checkId);
		String[] perties = {"roomId","chkExt","chkStat"};
		String roomId = guestDoc.getRoomId();
		Map<String, String> map = new HashMap<String, String>();
		map.put("withId", guestDoc.getWithId().toString());
		String info = guestDoc.getCheckId() + "," + roomId;
		map.put("info", info);
		//下拉框设置
		setGuestSelectInfo(model,roomId);
		//查询住客相关信息
		guestdocSvc.saveCheckIn(model, roomId, map);
		//查询该房间的联房或者团房的其他信息
		String[] roomsID = guestdocSvc.getRoomIds(guestDoc);
		model.addAttribute("roomIds", roomsID);
		model.addAttribute("checkRoom", roomsID);
		model.addAttribute("pageType", 2);
		model.addAttribute("chkStat", guestDoc.getChkStat());
		
		return "guestdetail/guestdetail";
	}
	
	/**
	 * @描述 预定入住
	 * @param roomId 房间ID roomID|isSave|bookid ... 
	 * @param withId 同来人标识
	 * @param bookIdArr 预定标记
	 * */
	@RequestMapping(value="/reserveCheckIn.do")
	public String reserveCheckIn(Model model,String roomId,Integer withId,Integer checkId,Integer bookId,String openType){
		String rid = "";
		if("fit".equals(openType)){
			rid = guestdocSvc.reserveCheckIn(model,roomId,withId,checkId,bookId);
			//根据房间号获取用户信息
			
		}else if("group".equals(openType)){
			rid = guestdocSvc.grpCheckIn(model, roomId, withId, checkId, bookId);
			//根据房间号获取用户信息
		}
		String mainRoomIDS = roomId.substring(0, roomId.indexOf("|"));
		String[] properties = {"roomId","chkExt","chkStat"};
		Object[] values = {mainRoomIDS,"1","I"};
		Guestdoc entity = guestdocSvc.get(properties, values);
		if(entity!=null){
			GuestDetailVo guestDetail = guestdocSvc.getGuestDetail(entity.getCheckId());
			model.addAttribute("detail", guestDetail);
			model.addAttribute("guestdetail",JSON.toJSON(guestDetail));
			// 默认入住类型为 普通（1）
			model.addAttribute("guestdetail_roomplanType", 1);
			model.addAttribute("guestdetail_prcSchemeId", hroomPlanSvc.findAvilListBy(0, null, 1,mainRoomIDS,null));
		}else{
			logger.error("roomId=" + mainRoomIDS + "数据存在问题");
		}
		
		setGuestSelectInfo(model, rid);
		model.addAttribute("roomIds", rid);
		model.addAttribute("pageType", 0);
		return "guestdetail/guestdetail";
	}
}
