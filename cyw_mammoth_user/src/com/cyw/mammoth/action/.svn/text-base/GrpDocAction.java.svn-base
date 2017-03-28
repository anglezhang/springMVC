package com.cyw.mammoth.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.cyw.mammoth.auth.AppBaseCfg;
import com.cyw.mammoth.bean.BPaied;
import com.cyw.mammoth.bean.GrpDoc;
import com.cyw.mammoth.bean.Guestdoc;
import com.cyw.mammoth.bean.Hcodes;
import com.cyw.mammoth.bean.Hconsume;
import com.cyw.mammoth.bean.Hcountry;
import com.cyw.mammoth.bean.Hexchange;
import com.cyw.mammoth.bean.Hfolk;
import com.cyw.mammoth.bean.Hsettle;
import com.cyw.mammoth.bean.Operator;
import com.cyw.mammoth.dao.GrpDocDao;
import com.cyw.mammoth.dao.RoomsDao;
import com.cyw.mammoth.service.GrpDocSvc;
import com.cyw.mammoth.service.GstBillSvc;
import com.cyw.mammoth.service.GuestdocSvc;
import com.cyw.mammoth.service.HcodesSvc;
import com.cyw.mammoth.service.HconsumeSvc;
import com.cyw.mammoth.service.HcountrySvc;
import com.cyw.mammoth.service.HexchangeSvc;
import com.cyw.mammoth.service.HfolkSvc;
import com.cyw.mammoth.service.HsettleSvc;
import com.cyw.mammoth.service.ParameterSvc;
import com.cyw.mammoth.vo.AjaxJson;
import com.cyw.mammoth.vo.GroupGuestUpdateVO;
import com.cyw.mammoth.vo.GuestSearchVO;
import com.cyw.mammoth.vo.GuestSourceVO;
import com.google.gson.Gson;

/**
 * <一句话功能简述>
 * <功能详细描述>
 * 
 * @author  litiangang@cyw.so
 * @version  v-1.0
 * @see  GrpDocAction.java
 * @since  cyw-1.0
 */
@Controller
public class GrpDocAction extends BaseAction {
	@Autowired
	private GrpDocSvc grpDocSvc;
	@Autowired
	private GuestdocSvc guestdocSvc;
	@Autowired
	private ParameterSvc parameterSvc;
	@Autowired
	private GrpDocDao grpDocDao;
	@Autowired
	private RoomsDao roomsDao;
	
	/** 散团互转
	 * <功能详细描述>
	 * @return
	 * @see GrpDocAction.java
	 */
	@RequestMapping("rooms/groupToChange.do")
	public String groupToChange(Model model){
		return "rooms/group_tochange";
	}
	/** 散团列表查询
	 * <功能详细描述>
	 * @param resMap
	 * @return
	 * @see GrpDocAction.java
	 */
	@SuppressWarnings({ "unused","unchecked", "rawtypes" })   
	@RequestMapping(value="rooms/grp_tochange1.do")
	public @ResponseBody String grp_tochange1(@RequestParam Map resMap){
		Map<String, Object> dataMap = new HashMap<String, Object>();
		dataMap.put("issuccess", true);
		Gson gson = new Gson();
		String billb_id = "";
		int bill_num = 0;
		Map map = null;
		String grp_id_name1 = resMap.get("grp_id_name1")+"";//团代码/团名
		String roomid1 = resMap.get("roomid1")+"";//相关房号
		if(!roomid1.equals("")){
			if(!roomsDao.isExistsRooms(roomid1)){
				dataMap.put("issuccess", false);
				dataMap.put("msg", "系统中不存在房号为："+roomid1+"的在房间信息。");
				return gson.toJson(dataMap);
			}
		}
		if(!grp_id_name1.equals("") && !grp_id_name1.equals("0")){
			List<GrpDoc> grpDocList = grpDocDao.getGrpDocListByNameOrId(grp_id_name1);
			if(grpDocList == null || grpDocList.size() == 0){
				dataMap.put("issuccess", false);
				dataMap.put("msg", "系统中不存在团代码/团名为："+grp_id_name1+"的在住团信息。");
				return gson.toJson(dataMap);
			}
		}
		if(grp_id_name1.length()!=0&&roomid1.length()!=0){
			resMap.put("grp_id_name1","");
		}
		List list = grpDocSvc.getgrpdoc(resMap);
		List temList = new ArrayList();
		if(list.size()!=0){
			Map tempMap = (Map)list.get(0);
			String grp_chkid = tempMap.get("grp_chkid")+"";
			bill_num = grpDocSvc.get_Biil_id_num(Integer.parseInt(tempMap.get("billb_id").toString()));
			if(grp_chkid.length()!=0){
				int check_id = Integer.parseInt(grp_chkid);
				if(0!=check_id){
					temList = grpDocSvc.getSelectGrpDoc(check_id);
				}
			}
		}
		
		dataMap.put("dlist", list);
		dataMap.put("bill_num", bill_num);
		dataMap.put("grpIdList", temList);
		return gson.toJson(dataMap);
	}
	
	/** 散团互转业务处理
	 * <功能详细描述>
	 * @param changeData
	 * @param changeCondLft
	 * @param changeCondRight
	 * @return
	 * @see GrpDocAction.java
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" }) 
	@RequestMapping(value="rooms/grp_changetodata.do")
	public @ResponseBody Map grp_changetodata(@RequestParam String changeData,String changeCondLft,String changeCondRight){
		Map dataMap = new HashMap();
		dataMap.put("isSuccess", true);
		
		//返回值
		//夜审判断
		boolean flag = parameterSvc.getNightAuditState();
		//flag为true正在夜审
		if(flag){
			dataMap.put("isSuccess", false);
			dataMap.put("msg", "正在夜审不能进行续住操作！");
			return dataMap;
		}
		
		JSONArray changeDataObj = JSONArray.parseArray(changeData);
		//System.out.println("改变的数据"+changeDataObj);
		
		JSONArray changeCondLftObj = JSONArray.parseArray(changeCondLft);
		JSONObject changLftObj = (JSONObject)changeCondLftObj.get(0);//左侧条件
		//System.out.println("左侧的条件："+changLftObj);
		
		JSONArray changeCondRightObj = JSONArray.parseArray(changeCondRight);
		JSONObject changRightObj = (JSONObject)changeCondRightObj.get(0);//右侧条件
		//System.out.println("右侧的条件："+changRightObj);
		for (int i = 0; i < changeDataObj.size(); i++) {
			JSONObject dataObj = (JSONObject) changeDataObj.get(i);
			//左边变到右边
			if("-1".equals(dataObj.get("flag")+"")&&"left".equals(dataObj.get("flagSign"))){
				//得到被修改的这条数据的B账号ID
				List list = grpDocSvc.getBillIdList(Integer.parseInt(dataObj.get("check_id")+""));
				//如果ID账号的所有账目条数为0
				if(0==list.size()){//********************
//					guestdocSvc.updataChangeGrpDoc(changeDataObj,changLftObj,changRightObj);
				}else{
					String str = "";
					List BillIdList = grpDocSvc.getGstCreditAuth(Integer.parseInt(dataObj.get("check_id")+""));
					Map mapNorm = (Map)BillIdList.get(0);
					Map map = null;
					
					for (int j = 0; j < list.size(); j++) {
						map = (Map)list.get(j);
						str+= "单号为"+(map.get("acco_id")==null?"":map.get("acco_id"))+","+"房号"+(map.get("bill_room_id")==null?"":map.get("bill_room_id"))+"项目为"+(map.get("cons_namec")==null?"":map.get("cons_namec"))+""+(map.get("setl_namec")==null?"":map.get("setl_namec"))+"("+(map.get("cons_id")==null?"":map.get("cons_id"))+")"+"金额为"+(map.get("balance")==null?"":map.get("balance"))+"\n"+"操作人号码"+(map.get("oper_id")==null?"":map.get("oper_id"))+"操作时间"+(map.get("oper_time")==null?"":map.get("oper_time"));
					}
					if(mapNorm.size()!=0){
						str+= "有一笔预授权未处理,持卡人"+(mapNorm.get("credit_holder")==null?"":mapNorm.get("credit_holder"))+"授权金额为"+(mapNorm.get("balance")==null?"":mapNorm.get("balance"))+"添加人号码为"+(mapNorm.get("oper_id")==null?"":mapNorm.get("oper_id"));
					}
					String stmsg = "你所转的人为"+map.get("gst_namec")+"房间号为"+map.get("room_id")+";";
					dataMap.put("msg", stmsg+str);
					dataMap.put("isSuccess", false);
					return dataMap;
				}
			}
			//右边变到左边
			if("-1".equals(dataObj.get("flag")+"")&&"right".equals(dataObj.get("flagSign"))){
				//得到被修改的这条数据的B账号ID
				List list = grpDocSvc.getBillIdList(Integer.parseInt(dataObj.get("check_id")+""));
				//如果ID账号的所有账目条数为0
				if(0==list.size()){//********************
//					guestdocSvc.updataChangeGrpDoc(changeDataObj,changLftObj,changRightObj);
				}else{
					String str = "";
					List BillIdList = grpDocSvc.getGstCreditAuth(Integer.parseInt(dataObj.get("check_id")+""));
					Map mapNorm = (Map)BillIdList.get(0);
					Map map = null;
					
					for (int j = 0; j < list.size(); j++) {
						map = (Map)list.get(j);
						str+= "单号为"+(map.get("acco_id")==null?"":map.get("acco_id"))+","+"房号"+(map.get("bill_room_id")==null?"":map.get("bill_room_id"))+"项目为"+(map.get("cons_namec")==null?"":map.get("cons_namec"))+""+(map.get("setl_namec")==null?"":map.get("setl_namec"))+"("+(map.get("cons_id")==null?"":map.get("cons_id"))+")"+"金额为"+(map.get("balance")==null?"":map.get("balance"))+"\n"+"操作人号码"+(map.get("oper_id")==null?"":map.get("oper_id"))+"操作时间"+(map.get("oper_time")==null?"":map.get("oper_time"));
					}
					if(mapNorm.size()!=0){
						str+= "有一笔预授权未处理,持卡人"+(mapNorm.get("credit_holder")==null?"":mapNorm.get("credit_holder"))+"授权金额为"+(mapNorm.get("balance")==null?"":mapNorm.get("balance"))+"添加人号码为"+(mapNorm.get("oper_id")==null?"":mapNorm.get("oper_id"));
					}
					String stmsg = "你所转的人为"+map.get("gst_namec")+"房间号为"+map.get("room_id")+";";
					dataMap.put("msg", stmsg+str);
					dataMap.put("isSuccess", false);
					return dataMap;
				}
			}
			
		}
		guestdocSvc.updataChangeGrpDoc(changeDataObj,changLftObj,changRightObj);
		return dataMap;
	}
	
	/**
	 * 获取团队详情
	 * <功能详细描述>
	 * @param grpCheckId
	 * @return
	 * @see GrpDocAction.java
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" }) 
	@RequestMapping(value="group/getGrpdocDetail.do")
	@ResponseBody
	public String getGrpdocDetail(Model model,Integer grpCheckId){
		Map resultMap = new HashMap();
		Map grpDocDetail = null;
		List guestList = null;
		GuestSearchVO searchVO = new GuestSearchVO();
		int roomNum = 0;
		String roomId = null;
		String tag =null;
		try {
			//团队资料
			grpDocDetail = grpDocSvc.getGrpDocDetail(grpCheckId);
			String bookStat = grpDocSvc.get(grpCheckId).getBookStat();
			grpDocDetail.put("bookStat", bookStat);
			//获取团队下的住客资料
			if(grpCheckId!=null){
				searchVO.setGrpChkid(grpCheckId.toString());
				guestList = guestdocSvc.getGuestDocList(searchVO);
			}
			if(guestList!=null && guestList.size()>0){
				for(int i=0;i<guestList.size();i++){
					tag = ((Map)guestList.get(i)).get("room_id").toString();
					if(!tag.equals(roomId)){
						roomNum++;
					}
					roomId = tag;
				}
			}
			//返回map
			resultMap.put("grpDocDetail", grpDocDetail);
			resultMap.put("guestList", guestList);
			resultMap.put("roomNum", roomNum);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return JSON.toJSONStringWithDateFormat(resultMap, "yyyy-MM-dd");
	}
	
	
	/**
	 * 团队办理入住
	 * <功能详细描述>
	 * @param grpCheckId
	 * @return
	 * @see GrpDocAction.java
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" }) 
	@RequestMapping(value="group/grpCheckIn.do")
	@ResponseBody
	public String grpCheckIn(HttpServletRequest request,
			HttpServletResponse response,String check_id,String with_id,String roomIds,String bookRoomId){
		Map resultMap = new HashMap();
		Map grpDocDetail = null;
		int roomNum = 0;
		Map map = null;
		List roomList = null;
		List guestList = null;
		String roomId = null;
		try {
			Operator operator=AppBaseCfg.getOperator();
			JSONArray array = JSONObject.parseArray(roomIds);
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
			String newCheckId = grpDocSvc.getGrpDocDetailIn(check_id, array, operator.getOperId(), with_id);
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
	/**
	 * 获取团队房价列表
	 * <功能详细描述>
	 * @param grpCheckId
	 * @return
	 * @see GrpDocAction.java
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" }) 
	@RequestMapping(value="group/getGrpRoomPriceList.do")
	@ResponseBody
	public String getGrpRoomPriceList(Integer grpCheckId){
		Map map = new HashMap();
		GuestSearchVO searchVO = new GuestSearchVO();
		List guestList = null;
		List roomList = new ArrayList();
		try {
			if(grpCheckId!=null){
				searchVO.setGrpChkid(grpCheckId.toString());
				guestList = guestdocSvc.getGuestDocList(searchVO);
				if(guestList!=null && guestList.size()>0){
					String checkId = null;
					String roomId = null;
					Map guestMap = null;
					for(int i=0;i<guestList.size();i++){
						guestMap = (Map)guestList.get(i);
						roomId = guestMap.get("room_id").toString();
						if(!roomList.contains(roomId)){
							roomList.add(roomId);
						}
					}
				}
			}
			map.put("roomList", roomList);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return JSON.toJSONStringWithDateFormat(map, "yyyy-MM-dd");
	}
	
	/**
	 * <一句话功能简述>
	 * <功能详细描述>
	 * @param grpDoc
	 * @return
	 * @see GrpDocAction.java
	 */
	@RequestMapping(value="group/updateGrpdocAndGuest.do")
	@ResponseBody
	public String updateGrpdocAndGuest(GrpDoc grpDoc,GroupGuestUpdateVO guestUpdateVO,BPaied bPaied){
		String operId = AppBaseCfg.getOperator().getOperId();
		AjaxJson ajaxJson = new AjaxJson();
		try {
			grpDocSvc.updateGrpdocAndGuest(grpDoc, guestUpdateVO, bPaied, operId);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			ajaxJson.setSuccess(false);
			ajaxJson.setMsg("操作失败");
		}
		return JSON.toJSONString(ajaxJson);
	}
	
	/**
	 * 获取团队详情
	 * <功能详细描述>
	 * @param grpCheckId
	 * @return
	 * @see GrpDocAction.java
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" }) 
	@RequestMapping(value="group/getGrpdocDetailByRoom.do")
	@ResponseBody
	public String getGrpdocDetail(String roomId){
		Map resultMap = new HashMap();
		Map grpDocDetail = null;
		List guestList = null;
		GuestSearchVO searchVO = new GuestSearchVO();
		int roomNum = 0;
		String tag =null;
		String room_id = null;
		try {
			String[] props = {"roomId","chkStat"};
			String[] vals = {roomId,"I"};
			List<Guestdoc> guests = guestdocSvc.getList(props, vals);
			if(guests==null || guests.size()==0) return null;
			Integer grpCheckId = guests.get(0).getGrpChkid();
			//团队资料
			grpDocDetail = grpDocSvc.getGrpDocDetail(grpCheckId);
			//获取团队下的住客资料
			if(grpCheckId!=null){
				searchVO.setGrpChkid(grpCheckId.toString());
				guestList = guestdocSvc.getGuestDocList(searchVO);
			}
			if(guestList!=null && guestList.size()>0){
				for(int i=0;i<guestList.size();i++){
					tag = ((Map)guestList.get(i)).get("room_id").toString();
					if(!tag.equals(room_id)){
						roomNum++;
					}
					room_id = tag;
				}
			}
			//返回map
			resultMap.put("grpDocDetail", grpDocDetail);
			resultMap.put("guestList", guestList);
			resultMap.put("roomNum", roomNum);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return JSON.toJSONStringWithDateFormat(resultMap, "yyyy-MM-dd");
	}
	
	/**
	 * 获取团队详情
	 * <功能详细描述>
	 * @param grpCheckId
	 * @return
	 * @see GrpDocAction.java
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" }) 
	@RequestMapping(value="group/getGrpdocDetailByBill.do")
	@ResponseBody
	public String getGrpdocDetailByBillId(Integer billId){
		Map resultMap = new HashMap();
		Map grpDocDetail = null;
		List guestList = null;
		GuestSearchVO searchVO = new GuestSearchVO();
		int roomNum = 0;
		String tag =null;
		String roomId = null;
		try {
			String[] props = {"billId","chkStat"};
			Object[] vals = {billId,"I"};
			GrpDoc grpDoc = grpDocSvc.get(props, vals);
			if(grpDoc == null) return null;
			Integer grpCheckId = grpDoc.getCheckId();
			//团队资料
			grpDocDetail = grpDocSvc.getGrpDocDetail(grpCheckId);
			//获取团队下的住客资料
			if(grpCheckId!=null){
				searchVO.setGrpChkid(grpCheckId.toString());
				guestList = guestdocSvc.getGuestDocList(searchVO);
			}
			if(guestList!=null && guestList.size()>0){
				for(int i=0;i<guestList.size();i++){
					tag = ((Map)guestList.get(i)).get("room_id").toString();
					if(!tag.equals(roomId)){
						roomNum++;
					}
					roomId = tag;
				}
			}
			//返回map
			resultMap.put("grpDocDetail", grpDocDetail);
			resultMap.put("guestList", guestList);
			resultMap.put("roomNum", roomNum);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return JSON.toJSONStringWithDateFormat(resultMap, "yyyy-MM-dd");
	}
	
}
