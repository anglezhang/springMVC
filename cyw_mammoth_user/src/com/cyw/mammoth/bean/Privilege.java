/**
 * 
 */
package com.cyw.mammoth.bean;

/**
 * 授权
 * 
 * @author wexl@163.com
 *
 */
public class Privilege implements java.io.Serializable {
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -4802452565010396845L;
	/**
	 * 授权主体,User/Role/Dept/...
	 */
	private String privilegeMaster;
	/**
	 * 授权主体标识 UserID/RoleId/DeptId/...
	 */
	private String privilegeMasterValue;
	/**
	 * 访问对象 Menu/Button/...
	 */
	private String privilegeAccess;
	/**
	 * 访问对象的标示 MenuId/ButtonId/....
	 */
	private String PrivilegeAccessValue;
	/**
	 * 授权操作
	 */
	private String privilegeOperation;

	/** setter and getter **/
	public String getPrivilegeMaster() {
		return privilegeMaster;
	}

	public void setPrivilegeMaster(String privilegeMaster) {
		this.privilegeMaster = privilegeMaster;
	}

	public String getPrivilegeMasterValue() {
		return privilegeMasterValue;
	}

	public void setPrivilegeMasterValue(String privilegeMasterValue) {
		this.privilegeMasterValue = privilegeMasterValue;
	}

	public String getPrivilegeAccess() {
		return privilegeAccess;
	}

	public void setPrivilegeAccess(String privilegeAccess) {
		this.privilegeAccess = privilegeAccess;
	}

	public String getPrivilegeAccessValue() {
		return PrivilegeAccessValue;
	}

	public void setPrivilegeAccessValue(String privilegeAccessValue) {
		PrivilegeAccessValue = privilegeAccessValue;
	}

	public String getPrivilegeOperation() {
		return privilegeOperation;
	}

	public void setPrivilegeOperation(String privilegeOperation) {
		this.privilegeOperation = privilegeOperation;
	}

}
