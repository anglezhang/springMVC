package com.zoomoor.jy.dao;

import java.util.List;

import com.zoomoor.common.base.bean.Pager;
import com.zoomoor.common.base.dao.BaseDao;
import com.zoomoor.jy.entity.InfoEmp;

public interface InfoEmpDao extends BaseDao<InfoEmp, Integer> {

	/**
	 * 描述：获取员工列表
	 * 
	 * @param type
	 *            岗位类型
	 * @param deptId
	 *            部门id
	 * */
	List<InfoEmp> getEmpByType(String type, Integer deptId);

	/**
	 * 描述:页面管理
	 * 
	 * @author zhangzhenxing
	 * @param deptId
	 *            部门ID
	 * @param queryEmpCode
	 *            员工编号
	 * @param queryName
	 *            员工姓名
	 * @param gender
	 *            性别
	 * @param queryPosition
	 *            职位
	 * @param status
	 *            状态 1:在职 2:离职
	 * @param queryIdCode
	 *            身份证号码
	 * */
	Pager getPager(Pager pager,Integer deptId, String queryEmpCode, String queryName,
			Integer gender, String queryPosition, Integer status, String queryIdCode,Integer islook);
	
	public List getMappingList(Integer unitId);
	
}
