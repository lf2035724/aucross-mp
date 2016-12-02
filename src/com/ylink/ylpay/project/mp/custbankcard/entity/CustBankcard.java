package com.ylink.ylpay.project.mp.custbankcard.entity;

import java.util.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.Type;

import com.google.code.lightssh.common.entity.Persistence;
import com.google.code.lightssh.common.model.Sequenceable;
import com.ylink.modules.orm.hibernate.type.CustomEnumType;
import com.ylink.ylpay.common.project.channel.constant.LinkMethod;
import com.ylink.ylpay.common.project.mp.constant.AuditBindingStatus;
import com.ylink.ylpay.common.project.mp.constant.AuditStatus;
import com.ylink.ylpay.project.constants.EncodeRulesConstant;
import com.ylink.ylpay.project.mp.basic.entity.AccountType;

/**
 * 客户银行卡
 * 
 * @author 张磊
 * 
 */
@Entity
@Table( name = "T_CUST_BANK_CARD" )
public class CustBankcard implements Persistence<String>, Sequenceable, Cloneable {

	private static final long serialVersionUID = 3776928789103941491L;

	@Id
	private String id;

	/**
	 * 客户ID
	 */
	@Column( name = "CUST_ID", unique = false, length = 20 )
	private String custId;

	/**
	 * 关联帐户类型
	 */
	@ManyToOne( fetch = FetchType.EAGER )
	@JoinColumn( name = "ACCOUNT_TYPE" )
	private AccountType accountType;

	/**
	 * 银行户名
	 */
	@Column( name = "CARD_NAME", unique = false, length = 50 )
	private String cardName;

	/**
	 * 银行卡号
	 */
	@Column( name = "CARD_NO", unique = false, length = 30 )
	private String cardNo;

	/**
	 * 银行卡类型
	 */
	@Column( name = "CARD_TYPE", unique = false )
	private String cardType;

	/**
	 * 行别
	 */
	@Column( name = "BANK_TYPE", unique = false, length = 16 )
	private String bankType;

	/**
	 * 创建时间
	 */
	@Column( name = "CREATE_DATE", columnDefinition = "Date" )
	@Temporal( TemporalType.TIMESTAMP )
	private Date createDate;

	/**
	 * 更新时间
	 */
	@Column( name = "UPDATE_TIME", columnDefinition = "Date" )
	@Temporal( TemporalType.TIMESTAMP )
	private Date updateTime;

	/**
	 * 是否发送监管行
	 */
	@Column( name = "IS_SEND", unique = false )
	private String isSend;

	/**
	 * 状态
	 */
	@Column( name = "STATUS", unique = false )
	private String status;

	/**
	 * 签代扣协议
	 */
	@Column( name = "IS_PROTOCOL", unique = false )
	private String isProtocol;

	/**
	 * 认证状态
	 */
	@Column( name = "IS_CERT", unique = false )
	private String isCert;

	/**
	 * 授权号
	 */
	@Column( name = "AUTH_NO", unique = false, length = 50 )
	private String authNo;

	/**
	 * 创建标志
	 */
	@Column( name = "CREATE_TYPE", unique = false )
	private String createType;

	/** 审核状态. */
	@Column( name = "AUDIT_STATUS" )
	@Type( type = CustomEnumType.ENUM_TYPE, parameters = { @Parameter( name = "enumClass", value = "com.ylink.ylpay.common.project.mp.constant.AuditStatus" ) } )
	private AuditStatus auditStatus;
	
	/** 联行号. */
	@Column(name="CONTACT_BANK_NO",length=20)
	private String contactBankNo;
	/** 网店名称. */
	@Column(name="BRANCH_NAME",length=100)
	private String  branchName;
	
	@Column(name="AUDIT_BINDING_STATUS")
	@Type(type = CustomEnumType.ENUM_TYPE , parameters = { @Parameter( name = "enumClass" , value="com.ylink.ylpay.common.project.mp.constant.AuditBindingStatus")})
	private AuditBindingStatus auditBindingStatus;
	
	/**
	 * 一级地理区域
	 */
	@Column( name = "FIRST_GEO", unique = false )
	private String firstGeo;
	/**
	 * 原始协议状态
	 */
	@Column( name = "ORIGINAL_PROTOCOL", unique = false )
	private String originalProtocol;
	/**
	 * 原始认证状态
	 */
	@Column( name = "ORIGINAL_CERT", unique = false )
	private String originalCert;
//	/**
//	 * 绑卡的类型 0快捷 1网关
//	 */
//	@Column( name = "BIND_CARD_TYPE", unique = false )
//	private String bindCardType;
	
	/**
	 * 二级地理区域
	 */
	@Column( name = "SECOND_GEO", unique = false )
	private String secondGeo;
	/**
	 * 绑定来源
	 */
	@Column( name = "BIND_SOURCE")
	private String bindSource;
	
	/**
	 * 绑卡渠道（银联用，区分是普通银行绑卡还是银联绑卡）
	 */
	@Column( name = "BIND_CANAL")
	private String bindCanal = LinkMethod.DIRECT_BANK.getValue();
	
	@Transient
	private String merchantCode;
	@Transient
	private String merchantId;
	
	
	public String getContactBankNo() {
		return contactBankNo;
	}

	public void setContactBankNo(String contactBankNo) {
		this.contactBankNo = contactBankNo;
	}

	public String getBranchName() {
		return branchName;
	}

	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}

	public String getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(String merchantId) {
		this.merchantId = merchantId;
	}

	public String getMerchantCode() {
		return merchantCode;
	}

	public void setMerchantCode(String merchantCode) {
		this.merchantCode = merchantCode;
	}

	public String getCustId() {
		return custId;
	}

	public void setCustId( String custId ) {
		this.custId = custId;
	}

	public AccountType getAccountType() {
		return accountType;
	}

	public void setAccountType( AccountType accountType ) {
		this.accountType = accountType;
	}

	public String getCardName() {
		return cardName;
	}

	public void setCardName( String cardName ) {
		this.cardName = cardName;
	}

	public String getCardNo() {
		return cardNo;
	}

	public void setCardNo( String cardNo ) {
		this.cardNo = cardNo;
	}

	public String getCardType() {
		return cardType;
	}

	public void setCardType( String cardType ) {
		this.cardType = cardType;
	}

	public String getBankType() {
		return bankType;
	}

	public void setBankType( String bankType ) {
		this.bankType = bankType;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate( Date createDate ) {
		this.createDate = createDate;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime( Date updateTime ) {
		this.updateTime = updateTime;
	}

	public String getIsSend() {
		return isSend;
	}

	public void setIsSend( String isSend ) {
		this.isSend = isSend;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus( String status ) {
		this.status = status;
	}

	public String getIsProtocol() {
		return isProtocol;
	}

	public void setIsProtocol( String isProtocol ) {
		this.isProtocol = isProtocol;
	}

	public String getIsCert() {
		return isCert;
	}

	public void setIsCert( String isCert ) {
		this.isCert = isCert;
	}

	public String getAuthNo() {
		return authNo;
	}

	public void setAuthNo( String authNo ) {
		this.authNo = authNo;
	}

	public String getCreateType() {
		return createType;
	}

	public void setCreateType( String createType ) {
		this.createType = createType;
	}

	public AuditStatus getAuditStatus() {
		return auditStatus;
	}

	public void setAuditStatus( AuditStatus auditStatus ) {
		this.auditStatus = auditStatus;
	}

	@Override
	public String getIdentity() {
		return id;
	}

	public void setIdentity( String id ) {
		this.id = id;
	}

	@Override
	public boolean isInsert() {
		return StringUtils.isEmpty( id );
	}

	@Override
	public void postInsertFailure() {
	}

	@Override
	public void preInsert() {
		this.id = UUID.randomUUID().toString();
	}

	@Override
	public String getSequenceKey() {
		return EncodeRulesConstant.CUST_CODE_PREFIX;
	}

	@Override
	public int getSequenceLength() {

		return EncodeRulesConstant.CUST_CODE_LENGTH;
	}
	
	@Override
	public int getSequenceStep() {
		return EncodeRulesConstant.DEFAULT_SPIKE;
	}
	
	/**
	 * @return the auditBindingStatus
	 */
	public AuditBindingStatus getAuditBindingStatus() {
		return auditBindingStatus;
	}

	/**
	 * @param auditBindingStatus the auditBindingStatus to set
	 */
	public void setAuditBindingStatus(AuditBindingStatus auditBindingStatus) {
		this.auditBindingStatus = auditBindingStatus;
	}
	
	public String getFirstGeo() {
		return firstGeo;
	}

	public void setFirstGeo(String firstGeo) {
		this.firstGeo = firstGeo;
	}

	public String getSecondGeo() {
		return secondGeo;
	}

	public void setSecondGeo(String secondGeo) {
		this.secondGeo = secondGeo;
	}
	public String getOriginalProtocol() {
		return originalProtocol;
	}

	public void setOriginalProtocol(String originalProtocol) {
		this.originalProtocol = originalProtocol;
	}

	public String getOriginalCert() {
		return originalCert;
	}

	public void setOriginalCert(String originalCert) {
		this.originalCert = originalCert;
	}

	public CustBankcard clone() {
		try {
			return ( CustBankcard ) super.clone();
		} catch ( CloneNotSupportedException e ) {
			return null;
		}
	}

	public String getBindSource() {
		return bindSource;
	}

	public void setBindSource(String bindSource) {
		this.bindSource = bindSource;
	}

	public String getBindCanal() {
		return bindCanal;
	}

	public void setBindCanal(String bindCanal) {
		this.bindCanal = bindCanal;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

//	public String getBindCardType() {
//		return bindCardType;
//	}
//
//	public void setBindCardType(String bindCardType) {
//		this.bindCardType = bindCardType;
//	}
}