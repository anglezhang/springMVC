package com.cyw.mammoth.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * Hsettle entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "hsettle", schema = "dbo", catalog = "mammoth", uniqueConstraints = @UniqueConstraint(columnNames = "code_id"))
public class Hsettle implements java.io.Serializable {

	// Fields

	private Integer id;
	private String codeId;
	private String codeNamee;
	private String codeNamec;
	private Integer status;
	private Double limit;
	private String kind;
	private String moneyId;
	private Integer arNum;
	private Integer codeKind;
	private String vilhotelId;
	private String chainId;

	// Constructors

	/** default constructor */
	public Hsettle() {
	}

	/** minimal constructor */
	public Hsettle(Integer id, String codeId, String codeNamee,
			String codeNamec, Integer status, Double limit, String kind,
			String moneyId, Integer codeKind) {
		this.id = id;
		this.codeId = codeId;
		this.codeNamee = codeNamee;
		this.codeNamec = codeNamec;
		this.status = status;
		this.limit = limit;
		this.kind = kind;
		this.moneyId = moneyId;
		this.codeKind = codeKind;
	}

	/** full constructor */
	public Hsettle(Integer id, String codeId, String codeNamee,
			String codeNamec, Integer status, Double limit, String kind,
			String moneyId, Integer arNum, Integer codeKind, String vilhotelId,
			String chainId) {
		this.id = id;
		this.codeId = codeId;
		this.codeNamee = codeNamee;
		this.codeNamec = codeNamec;
		this.status = status;
		this.limit = limit;
		this.kind = kind;
		this.moneyId = moneyId;
		this.arNum = arNum;
		this.codeKind = codeKind;
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

	@Column(name = "code_id", unique = true, nullable = false, length = 4)
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

	@Column(name = "limit", nullable = false, precision = 15, scale = 0)
	public Double getLimit() {
		return this.limit;
	}

	public void setLimit(Double limit) {
		this.limit = limit;
	}

	@Column(name = "kind", nullable = false, length = 3)
	public String getKind() {
		return this.kind;
	}

	public void setKind(String kind) {
		this.kind = kind;
	}

	@Column(name = "money_id", nullable = false, length = 3)
	public String getMoneyId() {
		return this.moneyId;
	}

	public void setMoneyId(String moneyId) {
		this.moneyId = moneyId;
	}

	@Column(name = "ar_num")
	public Integer getArNum() {
		return this.arNum;
	}

	public void setArNum(Integer arNum) {
		this.arNum = arNum;
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

}