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
 * AllocationList entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "allocation_list")
public class AllocationList implements java.io.Serializable {

	// Fields

	private Integer swId;
	private Allocation allocation;
	private Integer applyId;
	private Integer fromId;
	private Integer toId;
	private Integer goodsId;
	private Integer unitId;
	private Double num;
	private String source;
	private Boolean del;
	//申请数量
	private Double applyNum;
	
	@Transient
	public Double getApplyNum() {
		return applyNum;
	}

	public void setApplyNum(Double applyNum) {
		this.applyNum = applyNum;
	}

	// 调出部门
	private String fromDeptName;
	//调入部门
	private String toDeptName;
	//配件
	private InfoGoods infoGoods;
	@Transient
	public String getFromDeptName() {
		return fromDeptName;
	}

	public void setFromDeptName(String fromDeptName) {
		this.fromDeptName = fromDeptName;
	}
	@Transient
	public String getToDeptName() {
		return toDeptName;
	}

	public void setToDeptName(String toDeptName) {
		this.toDeptName = toDeptName;
	}

	@Transient
	public InfoGoods getInfoGoods() {
		return infoGoods;
	}

	public void setInfoGoods(InfoGoods infoGoods) {
		this.infoGoods = infoGoods;
	}

	/** default constructor */
	public AllocationList() {
	}

	/** full constructor */
	public AllocationList(Allocation allocation,Integer applyId, Integer fromId, Integer toId,
			Integer goodsId,Integer unitId, Double num,String source, Boolean del) {
		this.allocation = allocation;
		this.fromId = fromId;
		this.toId = toId;
		this.goodsId = goodsId;
		this.num = num;
		this.unitId=unitId;
		this.source=source;
		this.del = del;
		this.applyId=applyId;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "sw_id", unique = true, nullable = false)
	public Integer getSwId() {
		return this.swId;
	}

	public void setSwId(Integer swId) {
		this.swId = swId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "allocation_id")
	public Allocation getAllocation() {
		return this.allocation;
	}

	public void setAllocation(Allocation allocation) {
		this.allocation = allocation;
	}
	@Column(name = "apply_id", nullable = false)
	public Integer getApplyId() {
		return this.applyId;
	}

	public void setApplyId(Integer applyId) {
		this.applyId = applyId;
	}
	@Column(name = "from_id")
	public Integer getFromId() {
		return this.fromId;
	}

	public void setFromId(Integer fromId) {
		this.fromId = fromId;
	}

	@Column(name = "to_id")
	public Integer getToId() {
		return this.toId;
	}

	public void setToId(Integer toId) {
		this.toId = toId;
	}

	@Column(name = "goods_id")
	public Integer getGoodsId() {
		return this.goodsId;
	}

	public void setGoodsId(Integer goodsId) {
		this.goodsId = goodsId;
	}
	@Column(name = "unit_id")
	public Integer getUnitId() {
		return this.unitId;
	}

	public void setUnitId(Integer unitId) {
		this.unitId = unitId;
	} 
	@Column(name = "num", precision = 18, scale = 4)
	public Double getNum() {
		return this.num;
	}

	public void setNum(Double num) {
		this.num = num;
	}
	@Column(name = "source", length = 1000)
	public String getSource() {
		return this.source;
	}

	public void setSource(String source) {
		this.source = source;
	}
	@Column(name = "del")
	public Boolean getDel() {
		return this.del;
	}

	public void setDel(Boolean del) {
		this.del = del;
	}

}