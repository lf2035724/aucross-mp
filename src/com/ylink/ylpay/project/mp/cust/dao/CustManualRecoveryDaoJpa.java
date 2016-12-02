package com.ylink.ylpay.project.mp.cust.dao;

import org.springframework.stereotype.Component;

import com.ylink.modules.orm.jpa.CustomJpaDaoImpl;
import com.ylink.ylpay.project.mp.cust.entity.CustManualRecovery;

/**
 * 人工找回密码DAO.
 * 
 * @author 潘瑞峥
 * @date 2013-1-4
 */
@Component( "custManualRecoveryDao" )
public class CustManualRecoveryDaoJpa extends CustomJpaDaoImpl<CustManualRecovery> implements CustManualRecoveryDao {

	private static final long serialVersionUID = 5676137814620338428L;

}