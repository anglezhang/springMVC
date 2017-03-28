package com.cyw.mammoth.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cyw.common.base.service.impl.BaseSvcImpl;
import com.cyw.common.util.CommonUtil;
import com.cyw.mammoth.bean.Holidays;
import com.cyw.mammoth.bean.Parameter;
import com.cyw.mammoth.dao.HolidaysDao;
import com.cyw.mammoth.dao.ParameterDao;
import com.cyw.mammoth.service.HolidaysSvc;
@Service
public class HolidaysSvcImpl extends BaseSvcImpl<Holidays, Integer> implements HolidaysSvc {

	@Autowired
	HolidaysDao holidaysDao;
	@Autowired
	ParameterDao parameterDao;
	
	@Autowired
	public void setBaseDao(HolidaysDao dao) {
		super.setBaseDao(dao);
	}

	@Override
	public void updateStatusById(String[] ids , Integer status) {
		if(ids!=null){
			for(String id:ids){
				Holidays holiday=holidaysDao.get(Integer.valueOf(id));
				if(holiday == null) continue ;
				holiday.setStatus(status);
				holidaysDao.update(holiday);
			}
		}
	}

	@Override
	public void saveOrUpdateOrDel(List<Holidays> holidays, String delIds ,String week, String operId) {
		// 修改参数表中的星期状态
		if(StringUtils.isNotBlank(week)){
			Parameter entity = parameterDao.get(1001) ;
			entity.setPara2Name(week) ;
			parameterDao.update(entity);
		}
		boolean delBool = StringUtils.isNotBlank(delIds) ;
		if(holidays !=null && holidays.size() > 0){
			Holidays hday = null ;
			for (Holidays holiday : holidays) {
				// 查询新增或者修改的对象中的日期（holidayDate）在数据库中是否存在对应的对象
				hday = holidaysDao.get(new String[]{"holidayDate" , "status"}, new Object[]{holiday.getHolidayDate() , 0}) ;
				// 修改
				if(holiday.getId() != null){
					// 该对象同时存在被删除的对象中或者该对象中的日期（holidayDate）在数据库重复  
 					if(delBool && delIds.contains(holiday.getId().toString()) || (hday !=null && hday.getId().intValue() != holiday.getId().intValue()))
						continue ;
 					if(hday!=null)
	 					// 从session中移除相同的对象
	 					holidaysDao.evict(hday);
					holidaysDao.update(holiday) ;
				}
				// 新增
				else{
					if(hday == null)
						holidaysDao.save(holiday) ;
				}
			}
		}
		// 删除
		if(delBool){
			String[] strArr = CommonUtil.array_unique(delIds.split(",")) ;
			this.updateStatusById(strArr , 1 );
		}
	}

	@Override
	public List<Holidays> findListBy(Integer status) throws Exception {
		return holidaysDao.findListBy(status == null ? 0 :status);
	}

	@Override
	public List<Map<String, Object>> findListBy(String startDate, String endDate)
			throws Exception {
		return holidaysDao.findListBy(startDate,endDate);
	}
	
}
