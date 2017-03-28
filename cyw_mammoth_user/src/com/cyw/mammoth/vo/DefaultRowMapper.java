package com.cyw.mammoth.vo;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.commons.beanutils.PropertyUtils;
import org.hibernate.cfg.ImprovedNamingStrategy;
import org.springframework.jdbc.core.RowMapper;

public class DefaultRowMapper<T> implements RowMapper<T> {
	/** 转换的目标对象 */
	private Class<?> clazz;
	/** 名称处理器 */
	private ImprovedNamingStrategy nameHandler;

	public DefaultRowMapper(Class<?> clazz) {
		this.clazz = clazz;
		this.nameHandler = new ImprovedNamingStrategy();
	}

	@SuppressWarnings("unchecked")
	@Override
	public T mapRow(ResultSet resultSet, int i) throws SQLException {
		T entity = null;
		try {
			entity = (T)Class.forName(this.clazz.getName()).newInstance();// this.clazz.getClass().newInstance();
			PropertyDescriptor[] pds = PropertyUtils.getPropertyDescriptors(entity);
			for (PropertyDescriptor pd : pds) {
				if(pd.getName().toLowerCase().equals("class")) continue;
				String column = nameHandler.propertyToColumnName(pd.getName());
				Method writeMethod = pd.getWriteMethod();
				if (!Modifier.isPublic(writeMethod.getDeclaringClass().getModifiers())) {
					writeMethod.setAccessible(true);
				}
				writeMethod.invoke(entity, resultSet.getObject(column));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return entity;
	}
}
