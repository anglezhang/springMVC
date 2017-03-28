package com.zoomoor.jy.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zoomoor.common.base.service.impl.BaseSvcImpl;
import com.zoomoor.jy.dao.MoneyAuthDao;
import com.zoomoor.jy.dao.MoneyUserMappingDao;
import com.zoomoor.jy.entity.MoneyAuth;
import com.zoomoor.jy.entity.MoneyUserMapping;
import com.zoomoor.jy.service.MoneyAuthSvc;
@Service
public class MoneyAuthSvcImpl extends BaseSvcImpl<MoneyAuth, Integer> implements
		MoneyAuthSvc {
	@Resource
	private MoneyUserMappingDao  mappingDao;
	@Resource
	private MoneyAuthDao dao;

	@Autowired
	public void setBaseDao(MoneyAuthDao dao) {
		super.setBaseDao(dao);
	}

	@Override
	public List<MoneyAuth> getMoneyAuthListByIsChecked(
			List<MoneyAuth> moneyAuthListr,Integer rid) {
		List<MoneyAuth> newMoneyAuthList= new ArrayList<MoneyAuth>();
		for(MoneyAuth moneyAuth:moneyAuthListr){
			String[] pror={"moneyAuth.id","sysRole.roleId"};
			Object[] objetr={moneyAuth.getId(),rid};
			MoneyUserMapping mapping= (MoneyUserMapping) mappingDao.get(pror, objetr);	
			if(mapping!=null){
				if(mapping.getMoneyAuth().getId()==moneyAuth.getId()){
						moneyAuth.setIsChecked(1);
					}else{
						moneyAuth.setIsChecked(0);
				}
			}else{
				moneyAuth.setIsChecked(0);
			}
				
			newMoneyAuthList.add(moneyAuth);
		}
		return newMoneyAuthList;
	}
}
