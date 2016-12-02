
package com.ylink.ylpay.project.mp.user.entity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.google.code.lightssh.project.log.entity.EntityAudit;

/**
 * 登录帐户审核
 * @author YangXiaojin
 *
 */
@Entity
@Table(name="T_CUST_USER_AUDIT")
public class UserAudit extends EntityAudit{

	private static final long serialVersionUID = 6505107451293645242L;
	
	@ManyToOne
	@JoinColumn(name="USER_CHANGE_ID")
	private UserChange userChange;
 
	public UserChange getUserChange() {
		return userChange;
	}

	public void setUserChange(UserChange userChange) {
		this.userChange = userChange;
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
