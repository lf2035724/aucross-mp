package com.ylink.ylpay.project.mp.basic.web; 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.google.code.lightssh.common.model.page.ListPage;
import com.google.code.lightssh.common.web.action.BaseAction;
import com.ylink.ylpay.common.project.mp.app.OtcbbCustomerAppService;
import com.ylink.ylpay.common.project.mp.dto.OtcbbCustomer;
import com.ylink.ylpay.common.project.mp.exception.MpCheckedException;
import com.ylink.ylpay.project.mp.basic.entity.BankCnaps;
import com.ylink.ylpay.project.mp.basic.service.BankCnapsManager;

/** 
 * @author  wenwen 
 * @date  2014-12-16 下午5:01:34 
 */
@Component("bankCnapsAction")
@Scope("prototype")
public class BankCnapsAction extends BaseAction{

	private static final long serialVersionUID = -8909159732823005699L;

	@Autowired
	private BankCnapsManager bankCnapsManager;
	@Autowired
	private OtcbbCustomerAppService otcbbCustomerAppService;
	
	private BankCnaps bankCnaps;
	
	private OtcbbCustomer otcbbCustomer;
	
	private ListPage<BankCnaps> page = new ListPage<BankCnaps>();

	/**
	 * 列表
	 * @return
	 */
	public String list(){
		if(bankCnaps == null)
			bankCnaps = new BankCnaps();
		try {
			page = bankCnapsManager.list(page,bankCnaps);
		} catch (Exception e) {
			this.addActionError("查询发生错误:" + e.getMessage());
		}

		return SUCCESS;
	}
	
	public String gotoSinupTest(){
		
		return SUCCESS;
	}
	
	public String sinupTest(){
		try {
			otcbbCustomerAppService.sinup(otcbbCustomer);
		} catch (MpCheckedException e) {
			this.addActionError("测试发生错误:" + e.getMessage());
		}
		return SUCCESS;
	}

	public ListPage<BankCnaps> getPage() {
		return page;
	}

	public void setPage(ListPage<BankCnaps> page) {
		this.page = page;
	}

	public BankCnaps getBankCnaps() {
		return bankCnaps;
	}

	public void setBankCnaps(BankCnaps bankCnaps) {
		this.bankCnaps = bankCnaps;
	}

	public OtcbbCustomer getOtcbbCustomer() {
		return otcbbCustomer;
	}

	public void setOtcbbCustomer(OtcbbCustomer otcbbCustomer) {
		this.otcbbCustomer = otcbbCustomer;
	}

}
 