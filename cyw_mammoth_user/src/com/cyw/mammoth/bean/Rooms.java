package com.cyw.mammoth.bean;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * Rooms entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "rooms", schema = "dbo", catalog = "mammoth")
public class Rooms implements java.io.Serializable {

	private static final long serialVersionUID = 7980815253082555154L;
	
	// Fields

	private String roomId;
	private String roomType;
	private String buildId;
	private String floorNo;
	private Long roomCharacter;
	private String currStat;
	private String modiStat;
	private Date modiTime;
	private String modiOperid;
	private Integer status;
	private String description;
	private String vilhotelId;
	private String chainId;
	private String roomTypeName;//类型名称
	private String buildName;//建筑物名称

	// Constructors

	/** default constructor */
	public Rooms() {
	}

	/** minimal constructor */
	public Rooms(String roomId, String roomType, String buildId,
			String floorNo, Long roomCharacter, String currStat,
			Date modiTime, String modiOperid, Integer status) {
		this.roomId = roomId;
		this.roomType = roomType;
		this.buildId = buildId;
		this.floorNo = floorNo;
		this.roomCharacter = roomCharacter;
		this.currStat = currStat;
		this.modiTime = modiTime;
		this.modiOperid = modiOperid;
		this.status = status;
	}

	/** full constructor */
	public Rooms(String roomId, String roomType, String buildId,
			String floorNo, Long roomCharacter, String currStat,
			String modiStat, Date modiTime, String modiOperid,
			Integer status, String description, String vilhotelId,
			String chainId) {
		this.roomId = roomId;
		this.roomType = roomType;
		this.buildId = buildId;
		this.floorNo = floorNo;
		this.roomCharacter = roomCharacter;
		this.currStat = currStat;
		this.modiStat = modiStat;
		this.modiTime = modiTime;
		this.modiOperid = modiOperid;
		this.status = status;
		this.description = description;
		this.vilhotelId = vilhotelId;
		this.chainId = chainId;
	}

	// Property accessors
	@Id
	@Column(name = "room_id", unique = true, nullable = false, length = 6)
	public String getRoomId() {
		return this.roomId;
	}

	public void setRoomId(String roomId) {
		this.roomId = roomId;
	}

	@Column(name = "room_type", nullable = false, length = 4)
	public String getRoomType() {
		return this.roomType;
	}

	public void setRoomType(String roomType) {
		this.roomType = roomType;
	}

	@Column(name = "build_id", nullable = false, length = 3)
	public String getBuildId() {
		return this.buildId;
	}

	public void setBuildId(String buildId) {
		this.buildId = buildId;
	}

	@Column(name = "floor_no", nullable = false, length = 3)
	public String getFloorNo() {
		return this.floorNo;
	}

	public void setFloorNo(String floorNo) {
		this.floorNo = floorNo;
	}

	@Column(name = "room_character", nullable = false)
	public Long getRoomCharacter() {
		return this.roomCharacter;
	}

	public void setRoomCharacter(Long roomCharacter) {
		this.roomCharacter = roomCharacter;
	}

	@Column(name = "curr_stat", nullable = false, length = 3)
	public String getCurrStat() {
		return this.currStat;
	}

	public void setCurrStat(String currStat) {
		this.currStat = currStat;
	}

	@Column(name = "modi_stat", length = 3)
	public String getModiStat() {
		return this.modiStat;
	}

	public void setModiStat(String modiStat) {
		this.modiStat = modiStat;
	}

	@Column(name = "modi_time", nullable = false, length = 16)
	public Date getModiTime() {
		return this.modiTime;
	}

	public void setModiTime(Date modiTime) {
		this.modiTime = modiTime;
	}

	@Column(name = "modi_operid", nullable = false, length = 10)
	public String getModiOperid() {
		return this.modiOperid;
	}

	public void setModiOperid(String modiOperid) {
		this.modiOperid = modiOperid;
	}

	@Column(name = "status", nullable = false)
	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	@Column(name = "description")
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
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

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
	
	@Transient
	public String getRoomTypeName() {
		return roomTypeName;
	}
	
	public void setRoomTypeName(String roomTypeName) {
		this.roomTypeName = roomTypeName;
	}

	@Transient
	public String getBuildName() {
		return buildName;
	}

	public void setBuildName(String buildName) {
		this.buildName = buildName;
	}
	
	

}