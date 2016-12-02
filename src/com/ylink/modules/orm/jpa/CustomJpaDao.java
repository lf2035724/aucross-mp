package com.ylink.modules.orm.jpa;

import java.util.Collection;
import java.util.List;

import com.google.code.lightssh.common.dao.Dao;
import com.google.code.lightssh.common.dao.Term;
import com.google.code.lightssh.common.entity.Persistence;
import com.google.code.lightssh.common.model.page.ListPage;

/**
 * 扩展JpaAnnotationDao.
 * 
 * @author 潘瑞峥
 * @date 2012-10-29
 */
public interface CustomJpaDao<T extends Persistence<?>> extends Dao<T> {

	/**
	 * 根据条件查询唯一对象.
	 */
	public T findUnique( Collection<Term> termList );

	/**
	 * 根据条件查询集合.
	 */
	public List<T> findList( Collection<Term> termList );
	/**
	 * listpage
	 */
	public ListPage<T> list( Collection<Term> termList );
}