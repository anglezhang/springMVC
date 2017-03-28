package com.cyw.common.base.dao.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.ResultTransformer;
import org.springframework.util.Assert;

import com.cyw.common.base.bean.Finder;
import com.cyw.common.base.bean.Pager;
import com.cyw.common.base.bean.Pager.OrderType;
import com.cyw.common.base.dao.BaseDao;

// TODO: Auto-generated Javadoc
/**
 * hibernate DAO基类
 * 
 * 提供hql分页查询，拷贝更新等一些常用功能。.
 *
 * @param <T>            entity class
 * @param <PK>            entity id
 */
public class BaseDaoImpl<T, PK extends Serializable> implements BaseDao<T, PK> {

	/** The entity class. */
	private Class<T> entityClass;
	
	/** The session factory. */
	protected SessionFactory sessionFactory;
	
	/** HIBERNATE 的 order 属性. */
	protected static final String ORDER_ENTRIES = "orderEntries";

	/**
	 * Instantiates a new base dao impl.
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })   
	public BaseDaoImpl() {
		this.entityClass = null;
        Class c = getClass();
        Type type = c.getGenericSuperclass();
        if (type instanceof ParameterizedType) {
            Type[] parameterizedType = ((ParameterizedType) type).getActualTypeArguments();
            this.entityClass = (Class<T>) parameterizedType[0];
        }
	}

	/**
	 * Sets the session factory.
	 *
	 * @param sessionFactory the new session factory
	 */
	@Resource
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	/**
	 * Gets the session.
	 *
	 * @return the session
	 */
	protected Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	/* (non-Javadoc)
	 * @see com.common.base.dao.BaseDao#get(java.io.Serializable)
	 */
	@SuppressWarnings("unchecked")
	public T get(PK id) {
		Assert.notNull(id, "id is required");
		return (T) getSession().get(entityClass, id);
	}
	/*
	 * (non-Javadoc)
	 * @see com.cyw.common.base.dao.BaseDao#getForUpdate(java.io.Serializable)
	 */
	@SuppressWarnings("unchecked")
	public T getForUpdate(PK id) {
		Assert.notNull(id, "id is required");
		String hql = "from " + entityClass.getName() + " as model where model.id = ?";
		return (T) getSession().createQuery(hql).setParameter(0, id).setLockMode("model", LockMode.UPGRADE).uniqueResult();
	}

	/* (non-Javadoc)
	 * @see com.common.base.dao.BaseDao#load(java.io.Serializable)
	 */
	@SuppressWarnings("unchecked")
	public T load(PK id) {
		Assert.notNull(id, "id is required");
		return (T) getSession().load(entityClass, id);
	}

	/* (non-Javadoc)
	 * @see com.common.base.dao.BaseDao#get(PK[])
	 */
	@SuppressWarnings("unchecked")
	public List<T> get(PK[] ids) {
		Assert.notEmpty(ids, "ids must not be empty");
		String hql = "from " + entityClass.getName() + " as model where model.id in(:ids)";
		return getSession().createQuery(hql).setParameterList("ids", ids).list();
	}

	/* (non-Javadoc)
	 * @see com.common.base.dao.BaseDao#get(java.lang.String, java.lang.Object)
	 */
	@SuppressWarnings("unchecked")
	public T get(String propertyName, Object value) {
		Assert.hasText(propertyName, "propertyName must not be empty");
		Assert.notNull(value, "value is required");
		String hql = "from " + entityClass.getName() + " as model where model." + propertyName + " = ?";
		return (T) getSession().createQuery(hql).setParameter(0, value).uniqueResult();
	}
	
	/* (non-Javadoc)
	 * @see com.common.base.dao.BaseDao#get(java.lang.String[], java.lang.Object[])
	 */
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
	
	/* (non-Javadoc)
	 * @see com.common.base.dao.BaseDao#getList(java.lang.String[], java.lang.Object[])
	 */
	@SuppressWarnings("unchecked")
	public List<T> getList(String[] propertyName, Object[] value) {
		StringBuffer hql =new StringBuffer();
		hql.append("from " + entityClass.getName() + " as model where 1=1 ");
		for(String property:propertyName){
		     hql.append("and model." + property + " = ? ");	
		 }
		Query query= getSession().createQuery(hql.toString());
		   for (int i = 0; i < value.length; i++) {
			   query.setParameter(i, value[i]);	
		  }
	   return query.list();
	}

	/* (non-Javadoc)
	 * @see com.common.base.dao.BaseDao#getList(java.lang.String, java.lang.Object)
	 */
	@SuppressWarnings("unchecked")
	public List<T> getList(String propertyName, Object value) {
		Assert.hasText(propertyName, "propertyName must not be empty");
		Assert.notNull(value, "value is required");
		String hql = "from " + entityClass.getName() + " as model where model." + propertyName + " = ?";
		return getSession().createQuery(hql).setParameter(0, value).list();
	}
	
	@SuppressWarnings("unchecked")
	public List<T> getList(String propertyName,Object[] value) {
		Assert.hasText(propertyName, "propertyName must not be empty");
		//Assert.notNull(value, "value is required");
		String hql = null;
		if(value==null){
			hql ="from " + entityClass.getName() + " as model where model." + propertyName + " is null";
			return getSession().createQuery(hql).list();
		}else{
			hql = "from " + entityClass.getName() + " as model where model." + propertyName + " in (:list)";
			return getSession().createQuery(hql).setParameterList("list",value).list();
		}
	}
	
	/* (non-Javadoc)
	 * @see com.common.base.dao.BaseDao#getAll()
	 */
	@SuppressWarnings("unchecked")
	public List<T> getAll() {
		String hql = "from " + entityClass.getName();
		return getSession().createQuery(hql).list();
	}

	/* (non-Javadoc)
	 * @see com.common.base.dao.BaseDao#getTotalCount()
	 */
	public Long getTotalCount() {
		String hql = "select count(*) from " + entityClass.getName();
		return (Long) getSession().createQuery(hql).uniqueResult();
	}

	/* (non-Javadoc)
	 * @see com.common.base.dao.BaseDao#isUnique(java.lang.String, java.lang.Object, java.lang.Object)
	 */
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

	/* (non-Javadoc)
	 * @see com.common.base.dao.BaseDao#isExist(java.lang.String, java.lang.Object)
	 */
	public boolean isExist(String propertyName, Object value) {
		Assert.hasText(propertyName, "propertyName must not be empty");
		Assert.notNull(value, "value is required");
		T object = get(propertyName, value);
		return (object != null);
	}

	/* (non-Javadoc)
	 * @see com.common.base.dao.BaseDao#save(java.lang.Object)
	 */
	@SuppressWarnings("unchecked")
	public PK save(T entity) {
		Assert.notNull(entity, "entity is required");
		return (PK) getSession().save(entity);
	}

	/* (non-Javadoc)
	 * @see com.common.base.dao.BaseDao#saveOrUpdate(java.lang.Object)
	 */
	public void saveOrUpdate(T entity) {
		Assert.notNull(entity, "entity is required");
		getSession().saveOrUpdate(entity);
	}

	/* (non-Javadoc)
	 * @see com.common.base.dao.BaseDao#merge(java.lang.Object)
	 */
	@SuppressWarnings("unchecked")
	public T merge(T entity) {
		Assert.notNull(entity, "entity is required");
		return (T) getSession().merge(entity);
	}

	/* (non-Javadoc)
	 * @see com.common.base.dao.BaseDao#update(java.lang.Object)
	 */
	public void update(T entity) {
		Assert.notNull(entity, "entity is required");
		getSession().update(entity);
	}

	/* (non-Javadoc)
	 * @see com.common.base.dao.BaseDao#delete(java.lang.Object)
	 */
	public void delete(T entity) {
		Assert.notNull(entity, "entity is required");
		getSession().delete(entity);
	}

	/* (non-Javadoc)
	 * @see com.common.base.dao.BaseDao#delete(java.io.Serializable)
	 */
	public void delete(PK id) {
		Assert.notNull(id, "id is required");
		T entity = load(id);
		getSession().delete(entity);
	}

	/* (non-Javadoc)
	 * @see com.common.base.dao.BaseDao#delete(PK[])
	 */
	public void delete(PK[] ids) {
		Assert.notEmpty(ids, "ids must not be empty");
		for (PK id : ids) {
			T entity = load(id);
			getSession().delete(entity);
		}
	}

	/* (non-Javadoc)
	 * @see com.common.base.dao.BaseDao#flush()
	 */
	public void flush() {
		getSession().flush();
	}

	/* (non-Javadoc)
	 * @see com.common.base.dao.BaseDao#clear()
	 */
	public void clear() {
		getSession().clear();
	}

	/* (non-Javadoc)
	 * @see com.common.base.dao.BaseDao#evict(java.lang.Object)
	 */
	public void evict(Object object) {
		Assert.notNull(object, "object is required");
		getSession().evict(object);
	}

	/* (non-Javadoc)
	 * @see com.common.base.dao.BaseDao#findByPager(com.common.base.bean.Pager)
	 */
	public Pager findByPager(Pager pager) {
		if (pager == null) {
			pager = new Pager();
		}
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(entityClass);
		return findByPager(pager, detachedCriteria);
	}

	/* (non-Javadoc)
	 * @see com.common.base.dao.BaseDao#findByPager(com.common.base.bean.Pager, org.hibernate.criterion.DetachedCriteria)
	 */
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
	 * 根据hql查询列表，查询hql语句，这里查询条件的值可以是占位符，也可以直接将条件拼接到hql语句中.
	 *
	 * @param hql 			查询hql语句
	 * @param args 		    查询条件所对应的值
	 * @return the list
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
	 * 根据hql查询列表，查询hql语句，这里查询条件的值可以是占位符，也可以直接将条件拼接到hql语句中.
	 *
	 * @param hql 			查询hql语句
	 * @param args 		    查询条件所对应的值
	 * @return the t
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
	 * 根据查询函数与参数列表创建Query对象,后续可进行更多处理,辅助函数.
	 *
	 * @param hql the hql
	 * @param values the values
	 * @return the query
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
	 * 通过Finder获得分页数据.
	 *
	 * @param finder            Finder对象
	 * @param pageNo            页码
	 * @param pageSize            每页条数
	 * @return the pager
	 */
	@SuppressWarnings({ "rawtypes" })   
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
	 * 通过Finder获得列表数据.
	 *
	 * @param finder the finder
	 * @return the list
	 */
	@SuppressWarnings({ "rawtypes" })   
	public List find(Finder finder) {
		Query query = finder.createQuery(getSession());
		List list = query.list();
		return list;
	}
	
	/**
	 * 获得Finder的记录总数.
	 *
	 * @param finder the finder
	 * @return the int
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
	 * 通过hql语句删除或者修改对象.
	 *
	 * @param finder the finder
	 * @return the int
	 */
	public int deleteOrupdateHql(Finder finder) {
		Query q = finder.createQuery(getSession());
		return q.executeUpdate();
	}
	
	/**
	 * 定义query查询返回数据的格式（用于关联查询）
	 * <功能详细描述>
	 * @param hql
	 * @return
	 * @see BaseDaoImpl.java
	 */
	public SQLQuery createTransformSqlQuery(String hql){
		SQLQuery query = this.getSession().createSQLQuery(hql);
		query.setResultTransformer(new ResultTransformer() {
			@Override
			public Object transformTuple(Object[] values, String[] columns) {
				Map<String, Object> map = new LinkedHashMap<String, Object>(1);
				int i = 0;
				for (String column : columns) {
					Object o = values[i++];
					if(o instanceof String){
						o = o.toString().trim();
					}else if(o instanceof Double){
						BigDecimal n = new BigDecimal(o.toString());
		                BigDecimal n2 = n.setScale(2, BigDecimal.ROUND_FLOOR);
						o = n2.toPlainString();
					}else if(o instanceof Integer){
						o = o.toString().trim();
					}
					map.put(column, o == null ? "" : o);
				}
				return map;
			}

			@Override
			public List transformList(List list) {
				return list;
			}
		});
		return query;
	}
}
