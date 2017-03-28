package com.cyw.mammoth.service.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cyw.common.base.service.impl.BaseSvcImpl;
import com.cyw.mammoth.bean.Fguest;
import com.cyw.mammoth.dao.FguestDao;
import com.cyw.mammoth.service.FguestSvc;
import com.cyw.mammoth.vo.FguestInfoVO;
import com.cyw.mammoth.vo.FguestSearchVo;
import com.cyw.mammoth.vo.FguestVo;

@Service
public class FguestSvcImpl extends BaseSvcImpl<Fguest, Integer> implements FguestSvc{
	
	private FguestDao fguestDao;
	@Autowired
	public void setFguestDao(FguestDao fguestDao) {
		this.fguestDao = fguestDao;
		super.setBaseDao(fguestDao);
	}
	@Override
	public List<FguestVo> search(FguestVo searchVo) {
		return fguestDao.search(searchVo);
	}
	@Override
	public List<FguestInfoVO> getFguestList(FguestSearchVo fguestSearch) {
		//
		return fguestDao.fguestSearch(fguestSearch);
	}

	
}
