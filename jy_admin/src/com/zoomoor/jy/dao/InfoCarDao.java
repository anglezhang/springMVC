package com.zoomoor.jy.dao;

import com.zoomoor.common.base.bean.Pager;
import com.zoomoor.common.base.dao.BaseDao;
import com.zoomoor.jy.entity.InfoCar;

/**
 * 描述：InfoCarSvcDao车辆信息Dao
 * @author Zhangzhenxing
 * @Date 2015年7月24日 下午2:13:57
 * @version 1.0
 */
public interface InfoCarDao extends BaseDao<InfoCar, Integer>{
	
	/**
	 * 获取选择框分页数据
	 * @param carNo 车牌号
	 * @param 客户id
	 * @param customerName 客户姓名
	 * */
	Pager getLookPager(Pager pager,String carNo,Integer customerId,String customerName);

}
