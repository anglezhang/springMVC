package com.cyw.mammoth.vo;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.cyw.mammoth.bean.Hfunction;
/**
 * 功能设置VO
 * @author Administrator
 *
 */
public class HfunctionVO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 432873294351502401L;
	
	private Hfunction hfunction ;                           // 功能对象
	private List<HfunctionControlVO> HfunctionControlVOs;   // 功能控件VO列表
	private String editFlag ;                               // 编辑标识 a 新增  u修改  d 删除
	
	private List<HfunctionControlVO> alreadyAuthHfunctionControlVOs;   // 已授权功能控件VO列表

	public Hfunction getHfunction() {
		return hfunction;
	}

	public void setHfunction(Hfunction hfunction) {
		this.hfunction = hfunction;
	}

	public List<HfunctionControlVO> getHfunctionControlVOs() {
		return HfunctionControlVOs;
	}

	public void setHfunctionControlVOs(List<HfunctionControlVO> hfunctionControlVOs) {
		HfunctionControlVOs = hfunctionControlVOs;
	}

	public String getEditFlag() {
		return editFlag;
	}

	public void setEditFlag(String editFlag) {
		this.editFlag = editFlag;
	}

	public List<HfunctionControlVO> getAlreadyAuthHfunctionControlVOs() {
		return alreadyAuthHfunctionControlVOs;
	}

	public void setAlreadyAuthHfunctionControlVOs(
			List<HfunctionControlVO> alreadyAuthHfunctionControlVOs) {
		this.alreadyAuthHfunctionControlVOs = alreadyAuthHfunctionControlVOs;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}
