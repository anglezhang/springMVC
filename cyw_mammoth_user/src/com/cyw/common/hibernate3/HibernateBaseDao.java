package com.cyw.common.hibernate3;

import static org.hibernate.EntityMode.POJO;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.LockMode;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.metadata.ClassMetadata;
import org.springframework.util.Assert;

import com.cyw.common.util.MyBeanUtils;

// TODO: Auto-generated Javadoc
/**
 * hibernate DAO基类
 * 
 * 提供hql分页查询，拷贝更新等一些常用功能。.
 *
 * @param <T>            entity class
 * @param <ID>            entity id
 */
public abstract class HibernateBaseDao<T, ID extends Serializable> extends
		HibernateSimpleDao {
	
	/**
	 * Gets the.
	 *
	 * @param id the id
	 * @return 持久化对象
	 * @see Session.get(Class,Serializable)
	 */
	protected T get(ID id) {
		return get(id, false);
	}

	/**
	 * Gets the.
	 *
	 * @param id            对象ID
	 * @param lock            是否锁定，使用LockMode.UPGRADE
	 * @return 持久化对象
	 * @see Session.get(Class,Serializable,LockMode)
	 */
	@SuppressWarnings("unchecked")
	protected T get(ID id, boolean lock) {
		T entity;
		if (lock) {
			entity = (T) getSession().get(getEntityClass(), id,
					LockMode.UPGRADE);
		} else {
			entity = (T) getSession().get(getEntityClass(), id);
		}
		return entity;
	}

	/**
	 * 按属性查找对象列表.
	 *
	 * @param property the property
	 * @param value the value
	 * @return the list
	 */
	@SuppressWarnings("unchecked")
	protected List<T> findByProperty(String property, Object value) {
		Assert.hasText(property);
		return createCriteria(Restrictions.eq(property, value)).list();
	}

	/**
	 * 按属性查找唯一对象.
	 *
	 * @param property the property
	 * @param value the value
	 * @return the t
	 */
	@SuppressWarnings("unchecked")
	protected T findUniqueByProperty(String property, Object value) {
		Assert.hasText(property);
		Assert.notNull(value);
		return (T) createCriteria(Restrictions.eq(property, value))
				.uniqueResult();
	}

	/**
	 * 按属性统计记录数.
	 *
	 * @param property the property
	 * @param value the value
	 * @return the int
	 */
	protected int countByProperty(String property, Object value) {
		Assert.hasText(property);
		Assert.notNull(value);
		return ((Number) (createCriteria(Restrictions.eq(property, value))
				.setProjection(Projections.rowCount()).uniqueResult()))
				.intValue();
	}

	/**
	 * 按Criterion查询列表数据.
	 *
	 * @param criterion            数量可变的Criterion.
	 * @return the list
	 */
	@SuppressWarnings({ "rawtypes" })   
	protected List findByCriteria(Criterion... criterion) {
		return createCriteria(criterion).list();
	}

	/**
	 * 通过Updater更新对象.
	 *
	 * @param updater the updater
	 * @return the t
	 */
	@SuppressWarnings("unchecked")
	public T updateByUpdater(Updater<T> updater) {
		ClassMetadata cm = sessionFactory.getClassMetadata(getEntityClass());
		T bean = updater.getBean();
		T po = (T) getSession().get(getEntityClass(),
				cm.getIdentifier(bean, POJO));
		updaterCopyToPersistentObject(updater, po, cm);
		return po;
	}

	/**
	 * 将更新对象拷贝至实体对象，并处理many-to-one的更新。.
	 *
	 * @param updater the updater
	 * @param po the po
	 * @param cm the cm
	 */
	private void updaterCopyToPersistentObject(Updater<T> updater, T po,
			ClassMetadata cm) {
		String[] propNames = cm.getPropertyNames();
		String identifierName = cm.getIdentifierPropertyName();
		T bean = updater.getBean();
		Object value;
		for (String propName : propNames) {
			if (propName.equals(identifierName)) {
				continue;
			}
			try {
				value = MyBeanUtils.getSimpleProperty(bean, propName);
				if (!updater.isUpdate(propName, value)) {
					continue;
				}
				cm.setPropertyValue(po, propName, value, POJO);
			} catch (Exception e) {
				throw new RuntimeException(
						"copy property to persistent object failed: '"
								+ propName + "'", e);
			}
		}
	}

	/**
	 * 根据Criterion条件创建Criteria,后续可进行更多处理,辅助函数.
	 *
	 * @param criterions the criterions
	 * @return the criteria
	 */
	protected Criteria createCriteria(Criterion... criterions) {
		Criteria criteria = getSession().createCriteria(getEntityClass());
		for (Criterion c : criterions) {
			criteria.add(c);
		}
		return criteria;
	}

	/**
	 * 获得Dao对于的实体类.
	 *
	 * @return the entity class
	 */
	abstract protected Class<T> getEntityClass();
}
