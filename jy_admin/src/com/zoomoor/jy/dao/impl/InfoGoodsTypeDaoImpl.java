package com.zoomoor.jy.dao.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import com.zoomoor.common.base.dao.impl.BaseDaoImpl;
import com.zoomoor.jy.dao.InfoGoodsTypeDao;
import com.zoomoor.jy.entity.InfoGoodsType;
@Repository
public class InfoGoodsTypeDaoImpl extends BaseDaoImpl<InfoGoodsType, Integer> implements
		InfoGoodsTypeDao {

	@Override
	public List<InfoGoodsType> getListByName(String typeName,Integer upid) {
		String hql="from InfoGoodsType bean where bean.del=0 ";
		if(StringUtils.isNotEmpty(typeName)){
			hql+=" and bean.typeName=? and bean.infoGoodsType.groupId="+upid;
			return createQueryList(hql, typeName);
		}
		hql+=" order by bean.sort asc ";
		return createQueryList(hql);
	}

	@Override
	public List<InfoGoodsType> getChildById(Integer authid, Integer tpid) {
		String hql = "from InfoGoodsType bean  where bean.infoGoodsType.groupId="+tpid;
		hql+=" order by sort asc";
		return this.createQueryList(hql);
	}

}
