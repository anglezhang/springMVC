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
 * SummaryDeptMapping entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "summary_dept_mapping" )
public class SummaryDeptMapping implements java.io.Serializable {

	// Fields

	private Integer id;
	private InfoDept infoDept;
	private InfoSummary infoSummary;
	private Boolean del;

	// Constructors

	/** default constructor */
	public SummaryDeptMapping() {
	}

	/** full constructor */
	public SummaryDeptMapping(InfoDept infoDept, InfoSummary infoSummary,
			Boolean del) {
		this.infoDept = infoDept;
		this.infoSummary = infoSummary;
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
	@JoinColumn(name = "dep_id")
	public InfoDept getInfoDept() {
		return this.infoDept;
	}

	public void setInfoDept(InfoDept infoDept) {
		this.infoDept = infoDept;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "summary_id")
	public InfoSummary getInfoSummary() {
		return this.infoSummary;
	}

	public void setInfoSummary(InfoSummary infoSummary) {
		this.infoSummary = infoSummary;
	}

	@Column(name = "del")
	public Boolean getDel() {
		return this.del;
	}

	public void setDel(Boolean del) {
		this.del = del;
	}

}