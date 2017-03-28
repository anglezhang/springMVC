package com.zoomoor.jy.service;

import java.util.List;

import com.zoomoor.common.base.bean.Pager;
import com.zoomoor.common.base.service.BaseSvc;
import com.zoomoor.jy.entity.InfoBrand;
import com.zoomoor.jy.entity.Serviceitem;

/**
 * 描述：ServiceitemScv.java
 * @author Zhangzhenxing
 * @Date 2015年7月20日 上午9:45:28
 * @version 1.0
 */
public interface ServiceitemScv extends BaseSvc<Serviceitem, Integer>{
	
	/**
	 * 获取分页数据
	 * @param queryItemCode 服务项目编号
	 * @param queryItemName 服务项目名称
	 * */
	Pager getList(Pager pager, String queryItemCode, String queryItemName,String queryPinyin,Integer queryAppType,InfoBrand infoBrand);
	
	/**
	 * 保存配件服务关系 
	 * @param serviceId 
	 * */
	void saveGoodsMapp(Integer serviceId,Serviceitem serviceItem);
	
	void deletById(Integer itemId);
	
	/**
	 * 删除服务项目与配件对应关系
	 * */
	void deleteByService(Serviceitem entity);
	
	/**
	 * Lookup Pager页面
	 * */
	Pager buildList(Pager pager, String queryItemCode, String queryItemName,String itemIdS,Integer carId);
	
	/**
	 * 更新bean对象
	 * */
	boolean updateService(Serviceitem service);
	
	List getServiceItem(String queryItemCode, String queryItemName,
			String itemIdS,Integer carId);

}
