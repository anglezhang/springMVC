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
 * CarCustmerMapping entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "car_custmer_mapping" )
public class CarCustmerMapping implements java.io.Serializable {

	// Fields

	private Integer vehicleContacId;
	private InfoCar infoCar;
	private InfoCustome infoCustome;

	// Constructors

	/** default constructor */
	public CarCustmerMapping() {
	}

	/** full constructor */
	public CarCustmerMapping(InfoCar infoCar, InfoCustome infoCustome) {
		this.infoCar = infoCar;
		this.infoCustome = infoCustome;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "vehicleContac_id", unique = true, nullable = false)
	public Integer getVehicleContacId() {
		return this.vehicleContacId;
	}

	public void setVehicleContacId(Integer vehicleContacId) {
		this.vehicleContacId = vehicleContacId;
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

}