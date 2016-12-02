package com.ylink.ylpay.project.mp.basic.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.google.code.lightssh.common.service.BaseManagerImpl;
import com.ylink.ylpay.project.mp.basic.dao.AccountTypeDao;
import com.ylink.ylpay.project.mp.basic.entity.AccountType;


@Component("accountTypeManager")
public class AccountTypeManagerImpl extends BaseManagerImpl<AccountType> implements AccountTypeManager{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3272702539485771163L;
	
	private AccountTypeDao accountTypeDao;
    
	@Resource( name = "accountTypeDao")
	public void setAccountTypeDao(AccountTypeDao accountTypeDao) {
		this.accountTypeDao = accountTypeDao;
		super.dao=accountTypeDao;
	}

	public AccountTypeDao getAccountTypeDao() {
		return accountTypeDao;
	}
	
}
