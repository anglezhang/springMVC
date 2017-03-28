package com.zoomoor.jy.dao;

import com.zoomoor.common.base.dao.BaseDao;
import com.zoomoor.jy.entity.ConfigEmpMapping;

/**
 * 描述：ConfigEmpMappingDao.java
 * @author Zhangzhenxing
 * @Date 2015年8月8日 下午3:03:50
 * @version 1.0
 */
public interface ConfigEmpMappingDao extends BaseDao<ConfigEmpMapping, Integer>{
	
	/**
	 * 描述:根据员工id删除隐射对象
	 * */
	void deleteByEmpId(Integer empId);

}
