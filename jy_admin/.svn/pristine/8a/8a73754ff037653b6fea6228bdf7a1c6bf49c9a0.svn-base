package com.zoomoor.jy.service.impl;

import java.math.BigInteger;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zoomoor.common.base.bean.Pager;
import com.zoomoor.common.base.service.impl.BaseSvcImpl;
import com.zoomoor.jy.dao.CarCustmerMappingDao;
import com.zoomoor.jy.dao.CustomerAppoitDao;
import com.zoomoor.jy.dao.CustomerEntrustDao;
import com.zoomoor.jy.dao.InfoCarDao;
import com.zoomoor.jy.dao.InfoCustomeDao;
import com.zoomoor.jy.dao.InfoDeptDao;
import com.zoomoor.jy.entity.CarCustmerMapping;
import com.zoomoor.jy.entity.CustomerAppoit;
import com.zoomoor.jy.entity.InfoCar;
import com.zoomoor.jy.entity.InfoCustome;
import com.zoomoor.jy.entity.InfoDept;
import com.zoomoor.jy.entity.InfoEmp;
import com.zoomoor.jy.service.CustomerAppoitSvc;

/**
 * 描述：CustomerAppoitSvcImpl.java
 * 
 * @author Zhangzhenxing
 * @Date 2015年7月17日 下午6:04:57
 * @version 1.0
 */
@Service
public class CustomerAppoitSvcImpl extends BaseSvcImpl<CustomerAppoit, Integer>
		implements CustomerAppoitSvc {

	@Resource
	private CustomerAppoitDao dao;

	/**
	 * 描述:车辆信息
	 * */
	@Resource
	private InfoCarDao carDao;

	/**
	 * 客户信息DAO
	 * */
	@Resource
	private InfoCustomeDao customerDao;

	/**
	 * 部门信息
	 * */
	@Resource
	private InfoDeptDao deptDao;

	/**
	 * 客户与车辆关系
	 * */
	@Resource
	private CarCustmerMappingDao carCusMapDao;
	
	/**
	 * 服务订单
	 * */
	@Resource
	private CustomerEntrustDao entrusDao;

	@Autowired
	public void setBaseDao(CustomerAppoitDao dao) {
		super.setBaseDao(dao);
	}

	/**
	 * 描述：该方法作用
	 * 
	 * @author zhangZhenxing
	 * @Date 2015年7月17日
	 */
	@Override
	public Pager getPager(Pager pager, String queryTel, String queryPlatenum,
			Integer queryAppType, Integer querySource, String queryBegintime,
			String queryEndtime, String querySellstart, String querySellend,
			Integer queryStatus) {
		pager = dao.getList(pager, queryTel, queryPlatenum, queryAppType,
				querySource, queryBegintime, queryEndtime, querySellstart,
				querySellend,queryStatus);
		List<CustomerAppoit> newList = new ArrayList<CustomerAppoit>();
		List<CustomerAppoit> oldList = pager.getList();
		for (CustomerAppoit ca : oldList) {
			Integer emptID = ca.getEmpId();
			if (emptID != null) {
				InfoDept dept = deptDao.get(emptID);
				ca.setInfoDept(dept);
			}
			newList.add(ca);
		}
		pager.setList(newList);
		return pager;
	}

	/**
	 * 描述：该方法作用
	 * 
	 * @author zhangZhenxing
	 * @Date 2015年7月27日
	 */
	@Override
	public Integer saveCustomer(CustomerAppoit entity) {
		Integer carId = entity.getInfoCar().getCarId();
		InfoCar infoCar = carDao.get(carId);
		// 客户信息实体
		InfoCustome customer = customerDao.get(entity.getInfoCustomer()
				.getCustomerId());
		entity.setInfoCar(infoCar);
		entity.setInfoCustomer(customer);
		entity.setCreatetime(new Date(System.currentTimeMillis()));
		entity.setDel(false);
		// 判断客户与车辆关系是否已经存在，若不存在则新增
		BigInteger count = carCusMapDao.mappingCount(carId,
				customer.getCustomerId());
		if (count.intValue() == 0) {
			CarCustmerMapping mapEntity = new CarCustmerMapping();
			mapEntity.setInfoCar(infoCar);
			mapEntity.setInfoCustome(customer);
			carCusMapDao.save(mapEntity);
		}
		entity.setCusorderNO(createYYOrderNO());
		return dao.save(entity);
	}

	/**
	 * 描述：该方法作用
	 * 
	 * @author zhangZhenxing
	 * @Date 2015年7月27日
	 */
	@Override
	public void deleteCustomerById(Integer cusappoitId) {
		if (cusappoitId != null) {
			CustomerAppoit entity = dao.get(cusappoitId);
			entity.setDel(true);
			dao.update(entity);
		}
	}

	/**
	 * 描述：该方法作用
	 * 
	 * @author zhangZhenxing
	 * @Date 2015年7月27日
	 */
	@Override
	public void editCustomer(CustomerAppoit entity) {
		// 持久对象
		CustomerAppoit customer = dao.get(entity.getCusappoitId());
		// 车辆信息对象
		Integer carId = entity.getInfoCar().getCarId();
		InfoCar carEntity = carDao.get(carId);
		// 设置信息
		customer.setAppname(entity.getAppname());
		customer.setApptel(entity.getApptel());
		customer.setAppoittype(entity.getAppoittype());
		customer.setAppoittime(entity.getAppoittime());
		customer.setEmpId(entity.getEmpId());
		customer.setRemark(entity.getRemark());
		customer.setDescription(entity.getDescription());
	}

	/**
	 * 生成预约单号
	 * */
	private String createYYOrderNO() {
		Integer maxOrderId = dao.maxOrderID();
		if(maxOrderId==null){
			maxOrderId=0;
		}
		Integer max = maxOrderId + 1;
		Date date = new Date(System.currentTimeMillis());
		DateFormat format = new SimpleDateFormat("yyyyMMdd");
		String NO = String.format("%04d", max);
		return "YYDD" + format.format(date) + NO;
	}

	@Override
	public List<Map<Integer, Integer>> getCountCus() {
		/**
		* 描述：该方法作用
		* @author zhangZhenxing
		* @Date 2015年8月15日
		*/
		return dao.getOrderCount();
	}
	
	@Override
	public CustomerAppoit get(Integer id){
		CustomerAppoit entity = dao.get(id);
		String last = entrusDao.getLastComeTime(id);
		if(entity != null){
			entity.setLastTime(last);
		}else
			entity = super.get(id);
		
		return entity;
	}

}
