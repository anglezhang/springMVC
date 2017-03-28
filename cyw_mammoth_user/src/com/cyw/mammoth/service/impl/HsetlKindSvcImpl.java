package com.cyw.mammoth.service.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cyw.common.base.service.impl.BaseSvcImpl;
import com.cyw.common.util.CommonUtil;
import com.cyw.mammoth.bean.HsetlKind;
import com.cyw.mammoth.dao.HsetlKindDao;
import com.cyw.mammoth.service.HsetlKindSvc;
@Service
public class HsetlKindSvcImpl extends BaseSvcImpl<HsetlKind, Integer> implements
		HsetlKindSvc {
	@Autowired
	HsetlKindDao dao;
	
	@Autowired
	public void setBaseDao(HsetlKindDao dao) {
		super.setBaseDao(dao);
	}
	@Override
	public List<HsetlKind> findListBy(Integer status) {
		return dao.findListBy(status==null?0:status);
	}

	@Override
	public void updateStatusById(String[] ids, Integer status) throws Exception {
		if(ids!=null){
			for(String id:ids){
				HsetlKind hsetlKind=dao.get(Integer.valueOf(id));
				if(hsetlKind == null) continue ;
				hsetlKind.setStatus(status);
				dao.update(hsetlKind);
			}
		}
	}

	@Override
	public void saveOrUpdateOrDel(List<HsetlKind> hsetlKinds, String delIds)
			throws Exception {
		boolean delBool = StringUtils.isNotBlank(delIds) ;
		if(hsetlKinds !=null && hsetlKinds.size() > 0){
			HsetlKind hsk = null ;
			for (HsetlKind hsetlKind : hsetlKinds) {
				hsk = dao.get(new String[]{"codeId" , "status"}, new Object[]{hsetlKind.getCodeId() , 0}) ;
				// 修改
				if(hsetlKind.getId() != null){
					// 该对象同时存在被删除的对象中或者该对象中的日期（holidayDate）在数据库重复  
 					if(delBool && delIds.contains(hsetlKind.getId().toString()) || (hsk !=null && hsk.getId().intValue() != hsetlKind.getId().intValue()))
						continue ;
 					if(hsk!=null)
	 					// 从session中移除相同的对象
	 					dao.evict(hsk);
					dao.update(hsetlKind) ;
				}
				// 新增
				else{
					if(hsk == null)
						dao.save(hsetlKind) ;
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

