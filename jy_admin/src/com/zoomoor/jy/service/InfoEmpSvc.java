package com.zoomoor.jy.service;

import java.util.List;
import com.zoomoor.common.base.bean.Pager;
import com.zoomoor.common.base.service.BaseSvc;
import com.zoomoor.jy.entity.InfoEmp;

public interface InfoEmpSvc extends BaseSvc<InfoEmp, Integer> {

	/**
	 * 描述：获取员工列表
	 * 
	 * @param type
	 *            岗位类型
	 * @param deptId
	 *            部门
	 * */
	List<InfoEmp> getEmpByType(String type, Integer deptId);

	/**
	 * @param pager
	 *            pager对象
	 * @param queryName
	 *            姓名
	 * @param queryIdCode
	 *            身份证号
	 * */
	Pager getLookPager(Pager pager, String queryName, String queryIdCode,Integer islook);

	/**
	 * 描述:获取分页对象
	 * @param queryDept 部门ID
	 * @param queryEmpCode 员工编号
	 * @param queryEmpName 员工姓名
	 * @param queryGender 性别
	 * @param queryPosition 职位
	 * @param queryStatus 是否在职 1在职 2离职
	 * */
	Pager getPager(Pager pager,Integer queryDept, String queryEmpCode,
			String queryEmpName, Integer queryGender, String queryPosition,
			Integer queryStatus);
	/**
	 * 保存员工对象
	 * @return 员工id
	 * */
	Integer saveEmplyeer(InfoEmp infoEmp);
	
	/**
	 * 描述:更新员工信息
	 * */
	boolean updateInfoEmp(InfoEmp infoEmp);

}
