package com.cyw.mammoth.action;

import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JsonConfig;
import net.sf.json.processors.JsDateJsonBeanProcessor;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.cyw.common.util.DateUtil;
import com.cyw.common.util.StrUtils;
import com.cyw.mammoth.auth.AppBaseCfg;
import com.cyw.mammoth.bean.BPaied;
import com.cyw.mammoth.bean.BookRoom;
import com.cyw.mammoth.bean.GrpDoc;
import com.cyw.mammoth.bean.GstBill;
import com.cyw.mammoth.bean.Hcodes;
import com.cyw.mammoth.bean.Hconsume;
import com.cyw.mammoth.bean.HgstOri;
import com.cyw.mammoth.bean.HroomCharacters;
import com.cyw.mammoth.bean.HroomType;
import com.cyw.mammoth.bean.HsetlKind;
import com.cyw.mammoth.bean.Hsettle;
import com.cyw.mammoth.bean.Parameter;
import com.cyw.mammoth.bean.TaDoc;
import com.cyw.mammoth.cywenum.HcodesEnumType;
import com.cyw.mammoth.service.BPaiedSvc;
import com.cyw.mammoth.service.BookRoomSvc;
import com.cyw.mammoth.service.GrpDocSvc;
import com.cyw.mammoth.service.GstBillSvc;
import com.cyw.mammoth.service.GuestdocSvc;
import com.cyw.mammoth.service.HbuildingSvc;
import com.cyw.mammoth.service.HcodesSvc;
import com.cyw.mammoth.service.HconsumeSvc;
import com.cyw.mammoth.service.HgstOriSvc;
import com.cyw.mammoth.service.HroomCharactersSvc;
import com.cyw.mammoth.service.HroomPlanSvc;
import com.cyw.mammoth.service.HroomTypeSvc;
import com.cyw.mammoth.service.HsetlKindSvc;
import com.cyw.mammoth.service.HsettleSvc;
import com.cyw.mammoth.service.ParameterSvc;
import com.cyw.mammoth.service.RoomNumSvc;
import com.cyw.mammoth.service.RoomsSvc;
import com.cyw.mammoth.service.TADocSvc;
import com.cyw.mammoth.vo.AjaxJson;
import com.cyw.mammoth.vo.BookRoomSearchVo;
import com.cyw.mammoth.vo.GroupBookSearchVo;
import com.cyw.mammoth.vo.GrpDocVo;
import com.google.gson.Gson;

/**
 * 团队预定管理
 * 
 * @author liuweixing@cyw.so
 * @version [v1.0, 2015-9-14]
 * @see [BookRoomAction]
 * @since [产品/模块版本]
 */
@SuppressWarnings({ "unchecked", "rawtypes" })
@Controller
public class GroupBookRoomAction {
	
	/**
     * 日志对象
     */
    protected Logger logger = LoggerFactory.getLogger(getClass());
    
	@Autowired
	private GrpDocSvc grpDocSvc;
	@Autowired
	private GuestdocSvc guestdocSvc;
	@Autowired
	private HcodesSvc hcodesSvc;
	@Autowired
	private HsetlKindSvc hsetlKindSvc;

	@Autowired
	private BookRoomSvc bookRoomSvc;

	@Autowired
	private HroomTypeSvc hRoomTypeSvc;

	@Autowired
	private RoomsSvc roomsSvc;

	@Autowired
	private RoomNumSvc roomNumSvc;

	@Autowired
	private HbuildingSvc hbuidingSvc;

	@Autowired
	private HroomTypeSvc hroomTypeSvc;

	@Autowired
	private HroomCharactersSvc hroomCharactersSvc;
	@Autowired
	private ParameterSvc parameterSvc;
	@Autowired
	private BPaiedSvc bPaiedSvc;
	@Autowired
	private HconsumeSvc hconsumeSvc;
	@Autowired
	private HgstOriSvc hgstOriSvc;
	@Autowired
	private HsettleSvc hsettleSvc;
	@Autowired
	private GstBillSvc gstBillSvc;
	@Autowired
	private TADocSvc tADocSvc;
	
	@Autowired
	private HroomPlanSvc hroomPlanSvc;
	/**
	 * 跳转至团体预定页面
	 * 
	 * @param model
	 * @param searchVo
	 * @return
	 */
	@RequestMapping("/group/grouplist.do")
	public String grouplist(ModelMap model, GroupBookSearchVo searchVo) {
		// 查询通用代码-预定方式
		List<Hcodes> ydList = hcodesSvc.getListByCodes("013");
		// 查询通用代码-客人分类
		List<Hcodes> krflList = hcodesSvc.getListByCodes("004");
		// 客人来源
		//List<Hcodes> gestOrilist = hcodesSvc.getListByCodes("023");
		List<HgstOri> gestOrilist= hgstOriSvc.getList("status", 0);
		// 查询通用代码-证件号
		List<Hcodes> zjlbList = hcodesSvc.getListByCodes("006");

		List<HroomCharacters> rclist = hroomCharactersSvc.getList("status", 0);

		model.addAttribute("rclist", rclist);
		
		if (searchVo.getActive() == null) {
			searchVo.setActive(0);
		}
		// 查询房类
		// 房间特征
		try {
			List<HroomCharacters> hroomCharacters = hroomCharactersSvc.getList("status", 0);
			model.addAttribute("hroomCharacters", hroomCharacters);
		} catch (Exception e) {
			e.printStackTrace();
		}
		//
		//List<HsetlKind> paytypeList = hsetlKindSvc.getList("status", 0);
		List<Hsettle> paytypeList= hsettleSvc.getList("status", 0);
		model.addAttribute("gestOrilist", gestOrilist);
		model.addAttribute("ydList", ydList);
		model.addAttribute("krflList", krflList);
		model.addAttribute("zjlbList", zjlbList);
		model.addAttribute("paytypeList", paytypeList);
		model.addAttribute("searchVo", searchVo);
		model.addAttribute("hroomPlanList", hroomPlanSvc.findAvilListBy(0, null, null, null, null)) ;
		//
		Parameter param = parameterSvc.get(1);
		Date hotleDateDB = param.getPara3();
		String hotelDateStr = DateUtil.convertDate2String(hotleDateDB, DateUtil.defaultDatePattern);
		model.addAttribute("hotelDateStr", hotelDateStr);
		return "reservation/groupbook";
	}
	/**
	 * 加载团队预定列表
	 * 
	 * @param model
	 * @param searchVo
	 * @return
	 * @see gusetDocBookList
	 */
	@RequestMapping("/group/groupdocbooklist.do")
	@ResponseBody
	public AjaxJson gusetDocBookList(ModelMap model, GroupBookSearchVo searchVo) {
		AjaxJson aj = new AjaxJson();
		/*
		 * if(searchVo.getActive()==null){ searchVo.setActive(0); }
		 */
		List<GrpDocVo> guestList = grpDocSvc.getBookRoomList(searchVo);
		// 查询通用代码-预定方式
		List<Hcodes> ydList = hcodesSvc.getListByCodes(HcodesEnumType.HCODE_BOOK_METHOD.getValue());
		//客人来源
		//List<Hcodes> gestOrilist = hcodesSvc.getListByCodes("023");
		List<HgstOri> gestOrilist= hgstOriSvc.getList("status", 0);
		// 查询通用代码-客人分类
		List<Hcodes> krflList = hcodesSvc.getListByCodes("004");
		// 查询通用代码-证件号
		List<Hcodes> zjlbList = hcodesSvc.getListByCodes("006");

		// 查询房类
		//
		//List<HsetlKind> paytypeList = hsetlKindSvc.getList("status", 0);
		List<Hsettle> paytypeList= hsettleSvc.getList("status", 0);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("gestOrilist", gestOrilist);
		map.put("ydList", ydList);
		map.put("krflList", krflList);
		map.put("zjlbList", zjlbList);
		map.put("guestList", guestList);
		map.put("paytypeList", paytypeList);
		map.put("searchVo", searchVo);

		aj.setAttributes(map);
		aj.setObj(guestList);
		Gson gson= new Gson();
		String json= gson.toJson(aj);
		System.out.println(json);
		return aj;
	}
    @Deprecated
	@RequestMapping("/group/addGroupBook.do")
	public String addGroupBook(ModelMap model, BookRoomSearchVo searchVo) {
		// 查询通用代码-预定方式
		List<Hcodes> ydList = hcodesSvc.getListByCodes("013");
		// 查询通用代码-客人分类
		List<Hcodes> krflList = hcodesSvc.getListByCodes("004");
		// 客人来源
		//List<Hcodes> gestOrilist = hcodesSvc.getListByCodes("023");
		List<HgstOri> gestOrilist= hgstOriSvc.getList("status", 0);
		// 查询通用代码-证件号
		List<Hcodes> zjlbList = hcodesSvc.getListByCodes("006");

		List<HroomCharacters> rclist = hroomCharactersSvc.getList("status", 0);

		model.addAttribute("rclist", rclist);

		if (searchVo.getActive() == null) {
			searchVo.setActive(0);
		}
		// 查询房类
		// 房间特征
		try {
			List<HroomCharacters> hroomCharacters = hroomCharactersSvc.getList("status", 0);
			model.addAttribute("hroomCharacters", hroomCharacters);
		} catch (Exception e) {
			e.printStackTrace();
		}
		//
		List<HsetlKind> paytypeList = hsetlKindSvc.getList("status", 0);
		model.addAttribute("gestOrilist", gestOrilist);
		model.addAttribute("ydList", ydList);
		model.addAttribute("krflList", krflList);
		model.addAttribute("zjlbList", zjlbList);
		model.addAttribute("paytypeList", paytypeList);
		return "reservation/groupbookDetail";
	}
    
    @RequestMapping("/group/getGroupDocByCheckId.do")
	@ResponseBody
	public AjaxJson getGuestDocByCheckId(Integer checkId){
		AjaxJson j = new AjaxJson();
		GrpDoc guestDoc = grpDocSvc.get("checkId", checkId);
		convertBookState(guestDoc);
		j.setObj(guestDoc);
		return j;
	}
    
    /***
	 * 将订单状态转换成对应的文字，需要的情况下使用，如果页面不需要转换，则无需使用此方法
	 * @param guestdoc
	 */
	private void convertBookState(GrpDoc guestdoc){
		if(guestdoc!=null){
			String bookState = guestdoc.getBookStat();
			if(StringUtils.isNotEmpty(bookState)){
				if("B".equals(bookState)){
					guestdoc.setBookStat("未确认");
				}else if("C".equals(bookState)){
					guestdoc.setBookStat("取消");
				}else if("N".equals(bookState)){
					guestdoc.setBookStat("NO SHOW");
				}else if("O".equals(bookState)){
					guestdoc.setBookStat("已确认");
				}else if("A".equals(bookState)){
					guestdoc.setBookStat("全部抵达");
				}else if("R".equals(bookState)){
					guestdoc.setBookStat("部分抵达");
				}
			}
		}
	}

	/**
	 * 新增团队预定
	 * 
	 * @return
	 * @see save
	 */
	@ResponseBody
	@RequestMapping("/group/save.do")
	public AjaxJson save(String bookRoomJson, String guestDocJson, String roomIds, String currBookRoomId, String bookState) {
		AjaxJson aj = new AjaxJson();
		List<BookRoom> brLists = new ArrayList<BookRoom>();
		BookRoom br;
		GrpDoc guestDoc = null;
		try {
			if (StringUtils.isNotEmpty(bookRoomJson)) {
				bookRoomJson = bookRoomJson.substring(1, bookRoomJson.length() - 1);
				JSONArray jo = JSONObject.parseArray(bookRoomJson);
				for (int i = 0; i < jo.size(); i++) {
					br = new BookRoom();
					br = (BookRoom) JSONObject.parseObject(jo.get(i).toString(), BookRoom.class);
					//--
					String strDate=com.cyw.common.util.DateUtil.convertDate2String(br.getReachDate(), com.cyw.common.util.DateUtil.defaultDatePattern);
					String reachDateTime=strDate+" "+br.getReachTime();
					Date reachDate=com.cyw.common.util.DateUtil.convertStringToDate(com.cyw.common.util.DateUtil.dateTimeShortPattern,reachDateTime);
					br.setReachDate(reachDate);
					//--
					brLists.add(br);
				}
			}
			List<BookRoom> brLists2 = new ArrayList<BookRoom>(new HashSet<BookRoom>(brLists));

			if (StringUtils.isNotEmpty(guestDocJson)) {
				guestDoc = JSONObject.parseObject(guestDocJson, GrpDoc.class);
				guestDoc.setBookStat("B"); // 新增订单状态未未确认
				if(StrUtils.isValidString(guestDoc.getBookList())){
					List list = grpDocSvc.getList("bookList", guestDoc.getBookList());
					if(list.size()>0){
						aj.setSuccess(false);
						aj.setMsg("订单号已存在");
						return aj;
					}
				}
				if(StrUtils.isValidString(guestDoc.getGrpId())){
					List list = grpDocSvc.getList("grpId", guestDoc.getGrpId().trim());
					if(list.size()>0){
						aj.setSuccess(false);
						aj.setMsg("团代码已存在");
						return aj;
					}
				}

			}
			GrpDoc backGuestdoc = grpDocSvc.saveForBookRoom(guestDoc, brLists2, roomIds, currBookRoomId);
			aj.setObj(backGuestdoc);
		} catch (Exception e) {
			aj.setSuccess(false);
			;
			e.printStackTrace();
		}
		return aj;

	}

	/**
	 * 修改散客预定
	 * 
	 * @param guestdoc
	 * @param checkId
	 * @return
	 * @see update
	 */
	@ResponseBody
	@RequestMapping("/group/update.do")
	public AjaxJson update(String bookRoomJson, String guestDocJson, String bookState, String roomIds, String currBookRoomId) {
		AjaxJson j = new AjaxJson();
		j.setSuccess(true);
		Map map = new HashMap();
		List<BookRoom> brLists = new ArrayList<BookRoom>();
		BookRoom br;
		GrpDoc guestDoc = null;
		if (StringUtils.isNotEmpty(bookRoomJson)) {
			bookRoomJson = bookRoomJson.substring(1, bookRoomJson.length() - 1);
			JSONArray jo = JSONObject.parseArray(bookRoomJson);
			for (int i = 0; i < jo.size(); i++) {
				br = new BookRoom();
				br = (BookRoom) JSONObject.parseObject(jo.get(i).toString(), BookRoom.class);
				brLists.add(br);
			}
		}

		if (StringUtils.isNotEmpty(guestDocJson)) {
			guestDoc = JSONObject.parseObject(guestDocJson, GrpDoc.class);
			guestDoc.setBookStat(bookState);
			if(StrUtils.isValidString(guestDoc.getGrpId())){
				List list = grpDocSvc.getList("grpId", guestDoc.getGrpId().trim());
				if(list.size()>0){
					GrpDoc gd=(GrpDoc)list.get(0);
					Integer ck1=gd.getCheckId();
					Integer ck2=guestDoc.getCheckId();
					if(gd!=null && (gd.getCheckId().intValue()!=guestDoc.getCheckId().intValue())){
						j.setSuccess(false);
						j.setMsg("团代码已存在");
						return j;
					}
				}
			}
		}
		GrpDoc backGuestdoc = grpDocSvc.updateForBookRoom(guestDoc, brLists, roomIds, currBookRoomId);
		j.setAttributes(map);
		j.setObj(backGuestdoc);
		return j;
	}
	@ResponseBody
	@RequestMapping("/group/delete.do")
	public AjaxJson logicDelete(Integer checkId){
		AjaxJson aj = new AjaxJson();
		
		return aj;
	}
	
	/**
	 * 查看详细
	 * 
	 * @param checkId
	 * @return
	 * @see
	 */
	@ResponseBody
	@RequestMapping("/group/view.do")
	public GrpDoc view(Integer checkId,HttpServletResponse response) {
		GrpDoc guestDoc = grpDocSvc.get("checkId", checkId);
		GstBill gsb=gstBillSvc.get(guestDoc.getBillId()==null?0:guestDoc.getBillId());
		if(gsb!=null){
			String strList="0.00";
			String srtBanlce="0.00";
			try{
				DecimalFormat    df   = new DecimalFormat("######0.00");  
				Double d=gsb.getLimit();
				Double banlace=gsb.getBorrow()-gsb.getLent();
				strList=df.format(d);
				
				srtBanlce=df.format(banlace);
				
			}catch(Exception e){
				e.printStackTrace();
			}
			guestDoc.setBiilbLimit(strList);
			guestDoc.setBillbBlance(srtBanlce);
		}
		try{
			if(StrUtils.isValidString(guestDoc.getCompId().trim())){
				TaDoc tadoc= tADocSvc.get(Integer.parseInt(guestDoc.getCompId().trim()));
				if(null!=tadoc){
					guestDoc.setUnitNamec(tadoc.getNamec());
					// 销售员
					Hcodes hc = hcodesSvc.get("codeId", tadoc.getSalemanId()) ;
					guestDoc.setSaler(hc.getCodeNamec());
				}
			}
		}catch(Exception e){
			logger.error("查询合约单位发生错误,",e);
		}
		return guestDoc;
	}

	/**
	 * 加载团队预定列表
	 * 
	 * @param model
	 * @param searchVo
	 * @return
	 * @see gusetDocBookList
	 */
	@RequestMapping("/group/groupbooklist.do")
	public String groupbooklist(ModelMap model, GroupBookSearchVo searchVo) {
		if (searchVo.getActive() == null) {
			searchVo.setActive(0);
		}
		List<GrpDocVo> guestList = grpDocSvc.getBookRoomList(searchVo);
		// 查询通用代码-预定方式
		List<Hcodes> ydList = hcodesSvc.getListByCodes("013");
		// 查询通用代码-客人分类
		List<Hcodes> krflList = hcodesSvc.getListByCodes("004");
		// 查询通用代码-证件号
		List<Hcodes> zjlbList = hcodesSvc.getListByCodes("006");
		//
		List<HsetlKind> paytypeList = hsetlKindSvc.getList("status", 0);
		model.addAttribute("ydList", ydList);
		model.addAttribute("krflList", krflList);
		model.addAttribute("zjlbList", zjlbList);
		model.addAttribute("guestList", guestList);
		model.addAttribute("paytypeList", paytypeList);
		model.addAttribute("searchVo", searchVo);
		// Gson gson= new Gson();
		// String object=gson.toJson(guestList);
		String object = JSON.toJSONStringWithDateFormat(guestList, "yyyy-MM-dd", SerializerFeature.WriteDateUseDateFormat);
		model.addAttribute("object", object);
		return "reservation/groupbook";
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
	@RequestMapping("/group/consumes.do")
	@ResponseBody
	public String getConsumes(HttpServletRequest request,HttpServletResponse response,Integer checkId){
		Map map = new HashMap();
		List blist = new ArrayList();
		List list = null;
		BPaied bp = null;
		try {
			GrpDoc guestdoc = grpDocSvc.get(checkId);
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
	@RequestMapping("/group/addBpaied.do")
	@ResponseBody
	public String setSplit(HttpServletRequest request,
			HttpServletResponse response,BPaied bpaied,String ifBate){
		AjaxJson json = new AjaxJson();
		try {
			//检查是否设置过B账信息
			String operId = AppBaseCfg.getOperator().getOperId();
			bpaied.setOperId(operId);
			grpDocSvc.setSplit(bpaied, ifBate);
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
	 * 查看预定详细信息
	 * 
	 * @param checkId
	 */
	@ResponseBody
	@RequestMapping("/group/getBookRoomListByCheckId.do")
	public AjaxJson getBookRoomList(Integer checkId) {
		AjaxJson aj = new AjaxJson();
		if (checkId != null) {
			List<BookRoom> list = bookRoomSvc.getList("checkId", checkId);
			for (int i = 0; i < list.size(); i++) {
				list.get(i).setNum(i + 1);
			}
			List<HroomType> roomTypeList = hRoomTypeSvc.getAll();
			for (HroomType hroomType : roomTypeList) {
				hroomType.setCodeNamec(hroomType.getCodeId().trim() + ":" + hroomType.getCodeNamec());
			}
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("list", list);
			map.put("roomTypeList", roomTypeList);
			aj.setAttributes(map);
		} else {
			aj.setSuccess(false);
		}
		return aj;
	}
	/**
	 * 新增散客预定
	 * 
	 * @return
	 * @see save
	 */
	/*
	 * @ResponseBody
	 * 
	 * @RequestMapping("/group/save.do") public String save(HttpServletRequest
	 * request,Guestdoc guestdoc){ Guestdoc
	 * backGuestdoc=guestdocSvc.saveForBookRoom(guestdoc); Gson gson= new
	 * Gson(); return gson.toJson(backGuestdoc); }
	 *//**
	 * 修改散客预定
	 * 
	 * @param guestdoc
	 * @param checkId
	 * @return
	 * @see update
	 */
	/*
	 * @ResponseBody
	 * 
	 * @RequestMapping("/group/update.do") public String update(Guestdoc
	 * guestdoc){ Guestdoc
	 * backGuestdoc=guestdocSvc.updateForBookRoom(guestdoc,guestdoc
	 * .getCheckId()); Gson gson= new Gson(); return gson.toJson(backGuestdoc);
	 * }
	 *//**
	 * 检查订单号是否存在
	 * 
	 * @param bookNo
	 * @return
	 * @see checkBookList
	 */
	/*
	 * @ResponseBody
	 * 
	 * @RequestMapping("/group/checkNo.ajax") public boolean
	 * checkBookList(String bookNo){ boolean ischeck=false; ischeck
	 * =guestdocSvc.getBookListByNo(bookNo.trim()); return ischeck; }
	 *//**
	 * 查询散客预定明细列表
	 * 
	 * @param checkId
	 * @return
	 * @see getBookListByChckId
	 */
	/*
	 * @SuppressWarnings("rawtypes")
	 * 
	 * @ResponseBody
	 * 
	 * @RequestMapping("/group/retain.do") public List
	 * getBookListByChckId(Integer checkId){ checkId=20000; List<BookRoom>
	 * roomList= bookRoomSvc.getList("checkId", checkId); return roomList; }
	 *//**
	 * 查看详细
	 * 
	 * @param checkId
	 * @return
	 * @see
	 */
	/*
	 * @ResponseBody
	 * 
	 * @RequestMapping("/group/view.do") public String view(Integer checkId){
	 * Guestdoc guestDoc=guestdocSvc.get("checkId", checkId); Gson gson= new
	 * Gson(); return gson.toJson(guestDoc);
	 * 
	 * }
	 */

}
