package com.cyw.mammoth.service.impl;


import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.hibernate.HibernateException;
import org.hibernate.NonUniqueResultException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.cyw.common.base.dao.BaseDao;
import com.cyw.common.base.service.impl.BaseSvcImpl;
import com.cyw.common.util.DateCompare;
import com.cyw.common.util.DateUtils;
import com.cyw.common.util.StrUtils;
import com.cyw.mammoth.action.BaseAction;
import com.cyw.mammoth.auth.AppBaseCfg;
import com.cyw.mammoth.auth.ShiroDbAuthRealm.ShiroUser;
import com.cyw.mammoth.bean.BPaied;
import com.cyw.mammoth.bean.Bills;
import com.cyw.mammoth.bean.BookRoom;
import com.cyw.mammoth.bean.BookRoomCheckin;
import com.cyw.mammoth.bean.GrpDoc;
import com.cyw.mammoth.bean.GstBill;
import com.cyw.mammoth.bean.GstCreditAuth;
import com.cyw.mammoth.bean.Guestdoc;
import com.cyw.mammoth.bean.Hconsume;
import com.cyw.mammoth.bean.Hcountry;
import com.cyw.mammoth.bean.Hfolk;
import com.cyw.mammoth.bean.HgstOriType;
import com.cyw.mammoth.bean.HroomType;
import com.cyw.mammoth.bean.Hsettle;
import com.cyw.mammoth.bean.Noguest;
import com.cyw.mammoth.bean.Operator;
import com.cyw.mammoth.bean.Parameter;
import com.cyw.mammoth.bean.RoomNum;
import com.cyw.mammoth.bean.Rooms;
import com.cyw.mammoth.bean.RoomsDiary;
import com.cyw.mammoth.dao.BPaiedDao;
import com.cyw.mammoth.dao.BillsDao;
import com.cyw.mammoth.dao.BookRoomCheckInDao;
import com.cyw.mammoth.dao.BookRoomDao;
import com.cyw.mammoth.dao.GrpDocDao;
import com.cyw.mammoth.dao.GstBillDao;
import com.cyw.mammoth.dao.GstCreditAuthDao;
import com.cyw.mammoth.dao.GuestdocDao;
import com.cyw.mammoth.dao.HconsumeDao;
import com.cyw.mammoth.dao.HcountryDao;
import com.cyw.mammoth.dao.HfolkDao;
import com.cyw.mammoth.dao.HgstOriTypeDao;
import com.cyw.mammoth.dao.HsettleDao;
import com.cyw.mammoth.dao.NoguestDao;
import com.cyw.mammoth.dao.ParameterDao;
import com.cyw.mammoth.dao.RoomNumDao;
import com.cyw.mammoth.dao.RoomsDao;
import com.cyw.mammoth.dao.RoomsDiaryDao;
import com.cyw.mammoth.dao.TADocDao;
import com.cyw.mammoth.service.BookRoomSvc;
import com.cyw.mammoth.service.GuestdocSvc;
import com.cyw.mammoth.service.ParameterSvc;
import com.cyw.mammoth.vo.AjaxJson;
import com.cyw.mammoth.vo.BookRoomCheckInVO;
import com.cyw.mammoth.vo.BookRoomSearchVo;
import com.cyw.mammoth.vo.BookStateEnum;
import com.cyw.mammoth.vo.GroupSearchVO;
import com.cyw.mammoth.vo.GuestDetailVo;
import com.cyw.mammoth.vo.GuestDocModel;
import com.cyw.mammoth.vo.GuestSearchVO;
import com.cyw.mammoth.vo.WebRoomSearchVO;

import freemarker.template.utility.DateUtil;
/**
 * <一句话功能简述>
 * <功能详细描述>
 * 
 * @author  litiangang@cyw.so
 * @version  v-1.0
 * @see  GuestdocSvcImpl.java
 * @since  cyw-1.0
 */
@SuppressWarnings({ "unchecked", "rawtypes" }) 
@Service
@Transactional
public class GuestdocSvcImpl extends BaseSvcImpl<Guestdoc, Integer> implements GuestdocSvc {
	
	private Logger log = LoggerFactory.getLogger(GuestdocSvcImpl.class);
	@Autowired
	GuestdocDao dao;
	@Autowired
	ParameterDao paramDao;
	@Autowired
	GstBillDao gstBillDao;
	@Autowired
	private BookRoomDao bookRoomDao;
	@Autowired
	BillsDao billsDao;
	@Autowired
	BPaiedDao paiedDao;
	@Autowired
	GstCreditAuthDao creditAuthDao;
	
	@Autowired
	HconsumeDao hconsumeDao; 
	
	@Autowired
	private HsettleDao hsettleDao;
	
	@Autowired
	private RoomsDao roomsDao;
	/**
	 * @描述：房间存量
	 * */
	@Autowired
	private RoomNumDao roomNumDao;
	
	@Autowired
	private HgstOriTypeDao hgstOriTypeDao;
	/**
	 * 团队信息dao
	 * */
	@Autowired
	private GrpDocDao grpDocDao;
	
	@Autowired
	private ParameterSvc parameterSvc;
	@Autowired
	private RoomsDiaryDao roomsDiaryDao;
	@Autowired
	private BPaiedDao bPaiedDao;
	@Autowired
	public void setBaseDao(GuestdocDao dao) {
		super.setBaseDao(dao);
	}
	@Resource
	private  TADocDao taDocDao;
	@Resource
	private BookRoomSvc bookRoomSvc;
	
	@Autowired
	private HcountryDao hcountryDao;
	@Autowired
	private HfolkDao hfolkDao;
	@Autowired
	private BookRoomCheckInDao bookRoomCheckInDao;
	@Autowired
	private NoguestDao noguestDao;
	/*@Override
	public List<Guestdoc> getBookRoomList(BookRoomSearchVo searchVo) {
		List<Guestdoc> newlist= new ArrayList<Guestdoc>();
		List<Guestdoc> list=dao.getBookRoomList(searchVo);
		for(Guestdoc guestDoc:list){
			if(StringUtils.isNotEmpty(guestDoc.getCompId())){
				TaDoc taDoc= taDocDao.get("compId", guestDoc.getCompId());
				if(taDoc!=null){
					guestDoc.setTaName(taDoc.getNamec());
				}
				Integer bookNum=bookRoomSvc.getNumByCheckId(guestDoc.getCheckId());
				guestDoc.setBookNum(bookNum);
			}
				newlist.add(guestDoc);	
		}
		return newlist;
	}*/
	
	/**
	 * 修改散客预定返回值
	 * @author yaochenglong
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<GuestDocModel> getBookRoomList(BookRoomSearchVo searchVo) {
		List<GuestDocModel> newlist= new ArrayList<GuestDocModel>();
		List list = dao.getBookRoomList(searchVo);
		GuestDocModel guestDocModel ;
		for (int i = 0; i < list.size(); i++) {
			guestDocModel = new GuestDocModel();
			Map map = (Map) list.get(i);
			guestDocModel.setCheckId(Integer.parseInt(map.get("check_id").toString()));
			guestDocModel.setBookList(map.get("book_list").toString());
			guestDocModel.setGstNamee(map.get("gst_namee").toString());
			guestDocModel.setGstNamec(map.get("gst_namec").toString());
			guestDocModel.setBookStat(map.get("book_stat").toString());
			guestDocModel.setBookRoomId(Integer.parseInt(map.get("book_room_id").toString()));
			guestDocModel.setRoomtypeId(map.get("roomtype_id").toString());
			try {
				//System.out.println(map.get("reach_date").toString());
				//System.out.println(map.get("leave_date").toString());
				//System.out.println(DateUtils.dateFormat2.parse(map.get("reach_date").toString()));
				//System.out.println(DateUtils.dateFormat2.parse(map.get("leave_date").toString()));
				guestDocModel.setBreachDate(DateUtils.dateFormat2.parse(map.get("reach_date").toString()));
				guestDocModel.setBleaveDate(DateUtils.dateFormat2.parse(map.get("leave_date").toString()));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			guestDocModel.setBookNum(Integer.parseInt(map.get("book_num").toString()));
			guestDocModel.setSaveNum(Integer.parseInt(map.get("save_num").toString()));
			guestDocModel.setReachNum(Integer.parseInt(map.get("reach_num").toString()));
			//guestDocModel.setcomap.get("code_id");
			guestDocModel.setCodeNamec(map.get("code_namec").toString());
			//map.get("code_namee");
			newlist.add(guestDocModel);
			
		}
		/*for (Iterator iterator = list.iterator(); iterator.hasNext();) {
			Object[] objects = (Object[]) iterator.next();
			Guestdoc guestdoc = (Guestdoc) objects[0];
			BookRoom bookroom = (BookRoom) objects[1];
			HroomType ht = (HroomType)objects[2];
			guestDocModel = new GuestDocModel();
			BeanUtils.copyProperties(guestdoc, guestDocModel);
			BeanUtils.copyProperties(bookroom, guestDocModel);
			BeanUtils.copyProperties(ht, guestDocModel);
			
			BookRoom br = bookRoomDao.get(bookroom.getId());
			System.out.println(guestdoc.getCheckId()+":");
			System.out.print("br id=" + br.getId() + ", getReachDate=" + br.getReachDate()+" br getCheckId=" + br.getCheckId());
			System.out.println(br.getLeaveDate());
			guestDocModel.setBleaveDate(br.getLeaveDate());
			guestDocModel.setBreachDate(br.getReachDate());
			guestDocModel.setBroomPrice(br.getRoomPrice());
			newlist.add(guestDocModel);
			
		}*/
		return newlist;
	}
	
	@Override
	public List<GuestDocModel> getBookAccommodate(BookRoomSearchVo searchVo) {
		List<GuestDocModel> newlist= new ArrayList<GuestDocModel>();
		List list=dao.getBookAccommodate(searchVo);
		GuestDocModel guestDocModel ;
		for (Iterator iterator = list.iterator(); iterator.hasNext();) {
			Object[] objects = (Object[]) iterator.next();
			Guestdoc guestdoc = (Guestdoc) objects[0];
			BookRoom bookroom = (BookRoom) objects[1];
			HroomType ht = (HroomType)objects[2];
			guestDocModel = new GuestDocModel();
			BeanUtils.copyProperties(guestdoc, guestDocModel);
			BeanUtils.copyProperties(bookroom, guestDocModel);
			BeanUtils.copyProperties(ht, guestDocModel);
			guestDocModel.setBleaveDate(bookroom.getLeaveDate());
			guestDocModel.setBreachDate(bookroom.getReachDate());
			guestDocModel.setBroomPrice(bookroom.getRoomPrice());
			newlist.add(guestDocModel);
			
		}
		return newlist;

	}
	
	
	@Override
	public Guestdoc saveForBookRoom(Guestdoc guestdoc,List<BookRoom> brLists,String roomIds,String currBookRoomId) throws HibernateException{
		List<Date> reachDateList = new ArrayList<Date>();
		List<Date> leaveDateList = new ArrayList<Date>();
		String operId=AppBaseCfg.getOperator().getOperId();
			guestdoc.setBookOperid(operId);;//预订登记人
			guestdoc.setBookTime(new Date());;//预订登记时间
			guestdoc.setLastOper(operId);//最后修改人
			guestdoc.setLastTime(new Date());//最后修改时间
		
		guestdoc.setBookStat(BookStateEnum.NO_Confirm.value);
		//从参数表中获取登记号
		Parameter parama= paramDao.get(1);
		//插入登记号 
		Integer newCheckId=parama.getPara1()+1;
		guestdoc.setCheckId(newCheckId);
		//产生B账号
		Parameter paramb= paramDao.get(2);
		Integer biilBId=paramb.getPara1()+1;
		guestdoc.setBillbId(biilBId);
		guestdoc.setWithId(biilBId);
		//产生a账号
		Integer biilAId=paramb.getPara1()+2;
		guestdoc.setBillaId(biilAId);
		//---产生B账号
		//修改限额
		Double Alimit=0.00d;
		GstBill billA = new GstBill(biilAId, 0.0, 0.0, null, null, true, null, null, Alimit);
		GstBill billB = gstBillDao.get(guestdoc.getBillbId());
		parama.setPara1(newCheckId);
		paramDao.evict(parama);
		paramDao.merge(parama);
		paramb.setPara1(biilAId);
		paramDao.evict(paramb);
		paramDao.merge(paramb);
		Double Blimit=0.00d;
		try{
			Blimit=new Double(guestdoc.getBiilbLimit());
		}catch(Exception e){
			e.printStackTrace();
		}
		if(billB == null){
			billB = new GstBill(biilBId, 0.0, 0.0, null, null, true, null, null, Blimit);
			gstBillDao.merge(billB);
		}else{
			dao.updateLimit(Blimit, biilBId);
		}
		gstBillDao.merge(billA);
		//---
		
		if(brLists!=null && brLists.size()>0){
			for (BookRoom bookRoom : brLists) {
				reachDateList.add(bookRoom.getReachDate());
				leaveDateList.add(bookRoom.getLeaveDate());
			}
			Collections.sort(reachDateList, new DateCompare());
			Collections.sort(leaveDateList, new DateCompare());
			String strDate=com.cyw.common.util.DateUtil.convertDate2String(reachDateList.get(0), com.cyw.common.util.DateUtil.defaultDatePattern);
			/*if(!StrUtils.isValidString(guestdoc.getReachtime())){
				guestdoc.setReachtime("00:00");
			}*/
			//String reachDateTime=strDate+" "+guestdoc.getReachtime();
			Date reachDate=com.cyw.common.util.DateUtil.convertStringToDate(com.cyw.common.util.DateUtil.defaultDatePattern,strDate);
			guestdoc.setReachDate(reachDate);
			guestdoc.setLeaveDate(leaveDateList.get(leaveDateList.size()-1));
		}
		guestdoc.setChangeRate(true);
		dao.save(guestdoc);
		
/*		param.setPara1(param.getPara1()+1);
		//更新参数表登记号
		paramDao.update(param);
		paramb.setPara1(paramb.getPara1()+2);
		paramDao.update(paramb);*/
		
		Date reacheD = null;
		Date leaveD = null;
		String roomTypeId = "";
		//保存bookRoom订房表   //插入bookroom表的同时也需要插入
		if(brLists!=null && brLists.size()>0){
			for (BookRoom bookRoom : brLists) {
				if(StringUtils.isNotEmpty(currBookRoomId) && bookRoom!=null && bookRoom.getCheckId()!=null){
					if(bookRoom.getBookRoomId() == Integer.parseInt(currBookRoomId)){
						reacheD = bookRoom.getReachDate();
						if(!StrUtils.isValidString(bookRoom.getReachTime())){
							bookRoom.setReachTime(new Date().getHours()+":"+new Date().getMinutes());
						}
						reacheD = DateUtils.getDateAddStr(bookRoom.getReachDate(),bookRoom.getReachTime()+":00");
						leaveD = bookRoom.getLeaveDate();
						roomTypeId = bookRoom.getRoomtypeId();
						//删除之前留过的房间,重新留房
						/*roomNumDao.updateBookRoomByChekcIdAndRoomType(guestdoc.getCheckId(), roomTypeId,roomIds,currBookRoomId,DateUtils.dateFormat.format(reacheD),DateUtils.dateFormat.format(leaveD));
						//保存房间存量表
						if(StringUtils.isNotEmpty(roomIds)){
							String[] ids = roomIds.split(",");
							for (int i = 0; i < ids.length; i++) {
								RoomNum rm = new RoomNum();
								rm.setRoomChkid(0);
								rm.setCheckId(guestdoc.getCheckId());
								rm.setRoomId(ids[i]);
								rm.setReachDate(reacheD);
								rm.setLeaveDate(leaveD);
								rm.setFlag(0);
								rm.setBookId(bookRoom.getBookRoomId());
								rm.setStatus(0);
								rm.setKeepFlag("0");
								//roomNumDao.flush();
								roomNumDao.clear();
								roomNumDao.save(rm);
							}
						}*/
					}
					//update code
					//update
					BookRoom br = bookRoomDao.get("bookRoomId", bookRoom.getBookRoomId());
					com.cyw.common.util.bean.BeanUtils.beanShallowCopy(br, bookRoom);
					br.setUpdateTimes(br.getUpdateTimes()+1);
					br.setModifyOper(operId);
					br.setModifyTime(new Date());
					bookRoomDao.update(br);
				}else{
					bookRoom.setBookRoomId(0);
					bookRoom.setCheckId(guestdoc.getCheckId());
					bookRoom.setStatus(0);
					bookRoom.setUpdateTimes(0);
					//update code
					if(bookRoom.getSaveNum()==null){
						bookRoom.setSaveNum(0);
					}
					if(bookRoom.getBookNum()==null){
						bookRoom.setBookNum(0);
					}
					if(bookRoom.getReachNum()==null){
						bookRoom.setReachNum(0);
					}
					bookRoom.setBookRoomId(0);
					bookRoom.setBookOper(operId);
					bookRoom.setBookTime(new Date());
					Integer id= bookRoomDao.save(bookRoom);
					BookRoom entity = bookRoomDao.get(id);
					entity.setBookRoomId(id);
					bookRoomDao.update(entity);
				}
			}
		}
		
		return guestdoc;
	}
	//更新散客预定
	/* (non-Javadoc)
	 * 
	 */
	@Override
	public Guestdoc updateForBookRoom(Guestdoc guestdoc,List<BookRoom> brLists,String roomIds,String currBookRoomId) {
		
		List<Date> reachDateList = new ArrayList<Date>();
		List<Date> leaveDateList = new ArrayList<Date>();
		Date reacheD = null;
		Date leaveD = null;
		String roomTypeId = "";
		String operId=AppBaseCfg.getOperator().getOperId();
		if(brLists!=null && brLists.size()>0){
			for (BookRoom bookRoom : brLists) {
				reachDateList.add(bookRoom.getReachDate());
				leaveDateList.add(bookRoom.getLeaveDate());
				String strDate=com.cyw.common.util.DateUtil.convertDate2String(bookRoom.getReachDate(), com.cyw.common.util.DateUtil.defaultDatePattern);
				if(!StrUtils.isValidString(bookRoom.getReachTime())){
					bookRoom.setReachTime("00:00");
				}
				String reachDateTime=strDate+" "+bookRoom.getReachTime();
				Date reachDate=com.cyw.common.util.DateUtil.convertStringToDate(com.cyw.common.util.DateUtil.dateTimeShortPattern,reachDateTime);
				bookRoom.setReachDate(reachDate);
				if(bookRoom!=null && bookRoom.getCheckId()!=null){
					if(StringUtils.isNotEmpty(currBookRoomId) && bookRoom.getBookRoomId() == Integer.parseInt(currBookRoomId)){
						reacheD = bookRoom.getReachDate();
						leaveD = bookRoom.getLeaveDate();
						roomTypeId = bookRoom.getRoomtypeId();
						//删除之前留过的房间,重新留房
						/*roomNumDao.updateBookRoomByChekcIdAndRoomType(guestdoc.getCheckId(), roomTypeId,roomIds,currBookRoomId,DateUtils.dateFormat.format(reacheD),DateUtils.dateFormat.format(leaveD));
						//保存房间存量表
						if(StringUtils.isNotEmpty(roomIds)){
							String[] ids = roomIds.split(",");
							for (int i = 0; i < ids.length; i++) {
								RoomNum rm = new RoomNum();
								rm.setRoomChkid(0);
								rm.setCheckId(guestdoc.getCheckId());
								rm.setRoomId(ids[i]);
								rm.setReachDate(reacheD);
								rm.setLeaveDate(leaveD);
								rm.setFlag(0);
								rm.setBookId(bookRoom.getBookRoomId());
								rm.setStatus(0);
								rm.setKeepFlag("0");
								//roomNumDao.flush();
								roomNumDao.clear();
								roomNumDao.save(rm);
							}
						}*/
					}
					//update
					BookRoom br = bookRoomDao.get("bookRoomId", bookRoom.getBookRoomId());
					com.cyw.common.util.bean.BeanUtils.beanShallowCopy(br, bookRoom);
					br.setUpdateTimes(br.getUpdateTimes()+1);
					br.setModifyOper(operId);
					br.setModifyTime(new Date());
					bookRoomDao.update(br);
					//bookRoomDao.flush();
				}
				if(bookRoom==null || bookRoom.getId()==null){
					//save
					bookRoom.setBookRoomId(0);
					//bookRoom.setId(5);
					bookRoom.setUpdateTimes(0);
					bookRoom.setCheckId(guestdoc.getCheckId());
					bookRoom.setStatus(0);
					bookRoom.setBookOper(operId);
					bookRoom.setBookTime(new Date());
					Integer id= bookRoomDao.save(bookRoom);
					BookRoom entity = bookRoomDao.get(id);
					entity.setBookRoomId(id);
					bookRoomDao.update(entity);
				}
				
			}
			
			Collections.sort(reachDateList, new DateCompare());
			Collections.sort(leaveDateList, new DateCompare());
			guestdoc.setReachDate(reachDateList.get(0));
			guestdoc.setLeaveDate(leaveDateList.get(leaveDateList.size()-1));
		}
		if(guestdoc!=null){
			//Date reachDate= DateUtils.getDateAddStr(guestdoc.getReachDate(),StrUtils.isValidString(guestdoc.getReachtime())!=true?"00:00":guestdoc.getReachtime()+":00");
/*			String strDate=com.cyw.common.util.DateUtil.convertDate2String(guestdoc.getReachDate(), com.cyw.common.util.DateUtil.defaultDatePattern);
			if(!StrUtils.isValidString(guestdoc.getReachtime())){
				guestdoc.setReachtime("00:00");
			}
			String reachDateTime=strDate+" "+guestdoc.getReachtime();
			Date reachDate=com.cyw.common.util.DateUtil.convertStringToDate(com.cyw.common.util.DateUtil.dateTimeShortPattern,reachDateTime);*/
/*			if(guestdoc.getBookStat().equals("O")){
				guestdoc.setConfirmDate(new Date());
				guestdoc.setConfirmOperid(AppBaseCfg.getOperator().getOperId());
			}*/
			//guestdoc.setReachDate(reachDate);
			Double Blimit=0.00d;
			try{
				Blimit=new Double(guestdoc.getBiilbLimit());
				GstBill billB = gstBillDao.get(guestdoc.getBillbId());
				if(billB!=null){
					billB.setLimit(Blimit);
					gstBillDao.merge(billB);
				}
			}catch(Exception e){
				e.printStackTrace();
			}
			
		}
		guestdoc.setLastOper(operId);
		guestdoc.setLastTime(new Date());
		Guestdoc gd = dao.get(guestdoc.getCheckId());
		com.cyw.common.util.bean.BeanUtils.beanShallowCopy(gd, guestdoc);
		dao.update(gd);
		return gd;
	}
	
	@Override
	public Guestdoc updateForBookRoom2(Guestdoc guestdoc,List<BookRoom> brLists, String roomIds, String currBookRoomId) {
		// TODO Auto-generated method stub
		List<Date> reachDateList = new ArrayList<Date>();
		List<Date> leaveDateList = new ArrayList<Date>();
		Date reacheD = null;
		Date leaveD = null;
		String roomTypeId = "";
		if(brLists!=null && brLists.size()>0){
			for (BookRoom bookRoom : brLists) {
				reachDateList.add(bookRoom.getReachDate());
				leaveDateList.add(bookRoom.getLeaveDate());
				bookRoom.setReachDate(DateUtils.getDateAddStr(bookRoom.getReachDate(),bookRoom.getReachTime()+":00"));
				if(bookRoom!=null && bookRoom.getCheckId()!=null){
					if(bookRoom.getRoomNums()!=null){
						for(RoomNum rtp:bookRoom.getRoomNums()){
							reacheD = bookRoom.getReachDate();
							leaveD = bookRoom.getLeaveDate();
							roomTypeId = bookRoom.getRoomtypeId();
							//删除之前留过的房间,重新留房
							roomNumDao.updateBookRoomByChekcIdAndRoomType(guestdoc.getCheckId(), roomTypeId,roomIds,currBookRoomId,DateUtils.dateFormat.format(reacheD),DateUtils.dateFormat.format(leaveD));
						    //重新保存留房信息
							RoomNum rm = new RoomNum();
							rm.setRoomChkid(0);
							rm.setCheckId(guestdoc.getCheckId());
							rm.setRoomId(rtp.getRoomId());
							rm.setReachDate(reacheD);
							rm.setLeaveDate(leaveD);
							rm.setFlag(0);
							rm.setBookId(bookRoom.getBookRoomId());
							rm.setStatus(0);
							rm.setKeepFlag("0");
							//roomNumDao.flush();
							roomNumDao.clear();
							roomNumDao.save(rm);
						}
					}

/*					if(StringUtils.isNotEmpty(currBookRoomId) && bookRoom.getBookRoomId() == Integer.parseInt(currBookRoomId)){
						reacheD = bookRoom.getReachDate();
						leaveD = bookRoom.getLeaveDate();
						roomTypeId = bookRoom.getRoomtypeId();
						//删除之前留过的房间,重新留房
						roomNumDao.updateBookRoomByChekcIdAndRoomType(guestdoc.getCheckId(), roomTypeId,roomIds,currBookRoomId,DateUtils.dateFormat.format(reacheD),DateUtils.dateFormat.format(leaveD));
						//保存房间存量表
						if(StringUtils.isNotEmpty(roomIds)){
							String[] ids = roomIds.split(",");
							for (int i = 0; i < ids.length; i++) {
								RoomNum rm = new RoomNum();
								rm.setRoomChkid(0);
								rm.setCheckId(guestdoc.getCheckId());
								rm.setRoomId(ids[i]);
								rm.setReachDate(reacheD);
								rm.setLeaveDate(leaveD);
								rm.setFlag(0);
								rm.setBookId(bookRoom.getBookRoomId());
								rm.setStatus(0);
								rm.setKeepFlag("0");
								//roomNumDao.flush();
								roomNumDao.clear();
								roomNumDao.save(rm);
							}
						}
					}*/
					//update
					BookRoom br = bookRoomDao.get("bookRoomId", bookRoom.getBookRoomId());
					bookRoomDao.evict(br);
					bookRoom.setUpdateTimes(br.getUpdateTimes()+1);
					bookRoom.setStatus(0);
					bookRoomDao.update(bookRoom);
				}
				if(bookRoom==null || bookRoom.getId()==null){
					//save
					bookRoom.setBookRoomId(0);
					//bookRoom.setId(5);
					bookRoom.setUpdateTimes(0);
					bookRoom.setCheckId(guestdoc.getCheckId());
					bookRoom.setStatus(0);
					bookRoomDao.save(bookRoom);
				}
				
			}
			
			Collections.sort(reachDateList, new DateCompare());
			Collections.sort(leaveDateList, new DateCompare());
			guestdoc.setReachDate(reachDateList.get(0));
			guestdoc.setLeaveDate(leaveDateList.get(leaveDateList.size()-1));
		}
		if(guestdoc!=null){
			Date reachDate= DateUtils.getDateAddStr(guestdoc.getReachDate(),guestdoc.getReachtime()+":00");
			if(guestdoc.getBookStat().equals("O")){
				guestdoc.setConfirmDate(new Date());
				//XXX 从session中取出当前登陆用户
				guestdoc.setConfirmOperid("临时用户");
			}
			guestdoc.setReachDate(reachDate);
		}
		dao.update(guestdoc);

		return guestdoc;
	}

	@Override
	public Guestdoc getGuestdoc(String roomId, String chkState, String chkExt) {
		return dao.getGuestdoc(roomId, chkState, chkExt);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public List getGuestDocListByRoomId(String roomId) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public List getGuestDocList(GuestSearchVO searchVO) {
		List list = dao.getGuestDocList(searchVO);
		if(list!=null){
			for(int i=0;i<list.size();i++){
				Map map = (Map)list.get(i);
				if("O".equals(map.get("chk_stat"))){
					map.put("gst_namec", "*"+map.get("gst_namec"));
				}
			}
		}
		return list;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public List getGroupList(GroupSearchVO searchVO) {
		return dao.getGroupList(searchVO);
	}
	@Override
	public boolean getBookListByNo(String bookNo) {
		List list= dao.getList("bookList", bookNo);
		if(list!=null&&list.size()>0){
			return true;
			
		}
		
		return false;
	}
	/**
	 * {@inheritDoc}
	 */
	@Override
	public List getGroupMembersByRoomId(String roomId) {
		// TODO Auto-generated method stub
		return dao.getGroupMembersByRoomId(roomId);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public List getWebRooms(WebRoomSearchVO searchVO) {
		return dao.getWebRooms(searchVO);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean setOrCancleWebRoom(String roomId,String flag) {
		// TODO Auto-generated method stub
		int result = dao.setOrCancleWebRoom(roomId, flag);
		return result==1?true:false;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public List searchWebRooms(WebRoomSearchVO searchVO) {
		List list = dao.searchWebRooms(searchVO);
		return list;
	}
	
	@Override
	public Map getGuestDocDetail(String checkId) {
		// TODO Auto-generated method stub
		Map map = dao.getGuestDocDetail(checkId);
		return map;
	}
	@Override
	public List getRoomsList(String with_id) {
		List list = dao.getRoomsList(with_id);
		return list;
	}
	@Override
	public List getGstPriceList(String check_id) {
		// TODO Auto-generated method stub
		return null;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public int saveGuest(Guestdoc guestdoc,Double Alimit,Double Blimit) {
		Parameter param= paramDao.get(1);
		//插入登记号
		int checkid = param.getPara1()+1;
		guestdoc.setCheckId(checkid);
		//产生a账号
		Parameter paramb= paramDao.get(2);
		guestdoc.setBillaId(paramb.getPara1()+1);
		int payManFlag = guestdoc.getPaymanFlag();
		if(payManFlag == 1){
			String[] props = {"withId","paymanFlag"};
			Integer[] vals = {guestdoc.getWithId(),1};
			Guestdoc gd = dao.get(props, vals);
			gd.setPaymanFlag(0);
			dao.update(gd);
		}
		dao.save(guestdoc);
		//更新参数表登记号
		param.setPara1(param.getPara1()+1);
		paramb.setPara1(paramb.getPara1()+1);
		//修改限额
		GstBill billA = new GstBill(paramb.getPara1()+1, 0.0, 0.0, null, null, true, null, null, Alimit);
		GstBill billB = gstBillDao.get(guestdoc.getBillbId());
		paramDao.update(param);
		paramDao.update(paramb);
		if(billB == null){
			billB = new GstBill(guestdoc.getBillbId(), 0.0, 0.0, null, null, true, null, null, Blimit);
			gstBillDao.save(billB);
		}else{
			dao.updateLimit(Blimit, guestdoc.getBillbId());
		}
		gstBillDao.save(billA);
		return checkid;
	}

	@Override
	public void updateGuestdoc(Guestdoc guestdoc,Double Alimit,Double Blimit) {
		int payManFlag = guestdoc.getPaymanFlag();
		//操作时间 最后修改人 操作时间
		String operId =  AppBaseCfg.getOperator().getOperId();
		Date now = new Date(System.currentTimeMillis());
		if(payManFlag == 1){
			String[] props = {"withId","paymanFlag"};
			Integer[] vals = {guestdoc.getWithId(),1};
			Guestdoc gd = dao.get(props, vals);
			if(gd.getCheckId().intValue()!= guestdoc.getCheckId().intValue()){
				gd.setPaymanFlag(0);
				dao.update(gd);
			}
		}
		Guestdoc entity = updateEntity(guestdoc);
		entity.setLastTime(now);
		entity.setLastOper(operId);
		dao.update(entity);
		GstBill billa = gstBillDao.get(guestdoc.getBillaId());
		if(billa!=null){
			dao.updateLimit(Alimit, guestdoc.getBillaId());
		}else{
			if(Alimit>0){
				GstBill billA = new GstBill(guestdoc.getBillaId(), 0.0, 0.0, null, null, true, null, null, Alimit);
				gstBillDao.save(billA);
			}
		}
		GstBill billb = gstBillDao.get(guestdoc.getBillbId());
		if(billb!=null){
			dao.updateLimit(Blimit, guestdoc.getBillbId());
		}else{
			if(Blimit>0){
				GstBill billB = new GstBill(guestdoc.getBillbId(), 0.0, 0.0, null, null, true, null, null, Blimit);
				gstBillDao.save(billB);
			}
		}
	}
	
	/**
	 * @描述 更新客户信息
	 * @param guestdoc
	 * */
	private Guestdoc updateEntity(Guestdoc guestdoc){
		Guestdoc entity = dao.get(guestdoc.getCheckId());//主键
		entity.setWithId(guestdoc.getWithId());
		entity.setBillaId(guestdoc.getBillaId());
		entity.setBillbId(guestdoc.getBillbId());
		entity.setGrpChkid(guestdoc.getGrpChkid());
		entity.setVisakindId(guestdoc.getVisakindId());
		entity.setVisaId(guestdoc.getVisaId());
		entity.setDepart(guestdoc.getDepart());
		entity.setCrdId(guestdoc.getCrdId());
		entity.setInDate(guestdoc.getInDate());
		entity.setInPort(guestdoc.getInPort());
		entity.setGstNamec(guestdoc.getGstNamec());
		entity.setGstNamee(guestdoc.getGstNamee());
		entity.setSex(guestdoc.getSex());
		entity.setFolk(guestdoc.getFolk());
		entity.setBirthday(guestdoc.getBirthday());
		entity.setNtId(guestdoc.getNtId());
		entity.setTele(guestdoc.getTele());
		entity.setCrdkindId(guestdoc.getCrdkindId());
		entity.setNative_(guestdoc.getNative_());
		entity.setEmail(guestdoc.getEmail());
		entity.setCrdId(guestdoc.getCrdId());
		entity.setPlateNumber(guestdoc.getPlateNumber());
		entity.setAddr(guestdoc.getAddr());
		entity.setNotice(guestdoc.getNotice());
		entity.setRoomId(guestdoc.getRoomId());
		entity.setPayId(guestdoc.getPayId());
		entity.setGstOriId(guestdoc.getGstOriId());
		entity.setReachDate(guestdoc.getReachDate());
		entity.setGstKind(guestdoc.getGstKind());
		entity.setLeaveDate(guestdoc.getLeaveDate());
		entity.setCompId(guestdoc.getCompId());
		entity.setCompType(guestdoc.getCompType());
		entity.setRoomPrice(guestdoc.getRoomPrice());
		entity.setCityLedger(guestdoc.getCityLedger());
		entity.setHousePay(guestdoc.getHousePay());
		entity.setFreeSvc(guestdoc.getFreeSvc());
		entity.setHideprice(guestdoc.getHideprice());
		entity.setIfFgst(guestdoc.getIfFgst());
		entity.setChkStat(guestdoc.getChkStat());
		entity.setCrdVld(guestdoc.getCrdVld());
		entity.setPrcSchemeId(guestdoc.getPrcSchemeId());
		entity.setIsRoomPlan(guestdoc.getIsRoomPlan());
		return entity;
	}
	
	@Override
	public Map copyGuest(Integer checkId) {
		// TODO Auto-generated method stub
		Map map = new HashMap();
		Guestdoc guestdoc = this.get("checkId",checkId);
		Parameter param= paramDao.get(1);
		Parameter paramb= paramDao.get(2);
		guestdoc.setBillaId(paramb.getPara1()+1);
		Guestdoc gd = new Guestdoc();
		this.evict(guestdoc);
		gd = guestdoc;
		gd.setChkExt("0");
		gd.setCheckId(param.getPara1()+1);
		gd.setPaymanFlag(0);
		gd.setRoomPrice(0.00);
		dao.save(gd);
		//更新参数表登记号
		param.setPara1(param.getPara1()+1);
		paramDao.update(param);
		paramb.setPara1(paramb.getPara1()+1);
		paramDao.update(paramb);
		map.put("guestdoc", gd);
		return map;
	}

	@Override
	public void updateOther(Guestdoc guestdoc) {
		// TODO Auto-generated method stub
		dao.updateOther(guestdoc);
	}

	@Override
	public void updeteGuestIfbate(Integer checkId, String ifBate) {
		// TODO Auto-generated method stub
		dao.updeteGuestIfbate(checkId, ifBate);
	}

	@Override
	public void updataChangeGrpDoc(JSONArray changeDataObj,JSONObject changLftObj, JSONObject changRightObj) {
		Integer check_id;
		Integer billb_id;
		Integer grp_chkid;
		Integer with_id;
		Integer payman_flag;
		Parameter paramb= paramDao.getForUpdate(2);
		//产生B账号
		int temp_billBid_Num = paramb.getPara1();
		String room_id = "";
		for (int i = 0; i < changeDataObj.size(); i++) {
			JSONObject dataObj = (JSONObject) changeDataObj.get(i);
			payman_flag = Integer.parseInt(dataObj.getString("payman_flag").trim());
			if(!room_id.equals(dataObj.getString("room_id").trim())){
				room_id = dataObj.getString("room_id").trim();
				temp_billBid_Num++;
			}
			//左边变到右边
			if("-1".equals(dataObj.get("flag")+"")&&"left".equals(dataObj.get("flagSign"))){
				check_id = Integer.parseInt(dataObj.get("check_id")+"");
				Guestdoc gs = dao.get(check_id);				
				//从右侧拿到billb_id(B账号)
				billb_id = Integer.parseInt(changRightObj.get("billb_id2")+"");
				//从右侧拿到grp_chkid(团代码)
				String grp_chkid2 = changRightObj.get("grp_chkid2")+"";
				if(grp_chkid2.length()!=0){
					grp_chkid = Integer.parseInt(changRightObj.get("grp_chkid2")+"");
					if(grp_chkid2.trim().equals("0")){
						gs.setGstFlag("F");
					}else{
						gs.setGstFlag("G");
					}
				}else{
					grp_chkid = null;
					gs.setGstFlag("F");
				}
				//从右侧拿到with_id
				with_id = Integer.parseInt(changRightObj.get("with_id2")+"");
				//billb_id为空说明右侧没有数据
				if(0==billb_id){
//					temp_billBid_Num++;
					gs.setBillbId(temp_billBid_Num);
					gs.setGrpChkid(grp_chkid);
					gs.setWithId(temp_billBid_Num);
					gs.setPaymanFlag(payman_flag);
					dao.update(gs);
				}else{
					gs.setBillbId(billb_id);
					gs.setGrpChkid(grp_chkid);
					gs.setWithId(with_id);
					gs.setPaymanFlag(payman_flag);
					dao.update(gs);
				}
			}
			//右边变到左边
			if("-1".equals(dataObj.get("flag"+"")+"")&&"right".equals(dataObj.get("flagSign"))){
				check_id = Integer.parseInt(dataObj.get("check_id")+"");
				Guestdoc gs = dao.get(check_id);
				//从左侧拿到billb_id(B账号)
				billb_id = Integer.parseInt(changLftObj.get("billb_id1")+"");
				//从左侧拿到grp_chkid(团代码)
				String grp_chkid1 = changLftObj.get("grp_chkid1")+"";
				if(grp_chkid1.length()!=0){
					grp_chkid = Integer.parseInt(grp_chkid1);
					if(grp_chkid1.trim().equals("0")){
						gs.setGstFlag("F");
					}else{
						gs.setGstFlag("G");
					}
				}else{
					grp_chkid = null;
					gs.setGstFlag("F");
				}
				//从左侧拿到with_id
				with_id = Integer.parseInt(changLftObj.get("with_id1")+"");
				//billb_id为空说明左侧没有数据
				if(0==billb_id){
//					temp_billBid_Num++;
					gs.setBillbId(temp_billBid_Num);
					gs.setGrpChkid(grp_chkid);
					gs.setWithId(temp_billBid_Num);
					gs.setPaymanFlag(payman_flag);
					dao.update(gs);
				}else{
					gs.setBillbId(billb_id);
					gs.setGrpChkid(grp_chkid);
					gs.setWithId(with_id);
					gs.setPaymanFlag(payman_flag);
					dao.update(gs);
				}
			}
		}

		paramb.setPara1(temp_billBid_Num);
		paramDao.update(paramb);
	}

	@Override
	public List selectRoomsWardToLive(String room_id) {
		return dao.selectRoomsWardToLive(room_id);
	}

	@Override
	public List checkToLive(String billb_id) {
		return dao.checkToLive(billb_id);
	}

	@Override
	public void updateWardsToLive(Map map){
		Parameter paramb = paramDao.get(2);
		//产生B账号
		int temp_billBid_Num = paramb.getPara1()+1;
		map.put("temp_billBid_Num", temp_billBid_Num);
		dao.updateWardsToLive(map);
		paramDao.update(paramb);
	}

	@Override
	public List getBills(Integer billType, String billId,String showType,String consId,String okFlag,String startDate,String endDate,String isInvalid) {
		List list = dao.getBills(billType, billId,showType,consId,okFlag,startDate,endDate,isInvalid);
		if(list!=null && list.size()>0){
			Map map = null;
			for(int i=0;i<list.size();i++){
				 map = (Map)list.get(i);
				 if("0".equals(map.get("status"))){
					 map.put("status", "正常");
				 }else if("1".equals(map.get("status"))){
					 map.put("status", "取消");
				 }
			}
		}
		return list;
	}

	@Override
	public List getselectRoomToleave(String nowDate) {
		return dao.getselectRoomToleave(nowDate);
	}

	@Override
	public void updateRoomToleave(JSONArray jsonArray) {
		dao.updateRoomToleave(jsonArray);
	}

	@Override
	public List loadConsumesByBillType(String billId) {
		// TODO Auto-generated method stub
		return dao.loadConsumesByBillType(billId);
	}
	
	public String checkConsId(Integer checkId,String consId){
		String billType = "1";
		BPaied bPaied = bPaiedDao.get(checkId);
		if(bPaied!=null){
			String cons = bPaied.getCons();
			if(StringUtils.isNotEmpty(cons)){
				String[] str = cons.split(",");
				for(String s : str){
					if(s.equals(consId)){
						billType = "2";
						break;
					}
				}
			}
		}
		return billType;
	}
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void saveBill(String type, String billids, Bills bill,Integer checkId) {
		//消费点
		String consId = bill.getConsId();
		if ("A".equals(type)) {// 当前客人
			bill.setHotelTime(getBIllsDate());
			bill.setBalance(setMoney(bill.getBalance()));
			billsDao.save(bill);
			// gst_bill保存或更新
			dao.saveOrUpdateGstBill(bill);
			//修改最后账目处理人
			Guestdoc guestdoc = dao.get(checkId);
			if(guestdoc!=null){
				guestdoc.setLastCashier(bill.getOperId());
				dao.update(guestdoc);
			}
		}else{
			List<Guestdoc> list = null;
			//判断存入A账或B账
			String tempBillType = null;
			Bills temp = null;
			if("B".equals(type)){  //所有客人
				list = dao.getList("chkStat", "I");
			}else if("C".equals(type)){ //所有房间
				String[] property = {"chkStat","chkExt"};
				String[] value = {"I","1"};
				list = dao.getList(property, value);
			}
			if(list!=null && list.size()>0){
				for (Guestdoc guest : list) {
					temp = bill;
					temp.setHotelTime(getBIllsDate());
					//判断当前消费点在客人的A账还是B账
					tempBillType = checkConsId(guest.getCheckId(),consId);
					temp.setBillType(tempBillType);
					temp.setBillId(Integer.valueOf("1".equals(tempBillType) ? guest.getBillaId() : guest.getBillbId()));
					//保存账目信息
					billsDao.save(temp);
					// gst_bill保存或更新
					dao.saveOrUpdateGstBill(temp);
					//修改最后账目处理人
					guest.setLastCashier(bill.getOperId());
					dao.update(guest);
					dao.flush();
					dao.clear();
				}
			}
		}
	}
    
	/**
	 * {@inheritDoc}
	 */
	@Override
	public AjaxJson checkBillId(String billType, String billid,String type) {
		// TODO Auto-generated method stub
		AjaxJson json = new AjaxJson();
		Guestdoc guestdoc = null;
		Noguest noguest = null;
		if(type==null){
			if("1".equals(billType)){
				guestdoc = dao.get("billaId", Integer.valueOf(billid));
				if(guestdoc==null){
					json.setMsg("账号"+billid+"不存在，请重试");
					json.setSuccess(false);
					return json;
				}
			}else if("2".equals(billType)){
				List<Guestdoc> list = dao.getList("billbId", Integer.valueOf(billid));
				if(list==null || list.size()==0){
					json.setMsg("账号"+billid+"不存在，请重试");
					json.setSuccess(false);
					return json;
				}
			}
		}else{
			noguest = noguestDao.get("billId", Integer.valueOf(billid));
			if(noguest==null){
				json.setMsg("账号"+billid+"不存在，请重试");
				json.setSuccess(false);
				return json;
			}
		}
		json.setSuccess(true);
		return json;
	}

	@Override
	public Map<String, Object> getUnionGuesInf(Integer checkId) {
		Guestdoc guestDoc = dao.get(checkId);
		Map<String, Object> result = new HashMap<String, Object>();
		//根据 grp_checkID 同一个团的房号
		Integer grpChkId = guestDoc.getGrpChkid();
		String[] properties =null;
		Object[] values = null;
		if(grpChkId==null){
			log.info("checkId=" + checkId +" 是联房");
			Integer withId = guestDoc.getWithId();
			properties = new String[]{"withId","chkStat","chkExt"};
			values = new Object[]{withId,"I","1"};
		}else{
			//grp_chkid一直 并且 在住状态为 I
			
			log.info("checkId=" + checkId +" 是团房");
			properties = new String[]{"grpChkid","chkStat","chkExt"};
			values = new Object[]{grpChkId,"I","1"};
			//团队名称 
			//2015-12-07 11:49 此处取grp_doc check_id 与徐工确认
			GrpDoc groDoc = grpDocDao.get("checkId",grpChkId);
			if(groDoc != null){
				result.put("GROUPNAME", groDoc.getGrpName());
			}
			
		}
		if(properties!=null && values!=null){
			List<Guestdoc> list = dao.getList(properties, values);
			List<Map<String, Object>> roomsInfs = new ArrayList<Map<String,Object>>();
			for(Guestdoc doc :list){
				Map<String, Object> roomInf = new HashMap<String, Object>();
				String roomId = doc.getRoomId();
				if(!StringUtils.isEmpty(roomId)){
					roomInf.put("ROOM_ID", roomId);
				}
				roomInf.put("ISMAIN", dao.isPayRoom(roomId));
				roomsInfs.add(roomInf);
			}
			result.put("LIST", roomsInfs);
			
			result.put("COUNT", list.size());
		}
		//根据结账人 找到对应记录 roomID 和 payman_flag 付款人标识
		String[] names = {"roomId","paymanFlag","chkStat"};
		Object[] vales = {guestDoc.getRoomId(),1,"I"};
		Guestdoc billGst = null;
		try {
			billGst = dao.get(names, vales);
		} catch (NonUniqueResultException e) {
			// TODO Auto-generated catch block
			log.error("房间号码[" + guestDoc.getRoomId() + "] 所住客人资料有误！ ");
			e.printStackTrace();
		}catch(Exception e){
			log.error("房间号码[" + guestDoc.getRoomId() + "] 所住客人资料有误！ ");
		}
		if(billGst!=null){
			Integer biiBId = billGst.getBillbId();
			GstBill gstBill = gstBillDao.get(biiBId);
			if(gstBill!=null){
				result.put("borrow", gstBill.getBorrow());
				result.put("lent", gstBill.getLent());
				result.put("auth_balance", gstBill.getAuthBalance());
				result.put("limit", gstBill.getLimit());
				result.put("banace",  gstBill.getBorrow()-gstBill.getLent()-gstBill.getAuthBalance()-gstBill.getLimit());
			}else{
				//数据不全面
				result.put("borrow", 0.00d);
				result.put("lent", 0.00d);
				result.put("auth_balance", 0.00d);
				result.put("limit", 0.00d);
				result.put("banace",0.0d);
			}
			
		}else{
			//数据不全面
			result.put("borrow", 0.00d);
			result.put("lent", 0.00d);
			result.put("auth_balance", 0.00d);
			result.put("limit", 0.00d);
			result.put("banace",0.0d);
		}
		
		return result;
	}
	
	@Override
	public List<Map<String, Object>> getNullRoomInf(String roomId,
			String startDate, String endDate) {
		
		return dao.getCannotUseRoom(roomId,startDate,endDate);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void depositSaveBill(Bills bills,Integer checkId) {
		// 保存账单
		bills.setHotelTime(getBIllsDate());
		bills.setBalance(setMoney(bills.getBalance()));
		billsDao.save(bills);
		//更新gst_bill
		dao.saveOrUpdateGstBill2(bills);
		if(checkId!=null){
			Guestdoc guestdoc = dao.get(checkId);
			if(guestdoc!=null){
				guestdoc.setLastCashier(bills.getOperId());
				guestdoc.setCashierTime(new Date());
				dao.update(guestdoc);
			}
		}
	}

	@Override
	public void setSplit(BPaied bpaied,String ifBate) {
		BPaied bp = paiedDao.get(bpaied.getCheckId());
		bpaied.setOperTime(new Date());
		//不存在则保存
		if(bp==null && !"".equals(bpaied.getCons())){
			bpaied.setUpdateTimes(0);
			paiedDao.save(bpaied);
		}else if(bp!=null){
			bpaied.setUpdateTimes(bp.getUpdateTimes()+1);
			paiedDao.evict(bp);
			if(!"".equals(bpaied.getCons())){
				paiedDao.update(bpaied);
			}else{
				paiedDao.delete(bp);
			}
		}
		//修改是否延长续住
		if(StringUtils.isNotEmpty(ifBate)){
			dao.updeteGuestIfbate(bpaied.getCheckId(), ifBate);
		}
	}

	@Override
	public List getPreAuthorization(Integer checkId,String status,Integer billId) {
		List list = dao.getPreAuthorization(checkId,status,billId);
		if(list!=null){
			Map map = null;
			String stat = null;
			for(int i=0;i<list.size();i++){
				map = (Map)list.get(i);
				stat = map.get("status").toString();
				if("1".equals(stat)){
					map.put("status0","取消");
				}else if("0".equals(stat)){
					map.put("status0","正常");
				}
			}
		}
		return list;
	}

	@Override
	public void savePreAuth(GstCreditAuth creditAuth) {
		//新增预授权
		Parameter p = paramDao.get(1);
		creditAuth.setHotelDate(p.getPara3());
		creditAuth.setStatus(0);
		creditAuthDao.save(creditAuth);
		//修改gst_bill
		GstBill gstBill = gstBillDao.get(creditAuth.getBillId());
		if(gstBill!=null){
			gstBill.setAuthBalance(setMoney(gstBill.getAuthBalance()+creditAuth.getBalance()));
			gstBillDao.update(gstBill);
		}
		//修改最后账目处理人
		Guestdoc guestdoc = dao.get(creditAuth.getCheckId());
		if(guestdoc!=null){
			Date now = new Date(System.currentTimeMillis());
			guestdoc.setLastCashier(creditAuth.getOperId());
			guestdoc.setCashierTime(now);
			dao.update(guestdoc);
		}
	}

	@Override
	public void finishAuth(String id,String balance,String operId,String finishNo,Integer billId,String billType) {
		if(StringUtils.isEmpty(id)) return;
		double money = setMoney(Double.valueOf(balance));
		GstCreditAuth auth = creditAuthDao.get(Integer.valueOf(id));
		auth.setAuthStat(1);
		auth.setFinishBalance(money);
		auth.setFinishOper(operId);
		auth.setFinishTime(new Date());
		creditAuthDao.update(auth);
		Parameter p =paramDao.get(5);
		Parameter para = paramDao.get(2);
		
		//新增账目信息
		Bills b = new Bills();
		b.setBalance(money);
		b.setOperId(operId);
		b.setSetlId(p.getPara5());
		b.setOkFlag("0");
		b.setStatus("0");
		b.setBillId(billId);
		b.setBillType(billType);
	    b.setAccoId(0);
	    b.setMoneyKind(para.getPara5());
	    b.setHotelTime(getBIllsDate());
		billsDao.save(b);
		//修改gst_bill预授权金额
		GstBill gstBill = gstBillDao.get("billId", billId);
		gstBill.setLent(setMoney(gstBill.getLent()+money));
		gstBill.setAuthBalance(setMoney(gstBill.getAuthBalance()-money));
		gstBillDao.update(gstBill);
		//修改最后账目处理人
		Guestdoc guestdoc = dao.get(auth.getCheckId());
		if(guestdoc!=null){
			guestdoc.setLastCashier(operId);
			dao.update(guestdoc);
		}
	}
	@Override
	public void cancleAuth(String id,String balance,String cancelNo,String operId,Integer billId){
		if(StringUtils.isEmpty(id)) return;
		double money = setMoney(Double.valueOf(balance));
		GstCreditAuth auth = creditAuthDao.get(Integer.valueOf(id));
		auth.setAuthStat(0);
		auth.setCancelNo(cancelNo);
		creditAuthDao.update(auth);
		//修改gst_bill预授权金额
		GstBill gstBill = gstBillDao.get("billId", billId);
		gstBill.setAuthBalance(gstBill.getAuthBalance()-money);
		gstBillDao.update(gstBill);
		//修改最后账目处理人
		Guestdoc guestdoc = dao.get(auth.getCheckId());
		if(guestdoc!=null){
			Date now = new Date(System.currentTimeMillis());
			guestdoc.setLastCashier(operId);
			guestdoc.setCashierTime(now);
			dao.update(guestdoc);
		}
	}

	@Override
	public void updateAuthStatus(String id,String balance,Integer billId) {
		if(StringUtils.isEmpty(id)) return;
		double money = setMoney(Double.valueOf(balance));
		GstCreditAuth	auth = creditAuthDao.get(Integer.valueOf(id));
		auth.setStatus(1);
		creditAuthDao.update(auth);
		//修改gst_bill预授权金额
		GstBill gstBill = gstBillDao.get("billId", billId);
		gstBill.setAuthBalance(gstBill.getAuthBalance()-money);
		gstBillDao.update(gstBill);
	}
	
	@Override
	public AjaxJson checkAuth(Integer checkId) {
		AjaxJson ajaxJson = new AjaxJson();
		//判断是否存在未处理的预授权
		List<GstCreditAuth> list = creditAuthDao.getList("checkId", checkId);
		if(list!=null && list.size()>0){
			for(GstCreditAuth auth:list){
				if(auth.getAuthStat()==null){
					ajaxJson.setMsg("有未完成的预授权，请先处理！");
					ajaxJson.setSuccess(false);
					return ajaxJson;
				}
			}
		}
		return ajaxJson;
	}
	
	@Override
	public AjaxJson checkOutAccount(Integer checkId,String ids,String bills,String operId,String oddMoney,String billId,String billType,String roomId,String tipMoney) {
		// TODO Auto-generated method stub
		AjaxJson ajaxJson = new AjaxJson();
		//酒店日期
		Parameter parameter = paramDao.get(1);
		//如果为B账,判断是否为付款人
		if("2".equals(billType)){
			Guestdoc guestdoc = dao.get(checkId);
			int pmf = guestdoc.getPaymanFlag();
			if(pmf==0){
				ajaxJson.setMsg("当前客人不是付款人，无法进行结账操作！");
				ajaxJson.setSuccess(false);
				return ajaxJson;
			}
		}
		//更改客户的借贷金额
		GstBill gstBill = gstBillDao.get("billId", Integer.valueOf(billId));
		//获取bills表里pay_num最大值
		int maxNum = dao.getMaxPayNum();
		//新增结账账目信息
		JSONArray array = JSONObject.parseArray(bills);
		if(array!=null){
			Bills bill =null;
			for(int i=0;i<array.size();i++){
				bill = JSONObject.parseObject(array.getString(i), Bills.class);
				bill.setAccoId(0);
				bill.setPayNum(maxNum+1);
				bill.setOperId(operId);
				bill.setOkFlag("1");
				bill.setBillType(billType);
				bill.setRoomId(roomId);
				bill.setPayDate(parameter.getPara3());
				bill.setHotelTime(getBIllsDate());
				billsDao.save(bill);
				gstBill.setLent(gstBill.getLent()+Double.valueOf(bill.getBalance()));
			}
		}
		//修改未结账目结账状态
		String[] idARR = ids.split(",");
		Bills b = null;
		for(String id:idARR){
			b = billsDao.get(Integer.valueOf(id));
			b.setPayNum(maxNum+1);
			b.setOkFlag("1");
			billsDao.update(b);
		}
		//判断是否有零头，有则新插入零头账目信息
		if(StringUtils.isNotEmpty(oddMoney)){
			Parameter p = paramDao.get(2);
			Bills bill = new Bills();
			bill.setBillId(Integer.valueOf(billId));
			bill.setAccoId(0);
			bill.setPayNum(maxNum+1);
			bill.setBalance(Double.valueOf(oddMoney));
			bill.setConsId(p.getPara6());
			bill.setStatus("0");
			bill.setOkFlag("1");
			bill.setPayDate(parameter.getPara3());
			bill.setOperId(operId);
			bill.setBillType(billType);
			bill.setRoomId(roomId);
			bill.setHotelTime(getBIllsDate());
			billsDao.save(bill);
			gstBill.setBorrow(gstBill.getBorrow()+Double.valueOf(oddMoney));
		}
		//判断是否有零头，有则新插入零头账目信息
		if(StringUtils.isNotEmpty(tipMoney)){
			Parameter p = paramDao.get(1);
			Bills bill = new Bills();
			bill.setBillId(Integer.valueOf(billId));
			bill.setAccoId(0);
			bill.setPayNum(maxNum+1);
			bill.setBalance(Double.valueOf(tipMoney));
			bill.setSetlId(p.getPara6());
			bill.setStatus("0");
			bill.setOkFlag("1");
			bill.setPayDate(parameter.getPara3());
			bill.setOperId(operId);
			bill.setBillType(billType);
			bill.setRoomId(roomId);
			bill.setHotelTime(getBIllsDate());
			billsDao.save(bill);
			gstBill.setLent(gstBill.getLent()+Double.valueOf(tipMoney));
		}
		gstBillDao.update(gstBill);
		//修改最后账目处理人
		Guestdoc guestdoc = dao.get(checkId);
		if(guestdoc!=null){
			Date now = new Date(System.currentTimeMillis());
			guestdoc.setLastCashier(operId);
			guestdoc.setCashierTime(now);
			dao.update(guestdoc);
		}
		return ajaxJson;
	}

	@Override
	public void leaveRoom(String checkIds) {
		// TODO Auto-generated method stub
		String[] ids = checkIds.split(",");
		if(ids!=null && ids.length>0){
			Guestdoc guestdoc = null;
			for(String checkId:ids){
				guestdoc = dao.get(Integer.valueOf(checkId));
				guestdoc.setChkStat("O");
				guestdoc.setLeaveDate(new Date());
				dao.update(guestdoc);
			}
		}
	}

	@Override
	public List<?> findFitBookCheckInList(BookRoomSearchVo bookRoomSearchVo)
			throws Exception {
		return dao.findFitBookCheckInList(bookRoomSearchVo);
	}
	@Override
	public List<?> findGroupBookCheckInList(BookRoomSearchVo bookRoomSearchVo)
			throws Exception {
		return dao.findGroupBookCheckInList(bookRoomSearchVo);
	}

	@Override
	public List<?> findFitBookCheckInInfoListBy(BookRoomCheckInVO bookRoomCheckInVO)
			throws Exception {
		return dao.findFitBookCheckInInfoListBy(bookRoomCheckInVO);
	}

	@Override
	public List<?> findAlreadyCheckInPersonListBy(
			BookRoomCheckInVO bookRoomCheckInVO) throws Exception {
		return dao.findAlreadyCheckInPersonListBy(bookRoomCheckInVO);
	}

	@Override
	public List getGroupBills(Integer billId) {
		List list = dao.getGroupBills(billId);
		return list;
	}

	@Override
	public List getNoguestBills(Integer billId,String showType,String okFlag,String startDate,String endDate,String isInvalid) {
		// TODO Auto-generated method stub
		List list = dao.getNoguestBills(billId, showType, okFlag, startDate, endDate, isInvalid);
		if(list!=null && list.size()>0){
			Map map = null;
			for(int i=0;i<list.size();i++){
				 map = (Map)list.get(i);
				 if("0".equals(map.get("status"))){
					 map.put("status", "正常");
				 }else if("1".equals(map.get("status"))){
					 map.put("status", "取消");
				 }
			}
		}
		return list;
	}

	@Override
	public void transferConfirm(String topBillId, String bottomBillId,
			String transferData,Integer checkId,String operId) {
		//处理需转账的数据
		JSONArray array = JSONObject.parseArray(transferData);
		if(array!=null && array.size()>0){
			Bills bills = null;
			Bills newBill = null;
			GstBill inGstBill = null;
			GstBill outGstBill = null;
			for(int i=0;i<array.size();i++){
				JSONObject obj = JSONObject.parseObject(array.getString(i));
				//判断账目是上方的账目还是下方的账目
				boolean isTop = obj.getBooleanValue("top");
				bills = billsDao.get(obj.getInteger("ID"));
				Integer outBillId = null;
				Integer inBillId = null;
				//设置转入转出帐号
				if(isTop){
					outBillId = Integer.valueOf(bottomBillId);
					inBillId = Integer.valueOf(topBillId);
				}else{
					outBillId = Integer.valueOf(topBillId);
					inBillId = Integer.valueOf(bottomBillId);
				}
				//更新余额
				outGstBill = gstBillDao.get("billId", outBillId);
				inGstBill = gstBillDao.get("billId", inBillId);
				if(StringUtils.isNotEmpty(obj.getString("cname"))){
					if(outGstBill!=null){
						outGstBill.setBorrow(outGstBill.getBorrow()+Double.valueOf(obj.getString("balance"))+Double.valueOf(obj.getString("svc_charge")));
					}
					if(inGstBill!=null){
						inGstBill.setBorrow(inGstBill.getBorrow()-Double.valueOf(obj.getString("balance"))-Double.valueOf(obj.getString("svc_charge")));
					}
				}
				if(StringUtils.isNotEmpty(obj.getString("sname"))){
					if(outGstBill!=null){
						outGstBill.setLent(outGstBill.getLent()+Double.valueOf(obj.getString("balance")));
					}
					if(inGstBill!=null){
						inGstBill.setLent(inGstBill.getLent()-Double.valueOf(obj.getString("balance")));
					}
				}
				//更新转出
				bills.setOutBill(outBillId);
				bills.setExtName("转出");
				billsDao.update(bills);
				billsDao.flush();
				billsDao.clear();
				newBill = bills;
				this.evict(newBill);
				//新增转入
				newBill.setBillId(outBillId);
				newBill.setInBill(inBillId);
				newBill.setExtName("转入");
				newBill.setOutBill(null);
				billsDao.save(newBill);
				//更新转账账目
				gstBillDao.update(outGstBill);
				gstBillDao.update(inGstBill);
				//修改最后账目处理人
				Guestdoc guestdoc = dao.get(checkId);
				if(guestdoc!=null){
					Date now = new Date(System.currentTimeMillis());
					guestdoc.setLastCashier(operId);
					guestdoc.setCashierTime(now);
					dao.update(guestdoc);
				}
			}
		}
	}

	@Override
	public List<?> findBookRoomsListBy(BookRoomCheckInVO bookRoomCheckInVO)
			throws Exception {
		return dao.findBookRoomsListBy(bookRoomCheckInVO);
	}
	@Override
	public void checkOutRoom(String roomIds,String checkIds,String operId) {
		//更改房间状态
		String[] roomArr = roomIds.split(",");
		if(roomArr!=null && roomArr.length>0){
			Rooms rooms = null;
			List<RoomNum> roomNumList = null;
			for(String roomId:roomArr){
				rooms = roomsDao.get("roomId",roomId);
				boolean isIp = paramDao.GetIPFlag();
				if(isIp){
					rooms.setCurrStat("VDP");
				}else{
					rooms.setCurrStat("VD");
				}
				roomsDao.update(rooms);
				//修改房间存量表状态
				String[] props = {"roomId","flag"};
				Object[] vals = {roomId,1};
				roomNumList = roomNumDao.getList(props, vals);
				if(roomNumList!=null && roomNumList.size()>0){
					for(RoomNum roomNum : roomNumList){
						roomNum.setStatus(1);
						roomNumDao.update(roomNum);
					}
				}
			}
			String[] ids = checkIds.split(",");
			if(ids!=null && ids.length>0){
				Guestdoc guestdoc = null;
				for(String checkId:ids){
					guestdoc = dao.get(Integer.valueOf(checkId));
					guestdoc.setChkStat("O");
					Date now = new Date(System.currentTimeMillis());
					guestdoc.setLeaveDate(now);
					//退房人 操作时间
					String operID = AppBaseCfg.getOperator().getOperId();
					guestdoc.setOutOper(operID);
					guestdoc.setOutTime(now);
					guestdoc.setLastOper(operID);
					guestdoc.setLastTime(now);
					dao.update(guestdoc);
				}
			}
		}
	}

	@Override
	public AjaxJson checkBills(String roomIds) {
		AjaxJson ajaxJson = new AjaxJson();
		String[] roomArr = roomIds.split(",");
		if(roomArr!=null && roomArr.length>0){
			List<Guestdoc> guestList = null;
			StringBuilder guestNames = new StringBuilder();
			for(String roomId:roomArr){
				//获取房间客人信息
				String[] properties = {"roomId","chkStat"};
				String[] values = {roomId,"I"};
				guestList = dao.getList(properties, values);
				//判断房间客人A账目是否结完
				Integer billid = null;
				List<Bills> bills = null;
				String[] props = {"billId","okFlag"};
				Object[] vals = new Object[2];
				if(guestList!=null && guestList.size()>0){
					for(Guestdoc guestdoc:guestList){
						billid = guestdoc.getBillaId();
						vals[0] = billid;
						vals[1] = "0";
						bills = billsDao.getList(props, vals);
						if(bills!=null && bills.size()>0){
//							ajaxJson.setSuccess(false);
//							ajaxJson.setMsg("客人【"+guestdoc.getGstNamec()+"】的A账目未结清，是否继续？");
//							ajaxJson.setObj(true);
//							return ajaxJson;
							guestNames.append(guestdoc.getGstNamec()+",");
						}
					}
				}
			}
			if(StringUtils.isNotEmpty(guestNames.toString())){
				ajaxJson.setSuccess(false);
				ajaxJson.setMsg("客人【"+guestNames.toString().substring(0,guestNames.toString().length()-1)+"】的A账目未结清，是否继续？");
				ajaxJson.setObj(true);
				return ajaxJson;
			}
			//判断B账是否结清
//			Integer billBid = guestList.get(0).getBillbId();
//			if(billBid!=null){
//				bills = billsDao.getList(props, vals);
//				if(bills!=null && bills.size()>0){
//					ajaxJson.setSuccess(false);
//					ajaxJson.setObj(true);
//					ajaxJson.setMsg("当前团队B账目未结清，是否继续？");
//					return ajaxJson;
//				}
//			}
		}
		return ajaxJson;
	}

	@Override
	public AjaxJson cancleBill(Integer id,String payNum,Integer billId,String operId,String okFlag,Integer checkId) {
		AjaxJson ajaxJson = new AjaxJson();
		Bills bill = billsDao.get(id);
		if(bill == null){
		    ajaxJson.setSuccess(false);
		    return ajaxJson;
		}
		if(!operId.equals(bill.getOperId().trim())){
			ajaxJson.setSuccess(false);
			ajaxJson.setMsg("非本人无法进行取消操作");
		    return ajaxJson;
		}
		Double borrow = 0.00;
		Double lent = 0.00;
		//更改选择账单的状态
		bill.setStatus("2");
		billsDao.update(bill);
		GstBill gstBill = gstBillDao.get("billId", billId);
		if(bill.getConsId()!=null && !"*".equals(bill.getConsId().trim())){
			borrow += (bill.getBalance()+(bill.getSvcCharge()==null ? 0: bill.getSvcCharge()));
			if(gstBill!=null)gstBill.setBorrow(gstBill.getBorrow()-borrow);
		}
		if(bill.getSetlId()!=null && !"*".equals(bill.getSetlId().trim())){
			lent += bill.getBalance();
			if(gstBill!=null)gstBill.setLent(gstBill.getLent()-lent);
		}
		gstBillDao.update(gstBill);
		//更改账目的payNum
		if(StringUtils.isNotEmpty(payNum)){
			List<Bills> billList = billsDao.getList("payNum", Integer.valueOf(payNum));
			if(billList!=null && billList.size()>0){
				for(Bills b:billList){
					b.setOkFlag("1");
					b.setPayNum(-1);
				}
			}
		}
		//修改最后账目处理人
		Guestdoc guestdoc = dao.get(checkId);
		if(guestdoc!=null){
			Date now = new Date(System.currentTimeMillis());
			guestdoc.setLastCashier(operId);
			guestdoc.setCashierTime(now);
			dao.update(guestdoc);
		}
		return ajaxJson;
	}
	@Override
	public void changeRoom(JSONArray data, JSONObject objA, JSONObject objB, Operator oper) {
		//取得酒店时间
		Date hotelDate = paramDao.GetHotelDate();
		//取得酒店ip信息
		boolean ipflag = parameterSvc.GetIPFlag();
		
		//房间A信息
		String room_idA = objA.getString("room_id").trim();
		String guest_numA = objA.getString("guest_num").trim();
		String curr_statA = objA.getString("curr_stat").trim();
		
				
		//房间B信息
		String room_idB = objB.getString("room_id").trim();
		String guest_numB = objB.getString("guest_num").trim();
		String curr_statB = objB.getString("curr_stat").trim();
		
		//住客信息
		for (Object obj : data) {
			JSONObject dataObj = (JSONObject)obj;
			//住客注册号
			String check_id = dataObj.getString("check_id").trim();
			//住客修改后的房间号
			String room_id = dataObj.getString("room_id").trim();
			//住客原本的房间号
			String room_id_old = dataObj.getString("room_id_old").trim();
			//住客修改后是否主人
			String chk_ext = dataObj.getString("chk_ext").trim();
			//住客修改前是否主人
			String chk_ext_old = dataObj.getString("chk_ext_old").trim();
			
			if(room_id.equals(room_id_old) && chk_ext.equals(chk_ext_old)){
				//住客房间号和是否主人都没有修改，循环下一个住客
				continue;
			}
			//用户信息有改变才处理
			if(!chk_ext.equals(chk_ext_old) || !room_id.equals(room_id_old)){
				//取得住客信息
				Guestdoc guestdoc = dao.get(Integer.parseInt(check_id));
				//如果房间号改变，更新住户房间号
				if(!room_id.equals(room_id_old)){
					guestdoc.setRoomId(room_id);
				}
				//如果是否主人改变，更新是否主人
				if(!chk_ext.equals(chk_ext_old)){
					guestdoc.setChkExt(chk_ext);
				}
				//跟新住客信息
				short updateTimes = (short)(guestdoc.getUpdateTimes()+(short)1);
				guestdoc.setUpdateTimes(updateTimes);
				guestdoc.setLastTime(new Date());
				guestdoc.setLastOper(oper.getOperId());
				dao.update(guestdoc);
				
				//住户房间号改变
				if(!room_id.equals(room_id_old)){
					//住客变为房主修改房间存量信息，否则不修改
					if(chk_ext.equals("1")){
						//住户的新房间状态
						String gst_new_room_stat = room_id.equals(room_idA) ? curr_statA : curr_statB;
						//如果新房间原本是空房，新增一条房间存量信息
						if(gst_new_room_stat.indexOf('V') >= 0){
							RoomNum roomNum = new RoomNum();
							roomNum.setRoomChkid(0);
							roomNum.setCheckId(Integer.parseInt(check_id));// 需要咨询徐工
							roomNum.setRoomId(room_id);
							roomNum.setReachDate(hotelDate == null ? DateUtils.getStartDate(new Date()) : hotelDate);
							roomNum.setLeaveDate(guestdoc.getLeaveDate());
							roomNum.setFlag(1);
							roomNum.setStatus(0);
							roomNum.setOperId(oper.getOperId());
							roomNum.setOperTime(DateUtils.getCurrSysDate());
							roomNumDao.save(roomNum);
						}else{//如果不是空房，修改原本的房间存量信息：把原本存量信息原有的check_id变更为当前住户的check_id
							List<RoomNum> rnList = roomNumDao.getRoomNumByRoomId(room_id);
							for (RoomNum roomNum : rnList) {
								roomNum.setCheckId(Integer.parseInt(check_id));
								roomNum.setOperId(oper.getOperId());
								roomNum.setOperTime(DateUtils.getCurrSysDate());
								roomNumDao.update(roomNum);
							}
						}
					}
				}else{//住客房间号不改变
					//如果住客从“同住”变为“主人”，修改原本房间存量信息
					if(!chk_ext.equals(chk_ext_old) && chk_ext.equals("1")){
						List<RoomNum> rnList = roomNumDao.getRoomNumByRoomId(room_id);
						for (RoomNum roomNum : rnList) {
							roomNum.setCheckId(Integer.parseInt(check_id));
							roomNum.setOperId(oper.getOperId());
							roomNum.setOperTime(DateUtils.getCurrSysDate());
							roomNumDao.update(roomNum);
						}
					}
				}
			}
		}
		//房间A状态是否改变
		String modify_curr_statA = "";
		if(curr_statA.indexOf('V') >= 0 && !guest_numA.equals("0")){
			modify_curr_statA = "O"+curr_statA.substring(1);
		}
		if(curr_statA.indexOf('O') >= 0 && guest_numA.equals("0")){
			modify_curr_statA = ipflag ? "VDP" : "VD";
			List<RoomNum> rnList = roomNumDao.getRoomNumByRoomId(room_idA);
			for (RoomNum roomNum : rnList) {
				roomNum.setStatus(1);
				roomNumDao.update(roomNum);
//				List<RoomsDiary> rdList = roomsDiaryDao.getList("roomChkid", roomNum.getRoomChkid());
//				if(rdList.size() > 0){
//					for (int i = 0; i < rdList.size(); i++) {
//						//把当前酒店日期之后的房间存量信息删除
//						if(rdList.get(i).getHotelDate().compareTo(hotelDate) >= 0){
//							roomsDiaryDao.delete(rdList.get(i));
//						}
//					}
//				}
			}
		}
		if(modify_curr_statA.length() > 0){
			List<Rooms> roomsAList = roomsDao.getList("roomId", room_idA);
			for (int i = 0; i < roomsAList.size(); i++) {
				Rooms rooms = roomsAList.get(i);
				rooms.setCurrStat(modify_curr_statA);
				rooms.setModiStat(curr_statA);
				rooms.setModiTime(DateUtils.getCurrSysDate());
				rooms.setModiOperid(oper.getOperId());//暂时不确定
				roomsDao.update(rooms);
			}
		}
		
		//房间B状态是否改变
		String modify_curr_statB = "";
		//若房间状态原本是空房并且换进了住客，则房间状态修改为住房
		if(curr_statB.indexOf('V') >= 0 && !guest_numB.equals("0")){
			modify_curr_statB = "O"+curr_statB.substring(1);
		}
		//若房间状态原本是住房并且换走了全部住客，则房间状态修改为空房
		if(curr_statB.indexOf('O') >= 0 && guest_numB.equals("0")){
			modify_curr_statB = ipflag ? "VDP" : "VD";
			List<RoomNum> rnList = roomNumDao.getRoomNumByRoomId(room_idB);
			for (RoomNum roomNum : rnList) {
				roomNum.setStatus(1);
				roomNumDao.update(roomNum);
//				List<RoomsDiary> rdList = roomsDiaryDao.getList("roomChkid", roomNum.getRoomChkid());
//				if(rdList.size() > 0){
//					for (int i = 0; i < rdList.size(); i++) {
//						if(rdList.get(i).getHotelDate().compareTo(hotelDate) >= 0){
//							roomsDiaryDao.delete(rdList.get(i));
//						}
//					}
//				}
			}
		}
		if(modify_curr_statB.length() > 0){
			List<Rooms> roomsBList = roomsDao.getList("roomId", room_idB);
			for (int i = 0; i < roomsBList.size(); i++) {
				Rooms rooms = roomsBList.get(i);
				rooms.setCurrStat(modify_curr_statB);
				rooms.setModiStat(curr_statB);
				rooms.setModiTime(DateUtils.getCurrSysDate());
				rooms.setModiOperid(oper.getOperId());//暂时不确定
				roomsDao.update(rooms);
			}
		}
	}

	@Override
	public void saveRoomToleave(JSONArray data, boolean if_bdate_flag, Operator oper) throws Exception {
		Date sysTime = DateUtils.getCurrSysDate();
		
		for (Object obj : data) {
			JSONObject dataObj = (JSONObject)obj;
			//住客注册号
			String check_id = dataObj.getString("check_id").trim();
			//住客新的离店日期
			String leave_date = dataObj.getString("leave_date").trim();
			
			//取得住客信息
			Guestdoc guestdoc = dao.get(Integer.parseInt(check_id));
			//修改住客信息
			guestdoc.setLeaveDate(DateUtils.dateFormat2.parse(leave_date));
			guestdoc.setLeaveDate0(DateUtils.dateFormat2.parse(leave_date));
			short updateTimes = (short)(guestdoc.getUpdateTimes()+(short)1);
			guestdoc.setUpdateTimes(updateTimes);
			guestdoc.setLastTime(new Date());
			guestdoc.setLastOper(oper.getOperId());
			dao.update(guestdoc);
			
			//住客是否房主
			String chk_ext = dataObj.getString("chk_ext").trim();
			//住客房号
			String room_id = dataObj.getString("room_id").trim();
			//住客是房主，更新房间存量信息
			if("1".equals(chk_ext)){
				//取得房间存量信息
				List<RoomNum> rnList = roomNumDao.getRoomNumByRoomId(room_id);
				//修改房间存量信息的离店日期
				for (RoomNum roomNum : rnList) {
					roomNum.setLeaveDate(DateUtils.dateFormat2.parse(leave_date));
					roomNum.setOperTime(sysTime);
					roomNum.setOperId(oper.getOperId());
					roomNumDao.update(roomNum);
				}
			}
			
			//提交的时候选择是否修改分账日期
			if(if_bdate_flag){
				//住客设定的是否修改分账日期
				if(guestdoc.getIfBdate()){
					//修改分账日期
					BPaied bpaied = bPaiedDao.get(guestdoc.getCheckId());
					if(bpaied != null){
						bpaied.setEndDate(DateUtils.dateFormat2.parse(leave_date));
						bpaied.setUpdateTimes(bpaied.getUpdateTimes()+1);
						bpaied.setOperTime(sysTime);
						bpaied.setOperId(oper.getOperId());
						bPaiedDao.update(bpaied);
					}
				}
			}
		}
	}

	@Override
	public AjaxJson checkBillsInleaveHotel(String checkIds) {
		AjaxJson ajaxJson = new AjaxJson();
		String[] checkArr = checkIds.split(",");
		StringBuilder sb = new StringBuilder();
		if(checkArr!=null && checkArr.length>0){
			Guestdoc guestdoc = null;
			Integer billid = null;
			List<Bills> bills = null;
			String[] props = {"billId","okFlag"};
			Object[] vals = new Object[2];
			for(String checkId:checkArr){
				guestdoc = dao.get(Integer.valueOf(checkId));
				//判断房间客人A账目是否结完
				billid = guestdoc.getBillaId();
				vals[0] = billid;
				vals[1] = "0";
				bills = billsDao.getList(props, vals);
				if(bills!=null && bills.size()>0){
					sb.append(guestdoc.getGstNamec()+",");
				}
			}
		}
		if(StringUtils.isNotEmpty(sb.toString())){
			ajaxJson.setObj(sb.toString());
			ajaxJson.setSuccess(false);
		}
		return ajaxJson;
	}
	

	@Override
	public void leaveHotel(String checkIds,String roomIds) {
		if(StringUtils.isEmpty(checkIds) || StringUtils.isEmpty(roomIds)) return;
		String[] ids = checkIds.split(",");
		String[] rids = roomIds.split(",");
		//更新离店状态
		Guestdoc guestdoc = null;
		for (String checkId : ids) {
			guestdoc = dao.get(Integer.valueOf(checkId));
			guestdoc.setChkStat("O");
			guestdoc.setLeaveDate(new Date());
			dao.update(guestdoc);
		}
		//查看房间是否还有在住人员，如果无则修改房间状态
		List<Guestdoc> guestList = null;
		String[] properties = {"roomId","chkStat"};
		String[] values = new String[2];
		Rooms rooms = null;
		List<RoomNum> roomNumList = null;
		String[] props = { "roomId", "flag" };
		Object[] vals = new Object[2];
		for (String roomId : rids) {
			values[0] = roomId;
			values[1] = "I";
			guestList = dao.getList(properties, values);
			if (guestList == null || guestList.size() == 0) {
				rooms = roomsDao.get("roomId", roomId);
				boolean isIp = paramDao.GetIPFlag();
				if (isIp) {
					rooms.setCurrStat("VDP");
				} else {
					rooms.setCurrStat("VD");
				}
				roomsDao.update(rooms);
				// 修改房间存量表状态
				vals[0] = roomId;
				vals[1] = 1;
				roomNumList = roomNumDao.getList(props, vals);
				if (roomNumList != null && roomNumList.size() > 0) {
					for (RoomNum roomNum : roomNumList) {
						roomNum.setStatus(1);
						roomNumDao.update(roomNum);
					}
				}

			}
		}
		
	}
	@Override
	public String getGuestDocDetailIn(Integer checkId,JSONArray array,String operId,String withId) {
		//获取登记号
		int newCheckId = 0;
		//新插入新的guestdoc记录
		if(array!=null && array.size()>0){
			Guestdoc guestdoc = null;
			JSONArray roomArray = null;
			JSONObject jsonObject = null;
			JSONObject roomObject = null;
			Parameter p = parameterSvc.get(1);
			Date leaveDate = null;
			Rooms rooms = null;
			BookRoomCheckin checkin = null;
			boolean isIp = paramDao.GetIPFlag();
			String ifSaveRoom = null;
			String roomId = null;
			RoomNum roomNum = null;
			String[] numProps = {"checkId","roomId","status"};
			Object[] numVals = new Object[3];
			String bookId = null;
			BookRoom bookRoom = null;
			String[] brProps = {"bookRoomId","checkId"};
			Object[] vals = new Object[2];
			boolean payManFlag = false;
			String bookList = null;
			//初始化国籍
			Hcountry hcountry = hcountryDao.get("codeNamec", "中国");
			//初始化民族
			Hfolk hfolk = hfolkDao.get("codeNamec","汉族");
			
			for(int i=0;i<array.size();i++){
				//留房数
				int saveNum = 0;
				jsonObject = JSONObject.parseObject(array.getString(i));
				roomArray = JSONObject.parseArray(jsonObject.getString("roomIds"));
				bookId = jsonObject.getString("bookId");
				//更新抵达房数及留房数
				vals[0] = Integer.valueOf(bookId);
				vals[1] = checkId;
				bookRoom = bookRoomDao.get(brProps, vals);
				if(roomArray!=null && roomArray.size()>0){
					checkin = new BookRoomCheckin();
					for(int j=0;j<roomArray.size();j++){
						roomObject = JSONObject.parseObject(roomArray.getString(j));
						//是否为留房
						ifSaveRoom = roomObject.getString("ifSaveRoom");
						roomId = roomObject.getString("roomId");
						Parameter param= paramDao.get(1);
						//获取登记号
						int id = param.getPara1()+1;
						param.setPara1(param.getPara1()+1);
						//修改房间状态
						rooms = roomsDao.get("roomId", roomId);
						//获取A帐号
						Parameter parama= paramDao.get(2);
						int billaid = parama.getPara1()+1;
						parama.setPara1(parama.getPara1()+1);
						if(i==0&& j==0){
							GuestSearchVO searchVO = new GuestSearchVO();
							searchVO.setWithId(withId);
							searchVO.setChkStat("I");
							List list = dao.getGuestDocList(searchVO);
							if(list!=null && list.size()>0){
								Map map = null;
								for(int k=0;k<list.size();k++){
									map = (Map)list.get(i);
									String paymanflag = (String)map.get("payman_flag");
									if("1".equals(paymanflag)){
										payManFlag = true;
										break;
									}
								}
							}
							guestdoc = dao.get(checkId);
							bookList = guestdoc.getBookList();
							leaveDate = guestdoc.getLeaveDate();
							Guestdoc gd = guestdoc;
							dao.evict(guestdoc);
							if(payManFlag){
								gd.setPaymanFlag(0);
							}else{
								gd.setPaymanFlag(1);
							}
							gd.setChkExt("1");
							gd.setSex("0260");
							gd.setCheckId(id);
							gd.setRoomId(roomId);
							gd.setReachDate(p.getPara3());
							gd.setChkStat("I");
							gd.setChkOperid(operId);
							gd.setBillaId(billaid);
							gd.setLeaveDate(leaveDate);
							gd.setLeaveDate0(leaveDate);
							dao.save(gd);
							Guestdoc gdnew = dao.get(checkId);
							if(!"A".equals(gdnew.getBookStat())){
								gdnew.setBookStat("R");
								dao.update(gdnew);
							}
							newCheckId = id;
						}else{
							Guestdoc newDoc = new Guestdoc();
							newDoc.setReachDate(p.getPara3());
							newDoc.setLeaveDate(leaveDate);
							newDoc.setLeaveDate0(leaveDate);
							newDoc.setChkExt("1");
							newDoc.setChkStat("I");
							newDoc.setCheckId(id);
							newDoc.setRoomId(roomId);
							newDoc.setSex("0260");
							newDoc.setPaymanFlag(0);
							newDoc.setBookStat("R");
							newDoc.setBillaId(billaid);
							newDoc.setChkOperid(operId);
							newDoc.setBillbId(Integer.valueOf(withId));
							newDoc.setHousePay(true);
							newDoc.setFreeSvc(true);
							newDoc.setIfFgst(true);
							newDoc.setWithId(Integer.valueOf(withId));
							newDoc.setGstNamec("unknown");
							newDoc.setBookList(bookList);
							dao.save(newDoc);
						}
						paramDao.update(param);
						paramDao.update(parama);
						if("0".equals(ifSaveRoom)){
							if(0==bookRoom.getStatus()){
								numVals[0] = checkId;
								numVals[1] = roomId;
								numVals[2] = 0;
								roomNum = roomNumDao.get(numProps, numVals);
								if(roomNum!=null){
									roomsDiaryDao.deleteRoomsDiary(roomNum.getRoomChkid(),Integer.valueOf(checkId),roomId);
									RoomNum roomNum2 = roomNum;
									roomNumDao.evict(roomNum);
									roomNum2.setFlag(1);
									roomNumDao.save(roomNum2);
								}
								saveNum++;
							}
							checkin.setFlag("L");
						}else if("1".equals(ifSaveRoom)){
							roomNum = new RoomNum();
							roomNum.setBookId(Integer.valueOf(bookId));
							roomNum.setCheckId(id);
							roomNum.setFlag(1);
							roomNum.setReachDate(p.getPara3());
							roomNum.setLeaveDate(leaveDate);
							roomNum.setOperId(operId);
							roomNum.setOperTime(new Date());
							roomNum.setStatus(0);
							roomNum.setRoomId(roomId);
							roomNum.setRoomChkid(0);
//							//房价设置
							//roomNum.setRoomPrice(roomPrice)
							roomNumDao.save(roomNum);
							checkin.setFlag("B");
						}
						//修改房间状态
						rooms = roomsDao.get("roomId", roomId);
						if (isIp) {
							rooms.setCurrStat("OCI");
						} else {
							rooms.setCurrStat("OC");
						}
						roomsDao.update(rooms);
//						//插入book_room_checkin
						checkin.setStatus(0);
						checkin.setRoomLinkId(Integer.valueOf(bookId));
						checkin.setOperId(operId);
						checkin.setOperTime(new Date());
						checkin.setRoomId(roomId);
						bookRoomCheckInDao.save(checkin);
					}
					bookRoom.setReachNum((bookRoom.getReachNum()==null?0:bookRoom.getReachNum())+roomArray.size());
					if(0==bookRoom.getStatus()){
						bookRoom.setSaveNum((bookRoom.getSaveNum()==null?0:bookRoom.getSaveNum())-saveNum);
					}
					bookRoomDao.update(bookRoom);
				}
			}
		}
		return String.valueOf(newCheckId);
	}
//	@Override
//	public int getNewCheckid() {
//		
//		return checkid;
//	}

	@Override
	public Map checkOut(String roomIds,String reachDate,String leaveDate,String operId) {
		// TODO Auto-generated method stub
		Map map = new HashMap();
		StringBuilder sb = new StringBuilder();
		//初始化国籍
		Hcountry hcountry = hcountryDao.get("codeNamec", "中国");
		//初始化民族
		Hfolk hfolk = hfolkDao.get("codeNamec","汉族");
		String[] props = {"status","checkType"};
		Integer[] vals = {0,1};
		List<HgstOriType> oriTypes = hgstOriTypeDao.getList(props, vals);
		if(oriTypes!=null && oriTypes.size()>0){
			for(HgstOriType oriType:oriTypes ){
				sb.append("'"+oriType.getCodeId().trim()+"',");
			}
		}
		StringBuilder builder = new StringBuilder();
		List hgstOris = dao.getGstOriByType(sb.toString().substring(0,sb.toString().length()-1));
		List<Hsettle> hsettles = hsettleDao.getList("status", 0);
		if(StringUtils.isNotEmpty(roomIds)){
			String[] roomArr  = roomIds.split(",");
			if(roomArr!=null && roomArr.length>0){
				Guestdoc guestdoc = null;
				RoomNum roomNum = null;
				Rooms rooms = null;
				boolean isIp = paramDao.GetIPFlag();
				Parameter paramb= paramDao.get(2);
				int billbId = paramb.getPara1()+1;
				paramb.setPara1(paramb.getPara1()+1);
				paramDao.update(paramb);
				Parameter parama= paramDao.get(2);
				for(int i=0;i<roomArr.length;i++){
					//新增住客
					Parameter param= paramDao.get(1);
					//获取登记号
					int id = param.getPara1()+1;
					param.setPara1(param.getPara1()+1);
					guestdoc = new Guestdoc();
					int billaid = parama.getPara1()+1;
					parama.setPara1(parama.getPara1() + 1);
					paramDao.update(parama);
					if(i==0){
						map.put("withId", billbId);
						guestdoc.setPaymanFlag(1);
					}else{
						guestdoc.setPaymanFlag(0);
					}
					guestdoc.setCheckId(id);
					guestdoc.setChkExt("1");
					guestdoc.setSex("0260");
					guestdoc.setRoomId(roomArr[i]);
					guestdoc.setReachDate(DateUtils.StrToDate(reachDate));
					guestdoc.setChkStat("I");
					guestdoc.setLeaveDate(DateUtils.StrToDate(leaveDate));
					guestdoc.setLeaveDate0(DateUtils.StrToDate(leaveDate));
					guestdoc.setBillaId(billaid);
					guestdoc.setBillbId(billbId);
					guestdoc.setGstNamec("unknown");
					guestdoc.setChkOperid(operId);
					guestdoc.setWithId(billbId);
					guestdoc.setHousePay(true);
					guestdoc.setFreeSvc(true);
					guestdoc.setIfFgst(true);
					guestdoc.setNative_("00801");
					guestdoc.setGstKind("004001");
					guestdoc.setRoomPrice(-1d);//入住登记 默认房费为-1
					guestdoc.setNtId(hcountry==null ? null : hcountry.getCodeId());
					guestdoc.setFolk(hfolk==null ? null : hfolk.getCodeId());
					guestdoc.setPayId(hsettles!=null && hsettles.size()>0 ? hsettles.get(0).getCodeId() : null);
					guestdoc.setGstOriId(hgstOris!=null && hgstOris.size()>0 ? ((Map)hgstOris.get(0)).get("code_id").toString() : null);
					dao.save(guestdoc);
					paramDao.update(param);
					roomNum = new RoomNum();
					roomNum.setCheckId(id);
					roomNum.setFlag(1);
					roomNum.setReachDate(DateUtils.StrToDate(reachDate));
					roomNum.setLeaveDate(DateUtils.StrToDate(leaveDate));
					roomNum.setOperId(operId);
					roomNum.setOperTime(new Date());
					roomNum.setStatus(0);
					roomNum.setRoomId(roomArr[i]);
					roomNum.setRoomChkid(0);
//					//房价设置
					//roomNum.setRoomPrice(roomPrice)
					roomNumDao.save(roomNum);
					
					//修改房间状态
					rooms = roomsDao.get("roomId", roomArr[i]);
					if (isIp) {
						rooms.setCurrStat("OCI");
					} else {
						rooms.setCurrStat("OC");
					}
					roomsDao.update(rooms);
					builder.append(id+","+roomArr[i]+"|");
				}
			}
		}
		map.put("info", builder.toString().substring(0, builder.toString().length()-1));
		return map;
	}

	@Override
	public Map<String, Object> getGuestDocInf(Integer checkId) {
		// TODO Auto-generated method stub
		return dao.getGuestDocInf(checkId);
	}

	@Override
	public Map<String, Object> getArrearsByRoomId(String roomId) {
		
		return dao.getArrearsByRoomId(roomId);
	}

	@Override
	public List getGstOriByType(String codeIds) {
		return dao.getGstOriByType(codeIds);
	}

	@Override
	public List<?> findRoomPriceListBy(Integer checkId , Integer withId) {
		if(checkId == null && withId == null)
			return null;
		else{
			return dao.findRoomPriceListBy(checkId,withId);
		}
	}

	@Override
	public void saveCheckIn(Model model,String roomIds,Map map) {
		
		String roomInfMap = map.get("info").toString();
		String[] roomInf = null;//入住信息
		String checkId= null;//客户信息checkId
		String withId = map.get("withId").toString();//客户with_id
		if(roomInfMap!=null){
			roomInf = roomInfMap.split(",");
			checkId = roomInf[0];
		}
		//非空判断
		//Map checkInMap = null;//入住信息
		GuestDetailVo guestDetalVo = null;
		List roomList = null;//房间列表
		List guestList = null;//客户列表
		Map m = null;
		try {
			if(checkId!=null && withId!=null){
				guestDetalVo = dao.getGuestDetailByCheckId(Integer.parseInt(checkId));
				//设置b账务信息
				BPaied bPaied = bPaiedDao.get(guestDetalVo.getCheckId());
				if(bPaied!=null){
					guestDetalVo.setIsBpaid(true);
				}
				roomList = getRoomsList(withId);
				//房间客户信息
				GuestSearchVO searchVO = new GuestSearchVO();
				if(StringUtils.isNotEmpty(roomIds)){
					if(roomIds.contains(",")){
						searchVO.setRoomId(roomIds.substring(0,roomIds.indexOf(",")));
					}else{
						searchVO.setRoomId(roomIds);
					}
					
				}
				if(StringUtils.isNotEmpty(withId)){
					searchVO.setWithId(withId);
					guestList = getGuestDocList(searchVO);
				}else if(StringUtils.isEmpty(withId)){
					log.error("数据错误 没有with_id");
				}
				//searchVO.setChkStat("I");//住客状态为在住
				
			}
			model.addAttribute("detail",guestDetalVo);
			model.addAttribute("guestdetail",JSON.toJSON(guestDetalVo));
			model.addAttribute("rooms", JSON.toJSON(roomList));
			model.addAttribute("guests", JSON.toJSON(guestList));
			//获取主人离店日期
			for(int i = 0;i<guestList.size();i++){
				m = (Map)guestList.get(i);
				String chkext = m.get("chk_ext").toString();
				if("1".equals(chkext)){
					model.addAttribute("extLeaveDate", m.get("leave_date"));
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.error("入住登记出错 rooms=[" + roomIds  + "]");
		}
	}

	@Override
	public GuestDetailVo getGuestDetail(Integer checkId) {
		return dao.getGuestDetailByCheckId(checkId);
	}

	@Override
	public String[] getRoomIds(Guestdoc entity) {
		Integer withId = entity.getWithId();
		String[] propertyName = {"withId","chkExt","chkStat"};
		Object[] values = {withId,"1","I"}; 
		List<Guestdoc> guests = this.getList(propertyName, values);
		if(guests==null || guests.size()==0){
			return null;
		}
		String[] roomIDS = new String[guests.size()];
		for(int i=0;i<guests.size();i++){
			Guestdoc g = guests.get(i);
			roomIDS[i] = g.getRoomId().trim();
		}
		return roomIDS;
	}

	@Override
	public List getInvalidBIlls(Integer billType, String billId,
			String showType, String consId, String startDate, String endDate) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Map<String, String>> getRoomBillInfo(String roomIds) {
		String roomId = null;
		if(roomIds.contains(",")){
			roomId = roomIds.split(",")[0];
		}else{
			roomId = roomIds;
		}
		List<Map<String, String>> result = new ArrayList<Map<String,String>>();
		//查询当前房屋主人
		Object[] vals = {"I",roomId,"1"};
		String[] nams = {"chkStat","roomId","chkExt"};
		Guestdoc guestDoc = dao.get(nams,vals);
		if(guestDoc==null){
			log.error("roomIds=" + roomIds + " 数据错误！");
			return result;
		}
		Integer whitId = guestDoc.getWithId();
		//查询付费人
		String[] payNames = {"chkStat","paymanFlag","withId"};
		Object[] payValues = {"I",1,whitId};
		Guestdoc payGuest = dao.get(payNames, payValues);
		if(payGuest==null){
			log.error("whitId = " + whitId + " 的房间没有主付费人！");
			return result;
		}
		String payRoomId = payGuest.getRoomId();
		//查询房屋主人信息
		String[] perties = {"withId","chkExt","chkStat"};
		Object[] values = {whitId,"1","I"};
		List<Guestdoc> guests = dao.getList(perties, values);
		//迭代计算账务信息
		for(Guestdoc g:guests){
			Map<String, String> map = new HashMap<String, String>();
			String gRoomID = g.getRoomId();
			Double bill = dao.getRoomBillInfo(gRoomID);
			map.put("room_id", gRoomID);
			map.put("sum", bill.toString());
			map.put("payman_flag", "0");
			result.add(map);
			if(gRoomID.trim().equals(payRoomId.trim())){
				
				//B账信息
				Map<String, String> bmap = new HashMap<String, String>();
				GstBill gstBill = gstBillDao.get(g.getWithId());
				if(gstBill!=null){
					bmap.put("room_id", gRoomID);
					Double bSum = gstBill.getLent() - gstBill.getBorrow();
					bmap.put("sum", bSum.toString());
				}else{
					bmap.put("room_id", gRoomID);
					bmap.put("sum", "0.00");
					
				}
				bmap.put("payman_flag", "1");
				result.add(bmap);
			}
		}
		return result;
	}
	
	
	public double setMoney(Double money){
		Parameter p = paramDao.get(1);
		BigDecimal n = new BigDecimal(money);
        BigDecimal n2 = n.setScale(p.getPara4().intValue(), BigDecimal.ROUND_HALF_UP);
		return n2.doubleValue();
	}
	
	public Date getBIllsDate(){
		Parameter p = paramDao.get(1);
		String hotelDate = DateUtils.getDate(p.getPara3(),"yyyy/MM/dd");
		String time = DateUtils.getNowTime();
		return DateUtils.StrToFullDate(hotelDate+" "+time);
	}

	@Override
	public List<Map<String, String>> getRoomNoSaleInfo(String roomId,
			String startDate, String endDate) {
		List<Map<String, Object>> list = getNullRoomInf(roomId, startDate, endDate);
		List<Map<String, String>> result = new ArrayList<Map<String,String>>();
		for(Map map :list){
			Map<String, String> reMap = new HashMap<String, String>();
			String rid = map.get("roomid").toString().trim();
			String sDate = map.get("startDate").toString().trim();
			String eDate = map.get("endDate").toString().trim();
			String inf = map.get("info").toString().trim();
			String type = inf.substring(0, inf.indexOf("("));
			String noSaleInfo = inf.substring(inf.indexOf("(")+1, inf.indexOf(")"));
			reMap.put("roomid", rid);
			reMap.put("startDate", sDate);
			reMap.put("endDate", eDate);
			reMap.put("type", type);
			reMap.put("noSaleInfo", noSaleInfo);
			result.add(reMap);
		}
		return result;
	}

	@Override
	public String reserveCheckIn(Model model, String roomIds, Integer withId,Integer checkId,Integer bookId) {
		//当前登录人信息
		Operator operator=AppBaseCfg.getOperator();
		String rid = "";
		String[] roomInfs = roomIds.split(",");
		Guestdoc gd = dao.get(checkId);
		GstBill billB = gstBillDao.get(gd.getBillbId());
		//默认国籍 民族
		StringBuilder sb = new StringBuilder();
		//初始化国籍
		Hcountry hcountry = hcountryDao.get("codeNamec", "中国");
		//初始化民族
		Hfolk hfolk = hfolkDao.get("codeNamec","汉族");
		String[] props = {"status","checkType"};
		Integer[] vals = new Integer[2];
		//更新抵达房数及留房数
		vals[0] = 0;//状态可用
		vals[1] = 1;//类型是来自散客
		List<HgstOriType> oriTypes = hgstOriTypeDao.getList(props, vals);
		if(oriTypes!=null && oriTypes.size()>0){
			for(HgstOriType oriType:oriTypes ){
				sb.append("'"+oriType.getCodeId().trim()+"',");
			}
		}
		List hgstOris = dao.getGstOriByType(sb.toString().substring(0,sb.toString().length()-1));
		List<Hsettle> hsettles = hsettleDao.getList("status", 0);
		//循环组织代码//更新抵达房数及留房数
		vals[0] = Integer.valueOf(bookId);
		vals[1] = checkId;
		int index = 0;//下标
		String[] brProps = {"bookRoomId","checkId"};
		String[] numProps = {"checkId","roomId","status"};
		BookRoom bookRoom = bookRoomDao.get(brProps, vals);
		int saveNum = 0;
		for(String rmInf:roomInfs){
			String rmId = rmInf.substring(0, rmInf.indexOf("|"));
			String isSaveRoom = rmInf.substring(rmInf.indexOf("|") + 1, rmInf.length());
			rid += rmId + ",";
			//处理数据 修改房间状态
			Parameter param= paramDao.get(1);
			int id = param.getPara1()+1;
			param.setPara1(param.getPara1()+1);
			Rooms room = roomsDao.get("roomId", rmId);
			BookRoomCheckin checkin = new BookRoomCheckin();
			//设置A账务
			Parameter parama= paramDao.get(2);
			int billaid = parama.getPara1()+1;
			parama.setPara1(parama.getPara1()+1);
			//设置客户信息
			Guestdoc guestDoc = new Guestdoc();
			guestDoc.setCheckId(id);
			guestDoc.setChkExt("1");
			guestDoc.setSex("0260");
			if(index==0){
				String[] serchPerties = {"paymanFlag","withId"};
				Object[] serchValues = {1,billB.getBillId()};
				Guestdoc entity = dao.get(serchPerties, serchValues);
				if(entity==null){
					guestDoc.setPaymanFlag(1);
				}else{
					guestDoc.setPaymanFlag(0);
				}
				model.addAttribute("checkRoom", rmId);
				model.addAttribute("chkStat", "I");
				guestDoc.setGstNamec(gd.getGstNamec());
			}else{
				guestDoc.setPaymanFlag(0);
				guestDoc.setGstNamec("unknown");
			}
			guestDoc.setCheckId(id);
			guestDoc.setChkExt("1");
			guestDoc.setSex("0260");
			guestDoc.setRoomId(rmId);
			guestDoc.setReachDate(gd.getReachDate());
			guestDoc.setChkStat("I");
			guestDoc.setLeaveDate(bookRoom.getLeaveDate());
			guestDoc.setLeaveDate0(gd.getLeaveDate0());
			guestDoc.setBillaId(billaid);
			guestDoc.setBillbId(billB.getBillId());
			guestDoc.setChkOperid(operator.getOperId());
			guestDoc.setWithId(billB.getBillId());
			guestDoc.setHousePay(true);
			guestDoc.setFreeSvc(true);
			guestDoc.setIfFgst(true);
			guestDoc.setNative_("00801");
			guestDoc.setGstKind("004001");
			guestDoc.setRoomPrice(-1d);//
			guestDoc.setNtId(hcountry==null ? null : hcountry.getCodeId());//国家
			guestDoc.setFolk(hfolk==null ? null : hfolk.getCodeId().trim());//民族
			guestDoc.setPayId(hsettles!=null && hsettles.size()>0 ? hsettles.get(0).getCodeId() : null);//付款方式
			guestDoc.setGstOriId(hgstOris!=null && hgstOris.size()>0 ? ((Map)hgstOris.get(0)).get("code_id").toString() : null);
			//保存客户信息
			dao.save(guestDoc);
			parama.setPara1(parama.getPara1()+1);
			paramDao.update(param);
			paramDao.update(parama);
			RoomNum roomNum = new RoomNum();
			roomNum.setCheckId(id);
			roomNum.setFlag(1);
			roomNum.setReachDate(gd.getReachDate());
			roomNum.setLeaveDate(gd.getLeaveDate());
			roomNum.setOperId(operator.getOperId());
			roomNum.setOperTime(new Date());
			roomNum.setStatus(0);
			roomNum.setRoomId(rid);
			roomNum.setRoomChkid(0);
			Object[] numVals = new Object[3];
			if("0".equals(isSaveRoom)){
				if(0==bookRoom.getStatus()){
					numVals[0] = checkId;
					numVals[1] = rmId;
					numVals[2] = 0;
					roomNum = roomNumDao.get(numProps, numVals);
					if(roomNum!=null){
						roomsDiaryDao.deleteRoomsDiary(roomNum.getRoomChkid(),Integer.valueOf(checkId),rid);
						RoomNum roomNum2 = roomNum;
						roomNumDao.evict(roomNum);
						roomNum2.setFlag(1);
						roomNumDao.save(roomNum2);
					}
					saveNum++;
				}
				checkin.setFlag("L");
			}else if("1".equals(isSaveRoom)){
				roomNum = new RoomNum();
				roomNum.setBookId(Integer.valueOf(bookId));
				roomNum.setCheckId(id);
				roomNum.setFlag(1);
				roomNum.setReachDate(param.getPara3());
				roomNum.setLeaveDate(gd.getLeaveDate());
				roomNum.setOperId(operator.getOperId());
				roomNum.setOperTime(new Date());
				roomNum.setStatus(0);
				roomNum.setRoomId(rmId);
				roomNum.setRoomChkid(0);
//				//房价设置
				//roomNum.setRoomPrice(roomPrice)
				roomNumDao.save(roomNum);
				checkin.setFlag("B");
			}
			//roomNum.setRoomPrice(roomPrice)
			roomNumDao.save(roomNum);
			room = roomsDao.get("roomId", rmId);
			boolean isIp = paramDao.GetIPFlag();
			if (isIp) {
				room.setCurrStat("OCI");
			} else {
				room.setCurrStat("OC");
			}
			roomsDao.update(room);
//			//插入book_room_checkin
			checkin.setStatus(0);
			checkin.setRoomLinkId(Integer.valueOf(bookId));
			checkin.setOperId(operator.getOperId());
			checkin.setOperTime(new Date());
			checkin.setRoomId(rmId);
			bookRoomCheckInDao.save(checkin);
			index++;
		}
		bookRoom.setReachNum((bookRoom.getReachNum()==null?0:bookRoom.getReachNum())+roomInfs.length);
		if(0==bookRoom.getStatus()){
			bookRoom.setSaveNum((bookRoom.getSaveNum()==null?0:bookRoom.getSaveNum())-saveNum);
		}
		bookRoomDao.update(bookRoom);
		
		return creatModleInfo(model, withId);
	}

	@Override
	public String grpCheckIn(Model model, String roomIds, Integer withId,
			Integer checkId, Integer bookId) {
		//团队预定 获取登记号
		int newCheckId = 0;
		GrpDoc grpDoc = null;
		Date leaveDate = null;
		Rooms rooms = null;
		BookRoomCheckin checkin = null;
		String thisBookId = null;
		BookRoom bookRoom = null;
		String[] brProps = {"bookRoomId","checkId"};
		Object[] vals = new Object[2];
		String[] numProps = {"checkId","roomId","status"};
		Object[] numVals = new Object[3];
		Parameter param = paramDao.get(1);
		boolean isIp = paramDao.GetIPFlag();//是否含IP
		int grpCheckid = param.getPara1()+1;//团队ID
		param.setPara1(param.getPara1() + 1);
		paramDao.update(param);
		String[] roomInfs = roomIds.split(",");
		String bookList = null;
		//团代码
		int grpcheckid = 0;
		//团队帐号
		int grpBillId = 0;
		int index = 0;//下标
		
		Operator opter = AppBaseCfg.getOperator();
		int saveNum = 0;
		for(String rmInf:roomInfs){
			int id = param.getPara1()+1;
			param.setPara1(param.getPara1() + 1);
			RoomNum roomNum = null;
			String rmId = rmInf.substring(0, rmInf.indexOf("|"));
			String isSaveRoom = rmInf.substring(rmInf.indexOf("|") + 1, rmInf.length());
			vals[0] = Integer.valueOf(bookId);
			vals[1] = Integer.valueOf(checkId);
			bookRoom = bookRoomDao.get(brProps, vals);
			rooms = roomsDao.get("roomId", rmId);
			Parameter parama= paramDao.get(2);
			int billaid = parama.getPara1()+1;
			parama.setPara1(parama.getPara1()+1);
			Guestdoc newDoc = new Guestdoc();
			if(index==0){
				grpDoc = grpDocDao.get(checkId);
				bookList = grpDoc.getBookList();
				leaveDate = bookRoom.getLeaveDate();
				if("B".equals(grpDoc.getBookStat())){
					//生成新的团队帐号及withid
					Parameter paramb= paramDao.get(2);
					grpBillId = paramb.getPara1()+1;
					paramb.setPara1(paramb.getPara1()+1);
					GrpDoc gd = grpDoc;
					grpDocDao.evict(grpDoc);
					gd.setCheckId(grpCheckid);
					gd.setReachDate(param.getPara3());
					gd.setChkStat("I");
					gd.setChkOperid(opter.getOperId());
					gd.setBookStat(null);
					gd.setBillId(grpBillId);
					gd.setWithId(grpBillId);
					grpDocDao.save(gd);
					grpcheckid = grpCheckid;
					newDoc.setPaymanFlag(1);
				}else{
					String[] props = {"grpId","chkStat"};
					String[] vs = {grpDoc.getGrpId(),"I"};
					GrpDoc doc = grpDocDao.get(props, vs);
					grpcheckid = doc.getCheckId();
					grpBillId = doc.getBillId();
					newDoc.setPaymanFlag(0);
				}
				GrpDoc gdnew = grpDocDao.get(checkId);
				if(!"A".equals(gdnew.getBookStat())){
					gdnew.setBookStat("R");
					grpDocDao.update(gdnew);
				}
				newCheckId = id;
				model.addAttribute("checkRoom", rmId);
				model.addAttribute("chkStat", "I");
			}
			else{
				newDoc.setPaymanFlag(0);
			}
			//设置新增客户信息
			newDoc.setGrpChkid(grpcheckid);
			newDoc.setReachDate(param.getPara3());
			newDoc.setLeaveDate(leaveDate);
			newDoc.setLeaveDate0(leaveDate);
			newDoc.setChkExt("1");
			newDoc.setChkStat("I");
			newDoc.setCheckId(id);
			newDoc.setRoomId(rmId);
			newDoc.setSex("0260");
			newDoc.setBookStat("R");
			newDoc.setBillaId(billaid);
			newDoc.setChkOperid(opter.getOperId());
			newDoc.setBillbId(grpBillId);
			newDoc.setHousePay(true);
			newDoc.setFreeSvc(true);
			newDoc.setIfFgst(true);
			newDoc.setWithId(grpBillId);
			newDoc.setGstNamec("unknown");
			newDoc.setBookList(bookList);
			dao.save(newDoc);
			paramDao.update(param);
			paramDao.update(parama);
			if("0".equals(isSaveRoom)){
				if(0==bookRoom.getStatus()){
					numVals[0] = Integer.valueOf(checkId);
					numVals[1] = rmId;
					numVals[2] = 0;
					roomNum = roomNumDao.get(numProps, numVals);
					if(roomNum!=null){
						roomsDiaryDao.deleteRoomsDiary(roomNum.getRoomChkid(),Integer.valueOf(checkId),rmId);
						RoomNum roomNum2 = roomNum;
						roomNumDao.evict(roomNum);
						roomNum2.setFlag(1);
						roomNumDao.save(roomNum2);
					}
					model.addAttribute("checkRoom", rmId);
					model.addAttribute("chkStat", "I");
					saveNum++;
				}
			}else if("1".equals(isSaveRoom)){
				roomNum = new RoomNum();
				roomNum.setBookId(Integer.valueOf(bookId));
				roomNum.setCheckId(id);
				roomNum.setFlag(1);
				roomNum.setReachDate(param.getPara3());
				roomNum.setLeaveDate(leaveDate);
				roomNum.setOperId(opter.getOperId());
				roomNum.setOperTime(new Date());
				roomNum.setStatus(0);
				roomNum.setRoomId(rmId);
				roomNum.setRoomChkid(0);
//				//房价设置
				//roomNum.setRoomPrice(roomPrice)
				roomNumDao.save(roomNum);
			}
			//修改房间状态
			rooms = roomsDao.get("roomId", rmId);
			if (isIp) {
				rooms.setCurrStat("OCI");
			} else {
				rooms.setCurrStat("OC");
			}
			roomsDao.update(rooms);
//			//插入book_room_checkin
			checkin = new BookRoomCheckin();
			checkin.setFlag("B");
			checkin.setStatus(0);
			checkin.setRoomLinkId(Integer.valueOf(bookId));
			checkin.setOperId(opter.getOperId());
			checkin.setOperTime(new Date());
			checkin.setRoomId(rmId);
			bookRoomCheckInDao.save(checkin);
		}
		bookRoom.setReachNum((bookRoom.getReachNum()==null?0:bookRoom.getReachNum()) + roomInfs.length);
		if(0==bookRoom.getStatus()){
			bookRoom.setSaveNum((bookRoom.getSaveNum()==null?0:bookRoom.getSaveNum())-saveNum);
		}
		bookRoomDao.update(bookRoom);
		return creatModleInfo(model, grpBillId);
	}
	
	/**
	 * @描述 给团队预定和散客预定添加必要信息
	 * */
	private String creatModleInfo(Model model,Integer withId){
		//主客户资料
		String[] searGuestPerties = {"paymanFlag","withId"};
		Object[] searGuestVaules = {1,withId};
		Guestdoc guestDoc = get(searGuestPerties, searGuestVaules);
		String rid = "";
		if(guestDoc!=null){
			String[] roomIds = getRoomIds(guestDoc);
			model.addAttribute("roomIds", roomIds);
			GuestDetailVo guestDetailVo = getGuestDetail(guestDoc.getCheckId());
			//住客信息列表 住房列表
			GuestSearchVO searchVO = new GuestSearchVO();
			searchVO.setRoomId(guestDetailVo.getRoomId());
			searchVO.setChkStat("I");
			searchVO.setWithId(withId.toString());
			List roomList = getRoomsList(withId.toString());
			for(Object rsId :roomList){
				LinkedHashMap<String, String> map = (LinkedHashMap<String, String>) rsId;
				rid += map.get("room_id").trim() + ",";
			}
			if(StringUtils.isNotEmpty(rid) && rid.contains(",")){
				rid = rid.substring(0, rid.length()-1);
			}
			List guestList = getGuestDocList(searchVO);
			model.addAttribute("rooms", JSON.toJSON(roomList));
			model.addAttribute("guests", JSON.toJSON(guestList));
		}
		return rid;
	}
}

