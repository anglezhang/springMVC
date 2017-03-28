package com.cyw.mammoth.vo;

import java.util.Date;

/**
 * 班次记录
 * @author wexl@163.com
 *
 */
public class ShfitLog implements java.io.Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -1080025391363352884L;
	private int id;
	private int linkId;
	private int flag;
	private String operId;
	private String shfitId;
	private Date operTime;
	private String memo;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getLinkId() {
		return linkId;
	}

	public void setLinkId(int linkId) {
		this.linkId = linkId;
	}

	public int getFlag() {
		return flag;
	}

	public void setFlag(int flag) {
		this.flag = flag;
	}

	public String getOperId() {
		return operId;
	}

	public void setOperId(String operId) {
		this.operId = operId;
	}

	public String getShfitId() {
		return shfitId;
	}

	public void setShfitId(String shfitId) {
		this.shfitId = shfitId;
	}

	public Date getOperTime() {
		return operTime;
	}

	public void setOperTime(Date operTime) {
		this.operTime = operTime;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}
}
