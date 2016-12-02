/**
 * 版权所有(C) 2013 深圳市雁联计算系统有限公司
 */
package com.ylink.ylpay.project.mp.basic.web;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.google.code.lightssh.common.model.page.ListPage;
import com.google.code.lightssh.common.web.action.BaseAction;
import com.ylink.ylpay.common.project.mp.exception.MpCheckedException;
import com.ylink.ylpay.project.mp.basic.entity.PayBankConfig;
import com.ylink.ylpay.project.mp.basic.service.PayBankConfigManager;

/**
 * 
 * @author yu
 *
 */
@Component("payBankConfigAction")
@Scope("prototype")
public class PayBankConfigAction extends BaseAction{
	private static final long serialVersionUID = -4884133361258313921L;
	
	@Autowired
	private PayBankConfigManager payBankConfigManager;
	
	private List<PayBankConfig> list;
	
	private ListPage<PayBankConfig> page = new ListPage<PayBankConfig>();
	
	private PayBankConfig payBankConfig;
	
	/**
	 * 保存
	 * @return
	 */
	public String save(){
		if(payBankConfig == null){
			return INPUT;
		}
		payBankConfig.setCreateTime(new Date());
		try {
			payBankConfigManager.saveEntity(payBankConfig);
		} catch (Exception e) {
			e.printStackTrace();
			saveErrorMessage("新增或修改银行信息时发生错误:"+ e.getMessage());
			return INPUT;
		}
		saveSuccessMessage("新增或修改成功");
		return SUCCESS;
	}
	/**
	 * 列表
	 * @return
	 */
	public String list(){
		if(payBankConfig == null)
			payBankConfig = new PayBankConfig();
		try {
			page =payBankConfigManager.list(page, payBankConfig);
//			throw new RuntimeException(); 
		} catch (Exception e) {
			this.addActionError("查询付款行信息发生错误:" + e.getMessage());
		}
		return SUCCESS;
	}
	public String edit(){
		if(payBankConfig==null||payBankConfig.getIdentity()==null){
			this.addActionError("银行信息为空，请检查数据");
		}
		try {
			payBankConfig = payBankConfigManager.get(payBankConfig);
			if(payBankConfig == null ){
				throw new MpCheckedException("未查询到该付款行信息：id="+payBankConfig.getIdentity());
			}
		} catch (Exception e) {
			this.addActionError("修改银行信息发生错误："+e.getMessage());
		}
		return SUCCESS;
	}
	public String delete(){
		if(payBankConfig ==null && StringUtils.isBlank(payBankConfig.getIdentity())){
			this.saveErrorMessage("银行信息为空，请检查数据");
			return INPUT;
		}
		try {
			payBankConfigManager.remove(payBankConfig.getIdentity());
		} catch (Exception e) {
			this.saveErrorMessage("删除银行信息发生错误:" + e.getMessage());
			return INPUT;
		}
		saveSuccessMessage("删除成功");
		return SUCCESS;
	}
	/**
	 * @return the page
	 */
	public ListPage<PayBankConfig> getPage() {
		return page;
	}
	/**
	 * @param page the page to set
	 */
	public void setPage(ListPage<PayBankConfig> page) {
		this.page = page;
	}
	/**
	 * @return the payBankConfig
	 */
	public PayBankConfig getPayBankConfig() {
		return payBankConfig;
	}
	/**
	 * @param payBankConfig the payBankConfig to set
	 */
	public void setPayBankConfig(PayBankConfig payBankConfig) {
		this.payBankConfig = payBankConfig;
	}
	/**
	 * @return the list
	 */
	public List<PayBankConfig> getList() {
		return list;
	}
	
}
