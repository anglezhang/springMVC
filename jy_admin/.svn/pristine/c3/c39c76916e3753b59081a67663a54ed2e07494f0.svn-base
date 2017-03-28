package com.zoomoor.jy.entity;

import static javax.persistence.GenerationType.IDENTITY;

import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.zoomoor.jy.entity.vo.DepotCheckVo;
import com.zoomoor.jy.entity.vo.DepotVo;

/**
 * DepotCheck entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "depot_check")
public class DepotCheck implements java.io.Serializable {

	// Fields

	private Integer id;
	private InfoGoods infoGoods;
	private Integer depId;
	private String BNo;
	private Date checkDate;
	private Integer comeId;
	private Integer depotPositionId;
	private Double accountNum;
	private Double num;
	private Double inNum;
	private Double outNum;
	private Double factNum;
	private Integer depotDetailId;
	private Boolean del;
	private DepotCheckVo depotCheckVo;
	private DepotVo[] depotVo;
	private String deptName;
	private String positionName;
	@Transient
	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	@Transient
	public String getPositionName() {
		return positionName;
	}

	public void setPositionName(String positionName) {
		this.positionName = positionName;
	}

	@Transient
	public DepotVo[] getDepotVo() {
		return depotVo;
	}

	public void setDepotVo(DepotVo[] depotVo) {
		this.depotVo = depotVo;
	}

	@Transient
	public DepotCheckVo getDepotCheckVo() {
		return depotCheckVo;
	}
	

	public void setDepotCheckVo(DepotCheckVo depotCheckVo) {
		this.depotCheckVo = depotCheckVo;
	}

	/** default constructor */
	public DepotCheck() {
	}

	/** full constructor */
	public DepotCheck(InfoGoods infoGoods, Integer depId, String BNo,
			Date checkDate, Integer comeId, Integer depotPositionId,
			Double accountNum, Double num, Double inNum, Double outNum,
			Double factNum, Integer depotDetailId, Boolean del) {
		this.infoGoods = infoGoods;
		this.depId = depId;
		this.BNo = BNo;
		this.checkDate = checkDate;
		this.comeId = comeId;
		this.depotPositionId = depotPositionId;
		this.accountNum = accountNum;
		this.num = num;
		this.inNum = inNum;
		this.outNum = outNum;
		this.factNum = factNum;
		this.depotDetailId = depotDetailId;
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
	@JoinColumn(name = "goods_id", nullable = false)
	public InfoGoods getInfoGoods() {
		return this.infoGoods;
	}

	public void setInfoGoods(InfoGoods infoGoods) {
		this.infoGoods = infoGoods;
	}

	@Column(name = "dep_id", nullable = false)
	public Integer getDepId() {
		return this.depId;
	}

	public void setDepId(Integer depId) {
		this.depId = depId;
	}

	@Column(name = "b_no", nullable = false, length = 20)
	public String getBNo() {
		return this.BNo;
	}

	public void setBNo(String BNo) {
		this.BNo = BNo;
	}

	@Column(name = "check_date", nullable = false, length = 19)
	public Date getCheckDate() {
		return this.checkDate;
	}

	public void setCheckDate(Date checkDate) {
		this.checkDate = checkDate;
	}

	@Column(name = "come_id", nullable = false)
	public Integer getComeId() {
		return this.comeId;
	}

	public void setComeId(Integer comeId) {
		this.comeId = comeId;
	}

	@Column(name = "depot_position_id", nullable = false)
	public Integer getDepotPositionId() {
		return this.depotPositionId;
	}

	public void setDepotPositionId(Integer depotPositionId) {
		this.depotPositionId = depotPositionId;
	}

	@Column(name = "account_num", nullable = false, precision = 18, scale = 8)
	public Double getAccountNum() {
		return this.accountNum;
	}

	public void setAccountNum(Double accountNum) {
		this.accountNum = accountNum;
	}

	@Column(name = "num", nullable = false, precision = 18, scale = 4)
	public Double getNum() {
		return this.num;
	}

	public void setNum(Double num) {
		this.num = num;
	}

	@Column(name = "in_num", nullable = false, precision = 18, scale = 4)
	public Double getInNum() {
		return this.inNum;
	}

	public void setInNum(Double inNum) {
		this.inNum = inNum;
	}

	@Column(name = "out_num", nullable = false, precision = 18, scale = 4)
	public Double getOutNum() {
		return this.outNum;
	}

	public void setOutNum(Double outNum) {
		this.outNum = outNum;
	}

	@Column(name = "fact_num", nullable = false, precision = 18, scale = 8)
	public Double getFactNum() {
		return this.factNum;
	}

	public void setFactNum(Double factNum) {
		this.factNum = factNum;
	}

	@Column(name = "depot_detail_id", nullable = false)
	public Integer getDepotDetailId() {
		return this.depotDetailId;
	}

	public void setDepotDetailId(Integer depotDetailId) {
		this.depotDetailId = depotDetailId;
	}

	@Column(name = "del", nullable = false)
	public Boolean getDel() {
		return this.del;
	}

	public void setDel(Boolean del) {
		this.del = del;
	}

}