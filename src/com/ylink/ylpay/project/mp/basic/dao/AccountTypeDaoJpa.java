package com.ylink.ylpay.project.mp.basic.dao;

import org.springframework.stereotype.Repository;

import com.google.code.lightssh.common.dao.jpa.JpaAnnotationDao;
import com.ylink.ylpay.project.mp.basic.entity.AccountType;


@Repository("accountTypeDao")
public class AccountTypeDaoJpa extends JpaAnnotationDao<AccountType> implements AccountTypeDao{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7153813557546428936L;

}
