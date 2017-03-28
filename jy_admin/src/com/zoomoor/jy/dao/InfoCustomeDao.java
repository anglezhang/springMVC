package com.zoomoor.jy.dao;

import com.zoomoor.common.base.bean.Pager;
import com.zoomoor.common.base.dao.BaseDao;
import com.zoomoor.jy.entity.InfoCustome;

/**
 * 描述：InfoCustomeDao 客户信息DAO
 * @author Zhangzhenxing
 * @Date 2015年7月23日 下午4:22:23
 * @version 1.0
 */
public interface InfoCustomeDao extends BaseDao<InfoCustome, Integer>{
	
	/**
	 * 描述：获取lookup分页数据
	 * @param querytName 客户姓名
	 * @param queryTel 查询电话
	 * @param CarNO 车牌号
	 * */
	Pager getLookupPager(Pager pager,String querytName,String queryTel,String CarNO,Integer carId);
	

}
