package com.cyw.mammoth.vo;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * GuestDetailVo entity. @author MyEclipse Persistence Tools
 */
public class GuestDetailVo implements java.io.Serializable {

	// Fields

	private Integer checkId;
	private String roomId;
	private Integer grpChkid;
	private String gstNamee;
	private String gstNamec;
	private Timestamp reachDate;
	private String tele;
	private Timestamp birthday;
	private String gstKind;
	private String folk;
	private Boolean ifBdate;
	private String chkExt;
	private Integer gstId;
	private Timestamp leaveDate;
	private String compId;
	private String chkStat;
	private String compType;
	private Integer paymanFlag;
	private String prcSchemeId;
	private String grpId;
	private String grpName;
	private String chkOperid;
	private String addr;
	private String sex;
	private String ntId;
	private String notice;
	private String native_;
	private Integer billaId;
	private Integer billbId;
	private String codeNamec;
	private String namec;
	private String taType;
	private Double price;
	private String plateNumber;
	private String crdkindId;
	private String crdId;
	private String email;
	private String sexname;
	private String gstOriId;
	private Integer withId;
	private Boolean cityLedger;
	private Boolean housePay;
	private Double roomPrice;
	private Boolean freeSvc;
	private Boolean hideprice;
	private Boolean ifFgst;
	private String payId;
	private String visakindId;
	private String visaId;
	private String depart;
	private Timestamp crdVld;
	private String inPort;
	private Timestamp inDate;
	private String chkOperName;
	private String rechkOperName;
	private String outOperName;
	private String lastOperName;
	private String lastCashierName;
	private Timestamp cashierTime;
	private Double borrow;
	private Double lent;
	private Double alimit;
	private Double authBalance;
	private Double bborrow;
	private Double blent;
	private Double blimit;
	private Double bauthBalance;
	private String payCodeNamec;
	private Boolean isBpaid;//是否已经分账 true 是 false 否
	// Constructors

	/** default constructor */
	public GuestDetailVo() {
	}

	/** minimal constructor */
	public GuestDetailVo(Integer checkId, String gstNamee, String gstNamec,
			Timestamp reachDate, String gstKind, Timestamp leaveDate,
			String compType, Integer paymanFlag, String sex, String ntId,
			Integer billaId, Integer billbId, String crdkindId,
			String gstOriId, Integer withId, Boolean cityLedger,
			Boolean housePay, Boolean freeSvc, Boolean hideprice,
			Boolean ifFgst, String payId, String visakindId, String inPort,
			Double borrow, Double lent, Double alimit, Double authBalance,
			Double bborrow, Double blent, Double blimit, Double bauthBalance) {
		this.checkId = checkId;
		this.gstNamee = gstNamee;
		this.gstNamec = gstNamec;
		this.reachDate = reachDate;
		this.gstKind = gstKind;
		this.leaveDate = leaveDate;
		this.compType = compType;
		this.paymanFlag = paymanFlag;
		this.sex = sex;
		this.ntId = ntId;
		this.billaId = billaId;
		this.billbId = billbId;
		this.crdkindId = crdkindId;
		this.gstOriId = gstOriId;
		this.withId = withId;
		this.cityLedger = cityLedger;
		this.housePay = housePay;
		this.freeSvc = freeSvc;
		this.hideprice = hideprice;
		this.ifFgst = ifFgst;
		this.payId = payId;
		this.visakindId = visakindId;
		this.inPort = inPort;
		this.borrow = borrow;
		this.lent = lent;
		this.alimit = alimit;
		this.authBalance = authBalance;
		this.bborrow = bborrow;
		this.blent = blent;
		this.blimit = blimit;
		this.bauthBalance = bauthBalance;
	}

	/** full constructor */
	public GuestDetailVo(Integer checkId, String roomId, Integer grpChkid,
			String gstNamee, String gstNamec, Timestamp reachDate, String tele,
			Timestamp birthday, String gstKind, String folk, Boolean ifBdate,
			String chkExt, Integer gstId, Timestamp leaveDate, String compId,
			String chkStat, String compType, Integer paymanFlag,
			String prcSchemeId, String grpId, String grpName, String chkOperid,
			String addr, String sex, String ntId, String notice,
			String native_, Integer billaId, Integer billbId, String codeNamec,
			String namec, String taType, Double price, String plateNumber,
			String crdkindId, String crdId, String email, String sexname,
			String gstOriId, Integer withId, Boolean cityLedger,
			Boolean housePay, Double roomPrice, Boolean freeSvc,
			Boolean hideprice, Boolean ifFgst, String payId, String visakindId,
			String visaId, String depart, Timestamp crdVld, String inPort,
			Timestamp inDate, String chkOperName, String rechkOperName,
			String outOperName, String lastOperName, String lastCashierName,
			Timestamp cashierTime, Double borrow, Double lent, Double alimit,
			Double authBalance, Double bborrow, Double blent, Double blimit,
			Double bauthBalance) {
		this.checkId = checkId;
		this.roomId = roomId;
		this.grpChkid = grpChkid;
		this.gstNamee = gstNamee;
		this.gstNamec = gstNamec;
		this.reachDate = reachDate;
		this.tele = tele;
		this.birthday = birthday;
		this.gstKind = gstKind;
		this.folk = folk;
		this.ifBdate = ifBdate;
		this.chkExt = chkExt;
		this.gstId = gstId;
		this.leaveDate = leaveDate;
		this.compId = compId;
		this.chkStat = chkStat;
		this.compType = compType;
		this.paymanFlag = paymanFlag;
		this.prcSchemeId = prcSchemeId;
		this.grpId = grpId;
		this.grpName = grpName;
		this.chkOperid = chkOperid;
		this.addr = addr;
		this.sex = sex;
		this.ntId = ntId;
		this.notice = notice;
		this.native_ = native_;
		this.billaId = billaId;
		this.billbId = billbId;
		this.codeNamec = codeNamec;
		this.namec = namec;
		this.taType = taType;
		this.price = price;
		this.plateNumber = plateNumber;
		this.crdkindId = crdkindId;
		this.crdId = crdId;
		this.email = email;
		this.sexname = sexname;
		this.gstOriId = gstOriId;
		this.withId = withId;
		this.cityLedger = cityLedger;
		this.housePay = housePay;
		this.roomPrice = roomPrice;
		this.freeSvc = freeSvc;
		this.hideprice = hideprice;
		this.ifFgst = ifFgst;
		this.payId = payId;
		this.visakindId = visakindId;
		this.visaId = visaId;
		this.depart = depart;
		this.crdVld = crdVld;
		this.inPort = inPort;
		this.inDate = inDate;
		this.chkOperName = chkOperName;
		this.rechkOperName = rechkOperName;
		this.outOperName = outOperName;
		this.lastOperName = lastOperName;
		this.lastCashierName = lastCashierName;
		this.cashierTime = cashierTime;
		this.borrow = borrow;
		this.lent = lent;
		this.alimit = alimit;
		this.authBalance = authBalance;
		this.bborrow = bborrow;
		this.blent = blent;
		this.blimit = blimit;
		this.bauthBalance = bauthBalance;
	}

	// Property accessors
	public Integer getCheckId() {
		return this.checkId;
	}

	public void setCheckId(Integer checkId) {
		this.checkId = checkId;
	}

	public String getRoomId() {
		return this.roomId;
	}

	public void setRoomId(String roomId) {
		this.roomId = roomId;
	}

	public Integer getGrpChkid() {
		return this.grpChkid;
	}

	public void setGrpChkid(Integer grpChkid) {
		this.grpChkid = grpChkid;
	}

	public String getGstNamee() {
		return this.gstNamee;
	}

	public void setGstNamee(String gstNamee) {
		this.gstNamee = gstNamee;
	}

	public String getGstNamec() {
		return this.gstNamec;
	}

	public void setGstNamec(String gstNamec) {
		this.gstNamec = gstNamec;
	}

	@DateTimeFormat(pattern="yyyy-MM-dd")
	public Timestamp getReachDate() {
		return this.reachDate;
	}

	public void setReachDate(Timestamp reachDate) {
		this.reachDate = reachDate;
	}

	public String getTele() {
		return this.tele;
	}

	public void setTele(String tele) {
		this.tele = tele;
	}

	@DateTimeFormat(pattern="yyyy-MM-dd")
	public Timestamp getBirthday() {
		return this.birthday;
	}

	public void setBirthday(Timestamp birthday) {
		this.birthday = birthday;
	}

	public String getGstKind() {
		return this.gstKind;
	}

	public void setGstKind(String gstKind) {
		this.gstKind = gstKind;
	}

	public String getFolk() {
		return this.folk;
	}

	public void setFolk(String folk) {
		this.folk = folk;
	}

	public Boolean getIfBdate() {
		return this.ifBdate;
	}

	public void setIfBdate(Boolean ifBdate) {
		this.ifBdate = ifBdate;
	}

	public String getChkExt() {
		return this.chkExt;
	}

	public void setChkExt(String chkExt) {
		this.chkExt = chkExt;
	}

	public Integer getGstId() {
		return this.gstId;
	}

	public void setGstId(Integer gstId) {
		this.gstId = gstId;
	}

	@DateTimeFormat(pattern="yyyy-MM-dd")
	public Timestamp getLeaveDate() {
		return this.leaveDate;
	}

	public void setLeaveDate(Timestamp leaveDate) {
		this.leaveDate = leaveDate;
	}

	public String getCompId() {
		return this.compId;
	}

	public void setCompId(String compId) {
		this.compId = compId;
	}

	public String getChkStat() {
		return this.chkStat;
	}

	public void setChkStat(String chkStat) {
		this.chkStat = chkStat;
	}

	public String getCompType() {
		return this.compType;
	}

	public void setCompType(String compType) {
		this.compType = compType;
	}

	public Integer getPaymanFlag() {
		return this.paymanFlag;
	}

	public void setPaymanFlag(Integer paymanFlag) {
		this.paymanFlag = paymanFlag;
	}

	public String getPrcSchemeId() {
		return this.prcSchemeId;
	}

	public void setPrcSchemeId(String prcSchemeId) {
		this.prcSchemeId = prcSchemeId;
	}

	public String getGrpId() {
		return this.grpId;
	}

	public void setGrpId(String grpId) {
		this.grpId = grpId;
	}

	public String getGrpName() {
		return this.grpName;
	}

	public void setGrpName(String grpName) {
		this.grpName = grpName;
	}

	public String getChkOperid() {
		return this.chkOperid;
	}

	public void setChkOperid(String chkOperid) {
		this.chkOperid = chkOperid;
	}

	public String getAddr() {
		return this.addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	public String getSex() {
		return this.sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getNtId() {
		return this.ntId;
	}

	public void setNtId(String ntId) {
		this.ntId = ntId;
	}

	public String getNotice() {
		return this.notice;
	}

	public void setNotice(String notice) {
		this.notice = notice;
	}

	public String getNative_() {
		return this.native_;
	}

	public void setNative_(String native_) {
		this.native_ = native_;
	}

	public Integer getBillaId() {
		return this.billaId;
	}

	public void setBillaId(Integer billaId) {
		this.billaId = billaId;
	}

	public Integer getBillbId() {
		return this.billbId;
	}

	public void setBillbId(Integer billbId) {
		this.billbId = billbId;
	}

	public String getCodeNamec() {
		return this.codeNamec;
	}

	public void setCodeNamec(String codeNamec) {
		this.codeNamec = codeNamec;
	}

	public String getNamec() {
		return this.namec;
	}

	public void setNamec(String namec) {
		this.namec = namec;
	}

	public String getTaType() {
		return this.taType;
	}

	public void setTaType(String taType) {
		this.taType = taType;
	}

	public Double getPrice() {
		return this.price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getPlateNumber() {
		return this.plateNumber;
	}

	public void setPlateNumber(String plateNumber) {
		this.plateNumber = plateNumber;
	}

	public String getCrdkindId() {
		return this.crdkindId;
	}

	public void setCrdkindId(String crdkindId) {
		this.crdkindId = crdkindId;
	}

	public String getCrdId() {
		return this.crdId;
	}

	public void setCrdId(String crdId) {
		this.crdId = crdId;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSexname() {
		return this.sexname;
	}

	public void setSexname(String sexname) {
		this.sexname = sexname;
	}

	public String getGstOriId() {
		return this.gstOriId;
	}

	public void setGstOriId(String gstOriId) {
		this.gstOriId = gstOriId;
	}

	public Integer getWithId() {
		return this.withId;
	}

	public void setWithId(Integer withId) {
		this.withId = withId;
	}

	public Boolean getCityLedger() {
		return this.cityLedger;
	}

	public void setCityLedger(Boolean cityLedger) {
		this.cityLedger = cityLedger;
	}

	public Boolean getHousePay() {
		return this.housePay;
	}

	public void setHousePay(Boolean housePay) {
		this.housePay = housePay;
	}

	public Double getRoomPrice() {
		return this.roomPrice;
	}

	public void setRoomPrice(Double roomPrice) {
		this.roomPrice = roomPrice;
	}

	public Boolean getFreeSvc() {
		return this.freeSvc;
	}

	public void setFreeSvc(Boolean freeSvc) {
		this.freeSvc = freeSvc;
	}

	public Boolean getHideprice() {
		return this.hideprice;
	}

	public void setHideprice(Boolean hideprice) {
		this.hideprice = hideprice;
	}

	public Boolean getIfFgst() {
		return this.ifFgst;
	}

	public void setIfFgst(Boolean ifFgst) {
		this.ifFgst = ifFgst;
	}

	public String getPayId() {
		return this.payId;
	}

	public void setPayId(String payId) {
		this.payId = payId;
	}

	public String getVisakindId() {
		return this.visakindId;
	}

	public void setVisakindId(String visakindId) {
		this.visakindId = visakindId;
	}

	public String getVisaId() {
		return this.visaId;
	}

	public void setVisaId(String visaId) {
		this.visaId = visaId;
	}

	public String getDepart() {
		return this.depart;
	}

	public void setDepart(String depart) {
		this.depart = depart;
	}

	public Timestamp getCrdVld() {
		return this.crdVld;
	}

	public void setCrdVld(Timestamp crdVld) {
		this.crdVld = crdVld;
	}

	public String getInPort() {
		return this.inPort;
	}

	public void setInPort(String inPort) {
		this.inPort = inPort;
	}

	public Timestamp getInDate() {
		return this.inDate;
	}

	public void setInDate(Timestamp inDate) {
		this.inDate = inDate;
	}

	public String getChkOperName() {
		return this.chkOperName;
	}

	public void setChkOperName(String chkOperName) {
		this.chkOperName = chkOperName;
	}

	public String getRechkOperName() {
		return this.rechkOperName;
	}

	public void setRechkOperName(String rechkOperName) {
		this.rechkOperName = rechkOperName;
	}

	public String getOutOperName() {
		return this.outOperName;
	}

	public void setOutOperName(String outOperName) {
		this.outOperName = outOperName;
	}

	public String getLastOperName() {
		return this.lastOperName;
	}

	public void setLastOperName(String lastOperName) {
		this.lastOperName = lastOperName;
	}

	public String getLastCashierName() {
		return this.lastCashierName;
	}

	public void setLastCashierName(String lastCashierName) {
		this.lastCashierName = lastCashierName;
	}

	public Timestamp getCashierTime() {
		return this.cashierTime;
	}

	public void setCashierTime(Timestamp cashierTime) {
		this.cashierTime = cashierTime;
	}

	public Double getBorrow() {
		return this.borrow;
	}

	public void setBorrow(Double borrow) {
		this.borrow = borrow;
	}

	public Double getLent() {
		return this.lent;
	}

	public void setLent(Double lent) {
		this.lent = lent;
	}

	public Double getAlimit() {
		return this.alimit;
	}

	public void setAlimit(Double alimit) {
		this.alimit = alimit;
	}

	public Double getAuthBalance() {
		return this.authBalance;
	}

	public void setAuthBalance(Double authBalance) {
		this.authBalance = authBalance;
	}

	public Double getBborrow() {
		return this.bborrow;
	}

	public void setBborrow(Double bborrow) {
		this.bborrow = bborrow;
	}

	public Double getBlent() {
		return this.blent;
	}

	public void setBlent(Double blent) {
		this.blent = blent;
	}

	public Double getBlimit() {
		return this.blimit;
	}

	public void setBlimit(Double blimit) {
		this.blimit = blimit;
	}

	public Double getBauthBalance() {
		return this.bauthBalance;
	}

	public void setBauthBalance(Double bauthBalance) {
		this.bauthBalance = bauthBalance;
	}

	public String getPayCodeNamec() {
		return payCodeNamec;
	}

	public void setPayCodeNamec(String payCodeNamec) {
		this.payCodeNamec = payCodeNamec;
	}

	public Boolean getIsBpaid() {
		return isBpaid;
	}

	public void setIsBpaid(Boolean isBpaid) {
		this.isBpaid = isBpaid;
	}

}