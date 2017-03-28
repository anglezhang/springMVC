package com.zoomoor.jy.service;

import com.zoomoor.common.base.bean.Pager;
import com.zoomoor.common.base.service.BaseSvc;
import com.zoomoor.jy.entity.Servicetype;

/**
 * 描述：ServicetypeSvc服务类型表
 * @author Zhangzhenxing
 * @Date 2015年7月20日 上午11:32:01
 * @version 1.0
 */
public interface ServicetypeSvc extends BaseSvc<Servicetype, Integer> {
	
	/**
	 * 获取分页对象
	 * @param pager 分页对象
	 * @param queryName 服务类型名称
	 * */
	Pager getPager(Pager pager,String queryName);
	
	/**
	 * 描述:删除服务项目
	 * */
	void deleteById(Integer servicetypeId);

}
