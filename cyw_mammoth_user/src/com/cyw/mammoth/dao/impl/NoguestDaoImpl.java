package com.cyw.mammoth.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import com.cyw.common.base.dao.impl.BaseDaoImpl;
import com.cyw.common.util.StrUtils;
import com.cyw.mammoth.bean.Noguest;
import com.cyw.mammoth.dao.NoguestDao;
import com.cyw.mammoth.vo.NoguestSelectVO;

@Repository
public class NoguestDaoImpl extends BaseDaoImpl<Noguest, Integer> implements NoguestDao{
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	@SuppressWarnings({ "unchecked", "rawtypes" })     
	public List getNoguestList(NoguestSelectVO noguestSelectVO) {
		String sql="select ng.id id,ng.nogst_id nogst_id,ng.nogst_name nogst_name,ng.connector connector,ng.mobile mobile,ng.hotel_flag hotel_flag,ng.status status,ng.bill_id bill_id,ng.borrow borrow,ng.lent lent,td.tele tele,ng.notes notes,ng.crea_time crea_time,td.namec namec from noguest ng left join ta_doc td  on ng.comp_id=td.id left join operator op on op.oper_id = ng.crea_id  ";
		StringBuilder sb = new StringBuilder(sql);
		sb.append(" where 1=1 ");
		if(StrUtils.isValidString(noguestSelectVO.getCodeLetter())){
			//#号 35 *号42
			int chatnum=(int)noguestSelectVO.getCodeLetter().charAt(0);
			if(chatnum==35){
				sb.append(" and ng.status = 0  ");
			}else if(chatnum==42){
				sb.append(" and ng.status = 1");
			}else{
				sb.append(" and ng.nogst_id like '" + noguestSelectVO.getCodeLetter() + "%'");
				if(noguestSelectVO.isChkStat()){//true
					sb.append(" and ng.status = 1  ");
				}else{
					sb.append(" and ng.status = 0  ");
				}
			}
		}else{
			if(StringUtils.isNotEmpty(noguestSelectVO.getNogstId())){
				sb.append(" and ng.nogst_id like '"+noguestSelectVO.getNogstId()+"%'");
			}
			if(StringUtils.isNotEmpty(noguestSelectVO.getNogstName())){
				sb.append(" and ng.nogst_name like '%"+noguestSelectVO.getNogstName().trim()+"%'");
			}
			if(StringUtils.isNotEmpty(noguestSelectVO.getCompId())){
				sb.append(" and ng.comp_id like '%"+noguestSelectVO.getCompId()+"%'");
			}
			if(StringUtils.isNotEmpty(noguestSelectVO.getConnEctor())){
				sb.append(" and ng.connector like '%"+noguestSelectVO.getConnEctor()+"%'");
			}
			if(StringUtils.isNotEmpty(noguestSelectVO.getCreaTimeStart())&&StringUtils.isNotEmpty(noguestSelectVO.getCreaTimeEnd())){
				sb.append(" and DATEDIFF ( day , ng.crea_time , '"+noguestSelectVO.getCreaTimeStart()+"' )<=0 and DATEDIFF ( day , ng.crea_time , '"+noguestSelectVO.getCreaTimeEnd()+"' )>=0");
			}
		
			if(StringUtils.isNotEmpty(noguestSelectVO.getPayDateStart())&&StringUtils.isNotEmpty(noguestSelectVO.getPayDateEnd())){
				sb.append(" and DATEDIFF ( day , ng.pay_date , '"+noguestSelectVO.getPayDateStart()+"' )<=0 and DATEDIFF ( day , ng.pay_date , '"+noguestSelectVO.getPayDateEnd()+"' )>=0");
			}
			if(StringUtils.isNotEmpty(noguestSelectVO.getBillId())){
				sb.append(" and ng.bill_id like '%"+noguestSelectVO.getBillId()+"%'");
			}
			boolean hotel_flag = noguestSelectVO.getHotelFlag();
			if(hotel_flag){
				sb.append(" and ng.hotel_flag = '"+hotel_flag+"'");
			}else{
				sb.append(" and ng.hotel_flag = 'false' ");
			}
			if(StringUtils.isNotEmpty(noguestSelectVO.getCrea_name())){
				sb.append(" and op.oper_name like '%"+noguestSelectVO.getCrea_name()+"%'");
			}
			if(noguestSelectVO.getRstatus()!=null){
				sb.append(" and ng.status = "+noguestSelectVO.getRstatus());
			}
		}
		List list = this.createTransformSqlQuery(sb.toString()).list();
		List dlist = new ArrayList();
		for (int i = 0; i < list.size(); i++) {
			Map dmap = (Map)list.get(i);
			float borrow = Float.parseFloat(dmap.get("borrow")+"") ;
			float lent = Float.parseFloat(dmap.get("lent")+"");
			
			float balance = borrow - lent;
			dmap.put("balance", balance);
			String hotelFlag = dmap.get("hotel_flag")+"";
			String status_ = dmap.get("status")+"";
			if("true".equals(hotelFlag)){
				dmap.put("hotel_flag", "是");
			}else{
				dmap.put("hotel_flag", "否");
			}
			if("0".equals(status_)){
				dmap.put("status", "是");
			}else{
				dmap.put("status", "否");
			}
			dlist.add(dmap);
		}
		return dlist;
	}

	@Override
	@SuppressWarnings({ "rawtypes" })   
	public List getCompanyData(String comp_type) {
		StringBuilder sql = new StringBuilder();
		sql.append("select td.id id,td.namec namec,td.tele tele,td.TA_type ta_type from ta_doc td where td.status<>'1' and (td.comp_id like '%"+comp_type+"%' or td.namec like '%"+comp_type+"%')");
		if(comp_type.length() > 11){
			sql.append(" and td.tele like '%"+comp_type+"%'");
		}
		
		return this.createTransformSqlQuery(sql.toString()).list();
	}

	@Override
	@SuppressWarnings({ "unchecked", "rawtypes" })   
	public List getSelectNoguest(Map map) {
		StringBuilder sql = new StringBuilder();
		List dlist = new ArrayList();
		int status = Integer.parseInt(map.get("status")+"");
		sql.append("select ng.id id,ng.nogst_id nogst_id,ng.nogst_name nogst_name,ng.connector connector,ng.mobile mobile,ng.hotel_flag hotel_flag,ng.status status,ng.bill_id bill_id,ng.borrow borrow,ng.lent lent,td.namec namec,td.tele tele,ng.notes notes,ng.crea_time crea_time");
		sql.append(" from noguest ng,ta_doc td");
		sql.append(" where ng.comp_id=td.id");
		sql.append(" and ng.status="+status);
		List list = this.createTransformSqlQuery(sql.toString()).list();
		for (int i = 0; i < list.size(); i++) {
			Map dmap = (Map)list.get(i);
			float borrow = Float.parseFloat(dmap.get("borrow")+"") ;
			float lent = Float.parseFloat(dmap.get("lent")+"");
			
			float balance = borrow - lent;
			dmap.put("balance", balance);
			String hotel_flag = dmap.get("hotel_flag")+"";
			String status_ = dmap.get("status")+"";
			if("true".equals(hotel_flag)){
				dmap.put("hotel_flag", "是");
			}else{
				dmap.put("hotel_flag", "否");
			}
			if("0".equals(status_)){
				dmap.put("status", "是");
			}else{
				dmap.put("status", "否");
			}
			dlist.add(dmap);
		}
		return dlist;
	}

	@Override
	public List selectSignleNoguest(int id) {
		StringBuilder sql = new StringBuilder();
		sql.append("select ng.id id,ng.nogst_id nogstId,ng.nogst_name nogstName,ng.connector connector,ng.mobile mobile,ng.hotel_flag hotel_flag,ng.status status,ng.bill_id bill_id,ng.borrow borrow,ng.lent lent,td.namec namec,td.tele tele,td.TA_type TA_type,ng.notes notes,ng.crea_time creaTime,ng.phone phone,ng.fax fax,ng.email email,ng.hotel_flag hotel_flag,ng.pay_date payDate,ng.bankaddr bankaddr,ng.bankno bankno,ng.limit limit,td.namec comp_type,ng.comp_id comp_id,ng.comp_type compType,ng.pay_id payId");
		sql.append(" from noguest ng left join ta_doc td  on ng.comp_id=td.id");
		sql.append(" where 1=1");
		sql.append(" and ng.id="+id);
		return this.createTransformSqlQuery(sql.toString()).list();
	}

	@Override
	public void updateSigleNoguest(Noguest noguest) {
		  getSession().flush();
	        getSession().clear();
		this.getSession().merge(noguest);
	}

	@Override
	public List<Noguest> selectNoguest(int id) {
		String hq = "from Noguest bean where id="+id;
		return this.createQueryList(hq);
		
	}

	@Override
	public void updateStatusCancle(int id) {
		String sql = "update noguest set status = 1 where id="+id;
		this.createTransformSqlQuery(sql).executeUpdate();
	}

	@Override
	public Map getNoguestInfo(int id) {
		String sql = "select ng.id id,ng.nogst_id nogstId,ng.nogst_name nogstName,ng.connector,ng.mobile,ng.hotel_flag hotelFlag,ng.status,ng.bill_id billId,td.namec namec,ng.notes,ng.crea_time creaTime,ng.phone,ng.fax,ng.email," +
				"ng.pay_date payDate,ng.bankaddr bankaddr,ng.bankno bankno,ng.comp_id compId,ng.comp_type compType,ng.pay_id payId,ISNULL(gb.limit,0.00) as nLimit,ISNULL(gb.borrow,0.00) as nBorrow,ISNULL(gb.lent,0.00) as nLent,ISNULL(gb.borrow-gb.lent,0.00) as nRemain,hs.code_namec payName " +
				"from noguest ng left join ta_doc td  on ng.comp_id=td.id"+
				" left join gst_bill gb on ng.bill_id=gb.bill_id" +
				" left join hsettle hs on hs.code_id = ng.pay_id"+
				" where ng.id="+id;
		Map map = (Map)this.createTransformSqlQuery(sql).uniqueResult();
		return map;
	}
}
