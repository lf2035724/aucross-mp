package com.ylink.ylpay.project.mp.basic.web;

import javax.annotation.Resource;

import org.apache.struts2.json.annotations.JSON;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.google.code.lightssh.project.web.action.GenericAction;
import com.ylink.ylpay.project.mp.basic.entity.FundsLimitBank;
import com.ylink.ylpay.project.mp.basic.service.FundsLimitBankManager;

@Component( "fundsLimitBankAction" )
@Scope( "prototype" )
public class FundsLimitBankAction extends GenericAction<FundsLimitBank>{

	private static final long serialVersionUID = 1L;
	
	private FundsLimitBank fundsLimitBank;
	
	private FundsLimitBankManager fundsLimitBankManager;
	@Resource( name = "fundsLimitBankManager" )
	public void setFundsLimitBankManager(FundsLimitBankManager fundsLimitBankManager) {
		this.fundsLimitBankManager = fundsLimitBankManager;
		super.manager=fundsLimitBankManager;
	}
	
	public FundsLimitBankManager getFundsLimitBankManager() {
		return fundsLimitBankManager;
	}
	
	public void setFundsLimitBank(FundsLimitBank fundsLimitBank) {
		this.fundsLimitBank = fundsLimitBank;
		super.model=fundsLimitBank;
	}
	
	public FundsLimitBank getFundsLimitBank() {
		this.fundsLimitBank=super.model;
		return fundsLimitBank;
	}
	
	@JSON(name="unique")
	public boolean isUnique() {
		return unique;
	}
	
  	public String unique( ){
		this.unique = this.getFundsLimitBankManager().isUniqueBankIdAndCardTypeAndChannelTypeAndBusinessId(fundsLimitBank);
		return SUCCESS;
	}

}
