package com.ylink.ylpay.project.mp.basic.dao;

import org.springframework.stereotype.Repository;

import com.ylink.modules.orm.jpa.CustomJpaDaoImpl;
import com.ylink.ylpay.project.mp.basic.entity.FundsLimit;
@Repository("fundsLimitDao")
public class FundsLimitDaoJpa extends CustomJpaDaoImpl<FundsLimit> implements FundsLimitDao{

	/**
	 * 查询银行卡限额
	 */
	private static final long serialVersionUID = 1646332317314195316L;


}
