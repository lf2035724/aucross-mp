package com.ylink.ylpay.project.mp.cust.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.ylink.ylpay.project.constants.entity.BaseEntity;

/**
 * 商户证件.
 * 
 * @author 潘瑞峥
 * @date 2013-4-19
 */
@Entity
@Table( name = "T_CUST_MERCHANT_CERT" )
public class CustMerchantCert extends BaseEntity {

	private static final long serialVersionUID = 2608787860069229960L;

	/** 对应的商户. */
	@ManyToOne( fetch = FetchType.LAZY )
	@JoinColumn( name = "MERCHANT_ID" )
	private CustMerchant custMerchant;

	/** 证书序列号. */
	@Column( name = "SERIAL_NUMBER" )
	private Integer serialNumber;

	/** KeyStore密码. */
	@Column( name = "STORE_PASSWORD" )
	private String storePassword;

	/** Key别名. */
	@Column( name = "KEY_ALIAS" )
	private String keyAlias;

	/** 加密算法（eg.RSA）. */
	@Column( name = "KEY_ALGORITHM" )
	private String keyAlgorithm;

	/** Key保护密码. */
	@Column( name = "KEY_PASSWORD" )
	private String keyPassword;

	/** 密钥长度. */
	@Column( name = "KEY_SIZE" )
	private Integer keySize;

	/** 国家代码（eg.CN）. */
	@Column( name = "MERCHANT_C" )
	private String merchantC;

	/** 名称. */
	@Column( name = "MERCHANT_CN" )
	private String merchantCn;

	/** 州或省份名称. */
	@Column( name = "MERCHANT_ST" )
	private String merchantSt;

	/** 城市或区域名称. */
	@Column( name = "MERCHANT_L" )
	private String merchantL;

	/** 组织名称. */
	@Column( name = "MERCHANT_O" )
	private String merchantO;

	/** 组织单位名称. */
	@Column( name = "MERCHANT_OU" )
	private String merchantOu;

	/** 私钥 - 证件文件. */
	@Lob
	@Column( name = "PRIVATE_FILE" )
	private byte[] privateKeyFile;

	/** 私钥 - 证件类型. */
	@Column( name = "PRIVATE_FILE_TYPE" )
	private String privateKeyFileType;

	/** 公钥 - 证件文件. */
	@Lob
	@Column( name = "PUBLIC_FILE" )
	private byte[] publicKeyFile;

	/** 公钥 - 证件类型. */
	@Column( name = "PUBLIC_FILE_TYPE" )
	private String publicKeyFileType;

	/** 有效期开始时间. */
	@Temporal( TemporalType.TIMESTAMP )
	@Column( name = "VALIDITY_TIME_BEGIN" )
	private Date validityTimeBegin;

	/** 有效年数. */
	@Column( name = "VALIDITY_YEAR" )
	private Integer validityYear;

	/** 创建时间. */
	@Temporal( TemporalType.TIMESTAMP )
	@Column( name = "CREATED_TIME" )
	private Date createdTime = new Date();

	/** 修改时间. */
	@Temporal( TemporalType.TIMESTAMP )
	@Column( name = "UPDATED_TIME" )
	private Date updatedTime;

	/** 备注. */
	private String description;

	public CustMerchantCert() {
	}

	public CustMerchant getCustMerchant() {
		return custMerchant;
	}

	public void setCustMerchant( CustMerchant custMerchant ) {
		this.custMerchant = custMerchant;
	}

	public Integer getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber( Integer serialNumber ) {
		this.serialNumber = serialNumber;
	}

	public String getStorePassword() {
		return storePassword;
	}

	public void setStorePassword( String storePassword ) {
		this.storePassword = storePassword;
	}

	public String getKeyAlias() {
		return keyAlias;
	}

	public void setKeyAlias( String keyAlias ) {
		this.keyAlias = keyAlias;
	}

	public String getKeyAlgorithm() {
		return keyAlgorithm;
	}

	public void setKeyAlgorithm( String keyAlgorithm ) {
		this.keyAlgorithm = keyAlgorithm;
	}

	public String getKeyPassword() {
		return keyPassword;
	}

	public void setKeyPassword( String keyPassword ) {
		this.keyPassword = keyPassword;
	}

	public Integer getKeySize() {
		return keySize;
	}

	public void setKeySize( Integer keySize ) {
		this.keySize = keySize;
	}

	public String getMerchantC() {
		return merchantC;
	}

	public void setMerchantC( String merchantC ) {
		this.merchantC = merchantC;
	}

	public String getMerchantCn() {
		return merchantCn;
	}

	public void setMerchantCn( String merchantCn ) {
		this.merchantCn = merchantCn;
	}

	public String getMerchantSt() {
		return merchantSt;
	}

	public void setMerchantSt( String merchantSt ) {
		this.merchantSt = merchantSt;
	}

	public String getMerchantL() {
		return merchantL;
	}

	public void setMerchantL( String merchantL ) {
		this.merchantL = merchantL;
	}

	public String getMerchantO() {
		return merchantO;
	}

	public void setMerchantO( String merchantO ) {
		this.merchantO = merchantO;
	}

	public String getMerchantOu() {
		return merchantOu;
	}

	public void setMerchantOu( String merchantOu ) {
		this.merchantOu = merchantOu;
	}

	public byte[] getPrivateKeyFile() {
		return privateKeyFile;
	}

	public void setPrivateKeyFile( byte[] privateKeyFile ) {
		this.privateKeyFile = privateKeyFile;
	}

	public String getPrivateKeyFileType() {
		return privateKeyFileType;
	}

	public void setPrivateKeyFileType( String privateKeyFileType ) {
		this.privateKeyFileType = privateKeyFileType;
	}

	public byte[] getPublicKeyFile() {
		return publicKeyFile;
	}

	public void setPublicKeyFile( byte[] publicKeyFile ) {
		this.publicKeyFile = publicKeyFile;
	}

	public String getPublicKeyFileType() {
		return publicKeyFileType;
	}

	public void setPublicKeyFileType( String publicKeyFileType ) {
		this.publicKeyFileType = publicKeyFileType;
	}

	public Date getValidityTimeBegin() {
		return validityTimeBegin;
	}

	public void setValidityTimeBegin( Date validityTimeBegin ) {
		this.validityTimeBegin = validityTimeBegin;
	}

	public Integer getValidityYear() {
		return validityYear;
	}

	public void setValidityYear( Integer validityYear ) {
		this.validityYear = validityYear;
	}

	public Date getCreatedTime() {
		return createdTime;
	}

	public Date getUpdatedTime() {
		return updatedTime;
	}

	public void setUpdatedTime( Date updatedTime ) {
		this.updatedTime = updatedTime;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription( String description ) {
		this.description = description;
	}

}