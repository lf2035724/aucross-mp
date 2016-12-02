package com.ylink.ylpay.project.mp.cust.dao;

import java.util.List;

import com.ylink.modules.orm.jpa.CustomJpaDao;
import com.ylink.ylpay.project.mp.cust.entity.Enterprise;

/**
 * 企业客户DAO.
 * 
 * @author 潘瑞峥
 * @date 2012-12-26
 */
public interface EnterpriseDao extends CustomJpaDao<Enterprise> {
	
	/**
	 * 增加批次号
	 */
	public int incBatchNo(String id );

	/**根据客户绑定的银行卡查询客户信息
	 * @description 
	 * @param bankCard
	 * @param isCert
	 * @return  
	 * @author yu.han
	 * @date 2014-4-16
	 */
	public List<Enterprise> findByBankcard(String bankCard, String isCert);
	
}