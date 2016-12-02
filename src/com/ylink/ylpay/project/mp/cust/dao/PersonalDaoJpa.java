package com.ylink.ylpay.project.mp.cust.dao;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.ylink.modules.orm.jpa.CustomJpaDaoImpl;
import com.ylink.ylpay.project.mp.cust.entity.Personal;

/**
 * 个人客户DAO
 * 
 * @author 张磊
 * @date 2012-10-31
 */
@Repository( "personalDao" )
public class PersonalDaoJpa extends CustomJpaDaoImpl<Personal> implements PersonalDao {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6500475045284835507L;

	@Override
	public Personal read(Class<?> clazz, Personal personal) {
		if( personal == null )
			return null;
		return  (Personal) this.getEntityManager().find(clazz,personal.getIdentity());
	}

	@SuppressWarnings("unchecked")
	@Override
	public Personal getCustomerInfo(String id) {
		// TODO Auto-generated method stub
		
		String hql = " SELECT m FROM " + entityClass.getName() 
			+ " AS m WHERE m.id = ?  ";
		
		//List<Personal> results = getJpaTemplate().find( hql				,new Object[]{id} );
		
		Query query = this.getEntityManager().createQuery(hql);
		List<Personal> results = this.addQueryParams(query,id).getResultList();

		return ( results == null || results.isEmpty() ) ? null : results.get( 0 );
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public Personal checkPayPassword(String custId, String payPassword) {
		String hql = " SELECT m FROM " + entityClass.getName() 
		+ " AS m WHERE m.id = ? AND m.payPassword = ? ";
	
		//List<Personal> results = getJpaTemplate().find( hql
		//	,new Object[]{custId,payPassword} );
		
		Query query = getEntityManager().createQuery( hql.toString() );
		this.addQueryParams(query, new Object[]{custId,payPassword});
		List<Personal> results = query.getResultList();

		return ( results == null || results.isEmpty() ) ? null : results.get( 0 );
	}

	
}
