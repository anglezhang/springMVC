package com.cyw.mammoth.bean;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

/**
 * GrpDoc entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "grp_doc", schema = "dbo", catalog = "mammoth")
public class GrpDoc implements java.io.Serializable {

	// Fields

	private Integer checkId;//登记号
	private String grpId;//团代码
	private String grpName;//团名
	private Short gstNum;//团体人数
	private String wrkComp;
	private String leadNamee;
	private String leadNamec;
	private String leadRoom;
	private Date reachDate;
	private Date leaveDate;
	private Date confirmDate;
	private String confirmOperid;
	private String notice;
	private String gstOriId;//客源代码
	private String gstKind;
	private String tele;//联系电话
	private String mobile;//电话
	private String bookStat;
	private String chkStat;
	private String payStat;
	private Double grpRent;
	private String flag;
	private Long roomCharacter;
	private String roomTypeid;
	private String payId;
	private Double limit;
	private String creditId;
	private String compType;
	private String compId;
	private Integer compCd;
	private Boolean cityLedger;
	private Boolean housePay;
	private Boolean changeRate;
	private String guarantor;
	private Boolean secret;
	private Boolean hideprice;
	private Integer withId;
	private Integer billId;//团账账号
	private Integer billOrgId;
	private Boolean freeSvc;
	private Boolean ifPack;
	private Boolean ifBpay;
	private String mealCode;
	private Date mealDate0;
	private Date mealDate1;
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
	private String bookType;
	private String bookList;
	private Short updateTimes;
	private String userId;
	private String computerId;
	private Date useTime;
	private String notice2;
	private String operation;
	private String teleStatus;
	private Boolean minibar;
	private Boolean iddOn;
	private String principal;
	private String vilhotelId;
	private String chainId;
	private String lastCashier;
	private Date cashierTime;
	private Boolean ifBdate=true;
	private Boolean noArriveCancel;//夜审时是否自动取消未抵留房
	private String prcSchemeId;
	// Constructors
	
	/**
	 * 临时字段 抵达时间
	 */
	private String reachTime;
	private String biilbLimit;//B账限额
	private String billbBlance;//B账余额
	/**
	 * 合约单位
	 */
	@Transient
	private String unitNamec;
	/**
	 * 销售员
	 */
	private String saler ;

	/** default constructor */
	public GrpDoc() {
	}

	/** minimal constructor */
	public GrpDoc(Integer checkId, String grpId, String grpName, Short gstNum,
			String leadNamee, String leadNamec, Date reachDate,
			Date leaveDate, Date confirmDate, String gstOriId,
			String gstKind, Double grpRent, Long roomCharacter, String roomTypeid,
			String payId, Double limit, String compType, Boolean cityLedger,
			Boolean housePay, Boolean changeRate, Boolean secret,
			Boolean hideprice, Integer withId, Integer billId, Boolean freeSvc,
			Boolean ifPack, Boolean ifBpay, String mealCode, String bookType,
			Short updateTimes, String userId, String computerId,
			String operation, String teleStatus, Boolean minibar,
			Boolean iddOn, String principal , String prcSchemeId) {
		this.checkId = checkId;
		this.grpId = grpId;
		this.grpName = grpName;
		this.gstNum = gstNum;
		this.leadNamee = leadNamee;
		this.leadNamec = leadNamec;
		this.reachDate = reachDate;
		this.leaveDate = leaveDate;
		this.confirmDate = confirmDate;
		this.gstOriId = gstOriId;
		this.gstKind = gstKind;
		this.grpRent = grpRent;
		this.roomCharacter = roomCharacter;
		this.roomTypeid = roomTypeid;
		this.payId = payId;
		this.limit = limit;
		this.compType = compType;
		this.cityLedger = cityLedger;
		this.housePay = housePay;
		this.changeRate = changeRate;
		this.secret = secret;
		this.hideprice = hideprice;
		this.withId = withId;
		this.billId = billId;
		this.freeSvc = freeSvc;
		this.ifPack = ifPack;
		this.ifBpay = ifBpay;
		this.mealCode = mealCode;
		this.bookType = bookType;
		this.updateTimes = updateTimes;
		this.userId = userId;
		this.computerId = computerId;
		this.operation = operation;
		this.teleStatus = teleStatus;
		this.minibar = minibar;
		this.iddOn = iddOn;
		this.principal = principal;
		this.prcSchemeId = prcSchemeId;
	}

	/** full constructor */
	public GrpDoc(Integer checkId, String grpId, String grpName, Short gstNum,
			String wrkComp, String leadNamee, String leadNamec,
			String leadRoom, Date reachDate, Date leaveDate,
			Date confirmDate, String notice, String gstOriId,
			String gstKind, String tele, String bookStat, String chkStat,
			String payStat, Double grpRent, String flag, Long roomCharacter,
			String roomTypeid, String payId, Double limit, String creditId,
			String compType, String compId, Integer compCd, Boolean cityLedger,
			Boolean housePay, Boolean changeRate, String guarantor,
			Boolean secret, Boolean hideprice, Integer withId, Integer billId,
			Boolean freeSvc, Boolean ifPack, Boolean ifBpay, String mealCode,
			Date mealDate0, Date mealDate1, String bookOperid,
			Date bookTime, String chkOperid, Date chkTime,
			String rechkOperid, Date rechkTime, String lastOper,
			Date lastTime, String outOper, Date outTime,
			String bookType, String bookList, Short updateTimes, String userId,
			String computerId, Date useTime, String notice2,
			String operation, String teleStatus, Boolean minibar,
			Boolean iddOn, String principal, String vilhotelId, String chainId,String lastCashier,Date cashierTime,Boolean ifBdate,String prcSchemeId) {
		this.checkId = checkId;
		this.grpId = grpId;
		this.grpName = grpName;
		this.gstNum = gstNum;
		this.wrkComp = wrkComp;
		this.leadNamee = leadNamee;
		this.leadNamec = leadNamec;
		this.leadRoom = leadRoom;
		this.reachDate = reachDate;
		this.leaveDate = leaveDate;
		this.confirmDate = confirmDate;
		this.notice = notice;
		this.gstOriId = gstOriId;
		this.gstKind = gstKind;
		this.tele = tele;
		this.bookStat = bookStat;
		this.chkStat = chkStat;
		this.payStat = payStat;
		this.grpRent = grpRent;
		this.flag = flag;
		this.roomCharacter = roomCharacter;
		this.roomTypeid = roomTypeid;
		this.payId = payId;
		this.limit = limit;
		this.creditId = creditId;
		this.compType = compType;
		this.compId = compId;
		this.compCd = compCd;
		this.cityLedger = cityLedger;
		this.housePay = housePay;
		this.changeRate = changeRate;
		this.guarantor = guarantor;
		this.secret = secret;
		this.hideprice = hideprice;
		this.withId = withId;
		this.billId = billId;
		this.freeSvc = freeSvc;
		this.ifPack = ifPack;
		this.ifBpay = ifBpay;
		this.mealCode = mealCode;
		this.mealDate0 = mealDate0;
		this.mealDate1 = mealDate1;
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
		this.bookType = bookType;
		this.bookList = bookList;
		this.updateTimes = updateTimes;
		this.userId = userId;
		this.computerId = computerId;
		this.useTime = useTime;
		this.notice2 = notice2;
		this.operation = operation;
		this.teleStatus = teleStatus;
		this.minibar = minibar;
		this.iddOn = iddOn;
		this.principal = principal;
		this.vilhotelId = vilhotelId;
		this.chainId = chainId;
		this.lastCashier = lastCashier;
		this.cashierTime = cashierTime;
		this.ifBdate = ifBdate;
		this.prcSchemeId = prcSchemeId;
	}

	// Property accessors
	@Id
	@Column(name = "check_id", unique = true, nullable = false)
	public Integer getCheckId() {
		return this.checkId;
	}

	public void setCheckId(Integer checkId) {
		this.checkId = checkId;
	}

	@Column(name = "grp_id", nullable = false, length = 10)
	public String getGrpId() {
		return this.grpId;
	}

	public void setGrpId(String grpId) {
		this.grpId = grpId;
	}

	@Column(name = "grp_name", nullable = false, length = 40)
	public String getGrpName() {
		return this.grpName;
	}

	public void setGrpName(String grpName) {
		this.grpName = grpName;
	}

	@Column(name = "gst_num", nullable = false)
	public Short getGstNum() {
		return this.gstNum;
	}

	public void setGstNum(Short gstNum) {
		this.gstNum = gstNum;
	}

	@Column(name = "wrk_comp", length = 40)
	public String getWrkComp() {
		return this.wrkComp;
	}

	public void setWrkComp(String wrkComp) {
		this.wrkComp = wrkComp;
	}

	@Column(name = "lead_namee", nullable = false, length = 40)
	public String getLeadNamee() {
		return this.leadNamee;
	}

	public void setLeadNamee(String leadNamee) {
		this.leadNamee = leadNamee;
	}

	@Column(name = "lead_namec", nullable = false, length = 10)
	public String getLeadNamec() {
		return this.leadNamec;
	}

	public void setLeadNamec(String leadNamec) {
		this.leadNamec = leadNamec;
	}

	@Column(name = "lead_room", length = 6)
	public String getLeadRoom() {
		return this.leadRoom;
	}

	public void setLeadRoom(String leadRoom) {
		this.leadRoom = leadRoom;
	}

	@Column(name = "reach_date", nullable = false, length = 16)
	@Temporal(TemporalType.DATE)
	public Date getReachDate() {
		return this.reachDate;
	}

	public void setReachDate(Date reachDate) {
		this.reachDate = reachDate;
	}

	@Column(name = "leave_date", nullable = false, length = 16)
	@Temporal(TemporalType.DATE)
	public Date getLeaveDate() {
		return this.leaveDate;
	}

	public void setLeaveDate(Date leaveDate) {
		this.leaveDate = leaveDate;
	}

	@Column(name = "confirm_date", nullable = true, length = 16)
	public Date getConfirmDate() {
		return this.confirmDate;
	}

	public void setConfirmDate(Date confirmDate) {
		this.confirmDate = confirmDate;
	}

	@Column(name = "notice")
	public String getNotice() {
		return this.notice;
	}

	public void setNotice(String notice) {
		this.notice = notice;
	}

	@Column(name = "gst_ori_id", nullable = false, length = 3)
	public String getGstOriId() {
		return this.gstOriId;
	}

	public void setGstOriId(String gstOriId) {
		this.gstOriId = gstOriId;
	}

	@Column(name = "gst_kind", nullable = false, length = 6)
	public String getGstKind() {
		return this.gstKind;
	}

	public void setGstKind(String gstKind) {
		this.gstKind = gstKind;
	}

	@Column(name = "tele", length = 20)
	public String getTele() {
		return this.tele;
	}

	public void setTele(String tele) {
		this.tele = tele;
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

	@Column(name = "pay_stat", length = 1)
	public String getPayStat() {
		return this.payStat;
	}

	public void setPayStat(String payStat) {
		this.payStat = payStat;
	}

	@Column(name = "grp_rent", nullable = false, precision = 15, scale = 0)
	public Double getGrpRent() {
		return this.grpRent;
	}

	public void setGrpRent(Double grpRent) {
		this.grpRent = grpRent;
	}

	@Column(name = "flag", length = 1)
	public String getFlag() {
		return this.flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	@Column(name = "room_character", nullable = false, length = 10)
	public Long getRoomCharacter() {
		return this.roomCharacter;
	}

	public void setRoomCharacter(Long roomCharacter) {
		this.roomCharacter = roomCharacter;
	}

	@Column(name = "room_typeid", nullable = false, length = 3)
	public String getRoomTypeid() {
		return this.roomTypeid;
	}

	public void setRoomTypeid(String roomTypeid) {
		this.roomTypeid = roomTypeid;
	}

	@Column(name = "pay_id", nullable = false, length = 3)
	public String getPayId() {
		return this.payId;
	}

	public void setPayId(String payId) {
		this.payId = payId;
	}

	@Column(name = "limit", nullable = false, precision = 15, scale = 0)
	public Double getLimit() {
		return this.limit;
	}

	public void setLimit(Double limit) {
		this.limit = limit;
	}

	@Column(name = "credit_id", length = 25)
	public String getCreditId() {
		return this.creditId;
	}

	public void setCreditId(String creditId) {
		this.creditId = creditId;
	}

	@Column(name = "comp_type", nullable = false, length = 1)
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

	@Column(name = "city_ledger", nullable = false)
	public Boolean getCityLedger() {
		return this.cityLedger;
	}

	public void setCityLedger(Boolean cityLedger) {
		this.cityLedger = cityLedger;
	}

	@Column(name = "house_pay", nullable = false)
	public Boolean getHousePay() {
		return this.housePay;
	}

	public void setHousePay(Boolean housePay) {
		this.housePay = housePay;
	}

	@Column(name = "change_rate", nullable = false)
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

	@Column(name = "secret", nullable = false)
	public Boolean getSecret() {
		return this.secret;
	}

	public void setSecret(Boolean secret) {
		this.secret = secret;
	}

	@Column(name = "hideprice", nullable = false)
	public Boolean getHideprice() {
		return this.hideprice;
	}

	public void setHideprice(Boolean hideprice) {
		this.hideprice = hideprice;
	}

	@Column(name = "with_id", nullable = false)
	public Integer getWithId() {
		return this.withId;
	}

	public void setWithId(Integer withId) {
		this.withId = withId;
	}

	@Column(name = "bill_id", nullable = false)
	public Integer getBillId() {
		return this.billId;
	}

	public void setBillId(Integer billId) {
		this.billId = billId;
	}

	@Column(name = "free_svc", nullable = false)
	public Boolean getFreeSvc() {
		return this.freeSvc;
	}

	public void setFreeSvc(Boolean freeSvc) {
		this.freeSvc = freeSvc;
	}

	@Column(name = "if_pack", nullable = false)
	public Boolean getIfPack() {
		return this.ifPack;
	}

	public void setIfPack(Boolean ifPack) {
		this.ifPack = ifPack;
	}

	@Column(name = "if_bpay", nullable = false)
	public Boolean getIfBpay() {
		return this.ifBpay;
	}

	public void setIfBpay(Boolean ifBpay) {
		this.ifBpay = ifBpay;
	}

	@Column(name = "meal_code", nullable = false, length = 3)
	public String getMealCode() {
		return this.mealCode;
	}

	public void setMealCode(String mealCode) {
		this.mealCode = mealCode;
	}

	@Column(name = "meal_date0", length = 16)
	@Temporal(TemporalType.DATE)
	public Date getMealDate0() {
		return this.mealDate0;
	}

	public void setMealDate0(Date mealDate0) {
		this.mealDate0 = mealDate0;
	}

	@Column(name = "meal_date1", length = 16)
	@Temporal(TemporalType.DATE)
	public Date getMealDate1() {
		return this.mealDate1;
	}

	public void setMealDate1(Date mealDate1) {
		this.mealDate1 = mealDate1;
	}

	@Column(name = "book_operid", length = 10)
	public String getBookOperid() {
		return this.bookOperid;
	}

	public void setBookOperid(String bookOperid) {
		this.bookOperid = bookOperid;
	}

	@Column(name = "book_time", length = 16)
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

	@Column(name = "chk_time", length = 16)
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

	@Column(name = "rechk_time", length = 16)
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

	@Column(name = "last_time", length = 16)
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

	@Column(name = "out_time", length = 16)
	@Temporal(TemporalType.DATE)
	public Date getOutTime() {
		return this.outTime;
	}

	public void setOutTime(Date outTime) {
		this.outTime = outTime;
	}

	@Column(name = "book_type", nullable = false, length = 6)
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

	@Column(name = "update_times", nullable = false)
	public Short getUpdateTimes() {
		return this.updateTimes;
	}

	public void setUpdateTimes(Short updateTimes) {
		this.updateTimes = updateTimes;
	}

	@Column(name = "user_id", nullable = false, length = 10)
	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	@Column(name = "computer_id", nullable = false, length = 15)
	public String getComputerId() {
		return this.computerId;
	}

	public void setComputerId(String computerId) {
		this.computerId = computerId;
	}

	@Column(name = "use_time", length = 16)
	@Temporal(TemporalType.DATE)
	public Date getUseTime() {
		return this.useTime;
	}

	public void setUseTime(Date useTime) {
		this.useTime = useTime;
	}

	@Column(name = "notice2")
	public String getNotice2() {
		return this.notice2;
	}

	public void setNotice2(String notice2) {
		this.notice2 = notice2;
	}

	@Column(name = "operation", nullable = false, length = 1)
	public String getOperation() {
		return this.operation;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}

	@Column(name = "Tele_Status", nullable = false, length = 6)
	public String getTeleStatus() {
		return this.teleStatus;
	}

	public void setTeleStatus(String teleStatus) {
		this.teleStatus = teleStatus;
	}

	@Column(name = "minibar", nullable = false)
	public Boolean getMinibar() {
		return this.minibar;
	}

	public void setMinibar(Boolean minibar) {
		this.minibar = minibar;
	}

	@Column(name = "IddOn", nullable = false)
	public Boolean getIddOn() {
		return this.iddOn;
	}

	public void setIddOn(Boolean iddOn) {
		this.iddOn = iddOn;
	}

	@Column(name = "principal", nullable = false, length = 20)
	public String getPrincipal() {
		return this.principal;
	}

	public void setPrincipal(String principal) {
		this.principal = principal;
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
	
	@Column(name="last_cashier")
	public String getLastCashier() {
		return lastCashier;
	}
	public void setLastCashier(String lastCashier) {
		this.lastCashier = lastCashier;
	}
	
	@Column(name="cashier_time")
	@Temporal(TemporalType.DATE)
	public Date getCashierTime() {
		return cashierTime;
	}
	public void setCashierTime(Date cashierTime) {
		this.cashierTime = cashierTime;
	}
	
	@Column(name="if_bdate")
	public Boolean getIfBdate() {
		return ifBdate;
	}

	public void setIfBdate(Boolean ifBdate) {
		this.ifBdate = ifBdate;
	}
	@Column(name = "reach_time", unique = false, nullable = true)
	public String getReachTime() {
		return reachTime;
	}

	public void setReachTime(String reachTime) {
		this.reachTime = reachTime;
	}
    @Column(name="bill_org_id",nullable=false)
	public Integer getBillOrgId() {
		return billOrgId;
	}

	public void setBillOrgId(Integer billOrgId) {
		this.billOrgId = billOrgId;
	}
	
	@Column(name="mobile",length=40)
	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	@Column(name = "prc_scheme_id", length = 6)
	public String getPrcSchemeId() {
		return this.prcSchemeId;
	}

	public void setPrcSchemeId(String prcSchemeId) {
		this.prcSchemeId = prcSchemeId;
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

	public void setConfirmOperid(String confirmOperid) {
		this.confirmOperid = confirmOperid;
	}
    @Column(name="confirm_operid")
	public String getConfirmOperid() {
		return confirmOperid;
	}
    @Transient
	public String getUnitNamec() {
		return unitNamec;
	}

	public void setUnitNamec(String unitNamec) {
		this.unitNamec = unitNamec;
	}
	@Transient
	public String getSaler() {
		return saler;
	}

	public void setSaler(String saler) {
		this.saler = saler;
	}
	
	

	
	
}