/**
 *@Project: znyt
 *@Title: CommonUtil.java 
 *@Package com.znyt.util 
 *@Description: TODO(用一句话描述该文件做什么) 
 *@author wangxiaojun  email:wangxiaojun@zoomoor.com 
 *@date 2014-10-20 上午11:06:50 
 *@Copyright:Copyright (c) 
 *@Company:zoomoor
 *@version V1.0 
 */
package com.cyw.common.util;

import java.security.MessageDigest;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;


// TODO: Auto-generated Javadoc
/**
 * The Class CommonUtil.
 *
 * @ClassName: CommonUtil
 * @Title:项目日常所使用到的常用方法 
 * @Description:TODO(这里用一句话描述这个类的作用) 
 * @Author:Administrator 
 * @Since:2014-10-20上午11:06:50 
 * @Version:1.0 
 */

public class CommonUtil {

	/**
	 * Reflush session.
	 *
	 * @param request the request
	 */
	public void reflushSession(HttpServletRequest request){
		
	}
   
   /**
    * 获取到客户端IP地址.
    *
    * @param request the request
    * @return the ip addr
    * @Title:  getIpAddr
    * @Description: TODO(这里用一句话描述这个方法的作用)
    * @Author: wangxiaojun
    * @Since: 2014-10-20上午11:08:59
    */
    public static String getIpAddr(HttpServletRequest request)
    {
        String ip = null;
        
        ip = request.getHeader("Cdn-Src-Ip");
        if(ip != null && !"".equals(ip))
        {
            return ip;
        }
        
        ip = request.getHeader("x-forwarded-for");
        if(ip != null && ip.indexOf(',') > 0)
        {
            String[] tmp = ip.split("[,]");
            for(int i = 0; tmp != null && i < tmp.length; i++)
            {
                if(tmp[i] != null && tmp[i].length() > 0 && !"unknown".equalsIgnoreCase(tmp[i]))
                {
                    ip = tmp[i];
                    break;
                }
            }
        }
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip))
        {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip))
        {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip))
        {
            ip = request.getRemoteAddr();
        }
        return ip;
    }
    
    /**
     * Md5code.
     *
     * @param sourceStr the source str
     * @return the string
     * @Title:  获取MD5加密后字符串
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @Author: wangxiaojun
     * @Since: 2014-10-20上午11:09:31
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
//			return buf.toString().substring(8, 24);// 16位加密
			 return buf.toString();// 32位加密
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
    
    /**
     * Gets the timestamp.
     *
     * @return the timestamp
     * @Title:  获取Timestamp时间
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @Author: wangxiaojun
     * @Since: 2014-10-20下午1:46:19
     */
    public static Timestamp getTimestamp(){
	    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");   
	    String time = df.format(new Date());   
	    Timestamp ts = Timestamp.valueOf(time);
		return ts;
    }
    
    /**
     * Check email.
     *
     * @param s the s
     * @return true, if successful
     * @Title:  checkEmail
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @Author: wangxiaojun
     * @Since: 2014-10-28下午4:12:31
     */
    public static boolean   checkEmail(String   s){   
        //String   regex="[a-zA-Z][\\w_]+@\\w+(\\.\\w+)+"; 
        String   regex= "^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$";
        Pattern   p=Pattern.compile(regex);   
        Matcher   m=p.matcher(s);   
        return   m.matches();   
        }    
    
    /**
     *   
     * 验证手机号码  .
     *
     * @param mobiles the mobiles
     * @return  [0-9]{5,9}
     */  
    public static boolean isMobileNO(String mobiles){  
     boolean flag = false;  
     try{  
     // Pattern p = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$");  
      Pattern p = Pattern.compile("^1[0-9]{10}$");  
      Matcher m = p.matcher(mobiles);  
      flag = m.matches();  
     }catch(Exception e){  
      flag = false;  
     }  
     return flag;  
    }
    
    /**
     * Generate random.
     *
     * @return the string
     * @Title:  生成六位随机数
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @Author: wangxiaojun
     * @Since: 2014-11-15下午4:27:44
     */
    @SuppressWarnings({ "rawtypes" })   
    public static String generateRandom() {  
        String[] beforeShuffle = new String[] { "2", "3", "4", "5", "6", "7",  
                "8", "9", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J",  
                "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V",  
                "W", "X", "Y", "Z" };  
        List list = Arrays.asList(beforeShuffle);  
        Collections.shuffle(list);  
        StringBuilder sb = new StringBuilder();  
        for (int i = 0; i < list.size(); i++) {  
            sb.append(list.get(i));  
        }  
        String afterShuffle = sb.toString();  
        String result = afterShuffle.substring(3, 9);  
        return result;  
    } 
    /**
     * 数组去重复
     * @param strArr 数组（入参）
     * @return
     */
    public static String[] array_unique(String[] strArr) {
		Set<String> set = new HashSet<String>();
		set.addAll(Arrays.asList(strArr));
		return set.toArray(new String[0]);
	}
    
    /**
     * The main method.
     *
     * @param args the arguments
     */
    public static void main(String[] args) {
//    	System.out.println(generateWord());
	}
}
