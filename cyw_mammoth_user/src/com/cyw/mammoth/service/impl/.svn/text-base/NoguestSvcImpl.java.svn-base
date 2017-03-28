package com.cyw.mammoth.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cyw.common.base.service.impl.BaseSvcImpl;
import com.cyw.mammoth.bean.GstBill;
import com.cyw.mammoth.bean.Noguest;
import com.cyw.mammoth.bean.Parameter;
import com.cyw.mammoth.dao.GstBillDao;
import com.cyw.mammoth.dao.NoguestDao;
import com.cyw.mammoth.dao.ParameterDao;
import com.cyw.mammoth.service.NoguestSvc;
import com.cyw.mammoth.vo.NoguestSelectVO;

@Service
@Transactional
public class NoguestSvcImpl extends BaseSvcImpl<Noguest, Integer> implements NoguestSvc {
	
	@Autowired
	private NoguestDao noguestDao;
	@Autowired
	ParameterDao paramDao;
	@Autowired
	GstBillDao gstBillDao;
	@Autowired
	public void setBaseDao(NoguestDao noguestDao) {
		super.setBaseDao(noguestDao);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public List getNoguestList(NoguestSelectVO noguestSelectVO) {
		// TODO Auto-generated method stub
		List list = noguestDao.getNoguestList(noguestSelectVO);
		return list;
	}

	@Override
	public List getCompanyData(String comp_type) {
		return noguestDao.getCompanyData(comp_type);
	}

	@Override
	public int insertNoGuest(Noguest noguest) {
		Parameter param= paramDao.get(1);
		int biil_id = param.getPara1()+1;
		noguest.setBillId(biil_id);
		noguest.setBorrow(0.00);
		param.setPara1(biil_id);
		paramDao.update(param);
		GstBill gstBill = gstBillDao.get(noguest.getBillId());
		//如果不存在则新增，存在则更新
		if (gstBill == null) {
			gstBill = new GstBill();
			gstBill.setBillId(noguest.getBillId());
			gstBill.setLimit(noguest.getLimit());
			gstBillDao.save(gstBill);
		} else {
			gstBill.setLimit(noguest.getLimit());
			gstBillDao.update(gstBill);
		}
		return noguestDao.save(noguest);
		
	}

	@Override
	public List getSelectNoguest(Map map) {
		return noguestDao.getSelectNoguest(map);
	}

	@Override
	public List selectSignleNoguest(int id) {
		return noguestDao.selectSignleNoguest(id);
	}

	@Override
	public void updateSigleNoguest(Noguest noguest) {
		noguestDao.updateSigleNoguest(noguest);
		GstBill gstBill = gstBillDao.get(noguest.getBillId());
		gstBill.setLimit(noguest.getLimit());
		gstBillDao.update(gstBill);
	}

	@Override
	public List<Noguest> selectNoguest(int id) {
		return noguestDao.selectNoguest(id);
	}

	@Override
	public void updateStatusCancle(int id) {
		noguestDao.updateStatusCancle(id);
		
	}

	@Override
	public Map getNoguestInfo(int id) {
		// TODO Auto-generated method stub
		Map map = noguestDao.getNoguestInfo(id);
		return map;
	}

}
