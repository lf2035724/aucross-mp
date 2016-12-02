package com.ylink.ylpay.project.mp.basic.web;

import javax.annotation.Resource;
import org.apache.struts2.json.annotations.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import com.google.code.lightssh.common.model.page.ListPage;
import com.google.code.lightssh.common.web.action.BaseAction;
import com.ylink.ylpay.common.project.mp.app.PayBankMessageAppService;
import com.ylink.ylpay.common.project.mp.dto.PayBankMessageDTO;
import com.ylink.ylpay.common.project.mp.exception.MpCheckedException;

/**
 * 
 * @author YULONG.DU
 * @date   2013-12-11
 * @description 付款行信息
 */
@Component("payBankMessageAction")
@Scope("prototype")
public class PayBankMessageAction extends BaseAction{

	private static final long serialVersionUID = 7580999886282862941L;
	
	private boolean unique;
	
	private Logger logger = LoggerFactory.getLogger(PayBankMessageAction.class);
	
	@Resource(name = "payBankMessageAppService")
	private PayBankMessageAppService payBankMessageAppService;

	private PayBankMessageDTO payBankMessageDTO;

	private ListPage<PayBankMessageDTO> page = new ListPage<PayBankMessageDTO>();
	
	public PayBankMessageAppService getPayBankMessageAppService() {
		return payBankMessageAppService;
	}
	public void setPayBankMessageAppService(
			PayBankMessageAppService payBankMessageAppService) {
		this.payBankMessageAppService = payBankMessageAppService;
	}
	public PayBankMessageDTO getPayBankMessageDTO() {
		return payBankMessageDTO;
	}
	public void setPayBankMessageDTO(PayBankMessageDTO payBankMessageDTO) {
		this.payBankMessageDTO = payBankMessageDTO;
	}
	public ListPage<PayBankMessageDTO> getPage() {
		return page;
	}
	public void setPage(ListPage<PayBankMessageDTO> page) {
		this.page = page;
	}

	public String list() throws Exception {
		logger.info("开始查询付款行信息。。。");
		if (payBankMessageDTO == null)
			payBankMessageDTO = new PayBankMessageDTO();
		
		try{
			page =payBankMessageAppService.list(page,payBankMessageDTO);
		}catch(Exception e){
			logger.error("查询付款行信息失败。。。");
            return INPUT;
		}
		logger.info("付款行信息查询成功。。。");
		return SUCCESS;
	}
	
	public String goSave() throws Exception {
		logger.debug("转入保存页面。。。");
		return SUCCESS;
	}
	
	public String save() throws Exception {
		logger.debug("保存付款行信息。。。");
		if(null == payBankMessageDTO){
			logger.error("未获取到需要保存的数据。。.");
			return INPUT;
		}
		
		try{
		   payBankMessageAppService.savePayBankMessage(payBankMessageDTO);
		}catch(Exception e){
			logger.error("付款行信息保存失败。。。");
			return INPUT;
		}
		
		saveSuccessMessage("保存成功");
		logger.info("付款行信息保存成功...");
		return SUCCESS;
	}
	
	public String edit() throws Exception {
		logger.debug("编辑付款行信息。。。");
		if(null == payBankMessageDTO){
			logger.error("未获取到需要修改的数据。。.");
			return INPUT;
		}
		
		try{
	       payBankMessageAppService.updatePayBankMessage(payBankMessageDTO);
		}catch(Exception e){
			logger.error("付款行信息修改失败。。。");
			return INPUT;
		}
		
		logger.info("付款行信息修改成功。。。");
	    saveSuccessMessage("保存成功");
		return SUCCESS;
	}
	public String goEdit() throws Exception {
		logger.info("查询需要修改的数据并跳转到修改页面。。。");
		if(null == payBankMessageDTO.getIdentity()){
			logger.error("未获取到需要修改数据的id:["+payBankMessageDTO.getIdentity()+"]");
			return INPUT;
		}
		
		try{
		   payBankMessageDTO=payBankMessageAppService.getOneMessage(payBankMessageDTO.getIdentity());
		}catch(Exception e){
			logger.error("查询需要修改的数据失败。。。");
			return INPUT;
		}
		return SUCCESS;
	}
	
	public String delete() throws Exception {
		logger.info("删除付款行信息。。。");
		if(null == payBankMessageDTO.getIdentity()){
			logger.error("未获取到需要删除数据的id:["+payBankMessageDTO.getIdentity()+"]");
			return INPUT;
		}
		
		try{
		   payBankMessageAppService.deleteByIdentity(payBankMessageDTO.getIdentity());
		}catch(Exception e){
			logger.error("付款行信息删除失败:["+e.getMessage()+"]");
			return INPUT;
			
		}
		saveSuccessMessage("删除成功");
		return SUCCESS;
	}
	
	@JSON(name="unique")
	public boolean isUnique() {
		return unique;
	}
	
  	public String unique( ){
  		if(null == payBankMessageDTO){
  			return INPUT;
  		}
		try {
			this.unique = this.payBankMessageAppService.isUniquePayBankMessage(payBankMessageDTO);
		} catch (MpCheckedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return SUCCESS;
	}
}
