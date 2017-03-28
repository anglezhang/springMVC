package com.cyw.mammoth.vo;

import java.io.Serializable;

/**
 * @Comments:  房间统计VO
 * @author zhangzhenxing(zhangzhenxing@cyw.so)
 * @date 下午1:49:44
 * @version 1.0
 */

public class HroomTypeVo implements Serializable{
	
	private Integer count;//数量
	private String code;//类型编码
	private String id;//类型ID
	private String name;//类型中文名称
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
