package com.zoomoor.jy.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * CustomerEntrust entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "customer_entrust"

)
public class CustomerEntrust implements java.io.Serializable {

	// Fields

	private Integer entrustId;
	private InfoDept infoDept;
	private Date createtime;
	private Integer fixplace;// 维修地点默认本店是0
	private CarCustmerMapping carCusMap;
	private Integer receptionist;
	private Integer fixer;
	private Date estimatetime;
	private Integer cusEntats;//订单状态
	private Double discount;
	private Integer discounter;
	private Double shopmonney;
	private String remark;
	private Boolean del;
	private Set<CustomerEntrustSub> customerEntrustSubs = new HashSet<CustomerEntrustSub>(
			0);
	private Set<CustomeOrderstatus> customeOrderstatuses = new HashSet<CustomeOrderstatus>(
			0);
	private String cusEnstrustNum;// 委托单号
	private Integer cusappoitId;// 预约单号
	private Serviceitem[] items;// 服务项目集合
	private InfoGoods[] goods;// 添加物料集合
	private InfoEmp accountman;//结算人
	private Double amount;
	private String settlement;//结算单号
	private Double reallymoney;//实收金额

	// Constructors

	/** default constructor */
	public CustomerEntrust() {
		
	}

	/** full constructor */
	public CustomerEntrust(InfoDept infoDept, Date createtime,
			Integer fixplace, Integer receptionist, Integer fixer,
			Integer discounter, Double shopmonney, String remark, Boolean del,
			Set<CustomerEntrustSub> customerEntrustSubs,
			Set<CustomeOrderstatus> customeOrderstatuses) {
		this.infoDept = infoDept;
		this.createtime = createtime;
		this.fixplace = fixplace;
		this.receptionist = receptionist;
		this.fixer = fixer;
		this.estimatetime = estimatetime;
		this.discount = discount;
		this.discounter = discounter;
		this.shopmonney = shopmonney;
		this.remark = remark;
		this.del = del;
		this.customerEntrustSubs = customerEntrustSubs;
		this.customeOrderstatuses = customeOrderstatuses;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "entrust_id", unique = true, nullable = false)
	public Integer getEntrustId() {
		return this.entrustId;
	}

	public void setEntrustId(Integer entrustId) {
		this.entrustId = entrustId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "dept_id")
	public InfoDept getInfoDept() {
		return this.infoDept;
	}

	public void setInfoDept(InfoDept infoDept) {
		this.infoDept = infoDept;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "vehicleContac_id")
	public CarCustmerMapping getCarCusMap() {
		return carCusMap;
	}

	public void setCarCusMap(CarCustmerMapping carCusMap) {
		this.carCusMap = carCusMap;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "createtime", length = 19)
	public Date getCreatetime() {
		return this.createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	@Column(name = "fixplace")
	public Integer getFixplace() {
		return this.fixplace;
	}

	public void setFixplace(Integer fixplace) {
		this.fixplace = fixplace;
	}

	@Column(name = "receptionist")
	public Integer getReceptionist() {
		return this.receptionist;
	}

	public void setReceptionist(Integer receptionist) {
		this.receptionist = receptionist;
	}

	@Column(name = "fixer")
	public Integer getFixer() {
		return this.fixer;
	}

	public void setFixer(Integer fixer) {
		this.fixer = fixer;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "estimatetime", length = 19)
	public Date getEstimatetime() {
		return this.estimatetime;
	}

	public void setEstimatetime(Date estimatetime) {
		this.estimatetime = estimatetime;
	}

	@Column(name = "order_status")
	public Integer getCusEntats() {
		return cusEntats;
	}

	public void setCusEntats(Integer cusEntats) {
		this.cusEntats = cusEntats;
	}

	@Column(name = "discount")
	public Double getDiscount() {
		return this.discount;
	}
	public void setDiscount(Double discount) {
		this.discount = discount;
	}

	@Column(name = "discounter")
	public Integer getDiscounter() {
		return this.discounter;
	}

	public void setDiscounter(Integer discounter) {
		this.discounter = discounter;
	}

	@Column(name = "shopmonney", precision = 9)
	public Double getShopmonney() {
		return this.shopmonney;
	}

	public void setShopmonney(Double shopmonney) {
		this.shopmonney = shopmonney;
	}

	@Column(name = "remark", length = 50)
	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Column(name = "del")
	public Boolean getDel() {
		return this.del;
	}

	public void setDel(Boolean del) {
		this.del = del;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "customerEntrust")
	public Set<CustomerEntrustSub> getCustomerEntrustSubs() {
		return this.customerEntrustSubs;
	}

	public void setCustomerEntrustSubs(
			Set<CustomerEntrustSub> customerEntrustSubs) {
		this.customerEntrustSubs = customerEntrustSubs;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "customerEntrust")
	public Set<CustomeOrderstatus> getCustomeOrderstatuses() {
		return this.customeOrderstatuses;
	}

	public void setCustomeOrderstatuses(
			Set<CustomeOrderstatus> customeOrderstatuses) {
		this.customeOrderstatuses = customeOrderstatuses;
	}
	@Column(name = "cusappoit_id")
	public Integer getCusappoitId() {
		return cusappoitId;
	}

	public void setCusappoitId(Integer cusappoitId) {
		this.cusappoitId = cusappoitId;
	}

	@Column(name = "cusordernum", length = 20)
	public String getCusEnstrustNum() {
		return cusEnstrustNum;
	}

	public void setCusEnstrustNum(String cusEnstrustNum) {
		this.cusEnstrustNum = cusEnstrustNum;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "accountman")
	public InfoEmp getAccountman() {
		return accountman;
	}

	public void setAccountman(InfoEmp accountman) {
		this.accountman = accountman;
	}

	@Column(name = "amount")
	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	@Transient
	public Serviceitem[] getItems() {
		return items;
	}

	public void setItems(Serviceitem[] items) {
		this.items = items;
	}

	@Transient
	public InfoGoods[] getGoods() {
		return goods;
	}

	public void setGoods(InfoGoods[] goods) {
		this.goods = goods;
	}

	@Column(name = "settlement")
	public String getSettlement() {
		return settlement;
	}

	public void setSettlement(String settlement) {
		this.settlement = settlement;
	}

	@Column(name = "reallymoney")
	public Double getReallymoney() {
		return reallymoney;
	}

	public void setReallymoney(Double reallymoney) {
		this.reallymoney = reallymoney;
	}

	
	
}