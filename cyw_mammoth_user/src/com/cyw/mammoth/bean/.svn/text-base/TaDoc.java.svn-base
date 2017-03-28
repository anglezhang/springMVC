package com.cyw.mammoth.bean;

import java.util.Date;

import javax.annotation.Generated;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Transient;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * TaDoc entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "ta_doc", schema = "dbo", catalog = "mammoth")
public class TaDoc implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer taCompCd;
	private String compId;
	private String contId;
	private String namee;
	private String namec;
	private String taType;
	private String levels;
	private String salemanId;
	private String area;
	private String trade;
	private Integer arNum;
	private String typeId;
	private String rateCode;
	private String gstKind;
	private String gstOri;
	private String connector;
	private String addr;
	private String tele;
	private String fax;
	private String zip;
	private String email;
	private Integer cmsPerc;
	private String cmsMeth;
	private String setlId;
	private Double limit;
	private Short discPert;
	private Integer period;
	private Boolean ifPerm;
	private String bankId;
	private String bankNum;
	private Date noticeDate;
	private Double amt;
	private String operId;
	private Date operTime;
	private String lastOper;
	private Date lastTime;
	private String cancelOper;
	private Date cancelTime;
	private Integer status;
	private String notes;
	private String vilhotelId;
	private String chainId;
	private String phone;
	private Date expiryDate;

	private String salemanName;
	// Constructors

	/** default constructor */
	public TaDoc() {
	}

	/** minimal constructor */
	public TaDoc(Integer id, String compId, String contId, String namee,
			String namec, String taType, String levels, String salemanId,
			String area, String trade, Double limit, Short discPert,
			Integer period, Boolean ifPerm, String bankId, Double amt,
			String operId, Date operTime, String lastOper,
			Date lastTime, String cancelOper, Date cancelTime,
			Integer status, String notes) {
		this.id = id;
		this.compId = compId;
		this.contId = contId;
		this.namee = namee;
		this.namec = namec;
		this.taType = taType;
		this.levels = levels;
		this.salemanId = salemanId;
		this.area = area;
		this.trade = trade;
		this.limit = limit;
		this.discPert = discPert;
		this.period = period;
		this.ifPerm = ifPerm;
		this.bankId = bankId;
		this.amt = amt;
		this.operId = operId;
		this.operTime = operTime;
		this.lastOper = lastOper;
		this.lastTime = lastTime;
		this.cancelOper = cancelOper;
		this.cancelTime = cancelTime;
		this.status = status;
		this.notes = notes;
	}

	/** full constructor */

	public TaDoc(Integer id, Integer taCompCd, String compId, String contId,
			String namee, String namec, String taType, String levels,
			String salemanId, String area, String trade, Integer arNum,
			String typeId, String rateCode, String gstKind, String gstOri,
			String connector, String addr, String tele, String fax, String zip,
			String email, Integer cmsPerc, String cmsMeth, String setlId,
			Double limit, Short discPert, Integer period, Boolean ifPerm,
			String bankId, String bankNum, Date noticeDate, Double amt,
			String operId, Date operTime, String lastOper, Date lastTime,
			String cancelOper, Date cancelTime, Integer status, String notes,
			String vilhotelId, String chainId) {
		super();
		this.id = id;
		this.taCompCd = taCompCd;
		this.compId = compId;
		this.contId = contId;
		this.namee = namee;
		this.namec = namec;
		this.taType = taType;
		this.levels = levels;
		this.salemanId = salemanId;
		this.area = area;
		this.trade = trade;
		this.arNum = arNum;
		this.typeId = typeId;
		this.rateCode = rateCode;
		this.gstKind = gstKind;
		this.gstOri = gstOri;
		this.connector = connector;
		this.addr = addr;
		this.tele = tele;
		this.fax = fax;
		this.zip = zip;
		this.email = email;
		this.cmsPerc = cmsPerc;
		this.cmsMeth = cmsMeth;
		this.setlId = setlId;
		this.limit = limit;
		this.discPert = discPert;
		this.period = period;
		this.ifPerm = ifPerm;
		this.bankId = bankId;
		this.bankNum = bankNum;
		this.noticeDate = noticeDate;
		this.amt = amt;
		this.operId = operId;
		this.operTime = operTime;
		this.lastOper = lastOper;
		this.lastTime = lastTime;
		this.cancelOper = cancelOper;
		this.cancelTime = cancelTime;
		this.status = status;
		this.notes = notes;
		this.vilhotelId = vilhotelId;
		this.chainId = chainId;
	}

	// Property accessors
	@Id
	@Column(name = "id", unique = true, nullable = false)
	@GeneratedValue(strategy = GenerationType.TABLE,generator="customer_gen")
    @TableGenerator(name = "customer_gen",
    		           schema = "dbo", 
    		           catalog = "mammoth",
                       table="parameter",
                       pkColumnName="id",
                       valueColumnName="para1",
                       pkColumnValue="4",
                       allocationSize=1
    )
	public Integer getId() {
		return this.id;
	}


	public void setId(Integer id) {
		this.id = id;
	}

	
	@Column(name = "TA_comp_cd", nullable = true)
	public Integer getTaCompCd() {
		return taCompCd;
	}

	public void setTaCompCd(Integer taCompCd) {
		this.taCompCd = taCompCd;
	}

	@Column(name = "comp_id", nullable = false, length = 60)
	public String getCompId() {
		return this.compId;
	}

	public void setCompId(String compId) {
		this.compId = compId;
	}

	@Column(name = "cont_id", nullable = false, length = 30)
	public String getContId() {
		return this.contId;
	}

	public void setContId(String contId) {
		this.contId = contId;
	}

	@Column(name = "namee", nullable = false, length = 100)
	public String getNamee() {
		return this.namee;
	}

	public void setNamee(String namee) {
		this.namee = namee;
	}

	@Column(name = "namec", nullable = false, length = 100)
	public String getNamec() {
		return this.namec;
	}

	public void setNamec(String namec) {
		this.namec = namec;
	}

	@Column(name = "TA_type", nullable = false, length = 1)
	public String getTaType() {
		return this.taType;
	}

	public void setTaType(String taType) {
		this.taType = taType;
	}

	@Column(name = "levels", nullable = false, length = 6)
	public String getLevels() {
		return this.levels;
	}

	public void setLevels(String levels) {
		this.levels = levels;
	}

	@Column(name = "saleman_id", nullable = false, length = 6)
	public String getSalemanId() {
		return this.salemanId;
	}

	public void setSalemanId(String salemanId) {
		this.salemanId = salemanId;
	}

	@Column(name = "area", nullable = false, length = 6)
	public String getArea() {
		return this.area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	@Column(name = "trade", nullable = false, length = 6)
	public String getTrade() {
		return this.trade;
	}

	public void setTrade(String trade) {
		this.trade = trade;
	}

	@Column(name = "ar_num")
	public Integer getArNum() {
		return this.arNum;
	}

	public void setArNum(Integer arNum) {
		this.arNum = arNum;
	}

	@Column(name = "type_id", length = 3)
	public String getTypeId() {
		return this.typeId;
	}

	public void setTypeId(String typeId) {
		this.typeId = typeId;
	}

	@Column(name = "rate_code", length = 3)
	public String getRateCode() {
		return this.rateCode;
	}

	public void setRateCode(String rateCode) {
		this.rateCode = rateCode;
	}

	@Column(name = "gst_kind", length = 6)
	public String getGstKind() {
		return this.gstKind;
	}

	public void setGstKind(String gstKind) {
		this.gstKind = gstKind;
	}

	@Column(name = "gst_ori", length = 3)
	public String getGstOri() {
		return this.gstOri;
	}

	public void setGstOri(String gstOri) {
		this.gstOri = gstOri;
	}

	@Column(name = "connector", length = 30)
	public String getConnector() {
		return this.connector;
	}

	public void setConnector(String connector) {
		this.connector = connector;
	}

	@Column(name = "addr", length = 100)
	public String getAddr() {
		return this.addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	@Column(name = "tele", length = 30)
	public String getTele() {
		return this.tele;
	}

	public void setTele(String tele) {
		this.tele = tele;
	}

	@Column(name = "fax", length = 20)
	public String getFax() {
		return this.fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	@Column(name = "zip", length = 20)
	public String getZip() {
		return this.zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	@Column(name = "email", length = 64)
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "cms_perc")
	public Integer getCmsPerc() {
		return this.cmsPerc;
	}

	public void setCmsPerc(Integer cmsPerc) {
		this.cmsPerc = cmsPerc;
	}

	@Column(name = "cms_meth", length = 1)
	public String getCmsMeth() {
		return this.cmsMeth;
	}

	public void setCmsMeth(String cmsMeth) {
		this.cmsMeth = cmsMeth;
	}

	@Column(name = "setl_id", length = 3)
	public String getSetlId() {
		return this.setlId;
	}

	public void setSetlId(String setlId) {
		this.setlId = setlId;
	}

	@Column(name = "limit", nullable = false, precision = 15, scale = 0)
	public Double getLimit() {
		return this.limit;
	}

	public void setLimit(Double limit) {
		this.limit = limit;
	}

	@Column(name = "disc_pert", nullable = false)
	public Short getDiscPert() {
		return this.discPert;
	}

	public void setDiscPert(Short discPert) {
		this.discPert = discPert;
	}

	@Column(name = "period", nullable = false)
	public Integer getPeriod() {
		return this.period;
	}

	public void setPeriod(Integer period) {
		this.period = period;
	}

	@Column(name = "if_perm", nullable = false)
	public Boolean getIfPerm() {
		return this.ifPerm;
	}

	public void setIfPerm(Boolean ifPerm) {
		this.ifPerm = ifPerm;
	}

	@Column(name = "bank_id", nullable = false, length = 30)
	public String getBankId() {
		return this.bankId;
	}

	public void setBankId(String bankId) {
		this.bankId = bankId;
	}

	@Column(name = "bank_num", length = 30)
	public String getBankNum() {
		return this.bankNum;
	}

	public void setBankNum(String bankNum) {
		this.bankNum = bankNum;
	}

	@Column(name = "notice_date", length = 23)
	@DateTimeFormat(pattern="yyyy-MM-dd")
	public Date getNoticeDate() {
		return this.noticeDate;
	}

	public void setNoticeDate(Date noticeDate) {
		this.noticeDate = noticeDate;
	}

	@Column(name = "amt", nullable = false, precision = 15, scale = 0)
	public Double getAmt() {
		return this.amt;
	}

	public void setAmt(Double amt) {
		this.amt = amt;
	}

	@Column(name = "oper_id", nullable = false, length = 10)
	public String getOperId() {
		return this.operId;
	}

	public void setOperId(String operId) {
		this.operId = operId;
	}

	@Column(name = "oper_time", nullable = false, length = 23)
	@DateTimeFormat(pattern="yyyy-MM-dd")
	public Date getOperTime() {
		return this.operTime;
	}

	public void setOperTime(Date operTime) {
		this.operTime = operTime;
	}

	@Column(name = "last_oper", nullable = false, length = 10)
	@DateTimeFormat(pattern="yyyy-MM-dd")
	public String getLastOper() {
		return this.lastOper;
	}

	public void setLastOper(String lastOper) {
		this.lastOper = lastOper;
	}

	@Column(name = "last_time", nullable = false, length = 23)
	@DateTimeFormat(pattern="yyyy-MM-dd")
	public Date getLastTime() {
		return this.lastTime;
	}

	public void setLastTime(Date lastTime) {
		this.lastTime = lastTime;
	}

	@Column(name = "cancel_oper", nullable = false, length = 10)
	public String getcancelOper() {
		return this.cancelOper;
	}

	public void setcancelOper(String cancelOper) {
		this.cancelOper = cancelOper;
	}

	@Column(name = "cancel_time", nullable = false, length = 23)
	@DateTimeFormat(pattern="yyyy-MM-dd")
	public Date getcancelTime() {
		return this.cancelTime;
	}

	public void setcancelTime(Date cancelTime) {
		this.cancelTime = cancelTime;
	}

	@Column(name = "status", nullable = false)
	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	@Column(name = "notes", nullable = false)
	public String getNotes() {
		return this.notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
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
	@Column(name = "phone", length = 15)
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Column(name = "expiry_date", nullable = false, length = 23)
	@DateTimeFormat(pattern="yyyy-MM-dd")
	public Date getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}
	@Transient
	public String getSalemanName() {
		return salemanName;
	}

	public void setSalemanName(String salemanName) {
		this.salemanName = salemanName;
	}
	
}