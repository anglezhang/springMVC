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

/**
 * OrderPurchase entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "order_purchase")
public class OrderPurchase implements java.io.Serializable {

	// Fields

	private Integer purOrderId;
	private InfoSup infoSup;
	private InfoEmp infoEmp;
	private ParamConfig paramConfig;
	private Date purOrderDate;
	private String memo;
	private String purOrderNo;
	private Integer dateOrderNo;
	private Integer auditType;
	private Integer deptId;
	private Boolean del;
	private Set<OrderList> orderLists = new HashSet<OrderList>(0);
	private Set<OrderAudit> orderAudits = new HashSet<OrderAudit>(0);
	//订单状态
	private Integer status;
	//实际入库总金额
	private Double depotNum;
	//含税总金额
	private Double countTaxPrice;
	//不含税总金额
	private Double countPrice;
	@Transient
	public Double getCountTaxPrice() {
		return countTaxPrice;
	}

	public void setCountTaxPrice(Double countTaxPrice) {
		this.countTaxPrice = countTaxPrice;
	}

	@Column(name = "status")
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
	@Transient
	public Double getDepotNum() {
		return depotNum;
	}

	public void setDepotNum(Double depotNum) {
		this.depotNum = depotNum;
	}

	
	@Transient
	public Double getCountPrice() {
		return countPrice;
	}

	public void setCountPrice(Double countPrice) {
		this.countPrice = countPrice;
	}

	private OrderList[] orderList;
	
	@Transient
	public OrderList[] getOrderList() {
		return orderList;
	}

	public void setOrderList(OrderList[] orderList) {
		this.orderList = orderList;
	}

	/** default constructor */
	public OrderPurchase() {
		
	}

	/** full constructor */
	public OrderPurchase(InfoSup infoSup, InfoEmp infoEmp,
			ParamConfig paramConfig, Date purOrderDate, String memo,
			String purOrderNo,Integer dateOrderNo,Integer status, Integer auditType,Integer deptId,Boolean del, Set<OrderList> orderLists,
			Set<OrderAudit> orderAudits) {
		this.infoSup = infoSup;
		this.infoEmp = infoEmp;
		this.paramConfig = paramConfig;
		this.purOrderDate = purOrderDate;
		this.memo = memo;
		this.purOrderNo = purOrderNo;
		this.dateOrderNo=dateOrderNo;
		this.status=status;
		this.auditType=auditType;
		this.deptId=deptId;
		this.del = del;
		this.orderLists = orderLists;
		this.orderAudits = orderAudits;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "order_id", unique = true, nullable = false)
	public Integer getPurOrderId() {
		return purOrderId;
	}

	public void setPurOrderId(Integer purOrderId) {
		this.purOrderId = purOrderId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id")
	public InfoSup getInfoSup() {
		return this.infoSup;
	}

	public void setInfoSup(InfoSup infoSup) {
		this.infoSup = infoSup;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "emp_id")
	public InfoEmp getInfoEmp() {
		return this.infoEmp;
	}

	public void setInfoEmp(InfoEmp infoEmp) {
		this.infoEmp = infoEmp;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "unit_id")
	public ParamConfig getParamConfig() {
		return this.paramConfig;
	}

	public void setParamConfig(ParamConfig paramConfig) {
		this.paramConfig = paramConfig;
	}

	@Column(name = "pur_order_date", length = 19)
	public Date getPurOrderDate() {
		return this.purOrderDate;
	}

	public void setPurOrderDate(Date purOrderDate) {
		this.purOrderDate = purOrderDate;
	}

	@Column(name = "memo", length = 500)
	public String getMemo() {
		return this.memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	@Column(name = "pur_order_no", length = 50)
	public String getPurOrderNo() {
		return this.purOrderNo;
	}

	public void setPurOrderNo(String purOrderNo) {
		this.purOrderNo = purOrderNo;
	}
	@Column(name = "date_order_no")
	public Integer getDateOrderNo() {
		return this.dateOrderNo;
	}

	public void setDateOrderNo(Integer dateOrderNo) {
		this.dateOrderNo = dateOrderNo;
	}
	@Column(name = "audit_type")
	public Integer getAuditType() {
		return this.auditType;
	}

	public void setAuditType(Integer auditType) {
		this.auditType = auditType;
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

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "orderPurchase")
	public Set<OrderList> getOrderLists() {
		return this.orderLists;
	}

	public void setOrderLists(Set<OrderList> orderLists) {
		this.orderLists = orderLists;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "orderPurchase")
	public Set<OrderAudit> getOrderAudits() {
		return this.orderAudits;
	}

	public void setOrderAudits(Set<OrderAudit> orderAudits) {
		this.orderAudits = orderAudits;
	}

}