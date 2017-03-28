package com.zoomoor.jy.entity;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Transient;

import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


/**
 * MoneyAuth entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="money_auth"
     
)

public class MoneyAuth  implements java.io.Serializable {


    // Fields    

     private Integer id;
     private String authKey;
     private Double quota;
     private Boolean del;
     private Set<MoneyUserMapping> moneyUserMappings = new HashSet<MoneyUserMapping>(0);
     //是否选中
     private Integer isChecked;

    // Constructors
    @Transient    
    public Integer getIsChecked() {
		return isChecked;
	}


	public void setIsChecked(Integer isChecked) {
		this.isChecked = isChecked;
	}


	/** default constructor */
    public MoneyAuth() {
    }

    
    /** full constructor */
    public MoneyAuth(String authKey, Double quota, Boolean del, Set<MoneyUserMapping> moneyUserMappings) {
        this.authKey = authKey;
        this.quota = quota;
        this.del = del;
        this.moneyUserMappings = moneyUserMappings;
    }

   
    // Property accessors
    @Id @GeneratedValue(strategy=IDENTITY)
    
    @Column(name="id", unique=true, nullable=false)

    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    
    @Column(name="auth_key", length=30)
    public String getAuthKey() {
        return this.authKey;
    }
    
    public void setAuthKey(String authKey) {
        this.authKey = authKey;
    }
    
    @Column(name="quota", precision=5)

    public Double getQuota() {
        return this.quota;
    }
    
    public void setQuota(Double quota) {
        this.quota = quota;
    }
    
    @Column(name="del")

    public Boolean getDel() {
        return this.del;
    }
    
    public void setDel(Boolean del) {
        this.del = del;
    }
@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="moneyAuth")

    public Set<MoneyUserMapping> getMoneyUserMappings() {
        return this.moneyUserMappings;
    }
    
    public void setMoneyUserMappings(Set<MoneyUserMapping> moneyUserMappings) {
        this.moneyUserMappings = moneyUserMappings;
    }
   








}