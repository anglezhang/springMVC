package com.zoomoor.jy.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * CustomerEntrustSub entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "customer_entrust_sub" )
public class CustomerEntrustSub implements java.io.Serializable {

	// Fields

	private Integer entrustsubId;
	private Serviceitem serviceitem;
	private CustomerEntrust customerEntrust;
	private Integer servicetype;
	private Integer goodsId;
	private Integer number;
	private Double price;
	private Boolean del;

	// Constructors

	/** default constructor */
	public CustomerEntrustSub() {
	}

	/** full constructor */
	public CustomerEntrustSub(Serviceitem serviceitem,
			CustomerEntrust customerEntrust, Integer servicetype,
			Integer goodsId, Integer number, Double price, Boolean del) {
		this.serviceitem = serviceitem;
		this.customerEntrust = customerEntrust;
		this.servicetype = servicetype;
		this.goodsId = goodsId;
		this.number = number;
		this.price = price;
		this.del = del;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "entrustsub_id", unique = true, nullable = false)
	public Integer getEntrustsubId() {
		return this.entrustsubId;
	}

	public void setEntrustsubId(Integer entrustsubId) {
		this.entrustsubId = entrustsubId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "item_id")
	public Serviceitem getServiceitem() {
		return this.serviceitem;
	}

	public void setServiceitem(Serviceitem serviceitem) {
		this.serviceitem = serviceitem;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "entrust_id")
	public CustomerEntrust getCustomerEntrust() {
		return this.customerEntrust;
	}

	public void setCustomerEntrust(CustomerEntrust customerEntrust) {
		this.customerEntrust = customerEntrust;
	}

	@Column(name = "servicetype")
	public Integer getServicetype() {
		return this.servicetype;
	}

	public void setServicetype(Integer servicetype) {
		this.servicetype = servicetype;
	}

	@Column(name = "goods_id")
	public Integer getGoodsId() {
		return this.goodsId;
	}

	public void setGoodsId(Integer goodsId) {
		this.goodsId = goodsId;
	}

	@Column(name = "number")
	public Integer getNumber() {
		return this.number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	@Column(name = "price", precision = 9)
	public Double getPrice() {
		return this.price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	@Column(name = "del")
	public Boolean getDel() {
		return this.del;
	}

	public void setDel(Boolean del) {
		this.del = del;
	}

}