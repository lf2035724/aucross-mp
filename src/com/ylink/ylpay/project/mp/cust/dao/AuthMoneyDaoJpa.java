package com.ylink.ylpay.project.mp.cust.dao;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.google.code.lightssh.common.dao.jpa.JpaAnnotationDao;
import com.ylink.ylpay.project.mp.cust.entity.AuthMoney;

/** 
 * @author feng.li
 * @date 2013-10-25
 * @description：TODO
 */

/**
 * @author feng.li
 *
 */
@Repository( "authMoneyDao" )
public class AuthMoneyDaoJpa extends JpaAnnotationDao<AuthMoney> implements AuthMoneyDao{

	private static final long serialVersionUID = 6893964261582610972L;

	@Override
	@SuppressWarnings("unchecked")
	public AuthMoney getByCustId(String custId) {
		String hql = " SELECT m FROM " + entityClass.getName() + " AS m WHERE m.custId = ? ";
		Query query = this.getEntityManager().createQuery(hql);
		this.addQueryParams(query,custId);
		List<AuthMoney> results = query.getResultList();
		return (results==null||results.isEmpty())?null:results.get(0);
	}
}
