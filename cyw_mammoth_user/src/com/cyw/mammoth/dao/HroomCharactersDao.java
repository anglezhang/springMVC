package com.cyw.mammoth.dao;

import java.util.List;

import com.cyw.common.base.dao.BaseDao;
import com.cyw.mammoth.bean.HroomCharacters;
import com.cyw.mammoth.vo.HroomCharactersVO;

public interface HroomCharactersDao extends BaseDao<HroomCharacters, Integer> {

	/**
	 * 获取房屋特征列表
	 * @param status 0有效  1无效
	 * @return
	 */
	List<HroomCharactersVO> findListBy(Integer status);

}
