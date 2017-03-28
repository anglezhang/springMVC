package com.cyw.mammoth.service;

import java.util.Map;
import java.util.Queue;


/**
 * <一句话功能简述> <功能详细描述>
 * 
 * @author liunan@cyw.so
 * @version v-1.0
 * @see DayAuditSvc.java
 * @since cyw-1.0
 */
public interface DayAuditSvc {
	
	/**
	 * 取消夜审
	 * @return
	 */
	public Map<String, Object> cancelDayAudit();

	/**
	 * 启动夜审线程
	 * @param operId
	 */
	public Map<String, Object> dayAudit(String operId, int currStep, int currSubStep, int sqlStep, Queue<Map<String, Object>> queue, int handle);
	
	/**
	 * 以房价列表为准处理房价
	 */
	public boolean handleRoomPrice();
}
