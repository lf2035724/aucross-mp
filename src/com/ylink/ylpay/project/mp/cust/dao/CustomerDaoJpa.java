/**
 * 版权所有(C) 2012 深圳市雁联计算系统有限公司
 */

package com.ylink.ylpay.project.mp.cust.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import com.google.code.lightssh.common.dao.jpa.JpaAnnotationDao;
import com.google.code.lightssh.common.model.page.ListPage;
import com.ylink.ylpay.common.project.mp.constant.AuthenticationStatus;
import com.ylink.ylpay.project.mp.cust.entity.Customer;
import com.ylink.ylpay.project.mp.cust.entity.Enterprise;
import com.ylink.ylpay.project.mp.cust.entity.Personal;

/**
 * @author YangXiaojin
 * @date 2012-11-19
 * @description：TODO
 */
@Repository( "customerDao" )
public class CustomerDaoJpa extends JpaAnnotationDao<Customer> implements CustomerDao {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7806402390963681764L;

	public ListPage<Customer> list( ListPage<Customer> page, Customer t ) {
		if ( t == null )
			return list( page );

		StringBuffer hql = new StringBuffer( " FROM " );
		if ( "1".equals( t.get_queryType() ) )
			hql.append( Personal.class.getName() );
		else if ( "2".equals( t.get_queryType() ) )
			hql.append( Enterprise.class.getName() );
		else
			hql.append( Customer.class.getName() );

		hql.append( " AS m WHERE 1=1 " );
		List<Object> params = new ArrayList<Object>();

		if ( !StringUtils.isEmpty( t.getId() ) ) {
			hql.append( " AND m.id like ? " );
			params.add( "%" + t.getId().trim() + "%" );
		}

		if ( !StringUtils.isEmpty( t.getName() ) ) {
			hql.append( " AND m.name like ? " );
			params.add( "%" + t.getName().trim() + "%" );
		}

		if ( !StringUtils.isEmpty( t.getStatus() ) ) {
			hql.append( " AND m.status = ? " );
			params.add( t.getStatus().trim() );
		}

		return this.query( page, hql.toString(), params.toArray() );
	}

	/**
	 * 更新认证状态
	 */
	public void updateCertStatus( String custid, AuthenticationStatus status ) {
		if ( StringUtils.isEmpty( custid ) || status == null )
			return;

		Customer customer = this.read( custid );
		if ( customer != null ) {
			customer.setCertStatus( status.getValue() );// TODO与商户暂时用一个状态
			this.update( customer );
		}
	}
	
	/**
	 * 更新支付密码
	 */
	public int updatePassword( String userId, String oldPassword, String newPassword ) {
		String hql = "UPDATE " + Customer.class.getName() 
				+ " SET payPassword = ? WHERE id = ? AND payPassword = ? ";
		
		return addQueryParams(getEntityManager().createQuery(hql), 
				new Object[]{newPassword,userId,oldPassword}).executeUpdate();
	}
	
	/**
	 * 根据证件类型及号码查询
	 */
	@SuppressWarnings("unchecked")
	public Customer get(Class<?> clazz, String certType, String certNo ) {
		if( clazz == null )
			clazz = Customer.class;
		
		String hql = " FROM " + clazz.getName() 
				+ " WHERE credentialsType = ? AND identityCardNumber = ? ";
		
		List<Customer> results = addQueryParams(getEntityManager().createQuery(hql), 
				new Object[]{certType,certNo}).getResultList();
		
		return (results==null||results.isEmpty())?null:results.get(0);
	}
	/**
	 * @see com.ylink.ylpay.project.mp.cust.dao.CustomerDao#checkPassword(java.lang.String, java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Customer checkPassword(String custId, String password) {
		String hql = " SELECT m FROM " + Customer.class.getName() 
				+ " AS m WHERE m.id = ? AND m.payPassword = ? ";
		Query query = getEntityManager().createQuery( hql.toString() );
		this.addQueryParams(query, new Object[]{custId,password});
		List<Personal> results = query.getResultList();
		if(results == null || results.isEmpty() || results.size() > 1){
			return null;
		}
		return results.get( 0 );
	}
}