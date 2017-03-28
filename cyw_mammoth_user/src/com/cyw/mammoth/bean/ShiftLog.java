package com.cyw.mammoth.bean;

import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;

import org.hibernate.annotations.GenericGenerator;

/**
 * ShiftLog entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "Shift_log", schema = "dbo", catalog = "mammoth")
public class ShiftLog implements java.io.Serializable {

	// Fields

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	/**
	 * 版本
	 */
	protected Integer version;
	private Integer linkId;
	private Integer flag;
	private String operId;
	private String shiftId;
	private Timestamp operTime;
	private Date operDate;
	private String memo;
	private String vilhotelId;
	private String chainId;

	// Constructors

	/** default constructor */
	public ShiftLog() {
	}

	/** minimal constructor */
	public ShiftLog(Integer id, Integer linkId, Integer flag, String operId,
			String shiftId, Timestamp operTime) {
		this.id = id;
		this.linkId = linkId;
		this.flag = flag;
		this.operId = operId;
		this.shiftId = shiftId;
		this.operTime = operTime;
	}

	/** full constructor */
	public ShiftLog(Integer id, Integer linkId, Integer flag, String operId,
			String shiftId, Timestamp operTime, String memo, String vilhotelId,
			String chainId) {
		this.id = id;
		this.linkId = linkId;
		this.flag = flag;
		this.operId = operId;
		this.shiftId = shiftId;
		this.operTime = operTime;
		this.memo = memo;
		this.vilhotelId = vilhotelId;
		this.chainId = chainId;
	}

	// Property accessors
	@Id
	@Column(name = "ID", unique = true, nullable = false)
	@GenericGenerator(name = "generator", strategy = "identity")
	@GeneratedValue(generator = "generator")
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "link_id", nullable = true)
	public Integer getLinkId() {
		return this.linkId;
	}

	public void setLinkId(Integer linkId) {
		this.linkId = linkId;
	}

	@Column(name = "flag")
	public Integer getFlag() {
		return this.flag;
	}

	public void setFlag(Integer flag) {
		this.flag = flag;
	}

	@Column(name = "oper_id", nullable = false, length = 10)
	public String getOperId() {
		return this.operId;
	}

	public void setOperId(String operId) {
		this.operId = operId;
	}

	@Column(name = "shift_id", nullable = false, length = 6)
	public String getShiftId() {
		return this.shiftId;
	}

	public void setShiftId(String shiftId) {
		this.shiftId = shiftId;
	}

	@Column(name = "oper_time", nullable = false)
	public Timestamp getOperTime() {
		return this.operTime;
	}

	public void setOperTime(Timestamp operTime) {
		this.operTime = operTime;
	}

	@Column(name = "memo")
	public String getMemo() {
		return this.memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
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
	@Version
	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}
	@Column(name = "oper_date", nullable = true)
	public Date getOperDate() {
		return operDate;
	}

	public void setOperDate(Date operDate) {
		this.operDate = operDate;
	}

}