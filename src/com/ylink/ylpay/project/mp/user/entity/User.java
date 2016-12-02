package com.ylink.ylpay.project.mp.user.entity;

import java.util.Calendar;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.Type;

import com.google.code.lightssh.common.entity.base.BaseModel;
import com.google.code.lightssh.common.model.Period;
import com.google.code.lightssh.common.model.Sequenceable;
import com.ylink.modules.orm.hibernate.type.CustomEnumType;
import com.ylink.ylpay.common.project.mp.constant.UserType;
import com.ylink.ylpay.project.constants.EncodeRulesConstant;

/**
 * 登录账号
 * 
 * @author YangXiaojin
 * 
 */
@Entity
@Table( name = "T_CUST_USER" )
public class User extends BaseModel implements Sequenceable {

	private static final long serialVersionUID = 8280727772996743600L;

	/**
	 * 登录名
	 */
	@Column( name = "LOGIN_NAME", unique = true, nullable = false )
	private String loginName;

	/**
	 * 密码
	 */
	@Column( name = "PASSWORD", unique = false, nullable = false )
	private String password;

	/**
	 * 邮箱
	 */
	@Column( name = "EMAIL", unique = true, length = 255 )
	private String email;

	/**
	 * 手机号
	 */
	@Column( name = "MOBILE", unique = true, length = 20 )
	private String mobile;

	/**
	 * 是否有效
	 */
	@Column( name = "ENABLED" )
	private Boolean enabled;

	/**
	 * 创建时间
	 */
	@Column( name = "CREATE_DATE", nullable = false, columnDefinition = "DATE" )
	@Temporal( TemporalType.TIMESTAMP )
	private Date createDate;

	/**
	 * 使用期限
	 */
	@Embedded
	private Period period;

	/**
	 * 描述
	 */
	@Column( name = "DESCRIPTION", length = 200 )
	private String description;

	/**
	 * 所属组织或人员
	 */
	@Column( name = "CUST_ID" )
	private String customerId;

	/**
	 * 最后一次更新密码时间
	 */
	@Column( name = "LAST_UPDATE_PASSWORD_TIME", columnDefinition = "DATE" )
	@Temporal( TemporalType.TIMESTAMP )
	private Calendar lastUpdatePasswordTime;

	/**
	 * 最近一次登录错误锁定时间
	 */
	@Column( name = "LAST_LOGIN_LOCK_TIME", columnDefinition = "DATE" )
	@Temporal( TemporalType.TIMESTAMP )
	private Calendar lastLoginLockTime;

	/** 客户类型. */
	@Column( name = "CUST_TYPE" )
	@Type( type = CustomEnumType.ENUM_TYPE, parameters = { @Parameter( name = "enumClass", value = "com.ylink.ylpay.common.project.mp.constant.UserType" ) } )
	private UserType userType;

	/** 支付密码. */
	@Column( name = "PAY_PASSWORD" )
	private String payPassword;
	
	/**
	 * 状态
	 */
	@Column( name = "STATUS" )
	private String status;

	/**
	 * 客户名称
	 */
	@Transient
	private String _custName;

	public User() {
		super();
	}

	public User( Long id, String name ) {
		this.loginName = name;
		this.id = id;
	}

	/**
	 * 是否过期
	 * 
	 * @return 过期返回false
	 */
	public boolean isExpired() {
		return period == null || !period.isExpired( new Date() );
	}

	/**
	 * 是否有效
	 */
	public boolean isEnabled() {
		return Boolean.TRUE.equals( this.enabled );
	}

	/**
	 * 设置时间区间
	 * 
	 * @param start
	 *            起始时间
	 * @param end
	 *            结束时间
	 */
	public void setPeriod( Date start, Date end ) {
		this.period = new Period( start, end );
	}

	public String getUsername() {
		return this.getLoginName();
	}

	public boolean isAccountNonExpired() {
		return isExpired();
	}

	public boolean isAccountNonLocked() {
		return true;
	}

	public boolean isCredentialsNonExpired() {
		return true;
	}

	// -- getters and setters --------------------------------------------------

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName( String loginName ) {
		this.loginName = loginName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword( String password ) {
		this.password = password;
	}

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled( Boolean enabled ) {
		this.enabled = enabled;
	}

	public Period getPeriod() {
		return period;
	}

	public void setPeriod( Period period ) {
		this.period = period;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription( String description ) {
		this.description = description;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate( Date createDate ) {
		this.createDate = createDate;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId( String customerId ) {
		this.customerId = customerId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail( String email ) {
		this.email = email;
	}

	public Calendar getLastUpdatePasswordTime() {
		return lastUpdatePasswordTime;
	}

	public void setLastUpdatePasswordTime( Calendar lastUpdatePasswordTime ) {
		this.lastUpdatePasswordTime = lastUpdatePasswordTime;
	}

	public Calendar getLastLoginLockTime() {
		return lastLoginLockTime;
	}

	public void setLastLoginLockTime( Calendar lastLoginLockTime ) {
		this.lastLoginLockTime = lastLoginLockTime;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile( String mobile ) {
		this.mobile = mobile;
	}

	public UserType getUserType() {
		return userType;
	}

	public void setUserType( UserType userType ) {
		this.userType = userType;
	}

	public String getPayPassword() {
		return payPassword;
	}

	public void setPayPassword( String payPassword ) {
		this.payPassword = payPassword;
	}

	@Override
	public String getSequenceKey() {
		return EncodeRulesConstant.USER_LOGINNAME_PREFIX;
	}

	@Override
	public int getSequenceStep() {
		return EncodeRulesConstant.DEFAULT_SPIKE;
	}

	@Override
	public int getSequenceLength() {
		return EncodeRulesConstant.USER_LOGINNAME_LENGTH;
	}

	public String get_custName() {
		return _custName;
	}

	public void set_custName( String custName ) {
		_custName = custName;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}