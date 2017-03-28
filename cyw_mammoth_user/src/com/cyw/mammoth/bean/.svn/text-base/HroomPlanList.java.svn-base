package com.cyw.mammoth.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * HroomPlanList entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "hroom_plan_list", schema = "dbo", catalog = "mammoth")
public class HroomPlanList implements java.io.Serializable {

	// Fields

	private Integer id;
	private String rmplanId;
	private String rmtypeId;
	private String holidayId;
	private Double price;
	private Double discount;
	private Integer status;
	private String memo;
	private String vilhotelId;
	private String chainId;

	// Constructors

	/** default constructor */
	public HroomPlanList() {
	}

	/** minimal constructor */
	public HroomPlanList(Integer id, String rmplanId, String rmtypeId,
			String holidayId, Integer status) {
		this.id = id;
		this.rmplanId = rmplanId;
		this.rmtypeId = rmtypeId;
		this.holidayId = holidayId;
		this.status = status;
	}

	/** full constructor */
	public HroomPlanList(Integer id, String rmplanId, String rmtypeId,
			String holidayId, Double price, Double discount, Integer status,
			String memo, String vilhotelId, String chainId) {
		this.id = id;
		this.rmplanId = rmplanId;
		this.rmtypeId = rmtypeId;
		this.holidayId = holidayId;
		this.price = price;
		this.discount = discount;
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

	@Column(name = "rmplan_id", nullable = false, length = 10)
	public String getRmplanId() {
		return this.rmplanId;
	}

	public void setRmplanId(String rmplanId) {
		this.rmplanId = rmplanId;
	}

	@Column(name = "rmtype_id", nullable = false, length = 4)
	public String getRmtypeId() {
		return this.rmtypeId;
	}

	public void setRmtypeId(String rmtypeId) {
		this.rmtypeId = rmtypeId;
	}

	@Column(name = "holiday_id", nullable = false, length = 6)
	public String getHolidayId() {
		return this.holidayId;
	}

	public void setHolidayId(String holidayId) {
		this.holidayId = holidayId;
	}

	@Column(name = "price", precision = 15, scale = 0)
	public Double getPrice() {
		return this.price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	@Column(name = "discount", precision = 15, scale = 0)
	public Double getDiscount() {
		return this.discount;
	}

	public void setDiscount(Double discount) {
		this.discount = discount;
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