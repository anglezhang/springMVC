package com.cyw.mammoth.service.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cyw.common.base.service.impl.BaseSvcImpl;
import com.cyw.common.util.CommonUtil;
import com.cyw.mammoth.bean.Hexchange;
import com.cyw.mammoth.dao.HexchangeDao;
import com.cyw.mammoth.service.HexchangeSvc;
@Service
public class HexchangeSvcImpl extends BaseSvcImpl<Hexchange, Integer> implements
		HexchangeSvc {
	
	@Autowired
	HexchangeDao hexchangeDao;
	@Autowired
	public void setBaseDao(HexchangeDao dao) {
		super.setBaseDao(dao);
	}
	
	@Override
	public void updateStatusById(String[] ids, Integer status) throws Exception {
		if(ids!=null){
			for(String id:ids){
				Hexchange hexchange=hexchangeDao.get(Integer.valueOf(id));
				if(hexchange == null) continue ;
				hexchange.setStatus(status);
				hexchangeDao.update(hexchange);
			}
		}
	}

	@Override
	public void saveOrUpdateOrDel(List<Hexchange> hexchanges, String delIds)
			throws Exception {
		boolean delBool = StringUtils.isNotBlank(delIds) ;
		if(hexchanges !=null && hexchanges.size() > 0){
			for (Hexchange hexchange : hexchanges) {
				hexchange.setFlag("a");
				// 修改
				if(hexchange.getId() != null){
					if(delBool && delIds.contains(hexchange.getId().toString()))
						continue ;
					hexchangeDao.update(hexchange) ;
				}
				// 新增
				else{
					hexchangeDao.save(hexchange) ;
				}
			}
		}
		// 删除
		if(delBool){
			String[] strArr = CommonUtil.array_unique(delIds.split(",")) ;
			this.updateStatusById(strArr , 1 );
		}
	}
}
