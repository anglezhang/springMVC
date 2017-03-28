package com.cyw.common.util;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
// TODO: Auto-generated Javadoc

/**
 * The Class ArrayUtils.
 *
 * @author Tom
 */
public class ArrayUtils {
	
	/**
	 * Convert str array to int.
	 *
	 * @param strArray the str array
	 * @return the integer[]
	 */
	public static Integer[] convertStrArrayToInt(String[] strArray){
		if(strArray!=null&&strArray.length>0){
			Integer array[]=new Integer[strArray.length];
			for(int i=0;i<strArray.length;i++){  
			    array[i]=Integer.parseInt(strArray[i]);
			}
			return array;
		}else{
			return null;
		}
	}
	/**
	 * Convert str array to String.
	 *
	 * @param strArray the str array
	 * @return the String
	 */
	public static String convertStrArrayToString(String[] strArray){
		if(strArray!=null&&strArray.length>0){
			StringBuffer sbf = new StringBuffer(strArray.length) ;
			for(int i=0;i<strArray.length;i++){  
				sbf.append(strArray[i]+",");
			}
			return sbf.substring(0, sbf.length()-1);
		}else{
			return null;
		}
	}
	/**  
	 * @Title: resultSetToList  
	 * @Description:查询结果转List
	 * @param rs
	 * @return
	 * @throws java.sql.SQLException List 
	 * @throws  
	 */  
	@SuppressWarnings({ "rawtypes", "unchecked"})
	public static List resultSetToList(ResultSet rs) throws java.sql.SQLException {   
		List list = new ArrayList();
		if (rs == null)   
            return Collections.EMPTY_LIST;   
        ResultSetMetaData md = rs.getMetaData(); //得到结果集(rs)的结构信息，比如字段数、字段名等   
        int columnCount = md.getColumnCount(); //返回此 ResultSet 对象中的列数   
        if(columnCount==0) return list;
        Map rowData = new HashMap();   
        while (rs.next()) {   
        	rowData = new HashMap(columnCount); 
         for (int i = 1; i <= columnCount; i++) {
        	 rowData.put(md.getColumnLabel(i),  rs.getString(i));
         }   
         list.add(rowData); 
        }   
        return list;   
	}
}
