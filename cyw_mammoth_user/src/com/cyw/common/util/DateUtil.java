package com.cyw.common.util;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.Random;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Date Utility Class This is used to convert Strings to Dates and Timestamps
 * 
 * lixf
 */
public class DateUtil {

	// ~ Static fields/initializers
	// =============================================
	private static final Logger logger = LoggerFactory.getLogger(DateUtil.class);

	public static String defaultDatePattern = "yyyy-MM-dd";
	
	public static String dateTimePattern = "yyyy-MM-dd HH:mm:ss";
	
	public static String dateTimeShortPattern = "yyyy-MM-dd HH:mm";

	public static String timePattern = "HH:mm:ss";
	public static String timeStamp14 = "yyyyMMddHHmmss";

	/**
     * 
     */
	public static String[] weekDayName = { "星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六" };
	
	

	public static String getTimeStamp14() {
		String s = convertDate2String(new Date(), timeStamp14);
		return s;
	}

	public static String getTimeStamp18() {
		String s = convertDate2String(new Date(), timeStamp14);
		int rint = new Random().nextInt(9999);
		return s + String.valueOf(rint);
	}
	
	public static final Date convertTimeStamp14StrToDate(String timeStamp14Str) {
		SimpleDateFormat df = null;
		Date date = null;
		df = new SimpleDateFormat(timeStamp14);
		logger.debug("converting '" + timeStamp14Str + "' to date with mask '" + timeStamp14 + "'");
		try {
			date = df.parse(timeStamp14Str);
		} catch (ParseException pe) {
			logger.error("ParseException: " + pe);
		}

		return date;
	}

	/**
	 * This method attempts to convert an Oracle-formatted date in the form
	 * dd-MMM-yyyy to mm/dd/yyyy.
	 * 
	 * @param aDate
	 *            date from database as a string
	 * @return formatted string for the ui
	 */
	public static final String getDate(Date aDate) {
		SimpleDateFormat df = null;
		String returnValue = "";

		if (aDate != null) {
			df = new SimpleDateFormat(getDatePattern());
			returnValue = df.format(aDate);
		}

		return returnValue;
	}

	public static String getDatePattern() {
		return defaultDatePattern;
	}

	public static String getDateTimePattern() {
		return getDatePattern() + " " + timePattern;
	}

	/**
	 * This method generates a string representation of a date/time in the
	 * format you specify on input
	 * 
	 * @param aMask
	 *            the date pattern the string is in
	 * @param strDate
	 *            a string representation of a date
	 * @return a converted Date object
	 * @see java.text.SimpleDateFormat
	 * @throws ParseException
	 */
	public static final Date convertStringToDate(String aMask, String strDate) {
		SimpleDateFormat df = null;
		Date date = null;
		df = new SimpleDateFormat(aMask);
		logger.debug("converting '" + strDate + "' to date with mask '" + aMask + "'");
		try {
			date = df.parse(strDate);
		} catch (ParseException pe) {
			logger.error("ParseException: " + pe);
		}

		return date;
	}

	/**
	 * This method returns the current date time in the format: MM/dd/yyyy HH:MM
	 * a
	 * 
	 * @param theTime
	 *            the current time
	 * @return the current date/time
	 */
	public static String getTimeNow(Date theTime) {
		return getDateTime(timePattern, theTime);
	}

	/**
	 * This method returns the current date in the format: yyyy-MM-dd
	 * 
	 * @return the current date
	 * @throws ParseException
	 */
	public static Calendar getToday() {
		Date today = getDateNow();
		SimpleDateFormat df = new SimpleDateFormat(getDatePattern());

		// This seems like quite a hack (date -> string -> date),
		// but it works ;-)
		String todayAsString = df.format(today);
		Calendar cal = new GregorianCalendar();
		cal.setTime(convertStringToDate(todayAsString));

		return cal;
	}

	/**
	 * This method generates a string representation of a date's date/time in
	 * the format you specify on input
	 * 
	 * @param aMask
	 *            the date pattern the string is in
	 * @param aDate
	 *            a date object
	 * @return a formatted string representation of the date
	 * 
	 * @see java.text.SimpleDateFormat
	 */
	public static final String getDateTime(String aMask, Date aDate) {
		SimpleDateFormat df = null;
		String returnValue = "";

		if (aDate == null) {
			logger.error("aDate is null!");
		} else {
			df = new SimpleDateFormat(aMask);
			returnValue = df.format(aDate);
		}

		return returnValue;
	}

	public static final String convertDate2String(Date date, String format) {
		SimpleDateFormat df = null;
		String returnValue = "";
		if (date == null) {
			logger.error("aDate is null!");
		} else {
			df = new SimpleDateFormat(format);
			returnValue = df.format(date);
		}

		return returnValue;
	}
	
	public static final String converDate2String(Date date){
		SimpleDateFormat df = null;
		String returnValue = "";
		if (date == null) {
			logger.error("aDate is null!");
		} else {
			df = new SimpleDateFormat(dateTimePattern);
			returnValue = df.format(date);
		}

		return returnValue;
	}

	/**
	 * This method generates a string representation of a date based on the
	 * System Property 'dateFormat' in the format you specify on input
	 * 
	 * @param aDate
	 *            A date to convert
	 * @return a string representation of the date
	 */
	public static final String convertDateToString(Date aDate) {
		return getDateTime(getDatePattern(), aDate);
	}

	public static final String getCurrentYear() {
		return getDateTime(getDatePattern(), getDateNow()).substring(0, 4);
	}

	public static final String getCurrentMonth() {
		return getDateTime(getDatePattern(), getDateNow()).substring(5, 7);
	}

	public static int getCurrentWeekOfMonth() {
		Calendar calendar = Calendar.getInstance();
		return calendar.get(Calendar.WEEK_OF_MONTH);
	}

	/**
	 * This method converts a String to a date using the datePattern
	 * 
	 * @param strDate
	 *            the date to convert (in format MM/dd/yyyy)
	 * @return a date object
	 * 
	 * @throws ParseException
	 */
	public static Date convertStringToDate(String strDate) {
		Date aDate = null;
		logger.debug("converting date with pattern: " + getDatePattern());
		aDate = convertStringToDate(getDatePattern(), strDate);
		return aDate;
	}

	public static Date getBeginOfMonth(Date date, int months) {
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		if (months != 0) {
			calendar.add(GregorianCalendar.MONTH, months);
		}
		calendar.set(GregorianCalendar.DAY_OF_MONTH, calendar.getActualMinimum(GregorianCalendar.DAY_OF_MONTH));
		return (Date) calendar.getTime().clone();
	}

	public static Date getEndOfMonth(Date date, int months) {
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		if (months != 0) {
			calendar.add(GregorianCalendar.MONTH, months);
		}
		calendar.set(GregorianCalendar.DAY_OF_MONTH, calendar.getActualMaximum(GregorianCalendar.DAY_OF_MONTH));
		return (Date) calendar.getTime().clone();
	}

	public static Date getBeginOfYear(Date date) {
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		calendar.set(GregorianCalendar.MONTH, calendar.getActualMinimum(GregorianCalendar.MONTH));
		calendar.set(GregorianCalendar.DAY_OF_MONTH, calendar.getActualMinimum(GregorianCalendar.DAY_OF_MONTH));
		calendar.set(GregorianCalendar.HOUR_OF_DAY, calendar.getActualMinimum(GregorianCalendar.HOUR_OF_DAY));
		calendar.set(GregorianCalendar.MINUTE, calendar.getActualMinimum(GregorianCalendar.MINUTE));
		calendar.set(GregorianCalendar.SECOND, calendar.getActualMinimum(GregorianCalendar.SECOND));
		calendar.set(GregorianCalendar.MILLISECOND, calendar.getActualMinimum(GregorianCalendar.MILLISECOND));
		return (Date) calendar.getTime().clone();
	}

	public static Date getEndOfYear(Date date) {
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		calendar.set(GregorianCalendar.MONTH, calendar.getActualMaximum(GregorianCalendar.MONTH));
		calendar.set(GregorianCalendar.DAY_OF_MONTH, calendar.getActualMaximum(GregorianCalendar.DAY_OF_MONTH));
		calendar.set(GregorianCalendar.HOUR_OF_DAY, calendar.getActualMaximum(GregorianCalendar.HOUR_OF_DAY));
		calendar.set(GregorianCalendar.MINUTE, calendar.getActualMaximum(GregorianCalendar.MINUTE));
		calendar.set(GregorianCalendar.SECOND, calendar.getActualMaximum(GregorianCalendar.SECOND));
		calendar.set(GregorianCalendar.MILLISECOND, calendar.getActualMaximum(GregorianCalendar.MILLISECOND));
		return (Date) calendar.getTime().clone();
	}

	public static int getYearOfDate(Date date) {
		Calendar calendar = new GregorianCalendar();
		return calendar.get(Calendar.YEAR);
	}

	public static Date getEndOfYear(int year) {
		Calendar calendar = new GregorianCalendar();
		calendar.set(GregorianCalendar.YEAR, year);
		calendar.set(GregorianCalendar.MONTH, calendar.getActualMaximum(GregorianCalendar.MONTH));
		calendar.set(GregorianCalendar.DAY_OF_MONTH, calendar.getActualMaximum(GregorianCalendar.DAY_OF_MONTH));
		calendar.set(GregorianCalendar.HOUR_OF_DAY, calendar.getActualMaximum(GregorianCalendar.HOUR_OF_DAY));
		calendar.set(GregorianCalendar.MINUTE, calendar.getActualMaximum(GregorianCalendar.MINUTE));
		calendar.set(GregorianCalendar.SECOND, calendar.getActualMaximum(GregorianCalendar.SECOND));
		calendar.set(GregorianCalendar.MILLISECOND, calendar.getActualMaximum(GregorianCalendar.MILLISECOND));
		return (Date) calendar.getTime().clone();
	}

	public static int monthsBetween(Date startDate, Date endDate) {
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(startDate);
		int startMonth = calendar.get(GregorianCalendar.MONTH);
		int startYear = calendar.get(GregorianCalendar.YEAR);
		calendar.setTime(endDate);
		int endMonth = calendar.get(GregorianCalendar.MONTH);
		int endYear = calendar.get(GregorianCalendar.YEAR);
		return (endYear - startYear) * 12 + (endMonth - startMonth);
	}

	public static int daysBetween(Date startDate, Date endDate) {
		Calendar c1 = Calendar.getInstance();
		Calendar c2 = Calendar.getInstance();
		c1.setTime(startDate);
		c2.setTime(endDate);
		return daysBetween(c1, c2);
	}

	public static int daysBetween(Calendar early, Calendar late) {
		return (int) (toJulian(late) - toJulian(early));
	}

	public static final float toJulian(Calendar c) {
		int Y = c.get(Calendar.YEAR);
		int M = c.get(Calendar.MONTH);
		int D = c.get(Calendar.DATE);
		int A = Y / 100;
		int B = A / 4;
		int C = 2 - A + B;
		float E = (int) (365.25f * (Y + 4716));
		float F = (int) (30.6001f * (M + 1));
		float JD = (C + D + E + F) - 1524.5f;
		return JD;
	}

	/**
	 * 获取当前时间
	 * 
	 * @return 当前日期时间
	 */
	public static Date getDateNow() {
		return Calendar.getInstance().getTime();
	}

	/**
	 * 获取当前时间
	 * 
	 * @return 当前日期时间
	 */
	public static Timestamp getTimestampNow() {
		return new Timestamp(System.currentTimeMillis());
	}

	/**
	 * 获取指定日期对应月份的第一天
	 * 
	 * @param date
	 *            指定日期
	 * @return 月份的第一天
	 */
	public static Date getFirstDayOfMonth(Date date) {
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		return calendar.getTime();
	}

	/**
	 * 获取指定日期对应月份的最后一天
	 * 
	 * @param date
	 *            指定日期
	 * @return 月份的最后一天
	 */
	public static Date getLastDayOfMonth(Date date) {
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		calendar.add(Calendar.MONTH, 1);
		calendar.set(Calendar.DATE, 1);
		calendar.add(Calendar.DATE, -1);
		return calendar.getTime();
	}

	public static Date getNextDay(Date date) {
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		calendar.add(GregorianCalendar.DAY_OF_MONTH, 1);
		return (Date) calendar.getTime().clone();
	}

	public static Date getNextNDay(Date date, int n) {
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		calendar.add(GregorianCalendar.DAY_OF_MONTH, n);
		return (Date) calendar.getTime().clone();
	}

	/**
	 * 获取某天的结束时间 yyyy-MM-dd 00:00:00
	 * 
	 * @param day
	 * @return
	 */
	public static String getDayBegin(int day) {
		SimpleDateFormat dateFormat = new SimpleDateFormat(DateUtil.getDatePattern());
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.DAY_OF_WEEK, day + 1);

		String thisDay = dateFormat.format(calendar.getTime()) + " 00:00:00";
		return thisDay;
	}

	/**
	 * 获取某天的结束时间 yyyy-MM-dd 23:59:59
	 * 
	 * @param day
	 * @return
	 */
	public static String getDayOver(int day) {
		SimpleDateFormat dateFormat = new SimpleDateFormat(DateUtil.getDatePattern());
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.DAY_OF_WEEK, day + 1);

		String thisDay = dateFormat.format(calendar.getTime()) + " 23:59:59";
		return thisDay;
	}

	public static Date extendConvertStringToDate(String dateStr) {
		String[] datePatterns = { "yyyyMMdd", "yyyy/MM/dd", "yyMMdd", "yy/MM/dd", "yyMd", "yy/M/d", "yyyy/M/d", "yyyyMd" };
		Date returnDate = null;
		String source = StringUtils.replace(dateStr, ".", "/");
		// 年月日汉字
		source = StringUtils.replace(source, "年", "/");
		source = StringUtils.replace(source, "月", "/");
		source = StringUtils.replace(source, "日", "");
		// 反斜线
		source = StringUtils.replace(source, "\\", "/");
		// 句号
		source = StringUtils.replace(source, "。", "/");
		source = StringUtils.replace(source, ".", "/");
		// 逗号
		source = StringUtils.replace(source, "，", "/");
		source = StringUtils.replace(source, ",", "/");
		// 顿号
		source = StringUtils.replace(source, "、", "/");
		// 下划线
		source = StringUtils.replace(source, "_", "/");
		// 中划线
		source = StringUtils.replace(source, "—", "/");
		source = StringUtils.replace(source, "-", "/");
		// 空格
		source = StringUtils.replace(source, "　", "/");
		source = StringUtils.replace(source, " ", "/");
		// 冒号
		source = StringUtils.replace(source, "：", "/");
		source = StringUtils.replace(source, ":", "/");
		// 问号
		source = StringUtils.replace(source, "?", "/");
		source = StringUtils.replace(source, "？", "/");
		// 分号
		source = StringUtils.replace(source, ";", "/");
		source = StringUtils.replace(source, "；", "/");

		source = StringUtils.replace(source, "＼", "/");
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat();
		for (String pattern : datePatterns) {
			simpleDateFormat.applyPattern(pattern);
			try {
				returnDate = simpleDateFormat.parse(source);
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(returnDate);
				int year = calendar.get(Calendar.YEAR);
				if (year < 100) {
					calendar.set(Calendar.YEAR, year + 2000);
				}
				returnDate = calendar.getTime();
			} catch (ParseException e) {
				continue;
			}
			break;
		}
		return returnDate;
	}
	
	public static Date getLastWeekMonday(){

	    Calendar date=Calendar.getInstance(Locale.CHINA);

	    date.setFirstDayOfWeek(Calendar.MONDAY);//将每周第一天设为星期一，默认是星期天

	    date.add(Calendar.WEEK_OF_MONTH,-1);//周数减一，即上周

	    date.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);//日子设为星期天
	    
	    return date.getTime();
	}
	
	public static Date getLastWeekSunday(){

	    Calendar date=Calendar.getInstance(Locale.CHINA);

	    date.setFirstDayOfWeek(Calendar.MONDAY);//将每周第一天设为星期一，默认是星期天

	    date.add(Calendar.WEEK_OF_MONTH,-1);//周数减一，即上周

	    date.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);//日子设为星期天

	    return date.getTime();

	}
	
	/**
     * 得到某年某周的第一天
     *
     * @param year
     * @param week
     * @return
     */
    public static Date getFirstDayOfWeek(int year, int week) {
        week = week - 1;
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, Calendar.JANUARY);
        calendar.set(Calendar.DATE, 1);

        Calendar cal = (Calendar) calendar.clone();
        cal.add(Calendar.DATE, week * 7);

        return getFirstDayOfWeek(cal.getTime());
    }

    /**
     * 得到某年某周的最后一天
     *
     * @param year
     * @param week
     * @return
     */
    public static Date getLastDayOfWeek(int year, int week) {
        week = week - 1;
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, Calendar.JANUARY);
        calendar.set(Calendar.DATE, 1);
        Calendar cal = (Calendar) calendar.clone();
        cal.add(Calendar.DATE, week * 7);

        return getLastDayOfWeek(cal.getTime());
    }

    /**
     * 取得当前日期所在周的第一天
     *
     * @param date
     * @return
     */
    public static Date getFirstDayOfWeek(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setFirstDayOfWeek(Calendar.SUNDAY);
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_WEEK,
                      calendar.getFirstDayOfWeek()); // Sunday
        return calendar.getTime();
    }

    /**
     * 取得当前日期所在周的最后一天
     *
     * @param date
     * @return
     */
    public static Date getLastDayOfWeek(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setFirstDayOfWeek(Calendar.SUNDAY);
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_WEEK,
                     calendar.getFirstDayOfWeek() + 6); // Saturday
        return calendar.getTime();
    }

    /**
     * 取得当前日期所在周的前一周最后一天
     *
     * @param date
     * @return
     */
    public static Date getLastDayOfLastWeek(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return getLastDayOfWeek(calendar.get(Calendar.YEAR),
                                calendar.get(Calendar.WEEK_OF_YEAR) - 1);
    }



    /**
     * 返回指定年月的月的第一天
     *
     * @param year
     * @param month
     * @return
     */
    public static Date getFirstDayOfMonth(Integer year, Integer month) {
        Calendar calendar = Calendar.getInstance();
        if (year == null) {
            year = calendar.get(Calendar.YEAR);
        }
        if (month == null) {
            month = calendar.get(Calendar.MONTH);
        }
        calendar.set(year, month, 1);
        return calendar.getTime();
    }



    /**
     * 返回指定年月的月的最后一天
     *
     * @param year
     * @param month
     * @return
     */
    public static Date getLastDayOfMonth(Integer year, Integer month) {
        Calendar calendar = Calendar.getInstance();
        if (year == null) {
            year = calendar.get(Calendar.YEAR);
        }
        if (month == null) {
            month = calendar.get(Calendar.MONTH);
        }
        calendar.set(year, month, 1);
        calendar.roll(Calendar.DATE, -1);
        return calendar.getTime();
    }

    /**
     * 返回指定日期的上个月的最后一天
     *
     * @param year
     * @param month
     * @return
     */
    public static Date getLastDayOfLastMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(calendar.get(Calendar.YEAR),
                     calendar.get(Calendar.MONTH) - 1, 1);
        calendar.roll(Calendar.DATE, -1);
        return calendar.getTime();
    }

    /**
     * 返回指定日期的季的第一天
     *
     * @param year
     * @param quarter
     * @return
     */
    public static Date getFirstDayOfQuarter(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return getFirstDayOfQuarter(calendar.get(Calendar.YEAR),
                                    getQuarterOfYear(date));
    }

    /**
     * 返回指定年季的季的第一天
     *
     * @param year
     * @param quarter
     * @return
     */
    public static Date getFirstDayOfQuarter(Integer year, Integer quarter) {
        Calendar calendar = Calendar.getInstance();
        Integer month = new Integer(0);
        if (quarter == 1) {
            month = 1 - 1;
        } else if (quarter == 2) {
            month = 4 - 1;
        } else if (quarter == 3) {
            month = 7 - 1;
        } else if (quarter == 4) {
            month = 10 - 1;
        } else {
            month = calendar.get(Calendar.MONTH);
        }
        return getFirstDayOfMonth(year, month);
    }

    /**
     * 返回指定日期的季的最后一天
     *
     * @param year
     * @param quarter
     * @return
     */
    public static Date getLastDayOfQuarter(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return getLastDayOfQuarter(calendar.get(Calendar.YEAR),
                                   getQuarterOfYear(date));
    }

    /**
     * 返回指定年季的季的最后一天
     *
     * @param year
     * @param quarter
     * @return
     */
    public static Date getLastDayOfQuarter(Integer year, Integer quarter) {
        Calendar calendar = Calendar.getInstance();
        Integer month = new Integer(0);
        if (quarter == 1) {
            month = 3 - 1;
        } else if (quarter == 2) {
            month = 6 - 1;
        } else if (quarter == 3) {
            month = 9 - 1;
        } else if (quarter == 4) {
            month = 12 - 1;
        } else {
            month = calendar.get(Calendar.MONTH);
        }
        return getLastDayOfMonth(year, month);
    }

    /**
     * 返回指定日期的上一季的最后一天
     *
     * @param year
     * @param quarter
     * @return
     */
    public static Date getLastDayOfLastQuarter(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return getLastDayOfLastQuarter(calendar.get(Calendar.YEAR),
                                       getQuarterOfYear(date));
    }

    /**
     * 返回指定年季的上一季的最后一天
     *
     * @param year
     * @param quarter
     * @return
     */
    public static Date getLastDayOfLastQuarter(Integer year, Integer quarter) {
        Calendar calendar = Calendar.getInstance();
        Integer month = new Integer(0);
        if (quarter == 1) {
            month = 12 - 1;
        } else if (quarter == 2) {
            month = 3 - 1;
        } else if (quarter == 3) {
            month = 6 - 1;
        } else if (quarter == 4) {
            month = 9 - 1;
        } else {
            month = calendar.get(Calendar.MONTH);
        }
        return getLastDayOfMonth(year, month);
    }

    /**
     * 返回指定日期的季度
     *
     * @param date
     * @return
     */
    public static int getQuarterOfYear(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.MONTH) / 3 + 1;
    }
    
    public static String getDateTimeChar(Date d1,Date d2){
    	 long bw = d2.getTime()-d1.getTime();
 	    long day=bw/(24*60*60*1000);   
 	    long hour=(bw/(60*60*1000)-day*24);   
 	    long min=((bw/(60*1000))-day*24*60-hour*60);   
 	    long s=(bw/1000-day*24*60*60-hour*60*60-min*60);   

 	    return ""+day+"天"+hour+"小时"+min+"分"+s+"秒";  
   }

	public static long[] getDateTimeCha(Date d1, Date d2) {
		long bw = d2.getTime() - d1.getTime();
		long day = bw / (24 * 60 * 60 * 1000);
		long hour = (bw / (60 * 60 * 1000) - day * 24);
		long min = ((bw / (60 * 1000)) - day * 24 * 60 - hour * 60);
		long s = (bw / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);
		long[] dayarry = new long[] { day, hour, min, s };

		return dayarry;
	}
	/**
	 * 获取当前时间到本周末结束时间的时间差
	 * @return
	 */
	public static long getThisDayTimeToWeekendBw(){
		Date cdate = new Date();
	    Date endweek = DateUtil.getLastDayOfWeek(cdate);
	    DateTime endweekTime = new DateTime(endweek);
	    endweekTime.add(DateTime.DAY_FIELD, 1);
	    endweekTime.set(DateTime.HOUR_FIELD, 23);
	    endweekTime.set(DateTime.MINUTE_FIELD, 59);
	    endweekTime.set(DateTime.SECOND_FIELD, 59);
	    //System.out.println(DateUtil.converDate2String(endweekTime.toDate()));
	    long bw = endweekTime.toDate().getTime()-cdate.getTime();
	    return bw;
	}
	
	public static void main(String[] args){
		//String timeStampStr="20130527171430";
        //System.out.println(convertTimeStamp14StrToDate(timeStampStr));
		System.out.println(DateUtil.converDate2String(getLastWeekMonday()));
		System.out.println(DateUtil.converDate2String(getLastWeekSunday()));
		System.out.println(DateUtil.converDate2String(getLastDayOfWeek(new Date())));
	}
}
