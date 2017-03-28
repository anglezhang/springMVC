package com.cyw.mammoth.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * ReportDoc entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "report_doc", schema = "dbo", catalog = "mammoth")
public class ReportDoc implements java.io.Serializable {

	// Fields

	private String rptId;
	private String rptTitle;
	private String rptTitlee;
	private String rptFile;
	private String rptType;
	private String rptSysid;
	private String rptShortcutAllow;
	private Integer placeholderId;

	// Constructors

	/** default constructor */
	public ReportDoc() {
	}

	/** minimal constructor */
	public ReportDoc(String rptId, String rptTitle, String rptFile,
			String rptType, String rptSysid) {
		this.rptId = rptId;
		this.rptTitle = rptTitle;
		this.rptFile = rptFile;
		this.rptType = rptType;
		this.rptSysid = rptSysid;
	}

	/** full constructor */
	public ReportDoc(String rptId, String rptTitle, String rptTitlee,
			String rptFile, String rptType, String rptSysid,
			String rptShortcutAllow, Integer placeholderId) {
		this.rptId = rptId;
		this.rptTitle = rptTitle;
		this.rptTitlee = rptTitlee;
		this.rptFile = rptFile;
		this.rptType = rptType;
		this.rptSysid = rptSysid;
		this.rptShortcutAllow = rptShortcutAllow;
		this.placeholderId = placeholderId;
	}

	// Property accessors
	@Id
	@Column(name = "rpt_id", unique = true, nullable = false, length = 10)
	public String getRptId() {
		return this.rptId;
	}

	public void setRptId(String rptId) {
		this.rptId = rptId;
	}

	@Column(name = "rpt_title", nullable = false, length = 128)
	public String getRptTitle() {
		return this.rptTitle;
	}

	public void setRptTitle(String rptTitle) {
		this.rptTitle = rptTitle;
	}

	@Column(name = "rpt_titlee", length = 128)
	public String getRptTitlee() {
		return this.rptTitlee;
	}

	public void setRptTitlee(String rptTitlee) {
		this.rptTitlee = rptTitlee;
	}

	@Column(name = "rpt_file", nullable = false, length = 20)
	public String getRptFile() {
		return this.rptFile;
	}

	public void setRptFile(String rptFile) {
		this.rptFile = rptFile;
	}

	@Column(name = "rpt_type", nullable = false, length = 1)
	public String getRptType() {
		return this.rptType;
	}

	public void setRptType(String rptType) {
		this.rptType = rptType;
	}

	@Column(name = "rpt_sysid", nullable = false, length = 6)
	public String getRptSysid() {
		return this.rptSysid;
	}

	public void setRptSysid(String rptSysid) {
		this.rptSysid = rptSysid;
	}

	@Column(name = "rpt_shortcut_allow", length = 1)
	public String getRptShortcutAllow() {
		return this.rptShortcutAllow;
	}

	public void setRptShortcutAllow(String rptShortcutAllow) {
		this.rptShortcutAllow = rptShortcutAllow;
	}

	@Column(name = "placeholder_id")
	public Integer getPlaceholderId() {
		return this.placeholderId;
	}

	public void setPlaceholderId(Integer placeholderId) {
		this.placeholderId = placeholderId;
	}

}