package com.cyw.mammoth.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * HroomCharacters entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "hroom_characters", schema = "dbo", catalog = "mammoth")
public class HroomCharacters implements java.io.Serializable {
	private static final long serialVersionUID = -6575524306956129604L;
	// Fields
	private Integer id;                   //  主键ID
	private Integer placeholderId;        //  登记号
	private String codeNamee;             //  英文名称
	private String codeNamec;             //  中文名称
	private Integer codeKind;             //  代码种类
	private Integer status;               //  状态  
	private String vilhotelId;            //  总店ID
	private String chainId;               //  连锁店ID
                                          
	// Constructors                       
                                          
	/** default constructor */            
	public HroomCharacters() {            
	}                                     
                                          
	/** minimal constructor */            
	public HroomCharacters(Integer id, Integer placeholderId, String codeNamec,
			Integer codeKind, Integer status) {
		this.id = id;
		this.placeholderId = placeholderId;
		this.codeNamec = codeNamec;
		this.codeKind = codeKind;
		this.status = status;
	}

	/** full constructor */
	public HroomCharacters(Integer id, Integer placeholderId, String codeNamee,
			String codeNamec, Integer codeKind, Integer status,
			String vilhotelId, String chainId) {
		this.id = id;
		this.placeholderId = placeholderId;
		this.codeNamee = codeNamee;
		this.codeNamec = codeNamec;
		this.codeKind = codeKind;
		this.status = status;
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

	@Column(name = "placeholder_id", nullable = false)
	public Integer getPlaceholderId() {
		return this.placeholderId;
	}

	public void setPlaceholderId(Integer placeholderId) {
		this.placeholderId = placeholderId;
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