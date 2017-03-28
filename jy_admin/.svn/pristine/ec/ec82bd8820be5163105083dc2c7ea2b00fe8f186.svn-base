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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import com.zoomoor.admin.entity.SysUser;

/**
 * InfoEmp entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "info_emp" )
public class InfoEmp implements java.io.Serializable {

	// Fields

	private Integer empId;
	private InfoDept infoDept;
	private ParamConfig paramConfig;
	private String empName;
	private String empCode;
	private Boolean gender;
	private String edu;
	private String email;
	private Date birthday;
	private String idCard;
	private String nativePlace;
	private String nation;
	private String addsHome;
	private String mobile;
	private Boolean ismarry;
	private String address;
	private String attn;
	private String attnTel;
	private Date lcBDate;
	private Date lcEDate;
	private Date inDate;
	private Date outDate;
	private Integer drive;
	private String url;
	private Date formalDate;
	private Integer holidayNum;
	private String recruitSource;
	private Integer status;
	private String interest;
	private Date startJob;
	private String school;
	private Integer age;
	private Boolean del;
	private Set<SysUser> sysUsers = new HashSet<SysUser>(0);
	private Set<ConfigEmpMapping> configEmpMappings = new HashSet<ConfigEmpMapping>(
			0);
	private Set<OrderPurchase> orderPurchases = new HashSet<OrderPurchase>(0);
	private Date createtime;
	private ParamConfig drivinglicense;
	private String position;
	private ConfigEmpMapping[] professConfigs;
	// Constructors

	/** default constructor */
	public InfoEmp() {
	}

	/** full constructor */
	public InfoEmp(InfoDept infoDept, ParamConfig paramConfig, String empName,
			String empCode, Boolean gender, String edu, String email,
			Date birthday, String idCard, String nativePlace,
			String nation, String addsHome, String mobile, Boolean ismarry,
			String address, String attn, String attnTel, Date lcBDate,
			Date lcEDate, Date inDate, Date outDate,
			Integer drive, String url, Date formalDate,
			Integer holidayNum, String recruitSource, Integer status,
			String interest, Date startJob, String school, Boolean del,
			Set<SysUser> sysUsers,
			Set<ConfigEmpMapping> configEmpMappings,
			Set<OrderPurchase> orderPurchases) {
		this.infoDept = infoDept;
		this.paramConfig = paramConfig;
		this.empName = empName;
		this.empCode = empCode;
		this.gender = gender;
		this.edu = edu;
		this.email = email;
		this.birthday = birthday;
		this.idCard = idCard;
		this.nativePlace = nativePlace;
		this.nation = nation;
		this.addsHome = addsHome;
		this.mobile = mobile;
		this.ismarry = ismarry;
		this.address = address;
		this.attn = attn;
		this.attnTel = attnTel;
		this.lcBDate = lcBDate;
		this.lcEDate = lcEDate;
		this.inDate = inDate;
		this.outDate = outDate;
		this.drive = drive;
		this.url = url;
		this.formalDate = formalDate;
		this.holidayNum = holidayNum;
		this.recruitSource = recruitSource;
		this.status = status;
		this.interest = interest;
		this.startJob = startJob;
		this.school = school;
		this.del = del;
		this.sysUsers = sysUsers;
		this.configEmpMappings = configEmpMappings;
		this.orderPurchases = orderPurchases;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "emp_id", unique = true, nullable = false)
	public Integer getEmpId() {
		return this.empId;
	}

	public void setEmpId(Integer empId) {
		this.empId = empId;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "dept_id")
	public InfoDept getInfoDept() {
		return this.infoDept;
	}

	public void setInfoDept(InfoDept infoDept) {
		this.infoDept = infoDept;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "unit_id")
	public ParamConfig getParamConfig() {
		return this.paramConfig;
	}

	public void setParamConfig(ParamConfig paramConfig) {
		this.paramConfig = paramConfig;
	}

	@Column(name = "emp_name", length = 30)
	public String getEmpName() {
		return this.empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	@Column(name = "emp_code", length = 30)
	public String getEmpCode() {
		return this.empCode;
	}

	public void setEmpCode(String empCode) {
		this.empCode = empCode;
	}

	@Column(name = "gender")
	public Boolean getGender() {
		return this.gender;
	}

	public void setGender(Boolean gender) {
		this.gender = gender;
	}

	@Column(name = "edu", length = 30)
	public String getEdu() {
		return this.edu;
	}

	public void setEdu(String edu) {
		this.edu = edu;
	}

	@Column(name = "email", length = 50)
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "birthday", length = 10)
	public Date getBirthday() {
		return this.birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	@Column(name = "id_card", length = 20)
	public String getIdCard() {
		return this.idCard;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

	@Column(name = "native_place", length = 20)
	public String getNativePlace() {
		return this.nativePlace;
	}

	public void setNativePlace(String nativePlace) {
		this.nativePlace = nativePlace;
	}

	@Column(name = "nation", length = 10)
	public String getNation() {
		return this.nation;
	}

	public void setNation(String nation) {
		this.nation = nation;
	}

	@Column(name = "adds_home", length = 200)
	public String getAddsHome() {
		return this.addsHome;
	}

	public void setAddsHome(String addsHome) {
		this.addsHome = addsHome;
	}

	@Column(name = "mobile", length = 20)
	public String getMobile() {
		return this.mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	@Column(name = "ismarry")
	public Boolean getIsmarry() {
		return this.ismarry;
	}

	public void setIsmarry(Boolean ismarry) {
		this.ismarry = ismarry;
	}

	@Column(name = "address", length = 100)
	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Column(name = "attn", length = 30)
	public String getAttn() {
		return this.attn;
	}

	public void setAttn(String attn) {
		this.attn = attn;
	}

	@Column(name = "attn_tel", length = 50)
	public String getAttnTel() {
		return this.attnTel;
	}

	public void setAttnTel(String attnTel) {
		this.attnTel = attnTel;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "LC_b_date", length = 10)
	public Date getLcBDate() {
		return this.lcBDate;
	}

	public void setLcBDate(Date lcBDate) {
		this.lcBDate = lcBDate;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "LC_e_date", length = 10)
	public Date getLcEDate() {
		return this.lcEDate;
	}

	public void setLcEDate(Date lcEDate) {
		this.lcEDate = lcEDate;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "in_date", length = 10)
	public Date getInDate() {
		return this.inDate;
	}

	public void setInDate(Date inDate) {
		this.inDate = inDate;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "out_date", length = 10)
	public Date getOutDate() {
		return this.outDate;
	}

	public void setOutDate(Date outDate) {
		this.outDate = outDate;
	}

	@Column(name = "drive")
	public Integer getDrive() {
		return this.drive;
	}

	public void setDrive(Integer drive) {
		this.drive = drive;
	}

	@Column(name = "url")
	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "formal_date", length = 10)
	public Date getFormalDate() {
		return this.formalDate;
	}

	public void setFormalDate(Date formalDate) {
		this.formalDate = formalDate;
	}

	@Column(name = "holiday_num")
	public Integer getHolidayNum() {
		return this.holidayNum;
	}

	public void setHolidayNum(Integer holidayNum) {
		this.holidayNum = holidayNum;
	}

	@Column(name = "recruit_source", length = 30)
	public String getRecruitSource() {
		return this.recruitSource;
	}

	public void setRecruitSource(String recruitSource) {
		this.recruitSource = recruitSource;
	}

	@Column(name = "status")
	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	@Column(name = "interest", length = 100)
	public String getInterest() {
		return this.interest;
	}

	public void setInterest(String interest) {
		this.interest = interest;
	}

	@Column(name = "start_job", length = 19)
	public Date getStartJob() {
		return this.startJob;
	}

	public void setStartJob(Date startJob) {
		this.startJob = startJob;
	}

	@Column(name = "school", length = 50)
	public String getSchool() {
		return this.school;
	}

	public void setSchool(String school) {
		this.school = school;
	}

	@Column(name = "del")
	public Boolean getDel() {
		return this.del;
	}

	public void setDel(Boolean del) {
		this.del = del;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "infoEmp")
	public Set<SysUser> getSysUsers() {
		return this.sysUsers;
	}

	public void setSysUsers(Set<SysUser> sysUsers) {
		this.sysUsers = sysUsers;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "infoEmp")
	public Set<ConfigEmpMapping> getConfigEmpMappings() {
		return this.configEmpMappings;
	}

	public void setConfigEmpMappings(Set<ConfigEmpMapping> configEmpMappings) {
		this.configEmpMappings = configEmpMappings;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "infoEmp")
	public Set<OrderPurchase> getOrderPurchases() {
		return this.orderPurchases;
	}

	public void setOrderPurchases(Set<OrderPurchase> orderPurchases) {
		this.orderPurchases = orderPurchases;
	}

	@Column(name = "createtime", length = 19)
	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) { 
		this.createtime = createtime;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "drivinglicense")
	public ParamConfig getDrivinglicense() {
		return drivinglicense;
	}

	public void setDrivinglicense(ParamConfig drivinglicense) {
		this.drivinglicense = drivinglicense;
	}

	@Column(name = "position", length = 20)
	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	@Column(name = "age")
	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	@Transient
	public ConfigEmpMapping[] getProfessConfigs() {
		return professConfigs;
	}

	public void setProfessConfigs(ConfigEmpMapping[] professConfigs) {
		this.professConfigs = professConfigs;
	}

	
	
}