package com.cyw.mammoth.dao;

import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.cyw.common.base.dao.BaseDao;
import com.cyw.mammoth.bean.GrpDoc;
import com.cyw.mammoth.service.GrpDocSvc;
import com.cyw.mammoth.vo.GroupBookSearchVo;
import com.cyw.mammoth.vo.GrpDocVo;

/**
 * 团体资料的DAO
 * <功能详细描述>
 * 
 * @author  litiangang@cyw.so
 * @version  v-1.0
 * @see  GrpDocDao.java
 * @since  cyw-1.0
 */
public interface GrpDocDao extends BaseDao<GrpDoc, Integer> {

	public List getgrpdoc(Map resMap);
	
	public int get_Biil_id_num(int bill_id);
	
	/** 根据check_id查询出
	 * <功能详细描述>
	 * @param check_id
	 * @return
	 * @see GrpDocDao.java
	 */
	public List getBillIdList(int check_id);
	
	public List<GrpDocVo> getBookRoomList(GroupBookSearchVo searchVo);
	
	public List getGstCreditAuth(int check_id);
	
	/** 根据页面上输入的条件查询出所续住的房间
	 * <功能详细描述>
	 * @param map
	 * @return
	 * @see GrpDocDao.java
	 */
	public List getSelectRoomToLive(Map map);
	
	/** 根据主键check_id查询出团代码和团名
	 * <功能详细描述>
	 * @param check_id
	 * @return
	 * @see GrpDocDao.java
	 */
	public List getSelectGrpDoc(int check_id);
	
	public Map getGrpDocDetail(int checkId);
	/**
	 * 分账处理
	 * @param checkId
	 * @param ifBate
	 */
	public void updeteGuestIfbate(Integer checkId, String ifBate);
	
	/**
	 * 匹配团名称或团id的团信息
	 * @param param
	 * @return
	 */
	public List<GrpDoc> getGrpDocListByNameOrId(String param);
	
	/**
	 * 团队资料修改
	 * <功能详细描述>
	 * @param grpDoc
	 * @see GrpDocDao.java
	 */
	public void updateGrpdoc(GrpDoc grpDoc);
	
	/**
	 * 批量修改团员资料
	 * <功能详细描述>
	 * @param sql
	 * @see GrpDocDao.java
	 */
	public void updateGuest(String str); 
	public void updateLimit(Double limit, Integer billId);
}
