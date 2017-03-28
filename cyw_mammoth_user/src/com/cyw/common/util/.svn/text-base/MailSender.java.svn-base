package com.cyw.common.util;


import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import org.springframework.core.task.TaskExecutor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

// TODO: Auto-generated Javadoc
/**
 * The Class MailSender.
 */
public class MailSender {
	
    /** The java mail sender. */
    private JavaMailSender javaMailSender;
	
    /** The simple mail message. */
    private SimpleMailMessage simpleMailMessage;
	
    @SuppressWarnings({ "unused" })   
    /** The free marker configurer. */
    private FreeMarkerConfigurer freeMarkerConfigurer;
	
    @SuppressWarnings({ "unused" })   
    /** The task executor. */
    private TaskExecutor taskExecutor;
    
    /**
     * 构建邮件内容，发送邮件。.
     *
     * @param user  用户
     * @param url   链接
     * @param email the email
     * @param content the content
     */
   /* public void send(ZmUser user, String url,String email,String content) {
        String nickname = user.getNick();
        String to = email;
        String emailContent=content;
        String text = "";
        Map<String, String> map = new HashMap<String, String>(1);
        map.put("url", url);
        map.put("content", emailContent);
        try {
//            从FreeMarker模板生成邮件内容
            Template template = freeMarkerConfigurer.getConfiguration().getTemplate("register_mail.ftl");
//            模板中用${XXX}站位，map中key为XXX的value会替换占位符内容。
            text = FreeMarkerTemplateUtils.processTemplateIntoString(template, map);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TemplateException e) {
            e.printStackTrace();
        }
        this.taskExecutor.execute(new SendMailThread(to,null,text));
    }*/
    //    内部线程类，利用线程池异步发邮件。
    /**
     * The Class SendMailThread.
     */
    @SuppressWarnings({ "unused"})   
    private class SendMailThread implements Runnable {
        
        /** The to. */
        private String to;
        
        /** The subject. */
        private String subject;
        
        /** The content. */
        private String content;
        
        /**
         * Instantiates a new send mail thread.
         *
         * @param to the to
         * @param subject the subject
         * @param content the content
         */
        private SendMailThread(String to, String subject, String content) {
            super();
            this.to = to;
            this.subject = subject;
            this.content = content;
        }
        
        /* (non-Javadoc)
         * @see java.lang.Runnable#run()
         */
        @Override
        public void run() {
            sendMail(to, subject, content);
        }
    }
    
    /**
     * 发送邮件.
     *
     * @param to        收件人邮箱
     * @param subject   邮件主题
     * @param content   邮件内容
     */
    public void sendMail(String to, String subject, String content) {
        try {
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper messageHelper = new MimeMessageHelper(message, true, "UTF-8");
            messageHelper.setFrom(simpleMailMessage.getFrom());
            if (subject != null) {
                messageHelper.setSubject(subject);
            } else {
                messageHelper.setSubject(simpleMailMessage.getSubject());
            }
            messageHelper.setTo(to);
            messageHelper.setText(content, true);
           javaMailSender.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
