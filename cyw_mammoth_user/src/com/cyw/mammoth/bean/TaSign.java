package com.cyw.mammoth.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * TaSign entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "ta_sign", schema = "dbo", catalog = "mammoth")
public class TaSign implements java.io.Serializable {

	// Fields

	private Integer id;
	private String taType;
	private String compId;
	private String namec;
	private String signImg;
	private String status;
	private String vilhotelId;
	private String chainId;

	// Constructors

	/** default constructor */
	public TaSign() {
	}

	/** minimal constructor */
	public TaSign(Integer id, String taType, String compId, String status) {
		this.id = id;
		this.taType = taType;
		this.compId = compId;
		this.status = status;
	}

	/** full constructor */
	public TaSign(Integer id, String taType, String compId, String namec,
			String signImg, String status, String vilhotelId, String chainId) {
		this.id = id;
		this.taType = taType;
		this.compId = compId;
		this.namec = namec;
		this.signImg = signImg;
		this.status = status;
		this.vilhotelId = vilhotelId;
		this.chainId = chainId;
	}

	// Property accessors
	@Id
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "TA_type", nullable = false, length = 1)
	public String getTaType() {
		return this.taType;
	}

	public void setTaType(String taType) {
		this.taType = taType;
	}

	@Column(name = "comp_id", nullable = false, length = 60)
	public String getCompId() {
		return this.compId;
	}

	public void setCompId(String compId) {
		this.compId = compId;
	}

	@Column(name = "namec", length = 30)
	public String getNamec() {
		return this.namec;
	}

	public void setNamec(String namec) {
		this.namec = namec;
	}

	@Column(name = "sign_img")
	public String getSignImg() {
		return this.signImg;
	}

	public void setSignImg(String signImg) {
		this.signImg = signImg;
	}

	@Column(name = "status", nullable = false, length = 1)
	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
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

}