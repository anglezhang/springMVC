package com.cyw.mammoth.service.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cyw.common.base.service.impl.BaseSvcImpl;
import com.cyw.common.util.CommonUtil;
import com.cyw.mammoth.bean.Hsettle;
import com.cyw.mammoth.dao.HsettleDao;
import com.cyw.mammoth.service.HsettleSvc;
@Service
public class HsettleSvcImpl extends BaseSvcImpl<Hsettle, Integer> implements HsettleSvc {
	
	@Autowired
	HsettleDao hsettleDao;
	@Autowired
	public void setBaseDao(HsettleDao hsettleDao) {
		super.setBaseDao(hsettleDao);
	}
	@Override
	public List<Hsettle> findListBy(Integer status) {
		return hsettleDao.findListBy(status==null?0:status);
	}

	@Override
	public void updateStatusById(String[] ids, Integer status) throws Exception {
		if(ids!=null){
			for(String id:ids){
				Hsettle hsettle=hsettleDao.get(Integer.valueOf(id));
				if(hsettle == null) continue ;
				hsettle.setStatus(status);
				hsettleDao.update(hsettle);
			}
		}
	}

	@Override
	public void saveOrUpdateOrDel(List<Hsettle> hsettles, String delIds)
			throws Exception {
		boolean delBool = StringUtils.isNotBlank(delIds) ;
		if(hsettles !=null && hsettles.size() > 0){
			Hsettle hst = null ;
			for (Hsettle hsettle : hsettles) {
				hst = hsettleDao.get(new String[]{"codeId" , "status"}, new Object[]{hsettle.getCodeId() , 0}) ;
				// 修改
				if(hsettle.getId() != null){
					// 该对象同时存在被删除的对象中或者该对象中的日期（holidayDate）在数据库重复  
 					if(delBool && delIds.contains(hsettle.getId().toString()) || (hst !=null && hst.getId().intValue() != hsettle.getId().intValue()))
						continue ;
 					if(hst!=null)
	 					// 从session中移除相同的对象
 						hsettleDao.evict(hst);
 					hsettleDao.update(hsettle) ;
				}
				// 新增
				else{
					if(hst == null)
						hsettleDao.save(hsettle) ;
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
