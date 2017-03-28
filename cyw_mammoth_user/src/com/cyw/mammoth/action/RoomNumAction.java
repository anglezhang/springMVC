package com.cyw.mammoth.action;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;

import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cyw.mammoth.bean.RoomNum;
import com.cyw.mammoth.service.RoomNumSvc;
import com.google.gson.Gson;
@Controller
public class RoomNumAction {
	@Autowired
	private RoomNumSvc roomNumSvc;
	@RequestMapping("/roomnum/update.do")
	public String update(){
		try {
			RoomNum bean= roomNumSvc.get(13);
			bean.setRoomId("8316");
			Date date= new Date();
			Timestamp tamp= new Timestamp(date.getTime());
			bean.setLeaveDate(tamp);
			roomNumSvc.updateById(bean);
		} catch (HibernateException e) {
			String errorMessage="错误信息:"+e.getCause().getMessage()+"系统错误码:"+((SQLException) e.getCause()).getErrorCode();
			System.err.println(errorMessage);
		}
		return "";
	}
@ResponseBody
@RequestMapping("/roomnum/test.json")
public String webTest(String  name ,String password){
	RoomNum bean= roomNumSvc.get(13);
	Gson gson= new Gson();
	return gson.toJson(bean);
	
}
}
