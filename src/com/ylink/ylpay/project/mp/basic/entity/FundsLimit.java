package com.ylink.ylpay.project.mp.basic.entity;

import javax.persistence.Column;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import javax.persistence.Entity;

import com.google.code.lightssh.common.entity.base.BaseModel;

/**
 * 虚户资金限额
 * 
 * @author xuwei
 * 
 */
@Entity
@Table( name = "T_FUNDS_LIMIT_ACC", uniqueConstraints = { @UniqueConstraint( columnNames = { "ACC_TYPE_ID", "BUSINESS_ID" } ) } )
public class FundsLimit extends BaseModel {

	private static final long serialVersionUID = 9089665529425358581L;
	/**
	 * 关联帐户类型T_ACCOUNT_TYPE
	 */
	@Column( name = "ACC_TYPE_ID" )
	private String accType;

	/**
	 * 关联交易类型T_BUSINESS_TYPE
	 */
	@Column( name = "BUSINESS_ID" )
	private String businessId;

	/**
	 * 单笔警告额（单位：分）
	 */
	@Column( name = "SINGLE_WARN" )
	private Long singleWarn;

	/**
	 * 每天警告额（单位：分）
	 */
	@Column( name = "DAY_WARN" )
	private Long dayWarn;

	/**
	 * 单笔限额（单位：分）
	 */
	@Column( name = "SINGLE_LIMIT" )
	private Long singleLimit;

	/**
	 * 每天限制（单位：分）
	 */
	@Column( name = "DAY_LIMIT" )
	private Long dayLimit;

	/**
	 * 每月警告额（单位：分）
	 */
	@Column( name = "MONTH_WARN" )
	private Long monthWarn;

	/**
	 * 每月限制（单位：分）
	 */
	@Column( name = "MONTH_LIMIT" )
	private Long monthLimit;

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public void setSingleLimit( Long singleLimit ) {
		this.singleLimit = singleLimit;
	}

	public Long getSingleLimit() {
		return singleLimit;
	}

	public void setDayLimit( Long dayLimit ) {
		this.dayLimit = dayLimit;
	}

	public Long getDayLimit() {
		return dayLimit;
	}

	public void setDayWarn( Long dayWarn ) {
		this.dayWarn = dayWarn;
	}

	public Long getDayWarn() {
		return dayWarn;
	}

	public void setSingleWarn( Long singleWarn ) {
		this.singleWarn = singleWarn;
	}

	public Long getSingleWarn() {
		return singleWarn;
	}

	public String getAccType() {
		return accType;
	}

	public void setAccType( String accType ) {
		this.accType = accType;
	}

	public void setBusinessId( String businessId ) {
		this.businessId = businessId;
	}

	public String getBusinessId() {
		return businessId;
	}

	public void setMonthWarn( Long monthWarn ) {
		this.monthWarn = monthWarn;
	}

	public Long getMonthWarn() {
		return monthWarn;
	}

	public void setMonthLimit( Long monthLimit ) {
		this.monthLimit = monthLimit;
	}

	public Long getMonthLimit() {
		return monthLimit;
	}

}