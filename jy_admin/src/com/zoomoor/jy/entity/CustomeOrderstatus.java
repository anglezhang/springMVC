package com.zoomoor.jy.entity;

import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * CustomeOrderstatus entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "custome_orderstatus" )
public class CustomeOrderstatus implements java.io.Serializable {

	// Fields

	private Integer statusId;
	private CustomerEntrust customerEntrust;
	private Integer statusKey;
	private Date opertime;
	private Integer operator;
	private Integer tagId;
	private Boolean del;

	// Constructors

	/** default constructor */
	public CustomeOrderstatus() {
	}

	/** full constructor */
	public CustomeOrderstatus(CustomerEntrust customerEntrust,
			Integer statusKey, Timestamp opertime, Integer operator,
			Integer tagId, Boolean del) {
		this.customerEntrust = customerEntrust;
		this.statusKey = statusKey;
		this.opertime = opertime;
		this.operator = operator;
		this.tagId = tagId;
		this.del = del;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "status_id", unique = true, nullable = false)
	public Integer getStatusId() {
		return this.statusId;
	}

	public void setStatusId(Integer statusId) {
		this.statusId = statusId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "entrust_id")
	public CustomerEntrust getCustomerEntrust() {
		return this.customerEntrust;
	}

	public void setCustomerEntrust(CustomerEntrust customerEntrust) {
		this.customerEntrust = customerEntrust;
	}

	@Column(name = "status_key")
	public Integer getStatusKey() {
		return this.statusKey;
	}

	public void setStatusKey(Integer statusKey) {
		this.statusKey = statusKey;
	}

	@Column(name = "opertime", length = 19)
	public Date getOpertime() {
		return this.opertime;
	}

	public void setOpertime(Date opertime) {
		this.opertime = opertime;
	}

	@Column(name = "operator")
	public Integer getOperator() {
		return this.operator;
	}

	public void setOperator(Integer operator) {
		this.operator = operator;
	}

	@Column(name = "tag_id")
	public Integer getTagId() {
		return this.tagId;
	}

	public void setTagId(Integer tagId) {
		this.tagId = tagId;
	}

	@Column(name = "del")
	public Boolean getDel() {
		return this.del;
	}

	public void setDel(Boolean del) {
		this.del = del;
	}

}