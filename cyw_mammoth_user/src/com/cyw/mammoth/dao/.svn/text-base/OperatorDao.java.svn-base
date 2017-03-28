package com.cyw.mammoth.dao;

import java.util.List;

import com.cyw.common.base.dao.BaseDao;
import com.cyw.mammoth.bean.Operator;
import com.cyw.mammoth.vo.OperatorListVO;
import com.cyw.mammoth.vo.OperatorVo;

public interface OperatorDao extends BaseDao<Operator, String> {
   /**
    * 查询
    * @param searchVo
    * @return
    */
   public List<Operator> search(OperatorVo searchVo);
	/**
	 * 列表
	 * @param status
	 * @return
	 */
	public List<OperatorListVO> findListBy(Integer status);
}
