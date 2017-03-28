package com.cyw.mammoth.bean;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * BookRoomDiary entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "book_room_diary", schema = "dbo", catalog = "mammoth")
public class BookRoomDiary implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer bookRoomId;
	private Integer checkId;
	private Date hotelDate;
	private String roomtypeId;
	private Integer bookNum;
	private Integer saveNum;
	private Integer reachNum;
	private Integer status;
	private Integer cancelNum;
	private Integer noShowNum;
	private String vilhotelId;
	private String chainId;

	// Constructors

	/** default constructor */
	public BookRoomDiary() {
	}

	/** minimal constructor */
	public BookRoomDiary(Integer id, Integer bookRoomId, Integer checkId,
			Date hotelDate, String roomtypeId, Integer bookNum,
			Integer saveNum, Integer reachNum, Integer status,
			Integer cancelNum, Integer noShowNum) {
		this.id = id;
		this.bookRoomId = bookRoomId;
		this.checkId = checkId;
		this.hotelDate = hotelDate;
		this.roomtypeId = roomtypeId;
		this.bookNum = bookNum;
		this.saveNum = saveNum;
		this.reachNum = reachNum;
		this.status = status;
		this.cancelNum = cancelNum;
		this.noShowNum = noShowNum;
	}

	/** full constructor */
	public BookRoomDiary(Integer id, Integer bookRoomId, Integer checkId,
			Date hotelDate, String roomtypeId, Integer bookNum,
			Integer saveNum, Integer reachNum, Integer status,
			Integer cancelNum, Integer noShowNum, String vilhotelId,
			String chainId) {
		this.id = id;
		this.bookRoomId = bookRoomId;
		this.checkId = checkId;
		this.hotelDate = hotelDate;
		this.roomtypeId = roomtypeId;
		this.bookNum = bookNum;
		this.saveNum = saveNum;
		this.reachNum = reachNum;
		this.status = status;
		this.cancelNum = cancelNum;
		this.noShowNum = noShowNum;
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

	@Column(name = "book_room_id", nullable = false)
	public Integer getBookRoomId() {
		return this.bookRoomId;
	}

	public void setBookRoomId(Integer bookRoomId) {
		this.bookRoomId = bookRoomId;
	}

	@Column(name = "check_id", nullable = false)
	public Integer getCheckId() {
		return this.checkId;
	}

	public void setCheckId(Integer checkId) {
		this.checkId = checkId;
	}

	@Column(name = "Hotel_Date", nullable = false, length = 16)
	public Date getHotelDate() {
		return this.hotelDate;
	}

	public void setHotelDate(Date hotelDate) {
		this.hotelDate = hotelDate;
	}

	@Column(name = "roomtype_id", nullable = false, length = 3)
	public String getRoomtypeId() {
		return this.roomtypeId;
	}

	public void setRoomtypeId(String roomtypeId) {
		this.roomtypeId = roomtypeId;
	}

	@Column(name = "book_num", nullable = false)
	public Integer getBookNum() {
		return this.bookNum;
	}

	public void setBookNum(Integer bookNum) {
		this.bookNum = bookNum;
	}

	@Column(name = "save_num", nullable = false)
	public Integer getSaveNum() {
		return this.saveNum;
	}

	public void setSaveNum(Integer saveNum) {
		this.saveNum = saveNum;
	}

	@Column(name = "reach_num", nullable = false)
	public Integer getReachNum() {
		return this.reachNum;
	}

	public void setReachNum(Integer reachNum) {
		this.reachNum = reachNum;
	}

	@Column(name = "status", nullable = false)
	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	@Column(name = "cancel_num", nullable = false)
	public Integer getCancelNum() {
		return this.cancelNum;
	}

	public void setCancelNum(Integer cancelNum) {
		this.cancelNum = cancelNum;
	}

	@Column(name = "NoShow_num", nullable = false)
	public Integer getNoShowNum() {
		return this.noShowNum;
	}

	public void setNoShowNum(Integer noShowNum) {
		this.noShowNum = noShowNum;
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