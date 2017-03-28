package com.zoomoor.admin.entity;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.zoomoor.jy.entity.InfoEmp;

/**
 * SysUser entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "sys_user" )
public class SysUser implements java.io.Serializable {

	// Fields

	private Integer userId;
	private InfoEmp infoEmp;
	private String username;
	private String password;
	private String email;
	private Date registerTime;
	private String registerIp;
	private Date lastLoginTime;
	private String lastLoginIp;
	private Integer loginCount;
	private Integer isDisabled;
	private Date errorTime;
	private Integer errorCount;
	private String errorIp;
	private Boolean del;
	private Set<SysRole> sysRoles = new HashSet<SysRole>(0);
	private Set<SysLog> sysLogs = new HashSet<SysLog>(0);

	// Constructors

	/** default constructor */
	public SysUser() {
	}

	/** minimal constructor */
	public SysUser(String username, String password, Date registerTime,
			String registerIp, Date lastLoginTime, String lastLoginIp,
			Integer loginCount, Integer isDisabled, Integer errorCount,Boolean del) {
		this.username = username;
		this.password = password;
		this.registerTime = registerTime;
		this.registerIp = registerIp;
		this.lastLoginTime = lastLoginTime;
		this.lastLoginIp = lastLoginIp;
		this.loginCount = loginCount;
		this.isDisabled = isDisabled;
		this.errorCount = errorCount;
		this.del=del;
	}

	/** full constructor */
	public SysUser(InfoEmp infoEmp, String username, String password,
			String email, Date registerTime, String registerIp,
			Date lastLoginTime, String lastLoginIp, Integer loginCount,
			Integer isDisabled, Date errorTime, Integer errorCount,
			String errorIp, Set<SysRole> sysRoles, Set<SysLog> sysLogs) {
		this.infoEmp = infoEmp;
		this.username = username;
		this.password = password;
		this.email = email;
		this.registerTime = registerTime;
		this.registerIp = registerIp;
		this.lastLoginTime = lastLoginTime;
		this.lastLoginIp = lastLoginIp;
		this.loginCount = loginCount;
		this.isDisabled = isDisabled;
		this.errorTime = errorTime;
		this.errorCount = errorCount;
		this.errorIp = errorIp;
		this.sysRoles = sysRoles;
		this.sysLogs = sysLogs;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "user_id", unique = true, nullable = false)
	public Integer getUserId() {
		return this.userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "emp_id")
	public InfoEmp getInfoEmp() {
		return this.infoEmp;
	}

	public void setInfoEmp(InfoEmp infoEmp) {
		this.infoEmp = infoEmp;
	}

	@Column(name = "username", nullable = false, length = 100)
	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Column(name = "password", nullable = false, length = 32)
	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name = "email", length = 100)
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "register_time", nullable = false, length = 19)
	public Date getRegisterTime() {
		return this.registerTime;
	}

	public void setRegisterTime(Date registerTime) {
		this.registerTime = registerTime;
	}

	@Column(name = "register_ip", nullable = false, length = 50)
	public String getRegisterIp() {
		return this.registerIp;
	}

	public void setRegisterIp(String registerIp) {
		this.registerIp = registerIp;
	}

	@Column(name = "last_login_time", nullable = false, length = 19)
	public Date getLastLoginTime() {
		return this.lastLoginTime;
	}

	public void setLastLoginTime(Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	@Column(name = "last_login_ip", nullable = false, length = 50)
	public String getLastLoginIp() {
		return this.lastLoginIp;
	}

	public void setLastLoginIp(String lastLoginIp) {
		this.lastLoginIp = lastLoginIp;
	}

	@Column(name = "login_count", nullable = false)
	public Integer getLoginCount() {
		return this.loginCount;
	}

	public void setLoginCount(Integer loginCount) {
		this.loginCount = loginCount;
	}

	@Column(name = "is_disabled", nullable = false)
	public Integer getIsDisabled() {
		return this.isDisabled;
	}

	public void setIsDisabled(Integer isDisabled) {
		this.isDisabled = isDisabled;
	}

	@Column(name = "error_time", length = 19)
	public Date getErrorTime() {
		return this.errorTime;
	}

	public void setErrorTime(Date errorTime) {
		this.errorTime = errorTime;
	}

	@Column(name = "error_count", nullable = false)
	public Integer getErrorCount() {
		return this.errorCount;
	}

	public void setErrorCount(Integer errorCount) {
		this.errorCount = errorCount;
	}

	@Column(name = "error_ip", length = 50)
	public String getErrorIp() {
		return this.errorIp;
	}

	public void setErrorIp(String errorIp) {
		this.errorIp = errorIp;
	}
	@Column(name = "del")
	public Boolean getDel() {
		return this.del;
	}

	public void setDel(Boolean del) {
		this.del = del;
	}
	@ManyToMany( fetch = FetchType.LAZY)
	@JoinTable(name = "sys_user_role" , joinColumns = { @JoinColumn(name = "user_id", nullable = false, updatable = false) }, inverseJoinColumns = { @JoinColumn(name = "role_id", nullable = false, updatable = false) })
	public Set<SysRole> getSysRoles() {
		return this.sysRoles;
	}

	public void setSysRoles(Set<SysRole> sysRoles) {
		this.sysRoles = sysRoles;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "sysUser")
	public Set<SysLog> getSysLogs() {
		return this.sysLogs;
	}

	public void setSysLogs(Set<SysLog> sysLogs) {
		this.sysLogs = sysLogs;
	}

}