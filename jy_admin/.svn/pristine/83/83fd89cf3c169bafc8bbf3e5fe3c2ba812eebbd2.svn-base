package com.zoomoor.jy.entity;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * DepotBillList entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "depot_bill_list")
public class DepotBillList implements java.io.Serializable {

	// Fields

	private Integer detailId;
	private InfoGoods infoGoods;
	private DepotBill depotBill;
	private ParamConfig paramConfig;
	private Double num;
	private Integer tagId;
	private String memo;
	private Integer positionId;
	private Integer infoSupId;
	private Boolean del;

	// Constructors

	/** default constructor */
	public DepotBillList() {
	}

	/** full constructor */
	public DepotBillList(InfoGoods infoGoods, DepotBill depotBill,
			ParamConfig paramConfig, Double num, Integer tagId, String memo,
			Integer positionId, Integer infoSupId, Boolean del) {
		this.infoGoods = infoGoods;
		this.depotBill = depotBill;
		this.paramConfig = paramConfig;
		this.num = num;
		this.tagId = tagId;
		this.memo = memo;
		this.positionId = positionId;
		this.infoSupId = infoSupId;
		this.del = del;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "detail_id", unique = true, nullable = false)
	public Integer getDetailId() {
		return this.detailId;
	}

	public void setDetailId(Integer detailId) {
		this.detailId = detailId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "goods_id")
	public InfoGoods getInfoGoods() {
		return this.infoGoods;
	}

	public void setInfoGoods(InfoGoods infoGoods) {
		this.infoGoods = infoGoods;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "bill_id")
	public DepotBill getDepotBill() {
		return this.depotBill;
	}

	public void setDepotBill(DepotBill depotBill) {
		this.depotBill = depotBill;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "unit_id")
	public ParamConfig getParamConfig() {
		return this.paramConfig;
	}

	public void setParamConfig(ParamConfig paramConfig) {
		this.paramConfig = paramConfig;
	}

	@Column(name = "num", precision = 18, scale = 4)
	public Double getNum() {
		return this.num;
	}

	public void setNum(Double num) {
		this.num = num;
	}

	@Column(name = "tag_id")
	public Integer getTagId() {
		return this.tagId;
	}

	public void setTagId(Integer tagId) {
		this.tagId = tagId;
	}

	@Column(name = "memo", length = 500)
	public String getMemo() {
		return this.memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	@Column(name = "position_id")
	public Integer getPositionId() {
		return this.positionId;
	}

	public void setPositionId(Integer positionId) {
		this.positionId = positionId;
	}

	@Column(name = "info_sup_id")
	public Integer getInfoSupId() {
		return this.infoSupId;
	}

	public void setInfoSupId(Integer infoSupId) {
		this.infoSupId = infoSupId;
	}

	@Column(name = "del")
	public Boolean getDel() {
		return this.del;
	}

	public void setDel(Boolean del) {
		this.del = del;
	}

}