/**
 * 版权所有(C) 2013 深圳市雁联计算系统有限公司
 * 创建:Leo 2013-2-25
 */

/**
 * UserChangeDaoJpa.java
 * 版权所有(C) 2013 深圳市雁联计算系统有限公司
 * 创建:Leo 2013-2-25
 */
package com.ylink.ylpay.project.mp.user.dao;

import org.springframework.stereotype.Repository;

import com.ylink.modules.orm.jpa.CustomJpaDaoImpl;
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
@Repository( "userChangeDao" )
public class UserChangeDaoJpa  extends CustomJpaDaoImpl<UserChange>  implements UserChangeDao {

	private static final long serialVersionUID = -8250364314282003930L;



}
