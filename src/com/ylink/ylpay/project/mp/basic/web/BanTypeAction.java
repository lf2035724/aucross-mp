package com.ylink.ylpay.project.mp.basic.web;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.google.code.lightssh.common.model.page.ListPage;
import com.ylink.modules.web.struts2.CustomAction;
import com.ylink.ylpay.project.mp.basic.entity.BanType;
import com.ylink.ylpay.project.mp.basic.service.BanTypeManager;

/**
 * BanTypeAction
 * @author xuwei
 *
 */
@Component( "banTypeAction" )
@Scope( "prototype" )
public class BanTypeAction extends CustomAction<BanType>{


	private static final long serialVersionUID = 1L;

	
	@SuppressWarnings("unused")
	private BanTypeManager banTypeManager;
	
	@Resource( name = "banTypeManager" )
	public void setBanTypeManager(BanTypeManager banTypeManager) {
		this.banTypeManager = banTypeManager;
		super.manager=banTypeManager;
	}
	
	
	/**
	 * 列表
	 */
	public String list() {
		if ( null == super.getPage() ) {
			super.setPage( new ListPage<BanType>() );
		}
		return super.findPage();
      }
}