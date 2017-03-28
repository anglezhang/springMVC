package com.zoomoor.jy.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zoomoor.common.base.bean.Pager;
import com.zoomoor.common.base.service.impl.BaseSvcImpl;
import com.zoomoor.jy.dao.InfoGoodsDao;
import com.zoomoor.jy.dao.InfoGoodsTypeDao;
import com.zoomoor.jy.entity.InfoGoods;
import com.zoomoor.jy.entity.InfoGoodsType;
import com.zoomoor.jy.service.InfoGoodsSvc;
@Service
public class InfoGoodsSvcImpl extends BaseSvcImpl<InfoGoods, Integer> implements
		InfoGoodsSvc {
	@Resource
	private InfoGoodsTypeDao typeDao;
	@Resource
	private InfoGoodsDao dao;

	@Autowired
	public void setBaseDao(InfoGoodsDao dao) {
		super.setBaseDao(dao);
	}

	@Override
	public Pager getPage(String queryGoodsCode, String queryName,String queryBrand, Integer brandId, int pageNo,
			int pageSize) {
		return dao.getPage(queryGoodsCode, queryName,queryBrand,brandId, pageNo, pageSize);
	}
	@Override
	public InfoGoods deleteById(Integer id) {
		InfoGoods bean = dao.get(id);
		bean.setDel(true);
		dao.update(bean);
		return bean;
	}
	
	@Override
	public InfoGoods[] deleteByIds(Integer[] ids) {
		InfoGoods[] beans = new InfoGoods[ids.length];
		for (int i = 0, len = ids.length; i < len; i++) {
			beans[i] = deleteById(ids[i]);
		}
		return beans;
	}

	/**
	* 描述：该方法作用
	* @author zhangZhenxing
	* @Date 2015年7月21日
	*/
	@Override
	public Pager getLookupPage(Pager pager, String queryGoodsCode,
			String queryName, String brandName,String goodIDS,Integer goodsType,Integer brandId) {
		
		return dao.getLookupPage(pager, queryGoodsCode, queryName, brandName,goodIDS,goodsType,brandId);
	}

}
