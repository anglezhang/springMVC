package com.cyw.mammoth.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Hfunction entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "hfunction", schema = "dbo", catalog = "mammoth")
public class Hfunction implements java.io.Serializable {

	// Fields

    /** 序列化ID */
    private static final long serialVersionUID = 2892766297880735148L;
    /** ID */
    private Integer id;
	/** fgroup */
	private String fgroup;//归属组
	/** functionId */
	private String functionId ;//编码
	/** functionName */
	private String functionName;//名称
	/** parentId**/
	private Integer parentId;//上级菜单ID
	/** cyUrl */
	private String cyUrl;//菜单url
	/** ctrlType */
	private String ctrlType;//功能类型  menu/button
	/** memo */
	private String memo ;
	/** vilhotelId */
	private String vilhotelId;
	/** chainId */
	private String chainId;

	// Constructors

	/** default constructor */
	public Hfunction() {
	}
	public Hfunction(Integer id, String fgroup, String functionId,
			String functionName, Integer parentId, String cyUrl,
			String ctrlType, String memo, String vilhotelId, String chainId) {
		super();
		this.id = id;
		this.fgroup = fgroup;
		this.functionId = functionId;
		this.functionName = functionName;
		this.parentId = parentId;
		this.cyUrl = cyUrl;
		this.ctrlType = ctrlType;
		this.memo = memo;
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

	@Column(name = "function_id", nullable = false, length = 10)
	public String getFunctionId() {
		return this.functionId;
	}

	public void setFunctionId(String functionId) {
		this.functionId = functionId;
	}

	@Column(name = "function_name", nullable = false, length = 50)
	public String getFunctionName() {
		return this.functionName;
	}

	public void setFunctionName(String functionName) {
		this.functionName = functionName;
	}

	@Column(name = "cy_url")
	public String getCyUrl() {
		return this.cyUrl;
	}

	public void setCyUrl(String cyUrl) {
		this.cyUrl = cyUrl;
	}

	@Column(name = "ctrl_type", nullable = false, length = 50)
	public String getCtrlType() {
		return this.ctrlType;
	}

	public void setCtrlType(String ctrlType) {
		this.ctrlType = ctrlType;
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
	@Column(name = "f_group", length = 50)
	public String getFgroup() {
		return fgroup;
	}
	public void setFgroup(String fgroup) {
		this.fgroup = fgroup;
	}
    @Column(name="parent_id")
	public Integer getParentId() {
		return parentId;
	}
	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	
	
}