package com.ylink.ylpay.project.mp.system.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.ylink.ylpay.project.constants.entity.BaseEntity;

/** 
 * @author feng.li
 * @date 2014-11-12
 * @description：TODO
 */

/**
 * @author feng.li
 *
 */
@Entity
@Table( name = "T_ACCEPT_BANK_MESSAGE" )
public class AcceptBankMessage extends BaseEntity{

	private static final long serialVersionUID = 106072564716485823L;

	@Column( name = "CUST_TYPE" )
	private String custType;
	
	@Column( name = "MERCHANT_NO" )
	private String merchantNO;
	
	@Column( name = "WEIGHT_LEVEL" )
	private Integer weightLevel; 
	
	@Column( name = "OPT_TYPE" )
	private String optType;
	
	@Column( name = "PAY_OPT_TYPE" )
	private String payOptType;
	
	@Column( name = "PUBLISH_BANK" )
	private String publishBank;
	
	@Column( name = "SIGN_BANK" )
	private String signBank;
	
	@Column( name = "ACCEPT_BANK" )
	private String acceptBank;
	
	@Column( name = "EXPIRY_FLAG" )
	private String expiryFlag;
	
	@Column( name = "START_AMOUNT" )
	private Long startAmount;
	
	@Column( name = "CUTOFF_AMOUNT" )
	private Long cutoffAmount;
	
	//签约终端
	@Column( name = "SIGN_TERMINAL" )
	private String signTerminal;
	
	@Column( name = "DESCRIPTION" )
	private String description;
	
	
	public String getCustType() {
		return custType;
	}

	public void setCustType(String custType) {
		this.custType = custType;
	}

	public String getExpiryFlag() {
		return expiryFlag;
	}

	public void setExpiryFlag(String expiryFlag) {
		this.expiryFlag = expiryFlag;
	}

	public Long getCutoffAmount() {
		return cutoffAmount;
	}

	public void setCutoffAmount(Long cutoffAmount) {
		this.cutoffAmount = cutoffAmount;
	}

	public Long getStartAmount() {
		return startAmount;
	}

	public void setStartAmount(Long startAmount) {
		this.startAmount = startAmount;
	}

	public String getOptType() {
		return optType;
	}

	public void setOptType(String optType) {
		this.optType = optType;
	}

	public String getPayOptType() {
		return payOptType;
	}

	public void setPayOptType(String payOptType) {
		this.payOptType = payOptType;
	}

	public String getPublishBank() {
		return publishBank;
	}

	public void setPublishBank(String publishBank) {
		this.publishBank = publishBank;
	}

	public String getAcceptBank() {
		return acceptBank;
	}

	public void setAcceptBank(String acceptBank) {
		this.acceptBank = acceptBank;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getSignBank() {
		return signBank;
	}

	public void setSignBank(String signBank) {
		this.signBank = signBank;
	}

	public String getSignTerminal() {
		return signTerminal;
	}

	public void setSignTerminal(String signTerminal) {
		this.signTerminal = signTerminal;
	}

	public String getMerchantNO() {
		return merchantNO;
	}

	public void setMerchantNO(String merchantNO) {
		this.merchantNO = merchantNO;
	}

	public Integer getWeightLevel() {
		return weightLevel;
	}

	public void setWeightLevel(Integer weightLevel) {
		this.weightLevel = weightLevel;
	}
}
