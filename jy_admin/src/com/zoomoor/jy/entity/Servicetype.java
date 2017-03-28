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
 * Servicetype entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="servicetype"
     
)

public class Servicetype  implements java.io.Serializable {


    // Fields    

     private Integer servicetypeId;
     private String servicename;
     private Boolean del;
     private Set<Serviceitem> serviceitems = new HashSet<Serviceitem>(0);


    // Constructors

    /** default constructor */
    public Servicetype() {
    }

    
    /** full constructor */
    public Servicetype(String servicename, Boolean del, Set<Serviceitem> serviceitems) {
        this.servicename = servicename;
        this.del = del;
        this.serviceitems = serviceitems;
    }

   
    // Property accessors
    @Id @GeneratedValue(strategy=IDENTITY)
    
    @Column(name="servicetype_id", unique=true, nullable=false)

    public Integer getServicetypeId() {
        return this.servicetypeId;
    }
    
    public void setServicetypeId(Integer servicetypeId) {
        this.servicetypeId = servicetypeId;
    }
    
    @Column(name="servicename", length=200)

    public String getServicename() {
        return this.servicename;
    }
    
    public void setServicename(String servicename) {
        this.servicename = servicename;
    }
    
    @Column(name="del")

    public Boolean getDel() {
        return this.del;
    }
    
    public void setDel(Boolean del) {
        this.del = del;
    }
    @OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="servicetype")

    public Set<Serviceitem> getServiceitems() {
        return this.serviceitems;
    }
    
    public void setServiceitems(Set<Serviceitem> serviceitems) {
        this.serviceitems = serviceitems;
    }
   








}