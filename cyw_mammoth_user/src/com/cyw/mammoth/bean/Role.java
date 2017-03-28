/**
 * 
 */
package com.cyw.mammoth.bean;

/**
 * @author wexl@163.com
 *
 */
public class Role implements java.io.Serializable {
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -2908241305914052027L;
	private int id;
	/**
	 * 名称
	 */
	private String name;
	/**
	 * 编码
	 */
	private String code;
	/**
	 * 说明
	 */
	private String memo;

	/** setter and getter **/
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}
}
