package com.zoomoor.jy.dao.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import com.zoomoor.common.base.bean.Finder;
import com.zoomoor.common.base.dao.impl.BaseDaoImpl;
import com.zoomoor.jy.dao.CustomerFixaddrDao;
import com.zoomoor.jy.entity.CustomerFixaddr;

/**
 * 描述：CustomerFixaddrDaoImpl.java
 * @author Zhangzhenxing
 * @Date 2015年8月4日 上午9:51:27
 * @version 1.0
 */
@Repository
public class CustomerFixaddrDaoImpl extends BaseDaoImpl<CustomerFixaddr, Integer> implements CustomerFixaddrDao{

	/**
	* 描述：该方法作用
	* @author zhangZhenxing
	* @Date 2015年8月4日
	*/
	@Override
	public List<CustomerFixaddr> getAddrsList(String addrName) {
		Finder f = Finder.create("FROM CustomerFixaddr bean WHERE 1=1 and bean.del=0");
		if(StringUtils.isNotEmpty(addrName)){
			f.append(" and bean.address lile :addrName");
			f.setParam("addrName", "%" + addrName + "%");
		}
		return find(f);
	}

}
