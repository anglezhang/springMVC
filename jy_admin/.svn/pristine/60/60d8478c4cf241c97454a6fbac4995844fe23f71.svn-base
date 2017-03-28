package com.zoomoor.jy.entity;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

/**
 * InfoSummary entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "info_summary" )
@JsonIgnoreProperties(value={"depotBills","summaryDeptMappings"})
public class InfoSummary implements java.io.Serializable {

	// Fields

	private Integer summaryId;
	private String name;
	private Boolean SType;
	private String zf;
	private Boolean del;
	private Set<DepotBill> depotBills = new HashSet<DepotBill>(0);
	private Set<SummaryDeptMapping> summaryDeptMappings = new HashSet<SummaryDeptMapping>(
			0);

	// Constructors

	/** default constructor */
	public InfoSummary() {
	}

	/** full constructor */
	public InfoSummary(String name, Boolean SType, String zf, Boolean del,
			Set<DepotBill> depotBills,
			Set<SummaryDeptMapping> summaryDeptMappings) {
		this.name = name;
		this.SType = SType;
		this.zf = zf;
		this.del = del;
		this.depotBills = depotBills;
		this.summaryDeptMappings = summaryDeptMappings;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "summary_id", unique = true, nullable = false)
	public Integer getSummaryId() {
		return this.summaryId;
	}

	public void setSummaryId(Integer summaryId) {
		this.summaryId = summaryId;
	}

	@Column(name = "name", length = 100)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "s_type", length = 10)
	public Boolean getSType() {
		return this.SType;
	}

	public void setSType(Boolean SType) {
		this.SType = SType;
	}

	@Column(name = "zf", length = 10)
	public String getZf() {
		return this.zf;
	}

	public void setZf(String zf) {
		this.zf = zf;
	}

	@Column(name = "del")
	public Boolean getDel() {
		return this.del;
	}

	public void setDel(Boolean del) {
		this.del = del;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "infoSummary")
	public Set<DepotBill> getDepotBills() {
		return this.depotBills;
	}

	public void setDepotBills(Set<DepotBill> depotBills) {
		this.depotBills = depotBills;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "infoSummary")
	public Set<SummaryDeptMapping> getSummaryDeptMappings() {
		return this.summaryDeptMappings;
	}

	public void setSummaryDeptMappings(
			Set<SummaryDeptMapping> summaryDeptMappings) {
		this.summaryDeptMappings = summaryDeptMappings;
	}

}