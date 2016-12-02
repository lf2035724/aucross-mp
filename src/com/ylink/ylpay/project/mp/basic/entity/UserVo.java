/**
 * 版权所有(C) 2013 深圳市雁联计算系统有限公司
 * 创建:Leo 2013-2-22
 */

/**
 * UserVo.java
 * 版权所有(C) 2013 深圳市雁联计算系统有限公司
 * 创建:Leo 2013-2-22
 */
package com.ylink.ylpay.project.mp.basic.entity;

import java.util.Calendar;
import java.util.Date;

import com.google.code.lightssh.common.entity.base.BaseModel;
import com.google.code.lightssh.common.model.Period;
import com.ylink.ylpay.common.project.mp.constant.UserType;

/** 
 * @author Leo
 * @date 2013-2-22
 * @description：TODO
 */

/**
 * @author Leo
 *
 */
public class UserVo extends BaseModel {


	private static final long serialVersionUID = 5330165110499335053L;
	private String loginName;
	private String name;
	private String password;
	private String email;
	private String mobile;
	private Boolean enabled;
	private Date createDate;
	private Period period;
	private String description;
	private String customerId;
	private Calendar lastUpdatePasswordTime;
	private Calendar lastLoginLockTime;
	private UserType userType;
	private String status;
	public String getLoginName() {
		return loginName;
	}
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public Boolean getEnabled() {
		return enabled;
	}
	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public Period getPeriod() {
		return period;
	}
	public void setPeriod(Period period) {
		this.period = period;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	public Calendar getLastUpdatePasswordTime() {
		return lastUpdatePasswordTime;
	}
	public void setLastUpdatePasswordTime(Calendar lastUpdatePasswordTime) {
		this.lastUpdatePasswordTime = lastUpdatePasswordTime;
	}
	public Calendar getLastLoginLockTime() {
		return lastLoginLockTime;
	}
	public void setLastLoginLockTime(Calendar lastLoginLockTime) {
		this.lastLoginLockTime = lastLoginLockTime;
	}
	public UserType getUserType() {
		return userType;
	}
	public void setUserType(UserType userType) {
		this.userType = userType;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	 
	

}
