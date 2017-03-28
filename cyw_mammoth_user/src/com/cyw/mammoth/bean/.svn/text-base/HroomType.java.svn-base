package com.cyw.mammoth.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * HroomType entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "hroom_type", schema = "dbo", catalog = "mammoth", uniqueConstraints = @UniqueConstraint(columnNames = "code_id"))
public class HroomType implements java.io.Serializable {

	private static final long serialVersionUID = 1059145078880781415L;
	
	// Fields
	private Integer id;              //  序号
	private String codeId;           //  房类代码
	private String codeNamee;        //  房类英文名
	private String codeNamec;        //  房类中文名
	private Integer status;          //  状态
	private Double price;            //  标准租金
	private String buildingId;       //  建筑物代码
	private Integer codeKind;        //  
	private Short num;               //  该类房数量
	private String vilhotelId;       //  总店ID
	private String chainId;          //  连锁店ID
                                     
	// Constructors                  
                                     
	/** default constructor */       
	public HroomType() {             
	}                                
                                      
	/** minimal constructor */
	public HroomType(Integer id, String codeId, String codeNamee,
			String codeNamec, Integer status, Double price, String buildingId,
			Integer codeKind, Short num) {
		this.id = id;
		this.codeId = codeId;
		this.codeNamee = codeNamee;
		this.codeNamec = codeNamec;
		this.status = status;
		this.price = price;
		this.buildingId = buildingId;
		this.codeKind = codeKind;
		this.num = num;
	}

	/** full constructor */
	public HroomType(Integer id, String codeId, String codeNamee,
			String codeNamec, Integer status, Double price, String buildingId,
			Integer codeKind, Short num, String vilhotelId, String chainId) {
		this.id = id;
		this.codeId = codeId;
		this.codeNamee = codeNamee;
		this.codeNamec = codeNamec;
		this.status = status;
		this.price = price;
		this.buildingId = buildingId;
		this.codeKind = codeKind;
		this.num = num;
		this.vilhotelId = vilhotelId;
		this.chainId = chainId;
	}

	// Property accessors
	@Id
	@GeneratedValue
	@Column(name = "ID", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "code_id", unique = true, nullable = false, length = 4)
	public String getCodeId() {
		return this.codeId;
	}

	public void setCodeId(String codeId) {
		this.codeId = codeId;
	}

	@Column(name = "code_namee", nullable = false, length = 40)
	public String getCodeNamee() {
		return this.codeNamee;
	}

	public void setCodeNamee(String codeNamee) {
		this.codeNamee = codeNamee;
	}

	@Column(name = "code_namec", nullable = false, length = 30)
	public String getCodeNamec() {
		return this.codeNamec;
	}

	public void setCodeNamec(String codeNamec) {
		this.codeNamec = codeNamec;
	}

	@Column(name = "status", nullable = false)
	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	@Column(name = "price", nullable = false, precision = 15, scale = 2)
	public Double getPrice() {
		return this.price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	@Column(name = "building_id", nullable = false, length = 6)
	public String getBuildingId() {
		return this.buildingId;
	}

	public void setBuildingId(String buildingId) {
		this.buildingId = buildingId;
	}

	@Column(name = "code_kind", nullable = false)
	public Integer getCodeKind() {
		return this.codeKind;
	}

	public void setCodeKind(Integer codeKind) {
		this.codeKind = codeKind;
	}

	@Column(name = "num", nullable = false)
	public Short getNum() {
		return this.num;
	}

	public void setNum(Short num) {
		this.num = num;
	}

	@Column(name = "vilhotel_id", length = 50)
	public String getVilhotelId() {
		return this.vilhotelId;
	}

	public void setVilhotelId(String vilhotelId) {
		this.vilhotelId = vilhotelId;
	}

	@Column(name = "chain_id", length = 50)
	public String getChainId() {
		return this.chainId;
	}

	public void setChainId(String chainId) {
		this.chainId = chainId;
	}
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}