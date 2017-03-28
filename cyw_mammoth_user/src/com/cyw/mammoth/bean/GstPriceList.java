package com.cyw.mammoth.bean;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * GstPriceList entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "gst_price_list", schema = "dbo", catalog = "mammoth")
public class GstPriceList implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer checkId;
	private Date hotelDate;
	private Double price;
	private String memo;
	private String operId;
	private Date operTime;
	private String modiOper;
	private Date modiTime;
	private String vilhotelId;
	private String chainId;
	private String week;

	// Constructors

	/** default constructor */
	public GstPriceList() {
	}

	/** minimal constructor */
	public GstPriceList(Integer id, Integer checkId, Date hotelDate,
			Double price, String memo, String operId, Date operTime) {
		this.id = id;
		this.checkId = checkId;
		this.hotelDate = hotelDate;
		this.price = price;
		this.memo = memo;
		this.operId = operId;
		this.operTime = operTime;
	}

	/** full constructor */
	public GstPriceList(Integer id, Integer checkId, Date hotelDate,
			Double price, String memo, String operId, Date operTime,
			String modiOper, Date modiTime, String vilhotelId,
			String chainId) {
		this.id = id;
		this.checkId = checkId;
		this.hotelDate = hotelDate;
		this.price = price;
		this.memo = memo;
		this.operId = operId;
		this.operTime = operTime;
		this.modiOper = modiOper;
		this.modiTime = modiTime;
		this.vilhotelId = vilhotelId;
		this.chainId = chainId;
	}

	// Property accessors
	@Id
	@Column(name = "ID", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "check_id", nullable = false)
	public Integer getCheckId() {
		return this.checkId;
	}

	public void setCheckId(Integer checkId) {
		this.checkId = checkId;
	}

	@Column(name = "hotel_date", nullable = false, length = 16)
	public Date getHotelDate() {
		return this.hotelDate;
	}

	public void setHotelDate(Date hotelDate) {
		this.hotelDate = hotelDate;
	}

	@Column(name = "price", nullable = false, precision = 15, scale = 0)
	public Double getPrice() {
		return this.price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	@Column(name = "memo", nullable = false, length = 50)
	public String getMemo() {
		return this.memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	@Column(name = "oper_id", nullable = false, length = 10)
	public String getOperId() {
		return this.operId;
	}

	public void setOperId(String operId) {
		this.operId = operId;
	}

	@Column(name = "oper_time", nullable = false, length = 16)
	public Date getOperTime() {
		return this.operTime;
	}

	public void setOperTime(Date operTime) {
		this.operTime = operTime;
	}

	@Column(name = "modi_oper", length = 10)
	public String getModiOper() {
		return this.modiOper;
	}

	public void setModiOper(String modiOper) {
		this.modiOper = modiOper;
	}

	@Column(name = "modi_time", length = 16)
	public Date getModiTime() {
		return this.modiTime;
	}

	public void setModiTime(Date modiTime) {
		this.modiTime = modiTime;
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
	
	@Transient
	public String getWeek() {
		return week;
	}

	public void setWeek(String week) {
		this.week = week;
	}
	
	
}