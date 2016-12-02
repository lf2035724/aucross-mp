package com.ylink.ylpay.project.mp.user.service;

import java.util.List;

import com.google.code.lightssh.common.service.BaseManager;
import com.ylink.ylpay.common.project.mp.dto.SafeQuestion;
import com.ylink.ylpay.project.mp.user.entity.UserSafeQuestion;

/**
 * 安全问题及答案业务层.
 * 
 * @author 潘瑞峥
 * @date 2012-10-26
 */
public interface UserSafeQuestionManager extends BaseManager<UserSafeQuestion> {

	/**
	 * 查询全部.
	 * 
	 * @return
	 */
	public List<UserSafeQuestion> listAll();

	/**
	 * 新增.
	 * 
	 * @param entity
	 */
	public void crate( UserSafeQuestion entity );

	/**
	 * 修改安全问题及密码.
	 * 
	 * @param userId
	 * @param oldSafeAnswer
	 * @param newQuestion
	 * @param newSafeAnswer
	 */
	public void update( Long userId, String oldSafeAnswer, String newQuestion, String newSafeAnswer );

	/**
	 * 通过用户ID查询安保问题.
	 * 
	 * @param userId
	 * @return
	 */
	public UserSafeQuestion getSafeQuestion( Long userId );
	
	/**
	 * 新增密保问题及答案
	 * @param userId
	 * @return
	 */
	public void create( String userId,SafeQuestion question) throws Exception;

}