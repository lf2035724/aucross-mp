package com.ylink.ylpay.project.mp.agency.service;

import java.util.List;

import com.google.code.lightssh.common.service.BaseManager;
import com.google.code.lightssh.project.security.entity.LoginAccount;
import com.ylink.ylpay.common.project.mp.constant.AuditStatus;
import com.ylink.ylpay.project.mp.agency.entity.CustAgency;

/**
 * 机构业务层.
 * 
 * @author 潘瑞峥
 * @date 2012-12-22
 */
public interface CustAgencyManager extends BaseManager<CustAgency> {

	/**
	 * code或name查询有效机构.
	 * 
	 * @param codeAndName
	 * @return
	 */
	public List<CustAgency> findCodeOrName( String codeOrName );

	/**
	 * 修改状态.
	 * 
	 * @param id
	 * @param fromStatus
	 * @param toStatus
	 */
	public void modify( String id, AuditStatus fromStatus, AuditStatus toStatus );
	/**
	 * @description 修改机构信息
	 * @param vo
	 * @param currentLoginAccount
	 * @return  
	 * @author Pengyindong
	 * @date 2013-2-27
	 */
	public String update(CustAgency vo, LoginAccount currentLoginAccount);

}