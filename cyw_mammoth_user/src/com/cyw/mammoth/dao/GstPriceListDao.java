package com.cyw.mammoth.dao;

import com.cyw.common.base.dao.BaseDao;
import com.cyw.mammoth.bean.GstPriceList;

public interface GstPriceListDao extends BaseDao<GstPriceList, Integer> {

	public void updatePrice(Integer id,Double price);
}
