package com.ylink.ylpay.project.mp.user.service;

import com.google.code.lightssh.common.service.BaseManager;
import com.ylink.ylpay.project.mp.user.entity.PotentialUser;

/**
 * 潜在客户信息业务层
 * 
 * @author 潘瑞峥
 * @date 2012-10-27
 */
public interface PotentialUserManager extends BaseManager<PotentialUser> {

	/**
	 * 根据ID激活用户.
	 * 
	 * @param id
	 */
	public void activeById( String id ) throws Exception;

}