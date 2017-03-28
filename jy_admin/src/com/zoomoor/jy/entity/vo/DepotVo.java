package com.zoomoor.jy.entity.vo;

import com.zoomoor.jy.entity.InfoDept;
import com.zoomoor.jy.entity.InfoGoods;

public class DepotVo {
	//来源 id
	private Integer comeId;
	//部门ID
	private Integer deptId;
	//供应商ID
	private Integer infoSupId;
	//配件ID
	private Integer goodsId;
	//库存
	private Double balance;
	//单位ID
	private Integer unitId;
	//库位ID
	private Integer positionId;
	//库位名称
	private String position;
	private InfoGoods infoGoods;
	
	private InfoDept infoDept;
	
	//目标ID
	private Integer tagId;
	//备注
	private String memo;
	//总数
	private Double countNum;
	//是否含税
	private Integer isIncludeTax;
	//list表主键ID
	private Integer listId;
	
	//实际库存
	private Double accNum;
	
	//盘点单号主键
	private Integer checkId;
	//盘点数量
	private Double checkNum;
	
	
	public Integer getCheckId() {
		return checkId;
	}
	public void setCheckId(Integer checkId) {
		this.checkId = checkId;
	}
	public Double getCheckNum() {
		return checkNum;
	}
	public void setCheckNum(Double checkNum) {
		this.checkNum = checkNum;
	}
	public Double getAccNum() {
		return accNum;
	}
	public void setAccNum(Double accNum) {
		this.accNum = accNum;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public Integer getListId() {
		return listId;
	}
	public void setListId(Integer listId) {
		this.listId = listId;
	}
	public Integer getIsIncludeTax() {
		return isIncludeTax;
	}
	public void setIsIncludeTax(Integer isIncludeTax) {
		this.isIncludeTax = isIncludeTax;
	}
	public Double getCountNum() {
		return countNum;
	}
	public void setCountNum(Double countNum) {
		this.countNum = countNum;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	public Integer getTagId() {
		return tagId;
	}
	public void setTagId(Integer tagId) {
		this.tagId = tagId;
	}
	public InfoGoods getInfoGoods() {
		return infoGoods;
	}
	public void setInfoGoods(InfoGoods infoGoods) {
		this.infoGoods = infoGoods;
	}
	public InfoDept getInfoDept() {
		return infoDept;
	}
	public void setInfoDept(InfoDept infoDept) {
		this.infoDept = infoDept;
	}
	public Integer getComeId() {
		return comeId;
	}
	public void setComeId(Integer comeId) {
		this.comeId = comeId;
	}
	public Integer getDeptId() {
		return deptId;
	}
	public void setDeptId(Integer deptId) {
		this.deptId = deptId;
	}
	public Integer getInfoSupId() {
		return infoSupId;
	}
	public void setInfoSupId(Integer infoSupId) {
		this.infoSupId = infoSupId;
	}
	public Integer getGoodsId() {
		return goodsId;
	}
	public void setGoodsId(Integer goodsId) {
		this.goodsId = goodsId;
	}
	public Double getBalance() {
		return balance;
	}
	public void setBalance(Double balance) {
		this.balance = balance;
	}
	public Integer getUnitId() {
		return unitId;
	}
	public void setUnitId(Integer unitId) {
		this.unitId = unitId;
	}
	public Integer getPositionId() {
		return positionId;
	}
	public void setPositionId(Integer positionId) {
		this.positionId = positionId;
	}
	
}
