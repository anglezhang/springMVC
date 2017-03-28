package com.zoomoor.common.base.dao.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.util.Assert;

import com.zoomoor.common.base.bean.Finder;
import com.zoomoor.common.base.bean.Pager;
import com.zoomoor.common.base.bean.Pager.OrderType;
import com.zoomoor.common.base.dao.BaseDao;

/**
 * hibernate DAO基类
 * 
 * 提供hql分页查询，拷贝更新等一些常用功能。
 * @param <T>
 *            entity class
 * @param <PK>
 *            entity id
 */
public class BaseDaoImpl<T, PK extends Serializable> implements BaseDao<T, PK> {

	private Class<T> entityClass;
	protected SessionFactory sessionFactory;
	
	/**
	 * HIBERNATE 的 order 属性
	 */
	protected static final String ORDER_ENTRIES = "orderEntries";

	@SuppressWarnings("unchecked")
	public BaseDaoImpl() {
		this.entityClass = null;
        Class c = getClass();
        Type type = c.getGenericSuperclass();
        if (type instanceof ParameterizedType) {
            Type[] parameterizedType = ((ParameterizedType) type).getActualTypeArguments();
            this.entityClass = (Class<T>) parameterizedType[0];
        }
	}

	@Resource
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	protected Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	@SuppressWarnings("unchecked")
	public T get(PK id) {
		Assert.notNull(id, "id is required");
		return (T) getSession().get(entityClass, id);
	}

	@SuppressWarnings("unchecked")
	public T load(PK id) {
		Assert.notNull(id, "id is required");
		return (T) getSession().load(entityClass, id);
	}

	@SuppressWarnings("unchecked")
	public List<T> get(PK[] ids) {
		Assert.notEmpty(ids, "ids must not be empty");
		String hql = "from " + entityClass.getName() + " as model where model.id in(:ids)";
		return getSession().createQuery(hql).setParameterList("ids", ids).list();
	}

	@SuppressWarnings("unchecked")
	public T get(String propertyName, Object value) {
		Assert.hasText(propertyName, "propertyName must not be empty");
		Assert.notNull(value, "value is required");
		String hql = "from " + entityClass.getName() + " as model where model." + propertyName + " = ?";
		return (T) getSession().createQuery(hql).setParameter(0, value).uniqueResult();
	}
	@SuppressWarnings("unchecked")
	public T get(String[] propertyName, Object[] value){
		StringBuffer hql =new StringBuffer();
		hql.append("from " + entityClass.getName() + " as model where 1=1 ");
	    for(String property:propertyName){
	     hql.append("and model." + property + " = ? ");	
	    }
	   Query query= getSession().createQuery(hql.toString());
		
	   for (int i = 0; i < value.length; i++) {
		   query.setParameter(i, value[i]);	
	  }
		return (T) query.uniqueResult();
	}
	@SuppressWarnings("unchecked")
	public List<T> getList(String propertyName, Object value) {
		Assert.hasText(propertyName, "propertyName must not be empty");
		Assert.notNull(value, "value is required");
		String hql = "from " + entityClass.getName() + " as model where model." + propertyName + " = ?";
		return getSession().createQuery(hql).setParameter(0, value).list();
	}

	@SuppressWarnings("unchecked")
	public List<T> getList(String[] propertyName,Object[] objects) {
		StringBuffer hql =new StringBuffer();
		hql.append("from " + entityClass.getName() + " as model where 1=1 ");
	    for(String property:propertyName){
	     hql.append("and model." + property + " = ? ");	
	    }
	   Query query= getSession().createQuery(hql.toString());
		
	   for (int i = 0; i < objects.length; i++) {
		   query.setParameter(i, objects[i]);	
	  }
		return (List<T>)query.list();
	}
	@SuppressWarnings("unchecked")
	public List<T> getAll() {
		String hql = "from " + entityClass.getName();
		return getSession().createQuery(hql).list();
	}

	public Long getTotalCount() {
		String hql = "select count(*) from " + entityClass.getName();
		return (Long) getSession().createQuery(hql).uniqueResult();
	}

	public boolean isUnique(String propertyName, Object oldValue, Object newValue) {
		Assert.hasText(propertyName, "propertyName must not be empty");
		Assert.notNull(newValue, "newValue is required");
		if (newValue == oldValue || newValue.equals(oldValue)) {
			return true;
		}
		if (newValue instanceof String) {
			if (oldValue != null && StringUtils.equalsIgnoreCase((String) oldValue, (String) newValue)) {
				return true;
			}
		}
		T object = get(propertyName, newValue);
		return (object == null);
	}

	public boolean isExist(String propertyName, Object value) {
		Assert.hasText(propertyName, "propertyName must not be empty");
		Assert.notNull(value, "value is required");
		T object = get(propertyName, value);
		return (object != null);
	}

	@SuppressWarnings("unchecked")
	public PK save(T entity) {
		Assert.notNull(entity, "entity is required");
		return (PK) getSession().save(entity);
	}

	@SuppressWarnings("unchecked")
	public void saveOrUpdate(T entity) {
		Assert.notNull(entity, "entity is required");
		getSession().saveOrUpdate(entity);
	}

	@SuppressWarnings("unchecked")
	public T merge(T entity) {
		Assert.notNull(entity, "entity is required");
		return (T) getSession().merge(entity);
	}

	public void update(T entity) {
		Assert.notNull(entity, "entity is required");
		getSession().update(entity);
	}

	public void delete(T entity) {
		Assert.notNull(entity, "entity is required");
		getSession().delete(entity);
	}

	public void delete(PK id) {
		Assert.notNull(id, "id is required");
		T entity = load(id);
		getSession().delete(entity);
	}

	public void delete(PK[] ids) {
		Assert.notEmpty(ids, "ids must not be empty");
		for (PK id : ids) {
			T entity = load(id);
			getSession().delete(entity);
		}
	}

	public void flush() {
		getSession().flush();
	}

	public void clear() {
		getSession().clear();
	}

	public void evict(Object object) {
		Assert.notNull(object, "object is required");
		getSession().evict(object);
	}

	public Pager findByPager(Pager pager) {
		if (pager == null) {
			pager = new Pager();
		}
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(entityClass);
		return findByPager(pager, detachedCriteria);
	}

	public Pager findByPager(Pager pager, DetachedCriteria detachedCriteria) {
		if (pager == null) {
			pager = new Pager();
		}
		Integer pageNumber = pager.getPageNum();
		Integer pageSize = pager.getNumPerPage();
		String property = pager.getProperty();
		String keyword = pager.getKeyword();
		String orderBy = pager.getOrderBy();
		OrderType orderType = pager.getOrderType();

		Criteria criteria = detachedCriteria.getExecutableCriteria(getSession());
		if (StringUtils.isNotEmpty(property) && StringUtils.isNotEmpty(keyword)) {
			String propertyString = "";
			if (property.contains(".")) {
				String propertyPrefix = StringUtils.substringBefore(property, ".");
				String propertySuffix = StringUtils.substringAfter(property, ".");
				criteria.createAlias(propertyPrefix, "model");
				propertyString = "model." + propertySuffix;
			} else {
				propertyString = property;
			}
			criteria.add(Restrictions.like(propertyString, "%" + keyword + "%"));
		}

		Integer totalCount = (Integer) criteria.setProjection(Projections.rowCount()).uniqueResult();

		criteria.setProjection(null);
		criteria.setResultTransformer(CriteriaSpecification.ROOT_ENTITY);
		criteria.setFirstResult((pageNumber - 1) * pageSize);
		criteria.setMaxResults(pageSize);
		if (StringUtils.isNotEmpty(orderBy) && orderType != null) {
			if (orderType == OrderType.asc) {
				criteria.addOrder(Order.asc(orderBy));
			} else {
				criteria.addOrder(Order.desc(orderBy));
			}
		}
		pager.setTotalCount(totalCount.intValue());
		pager.setList(criteria.list());
		return pager;
	}

	/**
	 * 根据hql查询列表，查询hql语句，这里查询条件的值可以是占位符，也可以直接将条件拼接到hql语句中
	 * @param hql
	 * 			查询hql语句
	 * @param args
	 * 		    查询条件所对应的值
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<T> createQueryList(String hql,String ...args){
		Query query = getSession().createQuery(hql);
		Assert.notNull(hql,  "hql is required");
		Assert.notNull(args,  "args is required");
		for(int i=0;i<args.length;i++){
			query.setParameter(i, args[i]);
		}
		return query.list();
	}

	/**
	 * 根据hql查询列表，查询hql语句，这里查询条件的值可以是占位符，也可以直接将条件拼接到hql语句中
	 * @param hql
	 * 			查询hql语句
	 * @param args
	 * 		    查询条件所对应的值
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public T createQueryObj(String hql,String ...args){
		Query query = getSession().createQuery(hql);
		Assert.notNull(hql,  "hql is required");
		Assert.notNull(args,  "args is required");
		for(int i=0;i<args.length;i++){
			query.setParameter(i, args[i]);
		}
		return (T)query.uniqueResult();
	}

	/**
	 *
	 * 根据查询函数与参数列表创建Query对象,后续可进行更多处理,辅助函数.
	 *
	 * @param hql
	 * @param values
	 * @return
	 */
	public Query createQuery(String hql, Object... values) {
		Assert.hasText(hql, "values is required");
		Assert.notNull(values, "values is required");
		Query queryObject = getSession().createQuery(hql);
		if (values != null) {
			for (int i = 0; i < values.length; i++) {
				queryObject.setParameter(i, values[i]);
			}
		}
		return queryObject;
	}

	/**
	 * 通过Finder获得分页数据
	 *
	 * @param finder
	 *            Finder对象
	 * @param pageNo
	 *            页码
	 * @param pageSize
	 *            每页条数
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Pager find(Finder finder, int pageNo, int pageSize){
		int totalCount = countQueryResult(finder);
		Pager p = new Pager(pageNo, pageSize, totalCount);
		if (totalCount < 1) {
			p.setList(new ArrayList());
			return p;
		}
		Query query = getSession().createQuery(finder.getOrigHql());
		finder.setParamsToQuery(query);
		query.setFirstResult(p.getFirstResult());
		query.setMaxResults(p.getNumPerPage());
		if (finder.isCacheable()) {
			query.setCacheable(true);
		}
		List list = query.list();
		p.setList(list);
		return p;
	}

	/**
	 * 通过Finder获得列表数据
	 *
	 * @param finder
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List find(Finder finder) {
		Query query = finder.createQuery(getSession());
		List list = query.list();
		return list;
	}
	/**
	 * 获得Finder的记录总数
	 *
	 * @param finder
	 * @return
	 */
	public int countQueryResult(Finder finder) {
		Query query = getSession().createQuery(finder.getRowCountHql());
		finder.setParamsToQuery(query);
		if (finder.isCacheable()) {
			query.setCacheable(true);
		}
		return ((Number) query.iterate().next()).intValue();
	}

	/**
	 * 通过hql语句删除或者修改对象
	 *
	 * @param finder
	 * @return
	 */
	public int deleteOrupdateHql(Finder finder) {
		Query q = finder.createQuery(getSession());
		return q.executeUpdate();
	}
}
