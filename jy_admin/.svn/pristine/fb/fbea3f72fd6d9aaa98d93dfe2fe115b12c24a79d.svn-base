package com.zoomoor.jy.entity;

import static javax.persistence.GenerationType.IDENTITY;

import java.sql.Timestamp;
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

import com.zoomoor.jy.entity.vo.DepotVo;


/**
 * DepotBill entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="depot_bill"
     
)

public class DepotBill  implements java.io.Serializable {


    // Fields    

     private Integer billId;
     private InfoDept infoDept;
     private InfoSummary infoSummary;
     private Timestamp billDate;
     private String memo;
     private Boolean del;
     private Set<DepotBillList> depotBillLists = new HashSet<DepotBillList>(0);
     private InfoDepotPosition[] infoDepotPosition;
     
     private InfoDepotPosition depotPosition;
     private InfoBrand infoBrand;
     private InfoGoodsType infoGoodsType;
     
     
     @Transient
     public InfoDepotPosition getDepotPosition() {
		return depotPosition;
	}


	public void setDepotPosition(InfoDepotPosition depotPosition) {
		this.depotPosition = depotPosition;
	}

	 @Transient
	public InfoBrand getInfoBrand() {
		return infoBrand;
	}


	public void setInfoBrand(InfoBrand infoBrand) {
		this.infoBrand = infoBrand;
	}

	 @Transient
	public InfoGoodsType getInfoGoodsType() {
		return infoGoodsType;
	}


	public void setInfoGoodsType(InfoGoodsType infoGoodsType) {
		this.infoGoodsType = infoGoodsType;
	}

	//用户id
     private Integer empId;
     
     
     @Transient
     public Integer getEmpId() {
		return empId;
	}


	public void setEmpId(Integer empId) {
		this.empId = empId;
	}

	private DepotVo[] deptvo;
     //子表信息
     private String[] positions;
	@Transient
     public String[] getPositions() {
		return positions;
	}


	public void setPositions(String[] positions) {
		this.positions = positions;
	}


	@Transient
    public DepotVo[] getDeptvo() {
		return deptvo;
	}


	public void setDeptvo(DepotVo[] deptvo) {
		this.deptvo = deptvo;
	}


	// Constructors
    @Transient
    public InfoDepotPosition[] getInfoDepotPosition() {
		return infoDepotPosition;
	}


	public void setInfoDepotPosition(InfoDepotPosition[] infoDepotPosition) {
		this.infoDepotPosition = infoDepotPosition;
	}


	/** default constructor */
    public DepotBill() {
    }

    
    /** full constructor */
    public DepotBill(InfoDept infoDept, InfoSummary infoSummary, Timestamp billDate, String memo, Boolean del, Set<DepotBillList> depotBillLists) {
        this.infoDept = infoDept;
        this.infoSummary = infoSummary;
        this.billDate = billDate;
        this.memo = memo;
        this.del = del;
        this.depotBillLists = depotBillLists;
    }

   
    // Property accessors
    @Id @GeneratedValue(strategy=IDENTITY)
    
    @Column(name="bill_id", unique=true, nullable=false)

    public Integer getBillId() {
        return this.billId;
    }
    
    public void setBillId(Integer billId) {
        this.billId = billId;
    }
	@ManyToOne(fetch=FetchType.LAZY)
        @JoinColumn(name="dep_id")

    public InfoDept getInfoDept() {
        return this.infoDept;
    }
    
    public void setInfoDept(InfoDept infoDept) {
        this.infoDept = infoDept;
    }
	@ManyToOne(fetch=FetchType.LAZY)
        @JoinColumn(name="summary_id")

    public InfoSummary getInfoSummary() {
        return this.infoSummary;
    }
    
    public void setInfoSummary(InfoSummary infoSummary) {
        this.infoSummary = infoSummary;
    }
    
    @Column(name="bill_date", length=19)

    public Timestamp getBillDate() {
        return this.billDate;
    }
    
    public void setBillDate(Timestamp billDate) {
        this.billDate = billDate;
    }
    
    @Column(name="memo", length=500)

    public String getMemo() {
        return this.memo;
    }
    
    public void setMemo(String memo) {
        this.memo = memo;
    }
    
    @Column(name="del")

    public Boolean getDel() {
        return this.del;
    }
    
    public void setDel(Boolean del) {
        this.del = del;
    }
@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="depotBill")

    public Set<DepotBillList> getDepotBillLists() {
        return this.depotBillLists;
    }
    
    public void setDepotBillLists(Set<DepotBillList> depotBillLists) {
        this.depotBillLists = depotBillLists;
    }
   








}