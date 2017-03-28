package com.zoomoor.admin.dao.impl;

import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.zoomoor.admin.dao.SysLogDao;
import com.zoomoor.admin.entity.SysLog;
import com.zoomoor.common.base.bean.Finder;
import com.zoomoor.common.base.bean.Pager;
import com.zoomoor.common.base.dao.impl.BaseDaoImpl;

@Repository
public class SysLogDaoImpl extends BaseDaoImpl<SysLog, Integer> implements SysLogDao {

	public Pager getPage(Integer category, Integer userId, String title, String ip, int pageNo, int pageSize) {
		Finder f = Finder.create("from SysLog bean where 1=1");
		if (category != null) {
			f.append(" and bean.category=:category");
			f.setParam("category", category);
		}
		
		if (userId != null) {
			f.append(" and bean.sysUser.userId=:userId");
			f.setParam("userId", userId);
		}
		if (StringUtils.isNotBlank(title)) {
			f.append(" and bean.title like :title");
			f.setParam("title", "%" + title + "%");
		}
		if (StringUtils.isNotBlank(ip)) {
			f.append(" and bean.ip like :ip");
			f.setParam("ip", ip + "%");
		}
		f.append(" order by bean.logId desc");
		return find(f, pageNo, pageSize);
	}

	@Override
	public SysLog deleteById(Integer id) {
		SysLog entity = super.get(id);
		if (entity != null) {
			getSession().delete(entity);
		}
		return entity;
	}
	@Override
	public int deleteBatch(Integer category, Date before) {
		Finder f = Finder.create("delete SysLog bean where 1=1");
		if (category != null) {
			f.append(" and bean.category=:category");
			f.setParam("category", category);
		}
		if (before != null) {
			f.append(" and bean.logTime<=:before");
			f.setParam("before", before);
		}
		Query q = f.createQuery(getSession());
		return q.executeUpdate();
	}
}