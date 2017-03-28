package com.zoomoor.core.dateutil;

import java.util.Date;

import org.springframework.core.convert.converter.Converter;

/**
 * 描述：DateConverter.java
 * @author Zhangzhenxing
 * @Date 2015年8月11日 下午4:29:28
 * @version 1.0
 */
public class DateConverter implements Converter<String, Date>{

	/**
	* 描述：该方法作用
	* @author zhangZhenxing
	* @Date 2015年8月11日
	*/
	@Override
	public Date convert(String source) {
		System.out.println(source);
		return null;
	}

}
