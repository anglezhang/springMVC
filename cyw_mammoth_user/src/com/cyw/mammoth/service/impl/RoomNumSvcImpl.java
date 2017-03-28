package com.cyw.mammoth.service.impl;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.hibernate.HibernateException;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.cyw.common.base.service.impl.BaseSvcImpl;
import com.cyw.common.util.DateUtils;
import com.cyw.mammoth.auth.AppBaseCfg;
import com.cyw.mammoth.bean.BookRoom;
import com.cyw.mammoth.bean.Operator;
import com.cyw.mammoth.bean.RoomNum;
import com.cyw.mammoth.bean.Rooms;
import com.cyw.mammoth.bean.RoomsDiary;
import com.cyw.mammoth.dao.BookRoomDao;
import com.cyw.mammoth.dao.ParameterDao;
import com.cyw.mammoth.dao.RoomNumDao;
import com.cyw.mammoth.dao.RoomsDao;
import com.cyw.mammoth.dao.RoomsDiaryDao;
import com.cyw.mammoth.service.RoomNumSvc;
@Service
public class RoomNumSvcImpl extends BaseSvcImpl<RoomNum, Integer> implements RoomNumSvc {
	
	private final org.slf4j.Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	RoomNumDao dao;
	
	/**
	 * 公共参数Dao
	 * */
	@Autowired
	private ParameterDao paramerDao;
	
	/**
	 * roomDao
	 * */
	@Autowired
	private RoomsDao roomDao;
	
	/**
	 * room_diary DAO
	 * */
	@Autowired
	private RoomNumDao roomNumDao;
	
	@Autowired
	private BookRoomDao bookRoomDao;
	
	@Autowired
	private RoomsDiaryDao roomDiaryDao;
	
	@Autowired
	public void setBaseDao(RoomNumDao dao) {
		super.setBaseDao(dao);
	}

	@Override
	public void updateById(RoomNum bean) throws HibernateException {
		dao.updateById(bean);
		
	}

	@Override
	public int updateBookRoomByChekcIdAndRoomType(Integer checkId, String roomTypeId,String roomIds,String currBookRoomId,String reachDate,String leaveDate) {
		// TODO Auto-generated method stub
		return dao.updateBookRoomByChekcIdAndRoomType(checkId, roomTypeId,roomIds,currBookRoomId,reachDate,leaveDate);
	}
	
	@Override
	public boolean setFix(String roomIds, String startDate, String endDate,
			String reason,String type) {
		//再次判断在日期范围内 是否有占用
		int count = roomDiaryDao.getDiaryCount(roomIds, startDate, endDate);
		//操作员
		Operator operator = AppBaseCfg.getOperator();
		String operId = operator.getOperId();
		Date operTime = new Date(System.currentTimeMillis());
		//操作时间
		if(count>0){
			return false;
		}
		String[] ids = roomIds.split(",");
		String flag = "-1";
		if("2".equalsIgnoreCase(type)){
			flag = "2";
		}else if("3".equalsIgnoreCase(type)){
			flag = "3";
		}
		//设置维修冻结之前判断是否有其他状态 如果有 则不设置
		try {
			for (String id : ids) {
				RoomNum roomNum = new RoomNum();
				roomNum.setRoomChkid(0);
				roomNum.setCheckId(-1);// 需要咨询徐工
				roomNum.setRoomId(id);
				roomNum.setReachDate(DateUtils.dateFormat2.parse(startDate));
				roomNum.setLeaveDate(DateUtils.dateFormat2.parse(endDate));
				roomNum.setFlag(Integer.parseInt(flag));
				roomNum.setStatus(0);
				roomNum.setOperId(operId.trim());
				roomNum.setOperTime(operTime);
				//调用session 设置操作员
				roomNum.setNote(reason);
				dao.save(roomNum);
			}
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public List<RoomNum> getFixFrozenInf(String roomId, String type,
			String active, String startDate, String endDate,Model model) {
		if("null".equals(type)){
			type = "2";
		}
		if("null".equals(active)){
			active = "0";
		}
		if("null".equals(roomId)){
			roomId = "";
		}
		List<RoomNum> result = dao.getFixFrozenInf(roomId, type, active, startDate, endDate);
//		if(result.size()==0 && "2".equals(type)){
//			type="3";
//			result = dao.getFixFrozenInf(roomId, type, active, startDate, endDate);
//		}
		model.addAttribute("type", type);
		model.addAttribute("active", active);
		return result;
	}

	@Override
	public boolean editFixfrozen(JSONArray array, String cancelIds) {
		try{
			for(int i=0;i<array.size();i++){
				JSONObject obj = (JSONObject) array.get(i);
				Integer id = Integer.parseInt(obj.get("id").toString());
				String reachDate = obj.get("reachDate").toString();
				String leaveDate = obj.get("leaveDate").toString();
				String reason = obj.get("note").toString();
				RoomNum roomNum = dao.get(id);
				roomNum.setReachDate(DateUtils.dateFormat2.parse(reachDate));
				roomNum.setLeaveDate(DateUtils.dateFormat2.parse(leaveDate));
				roomNum.setNote(reason);
				dao.update(roomNum);
			}
			//处理取消
			if(cancelIds.contains(",")){
				String[] ids = cancelIds.split(",");
				for(String id:ids){
					setRoomNumRoomStat(id);
				}
			}else{
				setRoomNumRoomStat(cancelIds);
			}
		}catch(ParseException e){
			logger.error("修改维修冻结日期格式保存出错");
			return false;
		}catch(Exception e){
			logger.error("修改维修冻结保存出错");
		}
		
		return true;
	}
	
	/**
	 * @描述 设置取消冻结状态 将房态修改为 空脏房
	 * @param id 取消的RoomNum对象 id
	 * */
	private void setRoomNumRoomStat(String id){
		if(!"-1".equals(id)){
			Integer roomNumId = Integer.parseInt(id);
			Operator operator = AppBaseCfg.getOperator();
			String operId = operator.getOperId();
			Date operTime = new Date(System.currentTimeMillis());
			RoomNum roomNum = dao.get(roomNumId);
			roomNum.setStatus(1);
			roomNum.setOperId(operId);
			roomNum.setOperTime(operTime);
			String roomId = roomNum.getRoomId();
			Rooms room = roomDao.get("roomId", roomId);
			Boolean hasIp = paramerDao.GetIPFlag();
			if(hasIp){
				room.setCurrStat("VDP");
			}else{
				room.setCurrStat("VD");
			}
			dao.update(roomNum);
			roomDao.update(room);
		}
	}

	@Override
	public void updateBookBoomNums(Integer checkId, Integer bookId, String roomTypeId,String roomIds, Date reachDate,Date leaveDate, List<RoomNum> roomNums) {
		try{
			//删除之前留过的房间,重新留房Integer checkId,String roomTypeId,String roomIds,String currBookRoomId,String reachDate,String LeaveDate
			roomNumDao.updateBookRoomByChekcIdAndRoomType(checkId, roomTypeId,roomIds,bookId.toString(),DateUtils.dateFormat.format(reachDate),DateUtils.dateFormat.format(leaveDate));
			//清空所有bookId关联的RoomNum
			List<RoomNum> list=dao.getList("bookId", bookId);
			for(RoomNum rn:list){
				dao.delete(rn.getId());
				List<RoomsDiary> rdlist=roomDiaryDao.getList("roomChkid", rn.getRoomChkid());
				for(RoomsDiary rd:rdlist){
					roomDiaryDao.delete(rd);
				}
			}
			//
			for(RoomNum roomNum:roomNums){
				dao.save(roomNum);
			}
			BookRoom bookRoomEntity=bookRoomDao.get(bookId);
			bookRoomEntity.setSaveNum(roomNums.size());
			bookRoomEntity.setRoomNums(null);
			bookRoomDao.evict(bookRoomEntity);
			bookRoomDao.merge(bookRoomEntity);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		
	}
}
