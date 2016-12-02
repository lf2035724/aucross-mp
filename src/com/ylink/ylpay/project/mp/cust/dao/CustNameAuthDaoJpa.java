package com.ylink.ylpay.project.mp.cust.dao;

import org.springframework.stereotype.Repository;

import com.ylink.modules.orm.jpa.CustomJpaDaoImpl;
import com.ylink.ylpay.project.mp.cust.entity.CustNameAuth;

/**
 * 个人客户实名认证DAO.
 * 
 * @author 潘瑞峥
 * @date 2013-1-16
 */
@Repository( "custNameAuthDao" )
public class CustNameAuthDaoJpa extends CustomJpaDaoImpl<CustNameAuth> implements CustNameAuthDao {

	private static final long serialVersionUID = 6739810533853980447L;

}