package com.zoomoor.jy.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zoomoor.common.base.bean.Pager;
import com.zoomoor.common.base.service.impl.BaseSvcImpl;
import com.zoomoor.jy.dao.InfoCarDao;
import com.zoomoor.jy.dao.InfoEmpDao;
import com.zoomoor.jy.dao.InfoGoodsDao;
import com.zoomoor.jy.dao.OrderDao;
import com.zoomoor.jy.dao.ParamConfigDao;
import com.zoomoor.jy.entity.InfoCar;
import com.zoomoor.jy.entity.InfoEmp;
import com.zoomoor.jy.entity.InfoGoods;
import com.zoomoor.jy.entity.OrderPurchase;
import com.zoomoor.jy.entity.ParamConfig;
import com.zoomoor.jy.service.ParamConfigSvc;
@Service
public class ParamConfigSvcImpl extends BaseSvcImpl<ParamConfig, Integer> implements
		ParamConfigSvc {
	@Resource
	private ParamConfigDao dao;

	@Autowired
	public void setBaseDao(ParamConfigDao dao) {
		super.setBaseDao(dao);
	}

	@Override
	public Pager getPage(int pageNo, int pageSize) {
		return dao.getPage(pageNo, pageSize);
	}

	@Override
	public ParamConfig[] deleteByIds(Integer[] ids) {
		ParamConfig[] beans = new ParamConfig[ids.length];
		for (int i = 0, len = ids.length; i < len; i++) {
			beans[i] = deleteById(ids[i]);
		}
		return beans;
	}
	
	@Override
	public ParamConfig deleteById(Integer id) {
		ParamConfig bean = dao.get(id);
		bean.setDel(true);
		dao.update(bean);
		return bean;
	}

	@Override
	public List<ParamConfig> getListByName(String name,Integer paramType) {
		return dao.getListByName(name,paramType);
	}

	@Override
	public Boolean isUser(ParamConfig config) {
		Boolean isuser=false;
		//单位信息
		if(config.getParamType()==1){
			String[] prom={"del","paramConfig.unitId"};
			Object[] objetm={false,config.getUnitId()};
			List<InfoGoods> goodsList= goodsDao.getList(prom, objetm);
			if(goodsList!=null&& goodsList.size()>0){
				isuser=true;
				return isuser;
			}
			
		}
		//岗位名称//
		if(config.getParamType()==2){
			String[] prom={"del","paramConfig.unitId"};
			Object[] objetm={false,config.getUnitId()};
			List<InfoEmp> empList= empDao.getList(prom, objetm);
			if(empList!=null&& empList.size()>0){
				isuser=true;
				return isuser;
			}
			
		}
		//职业技能
		if(config.getParamType()==3){
			List empMappingList= empDao.getMappingList(config.getUnitId());
			if(empMappingList!=null&& empMappingList.size()>0){
				isuser=true;
				return isuser;
			}
			
		}
		//驾照
		if(config.getParamType()==5){
			String[] prom={"del","drivinglicense.unitId"};
			Object[] objetm={false,config.getUnitId()};
			List<InfoEmp> empList= empDao.getList(prom, objetm);
			if(empList!=null&& empList.size()>0){
				isuser=true;
				return isuser;
			}
			
		}
		//付款方式
		if(config.getParamType()==4){
			String[] prom={"del","paramConfig.unitId"};
			Object[] objetm={false,config.getUnitId()};
			List<OrderPurchase> orderList= orderDao.getList(prom, objetm);
			if(orderList!=null&& orderList.size()>0){
				isuser=true;
				return isuser;
			}
			
		}
		//排量
		if(config.getParamType()==6){
			String[] prom={"del","paramConfig.unitId"};
			Object[] objetm={false,config.getUnitId()};
			List<InfoCar> carList= carDao.getList(prom, objetm);
			if(carList!=null&& carList.size()>0){
				isuser=true;
				return isuser;
			}
		}
		return isuser;
	}
	@Resource
	private InfoGoodsDao goodsDao;
	@Resource
	private InfoEmpDao empDao;
	@Resource
	private OrderDao orderDao;
	@Resource
	private InfoCarDao carDao;
	
	
}
