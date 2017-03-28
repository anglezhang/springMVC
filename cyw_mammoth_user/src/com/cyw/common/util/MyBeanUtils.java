package com.cyw.common.util;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.util.Locale;

import org.springframework.util.Assert;

// TODO: Auto-generated Javadoc
/**
 * The Class MyBeanUtils.
 */
public class MyBeanUtils {
	
	/**
	 * 直接读取对象属性值,无视private/protected修饰符,不经过getter函数.
	 *
	 * @param object the object
	 * @param fieldName the field name
	 * @return the field value
	 */
	public static Object getFieldValue(final Object object,
			final String fieldName) {
		Field field = getDeclaredField(object, fieldName);

		if (field == null) {
			throw new IllegalArgumentException("Could not find field ["
					+ fieldName + "] on target [" + object + "]");
		}

		makeAccessible(field);

		Object result = null;
		try {
			result = field.get(object);
		} catch (IllegalAccessException e) {
			throw new RuntimeException("never happend exception!", e);
		}
		return result;
	}

	/**
	 * 直接设置对象属性值,无视private/protected修饰符,不经过setter函数.
	 *
	 * @param object the object
	 * @param fieldName the field name
	 * @param value the value
	 */
	public static void setFieldValue(final Object object,
			final String fieldName, final Object value) {
		Field field = getDeclaredField(object, fieldName);

		if (field == null) {
			throw new IllegalArgumentException("Could not find field ["
					+ fieldName + "] on target [" + object + "]");
		}

		makeAccessible(field);

		try {
			field.set(object, value);
		} catch (IllegalAccessException e) {
			throw new RuntimeException("never happend exception!", e);
		}
	}

	/**
	 * 循环向上转型,获取对象的DeclaredField.
	 *
	 * @param object the object
	 * @param fieldName the field name
	 * @return the declared field
	 */
	protected static Field getDeclaredField(final Object object,
			final String fieldName) {
		Assert.notNull(object);
		return getDeclaredField(object.getClass(), fieldName);
	}

	/**
	 * 循环向上转型,获取类的DeclaredField.
	 *
	 * @param clazz the clazz
	 * @param fieldName the field name
	 * @return the declared field
	 */
	@SuppressWarnings({ "rawtypes" })   
	protected static Field getDeclaredField(final Class clazz,
			final String fieldName) {
		Assert.notNull(clazz);
		Assert.hasText(fieldName);
		for (Class superClass = clazz; superClass != Object.class; superClass = superClass
				.getSuperclass()) {
			try {
				return superClass.getDeclaredField(fieldName);
			} catch (NoSuchFieldException e) {
				// Field不在当前类定义,继续向上转型
			}
		}
		return null;
	}

	/**
	 * 强制转换fileld可访问.
	 *
	 * @param field the field
	 */
	protected static void makeAccessible(final Field field) {
		if (!Modifier.isPublic(field.getModifiers())
				|| !Modifier.isPublic(field.getDeclaringClass().getModifiers())) {
			field.setAccessible(true);
		}
	}

	/**
	 * Gets the simple property.
	 *
	 * @param bean the bean
	 * @param propName the prop name
	 * @return the simple property
	 * @throws IllegalArgumentException the illegal argument exception
	 * @throws SecurityException the security exception
	 * @throws IllegalAccessException the illegal access exception
	 * @throws InvocationTargetException the invocation target exception
	 * @throws NoSuchMethodException the no such method exception
	 */
	public static Object getSimpleProperty(Object bean, String propName)
			throws IllegalArgumentException, SecurityException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {
		return bean.getClass().getMethod(getReadMethod(propName)).invoke(bean);
	}

	/**
	 * Gets the read method.
	 *
	 * @param name the name
	 * @return the read method
	 */
	private static String getReadMethod(String name) {
		return "get" + name.substring(0, 1).toUpperCase(Locale.ENGLISH)
				+ name.substring(1);
	}
}
