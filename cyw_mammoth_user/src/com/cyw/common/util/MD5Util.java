/**
 *@Project: znyt
 *@Title: MD5Util.java 
 *@Package com.znyt.util 
 *@Description: TODO(用一句话描述该文件做什么) 
 *@author wangxiaojun  email:wangxiaojun@zoomoor.com 
 *@date 2014-10-20 上午10:47:31 
 *@Copyright:Copyright (c) 
 *@Company:zoomoor
 *@version V1.0 
 */
package com.cyw.common.util;

import java.security.MessageDigest;

// TODO: Auto-generated Javadoc
/**
 * The Class MD5Util.
 */
public class MD5Util {
	
	/**
	 * Md5code.
	 *
	 * @param sourceStr the source str
	 * @return the string
	 */
	public static String md5code(String sourceStr) {
		try {
			// 获得MD5摘要算法的 MessageDigest对象
			MessageDigest mdInst = MessageDigest.getInstance("MD5");
			// 使用指定的字节更新摘要
			mdInst.update(sourceStr.getBytes());
			// 获得密文
			byte[] md = mdInst.digest();
			// 把密文转换成十六进制的字符串形式
			StringBuffer buf = new StringBuffer();
			for (int i = 0; i < md.length; i++) {
				int tmp = md[i];
				if (tmp < 0)
					tmp += 256;
				if (tmp < 16)
					buf.append("0");
				buf.append(Integer.toHexString(tmp));
			}
			return buf.toString().substring(8, 24);// 16位加密
			// return buf.toString();// 32位加密
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * Md5code32.
	 *
	 * @param sourceStr the source str
	 * @return the string
	 */
	public static String md5code32(String sourceStr) {
		try {
			// 获得MD5摘要算法的 MessageDigest对象
			MessageDigest mdInst = MessageDigest.getInstance("MD5");
			// 使用指定的字节更新摘要
			mdInst.update(sourceStr.getBytes());
			// 获得密文
			byte[] md = mdInst.digest();
			// 把密文转换成十六进制的字符串形式
			StringBuffer buf = new StringBuffer();
			for (int i = 0; i < md.length; i++) {
				int tmp = md[i];
				if (tmp < 0)
					tmp += 256;
				if (tmp < 16)
					buf.append("0");
				buf.append(Integer.toHexString(tmp));
			}
//			return buf.toString().substring(8, 24);// 16位加密
			 return buf.toString();// 32位加密
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		String str = "123456";
		String encryptStr = md5code32(str);
		System.out.println("加密前：" + str);
		System.out.println("加密后：" + encryptStr);
	}
}