package com.ylink.ylpay.project.mp.cust.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.Type;

import com.google.code.lightssh.project.util.constant.AuditStatus;
import com.ylink.modules.orm.hibernate.type.CustomEnumType;
import com.ylink.ylpay.common.project.mp.constant.AuthMoneyStatus;
import com.ylink.ylpay.project.constants.entity.BaseEntity;

/** 
 * @author feng.li
 * @date 2013-10-25
 * @description：金汇宝授信实体
 */

/**
 * @author feng.li
 */
@Entity
@Table( name = "T_CUST_AUTH_MONEY" )
public class AuthMoney extends BaseEntity{

	private static final long serialVersionUID = -4085582860646290499L;

	@Column(name = "CUST_ID")
	private String custId;
	
	@Column(name = "AUTH_MONEY_REDEM")
	private Long authMoneyReDem;
	
	@Column(name = "REDEM_USED")
	private Long redemUsed;
	
	@Column( name = "REDEM_STATE" )
	@Type( type = CustomEnumType.ENUM_TYPE, parameters = { @Parameter( name = "enumClass", value = "com.ylink.ylpay.common.project.mp.constant.AuthMoneyStatus" ) } )
	private AuthMoneyStatus redemStatus;
	
	@Column(name = "AUTH_MONEY_PAY")
	private Long authMoneyPay;
	
	@Column(name = "PAY_USED")
	private Long payUsed;
	
	@Column( name = "BUY_STATE" )
	@Type( type = CustomEnumType.ENUM_TYPE, parameters = { @Parameter( name = "enumClass", value = "com.ylink.ylpay.common.project.mp.constant.AuthMoneyStatus" ) } )
	private AuthMoneyStatus buyStatus;
	
	/** 审核状态. */
	@Column( name="AUDIT_STATUS",length=50 )
	@Enumerated(EnumType.STRING)
	private AuditStatus auditStatus;
	
	public AuditStatus getAuditStatus() {
		return auditStatus;
	}

	public void setAuditStatus(AuditStatus auditStatus) {
		this.auditStatus = auditStatus;
	}

	public String getCustId() {
		return custId;
	}

	public void setCustId(String custId) {
		this.custId = custId;
	}

	public Long getAuthMoneyReDem() {
		return authMoneyReDem;
	}

	public void setAuthMoneyReDem(Long authMoneyReDem) {
		this.authMoneyReDem = authMoneyReDem;
	}

	public Long getRedemUsed() {
		return redemUsed;
	}

	public void setRedemUsed(Long redemUsed) {
		this.redemUsed = redemUsed;
	}

	public AuthMoneyStatus getRedemStatus() {
		return redemStatus;
	}

	public void setRedemStatus(AuthMoneyStatus redemStatus) {
		this.redemStatus = redemStatus;
	}

	public Long getAuthMoneyPay() {
		return authMoneyPay;
	}

	public void setAuthMoneyPay(Long authMoneyPay) {
		this.authMoneyPay = authMoneyPay;
	}

	public Long getPayUsed() {
		return payUsed;
	}

	public void setPayUsed(Long payUsed) {
		this.payUsed = payUsed;
	}

	public AuthMoneyStatus getBuyStatus() {
		return buyStatus;
	}

	public void setBuyStatus(AuthMoneyStatus buyStatus) {
		this.buyStatus = buyStatus;
	}
}
