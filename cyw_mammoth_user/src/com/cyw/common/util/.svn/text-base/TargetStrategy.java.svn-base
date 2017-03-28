package com.cyw.common.util;


import org.apache.commons.lang.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;

// TODO: Auto-generated Javadoc
/**
 * The Class TargetStrategy.
 */
public class TargetStrategy implements ExclusionStrategy {
	
	/** The log. */
	private static Logger log = LoggerFactory.getLogger(TargetStrategy.class);
	
	/** The target. */
	private Class<?> target;
	
	/** The fields. */
	private String[] fields;
	
	/** The clazz. */
	private Class<?>[] clazz;
	
	/** The reverse. */
	private boolean reverse;

	/**
	 * Instantiates a new target strategy.
	 *
	 * @param target the target
	 */
	public TargetStrategy(Class<?> target) {
		super();
		this.target = target;
	}

	/* (non-Javadoc)
	 * @see com.google.gson.ExclusionStrategy#shouldSkipClass(java.lang.Class)
	 */
	@Override
	public boolean shouldSkipClass(Class<?> class1) {
		return false;
	}

	/* (non-Javadoc)
	 * @see com.google.gson.ExclusionStrategy#shouldSkipField(com.google.gson.FieldAttributes)
	 */
	@Override
	public boolean shouldSkipField(FieldAttributes fieldattributes) {
		Class<?> owner = fieldattributes.getDeclaringClass();
		Class<?> c = fieldattributes.getDeclaredClass();
		String f = fieldattributes.getName();
		boolean isSkip = false;
		
		if (owner == target) {
			if (ArrayUtils.contains(fields, f)) {
				log.debug("fitler field:{} for class:{}", f, owner);
				isSkip = true;
			}
			if (ArrayUtils.contains(clazz, c)) {
				log.debug("fitler class:{} for class:{}", c, owner);
				isSkip = true;
			}
			if (reverse) {
				isSkip = !isSkip;
			}
		}

		return isSkip;
	}

	/**
	 * Sets the fields.
	 *
	 * @param fields the new fields
	 */
	public void setFields(String[] fields) {
		this.fields = fields;
	}

	/**
	 * Sets the clazz.
	 *
	 * @param clazz the new clazz
	 */
	public void setClazz(Class<?>[] clazz) {
		this.clazz = clazz;
	}

	/**
	 * Sets the reverse.
	 *
	 * @param reverse the new reverse
	 */
	public void setReverse(boolean reverse) {
		this.reverse = reverse;
	}
}

