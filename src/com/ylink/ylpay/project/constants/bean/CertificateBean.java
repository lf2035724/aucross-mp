package com.ylink.ylpay.project.constants.bean;

import java.io.Serializable;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Date;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.ylink.ylpay.project.constants.CertificateConstant;

/**
 * 数字证书.
 * 
 * @author 潘瑞峥
 * @date 2013-4-19
 */
public class CertificateBean implements Serializable {

	private static final long serialVersionUID = 6388799442124468188L;

	/** 证书序列号. */
	private Integer serialNumber;

	/** KeyStore密码. */
	private String storePassword = CertificateConstant.KEY_STORE_PASSWORD;

	/** Key别名. */
	private String keyAlias = CertificateConstant.KEY_ALIAS;

	/** 加密算法（eg.RSA）. */
	private String keyAlgorithm = CertificateConstant.CERTIFICATE_ALGORITHM;

	/** Key保护密码. */
	private String keyPassword = CertificateConstant.KEY_PASSWORD;

	/** 密钥长度. */
	private Integer keySize;

	/** 国家代码（eg.CN）. */
	private String merchantC = CertificateConstant.COUNTRY_CODE;

	/** 名称. */
	private String merchantCn;

	/** 州或省份名称. */
	private String merchantSt;

	/** 城市或区域名称. */
	private String merchantL;

	/** 组织名称. */
	private String merchantO;

	/** 组织单位名称. */
	private String merchantOu;

	/** 私钥 - 证件类型. */
	private String privateKeyFileType = CertificateConstant.JKS_TYPE;

	/** 公钥 - 证件类型. */
	private String publicKeyFileType = CertificateConstant.CER_TYPE;

	/** 有效期开始时间. */
	private Date validityTimeBegin = new Date();

	/** 有效年数. */
	private int validityYear = CertificateConstant.VALID_TERM_YEAR;

	/** 颁发者. */
	private String issuerDN = CertificateConstant.ISSUER_DN;

	/** 接受者. */
	private String subjectDN;

	/** 公钥. */
	private PublicKey publicKey;

	/** 私钥. */
	private PrivateKey privateKey;

	/**
	 * 让构造器自动生成要素.
	 * 
	 * @param merchantCn
	 * @throws Exception
	 */
	public CertificateBean( String merchantCn ) throws Exception {
		this.serialNumber = Integer.valueOf( RandomStringUtils.randomNumeric( CertificateConstant.SN_COUNT ) );
		this.merchantCn = merchantCn;

		KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance( this.keyAlgorithm );
		KeyPair keyPair = keyPairGenerator.generateKeyPair();
		this.publicKey = keyPair.getPublic();
		this.privateKey = keyPair.getPrivate();
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

	public String getKeyAlias() {
		return keyAlias;
	}

	public String getKeyAlgorithm() {
		return keyAlgorithm;
	}

	public String getKeyPassword() {
		return keyPassword;
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

	public String getPrivateKeyFileType() {
		return privateKeyFileType;
	}

	public String getPublicKeyFileType() {
		return publicKeyFileType;
	}

	public Date getValidityTimeBegin() {
		return validityTimeBegin;
	}

	public void setValidityTimeBegin( Date validityTimeBegin ) {
		this.validityTimeBegin = validityTimeBegin;
	}

	public int getValidityYear() {
		return validityYear;
	}

	public void setValidityYear( int validityYear ) {
		this.validityYear = validityYear;
	}

	public String getIssuerDN() {
		return issuerDN;
	}

	public void setIssuerDN( String issuerDN ) {
		this.issuerDN = issuerDN;
	}

	public PublicKey getPublicKey() {
		return publicKey;
	}

	public void setPublicKey( PublicKey publicKey ) {
		this.publicKey = publicKey;
	}

	public PrivateKey getPrivateKey() {
		return privateKey;
	}

	public void setPrivateKey( PrivateKey privateKey ) {
		this.privateKey = privateKey;
	}

	public String getSubjectDN() {
		StringBuffer buf = new StringBuffer();
		buf.append( "C" ).append( "=" ).append( this.merchantC );
		buf.append( "," ).append( "CN" ).append( "=" ).append( this.merchantCn );
		this.subjectDN = buf.toString();
		return subjectDN;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString( this, ToStringStyle.MULTI_LINE_STYLE );
	}

}