package com.zoomoor.admin.entity;
// default package

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * SysAuthentication entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "sys_authentication" )
public class SysAuthentication implements java.io.Serializable {
	
	private static final long serialVersionUID = -4918977422534769873L;

	public void init() {
		Date now = new Date(System.currentTimeMillis());
		setLoginTime(now);
		setUpdateTime(now);
	}
	
	// Fields

	private String authenticationId;
	private Integer userid;
	private String username;
	private String email;
	private Date loginTime;
	private String loginIp;
	private Date updateTime;

	// Constructors

	/** default constructor */
	public SysAuthentication() {
	}

	/** minimal constructor */
	public SysAuthentication(String authenticationId, Integer userid,
			String username, Date loginTime, String loginIp,
			Date updateTime) {
		this.authenticationId = authenticationId;
		this.userid = userid;
		this.username = username;
		this.loginTime = loginTime;
		this.loginIp = loginIp;
		this.updateTime = updateTime;
	}

	/** full constructor */
	public SysAuthentication(String authenticationId, Integer userid,
			String username, String email, Date loginTime, String loginIp,
			Date updateTime) {
		this.authenticationId = authenticationId;
		this.userid = userid;
		this.username = username;
		this.email = email;
		this.loginTime = loginTime;
		this.loginIp = loginIp;
		this.updateTime = updateTime;
	}

	// Property accessors
	@Id
	@Column(name = "authentication_id", unique = true, nullable = false, length = 32)
	public String getAuthenticationId() {
		return this.authenticationId;
	}

	public void setAuthenticationId(String authenticationId) {
		this.authenticationId = authenticationId;
	}

	@Column(name = "userid", nullable = false)
	public Integer getUserid() {
		return this.userid;
	}

	public void setUserid(Integer userid) {
		this.userid = userid;
	}

	@Column(name = "username", nullable = false, length = 100)
	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Column(name = "email", length = 100)
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "login_time", nullable = false, length = 19)
	public Date getLoginTime() {
		return this.loginTime;
	}

	public void setLoginTime(Date loginTime) {
		this.loginTime = loginTime;
	}

	@Column(name = "login_ip", nullable = false, length = 50)
	public String getLoginIp() {
		return this.loginIp;
	}

	public void setLoginIp(String loginIp) {
		this.loginIp = loginIp;
	}

	@Column(name = "update_time", nullable = false, length = 19)
	public Date getUpdateTime() {
		return this.updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

}