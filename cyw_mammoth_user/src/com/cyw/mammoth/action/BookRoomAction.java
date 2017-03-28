package com.cyw.mammoth.action;

import java.io.PrintWriter;
import java.io.Serializable;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.cyw.common.util.Constant;
import com.cyw.common.util.DateUtil;
import com.cyw.common.util.DateUtils;
import com.cyw.common.util.StrUtils;
import com.cyw.mammoth.auth.AppBaseCfg;
import com.cyw.mammoth.auth.ShiroDbAuthRealm.ShiroUser;
import com.cyw.mammoth.bean.BookRoom;
import com.cyw.mammoth.bean.GrpDoc;
import com.cyw.mammoth.bean.GstBill;
import com.cyw.mammoth.bean.Guestdoc;
import com.cyw.mammoth.bean.Hbuilding;
import com.cyw.mammoth.bean.Hcodes;
import com.cyw.mammoth.bean.HgstOri;
import com.cyw.mammoth.bean.HroomCharacters;
import com.cyw.mammoth.bean.HroomType;
import com.cyw.mammoth.bean.HsetlKind;
import com.cyw.mammoth.bean.Hsettle;
import com.cyw.mammoth.bean.Parameter;
import com.cyw.mammoth.bean.RoomNum;
import com.cyw.mammoth.bean.Rooms;
import com.cyw.mammoth.bean.TaDoc;
import com.cyw.mammoth.service.BookRoomSvc;
import com.cyw.mammoth.service.GrpDocSvc;
import com.cyw.mammoth.service.GstBillSvc;
import com.cyw.mammoth.service.GuestdocSvc;
import com.cyw.mammoth.service.HbuildingSvc;
import com.cyw.mammoth.service.HcodesSvc;
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
import com.cyw.mammoth.vo.BookStateEnum;
import com.cyw.mammoth.vo.GuestDocModel;
import com.google.common.collect.Lists;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

/**
 * 预定管理
 * 
 * @author liuweixing@cyw.so
 * @version [v1.0, 2015-9-14]
 * @see [BookRoomAction]
 * @since [产品/模块版本]
 */
@SuppressWarnings({ "unchecked", "rawtypes" }) 
@Controller
public class BookRoomAction implements Serializable {
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	/**
     * 日志对象
     */
    protected Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private GuestdocSvc guestdocSvc;
	@Autowired
	private GrpDocSvc grpDocSvc;
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
	private HgstOriSvc hgstOriSvc;
	@Autowired
	private GstBillSvc gstBillSvc;
	@Autowired
	private HsettleSvc hsettleSvc;
	@Autowired
	private TADocSvc tADocSvc;
	@Autowired
	private HroomPlanSvc hroomPlanSvc ;

	/**
	 * 跳转至散客预定页面
	 * 
	 * @param model
	 * @param searchVo
	 * @return
	 */
	@RequestMapping("/bookroom/booklist.do")
	public String booklist(ModelMap model, BookRoomSearchVo searchVo) {
		// 查询通用代码-预定方式
		List<Hcodes> ydList = hcodesSvc.getListByCodes("013");
		// 查询通用代码-客人分类
		List<Hcodes> krflList = hcodesSvc.getListByCodes("004");
		//客人来源
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
		//房间特征
		try{
			List<HroomCharacters> hroomCharacters=hroomCharactersSvc.getList("status", 0);
			model.addAttribute("hroomCharacters", hroomCharacters);
		}catch(Exception e){
			e.printStackTrace();
		}
		//
		List<HsetlKind> paytypeList = hsetlKindSvc.getList("status", 0);
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

		return "reservation/fitbook";
	}
	
	
	@RequestMapping("/bookroom/getGuestDocByCheckId.do")
	@ResponseBody
	public AjaxJson getGuestDocByCheckId(Integer checkId){
		AjaxJson j = new AjaxJson();
		Guestdoc guestDoc = guestdocSvc.get("checkId", checkId);
		convertBookState(guestDoc);
		j.setObj(guestDoc);
		return j;
	}
	/**
	 * 确认散客预订单
	 * @param checkId
	 * @return
	 */
	@RequestMapping("/bookroom/confirmGuestDoc.do")
	@ResponseBody
	public AjaxJson confirmGuestDoc(Integer checkId){
		logger.debug("start /bookroom/confirmGuestDoc.do");
		AjaxJson aj = new AjaxJson();
		Guestdoc guestDoc = guestdocSvc.get(checkId);
		if(null==guestDoc){
		   aj.setSuccess(false);
		   aj.setMsg("checkId不存在");
		   return aj;
		}
		try{
			String operId=AppBaseCfg.getOperator().getOperId();
			guestDoc.setBookStat(BookStateEnum.CONFIRMED.value);
			guestDoc.setConfirmDate(new Date());
			guestDoc.setConfirmOperid(operId);
			guestDoc.setLastOper(operId);
			guestDoc.setLastTime(new Date());
			guestdocSvc.update(guestDoc);
			aj.setSuccess(true);
		}catch(Exception e){
			aj.setSuccess(false);
			aj.setMsg("确认订单操作失败");
			logger.error("confirmGuestDoc error,reson:",e);
		}
		logger.debug("end /bookroom/confirmGuestDoc.do");
		return aj;
	}
	/**
	 * 确认团队预订单
	 * @param checkId
	 * @return
	 */
	@RequestMapping("/bookroom/confirmGrpDoc.do")
	@ResponseBody
	public AjaxJson confirmGrpDoc(Integer checkId){
		logger.debug("start /bookroom/confirmGrpDoc.do");
		AjaxJson aj = new AjaxJson();
		GrpDoc grpDoc = grpDocSvc.get(checkId);
		if(null==grpDoc){
		   aj.setSuccess(false);
		   aj.setMsg("checkId不存在");
		   return aj;
		}
		try{
			String operId=AppBaseCfg.getOperator().getOperId();
			grpDoc.setBookStat(BookStateEnum.CONFIRMED.value);
			grpDoc.setConfirmDate(new Date());
			grpDoc.setConfirmOperid(operId);
			grpDoc.setLastOper(operId);
			grpDoc.setLastTime(new Date());
			grpDocSvc.update(grpDoc);
			aj.setSuccess(true);
		}catch(Exception e){
			aj.setSuccess(false);
			aj.setMsg("确认订单操作失败");
			logger.error("confirmGuestDoc error,reson:",e);
		}
		logger.debug("end /bookroom/confirmGrpDoc.do");
		return aj;
	}
	
	/***
	 * 将订单状态转换成对应的文字，需要的情况下使用，如果页面不需要转换，则无需使用此方法
	 * @param guestdoc
	 */
	private void convertBookState(Guestdoc guestdoc){
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
	 * 加载散客预定列表
	 * 
	 * @param model
	 * @param searchVo
	 * @return
	 * @see gusetDocBookList
	 */
	@RequestMapping("/bookroom/guestdocbooklist.do")
	@ResponseBody
	public AjaxJson gusetDocBookList(ModelMap model, BookRoomSearchVo searchVo) {
		AjaxJson j = new AjaxJson();
		/*
		 * if(searchVo.getActive()==null){ searchVo.setActive(0); }
		 */
		List<GuestDocModel> guestList = guestdocSvc.getBookRoomList(searchVo);
		// 查询通用代码-预定方式
		List<Hcodes> ydList = hcodesSvc.getListByCodes("013");
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

		j.setAttributes(map);
		j.setObj(guestList);
		logger.debug("散客预定列表:",guestList);
		// String object=JSON.toJSONStringWithDateFormat(guestList, "yyyy-MM-dd",SerializerFeature.WriteDateUseDateFormat);
		// model.addAttribute("object", object);
		// return "reservation/fitbook";
		return j;
	}

	/**
	 * 新增散客预定
	 * 
	 * @return
	 * @see save
	 */
	@ResponseBody
	@RequestMapping("/bookroom/save.do")
	public AjaxJson save(String bookRoomJson, String guestDocJson,String roomIds,String currBookRoomId,String bookState) {
		AjaxJson j = new AjaxJson();
		List<BookRoom> brLists = new ArrayList<BookRoom>();
		BookRoom br;
		Guestdoc guestDoc = null;
		try {
			if (StringUtils.isNotEmpty(bookRoomJson)) {
				bookRoomJson = bookRoomJson.substring(1, bookRoomJson.length()-1);
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
				guestDoc = JSONObject.parseObject(guestDocJson, Guestdoc.class);
				guestDoc.setBookStat("B"); //新增订单状态未未确认
				
			}
			if(StrUtils.isValidString(guestDoc.getBookList())){
				List list = guestdocSvc.getList("bookList", guestDoc.getBookList());
				if(list.size()>0){
					j.setSuccess(false);
					j.setMsg("订单号已存在");
					return j;
				}
			}
			Guestdoc backGuestdoc = guestdocSvc.saveForBookRoom(guestDoc, brLists2,roomIds,currBookRoomId);
			j.setObj(backGuestdoc);
		} catch (Exception e) {
			j.setSuccess(false);
			//String errorMessage = "错误信息:" + e.getCause().getMessage() + "系统错误号:" + ((SQLException) e.getCause()).getErrorCode();
			//j.setMsg(errorMessage);
			//System.out.println(errorMessage);
			e.printStackTrace();
		}
		return j;

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
	@RequestMapping("/bookroom/update.do")
	public AjaxJson update(String bookRoomJson, String guestDocJson,String bookState,String roomIds,String currBookRoomId) {
		AjaxJson j = new AjaxJson();
		/*System.out.println(bookRoomJson);
		System.out.println(guestDocJson);
		System.out.println(bookState);*/
		j.setSuccess(true);
		Map map = new HashMap();
		List<BookRoom> brLists = new ArrayList<BookRoom>();
		BookRoom br;
		Guestdoc guestDoc = null;
		if (StringUtils.isNotEmpty(bookRoomJson)) {
			bookRoomJson = bookRoomJson.substring(1, bookRoomJson.length()-1);
			JSONArray jo = JSONObject.parseArray(bookRoomJson);
			for (int i = 0; i < jo.size(); i++) {
				br = new BookRoom();
				br = (BookRoom) JSONObject.parseObject(jo.get(i).toString(), BookRoom.class);
				brLists.add(br);
			}
		}
		
		if (StringUtils.isNotEmpty(guestDocJson)) {
			guestDoc = JSONObject.parseObject(guestDocJson, Guestdoc.class);
			guestDoc.setBookStat(bookState);
		}
		Guestdoc backGuestdoc = guestdocSvc.updateForBookRoom(guestDoc, brLists,roomIds,currBookRoomId);
		j.setAttributes(map);
		j.setObj(backGuestdoc);
		return j;
	}

	/**
	 * 取消预定
	 * @param check_id
	 * @param cacelObject
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/bookroom/cancelbookroom.do")
	public String cancelBookRoom(@RequestParam String check_id, @RequestParam String cacelObject){
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("issuccess", true);
		bookRoomSvc.cacelBookRoom(Integer.parseInt(check_id), cacelObject, AppBaseCfg.getOperator().getOperId());
		Gson gson = new Gson();
		return gson.toJson(resultMap);
	}
	
	@ResponseBody
	@RequestMapping("/bookroom/delete.do")
	public AjaxJson loginDelete(Integer bookRoomId){
		AjaxJson aj=new AjaxJson();
		try{
			BookRoom bookRoom = bookRoomSvc.get(bookRoomId);
			if(bookRoom==null){
				aj.setSuccess(false);
				aj.setMsg("预定记录不存在");
				return aj;
			}
			List<RoomNum> roomNumList = roomNumSvc.getList("bookId", bookRoomId);
			if(roomNumList.size()>0){
				aj.setSuccess(false);
				aj.setMsg("留房记录不为空不能删除");
				return aj;
			}
			bookRoom.setStatus(0);
			Subject currentUser = SecurityUtils.getSubject();
			ShiroUser shiroUser = (ShiroUser) currentUser.getPrincipal();
			String operId=shiroUser.getLoginName();
			bookRoom.setCancelOper(operId);
			bookRoom.setCancelTime(new Date());
			bookRoom.setModifyOper(operId);
			bookRoom.setModifyTime(new Date());
			bookRoomSvc.update(bookRoom);
			aj.setSuccess(true);
		}catch(Exception e){
			aj.setSuccess(false);
			aj.setMsg("删除失败");
		}
			
		return aj;
	}

	/**
	 * 检查订单号是否存在
	 * 
	 * @param bookNo
	 * @return
	 * @see checkBookList
	 */
	@ResponseBody
	@RequestMapping("/bookroom/checkNo.ajax")
	public boolean checkBookList(String bookNo) {
		boolean ischeck = false;
		ischeck = guestdocSvc.getBookListByNo(bookNo.trim());
		return ischeck;
	}

	/**
	 * 查询散客预定明细列表
	 * 
	 * @param checkId
	 * @return
	 * @see getBookListByChckId
	 */
	@ResponseBody
	@RequestMapping("/bookroom/retain.do")
	public AjaxJson getBookListByChckId(Integer checkId) {
		AjaxJson j = new AjaxJson();
		Map<String, Object> map = new HashMap<String, Object>();
		List<HroomType> roomTypeList1 = hRoomTypeSvc.getAll();
		List roomTypeList = new ArrayList();
		Map<String,String> maproom = null;
		for (HroomType hroomType : roomTypeList1) {
			maproom = new HashMap<String, String>();
			maproom.put("codeId", hroomType.getCodeId().trim());
			maproom.put("codeNamec", hroomType.getCodeId().trim()+":"+hroomType.getCodeNamec().trim());
			maproom.put("price", hroomType.getPrice().toString());
			roomTypeList.add(maproom);
		}
		map.put("roomTypeList", roomTypeList);
		j.setAttributes(map);
		if (checkId != null) {
			List<BookRoom> roomList = bookRoomSvc.getList("checkId", checkId);
			for (BookRoom bookRoom : roomList) {
				Iterator<RoomNum> it = bookRoom.getRoomNums().iterator();
				while(it.hasNext()){
					if(it.next().getStatus()==1){
						it.remove();
					}
				}
				//查询room_num的具体留房
				List<RoomNum> roomNumList= roomNumSvc.getList("bookId", bookRoom.getBookRoomId());
				for(RoomNum tp:roomNumList){
					tp.setBookRoom(null);
				}
				//System.out.println(roomNumList.size());
				Date d = bookRoom.getReachDate();
				String value = DateUtils.dateFormat.format(d);
				if(StringUtils.isNotEmpty(value)){
					//System.out.print(bookRoom.getId()+":");
					//System.out.println(value.substring(11, 16));
					bookRoom.setReachTime(value.substring(11, 16));
				}
			}
			j.setObj(roomList);
		}
		return j;
	}

	/**
	 * 查看详细
	 * 
	 * @param checkId
	 * @return
	 * @see
	 */
	@ResponseBody
	@RequestMapping("/bookroom/view.do")
	public void view(Integer checkId,HttpServletResponse response) {
		AppBaseCfg.getOperator();
		Guestdoc guestDoc = guestdocSvc.get("checkId", checkId);
		try{
			GstBill gsb=gstBillSvc.get(guestDoc.getBillbId()==null?0:guestDoc.getBillbId());
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
		}catch(Exception e){
			logger.error("获取GuestDoc账目信息发生错误,",e);
		}
		try{
			net.sf.json.JSONObject json = net.sf.json.JSONObject.fromObject(guestDoc);
			if(StrUtils.isValidString(guestDoc.getCompId().trim())){
				TaDoc tadoc= tADocSvc.get(Integer.parseInt(guestDoc.getCompId().trim()));
				if(null!=tadoc){
					json.put("unitNamec", tadoc.getNamec());
					// 销售员
					Hcodes hc = hcodesSvc.get("codeId", tadoc.getSalemanId()) ;
					json.put("saler", hc.getCodeNamec());
				}
			}
			//页面输出JSONArray的内容  .
			response.setContentType("application/json;charset=UTF-8");
		    PrintWriter out = response.getWriter();  
		    out.print(json.toString());  
		    out.close();
		}catch(Exception e){
			logger.error("查询合约单位发生错误,",e);
		}
	}
	
	/**
	 * 根据房间id串查询房间列表
	 * @param roomIds
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/bookroom/findRoomsBy.do")
	public String findRoomsBy(@RequestParam("roomIds[]") List<String> roomIds){
		StringBuffer checkIdStrs=new StringBuffer();
		if(null!=roomIds){
			HashSet h=new HashSet(roomIds);
			roomIds.clear();
			roomIds.addAll(h);
			for(String roomId:roomIds){
				//List<RoomNum>  roomNumList = roomNumSvc.getList("checkId", checkId);
				/*for(RoomNum roomNum:roomNumList){
					checkIdStrs.append(roomNum.getRoomId().trim()+",");
				}*/
				checkIdStrs.append(roomId.trim()+",");
			}
		}
		//roomsSvc.getSelRoomsInf(roomIds));
		//roomNumSvc.getList("checkId", value)
		JSONObject jsonObj =  new JSONObject();
		jsonObj.put("listJson", roomsSvc.getSelRoomsInf(checkIdStrs.toString()));
		/*if(StringUtils.isNotBlank(roomIds)){
			jsonObj.put("listJson", roomsSvc.getSelRoomsInf(roomIds));
		}*/
		return jsonObj.toJSONString();
	}
	
	@RequestMapping("/bookroom/submitBookRoomNum.do")
	@ResponseBody
	public AjaxJson submitBookRoomNum(String checkId, String bookId,String bookRoomJson){
		AjaxJson aj = new AjaxJson();
		Integer icheckId=0;
		Integer ibookId=0;
		try{
			icheckId = Integer.parseInt(checkId);
			ibookId = Integer.parseInt(bookId);
		}catch(Exception e){
			aj.setSuccess(false);
			aj.setMsg("保存失败");
			return aj;
		}
		Gson gson=new Gson();
		BookRoom book=JSONObject.parseObject(bookRoomJson, BookRoom.class);
		JSONObject json = JSONObject.parseObject(bookRoomJson);
		//System.out.println(json.toJSONString());
		List<RoomNum> roomNums=new ArrayList<RoomNum>();
		BookRoom bookRoom = bookRoomSvc.get(ibookId);
		if(null==bookRoom){
			aj.setSuccess(false);
			aj.setMsg("订单不存在");
			return aj;
		}
		if(book.getRoomNums().isEmpty()){
			
			//roomNumSvc.updateBookBoomNums(icheckId, ibookId,roomNums );
		}
		StringBuffer sb = new StringBuffer();
		for(RoomNum roomNum:book.getRoomNums()){
			roomNum.setRoomChkid(0);
			roomNum.setFlag(0);
			roomNum.setStatus(0);
			roomNum.setKeepFlag("0");
			roomNum.setReachDate(bookRoom.getReachDate());
			roomNum.setLeaveDate(bookRoom.getLeaveDate());
			roomNum.setBookRoom(null);
			roomNums.add(roomNum);
			sb.append(roomNum.getRoomId()+",");
		}
		String roomIds=sb.toString();
		if(roomIds.length()>0 && roomIds.lastIndexOf(",")>0)
		   roomIds=roomIds.substring(0,roomIds.lastIndexOf(","));
		try{
			bookRoom.getCheckId();
			bookRoom.getRoomtypeId();
			bookRoom.getBookRoomId();
			bookRoom.setRoomtypeId(book.getRoomtypeId());
			//--
			String strDate=com.cyw.common.util.DateUtil.convertDate2String(book.getReachDate(), com.cyw.common.util.DateUtil.defaultDatePattern);
			String reachDateTime=strDate+" "+book.getReachTime();
			Date reachDate=com.cyw.common.util.DateUtil.convertStringToDate(com.cyw.common.util.DateUtil.dateTimeShortPattern,reachDateTime);
			bookRoom.setReachDate(reachDate);
			//--
			bookRoom.setLeaveDate(book.getLeaveDate());
			bookRoom.setReachTime(book.getReachTime());
			bookRoomSvc.merge(bookRoom);
			roomNumSvc.updateBookBoomNums(icheckId, ibookId,bookRoom.getRoomtypeId(),roomIds,bookRoom.getReachDate(),bookRoom.getLeaveDate(),roomNums );
		}catch(Exception e){
			e.printStackTrace();
			aj.setSuccess(false);
			aj.setMsg("保存发生错误");
		}
		return aj;
	}

	/**
	 * 查看预定详细信息
	 * 
	 * @param checkId
	 */
	@ResponseBody
	@RequestMapping("/bookroom/getBookRoomListByCheckId.do")
	public AjaxJson getBookRoomList(Integer checkId) {
		AjaxJson j = new AjaxJson();
		if (checkId != null) {
			List<BookRoom> list = bookRoomSvc.getList("checkId", checkId);
			for (int i=0;i<list.size();i++) {
				list.get(i).setNum(i+1);
			}
			List<HroomType> roomTypeList = hRoomTypeSvc.getAll();
			for (HroomType hroomType : roomTypeList) {
				hroomType.setCodeNamec(hroomType.getCodeId().trim()+":"+hroomType.getCodeNamec());
			}
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("list", list);
			map.put("roomTypeList", roomTypeList);
			j.setAttributes(map);
		} else {
			j.setSuccess(false);
		}
		return j;
	}

	/**
	 * 
	 * @param roomTypeId
	 *            接受roomType代码，而不是name，如：001。
	 * @return
	 */
	@RequestMapping("bookRoom/saleList.do")
	@ResponseBody
	public AjaxJson saleAll(String bookRoomId,String roomTypeId,String checkId,String reachDate,String leaveDate) {
		AjaxJson j = new AjaxJson();
		Map searchCondition = new HashMap();
		searchCondition.put("roomTypeId", roomTypeId);
		try {
			Map map = new HashMap();
			boolean ipflag = parameterSvc.GetIPFlag();
			if (ipflag) {
				searchCondition.put("roomStat",Constant.CLEAN_CHECK_EMPTY_ROOM);
			} else {
				searchCondition.put("roomStat",Constant.CLEAR_EMPTY);
			}
			
			//DateUtils.dateFormat2.format(new Date(reachDate));
			//DateUtils.dateFormat2.format(new Date(leaveDate));
			//searchCondition.put("roomStat","OCI");
			/*searchCondition.put("reachDate", DateUtils.dateFormat2.format(new Date(reachDate)));
			searchCondition.put("leaveDate", DateUtils.dateFormat2.format(new Date(leaveDate)));*/
			searchCondition.put("reachDate", reachDate);
			searchCondition.put("leaveDate", leaveDate);
			searchCondition.put("checkId", checkId);
			searchCondition.put("bookId", bookRoomId);
			List roomsList = roomsSvc.getRoomsList(searchCondition);
			List<String> listFloorNo = roomsSvc.getAllFloorNo();
			List<Hbuilding> listBuilding = hbuidingSvc.getList("status" , Constant.HBUILDING_NORMAL);;
			List<HroomType> roomTypeList1 = hRoomTypeSvc.getAll();
			List roomTypeList = new ArrayList();
			Map roomTypeMap = null;
			for (HroomType hroomType : roomTypeList1) {
				roomTypeMap = new HashMap<String, String>();
				roomTypeMap.put("codeId", hroomType.getCodeId().trim());
				roomTypeMap.put("codeNamec", hroomType.getCodeId().trim()+":"+hroomType.getCodeNamec().trim());
				roomTypeList.add(roomTypeMap);
			}
			//此订单选定的房间
			if(StringUtils.isNotEmpty(checkId)){
				//List<Rooms> list = roomsSvc.getRoomsByCheckId(checkId,reachDate,leaveDate);
				if(StrUtils.isValidString(bookRoomId)){
					List<RoomNum> savedRooms = roomNumSvc.getList("bookId", Integer.parseInt(bookRoomId));
					Iterator<RoomNum> it=savedRooms.iterator();
					List<String> savedRoomIds=Lists.newArrayList();
					RoomNum rn=null;
					while(it.hasNext()){
						rn=it.next();
						rn.setBookRoom(null);
						if(savedRoomIds.contains(rn.getRoomId().trim())){
							it.remove();
						}else{
							savedRoomIds.add(rn.getRoomId().trim());
						}
					}
					map.put("savedRooms", savedRooms);
				}else{
					map.put("savedRooms", new ArrayList<RoomNum>());
				}
				
			}
			
			map.put("roomTypeList", roomTypeList);
			map.put("listFloorNo", listFloorNo);
			map.put("listBuilding", listBuilding);
			
			List list = new ArrayList();
			//找出所有楼层
			List<Map<String,Object>> floorMapList = new ArrayList<Map<String,Object>>();
			for(int i=0;i<roomsList.size();i++){
				Rooms room = (Rooms) roomsList.get(i);
				String floorNo = room.getFloorNo().trim();
				if(floorMapList.isEmpty()){
					Map<String,Object> floorMap=new HashMap<String,Object>();
					floorMap.put("floorNo", floorNo);
					floorMap.put("rooms", new ArrayList<Rooms>());
					floorMapList.add(floorMap);
				}else{
					boolean canPut=true;
					//检索楼层是否已经存在
					for(Map<String,Object> tmpMap:floorMapList){
						String floorValu=(String)tmpMap.get("floorNo");
						if(floorValu.trim().equals(floorNo)){
							canPut=false;
							break;
						}
					}
					if(canPut){
						Map<String,Object> floorMap=new HashMap<String,Object>();
						floorMap.put("floorNo", floorNo);
						floorMap.put("rooms", new ArrayList<Rooms>());
						floorMapList.add(floorMap);
						
					}
				}
			}
			for(int i=0;i<roomsList.size();i++){
				Rooms room = (Rooms) roomsList.get(i);
				String room_type = room.getRoomType();
				HroomType hroomType = hroomTypeSvc.get("codeId", room_type);
				String roomTypeName = hroomType.getCodeNamec();
				room.setRoomTypeName(roomTypeName);
				String floorNo = room.getFloorNo().trim();
				for(Map<String,Object> tmpMap:floorMapList){
					String floorValu=(String)tmpMap.get("floorNo").toString();
					if(floorValu.equals(floorNo)){//找到楼层
						((List)tmpMap.get("rooms")).add(room);
					}
				}

			}
			Gson gson = new Gson();
			String ja = gson.toJson(floorMapList);
			System.out.println(ja);
			j.setExtra(floorMapList);
			for (int i = 0; i < roomsList.size(); i++) {
				Map roomsMap = new HashMap<String, Object>();
				Rooms room = (Rooms) roomsList.get(i);
				String roomId = room.getRoomId();
				roomsMap.put("roomId", roomId);// 房间号码
				String room_type = room.getRoomType();
				HroomType hroomType = hroomTypeSvc.get("codeId", room_type);
				String roomType = hroomType.getCodeNamec();
				roomsMap.put("hroomType", roomType);// 房间类型
				list.add(roomsMap);
			}
			j.setAttributes(map);
			j.setObj(list);
		} catch (HibernateException e) {
			j.setSuccess(false);
			String errorMessage = "错误信息:" + e.getCause().getMessage() + "系统错误号:" + ((SQLException) e.getCause()).getErrorCode();
			j.setMsg(errorMessage);
			System.out.println(errorMessage);
		}
		return j;
	}
	
	public AjaxJson deleteBookRoom(String bookId){
		AjaxJson aj = new AjaxJson();
		
		return aj;
	}

	/**
	 * 留房操作弹出层查询确定按钮
	 * 
	 * @param remainBuildingName
	 *            楼名
	 * @param remainFloorNo
	 *            楼层
	 * @param roomTypeId
	 *            房型
	 * @param features
	 *            特征
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/bookRoom/queryRemainRoom.do")
	public AjaxJson queryRemainRoom(String bookId,String remainCheckId, String remainBuildingName, String remainFloorNo, String remainRoomTypeId, String remainReachDate, String remainLeaveDate, String[] features, String roomCharacter) {
		RoomNum rn=null;
		//Hibernate.initialize(rn);
		rn = roomNumSvc.get(353);
		//rn = roomNumSvc.load(new Integer(353));
		rn = roomNumSvc.get("id", 353);
		Map searchCondition = new HashMap();
		searchCondition.put("roomTypeId",remainRoomTypeId);
		searchCondition.put("leaveDate",remainLeaveDate);
		searchCondition.put("reachDate",remainReachDate);
		searchCondition.put("buildingName",remainBuildingName.equals("0") || null == remainBuildingName ? "" : remainBuildingName);
		searchCondition.put("floorNo",remainFloorNo);
		searchCondition.put("checkId", remainCheckId);
		searchCondition.put("bookId", bookId);
		searchCondition.put("roomCharacter", roomCharacter);
		// 查询出所有房间特征的特征数，求和
		// roomsSearchVo.setFangjiantezheng("13");
		AjaxJson j = new AjaxJson();
		Map map = new HashMap();
		boolean ipflag = parameterSvc.GetIPFlag();
		if (ipflag) {
			searchCondition.put("roomStat",Constant.CLEAN_CHECK_EMPTY_ROOM);
		} else {
			searchCondition.put("roomStat",Constant.CLEAR_EMPTY);
		}
		
		//此订单选定的房间
		if(StringUtils.isNotEmpty(remainCheckId)){
			List<Rooms> list = roomsSvc.getRoomsByCheckId(remainCheckId,remainReachDate,remainLeaveDate);
			map.put("savedRooms", list);
		}
		List<Rooms> roomsList = new ArrayList<Rooms>();
		List<Rooms> roomsList1 = roomsSvc.getRoomsList(searchCondition);
		for (Rooms rooms : roomsList1) {
			Rooms r = new Rooms();
			BeanUtils.copyProperties(rooms, r);
			HroomType hroomType = hRoomTypeSvc.get("codeId",rooms.getRoomType().trim());
			//r.setRoomType(hroomType.getCodeNamec());
			r.setRoomTypeName(hroomType.getCodeNamec());
			roomsList.add(r);
		}
		map.put("roomsList", roomsList);
		//-----------
		//找出所有楼层
		List<Map<String,Object>> floorMapList = new ArrayList<Map<String,Object>>();
		for(int i=0;i<roomsList.size();i++){
			Rooms room = (Rooms) roomsList.get(i);
			String floorNo = room.getFloorNo().trim();
			if(floorMapList.isEmpty()){
				Map<String,Object> floorMap=new HashMap<String,Object>();
				floorMap.put("floorNo", floorNo);
				floorMap.put("rooms", new ArrayList<Rooms>());
				floorMapList.add(floorMap);
			}else{
				boolean canPut=true;
				//检索楼层是否已经存在
				for(Map<String,Object> tmpMap:floorMapList){
					String floorValu=(String)tmpMap.get("floorNo");
					if(floorValu.trim().equals(floorNo)){
						canPut=false;
						break;
					}
				}
				if(canPut){
					Map<String,Object> floorMap=new HashMap<String,Object>();
					floorMap.put("floorNo", floorNo);
					floorMap.put("rooms", new ArrayList<Rooms>());
					floorMapList.add(floorMap);
					
				}
			}
		}
		for(int i=0;i<roomsList.size();i++){
			Rooms room = (Rooms) roomsList.get(i);
			String room_type = room.getRoomType();
			HroomType hroomType = hroomTypeSvc.get("codeId", room_type);
			String roomTypeName = hroomType.getCodeNamec();
			room.setRoomTypeName(roomTypeName);
			String floorNo = room.getFloorNo().trim();
			for(Map<String,Object> tmpMap:floorMapList){
				String floorValu=(String)tmpMap.get("floorNo").toString();
				if(floorValu.equals(floorNo)){//找到楼层
					((List)tmpMap.get("rooms")).add(room);
				}
			}

		}
		Gson gson = new Gson();
		String ja = gson.toJson(floorMapList);
		System.out.println(ja);
		j.setExtra(floorMapList);
		//-----------
	
		j.setAttributes(map);
		j.setObj(map);
		return j;
	}
	
	/**
	 * 保存房间设置
	 * @param roomCharacter
	 * @param checkId
	 * @return
	 */
	@RequestMapping("/bookroom/saveRoomFeature.do")
	@ResponseBody
	public AjaxJson saveRoomCharacter(String roomCharacter,Integer checkId){
		AjaxJson j = new AjaxJson();
		if(checkId!=null){
			if(StringUtils.isNotEmpty(roomCharacter)){
				String[] rcs = roomCharacter.split(",");
				Long l =1l;//权值
				Long base = 0l;//基础值
				for (String string : rcs) {
					long thisVal = Long.parseLong(string);
					thisVal = l<<thisVal;
					base += thisVal;
				}
				Guestdoc gd = guestdocSvc.get("checkId", checkId);
				if(gd!=null){
					gd.setRoomCharacter(base);
					guestdocSvc.update(gd);
				}
				j.setObj(gd);
			}else{
				Guestdoc gd = guestdocSvc.get("checkId", checkId);
				if(gd!=null){
					gd.setRoomCharacter(new Long(0));
					guestdocSvc.update(gd);
				}
				j.setObj(gd);
			}
		}else{
			j.setSuccess(false);
		}
		return j;
	}

}
