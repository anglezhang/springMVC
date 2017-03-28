package com.cyw.mammoth.dao;

import java.util.List;

import com.cyw.common.base.dao.BaseDao;
import com.cyw.mammoth.bean.Hconsume;

public interface HconsumeDao extends BaseDao<Hconsume, Integer> {
	
	public List getConsumeList(String cons);

}
