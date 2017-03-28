package com.cyw.mammoth.service.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cyw.common.base.service.impl.BaseSvcImpl;
import com.cyw.common.util.CommonUtil;
import com.cyw.mammoth.bean.Hcountry;
import com.cyw.mammoth.dao.HcountryDao;
import com.cyw.mammoth.service.HcountrySvc;
@Service
public class HcountrySvcImpl extends BaseSvcImpl<Hcountry, Integer> implements HcountrySvc {
	@Autowired
	HcountryDao dao;
	@Autowired
	public void setBaseDao(HcountryDao dao) {
		super.setBaseDao(dao);
	}
	@Override
	public List<Hcountry> findListBy(Integer status) {
		return dao.findListBy(status==null?0:status);
	}

	@Override
	public void updateStatusById(String[] ids, Integer status) throws Exception {
		if(ids!=null){
			for(String id:ids){
				Hcountry hcountry=dao.get(Integer.valueOf(id));
				if(hcountry == null) continue ;
				hcountry.setStatus(status);
				dao.update(hcountry);
			}
		}
	}

	@Override
	public void saveOrUpdateOrDel(List<Hcountry> hcountrys, String delIds)
			throws Exception {
		boolean delBool = StringUtils.isNotBlank(delIds) ;
		if(hcountrys !=null && hcountrys.size() > 0){
			Hcountry hcy = null ;
			for (Hcountry hcountry : hcountrys) {
				hcy = dao.get(new String[]{"codeId" , "status"}, new Object[]{hcountry.getCodeId() , 0}) ;
				// 修改
				if(hcountry.getId() != null){
					// 该对象同时存在被删除的对象中或者该对象中的日期（holidayDate）在数据库重复  
 					if(delBool && delIds.contains(hcountry.getId().toString()) || (hcy !=null && hcy.getId().intValue() != hcountry.getId().intValue()))
						continue ;
 					if(hcy!=null)
	 					// 从session中移除相同的对象
	 					dao.evict(hcy);
					dao.update(hcountry) ;
				}
				// 新增
				else{
					if(hcy == null)
						dao.save(hcountry) ;
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
