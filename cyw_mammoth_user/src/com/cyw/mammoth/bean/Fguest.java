package com.cyw.mammoth.bean;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * Fguest entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "fguest", schema = "dbo", catalog = "mammoth")
public class Fguest implements java.io.Serializable {

	// Fields

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -1218196087692340630L;
	private Integer gstId;//熟客编号
	private String gstNamee;//英文名
	private String gstNamec;//中文名
	private String sex;//性别
	private Integer age;
	private String wrkComp;
	private String notice;//备注
	private Date reachDate;//抵店日期/上次抵店日期
	private Date leaveDate;//离店日期//上次离店日期
	private Double lastCons;
	private String roomId;//房号/上次房号
	private String roomTypeid;//常主房类/房类代码
	private Long roomCharacter;//客房特征
	private Double roomPrice;//房价/上次房价
	private Integer paymanFlag;
	private String gstOriId;//客人来源
	private String prcSchemeId;
	private String payId;
	private Double limit;//信用限额
	private String creditId;//信用卡号
	private String compType;//合约单位类型
	private String compId;//合约单位ID
	private Integer compCd;
	private Boolean cityLedger=Boolean.TRUE;//是否可挂账/可挂AR账
	private Boolean housePay=Boolean.TRUE;;//是否可挂房账
	private Boolean freeSvc=Boolean.TRUE;;//是否免收服务费
	private Boolean changeRate=Boolean.TRUE;;
	private String guarantor;//担保人
	private Boolean secret;
	private Boolean hideprice;//是否隐藏房价
	private String teleStatus;
	private String gstKind;//客人分类
	private String gstFlag;
	private String ntId;//国籍
	private String areaId;//地区/省市
	private String bookType;
	private String bookList;
	private String tele;//电话
	private String mobile;//联系电话
	private String email;//email
	private String plateNumber;//车牌号
	private String crdkindId;//证件类型
	private String crdId;//证件号码
	private Date crdVld;
	private byte[] crdImg;
	private byte[] signImg;
	private Date birthday;//出生日期
	private String visakindId;//签证类型
	private String visaId;//签证号码
	private String depart;//签发机关
	private String inPort;//入境口岸
	private Date inDate;//入境日期
	private String reason;
	private String addr;//地址
	private String operId;
	private Date operTime;
	private String native_;//籍贯
	private String folk;//民族
	private String works;
	private String regAddr;
	private Boolean iddOn=Boolean.TRUE;
	private Integer arNum;//AR账号
	private String vilhotelId;
	private String chainId;
	private Integer status;//状态
	private String bankId;//开户行 

	// Constructors

	/** default constructor */
	public Fguest() {
	}

	/** minimal constructor */
	public Fguest(Integer gstId, String gstNamee, String gstNamec, String sex,
			Integer age, Date reachDate, Date leaveDate,
			Double lastCons, String roomTypeid, Long roomCharacter,
			Integer paymanFlag, String gstOriId, String payId, Double limit,
			String creditId, String compType, Boolean cityLedger,
			Boolean housePay, Boolean freeSvc, Boolean changeRate,
			Boolean secret, Boolean hideprice, String teleStatus,
			String gstKind, String gstFlag, String ntId, String areaId,
			String bookType, String crdkindId, String visakindId,
			String inPort, String reason, String operId, Date operTime,
			Boolean iddOn) {
		this.gstId = gstId;
		this.gstNamee = gstNamee;
		this.gstNamec = gstNamec;
		this.sex = sex;
		this.age = age;
		this.reachDate = reachDate;
		this.leaveDate = leaveDate;
		this.lastCons = lastCons;
		this.roomTypeid = roomTypeid;
		this.roomCharacter = roomCharacter;
		this.paymanFlag = paymanFlag;
		this.gstOriId = gstOriId;
		this.payId = payId;
		this.limit = limit;
		this.creditId = creditId;
		this.compType = compType;
		this.cityLedger = cityLedger;
		this.housePay = housePay;
		this.freeSvc = freeSvc;
		this.changeRate = changeRate;
		this.secret = secret;
		this.hideprice = hideprice;
		this.teleStatus = teleStatus;
		this.gstKind = gstKind;
		this.gstFlag = gstFlag;
		this.ntId = ntId;
		this.areaId = areaId;
		this.bookType = bookType;
		this.crdkindId = crdkindId;
		this.visakindId = visakindId;
		this.inPort = inPort;
		this.reason = reason;
		this.operId = operId;
		this.operTime = operTime;
		this.iddOn = iddOn;
	}

	/** full constructor */
	public Fguest(Integer gstId, String gstNamee, String gstNamec, String sex,
			Integer age, String wrkComp, String notice, Date reachDate,
			Date leaveDate, Double lastCons, String roomId,
			String roomTypeid, Long roomCharacter, Double roomPrice,
			Integer paymanFlag, String gstOriId, String prcSchemeId,
			String payId, Double limit, String creditId, String compType,
			String compId, Integer compCd, Boolean cityLedger,
			Boolean housePay, Boolean freeSvc, Boolean changeRate,
			String guarantor, Boolean secret, Boolean hideprice,
			String teleStatus, String gstKind, String gstFlag, String ntId,
			String areaId, String bookType, String bookList, String tele,
			String mobile, String email, String plateNumber, String crdkindId,
			String crdId, Date crdVld, byte[] crdImg, byte[] signImg,
			Date birthday, String visakindId, String visaId,
			String depart, String inPort, Date inDate, String reason,
			String addr, String operId, Date operTime, String native_,
			String folk, String works, String regAddr, Boolean iddOn,
			Integer arNum, String vilhotelId, String chainId) {
		this.gstId = gstId;
		this.gstNamee = gstNamee;
		this.gstNamec = gstNamec;
		this.sex = sex;
		this.age = age;
		this.wrkComp = wrkComp;
		this.notice = notice;
		this.reachDate = reachDate;
		this.leaveDate = leaveDate;
		this.lastCons = lastCons;
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
		this.mobile = mobile;
		this.email = email;
		this.plateNumber = plateNumber;
		this.crdkindId = crdkindId;
		this.crdId = crdId;
		this.crdVld = crdVld;
		this.crdImg = crdImg;
		this.signImg = signImg;
		this.birthday = birthday;
		this.visakindId = visakindId;
		this.visaId = visaId;
		this.depart = depart;
		this.inPort = inPort;
		this.inDate = inDate;
		this.reason = reason;
		this.addr = addr;
		this.operId = operId;
		this.operTime = operTime;
		this.native_ = native_;
		this.folk = folk;
		this.works = works;
		this.regAddr = regAddr;
		this.iddOn = iddOn;
		this.arNum = arNum;
		this.vilhotelId = vilhotelId;
		this.chainId = chainId;
	}

	// Property accessors
	@Id
	@Column(name = "gst_id", unique = true, nullable = false)
	@GeneratedValue(strategy = GenerationType.TABLE,generator="customer_gen")
    @TableGenerator(name = "customer_gen",
    		           schema = "dbo", 
    		           catalog = "mammoth",
                       table="parameter",
                       pkColumnName="id",
                       valueColumnName="para1",
                       pkColumnValue="3",
                       allocationSize=1
    )
	public Integer getGstId() {
		return this.gstId;
	}

	public void setGstId(Integer gstId) {
		this.gstId = gstId;
	}

	@Column(name = "gst_namee", nullable = false, length = 40)
	public String getGstNamee() {
		return this.gstNamee;
	}

	public void setGstNamee(String gstNamee) {
		this.gstNamee = gstNamee;
	}

	@Column(name = "gst_namec", nullable = false, length = 10)
	public String getGstNamec() {
		return this.gstNamec;
	}

	public void setGstNamec(String gstNamec) {
		this.gstNamec = gstNamec;
	}

	@Column(name = "sex", nullable = false, length = 6)
	public String getSex() {
		return this.sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	@Column(name = "age", nullable = false)
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

	@Column(name = "notice")
	public String getNotice() {
		return this.notice;
	}

	public void setNotice(String notice) {
		this.notice = notice;
	}

	@Column(name = "reach_date", nullable = false, length = 23)
	@DateTimeFormat(pattern="yyyy-MM-dd")
	public Date getReachDate() {
		return this.reachDate;
	}

	public void setReachDate(Date reachDate) {
		this.reachDate = reachDate;
	}

	@Column(name = "leave_date", nullable = false, length = 23)
	@DateTimeFormat(pattern="yyyy-MM-dd")
	public Date getLeaveDate() {
		return this.leaveDate;
	}

	public void setLeaveDate(Date leaveDate) {
		this.leaveDate = leaveDate;
	}

	@Column(name = "last_cons", nullable = false, precision = 15, scale = 0)
	public Double getLastCons() {
		return this.lastCons;
	}

	public void setLastCons(Double lastCons) {
		this.lastCons = lastCons;
	}

	@Column(name = "room_id", length = 6)
	public String getRoomId() {
		return this.roomId;
	}

	public void setRoomId(String roomId) {
		this.roomId = roomId;
	}

	@Column(name = "room_typeid", nullable = false, length = 3)
	public String getRoomTypeid() {
		return this.roomTypeid;
	}

	public void setRoomTypeid(String roomTypeid) {
		this.roomTypeid = roomTypeid;
	}

	@Column(name = "room_character", nullable = false, length = 50)
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

	@Column(name = "payman_flag", nullable = false)
	public Integer getPaymanFlag() {
		return this.paymanFlag;
	}

	public void setPaymanFlag(Integer paymanFlag) {
		this.paymanFlag = paymanFlag;
	}

	@Column(name = "gst_ori_id", nullable = false, length = 3)
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

	@Column(name = "pay_id", nullable = false, length = 6)
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

	@Column(name = "credit_id", nullable = false, length = 25)
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

	@Column(name = "free_svc", nullable = false)
	public Boolean getFreeSvc() {
		return this.freeSvc;
	}

	public void setFreeSvc(Boolean freeSvc) {
		this.freeSvc = freeSvc;
	}

	@Column(name = "Change_rate", nullable = false)
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

	@Column(name = "tele_status", nullable = false, length = 6)
	public String getTeleStatus() {
		return this.teleStatus;
	}

	public void setTeleStatus(String teleStatus) {
		this.teleStatus = teleStatus;
	}

	@Column(name = "gst_kind", nullable = false, length = 6)
	public String getGstKind() {
		return this.gstKind;
	}

	public void setGstKind(String gstKind) {
		this.gstKind = gstKind;
	}

	@Column(name = "gst_flag", nullable = false, length = 1)
	public String getGstFlag() {
		return this.gstFlag;
	}

	public void setGstFlag(String gstFlag) {
		this.gstFlag = gstFlag;
	}

	@Column(name = "nt_id", nullable = false, length = 3)
	public String getNtId() {
		return this.ntId;
	}

	public void setNtId(String ntId) {
		this.ntId = ntId;
	}

	@Column(name = "area_id", nullable = false, length = 6)
	public String getAreaId() {
		return this.areaId;
	}

	public void setAreaId(String areaId) {
		this.areaId = areaId;
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

	@Column(name = "crdkind_id", nullable = false, length = 6)
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

	@Column(name = "crd_vld", length = 23)
	public Date getCrdVld() {
		return this.crdVld;
	}

	public void setCrdVld(Date crdVld) {
		this.crdVld = crdVld;
	}

	@Column(name = "crd_img")
	public byte[] getCrdImg() {
		return this.crdImg;
	}

	public void setCrdImg(byte[] crdImg) {
		this.crdImg = crdImg;
	}

	@Column(name = "sign_img")
	public byte[] getSignImg() {
		return this.signImg;
	}

	public void setSignImg(byte[] signImg) {
		this.signImg = signImg;
	}

	@Column(name = "birthday", length = 23)
	@DateTimeFormat(pattern="yyyy-MM-dd")
	public Date getBirthday() {
		return this.birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	@Column(name = "visakind_id", nullable = false, length = 6)
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

	@Column(name = "in_port", nullable = false, length = 6)
	public String getInPort() {
		return this.inPort;
	}

	public void setInPort(String inPort) {
		this.inPort = inPort;
	}

	@Column(name = "in_date", length = 23)
	public Date getInDate() {
		return this.inDate;
	}

	public void setInDate(Date inDate) {
		this.inDate = inDate;
	}

	@Column(name = "reason", nullable = false, length = 6)
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

	@Column(name = "oper_id", nullable = false, length = 10)
	public String getOperId() {
		return this.operId;
	}

	public void setOperId(String operId) {
		this.operId = operId;
	}

	@Column(name = "oper_time", nullable = false, length = 23)
	public Date getOperTime() {
		return this.operTime;
	}

	public void setOperTime(Date operTime) {
		this.operTime = operTime;
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

	@Column(name = "IddOn", nullable = false)
	public Boolean getIddOn() {
		return this.iddOn;
	}

	public void setIddOn(Boolean iddOn) {
		this.iddOn = iddOn;
	}

	@Column(name = "AR_num")
	public Integer getArNum() {
		return this.arNum;
	}

	public void setArNum(Integer arNum) {
		this.arNum = arNum;
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
	@Column(name = "status", nullable = false, length = 6)
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
	@Column(name = "bankId",  length = 6)
	public String getBankId() {
		return bankId;
	}

	public void setBankId(String bankId) {
		this.bankId = bankId;
	}

}