package com.cyw.mammoth.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.apache.commons.lang.builder.ToStringBuilder;

@Entity
@Table(name = "hgst_ori", schema = "dbo", catalog = "mammoth", uniqueConstraints = @UniqueConstraint(columnNames = "code_id"))
public class HgstOri implements java.io.Serializable  {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5104039858677428083L;
	
	// Fields
	
	private Integer id;               //  序列号
	private String codeId;            //  代码值
	private String codeNamee;         //  代码英文名
	private String codeNamec;         //  代码中文名
	private String oriKind;          //  来源类别
	private Integer status;           //  状态
	private String priceId;           //  
	private String vilhotelId;        //  总店ID
	private String chainId;           //  连锁店ID
	
	
	// Constructors                 
	public HgstOri() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public HgstOri(Integer id, String codeId, String codeNamee,
			String codeNamec, String oriKind, Integer status, String priceId,
			String vilhotelId, String chainId) {
		super();
		this.id = id;
		this.codeId = codeId;
		this.codeNamee = codeNamee;
		this.codeNamec = codeNamec;
		this.oriKind = oriKind;
		this.status = status;
		this.priceId = priceId;
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

	@Column(name = "ori_kind", nullable = false, length = 6)
	public String getOriKind() {
		return oriKind;
	}

	public void setOriKind(String oriKind) {
		this.oriKind = oriKind;
	}

	@Column(name = "status", nullable = false , length=1)
	public Integer getStatus() {
		return this.status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}

	@Column(name = "price_id", length = 4)
	public String getPriceId() {
		return priceId;
	}

	public void setPriceId(String priceId) {
		this.priceId = priceId;
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
