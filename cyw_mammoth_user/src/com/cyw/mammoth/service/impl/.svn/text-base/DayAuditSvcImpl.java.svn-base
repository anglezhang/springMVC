package com.cyw.mammoth.service.impl;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.CallableStatementCallback;
import org.springframework.jdbc.core.CallableStatementCreator;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import com.cyw.common.util.MathUtil;
import com.cyw.mammoth.bean.BPaied;
import com.cyw.mammoth.bean.Bills;
import com.cyw.mammoth.bean.BookRoom;
import com.cyw.mammoth.bean.BookRoomCheckin;
import com.cyw.mammoth.bean.BookRoomDiary;
import com.cyw.mammoth.bean.GrpDoc;
import com.cyw.mammoth.bean.GstBill;
import com.cyw.mammoth.bean.GstCreditAuth;
import com.cyw.mammoth.bean.GstPriceList;
import com.cyw.mammoth.bean.Guestdoc;
import com.cyw.mammoth.bean.Noguest;
import com.cyw.mammoth.bean.RoomNum;
import com.cyw.mammoth.bean.RoomsDiary;
import com.cyw.mammoth.service.DayAuditSvc;
import com.google.gson.Gson;
import com.mchange.v2.c3p0.ComboPooledDataSource;
@Service
public class DayAuditSvcImpl implements
		DayAuditSvc {
	
	@Autowired
	private TransactionTemplate transactionTemplate;
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private ComboPooledDataSource dataSourceSlave;

	/**
	 * 开始某一步操作(记夜审跟踪)
	 * @author Administrator 
	 *
	 */
	class StartStep implements TransactionCallback<Boolean>{
		private String operId;
		private int currStep;
		private int currSubStep;
		private String hotelDate;
		public StartStep(String operId, int currStep, int currSubStep, String hotelDate){
			this.operId = operId;
			this.currStep = currStep;
			this.currSubStep = currSubStep;
			this.hotelDate = hotelDate;
		}
		@Override
		public Boolean doInTransaction(TransactionStatus arg0) {
			//开始夜审，添加夜审跟踪记录
			try {
				List<Map<String, Object>> list = jdbcTemplate.queryForList("select * from night_trace a where datediff(day, a.hotel_date, '"+hotelDate+"') = 0 ");
				if(list.size() > 0){
					jdbcTemplate.update("update night_trace set curr_step = "+currStep+", curr_substep = "+currSubStep+" where datediff(day, hotel_date, '"+hotelDate+"') = 0 ");
				}else{
					jdbcTemplate.update("insert into night_trace(hotel_date, start_time, end_time, oper_id, curr_step, curr_substep) values('"+hotelDate+"', getdate(), getdate(), ?, ?, ?)", new Object[]{operId, currStep, currSubStep}, new int[]{Types.CHAR, Types.INTEGER, Types.INTEGER});
				}
			} catch (Exception e) {
				e.printStackTrace();
				arg0.setRollbackOnly();
				return false;
			}
			return true;
		}
	}
	/**
	 * 完成某一步操作（修改夜审跟踪结束日期）
	 * @author Administrator
	 *
	 */
	class EndStep implements TransactionCallback<Boolean>{
		private String operId;
		private int currStep;
		private int currSubStep;
		private String hotelDate;
		private int sqlStep;
		private String curr_table;
		private String result;
		public EndStep(String operId, int currStep, int currSubStep, String hotelDate, int sqlStep, String curr_table, String result){
			this.operId = operId;
			this.currStep = currStep;
			this.currSubStep = currSubStep;
			this.hotelDate = hotelDate;
			this.sqlStep = sqlStep;
			this.curr_table = curr_table;
			this.result = result;
		}
		@Override
		public Boolean doInTransaction(TransactionStatus arg0) {
			try {
				//新增夜审记录
				jdbcTemplate.update("insert into night_rec (hotel_date, oper_id, oper_time, curr_step, curr_substep, curr_sqlstep, curr_table, result) values('"+hotelDate+"', '"+operId+"', getdate(), "+currStep+", "+currSubStep+", "+ sqlStep +", '"+curr_table+"', '"+result+"')");
				//某一步完成，更新夜审跟踪记录
				jdbcTemplate.update("update night_trace set end_time = getdate(), curr_sqlstep = "+sqlStep+", curr_step = "+currStep+", curr_substep = "+currSubStep+" where datediff(day, '"+hotelDate+"', hotel_date) = 0 ");
			} catch (Exception e) {
				e.printStackTrace();
				arg0.setRollbackOnly();
				return false;
			}
			return true;	
			
		}
	}
	

	

	/**
	 * 夜审第零大步：夜审前数据检查
	 * @param operId
	 * @param nextStep
	 * @return
	 */
	//夜审状态定义
	public static final int CURR_STEP_DATACCHECK = 0;//夜审第零大步：检查数据完整性
	public static final int CURR_SUBSTEP_DATACCHECK_CHECK_01 = 1;//夜审第零大步第一小步：是否有房费为-1的房间？（要确认房费）
	public static final int CURR_SUBSTEP_DATACCHECK_CHECK_02 = 2;//夜审第零大步第二小步：是否有今日应离店但还在住的客人（延房或退房的选择处理）
	public static final int CURR_SUBSTEP_DATACCHECK_CHECK_03 = 3;//夜审第零大步第三小步：是否有无主账
	public static final int CURR_SUBSTEP_DATACCHECK_CHECK_04 = 4;//夜审第零大步第四小步：账目是否一致
	public static final int CURR_SUBSTEP_DATACCHECK_CHECK_05 = 5;//夜审第零大步第五小步：房态和客态是否一致（含IP或不含IP）
	public static final int CURR_SUBSTEP_DATACCHECK_CHECK_06 = 6;//检查房间存量（房间存量表数字和预定、维修、冻结、网房、在住等应有的结果是否一致）
	public static final int CURR_SUBSTEP_DATACCHECK_CHECK_07 = 7;//检查在住房间，有且只有一个主人，在住团或联有且只有一个付费人
	public static final int CURR_SUBSTEP_DATACCHECK_CHECK_08 = 8;//检查在住房间，有且只有一个主人，在住团或联有且只有一个付费人
	private Map<String, Object> dataCheck(String operId, String hotelDate, int currSubStep, int handle) {
		Map<String, Object> result = new HashMap<String, Object>();
		StringBuilder msg = new StringBuilder("");
		String issuccess = "true";
		switch (currSubStep) {
		case CURR_SUBSTEP_DATACCHECK_CHECK_01:
			result = transactionTemplate.execute(new Check01(operId, CURR_STEP_DATACCHECK, CURR_SUBSTEP_DATACCHECK_CHECK_01, hotelDate));
			if(result.get("issuccess").toString().equals("false")){
				result.put("currStep", CURR_STEP_DATACCHECK);
				result.put("currSubStep", CURR_SUBSTEP_DATACCHECK_CHECK_01);
				issuccess = "false";
				msg.append(result.get("msg").toString());
			}
		case CURR_SUBSTEP_DATACCHECK_CHECK_02:
			result = transactionTemplate.execute(new Check02(operId, CURR_STEP_DATACCHECK, CURR_SUBSTEP_DATACCHECK_CHECK_02, hotelDate));
			if(result.get("issuccess").toString().equals("false")){
				result.put("currStep", CURR_STEP_DATACCHECK);
				result.put("currSubStep", CURR_SUBSTEP_DATACCHECK_CHECK_02);
				issuccess = "false";
				msg.append(result.get("msg").toString());
			}
		case CURR_SUBSTEP_DATACCHECK_CHECK_03:
			result = transactionTemplate.execute(new Check03(operId, CURR_STEP_DATACCHECK, CURR_SUBSTEP_DATACCHECK_CHECK_03, hotelDate));
			if(result.get("issuccess").toString().equals("false")){
				result.put("currStep", CURR_STEP_DATACCHECK);
				result.put("currSubStep", CURR_SUBSTEP_DATACCHECK_CHECK_03);
				issuccess = "false";
				msg.append(result.get("msg").toString());
			}
		case CURR_SUBSTEP_DATACCHECK_CHECK_04:
			result = transactionTemplate.execute(new Check04(operId, CURR_STEP_DATACCHECK, CURR_SUBSTEP_DATACCHECK_CHECK_04, hotelDate));
			if(result.get("issuccess").toString().equals("false")){
				result.put("currStep", CURR_STEP_DATACCHECK);
				result.put("currSubStep", CURR_SUBSTEP_DATACCHECK_CHECK_04);
				issuccess = "false";
				msg.append(result.get("msg").toString());
			}
		case CURR_SUBSTEP_DATACCHECK_CHECK_05:
			result = transactionTemplate.execute(new Check05(operId, CURR_STEP_DATACCHECK, CURR_SUBSTEP_DATACCHECK_CHECK_05, hotelDate));
			if(result.get("issuccess").toString().equals("false")){
				result.put("currStep", CURR_STEP_DATACCHECK);
				result.put("currSubStep", CURR_SUBSTEP_DATACCHECK_CHECK_05);
				issuccess = "false";
				msg.append(result.get("msg").toString());
			}
		case CURR_SUBSTEP_DATACCHECK_CHECK_06:
			result = transactionTemplate.execute(new Check06(operId, CURR_STEP_DATACCHECK, CURR_SUBSTEP_DATACCHECK_CHECK_06, hotelDate));
			if(result.get("issuccess").toString().equals("false")){
				result.put("currStep", CURR_STEP_DATACCHECK);
				result.put("currSubStep", CURR_SUBSTEP_DATACCHECK_CHECK_06);
				issuccess = "false";
				msg.append(result.get("msg").toString());
			}
		case CURR_SUBSTEP_DATACCHECK_CHECK_07:
			result = transactionTemplate.execute(new Check07(operId, CURR_STEP_DATACCHECK, CURR_SUBSTEP_DATACCHECK_CHECK_07, hotelDate));
			if(result.get("issuccess").toString().equals("false")){
				result.put("currStep", CURR_STEP_DATACCHECK);
				result.put("currSubStep", CURR_SUBSTEP_DATACCHECK_CHECK_07);
				issuccess = "false";
				msg.append(result.get("msg").toString());
			}
			if(issuccess.equals("false")){
				result.put("issuccess", issuccess);
				result.put("msg", msg.toString());
				return result;
			}
		case CURR_SUBSTEP_DATACCHECK_CHECK_08:
			result = transactionTemplate.execute(new Check08(operId, CURR_STEP_DATACCHECK, CURR_SUBSTEP_DATACCHECK_CHECK_08, hotelDate));
			if(result.get("issuccess").toString().equals("false")){
				if(handle == 1){
					result.put("currStep", CURR_STEP_DATACCHECK);
					result.put("currSubStep", CURR_SUBSTEP_DATACCHECK_CHECK_08);
					
					result.put("issuccess", "false");
					result.put("msg", result.get("msg").toString());
					return result;
				}
			}
		default:
			break;
		}
		result.put("currStep", CURR_STEP_DATACCHECK);
		result.put("currSubStep", CURR_SUBSTEP_DATACCHECK_CHECK_08);
		result.put("issuccess", issuccess);
		result.put("msg", msg.toString());
		return result;
	}
	
	
	
	/**
	 * 检查数据完整性：是否有房费为-1的房间？（要确认房费）
	 * 
	 * 检查guestdoc 字段room_price=-1；提示所有为-1的房号，并自动复制到粘贴板并提示用户已复制。
	 */
	class Check01 implements TransactionCallback<Map<String, Object>>{
		private String operId;
		private int currStep;
		private int currSubStep;
		private String hotelDate;
		
		//用构造方法给内部类传参数
		public Check01(String operId, int currStep, int currSubStep, String hotelDate){
			this.operId = operId;
			this.currStep = currStep;
			this.currSubStep = currSubStep;
			this.hotelDate = hotelDate;
		}

		@Override
		public Map<String, Object> doInTransaction(TransactionStatus arg0) {
			//查询房价等于-1的在住房号
			String sql = "select a.room_id from guestdoc a where a.chk_stat = 'I' and a.room_price < 0 ";
			List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);
			//步骤完成，更新夜审跟踪
			transactionTemplate.execute(new EndStep(operId, currStep, currSubStep, hotelDate, 1, "guestdoc", new Gson().toJson(list)));
			//如果存在房价小于0的房号，返回房号信息，否则返回成功
			Map<String, Object> result = new HashMap<String, Object>();
			if(list.size() > 0){
				StringBuilder builder = new StringBuilder("<p>房间号为：");
				for (int i = 0; i < list.size(); i++) {
					if(i == 0){
						builder.append(list.get(i).get("room_id"));
					}else{
						builder.append(","+list.get(i).get("room_id"));
					}
				}
				builder.append("的房间房价小于零。</P>");
				result.put("issuccess", "false");
				result.put("msg", builder.toString());
			}else{
				result.put("issuccess", "true");
			}
			return result;
		}
		
	}
	
	/**
	 * 检查数据完整性：是否有今日应离店但还在住的客人（延房或退房的选择处理）
	 * 
	 * 检查guestdoc在住客人离店日期 leave_date 是否是今天，提示今天离店的所有房号，并自动复制到粘贴板并提示用户已复制。
	 */
	class Check02 implements TransactionCallback<Map<String, Object>>{
		private String operId;
		private int currStep;
		private int currSubStep;
		private String hotelDate;
		
		//用构造方法给内部类传参数
		public Check02(String operId, int currStep, int currSubStep, String hotelDate){
			this.operId = operId;
			this.currStep = currStep;
			this.currSubStep = currSubStep;
			this.hotelDate = hotelDate;
		}

		@Override
		public Map<String, Object> doInTransaction(TransactionStatus arg0) {
			//查询离店日期与当前酒店日期相同的在住房号
			String sql = "select a.room_id from guestdoc a where a.chk_stat = 'I' and datediff(day, a.leave_date, '"+hotelDate+"') >= 0 and a.book_stat is null";
			List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);
			//步骤完成，更新夜审跟踪
			transactionTemplate.execute(new EndStep(operId, currStep, currSubStep, hotelDate, 1, "guestdoc", new Gson().toJson(list)));
			Map<String, Object> result = new HashMap<String, Object>();
			if(list.size() > 0){
				StringBuilder builder = new StringBuilder("<p>房间号为：");
				for (int i = 0; i < list.size(); i++) {
					if(i == 0){
						builder.append(list.get(i).get("room_id"));
					}else{
						builder.append(","+list.get(i).get("room_id"));
					}
				}
				builder.append("的房间住客应该今日离店。</p>");
				result.put("issuccess", "false");
				result.put("msg", builder.toString());
			}else{
				result.put("issuccess", "true");
			}
			return result;
		}
		
	}

	/**
	 * 检查数据完整性：是否有无主账
	 * 
	 * 用bills.bill_id 分别搜索guestdoc.billa_id 及guestdoc.billb_id ;noguest.bill_id;grpdoc.bill_id如不存在则为无主账账目。提示有无主账显示出所有的bills.bill_id. 并记录至数据库。询问用户是否继续。
	 */
	class Check03 implements TransactionCallback<Map<String, Object>>{
		private String operId;
		private int currStep;
		private int currSubStep;
		private String hotelDate;
		
		//用构造方法给内部类传参数
		public Check03(String operId, int currStep, int currSubStep, String hotelDate){
			this.operId = operId;
			this.currStep = currStep;
			this.currSubStep = currSubStep;
			this.hotelDate = hotelDate;
		}

		@Override
		public Map<String, Object> doInTransaction(TransactionStatus arg0) {
			String sql = "select a.id, a.bill_id from bills a where not exists(select * from guestdoc b where b.billa_id = a.bill_id or b.billb_id = a.bill_id) and not exists(select * from noguest c where c.bill_id = a.bill_id) and not exists(select * from grp_doc d where d.bill_id = a.bill_id) AND a.bill_id > 1000";
			List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);
			
			//步骤完成，更新夜审跟踪
			transactionTemplate.execute(new EndStep(operId, currStep, currSubStep, hotelDate, 1, "bills", new Gson().toJson(list)));
			Map<String, Object> result = new HashMap<String, Object>();
			String issuccess = "true";
			StringBuilder builder = new StringBuilder("");
			if(list.size() > 0){
				builder.append("<p>账号：");
				for (int i = 0; i < list.size(); i++) {
					if(i == 0){
						builder.append(list.get(i).get("bill_id"));
					}else{
						builder.append(","+list.get(i).get("bill_id"));
					}
				}
				builder.append("为无主账。</p>");
				issuccess = "false";
			}
			
			String sql1 = "select * from gst_credit_auth a where not exists(select * from guestdoc b where b.billa_id = a.bill_id or b.billb_id = a.bill_id) and a.bill_id > 1000";
			List<Map<String, Object>> list1 = jdbcTemplate.queryForList(sql1);
			//步骤完成，更新夜审跟踪
			transactionTemplate.execute(new EndStep(operId, currStep, currSubStep, hotelDate, 2, "bills", new Gson().toJson(list)));
			if(list1.size() > 0){
				builder.append("<p>授权号：");
				for (int i = 0; i < list1.size(); i++) {
					if(i == 0){
						builder.append(list1.get(i).get("bill_id"));
					}else{
						builder.append(","+list1.get(i).get("bill_id"));
					}
				}
				builder.append("为无主授权。</p>");
				issuccess = "false";
			}
			result.put("issuccess", issuccess);
			result.put("msg", builder.toString());
			return result;
		}
		
	}
	/**
	 * 检查数据完整性：账目是否一致
	 * 
	 * （1）账目明细和汇总（余额）
	 * （2）预授权明细和汇总（余额）
	 * （3）已经全部抵达的订单是否还有预订金
	 * 用bills和guest_credit_auth表去验证gst_bill。
	 */
	class Check04 implements TransactionCallback<Map<String, Object>>{
		private String operId;
		private int currStep;
		private int currSubStep;
		private String hotelDate;
		
		//用构造方法给内部类传参数
		public Check04(String operId, int currStep, int currSubStep, String hotelDate){
			this.operId = operId;
			this.currStep = currStep;
			this.currSubStep = currSubStep;
			this.hotelDate = hotelDate;
		}

		@Override
		public Map<String, Object> doInTransaction(TransactionStatus arg0) {
			//1.账目明细和汇总对比
			String sql = "select x.bill_id " +
					"from " +
					"(select a.bill_id, a.borrow, a.lent, " +
					"	SUM(CASE WHEN b.cons_id is not null and rtrim(b.cons_id) <> '*' then b.balance + b.svc_charge else 0 end) cons_id, " +
					"	SUM(CASE WHEN b.setl_id is not null and rtrim(b.setl_id) <> '*' then b.balance + b.svc_charge else 0 end) setl_id " +
					"	from gst_bill a " +
					"	left join bills b on a.bill_id = b.bill_id and rtrim(b.ext_name) = '转出' and b.status = 2 " +
					"	group by a.bill_id, a.borrow, a.lent) x " +
					"where abs(x.borrow - x.cons_id) > 0.001 or abs(x.lent - x.setl_id) > 0.001";
			List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);
			//步骤完成，更新夜审跟踪
			transactionTemplate.execute(new EndStep(operId, currStep, currSubStep, hotelDate, 1, "gst_bill, bills", new Gson().toJson(list)));
			Map<String, Object> result = new HashMap<String, Object>();
			String issuccess = "true";
			StringBuilder builder = new StringBuilder("");
			if(list.size() > 0){
				issuccess = "false";
				builder.append("<p>账目明细和汇总数不一致。</p>");
			}else{
				issuccess = "true";
			}
			
			//2.预授权明细和汇总
			String sql1 = "select x.bill_id from (select a.bill_id, a.auth_balance, SUM(isnull(b.balance,0)) balance from gst_bill a left join gst_credit_auth b on a.bill_id = b.bill_id group by a.bill_id, a.auth_balance) x where abs(x.auth_balance - x.balance) > 0.001";
			List<Map<String, Object>> list1 = jdbcTemplate.queryForList(sql1);
			//步骤完成，更新夜审跟踪
			transactionTemplate.execute(new EndStep(operId, currStep, currSubStep, hotelDate, 2, "gst_bill, gst_credit_auth", new Gson().toJson(list1)));
			if(list1.size() > 0){
				issuccess = "false";
				builder.append("<p>预授权明细和汇总数不一致。</p>");
			}
			
			//3.验证已经全部抵达的订单是否还有预订金
			//TODO 需要把未清理的预定查询出来
			String sql2 = "(select distinct a.check_id, b.book_list, '团队预定' book_type from book_room a join grp_doc b on a.check_id = b.check_id where a.status = 2 and (select c.lent from gst_bill c where c.bill_id = b.bill_id) > 0) union all (select distinct a.check_id, b.book_list, '散客预定' book_type from book_room a join guestdoc b on b.check_id = a.check_id where a.status = 2 and (select c.lent from gst_bill c where c.bill_id = b.billb_id) > 0)";
			List<Map<String, Object>> list2 = jdbcTemplate.queryForList(sql2);
			//步骤完成，更新夜审跟踪
			transactionTemplate.execute(new EndStep(operId, currStep, currSubStep, hotelDate, 3, "book_room, grp_doc", new Gson().toJson(list2)));
			if(list2.size() > 0){
				builder.append("<p>预定号");
				for (int i = 0; i < list2.size(); i++) {
					if(i == 0){
						builder.append("为" + list2.get(i).get("book_list") + "的" + list2.get(i).get("book_type").toString());
					}else{
						builder.append(",为" + list2.get(i).get("book_list") + "的" + list2.get(i).get("book_type").toString());
					}
				}
				builder.append("已经全部抵达，但还存在预订金。</p>");
				issuccess = "false";
			}
			result.put("issuccess", issuccess);
			result.put("msg", builder.toString());
			return result;
		}
		
	}
	/**
	 * 检查数据完整性：房态和客态是否一致（含IP或不含IP）
	 * 
	 * 根据含IP或不含IP检查rooms自身房态cur_stat 是否符合规定。
	 * 用rooms.cur_stat所有的空房房号,查询guestdoc是否存在。反过来也要查询验证。如果不一致用户操作选择
	 * （1）以房态为准更改客态
	 * （2）以客态为准更改房态
	 */
	class Check05 implements TransactionCallback<Map<String, Object>>{
		private String operId;
		private int currStep;
		private int currSubStep;
		private String hotelDate;
		
		//用构造方法给内部类传参数
		public Check05(String operId, int currStep, int currSubStep, String hotelDate){
			this.operId = operId;
			this.currStep = currStep;
			this.currSubStep = currSubStep;
			this.hotelDate = hotelDate;
		}

		@Override
		public Map<String, Object> doInTransaction(TransactionStatus arg0) {
			//1.根据含IP或不含IP检查rooms自身房态cur_stat 是否符合规定
			//boolean ipflag = jdbcTemplate.queryForObject("select para2 from parameter where id = 1000", Boolean.class);
			boolean ipflag = jdbcTemplate.execute(new CallableStatementCreator() {
				
				@Override
				public CallableStatement createCallableStatement(Connection conn)
						throws SQLException {
					CallableStatement cs = conn.prepareCall("{call IPFlag(?)}");
					cs.registerOutParameter(1, Types.BIT);
					return cs;
				}
			}, new CallableStatementCallback<Boolean>() {
				
				public Boolean doInCallableStatement(CallableStatement cs) throws SQLException, DataAccessException{
					cs.execute();
					return cs.getBoolean(1);
				}
			});
			List<Map<String, Object>> list;
			String sql;
			if(ipflag){
				sql = "select a.room_id from rooms a where a.curr_stat not in ('VDP','VCP','VCI','ODP','OCP','OCI','OOO','OOI','NVC','NVR','NVP')";
			}else{
				sql = "select a.room_id from rooms a where a.curr_stat not in ('VD','VC','OD','OC','OOO','OOI','NVC','NVR','NVP')";
			}
			list = jdbcTemplate.queryForList(sql);
			//步骤完成，更新夜审跟踪
			transactionTemplate.execute(new EndStep(operId, currStep, currSubStep, hotelDate, 1, "rooms", new Gson().toJson(list)));
			Map<String, Object> result = new HashMap<String, Object>();
			String issuccess = "true";
			StringBuilder builder = new StringBuilder("");
			if(list.size() > 0){
				issuccess = "false";
				builder.append("<p>房间号：");
				for (int i = 0; i < list.size(); i++) {
					if(i == 0){
						builder.append(list.get(i).get("room_id"));
					}else{
						builder.append(","+list.get(i).get("room_id"));
					}
				}
				builder.append("的房态不正确。</p>");
			}
			
			//2.用rooms.cur_stat所有的空房房号,查询guestdoc是否存在。反过来也要查询验证
			String sql1 = "select a.room_id " +
					"from rooms a " +
					"where (a.curr_stat like 'V%' and exists(select b.* from guestdoc b where b.chk_stat = 'I' and b.room_id = a.room_id)) " +
					"or (a.curr_stat like 'O%' and rtrim(a.curr_stat) <> 'OOO' and rtrim(a.curr_stat) <> 'OOI' and not exists(select c.* from guestdoc c where c.chk_stat = 'I' and c.room_id = a.room_id))";
			List<Map<String, Object>> list1 = jdbcTemplate.queryForList(sql1);
			//步骤完成，更新夜审跟踪
			transactionTemplate.execute(new EndStep(operId, currStep, currSubStep, hotelDate, 2, "rooms", new Gson().toJson(list1)));
			if(list1.size() > 0){
				issuccess = "false";
				builder.append("<p>房间号：");
				for (int i = 0; i < list1.size(); i++) {
					if(i == 0){
						builder.append(list1.get(i).get("room_id"));
					}else{
						builder.append(","+list1.get(i).get("room_id"));
					}
				}
				builder.append("的房态不正确。</p>");
			}

			result.put("issuccess", issuccess);
			result.put("msg", builder.toString());
			return result;
		}
		
	}
	/**
	 * 检查数据完整性：检查房间存量（房间存量表数字和预定、维修、冻结、网房、在住等应有的结果是否一致）
	 */
	class Check06 implements TransactionCallback<Map<String, Object>>{
		private String operId;
		private int currStep;
		private int currSubStep;
		private String hotelDate;
		
		//用构造方法给内部类传参数
		public Check06(String operId, int currStep, int currSubStep, String hotelDate){
			this.operId = operId;
			this.currStep = currStep;
			this.currSubStep = currSubStep;
			this.hotelDate = hotelDate;
		}

		@Override
		public Map<String, Object> doInTransaction(TransactionStatus arg0) {
			String sql = "select x.save_num, x.live_num, y.repair_num, y.frozen_num, y.web_num, z.save_num1, z.live_num1, z.repair_num1, z.frozen_num1, z.web_num1 " +
					"from " +
					"	(select (select ISNULL(SUM(a.save_num), 0) - ISNULL(SUM(CASE WHEN b.room_id is null then 0 else 1 end), 0) " +
					"	from book_room a left join book_room_checkin b on b.room_link_id = a.book_room_id and b.flag = 'L' and b.status = 0 " +
					"	where a.status = 0 and DATEDIFF(DAY, a.reach_date, '"+hotelDate+"') >= 0 and DATEDIFF(DAY, '"+hotelDate+"', a.leave_date) >= 0) save_num, " +
					"	(select COUNT(distinct a.room_id) from guestdoc a where a.chk_stat = 'I') live_num) x " +
					"full join " +
					"	(select SUM(CASE WHEN rtrim(a.curr_stat) = 'OOO' then 1 else 0 end) repair_num, " +
					"	SUM(CASE WHEN rtrim(a.curr_stat) = 'OOI' then 1 else 0 end) frozen_num, " +
					"	SUM(CASE WHEN rtrim(a.curr_stat) = 'NVC' then 1 else 0 end) web_num " +
					"	from rooms a where a.status = 0) y " +
					"on 1 = 1 " +
					"full join " +
					"	(select SUM(CASE WHEN a.flag = 0 then 1 else 0 end) save_num1, " +
					"	SUM(CASE WHEN a.flag = 1 then 1 else 0 end) live_num1, " +
					"	SUM(CASE WHEN a.flag = 2 then 1 else 0 end) repair_num1, " +
					"	SUM(CASE WHEN a.flag = 3 then 1 else 0 end) frozen_num1, " +
					"	SUM(CASE WHEN a.flag = 4 then 1 else 0 end) web_num1 " +
					"	from room_num a where a.status = 0 and DATEDIFF(DAY, a.reach_date, '"+hotelDate+"') >= 0 and DATEDIFF(DAY, '"+hotelDate+"', a.leave_date) >= 0) z " +
					"on 1 = 1";
			List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);
			//步骤完成，更新夜审跟踪
			transactionTemplate.execute(new EndStep(operId, currStep, currSubStep, hotelDate, 1, "guestdoc, rooms, room_num", new Gson().toJson(list)));
			Map<String, Object> result = new HashMap<String, Object>();
			String issuccess = "true";
			StringBuilder builder = new StringBuilder("");
			if(list.size() > 0){
				Map<String, Object> map = list.get(0);
				int save_num = map.get("save_num") == null ? 0 : Integer.parseInt(map.get("save_num").toString());
				int save_num1 = map.get("save_num1") == null ? 0 : Integer.parseInt(map.get("save_num1").toString());
				if(save_num != save_num1){
					issuccess = "false";
					builder.append("<p>预定单预定房间数量与房间存量信息不一致。</p>");
				}

				int live_num = map.get("live_num") == null ? 0 : Integer.parseInt(map.get("live_num").toString());
				int live_num1 = map.get("live_num1") == null ? 0 : Integer.parseInt(map.get("live_num1").toString());
				if(live_num != live_num1){
					issuccess = "false";
					builder.append("<p>住客在住房间数量与房间存量信息不一致。</p>");
				}

				int repair_num = map.get("repair_num") == null ? 0 : Integer.parseInt(map.get("repair_num").toString());
				int repair_num1 = map.get("repair_num1") == null ? 0 : Integer.parseInt(map.get("repair_num1").toString());
				if(repair_num != repair_num1){
					issuccess = "false";
					builder.append("<p>维修房间数量与房间存量信息不一致。</p>");
				}

				int frozen_num = map.get("frozen_num") == null ? 0 : Integer.parseInt(map.get("frozen_num").toString());
				int frozen_num1 = map.get("frozen_num1") == null ? 0 : Integer.parseInt(map.get("frozen_num1").toString());
				if(frozen_num != frozen_num1){
					issuccess = "false";
					builder.append("<p>冻结房间数量与房间存量信息不一致。</p>");
				}

				int web_num = map.get("web_num") == null ? 0 : Integer.parseInt(map.get("web_num").toString());
				int web_num1 = map.get("web_num1") == null ? 0 : Integer.parseInt(map.get("web_num1").toString());
				if(web_num != web_num1){
					issuccess = "false";
					builder.append("<p>网房房间数量与房间存量信息不一致。</p>");
				}
			}

			result.put("issuccess", issuccess);
			result.put("msg", builder.toString());
			return result;
		}
		
	}
	
	/**
	 * 检查数据完整性：检查在住房间，有且只有一个主人，在住团或联有且只有一个付费人
	 * @author Administrator
	 *
	 */
	class Check07 implements TransactionCallback<Map<String, Object>>{
		private String operId;
		private int currStep;
		private int currSubStep;
		private String hotelDate;
		
		//用构造方法给内部类传参数
		public Check07(String operId, int currStep, int currSubStep, String hotelDate){
			this.operId = operId;
			this.currStep = currStep;
			this.currSubStep = currSubStep;
			this.hotelDate = hotelDate;
		}

		@Override
		public Map<String, Object> doInTransaction(TransactionStatus arg0) {
			Map<String, Object> result = new HashMap<String, Object>();
			//查询在住房间主人数量不为一的房间号
			String sql = "select x.room_id from (select a.room_id,  SUM(CASE WHEN rtrim(a.chk_ext) = '1' then 1 else 0 end) chk_ext_num from guestdoc a where a.chk_stat = 'I' group by a.room_id) x where  x.chk_ext_num <> 1";
			List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);
			String issuccess = "true";
			StringBuilder builder = new StringBuilder("");
			//步骤完成，更新夜审跟踪
			transactionTemplate.execute(new EndStep(operId, currStep, currSubStep, hotelDate, 1, "guestdoc", new Gson().toJson(list)));
			if(list.size() > 0){
				issuccess = "false";
				builder.append("<p>房间号为：");
				for (int i = 0; i < list.size(); i++) {
					if(i == 0){
						builder.append(list.get(i).toString());
					}else{
						builder.append(","+list.get(i).toString());
					}
				}
				builder.append("的房间主人设置有误。</p>");
			}

			//查询在住住客中同来人中付费人不为一的同来信息
			sql = "select x.with_id from (select a.with_id, SUM(CASE WHEN a.payman_flag = 1 then 1 else 0 end) payman_flag_num from guestdoc a where a.chk_stat = 'I' group by a.with_id) x where x.payman_flag_num <> 1";
			list = jdbcTemplate.queryForList(sql);
			//步骤完成，更新夜审跟踪
			transactionTemplate.execute(new EndStep(operId, currStep, currSubStep, hotelDate, 2, "guestdoc", new Gson().toJson(list)));
			if(list.size() > 0){
				issuccess = "false";
				builder.append("<p>在住客中部分付费人设置不正确。</p>");
			}

			result.put("issuccess", issuccess);
			result.put("msg", builder.toString());
			return result;
		}
	}
	
	/**
	 * 检查数据完整性：检查guestdoc.room_price是否和房价列表相同
	 * @author Administrator
	 *
	 */
	class Check08 implements TransactionCallback<Map<String, Object>>{
		private String operId;
		private int currStep;
		private int currSubStep;
		private String hotelDate;
		
		//用构造方法给内部类传参数
		public Check08(String operId, int currStep, int currSubStep, String hotelDate){
			this.operId = operId;
			this.currStep = currStep;
			this.currSubStep = currSubStep;
			this.hotelDate = hotelDate;
		}

		@Override
		public Map<String, Object> doInTransaction(TransactionStatus arg0) {
			Map<String, Object> result = new HashMap<String, Object>();
			//查询在住房间主人数量不为一的房间号
			StringBuilder builder = new StringBuilder("");
			builder.append("SELECT DISTINCT x.room_id ");
			builder.append("  FROM (SELECT a.room_id, a.prc_scheme_id,  a.room_price, b.room_type ");
			builder.append("          FROM guestdoc a LEFT JOIN rooms b ON b.room_id = a.room_id ");
			builder.append("         WHERE a.chk_stat = 'I' AND a.is_room_plan = 1) x ");
			builder.append("  LEFT JOIN hroom_plan y ON y.code_id = x.prc_scheme_id ");
			builder.append(" WHERE y.ID IS NOT NULL ");
			builder.append("       AND DATEDIFF (day, y.start_date, '"+hotelDate+"') >= 0 ");
			builder.append("       AND DATEDIFF (day, y.end_date, '"+hotelDate+"') <= 0 ");
			builder.append("       AND x.room_price NOT IN (SELECT price ");
			builder.append("                                  FROM hroom_plan_list");
			builder.append("                                 WHERE rmplan_id = y.code_id AND rmtype_id = x.room_type ");
			builder.append("                                       AND holiday_id IN (SELECT id FROM holidays WHERE holiday_date = '"+hotelDate+"'))");
			String sql = builder.toString();
			List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);
			//步骤完成，更新夜审跟踪
			transactionTemplate.execute(new EndStep(operId, currStep, currSubStep, hotelDate, 1, "guestdoc", new Gson().toJson(list)));
			if(list.size() > 0){
				
				result.put("issuccess", "false");
				result.put("msg", "房间号为：的房间主人设置有误。");
				result.put("data", new Gson().toJson(list));
				return result;
			}
			result.put("issuccess", "true");
			return result;
		}
	}
	/**
	 * 夜审第一大步：夜审开始，打开夜审状态
	 * @return
	 */
	//夜审状态定义
	public static final int CURR_STEP_OPEN_DAYAUDIT = 1;//夜审第一大步：打开夜审状态
	private Map<String, Object> openDayAuditStatus(final String operId, final String hotelDate, final int currSubStep){
		return transactionTemplate.execute(new TransactionCallback<Map<String, Object>>() {

			@Override
			public Map<String, Object> doInTransaction(TransactionStatus arg0) {
				Map<String, Object> result = new HashMap<String, Object>();
				try {
					String sql = "update parameter set para2 = 1 where id = 1";
					jdbcTemplate.execute(sql);
					transactionTemplate.execute(new StartStep(operId, 0, 0, hotelDate));
					result.put("issuccess", "true");
					result.put("msg", "夜审启动成功，打开夜审状态成功。");
				} catch (DataAccessException e) {
					arg0.setRollbackOnly();
					e.printStackTrace();
					result.put("issuccess", "false");
				}
				result.put("currStep", CURR_STEP_OPEN_DAYAUDIT);
				result.put("currSubStep", 1);
				return result;
			}
		});
	}
	
	/**
	 * 夜审第二大步：过房租
	 * @return
	 */
	//夜审状态定义
	public static final int CURR_STEP_CHANGERENT = 2;//夜审第二大步：过房租
	public static final int CURR_SUBSTEP_CHANGERENT_01 = 1;//夜审第二大步第一小步
	public static final int CURR_SUBSTEP_CHANGERENT_02 = 2;//夜审第二大步第二小步
	public static final String billOper ="audit";//定义夜审账务用户
	private Map<String, Object> changeRent(String hotelDate, int currSubStep, int sqlStep){
		Map<String, Object> result = new HashMap<String, Object>();
		switch (currSubStep) {
		case CURR_SUBSTEP_CHANGERENT_01:
			String code_id = jdbcTemplate.queryForObject("select para6 from parameter where id = 12", String.class).trim();
			String sql = "select a.check_id, a.room_id, a.room_price, a.billa_id, a.billb_id, a.free_svc, b.cons " +
					"from guestdoc a " +
					"left join b_paied b on a.check_id = b.check_id " +
					//TODO charindex? 处理方法，两头加逗号，用charindex匹配
					"	and CHARINDEX(',"+code_id+",', ','+b.cons+',') > 0 " +
					"	and DATEDIFF(DAY, b.begin_date, '"+hotelDate+"') >= 0 " +
					"	and DATEDIFF(DAY, b.end_date, '"+hotelDate+"') <= 0 " +
					"where a.chk_stat = 'I' and a.room_price > 0.001 order by a.check_id";
			List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);
			if(list.size() == 0){
				transactionTemplate.execute(new EndStep(billOper, CURR_STEP_CHANGERENT, CURR_SUBSTEP_CHANGERENT_01, hotelDate, sqlStep, "guestdoc, rooms, room_num", "{}"));
			}
			int i = sqlStep - 1;
			for (; i < list.size(); i++) {
				result = transactionTemplate.execute(new ChangeRent(billOper, hotelDate, CURR_STEP_CHANGERENT, currSubStep, i+1, list.get(i), code_id));
				if(!result.get("issuccess").toString().equals("true")){
					result.put("currStep", CURR_STEP_CHANGERENT);
					result.put("currSubStep", CURR_SUBSTEP_CHANGERENT_01);
					result.put("sqlStep", i+1);
					return result;
				}
			}

		case CURR_SUBSTEP_CHANGERENT_02://修改下一天的房价
			//TODO 根据房价方案修改下一日的房价
			break;
			
		default:
			break;
		}
		
		result.put("issuccess", "true");
		result.put("currStep", CURR_STEP_CHANGERENT);
		result.put("currSubStep", CURR_SUBSTEP_CHANGERENT_01);
		return result;
	}
	//过房租事务
	class ChangeRent implements TransactionCallback<Map<String, Object>>{
		private String operId;
		private String hotelDate;
		private int currStep;
		private int currSubStep;
		private int sqlStep;
		private Map<String, Object> map;
		private String code_id;
		public ChangeRent(String operId, String hotelDate, int currStep, int currSubStep, int sqlStep, Map<String, Object> map, String code_id){
			this.operId = operId;
			this.hotelDate = hotelDate;
			this.currStep = currStep;
			this.currSubStep = currSubStep;
			this.sqlStep = sqlStep;
			this.map = map;
			this.code_id = code_id;
		}

		@Override
		public Map<String, Object> doInTransaction(TransactionStatus arg0) {
			Map<String, Object> result = new HashMap<String, Object>();
			try {
				//取本位币代码
				String money_kind = jdbcTemplate.queryForObject("select para5 from parameter where id = 2", String.class);
				String cons = map.get("cons") == null ? "" : map.get("cons").toString();
				Double room_price = Double.parseDouble(map.get("room_price") == null ? "0" :map.get("room_price").toString());
				BigDecimal room_price_bd = new BigDecimal(room_price);
				room_price_bd.setScale(2, BigDecimal.ROUND_HALF_UP);
				String room_id = map.get("room_id") == null ? "" :map.get("room_id").toString();
				//是否免服务费
				String free_svc = map.get("free_svc") == null ? "0" : map.get("free_svc").toString();
				
				//TODO 尾数截断方法用三种，MathUtil的全局参数定义
				//计算服务费
				String svc_charge = "0";
				//根据是否免服务费计算服务费
				if(free_svc.equals("true")){
					svc_charge = "0";
				}else{
					//服务费率
					String svc_rate = jdbcTemplate.queryForObject("select svc_rate from hconsume where code_id = " + code_id, String.class);
					Double consume = room_price * Double.parseDouble((svc_rate == null || svc_rate.equals("")) ? "0" : svc_rate) / 100;
					//记账精度
					int bill_round = jdbcTemplate.queryForInt("select para4 from parameter where id = 1");
					//舍入方式
					int round_type = jdbcTemplate.queryForInt("select para1 from parameter where id = 14");
					svc_charge = Double.toString(MathUtil.doubleRound(consume, round_type, bill_round));
				}
				String bill_id = "";
				String bill_type = "";
				if(cons.equals("")){
					bill_id = map.get("billa_id") == null ? "0" : map.get("billa_id").toString(); 
					bill_type = "1";
				}else{
					bill_id = map.get("billb_id") == null ? "0" : map.get("billb_id").toString();
					bill_type = "2";
				}
				//保存账目信息
				jdbcTemplate.update("INSERT INTO bills (bill_type,bill_id,acco_id,cons_id,setl_id,money_kind,balance,foreignm,svc_charge,ext_name,room_id,notes,status,oper_time,oper_id,in_bill,out_bill,pay_num,Ar_Flag,hotel_time,AR_num,pay_date,vilhotel_id,chain_id) " +
						"VALUES ("+bill_type+", "+bill_id+", 0, '"+code_id+"', '*', '"+money_kind+"', "+Double.toString(room_price_bd.doubleValue())+",0,"+svc_charge+",'空','"+room_id+"','夜审过租',0," + "getdate(),'"+operId+"',null,null,null,0,'"+hotelDate+"',null,null,null,null)");
				//修改住客账页资料
				String check_id = map.get("check_id") == null ? "" : map.get("check_id").toString();
				jdbcTemplate.update("update gst_bill set borrow = borrow + "+Double.toString(room_price_bd.doubleValue())+" where bill_id = " + ((map.get("cons") == null || map.get("cons").equals("")) ? map.get("billb_id") : map.get("billa_id")));
				//TODO gst_price_list新增
				jdbcTemplate.update("INSERT INTO gst_price_list(check_id,hotel_date,price,memo,oper_id,oper_time,modi_oper,modi_time,vilhotel_id,chain_id) " +
						"VALUES (" + check_id + ",'"+hotelDate+"',"+room_price+",N'夜审过房租','audit',getdate(),NULL,NULL,NULL,NULL)");
				transactionTemplate.execute(new EndStep(operId, currStep, currSubStep, hotelDate, sqlStep, "guestdoc, rooms, room_num", new Gson().toJson(map)));
			} catch (DataAccessException e) {
				arg0.setRollbackOnly();
				e.printStackTrace();
				result.put("issuccess", "false");
				result.put("msg", e.getMessage());
				return result;
			}
			result.put("issuccess", "true");
			return result;
		}
		
	}

	/**
	 * 夜审第三大步：修改房态
	 * @param operId
	 * @return
	 */
	//夜审状态定义
	public static final int CURR_STEP_CHANGEROOMSTAT = 3;//夜审第三大步：修改房态
	public static final int CURR_SUBSTEP_CHANGEROOMSTAT_01 = 1;//夜审第三大步第一小步
	public static final int CURR_SUBSTEP_CHANGEROOMSTAT_02 = 2;//夜审第三大步第二小步
	public static final int CURR_SUBSTEP_CHANGEROOMSTAT_03 = 3;//夜审第三大步第三小步
	public static final int CURR_SUBSTEP_CHANGEROOMSTAT_04 = 4;//夜审第三大步第四小步
	private Map<String, Object> changeRoomStat(String operId, String hotelDate, int currSubStep, int sqlStep){
		Map<String, Object> result = new HashMap<String, Object>();
		String sql = "";
		List<Map<String, Object>> list;
		boolean ipflag = jdbcTemplate.execute(new CallableStatementCreator() {
			
			@Override
			public CallableStatement createCallableStatement(Connection conn)
					throws SQLException {
				CallableStatement cs = conn.prepareCall("{call IPFlag(?)}");
				cs.registerOutParameter(1, java.sql.Types.BOOLEAN);
				return cs;
			}
		}, new CallableStatementCallback<Boolean>() {
			
			public Boolean doInCallableStatement(CallableStatement cs) throws SQLException, DataAccessException{
				cs.execute();
				return cs.getBoolean(1);
			}
		});
		switch (currSubStep) {
		case CURR_SUBSTEP_CHANGEROOMSTAT_01:
			//第一小步：修改所有在住房房态为脏房。
			sql = "select distinct a.room_id from guestdoc a where a.chk_stat = 'I'";
			list = jdbcTemplate.queryForList(sql);
			if(list.size() == 0){
				transactionTemplate.execute(new EndStep(operId, CURR_STEP_CHANGEROOMSTAT, CURR_SUBSTEP_CHANGEROOMSTAT_01, hotelDate, 0, "guestdoc", "{}"));
			}
			int i = sqlStep - 1;
			for (; i < list.size(); i++) {
				result = transactionTemplate.execute(new ChangeRoomStat(operId, hotelDate, CURR_STEP_CHANGEROOMSTAT, CURR_SUBSTEP_CHANGEROOMSTAT_01, i + 1, ipflag ? "ODP" : "OD", list.get(i)));
				if(!result.get("issuccess").toString().equals("true")){
					result.put("currStep", CURR_STEP_CHANGEROOMSTAT);
					result.put("currSubStep", CURR_SUBSTEP_CHANGEROOMSTAT_01);
					result.put("sqlStep", i+1);
					return result;
				}
			}

		case CURR_SUBSTEP_CHANGEROOMSTAT_02:
			//第二小步：空净房根据parameter 参数设置判断多久未住修改为脏房。
			sql = "select a.room_id from rooms a where (rtrim(a.curr_stat) = 'VCI' or rtrim(a.curr_stat) = 'VC') and DATEDIFF(DAY, DATEADD(DAY, (select para1 from parameter where id = 12), a.modi_time), '"+hotelDate+"') >= 0";
			list = jdbcTemplate.queryForList(sql);
			if(list.size() == 0){
				transactionTemplate.execute(new EndStep(operId, CURR_STEP_CHANGEROOMSTAT, CURR_SUBSTEP_CHANGEROOMSTAT_02, hotelDate, 0, "rooms", "{}"));
			}
			int j = sqlStep - 1;
			for (; j < list.size(); j++) {
				result = transactionTemplate.execute(new ChangeRoomStat(operId, hotelDate, CURR_STEP_CHANGEROOMSTAT, CURR_SUBSTEP_CHANGEROOMSTAT_02, j + 1, ipflag ? "VDP" : "VD", list.get(j)));
				if(!result.get("issuccess").toString().equals("true")){
					result.put("currStep", CURR_STEP_CHANGEROOMSTAT);
					result.put("currSubStep", CURR_SUBSTEP_CHANGEROOMSTAT_02);
					result.put("sqlStep", j+1);
					return result;
				}
			}

		case CURR_SUBSTEP_CHANGEROOMSTAT_03:
			
			//第四小步：网房、冻结、维修到期的改空脏。
			sql = "select a.room_id from room_num a where a.status = 0 and a.flag in (2,3,4,5,6) and DATEDIFF(DAY, DATEADD(DAY, 1, '"+hotelDate+"'), a.leave_date) = 0";
			list = jdbcTemplate.queryForList(sql);

			if(list.size() == 0){
				transactionTemplate.execute(new EndStep(operId, CURR_STEP_CHANGEROOMSTAT, CURR_SUBSTEP_CHANGEROOMSTAT_03, hotelDate, 0, "room_num", "{}"));
			}
			int l = sqlStep - 1;
			for (; l < list.size(); l++) {
				result = transactionTemplate.execute(new ChangeRoomStat(operId, hotelDate, CURR_STEP_CHANGEROOMSTAT, CURR_SUBSTEP_CHANGEROOMSTAT_03, l + 1, ipflag ? "VDP" : "VD", list.get(l)));
				if(!result.get("issuccess").toString().equals("true")){
					result.put("currStep", CURR_STEP_CHANGEROOMSTAT);
					result.put("currSubStep", CURR_SUBSTEP_CHANGEROOMSTAT_03);
					result.put("sqlStep", l+1);
					return result;
				}
			}

		case CURR_SUBSTEP_CHANGEROOMSTAT_04:
			//第三小步：网房、冻结、维修房根据时间修改房态。
			//TODO 根据flag修改
			sql = "select a.room_id, a.flag from room_num a where a.status = 0 and a.flag in (2,3,4,5,6) and DATEDIFF(DAY, DATEADD(DAY, 1, '"+hotelDate+"'), a.reach_date) = 0";
			List<Map<String, Object>> list1 = jdbcTemplate.queryForList(sql);
			if(list1.size() == 0){
				transactionTemplate.execute(new EndStep(operId, CURR_STEP_CHANGEROOMSTAT, CURR_SUBSTEP_CHANGEROOMSTAT_04, hotelDate, 0, "room_num", "{}"));
			}
			int k = sqlStep - 1;
			for (; k < list1.size(); k++) {
				result = transactionTemplate.execute(new ChangeRoomStat1(operId, hotelDate, CURR_STEP_CHANGEROOMSTAT, CURR_SUBSTEP_CHANGEROOMSTAT_04, k + 1, list1.get(k)));
				if(!result.get("issuccess").toString().equals("true")){
					result.put("currStep", CURR_STEP_CHANGEROOMSTAT);
					result.put("currSubStep", CURR_SUBSTEP_CHANGEROOMSTAT_04);
					result.put("sqlStep", k+1);
					return result;
				}
			}

		default:
			result.put("issuccess", "true");
			result.put("currStep", CURR_STEP_CHANGEROOMSTAT);
			return result;
		}
	}
	//更改房态事务
	class ChangeRoomStat implements TransactionCallback<Map<String, Object>>{
		private String operId;
		private String hotelDate;
		private int currStep;
		private int currSubStep;
		private int sqlStep;
		private String roomStat;
		private Map<String, Object> roomMap;
		public ChangeRoomStat(String operId, String hotelDate, int currStep, int currSubStep, int sqlStep, String roomStat, Map<String, Object> roomMap){
			this.operId = operId;
			this.hotelDate = hotelDate;
			this.currStep = currStep;
			this.currSubStep = currSubStep;
			this.sqlStep = sqlStep;
			this.roomStat = roomStat;
			this.roomMap = roomMap;
		}
		@Override
		public Map<String, Object> doInTransaction(TransactionStatus arg0) {
			Map<String, Object> result = new HashMap<String, Object>();
			try {
				jdbcTemplate.update("update rooms set curr_stat = '"+roomStat+"', modi_time = getdate()  where room_id = " + roomMap.get("room_id").toString());
				transactionTemplate.execute(new EndStep(operId, currStep, currSubStep, hotelDate, sqlStep, "guestdoc, rooms, room_num", new Gson().toJson(roomMap)));
			} catch (DataAccessException e) {
				arg0.setRollbackOnly();
				e.printStackTrace();
				result.put("issuccess", "false");
				result.put("msg", e.getMessage());
				return result;
			}
			result.put("issuccess", "true");
			return result;
		}
		
	}
	class ChangeRoomStat1 implements TransactionCallback<Map<String, Object>>{
		private String operId;
		private String hotelDate;
		private int currStep;
		private int currSubStep;
		private int sqlStep;
		private Map<String, Object> roomMap;
		public ChangeRoomStat1(String operId, String hotelDate, int currStep, int currSubStep, int sqlStep, Map<String, Object> roomMap){
			this.operId = operId;
			this.hotelDate = hotelDate;
			this.currStep = currStep;
			this.currSubStep = currSubStep;
			this.sqlStep = sqlStep;
			this.roomMap = roomMap;
		}
		@Override
		public Map<String, Object> doInTransaction(TransactionStatus arg0) {
			Map<String, Object> result = new HashMap<String, Object>();
			try {
				String flag = roomMap.get("flag") == null ? "2" : roomMap.toString();
				String roomStat = "OOO";
				if(flag.equals("2")){
					roomStat = "OOO";
				}else if(flag.equals("3")){
					roomStat = "OOI";
				}else{
					roomStat = "NVC";
				}
				jdbcTemplate.update("update rooms set curr_stat = '"+roomStat+"', modi_time = getdate()  where room_id = " + roomMap.get("room_id").toString());
				transactionTemplate.execute(new EndStep(operId, currStep, currSubStep, hotelDate, sqlStep, "guestdoc, rooms, room_num", new Gson().toJson(roomMap)));
			} catch (DataAccessException e) {
				arg0.setRollbackOnly();
				e.printStackTrace();
				result.put("issuccess", "false");
				result.put("msg", e.getMessage());
				return result;
			}
			result.put("issuccess", "true");
			return result;
		}
		
	}
	
	/**
	 * 夜审第四大步：转熟客
	 * @param operId
	 * @param hotelDate
	 * @param currStep
	 * @param currSubStep
	 * @return
	 */
	//夜审状态定义
	public static final int CURR_STEP_CHANGEFGUEST = 4;//夜审第四大步：转熟客
	public static final int CURR_SUBSTEP_CHANGEFGUESTT_01 = 1;//夜审第四大步第一小步
	private Map<String, Object> changeFGuest(String operId, String hotelDate, int currSubStep, int sqlStep){
		Map<String, Object> result = new HashMap<String, Object>();
		String sql = "select a.check_id, a.room_id, a.gst_id, convert(varchar(10), a.reach_date, 23) reach_date, convert(varchar(10), a.leave_date, 23) leave_date, a.chk_stat, a.room_price, a.if_fgst, isnull((CASE WHEN a.payman_flag = 1 then (select SUM(balance) from bills where bill_id = a.billa_id or bill_id = a.billb_id) else (select SUM(balance) from bills where bill_id = a.billa_id) end), 0) amount from guestdoc a where rtrim(a.chk_stat) = 'O' and DATEDIFF(DAY, a.leave_date, '"+hotelDate+"') = 0 and (a.gst_id is not null or a.if_fgst = 1)";
		List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);

		if(list.size() == 0){
			transactionTemplate.execute(new EndStep(operId, CURR_STEP_CHANGEFGUEST, CURR_SUBSTEP_CHANGEFGUESTT_01, hotelDate, sqlStep, "guestdoc, fguest", "{}"));
		}
		int i = sqlStep - 1;
		for (; i < list.size(); i++) {
			result = transactionTemplate.execute(new ChangeFGuest(operId, hotelDate, CURR_STEP_CHANGEFGUEST, CURR_SUBSTEP_CHANGEFGUESTT_01, i+1, list.get(i)));
			if(!result.get("issuccess").toString().equals("true")){
				result.put("currStep", CURR_STEP_CHANGEFGUEST);
				result.put("currSubStep", CURR_SUBSTEP_CHANGEFGUESTT_01);
				result.put("sqlStep", i+1);
				return result;
			}
		}
		result.put("issuccess", "true");
		result.put("currStep", CURR_STEP_CHANGEFGUEST);
		return result;
	}
	//转熟客事务
	class ChangeFGuest implements TransactionCallback<Map<String, Object>>{
		private String operId;
		private String hotelDate;
		private int currStep;
		private int currSubStep;
		private int sqlStep;
		private Map<String, Object> guestdocMap;
		public ChangeFGuest(String operId, String hotelDate, int currStep, int currSubStep, int sqlStep, Map<String, Object> guestdocMap){
			this.operId = operId;
			this.hotelDate = hotelDate;
			this.currStep = currStep;
			this.currSubStep = currSubStep;
			this.sqlStep = sqlStep;
			this.guestdocMap = guestdocMap;
		}
		@Override
		public Map<String, Object> doInTransaction(TransactionStatus arg0) {
			Map<String, Object> result = new HashMap<String, Object>();
			try {
				String gst_id = guestdocMap.get("gst_id") == null ? "-1" : guestdocMap.get("gst_id").toString().trim();
				String check_id = guestdocMap.get("check_id") == null ? "" : guestdocMap.get("check_id").toString().trim();
				String room_id = guestdocMap.get("room_id") == null ? "" : guestdocMap.get("room_id").toString().trim();
				String room_price = guestdocMap.get("room_price") == null ? "null" : guestdocMap.get("room_price").toString().trim();
				String reach_date = guestdocMap.get("reach_date") == null ? "" : guestdocMap.get("reach_date").toString().trim();
				String leave_date = guestdocMap.get("leave_date") == null ? "" : guestdocMap.get("leave_date").toString().trim();
				String amount = guestdocMap.get("amount") == null ? "0" : guestdocMap.get("amount").toString();
				int gst_records = jdbcTemplate.queryForInt("select count(*) from fguest where status <> 0 and gst_id="+gst_id);
				if(gst_records == 0){
					//新生成熟客id
					int fgst_id = jdbcTemplate.queryForInt("select para1 from parameter where id = 3");
					fgst_id++;
					//新增熟客信息，从guestdoc里面取客人信息
					String insertFGuest = "insert into fguest select "+fgst_id+", gst_namee, gst_namec, sex, age, wrk_comp, notice, reach_date, leave_date, 0, room_id, room_typeid, room_character, room_price, payman_flag, gst_ori_id, prc_scheme_id, pay_id, limit, credit_id, comp_type, comp_id, comp_cd, city_ledger, house_pay, free_svc, Change_rate, guarantor, secret, hideprice, tele_status, gst_kind, gst_flag, nt_id, area_id, book_type, book_list, tele, mobile, email, plate_number, crdkind_id, crd_id, crd_vld, null, null, birthday, visakind_id, visa_id, depart, in_port, in_date, reason, addr, "+operId+", getdate(), native, folk, works, reg_addr, IddOn, 0, null, 0, Vilhotel_id, chain_id from guestdoc where check_id = " + check_id;
					jdbcTemplate.execute(insertFGuest);
					//保存id序列
					jdbcTemplate.update("update parameter set para1 = "+fgst_id+" where id = 3");
					//保存熟客房价列表
					List<Map<String, Object>> list = jdbcTemplate.queryForList("select a.id, a.check_id, convert(varchar(10), a.hotel_date, 23) hotel_date, a.price, a.memo, a.vilhotel_id, a.chain_id from gst_price_list a where a.check_id = " + check_id);
					int chk_rooms_available = 0;
					double rent = 0;
					for (Map<String, Object> map : list) {
						String thisHotelDate = map.get("hotel_date") == null ? "" : map.get("hotel_date").toString();
						String price = map.get("price") == null ? "0" : map.get("price").toString();
						if(Double.parseDouble(price) >= 0.01){
							chk_rooms_available++;
							rent += Double.parseDouble(price);
						}
						String memo = map.get("memo") == null ? "" : map.get("memo").toString();
						String vilhotel_id = map.get("vilhotel_id") == null ? "" : map.get("vilhotel_id").toString();
						String chain_id = map.get("chain_id") == null ? "" : map.get("chain_id").toString();
						jdbcTemplate.execute("insert into fgst_price_list(check_id, hotel_date, price, memo, oper_id, oper_time, vilhotel_id, chain_id) " +
								"values("+check_id+", '"+thisHotelDate +"', "+price+", '"+memo+"', '"+operId+"', getdate(), '"+vilhotel_id+"', '"+chain_id+"')");
					}
					
					//新增熟客业绩
					jdbcTemplate.execute("INSERT INTO fguest_detail ( gst_id, check_id, room_id, room_price, reach_date, leave_date, spec_svc, chk_rooms, chk_rooms_available, rent, amount) " +
							"VALUES ("+fgst_id+", "+check_id+", '"+room_id+"', "+room_price+", '"+reach_date+"', '"+leave_date+"', null, DATEDIFF(DAY, '"+reach_date+"', '"+leave_date+"'), "+chk_rooms_available+", "+rent+", "+amount+")");
				}else{
					int fgst_id = Integer.parseInt(gst_id);
					//保存熟客房价列表
					List<Map<String, Object>> list = jdbcTemplate.queryForList("select a.id, a.check_id, convert(varchar(10), a.hotel_date, 23) hotel_date, a.price, a.memo, a.vilhotel_id, a.chain_id from gst_price_list a where a.check_id = " + check_id);
					int chk_rooms_available = 0;
					double rent = 0;
					for (Map<String, Object> map : list) {
						String thisHotelDate = map.get("hotel_date") == null ? "" : map.get("hotel_date").toString();
						String price = map.get("price") == null ? "0" : map.get("price").toString();
						if(Double.parseDouble(price) >= 0.01){
							chk_rooms_available++;
							rent += Double.parseDouble(price);
						}
						String memo = map.get("memo") == null ? "" : map.get("memo").toString();
						String vilhotel_id = map.get("vilhotel_id") == null ? "" : map.get("vilhotel_id").toString();
						String chain_id = map.get("chain_id") == null ? "" : map.get("chain_id").toString();
						jdbcTemplate.execute("insert into fgst_price_list(check_id, hotel_date, price, memo, oper_id, oper_time, vilhotel_id, chain_id) " +
								"values("+check_id+", '"+thisHotelDate +"', "+price+", '"+memo+"', '"+operId+"', getdate(), '"+vilhotel_id+"', '"+chain_id+"')");
					}
					
					//新增熟客业绩
					jdbcTemplate.execute("INSERT INTO fguest_detail ( gst_id, check_id, room_id, room_price, reach_date, leave_date, spec_svc, chk_rooms, chk_rooms_available, rent, amount) " +
							"VALUES ("+fgst_id+", "+check_id+", '"+room_id+"', "+room_price+", '"+reach_date+"', '"+leave_date+"', null, DATEDIFF(DAY, '"+reach_date+"', '"+leave_date+"'), "+chk_rooms_available+", "+rent+", "+amount+")");
				}

				transactionTemplate.execute(new EndStep(operId, currStep, currSubStep, hotelDate, sqlStep, "guestdoc, rooms, room_num", new Gson().toJson(guestdocMap)));
			} catch (Exception e) {
				e.printStackTrace();
				arg0.setRollbackOnly();
				result.put("issuccess", "false");
				result.put("msg", e.getMessage());
				return result;
			}
			result.put("issuccess", "true");
			return result;
		}
	}
	
	/**
	 * 夜审第五大步：清理预定数据
	 * @param operId
	 * @param hotelDate
	 * @param currStep
	 * @param currSubStep
	 * @return
	 */
	//夜审状态定义
	public static final int CURR_STEP_CLEARBOOKROOM = 5;//夜审第五大步：清理预定数据
	public static final int CURR_SUBSTEP_CLEARBOOKROOM_01 = 1;//夜审第五大步第一小步
	public static final int CURR_SUBSTEP_CLEARBOOKROOM_02 = 2;//夜审第五大步第二小步
	public static final int CURR_SUBSTEP_CLEARBOOKROOM_03 = 3;//夜审第五大步第三小步
	private Map<String, Object> clearBookRoom(String operId, String hotelDate, int currSubStep, int sqlStep){
		Map<String, Object> result = new HashMap<String, Object>();
		List<Map<String, Object>> list;
		switch (currSubStep) {
		case CURR_SUBSTEP_CLEARBOOKROOM_01:
			//第一小步：清理状态正常的预定
			list = jdbcTemplate.queryForList("select a.id, a.book_room_id, a.check_id, a.roomtype_id, a.book_num, a.save_num, a.reach_num, a.room_price, a.reach_date, a.leave_date, (CASE WHEN b.book_stat is not null then b.book_stat when c.book_stat is not null then c.book_stat else 'C' end) book_stat, (isnull(b.no_arrive_cancel, 0) | isnull(c.no_arrive_cancel, 0)) no_arrive_cancel from book_room a left join grp_doc b on b.check_id = a.check_id left join guestdoc c on c.check_id = a.check_id where datediff(day, a.reach_date, '"+hotelDate+"') = 0 and a.status = 0 and (isnull(b.no_arrive_cancel, 0) | isnull(c.no_arrive_cancel, 0)) <> 1 ");

			if(list.size() == 0){
				transactionTemplate.execute(new EndStep(operId, CURR_STEP_CLEARBOOKROOM, currSubStep, hotelDate, sqlStep, "guestdoc, book_room, grp_doc", "{}"));
			}
			int i = sqlStep - 1;
			for (; i < list.size(); i++) {
				result = transactionTemplate.execute(new ClearBookRoom(operId, hotelDate, CURR_STEP_CLEARBOOKROOM, CURR_SUBSTEP_CLEARBOOKROOM_01, i+1, list.get(i)));
				if(!result.get("issuccess").toString().equals("true")){
					result.put("currStep", CURR_STEP_CLEARBOOKROOM);
					result.put("currSubStep", CURR_SUBSTEP_CLEARBOOKROOM_01);
					result.put("sqlStep", i+1);
					return result;
				}
			}

		case CURR_SUBSTEP_CLEARBOOKROOM_02:
			//第二小步：清理留房到离店日期的预定（释放房间）
			list = jdbcTemplate.queryForList("select a.id, a.book_room_id, a.check_id, a.roomtype_id, a.book_num, a.save_num, a.reach_num, a.room_price, a.reach_date, a.leave_date, (CASE WHEN b.book_stat is not null then b.book_stat when c.book_stat is not null then c.book_stat else 'C' end) book_stat from book_room a left join grp_doc b on b.check_id = a.check_id left join guestdoc c on c.check_id = a.check_id where datediff(day, a.leave_date, '"+hotelDate+"') = 0 and a.status <> 1 and (select para2 from parameter where id = 12) = 0");

			if(list.size() == 0){
				transactionTemplate.execute(new EndStep(operId, CURR_STEP_CLEARBOOKROOM, CURR_SUBSTEP_CLEARBOOKROOM_02, hotelDate, sqlStep, "guestdoc, book_room, grp_doc", "{}"));
			}
			int j = sqlStep - 1;
			for (; j < list.size(); j++) {
				result = transactionTemplate.execute(new ClearSaveRoom(operId, hotelDate, CURR_STEP_CLEARBOOKROOM, CURR_SUBSTEP_CLEARBOOKROOM_02, j+1, list.get(j)));
				if(!result.get("issuccess").toString().equals("true")){
					result.put("currStep", CURR_STEP_CLEARBOOKROOM);
					result.put("currSubStep", CURR_SUBSTEP_CLEARBOOKROOM_02);
					result.put("sqlStep", j+1);
					return result;
				}
			}

		case CURR_SUBSTEP_CLEARBOOKROOM_03:
			//第三小步：修改预定状态
			list = jdbcTemplate.queryForList("select z.* " +
					"from ( " +
					"	select x.check_id, x.book_stat, x.book_type, " +
					"	sum(CASE WHEN y.status is null then 0 else 1 end) count, " +
					"	sum(CASE WHEN y.status = 1 then 1 else 0 end) cancelcount, " +
					"	sum(CASE WHEN y.status = 3 then 1 else 0 end) noshowcount " +
					"	from ( " +
					"		select a.check_id, a.book_stat, 'GR' book_type " +
					"		from grp_doc a where a.book_stat in ('B', '$', 'G', 'O') " +
					"		union all " +
					"		select b.check_id, b.book_stat, 'GU' book_type " +
					"		from guestdoc b where b.book_stat in ('B', '$', 'G', 'O') " +
					"	) x " +
					"	left join book_room y on x.check_id = y.check_id " +
					"	group by x.check_id, x.book_stat, x.book_type" +
					") z " +
					"where (rtrim(z.book_stat) = 'B' and z.count <> 0 and z.count = z.cancelcount) " +
					"or (rtrim(z.book_stat) <> 'B' and z.count <> 0 and z.count = z.noshowcount) ");

			if(list.size() == 0){
				transactionTemplate.execute(new EndStep(operId, CURR_STEP_CLEARBOOKROOM, CURR_SUBSTEP_CLEARBOOKROOM_03, hotelDate, sqlStep, "guestdoc, book_room, grp_doc", "{}"));
			}
			int k = sqlStep - 1;
			for (; k < list.size(); k++) {
				result = transactionTemplate.execute(new ChangeBookStat(operId, hotelDate, CURR_STEP_CLEARBOOKROOM, CURR_SUBSTEP_CLEARBOOKROOM_03, k+1, list.get(k)));
				if(!result.get("issuccess").toString().equals("true")){
					result.put("currStep", CURR_STEP_CLEARBOOKROOM);
					result.put("currSubStep", CURR_SUBSTEP_CLEARBOOKROOM_03);
					result.put("sqlStep", k+1);
					return result;
				}
			}

		default:
			result.put("issuccess", "true");
			result.put("currStep", CURR_STEP_CLEARBOOKROOM);
			return result;
		}
		
		
		
	}
	
	//清理正常预定事务
	class ClearBookRoom implements TransactionCallback<Map<String, Object>>{
		private String operId;
		private String hotelDate;
		private int currStep;
		private int currSubStep;
		private int sqlStep;
		private Map<String, Object> bookRoomMap;
		public ClearBookRoom(String operId, String hotelDate, int currStep, int currSubStep, int sqlStep, Map<String, Object> bookRoomMap){
			this.operId = operId;
			this.hotelDate = hotelDate;
			this.currStep = currStep;
			this.currSubStep = currSubStep;
			this.sqlStep = sqlStep;
			this.bookRoomMap = bookRoomMap;
		}
		@Override
		public Map<String, Object> doInTransaction(TransactionStatus arg0) {
			Map<String, Object> result = new HashMap<String, Object>();
			try {
				int book_num = bookRoomMap.get("book_num") == null ? 0 : Integer.parseInt(bookRoomMap.get("book_num").toString());
				String book_stat = bookRoomMap.get("book_stat") == null ? "" : bookRoomMap.get("book_stat").toString().trim();
				int reach_num = bookRoomMap.get("reach_num") == null ? 0 : Integer.parseInt(bookRoomMap.get("reach_num").toString());
				String id = bookRoomMap.get("id") == null ? "0" : bookRoomMap.get("id").toString();
				String no_arrive_cancel = bookRoomMap.get("no_arrive_cancel") == null ? "" : bookRoomMap.get("no_arrive_cancel").toString().trim();
				//计算noshow_num
				int noshownum = (book_num-reach_num) < 0 ? 0 : (book_num-reach_num);
				//TODO 如果有留房，根据过期未抵达是否释放留房

				//判断释放留房
				if(!no_arrive_cancel.equals("1")){
					//如果不释放留房，那么原订单状态不变
					result.put("issuccess", "true");
					return result;
				}
				if(reach_num <= 0){
					//订单未抵达，根据订单是否确认处理
					if(book_stat.equals("B")){
						//未确认预定的情况，状态改为过期预定：3
						jdbcTemplate.update("update book_room set status = 3 where id = " + id);
					}else if(book_stat.equals("$") || book_stat.equals("G") || book_stat.equals("O")){
						//已确认预定的情况
						jdbcTemplate.update("update book_room set noshow_num = " + noshownum + " where id = " + id);
					}else{
						//其他情况不处理
					}
				}else{
					//订单部分抵达，状态改成全部抵达
					jdbcTemplate.update("update book_room set status = 2, noshow_num = "+noshownum+" where id = " + id);
				}
				//释放留房
				List<Map<String, Object>> list = jdbcTemplate.queryForList("select a.id from room_num a where a.book_id = "+id+" and a.flag = 0 and a.status = 0");
				if(list.size() > 0){
					if (no_arrive_cancel.equals("1")){
						for (Map<String, Object> map : list) {
							jdbcTemplate.update("update room_num set status = 1 where id = " + (map.get("id") == null ? "0" : map.get("id").toString()));
						}
					}
				}
				transactionTemplate.execute(new EndStep(operId, currStep, currSubStep, hotelDate, sqlStep, "guestdoc, rooms, room_num", new Gson().toJson(bookRoomMap)));
			} catch (Exception e) {
				e.printStackTrace();
				arg0.setRollbackOnly();
				result.put("issuccess", "false");
				result.put("msg", e.getMessage());
				return result;
			}
			result.put("issuccess", "true");
			return result;
		}
	}

	//清理留房到离店日期的预定（释放房间）事务
	class ClearSaveRoom implements TransactionCallback<Map<String, Object>>{
		private String operId;
		private String hotelDate;
		private int currStep;
		private int currSubStep;
		private int sqlStep;
		private Map<String, Object> bookRoomMap;
		public ClearSaveRoom(String operId, String hotelDate, int currStep, int currSubStep, int sqlStep, Map<String, Object> bookRoomMap){
			this.operId = operId;
			this.hotelDate = hotelDate;
			this.currStep = currStep;
			this.currSubStep = currSubStep;
			this.sqlStep = sqlStep;
			this.bookRoomMap = bookRoomMap;
		}
		@Override
		public Map<String, Object> doInTransaction(TransactionStatus arg0) {
			Map<String, Object> result = new HashMap<String, Object>();
			try {
				//订单有留房的情况下释放留房
				String id = bookRoomMap.get("id") == null ? "0" : bookRoomMap.get("id").toString();
				List<Map<String, Object>> list = jdbcTemplate.queryForList("select a.id from room_num a where a.book_id = "+id+" and a.flag = 0 and a.status = 0");
				if(list.size() > 0){
					for (Map<String, Object> map : list) {
						jdbcTemplate.update("update room_num set status = 1 where id = " + (map.get("id") == null ? "0" : map.get("id").toString()));
					}
				}
				transactionTemplate.execute(new EndStep(operId, currStep, currSubStep, hotelDate, sqlStep, "guestdoc, rooms, room_num", new Gson().toJson(bookRoomMap)));
			} catch (Exception e) {
				e.printStackTrace();
				arg0.setRollbackOnly();
				result.put("issuccess", "false");
				result.put("msg", e.getMessage());
			}
			result.put("issuccess", "true");
			return result;
		}
	}
	
	/**
	 * 更新预定但状态
	 * @author Administrator
	 *
	 */
	class ChangeBookStat implements TransactionCallback<Map<String, Object>>{
		private String operId;
		private String hotelDate;
		private int currStep;
		private int currSubStep;
		private int sqlStep;
		private Map<String, Object> bookRoomMap;
		public ChangeBookStat(String operId, String hotelDate, int currStep, int currSubStep, int sqlStep, Map<String, Object> bookRoomMap){
			this.operId = operId;
			this.hotelDate = hotelDate;
			this.currStep = currStep;
			this.currSubStep = currSubStep;
			this.sqlStep = sqlStep;
			this.bookRoomMap = bookRoomMap;
		}
		@Override
		public Map<String, Object> doInTransaction(TransactionStatus arg0) {
			Map<String, Object> result = new HashMap<String, Object>();
			try {
				//预定状态
				String book_stat = bookRoomMap.get("book_stat") == null ? "" : bookRoomMap.get("book_stat").toString();
				//预定号
				String check_id = bookRoomMap.get("check_id") == null ? "-1" : bookRoomMap.get("check_id").toString();
				//预定类型（GR：团队预定，GU：散客预定）
				String book_type = bookRoomMap.get("book_type") == null ? "" : bookRoomMap.get("book_type").toString();
				String stat = "";
				if(book_stat.equals("B")){
					stat = "P";
				}else{
					stat = "N";
				}
				if(book_type.endsWith("GR")){
					jdbcTemplate.update("update grp_doc set book_stat = '"+stat+"' where check_id = " + check_id);
				}else{
					jdbcTemplate.update("update guestdoc set book_stat = '"+stat+"' where check_id = " + check_id);
				}
				transactionTemplate.execute(new EndStep(operId, currStep, currSubStep, hotelDate, sqlStep, "guestdoc, grp_doc", new Gson().toJson(bookRoomMap)));
			} catch (Exception e) {
				e.printStackTrace();
				arg0.setRollbackOnly();
				result.put("issuccess", "false");
				result.put("currStep", currStep);
				result.put("currSubStep", currSubStep);
				result.put("msg", e.getMessage());
			}
			result.put("currStep", currStep);
			result.put("currSubStep", currSubStep);
			result.put("issuccess", "true");
			return result;
		}
	}
	
	/**
	 * 夜审第六大步：统计
	 */
	public static final int CURR_STEP_STATISTICS = 6;//夜审第六大步：统计
	public static final int CURR_SUBSTEP_STATISTICS_01 = 1;//夜审第六大步第一小步
	public static final int CURR_SUBSTEP_STATISTICS_02 = 2;//夜审第六大步第二小步
	public static final int CURR_SUBSTEP_STATISTICS_03 = 3;//夜审第六大步第三小步
	private Map<String, Object> statistics(String operId, String hotelDate, int currSubStep, int sqlStep){
		Map<String, Object> result = new HashMap<String, Object>();
		switch (currSubStep) {
		case CURR_SUBSTEP_STATISTICS_01:
			result = transactionTemplate.execute(new BusinessStatistics(operId, hotelDate, CURR_STEP_STATISTICS, CURR_SUBSTEP_STATISTICS_01));
			if(!result.get("issuccess").equals("true")){
				result.put("currStep", CURR_STEP_STATISTICS);
				result.put("currSubStep", CURR_SUBSTEP_STATISTICS_01);
				result.put("sqlStep", 0);
				return result;
			}
		case CURR_SUBSTEP_STATISTICS_02:
			StringBuilder builder = new StringBuilder("");
			builder.append("SELECT a.cons_id, a.setl_id, ");
			builder.append("       isnull (sum (a.balance), 0) d_incomes, ");
			builder.append("       isnull (sum (a.svc_charge), 0) d_service, ");
			builder.append("       isnull (sum (a.balance+a.svc_charge), 0) d_credit ");
			builder.append("  FROM bills a ");
			builder.append(" WHERE a.status <> 2 AND a.cons_id <> '*' ");
			builder.append("       AND rtrim (a.ext_name) <> '转出' ");
			builder.append("       AND datediff (day, a.hotel_time, '"+hotelDate+"') = 0 ");
			builder.append("       AND a.bill_type IN ('1', '2', '3', '4')");
			builder.append(" GROUP BY a.cons_id, a.setl_id");
			List<Map<String, Object>> list = jdbcTemplate.queryForList(builder.toString());
			int i = sqlStep - 1;
			for (; i < list.size(); i++) {
				result = transactionTemplate.execute(new RevenueStatistics(operId, hotelDate, CURR_STEP_STATISTICS, CURR_SUBSTEP_STATISTICS_02, i+1, list.get(i)));
				if(!result.get("issuccess").toString().equals("true")){
					result.put("currStep", CURR_STEP_STATISTICS);
					result.put("currSubStep", CURR_SUBSTEP_STATISTICS_02);
					result.put("sqlStep", i+1);
					return result;
				}
			}
		case CURR_SUBSTEP_STATISTICS_03:
			StringBuilder builder1 = new StringBuilder("");
			builder1.append("SELECT a.cons_id, a.setl_id, ");
			builder1.append("       isnull (sum (a.balance), 0) d_incomes, ");
			builder1.append("       isnull (sum (a.svc_charge), 0) d_service, ");
			builder1.append("       isnull (sum (a.balance+a.svc_charge), 0) d_credit ");
			builder1.append("  FROM bills a ");
			builder1.append(" WHERE a.status <> 2 AND a.setl_id <> '*' ");
			builder1.append("       AND rtrim (a.ext_name) <> '转出' ");
			builder1.append("       AND datediff (day, a.hotel_time, '"+hotelDate+"') = 0 ");
			builder1.append("       AND a.bill_type IN ('1', '2', '3', '4')");
			builder1.append(" GROUP BY a.cons_id, a.setl_id");
			List<Map<String, Object>> list1 = jdbcTemplate.queryForList(builder1.toString());
			int j = sqlStep - 1;
			for (; j < list1.size(); j++) {
				result = transactionTemplate.execute(new RevenueStatistics1(operId, hotelDate, CURR_STEP_STATISTICS, CURR_SUBSTEP_STATISTICS_03, j+1, list1.get(j)));
				if(!result.get("issuccess").toString().equals("true")){
					result.put("currStep", CURR_STEP_STATISTICS);
					result.put("currSubStep", CURR_SUBSTEP_STATISTICS_03);
					result.put("sqlStep", j+1);
					return result;
				}
			}
		default:
			result.put("issuccess", "true");
			result.put("currStep", CURR_STEP_STATISTICS);
			return result;
		}
		
	}
	
	/*
	 * 营业统计
	 */
	class BusinessStatistics implements TransactionCallback<Map<String, Object>>{
		private String operId;
		private String hotelDate;
		private int currStep;
		private int currSubStep;
		public BusinessStatistics(String operId, String hotelDate, int currStep, int currSubStep){
			this.operId = operId;
			this.hotelDate = hotelDate;
			this.currStep = currStep;
			this.currSubStep = currSubStep;
		}

		@Override
		public Map<String, Object> doInTransaction(TransactionStatus arg0) {
			Map<String, Object> result = new HashMap<String, Object>();
			try {
				StringBuilder builder;
				
				//查询当前所有可用房间数量
				String sql = "select count(*) total_room from rooms a where a.status = 0";
				//总房间存量
				String total_room = jdbcTemplate.queryForObject(sql, String.class);
				//查询当前维修房数量和冻结房数量
				builder = new StringBuilder("");
				builder.append("SELECT x.hotel_date, ");
				builder.append("       sum (CASE WHEN y.flag = 2 THEN 1 ELSE 0 END) rfx_room, ");
				builder.append("       sum (CASE WHEN y.flag = 3 THEN 1 ELSE 0 END) njf_room ");
				builder.append("  FROM (SELECT '"+hotelDate+"' hotel_date) x ");
				builder.append("       LEFT JOIN rooms_diary y ");
				builder.append("          ON DATEDIFF (day, x.hotel_date, y.hotel_date) = 0 ");
				builder.append("GROUP BY x.hotel_date");
				String sql0 = builder.toString();
				List<Map<String, Object>> list0 = jdbcTemplate.queryForList(sql0);
				//维修房数量
				String rfx_room = "0";
				//冻结房数量
				String njf_room = "0";
				if(list0.size() > 0){
					Map<String, Object> map = list0.get(0);
					rfx_room = map.get("rfx_room") == null ? "0" : map.get("rfx_room").toString();
					njf_room = map.get("njf_room") == null ? "0" : map.get("njf_room").toString();
				}
				//查询当前自用房、团体房、免费房、Walk In房数
				builder = new StringBuilder("");
				builder.append("SELECT count(x.room_id) live_room, ");
				builder.append("        isnull (sum (x.self_room), 0) self_room, ");
				builder.append("       isnull (sum (x.sale_grp_room), 0) sale_grp_room, ");
				builder.append("       isnull (sum (x.free_room), 0) free_room, ");
				builder.append("       isnull (sum (x.checkout_gst), 0) checkout_gst ");
				builder.append("  FROM (SELECT e.room_id, ");
				builder.append("               (CASE WHEN d.check_type = 5 THEN 1 ELSE 0 END) self_room, ");
				builder.append("               (CASE WHEN e.gst_flag = 'G' THEN 1 ELSE 0 END) sale_grp_room, ");
				builder.append("               (CASE WHEN d.check_type = 4 THEN 1 ELSE 0 END) free_room, ");
				builder.append("               (CASE WHEN d.check_type = 1 THEN 1 ELSE 0 END) checkout_gst ");
				builder.append("          FROM (SELECT DISTINCT a.room_id, a.gst_flag, a.gst_ori_id ");
				builder.append("                  FROM guestdoc a ");
				builder.append("                 WHERE a.chk_stat = 'I') e ");
				builder.append("               LEFT JOIN (SELECT b.code_id, ");
				builder.append("                                 (SELECT c.check_type ");
				builder.append("                                    FROM hgst_ori_type c ");
				builder.append("                                   WHERE c.code_id = b.ori_kind) ");
				builder.append("                                    check_type ");
				builder.append("                            FROM hgst_ori b) d ");
				builder.append("                  ON e.gst_ori_id = d.code_id) x ");
				String sql1 = builder.toString();
				List<Map<String, Object>> list1 = jdbcTemplate.queryForList(sql1);
				//在住房数量
				String live_room = "0";
				//自用房数量
				String self_room = "0";
				//团队房数量
				String sale_grp_room = "0";
				//免费房数量
				String free_room = "0";
				//Walk In房数量
				String checkout_gst = "0";
				if(list1.size() > 0){
					Map<String, Object> map = list1.get(0);
					live_room = map.get("live_room") == null ? "0" : map.get("live_room").toString();
					self_room = map.get("self_room") == null ? "0" : map.get("self_room").toString();
					sale_grp_room = map.get("sale_grp_room") == null ? "0" : map.get("sale_grp_room").toString();
					free_room = map.get("free_room") == null ? "0" : map.get("free_room").toString();
					checkout_gst = map.get("checkout_gst") == null ? "0" : map.get("checkout_gst").toString();
				}
				//可买房数量=总房数-自用房数-免费房数-维修房数-冻结房数
				int rentable_room_temp = Integer.parseInt(total_room) -Integer.parseInt(self_room) - Integer.parseInt(free_room) - Integer.parseInt(rfx_room) - Integer.parseInt(njf_room);
				String rentable_room = Integer.toString(rentable_room_temp);
				
				
				
				//sale_room=所有在住房-免费-自用？
				int sale_room_temp = Integer.parseInt(live_room) - Integer.parseInt(free_room) - Integer.parseInt(self_room);
				String sale_room = Integer.toString(sale_room_temp);
				
				
				
				//查询当前预定房数、团队订房数、未到房数
				builder = new StringBuilder("");
				builder.append("SELECT isnull (sum (a.book_num), 0) book_room, ");
				builder.append("       isnull (sum (CASE WHEN b.grp_id IS NULL THEN 0 ELSE a.book_num END), 0) book_grp_room, ");
				builder.append("       isnull (sum (a.noshow_num), 0) noshow_room ");
				builder.append("  FROM book_room a LEFT JOIN grp_doc b ON b.check_id = a.check_id ");
				builder.append(" WHERE     DATEDIFF (day, a.reach_date, '"+hotelDate+"') >= 0 ");
				builder.append("       AND DATEDIFF (day, '"+hotelDate+"', a.leave_date) >= 0");
				String sql2 = builder.toString();
				List<Map<String, Object>> list2 = jdbcTemplate.queryForList(sql2);
				//预定房数量
				String book_room = "0";
				//团体预定房数量
				String book_grp_room = "0";
				//未到房数量
				String noshow_room = "0";
				if(list2.size() > 0){
					Map<String, Object> map = list2.get(0);
					book_room = map.get("book_room") == null ? "0" : map.get("book_room").toString();
					book_grp_room = map.get("book_grp_room") == null ? "0" : map.get("book_grp_room").toString();
					noshow_room = map.get("noshow_room") == null ? "0" : map.get("noshow_room").toString();
				}
				//查询当前离店房数
				//TODO 一个房间有一个人离店的情况
				String sql3 = "select count(distinct a.room_id) checkout_room from guestdoc a where a.chk_stat = 'O' and DATEDIFF(day, '"+hotelDate+"', a.leave_date) = 0  and not exists(select * from guestdoc b where b.chk_stat = 'I' and b.room_id = a.room_id)";
				//离店房数量
				String checkout_room = jdbcTemplate.queryForObject(sql3, String.class);
				//查询当天入住房数
				String sql4 = "select count(distinct a.room_id) checkin_room from guestdoc a where a.chk_stat in ('I', 'O') and DATEDIFF(day, '"+hotelDate+"', a.reach_date) = 0";
				//入住房数量
				String checkin_room = jdbcTemplate.queryForObject(sql4, String.class);
				//查询当天在住人数、团员人数、免费人数、离店人数、提前离店人数、入住人数、散客人数
				//TODO 当天入住，当天离店的情况
				builder = new StringBuilder("");
				builder.append("SELECT isnull(sum(CASE WHEN a.chk_stat = 'I' THEN 1 ELSE 0 end), 0) tol_guest, ");
				builder.append("	   isnull(sum(CASE WHEN a.grp_chkid is not null and a.grp_chkid > 0 THEN 1 ELSE 0 END), 0) grp_guest, ");
				builder.append("	   isnull(sum(CASE WHEN a.chk_stat = 'I' and d.check_type = 4 THEN 1 ELSE 0 END), 0) free_guest, ");
				builder.append("	   isnull(sum(CASE WHEN a.chk_stat = 'O' and DATEDIFF(day, a.leave_date, '"+hotelDate+"') = 0 THEN 1 ELSE 0 END), 0) leave_guest, ");
				builder.append("	   isnull(sum(CASE WHEN a.chk_stat = 'O' and DATEDIFF(day, a.leave_date0, a.leave_date) > 0 THEN 1 ELSE 0 END), 0) unexptd_leave, ");
				builder.append("	   isnull(sum(CASE WHEN a.chk_stat IN ('I','O') and DATEDIFF(day, a.reach_date, '"+hotelDate+"') = 0 THEN 1 ELSE 0 END), 0) checkin_gst, ");
				builder.append("	   isnull(sum(CASE WHEN a.chk_stat = 'I' and d.check_type = 5 THEN 1 ELSE 0 END), 0) walkin_gst ");
				builder.append("  FROM guestdoc a ");
				builder.append("	   LEFT JOIN (SELECT b.code_id, ");
				builder.append("						 (SELECT c.check_type ");
				builder.append("							FROM hgst_ori_type c ");
				builder.append("						   WHERE c.code_id = b.ori_kind) check_type ");
				builder.append("					FROM hgst_ori b) d ");
				builder.append("		  ON a.gst_ori_id = d.code_id");
				String sql5 = builder.toString();
				List<Map<String, Object>> list5 = jdbcTemplate.queryForList(sql5);
				//在住人数
				String tol_guest = "0";
				//团员人数
				String grp_guest = "0";
				//免费人数
				String free_guest = "0";
				//离店人数
				String leave_guest = "0";
				//提前离店人数
				String unexptd_leave = "0";
				//入住人数
				String checkin_gst = "0";
				//散客人数
				String walkin_gst = "0";
				if(list5.size() > 0){
					Map<String, Object> map = list5.get(0);
					tol_guest = map.get("tol_guest") == null ? "0" : map.get("tol_guest").toString();
					grp_guest = map.get("grp_guest") == null ? "0" : map.get("grp_guest").toString();
					free_guest = map.get("free_guest") == null ? "0" : map.get("free_guest").toString();
					leave_guest = map.get("leave_guest") == null ? "0" : map.get("leave_guest").toString();
					unexptd_leave = map.get("unexptd_leave") == null ? "0" : map.get("unexptd_leave").toString();
					checkin_gst = map.get("checkin_gst") == null ? "0" : map.get("checkin_gst").toString();
					walkin_gst = map.get("walkin_gst") == null ? "0" : map.get("walkin_gst").toString();
				}
				
				//挂账的付款类型代码
				List<Map<String, Object>> codelist = jdbcTemplate.queryForList("select STUFF((select ',' + code_id from hsettle where kind = a.kind for xml path('')), 1, 1, '') code_id from hsettle a where kind = (select para5 from parameter where id = 13) group by a.kind");
				String code_id = codelist.size() > 0 ? codelist.get(0).get("code_id").toString() : "0";
				code_id.replace(",", "','");
				//挂AR账的付款类型代码
				List<Map<String, Object>> arcodelist = jdbcTemplate.queryForList("select STUFF((select ',' + code_id from hsettle where kind = a.kind for xml path('')), 1, 1, '') code_id from hsettle a where kind = (select para5 from parameter where id = 14) group by a.kind");
				String ar_code_id = arcodelist.size() > 0 ? arcodelist.get(0).get("code_id").toString() : "0";
				ar_code_id.replace(",", "','");
				
				//房租消费点代码
				List<Map<String, Object>> consume_code_room_list = jdbcTemplate.queryForList(" select code_id from hconsume where kind like (select para5+'%' from parameter where id = 12)");
				String consume_code_room = "";
				for (int i = 0; i < consume_code_room_list.size(); i++) {
					if(i == 0){
						consume_code_room = consume_code_room_list.get(i).get("code_id").toString();
					}else{
						consume_code_room += ("','" + consume_code_room_list.get(i).get("code_id").toString());
					}
				}
				//日租、半日租消费点代码
				String consume_code_day = jdbcTemplate.queryForObject("select para6 from parameter where id = 13", String.class);
				//钟点租消费点代码
				String consume_code_clock = jdbcTemplate.queryForObject("select para6 from parameter where id = 14", String.class);
				//查询当天前台挂帐余额、挂房帐、挂非住客总额、结房帐总额、结非住客总额、酒店总营业收入、房租营业收入、房租服务费收入、
				builder = new StringBuilder("");
				builder.append("SELECT isnull(sum(CASE WHEN rtrim(a.cons_id) <> '*' THEN a.balance ELSE 0 END), 0) balance, ");
				builder.append("	   isnull(sum(CASE WHEN rtrim(a.cons_id) <> '*' and b.check_id is not null THEN a.balance ELSE 0 END), 0) gst_debit, ");
				builder.append("	   isnull(sum(CASE WHEN rtrim(a.cons_id) <> '*' and c.id is not null THEN a.balance ELSE 0 END), 0) nogst_debit, ");
				builder.append("	   isnull(sum(CASE WHEN rtrim(a.setl_id) <> '*' and b.check_id is not null THEN a.balance ELSE 0 END), 0) gst_credit, ");
				builder.append("	   isnull(sum(CASE WHEN rtrim(a.setl_id) <> '*' and c.id is not null and a.ok_flag = 1 THEN a.balance ELSE 0 END), 0) nogst_credit, ");
				builder.append("	   isnull(sum(CASE WHEN rtrim(a.cons_id) <> '*' and b.check_id is not null THEN a.balance ELSE 0 END), 0) hotel_incomes, ");
				builder.append("	   isnull(sum(CASE WHEN rtrim(a.cons_id) in ('"+consume_code_room+"') and b.check_id is not null THEN a.balance ELSE 0 END), 0) rent_incomes, ");
				builder.append("	   isnull(sum(CASE WHEN rtrim(a.cons_id) in ('"+consume_code_room+"') and b.check_id is not null THEN a.svc_charge ELSE 0 END), 0) rmsvc_incomes, ");
				builder.append("	   isnull(sum(CASE WHEN rtrim(a.cons_id) = '"+consume_code_day+"' THEN a.balance ELSE 0 END), 0) day_incomes, ");
				builder.append("	   isnull(sum(CASE WHEN rtrim(a.cons_id) = '"+consume_code_day+"' THEN a.svc_charge ELSE 0 END), 0) drsvc_incomes,");
				builder.append("	   isnull(sum(CASE WHEN rtrim(a.cons_id) = '"+consume_code_clock+"' THEN a.balance ELSE 0 END), 0) clock_incomes,");
				builder.append("	   isnull(sum(CASE WHEN rtrim(a.cons_id) = '"+consume_code_clock+"' THEN a.svc_charge ELSE 0 END), 0) csvc_incomes,");
				builder.append("	   isnull(sum(CASE WHEN rtrim(a.setl_id) not in ('"+code_id+"') THEN a.svc_charge+a.balance ELSE 0 END), 0) hotel_credit,");
				builder.append("	   isnull(sum(CASE WHEN rtrim(a.cons_id) in ('"+ar_code_id+"') THEN a.svc_charge+a.balance ELSE 0 END), 0) hotel_city");
				builder.append("  FROM bills a ");
				builder.append("	   LEFT JOIN guestdoc b ON b.billa_id = a.bill_id or b.billb_id = a.bill_id ");
				builder.append("	   LEFT JOIN noguest c on c.bill_id = a.bill_id ");
				builder.append(" WHERE a.status <> 2 and rtrim(a.ext_name) <> '转出' and datediff(day, a.hotel_time, '"+hotelDate+"') = 0 and a.bill_type in ('1', '2', '3', '4')");
				String sql6 = builder.toString();
				List<Map<String, Object>> list6 = jdbcTemplate.queryForList(sql6);
				//前台挂帐余额
				String balance = "0";
				//挂房帐
				String gst_debit = "0";
				//挂非住客总额
				String nogst_debit = "0";
				//结房帐总额
				String gst_credit = "0";
				//挂非住客总额
				String nogst_credit = "0";
				//酒店总营业收入
				String hotel_incomes = "0";
				//房租营业收入
				String rent_incomes = "0";
				//房租服务费收入
				String rmsvc_incomes = "0";
				//日租半日租收入
				String day_incomes = "0";
				//日租半日租服务费收入
				String drsvc_incomes = "0";
				//钟点租营业额
				String clock_incomes = "0";
				//钟点租服务费
				String csvc_incomes = "0";
				//酒店收款
				String hotel_credit = "0";
				//AR挂帐
				String hotel_city = "0";
				if(list6.size() > 0){
					Map<String, Object> map = list6.get(0);
					balance = map.get("balance") == null ? "0" : map.get("balance").toString();
					gst_debit = map.get("gst_debit") == null ? "0" : map.get("gst_debit").toString();
					nogst_debit = map.get("nogst_debit") == null ? "0" : map.get("nogst_debit").toString();
					gst_credit = map.get("gst_credit") == null ? "0" : map.get("gst_credit").toString();
					nogst_credit = map.get("nogst_credit") == null ? "0" : map.get("nogst_credit").toString();
					hotel_incomes = map.get("hotel_incomes") == null ? "0" : map.get("hotel_incomes").toString();
					rent_incomes = map.get("rent_incomes") == null ? "0" : map.get("rent_incomes").toString();
					rmsvc_incomes = map.get("rmsvc_incomes") == null ? "0" : map.get("rmsvc_incomes").toString();
					day_incomes = map.get("day_incomes") == null ? "0" : map.get("day_incomes").toString();
					drsvc_incomes = map.get("drsvc_incomes") == null ? "0" : map.get("drsvc_incomes").toString();
					clock_incomes = map.get("clock_incomes") == null ? "0" : map.get("clock_incomes").toString();
					csvc_incomes = map.get("csvc_incomes") == null ? "0" : map.get("csvc_incomes").toString();
					hotel_credit = map.get("hotel_credit") == null ? "0" : map.get("hotel_credit").toString();
					hotel_credit = map.get("hotel_credit") == null ? "0" : map.get("hotel_credit").toString();
				}
				
				//构建增加日统计sql
				builder = new StringBuilder("");
				builder.append("INSERT INTO mammoth.dbo.day_rec(hotel_date, flag,rfx_room,njf_room,self_room,rentable_room,sale_room,sale_grp_room,free_room,book_room,book_grp_room,noshow_room,checkin_room,checkout_room,tol_guest,grp_guest,free_guest,leave_guest,unexptd_leave,checkin_gst,walkin_gst,checkout_gst,balance,gst_debit,nogst_debit,gst_credit,nogst_credit,hotel_incomes,rent_incomes,rmsvc_incomes,day_incomes,drsvc_incomes,clock_incomes,csvc_incomes,hotel_credit,hotel_city,hotel_pay,cancel_room) ");
				builder.append("VALUES ('");
				builder.append(hotelDate);
				builder.append("', 1, ");
				builder.append(rfx_room);
				builder.append(",");
				builder.append(njf_room);
				builder.append(",");
				builder.append(self_room);
				builder.append(",");
				builder.append(rentable_room);
				builder.append(",");
				builder.append(sale_room);
				builder.append(",");
				builder.append(sale_grp_room);
				builder.append(",");
				builder.append(free_room);
				builder.append(",");
				builder.append(book_room);
				builder.append(",");
				builder.append(book_grp_room);
				builder.append(",");
				builder.append(noshow_room);
				builder.append(",");
				builder.append(checkin_room);
				builder.append(",");
				builder.append(checkout_room);
				builder.append(",");
				builder.append(tol_guest);
				builder.append(",");
				builder.append(grp_guest);
				builder.append(",");
				builder.append(free_guest);
				builder.append(",");
				builder.append(leave_guest);
				builder.append(",");
				builder.append(unexptd_leave);
				builder.append(",");
				builder.append(checkin_gst);
				builder.append(",");
				builder.append(walkin_gst);
				builder.append(",");
				builder.append(checkout_gst);
				builder.append(",");
				builder.append(balance);
				builder.append(",");
				builder.append(gst_debit);
				builder.append(",");
				builder.append(nogst_debit);
				builder.append(",");
				builder.append(gst_credit);
				builder.append(",");
				builder.append(nogst_credit);
				builder.append(",");
				builder.append(hotel_incomes);
				builder.append(",");
				builder.append(rent_incomes);
				builder.append(",");
				builder.append(rmsvc_incomes);
				builder.append(",");
				builder.append(day_incomes);
				builder.append(",");
				builder.append(drsvc_incomes);
				builder.append(",");
				builder.append(clock_incomes);
				builder.append(",");
				builder.append(csvc_incomes);
				builder.append(",");
				builder.append(hotel_credit);
				builder.append(",");
				builder.append(hotel_city);
				builder.append(", 0, 0)");

				String insertSql = builder.toString();
				jdbcTemplate.update(insertSql);
				transactionTemplate.execute(new EndStep(operId, currStep, currSubStep, hotelDate, 1, "bills, day_rec", new Gson().toJson("")));
				
				
				//查询月起始日期
				String monthStartDay = jdbcTemplate.queryForObject("SELECT CONVERT (VARCHAR (4), DATEPART (day, a.para3)) FROM parameter a WHERE a.ID = 12", String.class);
				String currDay = hotelDate.substring(8, 10);
				//维修房数量
				String rfx_room_m = "0";
				//冻结房数量
				String njf_room_m = "0";
				//自用房数量
				String self_room_m = "0";
				//团队房数量
				String sale_grp_room_m = "0";
				//免费房数量
				String free_room_m = "0";
				//散客房数量
				String checkout_gst_m = "0";
				//可买房数量
				String rentable_room_m = "0";
				//sale_room
				String sale_room_m = "0";
				//预定房数量
				String book_room_m = "0";
				//团体预定房数量
				String book_grp_room_m = "0";
				//未到房数量
				String noshow_room_m = "0";
				//离店房数量
				String checkout_room_m = "0";
				//入住房数量
				String checkin_room_m = "0";
				//在住人数
				String tol_guest_m = "0";
				//团员人数
				String grp_guest_m = "0";
				//免费人数
				String free_guest_m = "0";
				//离店人数
				String leave_guest_m = "0";
				//提前离店人数
				String unexptd_leave_m = "0";
				//入住人数
				String checkin_gst_m = "0";
				//散客人数
				String walkin_gst_m = "0";
				//前台挂帐余额
				String balance_m = "0";
				//挂房帐
				String gst_debit_m = "0";
				//挂非住客总额
				String nogst_debit_m = "0";
				//结房帐总额
				String gst_credit_m = "0";
				//挂非住客总额
				String nogst_credit_m = "0";
				//酒店总营业收入
				String hotel_incomes_m = "0";
				//房租营业收入
				String rent_incomes_m = "0";
				//房租服务费收入
				String rmsvc_incomes_m = "0";
				//日租半日租收入
				String day_incomes_m = "0";
				//日租半日租服务费收入
				String drsvc_incomes_m = "0";
				//钟点租营业额
				String clock_incomes_m = "0";
				//钟点租服务费
				String csvc_incomes_m = "0";
				//酒店收款
				String hotel_credit_m = "0";
				//AR挂帐
				String hotel_city_m = "0";
				if(monthStartDay.equals(currDay)){
					rfx_room_m = rfx_room;
					njf_room_m = njf_room;
					self_room_m = self_room;
					sale_grp_room_m = sale_grp_room;
					free_room_m = free_room;
					checkout_gst_m = checkout_gst;
					rentable_room_m = rentable_room;
					sale_room_m = sale_room;
					book_room_m = book_room;
					book_grp_room_m = book_grp_room;
					noshow_room_m = noshow_room;
					checkout_room_m = checkout_room;
					checkin_room_m = checkin_room;
					tol_guest_m = tol_guest;
					grp_guest_m = grp_guest;
					free_guest_m = free_guest;
					leave_guest_m = leave_guest;
					unexptd_leave_m = unexptd_leave;
					checkin_gst_m = checkin_gst;
					walkin_gst_m = walkin_gst;
					balance_m = balance;
					gst_debit_m = gst_debit;
					nogst_debit_m = nogst_debit;
					gst_credit_m = gst_credit;
					nogst_credit_m = nogst_credit;
					hotel_incomes_m = hotel_incomes;
					rent_incomes_m = rent_incomes;
					rmsvc_incomes_m = rmsvc_incomes;
					day_incomes_m = day_incomes;
					drsvc_incomes_m = drsvc_incomes;
					clock_incomes_m = clock_incomes;
					csvc_incomes_m = csvc_incomes;
					hotel_credit_m = hotel_credit;
					hotel_city_m = hotel_city;
				}else{
					List<Map<String, Object>> recList = jdbcTemplate.queryForList("select * from day_rec where flag = 2 and datediff(day, hotel_date, dateadd(day, -1, '"+hotelDate+"')) = 0");
					if(recList.size() == 0){
						rfx_room_m = rfx_room;
						njf_room_m = njf_room;
						self_room_m = self_room;
						sale_grp_room_m = sale_grp_room;
						free_room_m = free_room;
						checkout_gst_m = checkout_gst;
						rentable_room_m = rentable_room;
						sale_room_m = sale_room;
						book_room_m = book_room;
						book_grp_room_m = book_grp_room;
						noshow_room_m = noshow_room;
						checkout_room_m = checkout_room;
						checkin_room_m = checkin_room;
						tol_guest_m = tol_guest;
						grp_guest_m = grp_guest;
						free_guest_m = free_guest;
						leave_guest_m = leave_guest;
						unexptd_leave_m = unexptd_leave;
						checkin_gst_m = checkin_gst;
						walkin_gst_m = walkin_gst;
						balance_m = balance;
						gst_debit_m = gst_debit;
						nogst_debit_m = nogst_debit;
						gst_credit_m = gst_credit;
						nogst_credit_m = nogst_credit;
						hotel_incomes_m = hotel_incomes;
						rent_incomes_m = rent_incomes;
						rmsvc_incomes_m = rmsvc_incomes;
						day_incomes_m = day_incomes;
						drsvc_incomes_m = drsvc_incomes;
						clock_incomes_m = clock_incomes;
						csvc_incomes_m = csvc_incomes;
						hotel_credit_m = hotel_credit;
						hotel_city_m = hotel_city;
					}else{
						Map<String, Object> recMap = recList.get(0);
						int temp = Integer.parseInt(recMap.get("rfx_room").toString()) + Integer.parseInt(rfx_room);
						rfx_room_m = Integer.toString(temp);
						temp = Integer.parseInt(recMap.get("njf_room").toString()) + Integer.parseInt(njf_room);
						njf_room_m = Integer.toString(temp);
						temp = Integer.parseInt(recMap.get("self_room").toString()) + Integer.parseInt(self_room);
						self_room_m = Integer.toString(temp);
						temp = Integer.parseInt(recMap.get("sale_grp_room").toString()) + Integer.parseInt(sale_grp_room);
						sale_grp_room_m = Integer.toString(temp);
						temp = Integer.parseInt(recMap.get("free_room").toString()) + Integer.parseInt(free_room);
						free_room_m = Integer.toString(temp);
						temp = Integer.parseInt(recMap.get("checkout_gst").toString()) + Integer.parseInt(checkout_gst);
						checkout_gst_m = Integer.toString(temp);
						temp = Integer.parseInt(recMap.get("rentable_room").toString()) + Integer.parseInt(rentable_room);
						rentable_room_m = Integer.toString(temp);
						temp = Integer.parseInt(recMap.get("sale_room").toString()) + Integer.parseInt(sale_room);
						sale_room_m = Integer.toString(temp);
						temp = Integer.parseInt(recMap.get("book_room").toString()) + Integer.parseInt(book_room);
						book_room_m = Integer.toString(temp);
						temp = Integer.parseInt(recMap.get("book_grp_room").toString()) + Integer.parseInt(book_grp_room);
						book_grp_room_m = Integer.toString(temp);
						temp = Integer.parseInt(recMap.get("noshow_room").toString()) + Integer.parseInt(noshow_room);
						noshow_room_m = Integer.toString(temp);
						temp = Integer.parseInt(recMap.get("checkout_room").toString()) + Integer.parseInt(checkout_room);
						checkout_room_m = Integer.toString(temp);
						temp = Integer.parseInt(recMap.get("checkin_room").toString()) + Integer.parseInt(checkin_room);
						checkin_room_m = Integer.toString(temp);
						temp = Integer.parseInt(recMap.get("tol_guest").toString()) + Integer.parseInt(tol_guest);
						tol_guest_m = Integer.toString(temp);
						temp = Integer.parseInt(recMap.get("grp_guest").toString()) + Integer.parseInt(grp_guest);
						grp_guest_m = Integer.toString(temp);
						temp = Integer.parseInt(recMap.get("free_guest").toString()) + Integer.parseInt(free_guest);
						free_guest_m = Integer.toString(temp);
						temp = Integer.parseInt(recMap.get("leave_guest").toString()) + Integer.parseInt(leave_guest);
						leave_guest_m = Integer.toString(temp);
						temp = Integer.parseInt(recMap.get("unexptd_leave").toString()) + Integer.parseInt(unexptd_leave);
						unexptd_leave_m = Integer.toString(temp);
						temp = Integer.parseInt(recMap.get("checkin_gst").toString()) + Integer.parseInt(checkin_gst);
						checkin_gst_m = Integer.toString(temp);
						temp = Integer.parseInt(recMap.get("walkin_gst").toString()) + Integer.parseInt(walkin_gst);
						walkin_gst_m = Integer.toString(temp);
						double tempD = Double.parseDouble(recMap.get("balance").toString()) + Double.parseDouble(balance);
						balance_m = Double.toString(tempD);
						tempD = Double.parseDouble(recMap.get("gst_debit").toString()) + Double.parseDouble(gst_debit);
						gst_debit_m = Double.toString(tempD);
						tempD = Double.parseDouble(recMap.get("nogst_debit").toString()) + Double.parseDouble(nogst_debit);
						nogst_debit_m = Double.toString(tempD);
						tempD = Double.parseDouble(recMap.get("gst_credit").toString()) + Double.parseDouble(gst_credit);
						gst_credit_m = Double.toString(tempD);
						tempD = Double.parseDouble(recMap.get("nogst_credit").toString()) + Double.parseDouble(nogst_credit);
						nogst_credit_m = Double.toString(tempD);
						tempD = Double.parseDouble(recMap.get("hotel_incomes").toString()) + Double.parseDouble(hotel_incomes);
						hotel_incomes_m = Double.toString(tempD);
						tempD = Double.parseDouble(recMap.get("rent_incomes").toString()) + Double.parseDouble(rent_incomes);
						rent_incomes_m = Double.toString(tempD);
						tempD = Double.parseDouble(recMap.get("rmsvc_incomes").toString()) + Double.parseDouble(rmsvc_incomes);
						rmsvc_incomes_m = Double.toString(tempD);
						tempD = Double.parseDouble(recMap.get("day_incomes").toString()) + Double.parseDouble(day_incomes);
						day_incomes_m = Double.toString(tempD);
						tempD = Double.parseDouble(recMap.get("drsvc_incomes").toString()) + Double.parseDouble(drsvc_incomes);
						drsvc_incomes_m = Double.toString(tempD);
						tempD = Double.parseDouble(recMap.get("clock_incomes").toString()) + Double.parseDouble(clock_incomes);
						clock_incomes_m = Double.toString(tempD);
						tempD = Double.parseDouble(recMap.get("csvc_incomes").toString()) + Double.parseDouble(csvc_incomes);
						csvc_incomes_m = Double.toString(tempD);
						tempD = Double.parseDouble(recMap.get("hotel_credit").toString()) + Double.parseDouble(hotel_credit);
						hotel_credit_m = Double.toString(tempD);
						tempD = Double.parseDouble(recMap.get("hotel_city").toString()) + Double.parseDouble(hotel_city);
						hotel_city_m = Double.toString(tempD);
					}
				}
				
				//构建增加月统计sql
				builder = new StringBuilder("");
				builder.append("INSERT INTO mammoth.dbo.day_rec(hotel_date, flag,rfx_room,njf_room,self_room,rentable_room,sale_room,sale_grp_room,free_room,book_room,book_grp_room,noshow_room,checkin_room,checkout_room,tol_guest,grp_guest,free_guest,leave_guest,unexptd_leave,checkin_gst,walkin_gst,checkout_gst,balance,gst_debit,nogst_debit,gst_credit,nogst_credit,hotel_incomes,rent_incomes,rmsvc_incomes,day_incomes,drsvc_incomes,clock_incomes,csvc_incomes,hotel_credit,hotel_city,hotel_pay,cancel_room) ");
				builder.append("VALUES ('");
				builder.append(hotelDate);
				builder.append("', 2, ");
				builder.append(rfx_room_m);
				builder.append(",");
				builder.append(njf_room_m);
				builder.append(",");
				builder.append(self_room_m);
				builder.append(",");
				builder.append(rentable_room_m);
				builder.append(",");
				builder.append(sale_room_m);
				builder.append(",");
				builder.append(sale_grp_room_m);
				builder.append(",");
				builder.append(free_room_m);
				builder.append(",");
				builder.append(book_room_m);
				builder.append(",");
				builder.append(book_grp_room_m);
				builder.append(",");
				builder.append(noshow_room_m);
				builder.append(",");
				builder.append(checkin_room_m);
				builder.append(",");
				builder.append(checkout_room_m);
				builder.append(",");
				builder.append(tol_guest_m);
				builder.append(",");
				builder.append(grp_guest_m);
				builder.append(",");
				builder.append(free_guest_m);
				builder.append(",");
				builder.append(leave_guest_m);
				builder.append(",");
				builder.append(unexptd_leave_m);
				builder.append(",");
				builder.append(checkin_gst_m);
				builder.append(",");
				builder.append(walkin_gst_m);
				builder.append(",");
				builder.append(checkout_gst_m);
				builder.append(",");
				builder.append(balance_m);
				builder.append(",");
				builder.append(gst_debit_m);
				builder.append(",");
				builder.append(nogst_debit_m);
				builder.append(",");
				builder.append(gst_credit_m);
				builder.append(",");
				builder.append(nogst_credit_m);
				builder.append(",");
				builder.append(hotel_incomes_m);
				builder.append(",");
				builder.append(rent_incomes_m);
				builder.append(",");
				builder.append(rmsvc_incomes_m);
				builder.append(",");
				builder.append(day_incomes_m);
				builder.append(",");
				builder.append(drsvc_incomes_m);
				builder.append(",");
				builder.append(clock_incomes_m);
				builder.append(",");
				builder.append(csvc_incomes_m);
				builder.append(",");
				builder.append(hotel_credit_m);
				builder.append(",");
				builder.append(hotel_city_m);
				builder.append(", 0, 0)");

				String insertSql_m = builder.toString();
				jdbcTemplate.update(insertSql_m);
				transactionTemplate.execute(new EndStep(operId, currStep, currSubStep, hotelDate, 2, "bills, day_rec", new Gson().toJson("")));
				
				
				////查询年起始日期
				String yearStartDay = jdbcTemplate.queryForObject("SELECT CONVERT (VARCHAR (4), DATEPART (day, a.para3)) FROM parameter a WHERE a.ID = 13", String.class);
				String yearStartMonth = jdbcTemplate.queryForObject("SELECT CONVERT (VARCHAR (4), DATEPART (day, a.para3)) FROM parameter a WHERE a.ID = 13", String.class);
				currDay = hotelDate.substring(8, 10);
				String currMonth = hotelDate.substring(5, 7);
				//维修房数量
				String rfx_room_y = "0";
				//冻结房数量
				String njf_room_y = "0";
				//自用房数量
				String self_room_y = "0";
				//团队房数量
				String sale_grp_room_y = "0";
				//免费房数量
				String free_room_y = "0";
				//散客房数量
				String checkout_gst_y = "0";
				//可买房数量
				String rentable_room_y = "0";
				//sale_room
				String sale_room_y = "0";
				//预定房数量
				String book_room_y = "0";
				//团体预定房数量
				String book_grp_room_y = "0";
				//未到房数量
				String noshow_room_y = "0";
				//离店房数量
				String checkout_room_y = "0";
				//入住房数量
				String checkin_room_y = "0";
				//在住人数
				String tol_guest_y = "0";
				//团员人数
				String grp_guest_y = "0";
				//免费人数
				String free_guest_y = "0";
				//离店人数
				String leave_guest_y = "0";
				//提前离店人数
				String unexptd_leave_y = "0";
				//入住人数
				String checkin_gst_y = "0";
				//散客人数
				String walkin_gst_y = "0";
				//前台挂帐余额
				String balance_y = "0";
				//挂房帐
				String gst_debit_y = "0";
				//挂非住客总额
				String nogst_debit_y = "0";
				//结房帐总额
				String gst_credit_y = "0";
				//挂非住客总额
				String nogst_credit_y = "0";
				//酒店总营业收入
				String hotel_incomes_y = "0";
				//房租营业收入
				String rent_incomes_y = "0";
				//房租服务费收入
				String rmsvc_incomes_y = "0";
				//日租半日租收入
				String day_incomes_y = "0";
				//日租半日租服务费收入
				String drsvc_incomes_y = "0";
				//钟点租营业额
				String clock_incomes_y = "0";
				//钟点租服务费
				String csvc_incomes_y = "0";
				//酒店收款
				String hotel_credit_y = "0";
				//AR挂帐
				String hotel_city_y = "0";
				if(yearStartMonth.equals(currMonth)){
					if(yearStartDay.equals(currDay)){
						rfx_room_y = rfx_room;
						njf_room_y = njf_room;
						self_room_y = self_room;
						sale_grp_room_y = sale_grp_room;
						free_room_y = free_room;
						checkout_gst_y = checkout_gst;
						rentable_room_y = rentable_room;
						sale_room_y = sale_room;
						book_room_y = book_room;
						book_grp_room_y = book_grp_room;
						noshow_room_y = noshow_room;
						checkout_room_y = checkout_room;
						checkin_room_y = checkin_room;
						tol_guest_y = tol_guest;
						grp_guest_y = grp_guest;
						free_guest_y = free_guest;
						leave_guest_y = leave_guest;
						unexptd_leave_y = unexptd_leave;
						checkin_gst_y = checkin_gst;
						walkin_gst_y = walkin_gst;
						balance_y = balance;
						gst_debit_y = gst_debit;
						nogst_debit_y = nogst_debit;
						gst_credit_y = gst_credit;
						nogst_credit_y = nogst_credit;
						hotel_incomes_y = hotel_incomes;
						rent_incomes_y = rent_incomes;
						rmsvc_incomes_y = rmsvc_incomes;
						day_incomes_y = day_incomes;
						drsvc_incomes_y = drsvc_incomes;
						clock_incomes_y = clock_incomes;
						csvc_incomes_y = csvc_incomes;
						hotel_credit_y = hotel_credit;
						hotel_city_y = hotel_city;
					}else{
						List<Map<String, Object>> recList = jdbcTemplate.queryForList("select * from day_rec where flag = 2 datediff(day, hotel_date, dateadd(day, -1, '"+hotelDate+"'))");
						if(recList.size() == 0){
							rfx_room_y = rfx_room;
							njf_room_y = njf_room;
							self_room_y = self_room;
							sale_grp_room_y = sale_grp_room;
							free_room_y = free_room;
							checkout_gst_y = checkout_gst;
							rentable_room_y = rentable_room;
							sale_room_y = sale_room;
							book_room_y = book_room;
							book_grp_room_y = book_grp_room;
							noshow_room_y = noshow_room;
							checkout_room_y = checkout_room;
							checkin_room_y = checkin_room;
							tol_guest_y = tol_guest;
							grp_guest_y = grp_guest;
							free_guest_y = free_guest;
							leave_guest_y = leave_guest;
							unexptd_leave_y = unexptd_leave;
							checkin_gst_y = checkin_gst;
							walkin_gst_y = walkin_gst;
							balance_y = balance;
							gst_debit_y = gst_debit;
							nogst_debit_y = nogst_debit;
							gst_credit_y = gst_credit;
							nogst_credit_y = nogst_credit;
							hotel_incomes_y = hotel_incomes;
							rent_incomes_y = rent_incomes;
							rmsvc_incomes_y = rmsvc_incomes;
							day_incomes_y = day_incomes;
							drsvc_incomes_y = drsvc_incomes;
							clock_incomes_y = clock_incomes;
							csvc_incomes_y = csvc_incomes;
							hotel_credit_y = hotel_credit;
							hotel_city_y = hotel_city;
						}else{
							Map<String, Object> recMap = recList.get(0);
							int temp = Integer.parseInt(recMap.get("rfx_room").toString()) + Integer.parseInt(rfx_room);
							rfx_room_y = Integer.toString(temp);
							temp = Integer.parseInt(recMap.get("njf_room").toString()) + Integer.parseInt(njf_room);
							njf_room_y = Integer.toString(temp);
							temp = Integer.parseInt(recMap.get("self_room").toString()) + Integer.parseInt(self_room);
							self_room_y = Integer.toString(temp);
							temp = Integer.parseInt(recMap.get("sale_grp_room").toString()) + Integer.parseInt(sale_grp_room);
							sale_grp_room_y = Integer.toString(temp);
							temp = Integer.parseInt(recMap.get("free_room").toString()) + Integer.parseInt(free_room);
							free_room_y = Integer.toString(temp);
							temp = Integer.parseInt(recMap.get("checkout_gst").toString()) + Integer.parseInt(checkout_gst);
							checkout_gst_y = Integer.toString(temp);
							temp = Integer.parseInt(recMap.get("rentable_room").toString()) + Integer.parseInt(rentable_room);
							rentable_room_y = Integer.toString(temp);
							temp = Integer.parseInt(recMap.get("sale_room").toString()) + Integer.parseInt(sale_room);
							sale_room_y = Integer.toString(temp);
							temp = Integer.parseInt(recMap.get("book_room").toString()) + Integer.parseInt(book_room);
							book_room_y = Integer.toString(temp);
							temp = Integer.parseInt(recMap.get("book_grp_room").toString()) + Integer.parseInt(book_grp_room);
							book_grp_room_y = Integer.toString(temp);
							temp = Integer.parseInt(recMap.get("noshow_room").toString()) + Integer.parseInt(noshow_room);
							noshow_room_y = Integer.toString(temp);
							temp = Integer.parseInt(recMap.get("checkout_room").toString()) + Integer.parseInt(checkout_room);
							checkout_room_y = Integer.toString(temp);
							temp = Integer.parseInt(recMap.get("checkin_room").toString()) + Integer.parseInt(checkin_room);
							checkin_room_y = Integer.toString(temp);
							temp = Integer.parseInt(recMap.get("tol_guest").toString()) + Integer.parseInt(tol_guest);
							tol_guest_y = Integer.toString(temp);
							temp = Integer.parseInt(recMap.get("grp_guest").toString()) + Integer.parseInt(grp_guest);
							grp_guest_y = Integer.toString(temp);
							temp = Integer.parseInt(recMap.get("free_guest").toString()) + Integer.parseInt(free_guest);
							free_guest_y = Integer.toString(temp);
							temp = Integer.parseInt(recMap.get("leave_guest").toString()) + Integer.parseInt(leave_guest);
							leave_guest_y = Integer.toString(temp);
							temp = Integer.parseInt(recMap.get("unexptd_leave").toString()) + Integer.parseInt(unexptd_leave);
							unexptd_leave_y = Integer.toString(temp);
							temp = Integer.parseInt(recMap.get("checkin_gst").toString()) + Integer.parseInt(checkin_gst);
							checkin_gst_y = Integer.toString(temp);
							temp = Integer.parseInt(recMap.get("walkin_gst").toString()) + Integer.parseInt(walkin_gst);
							walkin_gst_y = Integer.toString(temp);
							double tempD = Double.parseDouble(recMap.get("balance").toString()) + Double.parseDouble(balance);
							balance_y = Double.toString(tempD);
							tempD = Double.parseDouble(recMap.get("gst_debit").toString()) + Double.parseDouble(gst_debit);
							gst_debit_y = Double.toString(tempD);
							tempD = Double.parseDouble(recMap.get("nogst_debit").toString()) + Double.parseDouble(nogst_debit);
							nogst_debit_y = Double.toString(tempD);
							tempD = Double.parseDouble(recMap.get("gst_credit").toString()) + Double.parseDouble(gst_credit);
							gst_credit_y = Double.toString(tempD);
							tempD = Double.parseDouble(recMap.get("nogst_credit").toString()) + Double.parseDouble(nogst_credit);
							nogst_credit_y = Double.toString(tempD);
							tempD = Double.parseDouble(recMap.get("hotel_incomes").toString()) + Double.parseDouble(hotel_incomes);
							hotel_incomes_y = Double.toString(tempD);
							tempD = Double.parseDouble(recMap.get("rent_incomes").toString()) + Double.parseDouble(rent_incomes);
							rent_incomes_y = Double.toString(tempD);
							tempD = Double.parseDouble(recMap.get("rmsvc_incomes").toString()) + Double.parseDouble(rmsvc_incomes);
							rmsvc_incomes_y = Double.toString(tempD);
							tempD = Double.parseDouble(recMap.get("day_incomes").toString()) + Double.parseDouble(day_incomes);
							day_incomes_y = Double.toString(tempD);
							tempD = Double.parseDouble(recMap.get("drsvc_incomes").toString()) + Double.parseDouble(drsvc_incomes);
							drsvc_incomes_y = Double.toString(tempD);
							tempD = Double.parseDouble(recMap.get("clock_incomes").toString()) + Double.parseDouble(clock_incomes);
							clock_incomes_y = Double.toString(tempD);
							tempD = Double.parseDouble(recMap.get("csvc_incomes").toString()) + Double.parseDouble(csvc_incomes);
							csvc_incomes_y = Double.toString(tempD);
							tempD = Double.parseDouble(recMap.get("hotel_credit").toString()) + Double.parseDouble(hotel_credit);
							hotel_credit_y = Double.toString(tempD);
							tempD = Double.parseDouble(recMap.get("hotel_city").toString()) + Double.parseDouble(hotel_city);
							hotel_city_y = Double.toString(tempD);
						}
					}
				}else{
					List<Map<String, Object>> recList = jdbcTemplate.queryForList("select * from day_rec where flag = 3 and datediff(day, hotel_date, dateadd(day, -1, '"+hotelDate+"')) = 0");
					if(recList.size() == 0){
						rfx_room_y = rfx_room;
						njf_room_y = njf_room;
						self_room_y = self_room;
						sale_grp_room_y = sale_grp_room;
						free_room_y = free_room;
						checkout_gst_y = checkout_gst;
						rentable_room_y = rentable_room;
						sale_room_y = sale_room;
						book_room_y = book_room;
						book_grp_room_y = book_grp_room;
						noshow_room_y = noshow_room;
						checkout_room_y = checkout_room;
						checkin_room_y = checkin_room;
						tol_guest_y = tol_guest;
						grp_guest_y = grp_guest;
						free_guest_y = free_guest;
						leave_guest_y = leave_guest;
						unexptd_leave_y = unexptd_leave;
						checkin_gst_y = checkin_gst;
						walkin_gst_y = walkin_gst;
						balance_y = balance;
						gst_debit_y = gst_debit;
						nogst_debit_y = nogst_debit;
						gst_credit_y = gst_credit;
						nogst_credit_y = nogst_credit;
						hotel_incomes_y = hotel_incomes;
						rent_incomes_y = rent_incomes;
						rmsvc_incomes_y = rmsvc_incomes;
						day_incomes_y = day_incomes;
						drsvc_incomes_y = drsvc_incomes;
						clock_incomes_y = clock_incomes;
						csvc_incomes_y = csvc_incomes;
						hotel_credit_y = hotel_credit;
						hotel_city_y = hotel_city;
					}else{
						Map<String, Object> recMap = recList.get(0);
						int temp = Integer.parseInt(recMap.get("rfx_room").toString()) + Integer.parseInt(rfx_room);
						rfx_room_y = Integer.toString(temp);
						temp = Integer.parseInt(recMap.get("njf_room").toString()) + Integer.parseInt(njf_room);
						njf_room_y = Integer.toString(temp);
						temp = Integer.parseInt(recMap.get("self_room").toString()) + Integer.parseInt(self_room);
						self_room_y = Integer.toString(temp);
						temp = Integer.parseInt(recMap.get("sale_grp_room").toString()) + Integer.parseInt(sale_grp_room);
						sale_grp_room_y = Integer.toString(temp);
						temp = Integer.parseInt(recMap.get("free_room").toString()) + Integer.parseInt(free_room);
						free_room_y = Integer.toString(temp);
						temp = Integer.parseInt(recMap.get("checkout_gst").toString()) + Integer.parseInt(checkout_gst);
						checkout_gst_y = Integer.toString(temp);
						temp = Integer.parseInt(recMap.get("rentable_room").toString()) + Integer.parseInt(rentable_room);
						rentable_room_y = Integer.toString(temp);
						temp = Integer.parseInt(recMap.get("sale_room").toString()) + Integer.parseInt(sale_room);
						sale_room_y = Integer.toString(temp);
						temp = Integer.parseInt(recMap.get("book_room").toString()) + Integer.parseInt(book_room);
						book_room_y = Integer.toString(temp);
						temp = Integer.parseInt(recMap.get("book_grp_room").toString()) + Integer.parseInt(book_grp_room);
						book_grp_room_y = Integer.toString(temp);
						temp = Integer.parseInt(recMap.get("noshow_room").toString()) + Integer.parseInt(noshow_room);
						noshow_room_y = Integer.toString(temp);
						temp = Integer.parseInt(recMap.get("checkout_room").toString()) + Integer.parseInt(checkout_room);
						checkout_room_y = Integer.toString(temp);
						temp = Integer.parseInt(recMap.get("checkin_room").toString()) + Integer.parseInt(checkin_room);
						checkin_room_y = Integer.toString(temp);
						temp = Integer.parseInt(recMap.get("tol_guest").toString()) + Integer.parseInt(tol_guest);
						tol_guest_y = Integer.toString(temp);
						temp = Integer.parseInt(recMap.get("grp_guest").toString()) + Integer.parseInt(grp_guest);
						grp_guest_y = Integer.toString(temp);
						temp = Integer.parseInt(recMap.get("free_guest").toString()) + Integer.parseInt(free_guest);
						free_guest_y = Integer.toString(temp);
						temp = Integer.parseInt(recMap.get("leave_guest").toString()) + Integer.parseInt(leave_guest);
						leave_guest_y = Integer.toString(temp);
						temp = Integer.parseInt(recMap.get("unexptd_leave").toString()) + Integer.parseInt(unexptd_leave);
						unexptd_leave_y = Integer.toString(temp);
						temp = Integer.parseInt(recMap.get("checkin_gst").toString()) + Integer.parseInt(checkin_gst);
						checkin_gst_y = Integer.toString(temp);
						temp = Integer.parseInt(recMap.get("walkin_gst").toString()) + Integer.parseInt(walkin_gst);
						walkin_gst_y = Integer.toString(temp);
						double tempD = Double.parseDouble(recMap.get("balance").toString()) + Double.parseDouble(balance);
						balance_y = Double.toString(tempD);
						tempD = Double.parseDouble(recMap.get("gst_debit").toString()) + Double.parseDouble(gst_debit);
						gst_debit_y = Double.toString(tempD);
						tempD = Double.parseDouble(recMap.get("nogst_debit").toString()) + Double.parseDouble(nogst_debit);
						nogst_debit_y = Double.toString(tempD);
						tempD = Double.parseDouble(recMap.get("gst_credit").toString()) + Double.parseDouble(gst_credit);
						gst_credit_y = Double.toString(tempD);
						tempD = Double.parseDouble(recMap.get("nogst_credit").toString()) + Double.parseDouble(nogst_credit);
						nogst_credit_y = Double.toString(tempD);
						tempD = Double.parseDouble(recMap.get("hotel_incomes").toString()) + Double.parseDouble(hotel_incomes);
						hotel_incomes_y = Double.toString(tempD);
						tempD = Double.parseDouble(recMap.get("rent_incomes").toString()) + Double.parseDouble(rent_incomes);
						rent_incomes_y = Double.toString(tempD);
						tempD = Double.parseDouble(recMap.get("rmsvc_incomes").toString()) + Double.parseDouble(rmsvc_incomes);
						rmsvc_incomes_y = Double.toString(tempD);
						tempD = Double.parseDouble(recMap.get("day_incomes").toString()) + Double.parseDouble(day_incomes);
						day_incomes_y = Double.toString(tempD);
						tempD = Double.parseDouble(recMap.get("drsvc_incomes").toString()) + Double.parseDouble(drsvc_incomes);
						drsvc_incomes_y = Double.toString(tempD);
						tempD = Double.parseDouble(recMap.get("clock_incomes").toString()) + Double.parseDouble(clock_incomes);
						clock_incomes_y = Double.toString(tempD);
						tempD = Double.parseDouble(recMap.get("csvc_incomes").toString()) + Double.parseDouble(csvc_incomes);
						csvc_incomes_y = Double.toString(tempD);
						tempD = Double.parseDouble(recMap.get("hotel_credit").toString()) + Double.parseDouble(hotel_credit);
						hotel_credit_y = Double.toString(tempD);
						tempD = Double.parseDouble(recMap.get("hotel_city").toString()) + Double.parseDouble(hotel_city);
						hotel_city_y = Double.toString(tempD);
					}
				}
				
				//构建增加月统计sql
				builder = new StringBuilder("");
				builder.append("INSERT INTO mammoth.dbo.day_rec(hotel_date, flag,rfx_room,njf_room,self_room,rentable_room,sale_room,sale_grp_room,free_room,book_room,book_grp_room,noshow_room,checkin_room,checkout_room,tol_guest,grp_guest,free_guest,leave_guest,unexptd_leave,checkin_gst,walkin_gst,checkout_gst,balance,gst_debit,nogst_debit,gst_credit,nogst_credit,hotel_incomes,rent_incomes,rmsvc_incomes,day_incomes,drsvc_incomes,clock_incomes,csvc_incomes,hotel_credit,hotel_city,hotel_pay,cancel_room) ");
				builder.append("VALUES ('");
				builder.append(hotelDate);
				builder.append("', 3, ");
				builder.append(rfx_room_y);
				builder.append(",");
				builder.append(njf_room_y);
				builder.append(",");
				builder.append(self_room_y);
				builder.append(",");
				builder.append(rentable_room_y);
				builder.append(",");
				builder.append(sale_room_y);
				builder.append(",");
				builder.append(sale_grp_room_y);
				builder.append(",");
				builder.append(free_room_y);
				builder.append(",");
				builder.append(book_room_y);
				builder.append(",");
				builder.append(book_grp_room_y);
				builder.append(",");
				builder.append(noshow_room_y);
				builder.append(",");
				builder.append(checkin_room_y);
				builder.append(",");
				builder.append(checkout_room_y);
				builder.append(",");
				builder.append(tol_guest_y);
				builder.append(",");
				builder.append(grp_guest_y);
				builder.append(",");
				builder.append(free_guest_y);
				builder.append(",");
				builder.append(leave_guest_y);
				builder.append(",");
				builder.append(unexptd_leave_y);
				builder.append(",");
				builder.append(checkin_gst_y);
				builder.append(",");
				builder.append(walkin_gst_y);
				builder.append(",");
				builder.append(checkout_gst_y);
				builder.append(",");
				builder.append(balance_y);
				builder.append(",");
				builder.append(gst_debit_y);
				builder.append(",");
				builder.append(nogst_debit_y);
				builder.append(",");
				builder.append(gst_credit_y);
				builder.append(",");
				builder.append(nogst_credit_y);
				builder.append(",");
				builder.append(hotel_incomes_y);
				builder.append(",");
				builder.append(rent_incomes_y);
				builder.append(",");
				builder.append(rmsvc_incomes_y);
				builder.append(",");
				builder.append(day_incomes_y);
				builder.append(",");
				builder.append(drsvc_incomes_y);
				builder.append(",");
				builder.append(clock_incomes_y);
				builder.append(",");
				builder.append(csvc_incomes_y);
				builder.append(",");
				builder.append(hotel_credit_y);
				builder.append(",");
				builder.append(hotel_city_y);
				builder.append(", 0, 0)");

				String insertSql_y = builder.toString();
				jdbcTemplate.update(insertSql_y);
				transactionTemplate.execute(new EndStep(operId, currStep, currSubStep, hotelDate, 3, "bills, day_rec", new Gson().toJson("")));
				result.put("issuccess", "true");
			} catch (DataAccessException e) {
				e.printStackTrace();
				arg0.setRollbackOnly();
				result.put("issuccess", "false");
				result.put("msg", e.getMessage());
			}
			return result;
		}
		
	}
	
	/**
	 * 收入统计
	 * @author Administrator
	 *
	 */
	class RevenueStatistics implements TransactionCallback<Map<String, Object>>{
		private String operId;
		private String hotelDate;
		private int currStep;
		private int currSubStep;
		private int sqlStep;
		private Map<String, Object> map;
		public RevenueStatistics(String operId, String hotelDate, int currStep, int currSubStep, int sqlStep, Map<String, Object> map){
			this.operId = operId;
			this.hotelDate = hotelDate;
			this.currStep = currStep;
			this.currSubStep = currSubStep;
			this.sqlStep = sqlStep;
			this.map = map;
		}

		@Override
		public Map<String, Object> doInTransaction(TransactionStatus arg0) {
			Map<String, Object> result = new HashMap<String, Object>();
			try {
				//取月起始日，年起始日
				String startDay = jdbcTemplate.queryForObject("SELECT CONVERT (VARCHAR (4), DATEPART (day, a.para3)) FROM parameter a WHERE a.ID = 13", String.class);
				String startMonth = jdbcTemplate.queryForObject("SELECT CONVERT (VARCHAR (4), DATEPART (day, a.para3)) FROM parameter a WHERE a.ID = 13", String.class);
				//当前月份，当前日
				String currDay = hotelDate.substring(8, 10);
				String currMonth = hotelDate.substring(5, 7);
				
				//定义数据
				String cons_id = map.get("cons_id") == null ? "*" : map.get("cons_id").toString().trim();
				String setl_id = map.get("setl_id") == null ? "*" : map.get("setl_id").toString().trim();
				String d_incomes = map.get("d_incomes") == null ? "0" : map.get("d_incomes").toString();
				String d_service = map.get("d_service") == null ? "0" : map.get("d_service").toString();
				String d_credit = map.get("d_credit") == null ? "0" : map.get("d_credit").toString();
				String m_incomes = "0";
				String m_service = "0";
				String m_credit = "0";
				String y_incomes = "0";
				String y_service = "0";
				String y_credit = "0";
				
				//取上一天的统计数据
				List<Map<String, Object>> incomsList = jdbcTemplate.queryForList("select * from day_incomes a where datediff(day, DATEADD(day, -1, '"+hotelDate+"'), a.hotel_date) = 0 and rtrim(a.cons_id) = '"+cons_id+"' and rtrim(a.setl_id) = '"+setl_id+"'");
				
				if(currDay.equals(startDay)){
					m_incomes = d_incomes;
					m_service = d_service;
					m_credit = d_credit;
				}else{
					if(incomsList.size() > 0){
						Map<String, Object> incomsMap = incomsList.get(0);
						double temp = Double.parseDouble(d_incomes) + Double.parseDouble(incomsMap.get("m_incomes").toString());
						m_incomes = Double.toString(temp);
						temp = Double.parseDouble(d_service) + Double.parseDouble(incomsMap.get("m_service").toString());
						m_service = Double.toString(temp);
						temp = Double.parseDouble(d_credit) + Double.parseDouble(incomsMap.get("m_credit").toString());
						m_credit = Double.toString(temp);
					}else{
						m_incomes = d_incomes;
						m_service = d_service;
						m_credit = d_credit;
					}
				}
				
				if(currMonth.equals(startMonth)){
					if(currDay.equals(startDay)){
						//当天是年起始日
						y_incomes = d_incomes;
						y_service = d_service;
						y_credit = d_credit;
					}else{
						if(incomsList.size() > 0){
							Map<String, Object> incomsMap = incomsList.get(0);
							double temp = Double.parseDouble(d_incomes) + Double.parseDouble(incomsMap.get("y_incomes").toString());
							y_incomes = Double.toString(temp);
							temp = Double.parseDouble(d_service) + Double.parseDouble(incomsMap.get("y_service").toString());
							y_service = Double.toString(temp);
							temp = Double.parseDouble(d_credit) + Double.parseDouble(incomsMap.get("y_credit").toString());
							y_credit = Double.toString(temp);
						}else{
							y_incomes = d_incomes;
							y_service = d_service;
							y_credit = d_credit;
						}
					}
				}else{
					if(incomsList.size() > 0){
						Map<String, Object> incomsMap = incomsList.get(0);
						double temp = Double.parseDouble(d_incomes) + Double.parseDouble(incomsMap.get("y_incomes").toString());
						y_incomes = Double.toString(temp);
						temp = Double.parseDouble(d_service) + Double.parseDouble(incomsMap.get("y_service").toString());
						y_service = Double.toString(temp);
						temp = Double.parseDouble(d_credit) + Double.parseDouble(incomsMap.get("y_credit").toString());
						y_credit = Double.toString(temp);
					}else{
						y_incomes = d_incomes;
						y_service = d_service;
						y_credit = d_credit;
					}
				}
				StringBuilder builder = new StringBuilder("");
				builder.append("INSERT INTO day_incomes(hotel_date,cons_id,setl_id,d_incomes,d_service,d_credit,m_incomes,m_service,m_credit,y_incomes,y_service,y_credit,vilhotel_id,chain_id) VALUES (");
				builder.append("'"+hotelDate+"'");
				builder.append(",'"+cons_id+"'");
				builder.append(",'"+setl_id+"'");
				builder.append(","+d_incomes);
				builder.append(","+d_service);
				builder.append(","+d_credit);
				builder.append(","+m_incomes);
				builder.append(","+m_service);
				builder.append(","+m_credit);
				builder.append(","+y_incomes);
				builder.append(","+y_service);
				builder.append(","+y_credit);
				builder.append(",null, null)");
				jdbcTemplate.update(builder.toString());
				transactionTemplate.execute(new EndStep(operId, currStep, currSubStep, hotelDate, 1, "bills, day_incomes, day_incomes1", new Gson().toJson("")));
				transactionTemplate.execute(new EndStep(operId, currStep, currSubStep, hotelDate, sqlStep, "bills, day_incomes, day_incomes1", new Gson().toJson("")));
				result.put("issuccess", "true");
			} catch (DataAccessException e) {
				e.printStackTrace();
				result.put("issuccess", "false");
				result.put("msg", e.getMessage());
			}
			return result;
		}
		
	}
	class RevenueStatistics1 implements TransactionCallback<Map<String, Object>>{
		private String operId;
		private String hotelDate;
		private int currStep;
		private int currSubStep;
		private int sqlStep;
		private Map<String, Object> map;
		public RevenueStatistics1(String operId, String hotelDate, int currStep, int currSubStep, int sqlStep, Map<String, Object> map){
			this.operId = operId;
			this.hotelDate = hotelDate;
			this.currStep = currStep;
			this.currSubStep = currSubStep;
			this.sqlStep = sqlStep;
			this.map = map;
		}

		@Override
		public Map<String, Object> doInTransaction(TransactionStatus arg0) {
			Map<String, Object> result = new HashMap<String, Object>();
			try {
				//取月起始日，年起始日
				String startDay = jdbcTemplate.queryForObject("SELECT CONVERT (VARCHAR (4), DATEPART (day, a.para3)) FROM parameter a WHERE a.ID = 13", String.class);
				String startMonth = jdbcTemplate.queryForObject("SELECT CONVERT (VARCHAR (4), DATEPART (day, a.para3)) FROM parameter a WHERE a.ID = 13", String.class);
				//当前月份，当前日
				String currDay = hotelDate.substring(8, 10);
				String currMonth = hotelDate.substring(5, 7);
				
				//定义数据
				String cons_id = map.get("cons_id") == null ? "*" : map.get("cons_id").toString().trim();
				String setl_id = map.get("setl_id") == null ? "*" : map.get("setl_id").toString().trim();
				String d_incomes = map.get("d_incomes") == null ? "0" : map.get("d_incomes").toString();
				String d_service = map.get("d_service") == null ? "0" : map.get("d_service").toString();
				String d_credit = map.get("d_credit") == null ? "0" : map.get("d_credit").toString();
				String m_incomes = "0";
				String m_service = "0";
				String m_credit = "0";
				String y_incomes = "0";
				String y_service = "0";
				String y_credit = "0";
				
				//取上一天的统计数据
				List<Map<String, Object>> incomsList = jdbcTemplate.queryForList("select * from day_incomes1 a where datediff(day, DATEADD(day, -1, '"+hotelDate+"'), a.hotel_date) = 0 and rtrim(a.cons_id) = '"+cons_id+"' and rtrim(a.setl_id) = '"+setl_id+"'");
				
				if(currDay.equals(startDay)){
					m_incomes = d_incomes;
					m_service = d_service;
					m_credit = d_credit;
				}else{
					if(incomsList.size() > 0){
						Map<String, Object> incomsMap = incomsList.get(0);
						double temp = Double.parseDouble(d_incomes) + Double.parseDouble(incomsMap.get("m_incomes").toString());
						m_incomes = Double.toString(temp);
						temp = Double.parseDouble(d_service) + Double.parseDouble(incomsMap.get("m_service").toString());
						m_service = Double.toString(temp);
						temp = Double.parseDouble(d_credit) + Double.parseDouble(incomsMap.get("m_credit").toString());
						m_credit = Double.toString(temp);
					}else{
						m_incomes = d_incomes;
						m_service = d_service;
						m_credit = d_credit;
					}
				}
				
				if(currMonth.equals(startMonth)){
					if(currDay.equals(startDay)){
						//当天是年起始日
						y_incomes = d_incomes;
						y_service = d_service;
						y_credit = d_credit;
					}else{
						if(incomsList.size() > 0){
							Map<String, Object> incomsMap = incomsList.get(0);
							double temp = Double.parseDouble(d_incomes) + Double.parseDouble(incomsMap.get("y_incomes").toString());
							y_incomes = Double.toString(temp);
							temp = Double.parseDouble(d_service) + Double.parseDouble(incomsMap.get("y_service").toString());
							y_service = Double.toString(temp);
							temp = Double.parseDouble(d_credit) + Double.parseDouble(incomsMap.get("y_credit").toString());
							y_credit = Double.toString(temp);
						}else{
							y_incomes = d_incomes;
							y_service = d_service;
							y_credit = d_credit;
						}
					}
				}else{
					if(incomsList.size() > 0){
						Map<String, Object> incomsMap = incomsList.get(0);
						double temp = Double.parseDouble(d_incomes) + Double.parseDouble(incomsMap.get("y_incomes").toString());
						y_incomes = Double.toString(temp);
						temp = Double.parseDouble(d_service) + Double.parseDouble(incomsMap.get("y_service").toString());
						y_service = Double.toString(temp);
						temp = Double.parseDouble(d_credit) + Double.parseDouble(incomsMap.get("y_credit").toString());
						y_credit = Double.toString(temp);
					}else{
						y_incomes = d_incomes;
						y_service = d_service;
						y_credit = d_credit;
					}
				}
				StringBuilder builder = new StringBuilder("");
				builder.append("INSERT INTO day_incomes1(hotel_date,cons_id,setl_id,d_incomes,d_service,d_credit,m_incomes,m_service,m_credit,y_incomes,y_service,y_credit,vilhotel_id,chain_id) VALUES (");
				builder.append("'"+hotelDate+"'");
				builder.append(",'"+cons_id+"'");
				builder.append(",'"+setl_id+"'");
				builder.append(","+d_incomes);
				builder.append(","+d_service);
				builder.append(","+d_credit);
				builder.append(","+m_incomes);
				builder.append(","+m_service);
				builder.append(","+m_credit);
				builder.append(","+y_incomes);
				builder.append(","+y_service);
				builder.append(","+y_credit);
				builder.append(",null, null)");
				jdbcTemplate.update(builder.toString());
				transactionTemplate.execute(new EndStep(operId, currStep, currSubStep, hotelDate, 1, "bills, day_incomes, day_incomes1", new Gson().toJson("")));
				
				transactionTemplate.execute(new EndStep(operId, currStep, currSubStep, hotelDate, sqlStep, "bills, day_incomes, day_incomes1", new Gson().toJson("")));
				result.put("issuccess", "true");
			} catch (DataAccessException e) {
				e.printStackTrace();
				result.put("issuccess", "false");
				result.put("msg", e.getMessage());
			}
			return result;
		}
		
	}
	
	/**
	 * 夜审第七大步：数据迁移
	 */
	public static final int CURR_STEP_DATAMOVE = 7;//夜审第七大步：数据迁移
	public static final int CURR_STEP_DATAMOVE_01 = 1;//夜审第七大步第一小步
	public static final int CURR_STEP_DATAMOVE_02 = 2;//夜审第七大步第二小步
	public static final int CURR_STEP_DATAMOVE_03 = 3;//夜审第七大步第三小步
	private Map<String, Object> dataMove(String operId, String hotelDate, int currSubStep, int sqlStep){
		Map<String, Object> result = new HashMap<String, Object>();
		//取数据保留天数
		int dataSaveNum = jdbcTemplate.queryForInt("SELECT para1 from parameter where id = 13");
		StringBuilder sql;
		switch (currSubStep) {
		case CURR_STEP_DATAMOVE_01:
			//guestdoc迁移
			sql = new StringBuilder("");
			sql.append("SELECT x.with_id, y.check_id, y.billa_id, y.billb_id, y.grp_chkid ");
			sql.append("  FROM (SELECT a.with_id, count (1) all_gst, ");
			sql.append("               isnull (sum (CASE WHEN a.chk_stat = 'I' THEN 1 ELSE 0 END), 0) live_gst, ");
			sql.append("               isnull (sum (CASE WHEN datediff (day, a.leave_date, DATEADD (day, -"+dataSaveNum+", '"+hotelDate+"')) >= 0 THEN 1 ELSE 0 END), 0) out_gst ");
			sql.append("          FROM guestdoc a GROUP BY a.with_id) x ");
			sql.append("       JOIN ");
			sql.append("       (SELECT b.*, ");
			sql.append("               (SELECT count (*) FROM bills c ");
			sql.append("                 WHERE c.bill_id = b.billa_id ");
			sql.append("                       AND DATEDIFF (day, c.oper_time, DATEADD (day, -"+dataSaveNum+", '"+hotelDate+"')) <= 0 ");
			sql.append("                       AND DATEDIFF (day, c.hotel_time, DATEADD (day, -"+dataSaveNum+", '"+hotelDate+"')) <= 0 ");
			sql.append("                       AND DATEDIFF (day, c.pay_date, DATEADD (day, -"+dataSaveNum+", '"+hotelDate+"')) <= 0) billa_num, ");
			sql.append("               (SELECT count (*) FROM bills d ");
			sql.append("                 WHERE d.bill_id = b.billb_id ");
			sql.append("                       AND DATEDIFF (day, d.oper_time, DATEADD (day, -"+dataSaveNum+", '"+hotelDate+"')) <= 0 ");
			sql.append("                       AND DATEDIFF (day, d.hotel_time, DATEADD (day, -"+dataSaveNum+", '"+hotelDate+"')) <= 0 ");
			sql.append("                       AND DATEDIFF (day, d.pay_date, DATEADD (day, -"+dataSaveNum+", '"+hotelDate+"')) <= 0) billb_num ");
			sql.append("          FROM guestdoc b WHERE b.chk_stat = 'O' AND datediff (day, b.leave_date, DATEADD (day, -"+dataSaveNum+", '"+hotelDate+"')) >= 0) y ON y.with_id = x.with_id ");
			sql.append(" WHERE x.live_gst = 0 AND y.chk_stat IS NOT NULL AND x.all_gst = x.out_gst AND x.live_gst = 0 AND y.billa_num = 0 AND y.billb_num = 0");
			List<Map<String, Object>> list = jdbcTemplate.queryForList(sql.toString());
			if(list.size() == 0){
				transactionTemplate.execute(new EndStep(operId, CURR_STEP_DATAMOVE, CURR_STEP_DATAMOVE_01, hotelDate, 0, "guestdoc", "{}"));
			}
			int i = sqlStep - 1;
			for (; i < list.size(); i++) {
				result = transactionTemplate.execute(new LeaveGuestMove(operId, hotelDate, CURR_STEP_DATAMOVE, CURR_STEP_DATAMOVE_01, i + 1, list.get(i)));
				if(!result.get("issuccess").toString().equals("true")){
					result.put("currStep", CURR_STEP_DATAMOVE);
					result.put("currSubStep", CURR_STEP_DATAMOVE_01);
					result.put("sqlStep", i+1);
					return result;
				}
			}
			
		case CURR_STEP_DATAMOVE_02:
			sql = new StringBuilder("");
			sql.append("SELECT a.id, a.bill_id ");
			sql.append("  FROM noguest a LEFT JOIN bills b ON a.bill_id = b.bill_id ");
			sql.append(" WHERE     datediff (day, a.modi_time, dateadd (day, -30, '"+hotelDate+"')) >= 0 ");
			sql.append("       AND DATEDIFF (day, b.oper_time, DATEADD (day, -30, '"+hotelDate+"')) >= 0 ");
			sql.append("       AND DATEDIFF (day, b.hotel_time, DATEADD (day, -30, '"+hotelDate+"')) >= 0 ");
			sql.append("       AND DATEDIFF (day, b.pay_date, DATEADD (day, -30, '"+hotelDate+"')) >= 0 ");
			List<Map<String, Object>> list1 = jdbcTemplate.queryForList(sql.toString());
			if(list1.size() == 0){
				transactionTemplate.execute(new EndStep(operId, CURR_STEP_DATAMOVE, CURR_STEP_DATAMOVE_02, hotelDate, 0, "guestdoc", "{}"));
			}
			int j = sqlStep - 1;
			for (; j < list1.size(); j++) {
				result = transactionTemplate.execute(new NoGuestMove(operId, hotelDate, CURR_STEP_DATAMOVE, CURR_STEP_DATAMOVE_02, j + 1, list1.get(j)));
				if(!result.get("issuccess").toString().equals("true")){
					result.put("currStep", CURR_STEP_DATAMOVE);
					result.put("currSubStep", CURR_STEP_DATAMOVE_02);
					result.put("sqlStep", j+1);
					return result;
				}
			}
			
		case CURR_STEP_DATAMOVE_03:
			sql = new StringBuilder("");
			sql.append("SELECT x.check_id, x.billb_id bill_id ");
			sql.append("  FROM (SELECT a.check_id, a.billb_id, ");
			sql.append("               isnull (sum (CASE WHEN b.ok_flag = 0 OR datediff (day, b.oper_time, dateadd (day, -30, '"+hotelDate+"')) <= 0");
			sql.append("                             OR datediff (day, b.hotel_time, dateadd (day, -30, '"+hotelDate+"')) <= 0 ");
			sql.append("                             OR datediff (day, b.pay_date, dateadd (day, -30, '"+hotelDate+"')) <= 0 THEN 1 ELSE 0 END),  0) ok_bill_num ");
			sql.append("          FROM guestdoc a LEFT JOIN bills b ON a.billb_id = b.bill_id ");
			sql.append("         WHERE a.chk_stat IS NULL AND datediff (day, a.leave_date, dateadd (day, -30, '"+hotelDate+"')) >= 0 ");
			sql.append("        GROUP BY a.check_id, a.billb_id) x ");
			sql.append(" WHERE x.ok_bill_num = 0 ");
			sql.append("UNION ALL ");
			sql.append("SELECT y.check_id, y.bill_id bill_id ");
			sql.append("  FROM (SELECT a.check_id, a.bill_id, ");
			sql.append("               isnull ( sum (CASE WHEN b.ok_flag = 0 OR datediff (day, b.oper_time, dateadd (day, -30, '"+hotelDate+"')) <= 0 ");
			sql.append("                             OR datediff (day, b.hotel_time, dateadd (day, -30, '"+hotelDate+"')) <= 0 ");
			sql.append("                             OR datediff (day, b.pay_date, dateadd (day, -30, '"+hotelDate+"')) <= 0 THEN 1 ELSE 0 END), 0) ok_bill_num ");
			sql.append("          FROM grp_doc a LEFT JOIN bills b ON a.bill_id = b.bill_id ");
			sql.append("         WHERE a.chk_stat IS NULL AND datediff (day, a.leave_date, dateadd (day, -30, '"+hotelDate+"')) >= 0 ");
			sql.append("        GROUP BY a.check_id, a.bill_id) y ");
			sql.append(" WHERE y.ok_bill_num = 0 ");
			List<Map<String, Object>> list2 = jdbcTemplate.queryForList(sql.toString());
			if(list2.size() == 0){
				transactionTemplate.execute(new EndStep(operId, CURR_STEP_DATAMOVE, CURR_STEP_DATAMOVE_03, hotelDate, 0, "guestdoc", "{}"));
			}
			int k = sqlStep - 1;
			for (; k < list2.size(); k++) {
				result = transactionTemplate.execute(new BookRoomMove(operId, hotelDate, CURR_STEP_DATAMOVE, CURR_STEP_DATAMOVE_03, k + 1, list2.get(k)));
				if(!result.get("issuccess").toString().equals("true")){
					result.put("currStep", CURR_STEP_DATAMOVE);
					result.put("currSubStep", CURR_STEP_DATAMOVE_03);
					result.put("sqlStep", k+1);
					return result;
				}
			}
		default:
			result.put("issuccess", "true");
			result.put("currStep", CURR_STEP_DATAMOVE);
			return result;
		}
	}
	
	/**
	 * 执行插入
	 * @param entity
	 * @param entityName
	 * @throws Exception
	 */
	private void executeInsert(Map<String, Object> entity, String entityName, Class<?> cla, String delSql) throws Exception{
		Set<String> set = entity.keySet();
		int i = 0;
		StringBuilder keyNameStr = new StringBuilder();
		StringBuilder valueSignStr = new StringBuilder();
		Field[] fields = cla.getDeclaredFields();
		for (Iterator<String> it = set.iterator(); it.hasNext();) {
			String key = it.next();
			String s = key.replaceAll("_", "");
			Class<?> type = String.class;
			for(int j = 0; j < fields.length; j++){
				if(s.toLowerCase().equals(fields[j].getName().toLowerCase())){
					type = fields[j].getType();
					break;
				}
			}
			String value = entity.get(key) == null ? "null" : entity.get(key).toString().trim();
			if(type.getSimpleName().equals("Integer") || type.getSimpleName().equals("Double") || type.getSimpleName().equals("Short") || type.getSimpleName().equals("Long")){
				if(i==0){
					keyNameStr.append(key);
					valueSignStr.append(value);
				}else{
					keyNameStr.append("," + key);
					valueSignStr.append("," + value);
				}
			}else if(type.getSimpleName().equals("Boolean")){
				if(i==0){
					keyNameStr.append(key);
					valueSignStr.append(value.equals("true") ? 1 : 0);
				}else{
					keyNameStr.append("," + key);
					valueSignStr.append("," + (value.equals("true") ? 1 : 0));
				}
			}else{
				if(i==0){
					keyNameStr.append(key);
					valueSignStr.append(value.equals("null") ? "null" : "'"+value + "'");
				}else{
					keyNameStr.append("," + key);
					valueSignStr.append(value.equals("null") ? ", null" : ", '"+value + "'");
				}
			}
			i++;
		}
		//同一条语句先删除后修改，用来控制jdbc事务
		String sql = delSql + "; INSERT INTO " + entityName + "("+keyNameStr.toString()+") VALUES("+valueSignStr.toString()+");";
		Connection conn = null;
		Statement st = null;
		try {
			conn = dataSourceSlave.getConnection();
			st = conn.createStatement();
			System.out.println(sql);
			st.executeUpdate(sql);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}finally{
			if(conn!=null){     // 最后关闭连接
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(st!=null){   //关闭Statement
                try {
                    st.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
		}
	}
	
	/**
	 * 离店住客迁移
	 * @author Administrator
	 *
	 */
	class LeaveGuestMove implements TransactionCallback<Map<String, Object>>{
		private String operId;
		private String hotelDate;
		private int currStep;
		private int currSubStep;
		private int sqlStep;
		private Map<String, Object> paraMap;
		public LeaveGuestMove(String operId, String hotelDate, int currStep, int currSubStep, int sqlStep, Map<String, Object> paraMap){
			this.operId = operId;
			this.hotelDate = hotelDate;
			this.currStep = currStep;
			this.currSubStep = currSubStep;
			this.sqlStep = sqlStep;
			this.paraMap = paraMap;
		}
		@Override
		public Map<String, Object> doInTransaction(TransactionStatus arg0) {
			Map<String, Object> result = new HashMap<String, Object>();
			try {
				String check_id = paraMap.get("check_id") == null ? "" : paraMap.get("check_id").toString();
				String billa_id = paraMap.get("billa_id") == null ? "" : paraMap.get("billa_id").toString();
				String billb_id = paraMap.get("billb_id") == null ? "" : paraMap.get("billb_id").toString();
				String grp_chkid = paraMap.get("grp_chkid") == null ? "" : paraMap.get("grp_chkid").toString();
				
				List<Map<String, Object>> guestdoclist = jdbcTemplate.queryForList("select * from guestdoc where check_id = " + check_id);
				List<Map<String, Object>> bpaidlist = jdbcTemplate.queryForList("select * from b_paid where check_id = " + check_id);
				List<Map<String, Object>> billslist = jdbcTemplate.queryForList("select * from bills where bill_id = "+billa_id+" or bill_id = " + billb_id);
//				List<Map<String, Object>> bookroomcheckinlist = jdbcTemplate.queryForList("select * from book_room_checkin");
				List<Map<String, Object>> grpdoclist = jdbcTemplate.queryForList("select * from grp_doc where grp_id = "+grp_chkid);
				List<Map<String, Object>> gstcreditauthlist = jdbcTemplate.queryForList("select * from gst_credit_auth where bill_id = "+billa_id+" or bill_id = " + billb_id);
				List<Map<String, Object>> gstpricelistlist = jdbcTemplate.queryForList("select * from gst_price_list where check_id = " + check_id);
				List<Map<String, Object>> gstbilllist = jdbcTemplate.queryForList("select * from gst_bill where bill_id = "+billa_id+" or bill_id = " + billb_id);
				
				//guestdoc迁移
				for (Map<String, Object> map : guestdoclist) {
					executeInsert(map, "guestdoc", Guestdoc.class, "delete from guestdoc where check_id = " + map.get("check_id").toString());
				}
				//b_paid迁移
				for (Map<String, Object> map : bpaidlist) {
					executeInsert(map, "b_paid", BPaied.class, "delete from b_paid where check_id = " + map.get("check_id").toString());
				}
				//bills迁移
				for (Map<String, Object> map : billslist) {
					executeInsert(map, "bills", Bills.class, "delete from bills where id = " + map.get("id").toString());
				}
				//book_room_checkin迁移
//				for (Map<String, Object> map : bookroomcheckinlist) {
//					executeInsert(map, "book_room_checkin", BookRoomCheckin.class);
//				}
				//grp_doc迁移
				for (Map<String, Object> map : grpdoclist) {
					executeInsert(map, "grp_doc", GrpDoc.class, "delete from grp_doc where check_id = " + map.get("check_id").toString());
				}
				//gst_credit_auth迁移
				for (Map<String, Object> map : gstcreditauthlist) {
					executeInsert(map, "gst_credit_auth", GstCreditAuth.class, "delete from gst_credit_auth where id = " + map.get("id").toString());
				}
				//gst_price_list迁移
				for (Map<String, Object> map : gstpricelistlist) {
					executeInsert(map, "gst_price_list", GstPriceList.class, "delete from gst_price_list where id = " + map.get("id").toString());
				}
				//gst_bill迁移
				for (Map<String, Object> map : gstbilllist) {
					executeInsert(map, "gst_bill", GstBill.class, "delete from gst_bill where bill_id = " + map.get("bill_id").toString());
				}
				transactionTemplate.execute(new EndStep(operId, currStep, currSubStep, hotelDate, sqlStep, "guestdoc,b_paid, bills, book_room_checkin , grp_doc,gst_credit_list,gst_price_list,gst_bill", new Gson().toJson(paraMap)));
				result.put("issuccess", "true");
			} catch (Exception e) {
				e.printStackTrace();
				arg0.setRollbackOnly();
				result.put("issuccess", "false");
				result.put("msg", e.getMessage());
			}
			return result;
		}
	}
	
	/**
	 * 非住客迁移
	 * @author Administrator
	 *
	 */
	class NoGuestMove implements TransactionCallback<Map<String, Object>>{
		private String operId;
		private String hotelDate;
		private int currStep;
		private int currSubStep;
		private int sqlStep;
		private Map<String, Object> paraMap;
		public NoGuestMove(String operId, String hotelDate, int currStep, int currSubStep, int sqlStep, Map<String, Object> paraMap){
			this.operId = operId;
			this.hotelDate = hotelDate;
			this.currStep = currStep;
			this.currSubStep = currSubStep;
			this.sqlStep = sqlStep;
			this.paraMap = paraMap;
		}
		@Override
		public Map<String, Object> doInTransaction(TransactionStatus arg0) {
			Map<String, Object> result = new HashMap<String, Object>();
			try {
				String id = paraMap.get("id") == null ? "" : paraMap.get("id").toString();
				String bill_id = paraMap.get("bill_id") == null ? "" : paraMap.get("bill_id").toString();
				
				List<Map<String, Object>> noguestlist = jdbcTemplate.queryForList("select * from noguest where id = " + id);
				List<Map<String, Object>> billslist = jdbcTemplate.queryForList("select * from bills where bill_id = " + bill_id);
				List<Map<String, Object>> gstcreditauthlist = jdbcTemplate.queryForList("select * from gst_credit_auth where bill_id = " + bill_id);
				List<Map<String, Object>> gstbilllist = jdbcTemplate.queryForList("select * from gst_bill where bill_id = " + bill_id);
				
				//guestdoc迁移
				for (Map<String, Object> map : noguestlist) {
					executeInsert(map, "noguest", Noguest.class, "delete from noguest where id = " + map.get("id").toString());
				}
				//bills迁移
				for (Map<String, Object> map : billslist) {
					executeInsert(map, "bills", Bills.class, "delete from bills where id = " + map.get("id").toString());
				}
				//gst_credit_auth迁移
				for (Map<String, Object> map : gstcreditauthlist) {
					executeInsert(map, "gst_credit_auth", GstCreditAuth.class, "delete from gst_credit_auth where id = " + map.get("id").toString());
				}
				//gst_bill迁移
				for (Map<String, Object> map : gstbilllist) {
					executeInsert(map, "gst_bill", GstBill.class, "delete from gst_bill where bill_id = " + map.get("bill_id").toString());
				}
				transactionTemplate.execute(new EndStep(operId, currStep, currSubStep, hotelDate, sqlStep, "noguest, bills, gst_credit_list, gst_bill", new Gson().toJson(paraMap)));
				result.put("issuccess", "true");
			} catch (Exception e) {
				e.printStackTrace();
				arg0.setRollbackOnly();
				result.put("issuccess", "false");
				result.put("msg", e.getMessage());
			}
			return result;
		}
		
	}
	
	/**
	 * 预定信息迁移
	 * @author Administrator
	 *
	 */
	class BookRoomMove implements TransactionCallback<Map<String, Object>>{
		private String operId;
		private String hotelDate;
		private int currStep;
		private int currSubStep;
		private int sqlStep;
		private Map<String, Object> paraMap;
		public BookRoomMove(String operId, String hotelDate, int currStep, int currSubStep, int sqlStep, Map<String, Object> paraMap){
			this.operId = operId;
			this.hotelDate = hotelDate;
			this.currStep = currStep;
			this.currSubStep = currSubStep;
			this.sqlStep = sqlStep;
			this.paraMap = paraMap;
		}
		@Override
		public Map<String, Object> doInTransaction(TransactionStatus arg0) {
			Map<String, Object> result = new HashMap<String, Object>();
			try {
				String check_id = paraMap.get("check_id") == null ? "" : paraMap.get("check_id").toString();
				String bill_id = paraMap.get("bill_id") == null ? "" : paraMap.get("bill_id").toString();
				
				List<Map<String, Object>> guestdoclist = jdbcTemplate.queryForList("select * from guestdoc where check_id = " + check_id);
				List<Map<String, Object>> grpdoclist = jdbcTemplate.queryForList("select * from grp_doc where check_id = "+check_id);
				List<Map<String, Object>> billslist = jdbcTemplate.queryForList("select * from bills where bill_id = "+bill_id);
				List<Map<String, Object>> gstbilllist = jdbcTemplate.queryForList("select * from gst_bill where bill_id = "+bill_id);
				List<Map<String, Object>> bookroomlist = jdbcTemplate.queryForList("select * from book_room where check_id = "+check_id);
				
				//guestdoc迁移
				for (Map<String, Object> map : guestdoclist) {
					executeInsert(map, "guestdoc", Guestdoc.class, "delete from guestdoc where check_id = " + map.get("check_id").toString());
				}
				//grp_doc迁移
				for (Map<String, Object> map : grpdoclist) {
					executeInsert(map, "grp_doc", GrpDoc.class, "delete from grp_doc where check_id = " + map.get("check_id").toString());
				}
				//bills迁移
				for (Map<String, Object> map : billslist) {
					executeInsert(map, "bills", Bills.class, "delete from gst_bill where id = " + map.get("id").toString());
				}
				//gst_bill迁移
				for (Map<String, Object> map : gstbilllist) {
					executeInsert(map, "gst_bill", GstBill.class, "delete from gst_bill where bill_id = " + map.get("bill_id").toString());
				}
				//gst_bill迁移
				for (Map<String, Object> map : bookroomlist) {
					executeInsert(map, "book_room", BookRoom.class, "delete from book_room where id = " + map.get("id").toString());
					String book_room_id = map.get("book_room_id") == null ? "-1" : map.get("book_room_id").toString();
					String id = map.get("id") == null ? "-1" : map.get("id").toString();
					List<Map<String, Object>> bookroomdiarylist = jdbcTemplate.queryForList("select * from book_room_diary where book_room_id = " + book_room_id);
					for (Map<String, Object> innerMap : bookroomdiarylist) {
						executeInsert(innerMap, "book_room_diary", BookRoomDiary.class, "delete from book_room_diary where id = " + map.get("id").toString());
					}
					List<Map<String, Object>> bookroomcheckinlist = jdbcTemplate.queryForList("select * from book_room_checkin where room_link_id = " + book_room_id);
					for (Map<String, Object> innerMap : bookroomcheckinlist) {
						executeInsert(innerMap, "book_room_checkin", BookRoomCheckin.class, "delete from book_room_checkin where id = " + map.get("id").toString());
					}
					List<Map<String, Object>> roomnumlist = jdbcTemplate.queryForList("select * from room_num where book_id = " + id);
					for (Map<String, Object> innerMap : roomnumlist) {
						executeInsert(innerMap, "room_num", RoomNum.class, "delete from room_num where id = " + map.get("id").toString());
						String room_chkid = map.get("room_chkid") == null ? "-1" : map.get("room_chkid").toString();
						List<Map<String, Object>> roomsdiarylist = jdbcTemplate.queryForList("select * from rooms_dirary where room_chkid = " + room_chkid);
						for (Map<String, Object> threeMap : roomsdiarylist) {
							executeInsert(threeMap, "rooms_dirary", RoomsDiary.class, "delete from rooms_dirary where id = " + map.get("id").toString());
						}
					}
				}
				transactionTemplate.execute(new EndStep(operId, currStep, currSubStep, hotelDate, sqlStep, "guestdoc,grp_doc,book_room, book_room_diary,book_room_checkin, room_num,rooms_dirary,bills,gst_bill", new Gson().toJson(paraMap)));
				result.put("issuccess", "true");
			} catch (Exception e) {
				e.printStackTrace();
				arg0.setRollbackOnly();
				result.put("issuccess", "false");
				result.put("msg", e.getMessage());
			}
			return result;
		}
	}
	
	/**
	 * 定义夜审线程
	 * @author Administrator
	 *
	 */
	class DayAuditThread extends Thread{
		private String operId;
		public void setOperId(String param){
			this.operId = param;
		}
		private int currStep;
		public int getCurrStep() {
			return currStep;
		}
		public void setCurrStep(int currStep) {
			this.currStep = currStep;
		}
		public int getCurrSubStep() {
			return currSubStep;
		}
		public void setCurrSubStep(int currSubStep) {
			this.currSubStep = currSubStep;
		}
		public int getSqlStep() {
			return sqlStep;
		}
		public void setSqlStep(int sqlStep) {
			this.sqlStep = sqlStep;
		}
		public Queue<Map<String, Object>> getQueue() {
			return queue;
		}
		public void setQueue(Queue<Map<String, Object>> queue) {
			this.queue = queue;
		}
		public String getOperId() {
			return operId;
		}
		public String getHotelDate() {
			return hotelDate;
		}
		private int currSubStep;
		private int sqlStep;
		private Queue<Map<String, Object>> queue;
		private String hotelDate;
		private int handle;
		public int getHandle() {
			return handle;
		}
		public void setHandle(int handle) {
			this.handle = handle;
		}
		public void setHotelDate(String hotelDate){
			this.hotelDate = hotelDate;
		}
		@Override
		public void run() {
			starDayAudit(operId, hotelDate, currStep, currSubStep, sqlStep, queue, handle);
		}
	}
	

	/**
	 * 启动夜审线程
	 */
	@Override
	public Map<String, Object> dayAudit(String operId, int currStep, int currSubStep, int sqlStep, Queue<Map<String, Object>> queue, int handle) {
		String hotelDate = jdbcTemplate.queryForObject("select convert(varchar(10), para3, 23) from parameter where id = 1", String.class);
		DayAuditThread thread = new DayAuditThread();
		thread.setOperId(operId);
		thread.setHotelDate(hotelDate);
		thread.setCurrStep(currStep);
		thread.setCurrSubStep(currSubStep);
		thread.setSqlStep(sqlStep);
		thread.setQueue(queue);
		thread.setHandle(handle);
		thread.start();
		return null;
	}
	
	/**
	 * 开始夜审
	 */
	private void starDayAudit(String operId, String hotelDate, int currStep, int currSubStep, int sqlStep, Queue<Map<String, Object>> queue, int handle){
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("issuccess", "true");
		
		switch (currStep) {
		case CURR_STEP_DATACCHECK:
			//第一步：数据检查
			result = dataCheck(operId, hotelDate, currSubStep, handle);
			queue.offer(result);
			if(!result.get("issuccess").toString().equals("true")){
				break;
			}

		case CURR_STEP_OPEN_DAYAUDIT:
			//第零步：打开夜审状态
			result = openDayAuditStatus(operId, hotelDate, currSubStep);
			queue.offer(result);
			if(!result.get("issuccess").toString().equals("true")){
				break;
			}

		case CURR_STEP_CHANGERENT:
			//第二步：过房租
			result = changeRent(hotelDate, currSubStep, sqlStep);
			queue.offer(result);
//			if(!result.get("issuccess").toString().equals("true")){
//				break;
//			}

		case CURR_STEP_CHANGEROOMSTAT:
			//第三步：修改房态
			result = changeRoomStat(operId, hotelDate, currSubStep, sqlStep);
			queue.offer(result);
//			if(!result.get("issuccess").toString().equals("true")){
//				break;
//			}

		case CURR_STEP_CHANGEFGUEST:
			//第四步：转熟客
			result = changeFGuest(operId, hotelDate, currSubStep, sqlStep);
			queue.offer(result);
//			if(!result.get("issuccess").toString().equals("true")){
//				break;
//			}

		case CURR_STEP_CLEARBOOKROOM:
			//第五步：清理预定
			result = clearBookRoom(operId, hotelDate, currSubStep, sqlStep);
			queue.offer(result);
//			if(!result.get("issuccess").toString().equals("true")){
//				break;
//			}

		case CURR_STEP_STATISTICS:
			//第六步：统计
			result = statistics(operId, hotelDate, currSubStep, sqlStep);
			queue.offer(result);
//			if(!result.get("issuccess").toString().equals("true")){
//				break;
//			}

		case CURR_STEP_DATAMOVE:
			//第七步：数据迁移
			result = dataMove(operId, hotelDate, currSubStep, sqlStep);
			queue.offer(result);
//			if(!result.get("issuccess").toString().equals("true")){
//				break;
//			}
			
		case 99:
			//最后一步：关闭夜审状态 
			result = closeDayAuditStatus();
			queue.offer(result);
//			if(!result.get("issuccess").toString().equals("true")){
//				break;
//			}
			
		default:
			result.put("issuccess", "true");
			break;
		}
		return;
		
	}
	
	
	/**
	 * 夜审完成，关闭夜审状态
	 * @return
	 */
	private Map<String, Object> closeDayAuditStatus(){
		return transactionTemplate.execute(new TransactionCallback<Map<String, Object>>() {

			@Override
			public Map<String, Object> doInTransaction(TransactionStatus arg0) {
				Map<String, Object> result = new HashMap<String, Object>();
				try {
					boolean dayAuditStatus = jdbcTemplate.queryForObject("select para2 from parameter where id = 1", Boolean.class);
					if(dayAuditStatus){

						result.put("issuccess", "false");
						result.put("msg", "启动夜审失败，另一个夜审线程正则进行中。");
					}
					//从parameter表中取得当前酒店日期
					String getHotelDate = "select convert(varchar(10), para3, 23) from parameter where id = 1";
					//当前酒店日期定义
					String hotelDate = jdbcTemplate.queryForObject(getHotelDate, String.class);
					//日期处理，给酒店日期加一天
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					Calendar calendar = Calendar.getInstance();
					calendar.setTime(sdf.parse(hotelDate));
					calendar.add(Calendar.DAY_OF_YEAR, 1);
					
					//更新夜审状态，并切换酒店日期
					String sql = "update parameter set para2 = 0, para3 = convert(datetime, '"+sdf.format(calendar.getTime())+"') where id = 1";
					jdbcTemplate.execute(sql);
					result.put("issuccess", "true");
				} catch (Exception e) {
					e.printStackTrace();
					result.put("issuccess", "false");
					result.put("msg", e.getMessage());
				}
				result.put("currStep", 99);
				return result;
			}
		});
	}
	
	
	
	/**
	 * 取消夜审
	 */
	@Override
	public Map<String, Object> cancelDayAudit() {
		Map<String, Object> result = new HashMap<String, Object>();
		boolean re = transactionTemplate.execute(new TransactionCallback<Boolean>() {

			@Override
			public Boolean doInTransaction(TransactionStatus arg0) {
				try {
					//更新夜审状态
					String sql = "update parameter set para2 = 1 where id = 1";
					jdbcTemplate.execute(sql);
				} catch (DataAccessException e) {
					e.printStackTrace();
					return false;
				} 
				return true;
			}
		});
		if(re){
			result.put("issuccess", "false");
			result.put("msg", "夜审取消失败，数据库异常。");
		}else{
			result.put("issuccess", "true");
			result.put("msg", "夜审已取消。");
		}
		return result;
	}

	/**
	 * 以房价列表为准处理房价
	 */
	@Override
	public boolean handleRoomPrice() {
		return transactionTemplate.execute(new TransactionCallback<Boolean>() {

			@Override
			public Boolean doInTransaction(TransactionStatus arg0) {
				//从parameter表中取得当前酒店日期
				String hotelDate = jdbcTemplate.queryForObject("select convert(varchar(10), para3, 23) from parameter where id = 1", String.class);
				//查询在住房间主人数量不为一的房间号
				StringBuilder builder = new StringBuilder("");
				builder.append("SELECT DISTINCT x.check_id, x.room_id, (SELECT price FROM hroom_plan_list WHERE rmplan_id = y.code_id AND rmtype_id = x.room_type AND holiday_id IN (SELECT id FROM holidays WHERE holiday_date = '"+hotelDate+"')) price ");
				builder.append("  FROM (SELECT a.check_id, a.room_id, a.prc_scheme_id,  a.room_price, b.room_type ");
				builder.append("          FROM guestdoc a LEFT JOIN rooms b ON b.room_id = a.room_id ");
				builder.append("         WHERE a.chk_stat = 'I' AND a.is_room_plan = 1) x ");
				builder.append("  LEFT JOIN hroom_plan y ON y.code_id = x.prc_scheme_id ");
				builder.append(" WHERE y.ID IS NOT NULL ");
				builder.append("       AND DATEDIFF (day, y.start_date, '"+hotelDate+"') >= 0 ");
				builder.append("       AND DATEDIFF (day, y.end_date, '"+hotelDate+"') <= 0 ");
				builder.append("       AND x.room_price NOT IN (SELECT price ");
				builder.append("                                  FROM hroom_plan_list");
				builder.append("                                 WHERE rmplan_id = y.code_id AND rmtype_id = x.room_type ");
				builder.append("                                       AND holiday_id IN (SELECT id FROM holidays WHERE holiday_date = '"+hotelDate+"'))");
				String sql = builder.toString();
				List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);
				for (Map<String, Object> map : list) {
					if(map.get("price") != null){
						jdbcTemplate.update("update guestdoc set room_price = " + map.get("price").toString() + " where check_id = " + map.get("check_id").toString());
					}
				}
				return true;
			}
		});
		
	}
	
	


}
