package com.cyw.mammoth.bean;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * GstCreditAuth entity. @author MyEclipse Persistence Tools
 */
@Entity
@org.hibernate.annotations.Entity(dynamicInsert=true)
@Table(name = "gst_credit_auth", schema = "dbo", catalog = "mammoth")
public class GstCreditAuth implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer checkId;
	private String creditId;
	private String creditHolder;
	private String authId;
	private Double balance;
	private Date expired;
	private Integer status;
	private String operId;
	private Date operTime;
	private Date hotelDate;
	private String cancelOper;
	private Date cancelTime;
	private String finishOper;
	private Date finishTime;
	private Double finishBalance;
	private String finishNo;
	private Integer billId;
	private String note;
	private String vilhotelId;
	private String chainId;
	private String cancelNo;
	private Integer authStat;
	// Constructors

	/** default constructor */
	public GstCreditAuth() {
	}

	/** minimal constructor */
	public GstCreditAuth(Integer id, Integer checkId, String creditId,
			String authId, Double balance, Date expired, Integer status,
			String operId, Date operTime, Date hotelDate) {
		this.id = id;
		this.checkId = checkId;
		this.creditId = creditId;
		this.authId = authId;
		this.balance = balance;
		this.expired = expired;
		this.status = status;
		this.operId = operId;
		this.operTime = operTime;
		this.hotelDate = hotelDate;
	}

	/** full constructor */
	public GstCreditAuth(Integer id, Integer checkId, String creditId,
			String creditHolder, String authId, Double balance,
			Date expired, Integer status, String operId,
			Date operTime, Date hotelDate, String cancelOper,
			Date cancelTime, String finishOper, Date finishTime,
			Double finishBalance, String finishNo, Integer billId, String note,
			String vilhotelId, String chainId,String cancelNo,Integer authStat) {
		this.id = id;
		this.checkId = checkId;
		this.creditId = creditId;
		this.creditHolder = creditHolder;
		this.authId = authId;
		this.balance = balance;
		this.expired = expired;
		this.status = status;
		this.operId = operId;
		this.operTime = operTime;
		this.hotelDate = hotelDate;
		this.cancelOper = cancelOper;
		this.cancelTime = cancelTime;
		this.finishOper = finishOper;
		this.finishTime = finishTime;
		this.finishBalance = finishBalance;
		this.finishNo = finishNo;
		this.billId = billId;
		this.note = note;
		this.vilhotelId = vilhotelId;
		this.chainId = chainId;
		this.cancelNo = cancelNo;
		this.authStat = authStat;
	}

	// Property accessors
	@Id
	@GeneratedValue
	@Column(name = "ID", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "check_ID", nullable = false)
	public Integer getCheckId() {
		return this.checkId;
	}

	public void setCheckId(Integer checkId) {
		this.checkId = checkId;
	}

	@Column(name = "credit_id", nullable = false, length = 30)
	public String getCreditId() {
		return this.creditId;
	}

	public void setCreditId(String creditId) {
		this.creditId = creditId;
	}

	@Column(name = "credit_holder", length = 40)
	public String getCreditHolder() {
		return this.creditHolder;
	}

	public void setCreditHolder(String creditHolder) {
		this.creditHolder = creditHolder;
	}

	@Column(name = "auth_id", nullable = false, length = 50)
	public String getAuthId() {
		return this.authId;
	}

	public void setAuthId(String authId) {
		this.authId = authId;
	}

	@Column(name = "balance", nullable = false, precision = 15, scale = 0)
	public Double getBalance() {
		return this.balance;
	}

	public void setBalance(Double balance) {
		this.balance = balance;
	}
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@Column(name = "expired", nullable = false, length = 16)
	public Date getExpired() {
		return this.expired;
	}

	public void setExpired(Date expired) {
		this.expired = expired;
	}

	@Column(name = "status", nullable = false)
	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	@Column(name = "oper_id", nullable = false, length = 10)
	public String getOperId() {
		return this.operId;
	}

	public void setOperId(String operId) {
		this.operId = operId;
	}

	@Column(name = "oper_time", length = 16)
	public Date getOperTime() {
		return this.operTime;
	}

	public void setOperTime(Date operTime) {
		this.operTime = operTime;
	}

	@Column(name = "hotel_date", nullable = false, length = 16)
	public Date getHotelDate() {
		return this.hotelDate;
	}

	public void setHotelDate(Date hotelDate) {
		this.hotelDate = hotelDate;
	}

	@Column(name = "cancel_oper", length = 10)
	public String getCancelOper() {
		return this.cancelOper;
	}

	public void setCancelOper(String cancelOper) {
		this.cancelOper = cancelOper;
	}
	
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@Column(name = "cancel_time", length = 16)
	public Date getCancelTime() {
		return this.cancelTime;
	}

	public void setCancelTime(Date cancelTime) {
		this.cancelTime = cancelTime;
	}

	@Column(name = "finish_oper", length = 10)
	public String getFinishOper() {
		return this.finishOper;
	}

	public void setFinishOper(String finishOper) {
		this.finishOper = finishOper;
	}
	
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@Column(name = "finish_time", length = 16)
	public Date getFinishTime() {
		return this.finishTime;
	}

	public void setFinishTime(Date finishTime) {
		this.finishTime = finishTime;
	}

	@Column(name = "finish_balance", precision = 15, scale = 0)
	public Double getFinishBalance() {
		return this.finishBalance;
	}

	public void setFinishBalance(Double finishBalance) {
		this.finishBalance = finishBalance;
	}

	@Column(name = "finish_no", length = 10)
	public String getFinishNo() {
		return this.finishNo;
	}

	public void setFinishNo(String finishNo) {
		this.finishNo = finishNo;
	}

	@Column(name = "bill_id")
	public Integer getBillId() {
		return this.billId;
	}

	public void setBillId(Integer billId) {
		this.billId = billId;
	}

	@Column(name = "note")
	public String getNote() {
		return this.note;
	}

	public void setNote(String note) {
		this.note = note;
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
	
	@Column(name = "cancel_no")
	public String getCancelNo() {
		return cancelNo;
	}

	public void setCancelNo(String cancelNo) {
		this.cancelNo = cancelNo;
	}
	
	@Column(name = "auth_stat")
	public Integer getAuthStat() {
		return authStat;
	}

	public void setAuthStat(Integer authStat) {
		this.authStat = authStat;
	}
	
	
}