package com.ylink.ylpay.project.mp.cust.service;

import java.io.Serializable;

import com.google.code.lightssh.common.service.BaseManager;
import com.ylink.ylpay.common.project.mp.constant.CertificationType;
import com.ylink.ylpay.common.project.mp.constant.RecoveryAllowType;
import com.ylink.ylpay.project.mp.cust.entity.CustCert;

/**
 * 客户证件业务层.
 * 
 * @author 潘瑞峥
 * @date 2013-1-16
 */
public interface CustCertManager extends BaseManager<CustCert> {

	public Serializable saveEntity( CustCert custCert );

	/**
	 * 保存并返回.
	 * 
	 * @param entity
	 * @return
	 */
	public CustCert save( CertificationType certType, byte[] certFile, RecoveryAllowType certFileType );
	/**
	 * 修改证件类型
	 * @author YangHan
	 * @param custCertId
	 * @param certFile
	 * @return
	 */
	public void update (String certCustId,byte[] certFile);
}