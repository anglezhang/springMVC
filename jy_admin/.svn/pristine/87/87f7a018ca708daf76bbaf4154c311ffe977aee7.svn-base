package com.zoomoor.jy.entity;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * InfoCar entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "info_car"

)
public class InfoCar implements java.io.Serializable {

	private Integer carId;
	private InfoBrand infoBrand;
	private InfoCustome infCustomer;
	private String framnum;
	private String vinnum;
	private String platenum;
	private Date buytime;
	private BigDecimal mileage;
	private Date nextmain;
	private Date nextexam;
	private Date nextins;
	private Date nextvis;
	private String inscompany;
	private String insremark;
	private String remark;
	private String color;
	private Date listingtime;
	private Boolean del;
	private Date factoryDate;// 出厂日期
	private String brandName;// 品牌车系
	private Boolean isAuto;// true 自动档 false手动档
	private String displacement;// 排量
	private Set<CarCustmerMapping> carCustmerMappings = new HashSet<CarCustmerMapping>(
			0);
	private Set<CustomerAppoit> customerAppoits = new HashSet<CustomerAppoit>(0);

	public InfoCar() {
	}

	public InfoCar(InfoBrand infoBrand, String framnum, String vinnum,
			String platenum, Date buytime, BigDecimal mileage, Date nextmain,
			Date nextexam, Date nextins, Date nextvis, String inscompany,
			String remark, Boolean del,
			Set<CarCustmerMapping> carCustmerMappings,
			Set<CustomerAppoit> customerAppoits) {
		this.infoBrand = infoBrand;
		this.framnum = framnum;
		this.vinnum = vinnum;
		this.platenum = platenum;
		this.mileage = mileage;
		this.nextmain = nextmain;
		this.nextexam = nextexam;
		this.nextins = nextins;
		this.nextvis = nextvis;
		this.inscompany = inscompany;
		this.insremark = insremark;
		this.remark = remark;
		this.del = del;
		this.carCustmerMappings = carCustmerMappings;
		this.customerAppoits = customerAppoits;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "car_id", unique = true, nullable = false)
	public Integer getCarId() {
		return this.carId;
	}

	public void setCarId(Integer carId) {
		this.carId = carId;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id")
	public InfoBrand getInfoBrand() {
		return this.infoBrand;
	}

	public void setInfoBrand(InfoBrand infoBrand) {
		this.infoBrand = infoBrand;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "customer_id")
	public InfoCustome getInfCustomer() {
		return infCustomer;
	}

	public void setInfCustomer(InfoCustome infCustomer) {
		this.infCustomer = infCustomer;
	}

	@Column(name = "framnum", length = 30)
	public String getFramnum() {
		return this.framnum;
	}

	public void setFramnum(String framnum) {
		this.framnum = framnum;
	}

	@Column(name = "vinnum", length = 30)
	public String getVinnum() {
		return this.vinnum;
	}

	public void setVinnum(String vinnum) {
		this.vinnum = vinnum;
	}

	@Column(name = "platenum", length = 30)
	public String getPlatenum() {
		return this.platenum;
	}

	public void setPlatenum(String platenum) {
		this.platenum = platenum;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "buytime", length = 10)
	public Date getBuytime() {
		return this.buytime;
	}

	public void setBuytime(Date buytime) {
		this.buytime = buytime;
	}

	@Column(name = "mileage", precision = 9)
	public BigDecimal getMileage() {
		return this.mileage;
	}

	public void setMileage(BigDecimal mileage) {
		this.mileage = mileage;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "nextmain", length = 19)
	public Date getNextmain() {
		return this.nextmain;
	}

	public void setNextmain(Date nextmain) {
		this.nextmain = nextmain;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "nextexam", length = 19)
	public Date getNextexam() {
		return this.nextexam;
	}

	public void setNextexam(Date nextexam) {
		this.nextexam = nextexam;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "nextins", length = 19)
	public Date getNextins() {
		return this.nextins;
	}

	public void setNextins(Date nextins) {
		this.nextins = nextins;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "nextvis", length = 19)
	public Date getNextvis() {
		return this.nextvis;
	}

	public void setNextvis(Date nextvis) {
		this.nextvis = nextvis;
	}

	@Column(name = "inscompany", length = 80)
	public String getInscompany() {
		return this.inscompany;
	}

	public void setInscompany(String inscompany) {
		this.inscompany = inscompany;
	}

	@Column(name = "insremark", length = 100)
	public String getInsremark() {
		return this.insremark;
	}

	public void setInsremark(String insremark) {
		this.insremark = insremark;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "listingtime", nullable = true, length = 19)
	public Date getListingtime() {
		return listingtime;
	}

	public void setListingtime(Date listingtime) {
		this.listingtime = listingtime;
	}

	@Column(name = "remark", length = 50)
	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Column(name = "color", length = 20)
	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	@Column(name = "del")
	public Boolean getDel() {
		return this.del;
	}

	public void setDel(Boolean del) {
		this.del = del;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "infoCar")
	public Set<CarCustmerMapping> getCarCustmerMappings() {
		return this.carCustmerMappings;
	}

	public void setCarCustmerMappings(Set<CarCustmerMapping> carCustmerMappings) {
		this.carCustmerMappings = carCustmerMappings;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "infoCar")
	public Set<CustomerAppoit> getCustomerAppoits() {
		return this.customerAppoits;
	}

	public void setCustomerAppoits(Set<CustomerAppoit> customerAppoits) {
		this.customerAppoits = customerAppoits;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "factoryDate")
	public Date getFactoryDate() {
		return factoryDate;
	}

	public void setFactoryDate(Date factoryDate) {
		this.factoryDate = factoryDate;
	}

	@Column(name = "brandName", length = 30)
	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	@Column(name = "isAuto")
	public Boolean getIsAuto() {
		return isAuto;
	}

	public void setIsAuto(Boolean isAuto) {
		this.isAuto = isAuto;
	}

	@Column(name = "displacement", length = 10)
	public String getDisplacement() {
		return displacement;
	}

	public void setDisplacement(String displacement) {
		this.displacement = displacement;
	}

}