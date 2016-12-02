/**
 * 版权所有(C) 2012 深圳市雁联计算系统有限公司
 */

package com.ylink.ylpay.project.mp.user.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import com.google.code.lightssh.common.model.page.ListPage;
import com.ylink.modules.orm.jpa.CustomJpaDaoImpl;
import com.ylink.ylpay.project.mp.user.entity.User;

/**
 * @author YangXiaojin
 * @date 2012-10-26
 * @description：UserDao实现类
 */
@Repository( "userDao" )
public class UserDaoJpa extends CustomJpaDaoImpl<User> implements UserDao {

	private static final long serialVersionUID = 7708788738399155834L;

	/**
	 * @description 通过邮箱地址查询门户登录帐户
	 * @param email
	 * @return User
	 * @author YangXiaojin
	 * @date 2012-10-26
	 */
	@SuppressWarnings( "unchecked" )
	public User getByLoginName( String loginName ) {
		if ( StringUtils.isEmpty( loginName ) )
			return null;

		String hql = " SELECT m FROM " + entityClass.getName() + " AS m WHERE m.loginName = ? ";
		// List<User> results = getJpaTemplate().find( hql, loginName );

		Query query = this.getEntityManager().createQuery( hql );
		this.addQueryParams( query, loginName );
		List<User> results = query.getResultList();

		return ( results == null || results.isEmpty() ) ? null : results.get( 0 );
	}

	/**
	 * @description 通过邮箱地址查询门户登录帐户
	 * @param email
	 * @return User
	 * @author YangXiaojin
	 * @date 2012-10-26
	 */
	@SuppressWarnings( "unchecked" )
	public User getByEmail( String email ) {
		if ( StringUtils.isEmpty( email ) )
			return null;

		String hql = " SELECT m FROM " + entityClass.getName() + " AS m WHERE m.email = ? ";
		// List<User> results = getJpaTemplate().find( hql, email );

		List<User> results = getEntityManager().createQuery( hql ).setParameter( 1, email ).getResultList();

		return ( results == null || results.isEmpty() ) ? null : results.get( 0 );
	}

	/**
	 * @description 通过邮箱地址查询门户登录帐户
	 * @param email
	 * @return User
	 * @author YangXiaojin
	 * @date 2012-10-26
	 */
	@SuppressWarnings( "unchecked" )
	public User getByMobile( String mobile ) {
		if ( StringUtils.isEmpty( mobile ) )
			return null;

		String hql = " SELECT m FROM " + entityClass.getName() + " AS m WHERE m.mobile = ? ";
		// List<User> results = getJpaTemplate().find( hql, mobile );

		List<User> results = getEntityManager().createQuery( hql ).setParameter( 1, mobile ).getResultList();

		return ( results == null || results.isEmpty() ) ? null : results.get( 0 );
	}
	
	public ListPage<User> list(ListPage<User> page,User user){
		StringBuffer hql = new StringBuffer(" FROM " + this.entityClass.getName() + " AS m WHERE 1=1 ");
		List<Object> params = new ArrayList<Object>();
		if( user != null ){
			if( !StringUtils.isEmpty(user.getMobile())){
				hql.append(" AND m.mobile like ? ");
				params.add("%"+user.getMobile()+"%");
			}
			if( !StringUtils.isEmpty(user.getEmail())){
				hql.append(" AND m.email like ? ");
				params.add("%"+user.getEmail()+"%");
			}
			if( !StringUtils.isEmpty(user.getCustomerId())){
				hql.append(" AND m.customerId = ? ");
				params.add(user.getCustomerId());
			}
			
			if( !StringUtils.isEmpty(user.get_custName())){
				hql.append(" AND m.customerId in ( SELECT n.id FROM Customer AS n WHERE n.name like ? ) ");
				params.add("%"+user.get_custName()+"%");
			}
		}
		
		return this.query(page,hql.toString(),params.toArray());
	}

	@Override
	public int activateOrFreezeUser(Long userId, boolean enabled) {
		String hql = " UPDATE User AS m SET m.enabled = '" +(enabled?1:0)
				+ "' WHERE m.id = "+userId;
			Query query = getEntityManager().createQuery(hql);
		return query.executeUpdate();

		
	}
	
	@Override
	public int updateUserStatus(Long userId, String status) {
		String hql = " UPDATE User AS m SET m.status = '" +status
				+ "' WHERE m.id = "+userId;
			Query query = getEntityManager().createQuery(hql);
		return query.executeUpdate();

		
	}

}