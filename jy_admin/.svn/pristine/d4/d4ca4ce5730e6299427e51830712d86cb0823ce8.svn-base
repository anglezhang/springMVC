package com.zoomoor.jy.dao.impl;

import java.util.Set;

import org.springframework.stereotype.Repository;

import com.zoomoor.common.base.dao.impl.BaseDaoImpl;
import com.zoomoor.jy.dao.SitemgoodsMappingDao;
import com.zoomoor.jy.entity.Serviceitem;
import com.zoomoor.jy.entity.SitemgoodsMapping;

/**
 * 描述：SitemgoodsMappingDaoImpl.java
 * 
 * @author Zhangzhenxing
 * @Date 2015年7月21日 上午11:57:59
 * @version 1.0
 */
@Repository
public class SitemgoodsMappingDaoImpl extends
		BaseDaoImpl<SitemgoodsMapping, Integer> implements SitemgoodsMappingDao {

	/**
	 * 描述：该方法作用
	 * 
	 * @author zhangZhenxing
	 * @Date 2015年7月22日
	 */
	@Override
	public void deleteByServiceitem(Serviceitem service) {
		// 迭代删除服务关系
		String hql="delete from SitemgoodsMapping bean where bean.serviceitem.itemId=?";
		Integer itemId = service.getItemId();
		createQuery(hql, itemId).executeUpdate();
//		Set<SitemgoodsMapping> sitemgoodsMappings = service
//				.getSitemgoodsMappings();
//		if(sitemgoodsMappings != null){
//			for(SitemgoodsMapping siteMap : sitemgoodsMappings){
//				super.delete(siteMap.getItemGoodsId());
//			}
//		}
	}

}
