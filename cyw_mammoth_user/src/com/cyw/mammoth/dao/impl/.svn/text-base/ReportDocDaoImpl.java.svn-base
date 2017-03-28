package com.cyw.mammoth.dao.impl;


import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import com.cyw.common.base.dao.impl.BaseDaoImpl;
import com.cyw.mammoth.bean.ReportDoc;
import com.cyw.mammoth.dao.ReportDocDao;
@Repository
public class ReportDocDaoImpl extends BaseDaoImpl<ReportDoc, String> implements ReportDocDao {

	@SuppressWarnings("rawtypes")
	@Override
	public List getReportDocList(String isShortCut, String rptType, String rptId) {
		String sql = "select a.rpt_id, a.rpt_title, a.rpt_titlee, a.rpt_file, a.rpt_type, a.rpt_sysid, a.rpt_shortcut_allow, a.placeholder_id from report_doc a where 1 = 1 ";
		//若是快捷报表，根据条件查询，否则查全部
		if (StringUtils.isNotEmpty(isShortCut) && isShortCut.equals("1")) {
			sql += "and a.rpt_shortcut_allow = 1 ";
		}
		//1：明细报表，2：汇总报表，3：全部报表
		if (StringUtils.isNotEmpty(rptType) && !rptType.equals("3")) {
			sql += "and a.rpt_type = '"+rptType+"'";
		}
		//报表代码和报表标题模糊匹配条件
		if (StringUtils.isNotEmpty(rptId)) {
			sql += "and (upper(a.rpt_id) like '%"+rptId.toLowerCase()+"%' or upper(a.rpt_title) like '%"+rptId.toLowerCase()+"%' or upper(a.rpt_titlee) like '%"+rptId.toLowerCase()+"%')";
		}
		return this.createTransformSqlQuery(sql).list();
	}
}
