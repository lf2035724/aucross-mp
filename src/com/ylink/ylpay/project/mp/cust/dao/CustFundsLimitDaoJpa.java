package com.ylink.ylpay.project.mp.cust.dao;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.ylink.modules.orm.jpa.CustomJpaDaoImpl;
import com.ylink.ylpay.project.mp.cust.entity.CustFundsLimit;

/**
 * 客户资金限额DAO.
 * 
 * @author 潘瑞峥
 * @date 2012-11-26
 */
@Repository( "custFundsLimitDao" )
public class CustFundsLimitDaoJpa extends CustomJpaDaoImpl<CustFundsLimit> implements CustFundsLimitDao {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8258098198470405905L;
	

	@SuppressWarnings("unchecked")
	@Override
	public List<CustFundsLimit> getList(String custId, String value) {
		if( value == null  )
			return null;
		
		StringBuffer hql = new StringBuffer();
		hql.append(" SELECT m FROM " + entityClass.getName());
		hql.append(" AS m WHERE m.custId = ? AND m.businessId = ?");

		Query query = this.getEntityManager().createQuery(hql.toString());
		this.addQueryParams(query,new Object[]{custId,value} );
		List<CustFundsLimit> results = query.getResultList();

		return ( results == null || results.isEmpty() ) ? null : results;
	}
}