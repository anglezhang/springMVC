package com.zoomoor.jy.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Entrustcarandcustome entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "entrustcarandcustome", catalog = "jiayue")
public class Entrustcarandcustome implements java.io.Serializable {

	// Fields

	private Integer id;
	private InfoCar infoCar;
	private InfoCustome infoCustome;
	private CustomerEntrust customerEntrust;
	private Boolean del;

	// Constructors

	/** default constructor */
	public Entrustcarandcustome() {
	}

	/** minimal constructor */
	public Entrustcarandcustome(Integer id) {
		this.id = id;
	}

	/** full constructor */
	public Entrustcarandcustome(Integer id, InfoCar infoCar,
			InfoCustome infoCustome, CustomerEntrust customerEntrust,
			Boolean del) {
		this.id = id;
		this.infoCar = infoCar;
		this.infoCustome = infoCustome;
		this.customerEntrust = customerEntrust;
		this.del = del;
	}

	// Property accessors
	@Id
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "car_id")
	public InfoCar getInfoCar() {
		return this.infoCar;
	}

	public void setInfoCar(InfoCar infoCar) {
		this.infoCar = infoCar;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "customer_id")
	public InfoCustome getInfoCustome() {
		return this.infoCustome;
	}

	public void setInfoCustome(InfoCustome infoCustome) {
		this.infoCustome = infoCustome;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "entrust_id")
	public CustomerEntrust getCustomerEntrust() {
		return this.customerEntrust;
	}

	public void setCustomerEntrust(CustomerEntrust customerEntrust) {
		this.customerEntrust = customerEntrust;
	}

	@Column(name = "del")
	public Boolean getDel() {
		return this.del;
	}

	public void setDel(Boolean del) {
		this.del = del;
	}

}