package com.cyw.mammoth.bean;

import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

/**
 * BookRoom entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "book_room", schema = "dbo", catalog = "mammoth")
public class BookRoom implements java.io.Serializable {

	// Fields

	private Integer num;
	private Integer id;
	private Integer bookRoomId;
	private Integer checkId;
	private String roomtypeId;
	private Integer bookNum;
	private Integer saveNum;
	private Integer reachNum;
	private Double roomPrice;
	private Date reachDate;
	private Date leaveDate;
	private Integer status;
	private Integer updateTimes;
	private Integer cancelNum;
	private Integer noShowNum;
	private String modifyOper;
	private Date modifyTime;
	private String bookOper;
	private Date bookTime;
	private String cancelOper;
	private Date cancelTime;
	private String vilhotelId;
	private String chainId;
	
	private String reachTime;
	// Constructors

	/** default constructor */
	public BookRoom() {
	}

	/** minimal constructor */
	public BookRoom(Integer id, Integer bookRoomId, Integer checkId,
			String roomtypeId, Integer bookNum, Integer saveNum,
			Integer reachNum, Double roomPrice, Date reachDate,
			Date leaveDate, Integer status, Integer updateTimes) {
		this.id = id;
		this.bookRoomId = bookRoomId;
		this.checkId = checkId;
		this.roomtypeId = roomtypeId;
		this.bookNum = bookNum;
		this.saveNum = saveNum;
		this.reachNum = reachNum;
		this.roomPrice = roomPrice;
		this.reachDate = reachDate;
		this.leaveDate = leaveDate;
		this.status = status;
		this.updateTimes = updateTimes;
	}

	/** full constructor */
	public BookRoom(Integer id, Integer bookRoomId, Integer checkId,
			String roomtypeId, Integer bookNum, Integer saveNum,
			Integer reachNum, Double roomPrice, Date reachDate,
			Date leaveDate, Integer status, Integer updateTimes,
			Integer cancelNum, Integer noShowNum, String vilhotelId,
			String chainId) {
		this.id = id;
		this.bookRoomId = bookRoomId;
		this.checkId = checkId;
		this.roomtypeId = roomtypeId;
		this.bookNum = bookNum;
		this.saveNum = saveNum;
		this.reachNum = reachNum;
		this.roomPrice = roomPrice;
		this.reachDate = reachDate;
		this.leaveDate = leaveDate;
		this.status = status;
		this.updateTimes = updateTimes;
		this.cancelNum = cancelNum;
		this.noShowNum = noShowNum;
		this.vilhotelId = vilhotelId;
		this.chainId = chainId;
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

	@Column(name = "room_price", nullable = false, precision = 15, scale = 0)
	public Double getRoomPrice() {
		return this.roomPrice;
	}

	public void setRoomPrice(Double roomPrice) {
		this.roomPrice = roomPrice;
	}

	@Column(name = "reach_date", nullable = false, length = 23)
	@Temporal(TemporalType.TIMESTAMP)
	public Date getReachDate() {
		return this.reachDate;
	}

	public void setReachDate(Date reachDate) {
		this.reachDate = reachDate;
	}

	@Column(name = "leave_date", nullable = false, length = 23)
	@Temporal(TemporalType.DATE)
	public Date getLeaveDate() {
		return this.leaveDate;
	}

	public void setLeaveDate(Date leaveDate) {
		this.leaveDate = leaveDate;
	}

	@Column(name = "status", nullable = false)
	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	@Column(name = "update_times", nullable = false)
	public Integer getUpdateTimes() {
		return this.updateTimes;
	}

	public void setUpdateTimes(Integer updateTimes) {
		this.updateTimes = updateTimes;
	}

	@Column(name = "cancel_num")
	public Integer getCancelNum() {
		return this.cancelNum;
	}

	public void setCancelNum(Integer cancelNum) {
		this.cancelNum = cancelNum;
	}

	@Column(name = "NoShow_num")
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

	@Transient
	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}
	@Transient
	public String getReachTime() {
		return reachTime;
	}

	public void setReachTime(String reachTime) {
		this.reachTime = reachTime;
	}
	
	//---非持久化数据
	private Set<RoomNum> roomNums;
	@OneToMany(targetEntity=RoomNum.class,cascade=CascadeType.REFRESH,mappedBy="bookRoom", fetch = FetchType.LAZY)
	public Set<RoomNum> getRoomNums() {
		return roomNums;
	}

	public void setRoomNums(Set<RoomNum> roomNums) {
		this.roomNums = roomNums;
	}
    @Column(name="modify_oper")
	public String getModifyOper() {
		return modifyOper;
	}

	public void setModifyOper(String modifyOper) {
		this.modifyOper = modifyOper;
	}
    @Column(name="modify_time")
	public Date getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}
    @Column(name="book_oper",nullable=false)
	public String getBookOper() {
		return bookOper;
	}

	public void setBookOper(String bookOper) {
		this.bookOper = bookOper;
	}
    @Column(name="book_time",nullable=false)
	public Date getBookTime() {
		return bookTime;
	}

	public void setBookTime(Date bookTime) {
		this.bookTime = bookTime;
	}
    @Column(name="cancel_oper")
	public String getCancelOper() {
		return cancelOper;
	}

	public void setCancelOper(String cancelOper) {
		this.cancelOper = cancelOper;
	}
    @Column(name="cancel_time")
	public Date getCancelTime() {
		return cancelTime;
	}

	public void setCancelTime(Date cancelTime) {
		this.cancelTime = cancelTime;
	}
	
	
	
	

}