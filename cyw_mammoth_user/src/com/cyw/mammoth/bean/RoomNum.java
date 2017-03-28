package com.cyw.mammoth.bean;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnore;

/**
 * RoomNum entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "room_num", schema = "dbo", catalog = "mammoth")
public class RoomNum implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer roomChkid;
	private Integer checkId;
	private String roomId;
	private BookRoom bookRoom;
	private Date reachDate;
	private Date leaveDate;
	private Integer flag;
	private Integer status;
	private String keepFlag;
	private String operId;
	private Date operTime;
	private String note;
	private Integer bookId;
	private Double roomPrice;
	private String vilhotelId;
	private String chainId;
	private Operator operator;
	// Constructors

	/** default constructor */
	public RoomNum() {
	}

	/** minimal constructor */
	public RoomNum(Integer id, Integer roomChkid, Integer checkId,
			String roomId, Date reachDate, Date leaveDate,
			Integer flag, Integer status) {
		this.id = id;
		this.roomChkid = roomChkid;
		this.checkId = checkId;
		this.roomId = roomId;
		this.reachDate = reachDate;
		this.leaveDate = leaveDate;
		this.flag = flag;
		this.status = status;
	}

	/** full constructor */
	public RoomNum(Integer id, Integer roomChkid, Integer checkId,
			String roomId, Date reachDate, Date leaveDate,
			Integer flag, Integer status, String keepFlag, String operId,
			Date operTime, String note, Integer bookId, Double roomPrice,
			String vilhotelId, String chainId) {
		this.id = id;
		this.roomChkid = roomChkid;
		this.checkId = checkId;
		this.roomId = roomId;
		this.reachDate = reachDate;
		this.leaveDate = leaveDate;
		this.flag = flag;
		this.status = status;
		this.keepFlag = keepFlag;
		this.operId = operId;
		this.operTime = operTime;
		this.note = note;
		this.bookId = bookId;
		this.roomPrice = roomPrice;
		this.vilhotelId = vilhotelId;
		this.chainId = chainId;
	}

	// Property accessors
	@Id
	@Column(name = "ID", unique = true, nullable = false)
	@GeneratedValue
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

	@Column(name = "check_id", nullable = false)
	public Integer getCheckId() {
		return this.checkId;
	}

	public void setCheckId(Integer checkId) {
		this.checkId = checkId;
	}

	@Column(name = "room_id", nullable = false, length = 6)
	public String getRoomId() {
		return this.roomId;
	}

	public void setRoomId(String roomId) {
		this.roomId = roomId;
	}

	@Column(name = "reach_date", nullable = false, length = 16)
	public Date getReachDate() {
		return this.reachDate;
	}

	public void setReachDate(Date reachDate) {
		this.reachDate = reachDate;
	}

	@Column(name = "leave_date", nullable = false, length = 16)
	public Date getLeaveDate() {
		return this.leaveDate;
	}

	public void setLeaveDate(Date leaveDate) {
		this.leaveDate = leaveDate;
	}

	@Column(name = "flag", nullable = false)
	public Integer getFlag() {
		return this.flag;
	}

	public void setFlag(Integer flag) {
		this.flag = flag;
	}

	@Column(name = "status", nullable = false)
	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	@Column(name = "keep_flag", length = 1)
	public String getKeepFlag() {
		return this.keepFlag;
	}

	public void setKeepFlag(String keepFlag) {
		this.keepFlag = keepFlag;
	}

	@Column(name = "oper_id", length = 10)
	public String getOperId() {
		return this.operId;
	}

	public void setOperId(String operId) {
		this.operId = operId;
	}

	@Column(name = "oper_time", length = 16)
	public Date getOperTime() {
		return this.operTime;
	}

	public void setOperTime(Date operTime) {
		this.operTime = operTime;
	}

	@Column(name = "note")
	public String getNote() {
		return this.note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	@Column(name = "book_id")
	public Integer getBookId() {
		return this.bookId;
	}

	public void setBookId(Integer bookId) {
		this.bookId = bookId;
	}

	@Column(name = "room_price", precision = 15, scale = 0)
	public Double getRoomPrice() {
		return this.roomPrice;
	}

	public void setRoomPrice(Double roomPrice) {
		this.roomPrice = roomPrice;
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
    @ManyToOne(cascade=CascadeType.REFRESH,fetch=FetchType.EAGER,optional=true)
    @JoinColumn(name="book_id",insertable=false,updatable=false,nullable=true,unique=false)
	public BookRoom getBookRoom() {
		return bookRoom;
	}

	public void setBookRoom(BookRoom bookRoom) {
		this.bookRoom = bookRoom;
	}

	@ManyToOne(cascade=CascadeType.REFRESH,fetch=FetchType.EAGER,optional=true)
    @JoinColumn(name="oper_id",insertable=false,updatable=false,nullable=true,unique=false)
	public Operator getOperator() {
		return operator;
	}

	public void setOperator(Operator operator) {
		this.operator = operator;
	}
	
}