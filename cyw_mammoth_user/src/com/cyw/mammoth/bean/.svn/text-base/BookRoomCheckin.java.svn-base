package com.cyw.mammoth.bean;

import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * BookRoomCheckin entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "book_room_checkin", schema = "dbo", catalog = "mammoth")
public class BookRoomCheckin implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer roomLinkId;
	private String roomId;
	private String flag;
	private Integer status;
	private String operId;
	private Date operTime;

	// Constructors

	/** default constructor */
	public BookRoomCheckin() {
	}

	/** full constructor */
	public BookRoomCheckin(Integer id, Integer roomLinkId, String roomId,
			String flag, Integer status, String operId, Timestamp operTime) {
		this.id = id;
		this.roomLinkId = roomLinkId;
		this.roomId = roomId;
		this.flag = flag;
		this.status = status;
		this.operId = operId;
		this.operTime = operTime;
	}

	// Property accessors
	@Id
	@GeneratedValue
	@Column(name = "ID", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "room_link_id", nullable = false)
	public Integer getRoomLinkId() {
		return this.roomLinkId;
	}

	public void setRoomLinkId(Integer roomLinkId) {
		this.roomLinkId = roomLinkId;
	}

	@Column(name = "room_id", nullable = false, length = 6)
	public String getRoomId() {
		return this.roomId;
	}

	public void setRoomId(String roomId) {
		this.roomId = roomId;
	}

	@Column(name = "flag", nullable = false, length = 1)
	public String getFlag() {
		return this.flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	@Column(name = "status", nullable = false)
	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	@Column(name = "oper_id", nullable = false, length = 10)
	public String getOperId() {
		return this.operId;
	}

	public void setOperId(String operId) {
		this.operId = operId;
	}

	@Column(name = "oper_time", nullable = false, length = 23)
	public Date getOperTime() {
		return this.operTime;
	}

	public void setOperTime(Date operTime) {
		this.operTime = operTime;
	}

}