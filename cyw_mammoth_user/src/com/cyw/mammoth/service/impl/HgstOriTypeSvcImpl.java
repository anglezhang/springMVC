package com.cyw.mammoth.service.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cyw.common.base.service.impl.BaseSvcImpl;
import com.cyw.common.util.CommonUtil;
import com.cyw.mammoth.bean.HgstOriType;
import com.cyw.mammoth.dao.HgstOriTypeDao;
import com.cyw.mammoth.service.HgstOriTypeSvc;

@Service
public class HgstOriTypeSvcImpl extends BaseSvcImpl<HgstOriType, Integer> implements HgstOriTypeSvc {

	@Autowired
	private HgstOriTypeDao hgstOriTypeDao ;
	
	@Autowired
	public void setBaseDao(HgstOriTypeDao hgstOriTypeDao) {
		super.setBaseDao(hgstOriTypeDao);
	}

	@Override
	public List<HgstOriType> findListBy(Integer status) throws Exception {
		return hgstOriTypeDao.findListBy(status==null?0:status);
	}

	@Override
	public void updateStatusById(String[] ids, Integer status) throws Exception {
		if(ids!=null){
			for(String id:ids){
				HgstOriType hgstOri=hgstOriTypeDao.get(Integer.valueOf(id));
				if(hgstOri == null) continue ;
				hgstOri.setStatus(status);
				hgstOriTypeDao.update(hgstOri);
			}
		}
		
	}

	@Override
	public void saveOrUpdateOrDel(List<HgstOriType> hgstOriTypes, String delIds)
			throws Exception {
		boolean delBool = StringUtils.isNotBlank(delIds) ;
		if(hgstOriTypes !=null && hgstOriTypes.size() > 0){
			HgstOriType hfk = null ;
			for (HgstOriType hgstOri : hgstOriTypes) {
				hfk = hgstOriTypeDao.get(new String[]{"codeId" , "status"}, new Object[]{hgstOri.getCodeId() , 0}) ;
				// 修改
				if(hgstOri.getId() != null){
					// 该对象同时存在被删除的对象中或者该对象中的codeId（codeId）在数据库重复  
 					if(delBool && delIds.contains(hgstOri.getId().toString()) || (hfk !=null && hfk.getId().intValue() != hgstOri.getId().intValue()))
						continue ;
 					if(hfk!=null)
	 					// 从session中移除相同的对象
 						hgstOriTypeDao.evict(hfk);
 					hgstOriTypeDao.update(hgstOri) ;
				}
				// 新增
				else{
					if(hfk == null)
						hgstOriTypeDao.save(hgstOri) ;
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
