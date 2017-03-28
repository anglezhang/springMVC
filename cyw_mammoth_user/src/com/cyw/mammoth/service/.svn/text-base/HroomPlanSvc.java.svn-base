package com.cyw.mammoth.service;

import java.util.Date;
import java.util.List;

import com.cyw.common.base.service.BaseSvc;
import com.cyw.mammoth.bean.HroomPlan;
import com.cyw.mammoth.vo.HroomPlanVo;

public interface HroomPlanSvc extends BaseSvc<HroomPlan, Integer> {

	List<HroomPlanVo> findListBy(Integer status) throws Exception;
	
	/**
	 * 根据id数组修改对应的实体的状态
	 * @param ids 删除id数组
	 * @param status 0 有效 1无效
	 */
	void updateStatusById(String[] ids, Integer status) throws Exception;
	/**
	 * 新增，修改，删除实体
	 * @param hbuildings 实体集合
	 * @param delIds     被删除ids
	 * @param operId     session userId
	 */
	void saveOrUpdateOrDel(List<HroomPlanVo> hroomPlans, String delIds ,String operId) throws Exception;
	
	/**
	 * 查询有效的房价方案列表
	 * @param status     状态
	 * @param startDate  开始日期
	 * @param roomplanType  入住类型
	 * @param roomId  房间号
	 * @param currentRoomPlanType  当前房价方案
	 * @return
	 * @throws Exception
	 */
	List<HroomPlanVo> findAvilListBy(Integer status , Date startDate , Integer roomplanType, String roomId , String currentRoomPlanType) ;
	/**
	 * 查询有效的房价方案价格
	 * @param roomplanCode  房价方案code
	 * @param roomId  房间号
	 * @return
	 * @throws Exception
	 */
	String findHroomPlanPrice(String roomplanCode, String roomId);

}
