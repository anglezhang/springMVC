package com.cyw.mammoth.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.cyw.common.base.dao.impl.BaseDaoImpl;
import com.cyw.common.util.StrUtils;
import com.cyw.mammoth.bean.Hcodes;
import com.cyw.mammoth.dao.CodeCareDao;

@Repository
public class CodeCareDaoImpl extends BaseDaoImpl<Hcodes, Integer> implements CodeCareDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<Hcodes> findGETypeList() throws Exception {
		List<Hcodes> list = (List<Hcodes>)getSession().createQuery("from Hcodes t where LEN(rtrim(t.codeId)) = 3 and t.status = 0 order by t.codeId asc ").list() ;
		return list;
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<Hcodes> findGEListBy(String flag, Integer status) {
		StringBuilder sbd = new StringBuilder() ;
		sbd.append(" SELECT   \n ") ;
		sbd.append("     t.id,  \n ") ;
		sbd.append("     SUBSTRING(t.code_id, 4, LEN (t.code_id)) as code_id,  \n ") ;
		sbd.append("     t.code_namee,  \n ") ;
		sbd.append("     t.code_namec,  \n ") ;
		sbd.append("     t.code_kind,  \n ") ;
		sbd.append("     t.status,  \n ") ;
		sbd.append("     t.flag,  \n ") ;
		sbd.append("     t.vilhotel_id,  \n ") ;
		sbd.append("     t.chain_id   \n ") ;
		sbd.append(" FROM  \n ") ;
		sbd.append("     hcodes t   \n ") ;
		sbd.append(" WHERE    \n ") ;
		sbd.append("     charindex (?, RTRIM(code_id)) = 1   \n ") ;
		sbd.append("     AND LEN (code_id) > 3   \n ") ;
		sbd.append("     AND t.status = ?   \n ") ;
		sbd.append("  order by t.code_id asc  \n ") ;
		List<Hcodes> list = (List<Hcodes>)getSession()
				.createSQLQuery(sbd.toString()).addEntity(Hcodes.class)
				.setParameter(0, StrUtils.replaceBlank(flag))
				.setParameter(1, status)
				.list();
		getSession().clear();	
		return list;
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<Hcodes> findAllListBy(String flag) {
		StringBuilder sbd = new StringBuilder() ;
		sbd.append(" FROM  \n ") ;
		sbd.append("     Hcodes t   \n ") ;
		sbd.append(" WHERE    \n ") ;
		sbd.append("     charindex (?, RTRIM(codeId)) = 1   \n ") ;
		sbd.append("     AND LEN (codeId) > 3   \n ") ;
		sbd.append("     AND t.status = 0   \n ") ;
		sbd.append("  order by t.status desc , t.codeId asc  \n ") ;
		
		return (List<Hcodes>)getSession().createQuery(sbd.toString()).setParameter(0, flag).list();
	}
	
	
}
