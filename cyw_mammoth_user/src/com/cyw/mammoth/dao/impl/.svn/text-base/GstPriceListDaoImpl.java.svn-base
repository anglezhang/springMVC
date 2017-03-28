package com.cyw.mammoth.dao.impl;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.cyw.common.base.dao.impl.BaseDaoImpl;
import com.cyw.mammoth.bean.GstPriceList;
import com.cyw.mammoth.dao.GstPriceListDao;
@Repository
public class GstPriceListDaoImpl extends BaseDaoImpl<GstPriceList, Integer> implements
		GstPriceListDao {

	@Override
	public void updatePrice(Integer id, Double price) {
		String sql = "update gst_price_list set price="+price+" where id="+id;
		Query query = this.createTransformSqlQuery(sql);
		query.executeUpdate();
	}

}
