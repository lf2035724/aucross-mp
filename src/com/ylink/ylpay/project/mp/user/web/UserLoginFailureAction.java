package com.ylink.ylpay.project.mp.user.web;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.google.code.lightssh.common.model.page.ListPage;
import com.ylink.modules.web.struts2.CustomAction;
import com.ylink.ylpay.project.mp.user.entity.UserLoginFailure;
import com.ylink.ylpay.project.mp.user.service.UserLoginFailureManager;

/**
 * 登录失败记录业务层
 * 
 * @author 潘瑞峥
 * @date 2012-10-27
 */
@Component( "userLoginFailureAction" )
@Scope( "prototype" )
public class UserLoginFailureAction extends CustomAction<UserLoginFailure> {

	private static final long serialVersionUID = -1788585268904853973L;

	private UserLoginFailureManager userLoginFailureManager;

	@Resource( name = "userLoginFailureManager" )
	public void setUserLoginFailureManager( UserLoginFailureManager userLoginFailureManager ) {
		this.userLoginFailureManager = userLoginFailureManager;
		super.manager = this.userLoginFailureManager;
	}

	/**
	 * 列表
	 */
	@Override
	public String list() {
		if ( null == super.getPage() ) {
			super.setPage( new ListPage<UserLoginFailure>() );
		}
		return super.findPage();
	}

}