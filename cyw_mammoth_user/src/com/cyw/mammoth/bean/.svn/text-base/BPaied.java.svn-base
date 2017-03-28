package com.cyw.mammoth.bean;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * Bills entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "b_paied", schema = "dbo", catalog = "mammoth")
public class BPaied implements java.io.Serializable {

	// Fields

	private Integer checkId;
	private String cons;
	private Date beginDate;
	private Date endDate;
	private String operId;
	private Date operTime;
	private Integer updateTimes;

	// Constructors

	/** default constructor */
	public BPaied() {
	}

	
	public BPaied(Integer checkId, String cons, Date beginDate, Date endDate,
			String operId, Date operTime, Integer updateTimes) {
		super();
		this.checkId = checkId;
		this.cons = cons;
		this.beginDate = beginDate;
		this.endDate = endDate;
		this.operId = operId;
		this.operTime = operTime;
		this.updateTimes = updateTimes;
	}

	@Id
	@Column(name="check_id",unique = true, nullable = false)
	public Integer getCheckId() {
		return checkId;
	}


	public void setCheckId(Integer checkId) {
		this.checkId = checkId;
	}

	@Column(name="cons", nullable = false)
	public String getCons() {
		return cons;
	}


	public void setCons(String cons) {
		this.cons = cons;
	}
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@Column(name="begin_date", nullable = false)
	public Date getBeginDate() {
		return beginDate;
	}


	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@Column(name="end_date", nullable = false)
	public Date getEndDate() {
		return endDate;
	}


	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	@Column(name="oper_id")
	public String getOperId() {
		return operId;
	}


	public void setOperId(String operId) {
		this.operId = operId;
	}

	@Column(name="oper_time")
	public Date getOperTime() {
		return operTime;
	}


	public void setOperTime(Date operTime) {
		this.operTime = operTime;
	}

	@Column(name="update_times")
	public Integer getUpdateTimes() {
		return updateTimes;
	}


	public void setUpdateTimes(Integer updateTimes) {
		this.updateTimes = updateTimes;
	}
	
	
}