package com.zoomoor.admin.dao.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import com.zoomoor.admin.dao.SysRoleDao;
import com.zoomoor.admin.entity.SysRole;
import com.zoomoor.common.base.bean.Finder;
import com.zoomoor.common.base.bean.Pager;
import com.zoomoor.common.base.dao.impl.BaseDaoImpl;

@Repository
public class SysRoleDaoImpl extends BaseDaoImpl<SysRole, Integer> implements SysRoleDao {
	
	@Override
	public SysRole deleteById(Integer id) {
		SysRole entity = super.get(id);
		//删除角色金额折扣权限中间表信息
		String sql="delete from money_user_mapping where role_id="+id;
		getSession().createSQLQuery(sql).executeUpdate();
		if (entity != null) {
			getSession().delete(entity);
		}
		return entity;
	}

	@Override
	public Boolean getUserByRoleIds(Integer[] ids) {
		Boolean isuser=false;
		if(ids!=null){
			for(Integer id:ids){
				String sql="select *from sys_user_role u where u.role_id="+id;
				List list =this.getSession().createSQLQuery(sql).list();
				if(list!=null&&list.size()>0){
					isuser=true;
					break;
				}
			}
		}
		return isuser;
	}

	@Override
	public Pager getPage(Pager pager, int pagerNum, int numPerPage) {
		Finder f = Finder.create("from SysRole bean where 1=1 and del=0");
		f.append(" order by bean.roleId desc");
		return find(f, pagerNum, numPerPage);
	}
}