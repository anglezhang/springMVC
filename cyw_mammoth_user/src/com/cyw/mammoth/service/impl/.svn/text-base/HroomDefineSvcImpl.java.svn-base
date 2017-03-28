package com.cyw.mammoth.service.impl;

import java.util.Calendar;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cyw.common.base.service.impl.BaseSvcImpl;
import com.cyw.common.util.CommonUtil;
import com.cyw.common.util.StrUtils;
import com.cyw.mammoth.bean.Rooms;
import com.cyw.mammoth.dao.HroomDefineDao;
import com.cyw.mammoth.dao.ParameterDao;
import com.cyw.mammoth.service.HroomDefineSvc;
import com.cyw.mammoth.service.ParameterSvc;
import com.cyw.mammoth.vo.RoomDefineVo;

@Service
public class HroomDefineSvcImpl extends BaseSvcImpl<Rooms, Integer> implements
		HroomDefineSvc {

	@Autowired
	private HroomDefineDao hroomDefineDao ;
	
	@Autowired
	private ParameterSvc parameterSvc ;
	
	@Autowired
	public void setBaseDao(HroomDefineDao hroomDefineDao) {
		super.setBaseDao(hroomDefineDao);
	}
	
	@Override
	public List<RoomDefineVo> findListBy(Integer status ) {
		return hroomDefineDao.findListBy(status == null ? 0 : status);
	}

	@Override
	public void updateStatusById(String[] ids , Integer status) {
		if(ids!=null){
			for(String id:ids){
				Rooms rooms=hroomDefineDao.get("roomId", id);
				if(rooms == null) continue ;
				// 记录修改之前的状态
				rooms.setModiStat(rooms.getCurrStat()) ;
				// true 含ip
				boolean bool = parameterSvc.GetIPFlag() ;
				// 只有空净房（VCI  VC）才可以置为无效
				if(status == 0){
					rooms.setCurrStat(bool ? "VCI": "VC");
				}else{
					if(bool ? !"VCI".equals(rooms.getCurrStat()) : !"VC".equals(rooms.getCurrStat())) continue ;
					rooms.setCurrStat("");
				}
				rooms.setStatus(status);
				hroomDefineDao.update(rooms);
				// status == 0 有效  增加1 否则 减少1
				updRoomTypeNumBy(rooms.getBuildId() , rooms.getRoomType() , status) ;
			}
		}
	}

	@Override
	public void updRoomTypeNumBy(String buildId, String roomType, Integer flag) {
		hroomDefineDao.updRoomTypeNumBy(buildId,roomType,flag);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void saveOrUpdateOrDel(List<Object> roomDefineVos, String delIds , String operId) {
		boolean delBool = StringUtils.isNotBlank(delIds) ;
		if(roomDefineVos !=null && roomDefineVos.size() > 0){
			Map<String, Object> map = null ;
			for (Object obj: roomDefineVos) {
				map = (Map<String, Object>)obj ;
				String editFlag = String.valueOf(map.get("editFlag"));
				String roomId = String.valueOf(map.get("roomId"));
				String roomType = String.valueOf(map.get("roomType"));
				String floorNo = String.valueOf(map.get("floorNo"));
				String currStat = String.valueOf(map.get("currStat"));
				Long roomCharacter = Long.valueOf(String.valueOf(map.get("roomCharacter")));
				Integer status = Integer.valueOf(String.valueOf(map.get("status")));
				
				Rooms room = new Rooms();
				room.setRoomId(StrUtils.replaceBlank(roomId));
				room.setRoomType(roomType.split("--")[1]);
				room.setFloorNo(floorNo);
				room.setBuildId(roomType.split("--")[0]);
				room.setRoomCharacter(roomCharacter);
				room.setStatus(status);
				room.setModiOperid(StringUtils.isNotBlank(operId) ? operId : "1") ;
				room.setModiTime(Calendar.getInstance().getTime()) ;
				
				
				// 修改
				if(StringUtils.isNotBlank(editFlag) && "u".equals(editFlag)){
					if(delBool && delIds.contains(room.getRoomId()))
						continue ;
					
					Rooms rm = hroomDefineDao.get("roomId", room.getRoomId());
					room.setCurrStat(currStat) ;
					hroomDefineDao.update(room) ;
					//减少 1 
					hroomDefineDao.updRoomTypeNumBy(rm.getBuildId() , rm.getRoomType() , 1) ;
					//增加 1
					hroomDefineDao.updRoomTypeNumBy(room.getBuildId() , room.getRoomType() , 0) ;
					
				}
				// 新增
				else{
					
					// true 含IP , false 不含IP
					room.setCurrStat(parameterSvc.GetIPFlag() ? "VCI" : "VC") ;
					hroomDefineDao.save(room) ;
					//增加 1
					hroomDefineDao.updRoomTypeNumBy(room.getBuildId() , room.getRoomType() , 0) ;
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
