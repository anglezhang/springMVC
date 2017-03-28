package com.zoomoor.jy.entity;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * ParamConfig entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "param_config" )
public class ParamConfig implements java.io.Serializable {

	// Fields

	private Integer unitId;
	private String name;
	private Integer paramType;
	private Integer sort;
	private Boolean del;
	private Set<AllocationApply> allocationApplies = new HashSet<AllocationApply>(
			0);
	private Set<InfoEmp> infoEmps = new HashSet<InfoEmp>(0);
	private Set<InfoGoods> infoGoodses = new HashSet<InfoGoods>(0);
	private Set<OrderPurchase> orderPurchases = new HashSet<OrderPurchase>(0);
	private Set<DepotMoth> depotMoths = new HashSet<DepotMoth>(0);
	private Set<DepotBillList> depotBillLists = new HashSet<DepotBillList>(0);
	private Set<ConfigEmpMapping> configEmpMappings = new HashSet<ConfigEmpMapping>(
			0);

	// Constructors

	/** default constructor */
	public ParamConfig() {
	}

	/** full constructor */
	public ParamConfig(String name, Integer paramType, Integer sort,
			Boolean del, Set<AllocationApply> allocationApplies,
			Set<InfoEmp> infoEmps, Set<InfoGoods> infoGoodses,
			Set<OrderPurchase> orderPurchases, Set<DepotMoth> depotMoths,
			Set<DepotBillList> depotBillLists,
			Set<ConfigEmpMapping> configEmpMappings) {
		this.name = name;
		this.paramType = paramType;
		this.sort = sort;
		this.del = del;
		this.allocationApplies = allocationApplies;
		this.infoEmps = infoEmps;
		this.infoGoodses = infoGoodses;
		this.orderPurchases = orderPurchases;
		this.depotMoths = depotMoths;
		this.depotBillLists = depotBillLists;
		this.configEmpMappings = configEmpMappings;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "unit_id", unique = true, nullable = false)
	public Integer getUnitId() {
		return this.unitId;
	}

	public void setUnitId(Integer unitId) {
		this.unitId = unitId;
	}

	@Column(name = "name", length = 10)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "param_type")
	public Integer getParamType() {
		return this.paramType;
	}

	public void setParamType(Integer paramType) {
		this.paramType = paramType;
	}

	@Column(name = "sort")
	public Integer getSort() {
		return this.sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	@Column(name = "del")
	public Boolean getDel() {
		return this.del;
	}

	public void setDel(Boolean del) {
		this.del = del;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "paramConfig")
	public Set<AllocationApply> getAllocationApplies() {
		return this.allocationApplies;
	}

	public void setAllocationApplies(Set<AllocationApply> allocationApplies) {
		this.allocationApplies = allocationApplies;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "paramConfig")
	public Set<InfoEmp> getInfoEmps() {
		return this.infoEmps;
	}

	public void setInfoEmps(Set<InfoEmp> infoEmps) {
		this.infoEmps = infoEmps;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "paramConfig")
	public Set<InfoGoods> getInfoGoodses() {
		return this.infoGoodses;
	}

	public void setInfoGoodses(Set<InfoGoods> infoGoodses) {
		this.infoGoodses = infoGoodses;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "paramConfig")
	public Set<OrderPurchase> getOrderPurchases() {
		return this.orderPurchases;
	}

	public void setOrderPurchases(Set<OrderPurchase> orderPurchases) {
		this.orderPurchases = orderPurchases;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "paramConfig")
	public Set<DepotMoth> getDepotMoths() {
		return this.depotMoths;
	}

	public void setDepotMoths(Set<DepotMoth> depotMoths) {
		this.depotMoths = depotMoths;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "paramConfig")
	public Set<DepotBillList> getDepotBillLists() {
		return this.depotBillLists;
	}

	public void setDepotBillLists(Set<DepotBillList> depotBillLists) {
		this.depotBillLists = depotBillLists;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "paramConfig")
	public Set<ConfigEmpMapping> getConfigEmpMappings() {
		return this.configEmpMappings;
	}

	public void setConfigEmpMappings(Set<ConfigEmpMapping> configEmpMappings) {
		this.configEmpMappings = configEmpMappings;
	}

}