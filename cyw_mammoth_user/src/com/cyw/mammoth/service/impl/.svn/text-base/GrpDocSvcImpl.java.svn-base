package com.cyw.mammoth.service.impl;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.cyw.common.base.service.impl.BaseSvcImpl;
import com.cyw.common.util.DateCompare;
import com.cyw.common.util.DateUtils;
import com.cyw.common.util.StrUtils;
import com.cyw.mammoth.auth.AppBaseCfg;
import com.cyw.mammoth.bean.BPaied;
import com.cyw.mammoth.bean.BookRoom;
import com.cyw.mammoth.bean.BookRoomCheckin;
import com.cyw.mammoth.bean.GrpDoc;
import com.cyw.mammoth.bean.GstBill;
import com.cyw.mammoth.bean.Guestdoc;
import com.cyw.mammoth.bean.HroomType;
import com.cyw.mammoth.bean.Parameter;
import com.cyw.mammoth.bean.RoomNum;
import com.cyw.mammoth.bean.Rooms;
import com.cyw.mammoth.dao.BPaiedDao;
import com.cyw.mammoth.dao.BillsDao;
import com.cyw.mammoth.dao.BookRoomCheckInDao;
import com.cyw.mammoth.dao.BookRoomDao;
import com.cyw.mammoth.dao.GrpDocDao;
import com.cyw.mammoth.dao.GstBillDao;
import com.cyw.mammoth.dao.GstCreditAuthDao;
import com.cyw.mammoth.dao.GuestdocDao;
import com.cyw.mammoth.dao.HconsumeDao;
import com.cyw.mammoth.dao.ParameterDao;
import com.cyw.mammoth.dao.RoomNumDao;
import com.cyw.mammoth.dao.RoomsDao;
import com.cyw.mammoth.dao.RoomsDiaryDao;
import com.cyw.mammoth.dao.TADocDao;
import com.cyw.mammoth.service.BookRoomSvc;
import com.cyw.mammoth.service.GrpDocSvc;
import com.cyw.mammoth.service.GuestdocSvc;
import com.cyw.mammoth.service.ParameterSvc;
import com.cyw.mammoth.vo.BookStateEnum;
import com.cyw.mammoth.vo.GroupBookSearchVo;
import com.cyw.mammoth.vo.GroupGuestUpdateVO;
import com.cyw.mammoth.vo.GrpDocVo;
import com.cyw.mammoth.vo.GuestSearchVO;

@Service
public class GrpDocSvcImpl extends BaseSvcImpl<GrpDoc, Integer> implements GrpDocSvc {
	@Autowired
	private GrpDocDao dao;
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
	GuestdocDao guestdocDao;
	@Autowired
	private RoomsDao roomsDao;
	
	@Autowired
	private BookRoomCheckInDao bookRoomCheckInDao;
	/**
	 * @描述：房间存量
	 * */
	@Autowired
	private RoomNumDao roomNumDao;

	
	@Autowired
	private ParameterSvc parameterSvc;
	@Autowired
	private RoomsDiaryDao roomsDiaryDao;
	@Autowired
	private BPaiedDao bPaiedDao;

	@Resource
	private  TADocDao taDocDao;
	@Resource
	private BookRoomSvc bookRoomSvc;
	
	@Resource
	private GuestdocSvc guestdocSvc;
	@Autowired
	public void setBaseDao(GrpDocDao dao) {
		super.setBaseDao(dao);
	}

	@Override
	public List getgrpdoc(Map resMap) {
		return dao.getgrpdoc(resMap);
	}

	@Override
	public int get_Biil_id_num(int bill_id) {
		return dao.get_Biil_id_num(bill_id);
	}

	@Override
	public List getBillIdList(int bill_id) {
		return dao.getBillIdList(bill_id);
	}

	@Override
	public List getGstCreditAuth(int check_id) {
		return dao.getGstCreditAuth(check_id);
	}

	@Override
	public List<GrpDocVo> getBookRoomList(GroupBookSearchVo searchVo) {
		List<GrpDocVo> newlist = new ArrayList<GrpDocVo>();
		List list = dao.getBookRoomList(searchVo);
		GrpDocVo grpDocVo;
		for (Iterator iterator = list.iterator(); iterator.hasNext();) {
			Object[] objects = (Object[]) iterator.next();
			grpDocVo = new GrpDocVo();
			grpDocVo.setCheckId(objects[0] == null ? -1 : Integer.parseInt(objects[0].toString()));
			grpDocVo.setGrpId(objects[1] == null ? "" : objects[1].toString());
			grpDocVo.setGrpName(objects[2] == null ? "" : objects[2].toString());
			grpDocVo.setBookStat(objects[3] == null ? "" : objects[3].toString());
			grpDocVo.setChkStat(objects[4] == null ? "" : objects[4].toString());
			grpDocVo.setBookList(objects[5] == null ? "" : objects[5].toString());
			
			grpDocVo.setBookRoomId(objects[6] == null ? -1 : Integer.parseInt(objects[6].toString()));
			grpDocVo.setRoomtypeId(objects[7] == null ? "" : objects[7].toString());
			grpDocVo.setBookNum(objects[8] == null ? 0 : Integer.parseInt(objects[8].toString()));
			grpDocVo.setSaveNum(objects[9] == null ? 0 : Integer.parseInt(objects[9].toString()));
			grpDocVo.setReachNum(objects[10] == null ? 0 : Integer.parseInt(objects[10].toString()));
			grpDocVo.setRoomPrice(objects[11] == null ? 0 : Double.parseDouble(objects[11].toString()));
			try {
				grpDocVo.setReachDate(DateUtils.dateFormat2.parse(objects[12].toString()));
				grpDocVo.setLeaveDate(DateUtils.dateFormat2.parse(objects[13].toString()));
			} catch (ParseException e) {
				e.printStackTrace();
			}
			grpDocVo.setStatus(objects[14] == null ? -1 : Integer.parseInt(objects[14].toString()));
			grpDocVo.setCancelNum(objects[15] == null ? 0 : Integer.parseInt(objects[15].toString()));
			grpDocVo.setNoShowNum(objects[16] == null ? 0 : Integer.parseInt(objects[16].toString()));
			
			grpDocVo.setCodeNamec(objects[17] == null ? "" : objects[17].toString());
			newlist.add(grpDocVo);
		}
		return newlist;
	}

	@Override
	public List getSelectRoomToLive(Map map) {
		return dao.getSelectRoomToLive(map);
	}

	@Override
	public List getSelectGrpDoc(int check_id) {
		return dao.getSelectGrpDoc(check_id);
	}
	
	@Override
	public Map getGrpDocDetail(int checkId){
		return dao.getGrpDocDetail(checkId);
	}
	@Override
	public GrpDoc saveForBookRoom(GrpDoc grpdoc, List<BookRoom> brLists, String roomIds, String currBookRoomId) {
		
		List<Date> reachDateList = new ArrayList<Date>();
		List<Date> leaveDateList = new ArrayList<Date>();
		/*if(grpdoc.getReachDate()!=null){
			String strDate=com.cyw.common.util.DateUtil.convertDate2String(grpdoc.getReachDate(), com.cyw.common.util.DateUtil.defaultDatePattern);
			if(!StrUtils.isValidString(grpdoc.getReachTime())){
				grpdoc.setReachTime("00:00");
			}
			String reachDateTime=strDate+" "+grpdoc.getReachTime();
			Date reachDate=com.cyw.common.util.DateUtil.convertStringToDate(com.cyw.common.util.DateUtil.dateTimeShortPattern,reachDateTime);
			grpdoc.setReachDate(reachDate);
		}*/
		
		String operId=AppBaseCfg.getOperator().getOperId();
		grpdoc.setBookOperid(operId);;//预订登记人
		grpdoc.setBookTime(new Date());;//预订登记时间
		grpdoc.setLastOper(operId);//最后修改人
		grpdoc.setLastTime(new Date());//最后修改时间
	
		grpdoc.setBookStat(BookStateEnum.NO_Confirm.value);
		//从参数表中获取登记号
		Parameter param= paramDao.get(1);
		//插入登记号 
		grpdoc.setCheckId(param.getPara1()+1);
		//产生B账号
		Parameter paramb= paramDao.get(2);
		Integer biilBId=paramb.getPara1()+1;
		grpdoc.setBillId(biilBId);
		grpdoc.setWithId(biilBId);
		
		GstBill billB = gstBillDao.get(grpdoc.getBillId());
		Double Blimit=0.00d;
		try{
			Blimit=new Double(grpdoc.getBiilbLimit());
		}catch(Exception e){
			e.printStackTrace();
		}
		if(billB == null){
			billB = new GstBill(biilBId, 0.0, 0.0, null, null, true, null, null, Blimit);
			gstBillDao.merge(billB);
		}else{
			dao.updateLimit(Blimit, biilBId);
		}

		
		if(brLists!=null && brLists.size()>0){
			for (BookRoom bookRoom : brLists) {
				reachDateList.add(bookRoom.getReachDate());
				leaveDateList.add(bookRoom.getLeaveDate());
			}
			Collections.sort(reachDateList, new DateCompare());
			Collections.sort(leaveDateList, new DateCompare());
			grpdoc.setLeaveDate(leaveDateList.get(leaveDateList.size()-1));
		}
		grpdoc.setChangeRate(true);
		grpdoc.setCityLedger(grpdoc.getCityLedger()==null?false:grpdoc.getCityLedger());
		grpdoc.setHousePay(grpdoc.getHousePay()==null?false:grpdoc.getHousePay());
		grpdoc.setHideprice(grpdoc.getHideprice()==null?false:grpdoc.getHideprice());
		grpdoc.setFreeSvc(grpdoc.getFreeSvc()==null?false:grpdoc.getFreeSvc());
		grpdoc.setComputerId("1");
		grpdoc.setGrpRent(1.00d);
		grpdoc.setIddOn(false);
		grpdoc.setIfBpay(false);
		grpdoc.setIfPack(false);
		grpdoc.setLimit(grpdoc.getLimit()==null?1.00d:grpdoc.getLimit());
		grpdoc.setRoomTypeid(StrUtils.isValidString(grpdoc.getRoomTypeid())!=true?"0":grpdoc.getRoomTypeid());
		grpdoc.setMealCode("0");
		grpdoc.setMinibar(false);
		grpdoc.setOperation("1");
		grpdoc.setPrincipal("0");
		grpdoc.setSecret(false);
		grpdoc.setTeleStatus("0");
		grpdoc.setUpdateTimes(new Short("1"));
		grpdoc.setUserId("0");
		grpdoc.setBillOrgId(1);
		dao.save(grpdoc);
		
		param.setPara1(param.getPara1()+1);
		//更新参数表登记号
		paramDao.update(param);
		paramb.setPara1(paramb.getPara1()+2);
		paramDao.update(paramb);
		
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
					BookRoom br = bookRoomDao.get("bookRoomId", bookRoom.getBookRoomId());
					com.cyw.common.util.bean.BeanUtils.beanShallowCopy(br, bookRoom);
					br.setUpdateTimes(br.getUpdateTimes()+1);
					br.setModifyOper(operId);
					br.setModifyTime(new Date());
					bookRoomDao.update(br);
				}else{
					bookRoom.setBookRoomId(0);
					bookRoom.setCheckId(grpdoc.getCheckId());
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
		
		return grpdoc;
	}

	@Override
	public GrpDoc updateForBookRoom(GrpDoc grpdoc, List<BookRoom> brLists, String roomIds, String currBookRoomId) {
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
					}
					//update
					BookRoom br = bookRoomDao.get("bookRoomId", bookRoom.getBookRoomId());
					com.cyw.common.util.bean.BeanUtils.beanShallowCopy(br, bookRoom);
					br.setUpdateTimes(br.getUpdateTimes()+1);
					br.setModifyOper(operId);
					br.setModifyTime(new Date());
					bookRoomDao.update(br);
				}
				if(bookRoom==null || bookRoom.getId()==null){
					//save
					bookRoom.setBookRoomId(0);
					//bookRoom.setId(5);
					bookRoom.setUpdateTimes(0);
					bookRoom.setCheckId(grpdoc.getCheckId());
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
			grpdoc.setReachDate(reachDateList.get(0));
			grpdoc.setLeaveDate(leaveDateList.get(leaveDateList.size()-1));
		}
		if(grpdoc!=null){
			
			//grpdoc.setBookStat("B");
			Double Blimit=0.00d;
			try{
				Blimit=new Double(grpdoc.getBiilbLimit());
				GstBill billB = gstBillDao.get(grpdoc.getBillId());
				if(billB!=null){
					billB.setLimit(Blimit);
					gstBillDao.merge(billB);
				}
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		
		//从参数表中获取登记号
		grpdoc.setLastOper(operId);
		grpdoc.setLastTime(new Date());

		GrpDoc grpdocEntity=dao.get(grpdoc.getCheckId());
		//以下代码，留作痕迹，测试通过后删除，
		/*grpdoc.setCityLedger(grpdoc.getCityLedger()==null?false:grpdoc.getCityLedger());
		grpdoc.setHousePay(grpdoc.getHousePay()==null?false:grpdoc.getHousePay());
		grpdoc.setHideprice(grpdoc.getHideprice()==null?false:grpdoc.getHideprice());
		grpdoc.setFreeSvc(grpdoc.getFreeSvc()==null?false:grpdoc.getFreeSvc());
		//-----临时处理，以后修改
		grpdoc.setChangeRate(grpdocEntity.getChangeRate());
		grpdoc.setComputerId(grpdocEntity.getComputerId());
		grpdoc.setGrpRent(grpdocEntity.getGrpRent());
		grpdoc.setIddOn(grpdocEntity.getIddOn());
		grpdoc.setIfBpay(grpdocEntity.getIfBdate());
		grpdoc.setIfPack(grpdocEntity.getIfPack());
		grpdoc.setLimit(grpdoc.getLimit()==null?1.00d:grpdoc.getLimit());
		grpdoc.setRoomTypeid(StrUtils.isValidString(grpdoc.getRoomTypeid())!=true?"0":grpdoc.getRoomTypeid());
		grpdoc.setMealCode("0");
		grpdoc.setMinibar(false);
		grpdoc.setOperation("1");
		grpdoc.setPrincipal("0");
		grpdoc.setSecret(false);
		grpdoc.setTeleStatus("0");
		grpdoc.setUpdateTimes(new Short("1"));
		grpdoc.setUserId("0");
		grpdoc.setBillOrgId(grpdocEntity.getBillOrgId());
		grpdoc.setWithId(grpdocEntity.getWithId());
		grpdoc.setBillId(grpdocEntity.getBillId());
		grpdoc.setWithId(grpdocEntity.getWithId());
		grpdoc.setBillOrgId(grpdocEntity.getBillOrgId());*/
		
		//-----
		//dao.update(grpdoc);
		com.cyw.common.util.bean.BeanUtils.beanShallowCopy(grpdocEntity, grpdoc);
		GrpDoc entity=dao.merge(grpdocEntity);
		dao.flush();

		return entity;
	}
	@Override
	public void setSplit(BPaied bpaied,String ifBate) {
		BPaied bp = paiedDao.get(bpaied.getCheckId());
		bpaied.setOperTime(new Date());
		//不存在则保存
		if(bp==null && !"".equals(bpaied.getCons())){
			bpaied.setUpdateTimes(0);//设置修改次数默认值
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
	public void updateGrpdocAndGuest(GrpDoc grpDoc,GroupGuestUpdateVO updateVO,BPaied bPaied,String operId) {
		//更新团队资料
		dao.updateGrpdoc(grpDoc);
		//更新团队限额
		GstBill billB = gstBillDao.get(grpDoc.getBillId());
		if(billB == null){
			billB = new GstBill(grpDoc.getBillId(), 0.0, 0.0, null, null, true, null, null, grpDoc.getLimit());
			gstBillDao.save(billB);
		}else{
			guestdocDao.updateLimit(grpDoc.getLimit(), grpDoc.getBillId());
		}
		//团员批量修改
		String[] props = {"grpChkid","chkStat"};
		Object[] vals = {grpDoc.getCheckId(),"I"};
		List<Guestdoc> guestdocs = guestdocDao.getList(props, vals);
		if(guestdocs!=null && guestdocs.size()>0){
			StringBuilder whereIn = new StringBuilder();
			StringBuilder sql = new StringBuilder("");
			for(Guestdoc doc:guestdocs){
				whereIn.append(doc.getCheckId()+",");
			}
			if(updateVO.getIsCountry()!=null && updateVO.getIsCountry()==true){
				if(StringUtils.isEmpty(sql.toString())){
					sql.append(" nt_id="+(updateVO.getCountry()==null?null:"'"+updateVO.getCountry()+"'"));
				}else{
					sql.append(",nt_id="+(updateVO.getCountry()==null?null:"'"+updateVO.getCountry()+"'"));
				}
			}
			if(updateVO.getIsGstKind()!=null && updateVO.getIsGstKind()==true){
				if(StringUtils.isEmpty(sql.toString())){
					sql.append(" gst_kind="+(updateVO.getGstKind()==null?null:"'"+updateVO.getGstKind()+"'"));
				}else{
					sql.append(",gst_kind="+(updateVO.getGstKind()==null?null:"'"+updateVO.getGstKind()+"'"));
				}
			}
			if(updateVO.getIsGstOri()!=null && updateVO.getIsGstOri()==true){
				if(StringUtils.isEmpty(sql.toString())){
					sql.append(" gst_ori_id="+(updateVO.getGstOriId()==null?null:"'"+updateVO.getGstOriId()+"'"));
				}else{
					sql.append(",gst_ori_id="+(updateVO.getGstOriId()==null?null:"'"+updateVO.getGstOriId()+"'"));
				}
			}
			if(updateVO.getIsNative()!=null && updateVO.getIsNative()==true){
				if(StringUtils.isEmpty(sql.toString())){
					sql.append(" native="+(updateVO.getNative_()==null?null:"'"+updateVO.getNative_()+"'"));
				}else{
					sql.append(",native="+(updateVO.getNative_()==null?null:"'"+updateVO.getNative_()+"'"));
				}
			}
			if(updateVO.getIsNotice()!=null && updateVO.getIsNotice()==true){
				if(StringUtils.isEmpty(sql.toString())){
					sql.append(" notice="+(updateVO.getNotice()==null?null:"'"+updateVO.getNotice()+"'"));
				}else{
					sql.append(",notice="+(updateVO.getNotice()==null?null:"'"+updateVO.getNotice()+"'"));
				}
			}
			if(updateVO.getIsOpen()!=null && updateVO.getIsOpen()==true){
				if(StringUtils.isEmpty(sql.toString())){
					sql.append(" city_ledger="+(grpDoc.getCityLedger()?1:0)+",house_pay="+(grpDoc.getHousePay()?1:0)+",free_svc="+(grpDoc.getFreeSvc()?1:0)+",hideprice="+(grpDoc.getHideprice()?1:0)+",if_fgst="+(updateVO.getIfFgst()?1:0));
				}else{
					sql.append(",city_ledger="+(grpDoc.getCityLedger()?1:0)+",house_pay="+(grpDoc.getHousePay()?1:0)+",free_svc="+(grpDoc.getFreeSvc()?1:0)+",hideprice="+(grpDoc.getHideprice()?1:0)+",if_fgst="+(updateVO.getIfFgst()?1:0));
				}
			}
			if(updateVO.getIsSplit()!=null && updateVO.getIsSplit()==true){
				for(Guestdoc doc:guestdocs){
					guestdocSvc.evict(bPaied);
					bPaied.setCheckId(doc.getCheckId());
					bPaied.setOperId(operId);
					bPaied.setUpdateTimes(1);
					guestdocSvc.setSplit(bPaied, grpDoc.getIfBdate()?"1":"0");
				}
			}
			
			if(updateVO.getIsPrice()!=null && updateVO.getIsPrice()==true && StringUtils.isNotEmpty(updateVO.getRoomPrice())){
				String priceSql = " room_price = "+(updateVO.getRoomPrice()==null?null:"'"+updateVO.getRoomPrice()+"'");
				dao.updateGuest(priceSql+" where chk_ext='1' and check_id in("+whereIn.toString().substring(0, whereIn.toString().length()-1)+")");
			}
			
			String resultSql = sql.toString()+" where check_id in("+whereIn.toString().substring(0, whereIn.toString().length()-1)+")";
			dao.updateGuest(resultSql);
		}
	}

	@Override
	public String getGrpDocDetailIn(String checkId, JSONArray array,
			String operId, String withId) {
		//获取登记号
		int newCheckId = 0;
		//新插入新的guestdoc记录
		if(array!=null && array.size()>0){
			GrpDoc grpDoc = null;
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
		//	boolean payManFlag = false;
			String bookList = null;
			Parameter gp= paramDao.get(1);
			int grpCheckid = gp.getPara1()+1;
			gp.setPara1(gp.getPara1()+1);
			paramDao.update(gp);
			//团代码
			int grpcheckid = 0;
			//团队帐号
			int grpBillId = 0;
			for(int i=0;i<array.size();i++){
				//留房数
				int saveNum = 0;
				jsonObject = JSONObject.parseObject(array.getString(i));
				roomArray = JSONObject.parseArray(jsonObject.getString("roomIds"));
				bookId = jsonObject.getString("bookId");
				vals[0] = Integer.valueOf(bookId);
				vals[1] = Integer.valueOf(checkId);
				bookRoom = bookRoomDao.get(brProps, vals);
				if(roomArray!=null && roomArray.size()>0){
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
						Guestdoc newDoc = new Guestdoc();
						if(i==0&& j==0){
//							GuestSearchVO searchVO = new GuestSearchVO();
//							searchVO.setWithId(withId);
//							searchVO.setChkStat("I");
//							List list = guestdocDao.getGuestDocList(searchVO);
//							if(list!=null && list.size()>0){
//								Map map = null;
//								for(int k=0;k<list.size();k++){
//									map = (Map)list.get(i);
//									String paymanflag = (String)map.get("payman_flag");
//									if("1".equals(paymanflag)){
//										payManFlag = true;
//										break;
//									}
//								}
//							}
//							if(payManFlag){
//								newDoc.setPaymanFlag(0);
//							}else{
//								newDoc.setPaymanFlag(1);
//							}
							grpDoc = dao.get(Integer.valueOf(checkId));
							bookList = grpDoc.getBookList();
							leaveDate = grpDoc.getLeaveDate();
							if("B".equals(grpDoc.getBookStat())){
								//生成新的团队帐号及withid
								Parameter paramb= paramDao.get(2);
								grpBillId = paramb.getPara1()+1;
								paramb.setPara1(paramb.getPara1()+1);
								paramDao.update(paramb);
								GrpDoc gd = grpDoc;
								dao.evict(grpDoc);
								gd.setCheckId(grpCheckid);
								gd.setReachDate(p.getPara3());
								gd.setChkStat("I");
								gd.setChkOperid(operId);
								gd.setBookStat(null);
								gd.setBillId(grpBillId);
								gd.setWithId(grpBillId);
								dao.save(gd);
								grpcheckid = grpCheckid;
								newDoc.setPaymanFlag(1);
							}else{
								String[] props = {"grpId","chkStat"};
								String[] vs = {grpDoc.getGrpId(),"I"};
								GrpDoc doc = dao.get(props, vs);
								grpcheckid = doc.getCheckId();
								grpBillId = doc.getBillId();
								newDoc.setPaymanFlag(0);
							}
							GrpDoc gdnew = dao.get(Integer.valueOf(checkId));
							if(!"A".equals(gdnew.getBookStat())){
								gdnew.setBookStat("R");
								dao.update(gdnew);
							}
							newCheckId = id;
						}else{
							newDoc.setPaymanFlag(0);
						}
						newDoc.setGrpChkid(grpcheckid);
						newDoc.setReachDate(p.getPara3());
						newDoc.setLeaveDate(leaveDate);
						newDoc.setLeaveDate0(leaveDate);
						newDoc.setChkExt("1");
						newDoc.setChkStat("I");
						newDoc.setCheckId(id);
						newDoc.setRoomId(roomId);
						newDoc.setSex("0260");
						newDoc.setBookStat("R");
						newDoc.setBillaId(billaid);
						newDoc.setChkOperid(operId);
						newDoc.setBillbId(grpBillId);
						newDoc.setHousePay(true);
						newDoc.setFreeSvc(true);
						newDoc.setIfFgst(true);
						newDoc.setWithId(grpBillId);
						newDoc.setGstNamec("unknown");
						newDoc.setBookList(bookList);
						guestdocDao.save(newDoc);
						paramDao.update(param);
						paramDao.update(parama);
						if("0".equals(ifSaveRoom)){
							if(0==bookRoom.getStatus()){
								numVals[0] = Integer.valueOf(checkId);
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
						checkin = new BookRoomCheckin();
						checkin.setFlag("B");
						checkin.setStatus(0);
						checkin.setRoomLinkId(Integer.valueOf(bookId));
						checkin.setOperId(operId);
						checkin.setOperTime(new Date());
						checkin.setRoomId(roomId);
						bookRoomCheckInDao.save(checkin);
					}
					//更新抵达房数及留房数
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

}
