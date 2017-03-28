package com.cyw.mammoth.bean;

import java.awt.Image;
import java.sql.Blob;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * Guestdoc entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "guestdoc", schema = "dbo", catalog = "mammoth")
public class Guestdoc implements java.io.Serializable {

	// Fields

	private Integer checkId;
	private Integer gstId;
	private String gstNamee="";
	private String gstNamec="";
	private String sex="";
	private Integer age=0;
	private String wrkComp;
	private Integer grpChkid;
	private String notice;
	private Date reachDate;
	private Date leaveDate;
	private Date leaveDate0;
	private Date confirmDate;
	private String confirmOperid;
	private String bookStat;
	private String chkStat;
	private String roomId;
	private String roomTypeid="*";
	private Long roomCharacter;
	private Double roomPrice;
	private Integer paymanFlag=0;
	private String gstOriId="N";
	private String prcSchemeId;
	private String payId="*";
	private Double limit=0.0;
	private String creditId="";
	private String compType="N";
	private String compId;
	private Integer compCd;
	private Boolean cityLedger=false;
	private Boolean housePay=false;
	private Boolean freeSvc=false;
	private Boolean changeRate=false;
	private String guarantor;
	private Boolean secret=false;
	private Boolean hideprice=false;
	private String teleStatus="*";
	private String gstKind="*";
	private String gstFlag="*";
	private String ntId="*";
	private String areaId="*" ;
	private String bookType="*";
	private String bookList;
	private String tele;
	private String mobile;
	private String email;
	private String plateNumber;
	private String crdkindId="*";
	private String crdId;
	private Date crdVld;
	private Date birthday;
	private String visakindId="*";
	private String visaId;
	private String depart;
	private String inPort="*";
	private Date inDate;
	private String reason="*";
	private String addr;
	private String bookOperid;
	private Date bookTime;
	private String chkOperid;
	private Date chkTime;
	private String rechkOperid;
	private Date rechkTime;
	private String lastOper;
	private Date lastTime;
	private String outOper;
	private Date outTime;
	private Integer withId=0;
	private Integer billaId=0;
	private Integer billbId=0;
	private Integer billbOrgId=0;
	private Boolean ifBpay=false;
	private Boolean ifFgst=false;
	private Boolean ifBdate=true;
	private Short gstNum=0;
	private Short updateTimes=0;
	private String bookExt;
	private String chkExt;
	private String connector="";
	private Date useTime;
	private String booker="*";
	private String operation="*";
	private String native_="";
	private String folk;
	private String works;
	private String regAddr;
	private Boolean iddOn=false;
	private String bookOrder="";
	private String vilhotelId;
	private String chainId="";
	private String bookerName="";
	private String lastCashier;
	private Date cashierTime;
	private String reachtime;
	private Boolean noArriveCancel=false;//夜审时是否自动取消未抵留房
	private Boolean isRoomPlan = false ;
	
	
	/**
	 * 临时字段 合约单位名称
	 */
	private String taName;
	/**
	 * 临时字段  订房数
	 */
	private Integer bookNum;
	private String biilbLimit;//B账限额
	private String billbBlance;//B账余额
	// Constructors
	@Column(name = "reach_time", unique = false, nullable = true)
	public String getReachtime() {
		return reachtime;
	}
	public void setReachtime(String reachtime) {
		this.reachtime = reachtime;
	}
	@Transient
	public String getTaName() {
		return taName;
	}
	public void setTaName(String taName) {
		this.taName = taName;
	}
	@Transient
	public Integer getBookNum() {
		return bookNum;
	}
	public void setBookNum(Integer bookNum) {
		this.bookNum = bookNum;
	}
	/** default constructor */
	public Guestdoc() {
	}
	/** full constructor */
	public Guestdoc(Integer checkId, Integer gstId, String gstNamee,
			String gstNamec, String sex, Integer age, String wrkComp,
			Integer grpChkid, String notice, Date reachDate,
			Date leaveDate, Date leaveDate0, Date confirmDate,String confirmOperid,
			String bookStat, String chkStat, String roomId, String roomTypeid,
			Long roomCharacter, Double roomPrice, Integer paymanFlag,
			String gstOriId, String prcSchemeId, String payId, Double limit,
			String creditId, String compType, String compId, Integer compCd,
			Boolean cityLedger, Boolean housePay, Boolean freeSvc,
			Boolean changeRate, String guarantor, Boolean secret,
			Boolean hideprice, String teleStatus, String gstKind,
			String gstFlag, String ntId, String areaId, String bookType,
			String bookList, String tele,String mobile, String email, String plateNumber,
			String crdkindId, String crdId, Date crdVld, Blob crdImg,
			Image signImg, Date birthday, String visakindId,
			String visaId, String depart, String inPort, Date inDate,
			String reason, String addr, String bookOperid, Date bookTime,
			String chkOperid, Date chkTime, String rechkOperid,
			Date rechkTime, String lastOper, Date lastTime,
			String outOper, Date outTime, Integer withId, Integer billaId,
			Integer billbId,Integer billbOrgId, Boolean ifBpay, Boolean ifFgst, Short gstNum,
			Short updateTimes, String bookExt, String chkExt, String connector,
			Date useTime, String booker, String operation, String native_,
			String folk, String works, String regAddr, Boolean iddOn,
			String bookOrder, String vilhotelId, String chainId,String bookerName,String lastCashier,Date cashierTime,Boolean isRoomPlan) {
		this.checkId = checkId;
		this.gstId = gstId;
		this.gstNamee = gstNamee;
		this.gstNamec = gstNamec;
		this.sex = sex;
		this.age = age;
		this.wrkComp = wrkComp;
		this.grpChkid = grpChkid;
		this.notice = notice;
		this.reachDate = reachDate;
		this.leaveDate = leaveDate;
		this.leaveDate0 = leaveDate0;
		this.confirmDate = confirmDate;
		this.confirmOperid=confirmOperid;
		this.bookStat = bookStat;
		this.chkStat = chkStat;
		this.roomId = roomId;
		this.roomTypeid = roomTypeid;
		this.roomCharacter = roomCharacter;
		this.roomPrice = roomPrice;
		this.paymanFlag = paymanFlag;
		this.gstOriId = gstOriId;
		this.prcSchemeId = prcSchemeId;
		this.payId = payId;
		this.limit = limit;
		this.creditId = creditId;
		this.compType = compType;
		this.compId = compId;
		this.compCd = compCd;
		this.cityLedger = cityLedger;
		this.housePay = housePay;
		this.freeSvc = freeSvc;
		this.changeRate = changeRate;
		this.guarantor = guarantor;
		this.secret = secret;
		this.hideprice = hideprice;
		this.teleStatus = teleStatus;
		this.gstKind = gstKind;
		this.gstFlag = gstFlag;
		this.ntId = ntId;
		this.areaId = areaId;
		this.bookType = bookType;
		this.bookList = bookList;
		this.tele = tele;
		this.mobile=mobile;
		this.email = email;
		this.plateNumber = plateNumber;
		this.crdkindId = crdkindId;
		this.crdId = crdId;
		this.crdVld = crdVld;
		this.birthday = birthday;
		this.visakindId = visakindId;
		this.visaId = visaId;
		this.depart = depart;
		this.inPort = inPort;
		this.inDate = inDate;
		this.reason = reason;
		this.addr = addr;
		this.bookOperid = bookOperid;
		this.bookTime = bookTime;
		this.chkOperid = chkOperid;
		this.chkTime = chkTime;
		this.rechkOperid = rechkOperid;
		this.rechkTime = rechkTime;
		this.lastOper = lastOper;
		this.lastTime = lastTime;
		this.outOper = outOper;
		this.outTime = outTime;
		this.withId = withId;
		this.billaId = billaId;
		this.billbId = billbId;
		this.billbOrgId=billbOrgId;
		this.ifBpay = ifBpay;
		this.ifFgst = ifFgst;
		this.gstNum = gstNum;
		this.updateTimes = updateTimes;
		this.bookExt = bookExt;
		this.chkExt = chkExt;
		this.connector = connector;
		this.useTime = useTime;
		this.booker = booker;
		this.operation = operation;
		this.native_ = native_;
		this.folk = folk;
		this.works = works;
		this.regAddr = regAddr;
		this.iddOn = iddOn;
		this.bookOrder = bookOrder;
		this.vilhotelId = vilhotelId;
		this.chainId = chainId;
		this.bookerName=bookerName;
		this.lastCashier = lastCashier;
		this.cashierTime = cashierTime;
		this.isRoomPlan = isRoomPlan;
	}

	// Property accessors
	@Id
	@Column(name = "check_id", unique = true, nullable = true)
	public Integer getCheckId() {
		return this.checkId;
	}

	public void setCheckId(Integer checkId) {
		this.checkId = checkId;
	}

	@Column(name = "gst_id")
	public Integer getGstId() {
		return this.gstId;
	}

	public void setGstId(Integer gstId) {
		this.gstId = gstId;
	}

	@Column(name = "gst_namee", nullable = true, length = 40)
	public String getGstNamee() {
		return this.gstNamee;
	}

	public void setGstNamee(String gstNamee) {
		this.gstNamee = gstNamee;
	}

	@Column(name = "gst_namec", nullable = true, length = 10)
	public String getGstNamec() {
		return this.gstNamec;
	}

	public void setGstNamec(String gstNamec) {
		this.gstNamec = gstNamec;
	}

	@Column(name = "sex", nullable = true, length = 6)
	public String getSex() {
		return this.sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	@Column(name = "age", nullable = true)
	public Integer getAge() {
		return this.age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	@Column(name = "wrk_comp", length = 40)
	public String getWrkComp() {
		return this.wrkComp;
	}

	public void setWrkComp(String wrkComp) {
		this.wrkComp = wrkComp;
	}

	@Column(name = "grp_chkid")
	public Integer getGrpChkid() {
		return this.grpChkid;
	}

	public void setGrpChkid(Integer grpChkid) {
		this.grpChkid = grpChkid;
	}

	@Column(name = "notice")
	public String getNotice() {
		return this.notice;
	}

	public void setNotice(String notice) {
		this.notice = notice;
	}
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@Column(name = "reach_date", nullable = true)
	public Date getReachDate() {
		return this.reachDate;
	}

	public void setReachDate(Date reachDate) {
		this.reachDate = reachDate;
	}
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@Column(name = "leave_date", nullable = true)
	public Date getLeaveDate() {
		return this.leaveDate;
	}

	public void setLeaveDate(Date leaveDate) {
		this.leaveDate = leaveDate;
	}

	@Column(name = "leave_date0")
	@Temporal(TemporalType.DATE)
	public Date getLeaveDate0() {
		return this.leaveDate0;
	}

	public void setLeaveDate0(Date leaveDate0) {
		this.leaveDate0 = leaveDate0;
	}

	@Column(name = "confirm_date", nullable = true)
	public Date getConfirmDate() {
		return this.confirmDate;
	}

	public void setConfirmDate(Date confirmDate) {
		this.confirmDate = confirmDate;
	}
	@Column(name = "confirm_operid", length = 10)
	public String getConfirmOperid() {
		return this.confirmOperid;
	}

	public void setConfirmOperid(String confirmOperid) {
		this.confirmOperid = confirmOperid;
	}
	@Column(name = "book_stat", length = 1)
	public String getBookStat() {
		return this.bookStat;
	}

	public void setBookStat(String bookStat) {
		this.bookStat = bookStat;
	}

	@Column(name = "chk_stat", length = 1)
	public String getChkStat() {
		return this.chkStat;
	}

	public void setChkStat(String chkStat) {
		this.chkStat = chkStat;
	}

	@Column(name = "room_id", length = 6)
	public String getRoomId() {
		return this.roomId;
	}

	public void setRoomId(String roomId) {
		this.roomId = roomId;
	}

	@Column(name = "room_typeid", nullable = true, length = 3)
	public String getRoomTypeid() {
		return this.roomTypeid;
	}

	public void setRoomTypeid(String roomTypeid) {
		this.roomTypeid = roomTypeid;
	}

	@Column(name = "room_character", nullable = true)
	public Long getRoomCharacter() {
		return this.roomCharacter;
	}

	public void setRoomCharacter(Long roomCharacter) {
		this.roomCharacter = roomCharacter;
	}

	@Column(name = "room_price", precision = 15, scale = 0)
	public Double getRoomPrice() {
		return this.roomPrice;
	}

	public void setRoomPrice(Double roomPrice) {
		this.roomPrice = roomPrice;
	}

	@Column(name = "payman_flag", nullable = true)
	public Integer getPaymanFlag() {
		return this.paymanFlag;
	}

	public void setPaymanFlag(Integer paymanFlag) {
		this.paymanFlag = paymanFlag;
	}

	@Column(name = "gst_ori_id", nullable = true, length = 3)
	public String getGstOriId() {
		return this.gstOriId;
	}

	public void setGstOriId(String gstOriId) {
		this.gstOriId = gstOriId;
	}

	@Column(name = "prc_scheme_id", length = 6)
	public String getPrcSchemeId() {
		return this.prcSchemeId;
	}

	public void setPrcSchemeId(String prcSchemeId) {
		this.prcSchemeId = prcSchemeId;
	}

	@Column(name = "pay_id", nullable = true, length = 3)
	public String getPayId() {
		return this.payId;
	}

	public void setPayId(String payId) {
		this.payId = payId;
	}

	@Column(name = "limit", nullable = true, precision = 15, scale = 0)
	public Double getLimit() {
		return this.limit;
	}

	public void setLimit(Double limit) {
		this.limit = limit;
	}

	@Column(name = "credit_id", nullable = true, length = 25)
	public String getCreditId() {
		return this.creditId;
	}

	public void setCreditId(String creditId) {
		this.creditId = creditId;
	}

	@Column(name = "comp_type", nullable = true, length = 1)
	public String getCompType() {
		return this.compType;
	}

	public void setCompType(String compType) {
		this.compType = compType;
	}

	@Column(name = "comp_id", length = 60)
	public String getCompId() {
		return this.compId;
	}

	public void setCompId(String compId) {
		this.compId = compId;
	}

	@Column(name = "comp_cd")
	public Integer getCompCd() {
		return this.compCd;
	}

	public void setCompCd(Integer compCd) {
		this.compCd = compCd;
	}

	@Column(name = "city_ledger", nullable = true)
	public Boolean getCityLedger() {
		return this.cityLedger;
	}

	public void setCityLedger(Boolean cityLedger) {
		this.cityLedger = cityLedger;
	}

	@Column(name = "house_pay", nullable = true)
	public Boolean getHousePay() {
		return this.housePay;
	}

	public void setHousePay(Boolean housePay) {
		this.housePay = housePay;
	}

	@Column(name = "free_svc", nullable = true)
	public Boolean getFreeSvc() {
		return this.freeSvc;
	}

	public void setFreeSvc(Boolean freeSvc) {
		this.freeSvc = freeSvc;
	}

	@Column(name = "Change_rate", nullable = true)
	public Boolean getChangeRate() {
		return this.changeRate;
	}

	public void setChangeRate(Boolean changeRate) {
		this.changeRate = changeRate;
	}

	@Column(name = "guarantor", length = 40)
	public String getGuarantor() {
		return this.guarantor;
	}

	public void setGuarantor(String guarantor) {
		this.guarantor = guarantor;
	}

	@Column(name = "secret", nullable = true)
	public Boolean getSecret() {
		return this.secret;
	}

	public void setSecret(Boolean secret) {
		this.secret = secret;
	}

	@Column(name = "hideprice", nullable = true)
	public Boolean getHideprice() {
		return this.hideprice;
	}

	public void setHideprice(Boolean hideprice) {
		this.hideprice = hideprice;
	}

	@Column(name = "tele_status", nullable = true, length = 6)
	public String getTeleStatus() {
		return this.teleStatus;
	}

	public void setTeleStatus(String teleStatus) {
		this.teleStatus = teleStatus;
	}

	@Column(name = "gst_kind", nullable = true, length = 6)
	public String getGstKind() {
		return this.gstKind;
	}

	public void setGstKind(String gstKind) {
		this.gstKind = gstKind;
	}

	@Column(name = "gst_flag", nullable = true, length = 1)
	public String getGstFlag() {
		return this.gstFlag;
	}

	public void setGstFlag(String gstFlag) {
		this.gstFlag = gstFlag;
	}

	@Column(name = "nt_id", nullable = true, length = 3)
	public String getNtId() {
		return this.ntId;
	}

	public void setNtId(String ntId) {
		this.ntId = ntId;
	}

	@Column(name = "area_id", nullable = true, length = 6)
	public String getAreaId() {
		return this.areaId;
	}

	public void setAreaId(String areaId) {
		this.areaId = areaId;
	}

	@Column(name = "book_type", nullable = true, length = 6)
	public String getBookType() {
		return this.bookType;
	}

	public void setBookType(String bookType) {
		this.bookType = bookType;
	}

	@Column(name = "book_list", length = 20)
	public String getBookList() {
		return this.bookList;
	}

	public void setBookList(String bookList) {
		this.bookList = bookList;
	}

	@Column(name = "tele", length = 40)
	public String getTele() {
		return this.tele;
	}

	public void setTele(String tele) {
		this.tele = tele;
	}
	@Column(name = "mobile", length = 40)
	public String getMobile() {
		return this.mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	@Column(name = "email", length = 50)
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "plate_number", length = 50)
	public String getPlateNumber() {
		return this.plateNumber;
	}

	public void setPlateNumber(String plateNumber) {
		this.plateNumber = plateNumber;
	}

	@Column(name = "crdkind_id", nullable = true, length = 6)
	public String getCrdkindId() {
		return this.crdkindId;
	}

	public void setCrdkindId(String crdkindId) {
		this.crdkindId = crdkindId;
	}

	@Column(name = "crd_id", length = 20)
	public String getCrdId() {
		return this.crdId;
	}

	public void setCrdId(String crdId) {
		this.crdId = crdId;
	}

	@Column(name = "crd_vld", length = 16)
	@DateTimeFormat(pattern="yyyy-MM-dd")
	public Date getCrdVld() {
		return this.crdVld;
	}

	public void setCrdVld(Date crdVld) {
		this.crdVld = crdVld;
	}
	@Column(name = "birthday")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	public Date getBirthday() {
		return this.birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	@Column(name = "visakind_id", nullable = true, length = 6)
	public String getVisakindId() {
		return this.visakindId;
	}

	public void setVisakindId(String visakindId) {
		this.visakindId = visakindId;
	}

	@Column(name = "visa_id", length = 10)
	public String getVisaId() {
		return this.visaId;
	}

	public void setVisaId(String visaId) {
		this.visaId = visaId;
	}

	@Column(name = "depart", length = 8)
	public String getDepart() {
		return this.depart;
	}

	public void setDepart(String depart) {
		this.depart = depart;
	}

	@Column(name = "in_port", nullable = true, length = 6)
	public String getInPort() {
		return this.inPort;
	}

	public void setInPort(String inPort) {
		this.inPort = inPort;
	}

	@Column(name = "in_date" )
	@Temporal(TemporalType.DATE)
	public Date getInDate() {
		return this.inDate;
	}

	public void setInDate(Date inDate) {
		this.inDate = inDate;
	}

	@Column(name = "reason", nullable = true, length = 6)
	public String getReason() {
		return this.reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	@Column(name = "addr", length = 50)
	public String getAddr() {
		return this.addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	@Column(name = "book_operid", length = 10)
	public String getBookOperid() {
		return this.bookOperid;
	}

	public void setBookOperid(String bookOperid) {
		this.bookOperid = bookOperid;
	}

	@Column(name = "book_time" )
	public Date getBookTime() {
		return this.bookTime;
	}

	public void setBookTime(Date bookTime) {
		this.bookTime = bookTime;
	}

	@Column(name = "chk_operid", length = 10)
	public String getChkOperid() {
		return this.chkOperid;
	}

	public void setChkOperid(String chkOperid) {
		this.chkOperid = chkOperid;
	}

	@Column(name = "chk_time" )
	@Temporal(TemporalType.DATE)
	public Date getChkTime() {
		return this.chkTime;
	}

	public void setChkTime(Date chkTime) {
		this.chkTime = chkTime;
	}

	@Column(name = "rechk_operid", length = 10)
	public String getRechkOperid() {
		return this.rechkOperid;
	}

	public void setRechkOperid(String rechkOperid) {
		this.rechkOperid = rechkOperid;
	}

	@Column(name = "rechk_time" )
	@Temporal(TemporalType.DATE)
	public Date getRechkTime() {
		return this.rechkTime;
	}

	public void setRechkTime(Date rechkTime) {
		this.rechkTime = rechkTime;
	}

	@Column(name = "last_oper", length = 10)
	public String getLastOper() {
		return this.lastOper;
	}

	public void setLastOper(String lastOper) {
		this.lastOper = lastOper;
	}

	@Column(name = "last_time" )
	public Date getLastTime() {
		return this.lastTime;
	}

	public void setLastTime(Date lastTime) {
		this.lastTime = lastTime;
	}

	@Column(name = "out_oper", length = 10)
	public String getOutOper() {
		return this.outOper;
	}

	public void setOutOper(String outOper) {
		this.outOper = outOper;
	}

	@Column(name = "out_time" )
	@Temporal(TemporalType.DATE)
	public Date getOutTime() {
		return this.outTime;
	}

	public void setOutTime(Date outTime) {
		this.outTime = outTime;
	}

	@Column(name = "with_id", nullable = true)
	public Integer getWithId() {
		return this.withId;
	}

	public void setWithId(Integer withId) {
		this.withId = withId;
	}

	@Column(name = "billa_id", nullable = true)
	public Integer getBillaId() {
		return this.billaId;
	}

	public void setBillaId(Integer billaId) {
		this.billaId = billaId;
	}

	@Column(name = "billb_id", nullable = true)
	public Integer getBillbId() {
		return this.billbId;
	}

	public void setBillbId(Integer billbId) {
		this.billbId = billbId;
	}
	@Column(name = "billb_org_id")
	public Integer getBillbOrgId() {
		return this.billbOrgId;
	}

	public void setBillbOrgId(Integer billbOrgId) {
		this.billbOrgId = billbOrgId;
	}
	@Column(name = "if_bpay", nullable = true)
	public Boolean getIfBpay() {
		return this.ifBpay;
	}

	public void setIfBpay(Boolean ifBpay) {
		this.ifBpay = ifBpay;
	}

	@Column(name = "if_fgst", nullable = true)
	public Boolean getIfFgst() {
		return this.ifFgst;
	}

	public void setIfFgst(Boolean ifFgst) {
		this.ifFgst = ifFgst;
	}

	@Column(name = "gst_num", nullable = true)
	public Short getGstNum() {
		return this.gstNum;
	}

	public void setGstNum(Short gstNum) {
		this.gstNum = gstNum;
	}

	@Column(name = "update_times", nullable = true)
	public Short getUpdateTimes() {
		return this.updateTimes;
	}

	public void setUpdateTimes(Short updateTimes) {
		this.updateTimes = updateTimes;
	}

	@Column(name = "book_ext", length = 1)
	public String getBookExt() {
		return this.bookExt;
	}

	public void setBookExt(String bookExt) {
		this.bookExt = bookExt;
	}

	@Column(name = "chk_ext", length = 1)
	public String getChkExt() {
		return this.chkExt;
	}

	public void setChkExt(String chkExt) {
		this.chkExt = chkExt;
	}

	@Column(name = "connector", nullable = true, length = 20)
	public String getConnector() {
		return this.connector;
	}

	public void setConnector(String connector) {
		this.connector = connector;
	}

	@Column(name = "use_time" )
	@Temporal(TemporalType.DATE)
	public Date getUseTime() {
		return this.useTime;
	}

	public void setUseTime(Date useTime) {
		this.useTime = useTime;
	}

	@Column(name = "Booker", nullable = true, length = 1)
	public String getBooker() {
		return this.booker;
	}

	public void setBooker(String booker) {
		this.booker = booker;
	}

	@Column(name = "operation", nullable = true, length = 1)
	public String getOperation() {
		return this.operation;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}

	@Column(name = "native", length = 6)
	public String getNative_() {
		return this.native_;
	}

	public void setNative_(String native_) {
		this.native_ = native_;
	}

	@Column(name = "folk", length = 6)
	public String getFolk() {
		return this.folk;
	}

	public void setFolk(String folk) {
		this.folk = folk;
	}

	@Column(name = "works", length = 6)
	public String getWorks() {
		return this.works;
	}

	public void setWorks(String works) {
		this.works = works;
	}

	@Column(name = "reg_addr", length = 6)
	public String getRegAddr() {
		return this.regAddr;
	}

	public void setRegAddr(String regAddr) {
		this.regAddr = regAddr;
	}

	@Column(name = "IddOn", nullable = true)
	public Boolean getIddOn() {
		return this.iddOn;
	}

	public void setIddOn(Boolean iddOn) {
		this.iddOn = iddOn;
	}

	@Column(name = "Book_Order", nullable = true, length = 50)
	public String getBookOrder() {
		return this.bookOrder;
	}

	public void setBookOrder(String bookOrder) {
		this.bookOrder = bookOrder;
	}

	@Column(name = "vilhotel_id", length = 50)
	public String getVilhotelId() {
		return this.vilhotelId;
	}

	public void setVilhotelId(String vilhotelId) {
		this.vilhotelId = vilhotelId;
	}

	@Column(name = "chain_id", length = 50)
	public String getChainId() {
		return this.chainId;
	}

	public void setChainId(String chainId) {
		this.chainId = chainId;
	}
	@Column(name = "booker_name", length = 10)
	public String getBookerName() {
		return this.bookerName;
	}

	public void setBookerName(String bookerName) {
		this.bookerName = bookerName;
	}
	
	@Column(name="if_bdate")
	public Boolean getIfBdate() {
		return ifBdate;
	}
	public void setIfBdate(Boolean ifBdate) {
		this.ifBdate = ifBdate;
	}
	
	@Column(name="last_cashier")
	public String getLastCashier() {
		return lastCashier;
	}
	public void setLastCashier(String lastCashier) {
		this.lastCashier = lastCashier;
	}
	
	@Column(name="cashier_time")
	@Temporal(TemporalType.TIMESTAMP)
	public Date getCashierTime() {
		return cashierTime;
	}
	public void setCashierTime(Date cashierTime) {
		this.cashierTime = cashierTime;
	}
	@Transient
	public String getBiilbLimit() {
		return biilbLimit;
	}
	public void setBiilbLimit(String biilbLimit) {
		this.biilbLimit = biilbLimit;
	}
	@Transient
	public String getBillbBlance() {
		return billbBlance;
	}
	public void setBillbBlance(String billbBlance) {
		this.billbBlance = billbBlance;
	}
	@Column(name="no_arrive_cancel")
	public Boolean getNoArriveCancel() {
		return noArriveCancel;
	}
	public void setNoArriveCancel(Boolean noArriveCancel) {
		this.noArriveCancel = noArriveCancel;
	}
	@Column(name="is_room_plan" )
	public Boolean getIsRoomPlan() {
		return isRoomPlan;
	}
	public void setIsRoomPlan(Boolean isRoomPlan) {
		this.isRoomPlan = isRoomPlan;
	}
	
}