package com.zoomoor.jy.util;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 描述：数值类计算工具
 * @author zhangzhenxing
 * @CreateDate 2015-06-09
 * */
public class MathUtil {
	
	/**
	 * 描述：将doubel类似数值 四舍五入 取两位小数
	 * @param number 需要个耍的数字
	 * @param digit 保留小数位位数
	 * */
	public static Double toFixed(Double number,int digit){
		if(number==null){
			return 0.0d;
		}
		String str = number.toString();  
        BigDecimal bd = new BigDecimal(Double.parseDouble(str));  
        bd.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();  
        DecimalFormat df = new DecimalFormat("#.00");   
        df.format(Double.parseDouble(str));  
        String.format("%.2f", Double.parseDouble(str));  
        NumberFormat nf = NumberFormat.getNumberInstance();   
        nf.setMaximumFractionDigits(digit);   
        return Double.parseDouble(nf.format(Double.parseDouble(str)).replaceAll(",", ""));
	}
	
	/**
	 * 描述:判断是否是Integer类型
	 * */
	public static boolean isInteger(String value) {
		  try {
		   Integer.parseInt(value);
		   return true;
		  } catch (NumberFormatException e) {
		   return false;
		  }
	 }
	
	/**
	 * 格式化日期
	 * @param date date对象
	 * @param fmtStr 格式化字符串
	 * */
	public static String fmtDate(Date date,String fmtStr){
		SimpleDateFormat format = new SimpleDateFormat(fmtStr);
		return format.format(date);
	}
}
