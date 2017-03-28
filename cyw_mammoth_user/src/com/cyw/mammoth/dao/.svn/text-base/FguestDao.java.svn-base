package com.cyw.mammoth.dao;

import java.util.List;
import java.util.Map;

import com.cyw.common.base.dao.BaseDao;
import com.cyw.mammoth.bean.Fguest;
import com.cyw.mammoth.vo.FguestInfoVO;
import com.cyw.mammoth.vo.FguestSearchVo;
import com.cyw.mammoth.vo.FguestVo;

public interface FguestDao extends BaseDao<Fguest, Integer>{
	/**
	 * 自定义查询
	 * @param searchVo
	 * @return
	 */
	List<FguestVo> search(FguestVo searchVo);
	
	/**
	 * @描述 查询熟客资料
	 * @param fguestSearch 搜索实体类
	 * */
	List<FguestInfoVO> fguestSearch(FguestSearchVo fguestSearch);
	
	/**
	 * @描述 获取客户消费记录
	 * @param startDate 开始日期
	 * @param endDate 结束日期
	 * */
	List<Map<String, Object>> getFguestRecord(String startDate,String endDate,Integer gstId);
}
