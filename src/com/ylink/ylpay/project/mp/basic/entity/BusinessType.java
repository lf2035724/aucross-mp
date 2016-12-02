package com.ylink.ylpay.project.mp.basic.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.Type;

import com.google.code.lightssh.common.entity.Persistence;
import com.ylink.modules.orm.hibernate.type.CustomEnumType;
import com.ylink.ylpay.common.project.mp.constant.OptType;
import com.ylink.ylpay.common.project.mp.constant.ProductType;

/**
 * 交易类型查询
 * 
 * @author xuwei
 * 
 */
@Entity
@Table( name = "T_BUSINESS_TYPE" )
public class BusinessType implements Persistence<String> {

	private static final long serialVersionUID = 8925200050721112178L;

	@Id
	@GeneratedValue( generator = "system-uuid" )
	@GenericGenerator( name = "system-uuid", strategy = "uuid" )
	@Column( name = "ID" )
	private String identity;

	@Override
	public String getIdentity() {
		return this.identity;
	}

	public void setIdentity( String identity ) {
		this.identity = identity;
	}

	/**
	 * 业务类型
	 */
	@Column( name = "CATALOG" )
	@Type( type = CustomEnumType.ENUM_TYPE, parameters = { @Parameter( name = "enumClass", value = "com.ylink.ylpay.common.project.mp.constant.ProductType" ) } )
	private ProductType productType;

	/**
	 * 交易类型名称
	 */
	@Column( name = "TYPE" )
	@Type( type = CustomEnumType.ENUM_TYPE, parameters = { @Parameter( name = "enumClass", value = "com.ylink.ylpay.common.project.mp.constant.OptType" ) } )
	private OptType optType;

	/**
	 * 备注
	 */
	@Column( name = "DESCRIPTION", length = 500 )
	private String description;

	// -- getters and setters --------------------------------------------------

	public void setDescription( String description ) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

	public ProductType getProductType() {
		return productType;
	}

	public void setProductType( ProductType productType ) {
		this.productType = productType;
	}

	public OptType getOptType() {
		return optType;
	}

	public void setOptType( OptType optType ) {
		this.optType = optType;
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