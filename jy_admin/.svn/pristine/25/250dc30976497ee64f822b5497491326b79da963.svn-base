package com.zoomoor.jy.entity;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * InfoDepotPosition entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "info_depot_position")
public class InfoDepotPosition implements java.io.Serializable {

	// Fields

	private Integer id;
	private InfoDept infoDept;
	private InfoDepotPosition infoDepotPosition;
	private String name;
	private Integer sort;
	private String content;
	private Boolean del;
	private Set<InfoDepotPosition> infoDepotPositions = new HashSet<InfoDepotPosition>(
			0);
	private Integer comeId;
	
	@Transient
	public Integer getComeId() {
		return comeId;
	}

	public void setComeId(Integer comeId) {
		this.comeId = comeId;
	}

	//listrowId
	private Integer rowId;
	
	@Transient
	public Integer getRowId() {
		return rowId;
	}

	public void setRowId(Integer rowId) {
		this.rowId = rowId;
	}

	//数量
	private Double num;
	
	// Constructors
	@Transient
	public Double getNum() {
		return num;
	}

	public void setNum(Double num) {
		this.num = num;
	}

	/** default constructor */
	public InfoDepotPosition() {
	}

	/** minimal constructor */
	public InfoDepotPosition(Integer id) {
		this.id = id;
	}

	/** full constructor */
	public InfoDepotPosition(Integer id, InfoDept infoDept,
			InfoDepotPosition infoDepotPosition, String name, Integer sort,
			String content, Boolean del,
			Set<InfoDepotPosition> infoDepotPositions) {
		this.id = id;
		this.infoDept = infoDept;
		this.infoDepotPosition = infoDepotPosition;
		this.name = name;
		this.sort = sort;
		this.content = content;
		this.del = del;
		this.infoDepotPositions = infoDepotPositions;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy=IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "dept_id")
	public InfoDept getInfoDept() {
		return this.infoDept;
	}

	public void setInfoDept(InfoDept infoDept) {
		this.infoDept = infoDept;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "up_id")
	public InfoDepotPosition getInfoDepotPosition() {
		return this.infoDepotPosition;
	}

	public void setInfoDepotPosition(InfoDepotPosition infoDepotPosition) {
		this.infoDepotPosition = infoDepotPosition;
	}

	@Column(name = "name", length = 50)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "sort")
	public Integer getSort() {
		return this.sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	@Column(name = "content", length = 200)
	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Column(name = "del")
	public Boolean getDel() {
		return this.del;
	}

	public void setDel(Boolean del) {
		this.del = del;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "infoDepotPosition")
	public Set<InfoDepotPosition> getInfoDepotPositions() {
		return this.infoDepotPositions;
	}

	public void setInfoDepotPositions(Set<InfoDepotPosition> infoDepotPositions) {
		this.infoDepotPositions = infoDepotPositions;
	}

}