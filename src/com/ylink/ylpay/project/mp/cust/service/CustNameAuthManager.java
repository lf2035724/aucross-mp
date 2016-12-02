package com.ylink.ylpay.project.mp.cust.service;

import com.google.code.lightssh.common.service.BaseManager;
import com.ylink.ylpay.common.project.mp.constant.AuditStatus;
import com.ylink.ylpay.common.project.mp.dto.CustNameAuthBean;
import com.ylink.ylpay.project.mp.cust.entity.CustNameAuth;

/**
 * 个人客户实名认证业务层.
 * 
 * @author 潘瑞峥
 * @date 2013-1-16
 */
public interface CustNameAuthManager extends BaseManager<CustNameAuth> {

	/**
	 * 保存.
	 */
	void save( CustNameAuthBean dtoBean );

	/**
	 * 修改审核状态.
	 * 
	 * @param id
	 * @param fromStatus
	 * @param toStatus
	 */
	void modify( String id, AuditStatus fromStatus, AuditStatus toStatus );

}