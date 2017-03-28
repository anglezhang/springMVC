package com.zoomoor.jy.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zoomoor.admin.entity.SysUser;
import com.zoomoor.common.base.bean.Pager;
import com.zoomoor.common.base.service.impl.BaseSvcImpl;
import com.zoomoor.jy.dao.InfoSupDao;
import com.zoomoor.jy.entity.InfoSup;
import com.zoomoor.jy.service.InfoSupSvc;
@Service
public class InfoSupSvcImpl extends BaseSvcImpl<InfoSup, Integer> implements InfoSupSvc {
	@Resource
	private InfoSupDao dao;

	@Autowired
	public void setBaseDao(InfoSupDao dao) {
		super.setBaseDao(dao);
	}


	@Override
	public InfoSup deleteById(Integer id) {
		InfoSup bean = dao.get(id);
		bean.setDel(true);
		dao.update(bean);
		return bean;
	}
	
	@Override
	public InfoSup[] deleteByIds(Integer[] ids) {
		InfoSup[] beans = new InfoSup[ids.length];
		for (int i = 0, len = ids.length; i < len; i++) {
			beans[i] = deleteById(ids[i]);
		}
		return beans;
	}

	@Override
	public InfoSup updateById(InfoSup infosub, Integer id) {
		InfoSup newInfoSub= dao.get(id);
		newInfoSub.setName(infosub.getName());
		newInfoSub.setCode(infosub.getCode());
		newInfoSub.setShortName(infosub.getShortName());
		newInfoSub.setLegalPerson(infosub.getLegalPerson());
		newInfoSub.setZipCode(infosub.getZipCode());
		newInfoSub.setPhone(infosub.getPhone());
		newInfoSub.setAddress(infosub.getAddress());
		newInfoSub.setFaxNum(infosub.getFaxNum());
		newInfoSub.setLinkMan(infosub.getLinkMan());
		newInfoSub.setBank(infosub.getBank());
		newInfoSub.setBankNo(infosub.getBankNo());
		newInfoSub.setTaxNo(infosub.getTaxNo());
		newInfoSub.setFinanceNo(infosub.getFinanceNo());
		newInfoSub.setRegCapital(infosub.getRegCapital());
		newInfoSub.setGrade(infosub.getGrade());
		newInfoSub.setBusinessScope(infosub.getBusinessScope());
		dao.update(newInfoSub);
		return newInfoSub;
	}

	@Override
	public List<InfoSup> getListByCode(String code) {
		// TODO Auto-generated method stub
		return dao.getListByCode(code);
	}


	@Override
	public Pager getPage(String queryName, String queryCode,
			String queryLinkMan, String queryPhone, int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		return dao.getPage(queryName, queryCode, queryLinkMan, queryPhone, pageNo, pageSize);
	}
}
