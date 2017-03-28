package com.zoomoor.jy.service;

import com.zoomoor.common.base.bean.Pager;
import com.zoomoor.common.base.service.BaseSvc;
import com.zoomoor.jy.entity.InfoGoods;

public interface InfoGoodsSvc extends BaseSvc<InfoGoods, Integer> {
	public Pager getPage(String queryGoodsCode, String queryName,String queryBrand,
			Integer brandId, int pageNo, int pageSize);

	public InfoGoods[] deleteByIds(Integer[] ids);

	public InfoGoods deleteById(Integer id);

	/**
	 * @param pager
	 *            页面对象
	 * @param queryGoodsCode
	 *            货物编码
	 * @param brandName
	 *            品牌名称
	 * */
	Pager getLookupPage(Pager pager, String queryGoodsCode, String queryName,
			String brandName, String goodIDS, Integer goodsType, Integer brandId);
}
