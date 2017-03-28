package com.zoomoor.jy.dao;

import java.util.List;

import com.zoomoor.common.base.dao.BaseDao;
import com.zoomoor.jy.entity.DepotMoth;

public interface DepotMothDao extends BaseDao<DepotMoth, Integer> {
	public List getMonthList(Integer depId);
	public List getMonthCheck(Integer depId,String curMonth);
	public void checkBalance(Integer depId);
}
