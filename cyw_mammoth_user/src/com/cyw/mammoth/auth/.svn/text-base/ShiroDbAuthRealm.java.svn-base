package com.cyw.mammoth.auth;

import java.io.Serializable;
import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import com.cyw.mammoth.action.OperatorAction.OperatorRoles;
import com.cyw.mammoth.bean.Operator;
import com.cyw.mammoth.service.OperatorSvc;
import com.google.common.collect.Lists;


/**
 * 自实现用户与权限查询.
 * 演示关系，密码用明文存储，因此使用默认 的SimpleCredentialsMatcher.
 */
public class ShiroDbAuthRealm extends AuthorizingRealm {

	private OperatorSvc operatorSvc;

    /**
     * 是否启用此验证
     */
    private boolean captchaEnable = true;
    
    /**
	 * 给ShiroDbRealm提供编码信息，用于密码密码比对
	 * 描述
	 */	
	public ShiroDbAuthRealm() {
		super();
	}
    
    /**
     * Session中存放的验证码名称默认值
     */
    public static final String DEFAULT_SESSION_VALIDATE_CODE_FIELD = "validateCode";
    
    /**
     * Session中存放的验证码名称
     */
    private String sessionValidateCodeField = DEFAULT_SESSION_VALIDATE_CODE_FIELD;
    
	/**
	 * 取得Session中存放的验证码名称
	 * 
	 * @return
	 */
	public String getSessionValidateCodeField() {
        return sessionValidateCodeField;
    }
	
	/**
	 * 认证回调函数, 登录时调用.
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) throws AuthenticationException {
		CaptchaUsernamePasswordToken token = (CaptchaUsernamePasswordToken) authcToken;
		// 如果验证码验证开启
		if(captchaEnable) {
			// 验证码验证
			String captcha = null;
			Object captchaObject = SecurityUtils.getSubject().getSession().getAttribute(getSessionValidateCodeField());
			// 如果session的验证码为空,则抛出验证码过期异常
			if(captchaObject == null) {
				throw new IncorrectCaptchaException("验证码过期！");
			}
			// 取得session中的验证码值
			if(captchaObject instanceof String) {
				captcha = String.valueOf(captchaObject);
			} else {
				throw new IncorrectCaptchaException("验证出错！");
			}
			// 如果session中的验证码与登录令牌中的不一致,则抛出验证码出错异常
			if(!captcha.equals(token.getCaptcha())) { 
				throw new IncorrectCaptchaException("验证出错！");
			}
		}
		//SysUser user = accountService.findUserByLoginName(token.getUsername());
		Operator operator = operatorSvc.get(token.getUsername());
		if (operator != null) {
			return new SimpleAuthenticationInfo(new ShiroUser(operator.getOperId(), operator.getOperName(),operator.getOperId(),null), operator.getPasswd(),
					getName());
		} else {
			return null;
		}
	}

	/**
	 * 授权查询回调函数, 进行鉴权但缓存中无用户的授权信息时调用.
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		ShiroUser shiroUser = (ShiroUser) principals.fromRealm(getName()).iterator().next();
		Operator operator = operatorSvc.get(shiroUser.getLoginName());
		if (operator != null) {
			/*List<Permission> permissionList = accountService.findPermissionsByLoginName(user.getLoginName());
			List<String> permissionValueList = Lists.newArrayList();
			for (Permission permission : permissionList) {
				permissionValueList.add(permission.getValue());
			}
			SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
			for (Permission permission : permissionList) {
				//基于Permission的权限信息
				info.addStringPermissions(permissionValueList);
			}
			return info;*/
			SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
			if(OperatorRoles.Admin.code.equals(operator.getGroupId().trim())){
				info.addRole("admin");
			}
			return info;
		} else {
			return null;
		}
	}

	/**
	 * 更新用户授权信息缓存.
	 */
	public void clearCachedAuthorizationInfo(String principal) {
		SimplePrincipalCollection principals = new SimplePrincipalCollection(principal, getName());
		clearCachedAuthorizationInfo(principals);
	}

	/**
	 * 清除所有用户授权信息缓存.
	 */
	public void clearAllCachedAuthorizationInfo() {
		Cache<Object, AuthorizationInfo> cache = getAuthorizationCache();
		if (cache != null) {
			for (Object key : cache.keys()) {
				cache.remove(key);
			}
		}
	}

	/**
	 * 自定义Authentication对象，使得Subject除了携带用户的登录名外还可以携带更多信息.
	 */
	public static class ShiroUser implements Serializable {
		private static final long serialVersionUID = -1748602382963711884L;
		private String userId;
		private String loginName;
		private String name;
		private String organId;
		private Operator operator;

		public ShiroUser(String loginName, String name) {
			this.loginName = loginName;
			this.name = name;
		}
		public ShiroUser(String loginName, String name,String userId) {
			this.loginName = loginName;
			this.name = name;
			this.userId = userId;
		}
		public ShiroUser(String loginName, String name,String userId,String organId) {
			this.loginName = loginName;
			this.name = name;
			this.userId = userId;
			this.organId = organId;
		}

		public String getLoginName() {
			return loginName;
		}

		/**
		 * 本函数输出将作为默认的<shiro:principal/>输出.
		 */
		@Override
		public String toString() {
			return loginName;
		}

		public String getName() {
			return name;
		}
		/**
		 * @return the userId
		 */
		public String getUserId() {
			return userId;
		}
		
		public String getOrganId() {
			return organId;
		}
		public Operator getOperator() {
			return operator;
		}
		public void setOperator(Operator operator) {
			this.operator = operator;
		}
	}
    @Autowired
	public void setOperatorSvc(OperatorSvc operatorSvc) {
		this.operatorSvc = operatorSvc;
	}

	/**
	 * @return the captchaEnable
	 */
	public boolean isCaptchaEnable() {
		return captchaEnable;
	}

	/**
	 * @param captchaEnable the captchaEnable to set
	 */
	public void setCaptchaEnable(boolean captchaEnable) {
		this.captchaEnable = captchaEnable;
	}

}
