package com.cyw.common.hibernate3;

import java.util.HashSet;
import java.util.Set;

// TODO: Auto-generated Javadoc
/**
 * 更新对象类
 * 
 * 提供三种更新模式：MAX, MIN, MIDDLE
 * <ul>
 * <li>MIDDLE：默认模式。除了null外，都更新。exclude和include例外。</li>
 * <li>MAX：最大化更新模式。所有字段都更新（包括null）。exclude例外。</li>
 * <li>MIN：最小化更新模式。所有字段都不更新。include例外。</li>
 * </ul>.
 *
 * @param <T> the generic type
 */
public class Updater<T> {
	
	/**
	 * 构造器.
	 *
	 * @param bean            待更新对象
	 */
	public Updater(T bean) {
		this.bean = bean;
	}

	/**
	 * 构造器.
	 *
	 * @param bean            待更新对象
	 * @param mode            更新模式
	 */
	public Updater(T bean, UpdateMode mode) {
		this.bean = bean;
		this.mode = mode;
	}

	/**
	 * 设置更新模式.
	 *
	 * @param mode the mode
	 * @return the updater
	 */
	public Updater<T> setUpdateMode(UpdateMode mode) {
		this.mode = mode;
		return this;
	}

	/**
	 * 必须更新的字段.
	 *
	 * @param property the property
	 * @return the updater
	 */
	public Updater<T> include(String property) {
		includeProperties.add(property);
		return this;
	}

	/**
	 * 不更新的字段.
	 *
	 * @param property the property
	 * @return the updater
	 */
	public Updater<T> exclude(String property) {
		excludeProperties.add(property);
		return this;
	}

	/**
	 * 某一字段是否更新.
	 *
	 * @param name            字段名
	 * @param value            字段值。用于检查是否为NULL
	 * @return true, if is update
	 */
	public boolean isUpdate(String name, Object value) {
		if (this.mode == UpdateMode.MAX) {
			return !excludeProperties.contains(name);
		} else if (this.mode == UpdateMode.MIN) {
			return includeProperties.contains(name);
		} else if (this.mode == UpdateMode.MIDDLE) {
			if (value != null) {
				return !excludeProperties.contains(name);
			} else {
				return includeProperties.contains(name);
			}
		} else {
			// never reach
		}
		return true;
	}

	/** The bean. */
	private T bean;

	/** The include properties. */
	private Set<String> includeProperties = new HashSet<String>();

	/** The exclude properties. */
	private Set<String> excludeProperties = new HashSet<String>();

	/** The mode. */
	private UpdateMode mode = UpdateMode.MIDDLE;

	// private static final Logger log = LoggerFactory.getLogger(Updater.class);

	/**
	 * The Enum UpdateMode.
	 */
	public static enum UpdateMode {
		
		/** The max. */
		MAX, 
 /** The min. */
 MIN, 
 /** The middle. */
 MIDDLE
	}

	/**
	 * Gets the bean.
	 *
	 * @return the bean
	 */
	public T getBean() {
		return bean;
	}

	/**
	 * Gets the exclude properties.
	 *
	 * @return the exclude properties
	 */
	public Set<String> getExcludeProperties() {
		return excludeProperties;
	}

	/**
	 * Gets the include properties.
	 *
	 * @return the include properties
	 */
	public Set<String> getIncludeProperties() {
		return includeProperties;
	}
}
