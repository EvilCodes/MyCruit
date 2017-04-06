package com.oucre.core.dao;

public interface BaseDao<T> {

	public Integer add(T entity);

	public boolean upd(T entity);

	public boolean del(Integer id);

	public T findById(Integer id);
}
