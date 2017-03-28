package com.cyw.mammoth.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * HroomTypeImg entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "hroom_type_img", schema = "dbo", catalog = "mammoth")
public class HroomTypeImg implements java.io.Serializable {

	// Fields

	private Integer id;
	private String rmtypeId;
	private String rmtypeImg;
	private String memo;
	private Integer status;
	private String vilhotelId;
	private String chainId;

	// Constructors

	/** default constructor */
	public HroomTypeImg() {
	}

	/** minimal constructor */
	public HroomTypeImg(Integer id, String rmtypeId, Integer status) {
		this.id = id;
		this.rmtypeId = rmtypeId;
		this.status = status;
	}

	/** full constructor */
	public HroomTypeImg(Integer id, String rmtypeId, String rmtypeImg,
			String memo, Integer status, String vilhotelId, String chainId) {
		this.id = id;
		this.rmtypeId = rmtypeId;
		this.rmtypeImg = rmtypeImg;
		this.memo = memo;
		this.status = status;
		this.vilhotelId = vilhotelId;
		this.chainId = chainId;
	}

	// Property accessors
	@Id
	@Column(name = "ID", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "rmtype_id", nullable = false, length = 3)
	public String getRmtypeId() {
		return this.rmtypeId;
	}

	public void setRmtypeId(String rmtypeId) {
		this.rmtypeId = rmtypeId;
	}

	@Column(name = "rmtype_img")
	public String getRmtypeImg() {
		return this.rmtypeImg;
	}

	public void setRmtypeImg(String rmtypeImg) {
		this.rmtypeImg = rmtypeImg;
	}

	@Column(name = "memo")
	public String getMemo() {
		return this.memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
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

}