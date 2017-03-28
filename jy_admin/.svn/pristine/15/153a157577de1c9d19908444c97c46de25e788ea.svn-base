package com.zoomoor.jy.entity;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.zoomoor.jy.entity.vo.AllocationApplyVo;

/**
 * AllocationApply entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "allocation_apply")
public class AllocationApply implements java.io.Serializable {

	// Fields

	private Integer applyId;
	private InfoDept infoDept;
	private InfoGoods infoGoods;
	private ParamConfig paramConfig;
	private Allocation allocation;
	private Double num;
	private Date unitDate;
	private Integer userId;
	private Date takeDate;
	private Integer status;
	private Boolean del;
	private String feedback;
	
	private AllocationApplyVo[] allocationApplyVo;
	@Transient
	public AllocationApplyVo[] getAllocationApplyVo() {
		return allocationApplyVo;
	}

	public void setAllocationApplyVo(AllocationApplyVo[] allocationApplyVo) {
		this.allocationApplyVo = allocationApplyVo;
	}
	//员工姓名
	private String empName;
	@Transient
	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	private InfoGoods[] infoGoodList;
	// Constructors
	@Transient
	public InfoGoods[] getInfoGoodList() {
		return infoGoodList;
	}

	public void setInfoGoodList(InfoGoods[] infoGoodList) {
		this.infoGoodList = infoGoodList;
	}

	/** default constructor */
	public AllocationApply() {
	}

	/** full constructor */
	public AllocationApply(InfoDept infoDept, InfoGoods infoGoods,
			ParamConfig paramConfig, Allocation allocation,Double num, Date unitDate,
			Integer userId, Date takeDate,Integer status, Boolean del,String feedback) {
		this.infoDept = infoDept;
		this.infoGoods = infoGoods;
		this.paramConfig = paramConfig;
		this.allocation=allocation;
		this.num = num;
		this.unitDate = unitDate;
		this.userId = userId;
		this.takeDate = takeDate;
		this.status=status;
		this.del = del;
		this.feedback=feedback;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "apply_id", unique = true, nullable = false)
	public Integer getApplyId() {
		return this.applyId;
	}

	public void setApplyId(Integer applyId) {
		this.applyId = applyId;
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
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "allocation_id")
	public Allocation getAllocation() {
		return this.allocation;
	}

	public void setAllocation(Allocation allocation) {
		this.allocation = allocation;
	}
	@Column(name = "num", precision = 18, scale = 4)
	public Double getNum() {
		return this.num;
	}

	public void setNum(Double num) {
		this.num = num;
	}

	@Column(name = "unit_date", length = 19)
	public Date getUnitDate() {
		return this.unitDate;
	}

	public void setUnitDate(Date unitDate) {
		this.unitDate = unitDate;
	}

	@Column(name = "user_id")
	public Integer getUserId() {
		return this.userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	@Column(name = "take_date", length = 19)
	public Date getTakeDate() {
		return this.takeDate;
	}

	public void setTakeDate(Date takeDate) {
		this.takeDate = takeDate;
	}
	@Column(name = "status")
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
	@Column(name = "feedback", length = 200)
	public String getFeedback() {
		return this.feedback;
	}

	public void setFeedback(String feedback) {
		this.feedback = feedback;
	}
	@Column(name = "del")
	public Boolean getDel() {
		return this.del;
	}

	public void setDel(Boolean del) {
		this.del = del;
	}

}