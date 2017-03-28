package com.cyw.mammoth.vo;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.cyw.mammoth.bean.HfunctionControl;
/**
 * 功能控件VO
 * @author Administrator
 *
 */
public class HfunctionControlVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6924388479942259783L;
	
	private HfunctionControl hfunctionControl ;  // 功能控件
	private String flag ;                        // 标识 a 新增  u 修改 d 删除
	private Integer refCount ;                    // 已授权工作组数量
	private String ctrlTypeName ;                    // 功能控件名称
	public HfunctionControl getHfunctionControl() {
		return hfunctionControl;
	}
	public void setHfunctionControl(HfunctionControl hfunctionControl) {
		this.hfunctionControl = hfunctionControl;
	}
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	
	public Integer getRefCount() {
		return refCount;
	}
	public void setRefCount(Integer refCount) {
		this.refCount = refCount;
	}
	
	public String getCtrlTypeName() {
		return ctrlTypeName;
	}
	public void setCtrlTypeName(String ctrlTypeName) {
		this.ctrlTypeName = ctrlTypeName;
	}
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}
