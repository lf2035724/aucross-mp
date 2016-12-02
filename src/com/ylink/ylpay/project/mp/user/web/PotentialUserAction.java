package com.ylink.ylpay.project.mp.user.web;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.google.code.lightssh.common.model.page.ListPage;
import com.ylink.modules.web.struts2.CustomAction;
import com.ylink.ylpay.project.mp.user.entity.PotentialUser;
import com.ylink.ylpay.project.mp.user.service.PotentialUserManager;

/**
 * 潜在客户信息业务层
 * 
 * @author 潘瑞峥
 * @date 2012-10-27
 */
@Component( "potentialUserAction" )
@Scope( "prototype" )
public class PotentialUserAction extends CustomAction<PotentialUser> {

	private static final long serialVersionUID = -3197241980599147087L;

	private PotentialUserManager potentialUserManager;

	@Resource( name = "potentialUserManager" )
	public void setPotentialUserManager( PotentialUserManager potentialUserManager ) {
		this.potentialUserManager = potentialUserManager;
		super.manager = this.potentialUserManager;
	}

	/**
	 * 列表
	 */
	@Override
	public String list() {
		if ( null == super.getPage() ) {
			super.setPage( new ListPage<PotentialUser>() );
		}
		return super.findPage();
	}

}