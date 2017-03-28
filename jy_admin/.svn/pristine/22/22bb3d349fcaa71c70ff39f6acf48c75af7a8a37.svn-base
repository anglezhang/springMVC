package com.zoomoor.jy.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.zoomoor.common.base.bean.Finder;
import com.zoomoor.common.base.bean.Pager;
import com.zoomoor.common.base.dao.impl.BaseDaoImpl;
import com.zoomoor.jy.dao.InfoDeptDao;
import com.zoomoor.jy.entity.InfoDept;
import com.zoomoor.jy.entity.InfoGoodsType;

@Repository
public class InfoDeptDaoImpl extends BaseDaoImpl<InfoDept, Integer> implements
		InfoDeptDao {

	@Override
	public List<InfoDept> getListByName(String DeptName, Integer upid) {
		String hql = "from InfoDept bean where bean.del=0 ";
		if (StringUtils.isNotEmpty(DeptName)) {
			hql += " and bean.name=? and bean.infoDept.deptId=" + upid;
			return createQueryList(hql, DeptName);
		}
		hql += " order by bean.sort asc ";
		return createQueryList(hql);
	}

	@Override
	public List<InfoDept> getChildById(Integer authid, Integer tpid) {
		String hql = "from InfoDept bean  where bean.infoDept.deptId=" + tpid;
		hql += " order by sort asc";
		return this.createQueryList(hql);
	}

	/**
	 * 描述：该方法作用
	 * 
	 * @author zhangZhenxing
	 * @Date 2015年7月27日
	 */
	@Override
	public Pager getLookupPager(Pager pager,String queryDeptName, String queryUpDeptName) {
		Finder f = Finder
				.create("FROM InfoDept bean WHERE 1=1 and bean.del=0 and bean.isShop=1");
		if(StringUtils.isNotEmpty(queryDeptName)){
			f.append(" and bean.name like :queryDeptName");
			f.setParam("queryDeptName", queryDeptName);
			
		}
		if(StringUtils.isNotEmpty(queryUpDeptName)){
			f.append(" and bean.infoDept.name like :queryUpDeptName");
			f.setParam("queryUpDeptName", queryUpDeptName);
		}
		return find(f, pager.getPageNum(), pager.getNumPerPage());
	}

	@Override
	public List<InfoDept> getListById(Integer deptId) {
		String hql = "from InfoDept bean  where (bean.infoDept.deptId=" + deptId +" or bean.deptId="+deptId+") and del=0 and isShop=1";
		return this.createQueryList(hql);
	}
}
