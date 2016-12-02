package com.ylink.ylpay.project.mp.basic.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.google.code.lightssh.common.entity.Persistence;
/**
 * 银行类别
 * @author xuwei
 *
 */
@Entity
@Table( name = "T_BANK_TYPE" )
public class BanType implements Persistence<String>{
	
	private static final long serialVersionUID = -8506170441862939228L;
	
	/**
	 * 类别编号
	 */
	@Id
	@Column( name = "BANK_TYPE" )
	private String bankType;
	
	/**
	 * 名称
	 */
	@Column(name = "BANK_NAME",nullable = true,length = 128)
	private String bankName;

	
	// -- getters and setters --------------------------------------------------
	public String getBankType() {
		return bankType;
	}
	public void setBankType(String bankType) {
		this.bankType = bankType;
	}
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	@Override
	public String getIdentity() {
		return this.bankType;
	}
	@Override
	public boolean isInsert() {
		return this.bankType == null;
	}
	@Override
	public void postInsertFailure() {
		
	}
	@Override
	public void preInsert() {
		
	}
	
}
