package com.cyw.mammoth.service.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cyw.common.base.service.impl.BaseSvcImpl;
import com.cyw.common.util.CommonUtil;
import com.cyw.mammoth.bean.HgstOri;
import com.cyw.mammoth.dao.HgstOriDao;
import com.cyw.mammoth.service.HgstOriSvc;

@Service
public class HgstOriSvcImpl extends BaseSvcImpl<HgstOri, Integer> implements HgstOriSvc {

	@Autowired
	HgstOriDao hgstOriDao;
	@Autowired
	public void setBaseDao(HgstOriDao hgstOriDao) {
		super.setBaseDao(hgstOriDao);
	}
	@Override
	public List<HgstOri> findListBy(Integer status) throws Exception {
		return hgstOriDao.findListBy(status==null?0:status);
	}
	@Override
	public void updateStatusById(String[] ids, Integer status) throws Exception {
		if(ids!=null){
			for(String id:ids){
				HgstOri hgstOri=hgstOriDao.get(Integer.valueOf(id));
				if(hgstOri == null) continue ;
				hgstOri.setStatus(status);
				hgstOriDao.update(hgstOri);
			}
		}
	}
	@Override
	public void saveOrUpdateOrDel(List<HgstOri> hgstOris, String delIds)
			throws Exception {
		boolean delBool = StringUtils.isNotBlank(delIds) ;
		if(hgstOris !=null && hgstOris.size() > 0){
			HgstOri hfk = null ;
			for (HgstOri hgstOri : hgstOris) {
				hfk = hgstOriDao.get(new String[]{"codeId" , "status"}, new Object[]{hgstOri.getCodeId() , 0}) ;
				// 修改
				if(hgstOri.getId() != null){
					// 该对象同时存在被删除的对象中或者该对象中的codeId（codeId）在数据库重复  
 					if(delBool && delIds.contains(hgstOri.getId().toString()) || (hfk !=null && hfk.getId().intValue() != hgstOri.getId().intValue()))
						continue ;
 					if(hfk!=null)
	 					// 从session中移除相同的对象
 						hgstOriDao.evict(hfk);
 					hgstOriDao.update(hgstOri) ;
				}
				// 新增
				else{
					if(hfk == null)
						hgstOriDao.save(hgstOri) ;
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
