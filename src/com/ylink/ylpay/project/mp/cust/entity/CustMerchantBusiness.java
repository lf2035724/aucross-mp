package com.ylink.ylpay.project.mp.cust.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.Type;

import com.google.code.lightssh.common.entity.Persistence;
import com.ylink.modules.orm.hibernate.type.CustomEnumType;
import com.ylink.ylpay.common.project.mp.constant.OptType;

/**
 * 商户交易类型.
 * 
 * @author 潘瑞峥
 * @date 2012-11-2
 */
@Entity
@Table( name = "T_MERCHANT_BUSINESS" )
public class CustMerchantBusiness implements Persistence<String> {

	private static final long serialVersionUID = 963534551523624466L;

	@Id
	@GeneratedValue( generator = "system-uuid" )
	@GenericGenerator( name = "system-uuid", strategy = "uuid" )
	@Column( name = "ID" )
	private String identity;

	/** 商户. */
	@ManyToOne( fetch = FetchType.LAZY )
	@JoinColumn( name = "MERCHANT_ID" )
	private CustMerchant custMerchant;

	/** 交易类型. */
	@Column( name = "BUSINESS_TYPE" )
	@Type( type = CustomEnumType.ENUM_TYPE, parameters = { @Parameter( name = "enumClass", value = "com.ylink.ylpay.common.project.mp.constant.OptType" ) } )
	private OptType businessType;

	/** 创建日期. */
	@Temporal( TemporalType.DATE )
	@Column( name = "CREATED_TIME" )
	private Date createdTime;

	@Override
	public String getIdentity() {
		return identity;
	}

	public void setIdentity( String identity ) {
		this.identity = identity;
	}

	public CustMerchant getCustMerchant() {
		return custMerchant;
	}

	public void setCustMerchant( CustMerchant custMerchant ) {
		this.custMerchant = custMerchant;
	}

	public OptType getBusinessType() {
		return businessType;
	}

	public void setBusinessType( OptType businessType ) {
		this.businessType = businessType;
	}

	public Date getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime( Date createdTime ) {
		this.createdTime = createdTime;
	}

	@Override
	public void preInsert() {
	}

	@Override
	public void postInsertFailure() {
	}

	@Override
	public boolean isInsert() {
		return false;
	}

}