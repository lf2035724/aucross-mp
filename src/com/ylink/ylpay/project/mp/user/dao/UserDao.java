/**
 * 版权所有(C) 2012 深圳市雁联计算系统有限公司
 */

package com.ylink.ylpay.project.mp.user.dao;

import com.ylink.modules.orm.jpa.CustomJpaDao;
import com.ylink.ylpay.project.mp.user.entity.User;

/** 
 * @author YangXiaojin
 * @date 2012-10-26
 * @description：TODO
 */
public interface UserDao extends CustomJpaDao<User>{
	
	/**
	 * @description 通过登录名称查询门户登录帐户
	 * @param email
	 * @return User
	 * @author YangXiaojin
	 * @date 2012-10-26
	 */
	public User getByLoginName(String loginName);
	
	/**
	 * @description 通过邮箱地址查询门户登录帐户
	 * @param email
	 * @return User
	 * @author YangXiaojin
	 * @date 2012-10-26
	 */
	public User getByEmail(String email);
	
	/**
	 * @description 通过邮箱地址查询门户登录帐户
	 * @param email
	 * @return  User
	 * @author YangXiaojin
	 * @date 2012-10-26
	 */
	public User getByMobile(String mobile);

	/**
	 * @description 更新用户状态
	 * @param userId
	 * @param enabled  
	 * @author yu.han
	 * @return 
	 * @date 2013-3-13
	 */
	public int activateOrFreezeUser(Long userId, boolean enabled);

	/**
	 * @description 更新用户状态
	 * @param userId
	 * @param status  
	 * @author feng.li
	 * @return 
	 * @date 2014-5-13
	 */
	public int updateUserStatus(Long userId, String status);
}
