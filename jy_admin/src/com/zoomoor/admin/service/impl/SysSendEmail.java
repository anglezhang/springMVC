package com.zoomoor.admin.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.zoomoor.admin.service.SysEmailRecordSvc;


/**
 *@ClassName: SendEmail
 *@Title:
 *@Description:定时发送邮件根据spring配置进行
 *@Author:liuweixing
 *@Since:2014-12-11下午5:02:37 
 *@Version:1.0 
*/
@Component("emailsend")
public class SysSendEmail{
	@Autowired
	private  SysEmailRecordSvc emailRecordSvc;
	public void run() {
		System.out.println("=============+_+++++++===============");
	/*List<SysEmailRecord> emailList=emailRecordSvc.getAll();
	for(SysEmailRecord emailRecord:emailList){
		System.out.println(emailRecord.getContent()+"============================");
	}*/
	}

}
