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
 * SitemgoodsMapping entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="sitemgoods_mapping"
     
)

public class SitemgoodsMapping  implements java.io.Serializable {


    // Fields    

     private Integer itemGoodsId;
     private Serviceitem serviceitem;
     private InfoGoods infoGoods;
     private Integer number;
     private Boolean del;


    // Constructors

    /** default constructor */
    public SitemgoodsMapping() {
    }

    
    /** full constructor */
    public SitemgoodsMapping(Serviceitem serviceitem, InfoGoods infoGoods, Integer number, Boolean del) {
        this.serviceitem = serviceitem;
        this.infoGoods = infoGoods;
        this.number = number;
        this.del = del;
    }

   
    // Property accessors
    @Id @GeneratedValue(strategy=IDENTITY)
    
    @Column(name="item_goods_id", unique=true, nullable=false)

    public Integer getItemGoodsId() {
        return this.itemGoodsId;
    }
    
    public void setItemGoodsId(Integer itemGoodsId) {
        this.itemGoodsId = itemGoodsId;
    }
	@ManyToOne(fetch=FetchType.LAZY)
        @JoinColumn(name="item_id")

    public Serviceitem getServiceitem() {
        return this.serviceitem;
    }
    
    public void setServiceitem(Serviceitem serviceitem) {
        this.serviceitem = serviceitem;
    }
	@ManyToOne(fetch=FetchType.LAZY)
        @JoinColumn(name="goods_id")

    public InfoGoods getInfoGoods() {
        return this.infoGoods;
    }
    
    public void setInfoGoods(InfoGoods infoGoods) {
        this.infoGoods = infoGoods;
    }
    
    @Column(name="number")

    public Integer getNumber() {
        return this.number;
    }
    
    public void setNumber(Integer number) {
        this.number = number;
    }
    
    @Column(name="del")

    public Boolean getDel() {
        return this.del;
    }
    
    public void setDel(Boolean del) {
        this.del = del;
    }
   








}