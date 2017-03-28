package com.cyw.mammoth.service;

import java.util.List;

import com.cyw.common.base.service.BaseSvc;
import com.cyw.mammoth.bean.HroomType;
import com.cyw.mammoth.vo.RoomTypeVO;

public interface HroomTypeSvc extends BaseSvc<HroomType, Integer> {

	/**
	 * 获取房屋类型列表
	 * @param status 0有效   1无效
	 * @return
	 */
	List<RoomTypeVO> findListBy(Integer status);
	
	/**
	 * 根据id数组修改对应的实体状态
	 * @param ids 删除id数组
	 * @param status 0 有效 1 无效
	 */
	void updateStatusById(String[] ids,Integer status);
	/**
	 * 新增，修改，删除实体
	 * @param hroomTypes   实体集合
	 * @param delIds       被删除的ids
	 */
	void saveOrUpdateOrDel(List<HroomType> hroomTypes, String delIds);

}
