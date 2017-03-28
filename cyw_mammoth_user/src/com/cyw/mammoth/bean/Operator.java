package com.cyw.mammoth.bean;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * Operator entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "operator", schema = "dbo", catalog = "mammoth")
public class Operator implements java.io.Serializable {

	// Fields

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -6778120289556868662L;
	private String operId;
	private String operName;
	private String passwd;
	private String groupId;
	private String deptId;
	private Integer rights;
	private Date lastDate;
	private String lastPass;
	private Short passLimit;
	private String updateId;
	private Date updateDate;
	private String consId;
	private String tel;
	private String mobile;
	private String post;
	private Date createDate;
	private String note;
	private Integer status;
	private String vilhotelId;
	private String chainId;
	/**
	 * 班次记录
	 */
	@Transient
	private ShiftLog shiftLog;

	// Constructors

	/** default constructor */
	public Operator() {
	}

	/** minimal constructor */
	public Operator(String operId, String operName, String passwd,
			String groupId, String deptId, Integer rights, Date lastDate,
			Short passLimit, String updateId, Date updateDate,
			Integer status) {
		this.operId = operId;
		this.operName = operName;
		this.passwd = passwd;
		this.groupId = groupId;
		this.deptId = deptId;
		this.rights = rights;
		this.lastDate = lastDate;
		this.passLimit = passLimit;
		this.updateId = updateId;
		this.updateDate = updateDate;
		this.status = status;
	}

	/** full constructor */
	public Operator(String operId, String operName, String passwd,
			String groupId, String deptId, Integer rights, Date lastDate,
			String lastPass, Short passLimit, String updateId,
			Date updateDate, String consId, Integer status,
			String vilhotelId, String chainId) {
		this.operId = operId;
		this.operName = operName;
		this.passwd = passwd;
		this.groupId = groupId;
		this.deptId = deptId;
		this.rights = rights;
		this.lastDate = lastDate;
		this.lastPass = lastPass;
		this.passLimit = passLimit;
		this.updateId = updateId;
		this.updateDate = updateDate;
		this.consId = consId;
		this.status = status;
		this.vilhotelId = vilhotelId;
		this.chainId = chainId;
	}

	// Property accessors
	@Id
	@Column(name = "oper_id", unique = true, nullable = false, length = 6)
	public String getOperId() {
		return this.operId;
	}

	public void setOperId(String operId) {
		this.operId = operId;
	}

	@Column(name = "oper_name", nullable = false, length = 50)
	public String getOperName() {
		return this.operName;
	}

	public void setOperName(String operName) {
		this.operName = operName;
	}

	@Column(name = "passwd", nullable = false, length = 10)
	public String getPasswd() {
		return this.passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

	@Column(name = "group_id", nullable = false, length = 3)
	public String getGroupId() {
		return this.groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	@Column(name = "dept_id", length = 3)
	public String getDeptId() {
		return this.deptId;
	}

	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}

	@Column(name = "rights")
	public Integer getRights() {
		return this.rights;
	}

	public void setRights(Integer rights) {
		this.rights = rights;
	}

	@Column(name = "last_date", length = 16)
	public Date getLastDate() {
		return this.lastDate;
	}

	public void setLastDate(Date lastDate) {
		this.lastDate = lastDate;
	}

	@Column(name = "last_pass", length = 8)
	public String getLastPass() {
		return this.lastPass;
	}

	public void setLastPass(String lastPass) {
		this.lastPass = lastPass;
	}

	@Column(name = "pass_limit")
	public Short getPassLimit() {
		return this.passLimit;
	}

	public void setPassLimit(Short passLimit) {
		this.passLimit = passLimit;
	}

	@Column(name = "update_id", length = 6)
	public String getUpdateId() {
		return this.updateId;
	}

	public void setUpdateId(String updateId) {
		this.updateId = updateId;
	}

	@Column(name = "update_date", length = 16)
	public Date getUpdateDate() {
		return this.updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	@Column(name = "cons_id", length = 4)
	public String getConsId() {
		return this.consId;
	}

	public void setConsId(String consId) {
		this.consId = consId;
	}

	@Column(name = "status", nullable = false)
	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	@Column(name = "vilhotel_id", length = 50)
	public String getVilhotelId() {
		return this.vilhotelId;
	}

	public void setVilhotelId(String vilhotelId) {
		this.vilhotelId = vilhotelId;
	}

	@Column(name = "chain_id", length = 50)
	public String getChainId() {
		return this.chainId;
	}

	public void setChainId(String chainId) {
		this.chainId = chainId;
	}
	@Transient
	public ShiftLog getShiftLog() {
		return shiftLog;
	}
	
	public void setShiftLog(ShiftLog shiftLog) {
		this.shiftLog = shiftLog;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this) ;
	}
    @Column(name="tel",length=20)
	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}
	@Column(name="mobile",length=20)
	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	@Column(name="create_date")
	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	@Column(name="note")
	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}
	@Column(name="post")
	public String getPost() {
		return post;
	}

	public void setPost(String post) {
		this.post = post;
	}


}