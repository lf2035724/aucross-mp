/**
 * 版权所有(C) 2013 深圳市雁联计算系统有限公司
 */
package com.ylink.ylpay.project.mp.basic.web;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.google.code.lightssh.common.model.page.ListPage;
import com.google.code.lightssh.common.web.action.BaseAction;
import com.ylink.ylpay.project.mp.basic.entity.BanType;
import com.ylink.ylpay.project.mp.basic.entity.Bank;
import com.ylink.ylpay.project.mp.basic.service.BankManager;

/**
 * 银行信息
 * @author yanghan
 * @date 2013-3-18下午4:03:10
 */
@Component("bankAction")
@Scope("prototype")
public class BankAction extends BaseAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4884133361258313921L;
	
	@Autowired
	private BankManager bankManager;
	
	private List<BanType> list;
	
	private ListPage<Bank> page = new ListPage<Bank>();
	
	private Bank bank;
	
	private boolean checkBankCode = false;
	/**
	 * 保存
	 * @return
	 */
	public String save(){
		if(bank == null){
			return INPUT;
		}
		try {
			bankManager.saveEntity(bank);
		} catch (Exception e) {
			saveErrorMessage("新增或修改银行信息时发生错误:"+ e.getMessage());
			return INPUT;
		}
		saveSuccessMessage("新增或修改成功");
		return SUCCESS;
	}
	/**
	 * 检查银行行号是否重复
	 * @return
	 */
	public String checkRepeatBankCode(){
		try {
			if(bank!=null){
				if(StringUtils.isNotEmpty(bank.getIdentity())){
					return SUCCESS;
				}else if(bank.getBankCode() != null){
					checkBankCode =  bankManager.checkRepeatBankCode(bank.getBankCode());					
				}
			}
		} catch (Exception e) {
			this.addActionError("检查银行行号时发生错误:" + e.getMessage());
		}
		return SUCCESS;
	}
	/**
	 * 列表
	 * @return
	 */
	public String list(){
		if(bank == null)
			bank = new Bank();
		try {
			page = bankManager.list(page, bank);
		} catch (Exception e) {
			this.addActionError("查询银行信息发生错误:" + e.getMessage());
		}
		return SUCCESS;
	}
	public String edit(){
		if(bank == null && StringUtils.isEmpty(bank.getIdentity())){
			this.addActionError("银行信息为空，请检查数据");
		}
		try {
			bank = bankManager.get(bank);
			if(bank != null && bank.getStatusMark()!=null)
				bank.setStatus(bank.getStatusMark().getValue());
			if(bank != null && bank.getBankType()!=null)
				bank.setBankTypeCode(bank.getBankType().getBankType());
			if(!bank.getGeoCode().equals(bank.getGeo().getCode())){
				bank.setGeoSecondCode(bank.getGeo().getCode());
			}
		} catch (Exception e) {
			this.addActionError("修改银行信息发生错误："+e.getMessage());
		}
		return SUCCESS;
	}
	public String delete(){
		if(bank ==null && StringUtils.isEmpty(bank.getIdentity())){
			this.saveErrorMessage("银行信息为空，请检查数据");
			return INPUT;
		}
		try {
			bankManager.remove(bank.getIdentity());
		} catch (Exception e) {
			this.saveErrorMessage("删除银行信息发生错误:" + e.getMessage());
			return INPUT;
		}
		saveSuccessMessage("删除成功");
		return SUCCESS;
	}
	/**
	 * @return the bank
	 */
	public Bank getBank() {
		return bank;
	}

	/**
	 * @param bank the bank to set
	 */
	public void setBank(Bank bank) {
		this.bank = bank;
	}
	/**
	 * @return the list
	 */
	public List<BanType> getList() {
		return list;
	}
	/**
	 * @param list the list to set
	 */
	public void setList(List<BanType> list) {
		this.list = list;
	}
	/**
	 * @return the checkBankCode
	 */
	public boolean isCheckBankCode() {
		return checkBankCode;
	}
	/**
	 * @param checkBankCode the checkBankCode to set
	 */
	public void setCheckBankCode(boolean checkBankCode) {
		this.checkBankCode = checkBankCode;
	}
	/**
	 * @return the page
	 */
	public ListPage<Bank> getPage() {
		return page;
	}
	/**
	 * @param page the page to set
	 */
	public void setPage(ListPage<Bank> page) {
		this.page = page;
	}
	
}
