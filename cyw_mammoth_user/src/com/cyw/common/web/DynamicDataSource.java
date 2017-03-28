package com.cyw.common.web;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

public class DynamicDataSource extends AbstractRoutingDataSource {
	private static final ThreadLocal<String> contextHolder = new ThreadLocal<String>();
	
	public final static String dataSourceMainKey="dataSourceMain";
	public final static String dataSourceSlaveKey="dataSourceSlave";
	
	 public static String getCurrentLookupKey() {  
	        return (String) contextHolder.get();  
	 }
	 public static void setCurrentLookupKey(String currentLookupKey) {  
	        contextHolder.set(currentLookupKey);  
	 }
	 // 清除数据源类型  
	  public static void clearCurrentLookupKey() {  
	        contextHolder.remove();
	  } 
	@Override
	protected Object determineCurrentLookupKey() {
		return getCurrentLookupKey();
	}

}
