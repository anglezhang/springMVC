package com.zoomoor.admin.service.impl;

import java.util.Date;
import java.util.HashSet;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zoomoor.admin.dao.SysAuthenticationDao;
import com.zoomoor.admin.entity.SysAuth;
import com.zoomoor.admin.entity.SysAuthentication;
import com.zoomoor.admin.entity.SysRole;
import com.zoomoor.admin.entity.SysUser;
import com.zoomoor.admin.service.SysAuthenticationSvc;
import com.zoomoor.admin.service.SysUserSvc;
import com.zoomoor.common.base.service.impl.BaseSvcImpl;
import com.zoomoor.common.security.BadCredentialsException;
import com.zoomoor.common.security.UsernameNotFoundException;
import com.zoomoor.common.web.session.SessionProvider;

@Service
@Transactional
public class SysAuthenticationSvcImpl extends BaseSvcImpl<SysAuthentication, String> implements SysAuthenticationSvc {
	private Logger log = LoggerFactory.getLogger(SysAuthenticationSvcImpl.class);
	
	@Resource
	private SysAuthenticationDao dao;
	
	@Resource
	private SysUserSvc sysUserSvc;

	@Autowired
	public void setBaseDao(SysAuthenticationDao dao) {
		super.setBaseDao(dao);
	}

	@Autowired
	public void setSysUserSvc(SysUserSvc sysUserSvc) {
		this.sysUserSvc = sysUserSvc;
	}
	
	public SysAuthentication login(String username, String password, String ip,
			HttpServletRequest request, HttpServletResponse response,
			SessionProvider session) throws UsernameNotFoundException,
			BadCredentialsException {
		SysUser user = sysUserSvc.login(username, password, ip);
		SysAuthentication auth = new SysAuthentication();
		auth.setUserid(user.getUserId());
		auth.setUsername(user.getUsername());
		auth.setEmail(user.getEmail());
		auth.setLoginIp(ip);
		save(auth);
		session.setAttribute(request, response, AUTH_KEY, auth.getAuthenticationId());
		
		// 保存用户session
		session.setUserSession(request, response, AUTH_USER, user);
		
		boolean flag = false;
		HashSet<SysAuth> authSet = new HashSet<SysAuth>();
		for (SysRole role : user.getSysRoles()){
			if(!role.getDel()){
				if(role.getIsSuper() == 1){
					flag = true;
					authSet = null;
					break;
				}else{
					for (SysAuth sysAuth : role.getSysAuths()){
						if(!sysAuth.getDel()){
							authSet.add(sysAuth);
						}
					}
				}
			}
		}
		// 保存是否拥有所有权限 session
		session.setIsSuperSession(request, response, AUTH_SUPER, flag);
			
		//保存用户权限
		session.setAuthSession(request, response, AUTH_LIST, authSet);

		return auth;
	}
	
//	public SysAuthentication activeLogin(SysUser user, String ip,
//			HttpServletRequest request, HttpServletResponse response,
//			SessionProvider session) {
//		sysUserSvc.activeLogin(user, ip);
//		SysAuthentication auth = new SysAuthentication();
//		auth.setUserid(user.getUserId());
//		auth.setUsername(user.getUsername());
//		auth.setEmail(user.getEmail());
//		auth.setLoginIp(ip);
//		save(auth);
//		session.setAttribute(request, response, AUTH_KEY, auth.getAuthenticationId());
//		return auth;
//	}

	public SysAuthentication retrieve(String authId) {
		long current = System.currentTimeMillis();
		// 是否刷新数据库
		if (refreshTime < current) {
			refreshTime = getNextRefreshTime(current, interval);
			int count = dao.deleteExpire(new Date(current - timeout));
			log.info("refresh SysAuthentication, delete count: {}", count);
		}
		SysAuthentication auth = findById(authId);
		if (auth != null && auth.getUpdateTime().getTime() + timeout > current) {
			auth.setUpdateTime(new Date(current));
			return auth;
		} else {
			return null;
		}
	}

	public Integer retrieveUserIdFromSession(SessionProvider session, HttpServletRequest request) {
		String authId = (String) session.getAttribute(request, AUTH_KEY);
		if (authId == null) {
			return null;
		}
		SysAuthentication auth = retrieve(authId);
		if (auth == null) {
			return null;
		}
		return auth.getUserid();
	}

	public void storeAuthIdToSession(SessionProvider session,
			HttpServletRequest request, HttpServletResponse response,
			String authId) {
		session.setAttribute(request, response, AUTH_KEY, authId);
	}

//	@Transactional(readOnly = true)
//	public Pager getPage(int pageNo, int pageSize) {
//		Pager page = dao.getPage(pageNo, pageSize);
//		return page;
//	}

	@Transactional(readOnly = true)
	public SysAuthentication findById(String id) {
		SysAuthentication entity = dao.findById(id);
		return entity;
	}

	public String save(SysAuthentication bean) {
		bean.setAuthenticationId(StringUtils.remove(UUID.randomUUID().toString(), '-'));
		bean.init();
		return dao.save(bean);
	}

	public SysAuthentication deleteById(String id) {
		SysAuthentication bean = dao.deleteById(id);
		return bean;
	}

	public SysAuthentication[] deleteByIds(String[] ids) {
		SysAuthentication[] beans = new SysAuthentication[ids.length];
		for (int i = 0, len = ids.length; i < len; i++) {
			beans[i] = deleteById(ids[i]);
		}
		return beans;
	}

	// 过期时间
	private int timeout = 30 * 60 * 1000; // 30分钟

	// 间隔时间
	private int interval = 4 * 60 * 60 * 1000; // 4小时

	// 刷新时间。
	private long refreshTime = getNextRefreshTime(System.currentTimeMillis(),
			this.interval);

	


	/**
	 * 设置认证过期时间。默认30分钟。
	 * 
	 * @param timeout
	 *            单位分钟
	 */
	public void setTimeout(int timeout) {
		this.timeout = timeout * 60 * 1000;
	}

	/**
	 * 设置刷新数据库时间。默认4小时。
	 * 
	 * @param interval
	 *            单位分钟
	 */
	public void setInterval(int interval) {
		this.interval = interval * 60 * 1000;
		this.refreshTime = getNextRefreshTime(System.currentTimeMillis(),
				this.interval);
	}

	/**
	 * 获得下一个刷新时间。
	 * 
	 * 
	 * 
	 * @param current
	 * @param interval
	 * @return 随机间隔时间
	 */
	private long getNextRefreshTime(long current, int interval) {
		return current + interval;
		// 为了防止多个应用同时刷新，间隔时间=interval+RandomUtils.nextInt(interval/4);
		// return current + interval + RandomUtils.nextInt(interval / 4);
	}
}