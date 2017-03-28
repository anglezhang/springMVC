package com.cyw.mammoth.vo;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.cyw.mammoth.bean.HroomCharacters;
/**
 * 房间特征VO
 * @author Administrator
 *
 */
public class HroomCharactersVO {
	/**
	 * 房间特征实体
	 */
	private HroomCharacters hroomCharacters ;
	/**
	 * 房间特征被房间（包含无效的房间）引用的数量
	 */
	private Integer referCount ;
	public HroomCharacters getHroomCharacters() {
		return hroomCharacters;
	}
	public void setHroomCharacters(HroomCharacters hroomCharacters) {
		this.hroomCharacters = hroomCharacters;
	}
	public Integer getReferCount() {
		return referCount;
	}
	public void setReferCount(Integer referCount) {
		this.referCount = referCount;
	}
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
	

}
