package com.cyw.mammoth.bean;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * Noguest entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "noguest", schema = "dbo", catalog = "mammoth")
public class Noguest implements java.io.Serializable {

	// Fields

	private Integer id;
	private String nogstId;
	private String nogstName;
	private String notes;
	private Integer billId;
	private Double borrow;
	private Double lent;
	private String payId;
	private Double limit;
	private String creditId;
	private String compType;
	private String compId;
	private String creaId;
	private Date creaTime;
	private Date modiTime;
	private Date payDate;
	private Boolean hotelFlag;
	private Integer status;
	private String modiId;
	private Integer updateTimes;
	private Integer compCd;
	private Integer changeRate;
	private String phone;
	private String mobile;
	private String email;
	private String fax;
	private String connector;
	private String lastCashier;
	private Date cashierTime;
	private String vilhotelId;
	private String chainId;
	private String bankaddr;
	private String bankno;
	
	private Double nBorrow;
	private Double nLent;
	private Double nRemain;
	private Double nLimit;
	private String namec;
	@Transient
	private String unitNamec;

	// Constructors

	/** default constructor */
	public Noguest() {
	}

	/** minimal constructor */
	public Noguest(Integer id, String nogstId, String nogstName,
			Integer billId, Double borrow, Double lent, String payId,
			Double limit, String creditId, String compType, String creaId,
			Date creaTime, Boolean hotelFlag, Integer status,
			Integer updateTimes, String bankaddr, String bankno) {
		this.id = id;
		this.nogstId = nogstId;
		this.nogstName = nogstName;
		this.billId = billId;
		this.borrow = borrow;
		this.lent = lent;
		this.payId = payId;
		this.limit = limit;
		this.creditId = creditId;
		this.compType = compType;
		this.creaId = creaId;
		this.creaTime = creaTime;
		this.hotelFlag = hotelFlag;
		this.status = status;
		this.updateTimes = updateTimes;
		this.bankaddr = bankaddr;
		this.bankno = bankno;
	}

	/** full constructor */
	public Noguest(Integer id, String nogstId, String nogstName, String notes,
			Integer billId, Double borrow, Double lent, String payId,
			Double limit, String creditId, String compType, String compId,
			String creaId, Date creaTime, Date modiTime,
			Date payDate, Boolean hotelFlag, Integer status,
			String modiId, Integer updateTimes, Integer compCd,
			Integer changeRate, String phone, String mobile, String email,
			String fax, String connector, String lastCashier,
			Date cashierTime, String vilhotelId, String chainId, String bankaddr, String bankno) {
		this.id = id;
		this.nogstId = nogstId;
		this.nogstName = nogstName;
		this.notes = notes;
		this.billId = billId;
		this.borrow = borrow;
		this.lent = lent;
		this.payId = payId;
		this.limit = limit;
		this.creditId = creditId;
		this.compType = compType;
		this.compId = compId;
		this.creaId = creaId;
		this.creaTime = creaTime;
		this.modiTime = modiTime;
		this.payDate = payDate;
		this.hotelFlag = hotelFlag;
		this.status = status;
		this.modiId = modiId;
		this.updateTimes = updateTimes;
		this.compCd = compCd;
		this.changeRate = changeRate;
		this.phone = phone;
		this.mobile = mobile;
		this.email = email;
		this.fax = fax;
		this.connector = connector;
		this.lastCashier = lastCashier;
		this.cashierTime = cashierTime;
		this.vilhotelId = vilhotelId;
		this.chainId = chainId;
		this.bankaddr = bankaddr;
		this.bankno = bankno;
	}

	// Property accessors
	@Id
	@Column(name = "ID", unique = true, nullable = false)
	@GeneratedValue
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "nogst_id", nullable = false, length = 40)
	public String getNogstId() {
		return this.nogstId;
	}

	public void setNogstId(String nogstId) {
		this.nogstId = nogstId;
	}

	@Column(name = "nogst_name", nullable = false, length = 40)
	public String getNogstName() {
		return this.nogstName;
	}

	public void setNogstName(String nogstName) {
		this.nogstName = nogstName;
	}

	@Column(name = "notes")
	public String getNotes() {
		return this.notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	@Column(name = "bill_id", nullable = false)
	public Integer getBillId() {
		return this.billId;
	}

	public void setBillId(Integer billId) {
		this.billId = billId;
	}

	@Column(name = "borrow", nullable = false, precision = 15, scale = 0)
	public Double getBorrow() {
		return this.borrow;
	}

	public void setBorrow(Double borrow) {
		this.borrow = borrow;
	}

	@Column(name = "lent", nullable = true, precision = 15, scale = 0)
	public Double getLent() {
		return this.lent;
	}

	public void setLent(Double lent) {
		this.lent = lent;
	}

	@Column(name = "pay_id", nullable = true, length = 6)
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

	@Column(name = "credit_id", nullable = true, length = 25,insertable=true,updatable=true)
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

	@Column(name = "crea_id", nullable = true, length = 10)
	public String getCreaId() {
		return this.creaId;
	}

	public void setCreaId(String creaId) {
		this.creaId = creaId;
	}
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@Column(name = "crea_time", nullable = false, length = 23)
	public Date getCreaTime() {
		return this.creaTime;
	}

	public void setCreaTime(Date creaTime) {
		this.creaTime = creaTime;
	}
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@Column(name = "modi_time", length = 23)
	public Date getModiTime() {
		return this.modiTime;
	}

	public void setModiTime(Date modiTime) {
		this.modiTime = modiTime;
	}
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@Column(name = "pay_date", length = 23)
	public Date getPayDate() {
		return this.payDate;
	}

	public void setPayDate(Date payDate) {
		this.payDate = payDate;
	}

	@Column(name = "hotel_flag", nullable = false)
	public Boolean getHotelFlag() {
		return this.hotelFlag;
	}

	public void setHotelFlag(Boolean hotelFlag) {
		this.hotelFlag = hotelFlag;
	}

	@Column(name = "status", nullable = true)
	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	@Column(name = "modi_id", length = 10)
	public String getModiId() {
		return this.modiId;
	}

	public void setModiId(String modiId) {
		this.modiId = modiId;
	}

	@Column(name = "update_times", nullable = true)
	public Integer getUpdateTimes() {
		return this.updateTimes;
	}

	public void setUpdateTimes(Integer updateTimes) {
		this.updateTimes = updateTimes;
	}

	@Column(name = "comp_cd")
	public Integer getCompCd() {
		return this.compCd;
	}

	public void setCompCd(Integer compCd) {
		this.compCd = compCd;
	}

	@Column(name = "change_rate")
	public Integer getChangeRate() {
		return this.changeRate;
	}

	public void setChangeRate(Integer changeRate) {
		this.changeRate = changeRate;
	}

	@Column(name = "phone", length = 20)
	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Column(name = "mobile", length = 50)
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

	@Column(name = "fax", length = 20)
	public String getFax() {
		return this.fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	@Column(name = "connector", length = 20)
	public String getConnector() {
		return this.connector;
	}

	public void setConnector(String connector) {
		this.connector = connector;
	}

	@Column(name = "last_cashier", length = 10)
	public String getLastCashier() {
		return this.lastCashier;
	}

	public void setLastCashier(String lastCashier) {
		this.lastCashier = lastCashier;
	}
	
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@Column(name = "cashier_time", length = 23)
	public Date getCashierTime() {
		return this.cashierTime;
	}

	public void setCashierTime(Date cashierTime) {
		this.cashierTime = cashierTime;
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

	@Column(name = "bankaddr", length = 50)
	public String getBankaddr() {
		return bankaddr;
	}

	public void setBankaddr(String bankaddr) {
		this.bankaddr = bankaddr;
	}

	@Column(name = "bankno", length = 50)
	public String getBankno() {
		return bankno;
	}

	public void setBankno(String bankno) {
		this.bankno = bankno;
	}
    
	@Transient
	public String getUnitNamec() {
		return unitNamec;
	}

	public void setUnitNamec(String unitNamec) {
		this.unitNamec = unitNamec;
	}
	
	@Transient
	public Double getnBorrow() {
		return nBorrow;
	}

	public void setnBorrow(Double nBorrow) {
		this.nBorrow = nBorrow;
	}
	@Transient
	public Double getnLent() {
		return nLent;
	}

	public void setnLent(Double nLent) {
		this.nLent = nLent;
	}
	@Transient
	public Double getnRemain() {
		return nRemain;
	}

	public void setnRemain(Double nRemain) {
		this.nRemain = nRemain;
	}
	@Transient
	public Double getnLimit() {
		return nLimit;
	}

	public void setnLimit(Double nLimit) {
		this.nLimit = nLimit;
	}
	@Transient
	public String getNamec() {
		return namec;
	}

	public void setNamec(String namec) {
		this.namec = namec;
	}

	
}