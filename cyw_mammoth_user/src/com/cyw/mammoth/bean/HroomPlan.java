package com.cyw.mammoth.bean;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * HroomPlan entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "hroom_plan", schema = "dbo", catalog = "mammoth")
public class HroomPlan implements java.io.Serializable {

	// Fields

	private Integer id;
	private String codeId;
	private String codeNamee;
	private String codeNamec;
	private Timestamp startDate;
	private Timestamp endDate;
	private Integer rmplanType;
	private String memo;
	private Integer status;
	private String vilhotelId;
	private String chainId;
	
	private Integer checked ;    //选中启用  默认0否      1是
	private Integer editable ;   //允许修改  默认0否      1是

	// Constructors

	/** default constructor */
	public HroomPlan() {
	}

	/** minimal constructor */
	public HroomPlan(Integer id, String codeId, String codeNamec,
			Timestamp startDate, Timestamp endDate, Integer rmplanType,
			Integer status, Integer checked, Integer editable) {
		this.id = id;
		this.codeId = codeId;
		this.codeNamec = codeNamec;
		this.startDate = startDate;
		this.endDate = endDate;
		this.rmplanType = rmplanType;
		this.status = status;
		this.checked = checked;
		this.editable = editable;
	}

	/** full constructor */
	public HroomPlan(Integer id, String codeId, String codeNamee,
			String codeNamec, Timestamp startDate, Timestamp endDate,
			Integer rmplanType, String memo, Integer status, Integer checked, Integer editable, String vilhotelId,
			String chainId) {
		this.id = id;
		this.codeId = codeId;
		this.codeNamee = codeNamee;
		this.codeNamec = codeNamec;
		this.startDate = startDate;
		this.endDate = endDate;
		this.rmplanType = rmplanType;
		this.memo = memo;
		this.status = status;
		this.checked = checked;
		this.editable = editable;
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

	@Column(name = "code_id", nullable = false, length = 3)
	public String getCodeId() {
		return this.codeId;
	}

	public void setCodeId(String codeId) {
		this.codeId = codeId;
	}

	@Column(name = "code_namee", length = 40)
	public String getCodeNamee() {
		return this.codeNamee;
	}

	public void setCodeNamee(String codeNamee) {
		this.codeNamee = codeNamee;
	}

	@Column(name = "code_namec", nullable = false, length = 30)
	public String getCodeNamec() {
		return this.codeNamec;
	}

	public void setCodeNamec(String codeNamec) {
		this.codeNamec = codeNamec;
	}

	@Column(name = "start_date", nullable = false, length = 23)
	public Timestamp getStartDate() {
		return this.startDate;
	}

	public void setStartDate(Timestamp startDate) {
		this.startDate = startDate;
	}

	@Column(name = "end_date", nullable = false, length = 23)
	public Timestamp getEndDate() {
		return this.endDate;
	}

	public void setEndDate(Timestamp endDate) {
		this.endDate = endDate;
	}

	@Column(name = "rmplan_type", nullable = false)
	public Integer getRmplanType() {
		return this.rmplanType;
	}

	public void setRmplanType(Integer rmplanType) {
		this.rmplanType = rmplanType;
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
	@Column(name = "checked", nullable = false)
	public Integer getChecked() {
		return checked;
	}

	public void setChecked(Integer checked) {
		this.checked = checked;
	}
	@Column(name = "editable", nullable = false)
	public Integer getEditable() {
		return editable;
	}

	public void setEditable(Integer editable) {
		this.editable = editable;
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