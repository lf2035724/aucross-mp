package com.ylink.ylpay.project.mp.cust.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.Type;

import com.google.code.lightssh.common.entity.Persistence;
import com.google.code.lightssh.common.model.Sequenceable;
import com.ylink.modules.orm.hibernate.type.CustomEnumType;
import com.ylink.ylpay.common.project.mp.constant.AuditStatus;
import com.ylink.ylpay.common.project.mp.constant.CustType;
import com.ylink.ylpay.common.project.mp.constant.MerchantStatus;
import com.ylink.ylpay.common.project.pay.constant.InvokeMode;
import com.ylink.ylpay.project.constants.EncodeRulesConstant;
import com.ylink.ylpay.project.mp.agency.entity.CustAgency;
import com.ylink.ylpay.project.mp.custbankcard.entity.CustBankcard;

/**
 * 商户实体.
 * 
 * @author 潘瑞峥
 * @date 2012-11-1
 */
@Entity
@Table( name = "T_CUST_MERCHANT" )
public class CustMerchant implements Persistence<String>, Sequenceable {

	private static final long serialVersionUID = -5768877506415698777L;

	@Id
	@GeneratedValue( generator = "system-uuid" )
	@GenericGenerator( name = "system-uuid", strategy = "uuid" )
	private String id;

	/** 商户编号. */
	private String code;

	/** 名称. */
	private String name;

	/** 链接. */
	private String url;

	/** 关联企业用户. */
	@ManyToOne( fetch = FetchType.EAGER )
	@JoinColumn( name = "CUST_ID" )
	@NotFound(action=NotFoundAction.IGNORE)
	private Enterprise cust;

	/** 关联客户银行卡. */
	@ManyToOne( fetch = FetchType.EAGER )
	@JoinColumn( name = "BANKCARD_ID" )
	private CustBankcard bankcard;

	/** 关联机构. */
	@ManyToOne( fetch = FetchType.LAZY )
	@JoinColumn( name = "AGENCY_ID" )
	private CustAgency agency;

	/** 状态. */
	@Type( type = CustomEnumType.ENUM_TYPE, parameters = { @Parameter( name = "enumClass", value = "com.ylink.ylpay.common.project.mp.constant.MerchantStatus" ) } )
	private MerchantStatus status;

	/** 审核状态. */
	@Column( name = "AUDIT_STATUS" )
	@Type( type = CustomEnumType.ENUM_TYPE, parameters = { @Parameter( name = "enumClass", value = "com.ylink.ylpay.common.project.mp.constant.AuditStatus" ) } )
	private AuditStatus auditStatus;

	/** 类型. */
	@Column( name = "TYPE" )
	@Type( type = CustomEnumType.ENUM_TYPE, parameters = { @Parameter( name = "enumClass", value = "com.ylink.ylpay.common.project.mp.constant.CustType" ) } )
	private CustType type;

	/** 数字证书. */
	@Lob
	@Column( name = "DIGITAL_CERTIFICATE" )
	private byte[] digitalCertificate;

	/** 数字证书更新时间. */
	@Temporal( TemporalType.TIMESTAMP )
	@Column( name = "CERT_UPDATE_TIME" )
	private Date certUpdateTime;

	/** 手续费计费方式: 线上、线下. */
	@Column( name = "FEE_INVOKE_MODE" )
	@Type( type = CustomEnumType.ENUM_TYPE, parameters = { @Parameter( name = "enumClass", value = "com.ylink.ylpay.common.project.pay.constant.InvokeMode" ) } )
	private InvokeMode feeInvokeMode;

	/** 银联mcc码 */
	@Column( name = "CUP_MCC_CODE" )
	private String CUPMCCCode;
	
	/** 银联商户号 */
	@Column( name = "CUP_MERCHANT_NO" )
	private String CUPMerchantNO;
	
	/** 银联商户简称 */
	@Column( name = "MER_ABBR" )
	private String merAbbr;
	
	/** 资金托管商户 */
	@Column( name = "ISFUNDMANAGE" )
	private String isFundManage;
	

	/** 备注. */
	private String description;
	
	/** 商户在银联的备案名称. */
	@Column( name = "CUP_MERCHANT_NAME" )
	private String CUPMerchantName;
	
	/** 商户在银联的商户类型（平台商或普通商户）.0普通类，1平台类 */
	@Column( name = "CUP_MERCHANT_TYPE" )
	@Type( type = CustomEnumType.ENUM_TYPE, parameters = { @Parameter( name = "enumClass", value = "com.ylink.ylpay.common.project.mp.constant.CUPMerchantType" ) } )
	private String CUPMerchantType;
	

	/** 关联的交易类型. */
	@OneToMany( cascade = CascadeType.ALL, mappedBy = "custMerchant", orphanRemoval = true )
	private List<CustMerchantBusiness> custMerchantBusinesses = new ArrayList<CustMerchantBusiness>();

	/** 关联的交易接口类型. */
	@OneToMany( cascade = CascadeType.ALL, mappedBy = "custMerchant", orphanRemoval = true )
	private List<MerchantProjectOrder> merchantProjectOrders = new ArrayList<MerchantProjectOrder>();
	
	@Override
	public String getIdentity() {
		return id;
	}

	public void setIdentity( String id ) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode( String code ) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName( String name ) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl( String url ) {
		this.url = url;
	}

	public Enterprise getCust() {
		return cust;
	}

	public void setCust( Enterprise cust ) {
		this.cust = cust;
	}

	public CustBankcard getBankcard() {
		return bankcard;
	}

	public void setBankcard( CustBankcard bankcard ) {
		this.bankcard = bankcard;
	}

	public CustAgency getAgency() {
		return agency;
	}

	public void setAgency( CustAgency agency ) {
		this.agency = agency;
	}

	public MerchantStatus getStatus() {
		return status;
	}

	public void setStatus( MerchantStatus status ) {
		this.status = status;
	}

	public AuditStatus getAuditStatus() {
		return auditStatus;
	}

	public void setAuditStatus( AuditStatus auditStatus ) {
		this.auditStatus = auditStatus;
	}

	public CustType getType() {
		return type;
	}

	public void setType( CustType type ) {
		this.type = type;
	}

	public byte[] getDigitalCertificate() {
		return digitalCertificate;
	}

	public void setDigitalCertificate( byte[] digitalCertificate ) {
		this.digitalCertificate = digitalCertificate;
	}

	public Date getCertUpdateTime() {
		return certUpdateTime;
	}

	public void setCertUpdateTime( Date certUpdateTime ) {
		this.certUpdateTime = certUpdateTime;
	}

	public InvokeMode getFeeInvokeMode() {
		return feeInvokeMode;
	}

	public void setFeeInvokeMode( InvokeMode feeInvokeMode ) {
		this.feeInvokeMode = feeInvokeMode;
	}

	public List<CustMerchantBusiness> getCustMerchantBusinesses() {
		return custMerchantBusinesses;
	}

	public void setCustMerchantBusinesses( List<CustMerchantBusiness> custMerchantBusinesses ) {
		this.custMerchantBusinesses = custMerchantBusinesses;
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

	@Override
	public String getSequenceKey() {
		return EncodeRulesConstant.CUST_CODE_PREFIX;
	}

	@Override
	public int getSequenceStep() {
		return EncodeRulesConstant.DEFAULT_SPIKE;
	}

	@Override
	public int getSequenceLength() {
		return EncodeRulesConstant.CUST_CODE_LENGTH;
	}

	public void addCustMerchantBusiness( CustMerchantBusiness custMerchantBusiness ) {
		custMerchantBusiness.setCustMerchant( this );
		this.custMerchantBusinesses.add( custMerchantBusiness );
	}
	
	public void addMerchantProjectOrder( MerchantProjectOrder merchantProjectOrder ) {
		merchantProjectOrder.setCustMerchant(this);
		this.merchantProjectOrders.add( merchantProjectOrder );
	}

	public String getCUPMerchantNO() {
		return CUPMerchantNO;
	}

	public void setCUPMerchantNO(String cUPMerchantNO) {
		CUPMerchantNO = cUPMerchantNO;
	}

	public String getCUPMCCCode() {
		return CUPMCCCode;
	}

	public void setCUPMCCCode(String cUPMCCCode) {
		CUPMCCCode = cUPMCCCode;
	}

	public String getMerAbbr() {
		return merAbbr;
	}

	public void setMerAbbr(String merAbbr) {
		this.merAbbr = merAbbr;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<MerchantProjectOrder> getMerchantProjectOrders() {
		return merchantProjectOrders;
	}

	public void setMerchantProjectOrders(
			List<MerchantProjectOrder> merchantProjectOrders) {
		this.merchantProjectOrders = merchantProjectOrders;
	}

	public String getIsFundManage() {
		return isFundManage;
	}

	public void setIsFundManage(String isFundManage) {
		this.isFundManage = isFundManage;
	}

	public String getCUPMerchantName() {
		return CUPMerchantName;
	}

	public void setCUPMerchantName(String cUPMerchantName) {
		CUPMerchantName = cUPMerchantName;
	}

	public String getCUPMerchantType() {
		return CUPMerchantType;
	}

	public void setCUPMerchantType(String cUPMerchantType) {
		CUPMerchantType = cUPMerchantType;
	}
}