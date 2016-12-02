package com.ylink.ylpay.project.mp.cust.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.Type;

import com.ylink.modules.orm.hibernate.type.CustomEnumType;
import com.ylink.ylpay.common.project.mp.constant.AuditStatus;
import com.ylink.ylpay.project.constants.entity.BaseEntity;

/**
 * 个人客户实名认证实体.
 * 
 * @author 潘瑞峥
 * @date 2013-1-15
 */
@Entity
@Table( name = "T_CUST_NAME_AUTH" )
public class CustNameAuth extends BaseEntity {

	private static final long serialVersionUID = 8474587404728452187L;

	/** 个人客户. */
	@ManyToOne( fetch = FetchType.LAZY )
	@JoinColumn( name = "CUST_ID" )
	private Personal cust;

	/** 客户证件. */
	@ManyToOne( fetch = FetchType.LAZY )
	@JoinColumn( name = "CERT_ID" )
	private CustCert cert;

	/** 审核状态. */
	@Column( name = "AUDIT_STATUS" )
	@Type( type = CustomEnumType.ENUM_TYPE, parameters = { @Parameter( name = "enumClass", value = "com.ylink.ylpay.common.project.mp.constant.AuditStatus" ) } )
	private AuditStatus auditStatus;

	/** 创建时间. */
	@Temporal( TemporalType.DATE )
	@Column( name = "CREATED_TIME" )
	private Date createdTime = new Date();

	public CustNameAuth() {
	}

	public Personal getCust() {
		return cust;
	}

	public void setCust( Personal cust ) {
		this.cust = cust;
	}

	public CustCert getCert() {
		return cert;
	}

	public void setCert( CustCert cert ) {
		this.cert = cert;
	}

	public AuditStatus getAuditStatus() {
		return auditStatus;
	}

	public void setAuditStatus( AuditStatus auditStatus ) {
		this.auditStatus = auditStatus;
	}

	public Date getCreatedTime() {
		return createdTime;
	}

}