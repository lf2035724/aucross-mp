/**
 * 版权所有(C) 2013 深圳市雁联计算系统有限公司
 * 创建:Leo 2013-2-25
 */

/**
 * UserChangeManagerImpl.java
 * 版权所有(C) 2013 深圳市雁联计算系统有限公司
 * 创建:Leo 2013-2-25
 */
package com.ylink.ylpay.project.mp.user.service;

import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.annotation.Resource;

import org.apache.commons.lang3.Validate;
import org.springframework.stereotype.Component;

import com.google.code.lightssh.common.service.BaseManagerImpl;
import com.ylink.ylpay.project.mp.user.dao.UserChangeDao;
import com.ylink.ylpay.project.mp.user.entity.UserChange;

/** 
 * @author Leo
 * @date 2013-2-25
 */

/**
 * @author Leo
 *
 */
@Component( "userChangeManager" )
public class UserChangeManagerImpl extends BaseManagerImpl<UserChange>  implements UserChangeManager {

	UserChangeDao userChangeDao;
	
	@Resource( name = "userChangeDao" )
	public void setUserChangeDao( UserChangeDao userChangeDao) {
		this.userChangeDao = userChangeDao;
		super.dao = userChangeDao;
	}

	public UserChangeDao getUserChangeDao() {
		return ( UserChangeDao ) this.userChangeDao;
	}
	
	private static final long serialVersionUID = 5441205343790911577L;

	 

	public void save(UserChange userChange) {
		Validate.notNull( userChange );

		// 创建日期.
		Calendar c=new GregorianCalendar();
		userChange.setCreatedTime( c);
		this.userChangeDao.create( userChange );

	}
 

}
