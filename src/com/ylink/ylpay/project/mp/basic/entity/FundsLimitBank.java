package com.ylink.ylpay.project.mp.basic.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.google.code.lightssh.common.entity.base.BaseModel;

/**
 * 银行资金限额
 * @author xuwei
 *
 */
@Entity
@Table( name = "T_FUNDS_LIMIT_BANK" )
public class FundsLimitBank extends BaseModel{

	private static final long serialVersionUID = 2477430845125886336L;
	/**
	 * 关联行别T_BANK_TYPE表
	 */
	@Column(name="BANK_ID")
	private String bankId;
	
	/**
	 * 银行卡类型     1为借记卡  2为贷记卡
	 */
	@Column(name="CARD_TYPE")
	private String cardType;
	
	/**
	 * 渠道类型(枚举)
	 */
	@Column(name="CHANNEL_TYPE")
	private String channelType;
	
	/**
	 * 交易类型(枚举)
	 */
	@Column(name="BUSINESS_ID")
	private String businessId;
	
	/**
	 * 单笔限额（单位：分）
	 */
	@Column(name="SINGLE_LIMIT")
	private Integer singleLimit;
	
	/**
	 * 每天限制（单位：分）
	 */
	@Column(name="DAY_LIMIT")
	private Integer dayLimit;
	
	/**
	 * 每月限制（单位：分）
	 */
	@Column(name="MONTH_LIMIT")
	private Integer monthLimit;

	public String getBankId() {
		return bankId;
	}

	public void setBankId(String bankId) {
		this.bankId = bankId;
	}

	public String getCardType() {
		return cardType;
	}

	public void setCardType(String cardType) {
		this.cardType = cardType;
	}

	public String getChannelType() {
		return channelType;
	}

	public void setChannelType(String channelType) {
		this.channelType = channelType;
	}

	public String getBusinessId() {
		return businessId;
	}

	public void setBusinessId(String businessId) {
		this.businessId = businessId;
	}

	public Integer getSingleLimit() {
		return singleLimit;
	}

	public void setSingleLimit(Integer singleLimit) {
		this.singleLimit = singleLimit;
	}

	public Integer getDayLimit() {
		return dayLimit;
	}

	public void setDayLimit(Integer dayLimit) {
		this.dayLimit = dayLimit;
	}

	public Integer getMonthLimit() {
		return monthLimit;
	}

	public void setMonthLimit(Integer monthLimit) {
		this.monthLimit = monthLimit;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
