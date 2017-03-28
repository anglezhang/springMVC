package com.cyw.mammoth.service.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cyw.common.base.service.impl.BaseSvcImpl;
import com.cyw.common.util.CommonUtil;
import com.cyw.common.util.NumberUtil;
import com.cyw.mammoth.bean.HroomType;
import com.cyw.mammoth.dao.HroomTypeDao;
import com.cyw.mammoth.service.HroomTypeSvc;
import com.cyw.mammoth.vo.RoomTypeVO;
@Service
public class HroomTypeSvcImpl extends BaseSvcImpl<HroomType, Integer> implements
		HroomTypeSvc {

	@Autowired
	HroomTypeDao hroomTypeDao;
	
	@Autowired
	public void setBaseDao(HroomTypeDao hroomTypeDao) {
		super.setBaseDao(hroomTypeDao);
	}

	@Override
	public List<RoomTypeVO> findListBy(Integer status) {
		return hroomTypeDao.findListBy(status==null?0:status);
	}

	@Override
	public void updateStatusById(String[] ids,Integer status) {
		if(ids!=null){
			for(String id:ids){
				HroomType hroomType=hroomTypeDao.get(Integer.valueOf(id));
				// 如果code_kind = 1 （不可修改），即为系统增加不允许删除
				if(hroomType == null || hroomType.getCodeKind() == 1) continue ;
				hroomType.setStatus(status);
				hroomTypeDao.update(hroomType);
			}
		}
	}

	@Override
	public void saveOrUpdateOrDel(List<HroomType> hroomTypes, String delIds) {
		boolean delBool = StringUtils.isNotBlank(delIds) ;
		if(hroomTypes !=null && hroomTypes.size() > 0){
			for (HroomType hroomType : hroomTypes) {
				hroomType.setPrice(Double.valueOf(NumberUtil.moneyFormat2TwoDigit(String.valueOf(hroomType.getPrice()))));
				// 修改
				if(hroomType.getId() != null){
					if(delBool && delIds.contains(hroomType.getId().toString()))
						continue ;
					hroomTypeDao.update(hroomType) ;
				}
				// 新增
				else{
					hroomTypeDao.save(hroomType) ;
				}
			}
		}
		// 删除
		if(StringUtils.isNotBlank(delIds)){
			String[] strArr = CommonUtil.array_unique(delIds.split(",")) ;
			this.updateStatusById(strArr,1);
		}
	}
	
}
