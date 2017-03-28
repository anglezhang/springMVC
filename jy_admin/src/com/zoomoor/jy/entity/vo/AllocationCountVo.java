package com.zoomoor.jy.entity.vo;

import java.util.Date;

public class AllocationCountVo {
	//店铺ID
	private Integer countDeptId;
	//日期
	private Date countDate;
	//店铺名称
	private String countDeptName;
	//要货数量
	private Integer countNum;
	//总金额（成本价）
	private Double countMoney;
	//配件ID
	private Integer countGoodsId;
	//配件名称
	private String countGoodsName;
	//配件编号
	private String countGoodsCode;
	//配件规格
	private String countStandard;
	//配件类型
	private String countTypeShow;
	
	public Integer getCountGoodsId() {
		return countGoodsId;
	}
	public void setCountGoodsId(Integer countGoodsId) {
		this.countGoodsId = countGoodsId;
	}
	public String getCountStandard() {
		return countStandard;
	}
	public void setCountStandard(String countStandard) {
		this.countStandard = countStandard;
	}
	public String getCountTypeShow() {
		return countTypeShow;
	}
	public void setCountTypeShow(String countTypeShow) {
		this.countTypeShow = countTypeShow;
	}
	public Integer getCountDeptId() {
		return countDeptId;
	}
	public void setCountDeptId(Integer countDeptId) {
		this.countDeptId = countDeptId;
	}
	public Date getCountDate() {
		return countDate;
	}
	public void setCountDate(Date countDate) {
		this.countDate = countDate;
	}
	public String getCountDeptName() {
		return countDeptName;
	}
	public void setCountDeptName(String countDeptName) {
		this.countDeptName = countDeptName;
	}
	public Integer getCountNum() {
		return countNum;
	}
	public void setCountNum(Integer countNum) {
		this.countNum = countNum;
	}
	public Double getCountMoney() {
		return countMoney;
	}
	public void setCountMoney(Double countMoney) {
		this.countMoney = countMoney;
	}
	public String getCountGoodsName() {
		return countGoodsName;
	}
	public void setCountGoodsName(String countGoodsName) {
		this.countGoodsName = countGoodsName;
	}
	public String getCountGoodsCode() {
		return countGoodsCode;
	}
	public void setCountGoodsCode(String countGoodsCode) {
		this.countGoodsCode = countGoodsCode;
	}
	
}
