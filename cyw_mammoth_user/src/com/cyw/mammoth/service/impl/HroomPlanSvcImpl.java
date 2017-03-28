package com.cyw.mammoth.service.impl;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cyw.common.base.service.impl.BaseSvcImpl;
import com.cyw.common.util.CommonUtil;
import com.cyw.mammoth.bean.HroomPlan;
import com.cyw.mammoth.bean.HroomPlanList;
import com.cyw.mammoth.bean.Rooms;
import com.cyw.mammoth.dao.HroomPlanDao;
import com.cyw.mammoth.dao.HroomPlanListDao;
import com.cyw.mammoth.dao.ParameterDao;
import com.cyw.mammoth.service.HroomDefineSvc;
import com.cyw.mammoth.service.HroomPlanSvc;
import com.cyw.mammoth.vo.HroomPlanListVO;
import com.cyw.mammoth.vo.HroomPlanVo;
@Service
public class HroomPlanSvcImpl extends BaseSvcImpl<HroomPlan, Integer> implements
		HroomPlanSvc {
	@Autowired
	HroomPlanDao hroomPlanDao;
	@Autowired
	HroomPlanListDao hroomPlanListDao ;
	@Autowired
	ParameterDao parameterDao ;
	@Autowired
	private HroomDefineSvc hroomDefineSvc ;
	@Autowired
	public void setBaseDao(HroomPlanDao hroomPlanDao) {
		super.setBaseDao(hroomPlanDao);
	}
	@Override
	public List<HroomPlanVo> findListBy(Integer status) throws Exception {
		return hroomPlanDao.findListBy(null, status==null?0:status);
	}

	@Override
	public void updateStatusById(String[] ids, Integer status) throws Exception  {
		if(ids!=null){
			for(String id:ids){
				HroomPlan entity=hroomPlanDao.get(Integer.valueOf(id));
				if(entity == null) continue ;
				entity.setStatus(status);
				hroomPlanDao.update(entity);
				hroomPlanDao.deleteHroomPlanListBy(id , status , status == 0 ? 1 : 0) ;
			}
		}
	}

	@Override
	public void saveOrUpdateOrDel(List<HroomPlanVo> hroomPlans, String delIds,
			String operId) throws Exception {
		boolean delBool = StringUtils.isNotBlank(delIds) ;
		if(hroomPlans !=null && hroomPlans.size() > 0){
			HroomPlan hp = null ;
			HroomPlan hroomPlan = null ;
			Integer hroomPlanId = null ;
			for (HroomPlanVo hroomPlanVO : hroomPlans) {
			    hroomPlan = hroomPlanVO.getHroomPlan() ;
			    hroomPlan.setStartDate(Timestamp.valueOf(hroomPlanVO.getStartDate().concat(" 00:00:00")));
				hroomPlan.setEndDate(Timestamp.valueOf(hroomPlanVO.getEndDate().concat(" 23:59:59")));
			    hp = hroomPlanDao.get(new String[]{"codeId" , "status"}, new Object[]{hroomPlan.getCodeId() , 0}) ;
			    
			    hroomPlanId = hroomPlan.getId() ;
			    // 修改
				if(hroomPlanId != null){
					// 该对象同时存在被删除的对象中或者该对象中的在数据库重复  
 					if(delBool && delIds.contains(hroomPlanId.toString()) || (hp !=null && hp.getId().intValue() != hroomPlan.getId().intValue()))
						continue ;
 					if(hp!=null)
	 					// 从session中移除相同的对象
 						hroomPlanDao.evict(hp);
 					hroomPlanDao.update(hroomPlan) ;
 					saveOrUpdateOrDel_hrommPlanList(hroomPlanId , hroomPlan.getCodeId() , hroomPlanVO.getHroomPlanListVOs()) ;
				}
				// 新增
				else{
					if(hp == null){
						hroomPlanId = hroomPlanDao.save(hroomPlan) ;
						saveOrUpdateOrDel_hrommPlanList(hroomPlanId , hroomPlan.getCodeId() , hroomPlanVO.getHroomPlanListVOs()) ;
					}
				}
			}
		}
		// 删除
		if(delBool){
			String[] strArr = CommonUtil.array_unique(delIds.split(",")) ;
			this.updateStatusById(strArr , 1 );
			hroomPlanDao.deleteHroomPlanListBy(delIds , 1 , 0) ;
		}
	}

	private void saveOrUpdateOrDel_hrommPlanList(Integer hroomPlanId , String hroomPlanCodeId , List<HroomPlanListVO> hroomPlanListVOs) throws Exception{
		
		if(hroomPlanListVOs !=null && hroomPlanListVOs.size() > 0){
			HroomPlanList hroomPlanList = null ;
			HroomPlanList hpl = null ;
			for (HroomPlanListVO hroomPlanListVO : hroomPlanListVOs) {
				hroomPlanList = hroomPlanListVO.getHroomPlanList();
				hroomPlanList.setRmplanId(hroomPlanCodeId) ;
				if("a".equals(hroomPlanListVO.getFlag())){
					hroomPlanListDao.save(hroomPlanList) ;
				}else if("u".equals(hroomPlanListVO.getFlag())){
					hpl = hroomPlanListDao.get(hroomPlanList.getId()) ;
					hroomPlanList.setChainId(hpl.getChainId());
					hroomPlanList.setDiscount(hpl.getDiscount());
					hroomPlanList.setMemo(hpl.getMemo());
					hroomPlanList.setVilhotelId(hpl.getVilhotelId());
					// 从session中移除相同的对象
					hroomPlanListDao.evict(hpl);
					hroomPlanListDao.update(hroomPlanList);
				}else if("d".equals(hroomPlanListVO.getFlag())){
					hpl = hroomPlanListDao.get(hroomPlanList.getId()) ;
					// 从session中移除相同的对象
					hroomPlanListDao.evict(hroomPlanList);
					hpl.setStatus(-1);
					hroomPlanListDao.update(hpl);
				} 
			}
		}
	}
	@Override
	public List<HroomPlanVo> findAvilListBy(Integer status, Date startDate , Integer roomplanType , String roomId , String currentRoomPlanType) {
		if(StringUtils.isNotBlank(roomId)){
			Rooms room = hroomDefineSvc.get("roomId", roomId.trim()) ;
			return hroomPlanDao.findAvilListBy(status==null?0:status, startDate == null ? parameterDao.GetHotelDate() : startDate , roomplanType , room.getRoomType() , currentRoomPlanType);
		}else{
			return hroomPlanDao.findAvilListBy(status==null?0:status, startDate == null ? parameterDao.GetHotelDate() : startDate , roomplanType);
		}
	}
	@Override
	public String findHroomPlanPrice(String roomplanCode, String roomId) {
		if(StringUtils.isNotBlank(roomplanCode) && StringUtils.isNotBlank(roomId)){
			Rooms room = hroomDefineSvc.get("roomId", roomId.trim()) ;
			return hroomPlanDao.findHroomPlanPrice(roomplanCode, room.getRoomType());
		}else
			return "-1";
	}
	
}
