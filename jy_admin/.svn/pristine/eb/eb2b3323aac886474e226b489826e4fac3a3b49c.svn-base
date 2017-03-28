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
 * InfoGoodsType entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="info_goods_type"
     
)

public class InfoGoodsType  implements java.io.Serializable {


    // Fields    

     private Integer groupId;
     private InfoGoodsType infoGoodsType;
     private String typeName;
     private Integer sort;
     private Boolean del;
     private Set<InfoGoods> infoGoodses = new HashSet<InfoGoods>(0);
     private Set<InfoGoodsType> infoGoodsTypes = new HashSet<InfoGoodsType>(0);


    // Constructors

    /** default constructor */
    public InfoGoodsType() {
    }

    
    /** full constructor */
    public InfoGoodsType(InfoGoodsType infoGoodsType, String typeName, Integer sort, Boolean del, Set<InfoGoods> infoGoodses, Set<InfoGoodsType> infoGoodsTypes) {
        this.infoGoodsType = infoGoodsType;
        this.typeName = typeName;
        this.sort = sort;
        this.del = del;
        this.infoGoodses = infoGoodses;
        this.infoGoodsTypes = infoGoodsTypes;
    }

   
    // Property accessors
    @Id @GeneratedValue(strategy=IDENTITY)
    
    @Column(name="group_id", unique=true, nullable=false)

    public Integer getGroupId() {
        return this.groupId;
    }
    
    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }
	@ManyToOne(fetch=FetchType.LAZY)
        @JoinColumn(name="up_id")

    public InfoGoodsType getInfoGoodsType() {
        return this.infoGoodsType;
    }
    
    public void setInfoGoodsType(InfoGoodsType infoGoodsType) {
        this.infoGoodsType = infoGoodsType;
    }
    
    @Column(name="type_name", length=30)

    public String getTypeName() {
        return this.typeName;
    }
    
    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }
    
    @Column(name="sort")

    public Integer getSort() {
        return this.sort;
    }
    
    public void setSort(Integer sort) {
        this.sort = sort;
    }
    
    @Column(name="del")

    public Boolean getDel() {
        return this.del;
    }
    
    public void setDel(Boolean del) {
        this.del = del;
    }
@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="infoGoodsType")

    public Set<InfoGoods> getInfoGoodses() {
        return this.infoGoodses;
    }
    
    public void setInfoGoodses(Set<InfoGoods> infoGoodses) {
        this.infoGoodses = infoGoodses;
    }
@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="infoGoodsType")

    public Set<InfoGoodsType> getInfoGoodsTypes() {
        return this.infoGoodsTypes;
    }
    
    public void setInfoGoodsTypes(Set<InfoGoodsType> infoGoodsTypes) {
        this.infoGoodsTypes = infoGoodsTypes;
    }
   








}