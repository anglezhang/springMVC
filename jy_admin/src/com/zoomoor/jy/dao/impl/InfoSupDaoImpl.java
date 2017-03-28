package com.zoomoor.jy.dao.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import com.zoomoor.common.base.bean.Finder;
import com.zoomoor.common.base.bean.Pager;
import com.zoomoor.common.base.dao.impl.BaseDaoImpl;
import com.zoomoor.jy.dao.InfoSupDao;
import com.zoomoor.jy.entity.InfoSup;
@Repository
public class InfoSupDaoImpl extends BaseDaoImpl<InfoSup, Integer> implements InfoSupDao {
	@Override
	public List<InfoSup> getListByCode(String code) {
		String hql="from InfoSup bean where bean.del=0 and bean.code='"+code+"'";
		return createQueryList(hql);
	}

	@Override
	public Pager getPage(String queryName, String queryCode,
			String queryLinkMan, String queryPhone, int pageNo, int pageSize) {
		Finder f = Finder.create("from InfoSup bean where 1=1 and bean.del=0");
		if(StringUtils.isNotEmpty(queryName)){
			f.append(" and  bean.name like:queryName");
			f.setParam("queryName","%"+queryName+"%" );
		}
		if(StringUtils.isNotEmpty(queryCode)){
			f.append(" and  bean.code like:queryCode");
			f.setParam("queryCode","%"+queryCode+"%" );
		}
		if(StringUtils.isNotEmpty(queryLinkMan)){
			f.append(" and  bean.linkMan like:queryLinkMan");
			f.setParam("queryLinkMan","%"+queryLinkMan+"%" );
		}
		if(StringUtils.isNotEmpty(queryPhone)){
			f.append(" and  bean.phone like:queryPhone");
			f.setParam("queryPhone","%"+queryPhone+"%" );
		}
		f.append(" order by bean.id desc");
		return find(f, pageNo, pageSize);
	}

}
