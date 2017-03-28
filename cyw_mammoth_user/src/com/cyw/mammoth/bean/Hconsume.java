package com.cyw.mammoth.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * Hconsume entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "hconsume", schema = "dbo", catalog = "mammoth", uniqueConstraints = @UniqueConstraint(columnNames = "code_id"))
public class Hconsume implements java.io.Serializable {

	private static final long serialVersionUID = 3258988121020378324L;
	
	// Fields
	private Integer id;            //  序号       
	private String codeId;         //  消费点代码
	private String codeNamee;      //  消费点英文名
	private String codeNamec;      //  消费点中文名
	private Integer status;        //  状态
	private Short svcRate;         //  服务费率
	private String kind;           //  消费点类型
	private Double integral;       //  会员积分比例
	private Integer codeKind;      //  代码类型
	private String vilhotelId;     //  总店ID
	private String chainId;        //  连锁店ID
	private String moneyId;   	  //币种                              
	// Constructors               
                                   
	/** default constructor */      
	public Hconsume() {            
	}                              
                                    
	/** minimal constructor */
	public Hconsume(Integer id, String codeId, String codeNamee,
			String codeNamec, Integer status, Short svcRate, String kind,
			Double integral, Integer codeKind) {
		this.id = id;
		this.codeId = codeId;
		this.codeNamee = codeNamee;
		this.codeNamec = codeNamec;
		this.status = status;
		this.svcRate = svcRate;
		this.kind = kind;
		this.integral = integral;
		this.codeKind = codeKind;
	}

	/** full constructor */
	public Hconsume(Integer id, String codeId, String codeNamee,
			String codeNamec, Integer status, Short svcRate, String kind,
			Double integral, Integer codeKind, String vilhotelId, String chainId) {
		this.id = id;
		this.codeId = codeId;
		this.codeNamee = codeNamee;
		this.codeNamec = codeNamec;
		this.status = status;
		this.svcRate = svcRate;
		this.kind = kind;
		this.integral = integral;
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

	@Column(name = "svc_rate", nullable = false)
	public Short getSvcRate() {
		return this.svcRate;
	}

	public void setSvcRate(Short svcRate) {
		this.svcRate = svcRate;
	}

	@Column(name = "kind", nullable = false, length = 6)
	public String getKind() {
		return this.kind;
	}

	public void setKind(String kind) {
		this.kind = kind;
	}

	@Column(name = "integral", nullable = false, precision = 15, scale = 0)
	public Double getIntegral() {
		return this.integral;
	}

	public void setIntegral(Double integral) {
		this.integral = integral;
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
	
	@Column(name = "money_id", nullable = false, length = 3)
	public String getMoneyId() {
		return this.moneyId;
	}

	public void setMoneyId(String moneyId) {
		this.moneyId = moneyId;
	}
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
	
}