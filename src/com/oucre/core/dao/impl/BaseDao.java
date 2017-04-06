package com.oucre.core.dao.impl;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * 对dao的数据连接base类
 * 
 * @date 2015年5月25日 下午2:03:53
 */
@SuppressWarnings("unchecked")
public class BaseDao<T> {

	/**
	 * 初始化Log4j的一个实例
	 */
	private static final Logger logger = Logger.getLogger(BaseDao.class);

	protected String hql = "";

	protected String sql = "";

	@Autowired
	@Qualifier("jdbcTemplate")
	protected JdbcTemplate jdbcTemplate;

	@Autowired
	@Qualifier("sessionFactory")
	protected SessionFactory sessionFactory;

	public Session getSession() {
		// 事务必须是开启的(Required)，否则获取不到
		return sessionFactory.getCurrentSession();
	}

	/**
	 * 保存实体对象
	 * 
	 * @param entity
	 * @return
	 */
	public Serializable saveEntity(T entity) {
		try {
			Serializable id = getSession().save(entity);
			getSession().flush();
			return id;
		} catch (RuntimeException e) {
			logger.error("保存实体异常", e);
			throw e;
		}
	}

	/**
	 * 更新 实体对象
	 * 
	 * @param entity
	 */
	public void updateEntity(T entity) {
		try {
			getSession().update(entity);
			getSession().flush();
		} catch (RuntimeException e) {
			logger.error("更新实体异常", e);
			throw e;
		}
	}

	/**
	 * 
	 * @param entity
	 */
	public void deleteEntity(T entity) {
		try {
			getSession().delete(entity);
			getSession().flush();
		} catch (RuntimeException e) {
			logger.error("删除实体异常", e);
			throw e;
		}
	}

	/**
	 * Hql查询
	 * 
	 * @param hql
	 * @return
	 */
	public List<T> findHql(String hql) {
		Query q = getSession().createQuery(hql);
		return q.list();
	}

	public <T> List<T> findHql(String hql, Object... param) {
		Query q = getSession().createQuery(hql);
		if (param != null && param.length > 0) {
			for (int i = 0; i < param.length; i++) {
				q.setParameter(i, param[i]);
			}
		}
		return q.list();
	}

	/**
	 * 使用sql 查询
	 * 
	 * @param sql
	 * @return
	 */
	public List<T> findSql(String sql) {
		Query q = getSession().createSQLQuery(sql);
		return q.list();
	}

	/**
	 * 根据 ID查询 对应实体
	 * 
	 * @param entityClass
	 * @param id
	 * @return
	 */
	public T get(Class<T> entityClass, Integer id) {
		T t = (T) getSession().get(entityClass, id);
		getSession().clear();
		return t;
	}

	/**
	 * 根据传入的实体添加或更新对象
	 * 
	 * @param <T>
	 * 
	 * @param entity
	 */

	public void saveOrUpdate(T entity) {
		try {
			getSession().saveOrUpdate(entity);
			getSession().flush();
		} catch (RuntimeException e) {
			logger.error("saveOrUpdate异常", e);
			throw e;
		}
	}

	/**
	 * 保存多个对象
	 * 
	 * @param entitys
	 */
	public void saveAll(List<T> entitys) {
		for (int i = 0; i < entitys.size(); i++) {
			getSession().save(entitys.get(i));
			if (i % 20 == 0) {
				// 20个对象后才清理缓存，写入数据库
				getSession().flush();
				getSession().clear();
			}
		}
		// 最后清理一下----防止大于20小于40的不保存
		getSession().flush();
		getSession().clear();
	}

	/**
	 * 多个对象删除
	 * 
	 * @param entitys
	 */
	public void deleteAll(List<T> entitys) {
		for (Object entity : entitys) {
			getSession().delete(entity);
			getSession().flush();
		}
	}

	public Integer findByHqlForPageCount(final String sql,
			final Integer pageSize) {
		Query query = getSession().createSQLQuery(sql);
		System.out.println(query.uniqueResult()
				+ "-------------------------------------");
		BigInteger totalBig = (BigInteger) query.uniqueResult();
		Integer total = totalBig.intValue();
		Integer pageNumber = 0;
		if (total % pageSize == 0) {
			pageNumber = total / pageSize;
		} else {
			pageNumber = total / pageSize + 1;
		}
		return pageNumber;
	}

	public List<?> findSqlByPager(final String sql, final Integer FirstResult,
			final Integer MaxResults) {
		Query query = getSession().createSQLQuery(sql);
		query.setFirstResult(FirstResult);
		query.setMaxResults(MaxResults);
		return query.list();
	}

	public List<T> findHqlByPager(final String hql, final Integer FirstResult,
			final Integer MaxResults) {
		Query query = getSession().createQuery(hql);
		query.setFirstResult(FirstResult);
		query.setMaxResults(MaxResults);
		return query.list();
	}

	public Integer findByHqlForPageCountEasyUi(final String queryString) {
		Query query = getSession().createSQLQuery(queryString);
		Object o = query.uniqueResult();
		return Integer.parseInt(o.toString());
	}
}
