package com.cyw.mammoth.bean;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * Hbuilding entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "hbuilding", schema = "dbo", catalog = "mammoth", uniqueConstraints = @UniqueConstraint(columnNames = "code_id"))
public class Hbuilding implements java.io.Serializable {

	private static final long serialVersionUID = -1918453605418863931L;
	// Fields
	private Integer id;                 // 序号 
	private String codeId;              // 楼名代码 
	private String codeNamee;           // 楼名英文名 
	private String codeNamec;           // 楼名中文名 
	private Integer status;             // 状态  0有效  1无效
	private Integer minNum;             // 起始楼层 
	private Integer maxNum;             // 最高楼层 
	private Integer codeKind;           // 状态  0可修改  1不可修改
	private String vilhotelId;          // 总店ID
	private String chainId;             // 连锁店ID
	private Date updateDate;            // 更新时间  
	private String operId;              // 操作员  
                                         
	// Constructors                      
                                         
	/** default constructor */           
	public Hbuilding() {                 
	}                                     

	/** minimal constructor */
	public Hbuilding(Integer id, String codeId, String codeNamee,
			String codeNamec, Integer status, Integer minNum, Integer maxNum,
			Integer codeKind) {
		this.id = id;
		this.codeId = codeId;
		this.codeNamee = codeNamee;
		this.codeNamec = codeNamec;
		this.status = status;
		this.minNum = minNum;
		this.maxNum = maxNum;
		this.codeKind = codeKind;
	}

	/** full constructor */
	public Hbuilding(Integer id, String codeId, String codeNamee,
			String codeNamec, Integer status, Integer minNum, Integer maxNum,
			Integer codeKind, String vilhotelId, String chainId,
			Date updateDate, String operId) {
		this.id = id;
		this.codeId = codeId;
		this.codeNamee = codeNamee;
		this.codeNamec = codeNamec;
		this.status = status;
		this.minNum = minNum;
		this.maxNum = maxNum;
		this.codeKind = codeKind;
		this.vilhotelId = vilhotelId;
		this.chainId = chainId;
		this.updateDate = updateDate;
		this.operId = operId;
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

	@Column(name = "code_id", unique = true, nullable = false, length = 3)
	public String getCodeId() {
		return this.codeId;
	}

	public void setCodeId(String codeId) {
		this.codeId = codeId;
	}

	@Column(name = "code_namee", nullable = false, length = 40)
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

	@Column(name = "status", nullable = false)
	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	@Column(name = "min_num", nullable = false)
	public Integer getMinNum() {
		return this.minNum;
	}

	public void setMinNum(Integer minNum) {
		this.minNum = minNum;
	}

	@Column(name = "max_num", nullable = false)
	public Integer getMaxNum() {
		return this.maxNum;
	}

	public void setMaxNum(Integer maxNum) {
		this.maxNum = maxNum;
	}

	@Column(name = "code_kind", nullable = false)
	public Integer getCodeKind() {
		return this.codeKind;
	}

	public void setCodeKind(Integer codeKind) {
		this.codeKind = codeKind;
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

	@Column(name = "update_date", length = 16)
	public Date getUpdateDate() {
		return this.updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	@Column(name = "oper_id", length = 10)
	public String getOperId() {
		return this.operId;
	}

	public void setOperId(String operId) {
		this.operId = operId;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}