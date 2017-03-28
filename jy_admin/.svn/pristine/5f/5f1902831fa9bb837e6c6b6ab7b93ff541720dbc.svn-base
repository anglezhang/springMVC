package com.zoomoor.jy.service.impl;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zoomoor.common.base.bean.Pager;
import com.zoomoor.common.base.service.impl.BaseSvcImpl;
import com.zoomoor.jy.dao.ServicetypeDao;
import com.zoomoor.jy.entity.Servicetype;
import com.zoomoor.jy.service.ServicetypeSvc;

/**
 * 描述：ServicetypeSvcImpl.java
 * @author Zhangzhenxing
 * @Date 2015年7月20日 上午11:36:47
 * @version 1.0
 */
@Service
public class ServicetypeSvcImpl extends BaseSvcImpl<Servicetype, Integer> implements ServicetypeSvc{
	
	@Resource
	private ServicetypeDao dao;
	
	@Autowired
	public void setBaseDao(ServicetypeDao dao){
		super.setBaseDao(dao);
	}

	/**
	* 描述：该方法作用
	* @author zhangZhenxing
	* @Date 2015年7月20日
	*/
	@Override
	public Pager getPager(Pager pager, String queryName) {
		
		return dao.getPager(pager, queryName);
	}

	/**
	* 描述：该方法作用
	* @author zhangZhenxing
	* @Date 2015年7月30日
	*/
	@Override
	public void deleteById(Integer servicetypeId) {
		Servicetype entity = dao.get(servicetypeId);
		entity.setDel(true);
		dao.update(entity);
	}

}
