package com.cyw.common.util;

import java.math.BigDecimal;

public class MathUtil {

	public static int ROUND_TYPE_1 = 1;
	public static int ROUND_TYPE_2 = 2;
	public static int ROUND_TYPE_3 = 3;
	/**
	 * 根据类型执行金额计算舍入
	 * @param digit 要处理的数字
	 * @param type 处理类型 1：四舍五入，2：逢数进位，3：截断
	 * @param saveNum 保留的小数位数
	 * @return
	 */
	public static Double doubleRound(double digit, int type, int saveNum){
		if(type > 3 || type < 1){
			return digit;
		}
		if(saveNum < 0){
			return digit;
		}
		if(type == ROUND_TYPE_1){
			return Double.parseDouble(String.format("%."+saveNum+"f", digit));
		}else{
			BigDecimal bd = new BigDecimal(digit);
			bd = bd.setScale(saveNum, type);
			return bd.doubleValue();
		}
	}
}
