package com.ylink.ylpay.project.mp.cust.dao;

import java.util.List;

import com.ylink.modules.orm.jpa.CustomJpaDao;
import com.ylink.ylpay.project.mp.cust.entity.CustFundsLimit;

/**
 * 客户资金限额DAO.
 *
 * @author 潘瑞峥
 * @date 2012-11-26
 */
public interface CustFundsLimitDao extends CustomJpaDao<CustFundsLimit> {
	

	/**
	 * 查询资金限额
	 * @param custId
	 * @param optType
	 * @author xuwei
	 * @return
	 */
	public List<CustFundsLimit> getList(String custId, String value);
}