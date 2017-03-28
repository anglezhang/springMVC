package com.cyw.mammoth.service.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cyw.common.base.service.impl.BaseSvcImpl;
import com.cyw.common.util.CommonUtil;
import com.cyw.mammoth.bean.Hcodes;
import com.cyw.mammoth.dao.CodeCareDao;
import com.cyw.mammoth.service.CodeCareSvc;
@Service
public class CodeCareSvcImpl extends BaseSvcImpl<Hcodes, Integer> implements CodeCareSvc {

	@Autowired
	private CodeCareDao codeCareDao ;
	
	@Autowired
	public void setBaseDao(CodeCareDao dao) {
		super.setBaseDao(dao);
	}
	@Override
	public List<Hcodes> findGETypeList() throws Exception {
		return codeCareDao.findGETypeList();
	}

	@Override
	public List<?> findSEListBy(String flag,Integer status) throws Exception  {
		return null;
	}

	@Override
	public List<Hcodes> findGEListBy(String flag,Integer status) throws Exception {
		if(StringUtils.isBlank(flag))
			return null;
		return codeCareDao.findGEListBy(flag,status==null?0:status);
	}

	@Override
	public void updateStatusById(String[] ids, Integer status) throws Exception {
		if(ids!=null){
			for(String id:ids){
				Hcodes hcode=codeCareDao.get(Integer.valueOf(id));
				// 如果code_kind = 1 （不可修改），即为系统增加不允许删除
				if(hcode == null||hcode.getCodeKind() == 1) continue ;
				hcode.setStatus(status);
				codeCareDao.update(hcode);
			}
		}
	}

	@Override
	public void saveOrUpdateOrDel(List<Hcodes> hcodes, String delIds)
			throws Exception {
		boolean delBool = StringUtils.isNotBlank(delIds) ;
		if(hcodes !=null && hcodes.size() > 0){
			for (Hcodes hcode : hcodes) {
				// 修改
				if(hcode.getId() != null){
					if(delBool && delIds.contains(hcode.getId().toString()))
						continue ;
					codeCareDao.update(hcode) ;
				}
				// 新增
				else{
					codeCareDao.save(hcode) ;
				}
			}
		}
		// 删除
		if(delBool){
			String[] strArr = CommonUtil.array_unique(delIds.split(",")) ;
			this.updateStatusById(strArr , 1 );
		}
	}

	@Override
	public List<Hcodes> findAllListBy(String flag) {
		if(StringUtils.isBlank(flag))return null;
		return codeCareDao.findAllListBy(flag);
	}
	
}
