package com.ylink.ylpay.project.mp.user.service;

import com.google.code.lightssh.common.service.BaseManager;
import com.ylink.ylpay.project.mp.user.entity.UserLoginFailure;

/**
 * 登录失败记录业务层
 * 
 * @author 潘瑞峥
 * @date 2012-10-27
 */
public interface UserLoginFailureManager extends BaseManager<UserLoginFailure> {

	/**
	 * 登录失败日志.
	 * 
	 * @param userId
	 * @param loginName
	 * @param ip
	 * @param sessionId
	 */
	public void loginFailure( Long userId, String loginName, String ip, String sessionId );

}