package com.cyw.mammoth.bean;

import java.util.Date;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * RmtypeNum entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "rmtype_num", schema = "dbo", catalog = "mammoth")
public class RmtypeNum implements java.io.Serializable {

	// Fields
	private String roomtypeId;
	private Date bookDate;
	private Short bookNum;
	private String vilhotelId;
	private String chainId;

	// Constructors

	/** default constructor */
	public RmtypeNum() {
	}

	/** minimal constructor */
	public RmtypeNum(String roomtypeId, Short bookNum) {
		this.roomtypeId = roomtypeId;
		this.bookNum = bookNum;
	}

	/** full constructor */
	public RmtypeNum(String roomtypeId, Short bookNum, String vilhotelId,
			String chainId) {
		this.roomtypeId = roomtypeId;
		this.bookNum = bookNum;
		this.vilhotelId = vilhotelId;
		this.chainId = chainId;
	}

	@Column(name = "book_date", nullable = false, length = 16)
	public Date getBookDate() {
		return bookDate;
	}

	public void setBookDate(Date bookDate) {
		this.bookDate = bookDate;
	}
	@Id
	@Column(name = "roomtype_id", nullable = false, length = 3)
	public String getRoomtypeId() {
		return roomtypeId;
	}

	public void setRoomtypeId(String roomtypeId) {
		this.roomtypeId = roomtypeId;
	}

	@Column(name = "book_num", nullable = false)
	public Short getBookNum() {
		return this.bookNum;
	}

	public void setBookNum(Short bookNum) {
		this.bookNum = bookNum;
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