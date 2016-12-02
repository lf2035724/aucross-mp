package com.ylink.ylpay.project.mp.basic.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.google.code.lightssh.common.entity.Persistence;

/**
 * 账户类型查询
 * @author xuwei
 */
@Entity
@Table( name = "T_ACCOUNT_TYPE" )
public class AccountType implements Persistence<String>{


	private static final long serialVersionUID = -3790065662016382007L;
	
	/**
	 * 账号类型
	 */
	@Id
	@Column( name = "KEY" )
	private String key;
	
	/**
	 * 账号名称
	 */
	@Column( name = "NAME" )
	private String name;
	
	/**
	 * 是否计息
	 */
	@Column( name = "INTEREST_FLAG",length = 100)
	private Integer interrst;
	
	/**
	 * 是否存入
	 */
	@Column( name = "DEPOSIT_FLAG" )
	private Integer deposit;
	
	/**
	 * 是否支出
	 */
	@Column( name = "WITHDRAW_FLAG" )
	private Integer withdraw;
	
	/**
	 * 备注
	 */
	@Column( name = "DESCRIPTION",length = 500)
	private Integer description;
	
	// -- getters and setters --------------------------------------------------
	
	
	@Override
	public String getIdentity() {
		// TODO Auto-generated method stub
		return key;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getInterrst() {
		return interrst;
	}

	public void setInterrst(Integer interrst) {
		this.interrst = interrst;
	}

	public Integer getDeposit() {
		return deposit;
	}

	public void setDeposit(Integer deposit) {
		this.deposit = deposit;
	}

	public Integer getWithdraw() {
		return withdraw;
	}

	public void setWithdraw(Integer withdraw) {
		this.withdraw = withdraw;
	}

	public Integer getDescription() {
		return description;
	}

	public void setDescription(Integer description) {
		this.description = description;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public boolean isInsert() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void postInsertFailure() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void preInsert() {
		// TODO Auto-generated method stub
		
	}

}
