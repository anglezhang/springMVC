package com.zoomoor.jy.dao;

import java.util.List;

import com.zoomoor.common.base.bean.Pager;
import com.zoomoor.common.base.dao.BaseDao;
import com.zoomoor.jy.entity.InfoGoods;
import com.zoomoor.jy.entity.vo.CusEntrustGoodsVo;

public interface InfoGoodsDao extends BaseDao<InfoGoods, Integer> {
	public Pager getPage(String queryGoodsCode, String queryName,String queryBrand,
			Integer brandId, int pageNo, int pageSize);

	/**
	 * @param pager
	 *            页面对象
	 * @param queryGoodsCode
	 *            货物编码
	 * @param brandName
	 *            品牌名称
	 * */
	Pager getLookupPage(Pager pager, String queryGoodsCode, String queryName,
			String brandName, String goodIDS, Integer goodsType,Integer brandId);
	
	/**
	 * 根据委托单ID查询货物列表
	 * @param entrustId
	 * */
	List<CusEntrustGoodsVo> getGoodsByEntrust(Integer entrustId);
	
	/**
	 * 根据委托订单查询该委托单货物价格总和
	 * */
	Double getGoodsPriceCount(Integer entrustId);
	
	/**
	 * 描述:根据服务ID查询所需货物
	 * */
	List<InfoGoods> getGoodsBtItemIDs(String itemIDs);
}
