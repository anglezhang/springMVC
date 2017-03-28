package com.cyw.mammoth.service.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cyw.common.base.service.impl.BaseSvcImpl;
import com.cyw.common.util.CommonUtil;
import com.cyw.mammoth.bean.HfunctionWorkgroupList;
import com.cyw.mammoth.bean.WorkGroup;
import com.cyw.mammoth.dao.HfunctionWorkgroupListDao;
import com.cyw.mammoth.dao.WorkGroupDao;
import com.cyw.mammoth.service.WorkGroupSvc;
import com.cyw.mammoth.vo.HfunctionVO;
import com.cyw.mammoth.vo.WorkGroupVO;
@Service
public class WorkGroupSvcImpl extends BaseSvcImpl<WorkGroup, Integer> implements
		WorkGroupSvc {
	
	@Autowired
	private WorkGroupDao workGroupDao ;
	@Autowired
	private HfunctionWorkgroupListDao hfunctionWorkgroupListDao ;
	@Autowired
	public void setBaseDao(WorkGroupDao workGroupDao) {
		super.setBaseDao(workGroupDao);
	}
	
	@Override
	public List<HfunctionVO> findFunctionListBy(String funcType, String groupId) throws Exception {
		if(StringUtils.isBlank(funcType)||StringUtils.isBlank(groupId))
			return null ;
		return workGroupDao.findFunctionListBy(funcType , groupId);
	}

	@Override
	public void updateStatusById(String[] ids, Integer status) {
		if(ids!=null){
			for(String id:ids){
				WorkGroup workGroup=workGroupDao.get("groupId",id) ;
				if(workGroup == null) continue ;
				workGroup.setStatus(status);
				workGroupDao.update(workGroup);
			}
		}
	}

	@Override
	public void saveOrUpdateOrDel(List<WorkGroup> workGroups, String delIds) throws Exception {
		boolean delBool = StringUtils.isNotBlank(delIds);
		if(workGroups !=null && workGroups.size() > 0){
			WorkGroup wg = null ;
			for (WorkGroup workGroup : workGroups) {
				// 查询新增或者修改的对象中在数据库中是否存在对应的对象
				wg = workGroupDao.get("groupId", workGroup.getGroupId()) ;
				
				// 修改
				if(wg != null){
					// 该对象同时存在被删除的对象中或者该对象中的 在数据库重复  
					if((delBool && delIds.contains(workGroup.getGroupId()) ))
						continue ;
 					// 从session中移除相同的对象
					workGroupDao.evict(wg);
					workGroupDao.update(workGroup) ;
				}
				// 新增
				else{
					workGroupDao.save(workGroup) ;
				}
			}
		}
		// 删除
		if(StringUtils.isNotBlank(delIds)){
			String[] strArr = CommonUtil.array_unique(delIds.split(",")) ;
			this.updateStatusById(strArr,1);
		}
	}

	@Override
	public void saveWorkGroupAuth(
			List<HfunctionWorkgroupList> hfunctionWorkgroupLists, String workGroupId , String hfunctionGroup) throws Exception {
		hfunctionWorkgroupListDao.deleteBy(workGroupId , hfunctionGroup) ;
		if(hfunctionWorkgroupLists != null && hfunctionWorkgroupLists.size()>0){
			for (HfunctionWorkgroupList hfunctionWorkgroupList : hfunctionWorkgroupLists) {
				hfunctionWorkgroupListDao.save(hfunctionWorkgroupList) ;
			}
		}
	}
	@Override
	public List<WorkGroupVO> findListBy(Integer status) throws Exception {
		return workGroupDao.findListBy(status == null ? 0 : status);
	}
	
	
}
