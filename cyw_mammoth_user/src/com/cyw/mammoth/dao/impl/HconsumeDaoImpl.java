package com.cyw.mammoth.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.cyw.common.base.dao.impl.BaseDaoImpl;
import com.cyw.mammoth.bean.Hconsume;
import com.cyw.mammoth.dao.HconsumeDao;
@Repository
public class HconsumeDaoImpl extends BaseDaoImpl<Hconsume, Integer> implements HconsumeDao {

	@SuppressWarnings("rawtypes")
	@Override
	public List getConsumeList(String cons) {
		String sql="select id,code_id codeId,code_namee codeNamee,code_namec codeNamec,status status,svc_rate svcRate,kind,integral,code_kind codeKind,vilhotel_id vilhotelId,chain_id chainId from hconsume hs where code_id not in("+cons+") order by code_id asc ";
		Query query = this.createTransformSqlQuery(sql);
		List list = query.list();
		return list;
	}

}
