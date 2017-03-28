package com.cyw.common.email;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Properties;
import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

// TODO: Auto-generated Javadoc
/**
 * The Class EmailSendTool.
 */
public class EmailSendTool {
	// 邮箱服务器
	/** The host. */
	private String host;
	// 这个是你的邮箱用户名
	/** The username. */
	private String username;
	// 你的邮箱密码
	/** The password. */
	private String password;

	/** The mail_head_name. */
	private String mail_head_name = "this is head of this mail";

	/** The mail_head_value. */
	private String mail_head_value = "this is head of this mail";

	/** The mail_to. */
	private String mail_to;

	/** The mail_from. */
	private String mail_from;

	/** The mail_subject. */
	private String mail_subject = "this is the subject of this test mail";

	/** The mail_body. */
	private String mail_body = "this is the mail_body of this test mail";

	/** The personal name. */
	private String personalName = "";

	/**
	 * Instantiates a new email send tool.
	 */
	public EmailSendTool() {
	}

	/**
	 * Instantiates a new email send tool.
	 *
	 * @param host the host
	 * @param username the username
	 * @param password the password
	 * @param mailto the mailto
	 * @param subject the subject
	 * @param text the text
	 * @param name the name
	 * @param head_name the head_name
	 * @param head_value the head_value
	 */
	public EmailSendTool(String host, String username, String password,
			String mailto, String subject, String text, String name,
			String head_name, String head_value) {
		this.host = host;
		this.username = username;
		this.mail_from = username;
		this.password = password;
		this.mail_to = mailto;
		this.mail_subject = subject;
		this.mail_body = text;
		this.personalName = name;
		this.mail_head_name = head_name;
		this.mail_head_value = head_value;
	}

	/**
	 * 此段代码用来发送普通电子邮件.
	 *
	 * @throws MessagingException the messaging exception
	 * @throws UnsupportedEncodingException the unsupported encoding exception
	 */
	public void send() throws MessagingException, UnsupportedEncodingException {
		Properties props = new Properties();
		Authenticator auth = new Email_Autherticator(); // 进行邮件服务器用户认证
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.auth", "true");
		Session session = Session.getDefaultInstance(props, auth);
		// 设置session,和邮件服务器进行通讯。
		Message message = new MimeMessage(session);
		// message.setContent("foobar, "application/x-foobar"); // 设置邮件格式
		message.setSubject(mail_subject); // 设置邮件主题
		message.setText(mail_body); // 设置邮件正文
		message.setHeader(mail_head_name, mail_head_value); // 设置邮件标题

		message.setSentDate(new Date()); // 设置邮件发送日期
		Address address = new InternetAddress(mail_from, personalName);
		message.setFrom(address); // 设置邮件发送者的地址
		Address toAddress = new InternetAddress(mail_to); // 设置邮件接收方的地址
		message.addRecipient(Message.RecipientType.TO, toAddress);
		Transport transport = session.getTransport("smtp");  
		transport.connect(host,username, password);  
		Transport.send(message); // 发送邮件
	}

	/**
	 * 用来进行服务器对用户的认证.
	 */
	public class Email_Autherticator extends Authenticator {
		
		/**
		 * Instantiates a new email_ autherticator.
		 */
		public Email_Autherticator() {
			super();
		}

		/**
		 * Instantiates a new email_ autherticator.
		 *
		 * @param user the user
		 * @param pwd the pwd
		 */
		public Email_Autherticator(String user, String pwd) {
			super();
			username = user;
			password = pwd;
		}

		/* (non-Javadoc)
		 * @see javax.mail.Authenticator#getPasswordAuthentication()
		 */
		public PasswordAuthentication getPasswordAuthentication() {
			return new PasswordAuthentication(username, password);
		}
	}

	/**
	 * Gets the host.
	 *
	 * @return the host
	 */
	public String getHost() {
		return host;
	}

	/**
	 * Sets the host.
	 *
	 * @param host the new host
	 */
	public void setHost(String host) {
		this.host = host;
	}

	/**
	 * Gets the username.
	 *
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * Sets the username.
	 *
	 * @param username the new username
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * Gets the password.
	 *
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Sets the password.
	 *
	 * @param password the new password
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * Gets the mail_head_name.
	 *
	 * @return the mail_head_name
	 */
	public String getMail_head_name() {
		return mail_head_name;
	}

	/**
	 * Sets the mail_head_name.
	 *
	 * @param mail_head_name the new mail_head_name
	 */
	public void setMail_head_name(String mail_head_name) {
		this.mail_head_name = mail_head_name;
	}

	/**
	 * Gets the mail_head_value.
	 *
	 * @return the mail_head_value
	 */
	public String getMail_head_value() {
		return mail_head_value;
	}

	/**
	 * Sets the mail_head_value.
	 *
	 * @param mail_head_value the new mail_head_value
	 */
	public void setMail_head_value(String mail_head_value) {
		this.mail_head_value = mail_head_value;
	}

	/**
	 * Gets the mail_to.
	 *
	 * @return the mail_to
	 */
	public String getMail_to() {
		return mail_to;
	}

	/**
	 * Sets the mail_to.
	 *
	 * @param mail_to the new mail_to
	 */
	public void setMail_to(String mail_to) {
		this.mail_to = mail_to;
	}

	/**
	 * Gets the mail_from.
	 *
	 * @return the mail_from
	 */
	public String getMail_from() {
		return mail_from;
	}

	/**
	 * Sets the mail_from.
	 *
	 * @param mail_from the new mail_from
	 */
	public void setMail_from(String mail_from) {
		this.mail_from = mail_from;
	}

	/**
	 * Gets the mail_subject.
	 *
	 * @return the mail_subject
	 */
	public String getMail_subject() {
		return mail_subject;
	}

	/**
	 * Sets the mail_subject.
	 *
	 * @param mail_subject the new mail_subject
	 */
	public void setMail_subject(String mail_subject) {
		this.mail_subject = mail_subject;
	}

	/**
	 * Gets the mail_body.
	 *
	 * @return the mail_body
	 */
	public String getMail_body() {
		return mail_body;
	}

	/**
	 * Sets the mail_body.
	 *
	 * @param mail_body the new mail_body
	 */
	public void setMail_body(String mail_body) {
		this.mail_body = mail_body;
	}

	/**
	 * Gets the personal name.
	 *
	 * @return the personal name
	 */
	public String getPersonalName() {
		return personalName;
	}

	/**
	 * Sets the personal name.
	 *
	 * @param personalName the new personal name
	 */
	public void setPersonalName(String personalName) {
		this.personalName = personalName;
	}

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
	for (int i = 1; i < 2; i++) {
		StringBuffer sb_email=new StringBuffer();
		sb_email.append("Hi 您好 这是第"+i+":\t\n");
		sb_email.append("您已经成功注册X梦想，您的账号邮箱是:  请妥善保存。\t\n");
		sb_email.append("X梦想平台是一个以项目孵化和创业社交为基础的平台，在这里您可以把自己的\t\n");
		sb_email.append("创业，项目，知识，经验分享出来，获取投资，关注，点评和指导。再小的梦\t\n");
		sb_email.append("想也值得我们尊重！\t\n");
		sb_email.append("我们同样欢迎您对我们产品提出宝贵意见，创业路上，一路相随O(∩_∩)O~\t\n");
		sb_email.append("\t\n\t\n\t\n\t\n\t\n\t\n\t\n");
		sb_email.append("X梦想团队 \t\n");
		sb_email.append("service@zoomoor.com \t\n");
		
		
//		MailSenderInfo info=new MailSenderInfo();
//		info.setMailServerHost("smtp.qq.com");
//		info.setValidate(true);
//		info.setFromAddress("service@zoomoor.com");
//		info.setUserName("service@zoomoor.com");
//		info.setPassword("asdf12345");
//		info.setSubject("注册成功");
//		info.setContent(sb_email.toString());
//		info.setToAddress("19840904jun@sina.com");
//		new SimpleMailSender().sendTextMail(info);
		
		
		
		EmailSendTool sendEmail = new EmailSendTool(
				"smtp.qq.com",
				"service@zoomoor.com", 
				"asdf12345", 
				"19840904jun@sina.com",
				"注册成功", sb_email.toString(), "X梦想平台-注册成功", "", "");
 		try {
 			sendEmail.send();
 		} catch (Exception ex) {
 			ex.printStackTrace();
	}
	}	
 }	
		

}