package com.cyw.mammoth.dao;

import java.util.List;

import com.cyw.common.base.dao.BaseDao;
import com.cyw.mammoth.bean.Hcodes;

public interface HcodesDao extends BaseDao<Hcodes, Integer> {
	public List<Hcodes> getListByCodes(String codeId);
}
