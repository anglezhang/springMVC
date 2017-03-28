package com.zoomoor.jy.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.zoomoor.common.base.bean.Finder;
import com.zoomoor.common.base.bean.Pager;
import com.zoomoor.common.base.dao.impl.BaseDaoImpl;
import com.zoomoor.jy.dao.ParamConfigDao;
import com.zoomoor.jy.entity.ParamConfig;
@Repository
public class ParamConfigDaoImpl extends BaseDaoImpl<ParamConfig, Integer> implements
		ParamConfigDao {

	@Override
	public Pager getPage(int pageNo, int pageSize) {
		Finder f = Finder.create("from ParamConfig bean where 1=1 and bean.del=0");
		f.append(" order by bean.unitId desc");
		return find(f, pageNo, pageSize);
	}
	
	@Override
	public ParamConfig deleteById(Integer id) {
		ParamConfig entity = super.get(id);
		if (entity != null) {
			getSession().delete(entity);
		}
		return entity;
	}

	@SuppressWarnings("unchecked")
	public List<ParamConfig> getListByName(String name,Integer paramType) {
		String hql="from ParamConfig bean where bean.del=0 and bean.name='"+name+"' and bean.paramType="+paramType;
		return this.getSession().createQuery(hql).list();
	}


}
