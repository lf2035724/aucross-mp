package com.ylink.ylpay.project.mp.cust.dao;

import java.io.Serializable;

import com.ylink.modules.orm.jpa.CustomJpaDao;
import com.ylink.ylpay.project.mp.cust.entity.CustCert;

/**
 * 客户证件DAO.
 * 
 * @author 潘瑞峥
 * @date 2013-1-16
 */
public interface CustCertDao extends CustomJpaDao<CustCert> {
	
	public Serializable createEntity(CustCert custCert);
	
}