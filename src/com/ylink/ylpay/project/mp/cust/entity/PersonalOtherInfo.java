package com.ylink.ylpay.project.mp.cust.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import com.google.code.lightssh.common.entity.Persistence;
/**
 * 个人客户其他信息表
 * @author qp
 *
 */
@Entity
@Table(name="T_CUST_PERSON_OTHERINFO")
public class PersonalOtherInfo implements Persistence<String> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4006912183604452297L;
	
	@Id
	@Column( name="CUST_ID",length=20)
	private String custId;
	/**
	 * 地址
	 */
	@Column(name="ADDR",unique=false,nullable=true)
	private String addr;
	/**
	 * 邮编
	 */
	@Column(name="ZIPCODE",unique=false,nullable=true)
	private String zipcode;
	/**
	 * 办公电话
	 */
	@Column(name="OFFICEPHONE",unique=false,nullable=true)
	private String officePhone;
	/**
	 * 住宅电话
	 */
	@Column(name="HOMEPHONE",unique=false,nullable=true)
	private String homePhone;
	/**
	 * 服务定制
	 */
	@Column(name="SERVICETYPE",unique=false,nullable=true)
	private String serviceType;
	/**
	 * 对账单寄送方式
	 */
	@Column(name="SENDWAY",unique=false,nullable=true)
	private String sendWay;
	/**
	 * 对账单寄送频率
	 */
	@Column(name="SENDFREQUENCY",unique=false,nullable=true)
	private String sendFrequency;
	/**
	 * 学历
	 */
	@Column(name="DEGREE",unique=false,nullable=true)
	private String degree;
	
	/**
	 * 图片
	 */
	@Lob
	@Basic(fetch=FetchType.LAZY)
	@Column(name="CREDENTIALSFILE")
	private byte[] credentialsFile;

	public byte[] getCredentialsFile() {
		return credentialsFile;
	}
	public void setCredentialsFile(byte[] credentialsFile) {
		this.credentialsFile = credentialsFile;
	}
	public String getCustId() {
		return custId;
	}
	public void setCustId(String custId) {
		this.custId = custId;
	}
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	public String getZipcode() {
		return zipcode;
	}
	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}
	public String getOfficePhone() {
		return officePhone;
	}
	public void setOfficePhone(String officePhone) {
		this.officePhone = officePhone;
	}
	public String getHomePhone() {
		return homePhone;
	}
	public void setHomePhone(String homePhone) {
		this.homePhone = homePhone;
	}
	public String getServiceType() {
		return serviceType;
	}
	public void setServiceType(String serviceType) {
		this.serviceType = serviceType;
	}
	public String getSendWay() {
		return sendWay;
	}
	public void setSendWay(String sendWay) {
		this.sendWay = sendWay;
	}
	public String getSendFrequency() {
		return sendFrequency;
	}
	public void setSendFrequency(String sendFrequency) {
		this.sendFrequency = sendFrequency;
	}
	public String getDegree() {
		return degree;
	}
	public void setDegree(String degree) {
		this.degree = degree;
	}
	@Override
	public boolean isInsert() {
		return false;
	}
	@Override
	public void postInsertFailure() {
		
	}
	@Override
	public void preInsert() {
		
	}
	@Override
	public String getIdentity() {
		return this.custId;
	}
}
