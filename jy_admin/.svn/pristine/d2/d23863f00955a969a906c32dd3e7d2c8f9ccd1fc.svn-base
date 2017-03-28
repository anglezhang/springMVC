package com.zoomoor.common.email;

import javax.mail.*;  

public class MyAuthenticator extends Authenticator{  
    String userName=null;  
    String password=null;  
       
    public MyAuthenticator(){  
    }  
    public MyAuthenticator(String username, String password) {   
        this.userName = username;   
        this.password = password;   
    }   
    protected PasswordAuthentication getPasswordAuthentication(){  
        return new PasswordAuthentication(userName, password);  
    }  
    
    public static void main(String[] args){  
     //这个类主要是设置邮件  
     MailSenderInfo mailInfo = new MailSenderInfo();   
     mailInfo.setMailServerHost("smtp.qq.com");   
     mailInfo.setMailServerPort("25");   
     mailInfo.setValidate(true);   
     mailInfo.setUserName("jun_lengxue@qq.com");   //自己的邮箱
     mailInfo.setPassword("2012_andy");//自己的邮箱密码，用于验证      
     
     mailInfo.setFromAddress("jun_lengxue@qq.com");  ///自己的邮箱
     mailInfo.setToAddress("wangxj_wangyi@163.com");   ///对方的邮箱
     mailInfo.setSubject("设置邮箱标题");   
     mailInfo.setContent("<html>" +
     		"<body>" +
     		"<font color='red'>http://dasdas.com</font>" +
     		"</body>" +
     		"<html>");
     
     //这个类主要来发送邮件  
     SimpleMailSender sms = new SimpleMailSender();  
//     sms.sendTextMail(mailInfo);//发送文体格式   
     sms.sendHtmlMail(mailInfo);//发送html格式
       
   }  
}
