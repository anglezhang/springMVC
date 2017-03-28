package com.zoomoor.jy.service.impl;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zoomoor.common.base.bean.Pager;
import com.zoomoor.common.base.service.impl.BaseSvcImpl;
import com.zoomoor.jy.dao.CarCustmerMappingDao;
import com.zoomoor.jy.dao.CustomerAppoitDao;
import com.zoomoor.jy.dao.InfoBrandDao;
import com.zoomoor.jy.dao.InfoCarDao;
import com.zoomoor.jy.dao.InfoCustomeDao;
import com.zoomoor.jy.entity.CarCustmerMapping;
import com.zoomoor.jy.entity.InfoBrand;
import com.zoomoor.jy.entity.InfoCar;
import com.zoomoor.jy.entity.InfoCustome;
import com.zoomoor.jy.service.InfoCarSvc;

/**
 * 描述：InfoCarSvcImpl.java
 * @author Zhangzhenxing
 * @Date 2015年7月24日 下午2:09:53
 * @version 1.0
 */
@Service
public class InfoCarSvcImpl extends BaseSvcImpl<InfoCar, Integer> implements InfoCarSvc{
	
	@Resource
	private InfoCarDao dao;
	
	/**
	 * 车辆品牌信息
	 * */
	@Resource
	private InfoBrandDao brandDao;
	
	/**
	 * 车辆关系Dao
	 * */
	@Resource
	private CarCustmerMappingDao mapDao;
	
	/**
	 * 客户信息
	 * */
	@Resource 
	private InfoCustomeDao cusTomDao;
	
	@Autowired
	public void setBaseDao(InfoCarDao dao) {
		super.setBaseDao(dao);
	}

	/**
	* 描述：该方法作用
	* @author zhangZhenxing
	* @Date 2015年7月24日
	*/
	@Override
	public Pager getLookPager(Pager pager, String carNO,Integer customerId,String customerName) {
		if(customerId == null){
			customerId = 0;
		}
		return dao.getLookPager(pager, carNO,customerId,customerName);
	}

	/**
	* 描述：该方法作用
	* @author zhangZhenxing
	* @param brandName 品牌车系
	* @Date 2015年7月24日
	*/
	@Override
	public Integer saveCarInf(InfoCar infoCar,String brandName) {
		InfoBrand infoBrand = brandDao.get(infoCar.getInfoBrand().getId());
		InfoCustome customer = infoCar.getInfCustomer();
		Integer customerID = customer.getCustomerId();
		if(customer != null && customerID != null){
			InfoCustome cusEntity = cusTomDao.get(customerID);
			infoCar.setInfCustomer(cusEntity);
		}else{
			infoCar.setInfCustomer(null);
		}
		infoCar.setDel(false);
		infoCar.setInfoBrand(infoBrand);
		infoCar.setBrandName(brandName);
		return dao.save(infoCar);
	}

	/**
	* 描述：该方法作用
	* @author zhangZhenxing
	* @Date 2015年7月29日
	*/
	@Override
	public void deleteLookup(Integer carId) {
		InfoCar infoCar = dao.get(carId);
		infoCar.setDel(true);
		dao.update(infoCar);
	}

	/**
	* 描述：该方法作用
	* @author zhangZhenxing
	* @Date 2015年7月29日
	*/
	@Override
	public void lookUpdate(InfoCar infoCar,String brandName) {
		
		InfoCar entity = dao.get(infoCar.getCarId());
		//更新信息
		entity.setBrandName(brandName);
		entity.setPlatenum(infoCar.getPlatenum());
		entity.setColor(infoCar.getColor());
		InfoBrand infoBrand = brandDao.get(infoCar.getInfoBrand().getId());
		entity.setFramnum(infoCar.getFramnum());
		entity.setVinnum(infoCar.getVinnum());
		entity.setBuytime(infoCar.getBuytime());
		entity.setMileage(infoCar.getMileage());
		entity.setNextmain(infoCar.getNextmain());
		entity.setListingtime(infoCar.getListingtime());
		entity.setNextins(infoCar.getNextins());
		entity.setNextvis(infoCar.getNextvis());
		entity.setDisplacement(infoCar.getDisplacement());
		entity.setIsAuto(infoCar.getIsAuto());
		entity.setFactoryDate(infoCar.getFactoryDate());
		entity.setInscompany(infoCar.getInscompany());
		entity.setRemark(infoCar.getRemark());
		dao.update(entity);
	}

}
