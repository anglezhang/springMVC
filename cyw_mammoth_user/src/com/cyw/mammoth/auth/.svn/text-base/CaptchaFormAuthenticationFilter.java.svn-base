package com.cyw.mammoth.auth;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.util.WebUtils;

/**
 * 带验证码验证的登录认证Filter
 * 
 * @author 魏晓亮
 */
public class CaptchaFormAuthenticationFilter extends FormAuthenticationFilter {

    /**
     * 日志对象
     */
    private static Log logger = LogFactory.getLog(CaptchaFormAuthenticationFilter.class);

    /**
     * Request提交的验证码名称
     */
    private String captchaParam = DEFAULT_VALIDATE_CODE_PARAMETER;

    /**
     * Request提交的验证码名称默认值
     */
    public static final String DEFAULT_VALIDATE_CODE_PARAMETER = "validateCode";

	/**
	 * 取得验证码的参数名
	 * 
	 * @return
	 */
	public String getCaptchaParam() {
        return captchaParam;
    }
	
    /**
     * 取得验证码值
     * 
     * @param request
     * @return
     */
    protected String getCaptcha(ServletRequest request) {
        return WebUtils.getCleanParam(request, getCaptchaParam());
    }
    
    protected AuthenticationToken createToken(ServletRequest request, ServletResponse response) {
        String username = getUsername(request);
        String password = getPassword(request);
        String captcha = getCaptcha(request);
        boolean rememberMe = isRememberMe(request);
        String host = getHost(request);
        return new CaptchaUsernamePasswordToken(username, password, rememberMe, host,captcha);
    }
}
