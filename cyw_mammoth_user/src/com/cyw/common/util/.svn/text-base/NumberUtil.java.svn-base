package com.cyw.common.util;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
/**
 * 数字格式化通用类
 * @author Administrator
 *
 */
public class NumberUtil {

	/**
	 * 将金钱格式化成两位小数，不进行四舍五入，不足补0
	 * @param str 被格式化的金钱
	 * 
	 * 9999.99     9999.99
	 * 9999        9999.00
	 * 9999.9989   9999.99
	 * null or "" or 非合法数值   返回  0.00
	 * @return
	 */
	public static String moneyFormat2TwoDigit (String str) {
		try {
			BigDecimal b = new BigDecimal(str);
		    //,代表分隔符#,##.00
		    //.后面的##代表位数 如果换成0 效果就是位数不足0补齐
		    DecimalFormat d1 =new DecimalFormat("#.00");
		    // 设置舍入模式
		    d1.setRoundingMode(RoundingMode.FLOOR); 
		  	return d1.format(b) ;
		} catch (NullPointerException e) {
			return "0.00";
		} catch (NumberFormatException e) {
			return "0.00";
		}
	    
	}
	 /**   
     * 对double数据进行取精度.   
     * <p>   
     * For example: <br>   
     * double value = 100.345678; <br>   
     * double ret = round(value,4,BigDecimal.ROUND_HALF_UP); <br>   
     * ret为100.3457 <br>   
     *    
     * @param value   
     *            double数据.   
     * @param scale   
     *            精度位数(保留的小数位数).   
     * @param roundingMode   
     *            精度取值方式.   
     * @return 精度计算后的数据.   
     */    
    public static double round(double value, int scale, int roundingMode) {     
        BigDecimal bd = new BigDecimal(value);     
        bd = bd.setScale(scale, roundingMode);     
        double d = bd.doubleValue();     
        bd = null;     
        return d;     
    }
	
	public static void main(String[] args) {
		
		
		BigDecimal bd = new BigDecimal("3.00");    
		bd.setScale(3) ;
		System.out.println(bd.doubleValue());
		/*//ROUND_UP     
        //只要第2位后面存在大于0的小数，则第2位就+1     
        System.out.println(round(12,2,BigDecimal.ROUND_UP));//12.35     
        //ROUND_DOWN     
        //与ROUND_UP相反     
        //直接舍弃第2位后面的所有小数     
        System.out.println(round(12,2,BigDecimal.ROUND_DOWN));//12.34     
        //ROUND_CEILING     
        //如果数字>0 则和ROUND_UP作用一样     
        //如果数字<0 则和ROUND_DOWN作用一样     
        System.out.println(round(12,2,BigDecimal.ROUND_CEILING));//12.35     
        System.out.println(round(12,2,BigDecimal.ROUND_FLOOR));//12.34     
        //ROUND_HALF_UP [这种方法最常用]     
        //如果第3位数字>=5,则第2位数字+1     
        //备注:只看第3位数字的值,不会考虑第3位之后的小数的     
        System.out.println(round(12,2,BigDecimal.ROUND_HALF_UP));//12.35     
        System.out.println(round(12,2,BigDecimal.ROUND_HALF_UP));//12.34     
        //ROUND_HALF_DOWN     
        //如果第3位数字>=5,则做ROUND_UP     
        //如果第3位数字<5,则做ROUND_DOWN     
        System.out.println(round(12,2,BigDecimal.ROUND_HALF_DOWN));//12.35     
        System.out.println(round(12,2,BigDecimal.ROUND_HALF_DOWN));//12.34     
        //ROUND_HALF_EVEN     
        //如果第3位是偶数,则做ROUND_HALF_DOWN     
        //如果第3位是奇数,则做ROUND_HALF_UP     
        System.out.println(round(12,2,BigDecimal.ROUND_HALF_EVEN));//12.35     
        System.out.println(round(12,4,BigDecimal.ROUND_HALF_EVEN));//12.35    
*/	}
}
