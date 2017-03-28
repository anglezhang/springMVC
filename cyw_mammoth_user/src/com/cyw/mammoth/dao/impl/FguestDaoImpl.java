package com.cyw.mammoth.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Hibernate;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.cyw.common.base.dao.impl.BaseDaoImpl;
import com.cyw.common.util.DateUtils;
import com.cyw.common.util.StrUtils;
import com.cyw.mammoth.bean.Fguest;
import com.cyw.mammoth.dao.FguestDao;
import com.cyw.mammoth.vo.FguestInfoVO;
import com.cyw.mammoth.vo.FguestSearchVo;
import com.cyw.mammoth.vo.FguestVo;
import com.google.common.collect.Lists;
import com.googlecode.svntask.command.Add;

@Repository
public class FguestDaoImpl extends BaseDaoImpl<Fguest, Integer> implements
		FguestDao {

	private DataSource dataSource;
	private JdbcTemplate jdbcTemplate;

	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public List<FguestVo> search(FguestVo searchVo) {
		StringBuilder hqlBuilder = new StringBuilder();
		hqlBuilder
				.append("SELECT  f.gst_id gstId,f.gst_namec gstNamec,f.gst_namee gstNamee,f.sex,f.mobile,f.crd_id crdId,f.notice,f.bankId bankId,f.status,f.visakind_id visakindId,f.visa_id visaId,f.depart depart,f.in_date inDate,f.in_port inPort,f.room_character roomCharacter, t.namec,t.TA_type compType,t.id compId FROM fguest f LEFT JOIN ta_doc t ON f.comp_id=t.id where 1=1");
		// hqlBuilder.append("SELECT f.gstId,f.gstNamec,f.gstNamee,f.sex,f.mobile,f.crdId,f.notice,t.namec unitNamec FROM Fguest f LEFT JOIN com.cyw.mammoth.bean.TaDoc t with f.compId=t.id where 1=1");
		// hqlBuilder.append("SELECT new com.cyw.mammoth.vo.FguestVo() FROM Fguest f LEFT JOIN TaDoc t with f.compId=t.id where 1=1");
		if (StrUtils.isValidString(searchVo.getCodeLetter())) {
			// #号 35 *号42
			int chatnum = (int) searchVo.getCodeLetter().charAt(0);
			if (chatnum == 35) {
				// #号
				hqlBuilder.append(" and f.status = 0  ");
			} else if (chatnum == 42) {
				// *号
				hqlBuilder.append(" and f.status = 1  ");
			} else {
				hqlBuilder.append(" and f.gst_namee like '"
						+ searchVo.getCodeLetter() + "%'");
				if(searchVo.getStatus()==0){//有效
					hqlBuilder.append( " and f.status = 0  ");
				}else{
					hqlBuilder.append( " and f.status = 1  ");
				}
			}
		} else {
			if (searchVo.getGstId() != null) {
				hqlBuilder.append(" and f.gst_id like '" + searchVo.getGstId()
						+ "%' ");
			}
			if (StrUtils.isValidString(searchVo.getGstNamee())) {
				hqlBuilder.append(" and f.gst_namee like '%"
						+ searchVo.getGstNamee() + "%' ");
			}
			if (StrUtils.isValidString(searchVo.getGstNamec())) {
				hqlBuilder.append(" and f.gst_namec like '%"
						+ searchVo.getGstNamec() + "%' ");
			}
			if (StrUtils.isValidString(searchVo.getMobile())) {
				hqlBuilder.append(" and f.mobile like '%"
						+ searchVo.getMobile() + "%' ");
			}
			if (StrUtils.isValidString(searchVo.getCrdId())) {
				hqlBuilder.append(" and f.crd_id like '" + searchVo.getCrdId()
						+ "%' ");
			}
			if (StrUtils.isValidString(searchVo.getUnitNamec())) {
				hqlBuilder.append(" and t.namec like '"
						+ searchVo.getUnitNamec() + "%' ");
			}
			if (searchVo.getStatus() != null
					&& searchVo.getStatus().intValue() == 0) {
				hqlBuilder.append(" and f.status = 0  ");
			}
			if (searchVo.getStatus() != null
					&& searchVo.getStatus().intValue() == 1) {
				hqlBuilder.append(" and f.status = 1  ");
			}
			if (searchVo.getReachDateStart() != null) {
				hqlBuilder.append(" and f.reach_date >'"
						+ DateUtils.dateFormat2.format(searchVo
								.getReachDateStart()) + " 00:00'");
			}
			if (searchVo.getReachDateEnd() != null) {
				hqlBuilder.append(" and f.reach_date <'"
						+ DateUtils.dateFormat2.format(searchVo
								.getReachDateEnd()) + " 23:59'");
			}
		}
		hqlBuilder.append(" order by f.gst_id desc");
		@SuppressWarnings("rawtypes")
		List list = this.createTransformSqlQuery(hqlBuilder.toString()).list();

		return list;
	}

	@Override
	public List<FguestInfoVO> fguestSearch(FguestSearchVo fguestSearch) {
		StringBuffer querySQL = new StringBuffer();
		// 计算消费 统计住宿次数
		querySQL.append("SELECT gstNamec,mobile,gstNamee,idCard,gstId");
		querySQL.append(",gstOrgSrc,gstType,reachDate,leaveDate,roomId");
		querySQL.append(",lastPrice,companyName,usuallyRoomType,notice,gender,leaveTimes,cumuLative FROM (");
		querySQL.append("SELECT f.gst_namec as gstNamec,f.mobile,f.gst_namee as gstNamee,f.crd_id as idCard,f.gst_id as gstId");
		querySQL.append(",ht.code_namec as gstOrgSrc,h.code_namec as gstType");
		querySQL.append(",f.reach_date as reachDate,f.leave_date as leaveDate,f.room_id as roomId");
		querySQL.append(",f.room_price as lastPrice,f.comp_id as companyName,hty.code_namec as usuallyRoomType");
		querySQL.append(",f.notice,f.sex as gender,rssum.sum as cumuLative");
		querySQL.append(",rscount.count as leaveTimes ");
		querySQL.append(" FROM fguest f LEFT JOIN hgst_ori h on f.gst_ori_id=h.code_id ");
		querySQL.append("LEFT JOIN hgst_ori_type ht on ht.code_id=h.ori_kind ");
		querySQL.append(" LEFT JOIN hroom_Type hty on hty.code_id=f.room_typeid");// 资料列表
		querySQL.append(" LEFT JOIN (select count(gdoc.check_id)as count,fgst.gst_id from fguest fgst");
		querySQL.append(" LEFT JOIN guestdoc gdoc on fgst.gst_id=gdoc.gst_id ");
		querySQL.append(" GROUP BY fgst.gst_id ) rscount on rscount.gst_id=f.gst_id ");
		querySQL.append(" LEFT JOIN (select sum(gb.lent) as sum,fbgest.gst_id from fguest fbgest ");
		querySQL.append(" LEFT JOIN guestdoc gsumdoc on fbgest.gst_id=gsumdoc.gst_id ");
		querySQL.append(" LEFT JOIN gst_bill gb on gsumdoc.billa_id=gb.bill_id ");
		querySQL.append(" GROUP BY fbgest.gst_id) rssum on rssum.gst_id=f.gst_id ");
		querySQL.append(" ) r WHERE 1=1 ");
		// 中文名称
		if (StringUtils.isNotEmpty(fguestSearch.getChinaeseName())) {
			querySQL.append(" AND gstnamec like '%"
					+ fguestSearch.getChinaeseName() + "%'");
		}
		// 英文名称
		if (StringUtils.isNotEmpty(fguestSearch.getEnglishName())) {
			querySQL.append(" AND gst_namee like '%"
					+ fguestSearch.getEnglishName() + "%'");
		}
		// 联系电话
		if (StringUtils.isNotEmpty(fguestSearch.getMobile())) {
			querySQL.append(" AND mobile like '%" + fguestSearch.getMobile()
					+ "%'");
		}
		// 身份证号码
		if (StringUtils.isNotEmpty(fguestSearch.getIdCard())) {
			querySQL.append(" AND idcard like '%" + fguestSearch.getIdCard()
					+ "%'");
		}
		// 熟客编号
		if (StringUtils.isNotEmpty(fguestSearch.getVipNO())) {
			querySQL.append(" AND gstId like '%" + fguestSearch.getVipNO()
					+ "%'");
		}
		@SuppressWarnings("unchecked")
		List<FguestInfoVO> list = this.getSession()
				.createSQLQuery(querySQL.toString())
				.addScalar("gstNamec", Hibernate.STRING)
				.addScalar("mobile", Hibernate.STRING)
				.addScalar("gstNamee", Hibernate.STRING)
				.addScalar("idCard",Hibernate.STRING)
				.addScalar("gstId", Hibernate.STRING)
				.addScalar("gstOrgSrc", Hibernate.STRING)
				.addScalar("gstType", Hibernate.STRING)
				.addScalar("reachDate", Hibernate.DATE)
				.addScalar("leaveDate", Hibernate.DATE)
				.addScalar("roomId", Hibernate.STRING)
				.addScalar("lastPrice", Hibernate.DOUBLE)
				.addScalar("companyName", Hibernate.STRING)
				.addScalar("usuallyRoomType", Hibernate.STRING)
				.addScalar("notice", Hibernate.STRING)
				.addScalar("gender", Hibernate.STRING)
				.addScalar("leaveTimes", Hibernate.INTEGER)
				.addScalar("cumuLative", Hibernate.DOUBLE)
				.setResultTransformer(Transformers.aliasToBean(FguestInfoVO.class))
				.list();
//		List list = this.createTransformSqlQuery(querySQL.toString()).list();
		return list;
	}

	@Override
	public List<Map<String, Object>> getFguestRecord(String startDate, String endDate,Integer gstId) {
		//房价和消费总额 暂时空置 房租暂时搁置
		StringBuffer querySQL = new StringBuffer();
		querySQL.append("SELECT gdoc.check_id,gdoc.reach_date,gdoc.leave_date");
		querySQL.append(",gdoc.room_id,gdoc.room_price,gb.borrow");
		querySQL.append(",datediff(day,gdoc.reach_date,gdoc.leave_date) as times ");
		querySQL.append(" FROM guestdoc gdoc ");
		querySQL.append(" LEFT JOIN fguest fg on fg.gst_id=gdoc.gst_id");
		querySQL.append(" LEFT JOIN gst_bill gb on gb.bill_id=gdoc.billa_id ");
		querySQL.append(" WHERE 1=1 AND gdoc.check_id=" + gstId + "  ");
		querySQL.append(" AND datediff(day,gdoc.reach_date,'" + startDate + "')<=0 ");
		querySQL.append(" AND datediff(day,gdoc.leave_date,'" + endDate + "')>=0 ");
		querySQL.append(" order by gdoc.reach_date asc ");
		List<Object[]> list = this.getSession().createSQLQuery(querySQL.toString()).list();
		List<Map<String, Object>> result = new ArrayList<Map<String,Object>>();
		for(Object[] res:list){
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("checkId", res[0]);
			map.put("reachDate", res[1]);
			map.put("leaveDate", res[2]);
			map.put("roomId", res[3]);
			map.put("roomPrice", res[4]);
			map.put("borrow", res[5]);
			map.put("times", res[6]);
			result.add(map);
		}
		return result;
	}

}
