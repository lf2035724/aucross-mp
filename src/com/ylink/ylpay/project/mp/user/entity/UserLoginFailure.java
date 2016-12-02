package com.ylink.ylpay.project.mp.user.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.google.code.lightssh.common.entity.base.UUIDModel;

/**
 * 登录失败记录
 * 
 * @author YangXiaojin
 */
@Entity
@Table( name = "T_CUST_LOGIN_FAILURE" )
public class UserLoginFailure extends UUIDModel {

	private static final long serialVersionUID = -4589723187557143853L;

	/**
	 * 关联登录帐户
	 */
	@ManyToOne( fetch = FetchType.EAGER )
	@JoinColumn( name = "USER_ID" )
	private User account;

	/**
	 * 登录名称
	 */
	@Column( name = "LOGIN_NAME", length = 50 )
	private String loginName;

	/**
	 * IP地址
	 */
	@Column( name = "IP", length = 20 )
	private String ip;

	/**
	 * 失败次数
	 */
	@Column( name = "FAILURE_COUNT" )
	private Integer failureCount;

	/**
	 * Session ID
	 */
	@Column( name = "SESSION_ID", length = 50 )
	private String sessionId;

	public User getAccount() {
		return account;
	}

	public void setAccount( User account ) {
		this.account = account;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName( String loginName ) {
		this.loginName = loginName;
	}

	public String getIp() {
		return ip;
	}

	public void setIp( String ip ) {
		this.ip = ip;
	}

	public Integer getFailureCount() {
		return failureCount;
	}

	public void setFailureCount( Integer failureCount ) {
		this.failureCount = failureCount;
	}

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId( String sessionId ) {
		this.sessionId = sessionId;
	}

	/* (non-Javadoc)
	 * @see com.google.code.lightssh.common.entity.Insertable#preInsert()
	 */
	@Override
	public void preInsert() {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see com.google.code.lightssh.common.entity.Insertable#isInsert()
	 */
	@Override
	public boolean isInsert() {
		// TODO Auto-generated method stub
		return false;
	}

}