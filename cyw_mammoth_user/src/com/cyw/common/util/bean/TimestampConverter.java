package com.cyw.common.util.bean;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.beanutils.ConversionException;
import org.apache.commons.beanutils.Converter;
import org.apache.commons.lang.StringUtils;

import  com.cyw.common.util.DateUtil;

/**
 * This class is converts a java.util.Date to a String and a String to a java.util.Date for use as a Timestamp. It is
 * used by BeanUtils when copying properties.
 * 
 * <p>
 * <a href="TimestampConverter.java.html"><i>View Source</i></a>
 * </p>
 * 
 * @author <a href="mailto:dan@getrolling.com">Dan Kibler</a>
 */
public class TimestampConverter implements Converter {
	public static final String TS_FORMAT = DateUtil.getDatePattern() + " HH:mm:ss.S";

	protected Object convertToTimestamp(Class type, Object value) {
		DateFormat df = new SimpleDateFormat(TS_FORMAT);
		if (value instanceof String) {
			try {
				if (StringUtils.isEmpty(value.toString())) {
					return null;
				}
				return df.parse((String) value);
			} catch (Exception pe) {
				throw new ConversionException("Error converting String to Timestamp");
			}
		} else if (value instanceof Date) {
			Date dateTemp = (Date) value;
			return new Timestamp(dateTemp.getTime());
		}

		throw new ConversionException("Could not convert " + value.getClass().getName() + " to " + type.getName());
	}

	public Object convert(Class type, Object value) {
		if (value == null) {
			return null;
		} else if (type == Timestamp.class) {
			return convertToTimestamp(type, value);
		} else if (type == Date.class) {
			return convertToTimestamp(type, value);
		}

		throw new ConversionException("Could not convert " + value.getClass().getName() + " to " + type.getName());
	}

}