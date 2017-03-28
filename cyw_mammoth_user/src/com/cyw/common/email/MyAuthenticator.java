package com.cyw.common.email;

import javax.mail.*;  

// TODO: Auto-generated Javadoc
/**
 * The Class MyAuthenticator.
 */
public class MyAuthenticator extends Authenticator{  
    
    /** The user name. */
    String userName=null;  
    
    /** The password. */
    String password=null;  
       
    /**
     * Instantiates a new my authenticator.
     */
    public MyAuthenticator(){  
    }  
    
    /**
     * Instantiates a new my authenticator.
     *
     * @param username the username
     * @param password the password
     */
    public MyAuthenticator(String username, String password) {   
        this.userName = username;   
        this.password = password;   
    }   
    
    /* (non-Javadoc)
     * @see javax.mail.Authenticator#getPasswordAuthentication()
     */
    protected PasswordAuthentication getPasswordAuthentication(){  
        return new PasswordAuthentication(userName, password);  
    }  
    
    /**
     * The main method.
     *
     * @param args the arguments
     */
    public static void main(String[] args){
    	 StringBuffer sb_email=new StringBuffer();
 		sb_email.append("Hi 您好 这是第1:\t\n");
 		sb_email.append("您已经成功注册X梦想，您的账号邮箱是:  请妥善保存。\t\n");
 		sb_email.append("X梦想平台是一个以项目孵化和创业社交为基础的平台，在这里您可以把自己的\t\n");
 		sb_email.append("创业，项目，知识，经验分享出来，获取投资，关注，点评和指导。再小的梦\t\n");
 		sb_email.append("想也值得我们尊重！\t\n");
 		sb_email.append("我们同样欢迎您对我们产品提出宝贵意见，创业路上，一路相随O(∩_∩)O~\t\n");
 		sb_email.append("\t\n\t\n\t\n\t\n\t\n\t\n\t\n");
 		sb_email.append("X梦想团队 \t\n");
 		sb_email.append("service@zoomoor.com \t\n");
     //这个类主要是设置邮件  
     MailSenderInfo mailInfo = new MailSenderInfo();   
     mailInfo.setMailServerHost("smtp.qq.com");   
     mailInfo.setMailServerPort("25");   
     mailInfo.setValidate(true);   
     mailInfo.setUserName("service@zoomoor.com");   //自己的邮箱
     mailInfo.setPassword("asdf12345");//自己的邮箱密码，用于验证      
     mailInfo.setFromAddress("service@zoomoor.com");  ///自己的邮箱
     mailInfo.setToAddress("19840904jun@sina.com");   ///对方的邮箱
     mailInfo.setSubject("设置邮箱标题");   
     mailInfo.setContent(sb_email.toString());
     //这个类主要来发送邮件  
     SimpleMailSender sms = new SimpleMailSender();  
     sms.sendTextMail(mailInfo);//发送文体格式   
//     sms.sendHtmlMail(mailInfo);//发送html格式
       
   }  
}
