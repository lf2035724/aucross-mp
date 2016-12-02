package com.ylink.ylpay.project.mp.cust.web;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.google.code.lightssh.common.model.page.ListPage;
import com.ylink.modules.web.struts2.CustomAction;
import com.ylink.ylpay.project.mp.cust.entity.Personal;
import com.ylink.ylpay.project.mp.cust.service.PersonalManager;

/**
 * 个人客户控制层
 * 
 * @author 张磊
 * @date 2012-10-31
 */
@Component( "personalAction" )
@Scope( "prototype" )
public class PersonalAction extends CustomAction<Personal>{

	private static final long serialVersionUID = -1268884250687342157L;

	private PersonalManager personalManager;

	private Personal personal;
	
	@Resource( name = "personalManager" )
	public void setPersonalManager( PersonalManager personalManager) {
		this.personalManager = personalManager;
		super.manager = this.personalManager;
	}

	public PersonalManager getPersonalManager( ){
		return (PersonalManager)this.manager;
	}
	
	public Personal getPersonal() {
		this.personal = super.model;
		return personal;
	}

	public void setPersonal(Personal personal) {
		this.personal = personal;
		super.model = this.personal;
	}
	/**
	 * 列表
	 */
	@Override
	public String list() {
		if ( null == super.getPage() ) {
			super.setPage( new ListPage<Personal>() );
		}
		
		page.addDescending("createDate");
		return super.findPage();
	}
	
	/**
	 * 查看
	 */
	public String view(){
		if( personal != null && personal instanceof Personal ){
			this.setPersonal(getPersonalManager().getPersonal(personal));
		}
		
		if( personal == null )
			return INPUT;
		
		return SUCCESS;
	}
	
}