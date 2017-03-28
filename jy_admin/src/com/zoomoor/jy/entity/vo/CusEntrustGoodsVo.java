package com.zoomoor.jy.entity.vo;

import com.zoomoor.jy.entity.InfoGoods;

/**
 * 描述：服务委托单列表
 * @author Zhangzhenxing
 * @Date 2015年8月17日 上午11:39:06
 * @version 1.0
 */
public class CusEntrustGoodsVo {
	
	private InfoGoods good;//货物
	private String goodNO;//配件编号
	private String goodName;//配件名称
	private Double price;//单价
	private Double num;//数量
	private String unit;//单位
	private String goodBrand;//配件名称
	private Double sumPrice;//总价
	private String standard;//配件规格
	
	public CusEntrustGoodsVo() {
		super();
	}
	public InfoGoods getGood() {
		return good;
	}
	public void setGood(InfoGoods good) {
		this.good = good;
	}
	public String getGoodNO() {
		return goodNO;
	}
	public void setGoodNO(String goodNO) {
		this.goodNO = goodNO;
	}
	public String getGoodName() {
		return goodName;
	}
	public void setGoodName(String goodName) {
		this.goodName = goodName;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public Double getNum() {
		return num;
	}
	public void setNum(Double num) {
		this.num = num;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public String getGoodBrand() {
		return goodBrand;
	}
	public void setGoodBrand(String goodBrand) {
		this.goodBrand = goodBrand;
	}
	public Double getSumPrice() {
		return sumPrice;
	}
	public void setSumPrice(Double sumPrice) {
		this.sumPrice = sumPrice;
	}
	public String getStandard() {
		return standard;
	}
	public void setStandard(String standard) {
		this.standard = standard;
	}
	
	

}
