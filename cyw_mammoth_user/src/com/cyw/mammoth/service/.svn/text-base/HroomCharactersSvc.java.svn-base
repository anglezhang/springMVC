package com.cyw.mammoth.service;

import java.util.List;

import com.cyw.common.base.service.BaseSvc;
import com.cyw.mammoth.bean.HroomCharacters;
import com.cyw.mammoth.vo.HroomCharactersVO;

public interface HroomCharactersSvc extends BaseSvc<HroomCharacters, Integer> {

	/**
	 * 获取房屋特征列表
	 * @param status 0有效   1无效
	 * @return
	 */
	List<HroomCharactersVO> findListBy(Integer status);
	
	/**
	 * 根据id数组删除对应的实体（逻辑）
	 * @param ids 删除id数组
	 * @param status 0 有效 1 无效
	 */
	void updateStatusById(String[] ids,Integer status);
	/**
	 * 新增，修改，删除实体
	 * @param hroomCharacterss 实体集合
	 * @param delIds           被删除的ids
	 */
	void saveOrUpdateOrDel(List<HroomCharacters> hroomCharacterss, String delIds);

}
