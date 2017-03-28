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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * Allocation entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "allocation")
public class Allocation implements java.io.Serializable {

	// Fields

	private Integer id;
	private String memo;
	private Integer userId;
	private Date swDate;
	private String allocationNo;
	private Integer dateAllocationNo;
	private Integer deptId;
	private Boolean del;
	private Set<AllocationList> allocationLists = new HashSet<AllocationList>(0);
	private Set<AllocationApply> allocationApplies = new HashSet<AllocationApply>(
			0);
	//员工姓名
	private String empName;
	//店铺名称
	private String deptName;
	
	// Constructors
	@Transient
	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}
	@Transient
	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	/** default constructor */
	public Allocation() {
	}

	/** full constructor */
	public Allocation(String memo, Integer userId, Date swDate,
			String allocationNo, Integer dateAllocationNo,Integer deptId, Boolean del,
			Set<AllocationList> allocationLists,
			Set<AllocationApply> allocationApplies) {
		this.memo = memo;
		this.userId = userId;
		this.swDate = swDate;
		this.allocationNo = allocationNo;
		this.dateAllocationNo = dateAllocationNo;
		this.deptId=deptId;
		this.del = del;
		this.allocationLists = allocationLists;
		this.allocationApplies = allocationApplies;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "ID", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "memo", length = 200)
	public String getMemo() {
		return this.memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	@Column(name = "user_id")
	public Integer getUserId() {
		return this.userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	@Column(name = "sw_date", length = 19)
	public Date getSwDate() {
		return this.swDate;
	}

	public void setSwDate(Date swDate) {
		this.swDate = swDate;
	}

	@Column(name = "allocation_no", length = 50)
	public String getAllocationNo() {
		return this.allocationNo;
	}

	public void setAllocationNo(String allocationNo) {
		this.allocationNo = allocationNo;
	}

	@Column(name = "date_allocation_no")
	public Integer getDateAllocationNo() {
		return this.dateAllocationNo;
	}

	public void setDateAllocationNo(Integer dateAllocationNo) {
		this.dateAllocationNo = dateAllocationNo;
	}
	@Column(name = "dept_id")
	public Integer getDeptId() {
		return this.deptId;
	}

	public void setDeptId(Integer deptId) {
		this.deptId = deptId;
	}
	@Column(name = "del")
	public Boolean getDel() {
		return this.del;
	}

	public void setDel(Boolean del) {
		this.del = del;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "allocation")
	public Set<AllocationList> getAllocationLists() {
		return this.allocationLists;
	}

	public void setAllocationLists(Set<AllocationList> allocationLists) {
		this.allocationLists = allocationLists;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "allocation")
	public Set<AllocationApply> getAllocationApplies() {
		return this.allocationApplies;
	}

	public void setAllocationApplies(Set<AllocationApply> allocationApplies) {
		this.allocationApplies = allocationApplies;
	}

}