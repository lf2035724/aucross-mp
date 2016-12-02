package com.ylink.ylpay.project.mp.cust.dao;

import java.io.Serializable;

import org.springframework.stereotype.Repository;

import com.ylink.modules.orm.jpa.CustomJpaDaoImpl;
import com.ylink.ylpay.project.mp.cust.entity.CustCert;

/**
 * 客户证件DAO.
 * 
 * @author 潘瑞峥
 * @date 2013-1-16
 */
@Repository( "custCertDao" )
public class CustCertDaoJpa extends CustomJpaDaoImpl<CustCert> implements CustCertDao {

	private static final long serialVersionUID = -1030575346384906376L;
	
	@Override
	public Serializable createEntity(CustCert custCert) {
		// TODO Auto-generated method stub
		super.create(custCert);
		custCert = super.read(custCert);
		return custCert.getIdentity();
	}

}