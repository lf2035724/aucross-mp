package com.ylink.ylpay.project.constants.bean;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 借贷凭证bean.
 * 
 * @author 潘瑞峥
 * @date 2013-1-27
 */
public class CreditDebitVoucherBean implements Serializable {

	private static final long serialVersionUID = 676482379057943926L;

	/** accountDate. */
	private String accountDate;

	/** creditRemark. */
	private String creditRemark;

	/** debitRemark. */
	private String debitRemark;

	/** creditSubject. */
	private String creditSubject;

	/** debitSubject. */
	private String debitSubject;

	/** creditMoney. */
	private String creditMoney;

	/** debitMoney. */
	private String debitMoney;

	/** totalCreditMoney. */
	private String totalCreditMoney;

	/** totalDebitMoney. */
	private String totalDebitMoney;

	public String getAccountDate() {
		return accountDate;
	}

	public void setAccountDate( String accountDate ) {
		this.accountDate = accountDate;
	}

	public String getCreditRemark() {
		return creditRemark;
	}

	public void setCreditRemark( String creditRemark ) {
		this.creditRemark = creditRemark;
	}

	public String getDebitRemark() {
		return debitRemark;
	}

	public void setDebitRemark( String debitRemark ) {
		this.debitRemark = debitRemark;
	}

	public String getCreditSubject() {
		return creditSubject;
	}

	public void setCreditSubject( String creditSubject ) {
		this.creditSubject = creditSubject;
	}

	public String getDebitSubject() {
		return debitSubject;
	}

	public void setDebitSubject( String debitSubject ) {
		this.debitSubject = debitSubject;
	}

	public String getCreditMoney() {
		return creditMoney;
	}

	public void setCreditMoney( String creditMoney ) {
		this.creditMoney = creditMoney;
	}

	public String getDebitMoney() {
		return debitMoney;
	}

	public void setDebitMoney( String debitMoney ) {
		this.debitMoney = debitMoney;
	}

	public String getTotalCreditMoney() {
		return totalCreditMoney;
	}

	public void setTotalCreditMoney( String totalCreditMoney ) {
		this.totalCreditMoney = totalCreditMoney;
	}

	public String getTotalDebitMoney() {
		return totalDebitMoney;
	}

	public void setTotalDebitMoney( String totalDebitMoney ) {
		this.totalDebitMoney = totalDebitMoney;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString( this, ToStringStyle.MULTI_LINE_STYLE );
	}

}