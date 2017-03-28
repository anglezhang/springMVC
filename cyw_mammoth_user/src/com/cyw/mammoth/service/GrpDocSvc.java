package com.cyw.mammoth.service;

import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONArray;
import com.cyw.common.base.service.BaseSvc;
import com.cyw.mammoth.bean.BPaied;
import com.cyw.mammoth.bean.BookRoom;
import com.cyw.mammoth.bean.GrpDoc;
import com.cyw.mammoth.vo.GroupBookSearchVo;
import com.cyw.mammoth.vo.GroupGuestUpdateVO;
import com.cyw.mammoth.vo.GrpDocVo;

public interface GrpDocSvc extends BaseSvc<GrpDoc, Integer> {
	
	public List getgrpdoc(Map resMap);
	
	public int get_Biil_id_num(int bill_id);
	
	public List getBillIdList(int bill_id);
	
	public List<GrpDocVo> getBookRoomList(GroupBookSearchVo searchVo);
	
	public List getGstCreditAuth(int check_id);
	
	/** 根据页面上输入的条件查询出所续住的房间
	 * <功能详细描述>
	 * @param map
	 * @return
	 * @see GrpDocSvc.java
	 */
	public List getSelectRoomToLive(Map map);
	
	/** 根据主键check_id查询出团代码和团名
	 * <功能详细描述>
	 * @param check_id
	 * @return
	 * @see GrpDocSvc.java
	 */
	public List getSelectGrpDoc(int check_id);
	/** 
	 * 保存预定信息
	 * @param guestdoc
	 * @return
	 * @see saveForBookRoom
	 */
	public GrpDoc saveForBookRoom(GrpDoc grpdoc,List<BookRoom> brLists,String roomIds,String currBookRoomId);
	
	/** 
	 * 修改预定信息
	 * @param guestdoc
	 * @param checkId
	 * @return
	 * @see updateForBookRoom
	 */
	public GrpDoc updateForBookRoom(GrpDoc grpdoc,List<BookRoom> brLists,String roomIds,String currBookRoomId);
	
	/**
	 * 获取团队资料详情
	 * <功能详细描述>
	 * @param checkId
	 * @return
	 * @see GrpDocSvc.java
	 */
	public Map getGrpDocDetail(int checkId);
	/**
	 * 分账设置
	 * @param bpaied
	 * @param ifBate
	 */
	public void setSplit(BPaied bpaied,String ifBate);
	
	/**
	 * 团队资料修改
	 * <功能详细描述>
	 * @see GrpDocSvc.java
	 */
	public void updateGrpdocAndGuest(GrpDoc grpDoc,GroupGuestUpdateVO updateVO,BPaied bPaied,String operId);
	
	public String getGrpDocDetailIn(String checkId,JSONArray array,String operId,String withId);
}
