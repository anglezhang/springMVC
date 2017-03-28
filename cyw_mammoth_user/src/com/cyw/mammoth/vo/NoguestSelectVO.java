package com.cyw.mammoth.vo;

/**
 * 根据页面的条件查询非住客信息
 * <功能详细描述>
 * 
 * @author  litiangang@cyw.so
 * @version  v-1.0
 * @see  NoguestSelectVO.java
 * @since  cyw-1.0
 */
public class NoguestSelectVO {
	
	//非住客简称
	private String nogstId;
	//非住客全称
	private String nogstName;
	//合约单位
	private String compId;
	//联系人
	private String connEctor;
	//创建日期起
	private String creaTimeStart;
	//创建日期止
	private String creaTimeEnd;
	//付款期限起
	private String payDateStart;
	//付款期限止
	private String payDateEnd;
	//账号
	private String billId;
	//酒店自用
	private boolean hotelFlag;
	//有效或者无效
	private Integer rstatus;
	/**
	 * 页面字母
	 */
	private String codeLetter;
	
	private boolean chkStat;
	
	private String crea_name;
	
	public String getNogstId() {
		return nogstId;
	}
	public void setNogstId(String nogstId) {
		this.nogstId = nogstId;
	}
	public String getNogstName() {
		return nogstName;
	}
	public void setNogstName(String nogstName) {
		this.nogstName = nogstName;
	}
	public String getCompId() {
		return compId;
	}
	public void setCompId(String compId) {
		this.compId = compId;
	}
	public String getConnEctor() {
		return connEctor;
	}
	public void setConnEctor(String connEctor) {
		this.connEctor = connEctor;
	}
	public String getCreaTimeStart() {
		return creaTimeStart;
	}
	public void setCreaTimeStart(String creaTimeStart) {
		this.creaTimeStart = creaTimeStart;
	}
	public String getCreaTimeEnd() {
		return creaTimeEnd;
	}
	public void setCreaTimeEnd(String creaTimeEnd) {
		this.creaTimeEnd = creaTimeEnd;
	}
	public String getPayDateStart() {
		return payDateStart;
	}
	public void setPayDateStart(String payDateStart) {
		this.payDateStart = payDateStart;
	}
	public String getPayDateEnd() {
		return payDateEnd;
	}
	public void setPayDateEnd(String payDateEnd) {
		this.payDateEnd = payDateEnd;
	}
	public String getBillId() {
		return billId;
	}
	public void setBillId(String billId) {
		this.billId = billId;
	}
	public boolean getHotelFlag() {
		return hotelFlag;
	}
	public void setHotelFlag(boolean hotelFlag) {
		this.hotelFlag = hotelFlag;
	}
	public Integer getRstatus() {
		return rstatus;
	}
	public void setRstatus(Integer rstatus) {
		this.rstatus = rstatus;
	}
	public String getCodeLetter() {
		return codeLetter;
	}
	public void setCodeLetter(String codeLetter) {
		this.codeLetter = codeLetter;
	}
	public boolean isChkStat() {
		return chkStat;
	}
	public void setChkStat(boolean chkStat) {
		this.chkStat = chkStat;
	}
	public String getCrea_name() {
		return crea_name;
	}
	public void setCrea_name(String crea_name) {
		this.crea_name = crea_name;
	}
	
	
}
