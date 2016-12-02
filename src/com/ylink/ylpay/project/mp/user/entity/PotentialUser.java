package com.ylink.ylpay.project.mp.user.entity;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.google.code.lightssh.common.entity.Persistence;
import com.google.code.lightssh.common.util.CryptographyUtil;
import com.google.code.lightssh.common.util.StringUtil;

/**
 * 潜在客户信息
 * @author YangXiaojin
 *
 */
@Entity
@Table( name="T_CUST_TMP_REGISTER" )
public class PotentialUser implements Persistence<String>{

	private static final long serialVersionUID = 1L;
	
	public enum Status{
		NEW("新建")			
		,ACTIVE("激活");		
		
		private String value;
		
		Status( String value ){
			this.value = value;
		}

		public String getValue() {
			return value;
		}
		
		public String toString(){
			return this.value;
		}
	}
	
	/**
	 * ID
	 */
	@Id
	@Column(name="ID")
	protected String id;
	
	/**
	 * 邮箱
	 */
	@Column(name="EMAIL",length=50)
	private String email;
	
	/**
	 * 手机
	 */
	@Column(name="MOBILE",length=20)
	private String mobile;
	
	/**
	 * 状态
	 */
	@Column( name="STATUS",length=20 )
	@Enumerated(value=EnumType.STRING)
	private Status status;
	
	/**
	 * 创建日期
	 */
	@Column(name="CREATED_TIME",columnDefinition="DATE")
	@Temporal( TemporalType.TIMESTAMP )
	private Calendar createdTime;

	@Override
	public String getIdentity() {
		return id;
	}

	@Override
	public boolean isInsert() {
		return StringUtil.clean(this.getIdentity()) == null;
	}

	@Override
	public void postInsertFailure() {
		this.id = null;
	}

	@Override
	public void preInsert() {
		this.createdTime = Calendar.getInstance();
		this.id = CryptographyUtil.hashMd5Hex(email+createdTime.getTimeInMillis());
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Calendar getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Calendar createdTime) {
		this.createdTime = createdTime;
	}

}
