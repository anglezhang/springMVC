package com.zoomoor.admin.service;

import javax.servlet.http.HttpServletRequest;

import com.zoomoor.admin.entity.SysLog;
import com.zoomoor.admin.entity.SysUser;
import com.zoomoor.common.base.bean.Pager;
import com.zoomoor.common.base.service.BaseSvc;

public interface SysLogSvc extends BaseSvc<SysLog, Integer>{
	
	public SysLog operating(HttpServletRequest request, String title, String content);
	
	public SysLog loginFailure(HttpServletRequest request, String content);

	public SysLog loginSuccess(HttpServletRequest request, SysUser user);
	
	public Integer save(SysLog bean);

	public Pager getPage(Integer category,  String username, String title, String ip, int pageNo, int pageSize);
	
	public SysLog deleteById(Integer id);
	
	public SysLog[] deleteByIds(Integer[] ids);

	public int deleteBatch(Integer category, Integer days);
}