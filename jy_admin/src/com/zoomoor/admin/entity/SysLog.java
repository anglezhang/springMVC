package com.zoomoor.admin.entity;
// default package

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
 * SysLog entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "sys_log" )
public class SysLog implements java.io.Serializable {
	
	private static final long serialVersionUID = -6674684437313353849L;
	
	public static final int LOGIN_SUCCESS = 1;
	public static final int LOGIN_FAILURE = 2;
	public static final int OPERATING = 3;
	
	public static final String LOGIN_SUCCESS_TITLE = "login success";
	public static final String LOGIN_FAILURE_TITLE = "login failure";
	
	// Fields

	private Integer logId;
	private SysUser sysUser;
	private Integer category;
	private Date logTime;
	private String ip;
	private String url;
	private String title;
	private String content;

	// Constructors

	/** default constructor */
	public SysLog() {
	}

	/** minimal constructor */
	public SysLog(Integer category, Date logTime) {
		this.category = category;
		this.logTime = logTime;
	}

	/** full constructor */
	public SysLog(SysUser sysUser, Integer category, Date logTime,
			String ip, String url, String title, String content) {
		this.sysUser = sysUser;
		this.category = category;
		this.logTime = logTime;
		this.ip = ip;
		this.url = url;
		this.title = title;
		this.content = content;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "log_id", unique = true, nullable = false)
	public Integer getLogId() {
		return this.logId;
	}

	public void setLogId(Integer logId) {
		this.logId = logId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	public SysUser getSysUser() {
		return this.sysUser;
	}

	public void setSysUser(SysUser sysUser) {
		this.sysUser = sysUser;
	}

	@Column(name = "category", nullable = false)
	public Integer getCategory() {
		return this.category;
	}

	public void setCategory(Integer category) {
		this.category = category;
	}

	@Column(name = "log_time", nullable = false, length = 19)
	public Date getLogTime() {
		return this.logTime;
	}

	public void setLogTime(Date logTime) {
		this.logTime = logTime;
	}

	@Column(name = "ip", length = 50)
	public String getIp() {
		return this.ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	@Column(name = "url")
	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Column(name = "title")
	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Column(name = "content")
	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}