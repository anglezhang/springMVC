package com.zoomoor.jy.entity;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.zoomoor.admin.entity.SysRole;

/**
 * MoneyUserMapping entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "money_user_mapping" )
public class MoneyUserMapping implements java.io.Serializable {

	// Fields

	private Integer mappingId;
	private SysRole sysRole;
	private MoneyAuth moneyAuth;

	// Constructors

	/** default constructor */
	public MoneyUserMapping() {
	}

	/** full constructor */
	public MoneyUserMapping(SysRole sysRole, MoneyAuth moneyAuth) {
		this.sysRole = sysRole;
		this.moneyAuth = moneyAuth;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "mapping_id", unique = true, nullable = false)
	public Integer getMappingId() {
		return this.mappingId;
	}

	public void setMappingId(Integer mappingId) {
		this.mappingId = mappingId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "role_id")
	public SysRole getSysRole() {
		return this.sysRole;
	}

	public void setSysRole(SysRole sysRole) {
		this.sysRole = sysRole;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id")
	public MoneyAuth getMoneyAuth() {
		return this.moneyAuth;
	}

	public void setMoneyAuth(MoneyAuth moneyAuth) {
		this.moneyAuth = moneyAuth;
	}

}