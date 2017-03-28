package com.cyw.mammoth.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cyw.common.base.service.impl.BaseSvcImpl;
import com.cyw.mammoth.bean.Hcodes;
import com.cyw.mammoth.dao.HbuildingDao;
import com.cyw.mammoth.dao.HcodesDao;
import com.cyw.mammoth.dao.HgstOriDao;
import com.cyw.mammoth.service.HcodesSvc;
import com.cyw.mammoth.vo.GuestSourceVO;
@Service
public class HcodesSvcImpl extends BaseSvcImpl<Hcodes, Integer> implements HcodesSvc {
	@Autowired
	HcodesDao dao;
	
	/**
	 * @author zhangzhenx
	 * @date 2015-12-11
	 * @描述 客人来源DAO
	 * */
	@Autowired
	private HgstOriDao hGstoriDao;
	@Autowired
	public void setBaseDao(HcodesDao dao) {
		super.setBaseDao(dao);
	}
	@Override
	public List<Hcodes> getListByCodes(String codeId) {
		
		return dao.getListByCodes(codeId);
	}
	
	@Override
	public List<GuestSourceVO> getGuestSource() {
		// TODO Auto-generated method stub
		return hGstoriDao.getGuestSource();
	}

}
