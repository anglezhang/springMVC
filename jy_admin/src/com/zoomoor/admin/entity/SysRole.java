package com.zoomoor.admin.entity;
// default package

import static javax.persistence.GenerationType.IDENTITY;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;

/**
 * SysRole entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "sys_role" )
public class SysRole implements java.io.Serializable {
	
	private static final long serialVersionUID = -5773212318873700001L;
	
	private Integer roleId;
	private String roleName;
	private Integer isSuper;
	private Boolean del;
	private Set<SysUser> sysUsers = new HashSet<SysUser>(0);
	private Set<SysAuth> sysAuths = new HashSet<SysAuth>(0);

	// Constructors

	/** default constructor */
	public SysRole() {
	}

	/** minimal constructor */
	public SysRole(String roleName, Integer isSuper) {
		this.roleName = roleName;
		this.isSuper = isSuper;
	}

	/** full constructor */
	public SysRole(String roleName, Integer isSuper,Boolean del,
			Set<SysUser> sysUsers, Set<SysAuth> sysAuths) {
		this.roleName = roleName;
		this.isSuper = isSuper;
		this.del=del;
		this.sysUsers = sysUsers;
		this.sysAuths = sysAuths;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "role_id", unique = true, nullable = false)
	public Integer getRoleId() {
		return this.roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	@Column(name = "role_name", nullable = false, length = 100)
	public String getRoleName() {
		return this.roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	@Column(name = "is_super", nullable = false)
	public Integer getIsSuper() {
		return this.isSuper;
	}

	public void setIsSuper(Integer isSuper) {
		this.isSuper = isSuper;
	}

	@Column(name = "del")
	public Boolean getDel() {
		return this.del;
	}

	public void setDel(Boolean del) {
		this.del = del;
	}
	@ManyToMany(fetch = FetchType.LAZY)
	@Cascade(value = {org.hibernate.annotations.CascadeType.SAVE_UPDATE})
	@JoinTable(name = "sys_user_role", joinColumns = { @JoinColumn(name = "role_id", nullable = false, updatable = false) }, inverseJoinColumns = { @JoinColumn(name = "user_id", nullable = false, updatable = false) })
	public Set<SysUser> getSysUsers() {
		return this.sysUsers;
	}

	public void setSysUsers(Set<SysUser> sysUsers) {
		this.sysUsers = sysUsers;
	}

	@ManyToMany(fetch = FetchType.LAZY)
	@Cascade(value = {org.hibernate.annotations.CascadeType.SAVE_UPDATE})
	@JoinTable(name = "sys_role_auth", joinColumns = { @JoinColumn(name = "role_id", nullable = false, updatable = false) }, inverseJoinColumns = { @JoinColumn(name = "auth_id", nullable = false, updatable = false) })
	public Set<SysAuth> getSysAuths() {
		return this.sysAuths;
	}

	public void setSysAuths(Set<SysAuth> sysAuths) {
		this.sysAuths = sysAuths;
	}

}