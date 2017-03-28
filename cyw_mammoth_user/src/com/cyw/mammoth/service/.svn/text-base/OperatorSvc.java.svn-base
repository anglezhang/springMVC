package com.cyw.mammoth.service;

import java.util.List;

import com.cyw.common.base.service.BaseSvc;
import com.cyw.mammoth.bean.Hfunction;
import com.cyw.mammoth.bean.Operator;
import com.cyw.mammoth.bean.ShiftLog;
import com.cyw.mammoth.bean.WorkGroup;
import com.cyw.mammoth.vo.OperatorListVO;
import com.cyw.mammoth.vo.OperatorVo;

public interface OperatorSvc extends BaseSvc<Operator, String> {
	public List<Operator> search(OperatorVo searchVo);
	public List<Hfunction> getOperatorAuthedFunctions(String operatorId);
	public List<Hfunction> getOperatorAuthedFunctions(Operator operator);
	public int checkIsInAuthManageNav(String funcId);
	public int checkIsInAuthManageCrt(String funcId);
	public int checkIsInAuthManage(String funcId);
	public boolean checkIsAuthed(String workgroupId,String funcId);
	/**
	 *  为了避免某些情况下可能发生的循环调用 getOperShiftLog
	 * @param operator
	 * @return
	 */
	public ShiftLog getOperShiftLog(Operator operator);
	/**
	 * 为了避免某些情况下可能发生的循环调用
	 * @param operId
	 * @return
	 */
	public ShiftLog getOperShiftLog(String operId);
	/**
	 * 查询列表
	 * @param status
	 * @return
	 * @throws Exception 
	 */
	public List<OperatorListVO> findListBy(Integer status) throws Exception;
	/**
	 * 根据id数组删除对应的实体（逻辑）
	 * @param ids 删除id数组
	 * @param status 0 有效 1 无效
	 */
	void updateStatusById(String[] ids,Integer status) throws Exception;
	/**
	 * 新增，修改，删除实体
	 * @param workGroups 实体集合
	 * @param delIds           被删除的ids
	 */
	void saveOrUpdateOrDel(List<Operator> operators, String delIds) throws Exception;
}
