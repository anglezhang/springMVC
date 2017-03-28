package com.cyw.mammoth.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Holidays entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "holidays", schema = "dbo", catalog = "mammoth")
public class Holidays implements java.io.Serializable {

	// Fields

	private Integer id;
	private String holidayDate;
	private String holidayId;
	private Integer status;
	private String memo;
	private String vilhotelId;
	private String chainId;

	// Constructors

	/** default constructor */
	public Holidays() {
	}

	/** minimal constructor */
	public Holidays(Integer id, String holidayDate, String holidayId,
			Integer status) {
		this.id = id;
		this.holidayDate = holidayDate;
		this.holidayId = holidayId;
		this.status = status;
	}

	/** full constructor */
	public Holidays(Integer id, String holidayDate, String holidayId,
			Integer status, String memo, String vilhotelId, String chainId) {
		this.id = id;
		this.holidayDate = holidayDate;
		this.holidayId = holidayId;
		this.status = status;
		this.memo = memo;
		this.vilhotelId = vilhotelId;
		this.chainId = chainId;
	}

	// Property accessors
	@Id
	@GeneratedValue
	@Column(name = "ID", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "holiday_date", nullable = false, unique = true, length = 10)
	public String getHolidayDate() {
		return this.holidayDate;
	}

	public void setHolidayDate(String holidayDate) {
		this.holidayDate = holidayDate;
	}

	@Column(name = "holiday_id", nullable = false, length = 6)
	public String getHolidayId() {
		return this.holidayId;
	}

	public void setHolidayId(String holidayId) {
		this.holidayId = holidayId;
	}

	@Column(name = "status", nullable = false)
	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	@Column(name = "memo")
	public String getMemo() {
		return this.memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
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

}