package com.ylink.ylpay.project.mp.basic.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.Type;

import com.ylink.modules.orm.hibernate.type.CustomEnumType;
import com.ylink.ylpay.common.project.fund.constant.BankType;
import com.ylink.ylpay.project.constants.entity.BaseEntity;

/**
 * 
 * @author wenwen
 * @date 2014-12-15 下午5:45:45
 *
 */
@Entity
@Table(name="T_BANK_CNAPS")
public class BankCnaps extends BaseEntity{

	private static final long serialVersionUID = 1412356359853809189L;
	
	@Column(name="BANK_CODE",length=12)
	private String bankCode;//联行号
	@Column(name="BANK_NAME",length=280)
	private String bankName;//网点名称
	@Column(name="BANK_TYPE",length=4)
	private String bankType;//行别
	@Column(name="DREC_CODE",length=12)
	private String drecCode;//所属清算行行号
	@Column(name="CCPC",length=12)
	private String ccpc;
	@Column(name="SYSCODE",length=32)
	private String syscode;
	@Column(name="TEL",length=32)
	private String tel;
	@Column(name="EFFECTDATE",length=8)
	private String effecidate;//生效日期
	@Column(name="EXPDATE",length=8)
	private String expdate;//过期日期
	
	
	public String getBankCode() {
		return bankCode;
	}
	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	public String getBankType() {
		return bankType;
	}
	public void setBankType(String bankType) {
		this.bankType = bankType;
	}
	public String getDrecCode() {
		return drecCode;
	}
	public void setDrecCode(String drecCode) {
		this.drecCode = drecCode;
	}
	public String getCcpc() {
		return ccpc;
	}
	public void setCcpc(String ccpc) {
		this.ccpc = ccpc;
	}
	public String getSyscode() {
		return syscode;
	}
	public void setSyscode(String syscode) {
		this.syscode = syscode;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getEffecidate() {
		return effecidate;
	}
	public void setEffecidate(String effecidate) {
		this.effecidate = effecidate;
	}
	public String getExpdate() {
		return expdate;
	}
	public void setExpdate(String expdate) {
		this.expdate = expdate;
	}
	
}
