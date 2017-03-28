package com.cyw.mammoth.bean;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * GstBill entity. @author MyEclipse Persistence Tools
 */
@Entity
@org.hibernate.annotations.Entity(dynamicInsert=true)
@Table(name = "gst_bill", schema = "dbo", catalog = "mammoth")
public class GstBill implements java.io.Serializable {

	// Fields

	private Integer billId;
	private Double borrow;
	private Double lent;
	private String payFlag;
	private Date payDate;
	private Boolean okFlag;
	private String vilhotelId;
	private String chainId;
	private Double limit;
	private Double authBalance;
	// Constructors

	/** default constructor */
	public GstBill() {
	}

	/** minimal constructor */
	public GstBill(Integer billId, Double borrow, Double lent, Boolean okFlag) {
		this.billId = billId;
		this.borrow = borrow;
		this.lent = lent;
		this.okFlag = okFlag;
	}

	/** full constructor */
	public GstBill(Integer billId, Double borrow, Double lent, String payFlag,
			Date payDate, Boolean okFlag, String vilhotelId, String chainId,Double limit) {
		this.billId = billId;
		this.borrow = borrow;
		this.lent = lent;
		this.payFlag = payFlag;
		this.payDate = payDate;
		this.okFlag = okFlag;
		this.vilhotelId = vilhotelId;
		this.chainId = chainId;
		this.limit = limit;
	}

	// Property accessors
	@Id
	@Column(name = "bill_id", unique = true, nullable = false)
	public Integer getBillId() {
		return this.billId;
	}

	public void setBillId(Integer billId) {
		this.billId = billId;
	}

	@Column(name = "borrow", precision = 15, scale = 0)
	public Double getBorrow() {
		return this.borrow;
	}

	public void setBorrow(Double borrow) {
		this.borrow = borrow;
	}

	@Column(name = "lent", precision = 15, scale = 0)
	public Double getLent() {
		return this.lent;
	}

	public void setLent(Double lent) {
		this.lent = lent;
	}

	@Column(name = "pay_flag", length = 1)
	public String getPayFlag() {
		return this.payFlag;
	}

	public void setPayFlag(String payFlag) {
		this.payFlag = payFlag;
	}

	@Column(name = "pay_date", length = 16)
	public Date getPayDate() {
		return this.payDate;
	}

	public void setPayDate(Date payDate) {
		this.payDate = payDate;
	}

	@Column(name = "ok_flag")
	public Boolean getOkFlag() {
		return this.okFlag;
	}

	public void setOkFlag(Boolean okFlag) {
		this.okFlag = okFlag;
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
	
	@Column(name = "limit")
	public Double getLimit() {
		return limit;
	}

	public void setLimit(Double limit) {
		this.limit = limit;
	}
	
	@Column(name = "auth_balance")
	public Double getAuthBalance() {
		return authBalance;
	}

	public void setAuthBalance(Double authBalance) {
		this.authBalance = authBalance;
	}
	
}