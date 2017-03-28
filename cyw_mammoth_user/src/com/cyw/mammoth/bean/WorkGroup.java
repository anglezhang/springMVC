package com.cyw.mammoth.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * WorkGroup entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "work_group", schema = "dbo", catalog = "mammoth")
public class WorkGroup implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 7392785884754483678L;
	private String groupId;
	private String groupName;
	private Integer status;
	private String memo;
	private String vilhotelId;
	private String chainId;

	// Constructors

	/** default constructor */
	public WorkGroup() {
	}

	/** minimal constructor */
	public WorkGroup(String groupId, String groupName, Integer status) {
		this.groupId = groupId;
		this.groupName = groupName;
		this.status = status;
	}

	/** full constructor */
	public WorkGroup(String groupId, String groupName, Integer status,
			String memo, String vilhotelId, String chainId) {
		this.groupId = groupId;
		this.groupName = groupName;
		this.status = status;
		this.memo = memo;
		this.vilhotelId = vilhotelId;
		this.chainId = chainId;
	}

	// Property accessors
	@Id
	@Column(name = "group_id", unique = true, nullable = false, length = 3)
	public String getGroupId() {
		return this.groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	@Column(name = "group_name", nullable = false, length = 30)
	public String getGroupName() {
		return this.groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	@Column(name = "status", nullable = false)
	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	@Column(name = "memo")
	public String getMemo() {
		return this.memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
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

}