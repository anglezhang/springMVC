package com.cyw.mammoth.vo;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.cyw.mammoth.bean.HroomType;

/**
 * 房型VO
 * @author Administrator
 *
 */
public class RoomTypeVO {
	/**
	 * 房型实体
	 */
	private HroomType hroomType ;
	/**
	 * 该房型下房间数量（包含无效的房间）
	 */
	private Integer referCount ;
	public HroomType getHroomType() {
		return hroomType;
	}
	public void setHroomType(HroomType hroomType) {
		this.hroomType = hroomType;
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
