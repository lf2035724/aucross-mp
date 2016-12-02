package com.ylink.ylpay.project.mp.user.dao;

import org.springframework.stereotype.Repository;

import com.ylink.modules.orm.jpa.CustomJpaDaoImpl;
import com.ylink.ylpay.project.mp.user.entity.PotentialUser;

/**
 * 潜在客户信息DAO
 * 
 * @author 潘瑞峥
 * @date 2012-10-27
 */
@Repository( "potentialUserDao" )
public class PotentialUserDaoJpa extends CustomJpaDaoImpl<PotentialUser> implements PotentialUserDao {

	private static final long serialVersionUID = -7830670787032742324L;

}