package com.zoomoor.jy.service.impl;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zoomoor.common.base.bean.Pager;
import com.zoomoor.common.base.service.impl.BaseSvcImpl;
import com.zoomoor.jy.dao.InfoCustomeDao;
import com.zoomoor.jy.entity.InfoCustome;
import com.zoomoor.jy.service.InfoCustomeSvc;

/**
 * 描述：InfoCustomeSvcImpl 客户信息服务
 * @author Zhangzhenxing
 * @Date 2015年7月23日 下午4:17:01
 * @version 1.0
 */
@Service
public class InfoCustomeSvcImpl extends BaseSvcImpl<InfoCustome, Integer> implements InfoCustomeSvc{

	@Resource
	private InfoCustomeDao dao;
	
	@Autowired
	public void setBaseDao(InfoCustomeDao dao){
		super.setBaseDao(dao);
	}
	/**
	* 描述：该方法作用
	* @author zhangZhenxing
	* @Date 2015年7月23日
	*/
	@Override
	public Pager getLookupPager(Pager pager, String querytName,
			String queryTel, String CarNO,Integer carId) {
		
		return dao.getLookupPager(pager, querytName, queryTel, CarNO,carId);
	}
	
	/**
	* 描述：该方法作用
	* @author zhangZhenxing
	* @Date 2015年7月29日
	*/
	@Override
	public void deleteCustomerById(Integer customerId) {
		InfoCustome infoCustomer = dao.get(customerId);
		infoCustomer.setDel(true);
		dao.update(infoCustomer);
	}
	
	/**
	* 描述：该方法作用
	* @author zhangZhenxing
	* @Date 2015年7月29日
	*/
	@Override
	public void updateByCustome(InfoCustome infoCustome) {
		InfoCustome entity = dao.get(infoCustome.getCustomerId());
		entity.setGender(infoCustome.getGender());
		entity.setCustomerName(infoCustome.getCustomerName());
		entity.setTel(infoCustome.getTel());
		entity.setEamil(infoCustome.getEamil());
		entity.setProvince(infoCustome.getProvince());
		entity.setCity(infoCustome.getCity());
		entity.setDetaladdress(infoCustome.getDetaladdress());
		dao.update(entity);
	}

}
