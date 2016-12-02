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
import javax.persistence.Transient;

import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.Type;

import com.google.code.lightssh.project.geo.entity.GeographicBoundary;
import com.ylink.modules.orm.hibernate.type.CustomEnumType;
import com.ylink.ylpay.common.project.mp.constant.AuditStatus;
import com.ylink.ylpay.common.project.mp.constant.AuthApplicantType;
import com.ylink.ylpay.common.project.mp.constant.CertificationType;
import com.ylink.ylpay.common.project.mp.constant.EnterLinkStatus;
import com.ylink.ylpay.common.project.mp.constant.MerchantStatus;
import com.ylink.ylpay.common.project.mp.constant.RegisteredType;
import com.ylink.ylpay.common.project.mp.constant.WhetherStatus;
import com.ylink.ylpay.project.constants.EncodeRulesConstant;

/**
 * 企业客户.
 * 
 * certStatus字段对于企业客户来说, 已没意义.
 * 
 * @author 潘瑞峥(修改)
 * @date 2012-12-26
 */
@Entity
@Table( name = "T_CUST_ENTERPRISE" )
public class Enterprise extends Customer implements Cloneable {

	private static final long serialVersionUID = -7936942580247525393L;

	/** 企业简称. */
	@Column( name = "SHORT_NAME" )
	private String shortName;

	/** 英文名称. */
	@Column( name = "EN_NAME" )
	private String enName;

	/** 简介. */
	@Column( name = "SUMMARY" )
	private String summary;

	/** 注册类型. */
	@Column( name = "REGISTERED_TYPE" )
	@Type( type = CustomEnumType.ENUM_TYPE, parameters = { @Parameter( name = "enumClass", value = "com.ylink.ylpay.common.project.mp.constant.RegisteredType" ) } )
	private RegisteredType registeredType;

	/** 开户科目. */
	@Column( name = "SUBJECT" )
	private String subject;

	/** 开户科目. */
	@Transient
	private String[] subjects;

	/** 审核状态. */
	@Column( name = "AUDIT_STATUS" )
	@Type( type = CustomEnumType.ENUM_TYPE, parameters = { @Parameter( name = "enumClass", value = "com.ylink.ylpay.common.project.mp.constant.AuditStatus" ) } )
	private AuditStatus auditStatus;

	/** 联系电话. */
	@Column( name = "PHONE" )
	private String phone;

	/** 传真. */
	@Column( name = "FAX" )
	private String fax;

	/** 注册地址及邮编. */
	@Column( name = "ADDR_REG" )
	private String addrReg;

	/** 通讯地址及邮编. */
	@Column( name = "ADDR_CONTACT" )
	private String addrContact;

	/** 企业电子邮箱. */
	@Column( name = "EMAIL" )
	private String email;

	/** 有权确认人姓名. */
	@Column( name = "CONFIRM_PERSON_NAME" )
	private String confirmPersonName;

	/** 移动电话. */
	@Column( name = "MOBILE" )
	private String mobile;

	/** 办公电话. */
	@Column( name = "OFFICE_PHONE" )
	private String officePhone;

	/** 银行卡行别. */
	@Column( name = "BANK_TYPE" )
	private String bankType;

	/** 银行卡账号. */
	@Column( name = "ACCOUNT_NO" )
	private String accountNo;

	/** 营业执照 - 文件ID. */
	@Column( name = "LICENSE_ID" )
	private String licenseId;

	/** 营业执照 - 注册号. */
	@Column( name = "LICENSE_NO" )
	private String licenseNo;

	/** 营业执照 - 法人. */
	@Column( name = "LICENSE_LEGAL_PERSON" )
	private String licenseLegalPerson;

	/** 营业执照 - 注册资本. */
	@Column( name = "LICENSE_REG_CAPITAL" )
	private String licenseRegCapital;

	/** 营业执照 - 公司类型. */
	@Column( name = "LICENSE_LEGAL_ENT_TYPE" )
	private String licenseLegalEntType;

	/** 营业执照 - 经营范围. */
	@Column( name = "LICENSE_LEGAL_SCOPE" )
	private String licenseLegalScope;

	/** 营业执照 - 成立日期. */
	@Column( name = "LICENSE_LEGAL_FOUND_DATE" )
	private String licenseLegalFoundDate;

	/** 营业执照 - 营业期限. */
	@Column( name = "LICENSE_LEGAL_VALID_TERM" )
	private String licenseLegalValidTerm;

	/** 组织机构 - 文件ID. */
	@Column( name = "ORG_ID" )
	private String orgId;

	/** 组织机构 - 代码. */
	@Column( name = "ORG_NO" )
	private String orgNo;

	/** 组织机构 - 有效期. */
	@Column( name = "ORG_VALID_TERM" )
	private String orgValidTerm;

	/** 地税 - 文件ID. */
	@Column( name = "LANDTAX_ID" )
	private String landtaxId;

	/** 地税 - 号码. */
	@Column( name = "LANDTAX_NO" )
	private String landtaxNo;

	/** 国税 - 文件ID. */
	@Column( name = "NATIONALTAX_ID" )
	private String nationaltaxId;

	/** 国税 - 号码. */
	@Column( name = "NATIONALTAX_NO" )
	private String nationaltaxNo;

	/** 法人 - 文件ID. */
	@Column( name = "LEGALPERSON_ID" )
	private String legalpersonId;

	/** 法人 - 姓名. */
	@Column( name = "LEGALPERSON_NAME" )
	private String legalpersonName;

	/** 法人 - 证件类型. */
	@Column( name = "LEGALPERSON_CERT_TYPE" )
	private String legalpersonCertType;

	/** 法人 - 证件号码. */
	@Column( name = "LEGALPERSON_CERT_NO" )
	private String legalpersonCertNo;

	/** 财务负责人 - 姓名. */
	@Column( name = "FINANCE_NAME" )
	private String financeName;

	/** 财务负责人 - 证件类型. */
	@Column( name = "FINANCE_CERT_TYPE" )
	private String financeCertType;

	/** 财务负责人 - 证件号码. */
	@Column( name = "FINANCE_CERT_NO" )
	private String financeCertNo;

	/**
	 * ################################################################<br>
	 * 在开发门户企业客户注册时, 新加字段.<br>
	 * 
	 * @author 潘瑞峥
	 * @date 2013-03-26<br>
	 *       ################################################################
	 */

	/** 营业执照 - 是否长期. */
	@Column( name = "LICENSE_IS_LONGTERM" )
	@Type( type = CustomEnumType.ENUM_TYPE, parameters = { @Parameter( name = "enumClass", value = "com.ylink.ylpay.common.project.mp.constant.WhetherStatus" ) } )
	private WhetherStatus licenseIsLongterm;

	/** 营业执照 - 所在地. */
	@ManyToOne( fetch = FetchType.LAZY )
	@JoinColumn( name = "LICENSE_GEO_ID" )
	private GeographicBoundary licenseGeo;

	/** 营业执照 - 住所. */
	@Column( name = "LICENSE_RESIDENCE" )
	private String licenseResidence;

	/** 法人 - 背面文件ID. */
	@Column( name = "LEGALPERSON_BACK_ID" )
	private String legalpersonBackId;

	/** 法人 - 归属地. */
	@ManyToOne( fetch = FetchType.LAZY )
	@JoinColumn( name = "LEGALPERSON_GEO_ID" )
	private GeographicBoundary legalpersonGeo;

	/** 法人 - 手机号. */
	@Column( name = "LEGALPERSON_MOBILE" )
	private String legalpersonMobile;

	/** 实名认证申请人类型. */
	@Column( name = "AUTH_APPLICANT_TYPE" )
	@Type( type = CustomEnumType.ENUM_TYPE, parameters = { @Parameter( name = "enumClass", value = "com.ylink.ylpay.common.project.mp.constant.AuthApplicantType" ) } )
	private AuthApplicantType authApplicantType;

	/** 代理人 - 文件ID. */
	@Column( name = "AGENT_ID" )
	private String agentId;

	/** 代理人 - 背面文件ID. */
	@Column( name = "AGENT_BACK_ID" )
	private String agentBackId;

	/** 代理人 - 归属地. */
	@ManyToOne( fetch = FetchType.LAZY )
	@JoinColumn( name = "AGENT_GEO_ID" )
	private GeographicBoundary agentGeo;

	/** 代理人 - 姓名. */
	@Column( name = "AGENT_NAME" )
	private String agentName;

	/** 代理人 - 证件类型. */
	@Column( name = "AGENT_CERT_TYPE" )
	@Type( type = CustomEnumType.ENUM_TYPE, parameters = { @Parameter( name = "enumClass", value = "com.ylink.ylpay.common.project.mp.constant.CertificationType" ) } )
	private CertificationType agentCertType;

	/** 代理人 - 证件号码. */
	@Column( name = "AGENT_CERT_NO" )
	private String agentCertNo;

	/** 代理人 - 手机号. */
	@Column( name = "AGENT_MOBILE" )
	private String agentMobile;

	/** 代理人 - 企业委托书文件ID. */
	@Column( name = "COMPANY_POA_ID" )
	private String companyPoaId;

	/** 验证金额(分). */
	@Column( name = "MONEY_VERIFY" )
	private Long moneyVerify;

	/** 验证金额失败次数. */
	@Column( name = "MONEY_FAILURE_COUNT" )
	private Long moneyFailureCount;

	/** 审核失败描述. */
	@Column( name = "AUDIT_FAILURE_DESCRIPTION" )
	private String auditFailureDescription;
	/**
	 * ################################################################<br>
	 * 在开发门户企业客户注册时, 新加字段.<br>
	 * 
	 * @author YangHan
	 * @date 2013-04-12<br>
	 *       ################################################################
	 */
	/** 审核批次号 */
	@Column( name = "BATCH_NO" )
	private Integer batchNo;
	
	/** 是否开通企业通 默认为
	 * 0：未开通	1：已开通
	 */
	@Column( name = "IF_OPEN_ENTER_LINK" )
	private String ifOpenEnterLink;
	
	/** 
	 * 企业通开通时间
	 */
	@Column( name = "OPEN_ENTER_LINK_TIME", columnDefinition = "DATE" )
	@Temporal( TemporalType.TIMESTAMP )
	private Date openEnterLinkTime;
	
	/** 
	 * 企业通状态（1：正常，2：冻结）
	 */
	@Column( name = "ENTER_LINK_STATUS" )
	@Type( type = CustomEnumType.ENUM_TYPE, parameters = { @Parameter( name = "enumClass", value = "com.ylink.ylpay.common.project.mp.constant.EnterLinkStatus" ) } )
	private EnterLinkStatus enterLinkStatus;
	
	/**
	 * 是否供应链企业
	 */
	@Column( name = "ISSUPPLY" )
	private String isSupply;

	@Override
	public String getSequenceKey() {
		return EncodeRulesConstant.CUSTOMER_ENTERPRISE_PREFIX;
	}

	/**
	 * 状态是否有效
	 */
	public boolean isEffective() {
		return MerchantStatus.EFFECTIVE.getValue().equals( super.getStatus() );
	}

	public Enterprise clone() {
		return ( Enterprise ) super.clone();
	}

	public String getShortName() {
		return shortName;
	}

	public void setShortName( String shortName ) {
		this.shortName = shortName;
	}

	public String getEnName() {
		return enName;
	}

	public void setEnName( String enName ) {
		this.enName = enName;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary( String summary ) {
		this.summary = summary;
	}

	public RegisteredType getRegisteredType() {
		return registeredType;
	}

	public void setRegisteredType( RegisteredType registeredType ) {
		this.registeredType = registeredType;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject( String subject ) {
		this.subject = subject;
	}

	public String[] getSubjects() {
		return subjects;
	}

	public void setSubjects( String[] subjects ) {
		this.subjects = subjects;
	}

	public AuditStatus getAuditStatus() {
		return auditStatus;
	}

	public void setAuditStatus( AuditStatus auditStatus ) {
		this.auditStatus = auditStatus;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone( String phone ) {
		this.phone = phone;
	}

	public String getFax() {
		return fax;
	}

	public void setFax( String fax ) {
		this.fax = fax;
	}

	public String getAddrReg() {
		return addrReg;
	}

	public void setAddrReg( String addrReg ) {
		this.addrReg = addrReg;
	}

	public String getAddrContact() {
		return addrContact;
	}

	public void setAddrContact( String addrContact ) {
		this.addrContact = addrContact;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail( String email ) {
		this.email = email;
	}

	public String getConfirmPersonName() {
		return confirmPersonName;
	}

	public void setConfirmPersonName( String confirmPersonName ) {
		this.confirmPersonName = confirmPersonName;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile( String mobile ) {
		this.mobile = mobile;
	}

	public String getOfficePhone() {
		return officePhone;
	}

	public void setOfficePhone( String officePhone ) {
		this.officePhone = officePhone;
	}

	public String getBankType() {
		return bankType;
	}

	public void setBankType( String bankType ) {
		this.bankType = bankType;
	}

	public String getAccountNo() {
		return accountNo;
	}

	public void setAccountNo( String accountNo ) {
		this.accountNo = accountNo;
	}

	public String getLicenseId() {
		return licenseId;
	}

	public void setLicenseId( String licenseId ) {
		this.licenseId = licenseId;
	}

	public String getLicenseNo() {
		return licenseNo;
	}

	public void setLicenseNo( String licenseNo ) {
		this.licenseNo = licenseNo;
	}

	public String getLicenseLegalPerson() {
		return licenseLegalPerson;
	}

	public void setLicenseLegalPerson( String licenseLegalPerson ) {
		this.licenseLegalPerson = licenseLegalPerson;
	}

	public String getLicenseRegCapital() {
		return licenseRegCapital;
	}

	public void setLicenseRegCapital( String licenseRegCapital ) {
		this.licenseRegCapital = licenseRegCapital;
	}

	public String getLicenseLegalEntType() {
		return licenseLegalEntType;
	}

	public void setLicenseLegalEntType( String licenseLegalEntType ) {
		this.licenseLegalEntType = licenseLegalEntType;
	}

	public String getLicenseLegalScope() {
		return licenseLegalScope;
	}

	public void setLicenseLegalScope( String licenseLegalScope ) {
		this.licenseLegalScope = licenseLegalScope;
	}

	public String getLicenseLegalFoundDate() {
		return licenseLegalFoundDate;
	}

	public void setLicenseLegalFoundDate( String licenseLegalFoundDate ) {
		this.licenseLegalFoundDate = licenseLegalFoundDate;
	}

	public String getLicenseLegalValidTerm() {
		return licenseLegalValidTerm;
	}

	public void setLicenseLegalValidTerm( String licenseLegalValidTerm ) {
		this.licenseLegalValidTerm = licenseLegalValidTerm;
	}

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId( String orgId ) {
		this.orgId = orgId;
	}

	public String getOrgNo() {
		return orgNo;
	}

	public void setOrgNo( String orgNo ) {
		this.orgNo = orgNo;
	}

	public String getOrgValidTerm() {
		return orgValidTerm;
	}

	public void setOrgValidTerm( String orgValidTerm ) {
		this.orgValidTerm = orgValidTerm;
	}

	public String getLandtaxId() {
		return landtaxId;
	}

	public void setLandtaxId( String landtaxId ) {
		this.landtaxId = landtaxId;
	}

	public String getLandtaxNo() {
		return landtaxNo;
	}

	public void setLandtaxNo( String landtaxNo ) {
		this.landtaxNo = landtaxNo;
	}

	public String getNationaltaxId() {
		return nationaltaxId;
	}

	public void setNationaltaxId( String nationaltaxId ) {
		this.nationaltaxId = nationaltaxId;
	}

	public String getNationaltaxNo() {
		return nationaltaxNo;
	}

	public void setNationaltaxNo( String nationaltaxNo ) {
		this.nationaltaxNo = nationaltaxNo;
	}

	public String getLegalpersonId() {
		return legalpersonId;
	}

	public void setLegalpersonId( String legalpersonId ) {
		this.legalpersonId = legalpersonId;
	}

	public String getLegalpersonName() {
		return legalpersonName;
	}

	public void setLegalpersonName( String legalpersonName ) {
		this.legalpersonName = legalpersonName;
	}

	public String getLegalpersonCertType() {
		return legalpersonCertType;
	}

	public void setLegalpersonCertType( String legalpersonCertType ) {
		this.legalpersonCertType = legalpersonCertType;
	}

	public String getLegalpersonCertNo() {
		return legalpersonCertNo;
	}

	public void setLegalpersonCertNo( String legalpersonCertNo ) {
		this.legalpersonCertNo = legalpersonCertNo;
	}

	public String getFinanceName() {
		return financeName;
	}

	public void setFinanceName( String financeName ) {
		this.financeName = financeName;
	}

	public String getFinanceCertType() {
		return financeCertType;
	}

	public void setFinanceCertType( String financeCertType ) {
		this.financeCertType = financeCertType;
	}

	public String getFinanceCertNo() {
		return financeCertNo;
	}

	public void setFinanceCertNo( String financeCertNo ) {
		this.financeCertNo = financeCertNo;
	}

	public WhetherStatus getLicenseIsLongterm() {
		return licenseIsLongterm;
	}

	public void setLicenseIsLongterm( WhetherStatus licenseIsLongterm ) {
		this.licenseIsLongterm = licenseIsLongterm;
	}

	public GeographicBoundary getLicenseGeo() {
		return licenseGeo;
	}

	public void setLicenseGeo( GeographicBoundary licenseGeo ) {
		this.licenseGeo = licenseGeo;
	}

	public String getLicenseResidence() {
		return licenseResidence;
	}

	public void setLicenseResidence( String licenseResidence ) {
		this.licenseResidence = licenseResidence;
	}

	public String getLegalpersonBackId() {
		return legalpersonBackId;
	}

	public void setLegalpersonBackId( String legalpersonBackId ) {
		this.legalpersonBackId = legalpersonBackId;
	}

	public GeographicBoundary getLegalpersonGeo() {
		return legalpersonGeo;
	}

	public void setLegalpersonGeo( GeographicBoundary legalpersonGeo ) {
		this.legalpersonGeo = legalpersonGeo;
	}

	public String getLegalpersonMobile() {
		return legalpersonMobile;
	}

	public void setLegalpersonMobile( String legalpersonMobile ) {
		this.legalpersonMobile = legalpersonMobile;
	}

	public AuthApplicantType getAuthApplicantType() {
		return authApplicantType;
	}

	public void setAuthApplicantType( AuthApplicantType authApplicantType ) {
		this.authApplicantType = authApplicantType;
	}

	public String getAgentId() {
		return agentId;
	}

	public void setAgentId( String agentId ) {
		this.agentId = agentId;
	}

	public String getAgentBackId() {
		return agentBackId;
	}

	public void setAgentBackId( String agentBackId ) {
		this.agentBackId = agentBackId;
	}

	public GeographicBoundary getAgentGeo() {
		return agentGeo;
	}

	public void setAgentGeo( GeographicBoundary agentGeo ) {
		this.agentGeo = agentGeo;
	}

	public String getAgentName() {
		return agentName;
	}

	public void setAgentName( String agentName ) {
		this.agentName = agentName;
	}

	public CertificationType getAgentCertType() {
		return agentCertType;
	}

	public void setAgentCertType( CertificationType agentCertType ) {
		this.agentCertType = agentCertType;
	}

	public String getAgentCertNo() {
		return agentCertNo;
	}

	public void setAgentCertNo( String agentCertNo ) {
		this.agentCertNo = agentCertNo;
	}

	public String getAgentMobile() {
		return agentMobile;
	}

	public void setAgentMobile( String agentMobile ) {
		this.agentMobile = agentMobile;
	}

	public String getCompanyPoaId() {
		return companyPoaId;
	}

	public void setCompanyPoaId( String companyPoaId ) {
		this.companyPoaId = companyPoaId;
	}

	public Long getMoneyVerify() {
		return moneyVerify;
	}

	public void setMoneyVerify( Long moneyVerify ) {
		this.moneyVerify = moneyVerify;
	}

	public Long getMoneyFailureCount() {
		return moneyFailureCount;
	}

	public void setMoneyFailureCount( Long moneyFailureCount ) {
		this.moneyFailureCount = moneyFailureCount;
	}

	public String getAuditFailureDescription() {
		return auditFailureDescription;
	}

	public void setAuditFailureDescription( String auditFailureDescription ) {
		this.auditFailureDescription = auditFailureDescription;
	}

	public Integer getBatchNo() {
		return batchNo;
	}

	public void setBatchNo( Integer batchNo ) {
		this.batchNo = batchNo;
	}

	public EnterLinkStatus getEnterLinkStatus() {
		return enterLinkStatus;
	}

	public void setEnterLinkStatus(EnterLinkStatus enterLinkStatus) {
		this.enterLinkStatus = enterLinkStatus;
	}

	public String getIfOpenEnterLink() {
		return ifOpenEnterLink;
	}

	public void setIfOpenEnterLink(String ifOpenEnterLink) {
		this.ifOpenEnterLink = ifOpenEnterLink;
	}

	public Date getOpenEnterLinkTime() {
		return openEnterLinkTime;
	}

	public void setOpenEnterLinkTime(Date openEnterLinkTime) {
		this.openEnterLinkTime = openEnterLinkTime;
	}

	public String getIsSupply() {
		return isSupply;
	}

	public void setIsSupply(String isSupply) {
		this.isSupply = isSupply;
	}

}