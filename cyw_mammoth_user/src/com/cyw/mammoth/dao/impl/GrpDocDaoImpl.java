package com.cyw.mammoth.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.cyw.common.base.dao.impl.BaseDaoImpl;
import com.cyw.mammoth.bean.BookRoom;
import com.cyw.mammoth.bean.GrpDoc;
import com.cyw.mammoth.bean.Guestdoc;
import com.cyw.mammoth.bean.HroomType;
import com.cyw.mammoth.dao.GrpDocDao;
import com.cyw.mammoth.vo.BookRoomSearchVo;
import com.cyw.mammoth.vo.GroupBookSearchVo;
import com.cyw.mammoth.vo.GrpDocVo;

/**
 * 团体资料
 * <功能详细描述>
 * 
 * @author  litiangang@cyw.so
 * @version  v-1.0
 * @see  GrpDocDaoImpl.java
 * @since  cyw-1.0
 */
@SuppressWarnings({ "unchecked", "rawtypes" }) 
@Repository
public class GrpDocDaoImpl extends BaseDaoImpl<GrpDoc, Integer> implements GrpDocDao {

	/** {@inheritDoc} */
	 
	@Override
	public List getgrpdoc(Map resMap) {
//		String hq = "select distinct a.check_id,a.room_id as room_id,a.gst_namec as gst_namec,a.gst_namee as gst_namee,a.check_id as check_id,a.billb_id as billb_id,a.grp_chkid as grp_chkid,a.payman_flag as payman_flag,a.with_id as with_id,a.billb_org_id as billb_org_id,a.chk_ext as chk_ext from guestdoc b, guestdoc a, grp_doc gd ";
//		StringBuilder sb=new StringBuilder(hq);
//		if(StringUtils.isNotEmpty(resMap.get("grp_id_name1")+"")){//团代码/团名
//			sb.append(" where a.chk_stat='I' AND a.with_id = gd.with_id and (gd.grp_id='"+(resMap.get("grp_id_name1")+"").trim()+"' or gd.grp_name='"+resMap.get("grp_id_name1")+"') order by a.room_id asc");
//		}
//		if(StringUtils.isNotEmpty(resMap.get("roomid1")+"")){//相关房号
//			sb.append(" where a.chk_stat='I' AND a.with_id = b.with_id and b.room_id='"+(resMap.get("roomid1")+"").trim()+"' order by a.room_id asc");
//		}
		String hq = "select distinct a.check_id,a.room_id as room_id,a.gst_namec as gst_namec,a.gst_namee as gst_namee,a.check_id as check_id,a.billb_id as billb_id," +
				"a.grp_chkid as grp_chkid,a.payman_flag as payman_flag,a.with_id as with_id,a.billb_org_id as billb_org_id,a.chk_ext as chk_ext, " +
				"(case when a.grp_chkid is not null and a.grp_chkid <> 0 then 'T' " +
				"when (select count(distinct b.room_id) from guestdoc b where b.chk_stat = 'I' and b.with_id in (select with_id from guestdoc where room_id = a.room_id)) > 1 then 'L' " +
				"when (select count(distinct b.room_id) from guestdoc b where b.chk_stat = 'I' and b.with_id in (select with_id from guestdoc where room_id = a.room_id)) = 1 then 'S' " +
				"else 'K' end) gst_type from guestdoc a  where a.chk_stat='I' ";
		StringBuilder sb=new StringBuilder(hq);
		if(StringUtils.isNotEmpty(resMap.get("grp_id_name1")+"")){//团代码/团名
			sb.append(" AND a.with_id in (select b.with_id from grp_doc b where b.grp_id='"+(resMap.get("grp_id_name1")+"").trim()+"' or b.grp_name='"+resMap.get("grp_id_name1")+"') order by a.room_id asc");
		}
		if(StringUtils.isNotEmpty(resMap.get("roomid1")+"")){//相关房号
			sb.append(" AND a.with_id in (select c.with_id from guestdoc c where c.chk_stat = 'I' and c.room_id='"+(resMap.get("roomid1")+"").trim()+"') order by a.room_id asc");
		}
		Query query = this.createTransformSqlQuery(sb.toString());
		return query.list();
	}

	@Override
	public int get_Biil_id_num(int bill_id) {
		String hq = "select count(*) from gst_credit_auth gca where gca.status <>'2' and gca.bill_id="+bill_id;
		Number num = (Number) this.getSession().createSQLQuery(hq).uniqueResult();
		String hq1 = "select count(*) from bills b where b.bill_type in ('1','2','3','4','') and b.status<>'2' and rtrim(ltrim(b.ext_name))<>'转出' and b.bill_id="+bill_id;
		Number num1 = (Number) this.getSession().createSQLQuery(hq1).uniqueResult();
		int countNum = num.intValue()+num1.intValue();
		return countNum;
	}
	
	

	@Override
	public List getBookRoomList(GroupBookSearchVo searchVo) {
		StringBuffer  sb = new StringBuffer();
		sb.append("SELECT  g.check_id, g.grp_id, g.grp_name, g.book_stat, g.chk_stat, g.book_list, br.book_room_id, br.roomtype_id, br.book_num, br.save_num, br.reach_num, br.room_price, br.reach_date, br.leave_date,br.status, br.cancel_num, br.NoShow_num, ht.code_namec");
		sb.append(" FROM grp_doc g,book_room br,hroom_type ht WHERE 1=1 AND g.check_id = br.check_id AND br.roomtype_id = ht.code_id");
		sb.append(" AND g.check_id in(");
		//sb.append("select g.*,b.*,r.* from guestdoc g, book_room b,hroom_type r where g.check_id = b.check_id and b.roomtype_id = r.code_id");
		sb.append("select g.check_id ");
		//sb.append(" g.check_id,g.book_list,g.gst_namee,g.gst_namec,g.book_stat,");
		//sb.append(" br.book_room_id,br.roomtype_id,br.reach_date,br.leave_date,br.book_num,br.save_num,br.reach_num, ");
		//sb.append(" ht.code_id,ht.code_namec,ht.code_namee ");
		sb.append(" from grp_doc g,book_room br,hroom_type ht ");
		sb.append(" where 1=1 ");
		sb.append(" and g.check_id = br.check_id and br.roomtype_id = ht.code_id ");
		
		//* # 拥有最高优先级
		if(searchVo.getSymbol()!=null){
			//*所有无效
			if(searchVo.getSymbol()==1){
				sb.append(" and g.book_stat in ('C','P','A') ");
			}else{
			//#所有有效	
				sb.append(" and g.book_stat in ('B','G','O','R')");
			}
		}else{
			
			if(StringUtils.isNotEmpty(searchVo.getRoom_id())){
				sb.append(getCheckIdByRoomId(searchVo));
			}
			
			if(searchVo.getActive()!=null){
				
				if(searchVo.getActive()==1){   //无效
					sb.append(" and g.book_stat in ('C','P','A') ");
				}else{
				//#所有有效	
					sb.append(" and g.book_stat in ('B','G','O','R')");
				}
				
				if(StringUtils.isNotEmpty(searchVo.getCodeLetter())){
					sb.append(" and g.gst_namee like '"+searchVo.getCodeLetter().trim()+"%'");
				}else{
					if(StringUtils.isNotEmpty(searchVo.getBookList())){
						sb.append(" and g.book_list  like '%"+searchVo.getBookList()+"%'");
					}
					if(StringUtils.isNotEmpty(searchVo.getGrpId())){
						sb.append(" and g.grp_id  like '%"+searchVo.getGrpId()+"%'");
					}
					if(StringUtils.isNotEmpty(searchVo.getGrpName())){
						sb.append(" and g.grp_name  like '%"+searchVo.getGrpName()+"%'");
					}
					if(StringUtils.isNotEmpty(searchVo.getLeaderNamec())){
						sb.append(" and g.lead_namee like '%"+searchVo.getLeaderNamec()+"%' or g.lead_namec like '%"+searchVo.getLeaderNamec()+"%' ");
					}
					if(StringUtils.isNotEmpty(searchVo.getBook_operid())){
						sb.append(" and g.book_operid like '%"+searchVo.getBook_operid()+"%'");
					}
					if(StringUtils.isNotEmpty(searchVo.getMobile())){
						sb.append(" and g.mobile like '%"+searchVo.getMobile()+"%'");
					}
					if(StringUtils.isNotEmpty(searchVo.getReachDate())&&StringUtils.isNotEmpty(searchVo.getLeaveDate())){
						sb.append(" and datediff(day,convert(varchar(100),br.reach_date,23),'"+searchVo.getReachDate()+"') =0 ");
						sb.append(" and datediff(day,convert(varchar(100),br.leave_date,23),'"+searchVo.getLeaveDate()+"') =0 ");
					}else{
						if(StringUtils.isNotEmpty(searchVo.getReachDate())){
							sb.append(" and  datediff(day,convert(varchar(100),br.reach_date,23),'"+searchVo.getReachDate()+"')=0 ");
						}
						if(StringUtils.isNotEmpty(searchVo.getLeaveDate())){
							sb.append(" and  datediff(day,convert(varchar(100),br.leave_date,23),'"+searchVo.getLeaveDate()+"')=0 ");
						}
					}
					
					if(StringUtils.isNotEmpty(searchVo.getBookStat())){
						sb.append(" and g.book_stat='"+searchVo.getBookStat()+"'");
					}
				}
			}
		}
		sb.append(")");
		//sb.append(" order by g.check_id desc");
		sb.append(" order by g.check_id desc, br.reach_date desc");
		
		
		List list =  this.getSession().createSQLQuery(sb.toString()).list();
		return list;
	}
	public List getBookRoomList2(GroupBookSearchVo searchVo) {
		StringBuffer  sb = new StringBuffer();
		sb.append("SELECT  	g.check_id,g.book_list,g.grp_id,g.grp_name,g.book_stat,br.book_room_id,br.roomtype_id,	br.reach_date,br.leave_date,br.book_num,br.save_num,br.reach_num,ht.code_id,ht.code_namec,ht.code_namee");
		sb.append(" FROM grp_doc g,book_room br,hroom_type ht WHERE 1=1 AND g.check_id = br.check_id AND br.roomtype_id = ht.code_id");
		sb.append(" AND g.check_id in(");
		//sb.append("select g.*,b.*,r.* from guestdoc g, book_room b,hroom_type r where g.check_id = b.check_id and b.roomtype_id = r.code_id");
		sb.append("select g.check_id ");
		//sb.append(" g.check_id,g.book_list,g.gst_namee,g.gst_namec,g.book_stat,");
		//sb.append(" br.book_room_id,br.roomtype_id,br.reach_date,br.leave_date,br.book_num,br.save_num,br.reach_num, ");
		//sb.append(" ht.code_id,ht.code_namec,ht.code_namee ");
		sb.append(" from grp_doc g,book_room br,hroom_type ht ");
		sb.append(" where 1=1 ");
		sb.append(" and g.check_id = br.check_id and br.roomtype_id = ht.code_id ");
		
		//* # 拥有最高优先级
		if(searchVo.getSymbol()!=null){
			//*所有无效
			if(searchVo.getSymbol()==1){
				sb.append(" and g.book_stat in ('C','P','A') ");
			}else{
			//#所有有效	
				sb.append(" and g.book_stat in ('B','G','O','R')");
			}
		}else{
			
			if(StringUtils.isNotEmpty(searchVo.getRoom_id())){
				sb.append(getCheckIdByRoomId(searchVo));
			}
			
			if(searchVo.getActive()!=null){
				
				if(searchVo.getActive()==1){   //无效
					sb.append(" and g.book_stat in ('C','P','A') ");
				}else{
				//#所有有效	
					sb.append(" and g.book_stat in ('B','G','O','R')");
				}
				
				if(StringUtils.isNotEmpty(searchVo.getCodeLetter())){
					sb.append(" and g.gst_namee like '"+searchVo.getCodeLetter().trim()+"%'");
				}else{
					if(StringUtils.isNotEmpty(searchVo.getBookList())){
						sb.append(" and g.book_list  like '%"+searchVo.getBookList()+"%'");
					}
					if(StringUtils.isNotEmpty(searchVo.getGrpId())){
						sb.append(" and g.grp_id  like '%"+searchVo.getGrpId()+"%'");
					}
					if(StringUtils.isNotEmpty(searchVo.getGrpName())){
						sb.append(" and g.grp_name  like '%"+searchVo.getGrpName()+"%'");
					}
					if(StringUtils.isNotEmpty(searchVo.getLeaderNamec())){
						sb.append(" and g.lead_namee like '%"+searchVo.getLeaderNamec()+"%' or g.lead_namec like '%"+searchVo.getLeaderNamec()+"%' ");
					}
					if(StringUtils.isNotEmpty(searchVo.getBook_operid())){
						sb.append(" and g.book_operid like '%"+searchVo.getBook_operid()+"%'");
					}
					if(StringUtils.isNotEmpty(searchVo.getMobile())){
						sb.append(" and g.mobile like '%"+searchVo.getMobile()+"%'");
					}
					if(StringUtils.isNotEmpty(searchVo.getReachDate())&&StringUtils.isNotEmpty(searchVo.getLeaveDate())){
						sb.append(" and datediff(day,g.reach_date,'"+searchVo.getReachDate()+"') <=0 ");
						sb.append(" and datediff(day,g.leave_date,'"+searchVo.getLeaveDate()+"') >=0 ");
					}else{
						if(StringUtils.isNotEmpty(searchVo.getReachDate())){
							sb.append(" and  datediff(day,br.reach_date,'"+searchVo.getReachDate()+"')=0 ");
						}
						if(StringUtils.isNotEmpty(searchVo.getLeaveDate())){
							sb.append(" and  datediff(day,br.leave_date,'"+searchVo.getLeaveDate()+"')=0 ");
						}
					}
					
					if(StringUtils.isNotEmpty(searchVo.getBookStat())){
						sb.append(" and g.book_stat='"+searchVo.getBookStat()+"'");
					}
				}
			}
		}
		sb.append(")");
		sb.append(" order by g.check_id desc");
		
		
		List list =  this.getSession().createSQLQuery(sb.toString()).addEntity("g",GrpDoc.class).addEntity("b",BookRoom.class).addEntity("r",HroomType.class).list();
		return list;
	}
	
	/** 
	 * 根据房间号查询预定信息
	 * @param searchVo
	 * @param hql
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	private StringBuffer getCheckIdByRoomId(GroupBookSearchVo searchVo) {
		StringBuffer sb = new StringBuffer();
		String checkIdstr="";
		String sql="select check_id from room_num where room_id='"+searchVo.getRoom_id()+"'";
		Query query = this.getSession().createSQLQuery(sql);
		List list=query.list();
		if(list!=null){
			for (int i = 0; i < list.size(); i++) {
				checkIdstr+="'"+list.get(i)+"',";
			}	
		}
		if(StringUtils.isNotEmpty(checkIdstr)){
			checkIdstr=checkIdstr.substring(0,checkIdstr.length()-1);
			sb.append(" and  g.check_id in ("+checkIdstr+")");
		}
		return sb;
	}

	@Override
	public List getBillIdList(int check_id) {
		StringBuilder sb = new StringBuilder();
		sb.append("select gd.check_id check_id, gd.gst_namec gst_namec, gd.gst_namee gst_namee, gd.room_id room_id, b.acco_id acco_id, b.room_id bill_room_id, b.cons_id cons_id, hcos.code_namec cons_namec, hsetl.code_namec setl_namec, b.balance balance,b.oper_id oper_id, b.oper_time oper_time");
		sb.append(" from bills b inner join guestdoc gd on b.bill_id = gd.billb_id");
		sb.append(" left outer join hconsume hcos on hcos.code_id = b.cons_id");
		sb.append(" left outer join hsettle hsetl on hsetl.code_id = b.setl_id");
		sb.append(" where gd.chk_stat='I'");
		sb.append(" and b.bill_type in ('1','2','3','4','') and b.status<>'2' and rtrim(ltrim(b.ext_name))<>'转出'");
		sb.append(" and gd.check_id = " + check_id);
		System.out.println(sb);
		Query query = this.createTransformSqlQuery(sb.toString());
		return query.list();
	}

	@Override
	public List getGstCreditAuth(int check_id) {
		String hq = "select gca.credit_holder credit_holder,gca.balance balance,gca.oper_id oper_id from gst_credit_auth gca,guestdoc gd where gd.billb_id = gca.bill_id and gca.status <>'2' and gd.check_id="+check_id;
		List list = this.createTransformSqlQuery(hq).list();
		return list;
	}

	@Override
	public List getSelectRoomToLive(Map map) {
		StringBuilder builder = new StringBuilder();
		//所输入的房间号码
		String room_idP = map.get("room_idP") == null ? "" : map.get("room_idP").toString();
		//团代码或团名称
		String grp_id_nameP = map.get("grp_id_nameP") == null ? "" : map.get("grp_id_nameP").toString();
		//是否显示同来房
		String with_roomP = map.get("with_roomP") == null ? "" : map.get("with_roomP").toString();
//		//是否全部显示预离
//		String is_ready_leaveP = map.get("is_ready_leaveP") == null ? "" : map.get("is_ready_leaveP").toString();
		
		builder.append("select a.check_id, a.gst_namee, a.gst_namec, convert(varchar(21), a.reach_date, 23) reach_date, convert(varchar(21), a.leave_date, 23) leave_date, a.room_id, convert(varchar(100), cast(case when a.room_price is null then 0 else a.room_price end as money), 1) room_price, (case when a.chk_ext = '1' then '主人' when a.chk_ext = '0' then '同住' else '未知' end) chk_ext_show, a.chk_ext, a.If_bdate, a.grp_chkid, (select d.grp_id from grp_doc d where d.check_id = a.grp_chkid) grp_id, (select d.grp_name from grp_doc d where d.check_id = a.grp_chkid) grp_name from guestdoc a ");
		builder.append("where a.chk_stat = 'I' ");
//		if(is_ready_leaveP.equals("true")){
//			builder.append("and datediff(day, a.leave_date, (select para3 from parameter where id = 1)) = 0 ");
//		}
		if(!room_idP.equals("")){
			if("true".equals(with_roomP)){
				builder.append("and a.with_id in (select b.with_id from guestdoc b where b.room_id = '");
				builder.append(room_idP);
				builder.append("') ");
			}else if("false".equals(with_roomP)){
				builder.append("and a.room_id = '");
				builder.append(room_idP);
				builder.append("' ");
			}
		}
		if(!grp_id_nameP.equals("") && !grp_id_nameP.equals("0")){
			builder.append("and a.with_id in (select c.with_id from grp_doc c where c.grp_name = '");
			builder.append(grp_id_nameP);
			builder.append("' or c.grp_id = '");
			builder.append(grp_id_nameP);
			builder.append("')");
		}
		List list = this.createTransformSqlQuery(builder.toString()).list();
		return list;
	}

	@Override
	public List getSelectGrpDoc(int check_id) {
		String sql = "select gd.grp_id,gd.grp_name from grp_doc gd where check_id="+check_id;
		return this.createTransformSqlQuery(sql).list();
	}

	@Override
	public Map getGrpDocDetail(int checkId) {
		String sql = "SELECT row_number()OVER(ORDER BY gd.reach_date) as sortNum,gd.check_id checkId,gd.grp_id grpId,gd.grp_name grpName,gd.lead_namee leadNamee,gd.lead_namec leadNamec,gd.reach_date reachDate,gd.leave_date leaveDate,gd.lead_room leadRoom"
				+", op.oper_name checkName,op2.oper_name bookName,op3.oper_name rechkName,op4.oper_name outName,op5.oper_name lastName,gd.chk_time,gd.book_time,gd.rechk_time,gd.last_time,gd.out_time"
				+", gd.comp_id compId,gd.tele,gd.notice2,gd.gst_num gstNum,gd.book_stat bookStat,gd.confirm_date,gd.bill_id billId,br.room_num roomNum,td.namec,ISNULL((gb.borrow-gb.lent),0.00) AS remainB,gb.limit,hc.code_namec saleManName FROM grp_doc gd"
				+" LEFT JOIN (select count(*) as room_num,check_id from book_room group by check_id) br"
				+" on gd.check_id = br.check_id "
				+" LEFT JOIN ta_doc td on gd.comp_id=td.id"
				+" LEFT JOIN hcodes hc on td.saleman_id = hc.code_id"
				+" LEFT JOIN operator op on op.oper_id = gd.chk_operid "
				+" LEFT JOIN operator op2 on op2.oper_id = gd.book_operid "
				+" LEFT JOIN operator op3 on op3.oper_id = gd.rechk_operid "
				+" LEFT JOIN operator op4 on op4.oper_id = gd.out_oper "
				+" LEFT JOIN operator op5 on op5.oper_id = gd.last_oper "
				+" LEFT JOIN gst_bill gb on gb.bill_id = gd.bill_id "
				+"  where 1=1 and gd.check_id="+checkId;
		Query query = this.createTransformSqlQuery(sql);
		Map map = (Map)query.uniqueResult();
		return map;
	}
	@Override
	public void updeteGuestIfbate(Integer checkId, String ifBate) {
		String sql = "update grp_doc set if_bdate="+ifBate+" where check_id="+checkId;
		Query query = this.createTransformSqlQuery(sql);
		query.executeUpdate();
	}

	@Override
	public List<GrpDoc> getGrpDocListByNameOrId(String param) {
		StringBuffer hql =new StringBuffer();
		hql.append("from GrpDoc as model where model.chkStat = ? and (model.grpId = ? or model.grpName = ?) ");
		Query query= getSession().createQuery(hql.toString());
		query.setParameter(0, "I");	
		query.setParameter(1, param);
		query.setParameter(2, param);
		return query.list();
	}

	@Override
	public void updateGrpdoc(GrpDoc grpDoc) {
		String sql = "update grp_doc set lead_room = "+(grpDoc.getLeadRoom()==null?null:"'"+grpDoc.getLeadRoom()+"'")+",lead_namee = "+(grpDoc.getLeadNamee()==null?null:"'"+grpDoc.getLeadNamee()+"'")
			     +",lead_namec = "+(grpDoc.getLeadNamec()==null?null:"'"+grpDoc.getLeadNamec()+"'")+",tele="+(grpDoc.getTele()==null?null:"'"+grpDoc.getTele()+"'")+",notice2="
			     +(grpDoc.getNotice2()==null?null:"'"+grpDoc.getNotice2()+"'")+",limit="+(grpDoc.getLimit()==null?null:"'"+grpDoc.getLimit()+"'")
			     +",city_ledger="+(grpDoc.getCityLedger()?1:0)+",house_pay="+(grpDoc.getHousePay()?1:0)+",free_svc="+(grpDoc.getFreeSvc()?1:0)+",hideprice="+(grpDoc.getHideprice()?1:0)
			     +" where check_id="+grpDoc.getCheckId();
		Query query = this.createTransformSqlQuery(sql);
		query.executeUpdate();
	}

	@Override
	public void updateGuest(String str) {
		// TODO Auto-generated method stub
		String sql = "update guestdoc set "+str;
		Query query = this.createTransformSqlQuery(sql);
		query.executeUpdate();
	}
	@Override
	public void updateLimit(Double limit, Integer billId) {
		String sql = "update gst_bill set limit='"+limit+"' where bill_id='"+billId+"'";
		Query query = this.createTransformSqlQuery(sql);
		query.executeUpdate();
	}
}
