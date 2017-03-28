package com.cyw.mammoth.vo;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.cyw.mammoth.bean.HroomPlan;
/**
 * 房价方案VO
 * @author Administrator
 *
 */
public class HroomPlanVo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8650470437557628971L;
	
	private HroomPlan hroomPlan ;                       // 房价方案
	private String hroomPlanHolidayNames ;              // 房价方案节日描述
	private String hroomPlanListJsons ;                 // 房价方案列表json
	private String hroomPlanIds_del ;                   // 房价方案列表删除ids
	private List<HroomPlanListVO> hroomPlanListVOs ;    // 房价方案列表VO
	private String editFlag ;                           // 编辑标识 a 新增  u修改  d 删除
	private String startDate;                           // 起始日期
	private String endDate;                             // 截止日期
	public HroomPlan getHroomPlan() {
		return hroomPlan;
	}
	public void setHroomPlan(HroomPlan hroomPlan) {
		this.hroomPlan = hroomPlan;
	}
	public String getHroomPlanHolidayNames() {
		return hroomPlanHolidayNames;
	}
	public void setHroomPlanHolidayNames(String hroomPlanHolidayNames) {
		this.hroomPlanHolidayNames = hroomPlanHolidayNames;
	}
	public String getHroomPlanListJsons() {
		return hroomPlanListJsons;
	}
	public void setHroomPlanListJsons(String hroomPlanListJsons) {
		this.hroomPlanListJsons = hroomPlanListJsons;
	}
	
	public String getHroomPlanIds_del() {
		return hroomPlanIds_del;
	}
	public void setHroomPlanIds_del(String hroomPlanIds_del) {
		this.hroomPlanIds_del = hroomPlanIds_del;
	}
	public List<HroomPlanListVO> getHroomPlanListVOs() {
		return hroomPlanListVOs;
	}
	public void setHroomPlanListVOs(List<HroomPlanListVO> hroomPlanListVOs) {
		this.hroomPlanListVOs = hroomPlanListVOs;
	}
	
	public String getEditFlag() {
		return editFlag;
	}
	public void setEditFlag(String editFlag) {
		this.editFlag = editFlag;
	}
	
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
