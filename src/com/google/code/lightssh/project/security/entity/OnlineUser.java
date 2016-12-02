package com.google.code.lightssh.project.security.entity;

import java.io.Serializable;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import com.google.code.lightssh.project.util.RequestUtil;

/**
 * 在线用户信息
 * @author YangXiaojin
 *
 */
public class OnlineUser implements Serializable{
	
	private static final long serialVersionUID = 1517995311969262617L;

	/**
	 * 用户名称
	 */
	private String username;
	
	/**
	 * Session id
	 */
	private String sessionid;
	
	/**
	 * 登录IP
	 */
	private String ip;
	
	/**
	 * 登录时间
	 */
	private Date loginTime;

	public OnlineUser(String username,String sessionid,String ip) {
		super();
		this.username = username;
		this.sessionid = sessionid;
		this.ip = ip;
		this.loginTime = new Date();
	}
	
	public OnlineUser(String username,HttpServletRequest request) {
		super();
		this.username = username;
		this.loginTime = new Date();
		if( request != null ){
			this.sessionid = request.getSession().getId();
			this.ip = RequestUtil.getIpAddr(request);
		}
	}

	public OnlineUser() {
		super();
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getSessionid() {
		return sessionid;
	}

	public void setSessionid(String sessionid) {
		this.sessionid = sessionid;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public Date getLoginTime() {
		return loginTime;
	}

	public void setLoginTime(Date loginTime) {
		this.loginTime = loginTime;
	}

}
