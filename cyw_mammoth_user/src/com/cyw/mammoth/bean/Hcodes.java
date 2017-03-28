package com.cyw.mammoth.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * Hcodes entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "hcodes", schema = "dbo", catalog = "mammoth", uniqueConstraints = @UniqueConstraint(columnNames = "code_id"))
public class Hcodes implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5972275130397965268L;
	
	// Fields
	
	private Integer id;               //  序列号
	private String codeId;            //  代码值
	private String codeNamee;         //  代码英文名
	private String codeNamec;         //  代码中文名
	private Integer codeKind;         //  代码种类
	private Integer status;           //  状态
	private String flag;              //  
	private String vilhotelId;        //  总店ID
	private String chainId;           //  连锁店ID
                                      
	// Constructors                   
                                      
	/** default constructor */       
	public Hcodes() {                 
	}                                 
                                      
	/** minimal constructor */        
	public Hcodes(Integer id, String codeId, String codeNamee,
			String codeNamec, Integer codeKind, Integer status) {
		this.id = id;
		this.codeId = codeId;
		this.codeNamee = codeNamee;
		this.codeNamec = codeNamec;
		this.codeKind = codeKind;
		this.status = status;
	}

	/** full constructor */
	public Hcodes(Integer id, String codeId, String codeNamee,
			String codeNamec, Integer codeKind, Integer status, String flag,
			String vilhotelId, String chainId) {
		this.id = id;
		this.codeId = codeId;
		this.codeNamee = codeNamee;
		this.codeNamec = codeNamec;
		this.codeKind = codeKind;
		this.status = status;
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

	@Column(name = "code_id", unique = true, nullable = false, length = 6)
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

	@Column(name = "code_kind", nullable = false)
	public Integer getCodeKind() {
		return this.codeKind;
	}

	public void setCodeKind(Integer codeKind) {
		this.codeKind = codeKind;
	}

	@Column(name = "status", nullable = false)
	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	@Column(name = "flag", length = 1)
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