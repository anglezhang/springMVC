package com.cyw.mammoth.service.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cyw.common.base.service.impl.BaseSvcImpl;
import com.cyw.common.util.CommonUtil;
import com.cyw.mammoth.bean.Hfolk;
import com.cyw.mammoth.dao.HfolkDao;
import com.cyw.mammoth.service.HfolkSvc;
@Service
public class HfolkSvcImpl extends BaseSvcImpl<Hfolk, Integer> implements HfolkSvc {
	@Autowired
	HfolkDao dao;
	@Autowired
	public void setBaseDao(HfolkDao dao) {
		super.setBaseDao(dao);
	}
	
	@Override
	public List<Hfolk> findListBy(Integer status) {
		return dao.findListBy(status==null?0:status);
	}

	@Override
	public void updateStatusById(String[] ids, Integer status) throws Exception {
		if(ids!=null){
			for(String id:ids){
				Hfolk hfolk=dao.get(Integer.valueOf(id));
				if(hfolk == null) continue ;
				hfolk.setStatus(status);
				dao.update(hfolk);
			}
		}
	}

	@Override
	public void saveOrUpdateOrDel(List<Hfolk> hfolks, String delIds)
			throws Exception {
		boolean delBool = StringUtils.isNotBlank(delIds) ;
		if(hfolks !=null && hfolks.size() > 0){
			Hfolk hfk = null ;
			for (Hfolk hfolk : hfolks) {
				hfk = dao.get(new String[]{"codeId" , "status"}, new Object[]{hfolk.getCodeId() , 0}) ;
				// 修改
				if(hfolk.getId() != null){
					// 该对象同时存在被删除的对象中或者该对象中的日期（holidayDate）在数据库重复  
 					if(delBool && delIds.contains(hfolk.getId().toString()) || (hfk !=null && hfk.getId().intValue() != hfolk.getId().intValue()))
						continue ;
 					if(hfk!=null)
	 					// 从session中移除相同的对象
	 					dao.evict(hfk);
					dao.update(hfolk) ;
				}
				// 新增
				else{
					if(hfk == null)
						dao.save(hfolk) ;
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
