package com.ylink.ylpay.project.mp.user.dao;

import org.springframework.stereotype.Repository;

import com.ylink.modules.orm.jpa.CustomJpaDaoImpl;
import com.ylink.ylpay.project.mp.user.entity.UserSafeQuestion;

/**
 * 安全问题及答案DAO
 * 
 * @author 潘瑞峥
 * @date 2012-10-26
 */
@Repository( "userSafeQuestionDao" )
public class UserSafeQuestionDaoJpa extends CustomJpaDaoImpl<UserSafeQuestion> implements UserSafeQuestionDao {

	private static final long serialVersionUID = -6124838257720568599L;

}