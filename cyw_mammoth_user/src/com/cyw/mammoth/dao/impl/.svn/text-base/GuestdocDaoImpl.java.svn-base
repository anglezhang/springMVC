package com.cyw.mammoth.dao.impl;


import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.jdbc.Work;
import org.hibernate.transform.Transformers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.cyw.common.base.dao.impl.BaseDaoImpl;
import com.cyw.common.util.DateUtils;
import com.cyw.mammoth.bean.Bills;
import com.cyw.mammoth.bean.BookRoom;
import com.cyw.mammoth.bean.GstBill;
import com.cyw.mammoth.bean.Guestdoc;
import com.cyw.mammoth.bean.HgstOri;
import com.cyw.mammoth.bean.HroomType;
import com.cyw.mammoth.bean.Parameter;
import com.cyw.mammoth.dao.BillsDao;
import com.cyw.mammoth.dao.GstBillDao;
import com.cyw.mammoth.dao.GuestdocDao;
import com.cyw.mammoth.dao.HgstOriDao;
import com.cyw.mammoth.dao.ParameterDao;
import com.cyw.mammoth.vo.BookRoomCheckInVO;
import com.cyw.mammoth.vo.BookRoomSearchVo;
import com.cyw.mammoth.vo.GroupSearchVO;
import com.cyw.mammoth.vo.GuestDetailVo;
import com.cyw.mammoth.vo.GuestSearchVO;
import com.cyw.mammoth.vo.NoguestSearchVO;
import com.cyw.mammoth.vo.WebRoomSearchVO;
/**
 * <一句话功能简述>
 * <功能详细描述>
 * 
 * @author  litiangang@cyw.so
 * @version  v-1.0
 * @see  GuestdocDaoImpl.java
 * @since  cyw-1.0
 */
@SuppressWarnings({ "unchecked", "rawtypes" }) 
@Repository
public class GuestdocDaoImpl extends BaseDaoImpl<Guestdoc, Integer> implements GuestdocDao {
	
	private Logger log = LoggerFactory.getLogger(GuestdocDaoImpl.class);//记录日志类
	
	@Resource
	ParameterDao paramDao;
	@Resource
	BillsDao billsDao;
	@Autowired
	GstBillDao gstBillDao;
	/**
	 * @描述 客人来源
	 * */
	@Autowired
	private HgstOriDao orgDao;
	@Override
	public Guestdoc getGuestdoc(String roodId, String chkState,String chkExt) {
		String hql = "from Guestdoc bean  where bean.checkId ='"+roodId+"'";
		return this.createQueryObj(hql);
		
	}

	/**查询住客预定信息*/
	 
	@Override
	public List getBookRoomList(BookRoomSearchVo searchVo) throws HibernateException{
		StringBuffer  sb = new StringBuffer();
		sb.append("SELECT  	g.check_id,g.book_list,g.gst_namee,g.gst_namec,g.book_stat,br.book_room_id,br.roomtype_id,	br.reach_date,br.leave_date,br.book_num,br.save_num,br.reach_num,ht.code_id,ht.code_namec,ht.code_namee");
		sb.append(" FROM guestdoc g,book_room br,hroom_type ht WHERE 1=1 AND g.check_id = br.check_id AND br.roomtype_id = ht.code_id");
		sb.append(" AND g.check_id in(");
		//sb.append("select g.*,b.*,r.* from guestdoc g, book_room b,hroom_type r where g.check_id = b.check_id and b.roomtype_id = r.code_id");
		sb.append("select g.check_id ");
		//sb.append(" g.check_id,g.book_list,g.gst_namee,g.gst_namec,g.book_stat,");
		//sb.append(" br.book_room_id,br.roomtype_id,br.reach_date,br.leave_date,br.book_num,br.save_num,br.reach_num, ");
		//sb.append(" ht.code_id,ht.code_namec,ht.code_namee ");
		sb.append(" from guestdoc g,book_room br,hroom_type ht ");
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
					if(StringUtils.isNotEmpty(searchVo.getBook_list())){
						sb.append(" and g.book_list like '%"+searchVo.getBook_list()+"%'");
					}
					if(StringUtils.isNotEmpty(searchVo.getGstNamee())){
						sb.append(" and g.gst_namee like '%"+searchVo.getGstNamee()+"%'");
					}
					if(StringUtils.isNotEmpty(searchVo.getGstNamec())){
						sb.append(" and g.gst_namec like '%"+searchVo.getGstNamec()+"%'");
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
		sb.append(" order by g.check_id desc,  br.reach_date desc");
		//System.out.println(sb.toString());
		List list = this.createTransformSqlQuery(sb.toString()).list();
		
		//@SuppressWarnings("unchecked")
		//List list =  this.getSession().createSQLQuery(sb.toString()).addEntity("g",Guestdoc.class).addEntity("b",BookRoom.class).addEntity("r",HroomType.class).list();
		return list;
	}
	
	/**
	 * 预定入住查询条件
	 */
	@Override
	public List<Guestdoc> getBookAccommodate(BookRoomSearchVo searchVo) {
		StringBuffer  sb = new StringBuffer();
		sb.append("select g.*,b.* ,r.* from guestdoc g ,book_room b ,hroom_type r where g.check_id = b.check_id and  b.roomtype_id = r.code_id ");
		//房间号
		if(StringUtils.isNotEmpty(searchVo.getRoom_id())){
			sb.append(getCheckIdByRoomId(searchVo));
		}
		
		if(searchVo.getSymbol()!=null && searchVo.getSymbol()==1){			   //无效
			sb.append(" and g.book_stat = ''");
		}else{
			//订单状态
			if(StringUtils.isNotEmpty(searchVo.getBookStat())){
				sb.append(" and g.book_stat = '"+searchVo.getBookStat()+"'");
			}else{
				sb.append(" and g.book_stat in('B','O','R')");
			}
		}
		
		if(StringUtils.isNotEmpty(searchVo.getCodeLetter())){
			sb.append(" and g.gst_namee like '%"+searchVo.getCodeLetter().trim()+"%'");
		}else{
			if(StringUtils.isNotEmpty(searchVo.getBook_list())){
				sb.append(" and g.book_list like '%"+searchVo.getBook_list()+"%'");
			}
			if(StringUtils.isNotEmpty(searchVo.getGstNamee())){
				sb.append(" and g.gst_namee like '%"+searchVo.getGstNamee()+"%'");
			}
			if(StringUtils.isNotEmpty(searchVo.getGstNamec())){
				sb.append(" and g.gst_namec like '%"+searchVo.getGstNamec()+"%'");
			}
			if(StringUtils.isNotEmpty(searchVo.getBook_operid())){
				sb.append(" and g.book_operid like '%"+searchVo.getBook_operid()+"%'");
			}
			if(StringUtils.isNotEmpty(searchVo.getReachDate())&&StringUtils.isNotEmpty(searchVo.getLeaveDate())){
				sb.append(" and datediff(day,getDate(),'"+searchVo.getReachDate()+"') >=0 ");
				sb.append(" and datediff(day,b.leave_date,'"+searchVo.getLeaveDate()+"') <=0 ");
			}else{
				if(StringUtils.isNotEmpty(searchVo.getReachDate())){
					sb.append(" and  datediff(day,b.reach_date,'"+searchVo.getReachDate()+"')=0 ");
				}
				if(StringUtils.isNotEmpty(searchVo.getLeaveDate())){
					sb.append(" and  datediff(day,b.leave_date,'"+searchVo.getLeaveDate()+"')=0 ");
				}
			}
			if(StringUtils.isNotEmpty(searchVo.getMobile())){
				sb.append(" and g.mobile like '%"+searchVo.getMobile()+"%'");
			}
			if(StringUtils.isNotEmpty(searchVo.getBookStat())){
				sb.append(" and g.book_stat='"+searchVo.getBookStat()+"'");
			}
		}
		@SuppressWarnings("unchecked")
		List list =  this.getSession().createSQLQuery(sb.toString()).addEntity("g",Guestdoc.class).addEntity("b",BookRoom.class).addEntity("r",HroomType.class).list();
		return list;
	}

	/** 
	 * 根据房间号查询预定信息
	 * @param searchVo
	 * @param hql
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	private StringBuffer getCheckIdByRoomId(BookRoomSearchVo searchVo) {
		StringBuffer sb = new StringBuffer();
		String checkIdstr="";
		String sql="select check_id from room_num where room_id='"+searchVo.getRoom_id()+"'";
		Query query = this.getSession().createSQLQuery(sql);
		List list=query.list();
		if (list != null) {
			for (int i = 0; i < list.size(); i++) {
				checkIdstr += "'" + list.get(i) + "',";
			}
		}
		if (StringUtils.isNotEmpty(checkIdstr)) {
			checkIdstr = checkIdstr.substring(0, checkIdstr.length() - 1);
			sb.append(" and  g.check_id in (" + checkIdstr + ")");
		} else {
			sb.append(" and  g.check_id in ('')");
		}
		return sb;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void updateRoomState(String roomid, String state) {
		// 根据房间号更改房间状态
		String hql = "update Rooms set currStat=" + state + " where roomId="
				+ roomid;
		Query query = this.createQuery(hql);
		query.executeUpdate();
	}

	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public List getGuestDocList(GuestSearchVO searchVO) {		
		String sql = "SELECT row_number()OVER(ORDER BY gst.check_id ASC ) as sortNum,gst.check_id, gst.room_id, gst.gst_namee, gst.gst_namec, gst.reach_date"
			+ ", gst.leave_date, gst.comp_id, gst.chk_stat, gd.grp_id,gst.room_price, gd.grp_name,gst.chk_operid,gst.with_id,gst.chk_ext,"
			+ " gst.billa_id,gst.billb_id,ht.code_namec,td.namec,hcs.code_namec sexname,gst.payman_flag,gst.folk,"
			+ " gst.city_ledger,gst.house_pay,ISNULL(gst.room_price,ht.price) roomPrice,gst.free_svc,gst.hideprice,gst.if_fgst,gst.with_id,ISNULL(gb.limit,0.00) as Alimit,ISNULL(gb2.limit,0.00) AS Blimit,ISNULL(gb.auth_balance,0.00) AS authBalanceA,ISNULL(gb2.auth_balance,0.00) AS authBalanceB,"
			+ " ISNULL((gb.borrow-gb.lent),0.00) AS remainA, ISNULL((gb2.borrow-gb2.lent),0.00) AS remainB,"
			+ " case gst.chk_ext when '1' then '主人' when '0' then '同住' end as chkExt, "
			+ " case gst.chk_stat when 'I' then '在住' when 'O' then '离店' else '' end as chkStat,op2.oper_name, "
			+ " case gst.payman_flag when 1 then '是'  when 0 then '否'   else '' end as paymanFlag, "
			+ " gst.tele,gst.email,gst.crd_id,gst.plate_number,gst.addr,gst.notice  "
			+ " FROM guestdoc gst LEFT JOIN grp_doc gd ON gst.grp_chkid = gd.check_id "
			+ " LEFT JOIN rooms rs ON rs.room_id = gst.room_id"
			+ " LEFT JOIN hroom_type ht ON rs.room_type = ht.code_id"
			+ " LEFT JOIN ta_doc td on gst.comp_id=td.id"
			+ " LEFT JOIN hcodes hc on td.saleman_id = hc.code_id"
			+ " LEFT JOIN hcodes hcs on hcs.code_id = gst.sex "
			+ " LEFT JOIN gst_bill gb on gb.bill_id = gst.billa_id "
			+ " LEFT JOIN gst_bill gb2  on gb2.bill_id = gst.billb_id "
			+ " LEFT JOIN operator op on op.oper_id = gst.out_oper "
			+ " LEFT JOIN operator op2 on op2.oper_id = gst.chk_operid "
			+ " WHERE 1 = 1  ";
		StringBuilder sb = new StringBuilder(sql);
		if (StringUtils.isNotEmpty(searchVO.getCheckId())) {
			sb.append(" and gst.check_id =" + searchVO.getCheckId());
		}
		if (StringUtils.isNotEmpty(searchVO.getGstNamec())) {
			sb.append(" and gst.gst_namec like '%" + searchVO.getGstNamec()
					+ "%'");
		}
		if (StringUtils.isNotEmpty(searchVO.getGstNamee())) {
			sb.append(" and lower(gst.gst_namee) like '%"
				+ searchVO.getGstNamee().toLowerCase() + "%'");
		}
		if (StringUtils.isNotEmpty(searchVO.getGrpId())) {
			sb.append(" and gd.grp_id ='" + searchVO.getGrpId()+"'");
		}
		if (StringUtils.isNotEmpty(searchVO.getGrpName())) {
			sb.append(" and gd.grp_name like '%" + searchVO.getGrpName() + "%'");
		}
		if (StringUtils.isNotEmpty(searchVO.getReachDate())
			&& StringUtils.isNotEmpty(searchVO.getLeaveDate())) {
			sb.append(" and (convert(varchar(100),gst.reach_date,23) >='"
				+ searchVO.getReachDate()
				+ "' and convert(varchar(100),gst.leave_date,23)<='"
				+ searchVO.getLeaveDate() + "')");
		} else if (StringUtils.isNotEmpty(searchVO.getReachDate())) {
			sb.append(" and convert(varchar(100),gst.reach_date,23) ='"
				+ searchVO.getReachDate() + "'");
		} else if (StringUtils.isNotEmpty(searchVO.getLeaveDate())) {
			sb.append(" and convert(varchar(100),gst.leave_date,23) ='"
				+ searchVO.getLeaveDate() + "'");
		}
		if (StringUtils.isNotEmpty(searchVO.getNamec())) {
			sb.append(" and td.namec like '%" + searchVO.getNamec() + "%'");
		}
		if (StringUtils.isNotEmpty(searchVO.getSealPerson())) {
			sb.append(" and hc.code_namec like'%" + searchVO.getSealPerson()
				+ "%'");
		}
		if (StringUtils.isNotEmpty(searchVO.getRoomId())) {
			sb.append(" and gst.room_id = '" + searchVO.getRoomId()+"'");
		}
		if (StringUtils.isNotEmpty(searchVO.getChkStat())) {
			sb.append(" and gst.chk_stat ='" + searchVO.getChkStat() + "'");
		} else {
			sb.append(" and gst.chk_stat in('I','O')");
		}
		if (StringUtils.isNotEmpty(searchVO.getCodeVal())) {
			sb.append(" and lower(gst.gst_namee) like '"
				+ searchVO.getCodeVal().toLowerCase() + "%'");
		}
		if (StringUtils.isNotEmpty(searchVO.getCompId())) {
			sb.append(" and gst.comp_id ='"
				+ searchVO.getCompId() + "'");
		}
		if (StringUtils.isNotEmpty(searchVO.getWithId())) {
			sb.append(" and gst.with_id='" + searchVO.getWithId() + "'");
		}
		if(StringUtils.isNotEmpty(searchVO.getOutPerName())){
			sb.append(" and op.out_name like '%"+searchVO.getOutPerName()+"%'");
		}
		if(StringUtils.isNotEmpty(searchVO.getGrpChkid())){
			sb.append("and gst.grp_chkid="+searchVO.getGrpChkid());
		}
		if(StringUtils.isNotEmpty(searchVO.getBillId())){
			sb.append("and (gst.billa_id="+searchVO.getBillId()+" or gst.billb_id="+searchVO.getBillId()+")");
		}
		sb.append(" order by check_id ASC");
		Query query = this.createTransformSqlQuery(sb.toString());
		return query.list();
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public List getGroupList(GroupSearchVO searchVO) {	
			String sql = "SELECT row_number()OVER(ORDER BY gd.reach_date) as sortNum,gd.check_id,gd.grp_id,gd.grp_name,gd.lead_namee,gd.lead_namec,gd.reach_date,gd.leave_date"
				+ ", case gd.chk_stat when 'I' then '在住' when 'O' then '离店' else '' end as chkStat,op2.oper_name,t2.roomNum "
				+", gd.comp_id, gd.bill_id,td.namec,ISNULL((gb.borrow-gb.lent),0.00) AS remainB FROM grp_doc gd"
				+" LEFT JOIN ta_doc td on gd.comp_id=td.id"
				+" LEFT JOIN operator op on op.oper_id = gd.out_oper "
				+" LEFT JOIN gst_bill gb on gb.bill_id = gd.bill_id "
				+" LEFT JOIN operator op2 on op2.oper_id = gd.chk_operid "
				+" LEFT JOIN (select count(*) roomNum,t.grp_chkid from (select grp_chkid,room_id from guestdoc gd group by grp_chkid,room_id)t group by t.grp_chkid)t2 on t2.grp_chkid = gd.check_id"
				+"  where 1=1";
			StringBuilder sb=new StringBuilder(sql);
			if(StringUtils.isNotEmpty(searchVO.getGrpId())){
				sb.append(" and gd.grp_id ='"+searchVO.getGrpId()+"'");
			}
			if(StringUtils.isNotEmpty(searchVO.getGrpName())){
				sb.append(" and gd.grp_name like '%"+searchVO.getGrpName()+"%'");
			}
			if(StringUtils.isNotEmpty(searchVO.getLeadNamec())){
				sb.append(" and gd.lead_namec like '%"+searchVO.getLeadNamec()+"%'");
			}
			if(StringUtils.isNotEmpty(searchVO.getLeadNamec())){
				sb.append(" and gd.lead_namec like '%"+searchVO.getLeadNamec()+"%'");
			}
			if(StringUtils.isNotEmpty(searchVO.getReachDate())){
				sb.append(" and convert(varchar(100),gd.reach_date,23) ='"
						  +searchVO.getReachDate()+"'");
			}
			if(StringUtils.isNotEmpty(searchVO.getLeaveDate())){
				sb.append(" and convert(varchar(100),gd.leave_date,23) ='"
						  +searchVO.getLeaveDate()+"'");
			}
			if(StringUtils.isNotEmpty(searchVO.getNamec())){
				sb.append(" and td.namec like '%"+searchVO.getNamec()+"'");
			}
			if(StringUtils.isNotEmpty(searchVO.getOutPerName())){
				sb.append(" and op.out_name like '%"+searchVO.getOutPerName()+"%'");
			}
			if (StringUtils.isNotEmpty(searchVO.getChkStat())) {
				sb.append(" and gd.chk_stat ='" + searchVO.getChkStat() + "'");
			} else {
				sb.append(" and gd.chk_stat in('I','O')");
			}
			if (StringUtils.isNotEmpty(searchVO.getCompId())) {
				sb.append(" and gd.comp_id ='"
					+ searchVO.getCompId() + "'");
			}
			if(StringUtils.isNotEmpty(searchVO.getRoomId())){
				sb.append(" and gd.check_id = (select grp_chkid from guestdoc where chk_stat='I' and room_id = '"+searchVO.getRoomId()+"')");
			}
			if (StringUtils.isNotEmpty(searchVO.getCodeVal())) {
				sb.append(" and lower(gd.grp_name) like '"
						+ searchVO.getCodeVal().toLowerCase() + "%'");
			}
			if (StringUtils.isNotEmpty(searchVO.getBillId())) {
				sb.append(" and gd.bill_id = "+ searchVO.getBillId() + "");
			}
			Query query = this.createTransformSqlQuery(sb.toString());
			return query.list();
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public List getGroupMembersByRoomId(String roomId) {
		String hql="from Guestdoc gd where gd.grpChkid = ( select grpChkid from Guestdoc where roomId = ?)";
		Query query = this.createQuery(hql,roomId);
		return query.list();
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public List getWebRooms(WebRoomSearchVO searchVO) {
		String hql="select r.room_id,r.build_id,r.floor_no,r.curr_stat,ht.code_namec from rooms r left join hroom_type ht on r.room_type = ht.code_id where r.room_id in(select room_id from room_num where flag in('0','1','4','5','6'))";
		StringBuilder sb = new StringBuilder(hql);
		if(StringUtils.isNotEmpty(searchVO.getBuildId())){
			sb.append(" and r.build_id = "+searchVO.getBuildId());
		}
		if(StringUtils.isNotEmpty(searchVO.getFloorNo())){
			sb.append(" and r.floor_no = "+searchVO.getFloorNo());
		}
		if(StringUtils.isNotEmpty(searchVO.getRoomType())){
			sb.append(" and r.room_type = "+searchVO.getRoomType());
		}
		if(StringUtils.isNotEmpty(searchVO.getRoomCharacter())){
			sb.append(" and r.room_character= "+searchVO.getRoomCharacter());
		}
		Query query = this.createTransformSqlQuery(hql);
		return query.list();
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public int setOrCancleWebRoom(String roomId,String flag) {
//		String hql = "update RoomNum rn set flag=? where roomId=? ";
//		String[] obj = {flag,roomId};
//		Query query = this.createQuery(hql,obj);
		return 0;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public List searchWebRooms(WebRoomSearchVO searchVO) {
		String hql="select r.room_id,r.build_id,r.floor_no,r.curr_stat,ht.code_namec from rooms r left join hroom_type ht on r.room_type = ht.code_id where r.room_id in(select room_id from room_num where flag in('0','1','4','5','6'))";
		StringBuilder sb = new StringBuilder(hql);
		if(StringUtils.isNotEmpty(searchVO.getBuildId())){
			sb.append(" and r.build_id = "+searchVO.getBuildId());
		}
		if(StringUtils.isNotEmpty(searchVO.getFloorNo())){
			sb.append(" and r.floor_no = "+searchVO.getFloorNo());
		}
		if(StringUtils.isNotEmpty(searchVO.getRoomType())){
			sb.append(" and r.room_type = "+searchVO.getRoomType());
		}
		if(StringUtils.isNotEmpty(searchVO.getRoomCharacter())){
			sb.append(" and r.room_character= "+searchVO.getRoomCharacter());
		}
		Query query = this.createTransformSqlQuery(hql);
		return query.list();
	}
	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public List getNoguestList(NoguestSearchVO searchVO) {
		String sql="select ng.id,ng.nogst_id,ng.nogst_name,ng.hotel_flag,ng.status,td.namec from noguest ng left join ta_doc td on td.comp_id=noguest.comp_id";
		StringBuilder sb = new StringBuilder(sql);
		if(StringUtils.isNotEmpty(searchVO.getNogstId())){
			sb.append(" and ng.nogst_id = "+searchVO.getNogstId());
		}
		if(StringUtils.isNotEmpty(searchVO.getNogstName())){
			sb.append(" and ng.nogst_name = "+searchVO.getNogstName());
		}
		if(StringUtils.isNotEmpty(searchVO.getNamec())){
			sb.append(" and td.namec like '%"+searchVO.getNamec()+"%'");
		}
		if(StringUtils.isNotEmpty(searchVO.getConnector())){
			sb.append(" and ng.room_character= "+searchVO.getConnector());
		}
		if(StringUtils.isNotEmpty(searchVO.getCreateStartDate())){
			sb.append(" and convert(varchar(100),ng.crea_time,23)>= "+searchVO.getCreateStartDate());
		}
		if(StringUtils.isNotEmpty(searchVO.getCreateEndDate())){
			sb.append(" and convert(varchar(100),ng.crea_time,23)<= "+searchVO.getCreateEndDate());
		}
		if(StringUtils.isNotEmpty(searchVO.getPayStartDate())){
			sb.append(" and convert(varchar(100),ng.pay_date,23)>= "+searchVO.getPayStartDate());
		}
		if(StringUtils.isNotEmpty(searchVO.getPayEndDate())){
			sb.append(" and convert(varchar(100),ng.pay_date,23)<= "+searchVO.getPayEndDate());
		}
		if(StringUtils.isNotEmpty(searchVO.getBillId())){
			sb.append(" and ng.bill_id= "+searchVO.getBillId());
		}
		Query query = this.createTransformSqlQuery(sql);
		return query.list();
	}

	@Override
	public Map getGuestDocDetail(String checkId) {	
		String sql = "SELECT gst.check_id checkId, gst.room_id roomId,gst.grp_chkid grpChkid, gst.gst_namee gstNamee, gst.gst_namec gstNamec, gst.reach_date reachDate,gst.tele,gst.birthday,gst.gst_kind gstKind,gst.folk,gst.if_bdate ifBdate,gst.chk_ext chkExt,gst.gst_id gstId"+
			 ", gst.leave_date leaveDate, gst.comp_id compId, gst.chk_stat chkStat,gst.comp_type compType,gst.payman_flag paymanFlag,gst.prc_scheme_id prcSchemeId,gd.grp_id grpId,gd.grp_name grpName,gst.chk_operid chkOperid,gst.addr,gst.sex,gst.nt_id ntId,gst.notice,gst.native native_," +
			  " gst.billa_id billaId,gst.billb_id billbId,ht.code_namec,td.namec,td.TA_type TA_type,ht.price" +
			  ",gst.plate_number plateNumber,gst.crdkind_id crdkindId,gst.crd_id crdId,gst.email,hcs.code_namec sexname,gst.gst_ori_id gstOriId,gst.with_id withId"+
			  ",gst.city_ledger cityLedger,gst.house_pay housePay,ISNULL(gst.room_price,ht.price) roomPrice,gst.free_svc freeSvc,gst.hideprice,gst.if_fgst ifFgst,gst.pay_id payId,visakind_id visakindId,visa_id visaId,depart,crd_vld crdVld,in_port inPort,in_date inDate"+
			  ",op.oper_name chkOperName,op2.oper_name rechkOperName,op3.oper_name outOperName,op4.oper_name lastOperName,op5.oper_name lastCashierName,gst.cashier_time cashierTime,ISNULL(gbi.borrow,0.00) borrow,ISNULL(gbi.lent,0.00) lent,ISNULL(gbi.limit,0.00) Alimit,ISNULL(gbi.auth_balance,0.00) auth_balance,ISNULL(gbi2.borrow,0.00) Bborrow,ISNULL(gbi2.lent,0.00) Blent,ISNULL(gbi2.limit,0) Blimit,ISNULL(gbi2.auth_balance,0.00) Bauth_balance,codes.code_namec codeNamec"+
			  " FROM guestdoc gst LEFT JOIN grp_doc gd ON gst.grp_chkid = gd.check_id " +
			  " LEFT JOIN rooms rs ON rs.room_id = gst.room_id"+
			  " LEFT JOIN hroom_type ht ON rs.room_type = ht.code_id"+
			  " LEFT JOIN ta_doc td on gst.comp_id=td.id"+
			  " LEFT JOIN hcodes hc on td.saleman_id = hc.code_id"+
			  " LEFT JOIN hroom_plan hp on hp.id=gst.prc_scheme_id"+
			  " LEFT JOIN hcodes hcs on hcs.code_id = gst.sex "+
			  " LEFT JOIN operator op on op.oper_id = gst.chk_operid"+
			  " LEFT JOIN operator op2 on op2.oper_id = gst.rechk_operid"+
			  " LEFT JOIN operator op3 on op3.oper_id = gst.out_oper"+
			  " LEFT JOIN operator op4 on op4.oper_id = gst.last_oper"+
			  " LEFT JOIN operator op5 on op5.oper_id = gst.last_cashier"+
			  " LEFT JOIN gst_bill gbi on gst.billa_id = gbi.bill_id"+
			  " LEFT JOIN gst_bill gbi2 on gst.billb_id = gbi2.bill_id"+
			  " LEFT JOIN hcodes codes on codes.code_id = gst.pay_id"+
			  " WHERE gst.check_id="+checkId;
		Query query = this.createTransformSqlQuery(sql);
		Map map = (Map)query.uniqueResult();
		return map;
	}

	@Override
	public List getRoomsList(String with_id) {
		String hql = "select distinct(room_id) from guestdoc where room_id is not null and with_id ='"+with_id+"'";
		List list = this.createTransformSqlQuery(hql).list();
		return list;
	}

	@Override
	public List getGstPriceList(String check_id) {
		String sql = "select * from gst_price_list gpl where check_id = ";
		List list = this.createTransformSqlQuery(sql).list();
		return list;
	}

	@Override
	public void updateGuestdoc(Guestdoc guestdoc) {
		String sql = "update guestdoc set gst_namec = '"+guestdoc.getGstNamec()+"',gst_namee='"+guestdoc.getGstNamee()+"',sex='"+guestdoc.getSex()+"',folk='"+guestdoc.getFolk()+"',email='"+guestdoc.getEmail()+"',addr='"+guestdoc.getAddr()
				     +"',tele='"+guestdoc.getTele()+"',birthday="+(guestdoc.getBirthday()==null?null:"'"+new SimpleDateFormat("yyyy-MM-dd").format(guestdoc.getBirthday())+"'")+",nt_id='"+guestdoc.getNtId()+"',native='"+guestdoc.getNative_()+"',plate_number='"+guestdoc.getPlateNumber()
				     +"',crdkind_id='"+guestdoc.getCrdkindId()+"',crd_id='"+guestdoc.getCrdId()+"',notice='"+guestdoc.getNotice()+"',pay_id='"+guestdoc.getPayId()+"',gst_ori_id='"+guestdoc.getGstOriId()+"',prc_scheme_id='"+guestdoc.getPrcSchemeId()
				     +"',gst_kind='"+guestdoc.getGstKind()+"',room_price='"+(guestdoc.getRoomPrice()==null?"0":guestdoc.getRoomPrice())+"',payman_flag="+guestdoc.getPaymanFlag()+",city_ledger="+(guestdoc.getCityLedger()?1:0)
				     +",house_pay="+(guestdoc.getHousePay()?1:0)+",free_svc="+(guestdoc.getFreeSvc()?1:0)+",hideprice="+(guestdoc.getHideprice()?1:0)+",if_fgst="+(guestdoc.getIfFgst()?1:0)+",visakind_id = '"+guestdoc.getVisakindId()+"',visa_id='"+guestdoc.getVisaId()
				     +"',depart='"+guestdoc.getDepart()+"',crd_vld="+(guestdoc.getCrdVld()==null?null:"'"+new SimpleDateFormat("yyyy-MM-dd").format(guestdoc.getCrdVld())+"'")
				     +",in_port='"+guestdoc.getInPort()+"',in_date="+(guestdoc.getInDate()==null?null:"'"+new SimpleDateFormat("yyyy-MM-dd").format(guestdoc.getInDate())+"'")
				     +" , comp_id='"+guestdoc.getCompId()+"',comp_type='"+guestdoc.getCompType()+"',lastOper='" + guestdoc.getLastOper() + "' "
				     +" where check_id='"+guestdoc.getCheckId()+"'";
		Query query = this.createTransformSqlQuery(sql);
		query.executeUpdate();
	}

	@Override
	public void saveGuestdoc(Guestdoc guestdoc) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public Guestdoc copyGuest(Integer checkId){
		return null;
	}

	@Override
	public void updateLimit(Double limit, Integer billId) {
		String sql = "update gst_bill set limit='"+limit+"' where bill_id='"+billId+"'";
		Query query = this.createTransformSqlQuery(sql);
		query.executeUpdate();
	}

	@Override
	public void addLimit(Double limit, Integer billId) {
		
	}

	@Override
	public void updateOther(Guestdoc guestdoc) {
		// TODO Auto-generated method stub
		String sql = "update guestdoc set visakind_id = '"+guestdoc.getVisakindId()+"',visa_id='"+guestdoc.getVisaId()+"',depart='"+guestdoc.getDepart()+"',crd_vld='"+(guestdoc.getCrdVld()==null?"":new SimpleDateFormat("yyyy-MM-dd").format(guestdoc.getCrdVld()))
	     +"',in_port='"+guestdoc.getInPort()+"',in_date='"+(guestdoc.getInDate()==null?"":new SimpleDateFormat("yyyy-MM-dd").format(guestdoc.getInDate()))+"' where check_id='"+guestdoc.getCheckId()+"'";
		Query query = this.createTransformSqlQuery(sql);
		query.executeUpdate();
	}

	@Override
	public void updeteGuestIfbate(Integer checkId, String ifBate) {
		String sql = "update guestdoc set if_bdate="+ifBate+" where check_id="+checkId;
		Query query = this.createTransformSqlQuery(sql);
		query.executeUpdate();
	}

	@Override
	public List selectRoomsWardToLive(String room_id) {
		StringBuilder sb = new StringBuilder();
//		sb.append("select gd.gst_namec gst_namec,gd.room_id room_id,gd.gst_namee gst_namee,gd.reach_date reach_date,gd.leave_date leave_date,gd.check_id check_id,gd.payman_flag payman_flag,gd.billb_id billb_id,gd.with_id with_id,gd.room_price room_price,gd.chk_ext chk_ext,r.curr_stat curr_stat");
//		sb.append(" from rooms r left join guestdoc gd on r.room_id=gd.room_id");
//		sb.append(" where gd.chk_stat='I'");
//		sb.append(" and r.room_id='"+room_id+"'");
//		sb.append(" and r.status<>'2'");
		//查询房间的住客信息以及房间的状态、房间住客性质（团/联/散；grp_chkid存在为团，否则如果房间住客同来人有多间房为联，否则为散）
		sb.append("select x.room_id, (case when x.diary_stat is null then x.curr_stat else x.diary_stat end) curr_stat, y.gst_namec, y.gst_namee, convert(varchar(21), y.reach_date, 23) reach_date, convert(varchar(21), y.leave_date, 23) leave_date, y.check_id, y.with_id, convert(varchar(100), cast(case when y.room_price is null then 0 else y.room_price end as money), 1) room_price, y.chk_ext, ");
		sb.append("(case when y.grp_chkid is not null and y.grp_chkid <> 0 then 'T' when y.check_id IS NOT NULL AND x.room_num > 1 then 'L' when y.check_id IS NOT NULL AND x.room_num = 1 then 'S' else 'K' end) gst_type ");
		sb.append("from (select a.room_id, a.curr_stat, (select count(distinct b.room_id) from guestdoc b where b.chk_stat = 'I' and b.with_id in (select with_id from guestdoc where room_id = a.room_id)) room_num, (select c.room_stat from rooms_diary c where c.room_id = a.room_id and datediff(day, c.hotel_date, (select para3 from parameter where id = 1)) = 0) diary_stat from rooms a where rtrim(ltrim(a.room_id)) = '"+room_id+"' and a.status <> '2') x ");
		sb.append("left join guestdoc y on x.room_id = y.room_id and y.chk_stat = 'I'");
//		sb.append("where y.chk_stat = 'I'");
		List list = this.createTransformSqlQuery(sb.toString()).list();
		return list;
	}

	@Override
	public List checkToLive(String billb_id) {
		String sql = "select distinct gd.room_id from guestdoc gd where gd.billb_id= "+Integer.parseInt(billb_id);
		List list = this.createTransformSqlQuery(sql).list();
		return list;
	}

	@Override
	public void updateWardsToLive(Map map){
		int temp_billBid_Num = Integer.parseInt(map.get("temp_billBid_Num")+"");
		String sql = "";
		String sql1 = "";
		//被换房人的主键ID
		String check_id = map.get("check_id")+"";
		//被换入的房间号码
		String room_id = map.get("room_id")+"";
		//指定新的付款人和主人的主键ID
		String new_check_id = map.get("new_check_id")+"";
		//是否是主人
		String chk_ext = map.get("chk_ext")+"";
		//是否是付款人
		String payman_flag = map.get("payman_flag")+"";
		//根据被换入的房间号查询出billb_id以及with_id
		String hq = "select distinct gd.with_id from guestdoc gd where gd.with_id=gd.billb_id and gd.room_id='"+room_id+"'";
		List list = this.createTransformSqlQuery(hq).list();
		//换房的为付款人的时候指定付款人
		if("1".equals(payman_flag)){
			//若查询出来小于0说明换入空房,变房号新生成B账号和with_id
			if(list.size()<=0){
				sql = "update guestdoc set room_id ='"+room_id+"',with_id="+temp_billBid_Num+",billb_id="+temp_billBid_Num+" where check_id="+check_id;
			}else{
				Map dmap = (Map)list.get(0);
				int with_id = Integer.parseInt(dmap.get("with_id")+"");
				sql = "update guestdoc set room_id ='"+room_id+"',with_id="+with_id+",billb_id="+with_id+",payman_flag='0',chk_ext='0' where check_id="+check_id+"";
			}
			sql1 = "update guestdoc set payman_flag = '1' where check_id="+new_check_id;
			this.createTransformSqlQuery(sql).executeUpdate();
			this.createTransformSqlQuery(sql1).executeUpdate();
		}
		//换房的为主人的时候指定主人
		if("主人".equals(chk_ext)){
			//若查询出来小于0说明换入空房,变房号新生成B账号和with_id
			if(list.size()<=0){
				sql = "update guestdoc set room_id ='"+room_id+"',with_id="+temp_billBid_Num+",billb_id="+temp_billBid_Num+" where check_id="+check_id;
			}else{
				Map dmap = (Map)list.get(0);
				int with_id = Integer.parseInt(dmap.get("with_id")+"");
				sql = "update guestdoc set room_id ='"+room_id+"',with_id="+with_id+",billb_id="+with_id+",payman_flag='0',chk_ext='0' where check_id="+check_id+"";
			}
			sql1 = "update guestdoc set chk_ext = '1' where check_id="+new_check_id;
			this.createTransformSqlQuery(sql).executeUpdate();
			this.createTransformSqlQuery(sql1).executeUpdate();
		}else{
			//若查询出来小于0说明换入空房,变房号新生成B账号和with_id
			if(list.size()<=0){
				sql = "update guestdoc set room_id ='"+room_id+"',with_id="+temp_billBid_Num+",billb_id="+temp_billBid_Num+" where check_id="+check_id;
			}else{
				Map dmap = (Map)list.get(0);
				int with_id = Integer.parseInt(dmap.get("with_id")+"");
				sql = "update guestdoc set room_id ='"+room_id+"',with_id="+with_id+",billb_id="+with_id+",payman_flag='0',chk_ext='0' where check_id="+check_id+"";
			}
			this.createTransformSqlQuery(sql).executeUpdate();
		}
		
	}
	
	@Override
	public List getBills(Integer billType, String billId,String showType,String consId,String okFlag,String startDate,String endDate,String isInvalid) {
		StringBuilder sb = new StringBuilder();
		String baseSql = "";
		String flagSql = "";
		String dateSql = "";
		String isvalidSql = "";
		// 未结（N）、已结(Y)、全部
		if ("N".equals(okFlag)) {
			flagSql = " and b.ok_flag=0";
		} else if ("Y".equals(okFlag)) {
			flagSql = " and ( b.ok_flag=1 or b.ok_flag=2)";
		}
		//是否是无效单
		if("N".equals(isInvalid)){
			isvalidSql = " (b.status=2 or b.ext_name='转出')";
		}else {
			isvalidSql = " b.status=0 and b.ext_name!='转出'";
		}
		// 开始及结束日期筛选
		if (StringUtils.isNotEmpty(startDate) && StringUtils.isNotEmpty(endDate)) {
//			dateSql += " and b.oper_time>=cast('" + startDate
//					+ "' as datetime)";
			
			dateSql += " and (convert(varchar(100),b.oper_time,23) >='"
					+ startDate
					+ "' and convert(varchar(100),b.oper_time,23)<='"
					+ endDate + "')" ;
		}
//		if (StringUtils.isNotEmpty(endDate)) {
//			dateSql += " and b.oper_time<=cast('" + endDate + "' as datetime)";
//		}
		//明细（A）、汇总（B）、分类（C）
		if ("A".equals(showType)) {
			baseSql = "select row_number()OVER(ORDER BY b.ID) as sortNum,b.ID,b.acco_id,b.balance,b.foreignm,b.money_kind moneyKind,b.svc_charge,b.status,b.oper_time,b.ext_name,b.room_id,b.notes,b.pay_num payNum,hex.code_namec,op.oper_name,hc.code_namec cname,hs.code_namec sname,b.setl_id setlId,b.oper_id operId,b.ok_flag okFlag from bills b left join hexchange hex on b.money_kind = hex.code_id"
					+ " inner join operator op on op.oper_id = b.oper_id "
					+ " left join hconsume hc on hc.code_id = b.cons_id "
					+ " left join hsettle hs on hs.code_id = b.setl_id "
					+ " where "+isvalidSql
					+ flagSql + dateSql
					+ " and b.bill_id=" + billId;
		} else if ("B".equals(showType)) {
			baseSql = "select t.*,row_number()OVER(ORDER BY t.cname desc) as sortNum from (select '*' as ID,'*' as acco_id,'*' as money_kind,sum(b.balance) balance,sum(b.svc_charge) svc_charge,hc.code_namec cname,'' as sname from bills b "
					+ " inner join hconsume hc on hc.code_id = b.cons_id where b.status=0 and b.bill_id="
					+ billId + " and b.bill_type=" + billType
					+ flagSql + dateSql
					+" group by b.cons_id,hc.code_namec"
					+" union " 
					+" select '*' as ID,'*' as acco_id,'*' as money_kind,sum(b.balance) balance,sum(b.svc_charge) svc_charge,'' as cname,hs.code_namec sname from bills b "
					+ " inner join hsettle hs on hs.code_id = b.setl_id where "+isvalidSql+" and b.bill_id="
					+ billId  + flagSql + dateSql
					+" group by b.setl_id,hs.code_namec)t";
		} else if ("C".equals(showType)) {
			baseSql = "select t.*,row_number()OVER(ORDER BY t.cname desc) as sortNum from (select '*' as ID,'*' as acco_id,'*' as money_kind,sum(b.balance) balance,sum(b.svc_charge) svc_charge,cs.code_namec cname,'' as sname  from bills b"
					+ " inner join hconsume hc on hc.code_id = b.cons_id "
					+ " inner join hcodes cs on cs.code_id = hc.kind"
					+ " where" + isvalidSql
					+ " and b.bill_id=" + billId
					+ flagSql + dateSql
					+" group by hc.kind,cs.code_namec"
					+" union"
					+" select '*' as ID,'*' as acco_id,'*' as money_kind,sum(b.balance) balance,sum(b.svc_charge) svc_charge,'' as cname,hk.code_namec sname from bills b"
					+ " inner join hsettle hs on hs.code_id = b.setl_id "
					+ " inner join hsetl_kind hk on hs.kind = hk.code_id"
					+ " where"+isvalidSql
					+ " and b.bill_id=" + billId
					+ flagSql + dateSql
					+" group by hs.kind,hk.code_namec)t";
		}
		
		List list = this.createTransformSqlQuery(baseSql).list();
		this.getSession().clear();
		return list;
	}

	@Override
	public List getselectRoomToleave(String nowDate) {
		StringBuilder sb = new StringBuilder();
		sb.append("select gd.check_id check_id,gd.gst_namee gst_namee,gd.gst_namec gst_namec,gd.reach_date reach_date,gd.leave_date leave_date,gd.room_id room_id,gd.room_price room_price,gd.chk_ext chk_ext,gd.If_bdate If_bdate");
		sb.append(" from guestdoc gd where gd.leave_date='"+nowDate+"'");
		List list = this.createTransformSqlQuery(sb.toString()).list();
		return list;
	}

	@Override
	public void updateRoomToleave(JSONArray jsonArray) {
		for (int i = 0; i < jsonArray.size(); i++) {
			JSONObject obj = (JSONObject) jsonArray.get(i);
			int check_id = Integer.parseInt(obj.get("check_id")+"");
			String leave_date = (String) obj.get("leave_date");
			String sql = "update guestdoc set leave_date='"+leave_date+"' where check_id="+check_id;
			this.createTransformSqlQuery(sql).executeUpdate();
		}
	}

	@Override
	public List loadConsumesByBillType(String billId) {
		String sql = "select b.cons_id,hc.code_namec from bills b inner join hconsume hc on b.cons_id = hc.code_id where b.bill_id="+billId;
		List list = this.createTransformSqlQuery(sql).list();
		return list;
	}
    
	/**
	 * 入账后保存或更新gstbill的值
	 * <功能详细描述>
	 * @param bill
	 * @see GuestdocDaoImpl.java
	 */
	public void saveOrUpdateGstBill(Bills bill){
		//根据billid查询gstbill数据
		GstBill gstBill = gstBillDao.get(bill.getBillId());
		//如果不存在则新增，存在则更新
		if (gstBill == null) {
			gstBill = new GstBill();
			gstBill.setBillId(bill.getBillId());
			gstBill.setBorrow(bill.getBalance());
			gstBillDao.save(gstBill);
		} else {
			gstBill.setBorrow(gstBill.getBorrow() + bill.getBalance()
					+ (bill.getSvcCharge()==null?0:bill.getSvcCharge()));
			gstBillDao.update(gstBill);
		}
	}
	
	public void saveOrUpdateGstBill2(Bills bill){
		//根据billid查询gstbill数据
		GstBill gstBill = gstBillDao.get(bill.getBillId());
		//如果不存在则新增，存在则更新
		if (gstBill == null) {
			gstBill = new GstBill();
			gstBill.setBillId(bill.getBillId());
			gstBill.setLent(setMoney(bill.getBalance()));
			gstBillDao.save(gstBill);
		} else {
			gstBill.setLent(gstBill.getLent() + setMoney(bill.getBalance()));
			gstBillDao.update(gstBill);
		}
	}


	@Override
	public List<Map<String, Object>> getCannotUseRoom(String roomId,
			String startDate, String endDate) {
		List<Map<String, Object>> result = new ArrayList<Map<String,Object>>();
		StringBuffer querySQL = new StringBuffer();
		querySQL.append("SELECT room_id,startDate,endDate,inf FROM  (SELECT rd.room_id,rn.reach_date AS "
				+ "startDate,rn.leave_date AS endDate ,'留房  ('+ "
				+ "g.gst_namec+g.mobile + ')' AS inf FROM guestdoc g ");
		querySQL.append(" LEFT JOIN room_num rn on g.check_id=rn.check_id ");
		querySQL.append(" LEFT JOIN rooms_diary rd on rd.room_id=rn.room_id ");
		querySQL.append(" WHERE rd.room_id='");
		querySQL.append(roomId + "'");
		querySQL.append(" AND DATEDIFF(day,rn.reach_date,'" + startDate + "')<=0 ");
		querySQL.append(" AND DATEDIFF(day,rn.reach_date,'" + endDate + "')>=0  ");
		querySQL.append(" AND rn.status=0 AND rn.flag=0 ");
		querySQL.append(" UNION ");
		querySQL.append(" SELECT room_id,reach_date AS startDate,leave_date AS enddate,");
		querySQL.append(" CASE flag WHEN 2 THEN '维修房  (' + SUBSTRING(note,0,10)  + ')' ");
		querySQL.append(" WHEN 3 THEN '冻结房  ('+ SUBSTRING(note,0,10)  + ')' ");
		querySQL.append(" WHEN 4 THEN '网房 ('+ SUBSTRING(note,0,10)  + ')' ");
		querySQL.append("  WHEN 4 THEN '网房预定  ('+ SUBSTRING(note,0,10)   + ')' ");
		querySQL.append(" WHEN 4 THEN '网房留房  ('+ SUBSTRING(note,0,10)   + ')' ");
		querySQL.append(" END AS inf ");
		querySQL.append(" FROM room_num ");
		querySQL.append(" WHERE room_id='" + roomId + "' AND status=0 ");
		querySQL.append(" AND DATEDIFF(day,reach_date,'" + startDate + "')<=0 ");
		querySQL.append(" AND DATEDIFF(day,reach_date,'" + endDate + "')>=0 ");
		querySQL.append(" AND DATEDIFF(day,leave_date,'" + startDate + "')<=0 ");
		querySQL.append(" AND flag<>0  ");//) rs
		querySQL.append(" UNION ");
		querySQL.append(" SELECT DISTINCT rd.room_id,rn.reach_date AS startDate,rn.leave_date AS endDate ");
		querySQL.append(" ,'留房  ('+ g.lead_namec+g.mobile + ')' AS inf FROM grp_doc g");
		querySQL.append(" LEFT JOIN room_num rn on g.check_id=rn.check_id ");
		querySQL.append(" LEFT JOIN rooms_diary rd on rd.room_id=rn.room_id  ");
		querySQL.append(" WHERE rd.room_id='");
		querySQL.append(roomId + "'");
		querySQL.append(" AND DATEDIFF(day,rn.reach_date,'" + startDate + "')<=0 ");
		querySQL.append(" AND DATEDIFF(day,rn.reach_date,'" + endDate + "')>=0  ");
		querySQL.append(" AND rn.status=0 AND rn.flag=0 ");
		querySQL.append(" ) rs");
		List<Object[]> objs = this.getSession().createSQLQuery(querySQL.toString()).list();
		for(Object[] obj :objs){
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("roomid", obj[0].toString());
			map.put("startDate", obj[1]);
			map.put("endDate", obj[2]);
			map.put("info", obj[3]);
			result.add(map);
		}
		return result;
	}


	@Override
	public List getPreAuthorization(Integer checkId,String status,Integer billId) {
		String sql = "select au.*,op.oper_name from gst_credit_auth au left join operator op on au.oper_id = op.oper_id where au.bill_id="+billId;
		if(StringUtils.isNotEmpty(status)){
			sql+=" and au.status="+status;
		}
		List list = this.createTransformSqlQuery(sql).list();
		return list;
	}

	@Override
	public int getMaxPayNum() {
		String sql = "select max(pay_num) maxNum from bills";
		String max = (String)((Map)this.createTransformSqlQuery(sql).uniqueResult()).get("maxNum");
		return StringUtils.isEmpty(max) ? 0 : Integer.valueOf(max);
	}

	@Override
	public List<?> findFitBookCheckInList(BookRoomSearchVo bookRoomSearchVo)
			throws Exception {
		final List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
		final StringBuilder sbd = new StringBuilder()  ;
		sbd.append(" SELECT   \n ") ;
		sbd.append("     t.check_id,  \n ") ;
		sbd.append("     t.book_list,  \n ") ;
		sbd.append("     t.gst_namee,  \n ") ;
		sbd.append("     t.gst_namec,  \n ") ;
		sbd.append("     t.book_stat,  \n ") ;
		sbd.append("     t.with_id,  \n ") ;
		sbd.append("     t1.book_room_id,  \n ") ;
		sbd.append("     t1.roomtype_id,  \n ") ;
		sbd.append("     t1.reach_date,  \n ") ;
		sbd.append("     t1.leave_date,  \n ") ;
		sbd.append("     t1.book_num,  \n ") ;
		sbd.append("     t1.save_num,  \n ") ;
		sbd.append("     t1.reach_num,  \n ") ;
		sbd.append("     t2.code_id,  \n ") ;
		sbd.append("     t2.code_namec,  \n ") ;
		sbd.append("     t2.code_namee   \n ") ;
		sbd.append(" FROM  \n ") ;
		sbd.append("     guestdoc t   \n ") ;
		sbd.append("     LEFT JOIN book_room t1   \n ") ;
		sbd.append("         ON t.check_id = t1.check_id   \n ") ;
		sbd.append("     LEFT JOIN hroom_type t2   \n ") ;
		sbd.append("         ON t1.roomtype_id = t2.code_id   \n ") ;
		sbd.append(" WHERE 1 = 1   \n ") ;
		sbd.append(" AND (t1.status = 0 or t1.status = 2) ");
		sbd.append(" AND t.book_list is not null ");
		sbd.append(" AND t.book_list != '' ");
		
		//#或者有效	备注：有效订单指状态为“B 未确认、已确认($：已付订金（自动）G 有担保（人工）O 人工确认（人工）)、R 部分抵达”的订单
		sbd.append(" AND t.book_stat in ('B','$','G','O','R')");
		// 查询抵店日期等于今天的 ==== 正式应用时 放开该代码
		//sbd.append(" and datediff(day,t.reach_date,'"+DateFormatUtils.format(paramDao.GetHotelDate(), "yyyy-MM-dd")+"') =0 ");
		
		
		if(bookRoomSearchVo != null ){
			// *（symbol = 1时） 无效  
			if(bookRoomSearchVo.getSymbol()!=null && bookRoomSearchVo.getSymbol()==1)
				return null ;
			/*if(bookRoomSearchVo.getSymbol()==null){
				//#或者有效	备注：有效订单指状态为“B 未确认、已确认($：已付订金（自动）G 有担保（人工）O 人工确认（人工）)、R 部分抵达”的订单
				sbd.append(" AND t.book_stat in ('B','$','G','O','R')");
			}else if(bookRoomSearchVo.getSymbol()==1){
				//*或者无效 C：取消（无效） P：过期（未确认预订未到变成过期。夜审时自动修改） A：全部抵达（自动）N：Noshow（已确认预订未到变成Noshow，要收取Noshow charge。夜审时自动修改）
				sbd.append(" AND t.book_stat in ('C','N','P','A') ");
			}*/
			if(StringUtils.isNotBlank(bookRoomSearchVo.getBookStat())){
				sbd.append(" and t.book_stat "+("O".equals(bookRoomSearchVo.getBookStat()) ? " in('O','$') " : (" = '"+ bookRoomSearchVo.getBookStat()+"'")));
			}
			if(StringUtils.isNotBlank(bookRoomSearchVo.getBook_list())){
				sbd.append(" and t.book_list like '%"+bookRoomSearchVo.getBook_list()+"%'");
			}
			if(StringUtils.isNotBlank(bookRoomSearchVo.getGstNamee())){
				sbd.append(" and t.gst_namee like '%"+bookRoomSearchVo.getGstNamee()+"%'");
			}
			if(StringUtils.isNotBlank(bookRoomSearchVo.getCodeLetter())){
				//sbd.append(" and charindex ('"+bookRoomSearchVo.getCodeLetter()+"', lower(t.gst_namee)) = 1 ");
				sbd.append(" and lower(t.gst_namee) like '%"+bookRoomSearchVo.getCodeLetter()+"%'");
			}
			if(StringUtils.isNotBlank(bookRoomSearchVo.getGstNamec())){
				sbd.append(" and t.gst_namec like '%"+bookRoomSearchVo.getGstNamec()+"%'");
			}
			// 操作人
			if(StringUtils.isNotBlank(bookRoomSearchVo.getBook_operid())){
				sbd.append(" and t.booker_name like '%"+bookRoomSearchVo.getBook_operid()+"%'");
			}
			if(StringUtils.isNotBlank(bookRoomSearchVo.getMobile())){
				sbd.append(" and t.mobile like '%"+bookRoomSearchVo.getMobile()+"%'");
			}
			/*// 合约单位
			if(StringUtils.isNotBlank(bookRoomSearchVo.getComp_name())){
				sbd.append(" and t.comp_id like '%"+bookRoomSearchVo.getComp_name()+"%'");
			}*/
			/*// 销售员
			if(StringUtils.isNotBlank(bookRoomSearchVo.getSalePeson())){
				sbd.append(" and t.mobile like '%"+bookRoomSearchVo.getMobile()+"%'");
			}*/
			// 房间号
			if(StringUtils.isNotBlank(bookRoomSearchVo.getRoom_id())){
				sbd.append(" and t.check_id in ( select distinct check_id from room_num where room_id like '%"+bookRoomSearchVo.getRoom_id()+"%' )");
			}
		}
		sbd.append(" and t.check_id in ( select distinct check_id from book_room where datediff(day,reach_date,'"+DateFormatUtils.format(paramDao.GetHotelDate(), "yyyy-MM-dd")+"') =0 and status = 0 "
				+ (StringUtils.isNotBlank(bookRoomSearchVo.getLeaveDate()) ? (" and datediff(day,leave_date,'"+bookRoomSearchVo.getLeaveDate()+"')=0 )") : ")"));
		
		sbd.append(" ORDER BY t.check_id ASC , t1.book_room_id ASC   \n ");
		getSession().doWork(new Work() {
			@Override
			public void execute(Connection connection) throws SQLException {
				
				PreparedStatement pstmt= connection.prepareStatement(sbd.toString()) ;
				ResultSet rs = pstmt.executeQuery() ;
				while(rs.next()){
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("checkId", rs.getInt("check_id")) ;
					map.put("bookOrderNo", rs.getString("book_list")) ;
					map.put("bookStat", rs.getString("book_stat")) ;
					map.put("gstNamee", rs.getString("gst_namee")) ;
					map.put("gstNamec", rs.getString("gst_namec")) ;
					map.put("withId", rs.getString("with_id")) ;
					map.put("bookRoomId", rs.getString("book_room_id")) ;
					map.put("roomTypeId", rs.getString("roomtype_id")) ;
					String rd = rs.getString("reach_date") ;
					String ld = rs.getString("leave_date") ;
					map.put("reachDate", StringUtils.isNotBlank(rd) ? rd.substring(0, 10) : rd) ;
					map.put("leaveDate", StringUtils.isNotBlank(ld) ? ld.substring(0, 10) : ld) ;
					map.put("saveNum", rs.getInt("save_num")) ;
					map.put("reachNum", rs.getInt("reach_num")) ;
					map.put("bookNum", rs.getInt("book_num")) ;
					map.put("roomTypeCodeId", rs.getString("code_id")) ;
					map.put("roomTypeNamec", rs.getString("code_namec")) ;
					map.put("roomTypeNamee", rs.getString("code_namee")) ;
					list.add(map) ;
				}
				if(rs != null){
		            try {
		                rs.close();
		                rs=null;
		            } catch (Exception ex) {
		                rs=null;
		            }
		        } 
		        if(pstmt != null){
		            try {
		            	pstmt.close();
		            	pstmt=null;
		            } catch (Exception ex) {
		            	pstmt=null;
		            }
		        }
			}
		});
		return list;
	}
	@Override
	public List<?> findFitBookCheckInInfoListBy(BookRoomCheckInVO bookRoomCheckInVO)
			throws Exception {
		final List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
		final StringBuilder sbd = new StringBuilder()  ;
		sbd.append(" SELECT   \n ") ;
		sbd.append("     t1.id,  \n ") ;
		sbd.append("     t1.check_id,  \n ") ;
		sbd.append("     t1.book_room_id,  \n ") ;
		sbd.append("     t1.roomtype_id,  \n ") ;
		sbd.append("     t1.reach_date,  \n ") ;
		sbd.append("     t1.leave_date,  \n ") ;
		sbd.append("     t1.room_price,  \n ") ;
		sbd.append("     t1.book_num,  \n ") ;
		sbd.append("     t1.save_num,  \n ") ;
		sbd.append("     t1.reach_num,  \n ") ;
		sbd.append("     t1.status, \n ") ;
		sbd.append("     t2.code_id,  \n ") ;
		sbd.append("     t2.code_namec,  \n ") ;
		sbd.append("     t2.code_namee,   \n ") ;
		sbd.append("     count(t3.id) as checkInNum   \n ") ;
		sbd.append(" FROM  \n ") ;
		sbd.append("     book_room t1   \n ") ;
		sbd.append("     LEFT JOIN hroom_type t2   \n ") ;
		sbd.append("         ON t1.roomtype_id = t2.code_id   \n ") ;
		sbd.append("     LEFT JOIN book_room_checkin t3   \n ") ;
		sbd.append("         ON t1.book_room_id = t3.room_link_id   \n ") ;
		sbd.append(" WHERE t1.status = 0 \n ");
		//  抵店日期等于今天(酒店日期)的记录
		sbd.append(" AND datediff(day,t1.reach_date,'"+DateFormatUtils.format(paramDao.GetHotelDate(), "yyyy-MM-dd")+"')=0   \n ");
		sbd.append(" AND t1.check_id = " +bookRoomCheckInVO.getCheckId());
		sbd.append(" GROUP BY t1.id,       \n ");
		sbd.append("     t1.check_id,       \n ");
		sbd.append("     t1.book_room_id,       \n ");
		sbd.append("     t1.roomtype_id,       \n ");
		sbd.append("     t1.reach_date,       \n ");
		sbd.append("     t1.leave_date,       \n ");
		sbd.append("     t1.room_price,       \n ");
		sbd.append("     t1.book_num,       \n ");
		sbd.append("     t1.save_num,       \n ");
		sbd.append("     t1.reach_num,       \n ");
		sbd.append("     t1.status,       \n ");
		sbd.append("     t2.code_id,       \n ");
		sbd.append("     t2.code_namec,       \n ");
		sbd.append("     t2.code_namee        \n ");
		sbd.append(" ORDER BY t1.book_room_id ASC        \n ");
		getSession().doWork(new Work() {
			@Override
			public void execute(Connection connection) throws SQLException {
				
				PreparedStatement pstmt= connection.prepareStatement(sbd.toString()) ;
				ResultSet rs = pstmt.executeQuery() ;
				while(rs.next()){
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("id", rs.getInt("id")) ;
					map.put("checkId", rs.getInt("check_id")) ;
					map.put("bookRoomId", rs.getString("id")) ;
					map.put("roomTypeId", rs.getString("roomtype_id")) ;
					map.put("roomPrice", rs.getString("room_price")) ;
					String rd = rs.getString("reach_date") ;
					String ld = rs.getString("leave_date") ;
					map.put("reachDate", StringUtils.isNotBlank(rd) ? rd.substring(0, 10) : rd) ;
					map.put("leaveDate", StringUtils.isNotBlank(ld) ? ld.substring(0, 10) : ld) ;
					map.put("saveNum", rs.getInt("save_num")) ;
					map.put("reachNum", rs.getInt("reach_num")) ;
					map.put("bookNum", rs.getInt("book_num")) ;
					map.put("status", rs.getInt("status")) ;
					map.put("roomTypeCodeId", rs.getString("code_id")) ;
					map.put("roomTypeNamec", rs.getString("code_namec")) ;
					map.put("roomTypeNamee", rs.getString("code_namee")) ;
					map.put("checkInNum", rs.getInt("checkInNum")) ;
					
					// 该记录的留房信息列表
					try {
						map.put("saveRoomInfoList", findBookRoomsListBy(new BookRoomCheckInVO(rs.getString("check_id"), rs.getInt("id")))) ;
					} catch (Exception e) {
						map.put("saveRoomInfoList", null) ;
					}
					
					list.add(map) ;
				}
				if(rs != null){
		            try {
		                rs.close();
		                rs=null;
		            } catch (Exception ex) {
		                rs=null;
		            }
		        } 
		        if(pstmt != null){
		            try {
		            	pstmt.close();
		            	pstmt=null;
		            } catch (Exception ex) {
		            	pstmt=null;
		            }
		        }
			}
		});
		return list;
	}
	@Override
	public List<?> findBookRoomsListBy(BookRoomCheckInVO bookRoomCheckInVO)
			throws Exception {
		final List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
		final StringBuilder sbd = new StringBuilder()  ;
		// 该记录的留房信息列表
		sbd.append(" SELECT DISTINCT        \n ");
		sbd.append("     t.id,       \n ");
		sbd.append("     t.room_chkid,       \n ");
		sbd.append("     t.check_id,       \n ");
		sbd.append("     t.room_id,       \n ");
		sbd.append("     t.reach_date,       \n ");
		sbd.append("     t.leave_date,       \n ");
		sbd.append("     t.flag,       \n ");
		sbd.append("     t.status,       \n ");
		sbd.append("     t.keep_flag,       \n ");
		sbd.append("     t.book_id,       \n ");
		sbd.append("     t.room_price        \n ");
		sbd.append(" FROM       \n ");
		sbd.append("     room_num t        \n ");
		sbd.append(" WHERE t.flag = 0 and t.status = 0    \n ");
		sbd.append("     AND t.book_id = "+bookRoomCheckInVO.getBookId()+"        \n ");
		sbd.append("     AND t.check_id = "+bookRoomCheckInVO.getCheckId()+"        \n ");
		getSession().doWork(new Work() {
			@Override
			public void execute(Connection connection) throws SQLException {
				
				PreparedStatement pstmt= connection.prepareStatement(sbd.toString()) ;
				ResultSet rs = pstmt.executeQuery() ;
				while(rs.next()){
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("id", rs.getInt("id")) ;
					map.put("roomChkid", rs.getInt("room_chkid")) ;
					map.put("checkId", rs.getInt("check_id")) ;
					map.put("roomId", rs.getString("room_id")) ;
					String rd1 = rs.getString("reach_date") ;
					String ld1 = rs.getString("leave_date") ;
					map.put("reachDate", StringUtils.isNotBlank(rd1) ? rd1.substring(0, 10) : rd1) ;
					map.put("leaveDate", StringUtils.isNotBlank(ld1) ? ld1.substring(0, 10) : ld1) ;
					map.put("flag", rs.getInt("flag")) ;
					map.put("status", rs.getString("status")) ;
					map.put("keepFlag", rs.getString("keep_flag")) ;
					map.put("bookId", rs.getInt("book_id")) ;
					map.put("roomPrice", rs.getFloat("room_price")) ;
					list.add(map) ;
				}
				if(rs != null){
		            try {
		                rs.close();
		                rs=null;
		            } catch (Exception ex) {
		                rs=null;
		            }
		        } 
		        if(pstmt != null){
		            try {
		            	pstmt.close();
		            	pstmt=null;
		            } catch (Exception ex) {
		            	pstmt=null;
		            }
		        }
			}
		});
		return list;
	}
	@Override
	public List<?> findAlreadyCheckInPersonListBy(BookRoomCheckInVO bookRoomCheckInVO) throws Exception {
		final List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
		final StringBuilder sbd = new StringBuilder()  ;
		// 已入住人员列表
		sbd.append("  SELECT DISTINCT       \n ");
		sbd.append("      t.room_id,      \n ");
		sbd.append("      t.room_typeid,      \n ");
		sbd.append("      t.gst_id,      \n ");
		sbd.append("      t.check_id,      \n ");
		sbd.append("      t.gst_namee,      \n ");
		sbd.append("      t.gst_namec,      \n ");
		sbd.append("      t.sex,      \n ");
		sbd.append("      t.age       \n ");
		sbd.append("  FROM      \n ");
		sbd.append("      guestdoc t       \n ");
		sbd.append("  WHERE t.chk_stat = 'I'       \n ");
		sbd.append("      AND t.book_list = '"+bookRoomCheckInVO.getBookList()+"'       \n ");
		sbd.append("      AND t.check_id <> "+bookRoomCheckInVO.getCheckId()+"     \n ");
		if(bookRoomCheckInVO.getGrpChkid() != null){
			sbd.append("      AND t.grp_chkid = "+bookRoomCheckInVO.getGrpChkid()+"     \n ");
		}
		sbd.append("  ORDER BY t.room_id ASC     \n ");
		getSession().doWork(new Work() {
			@Override
			public void execute(Connection connection) throws SQLException {
				PreparedStatement pstmt= connection.prepareStatement(sbd.toString()) ;
				ResultSet rs = pstmt.executeQuery() ;
				while(rs.next()){
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("roomId", rs.getString("room_id")) ;
					map.put("roomTypeid", rs.getString("room_typeid")) ;
					map.put("gstId", rs.getInt("gst_id")) ;
					map.put("checkId", rs.getInt("check_id")) ;
					map.put("gstNamee", rs.getString("gst_namee")) ;
					map.put("gstNamec", rs.getString("gst_namec")) ;
					map.put("sex", rs.getString("sex")) ;
					map.put("age", rs.getString("age")) ;
					list.add(map) ;
				}
				if(rs != null){
		            try {
		                rs.close();
		                rs=null;
		            } catch (Exception ex) {
		                rs=null;
		            }
		        } 
		        if(pstmt != null){
		            try {
		            	pstmt.close();
		            	pstmt=null;
		            } catch (Exception ex) {
		            	pstmt=null;
		            }
		        }
			}
		});
		return list;
	}

	@Override
	public List<?> findGroupBookCheckInList(BookRoomSearchVo bookRoomSearchVo)
			throws Exception {
		final List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
		final StringBuilder sbd = new StringBuilder()  ;
		sbd.append(" SELECT   \n ") ;
		sbd.append("     t.check_id,  \n ") ;
		sbd.append("     t.book_list,  \n ") ;
		sbd.append("     t.grp_id,  \n ") ;
		sbd.append("     t.grp_name,  \n ") ;
		sbd.append("     t.book_stat,  \n ") ;
		sbd.append("     t.with_id,  \n ") ;
		sbd.append("     t1.book_room_id,  \n ") ;
		sbd.append("     t1.roomtype_id,  \n ") ;
		sbd.append("     t1.reach_date,  \n ") ;
		sbd.append("     t1.leave_date,  \n ") ;
		sbd.append("     t1.book_num,  \n ") ;
		sbd.append("     t1.save_num,  \n ") ;
		sbd.append("     t1.reach_num,  \n ") ;
		sbd.append("     t2.code_id,  \n ") ;
		sbd.append("     t2.code_namec,  \n ") ;
		sbd.append("     t2.code_namee   \n ") ;
		sbd.append(" FROM  \n ") ;
		sbd.append("     grp_doc t   \n ") ;
		sbd.append("     LEFT JOIN book_room t1   \n ") ;
		sbd.append("         ON t.check_id = t1.check_id   \n ") ;
		sbd.append("     LEFT JOIN hroom_type t2   \n ") ;
		sbd.append("         ON t1.roomtype_id = t2.code_id   \n ") ;
		sbd.append(" WHERE 1 = 1   \n ") ;
		sbd.append(" AND (t1.status = 0 or t1.status = 2) ");
		sbd.append(" AND t.book_list is not null ");
		sbd.append(" AND t.book_list != '' ");
		
		//#或者有效	备注：有效订单指状态为“B 未确认、已确认($：已付订金（自动）G 有担保（人工）O 人工确认（人工）)、R 部分抵达”的订单
		sbd.append(" AND t.book_stat in ('B','$','G','O','R')");						
		
		if(bookRoomSearchVo != null ){
			// *（symbol = 1时） 无效  
			if(bookRoomSearchVo.getSymbol()!=null && bookRoomSearchVo.getSymbol()==1)
				return null ;
			if(StringUtils.isNotBlank(bookRoomSearchVo.getBookStat())){
				sbd.append(" and t.book_stat "+("O".equals(bookRoomSearchVo.getBookStat()) ? " in('O','$') " : (" = '"+ bookRoomSearchVo.getBookStat()+"'")));
			}
			if(StringUtils.isNotBlank(bookRoomSearchVo.getBook_list())){
				sbd.append(" and t.book_list like '%"+bookRoomSearchVo.getBook_list()+"%'");
			}
			// 团名
			if(StringUtils.isNotBlank(bookRoomSearchVo.getGroupName())){
				sbd.append(" and t.grp_name like '%"+bookRoomSearchVo.getGroupName()+"%'");
			}
			//团代码
			if(StringUtils.isNotBlank(bookRoomSearchVo.getGroupCode())){
				sbd.append(" and t.grp_id like '%"+bookRoomSearchVo.getGroupCode()+"%'");
			}
			if(StringUtils.isNotBlank(bookRoomSearchVo.getCodeLetter())){
				//sbd.append(" and charindex ('"+bookRoomSearchVo.getCodeLetter()+"', lower(t.grp_id)) >= 0");
				sbd.append(" and lower(t.grp_id) like '%"+bookRoomSearchVo.getCodeLetter()+"%'");
			}
			//领队名
			if(StringUtils.isNotBlank(bookRoomSearchVo.getLeaderName())){
				sbd.append(" and t.lead_namec like '%"+bookRoomSearchVo.getLeaderName()+"%'");
			}
			if(StringUtils.isNotBlank(bookRoomSearchVo.getMobile())){
				sbd.append(" and t.mobile like '%"+bookRoomSearchVo.getMobile()+"%'");
			}
			if(StringUtils.isNotBlank(bookRoomSearchVo.getLeaveDate())){
				sbd.append(" and datediff(day,t.leave_date,'"+bookRoomSearchVo.getLeaveDate()+"') >=0 ");
			}
			if(StringUtils.isNotBlank(bookRoomSearchVo.getRoom_id())){
				sbd.append(" and t.check_id in ( select distinct check_id from room_num where room_id like '%"+bookRoomSearchVo.getRoom_id()+"%' )");
			}
		}
		sbd.append(" and t.check_id in ( select distinct check_id from book_room where datediff(day,reach_date,'"+DateFormatUtils.format(paramDao.GetHotelDate(), "yyyy-MM-dd")+"') =0 and status = 0 "
				+ (StringUtils.isNotBlank(bookRoomSearchVo.getLeaveDate()) ? (" and datediff(day,leave_date,'"+bookRoomSearchVo.getLeaveDate()+"')=0 )") : ")"));
		sbd.append(" ORDER BY t.check_id ASC , t1.book_room_id ASC  \n ");
		getSession().doWork(new Work() {
			@Override
			public void execute(Connection connection) throws SQLException {
				
				PreparedStatement pstmt= connection.prepareStatement(sbd.toString()) ;
				ResultSet rs = pstmt.executeQuery() ;
				while(rs.next()){
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("checkId", rs.getInt("check_id")) ;
					map.put("bookOrderNo", rs.getString("book_list")) ;
					map.put("withId", rs.getString("with_id")) ;
					map.put("bookStat", rs.getString("book_stat")) ;
					map.put("grpId", rs.getString("grp_id")) ;
					map.put("grpName", rs.getString("grp_name")) ;
					map.put("bookRoomId", rs.getString("book_room_id")) ;
					map.put("roomTypeId", rs.getString("roomtype_id")) ;
					String rd = rs.getString("reach_date") ;
					String ld = rs.getString("leave_date") ;
					map.put("reachDate", StringUtils.isNotBlank(rd) ? rd.substring(0, 10) : rd) ;
					map.put("leaveDate", StringUtils.isNotBlank(ld) ? ld.substring(0, 10) : ld) ;
					map.put("saveNum", rs.getInt("save_num")) ;
					map.put("reachNum", rs.getInt("reach_num")) ;
					map.put("bookNum", rs.getInt("book_num")) ;
					map.put("roomTypeCodeId", rs.getString("code_id")) ;
					map.put("roomTypeNamec", rs.getString("code_namec")) ;
					map.put("roomTypeNamee", rs.getString("code_namee")) ;
					list.add(map) ;
				}
				if(rs != null){
		            try {
		                rs.close();
		                rs=null;
		            } catch (Exception ex) {
		                rs=null;
		            }
		        } 
		        if(pstmt != null){
		            try {
		            	pstmt.close();
		            	pstmt=null;
		            } catch (Exception ex) {
		            	pstmt=null;
		            }
		        }
			}
		});
		return list;
	}

	@Override
	public List getGroupBills(Integer billId) {
		String sql = "select row_number()OVER(ORDER BY b.ID) as sortNum,b.ID,b.acco_id,b.balance,b.foreignm,b.money_kind,b.svc_charge,b.status,b.oper_time,b.ext_name,b.room_id,b.notes,b.pay_num,hex.code_namec,op.oper_name,hc.code_namec cname,hs.code_namec sname from bills b left join hexchange hex on b.money_kind = hex.code_id"
				+ " inner join operator op on op.oper_id = b.oper_id "
				+ " left join hconsume hc on hc.code_id = b.cons_id "
				+ " left join hsettle hs on hs.code_id = b.setl_id "
				+ " where b.status=0 and b.ext_name!='转出'"
				+ " and b.bill_id=" + billId;
		List list = this.createTransformSqlQuery(sql).list();
		return list;
	}

	@Override
	public List getNoguestBills(Integer billId,String showType,String okFlag,String startDate,String endDate,String isInvalid) {
		String baseSql = "";
		String flagSql = "";
		String dateSql = "";
		String isvalidSql = "";
		// 未结（N）、已结(Y)、全部
		if ("N".equals(okFlag)) {
			flagSql = " and b.ok_flag=0";
		} else if ("Y".equals(okFlag)) {
			flagSql = " and ( b.ok_flag=1 or b.ok_flag=2)";
		}
		//是否是无效单
		if("N".equals(isInvalid)){
			isvalidSql = " (b.status=2 or b.ext_name='转出')";
		}else {
			isvalidSql = " b.status=0 and b.ext_name!='转出'";
		}
		// 开始及结束日期筛选
		if (StringUtils.isNotEmpty(startDate) && StringUtils.isNotEmpty(endDate)) {
//			dateSql += " and b.oper_time>=cast('" + startDate
//					+ "' as datetime)";
			
			dateSql += " and (convert(varchar(100),b.oper_time,23) >='"
			+ startDate
			+ "' and convert(varchar(100),b.oper_time,23)<='"
			+ endDate + "')" ;
		}
//		if (StringUtils.isNotEmpty(endDate)) {
//			dateSql += " and b.oper_time<=cast('" + endDate + "' as datetime)";
//		}
		//明细（A）、汇总（B）、分类（C）
		if ("A".equals(showType)) {
			baseSql = "select row_number()OVER(ORDER BY b.ID) as sortNum,b.ID,b.acco_id,b.balance,b.foreignm,b.money_kind,b.svc_charge,b.status,b.oper_time,b.ext_name,b.room_id,b.notes,b.pay_num,hex.code_namec,op.oper_name,hc.code_namec cname,hs.code_namec sname from bills b left join hexchange hex on b.money_kind = hex.code_id"
					+ " inner join operator op on op.oper_id = b.oper_id "
					+ " left join hconsume hc on hc.code_id = b.cons_id "
					+ " left join hsettle hs on hs.code_id = b.setl_id "
					+ " where "+isvalidSql
					+ flagSql + dateSql
					+ " and b.bill_id=" + billId;
		} else if ("B".equals(showType)) {
			baseSql = "select t.*,row_number()OVER(ORDER BY t.cname desc) as sortNum from (select '*' as ID,'*' as acco_id,'*' as money_kind,sum(b.balance) balance,sum(b.svc_charge) svc_charge,hc.code_namec cname,'' as sname from bills b "
					+ " inner join hconsume hc on hc.code_id = b.cons_id where b.status=0 and b.bill_id="
					+ billId +  flagSql + dateSql
					+" group by b.cons_id,hc.code_namec"
					+" union " 
					+" select '*' as ID,'*' as acco_id,'*' as money_kind,sum(b.balance) balance,sum(b.svc_charge) svc_charge,'' as cname,hs.code_namec sname from bills b "
					+ " inner join hsettle hs on hs.code_id = b.setl_id where "+isvalidSql+" and b.bill_id="
					+ billId  + flagSql + dateSql
					+" group by b.setl_id,hs.code_namec)t";
		} else if ("C".equals(showType)) {
			baseSql = "select t.*,row_number()OVER(ORDER BY t.cname desc) as sortNum from (select '*' as ID,'*' as acco_id,'*' as money_kind,sum(b.balance) balance,sum(b.svc_charge) svc_charge,cs.code_namec cname,'' as sname  from bills b"
					+ " inner join hconsume hc on hc.code_id = b.cons_id "
					+ " inner join hcodes cs on cs.code_id = hc.kind"
					+ " where" + isvalidSql
					+ " and b.bill_id=" + billId
					+ flagSql + dateSql
					+" group by hc.kind,cs.code_namec"
					+" union"
					+" select '*' as ID,'*' as acco_id,'*' as money_kind,sum(b.balance) balance,sum(b.svc_charge) svc_charge,'' as cname,hk.code_namec sname from bills b"
					+ " inner join hsettle hs on hs.code_id = b.setl_id "
					+ " inner join hsetl_kind hk on hs.kind = hk.code_id"
					+ " where"+isvalidSql
					+ " and b.bill_id=" + billId
					+ flagSql + dateSql
					+" group by hs.kind,hk.code_namec)t";
		}
		
		List list = this.createTransformSqlQuery(baseSql).list();
		//List list = this.createTransformSqlQuery(sql).list();
		return list;
	}

	@Override
	public Map<String, Object> getGuestDocInf(Integer checkId) {
		// TODO Auto-generated method stub
		Guestdoc guest = this.get(checkId);
		Map<String, Object> guestInf = new HashMap<String, Object>();
		//客户姓名
		if(guest==null){
			log.info("checkId=" + checkId + "的对象已经不存在，请查询数据库 table guestdoc验证");
			return null;
		}
		guestInf.put("gstNamec", guest.getGstNamec());
		String source = guest.getGstOriId();
		if(source!=null){
			HgstOri hgstOri = orgDao.get("codeId", source);
			if(hgstOri!=null){
				guestInf.put("source", hgstOri.getCodeNamec());//宾客来源
			}
			
		}
		guestInf.put("roomPrice", guest.getRoomPrice());
		guestInf.put("reachDate", guest.getReachDate());
		guestInf.put("leaveDate", guest.getLeaveDate());
		//统计同间房人数
		String[] properties = {"roomId","chkStat"};
		Object[] values = {guest.getRoomId(),"I"};
		List<Guestdoc> list = getList(properties, values);
		if(list!=null){
			guestInf.put("count", list.size());
		}else{
			guestInf.put("count", 1);
		}
		Integer billAId = guest.getBillaId();
		GstBill gstBill = gstBillDao.get(billAId);
		//判断该人是否付款人 若非付款人 则仅计算A账 若为付款人 计算B账
		if(null!=gstBill){
			Double lent = gstBill.getLent();
			Double borrow = gstBill.getBorrow();
			guestInf.put("lent", lent);//贷
			guestInf.put("borrow", borrow);//借
			guestInf.put("balance", lent - borrow);//结余
			guestInf.put("limit", gstBill.getLimit());//限额
			guestInf.put("authBalance", gstBill.getAuthBalance());//预授权
			guestInf.put("notice", guest.getNotice());
		}else{
			guestInf.put("lent", 0.00);//贷
			guestInf.put("borrow", 0.00);//借
			guestInf.put("balance", 0.00);//结余
			guestInf.put("limit", 0.00);//限额
			guestInf.put("authBalance", 0.00);//预授权
			guestInf.put("notice", "");
		}
		
		return guestInf;
	}

	@Override
	public Map<String, Object> getArrearsByRoomId(String roomId) { 	
		String querySQL = "SELECT gdoc.gst_namec,gdoc.payman_flag"
				+ ",gdoc.check_id,gb.bill_id,gb.borrow,gb.lent,gb.auth_balance,gb.limit,gdoc.check_id "
				+ "FROM gst_bill gb "
				+ "LEFT OUTER JOIN guestdoc gdoc ON gb.bill_id=gdoc.billa_id "
				+ " WHERE (gb.borrow-gb.lent-gb.auth_balance-gb.limit)<0 "
				+ " AND room_id='"
				+ roomId + "' ORDER BY payman_flag DESC";
		List<Object[]> result = this.getSession().createSQLQuery(querySQL).list();
		Map<String, Object> infs = new HashMap<String, Object>();
		List<Map<String, String>> list = new ArrayList<Map<String,String>>();//住客欠费情况
		infs.put("ispayMan", false);
		for(Object[] objects:result){
			Map<String, String> map = new HashMap<String, String>();
			map.put("namec", objects[0].toString());
			map.put("borrow", objects[4].toString());
			map.put("limit", objects[7].toString());
			map.put("auth_balance", objects[6].toString());	
			list.add(map);
			//B账信息
			if("1".equals(objects[1].toString())){
				Integer checkId = Integer.parseInt(objects[8].toString());
				Guestdoc gdoc = this.get(checkId);
				Integer billbID = gdoc.getBillbId();
				if(billbID!=null){
					GstBill gbill = gstBillDao.get(gdoc.getBillaId());
					infs.put("ispayMan", true);
					infs.put("borrow", gbill.getBorrow());
					infs.put("lent", gbill.getLent());
					infs.put("auth_balance", gbill.getAuthBalance());
					infs.put("limit", gbill.getLimit());
					infs.put("balance", gbill.getBorrow() - gbill.getLent());
				}else{
					log.info("roomId=" + roomId +"B账 主付费人 无B账数据");
				}
				
			}
		}
		infs.put("size", list.size());
		infs.put("list", list);
		return infs;
	}

	@Override
	public List getGstOriByType(String codeIds) {
		String hql = "select * from hgst_ori where status=0 and ori_kind in("+codeIds+")";
		return this.createTransformSqlQuery(hql).list();
	}

	@Override
	public Boolean isPayRoom(String roomId) {
		String querySQL = "SELECT sum(payman_flag) FROM guestdoc WHERE room_id='"
				+ roomId + "' AND chk_stat='I'";
		List<Integer> list = this.getSession().createSQLQuery(querySQL).list();
		Integer sum = list.get(0)==null?0:list.get(0);//统计付费标记 付费标识为1 则是付费房间 为严格把控 和为1 则为付费 避免一个房间多个付费人
		if(1==sum){
			 return true;
		}
		return false;
	}

	@Override
	public List<?> findRoomPriceListBy(Integer checkId , Integer withId) {
		final StringBuffer sbd = new StringBuffer() ;
		final List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		sbd.append(" SELECT DISTINCT         \n ") ;
		//sbd.append("     t.check_id ,         \n ") ;
		sbd.append("     t.room_id,t.check_id,        \n ") ;
		sbd.append("     CONVERT(VARCHAR (100), t.reach_date, 23) AS reach_date,         \n ") ;
		sbd.append("     CONVERT(VARCHAR (100), t.leave_date, 23) AS leave_date,         \n ") ;
		sbd.append("     t2.code_namec,         \n ") ;
		sbd.append("     t2.price         \n ") ;
		//sbd.append("     t.room_price          \n ") ;
		sbd.append(" FROM         \n ") ;
		sbd.append("     guestdoc t          \n ") ;
		sbd.append("     INNER JOIN rooms t1          \n ") ;
		sbd.append("         ON t.room_id = t1.room_id          \n ") ;
		sbd.append("     INNER JOIN hroom_type t2          \n ") ;
		sbd.append("         ON t1.room_type = t2.code_id          \n ") ;
		sbd.append(" WHERE 1=1        \n ") ;
		sbd.append("               ") ;
		if(withId != null){
			sbd.append("   AND t.with_id = "+withId+"     \n ") ;
		}else{
			sbd.append("   AND t.check_id  = "+ checkId +"\n ") ;
		}
		sbd.append(" order by t.room_id asc       \n ") ;
		getSession().doWork(new Work() {
			@Override
			public void execute(Connection connection) throws SQLException {
				PreparedStatement pstmt= connection.prepareStatement(sbd.toString()) ;
				ResultSet rs = pstmt.executeQuery() ;
				while(rs.next()){
					Map<String, Object> map = new HashMap<String, Object>();
					//map.put("checkId", rs.getString("check_id")) ;
					map.put("roomId", rs.getString("room_id")) ;
					map.put("reachDate", rs.getString("reach_date"));
					map.put("leaveDate", rs.getString("leave_date"));
					map.put("week", DateToWeek(rs.getString("leave_date")));
					map.put("roomTypeName", rs.getString("code_namec")) ;
					map.put("standPrice", rs.getString("price")) ;
					map.put("check_id", rs.getString("check_id"));
					//String str =rs.getString("room_price") ;
					//map.put("doPrice", StringUtils.isNotBlank(str) ? str : rs.getString("price")) ;
					map.put("doPrice", rs.getString("price")) ;
					list.add(map) ;
				}
				if(rs != null){
		            try {
		                rs.close();
		                rs=null;
		            } catch (Exception ex) {
		                rs=null;
		            }
		        } 
		        if(pstmt != null){
		            try {
		            	pstmt.close();
		            	pstmt=null;
		            } catch (Exception ex) {
		            	pstmt=null;
		            }
		        }
			}
		});
		return list;
	}

	@Override
	public GuestDetailVo getGuestDetailByCheckId(Integer checkId) {
		String sql = "SELECT gst.check_id checkId, gst.room_id roomId,gst.grp_chkid grpChkid, gst.gst_namee gstNamee, gst.gst_namec gstNamec, gst.reach_date reachDate,gst.tele,gst.birthday,gst.gst_kind gstKind,gst.folk,gst.if_bdate ifBdate,gst.chk_ext chkExt,gst.gst_id gstId"+
				 ", gst.leave_date leaveDate, gst.comp_id compId, gst.chk_stat chkStat,gst.comp_type compType,gst.payman_flag paymanFlag,gst.prc_scheme_id prcSchemeId,gd.grp_id grpId,gd.grp_name grpName,gst.chk_operid chkOperid,gst.addr,gst.sex,gst.nt_id ntId,gst.notice,gst.native native_," +
				  " gst.billa_id billaId,gst.billb_id billbId,ht.code_namec codeNamec,td.namec,td.TA_type TaType,ht.price" +
				  ",gst.plate_number plateNumber,gst.crdkind_id crdkindId,gst.crd_id crdId,gst.email,hcs.code_namec sexname,gst.gst_ori_id gstOriId,gst.with_id withId"+
				  ",gst.city_ledger cityLedger,gst.house_pay housePay,ISNULL(gst.room_price,ht.price) roomPrice,gst.free_svc freeSvc,gst.hideprice,gst.if_fgst ifFgst,gst.pay_id payId,visakind_id visakindId,visa_id visaId,depart,crd_vld crdVld,in_port inPort,in_date inDate"+
				  ",op.oper_name chkOperName,op2.oper_name rechkOperName,op3.oper_name outOperName,op4.oper_name lastOperName,op5.oper_name lastCashierName,gst.cashier_time cashierTime,ISNULL(gbi.borrow,0.00) borrow,ISNULL(gbi.lent,0.00) lent,ISNULL(gbi.limit,0.00) alimit,ISNULL(gbi.auth_balance,0.00) authBalance,ISNULL(gbi2.borrow,0.00) bborrow,ISNULL(gbi2.lent,0.00) blent,ISNULL(gbi2.limit,0) blimit,ISNULL(gbi2.auth_balance,0.00) bauthBalance,codes.code_namec payCodeNamec"+
				  " FROM guestdoc gst LEFT JOIN grp_doc gd ON gst.grp_chkid = gd.check_id " +
				  " LEFT JOIN rooms rs ON rs.room_id = gst.room_id"+
				  " LEFT JOIN hroom_type ht ON rs.room_type = ht.code_id"+
				  " LEFT JOIN ta_doc td on gst.comp_id=td.id"+
				  " LEFT JOIN hcodes hc on td.saleman_id = hc.code_id"+
				  " LEFT JOIN hroom_plan hp on hp.id=gst.prc_scheme_id"+
				  " LEFT JOIN hcodes hcs on hcs.code_id = gst.sex "+
				  " LEFT JOIN operator op on op.oper_id = gst.chk_operid"+
				  " LEFT JOIN operator op2 on op2.oper_id = gst.rechk_operid"+
				  " LEFT JOIN operator op3 on op3.oper_id = gst.out_oper"+
				  " LEFT JOIN operator op4 on op4.oper_id = gst.last_oper"+
				  " LEFT JOIN operator op5 on op5.oper_id = gst.last_cashier"+
				  " LEFT JOIN gst_bill gbi on gst.billa_id = gbi.bill_id"+
				  " LEFT JOIN gst_bill gbi2 on gst.billb_id = gbi2.bill_id"+
				  " LEFT JOIN hsettle codes on codes.code_id = gst.pay_id"+
				  " WHERE gst.check_id="+checkId;
		Query query = this.getSession().createSQLQuery(sql).setResultTransformer(Transformers.aliasToBean(GuestDetailVo.class));
		GuestDetailVo detailVo = (GuestDetailVo)query.uniqueResult();
		return detailVo;
	}

	@Override
	public List getInvalidBIlls(Integer billType, String billId,
			String showType, String consId, String startDate, String endDate) {

		StringBuilder sb = new StringBuilder();
		String baseSql = "";
		String flagSql = "";
		String dateSql = "";
		// 未结（N）、已结(Y)、全部
		// 开始及结束日期筛选
		if (StringUtils.isNotEmpty(startDate)) {
			dateSql += " and b.oper_time>=cast('" + startDate
					+ "' as datetime)";
		}
		if (StringUtils.isNotEmpty(endDate)) {
			dateSql += " and b.oper_time<=cast('" + endDate + "' as datetime)";
		}
		//明细（A）、汇总（B）、分类（C）
		if ("A".equals(showType)) {
			baseSql = "select row_number()OVER(ORDER BY b.ID) as sortNum,b.ID,b.acco_id,b.balance,b.foreignm,b.money_kind moneyKind,b.svc_charge,b.status,b.oper_time,b.ext_name,b.room_id,b.notes,b.pay_num payNum,hex.code_namec,op.oper_name,hc.code_namec cname,hs.code_namec sname,b.setl_id setlId,b.oper_id operId,b.ok_flag okFlag from bills b left join hexchange hex on b.money_kind = hex.code_id"
					+ " inner join operator op on op.oper_id = b.oper_id "
					+ " left join hconsume hc on hc.code_id = b.cons_id "
					+ " left join hsettle hs on hs.code_id = b.setl_id "
					+ " where b.status=0 and b.ext_name!='转出' "
					+ flagSql + dateSql
					+ " and b.bill_id=" + billId;
		} else if ("B".equals(showType)) {
			baseSql = "select t.*,row_number()OVER(ORDER BY t.cname desc) as sortNum from (select '*' as ID,'*' as acco_id,'*' as money_kind,sum(b.balance) balance,sum(b.svc_charge) svc_charge,hc.code_namec cname,'' as sname from bills b "
					+ " inner join hconsume hc on hc.code_id = b.cons_id where b.status=0 and b.bill_id="
					+ billId + " and b.bill_type=" + billType
					+ flagSql + dateSql
					+" group by b.cons_id,hc.code_namec"
					+" union " 
					+" select '*' as ID,'*' as acco_id,'*' as money_kind,sum(b.balance) balance,sum(b.svc_charge) svc_charge,'' as cname,hs.code_namec sname from bills b "
					+ " inner join hsettle hs on hs.code_id = b.setl_id where b.status=0 and b.ext_name!='转出' and b.bill_id="
					+ billId  + flagSql + dateSql
					+" group by b.setl_id,hs.code_namec)t";
		} else if ("C".equals(showType)) {
			baseSql = "select t.*,row_number()OVER(ORDER BY t.cname desc) as sortNum from (select '*' as ID,'*' as acco_id,'*' as money_kind,sum(b.balance) balance,sum(b.svc_charge) svc_charge,cs.code_namec cname,'' as sname  from bills b"
					+ " inner join hconsume hc on hc.code_id = b.cons_id "
					+ " inner join hcodes cs on cs.code_id = hc.kind"
					+ " where b.status=0 and b.ext_name!='转出'"
					+ " and b.bill_id=" + billId
					+ flagSql + dateSql
					+" group by hc.kind,cs.code_namec"
					+" union"
					+" select '*' as ID,'*' as acco_id,'*' as money_kind,sum(b.balance) balance,sum(b.svc_charge) svc_charge,'' as cname,hk.code_namec sname from bills b"
					+ " inner join hsettle hs on hs.code_id = b.setl_id "
					+ " inner join hsetl_kind hk on hs.kind = hk.code_id"
					+ " where b.status=0 and b.ext_name!='转出'"
					+ " and b.bill_id=" + billId
					+ flagSql + dateSql
					+" group by hs.kind,hk.code_namec)t";
		}
		
		List list = this.createTransformSqlQuery(baseSql).list();
		this.getSession().clear();
		return list;
	
	}

	@Override
	public Double getRoomBillInfo(String roomId) {
		String querySQL = "SELECT sum(gb.lent-gb.borrow) as sum FROM gst_bill gb "
				+ " LEFT join guestdoc gd on gd.billa_id=gb.bill_id  "
				+ " WHERE gd.chk_stat='I' and gd.room_id='"
				+ roomId + "'";
		List<Double> list = this.getSession().createSQLQuery(querySQL).list();
		if(list==null || list.size()==0){
			return 0.0d;
		}else{
			Double result = 0.0d;
			result = list.get(0)==null?0.0d:list.get(0);
			return result;
		}
	}
	
	public double setMoney(Double money){
		Parameter p = paramDao.get(1);
		BigDecimal n = new BigDecimal(money);
        BigDecimal n2 = n.setScale(p.getPara4().intValue(), BigDecimal.ROUND_HALF_UP);
		return n2.doubleValue();
	}
	
	/**
	 * @描述 日期与星期之间的换算
	 * */
	private String DateToWeek(String date){
		String[] weekDays = {"周日", "周一", "周二", "周三", "周四", "周五", "周六"};
		try {
			Date theDate = DateUtils.dateFormat2.parse(date);
			Calendar cal = Calendar.getInstance();
			cal.setTime(theDate);
			int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
			if (w < 0)
	            w = 0;
	        return weekDays[w];
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
