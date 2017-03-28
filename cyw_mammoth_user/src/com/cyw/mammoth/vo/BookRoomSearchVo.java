package com.cyw.mammoth.vo;

public class BookRoomSearchVo {
	/**
	 * 订单号
	 */
	String book_list;
	
	/**
	 * 英文名
	 */
	String gstNamee;
	/**
	 * 中文名
	 */
	String gstNamec;
	/**
	 * 预定人
	 */
	String book_operid;
	/**
	 * 联系电话
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
	/**房态--预定入住***/
	/**
	 * 销售员
	 */
	String salePeson ;
	/**
	 * 团代码
	 */
	String groupCode ;
	/**
	 * 团名
	 */
	String groupName ;
	/**
	 * 领队名
	 */
	String leaderName ;
	/**房态--预定入住***/
	public Integer getActive() {
		return active;
	}
	public void setActive(Integer active) {
		this.active = active;
	}
	public String getBook_list() {
		return book_list;
	}
	public void setBook_list(String book_list) {
		this.book_list = book_list;
	}
	public String getGstNamee() {
		return gstNamee;
	}
	public void setGstNamee(String gstNamee) {
		this.gstNamee = gstNamee;
	}
	public String getGstNamec() {
		return gstNamec;
	}
	public void setGstNamec(String gstNamec) {
		this.gstNamec = gstNamec;
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
	public String getSalePeson() {
		return salePeson;
	}
	public void setSalePeson(String salePeson) {
		this.salePeson = salePeson;
	}
	public String getGroupCode() {
		return groupCode;
	}
	public void setGroupCode(String groupCode) {
		this.groupCode = groupCode;
	}
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	public String getLeaderName() {
		return leaderName;
	}
	public void setLeaderName(String leaderName) {
		this.leaderName = leaderName;
	}
	
}
