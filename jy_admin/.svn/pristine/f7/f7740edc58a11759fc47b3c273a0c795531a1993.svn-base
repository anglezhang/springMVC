package com.zoomoor.jy.entity;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * OrderAudit entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "order_audit" )
public class OrderAudit implements java.io.Serializable {

	// Fields

	private Integer auditId;
	private OrderPurchase orderPurchase;
	private Date auditDate;
	private Integer auditUserId;
	private Integer status;
	private String memo;
	private Integer auditType;

	// Constructors

	/** default constructor */
	public OrderAudit() {
	}

	/** minimal constructor */
	public OrderAudit(Integer auditId) {
		this.auditId = auditId;
	}

	/** full constructor */
	public OrderAudit(Integer auditId, OrderPurchase orderPurchase,
			Date auditDate, Integer auditUserId, Integer status,
			String memo,Integer auditType) {
		this.auditId = auditId;
		this.orderPurchase = orderPurchase;
		this.auditDate = auditDate;
		this.auditUserId = auditUserId;
		this.status = status;
		this.memo = memo;
		this.auditType=auditType;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "audit_id", unique = true, nullable = false)
	public Integer getAuditId() {
		return this.auditId;
	}

	public void setAuditId(Integer auditId) {
		this.auditId = auditId;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "order_id")
	public OrderPurchase getOrderPurchase() {
		return this.orderPurchase;
	}

	public void setOrderPurchase(OrderPurchase orderPurchase) {
		this.orderPurchase = orderPurchase;
	}

	@Column(name = "audit_date", length = 19)
	public Date getAuditDate() {
		return this.auditDate;
	}

	public void setAuditDate(Date auditDate) {
		this.auditDate = auditDate;
	}

	@Column(name = "audit_user_id")
	public Integer getAuditUserId() {
		return this.auditUserId;
	}

	public void setAuditUserId(Integer auditUserId) {
		this.auditUserId = auditUserId;
	}

	@Column(name = "status")
	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	@Column(name = "memo", length = 200)
	public String getMemo() {
		return this.memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}
	@Column(name = "audit_type")
	public Integer getAuditType() {
		return this.auditType;
	}

	public void setAuditType(Integer auditType) {
		this.auditType = auditType;
	}
}