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

import com.google.code.lightssh.project.geo.entity.GeographicBoundary;
import com.ylink.ylpay.project.constants.EncodeRulesConstant;

/**
 * 个人客户
 * 
 * @author 张磊
 * 
 */
@Entity
@Table( name = "T_CUST_PERSON" )
public class Personal extends Customer {

	private static final long serialVersionUID = 3776928789103941491L;

	@Override
	public String getSequenceKey() {
		return EncodeRulesConstant.CUSTOMER_INDIVIDUAL_PREFIX;
	}

	/**
	 * 注册类型
	 */
	@Column( name = "REGISTERED_TYPE", unique = false, nullable = false )
	private String registeredType;

	/**
	 * 证件有效期
	 */
	@Column( name = "CERT_EXPIRY_Date", columnDefinition = "Date" )
	@Temporal( TemporalType.TIMESTAMP )
	private Date certExpiryDate;

	/**
	 * 国籍
	 */
	@ManyToOne( fetch = FetchType.EAGER )
	@JoinColumn( name = "COUNTRY_ID" )
	private GeographicBoundary countryId;

	/**
	 * 所属二级地理区域
	 */
	@ManyToOne( fetch = FetchType.EAGER )
	@JoinColumn( name = "SECONDARY_GEO_ID" )
	private GeographicBoundary secondaryGeoId;

	/**
	 * 所属三级地理区域
	 */
	@ManyToOne( fetch = FetchType.EAGER )
	@JoinColumn( name = "THIRD_GEO_ID" )
	private GeographicBoundary thirdGeoId;

	/**
	 * 所属四级地理区域
	 */
	@ManyToOne( fetch = FetchType.EAGER )
	@JoinColumn( name = "FOURTH_GEO_ID" )
	private GeographicBoundary fourthGeoId;

	/**
	 * 别名
	 */
	@Column( name = "NICKNAME", unique = false, length = 100 )
	private String nickName;

	/**
	 * 性别
	 */
	@Column( name = "GENDER", unique = false, length = 20 )
	private String gender;

	/**
	 * 生日
	 */
	@Column( name = "BIRTHDAY", columnDefinition = "Date" )
	@Temporal( TemporalType.TIMESTAMP )
	private Date birthday;

	/**
	 * 婚姻状态
	 */
	@Column( name = "MARITAL_STATUS", unique = false, length = 20 )
	private String maritalStatus;

	/**
	 * 民族
	 */
	@Column( name = "ETHNIC_GROUP", unique = false, length = 50 )
	private String ethnicGroup;

	/**
	 * 政治面貌
	 */
	@Column( name = "PARTY_AFFILIATION", unique = false, length = 50 )
	private String partyAffiliation;

	/**
	 * 血型
	 */
	@Column( name = "BLOOD_TYPE", unique = false, length = 10 )
	private String bloodType;

	/**
	 * 身高
	 */
	@Column( name = "HEIGHT" )
	private Integer height;

	/**
	 * 体重
	 */
	@Column( name = "WEIGHT" )
	private Float weight;

	/**
	 * 职业
	 */
	@Column( name = "PROFESSION", unique = false, length = 100 )
	private String profession;

	/**
	 * 证券投资经验
	 */
	@Column( name = "INVESTMENT_EXP", unique = false, length = 100 )
	private String investmentExp;

	/**
	 * 年收入
	 */
	@Column( name = "ANNUAL_INCOME", unique = false, length = 100 )
	private String annualIncome;
	
	/**
	 * 手机号
	 */
	@Column( name = "MOBILE" )
	private String mobile;

	public String getRegisteredType() {
		return registeredType;
	}

	public void setRegisteredType( String registeredType ) {
		this.registeredType = registeredType;
	}

	public Date getCertExpiryDate() {
		return certExpiryDate;
	}

	public void setCertExpiryDate( Date certExpiryDate ) {
		this.certExpiryDate = certExpiryDate;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName( String nickName ) {
		this.nickName = nickName;
	}

	public String getGender() {
		return gender;
	}

	public void setGender( String gender ) {
		this.gender = gender;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday( Date birthday ) {
		this.birthday = birthday;
	}

	public String getMaritalStatus() {
		return maritalStatus;
	}

	public void setMaritalStatus( String maritalStatus ) {
		this.maritalStatus = maritalStatus;
	}

	public String getEthnicGroup() {
		return ethnicGroup;
	}

	public void setEthnicGroup( String ethnicGroup ) {
		this.ethnicGroup = ethnicGroup;
	}

	public String getPartyAffiliation() {
		return partyAffiliation;
	}

	public void setPartyAffiliation( String partyAffiliation ) {
		this.partyAffiliation = partyAffiliation;
	}

	public String getBloodType() {
		return bloodType;
	}

	public void setBloodType( String bloodType ) {
		this.bloodType = bloodType;
	}

	public Integer getHeight() {
		return height;
	}

	public void setHeight( Integer height ) {
		this.height = height;
	}

	public Float getWeight() {
		return weight;
	}

	public void setWeight( Float weight ) {
		this.weight = weight;
	}

	public String getProfession() {
		return profession;
	}

	public void setProfession( String profession ) {
		this.profession = profession;
	}

	public String getInvestmentExp() {
		return investmentExp;
	}

	public void setInvestmentExp( String investmentExp ) {
		this.investmentExp = investmentExp;
	}

	public String getAnnualIncome() {
		return annualIncome;
	}

	public void setAnnualIncome( String annualIncome ) {
		this.annualIncome = annualIncome;
	}

	public GeographicBoundary getCountryId() {
		return countryId;
	}

	public void setCountryId( GeographicBoundary countryId ) {
		this.countryId = countryId;
	}

	public GeographicBoundary getSecondaryGeoId() {
		return secondaryGeoId;
	}

	public void setSecondaryGeoId( GeographicBoundary secondaryGeoId ) {
		this.secondaryGeoId = secondaryGeoId;
	}

	public GeographicBoundary getThirdGeoId() {
		return thirdGeoId;
	}

	public void setThirdGeoId( GeographicBoundary thirdGeoId ) {
		this.thirdGeoId = thirdGeoId;
	}

	public GeographicBoundary getFourthGeoId() {
		return fourthGeoId;
	}

	public void setFourthGeoId( GeographicBoundary fourthGeoId ) {
		this.fourthGeoId = fourthGeoId;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

}