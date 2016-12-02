package com.ylink.ylpay.project.mp.user.dao;

import org.springframework.stereotype.Repository;

import com.ylink.modules.orm.jpa.CustomJpaDaoImpl;
import com.ylink.ylpay.project.mp.user.entity.UserLoginFailure;

/**
 * 登录失败记录DAO
 * 
 * @author 潘瑞峥
 * @date 2012-10-27
 */
@Repository( "userLoginFailureDao" )
public class UserLoginFailureDaoJpa extends CustomJpaDaoImpl<UserLoginFailure> implements UserLoginFailureDao {

	private static final long serialVersionUID = -7665166996420187607L;

}