package com.zoomoor.jy.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * ConfigEmpMapping entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "config_emp_mapping" )
public class ConfigEmpMapping implements java.io.Serializable {

	// Fields

	private Integer id;
	private InfoEmp infoEmp;
	private ParamConfig paramConfig;
	private Boolean del;

	// Constructors

	/** default constructor */
	public ConfigEmpMapping() {
	}

	/** full constructor */
	public ConfigEmpMapping(InfoEmp infoEmp, ParamConfig paramConfig,
			Boolean del) {
		this.infoEmp = infoEmp;
		this.paramConfig = paramConfig;
		this.del = del;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "emp_id")
	public InfoEmp getInfoEmp() {
		return this.infoEmp;
	}

	public void setInfoEmp(InfoEmp infoEmp) {
		this.infoEmp = infoEmp;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "unit_id")
	public ParamConfig getParamConfig() {
		return this.paramConfig;
	}

	public void setParamConfig(ParamConfig paramConfig) {
		this.paramConfig = paramConfig;
	}

	@Column(name = "del")
	public Boolean getDel() {
		return this.del;
	}

	public void setDel(Boolean del) {
		this.del = del;
	}

}