package com.ylink.ylpay.project.mp.user.web;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.google.code.lightssh.common.model.page.ListPage;
import com.ylink.modules.web.struts2.CustomAction;
import com.ylink.ylpay.project.mp.user.entity.UserSafeQuestion;
import com.ylink.ylpay.project.mp.user.service.UserSafeQuestionManager;

/**
 * 安全问题及答案控制层
 * 
 * @author 潘瑞峥
 * @date 2012-10-26
 */
@Component( "userSafeQuestionAction" )
@Scope( "prototype" )
public class UserSafeQuestionAction extends CustomAction<UserSafeQuestion> {

	private static final long serialVersionUID = -5867077803976233700L;

	private UserSafeQuestionManager userSafeQuestionManager;

	@Resource( name = "userSafeQuestionManager" )
	public void setUserSafeQuestionManager( UserSafeQuestionManager userSafeQuestionManager ) {
		this.userSafeQuestionManager = userSafeQuestionManager;
		super.manager = this.userSafeQuestionManager;
	}

	/**
	 * 列表
	 */
	@Override
	public String list() {
		if ( null == super.getPage() ) {
			super.setPage( new ListPage<UserSafeQuestion>() );
		}
		return super.findPage();
	}

}