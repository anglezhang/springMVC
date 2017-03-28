package com.cyw.mammoth.bean;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Bills entity. @author MyEclipse Persistence Tools
 */
@Entity
@org.hibernate.annotations.Entity(dynamicInsert=true)
@Table(name = "bills", schema = "dbo", catalog = "mammoth")
public class Bills implements java.io.Serializable {

	// Fields

	private Integer id;
	private String billType;
	private Integer billId;
	private Integer accoId;
	private String consId;
	private String setlId;
	private String moneyKind;
	private Double balance;
	private Double foreignm;
	private Double svcCharge;
	private String extName;
	private String roomId;
	private String notes;
	private String status;
	private Date operTime;
	private String operId;
	private Integer inBill;
	private Integer outBill;
	private Integer payNum;
	private Boolean arFlag;
	private Date hotelTime;
	private String billFlag;
	private String okFlag;
	private Integer arNum;
	private Date payDate;
	private String vilhotelId;
	private String chainId;

	// Constructors

	/** default constructor */
	public Bills() {
	}

	/** minimal constructor */
	public Bills(Integer id, String billType, Integer billId, Integer accoId,
			String consId, String setlId, String moneyKind, Double balance,
			Double foreignm, Double svcCharge, String extName, String notes,
			String status, Date operTime, String operId, Boolean arFlag,
			Date hotelTime, String billFlag, String okFlag) {
		this.id = id;
		this.billType = billType;
		this.billId = billId;
		this.accoId = accoId;
		this.consId = consId;
		this.setlId = setlId;
		this.moneyKind = moneyKind;
		this.balance = balance;
		this.foreignm = foreignm;
		this.svcCharge = svcCharge;
		this.extName = extName;
		this.notes = notes;
		this.status = status;
		this.operTime = operTime;
		this.operId = operId;
		this.arFlag = arFlag;
		this.hotelTime = hotelTime;
		this.billFlag = billFlag;
		this.okFlag = okFlag;
	}

	/** full constructor */
	public Bills(Integer id, String billType, Integer billId, Integer accoId,
			String consId, String setlId, String moneyKind, Double balance,
			Double foreignm, Double svcCharge, String extName, String roomId,
			String notes, String status, Date operTime, String operId,
			Integer inBill, Integer outBill, Integer payNum, Boolean arFlag,
			Date hotelTime, String billFlag, String okFlag, Integer arNum,
			Date payDate, String vilhotelId, String chainId) {
		this.id = id;
		this.billType = billType;
		this.billId = billId;
		this.accoId = accoId;
		this.consId = consId;
		this.setlId = setlId;
		this.moneyKind = moneyKind;
		this.balance = balance;
		this.foreignm = foreignm;
		this.svcCharge = svcCharge;
		this.extName = extName;
		this.roomId = roomId;
		this.notes = notes;
		this.status = status;
		this.operTime = operTime;
		this.operId = operId;
		this.inBill = inBill;
		this.outBill = outBill;
		this.payNum = payNum;
		this.arFlag = arFlag;
		this.hotelTime = hotelTime;
		this.billFlag = billFlag;
		this.okFlag = okFlag;
		this.arNum = arNum;
		this.payDate = payDate;
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

	@Column(name = "bill_type", nullable = false, length = 1)
	public String getBillType() {
		return this.billType;
	}

	public void setBillType(String billType) {
		this.billType = billType;
	}

	@Column(name = "bill_id", nullable = false)
	public Integer getBillId() {
		return this.billId;
	}

	public void setBillId(Integer billId) {
		this.billId = billId;
	}

	@Column(name = "acco_id", nullable = false)
	public Integer getAccoId() {
		return this.accoId;
	}

	public void setAccoId(Integer accoId) {
		this.accoId = accoId;
	}

	@Column(name = "cons_id", length = 4)
	public String getConsId() {
		return this.consId;
	}

	public void setConsId(String consId) {
		this.consId = consId;
	}

	@Column(name = "setl_id", length = 4)
	public String getSetlId() {
		return this.setlId;
	}

	public void setSetlId(String setlId) {
		this.setlId = setlId;
	}

	@Column(name = "money_kind",  length = 3)
	public String getMoneyKind() {
		return this.moneyKind;
	}

	public void setMoneyKind(String moneyKind) {
		this.moneyKind = moneyKind;
	}

	@Column(name = "balance",  precision = 15, scale = 0)
	public Double getBalance() {
		return this.balance;
	}

	public void setBalance(Double balance) {
		this.balance = balance;
	}

	@Column(name = "foreignm", precision = 15, scale = 0)
	public Double getForeignm() {
		return this.foreignm;
	}

	public void setForeignm(Double foreignm) {
		this.foreignm = foreignm;
	}

	@Column(name = "svc_charge",  precision = 15, scale = 0)
	public Double getSvcCharge() {
		return this.svcCharge;
	}

	public void setSvcCharge(Double svcCharge) {
		this.svcCharge = svcCharge;
	}

	@Column(name = "ext_name", length = 4)
	public String getExtName() {
		return this.extName;
	}

	public void setExtName(String extName) {
		this.extName = extName;
	}

	@Column(name = "room_id", length = 6)
	public String getRoomId() {
		return this.roomId;
	}

	public void setRoomId(String roomId) {
		this.roomId = roomId;
	}

	@Column(name = "notes")
	public String getNotes() {
		return this.notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	@Column(name = "status", length = 1)
	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Column(name = "oper_time", length = 16)
	public Date getOperTime() {
		return this.operTime;
	}

	public void setOperTime(Date operTime) {
		this.operTime = operTime;
	}

	@Column(name = "oper_id", nullable = false, length = 10)
	public String getOperId() {
		return this.operId;
	}

	public void setOperId(String operId) {
		this.operId = operId;
	}

	@Column(name = "in_bill")
	public Integer getInBill() {
		return this.inBill;
	}

	public void setInBill(Integer inBill) {
		this.inBill = inBill;
	}

	@Column(name = "out_bill")
	public Integer getOutBill() {
		return this.outBill;
	}

	public void setOutBill(Integer outBill) {
		this.outBill = outBill;
	}

	@Column(name = "pay_num")
	public Integer getPayNum() {
		return this.payNum;
	}

	public void setPayNum(Integer payNum) {
		this.payNum = payNum;
	}

	@Column(name = "Ar_Flag")
	public Boolean getArFlag() {
		return this.arFlag;
	}

	public void setArFlag(Boolean arFlag) {
		this.arFlag = arFlag;
	}

	@Column(name = "hotel_time", length = 16)
	public Date getHotelTime() {
		return this.hotelTime;
	}

	public void setHotelTime(Date hotelTime) {
		this.hotelTime = hotelTime;
	}

	@Column(name = "bill_flag",length = 1)
	public String getBillFlag() {
		return this.billFlag;
	}

	public void setBillFlag(String billFlag) {
		this.billFlag = billFlag;
	}

	@Column(name = "ok_flag", length = 1)
	public String getOkFlag() {
		return this.okFlag;
	}

	public void setOkFlag(String okFlag) {
		this.okFlag = okFlag;
	}

	@Column(name = "AR_num")
	public Integer getArNum() {
		return this.arNum;
	}

	public void setArNum(Integer arNum) {
		this.arNum = arNum;
	}

	@Column(name = "pay_date", length = 16)
	public Date getPayDate() {
		return this.payDate;
	}

	public void setPayDate(Date payDate) {
		this.payDate = payDate;
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