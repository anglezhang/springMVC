package com.cyw.mammoth.dao;

import java.util.List;
import java.util.Map;

import com.cyw.common.base.dao.BaseDao;
import com.cyw.mammoth.bean.Noguest;
import com.cyw.mammoth.service.NoguestSvc;
import com.cyw.mammoth.vo.NoguestSelectVO;

public interface NoguestDao extends BaseDao<Noguest, Integer>{
	
	/**
	 * 获取非住客列表
	 * <功能详细描述>
	 * @param searchVO
	 * @return
	 * @see GuestdocDao.java
	 */
	public List getNoguestList(NoguestSelectVO noguestSelectVO);
	
	/** 根据comp_type查询合约单位
	 * <功能详细描述>
	 * @param comp_type
	 * @return
	 * @see NoguestDao.java
	 */
	public List getCompanyData(String comp_type);
	
	/** 根据条件查询非住客资料
	 * <功能详细描述>
	 * @param map
	 * @return
	 * @see NoguestDao.java
	 */
	public List getSelectNoguest(Map map);
	
	/** 根据主键ID查询非住客详情
	 * <功能详细描述>
	 * @param id
	 * @return
	 * @see NoguestDao.java
	 */
	public List selectSignleNoguest(int id);
	
	/** 维护非住客
	 * <功能详细描述>
	 * @param noguest
	 * @see NoguestDao.java
	 */
	public void updateSigleNoguest(Noguest noguest);
	
	/** 根据主键ID查询非住客表（单张），返回一个实体对象（noguest）
	 * <功能详细描述>
	 * @param id
	 * @return
	 * @see NoguestSvc.java
	 */
	public List<Noguest> selectNoguest(int id);
	
	/** 页面点击放弃，将状态(status)改为1,无效
	 * <功能详细描述>
	 * @param id
	 * @see NoguestDao.java
	 */
	public void updateStatusCancle(int id);
	
	/**
	 * 根据ID获取客单详情
	 * <功能详细描述>
	 * @param id
	 * @see NoguestSvc.java
	 */
	public Map getNoguestInfo(int id);
}
