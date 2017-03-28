package com.zoomoor.core.security;

import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * 自定义Authentication对象
 * 
 */
public class SysShiroUser implements Serializable {
	private static final long serialVersionUID = 1L;
	public Integer id;
	public String username;

	public SysShiroUser(Integer id, String username) {
		this.id = id;
		this.username = username;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String toString() {
		return username;
	}

	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this,
				new String[] { username });
	}

	public boolean equals(Object obj) {
		return EqualsBuilder.reflectionEquals(this, obj,
				new String[] { username });
	}
}
