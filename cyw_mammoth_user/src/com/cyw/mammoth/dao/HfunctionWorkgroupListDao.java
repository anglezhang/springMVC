package com.cyw.mammoth.dao;

import com.cyw.common.base.dao.BaseDao;
import com.cyw.mammoth.bean.HfunctionWorkgroupList;

public interface HfunctionWorkgroupListDao extends BaseDao<HfunctionWorkgroupList, Integer> {

	void deleteBy(String workGroupId , String hfunctionGroup);

}
