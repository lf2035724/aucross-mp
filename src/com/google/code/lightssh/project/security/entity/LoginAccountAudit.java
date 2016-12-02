
package com.google.code.lightssh.project.security.entity;

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
@Table(name="T_SECURITY_LOGINACCOUNT_AUDIT")
public class LoginAccountAudit extends EntityAudit{

	private static final long serialVersionUID = 6505107451293645242L;
	
	/**
	 * 登录帐户变更
	 */
	@ManyToOne
	@JoinColumn(name="LOGINACCOUNT_CHANGE_ID")
	private LoginAccountChange loginAccountChange;

	public LoginAccountChange getLoginAccountChange() {
		return loginAccountChange;
	}

	public void setLoginAccountChange(LoginAccountChange loginAccountChange) {
		this.loginAccountChange = loginAccountChange;
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
