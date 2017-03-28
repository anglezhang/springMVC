package com.zoomoor.jy.dao.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import com.zoomoor.common.base.dao.impl.BaseDaoImpl;
import com.zoomoor.jy.dao.InfoBrandDao;
import com.zoomoor.jy.dao.InfoGoodsTypeDao;
import com.zoomoor.jy.entity.InfoBrand;
import com.zoomoor.jy.entity.InfoGoodsType;
@Repository
public class InfoBrandDaoImpl extends BaseDaoImpl<InfoBrand, Integer> implements
		InfoBrandDao {

	@Override
	public List<InfoBrand> getListByName(String name,Integer upId) {
		String hql="from InfoBrand bean where bean.del=0 ";
		if(StringUtils.isNotEmpty(name)){
			hql+=" and bean.name=? and bean.infoBrand.id="+upId;
			return createQueryList(hql, name);
		}
		hql+=" order by bean.sort asc ";
		return createQueryList(hql);
	}

	@Override
	public List<InfoBrand> getChildById(Integer authid, Integer tpid) {
		String hql = "from InfoBrand bean  where bean.infoBrand.id="+tpid;
		hql+=" order by sort asc";
		return this.createQueryList(hql);
	}

	@Override
	public List getListById(Integer id) {
		String sql="";
		if(id==0){
			sql="from InfoBrand bean  where bean.infoBrand.id is null and bean.del=0 and bean.BLevel!=0 ";
			
		}else{
			sql="from InfoBrand bean  where bean.infoBrand.id="+id+" and bean.del=0 and bean.BLevel!=0 ";
		}
		return createQueryList(sql);
	}

}
