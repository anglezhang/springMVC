package com.cyw.mammoth.dao.impl;

import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import com.cyw.common.base.dao.impl.BaseDaoImpl;
import com.cyw.mammoth.bean.HgstOri;
import com.cyw.mammoth.dao.HgstOriDao;
import com.cyw.mammoth.vo.GuestSourceVO;

@Repository
public class HgstOriDaoImpl extends BaseDaoImpl<HgstOri, Integer> implements HgstOriDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<HgstOri> findListBy(Integer status) {
		return (List<HgstOri>)getSession()
				.createQuery("from HgstOri t where t.status = ? order by codeId asc")
				.setParameter(0, status)
				.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<GuestSourceVO> getGuestSource() {
		String querySQL = "SELECT h.code_id as codeId"
				+ ",h.code_namec as name,ho.check_type as srcType FROM hgst_ori h "
				+ " LEFT JOIN hgst_ori_type ho ON ho.code_id=h.ori_kind "
				+ " where h.status=0 and ho.status=0 ORDER BY h.code_id ASC ";
		List<GuestSourceVO> list = this.getSession().createSQLQuery(querySQL)
				.addScalar("codeId",Hibernate.INTEGER)
				.addScalar("name",Hibernate.STRING)
				.addScalar("srcType",Hibernate.INTEGER)
				.setResultTransformer(Transformers.aliasToBean(GuestSourceVO.class))
				.list();
		
		return list;
	}

}
