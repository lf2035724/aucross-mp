package com.ylink.ylpay.project.mp.cust.dao;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.hibernate.QueryParameterException;
import com.ylink.modules.orm.jpa.CustomJpaDaoImpl;
import com.ylink.ylpay.project.mp.cust.entity.Enterprise;

/**
 * 企业客户DAO.
 * 
 * @author 潘瑞峥
 * @date 2012-12-26
 */
@Repository( "enterpriseDao" )
public class EnterpriseDaoJpa extends CustomJpaDaoImpl<Enterprise> implements EnterpriseDao {

	private static final long serialVersionUID = -8406861000297379561L;
	
	/**
	 * 增加批次号
	 */
	public int incBatchNo(String id ){
		String hql = " UPDATE " + this.entityClass.getName() + " SET batchNo = NVL(batchNo,0) + 1 WHERE id = ? ";
		return this.addQueryParams(getEntityManager().createQuery(hql),id).executeUpdate();
	}

	/* (non-Javadoc)
	 * @see com.ylink.ylpay.project.mp.cust.dao.EnterpriseDao#findByBankcard(java.lang.String, java.lang.String)
	 */
	@Override
	public List<Enterprise> findByBankcard(String bankCard, String isCert) {
		String hql = " select a from " + this.entityClass.getName() + " a, CustBankcard b where a.id = b.custId and b.cardNo =? and b.isCert=?";
		Query query = getEntityManager().createQuery(hql);
		query.setParameter(1, bankCard);
		query.setParameter(2, isCert);
		return query.getResultList();
	}


}