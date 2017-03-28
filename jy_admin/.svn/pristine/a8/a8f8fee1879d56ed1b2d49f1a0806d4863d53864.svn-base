package com.zoomoor.admin.service.impl;

import java.util.Date;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zoomoor.admin.dao.SysUserDao;
import com.zoomoor.admin.entity.SysRole;
import com.zoomoor.admin.entity.SysUser;
import com.zoomoor.admin.service.SysRoleSvc;
import com.zoomoor.admin.service.SysUserSvc;
import com.zoomoor.common.base.bean.Pager;
import com.zoomoor.common.base.service.impl.BaseSvcImpl;
import com.zoomoor.common.security.BadCredentialsException;
import com.zoomoor.common.security.UsernameNotFoundException;
import com.zoomoor.common.security.encoder.PwdEncoder;
import com.zoomoor.common.util.MyFileUtils;
import com.zoomoor.jy.dao.InfoEmpDao;
import com.zoomoor.jy.entity.InfoEmp;

@Service
@Transactional
public class SysUserSvcImpl extends BaseSvcImpl<SysUser, Integer> implements SysUserSvc {

	@Resource
	private SysUserDao dao;
	
	@Autowired
	public void setBaseDao(SysUserDao dao) {
		super.setBaseDao(dao);
	}
	
	@Resource
	private InfoEmpDao empDao;
	
	@Resource
	private SysRoleSvc sysRoleSvc;
	
	@Autowired
	public void setSysRoleSvc(SysRoleSvc sysRoleSvc) {
		this.sysRoleSvc = sysRoleSvc;
	}
	
	@Resource
	private PwdEncoder pwdEncoder;
	
	public SysUser save(SysUser entity, String ip, Integer[] roleIds){
		
		Date time = new Date(System.currentTimeMillis());
		
		InfoEmp emp = entity.getInfoEmp();
		if(emp!=null){
			Integer empId = emp.getEmpId();
			if(empId != null){
				InfoEmp empEntity = empDao.get(empId);
				entity.setInfoEmp(empEntity);
			}
		}
		
		entity.setPassword(pwdEncoder.encodePassword(entity.getPassword()));
		entity.setRegisterTime(time);
		entity.setRegisterIp(ip);
		entity.setLastLoginTime(time);
		entity.setLastLoginIp(ip);
		entity.setLoginCount(0);
		entity.setErrorCount(0);
		entity.setDel(false);
		if("".equals(entity.getEmail())){
			entity.setEmail(null);
		}
		// 保存角色
		Set<SysRole> sysRoles = sysRoleSvc.getRoleSetById(roleIds);
		entity.setSysRoles(sysRoles);
		dao.save(entity);
		return entity;
	}
	
	@Transactional(readOnly = true)
	public SysUser findByUsername(String username) {
		String[] prom={"del","username"};
		Object[] objetm={false,username};
		SysUser entity = dao.get(prom, objetm);
		return entity;
	}
	
	@Override
	public SysUser login(String username, String password, String ip)
			throws UsernameNotFoundException, BadCredentialsException {
		SysUser user = findByUsername(username);
		if (user == null||user.getDel()) {
			throw new UsernameNotFoundException("用户名不存在：" + username);
		}
		if (!pwdEncoder.isPasswordValid(user.getPassword(), password)) {
			updateLoginError(user.getUserId(), ip);
			throw new BadCredentialsException("密码有误");
		}
		if (user.getIsDisabled() != 0) {
			throw new BadCredentialsException("该用户被禁用");
		}
		updateLoginSuccess(user.getUserId(), ip);
		
		return user;
	}
	
	public void updateLoginSuccess(Integer userId, String ip) {
		SysUser user = get(userId);
		Date now = new Date(System.currentTimeMillis());
		user.setLoginCount(user.getLoginCount() + 1);
		user.setLastLoginIp(ip);
		user.setLastLoginTime(now);
		user.setErrorCount(0);
		user.setErrorTime(null);
		user.setErrorIp(null);
	}
	
	public void updateLoginError(Integer userId, String ip) {
		SysUser user = get(userId);
		Date now = new Date(System.currentTimeMillis());
		int errorInterval = Integer.parseInt(MyFileUtils.getValueByPropertyName("config", "login_error_interval"));
		Date errorTime = user.getErrorTime();

		user.setErrorIp(ip);
		if (errorTime == null || errorTime.getTime() + errorInterval * 60 * 1000 < now.getTime()) {
			user.setErrorTime(now);
			user.setErrorCount(1);
		} else {
			user.setErrorCount(user.getErrorCount() + 1);
		}
	}
	
	@Override
	public Integer errorRemaining(String username) {
		if (StringUtils.isBlank(username)) {
			return null;
		}
		SysUser user = findByUsername(username);
		if (user == null) {
			return null;
		}
		long now = System.currentTimeMillis();
		
		int maxErrorTimes = Integer.parseInt(MyFileUtils.getValueByPropertyName("config", "login_error_times"));
		int maxErrorInterval = Integer.parseInt(MyFileUtils.getValueByPropertyName("config", "login_error_interval")) * 60 * 1000;
		
		Integer errorCount = user.getErrorCount();
		Date errorTime = user.getErrorTime();
		if (errorCount <= 0 || errorTime == null || errorTime.getTime() + maxErrorInterval < now) {
			return maxErrorTimes;
		}
		return maxErrorTimes - errorCount;
	}
	
	@Override
	public void updateLoginInfo(Integer userId, String ip) {
		Date now = new Date(System.currentTimeMillis());
		SysUser user = get(userId);
		if (user != null) {
			user.setLoginCount(user.getLoginCount() + 1);
			user.setLastLoginIp(ip);
			user.setLastLoginTime(now);
		}
	}	

	@Override
	public SysUser update(SysUser sysUser, Integer userId, Integer[] roleIds) {
		SysUser entity = get(userId);
		
		if(!StringUtils.isEmpty(sysUser.getPassword())){
			entity.setPassword(pwdEncoder.encodePassword(sysUser.getPassword()));
		}
		
		entity.setIsDisabled(sysUser.getIsDisabled());
		InfoEmp infoEmp=empDao.get(sysUser.getInfoEmp().getEmpId());
		entity.setInfoEmp(infoEmp);
		// 保存角色
		Set<SysRole> sysRoles = sysRoleSvc.getRoleSetById(roleIds);
		entity.setSysRoles(sysRoles);
		
		return entity;
	}

	@Override
	public boolean isSuper(SysUser user) {
		boolean flag = false;
		for (SysRole role : user.getSysRoles()){
			if(role.getIsSuper() == 1){ 
				flag = true;
			}
		}
		
		return flag;
	}

	@Override
	public SysUser deleteById(Integer id) {
		SysUser bean = dao.get(id);
		bean.setDel(true);
		dao.update(bean);
		return bean;
	}
	
	@Override
	public SysUser[] deleteByIds(Integer[] ids) {
		SysUser[] beans = new SysUser[ids.length];
		for (int i = 0, len = ids.length; i < len; i++) {
			beans[i] = deleteById(ids[i]);
		}
		return beans;
	}

	@Override
	public Pager getPage(Pager pager, String queryUsername,
			String queryRealname, int pagerNum, int numPerPage) {
		return dao.getPage(pager, queryUsername, queryRealname, pagerNum, numPerPage);
	}
}