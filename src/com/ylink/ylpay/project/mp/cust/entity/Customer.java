package com.ylink.ylpay.project.mp.cust.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import com.google.code.lightssh.common.entity.Persistence;
import com.google.code.lightssh.common.model.Nameable;
import com.google.code.lightssh.common.model.Sequenceable;
import com.ylink.ylpay.project.constants.EncodeRulesConstant;
import org.apache.commons.lang3.StringUtils;

/**
 * 客户
 * 
 * @author 张磊
 * 
 */
@Entity
@Inheritance( strategy = InheritanceType.TABLE_PER_CLASS )
public abstract class Customer implements Persistence<String>, Sequenceable, Cloneable, Nameable {

	private static final long serialVersionUID = 5605051638088277447L;

	/**
	 * CODE
	 */
	@Id
	@Column( name = "ID" )
	private String id;

	/**
	 * 支付密码
	 */
	@Column( name = "PAY_PASSWORD", unique = false, length = 50 )
	private String payPassword;

	/**
	 * 姓名（个人/企业）
	 */
	@Column( name = "NAME", unique = false, nullable = false )
	private String name;

	/**
	 * 证件类型（个人/企业）
	 */
	@Column( name = "CREDENTIALS_TYPE", unique = false, nullable = false )
	private String credentialsType;

	/**
	 * 证件号码（个人/企业）
	 */
	@Column( name = "IDENTITY_CARD_NUMBER", unique = false, nullable = false )
	private String identityCardNumber;

	/**
	 * 状态
	 */
	@Column( name = "STATUS", unique = false, length = 20 )
	private String status;

	/**
	 * 认证状态
	 */
	@Column( name = "CERT_STATUS", unique = false, length = 20 )
	private String certStatus;

	/**
	 * 创建时间
	 */
	@Column( name = "CREATED_TIME", columnDefinition = "DATE" )
	@Temporal( TemporalType.TIMESTAMP )
	private Date createDate = new Date();

	/*
	 * STATUS VARCHAR2(20) N 冻结状态 EFFECTIVE:有效的 FROZEN:冻结的 CERT_STATUS VARCHAR2(20) N 认证状态 NEW：未认证
	 * PASSED：已认证通过 NOT_PASSED：未认证通过
	 */

	/**
	 * 查询类型 1为个人用户，2为企业用户，其它为全部
	 */
	@Transient
	public String _queryType;

	public String getId() {
		return id;
	}

	public void setId( String id ) {
		this.id = id;
	}

	public String getPayPassword() {
		return payPassword;
	}

	public void setPayPassword( String payPassword ) {
		this.payPassword = payPassword;
	}

	public String getName() {
		return name;
	}

	public void setName( String name ) {
		this.name = name;
	}

	public String getCredentialsType() {
		return credentialsType;
	}

	public void setCredentialsType( String credentialsType ) {
		this.credentialsType = credentialsType;
	}

	public String getIdentityCardNumber() {
		return identityCardNumber;
	}

	public void setIdentityCardNumber( String identityCardNumber ) {
		this.identityCardNumber = identityCardNumber;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus( String status ) {
		this.status = status;
	}

	public String getCertStatus() {
		return certStatus;
	}

	public void setCertStatus( String certStatus ) {
		this.certStatus = certStatus;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate( Date createDate ) {
		this.createDate = createDate;
	}

	public String get_queryType() {
		return _queryType;
	}

	public void set_queryType( String queryType ) {
		_queryType = queryType;
	}

	@Override
	public String getIdentity() {
		return this.id;
	}

	@Override
	public boolean isInsert() {
		return StringUtils.isEmpty( this.id );
	}

	@Override
	public void postInsertFailure() {
		this.id = null;
	}

	@Override
	public void preInsert() {

	}

	@Override
	public String getSequenceKey() {
		return EncodeRulesConstant.DEFAULT_PREFIX;
	}

	@Override
	public int getSequenceLength() {
		return EncodeRulesConstant.CUSTOMER_CODE_LENGTH;
	}

	@Override
	public int getSequenceStep() {
		return EncodeRulesConstant.DEFAULT_SPIKE;
	}

	public Customer clone() {
		try {
			return ( Customer ) super.clone();
		} catch ( CloneNotSupportedException e ) {
			return null;
		}
	}

}