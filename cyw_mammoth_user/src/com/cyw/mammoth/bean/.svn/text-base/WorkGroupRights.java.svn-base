package com.cyw.mammoth.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * WorkGroupRights entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "work_group_rights", schema = "dbo", catalog = "mammoth")
public class WorkGroupRights implements java.io.Serializable {

	// Fields

	private String wkgrpId;
	private String FId;
	private Integer rights;
	private String vilhotelId;
	private String chainId;

	// Constructors

	/** default constructor */
	public WorkGroupRights() {
	}

	/** minimal constructor */
	public WorkGroupRights(String FId, Integer rights) {
		this.FId = FId;
		this.rights = rights;
	}

	/** full constructor */
	public WorkGroupRights(String id, Integer rights,
			String vilhotelId, String chainId) {
		this.FId = FId;
		this.rights = rights;
		this.vilhotelId = vilhotelId;
		this.chainId = chainId;
	}
	@Id
	@Column(name = "f_id", nullable = false, length = 10)
	public String getFId() {
		return FId;
	}

	public void setFId(String fId) {
		FId = fId;
	}
	@Column(name = "wkgrp_id", nullable = false, length = 10)
	public String getWkgrpId() {
		return wkgrpId;
	}

	public void setWkgrpId(String wkgrpId) {
		this.wkgrpId = wkgrpId;
	}
	

	@Column(name = "rights", nullable = false)
	public Integer getRights() {
		return this.rights;
	}

	public void setRights(Integer rights) {
		this.rights = rights;
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