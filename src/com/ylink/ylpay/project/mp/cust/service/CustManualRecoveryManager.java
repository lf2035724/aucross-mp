package com.ylink.ylpay.project.mp.cust.service;

import com.google.code.lightssh.common.service.BaseManager;
import com.ylink.ylpay.common.project.mp.constant.AuditStatus;
import com.ylink.ylpay.common.project.mp.dto.ManualRecovery;
import com.ylink.ylpay.project.mp.cust.entity.CustManualRecovery;

/**
 * 人工找回密码业务层.
 * 
 * @author 潘瑞峥
 * @date 2013-1-4
 */
public interface CustManualRecoveryManager extends BaseManager<CustManualRecovery> {

	/**
	 * 通过dto保存.
	 * 
	 * @param dtoBean
	 */
	void save( ManualRecovery dtoBean );

	/**
	 * 修改审核状态.
	 * 
	 * @param id
	 * @param fromStatus
	 * @param toStatus
	 */
	void modify( String id, AuditStatus fromStatus, AuditStatus toStatus );

	/**
	 * 通过id修改为找回密码成功状态.
	 * 
	 * @param id
	 */
	void modifySuccess( String id );

	/**
	 * 通过id查询.
	 * 
	 * @param id
	 * @return
	 */
	ManualRecovery find( String id );

	/**
	 * 通过custId查询待审核且未找回的记录.
	 * 
	 * @param id
	 * @return
	 */
	ManualRecovery findValidByCustId( String custId );

}