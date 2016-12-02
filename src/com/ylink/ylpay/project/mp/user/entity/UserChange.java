package com.ylink.ylpay.project.mp.user.entity;

import java.util.Calendar;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.google.code.lightssh.project.log.entity.EntityChange;
import com.google.code.lightssh.project.util.constant.AuditResult;

/**
 * 企业客户信息变更
 * @author Petter
 */
@Entity
@Table(name="T_CUST_USER_CHANGE")
public class UserChange extends EntityChange{

	private static final long serialVersionUID = 4975640723111067418L;
	
	@ManyToOne
	@JoinColumn(name="REF_ID")
	private User user;

	@Column( name="AUDITUSER",length=128)
	private String auditUser;
	@Column( name="AUDITDATE" )
	private Date   auditDate;
	@Column( name="AUDITSTATUS",length=32)
	@Enumerated(EnumType.STRING)
	private AuditResult auditStatus;
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getAuditUser() {
		return auditUser;
	}

	public void setAuditUser(String auditUser) {
		this.auditUser = auditUser;
	}

	public Date getAuditDate() {
		return auditDate;
	}

	public void setAuditDate(Date auditDate) {
		this.auditDate = auditDate;
	}

	 

	public AuditResult getAuditStatus() {
		return auditStatus;
	}

	public void setAuditStatus(AuditResult auditStatus) {
		this.auditStatus = auditStatus;
	}

	public Calendar getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Calendar createdTime) {
		this.createdTime = createdTime;
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
