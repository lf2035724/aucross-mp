package com.ylink.ylpay.project.mp.basic.dao;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.google.code.lightssh.common.dao.jpa.JpaAnnotationDao;
import com.ylink.ylpay.project.mp.basic.entity.FundsLimitBank;

@Repository("fundsLimitBankDao")
public class FundsLimitBankDaoJpa  extends JpaAnnotationDao< FundsLimitBank> implements FundsLimitBankDao{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5447942598324060822L;

	@SuppressWarnings("unchecked")
	@Override
	public List<FundsLimitBank> getList(String value, String value2,
			String value3) {
			StringBuffer hql = new StringBuffer();
			hql.append(" SELECT m FROM " + entityClass.getName());
				hql.append(" AS m WHERE m.bankId = ? AND m.cardType = ? AND m.channelType=?");

			Query query = this.getEntityManager().createQuery(hql.toString());
			this.addQueryParams(query,new Object[]{value,value2,value3} );
			List<FundsLimitBank> results = query.getResultList();
			return ( results == null || results.isEmpty() ) ? null : results;
		}
	}

