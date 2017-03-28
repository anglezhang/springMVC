package com.cyw.mammoth.service.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cyw.common.base.service.impl.BaseSvcImpl;
import com.cyw.common.util.CommonUtil;
import com.cyw.mammoth.bean.Hconsume;
import com.cyw.mammoth.dao.HconsumeDao;
import com.cyw.mammoth.service.HconsumeSvc;
@Service
public class HconsumeSvcImpl extends BaseSvcImpl<Hconsume, Integer> implements HconsumeSvc {
	
	@Autowired
	private HconsumeDao hconsumeDao;
	
	@Autowired
	public void setBaseDao(HconsumeDao dao) {
		super.setBaseDao(dao);
	}

	@SuppressWarnings("rawtypes")
	@Override
	public List getConsumeList(String cons) {
		String[] consArr = cons.split(",");
		StringBuilder sb = new StringBuilder();
		for(String str:consArr){
			sb.append("'"+str+"'");
			sb.append(",");
		}
		sb.deleteCharAt(sb.length()-1);
		List list = hconsumeDao.getConsumeList(sb.toString());
		return list;
	}
	
	@Override
	public void updateStatusById(String[] ids, Integer status) throws Exception {
		if(ids!=null){
			for(String id:ids){
				Hconsume Hconsume=hconsumeDao.get(Integer.valueOf(id));
				// 如果code_kind = 1 （不可修改），即为系统增加不允许删除
				if(Hconsume == null||Hconsume.getCodeKind() == 1) continue ;
				Hconsume.setStatus(status);
				hconsumeDao.update(Hconsume);
			}
		}
	}

	@Override
	public void saveOrUpdateOrDel(List<Hconsume> hconsumes, String delIds)
			throws Exception {
		boolean delBool = StringUtils.isNotBlank(delIds) ;
		if(hconsumes !=null && hconsumes.size() > 0){
			for (Hconsume Hconsume : hconsumes) {
				// 修改
				if(Hconsume.getId() != null){
					if(delBool && delIds.contains(Hconsume.getId().toString()))
						continue ;
					hconsumeDao.update(Hconsume) ;
				}
				// 新增
				else{
					hconsumeDao.save(Hconsume) ;
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
