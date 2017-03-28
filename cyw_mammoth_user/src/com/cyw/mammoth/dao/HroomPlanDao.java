package com.cyw.mammoth.dao;

import java.util.Date;
import java.util.List;

import com.cyw.common.base.dao.BaseDao;
import com.cyw.mammoth.bean.HroomPlan;
import com.cyw.mammoth.vo.HroomPlanVo;

public interface HroomPlanDao extends BaseDao<HroomPlan, Integer> {
	/**
	 * 根据条件查询列表
	 * @param rmplanId 房价方案id
	 * @param status   状态
	 * @return
	 * @throws Exception
	 */
	List<HroomPlanVo> findListBy(Integer rmplanId , Integer status) throws Exception ;
	/**
	 * 
	 * @param hroomPlanId  房价方案id
	 * @param status       状态
	 * @param oldStatus    原来的状态
	 */
	void deleteHroomPlanListBy(String hroomPlanId, Integer status , Integer oldStatus);
	/**
	 * 查询有效的房价方案列表
	 * @param status     状态
	 * @param startDate  开始日期
	 * @param roomplanType  类型
	 * @return
	 * @throws Exception
	 */
	List<HroomPlanVo> findAvilListBy(Integer status , Date startDate , Integer roomplanType);
	/**
	 * 查询有效的房价方案列表
	 * @param status     状态
	 * @param startDate  开始日期
	 * @param roomplanType  入住类型
	 * @param roomType  房间类型
	 * @param currentRoomPlanType  当前房价方案
	 * @return
	 * @throws Exception
	 */
	List<HroomPlanVo> findAvilListBy(Integer status , Date startDate , Integer roomplanType, String roomType , String currentRoomPlanType) ;
	/**
	 * 查询有效的房价方案列表
	 * @param roomplanCode  房价方案code
	 * @param roomType  房间类型
	 * @return
	 * @throws Exception
	 */
	String findHroomPlanPrice(String roomplanCode, String roomType);
}
