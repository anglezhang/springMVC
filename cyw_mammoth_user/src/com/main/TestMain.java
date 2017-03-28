package com.main;

import java.util.Date;

import com.cyw.common.util.DateUtil;
import com.cyw.common.util.bean.BeanUtils;
import com.cyw.mammoth.bean.Guestdoc;

public class TestMain {

	public static void main(String[] args) {
		/*Guestdoc ga=new Guestdoc();
		ga.setBookOrder("");
		Guestdoc gb=new Guestdoc();
		gb.setAge(100);
		gb.setAddr("00000");
		gb.setBookOrder("");
		BeanUtils.beanShallowCopy(gb, ga);
		System.out.println(gb.getAge());
		System.out.println(gb.getBookOrder());*/
		Date hotleDate=new Date();
		String s1=DateUtil.convertDate2String(hotleDate, DateUtil.defaultDatePattern);
		System.out.println(s1);
		Date d1= DateUtil.convertStringToDate(s1);
		System.out.println(d1);
		System.out.println(DateUtil.convertDate2String(d1, DateUtil.dateTimePattern));
		
	}

}
