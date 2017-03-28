package com.cyw.mammoth.dao;

import java.util.List;

import com.cyw.common.base.dao.BaseDao;
import com.cyw.mammoth.bean.HgstOri;
import com.cyw.mammoth.vo.GuestSourceVO;

public interface HgstOriDao extends BaseDao<HgstOri, Integer> {

	/**
	* 根据条件查询列表
	* @param status 状态 0有效  1无效
	* @return
	* @author Sixp
	 */
	List<HgstOri> findListBy(Integer status);
	
	/**
	 * @描述 查询所有有效客户来源
	 * */
	List<GuestSourceVO> getGuestSource();
}
