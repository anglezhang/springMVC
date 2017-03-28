package com.zoomoor.admin.entity;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * SysEmailRecord entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "sys_email_record")
public class SysEmailRecord implements java.io.Serializable {
	
	private static final long serialVersionUID = 949633978065447304L;
	
	private Integer emailId;
	private Integer emailType;
	private String content;
	private String toEmailAdd;
	private Integer failedCount;
	private Date sendTime;

	// Constructors

	/** default constructor */
	public SysEmailRecord() {
	}

	/** full constructor */
	public SysEmailRecord(Integer emailType, String content, String toEmailAdd,
			Integer failedCount, Date sendTime) {
		this.emailType = emailType;
		this.content = content;
		this.toEmailAdd = toEmailAdd;
		this.failedCount = failedCount;
		this.sendTime = sendTime;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "email_id", unique = true, nullable = false)
	public Integer getEmailId() {
		return this.emailId;
	}

	public void setEmailId(Integer emailId) {
		this.emailId = emailId;
	}

	@Column(name = "email_type")
	public Integer getEmailType() {
		return this.emailType;
	}

	public void setEmailType(Integer emailType) {
		this.emailType = emailType;
	}

	@Column(name = "content", length = 500)
	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Column(name = "to_email_add", length = 50)
	public String getToEmailAdd() {
		return this.toEmailAdd;
	}

	public void setToEmailAdd(String toEmailAdd) {
		this.toEmailAdd = toEmailAdd;
	}

	@Column(name = "failed_count")
	public Integer getFailedCount() {
		return this.failedCount;
	}

	public void setFailedCount(Integer failedCount) {
		this.failedCount = failedCount;
	}

	@Column(name = "send_time", length = 19)
	public Date getSendTime() {
		return this.sendTime;
	}

	public void setSendTime(Date sendTime) {
		this.sendTime = sendTime;
	}

}