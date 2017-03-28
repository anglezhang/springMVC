package com.zoomoor.jy.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Transient;

import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * OrderList entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "order_list" )
public class OrderList implements java.io.Serializable {

	// Fields

	private Integer id;
	private OrderPurchase orderPurchase;
	private InfoGoods infoGoods;
	private Double orderSmallNum;
	private Integer unit;
	private Double orderNum;
	private Integer orderUnit;
	private Double orderPrice;
	private Double taxRate;
	private String memo;
	private Boolean del;
	//采购含税单价
	private Double taxtRatePrice;
	//采购不含税金额
	private Double nTaxRateCount;
	// 税额
	private Double taxRateCount;
	//采购含税金额
	private Double ytaxRateCount;
	
	@Transient
	public Double getTaxtRatePrice() {
		return taxtRatePrice;
	}

	public void setTaxtRatePrice(Double taxtRatePrice) {
		this.taxtRatePrice = taxtRatePrice;
	}
	@Transient
	public Double getnTaxRateCount() {
		return nTaxRateCount;
	}

	public void setnTaxRateCount(Double nTaxRateCount) {
		this.nTaxRateCount = nTaxRateCount;
	}
	@Transient
	public Double getTaxRateCount() {
		return taxRateCount;
	}

	public void setTaxRateCount(Double taxRateCount) {
		this.taxRateCount = taxRateCount;
	}
	@Transient
	public Double getYtaxRateCount() {
		return ytaxRateCount;
	}

	public void setYtaxRateCount(Double ytaxRateCount) {
		this.ytaxRateCount = ytaxRateCount;
	}

	//配件名称
	private String unitName;
	// Constructors
	@Transient
	public String getUnitName() {
		return unitName;
	}

	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}

	/** default constructor */
	public OrderList() {
	}

	/** full constructor */
	public OrderList(OrderPurchase orderPurchase, InfoGoods infoGoods,
			Double orderSmallNum, Integer unit, Double orderNum,
			Integer orderUnit, Double orderPrice, Double taxRate, String memo,
			Boolean del) {
		this.orderPurchase = orderPurchase;
		this.infoGoods = infoGoods;
		this.orderSmallNum = orderSmallNum;
		this.unit = unit;
		this.orderNum = orderNum;
		this.orderUnit = orderUnit;
		this.orderPrice = orderPrice;
		this.taxRate = taxRate;
		this.memo = memo;
		this.del = del;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "order_id")
	public OrderPurchase getOrderPurchase() {
		return this.orderPurchase;
	}

	public void setOrderPurchase(OrderPurchase orderPurchase) {
		this.orderPurchase = orderPurchase;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "goods_id")
	public InfoGoods getInfoGoods() {
		return this.infoGoods;
	}

	public void setInfoGoods(InfoGoods infoGoods) {
		this.infoGoods = infoGoods;
	}

	@Column(name = "order_small_num", precision = 18, scale = 4)
	public Double getOrderSmallNum() {
		return this.orderSmallNum;
	}

	public void setOrderSmallNum(Double orderSmallNum) {
		this.orderSmallNum = orderSmallNum;
	}

	@Column(name = "unit")
	public Integer getUnit() {
		return this.unit;
	}

	public void setUnit(Integer unit) {
		this.unit = unit;
	}

	@Column(name = "order_num", precision = 18, scale = 4)
	public Double getOrderNum() {
		return this.orderNum;
	}

	public void setOrderNum(Double orderNum) {
		this.orderNum = orderNum;
	}

	@Column(name = "order_unit")
	public Integer getOrderUnit() {
		return this.orderUnit;
	}

	public void setOrderUnit(Integer orderUnit) {
		this.orderUnit = orderUnit;
	}

	@Column(name = "order_price", precision = 18, scale = 4)
	public Double getOrderPrice() {
		return this.orderPrice;
	}

	public void setOrderPrice(Double orderPrice) {
		this.orderPrice = orderPrice;
	}

	@Column(name = "tax_rate", precision = 9)
	public Double getTaxRate() {
		return this.taxRate;
	}

	public void setTaxRate(Double taxRate) {
		this.taxRate = taxRate;
	}

	@Column(name = "memo", length = 500)
	public String getMemo() {
		return this.memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	@Column(name = "del")
	public Boolean getDel() {
		return this.del;
	}

	public void setDel(Boolean del) {
		this.del = del;
	}

}