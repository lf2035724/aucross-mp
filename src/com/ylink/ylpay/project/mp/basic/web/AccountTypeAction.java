package com.ylink.ylpay.project.mp.basic.web;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.google.code.lightssh.common.model.page.ListPage;
import com.ylink.modules.web.struts2.CustomAction;
import com.ylink.ylpay.project.mp.basic.entity.AccountType;
import com.ylink.ylpay.project.mp.basic.service.AccountTypeManager;


@Component( "accountTypeAction" )
@Scope( "prototype" )
public class AccountTypeAction extends CustomAction<AccountType>{

	private static final long serialVersionUID = 1L;
	
	@SuppressWarnings("unused")
	private AccountTypeManager accountTypeManager;
	
	@Resource( name = "accountTypeManager" )
	public void setAccountTypeManager(AccountTypeManager accountTypeManager) {
		this.accountTypeManager = accountTypeManager;
		super.manager=accountTypeManager;
	}
	
	/**
	 * 列表
	 */
	public String list() {
		if ( null == super.getPage() ) {
			super.setPage( new ListPage<AccountType>() );
		}
		return super.findPage();
      }

}
