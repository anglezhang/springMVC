package com.zoomoor.jy.entity;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.OneToOne;

import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;


/**
 * InfoDept entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="info_dept"
     
)

public class InfoDept  implements java.io.Serializable {


    // Fields    

     private Integer deptId;
     private InfoDept infoDept;
     private InfoDeptSub infoDeptSub;
     private String name;
     private Boolean isShop;
     private Integer sort;
     private Boolean del;
     private Set<DepotMoth> depotMoths = new HashSet<DepotMoth>(0);
     private Set<InfoEmp> infoEmps = new HashSet<InfoEmp>(0);
     private Set<CustomerEntrust> customerEntrusts = new HashSet<CustomerEntrust>(0);
     private Set<AllocationApply> allocationApplies = new HashSet<AllocationApply>(0);
     private Set<SummaryDeptMapping> summaryDeptMappings = new HashSet<SummaryDeptMapping>(0);
     private Set<InfoDept> infoDepts = new HashSet<InfoDept>(0);
     private Set<DepotBill> depotBills = new HashSet<DepotBill>(0);


    // Constructors

    /** default constructor */
    public InfoDept() {
    }

    
    /** full constructor */
    public InfoDept(InfoDept infoDept, String name, Boolean isShop, Integer sort, Boolean del, Set<DepotMoth> depotMoths, Set<InfoEmp> infoEmps, InfoDeptSub infoDeptSub, Set<CustomerEntrust> customerEntrusts, Set<AllocationApply> allocationApplies, Set<SummaryDeptMapping> summaryDeptMappings, Set<InfoDept> infoDepts, Set<DepotBill> depotBills) {
        this.infoDept = infoDept;
        this.name = name;
        this.isShop = isShop;
        this.sort = sort;
        this.del = del;
        this.depotMoths = depotMoths;
        this.infoEmps = infoEmps;
        this.infoDeptSub = infoDeptSub;
        this.customerEntrusts = customerEntrusts;
        this.allocationApplies = allocationApplies;
        this.summaryDeptMappings = summaryDeptMappings;
        this.infoDepts = infoDepts;
        this.depotBills = depotBills;
    }

	@OneToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="dept_child_id")
    public InfoDeptSub getInfoDeptSub() {
		return infoDeptSub;
	}


	public void setInfoDeptSub(InfoDeptSub infoDeptSub) {
		this.infoDeptSub = infoDeptSub;
	}


	// Property accessors
    @Id @GeneratedValue(strategy=IDENTITY)
    
    @Column(name="dept_id", unique=true, nullable=false)

    public Integer getDeptId() {
        return this.deptId;
    }
    
    public void setDeptId(Integer deptId) {
        this.deptId = deptId;
    }
	@ManyToOne(fetch=FetchType.LAZY)
        @JoinColumn(name="up_id")
    public InfoDept getInfoDept() {
        return this.infoDept;
    }
    
    public void setInfoDept(InfoDept infoDept) {
        this.infoDept = infoDept;
    }
    
    @Column(name="name", length=100)

    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    @Column(name="is_shop")

    public Boolean getIsShop() {
        return this.isShop;
    }
    
    public void setIsShop(Boolean isShop) {
        this.isShop = isShop;
    }
    
    @Column(name="sort")

    public Integer getSort() {
        return this.sort;
    }
    
    public void setSort(Integer sort) {
        this.sort = sort;
    }
    
    @Column(name="del")

    public Boolean getDel() {
        return this.del;
    }
    
    public void setDel(Boolean del) {
        this.del = del;
    }
@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="infoDept")

    public Set<DepotMoth> getDepotMoths() {
        return this.depotMoths;
    }
    
    public void setDepotMoths(Set<DepotMoth> depotMoths) {
        this.depotMoths = depotMoths;
    }
@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="infoDept")

    public Set<InfoEmp> getInfoEmps() {
        return this.infoEmps;
    }
    
    public void setInfoEmps(Set<InfoEmp> infoEmps) {
        this.infoEmps = infoEmps;
    }
@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="infoDept")
    public Set<CustomerEntrust> getCustomerEntrusts() {
        return this.customerEntrusts;
    }
    
    public void setCustomerEntrusts(Set<CustomerEntrust> customerEntrusts) {
        this.customerEntrusts = customerEntrusts;
    }
@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="infoDept")

    public Set<AllocationApply> getAllocationApplies() {
        return this.allocationApplies;
    }
    
    public void setAllocationApplies(Set<AllocationApply> allocationApplies) {
        this.allocationApplies = allocationApplies;
    }
@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="infoDept")

    public Set<SummaryDeptMapping> getSummaryDeptMappings() {
        return this.summaryDeptMappings;
    }
    
    public void setSummaryDeptMappings(Set<SummaryDeptMapping> summaryDeptMappings) {
        this.summaryDeptMappings = summaryDeptMappings;
    }
@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="infoDept")

    public Set<InfoDept> getInfoDepts() {
        return this.infoDepts;
    }
    
    public void setInfoDepts(Set<InfoDept> infoDepts) {
        this.infoDepts = infoDepts;
    }
@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="infoDept")

    public Set<DepotBill> getDepotBills() {
        return this.depotBills;
    }
    
    public void setDepotBills(Set<DepotBill> depotBills) {
        this.depotBills = depotBills;
    }
   








}