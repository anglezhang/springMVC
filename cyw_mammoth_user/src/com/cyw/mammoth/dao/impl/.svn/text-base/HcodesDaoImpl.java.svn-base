package com.cyw.mammoth.dao.impl;

import java.util.List;


import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import com.cyw.common.base.dao.impl.BaseDaoImpl;
import com.cyw.mammoth.bean.Hcodes;
import com.cyw.mammoth.dao.HcodesDao;
@Repository
public class HcodesDaoImpl extends BaseDaoImpl<Hcodes, Integer> implements HcodesDao {

	@Override
	public List<Hcodes> getListByCodes(String codeId) {
		String hql="from Hcodes bean where 1=1";
		if(StringUtils.isNotEmpty(codeId)){
			hql+=" and bean.codeId like '"+codeId+"%' and len(bean.codeId)>3 and bean.status=0 ";
		}
		return this.createQueryList(hql);
	}

}
