
package com.google.code.lightssh.project.security.service;

import com.google.code.lightssh.common.service.BaseManager;
import com.google.code.lightssh.project.security.entity.LoginAccountAudit;
import com.google.code.lightssh.project.security.entity.LoginAccountChange;

/** 
 * @author YangXiaojin
 * @date 2013-2-23
 * @description：TODO
 */

public interface LoginAccountAuditManager extends BaseManager<LoginAccountAudit>{
	
	/**
	 * 审核
	 */
	public void audit(LoginAccountAudit audit,LoginAccountChange change );

}
