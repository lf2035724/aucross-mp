package com.ylink.ylpay.project.mp.cust.dao;

import org.springframework.stereotype.Repository;

import com.ylink.modules.orm.jpa.CustomJpaDaoImpl;
import com.ylink.ylpay.project.mp.cust.entity.CustMerchantCert;

/**
 * 商户证件DAO.
 * 
 * @author 潘瑞峥
 * @date 2013-4-20
 */
@Repository( "custMerchantCertDao" )
public class CustMerchantCertDaoJpa extends CustomJpaDaoImpl<CustMerchantCert> implements CustMerchantCertDao {

	private static final long serialVersionUID = -3662152139803405521L;

}