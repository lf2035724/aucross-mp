package com.ylink.modules.orm.jpa;

import java.util.Collection;
import java.util.List;

import org.springframework.util.CollectionUtils;

import com.google.code.lightssh.common.dao.Term;
import com.google.code.lightssh.common.dao.jpa.JpaAnnotationDao;
import com.google.code.lightssh.common.entity.Persistence;
import com.google.code.lightssh.common.model.page.ListPage;

/**
 * 扩展JpaAnnotationDao.
 * 
 * @author 潘瑞峥
 * @date 2012-10-29
 */
public class CustomJpaDaoImpl<T extends Persistence<?>> extends JpaAnnotationDao<T> implements CustomJpaDao<T> {
	private static final long serialVersionUID = 7393732721703629653L;

	/**
	 * 根据条件查询唯一对象.
	 */
	@Override
	public T findUnique( Collection<Term> termList ) {
		List<T> list = this.findList( termList,1 );
		if ( !CollectionUtils.isEmpty( list ) ) {
			return list.get( 0 );
		}
		return null;
	}

	/**
	 * 根据条件查询集合.
	 */
	@Override
	public List<T> findList( Collection<Term> termList ) {
		return findList(termList,Integer.MAX_VALUE);
	}
	
	/**
	 * 根据条件查询集合.
	 */
	protected List<T> findList( Collection<Term> termList,int size) {
		ListPage<T> param = new ListPage<T>();
		param.setSize( size );
		ListPage<T> page = super.list( param, termList );
		if ( null != page && !CollectionUtils.isEmpty( page.getList() ) ) {
			return page.getList();
		}
		return null;
	}

	@Override
	public ListPage<T> list(Collection<Term> termList) {
		ListPage<T> param = new ListPage<T>();
		param.setSize(Integer.MAX_VALUE);
		return super.list( param, termList );
	}

}