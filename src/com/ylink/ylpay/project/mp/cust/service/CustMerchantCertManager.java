package com.ylink.ylpay.project.mp.cust.service;

import com.google.code.lightssh.common.service.BaseManager;
import com.ylink.ylpay.project.mp.cust.entity.CustMerchant;
import com.ylink.ylpay.project.mp.cust.entity.CustMerchantCert;

/**
 * 商户证件业务层.
 * 
 * @author 潘瑞峥
 * @date 2013-4-20
 */
public interface CustMerchantCertManager extends BaseManager<CustMerchantCert> {

	/**
	 * 根据商户查询证书.
	 * 
	 * @param custMerchant
	 * @return
	 */
	public CustMerchantCert find( CustMerchant custMerchant );

	/**
	 * 根据商户保存证书.
	 * 
	 * @param custMerchant
	 * @throws Exception
	 */
	public void save( CustMerchant custMerchant ) throws Exception;

	/**
	 * 根据商户修改证书.
	 * 
	 * @param custMerchant
	 * @throws Exception
	 */
	public void modify( CustMerchant custMerchant ) throws Exception;

}