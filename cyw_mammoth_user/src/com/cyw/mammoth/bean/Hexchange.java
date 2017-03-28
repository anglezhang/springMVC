package com.cyw.mammoth.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * Hexchange entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "hexchange", schema = "dbo", catalog = "mammoth", uniqueConstraints = @UniqueConstraint(columnNames = "code_id"))
public class Hexchange implements java.io.Serializable {

	private static final long serialVersionUID = 3457190054456630506L;
	// Fields

	private Integer id;            // 序号
	private String codeId;         // 外币代码
	private String codeNamee;      // 外币英文名
	private String codeNamec;      // 外币中文名
	private Integer status;        // 状态
	private Double inPrice;        // 买进价
	private Double outPrice;       // 卖出价
	private Double price;          // 汇率
	private Short svcRate;         // 服务费率
	private Double svcCharge;      // 服务费
	private String flag;           // 标志
	private String vilhotelId;     // 总店ID
	private String chainId;        // 连锁店ID

	// Constructors

	/** default constructor */
	public Hexchange() {
	}

	/** minimal constructor */
	public Hexchange(Integer id, String codeId, String codeNamee,
			String codeNamec, Integer status, Double inPrice, Double outPrice,
			Double price, Short svcRate, Double svcCharge, String flag) {
		this.id = id;
		this.codeId = codeId;
		this.codeNamee = codeNamee;
		this.codeNamec = codeNamec;
		this.status = status;
		this.inPrice = inPrice;
		this.outPrice = outPrice;
		this.price = price;
		this.svcRate = svcRate;
		this.svcCharge = svcCharge;
		this.flag = flag;
	}

	/** full constructor */
	public Hexchange(Integer id, String codeId, String codeNamee,
			String codeNamec, Integer status, Double inPrice, Double outPrice,
			Double price, Short svcRate, Double svcCharge, String flag,
			String vilhotelId, String chainId) {
		this.id = id;
		this.codeId = codeId;
		this.codeNamee = codeNamee;
		this.codeNamec = codeNamec;
		this.status = status;
		this.inPrice = inPrice;
		this.outPrice = outPrice;
		this.price = price;
		this.svcRate = svcRate;
		this.svcCharge = svcCharge;
		this.flag = flag;
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

	@Column(name = "in_price", nullable = false, precision = 15, scale = 0)
	public Double getInPrice() {
		return this.inPrice;
	}

	public void setInPrice(Double inPrice) {
		this.inPrice = inPrice;
	}

	@Column(name = "out_price", nullable = false, precision = 15, scale = 0)
	public Double getOutPrice() {
		return this.outPrice;
	}

	public void setOutPrice(Double outPrice) {
		this.outPrice = outPrice;
	}

	@Column(name = "price", nullable = false, precision = 15, scale = 0)
	public Double getPrice() {
		return this.price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	@Column(name = "svc_rate", nullable = false)
	public Short getSvcRate() {
		return this.svcRate;
	}

	public void setSvcRate(Short svcRate) {
		this.svcRate = svcRate;
	}

	@Column(name = "svc_charge", nullable = false, precision = 15, scale = 0)
	public Double getSvcCharge() {
		return this.svcCharge;
	}

	public void setSvcCharge(Double svcCharge) {
		this.svcCharge = svcCharge;
	}

	@Column(name = "flag", nullable = false, length = 1)
	public String getFlag() {
		return this.flag;
	}

	public void setFlag(String flag) {
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

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}