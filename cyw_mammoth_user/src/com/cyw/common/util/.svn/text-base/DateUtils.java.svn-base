package com.cyw.common.util;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

// TODO: Auto-generated Javadoc
/**
 * The Class DateUtils.
 *
 * @author Tom
 */
public class DateUtils {
	
	/** The buffer. */
	private StringBuffer buffer = new StringBuffer();
	
	/** The zero. */
	private static String ZERO = "0";
	
	/** The date. */
	private static DateUtils date;
	
	/** The format. */
	public static SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
	
	/** The format1.
	 * yyyyMMdd HH:mm:ss
	 *  */
	public static SimpleDateFormat format1 = new SimpleDateFormat("yyyyMMdd HH:mm:ss");
	
	/** The format2. */
	public static SimpleDateFormat format2 = new SimpleDateFormat("yyyyMMddHHmmssSSS");
	
	/** The format2. */
	public static SimpleDateFormat format3 = new SimpleDateFormat("HH:mm:ss");
    
    /** The date format.
     * yyyy-MM-dd HH:mm:ss
     *  */
    public static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    /**
     * yyyy-MM-dd
     */
    public static SimpleDateFormat dateFormat2 = new SimpleDateFormat("yyyy-MM-dd");

/**
 * Gets the date.
 *
 * @author admin
 * @param date 时间
 * @return the date
 * @date 2011年4月21日
 * @Description 格式化一个时间,以默认的yyyy-MM-dd hh:mm:ss 格式
 */
public static String getDate(Date date){
	return dateFormat.format(date);
}

/**
 * 获取指定格式的字符串类型时间
 * @param date
 * @param formatter
 * @return
 */
public static String getDate(Date date,String formatter) {
	SimpleDateFormat format = new SimpleDateFormat(formatter);
	return format.format(date);
}
/**
 * 获取指定格式的字符串类型时间
 * @param date
 * @param formatter
 * @return
 */
public static String getYMD(Date date) {
	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	return format.format(date);
}
	
	/**
	 * Gets the now string.
	 *
	 * @return the now string
	 */
	public String getNowString() {
		Calendar calendar = getCalendar();
		buffer.delete(0, buffer.capacity());
		buffer.append(getYear(calendar));

		if (getMonth(calendar) < 10) {
			buffer.append(ZERO);
		}
		buffer.append(getMonth(calendar));

		if (getDate(calendar) < 10) {
			buffer.append(ZERO);
		}
		buffer.append(getDate(calendar));
		if (getHour(calendar) < 10) {
			buffer.append(ZERO);
		}
		buffer.append(getHour(calendar));
		if (getMinute(calendar) < 10) {
			buffer.append(ZERO);
		}
		buffer.append(getMinute(calendar));
		if (getSecond(calendar) < 10) {
			buffer.append(ZERO);
		}
		buffer.append(getSecond(calendar));
		return buffer.toString();
	}

	/**
	 * Gets the date field.
	 *
	 * @param date the date
	 * @param field the field
	 * @return the date field
	 */
	private static int getDateField(Date date,int field) {
		Calendar c = getCalendar();
		c.setTime(date);
		return c.get(field);
	}
	
	/**
	 * Gets the years between date.
	 *
	 * @param begin the begin
	 * @param end the end
	 * @return the years between date
	 */
	public static int getYearsBetweenDate(Date begin,Date end){
		int bYear=getDateField(begin,Calendar.YEAR);
		int eYear=getDateField(end,Calendar.YEAR);
		return eYear-bYear;
	}
	
	/**
	 * Gets the months between date.
	 *
	 * @param begin the begin
	 * @param end the end
	 * @return the months between date
	 */
	public static int getMonthsBetweenDate(Date begin,Date end){
		int bMonth=getDateField(begin,Calendar.MONTH);
		int eMonth=getDateField(end,Calendar.MONTH);
		return eMonth-bMonth;
	}
	
	/**
	 * Gets the weeks between date.
	 *
	 * @param begin the begin
	 * @param end the end
	 * @return the weeks between date
	 */
	public static int getWeeksBetweenDate(Date begin,Date end){
		int bWeek=getDateField(begin,Calendar.WEEK_OF_YEAR);
		int eWeek=getDateField(end,Calendar.WEEK_OF_YEAR);
		return eWeek-bWeek;
	}
	
	/**
	 * Gets the days between date.
	 *
	 * @param begin the begin
	 * @param end the end
	 * @return the days between date
	 */
	public static int getDaysBetweenDate(Date begin,Date end){
		int bDay=getDateField(begin,Calendar.DAY_OF_YEAR);
		int eDay=getDateField(end,Calendar.DAY_OF_YEAR);
		return eDay-bDay;
	}
	
	
	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String args[]){
		/*System.out.println(getSpecficDateStart(new Date(), 288));*/
		//System.out.println(getNowTime());
		DecimalFormat format = new DecimalFormat("#.00");
		format.setMaximumFractionDigits(2);
		System.out.println(Double.parseDouble("36.000"));
		System.out.println(Double.valueOf("36.00"));
		BigDecimal n = new BigDecimal(30);
        BigDecimal n2 = n.setScale(2, BigDecimal.ROUND_HALF_UP);
		System.out.println( n2.doubleValue());
	}

	/**
	 * 获取date年后的amount年的第一天的开始时间.
	 *
	 * @param date the date
	 * @param amount            可正、可负
	 * @return the specfic year start
	 */
	public static Date getSpecficYearStart(Date date,int amount) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.YEAR, amount);
		cal.set(Calendar.DAY_OF_YEAR, 1);
		return getStartDate(cal.getTime());
	}

	/**
	 * 获取date年后的amount年的最后一天的终止时间.
	 *
	 * @param date the date
	 * @param amount            可正、可负
	 * @return the specfic year end
	 */
	public static Date getSpecficYearEnd(Date date,int amount) {
		Date temp = getStartDate(getSpecficYearStart(date,amount + 1));
		Calendar cal = Calendar.getInstance();
		cal.setTime(temp);
		cal.add(Calendar.DAY_OF_YEAR, -1);
		return getFinallyDate(cal.getTime());
	}

	/**
	 * 获取date月后的amount月的第一天的开始时间.
	 *
	 * @param date the date
	 * @param amount            可正、可负
	 * @return the specfic month start
	 */
	public static Date getSpecficMonthStart(Date date,int amount) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.MONTH, amount);
		cal.set(Calendar.DAY_OF_MONTH, 1);
		return getStartDate(cal.getTime());
	}

	/**
	 * 获取当前自然月后的amount月的最后一天的终止时间.
	 *
	 * @param date the date
	 * @param amount            可正、可负
	 * @return the specfic month end
	 */
	public static Date getSpecficMonthEnd(Date date,int amount) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(getSpecficMonthStart(date,amount + 1));
		cal.add(Calendar.DAY_OF_YEAR, -1);
		return getFinallyDate(cal.getTime());
	}

	/**
	 * 获取date周后的第amount周的开始时间（这里星期一为一周的开始）.
	 *
	 * @param date the date
	 * @param amount            可正、可负
	 * @return the specfic week start
	 */
	public static Date getSpecficWeekStart(Date date,int amount) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.setFirstDayOfWeek(Calendar.MONDAY); /* 设置一周的第一天为星期一 */
		cal.add(Calendar.WEEK_OF_MONTH, amount);
		cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		return getStartDate(cal.getTime());
	}

	/**
	 * 获取date周后的第amount周的最后时间（这里星期日为一周的最后一天）.
	 *
	 * @param date the date
	 * @param amount            可正、可负
	 * @return the specfic week end
	 */
	public static Date getSpecficWeekEnd(Date date,int amount) {
		Calendar cal = Calendar.getInstance();
		cal.setFirstDayOfWeek(Calendar.MONDAY); /* 设置一周的第一天为星期一 */
		cal.add(Calendar.WEEK_OF_MONTH, amount);
		cal.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
		return getFinallyDate(cal.getTime());
	}
	
	/**
	 * Gets the specfic date start.
	 *
	 * @param date the date
	 * @param amount the amount
	 * @return the specfic date start
	 */
	public static Date getSpecficDateStart(Date date,int amount){
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DAY_OF_YEAR, amount);
		return getStartDate(cal.getTime());
	}

	/**
	 * 得到指定日期的一天的的最后时刻23:59:59.
	 *
	 * @param date the date
	 * @return the finally date
	 */
	public static Date getFinallyDate(Date date) {
		String temp = format.format(date);
		temp += " 23:59:59";

		try {
			return format1.parse(temp);
		} catch (ParseException e) {
			return null;
		}
	}

	/**
	 * 得到指定日期的一天的开始时刻00:00:00.
	 *
	 * @param date the date
	 * @return the start date
	 */
	public static Date getStartDate(Date date) {
		String temp = format.format(date);
		temp += " 00:00:00";

		try {
			return format1.parse(temp);
		} catch (Exception e) {
			return null;
		}
	}
	
	/**
	 * Checks if is in date.
	 *
	 * @param date 指定比较日期
	 * @param compareDate the compare date
	 * @return true, if is in date
	 */
	public static boolean isInDate(Date date,Date compareDate){
		if(compareDate.after(getStartDate(date))&&compareDate.before(getFinallyDate(date))){
			return true;
		}else{
			return false;
		}
	}
	
	/**
	 * Gets the current time.
	 *
	 * @return the current time
	 */
	public static String getCurrentTime()
	{
		return format2.format(new Date());
	}

	/**
	 * Gets the year.
	 *
	 * @param calendar the calendar
	 * @return the year
	 */
	private int getYear(Calendar calendar) {
		return calendar.get(Calendar.YEAR);
	}

	/**
	 * Gets the month.
	 *
	 * @param calendar the calendar
	 * @return the month
	 */
	private int getMonth(Calendar calendar) {
		return calendar.get(Calendar.MONDAY) + 1;
	}

	/**
	 * Gets the date.
	 *
	 * @param calendar the calendar
	 * @return the date
	 */
	private int getDate(Calendar calendar) {
		return calendar.get(Calendar.DATE);
	}

	/**
	 * Gets the hour.
	 *
	 * @param calendar the calendar
	 * @return the hour
	 */
	private int getHour(Calendar calendar) {
		return calendar.get(Calendar.HOUR_OF_DAY);
	}

	/**
	 * Gets the minute.
	 *
	 * @param calendar the calendar
	 * @return the minute
	 */
	private int getMinute(Calendar calendar) {
		return calendar.get(Calendar.MINUTE);
	}

	/**
	 * Gets the second.
	 *
	 * @param calendar the calendar
	 * @return the second
	 */
	private int getSecond(Calendar calendar) {
		return calendar.get(Calendar.SECOND);
	}

	/**
	 * Gets the calendar.
	 *
	 * @return the calendar
	 */
	private static Calendar getCalendar() {
		return Calendar.getInstance();
	}

	/**
	 * Gets the date instance.
	 *
	 * @return the date instance
	 */
	public static DateUtils getDateInstance() {
		if (date == null) {
			date = new DateUtils();
		}
		return date;
	}

	public static Date getDateAddStr(Date reachDate, String reachtime) {
		String reachDateStr=dateFormat2.format(reachDate);
		try {
			return dateFormat.parse((reachDateStr+" "+reachtime).toString());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return reachDate;
	}
	
	/** 
	 * 比较两个日期之间的大小 
	 *  
	 * @param d1 
	 * @param d2 
	 * @return 前者大于后者返回true 反之false 
	 */  
	public boolean compareDate(Date d1, Date d2) {  
	    Calendar c1 = Calendar.getInstance();  
	    Calendar c2 = Calendar.getInstance();  
	    c1.setTime(d1);  
	    c2.setTime(d2);  
	  
	    int result = c1.compareTo(c2);  
	    if (result >= 0)  
	        return true;  
	    else  
	        return false;  
	}
	
	/**
	 * 取得当前系统时间
	 * @return
	 */
	public static Date getCurrSysDate(){
		return Calendar.getInstance().getTime();
	}
	
	/**
	* 字符串转换成日期
	* @param str
	* @return date
	*/
	public static Date StrToDate(String str) {
	  
	   SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	   Date date = null;
	   try {
	    date = format.parse(str);
	   } catch (ParseException e) {
	    e.printStackTrace();
	   }
	   return date;
	}
	
	/**
	* 字符串转换成日期
	* @param str
	* @return date
	*/
	public static Date StrToFullDate(String str) {
	  
	   SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	   Date date = null;
	   try {
	    date = format.parse(str);
	   } catch (ParseException e) {
	    e.printStackTrace();
	   }
	   return date;
	}
	public static String getNowTime()
	{
		return format3.format(new Date());
	}
}
