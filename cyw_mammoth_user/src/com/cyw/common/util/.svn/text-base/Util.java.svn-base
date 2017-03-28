package com.cyw.common.util;

import it.sauronsoftware.base64.Base64;

import java.io.UnsupportedEncodingException;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;


// TODO: Auto-generated Javadoc
/**
 * 工具类.
 *
 * @author Administrator
 */
public class Util {
	
	/** The Constant SORT_DESC. */
	public static final String SORT_DESC = "desc";
	
	/** The Constant SORT_ASC. */
	public static final String SORT_ASC = "asc";
	
	// 判断是否为空，并附默认值
	/**
	 * Check nullto default.
	 *
	 * @param value the value
	 * @param defaultValue the default value
	 * @return the string
	 */
	public static String checkNulltoDefault(String value, String defaultValue) {
		if (checkNull(value)) {
			return defaultValue;
		} else {
			return value;
		}
	}
	
	/**
	 * Check string not null.
	 *
	 * @param value the value
	 * @return true, if successful
	 */
	public static boolean checkStringNotNull(String value){
		if(value!=null && !"".equals(value)){
			return true;
		}
		return false;
	}
	
	/**
	 * Check not null.
	 *
	 * @param value the value
	 * @return true, if successful
	 */
	public static boolean checkNotNull(Object value) {
		if (value==null){
			return false;
		}
		return (value.toString().length()>0);
	}

	/**
	 * Check null.
	 *
	 * @param value the value
	 * @return true, if successful
	 */
	public static boolean checkNull(Object value) {
		if (value==null){
			return true;
		}
		
		return (value.toString().length()==0);
		
	}

	/**
	 * Check not null.
	 *
	 * @param values the values
	 * @return true, if successful
	 */
	public static boolean checkNotNull(Object[] values) {
		if (checkNull(values)) return false;
		if (values.length <= 0)return false;
		for (Object value : values) {
			if (!checkNotNull(value)) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Check not null.
	 *
	 * @param values the values
	 * @return true, if successful
	 */
	public static boolean checkNotNull(String[] values) {
		if (values==null || values.length <= 0)
			return false;
		for (String value : values) {
			if (!checkNotNull(value)) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Check num.
	 *
	 * @param value the value
	 * @return true, if successful
	 */
	public static boolean checkNum(String value) {
		return value.matches("\\d+");
	}

	

	
	
	/**
	 * 	获得客户端真实IP地址(使用反向代理软件的情况).
	 *
	 * @param request the request
	 * @return the remort ip
	 */
	public static String getRemortIP(HttpServletRequest request) {
	  if (request.getHeader("X-Forwarded-For") == null) {
	   return request.getRemoteAddr();
	  }
	  return request.getHeader("X-Forwarded-For");
	}
	
	/**
	 * 	获得客户端真实IP地址(使用了Squid反向代理软件的情况).
	 *
	 * @param request the request
	 * @return the ip addr
	 */	
	public static String getIpAddr(HttpServletRequest request) {
       String ip = request.getHeader("X-Forwarded-For");
       if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
           ip = request.getHeader("Proxy-Client-IP");
       }
       if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
           ip = request.getHeader("WL-Proxy-Client-IP");
       }
       if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
           ip = request.getRemoteAddr();
       }
       return ip;
	}
	
	// sql 语句过滤
	/**
	 * Sql char fiter.
	 *
	 * @param sql the sql
	 * @return the string
	 */
	public static String sqlCharFiter(String sql) {
		// sql=sql.replaceAll("['~!@#$%\\^&\\*\\(\\)_\\+\\|\\-=;,\\./\\{\\}:<>\\?`\\\\\"]","");
		if(Util.isEmpty(sql)){
			return sql;
		}
		sql = sql.replaceAll("[\\[\\]'~!@#$%\\^&\\*\\(\\)\\+\\|\\-=;,\\./\\{\\}:<>\\?`\\\\\"]+","");
		/*
		 * sql=sql.replaceAll("[']", ""); sql=sql.replaceAll("[~]", "");
		 * sql=sql.replaceAll("[!]", ""); sql=sql.replaceAll("[@]", "");
		 * sql=sql.replaceAll("[#]", ""); sql=sql.replaceAll("[$]", "");
		 * sql=sql.replaceAll("[%]", ""); sql=sql.replaceAll("[\\^]", "");
		 * sql=sql.replaceAll("[&]", ""); sql=sql.replaceAll("[*]", "");
		 * sql=sql.replaceAll("[(]", ""); sql=sql.replaceAll("[)]", "");
		 * sql=sql.replaceAll("[_]", ""); sql=sql.replaceAll("[+]", "");
		 * sql=sql.replaceAll("[|]", ""); sql=sql.replaceAll("[-]", "");
		 * sql=sql.replaceAll("[=]", ""); sql=sql.replaceAll("[ ]", "");
		 * sql=sql.replaceAll("[;]", ""); sql=sql.replaceAll("[,]", "");
		 * sql=sql.replaceAll("[.]", ""); sql=sql.replaceAll("[/]", "");
		 * sql=sql.replaceAll("[{]", ""); sql=sql.replaceAll("[}]", "");
		 * sql=sql.replaceAll("[:]", ""); sql=sql.replaceAll("[<]", "");
		 * sql=sql.replaceAll("[>]", ""); sql=sql.replaceAll("[?]", "");
		 * sql=sql.replaceAll("[`]", ""); sql=sql.replaceAll("[\\\\]", "");
		 * sql=sql.replaceAll("[\"]", "");
		 */
		return sql;
	}

	/**
	 * 判断对象是否为空.
	 *
	 * @param obj the obj
	 * @return true, if is empty
	 */
	public static boolean isEmpty(Object[] obj) {
		if (obj != null && obj.length > 0) {
			return false;
		}
		return true;
	}
	
	/**
	 * 判断字符是否为空.
	 *
	 * @param str the str
	 * @return true, if is empty
	 */
	public static boolean isEmpty(String str) {
		if (str != null && str.length() > 0) {
			return false;
		}
		return true;
	}
	
	/**
	 * Gets the ip addr by request.
	 *
	 * @param request the request
	 * @return the ip addr by request
	 */
	public static String getIpAddrByRequest(HttpServletRequest request) {  

        String ip = request.getHeader("x-forwarded-for");  

        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  

            ip = request.getHeader("Proxy-Client-IP");  

        }  

        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  

            ip = request.getHeader("WL-Proxy-Client-IP");  

        }  

        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  

            ip = request.getRemoteAddr();  

        }  

        return ip;  

    } 
	 
 	/**
 	 * 采用ResourceBundel类取得属性文件对应值，这个只能够读取，不可以更改及写新的属性.
 	 *
 	 * @param propertiesFileNameWithoutPostfix the properties file name without postfix
 	 * @param propertyName the property name
 	 * @return 根据属性名得到的属性值，如没有返回""
 	 * @paramp ropertiesFileNameWithoutPostfixproperties文件名，不带后缀
 	 * @paramp ropertyName属性名
 	 */
	public static  String getValueByPropertyName(String propertiesFileNameWithoutPostfix,String propertyName) {
        String s="";
        //如属性文件是test.properties，那此时propertiesFileNameWithoutPostfix的值就是test
        ResourceBundle bundel = ResourceBundle.getBundle(propertiesFileNameWithoutPostfix);
        s=bundel.getString(propertyName);
        return s;
    }
	
	/**
	 * Gets the BAS e64.
	 *
	 * @param s the s
	 * @return the BAS e64
	 */
	public static String getBASE64(String s){
		/*if(s==null){return null;}
		new Base64();
		return Base64.encode(s.getBytes());*/
		return "";
	}
	
	  
	/**
	 * The main method.
	 *
	 * @param args the arguments
	 * @throws UnsupportedEncodingException the unsupported encoding exception
	 */
	public static void main(String[] args) throws UnsupportedEncodingException {
		//String account64=new String(Base64.decode("aEhGZHhGNDNldDo6OjEyMzRhYmNEOjo6aEhGZHhGNDNldA=="), "UTF-8");
//		String account64 =getBASE64("admin");
//		System.out.println(account64);
//		 String类型 进行Base64编码
//		    String encoded = Base64.encode("Hello, world!");
//		    
//		    String类型 进行Base64解码
//		    String decoded = Base64.decode(encoded);
//		    
//		    指定字符编码方式
//		    String encoded = Base64.encode("Hello, world!", "UTF-8");    
//		    String decoded = Base64.decode(encoded, "UTF-8");    

	}

   //String account64=new String(Base64.decode(account), "UTF-8");
	
	
	/**
    * Checks if is mobile device.
    *
    * @param requestHeader the request header
    * @return true, if is mobile device
    * @Title:  判断请求是否来自PC或者移动端
    * @Description: TODO(这里用一句话描述这个方法的作用)
    * @Author: Administrator
    * @Since: 2015-3-12上午11:28:21
    */
	public static boolean  isMobileDevice(String requestHeader){
        /**
         * android : 所有android设备
         * mac os : iphone ipad
         * windows phone:Nokia等windows系统的手机
         */
        String[] deviceArray = new String[]{"android","mac os","windows phone","iphone","ipad"};
        if(requestHeader == null)
            return false;
        requestHeader = requestHeader.toLowerCase();
        for(int i=0;i<deviceArray.length;i++){
            if(requestHeader.indexOf(deviceArray[i])>0){
                return true;
            }
        }
        return false;
}
	

}
