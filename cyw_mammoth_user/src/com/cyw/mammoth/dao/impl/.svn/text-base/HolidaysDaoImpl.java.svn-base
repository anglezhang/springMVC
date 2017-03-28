package com.cyw.mammoth.dao.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import com.cyw.common.base.dao.impl.BaseDaoImpl;
import com.cyw.mammoth.bean.Hcodes;
import com.cyw.mammoth.bean.Holidays;
import com.cyw.mammoth.dao.HolidaysDao;
@Repository
public class HolidaysDaoImpl extends BaseDaoImpl<Holidays, Integer> implements HolidaysDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<Holidays> findListBy(Integer status) throws Exception {
		return (List<Holidays>)getSession().createQuery("SELECT distinct t from Holidays t where t.status = ? order by t.holidayDate asc")
				.setInteger(0, status)
				.list();
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<Map<String, Object>> findListBy(String startDate , String endDate) throws Exception {
		StringBuilder sbd = new StringBuilder() ;
		sbd.append("SELECT DISTINCT t.holiday_id as codeId , t1.code_namec as codeNamec FROM holidays t \n") ;
		sbd.append(" INNER JOIN hcodes t1 ON t.holiday_id = t1.code_id AND t1.status = 0 WHERE t.status = 0 \n") ;
		if(StringUtils.isNotBlank(startDate))
			sbd.append("  and datediff(day,t.holiday_date,'"+startDate+"') >=0 ");
		if(StringUtils.isNotBlank(endDate))	
			sbd.append("and datediff(day,t.holiday_date,'"+endDate+"') <=0  ") ;
		return (List<Map<String, Object>>)getSession().createSQLQuery(sbd.toString()).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();
	}
	
}
