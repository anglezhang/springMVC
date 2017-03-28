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
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * InfoSup entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "info_sup" )
public class InfoSup implements java.io.Serializable {

	// Fields

	private Integer id;
	private String name;
	private String code;
	private String shortName;
	private String legalPerson;
	private String zipCode;
	private String address;
	private String phone;
	private String faxNum;
	private String linkMan;
	private String bank;
	private String taxNo;
	private String bankNo;
	private String financeNo;
	private Double regCapital;
	private String businessScope;
	private String grade;
	private String memo;
	private Boolean del;
	private Set<OrderPurchase> orderPurchases = new HashSet<OrderPurchase>(0);

	// Constructors

	/** default constructor */
	public InfoSup() {
	}

	/** full constructor */
	public InfoSup(String name, String code, String shortName,
			String legalPerson, String zipCode, String address, String phone,
			String faxNum, String linkMan, String bank, String taxNo,
			String bankNo, String financeNo, Double regCapital,
			String businessScope, String grade, String memo, Boolean del,
			Set<OrderPurchase> orderPurchases) {
		this.name = name;
		this.code = code;
		this.shortName = shortName;
		this.legalPerson = legalPerson;
		this.zipCode = zipCode;
		this.address = address;
		this.phone = phone;
		this.faxNum = faxNum;
		this.linkMan = linkMan;
		this.bank = bank;
		this.taxNo = taxNo;
		this.bankNo = bankNo;
		this.financeNo = financeNo;
		this.regCapital = regCapital;
		this.businessScope = businessScope;
		this.grade = grade;
		this.memo = memo;
		this.del = del;
		this.orderPurchases = orderPurchases;
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

	@Column(name = "name", length = 100)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "code", length = 30)
	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Column(name = "short_name", length = 50)
	public String getShortName() {
		return this.shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	@Column(name = "legal_person", length = 30)
	public String getLegalPerson() {
		return this.legalPerson;
	}

	public void setLegalPerson(String legalPerson) {
		this.legalPerson = legalPerson;
	}

	@Column(name = "zip_code", length = 10)
	public String getZipCode() {
		return this.zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	@Column(name = "address", length = 100)
	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Column(name = "phone", length = 30)
	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Column(name = "fax_num", length = 30)
	public String getFaxNum() {
		return this.faxNum;
	}

	public void setFaxNum(String faxNum) {
		this.faxNum = faxNum;
	}

	@Column(name = "link_man", length = 30)
	public String getLinkMan() {
		return this.linkMan;
	}

	public void setLinkMan(String linkMan) {
		this.linkMan = linkMan;
	}

	@Column(name = "bank", length = 50)
	public String getBank() {
		return this.bank;
	}

	public void setBank(String bank) {
		this.bank = bank;
	}

	@Column(name = "tax_no", length = 50)
	public String getTaxNo() {
		return this.taxNo;
	}

	public void setTaxNo(String taxNo) {
		this.taxNo = taxNo;
	}

	@Column(name = "bank_no", length = 50)
	public String getBankNo() {
		return this.bankNo;
	}

	public void setBankNo(String bankNo) {
		this.bankNo = bankNo;
	}

	@Column(name = "finance_no", length = 50)
	public String getFinanceNo() {
		return this.financeNo;
	}

	public void setFinanceNo(String financeNo) {
		this.financeNo = financeNo;
	}

	@Column(name = "reg_capital", precision = 18)
	public Double getRegCapital() {
		return this.regCapital;
	}

	public void setRegCapital(Double regCapital) {
		this.regCapital = regCapital;
	}

	@Column(name = "business_scope", length = 500)
	public String getBusinessScope() {
		return this.businessScope;
	}

	public void setBusinessScope(String businessScope) {
		this.businessScope = businessScope;
	}

	@Column(name = "grade", length = 20)
	public String getGrade() {
		return this.grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	@Column(name = "memo", length = 500)
	public String getMemo() {
		return this.memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	@Column(name = "del")
	public Boolean getDel() {
		return this.del;
	}

	public void setDel(Boolean del) {
		this.del = del;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "infoSup")
	public Set<OrderPurchase> getOrderPurchases() {
		return this.orderPurchases;
	}

	public void setOrderPurchases(Set<OrderPurchase> orderPurchases) {
		this.orderPurchases = orderPurchases;
	}

}