/**
 * 
 */
package com.cyw.mammoth.bean;

/**
 * 权限
 * 
 * @author wex@163.com
 *
 */
public class Permission implements java.io.Serializable {
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 3079572008130896202L;
	/**
	 * 权限ID
	 */
	private int id;
	/**
	 * 权限表达式 [marking:fguest]:[update]:[*],资源标识符：操作：对象实例ID
	 */
	private String expression;
}
