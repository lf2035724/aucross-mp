package com.ylink.ylpay.project.mp.user.util;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.google.code.lightssh.common.dao.Term;
import com.google.code.lightssh.common.dao.Term.Type;
import com.google.code.lightssh.project.util.SpringContextHelper;
import com.google.common.collect.Lists;
import com.ylink.ylpay.project.mp.user.entity.User;
import com.ylink.ylpay.project.mp.user.service.UserManager;

/**
 * @author YangXiaojin
 * @date 2013-2-21
 * @description：TODO
 */
public class UserHelper {

	public static final String USER_MANAGER_NAME = "userManager";

	/**
	 * 查询用户
	 */
	public static User getUser( Long id ) {
		UserManager bean = ( UserManager ) SpringContextHelper.getBean( USER_MANAGER_NAME );
		if ( bean == null )
			return null;

		return bean.get( id );
	}

	/**
	 * 根据custId查询手机号.
	 * 
	 * @param id
	 * @return
	 */
	public static String getMobile( String custId ) {
		if ( StringUtils.isBlank( custId ) )
			return null;
		UserManager manager = ( UserManager ) SpringContextHelper.getBean( USER_MANAGER_NAME );
		if ( manager == null )
			return null;

		List<Term> termList = Lists.newArrayList( new Term( Type.EQUAL, "customerId", custId ) );
		User entity = manager.list( termList );
		if ( null != entity ) {
			return entity.getMobile();
		}
		return null;
	}

}