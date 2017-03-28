package com.zoomoor.admin.service.impl;

import java.util.Calendar;
import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.util.UrlPathHelper;

import com.zoomoor.admin.dao.SysLogDao;
import com.zoomoor.admin.entity.SysLog;
import com.zoomoor.admin.entity.SysUser;
import com.zoomoor.admin.service.SysLogSvc;
import com.zoomoor.admin.service.SysUserSvc;
import com.zoomoor.common.base.bean.Pager;
import com.zoomoor.common.base.service.impl.BaseSvcImpl;
import com.zoomoor.common.util.MyFileUtils;
import com.zoomoor.common.web.RequestUtils;
import com.zoomoor.core.web.util.SysUtils;

@Service
@Transactional
public class SysLogSvcImpl extends BaseSvcImpl<SysLog, Integer> implements SysLogSvc {
	
	@Resource
	private SysLogDao dao;

	@Autowired
	public void setBaseDao(SysLogDao dao) {
		super.setBaseDao(dao);
	}
	
	@Resource
	private SysUserSvc sysUserSvc;
	
	@Autowired
	public void setSysUserSvc(SysUserSvc sysUserSvc) {
		this.sysUserSvc = sysUserSvc;
	}

	public SysLog loginSuccess(HttpServletRequest request, SysUser user) {
		String ip = RequestUtils.getIpAddr(request);
		UrlPathHelper helper = new UrlPathHelper();
		String uri = helper.getOriginatingRequestUri(request);
		Date date = new Date();
		SysLog log = save(SysLog.LOGIN_SUCCESS, user, uri, ip, date, SysLog.LOGIN_SUCCESS_TITLE, null);
		return log;
	}

	public SysLog loginFailure(HttpServletRequest request, String content) {
		String ip = RequestUtils.getIpAddr(request);
		UrlPathHelper helper = new UrlPathHelper();
		String uri = helper.getOriginatingRequestUri(request);
		Date date = new Date();
		SysLog log = save(SysLog.LOGIN_FAILURE, null, uri, ip, date, SysLog.LOGIN_FAILURE_TITLE, content);
		return log;
	}
	
	public SysLog save(Integer category, SysUser user, String url, String ip, Date date, String title, String content) {
		SysLog log = new SysLog();
		log.setSysUser(user);
		log.setCategory(category);
		log.setIp(ip);
		log.setLogTime(new Date(date.getTime()));
		log.setUrl(url);
		log.setTitle(title);
		log.setContent(content);
		save(log);
		return log;
	}

	@Override
	public SysLog operating(HttpServletRequest request, String title, String content) {
		SysUser user = SysUtils.getUser(request);
		String ip = RequestUtils.getIpAddr(request);
		UrlPathHelper helper = new UrlPathHelper();
		String uri = helper.getOriginatingRequestUri(request);
		Date date = new Date();
		SysLog log = save(SysLog.OPERATING, user, uri, ip, date, MyFileUtils.getValueByPropertyName("message", title), content);
		return log;
	}

	@Override
	@Transactional(readOnly = true)
	public Pager getPage(Integer category,  String username, String title, String ip, int pageNo, int pageSize) {
		Pager page = new Pager();
		if (StringUtils.isBlank(username)) {
			page = dao.getPage(category, null, title, ip, pageNo, pageSize);
		} else {
			SysUser user = sysUserSvc.findByUsername(username);
			if (user != null) {
				page = dao.getPage(category, user.getUserId(), title, ip, pageNo, pageSize);
			} else {
				page = new Pager(1, pageSize, 0);
			}
		}
		return page;
	}
	
	@Override
	public SysLog deleteById(Integer id) {
		SysLog bean = dao.deleteById(id);
		return bean;
	}
	
	@Override
	public SysLog[] deleteByIds(Integer[] ids) {
		SysLog[] beans = new SysLog[ids.length];
		for (int i = 0, len = ids.length; i < len; i++) {
			beans[i] = deleteById(ids[i]);
		}
		return beans;
	}
	
	@Override
	public int deleteBatch(Integer category, Integer days) {
		Date date = null;
		if (days != null && days > 0) {
			Calendar cal = Calendar.getInstance();
			cal.add(Calendar.DAY_OF_MONTH, -days);
			date = cal.getTime();
		}
		return dao.deleteBatch(category, date);
	}
}