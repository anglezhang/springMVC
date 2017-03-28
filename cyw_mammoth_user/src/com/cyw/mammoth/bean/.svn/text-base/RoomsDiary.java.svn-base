package com.cyw.mammoth.bean;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * RoomsDiary entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "rooms_diary", schema = "dbo", catalog = "mammoth")
public class RoomsDiary implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer roomChkid;
	private Date hotelDate;
	private String rmtypeId;
	private String roomStat;
	private Integer roomNum;
	private Integer flag;
	private String vilhotelId;
	private String chainId;

	// Constructors

	/** default constructor */
	public RoomsDiary() {
	}

	/** minimal constructor */
	public RoomsDiary(Integer id, Integer roomChkid, Date hotelDate,
			String rmtypeId, String roomStat, Integer flag) {
		this.id = id;
		this.roomChkid = roomChkid;
		this.hotelDate = hotelDate;
		this.rmtypeId = rmtypeId;
		this.roomStat = roomStat;
		this.flag = flag;
	}

	/** full constructor */
	public RoomsDiary(Integer id, Integer roomChkid, Date hotelDate,
			String rmtypeId, String roomStat, Integer roomNum, Integer flag,
			String vilhotelId, String chainId) {
		this.id = id;
		this.roomChkid = roomChkid;
		this.hotelDate = hotelDate;
		this.rmtypeId = rmtypeId;
		this.roomStat = roomStat;
		this.roomNum = roomNum;
		this.flag = flag;
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

	@Column(name = "room_chkid", nullable = false)
	public Integer getRoomChkid() {
		return this.roomChkid;
	}

	public void setRoomChkid(Integer roomChkid) {
		this.roomChkid = roomChkid;
	}

	@Column(name = "hotel_date", nullable = false, length = 16)
	public Date getHotelDate() {
		return this.hotelDate;
	}

	public void setHotelDate(Date hotelDate) {
		this.hotelDate = hotelDate;
	}

	@Column(name = "rmtype_id", nullable = false, length = 3)
	public String getRmtypeId() {
		return this.rmtypeId;
	}

	public void setRmtypeId(String rmtypeId) {
		this.rmtypeId = rmtypeId;
	}

	@Column(name = "room_stat", nullable = false, length = 3)
	public String getRoomStat() {
		return this.roomStat;
	}

	public void setRoomStat(String roomStat) {
		this.roomStat = roomStat;
	}

	@Column(name = "room_num")
	public Integer getRoomNum() {
		return this.roomNum;
	}

	public void setRoomNum(Integer roomNum) {
		this.roomNum = roomNum;
	}

	@Column(name = "flag", nullable = false)
	public Integer getFlag() {
		return this.flag;
	}

	public void setFlag(Integer flag) {
		this.flag = flag;
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