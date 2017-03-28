package com.cyw.common.web;

import java.util.Locale;
import java.util.ResourceBundle;
// TODO: Auto-generated Javadoc

/**
 * The Class ResourceBundleReader.
 *
 * @ClassName: ResourceBundleReader
 * @Title:读取资源文件 
 * @Description:TODO(这里用一句话描述这个类的作用) 
 * @Author:wangxiaojun 
 * @Since:2014-11-27下午2:24:38 
 * @Version:1.0 
 */
public class ResourceBundleReader {
	
	/** The Constant initLock. */
	public final static Object initLock = new Object(); 

	/** The Constant PROPERTIES_FILE_NAME. */
	private final static String PROPERTIES_FILE_NAME = "jdbc"; 

	/** The bundle. */
	private static ResourceBundle bundle = null; 

	static { 
	try { 
	if (bundle == null) { 
	synchronized (initLock) { 
	if (bundle == null) 
	bundle = ResourceBundle.getBundle(PROPERTIES_FILE_NAME,Locale.CHINA); 
	} 
	} 
	} catch (Exception e) { 
	System.out.println("读取资源文件property_zh.properties失败!"); 
	} 
	} 
	
	/**
	 * Gets the bundle.
	 *
	 * @return the bundle
	 */
	public static ResourceBundle getBundle() { 
	return bundle; 
	} 
	
	/**
	 * Sets the bundle.
	 *
	 * @param bundle the new bundle
	 */
	public static void setBundle(ResourceBundle bundle) { 
	bundle = bundle; 
	} 
}
