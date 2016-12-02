package com.ylink.ylpay.project.mp.basic.web;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.google.code.lightssh.common.web.action.CrudAction;
import com.ylink.ylpay.project.mp.basic.entity.BusinessType;
import com.ylink.ylpay.project.mp.basic.service.BusinessTypeManager;

/**
 *  BusinessTypeAction
 * @author xuwei
 *
 */
@Component( "businessTypeAction" )
@Scope( "prototype" )
public class BusinessTypeAction extends CrudAction<BusinessType>{

	private static final long serialVersionUID = 1L;
	

	@SuppressWarnings("unused")
	private  BusinessTypeManager  businessTypeManager;

	@Resource( name = "businessTypeManager" )
	public void setBusinessTypeManager(BusinessTypeManager businessTypeManager) {
		this.businessTypeManager = businessTypeManager;
		super.manager=businessTypeManager;
	}

}
