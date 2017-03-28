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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * InfoBrand entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "info_brand" )
public class InfoBrand implements java.io.Serializable {

	// Fields

	private Integer id;
	private InfoBrand infoBrand;
	private String name;
	private Integer sort;
	private Integer BLevel;
	private String url;
	private Boolean del;
	private Set<InfoCar> infoCars = new HashSet<InfoCar>(0);
	private Set<InfoBrand> infoBrands = new HashSet<InfoBrand>(0);
	private Set<InfoGoods> infoGoodses = new HashSet<InfoGoods>(0);

	// Constructors

	/** default constructor */
	public InfoBrand() {
	}

	/** full constructor */
	public InfoBrand(InfoBrand infoBrand, String name, Integer sort,Integer BLevel,String url,
			Boolean del, Set<InfoCar> infoCars, Set<InfoBrand> infoBrands,
			Set<InfoGoods> infoGoodses) {
		this.infoBrand = infoBrand;
		this.name = name;
		this.sort = sort;
		this.BLevel=BLevel;
		this.url=url;
		this.del = del;
		this.infoCars = infoCars;
		this.infoBrands = infoBrands;
		this.infoGoodses = infoGoodses;
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
	@JoinColumn(name = "up_id")
	public InfoBrand getInfoBrand() {
		return this.infoBrand;
	}

	public void setInfoBrand(InfoBrand infoBrand) {
		this.infoBrand = infoBrand;
	}

	@Column(name = "name", length = 30)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}
	@Column(name = "b_level", nullable = false)
	public Integer getBLevel() {
		return this.BLevel;
	}

	public void setBLevel(Integer BLevel) {
		this.BLevel = BLevel;
	}
	@Column(name = "sort")
	public Integer getSort() {
		return this.sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}
	@Column(name = "url")
	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	@Column(name = "del")
	public Boolean getDel() {
		return this.del;
	}

	public void setDel(Boolean del) {
		this.del = del;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "infoBrand")
	public Set<InfoCar> getInfoCars() {
		return this.infoCars;
	}

	public void setInfoCars(Set<InfoCar> infoCars) {
		this.infoCars = infoCars;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "infoBrand")
	public Set<InfoBrand> getInfoBrands() {
		return this.infoBrands;
	}

	public void setInfoBrands(Set<InfoBrand> infoBrands) {
		this.infoBrands = infoBrands;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "infoBrand")
	public Set<InfoGoods> getInfoGoodses() {
		return this.infoGoodses;
	}

	public void setInfoGoodses(Set<InfoGoods> infoGoodses) {
		this.infoGoodses = infoGoodses;
	}

}