package com.zoomoor.admin.entity;
// default package

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.OrderBy;

/**
 * SysAuth entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "sys_auth" )
public class SysAuth implements java.io.Serializable {
	
	private static final long serialVersionUID = 3133802574582410813L;
	
	private Integer authId;
	private SysAuth sysAuth;
	private Integer authType;
	private String authName;
	private String authCode;
	private String authUrl;
	private String authDesc;
	private Integer priority;
	private Boolean del;
	private Set<SysRole> sysRoles = new HashSet<SysRole>(0);
	private Set<SysAuth> sysAuths = new HashSet<SysAuth>(0);

	// Constructors

	/** default constructor */
	public SysAuth() {
	}

	/** minimal constructor */
	public SysAuth(Integer authType, String authName, String authCode,
			Integer priority) {
		this.authType = authType;
		this.authName = authName;
		this.authCode = authCode;
		this.priority = priority;
	}

	/** full constructor */
	public SysAuth(SysAuth sysAuth, Integer authType, String authName,
			String authCode, String authUrl, String authDesc, Integer priority,Boolean del,
			Set<SysRole> sysRoles, Set<SysAuth> sysAuths) {
		this.sysAuth = sysAuth;
		this.authType = authType;
		this.authName = authName;
		this.authCode = authCode;
		this.authUrl = authUrl;
		this.authDesc = authDesc;
		this.priority = priority;
		this.sysRoles = sysRoles;
		this.sysAuths = sysAuths;
		this.del=del;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "auth_id", unique = true, nullable = false)
	public Integer getAuthId() {
		return this.authId;
	}

	public void setAuthId(Integer authId) {
		this.authId = authId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "parent_auth_id")
	public SysAuth getSysAuth() {
		return this.sysAuth;
	}

	public void setSysAuth(SysAuth sysAuth) {
		this.sysAuth = sysAuth;
	}

	@Column(name = "auth_type", nullable = false)
	public Integer getAuthType() {
		return this.authType;
	}

	public void setAuthType(Integer authType) {
		this.authType = authType;
	}

	@Column(name = "auth_name", nullable = false, length = 100)
	public String getAuthName() {
		return this.authName;
	}

	public void setAuthName(String authName) {
		this.authName = authName;
	}

	@Column(name = "auth_code", nullable = false, length = 100)
	public String getAuthCode() {
		return this.authCode;
	}

	public void setAuthCode(String authCode) {
		this.authCode = authCode;
	}

	@Column(name = "auth_url")
	public String getAuthUrl() {
		return this.authUrl;
	}

	public void setAuthUrl(String authUrl) {
		this.authUrl = authUrl;
	}

	@Column(name = "auth_desc", length = 500)
	public String getAuthDesc() {
		return this.authDesc;
	}

	public void setAuthDesc(String authDesc) {
		this.authDesc = authDesc;
	}

	@Column(name = "priority", nullable = false)
	public Integer getPriority() {
		return this.priority;
	}

	public void setPriority(Integer priority) {
		this.priority = priority;
	}
	@Column(name = "del")
	public Boolean getDel() {
		return this.del;
	}

	public void setDel(Boolean del) {
		this.del = del;
	}
	@ManyToMany(fetch = FetchType.LAZY)
	@Cascade(value = {org.hibernate.annotations.CascadeType.SAVE_UPDATE})
	@JoinTable(name = "sys_role_auth", joinColumns = { @JoinColumn(name = "auth_id", nullable = false, updatable = false) }, inverseJoinColumns = { @JoinColumn(name = "role_id", nullable = false, updatable = false) })
	public Set<SysRole> getSysRoles() {
		return this.sysRoles;
	}

	public void setSysRoles(Set<SysRole> sysRoles) {
		this.sysRoles = sysRoles;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "sysAuth")
	@OrderBy(clause = "priority asc")
	public Set<SysAuth> getSysAuths() {
		return this.sysAuths;
	}

	public void setSysAuths(Set<SysAuth> sysAuths) {
		this.sysAuths = sysAuths;
	}

}