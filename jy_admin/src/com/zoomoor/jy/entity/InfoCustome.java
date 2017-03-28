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


/**
 * InfoCustome entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="info_custome"
     
)

public class InfoCustome  implements java.io.Serializable {


    // Fields    

     private Integer customerId;
     private String customerName;
     private String tel;
     private Boolean gender;
     private String weixin;
     private String pcUsername;
     private String pcPasswd;
     private String eamil;
     private Integer custype;
     private String province;
     private String city;
     private String detaladdress;
     private Integer intergral;
     private Integer income;//收入水平
     private Boolean del;
     private Set<CarCustmerMapping> carCustmerMappings = new HashSet<CarCustmerMapping>(0);


    // Constructors

    /** default constructor */
    public InfoCustome() {
    }

    
    /** full constructor */
    public InfoCustome(String customerName, String tel, Boolean gender, String weixin, String pcUsername, String pcPasswd, String eamil, Integer intergral, Boolean del, Set<CarCustmerMapping> carCustmerMappings, Set<InfoCar> infoCars) {
        this.customerName = customerName;
        this.tel = tel;
        this.gender = gender;
        this.weixin = weixin;
        this.pcUsername = pcUsername;
        this.pcPasswd = pcPasswd;
        this.eamil = eamil;
        this.intergral = intergral;
        this.del = del;
        this.carCustmerMappings = carCustmerMappings;
    }

   
    // Property accessors
    @Id @GeneratedValue(strategy=IDENTITY)
    
    @Column(name="customer_id", unique=true, nullable=false)

    public Integer getCustomerId() {
        return this.customerId;
    }
    
    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }
    
    @Column(name="customer_name", length=80)

    public String getCustomerName() {
        return this.customerName;
    }
    
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
    
    @Column(name="tel", length=30)

    public String getTel() {
        return this.tel;
    }
    
    public void setTel(String tel) {
        this.tel = tel;
    }
    
    @Column(name="gender")

    public Boolean getGender() {
        return this.gender;
    }
    
    public void setGender(Boolean gender) {
        this.gender = gender;
    }
    
    @Column(name="weixin", length=30)

    public String getWeixin() {
        return this.weixin;
    }
    
    public void setWeixin(String weixin) {
        this.weixin = weixin;
    }
    
    @Column(name="pc_username", length=50)

    public String getPcUsername() {
        return this.pcUsername;
    }
    
    public void setPcUsername(String pcUsername) {
        this.pcUsername = pcUsername;
    }
    
    @Column(name="pc_passwd", length=50)

    public String getPcPasswd() {
        return this.pcPasswd;
    }
    
    public void setPcPasswd(String pcPasswd) {
        this.pcPasswd = pcPasswd;
    }
    
    @Column(name="eamil", length=50)
    public String getEamil() {
        return this.eamil;
    }
    
    public void setEamil(String eamil) {
        this.eamil = eamil;
    }
    
    @Column(name="intergral")

    public Integer getIntergral() {
        return this.intergral;
    }
    
    public void setIntergral(Integer intergral) {
        this.intergral = intergral;
    }
    
    @Column(name="del")

    public Boolean getDel() {
        return this.del;
    }
    
    public void setDel(Boolean del) {
        this.del = del;
    }
    @OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="infoCustome")

    public Set<CarCustmerMapping> getCarCustmerMappings() {
        return this.carCustmerMappings;
    }
    
    public void setCarCustmerMappings(Set<CarCustmerMapping> carCustmerMappings) {
        this.carCustmerMappings = carCustmerMappings;
    }


    @Column(name="custype")
	public Integer getCustype() {
		return custype;
	}


	public void setCustype(Integer custype) {
		this.custype = custype;
	}


	@Column(name="province", length=20)
	public String getProvince() {
		return province;
	}


	public void setProvince(String province) {
		this.province = province;
	}


	@Column(name="city", length=20)
	public String getCity() {
		return city;
	}


	public void setCity(String city) {
		this.city = city;
	}


	@Column(name="detaladdress", length=50)
	public String getDetaladdress() {
		return detaladdress;
	}


	public void setDetaladdress(String detaladdress) {
		this.detaladdress = detaladdress;
	}


	@Column(name="income", length=50)
	public Integer getIncome() {
		return income;
	}


	public void setIncome(Integer income) {
		this.income = income;
	}

	
    
}