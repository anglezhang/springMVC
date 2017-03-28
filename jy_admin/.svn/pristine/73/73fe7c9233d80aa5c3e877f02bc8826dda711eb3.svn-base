package com.zoomoor.jy.dao.impl;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import com.zoomoor.common.base.bean.Finder;
import com.zoomoor.common.base.bean.Pager;
import com.zoomoor.common.base.dao.impl.BaseDaoImpl;
import com.zoomoor.jy.dao.ServiceitemDao;
import com.zoomoor.jy.dao.ServicetypeDao;
import com.zoomoor.jy.entity.Servicetype;

/**
 * 描述：ServicetypeDaoImpl.java
 * @author Zhangzhenxing
 * @Date 2015年7月20日 上午11:40:24
 * @version 1.0
 */
@Repository
public class ServicetypeDaoImpl extends BaseDaoImpl<Servicetype, Integer> implements ServicetypeDao{

	/**
	* 描述：该方法作用
	* @author zhangZhenxing
	* @Date 2015年7月20日
	*/
	@Override
	public Pager getPager(Pager pager, String queryName) {
		Finder f = Finder.create("FROM Servicetype bean WHERE 1=1 and bean.del=0 ");
		if(StringUtils.isNotEmpty(queryName)){
			f.append(" and bean.servicename like :queryName");
			f.setParam("queryName", "%" + queryName + "%");
		}
		f.append(" order by bean.servicetypeId desc");
		return find(f, pager.getPageNum(), pager.getNumPerPage());
	}

}
