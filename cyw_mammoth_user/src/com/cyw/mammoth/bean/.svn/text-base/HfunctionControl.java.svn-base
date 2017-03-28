package com.cyw.mammoth.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang.builder.ToStringBuilder;
@Entity
@Table(name = "hfunction_control", schema = "dbo", catalog = "mammoth")
public class HfunctionControl implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2620529239632734874L;
	private Integer id;
	private String fgroup;//归属组
	private String functionId;//编码
	private String functionName;//名称
	private Integer parentId;//上级菜单ID
	private String ctrlType;//功能类型  menu/button
	private String memo;
	private String vilhotelId;
	private String chainId;
	public HfunctionControl() {
		super();
		// TODO Auto-generated constructor stub
	}
	public HfunctionControl(Integer id, String fgroup, String functionId,
			String functionName, Integer parentId, String ctrlType,
			String memo, String vilhotelId, String chainId) {
		super();
		this.id = id;
		this.fgroup = fgroup;
		this.functionId = functionId;
		this.functionName = functionName;
		this.parentId = parentId;
		this.ctrlType = ctrlType;
		this.memo = memo;
		this.vilhotelId = vilhotelId;
		this.chainId = chainId;
	}
	
	@Id
	@GeneratedValue
	@Column(name = "ID", unique = true, nullable = false)
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	@Column(name = "f_group", length = 50)
	public String getFgroup() {
		return fgroup;
	}
	public void setFgroup(String fgroup) {
		this.fgroup = fgroup;
	}
	@Column(name = "function_id", nullable = false, length = 10)
	public String getFunctionId() {
		return functionId;
	}
	public void setFunctionId(String functionId) {
		this.functionId = functionId;
	}
	@Column(name = "function_name", nullable = false, length = 50)
	public String getFunctionName() {
		return functionName;
	}
	public void setFunctionName(String functionName) {
		this.functionName = functionName;
	}
	
	@Column(name="parent_id",nullable=false)
	public Integer getParentId() {
		return parentId;
	}
	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}
	@Column(name = "ctrl_type", nullable = false, length = 50)
	public String getCtrlType() {
		return ctrlType;
	}
	public void setCtrlType(String ctrlType) {
		this.ctrlType = ctrlType;
	}
	@Column(name = "memo")
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	@Column(name = "vilhotel_id", length = 50)
	public String getVilhotelId() {
		return vilhotelId;
	}
	public void setVilhotelId(String vilhotelId) {
		this.vilhotelId = vilhotelId;
	}
	@Column(name = "chain_id", length = 50)
	public String getChainId() {
		return chainId;
	}
	public void setChainId(String chainId) {
		this.chainId = chainId;
	}
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
