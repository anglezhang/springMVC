package com.cyw.common.email;

// TODO: Auto-generated Javadoc
/**
 * 邮件发送者.
 */
public interface EmailSender {
	
	/**
	 * 发送服务器IP.
	 *
	 * @return the host
	 */
	public String getHost();

	/**
	 * 发送服务器端口.
	 *
	 * @return the port
	 */
	public Integer getPort();

	/**
	 * 发送编码.
	 *
	 * @return the encoding
	 */
	public String getEncoding();

	/**
	 * 登录名.
	 *
	 * @return the username
	 */
	public String getUsername();

	/**
	 * 密码.
	 *
	 * @return the password
	 */
	public String getPassword();

	/**
	 * 发送人.
	 *
	 * @return the personal
	 */
	public String getPersonal();
}
