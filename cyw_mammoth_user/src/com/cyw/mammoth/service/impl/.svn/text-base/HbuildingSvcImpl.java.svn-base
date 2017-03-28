package com.cyw.mammoth.service.impl;

import java.util.Calendar;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cyw.common.base.service.impl.BaseSvcImpl;
import com.cyw.common.util.CommonUtil;
import com.cyw.mammoth.bean.Hbuilding;
import com.cyw.mammoth.dao.HbuildingDao;
import com.cyw.mammoth.service.HbuildingSvc;
import com.cyw.mammoth.vo.HbuildingVo;
@Service
@Transactional
public class HbuildingSvcImpl extends BaseSvcImpl<Hbuilding, Integer> implements
		HbuildingSvc {
	@Autowired
	HbuildingDao hbuildingDao;
	
	@Autowired
	public void setBaseDao(HbuildingDao dao) {
		super.setBaseDao(dao);
	}

	@Override
	public List<HbuildingVo> getBuildList(Integer status) {
		return hbuildingDao.getBuildList(status);
	}

	@Override
	public void updateStatusById(String[] ids , Integer status) {
		if(ids!=null){
			for(String id:ids){
				Hbuilding build=hbuildingDao.get(Integer.valueOf(id));
				// 如果code_kind = 1 （不可修改），即为系统增加不允许删除
				if(build == null || build.getCodeKind() == 1) continue ;
				build.setStatus(status);
				hbuildingDao.update(build);
			}
		}
	}

	@Override
	public List<?> getPrepareCall() {
		return hbuildingDao.getPrepareCall();
	}

	@Override
	public void saveOrUpdateOrDel(List<Hbuilding> hbuildings, String delIds , String operId) {
		boolean delBool = StringUtils.isNotBlank(delIds) ;
		if(hbuildings !=null && hbuildings.size() > 0){
			for (Hbuilding hbuilding : hbuildings) {
				hbuilding.setOperId(operId) ;
				hbuilding.setUpdateDate(Calendar.getInstance().getTime()) ;
				
				// 修改
				if(hbuilding.getId() != null){
					if(delBool && delIds.contains(hbuilding.getId().toString()))
						continue ;
					hbuildingDao.update(hbuilding) ;
				}
				// 新增
				else{
					hbuildingDao.save(hbuilding) ;
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
