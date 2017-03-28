package com.zoomoor.jy.entity;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * InfoDeptSub entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "info_dept_sub")
public class InfoDeptSub implements java.io.Serializable {

	// Fields

	private Integer deptChildId;
	private Integer countDay;
	private Double alarmUpperLimit;
	private Double alarmLowerLimit;
	private String province;
	private String city;
	private String area;
	private String address;
	private String position;
	private Double acreage;
	private String memo;
	private Integer stationNum;
	private String phone;
	private String linkman;
	private String email;
	private String deptNo;

	// Constructors

	/** default constructor */
	public InfoDeptSub() {
	}

	/** full constructor */
	public InfoDeptSub( Integer countDay,
			Double alarmUpperLimit, Double alarmLowerLimit, String province,
			String city, String area, String address, String position,
			 Double acreage, String memo, Integer stationNum,
			String phone, String linkman, String email,String deptNo) {
		this.countDay = countDay;
		this.alarmUpperLimit = alarmUpperLimit;
		this.alarmLowerLimit = alarmLowerLimit;
		this.province = province;
		this.city = city;
		this.area = area;
		this.address = address;
		this.position = position;
		this.acreage = acreage;
		this.memo = memo;
		this.stationNum = stationNum;
		this.phone = phone;
		this.linkman = linkman;
		this.email = email;
		this.deptNo=deptNo;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "dept_child_id", unique = true, nullable = false)
	public Integer getDeptChildId() {
		return this.deptChildId;
	}

	public void setDeptChildId(Integer deptChildId) {
		this.deptChildId = deptChildId;
	}
	@Column(name = "count_day")
	public Integer getCountDay() {
		return this.countDay;
	}

	public void setCountDay(Integer countDay) {
		this.countDay = countDay;
	}

	@Column(name = "alarm_upper_limit", precision = 9)
	public Double getAlarmUpperLimit() {
		return this.alarmUpperLimit;
	}

	public void setAlarmUpperLimit(Double alarmUpperLimit) {
		this.alarmUpperLimit = alarmUpperLimit;
	}

	@Column(name = "alarm_lower_limit", precision = 9)
	public Double getAlarmLowerLimit() {
		return this.alarmLowerLimit;
	}

	public void setAlarmLowerLimit(Double alarmLowerLimit) {
		this.alarmLowerLimit = alarmLowerLimit;
	}

	@Column(name = "province", length = 30)
	public String getProvince() {
		return this.province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	@Column(name = "city", length = 30)
	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	@Column(name = "area", length = 30)
	public String getArea() {
		return this.area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	@Column(name = "address", length = 100)
	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Column(name = "position", length = 200)
	public String getPosition() {
		return this.position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	@Column(name = "acreage", precision = 9)
	public Double getAcreage() {
		return this.acreage;
	}

	public void setAcreage(Double acreage) {
		this.acreage = acreage;
	}

	@Column(name = "memo", length = 500)
	public String getMemo() {
		return this.memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	@Column(name = "station_num")
	public Integer getStationNum() {
		return this.stationNum;
	}

	public void setStationNum(Integer stationNum) {
		this.stationNum = stationNum;
	}

	@Column(name = "phone", length = 50)
	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Column(name = "linkman", length = 30)
	public String getLinkman() {
		return this.linkman;
	}

	public void setLinkman(String linkman) {
		this.linkman = linkman;
	}

	@Column(name = "email", length = 100)
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	@Column(name = "dept_no", length = 30)
	public String getDeptNo() {
		return this.deptNo;
	}

	public void setDeptNo(String deptNo) {
		this.deptNo = deptNo;
	}
}