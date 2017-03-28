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
 * DepotMoth entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "depot_moth")
public class DepotMoth implements java.io.Serializable {

	// Fields

	private Integer id;
	private InfoDept infoDept;
	private InfoGoods infoGoods;
	private ParamConfig paramConfig;
	private String BMonth;
	private Double num;
	private Integer positionId;
	private Integer infoSupId;
	private Boolean del;

	// Constructors

	/** default constructor */
	public DepotMoth() {
	}

	/** full constructor */
	public DepotMoth(InfoDept infoDept, InfoGoods infoGoods,
			ParamConfig paramConfig, String BMonth, Double num,
			Integer positionId, Integer infoSupId,Boolean del) {
		this.infoDept = infoDept;
		this.infoGoods = infoGoods;
		this.paramConfig = paramConfig;
		this.BMonth = BMonth;
		this.num = num;
		this.positionId = positionId;
		this.infoSupId=infoSupId;
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
	@JoinColumn(name = "dep_id")
	public InfoDept getInfoDept() {
		return this.infoDept;
	}

	public void setInfoDept(InfoDept infoDept) {
		this.infoDept = infoDept;
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
	@JoinColumn(name = "unit_id")
	public ParamConfig getParamConfig() {
		return this.paramConfig;
	}

	public void setParamConfig(ParamConfig paramConfig) {
		this.paramConfig = paramConfig;
	}

	@Column(name = "b_month", length = 8)
	public String getBMonth() {
		return this.BMonth;
	}

	public void setBMonth(String BMonth) {
		this.BMonth = BMonth;
	}

	@Column(name = "num", precision = 18, scale = 4)
	public Double getNum() {
		return this.num;
	}

	public void setNum(Double num) {
		this.num = num;
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