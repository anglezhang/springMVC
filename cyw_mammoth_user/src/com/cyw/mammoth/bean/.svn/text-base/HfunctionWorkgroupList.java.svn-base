package com.cyw.mammoth.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang.builder.ToStringBuilder;
@Entity
@Table(name = "hfunction_workgroup_list", schema = "dbo", catalog = "mammoth")
public class HfunctionWorkgroupList {
	
	private Integer id ;
	private String  hfunctionId ;
	private String  workGroupId ;
	private String  hfunctionType ;
	private String  hfunctionGroup ;
	public HfunctionWorkgroupList() {
		super();
		// TODO Auto-generated constructor stub
	}
	public HfunctionWorkgroupList(Integer id, String hfunctionId,
			String workGroupId, String hfunctionType, String hfunctionGroup) {
		super();
		this.id = id;
		this.hfunctionId = hfunctionId;
		this.workGroupId = workGroupId;
		this.hfunctionType = hfunctionType;
		this.hfunctionGroup = hfunctionGroup;
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
	
	@Column(name = "hfunction_id", nullable = false, length = 50)
	public String getHfunctionId() {
		return hfunctionId;
	}
	public void setHfunctionId(String hfunctionId) {
		this.hfunctionId = hfunctionId;
	}
	
	@Column(name = "workgroup_id", nullable = false, length = 50)
	public String getWorkGroupId() {
		return workGroupId;
	}
	public void setWorkGroupId(String workGroupId) {
		this.workGroupId = workGroupId;
	}
	
	@Column(name = "hfunction_type", nullable = false, length = 50)
	public String getHfunctionType() {
		return hfunctionType;
	}
	public void setHfunctionType(String hfunctionType) {
		this.hfunctionType = hfunctionType;
	}
	
	@Column(name = "hfunction_group", nullable = false, length = 50)
	public String getHfunctionGroup() {
		return hfunctionGroup;
	}
	public void setHfunctionGroup(String hfunctionGroup) {
		this.hfunctionGroup = hfunctionGroup;
	}
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
	
}
