/**
 * 版权所有(C) 2013 深圳市雁联计算系统有限公司
 */
package com.ylink.ylpay.project.mp.basic.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.Type;

import com.google.code.lightssh.project.geo.entity.GeographicBoundary;
import com.ylink.modules.orm.hibernate.type.CustomEnumType;
import com.ylink.ylpay.common.project.mp.constant.StatusMark;
import com.ylink.ylpay.project.constants.entity.BaseEntity;


/**
 * @author yanghan
 * @date 2013-3-18下午3:19:47
 */
@Entity
@Table(name="T_BANK")
public class Bank extends BaseEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6344435816950901204L;
	
	@Column(name="BANK_CODE",length=30)
	private String bankCode;//银行行号（联行号）
	@Column(name="BANK_NAME",length=100)
	private String bankName;//银行名称
	@Column(name="STATUS")
	@Type(type=CustomEnumType.ENUM_TYPE,parameters={ @Parameter( name = "enumClass", value = "com.ylink.ylpay.common.project.mp.constant.StatusMark" ) })
	private StatusMark statusMark;//状态
	@OneToOne
	@JoinColumn(name="UPPER_ID")
	private BanType bankType;//归属行别
	@Column(name="ORGAN_CODE",length=30)
	private String orgenCode;//机构号（预留）
	@Column(name="DESCRIPTION")
	private String description;//描述
	@OneToOne
	@JoinColumn(name="GEO_ID")
	private GeographicBoundary geo;
	@Column(name="FRIST_GEO_ID",length=50)
	private String geoCode;
	
	@Transient
	private String geoSecondCode;
	@Transient
	public String bankTypeCode;
	@Transient
	public String status;
	/**
	 * @return the bankCode
	 */
	public String getBankCode() {
		return bankCode;
	}
	/**
	 * @param bankCode the bankCode to set
	 */
	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}
	/**
	 * @return the bankName
	 */
	public String getBankName() {
		return bankName;
	}
	/**
	 * @param bankName the bankName to set
	 */
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	/**
	 * @return the statusMark
	 */
	public StatusMark getStatusMark() {
		return statusMark;
	}
	/**
	 * @param statusMark the statusMark to set
	 */
	public void setStatusMark(StatusMark statusMark) {
		this.statusMark = statusMark;
	}
	/**
	 * @return the bankType
	 */
	public BanType getBankType() {
		return bankType;
	}
	/**
	 * @param bankType the bankType to set
	 */
	public void setBankType(BanType bankType) {
		this.bankType = bankType;
	}
	/**
	 * @return the orgenCode
	 */
	public String getOrgenCode() {
		return orgenCode;
	}
	/**
	 * @param orgenCode the orgenCode to set
	 */
	public void setOrgenCode(String orgenCode) {
		this.orgenCode = orgenCode;
	}
	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	/**
	 * @return the geo
	 */
	public GeographicBoundary getGeo() {
		return geo;
	}
	/**
	 * @param geo the geo to set
	 */
	public void setGeo(GeographicBoundary geo) {
		this.geo = geo;
	}
	/**
	 * @return the bankTypeCode
	 */
	public String getBankTypeCode() {
		return bankTypeCode;
	}
	/**
	 * @param bankTypeCode the bankTypeCode to set
	 */
	public void setBankTypeCode(String bankTypeCode) {
		this.bankTypeCode = bankTypeCode;
	}
	/**
	 * @return the geoCode
	 */
	public String getGeoCode() {
		return geoCode;
	}
	/**
	 * @param geoCode the geoCode to set
	 */
	public void setGeoCode(String geoCode) {
		this.geoCode = geoCode;
	}
	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}
	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	/**
	 * @return the geoSecondCode
	 */
	public String getGeoSecondCode() {
		return geoSecondCode;
	}
	/**
	 * @param geoSecondCode the geoSecondCode to set
	 */
	public void setGeoSecondCode(String geoSecondCode) {
		this.geoSecondCode = geoSecondCode;
	}
}
