package com.cyw.mammoth.auth;

import org.apache.shiro.authc.UsernamePasswordToken;

/**
 * 增加了验证码的登录令牌	
 * 
 * @author 徐浩	
 */
public class CaptchaUsernamePasswordToken extends UsernamePasswordToken {

	/**
	 * 序列化ID
	 */
	private static final long serialVersionUID = 8624829699611333008L;

	/**
	 * 验证码
	 */
	private String captcha;
	
	public CaptchaUsernamePasswordToken() {
		super();
	}

	public CaptchaUsernamePasswordToken(String username, char[] password,
			boolean rememberMe, String host,String captcha) {		
		super(username, password, rememberMe, host);
		this.captcha = captcha;
	}

	public CaptchaUsernamePasswordToken(String username, char[] password,
			boolean rememberMe,String captcha) {
		super(username, password, rememberMe);
		this.captcha = captcha;
	}

	public CaptchaUsernamePasswordToken(String username, char[] password,
			String host,String captcha) {
		super(username, password, host);
		this.captcha = captcha;
	}

	public CaptchaUsernamePasswordToken(String username, char[] password
			,String captcha) {
		super(username, password);
		this.captcha = captcha;
	}

	public CaptchaUsernamePasswordToken(String username, String password,
			boolean rememberMe, String host,String captcha) {
		super(username, password, rememberMe, host);
		this.captcha = captcha;
	}

	public CaptchaUsernamePasswordToken(String username, String password,
			boolean rememberMe,String captcha) {
		super(username, password, rememberMe);
		this.captcha = captcha;
	}

	public CaptchaUsernamePasswordToken(String username, String password,
			String host,String captcha) {
		super(username, password, host);
		this.captcha = captcha;
	}

	public CaptchaUsernamePasswordToken(String username, String password,
			String captcha) {
		super(username, password);
		this.captcha = captcha;
	}

	/**
	 * @return the captcha
	 */
	public String getCaptcha() {
		return captcha;
	}

	/**
	 * @param captcha the captcha to set
	 */
	public void setCaptcha(String captcha) {
		this.captcha = captcha;
	}
}
