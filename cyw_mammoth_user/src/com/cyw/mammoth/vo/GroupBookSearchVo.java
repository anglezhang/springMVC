package com.cyw.mammoth.vo;

public class GroupBookSearchVo {
	/**
	 * 订单号
	 */
	String bookList;
	/**
	 * 团代码
	 */
	String grpId;
	/**
	 * 团名称
	 */
	String grpName;
	
	/**
	 * 领队
	 */
	String leaderNamec;
	/**
	 * 预定人
	 */
	String book_operid;
	/**
	 * 领队电话
	 */
	String mobile;
	/**
	 * 合约单位名称
	 */
	String comp_name;
	/**
	 * 抵店日期
	 */
	String reachDate;
	/**
	 * 离店日期
	 */
	String leaveDate;
	/**
	 * 房间号
	 */
	String room_id;
	/**
	 * 状态
	 */
	String bookStat;
	
	
	/**
	 * 页面字母
	 */
	String codeLetter;
	
	/**
	 * *#号
	 */
	Integer symbol;
	
	/**
	 * 是否有效
	 */
	Integer active;

	public String getBookList() {
		return bookList;
	}

	public void setBookList(String bookList) {
		this.bookList = bookList;
	}

	public String getGrpId() {
		return grpId;
	}

	public void setGrpId(String grpId) {
		this.grpId = grpId;
	}

	public String getGrpName() {
		return grpName;
	}

	public void setGrpName(String grpName) {
		this.grpName = grpName;
	}

	public String getBook_operid() {
		return book_operid;
	}

	public void setBook_operid(String book_operid) {
		this.book_operid = book_operid;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getComp_name() {
		return comp_name;
	}

	public void setComp_name(String comp_name) {
		this.comp_name = comp_name;
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

	public String getRoom_id() {
		return room_id;
	}

	public void setRoom_id(String room_id) {
		this.room_id = room_id;
	}

	public String getBookStat() {
		return bookStat;
	}

	public void setBookStat(String bookStat) {
		this.bookStat = bookStat;
	}

	public String getCodeLetter() {
		return codeLetter;
	}

	public void setCodeLetter(String codeLetter) {
		this.codeLetter = codeLetter;
	}

	public Integer getSymbol() {
		return symbol;
	}

	public void setSymbol(Integer symbol) {
		this.symbol = symbol;
	}

	public Integer getActive() {
		return active;
	}

	public void setActive(Integer active) {
		this.active = active;
	}

	public String getLeaderNamec() {
		return leaderNamec;
	}

	public void setLeaderNamec(String leaderNamec) {
		this.leaderNamec = leaderNamec;
	}
	
	
}
