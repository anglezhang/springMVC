package com.cyw.mammoth.vo;

import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * 办理入住查询条件VO
 * @author Administrator
 *
 */
public class BookRoomCheckInVO {
	/**
	 * 订单号
	 */
	private String bookList ;
	/**
	 * 抵店日期
	 */
	private String reachDate ;
	/**
	 * 登记号
	 */
	private String checkId ;
	/**
	 * 离店日期
	 */
	private String leaveDate ;
	/**
	 * 建筑
	 */
	private String buildId ;
	/**
	 * 楼层
	 */
	private String floorNo ;
	/**
	 * 房态
	 */
	private String stat ;
	/**
	 * 房间特征串  空调,WIFI....
	 */
	private String roomCharaterTotalVal ;
	/**
	 * 房间特征total
	 */
	private String roomCharaterTotalVal_1 ;
	/**
	 * 房类串  ASDK,ADST...
	 */
	private String roomType ;
	/**
	 * 预定号
	 */
	private Integer bookId;
	/**
	 * 团队id
	 */
	private Integer grpChkid;
	
	
	public BookRoomCheckInVO() {
		super();
	}
	
	public BookRoomCheckInVO(String checkId, Integer bookId) {
		super();
		this.checkId = checkId;
		this.bookId = bookId;
	}


	public BookRoomCheckInVO(String bookList, String reachDate, String checkId,
			String leaveDate, String buildId, String floorNo, String stat,
			String roomCharaterTotalVal, String roomCharaterTotalVal_1,
			String roomType, Integer bookId, Integer grpChkid) {
		super();
		this.bookList = bookList;
		this.reachDate = reachDate;
		this.checkId = checkId;
		this.leaveDate = leaveDate;
		this.buildId = buildId;
		this.floorNo = floorNo;
		this.stat = stat;
		this.roomCharaterTotalVal = roomCharaterTotalVal;
		this.roomCharaterTotalVal_1 = roomCharaterTotalVal_1;
		this.roomType = roomType;
		this.bookId = bookId;
		this.grpChkid = grpChkid;
	}

	public String getBookList() {
		return bookList;
	}
	public void setBookList(String bookList) {
		this.bookList = bookList;
	}
	
	public String getReachDate() {
		return reachDate;
	}
	public void setReachDate(String reachDate) {
		this.reachDate = reachDate;
	}
	public String getLeaveDate() {
		return leaveDate;
	}
	public void setLeaveDate(String leaveDate) {
		this.leaveDate = leaveDate;
	}
	public String getBuildId() {
		return buildId;
	}
	
	public String getCheckId() {
		return checkId;
	}
	public void setCheckId(String checkId) {
		this.checkId = checkId;
	}
	public void setBuildId(String buildId) {
		this.buildId = buildId;
	}
	public String getFloorNo() {
		return floorNo;
	}
	public void setFloorNo(String floorNo) {
		this.floorNo = floorNo;
	}
	
	public String getStat() {
		return stat;
	}
	public void setStat(String stat) {
		this.stat = stat;
	}
	
	public String getRoomCharaterTotalVal() {
		return roomCharaterTotalVal;
	}
	public void setRoomCharaterTotalVal(String roomCharaterTotalVal) {
		this.roomCharaterTotalVal = roomCharaterTotalVal;
	}
	
	public String getRoomCharaterTotalVal_1() {
		return roomCharaterTotalVal_1;
	}

	public void setRoomCharaterTotalVal_1(String roomCharaterTotalVal_1) {
		this.roomCharaterTotalVal_1 = roomCharaterTotalVal_1;
	}

	public String getRoomType() {
		return roomType;
	}
	public void setRoomType(String roomType) {
		this.roomType = roomType;
	}
	
	public Integer getBookId() {
		return bookId;
	}
	public void setBookId(Integer bookId) {
		this.bookId = bookId;
	}
	
	public Integer getGrpChkid() {
		return grpChkid;
	}

	public void setGrpChkid(Integer grpChkid) {
		this.grpChkid = grpChkid;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}
