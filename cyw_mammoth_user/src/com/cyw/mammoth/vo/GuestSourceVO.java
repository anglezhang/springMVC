package com.cyw.mammoth.vo;

import java.io.Serializable;

/**
 * @Comments:  客人来源 
 * @author zhangzhenxing(zhangzhenxing@cyw.so)
 * @date 上午11:28:24
 * @version 1.0
 */

public class GuestSourceVO implements Serializable{

	private static final long serialVersionUID = 278374850411871994L;
	
	private Integer codeId;//来源ID
	private String name;//来源名称
	private Integer srcType;//来源类型
	public Integer getCodeId() {
		return codeId;
	}
	public void setCodeId(Integer codeId) {
		this.codeId = codeId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getSrcType() {
		return srcType;
	}
	public void setSrcType(Integer srcType) {
		this.srcType = srcType;
	}
	

}
