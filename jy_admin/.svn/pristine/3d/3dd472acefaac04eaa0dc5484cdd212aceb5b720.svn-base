package com.zoomoor.jy.entity;

import static javax.persistence.GenerationType.IDENTITY;

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
 * InfoGoods entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "info_goods" )
public class InfoGoods implements java.io.Serializable {

	// Fields

	private Integer goodsId;
	private InfoBrand infoBrand;
	private InfoGoodsType infoGoodsType;
	private ParamConfig paramConfig;
	private String goodsCode;
	private String pinyin;
	private String name;
	private String standard;
	private String artNo;
	private String packUnit;
	private String packSpec;
	private Double price;
	private Double wholesalePrice;
	private Double claimPrice;
	private Double advisePrice;
	private Integer intergral;
	private Double insurancePrice;
	private Double memberPrice;
	private String typeShow;
	private Double discount;
	private String goodsBrand;
	private Double costPrice;
	private Boolean del;
	private Set<AllocationApply> allocationApplies = new HashSet<AllocationApply>(
			0);
	private Set<DepotBillList> depotBillLists = new HashSet<DepotBillList>(0);
	private Set<SitemgoodsMapping> sitemgoodsMappings = new HashSet<SitemgoodsMapping>(
			0);
	private Set<OrderList> orderLists = new HashSet<OrderList>(0);
	private Set<DepotMoth> depotMoths = new HashSet<DepotMoth>(0);
	//申请数量
	private Double num;
	@Transient
	public Double getNum() {
		return num;
	}

	public void setNum(Double num) {
		this.num = num;
	}

	/** default constructor */
	public InfoGoods() {
	}

	/** full constructor */
	public InfoGoods(InfoBrand infoBrand, InfoGoodsType infoGoodsType,
			ParamConfig paramConfig, String goodsCode,String pinyin, String name,
			String standard, String artNo,  String packUnit,
			String packSpec, Double price, Double wholesalePrice,
			Double claimPrice, Double advisePrice, Integer intergral,
			Double insurancePrice, Double memberPrice,String typeShow,
			Double discount,String goodsBrand,Double costPrice,Boolean del,
			Set<AllocationApply> allocationApplies,
			Set<DepotBillList> depotBillLists,
			Set<SitemgoodsMapping> sitemgoodsMappings,
			Set<OrderList> orderLists, Set<DepotMoth> depotMoths) {
		this.infoBrand = infoBrand;
		this.infoGoodsType = infoGoodsType;
		this.paramConfig = paramConfig;
		this.goodsCode = goodsCode;
		this.pinyin=pinyin;
		this.name = name;
		this.standard = standard;
		this.artNo = artNo;
		this.packUnit = packUnit;
		this.packSpec = packSpec;
		this.price = price;
		this.wholesalePrice = wholesalePrice;
		this.claimPrice = claimPrice;
		this.advisePrice = advisePrice;
		this.intergral = intergral;
		this.insurancePrice = insurancePrice;
		this.memberPrice=memberPrice;
		this.discount=discount;
		this.goodsBrand=goodsBrand;
		this.costPrice=costPrice;
		this.del = del;
		this.typeShow=typeShow;
		this.allocationApplies = allocationApplies;
		this.depotBillLists = depotBillLists;
		this.sitemgoodsMappings = sitemgoodsMappings;
		this.orderLists = orderLists;
		this.depotMoths = depotMoths;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "goods_id", unique = true, nullable = false)
	public Integer getGoodsId() {
		return this.goodsId;
	}

	public void setGoodsId(Integer goodsId) {
		this.goodsId = goodsId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id")
	public InfoBrand getInfoBrand() {
		return this.infoBrand;
	}

	public void setInfoBrand(InfoBrand infoBrand) {
		this.infoBrand = infoBrand;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "group_id")
	public InfoGoodsType getInfoGoodsType() {
		return this.infoGoodsType;
	}

	public void setInfoGoodsType(InfoGoodsType infoGoodsType) {
		this.infoGoodsType = infoGoodsType;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "unit_id")
	public ParamConfig getParamConfig() {
		return this.paramConfig;
	}

	public void setParamConfig(ParamConfig paramConfig) {
		this.paramConfig = paramConfig;
	}

	@Column(name = "goods_code", length = 50)
	public String getGoodsCode() {
		return this.goodsCode;
	}

	public void setGoodsCode(String goodsCode) {
		this.goodsCode = goodsCode;
	}

	@Column(name = "name", length = 100)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "standard", length = 10)
	public String getStandard() {
		return this.standard;
	}

	public void setStandard(String standard) {
		this.standard = standard;
	}

	@Column(name = "art_no", length = 50)
	public String getArtNo() {
		return this.artNo;
	}

	public void setArtNo(String artNo) {
		this.artNo = artNo;
	}
	@Column(name = "pack_unit", length = 30)
	public String getPackUnit() {
		return this.packUnit;
	}

	public void setPackUnit(String packUnit) {
		this.packUnit = packUnit;
	}

	@Column(name = "pack_spec", length = 50)
	public String getPackSpec() {
		return this.packSpec;
	}

	public void setPackSpec(String packSpec) {
		this.packSpec = packSpec;
	}

	@Column(name = "price", precision = 9)
	public Double getPrice() {
		return this.price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	@Column(name = "wholesale_price", precision = 9)
	public Double getWholesalePrice() {
		return this.wholesalePrice;
	}

	public void setWholesalePrice(Double wholesalePrice) {
		this.wholesalePrice = wholesalePrice;
	}

	@Column(name = "claim_price", precision = 9)
	public Double getClaimPrice() {
		return this.claimPrice;
	}

	public void setClaimPrice(Double claimPrice) {
		this.claimPrice = claimPrice;
	}

	@Column(name = "advise_price", precision = 9)
	public Double getAdvisePrice() {
		return this.advisePrice;
	}

	public void setAdvisePrice(Double advisePrice) {
		this.advisePrice = advisePrice;
	}

	@Column(name = "intergral")
	public Integer getIntergral() {
		return this.intergral;
	}

	public void setIntergral(Integer intergral) {
		this.intergral = intergral;
	}

	@Column(name = "insurance_price", precision = 9)
	public Double getInsurancePrice() {
		return this.insurancePrice;
	}

	public void setInsurancePrice(Double insurancePrice) {
		this.insurancePrice = insurancePrice;
	}
	@Column(name = "member_price", precision = 9)
	public Double getMemberPrice() {
		return this.memberPrice;
	}

	public void setMemberPrice(Double memberPrice) {
		this.memberPrice = memberPrice;
	}
	@Column(name = "type_show", length = 100)
	public String getTypeShow() {
		return this.typeShow;
	}

	public void setTypeShow(String typeShow) {
		this.typeShow = typeShow;
	}
	@Column(name = "discount")
	public Double getDiscount() {
		return this.discount;
	}

	public void setDiscount(Double discount) {
		this.discount = discount;
	}
	@Column(name = "goods_brand", length = 50)
	public String getGoodsBrand() {
		return this.goodsBrand;
	}

	public void setGoodsBrand(String goodsBrand) {
		this.goodsBrand = goodsBrand;
	}
	@Column(name = "cost_price", precision = 10)
	public Double getCostPrice() {
		return this.costPrice;
	}

	public void setCostPrice(Double costPrice) {
		this.costPrice = costPrice;
	}
	@Column(name = "del")
	public Boolean getDel() {
		return this.del;
	}

	public void setDel(Boolean del) {
		this.del = del;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "infoGoods")
	public Set<AllocationApply> getAllocationApplies() {
		return this.allocationApplies;
	}

	public void setAllocationApplies(Set<AllocationApply> allocationApplies) {
		this.allocationApplies = allocationApplies;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "infoGoods")
	public Set<DepotBillList> getDepotBillLists() {
		return this.depotBillLists;
	}

	public void setDepotBillLists(Set<DepotBillList> depotBillLists) {
		this.depotBillLists = depotBillLists;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "infoGoods")
	public Set<SitemgoodsMapping> getSitemgoodsMappings() {
		return this.sitemgoodsMappings;
	}

	public void setSitemgoodsMappings(Set<SitemgoodsMapping> sitemgoodsMappings) {
		this.sitemgoodsMappings = sitemgoodsMappings;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "infoGoods")
	public Set<OrderList> getOrderLists() {
		return this.orderLists;
	}

	public void setOrderLists(Set<OrderList> orderLists) {
		this.orderLists = orderLists;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "infoGoods")
	public Set<DepotMoth> getDepotMoths() {
		return this.depotMoths;
	}

	public void setDepotMoths(Set<DepotMoth> depotMoths) {
		this.depotMoths = depotMoths;
	}
	@Column(name = "pinyin", nullable = false, length = 50)
	public String getPinyin() {
		return this.pinyin;
	}

	public void setPinyin(String pinyin) {
		this.pinyin = pinyin;
	}
}