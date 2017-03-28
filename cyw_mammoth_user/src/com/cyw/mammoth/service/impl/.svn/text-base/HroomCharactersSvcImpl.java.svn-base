package com.cyw.mammoth.service.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cyw.common.base.service.impl.BaseSvcImpl;
import com.cyw.common.util.CommonUtil;
import com.cyw.mammoth.bean.HroomCharacters;
import com.cyw.mammoth.dao.HroomCharactersDao;
import com.cyw.mammoth.service.HroomCharactersSvc;
import com.cyw.mammoth.vo.HroomCharactersVO;
@Service
public class HroomCharactersSvcImpl extends BaseSvcImpl<HroomCharacters, Integer> implements
		HroomCharactersSvc {

	@Autowired
	HroomCharactersDao hroomCharactersDao;
	
	@Autowired
	public void setBaseDao(HroomCharactersDao dao) {
		super.setBaseDao(dao);
	}
	
	@Override
	public List<HroomCharactersVO> findListBy(Integer status) {
		return hroomCharactersDao.findListBy(status==null?0:status);
	}

	@Override
	public void updateStatusById(String[] ids,Integer status) {
		if(ids!=null){
			for(String id:ids){
				HroomCharacters hroomCharacters=hroomCharactersDao.get(Integer.valueOf(id));
				// 如果code_kind = 1 （不可修改），即为系统增加不允许删除
				if(hroomCharacters == null || hroomCharacters.getCodeKind() == 1) continue ;
				hroomCharacters.setStatus(status);
				hroomCharactersDao.update(hroomCharacters);
			}
		}
	}
	
	@Override
	public void saveOrUpdateOrDel(List<HroomCharacters> hroomCharacterss,
			String delIds) {
		boolean delBool = StringUtils.isNotBlank(delIds) ;
		if(hroomCharacterss !=null && hroomCharacterss.size() > 0){
			HroomCharacters hct = null ;
			for (HroomCharacters hroomCharacters : hroomCharacterss) {
				// 查询新增或者修改的对象中的等记号（placeholder_id）在数据库中是否存在对应的对象
				hct = hroomCharactersDao.get("placeholderId", hroomCharacters.getPlaceholderId()) ;
				
				// 修改
				if(hroomCharacters.getId() != null){
					// 该对象同时存在被删除的对象中或者该对象中的等记号（placeholder_id） 在数据库重复  
					if((delBool && delIds.contains(hroomCharacters.getId().toString()) ) || (hct !=null && hct.getId().intValue() != hroomCharacters.getId().intValue()))
						continue ;
					if(hct!=null)
	 					// 从session中移除相同的对象
						hroomCharactersDao.evict(hct);
					hroomCharactersDao.update(hroomCharacters) ;
				}
				// 新增
				else{
					if(hct == null)
						hroomCharactersDao.save(hroomCharacters) ;
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
