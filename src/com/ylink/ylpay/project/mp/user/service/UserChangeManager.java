/**
 * 版权所有(C) 2013 深圳市雁联计算系统有限公司
 * 创建:Leo 2013-2-25
 */

/**
 * UserChangeManager.java
 * 版权所有(C) 2013 深圳市雁联计算系统有限公司
 * 创建:Leo 2013-2-25
 */
package com.ylink.ylpay.project.mp.user.service;

import com.google.code.lightssh.common.service.BaseManager;
import com.ylink.ylpay.project.mp.user.entity.UserChange;

/** 
 * @author Leo
 * @date 2013-2-25
 * @description：TODO
 */

/**
 * @author Leo
 *
 */
public interface UserChangeManager extends BaseManager<UserChange> {
	public void save(UserChange userChange);
}
