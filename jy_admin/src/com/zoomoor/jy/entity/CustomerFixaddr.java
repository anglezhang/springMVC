package com.zoomoor.jy.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * CustomerFixaddr entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "customer_fixaddr" )
public class CustomerFixaddr implements java.io.Serializable {

	// Fields

	private Integer addrId;
	private String address;
	private String password;
	private String telephone;
	private String detailedaddr;
	private String email;
	private Boolean del;

	// Constructors

	/** default constructor */
	public CustomerFixaddr() {
	}

	/** full constructor */
	public CustomerFixaddr(String address, String password, Boolean del) {
		this.address = address;
		this.password = password;
		this.del = del;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "addr_id", unique = true, nullable = false)
	public Integer getAddrId() {
		return this.addrId;
	}

	public void setAddrId(Integer addrId) {
		this.addrId = addrId;
	}

	@Column(name = "address", length = 80)
	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Column(name = "password", length = 50)
	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name = "del")
	public Boolean getDel() {
		return this.del;
	}

	public void setDel(Boolean del) {
		this.del = del;
	}

	public String getTelephone() {
		return telephone;
	}

	@Column(name = "telephone", length = 50)
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getDetailedaddr() {
		return detailedaddr;
	}

	@Column(name = "detailedaddr", length = 50)
	public void setDetailedaddr(String detailedaddr) {
		this.detailedaddr = detailedaddr;
	}

	public String getEmail() {
		return email;
	}

	@Column(name = "email", length = 50)
	public void setEmail(String email) {
		this.email = email;
	}

}