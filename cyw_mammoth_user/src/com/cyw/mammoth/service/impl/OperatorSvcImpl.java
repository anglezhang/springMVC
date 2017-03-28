package com.cyw.mammoth.service.impl;

import java.sql.Timestamp;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import net.sf.ehcache.Cache;
import net.sf.ehcache.Element;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.cyw.common.base.service.impl.BaseSvcImpl;
import com.cyw.common.util.CommonUtil;
import com.cyw.common.util.DateUtil;
import com.cyw.common.util.StrUtils;
import com.cyw.common.web.intercepter.LoginInterceptor;
import com.cyw.mammoth.auth.AppBaseCfg;
import com.cyw.mammoth.bean.Hcodes;
import com.cyw.mammoth.bean.Hfunction;
import com.cyw.mammoth.bean.HfunctionWorkgroupList;
import com.cyw.mammoth.bean.Operator;
import com.cyw.mammoth.bean.Parameter;
import com.cyw.mammoth.bean.ShiftLog;
import com.cyw.mammoth.bean.Operator;
import com.cyw.mammoth.cywenum.HcodesEnumType;
import com.cyw.mammoth.dao.CodeCareDao;
import com.cyw.mammoth.dao.OperatorDao;
import com.cyw.mammoth.dao.ShiftLogDao;
import com.cyw.mammoth.service.OperatorSvc;
import com.cyw.mammoth.service.ParameterSvc;
import com.cyw.mammoth.vo.DefaultRowMapper;
import com.cyw.mammoth.vo.OperatorListVO;
import com.cyw.mammoth.vo.OperatorVo;

@Service
public class OperatorSvcImpl extends BaseSvcImpl<Operator, String> implements OperatorSvc {
	/**
	 * 日志对象
	 */
	protected Logger logger = LoggerFactory.getLogger(getClass());
	private OperatorDao operatorDao;

	@Autowired
	public void setOperatorDao(OperatorDao operatorDao) {
		this.operatorDao = operatorDao;
		setBaseDao(operatorDao);
	}

	private JdbcTemplate jdbcTemplate;

	@Autowired
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	private Cache simpleCache;

	@Autowired
	public void setSimpleCache(Cache simpleCache) {
		this.simpleCache = simpleCache;
	}

	private ParameterSvc parameterSvc;

	@Autowired
	public void setParameterSvc(ParameterSvc parameterSvc) {
		this.parameterSvc = parameterSvc;
	}

	private ShiftLogDao shiftLogDao;

	@Autowired
	public void setShiftLogDao(ShiftLogDao shiftLogDao) {
		this.shiftLogDao = shiftLogDao;
	}

	private CodeCareDao codeCareDao;

	@Autowired
	public void setCodeCareDao(CodeCareDao codeCareDao) {
		this.codeCareDao = codeCareDao;
	}

	@Override
	public List<Operator> search(OperatorVo searchVo) {

		return operatorDao.search(searchVo);
	}

	@Override
	public List<Hfunction> getOperatorAuthedFunctions(String operatorId) {
		if (!StrUtils.isValidString(operatorId))
			return null;
		Operator user = operatorDao.get(operatorId);
		return getOperatorAuthedFunctions(user);
	}

	@Override
	public List<Hfunction> getOperatorAuthedFunctions(Operator operator) {
		if (null == operator)
			return null;
		List<Hfunction> hfunctions = new ArrayList<Hfunction>();
		Element authedFuncEmt = simpleCache.get(LoginInterceptor.authedFuncKey + "_" + operator.getOperId());
		if (null != authedFuncEmt) {
			hfunctions = (List<Hfunction>) authedFuncEmt.getObjectValue();
		} else {
			hfunctions = getAuthedHfunctions(operator.getGroupId());
			authedFuncEmt = new Element(LoginInterceptor.authedFuncKey + "_" + operator.getOperId(), hfunctions);
			int limitSeconds = 60 * 60 * 24;
			authedFuncEmt.setTimeToIdle(limitSeconds);
			authedFuncEmt.setTimeToLive(limitSeconds);
			simpleCache.put(authedFuncEmt);
		}

		return hfunctions;
	}

	private List<Hfunction> getAuthedHfunctions(String groupId) {
		String sql = "select * from hfunction_workgroup_list hfw left join hfunction_control hfu on hfw.hfunction_id=hfu.function_id  where hfw.workgroup_id=?";

		List<Hfunction> hfunctions = jdbcTemplate.query(sql, new Object[] { groupId }, new int[] { Types.CHAR }, new DefaultRowMapper<Hfunction>(Hfunction.class));
		return hfunctions;
	}

	@Override
	public int checkIsInAuthManageNav(String funcId) {
		String sql = "SELECT COUNT(id) FROM hfunction WHERE function_id='" + funcId + "'";

		return jdbcTemplate.queryForInt(sql);
	}

	@Override
	public int checkIsInAuthManageCrt(String funcId) {
		String sql = "SELECT COUNT(id) FROM hfunction_control WHERE function_id='" + funcId + "'";

		return jdbcTemplate.queryForInt(sql);
	}

	@Override
	public int checkIsInAuthManage(String funcId) {

		return checkIsInAuthManageCrt(funcId) + checkIsInAuthManageCrt(funcId);
	}

	@Override
	public boolean checkIsAuthed(String workgroupId, String funcId) {
		String sql = "select COUNT(hfunction_id) from hfunction_workgroup_list hfw   where workgroup_id=? AND hfunction_id=?";
		int cout = jdbcTemplate.queryForInt(sql, new Object[] { workgroupId, funcId }, new int[] { Types.VARCHAR, Types.VARCHAR });
		return cout > 0;
	}

	@Override
	public ShiftLog getOperShiftLog(Operator operator) {
		// 查询班次记录
		ShiftLog shfitLog = null;
		try {
			if (null == operator)
				return null;
			if (!StrUtils.isValidString(operator.getOperId()))
				return null;
			Parameter param = parameterSvc.get(1);
			Date hotleDateDB = param.getPara3();
			String hotelDateStr = DateUtil.convertDate2String(hotleDateDB, DateUtil.defaultDatePattern);
			Date hotleDate= DateUtil.convertStringToDate(hotelDateStr);
			String sql = "SELECT * FROM Shift_log WHERE oper_id=? and datediff(day,oper_date,?) = 0  ORDER BY id DESC";
			List<ShiftLog> shiftLogs = jdbcTemplate.query(sql, new Object[] { operator.getOperId(), hotelDateStr }, new int[] { Types.VARCHAR, Types.VARCHAR },
					new DefaultRowMapper<ShiftLog>(ShiftLog.class));
			if (shiftLogs.isEmpty()) {
				List<Hcodes> hcodesList = codeCareDao.findGEListBy(HcodesEnumType.HCODE_HANDOVER.getValue(), 0);
				if (hcodesList.isEmpty()) {
					throw new Exception("班次通用代码没有配置,必须配置");
				}

				ShiftLog shiftLog = new ShiftLog();
				shiftLog.setShiftId(hcodesList.get(0).getCodeId());
				shiftLog.setOperId(operator.getOperId());
				shiftLog.setOperDate(hotleDate);
				shiftLog.setOperTime(new Timestamp(new Date().getTime()));
				Integer shiftLogEId = shiftLogDao.save(shiftLog);
				shfitLog = shiftLogDao.get(shiftLogEId);
			} else {
				shfitLog = shiftLogs.get(0);
			}

		} catch (Exception e) {
			logger.error("获取用户交接班记录发生错误,", e);
		}
		return shfitLog;

	}

	@Override
	public ShiftLog getOperShiftLog(String operId) {
		// 查询班次记录
		ShiftLog shfitLog = null;
		try {

			if (!StrUtils.isValidString(operId))
				return null;
			Parameter param = parameterSvc.get(1);
			Date hotleDateDB = param.getPara3();
			String hotelDateStr = DateUtil.convertDate2String(hotleDateDB, DateUtil.defaultDatePattern);
			Date hotleDate= DateUtil.convertStringToDate(hotelDateStr);
			String sql = "SELECT * FROM Shift_log WHERE oper_id=? and datediff(day,oper_date,?) = 0  ORDER BY id DESC";
			List<ShiftLog> shiftLogs = jdbcTemplate.query(sql, new Object[] { operId, hotelDateStr }, new int[] { Types.VARCHAR, Types.VARCHAR }, new DefaultRowMapper<ShiftLog>(
					ShiftLog.class));
			if (shiftLogs.isEmpty()) {
				List<Hcodes> hcodesList = codeCareDao.findGEListBy(HcodesEnumType.HCODE_HANDOVER.getValue(), 0);
				if (hcodesList.isEmpty()) {
					throw new Exception("班次通用代码没有配置,必须配置");
				}

				ShiftLog shiftLog = new ShiftLog();
				shiftLog.setShiftId(hcodesList.get(0).getCodeId());
				shiftLog.setOperId(operId);
				shiftLog.setOperDate(hotleDate);
				shiftLog.setOperTime(new Timestamp(new Date().getTime()));
				Integer shiftLogEId = shiftLogDao.save(shiftLog);
				shfitLog = shiftLogDao.get(shiftLogEId);
			} else {
				shfitLog = shiftLogs.get(0);
			}

		} catch (Exception e) {
			logger.error("获取用户交接班记录发生错误,", e);
		}
		return shfitLog;
	}

	
	
	
	
	@Override
	public void updateStatusById(String[] ids, Integer status) {
		if(ids!=null){
			for(String id:ids){
				Operator operator=operatorDao.get("operId",id) ;
				if(operator == null) continue ;
				operator.setStatus(status);
				operatorDao.update(operator);
			}
		}
	}

	@Override
	public void saveOrUpdateOrDel(List<Operator> operators, String delIds) throws Exception {
		boolean delBool = StringUtils.isNotBlank(delIds);
		if(operators !=null && operators.size() > 0){
			Operator wg = null ;
			for (Operator operator : operators) {
				// 查询新增或者修改的对象中在数据库中是否存在对应的对象
				wg = operatorDao.get("operId", operator.getOperId()) ;
				
				// 修改
				if(wg != null){
					// 该对象同时存在被删除的对象中或者该对象中的 在数据库重复  
					if((delBool && delIds.contains(operator.getOperId()) ))
						continue ;
 					// 从session中移除相同的对象
					operatorDao.evict(wg);
					operatorDao.update(operator) ;
				}
				// 新增
				else{
					operator.setCreateDate(Calendar.getInstance().getTime());
					operatorDao.save(operator) ;
				}
			}
		}
		// 删除
		if(StringUtils.isNotBlank(delIds)){
			String[] strArr = CommonUtil.array_unique(delIds.split(",")) ;
			this.updateStatusById(strArr,1);
		}
	}
	@Override
	public List<OperatorListVO> findListBy(Integer status) throws Exception {
		return operatorDao.findListBy(status == null ? 0 : status);
	}
}
