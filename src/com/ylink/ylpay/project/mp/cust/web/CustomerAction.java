/**
 * 版权所有(C) 2012 深圳市雁联计算系统有限公司
 */

package com.ylink.ylpay.project.mp.cust.web;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.google.code.lightssh.common.model.page.ListPage;
import com.google.code.lightssh.project.web.action.GenericAction;
import com.ylink.ylpay.project.mp.cust.entity.Customer;
import com.ylink.ylpay.project.mp.cust.service.CustomerManager;

/** 
 * @author YangXiaojin
 * @date 2012-11-19
 * @description：TODO
 */
@Component( "customerAction" )
@Scope( "prototype" )
public class CustomerAction extends GenericAction<Customer>{

	private static final long serialVersionUID = -1268884250687342157L;
	
	@Resource(name="customerManager")
	private CustomerManager customerManager;
	
	private Customer customer;
	
	private ListPage<Customer> page;
	
	public ListPage<Customer> getPage() {
		return page;
	}

	public void setPage(ListPage<Customer> page) {
		this.page = page;
	}

	public Customer getCustomer() {
		this.customer = super.model;
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
		super.model = this.customer;
	}

	/**
	 * 弹出窗口
	 */
	public String popup( ){
		if( page == null )
			page = new ListPage<Customer>( );
		
		page.setSize(10);
		page = customerManager.list(page,customer);
		
		return SUCCESS;
	}

}
