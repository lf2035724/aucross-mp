package com.ylink.ylpay.project.mp.cust.web;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.google.code.lightssh.common.model.page.ListPage;
import com.google.code.lightssh.common.web.action.BaseAction;
import com.ylink.ylpay.common.project.supplychain.app.SupplyChainContractAppService;
import com.ylink.ylpay.common.project.supplychain.app.SupplyChainCapitalAppService;
import com.ylink.ylpay.common.project.supplychain.constant.AuditStatus;
import com.ylink.ylpay.common.project.supplychain.dto.CapitalRequirementsDTO;
import com.ylink.ylpay.common.project.supplychain.dto.CapitalSupplyDTO;
import com.ylink.ylpay.common.project.supplychain.dto.ContractDTO;

/**
 * 供应链
 * 
 * @author feng.li
 * @date 2013-9-10
 */
@Component( "supplyChainAction" )
@Scope( "prototype" )
public class SupplyChainAction extends BaseAction {

	private static final long serialVersionUID = -8046472883893562816L;
	
	private ContractDTO contractDTO;
	
	private CapitalSupplyDTO capitalSupplyDTO;
	
	private CapitalRequirementsDTO capitalRequirementsDTO;
	
	private ListPage<ContractDTO> page;
	
	private ListPage<CapitalSupplyDTO> capitalSupplyPage;
	
	private ListPage<CapitalRequirementsDTO> capitalRequirementsPage;
	
	private List<CapitalSupplyDTO> capitalSupplyList;
	
	
	@Resource(name="supplyChainContractService")
	private SupplyChainContractAppService supplyChainContractService;
	
	@Resource(name="supplyChainCapitalAppService")
	private SupplyChainCapitalAppService supplyChainCapitalAppService;
	
	public String list() throws Exception{
		if( page == null )
			page = new ListPage<ContractDTO>();
		try {
			page = supplyChainContractService.contractSelectList(page, contractDTO);
		} catch ( Exception e ) {
			this.saveErrorMessage( "供应链系统异常无法获取协议：" + e.getMessage());
		}
		return SUCCESS;
	}
	
	public String listCapitalRequirements() throws Exception{
		if( capitalRequirementsPage == null )
			capitalRequirementsPage = new ListPage<CapitalRequirementsDTO>();
		try {
			capitalRequirementsPage = supplyChainCapitalAppService.capitalRequirementsSelectList(capitalRequirementsPage, capitalRequirementsDTO);
		} catch ( Exception e ) {
			this.saveErrorMessage( "供应链系统异常无法获取协议：" + e.getMessage());
		}
		return SUCCESS;
	}
	
	public String listSupplyMessage() throws Exception{
		if( capitalSupplyPage == null )
			capitalSupplyPage = new ListPage<CapitalSupplyDTO>();
		try {
			capitalSupplyPage = supplyChainCapitalAppService.capitalSupplySelectList(capitalSupplyPage, capitalSupplyDTO);
			capitalSupplyList = capitalSupplyPage.getList();
		} catch ( Exception e ) {
			this.saveErrorMessage( "供应链系统异常无法获取协议：" + e.getMessage());
		}
		return SUCCESS;
	}
	
	public String detail(){
		if(contractDTO != null ){
			try {
				contractDTO = supplyChainContractService.contractSelectOne(contractDTO.getContractSeq(),contractDTO.getContractType());
			} catch ( Exception e ) {
				this.saveErrorMessage( "供应链系统异常无法获取协议：" + e.getMessage());
			}
		}
		return SUCCESS;
	}
	
	
	
	public String supplyMessageDetail(){
		if(capitalSupplyDTO != null ){
			try {
				capitalSupplyDTO = supplyChainCapitalAppService.capitalSupplySelectOne(capitalSupplyDTO.getMessageSeq());
			} catch ( Exception e ) {
				this.saveErrorMessage( "供应链系统异常无法获取协议：" + e.getMessage());
			}
		}
		return SUCCESS;
	}
	
	public String capitalRequirementsDetail(){
		if(capitalRequirementsDTO != null ){
			try {
				capitalRequirementsDTO = supplyChainCapitalAppService.capitalRequirementsSelectOne(capitalRequirementsDTO.getMessageSeq());
			} catch ( Exception e ) {
				this.saveErrorMessage( "供应链系统异常无法获取协议：" + e.getMessage());
			}
		}
		return SUCCESS;
	}

	public String supplyMessageToAudit(){
		if(capitalSupplyDTO != null ){
			try {
				capitalSupplyDTO = supplyChainCapitalAppService.capitalSupplySelectOne(capitalSupplyDTO.getMessageSeq());
			} catch ( Exception e ) {
				this.saveErrorMessage( "供应链系统异常无法获取协议：" + e.getMessage());
			}
		}
		return SUCCESS;
	}
	
	
	/**
	 * 审核
	 */
	public String supplyMessageAudit(){
		if(capitalSupplyDTO == null || StringUtils.isEmpty(capitalSupplyDTO.getMessageSeq())){
			saveErrorMessage("审核出错，数据编号为空");
			return INPUT;
		}
		Boolean isPassed = request.getParameter("passed") != null;
		Boolean isReject = request.getParameter("reject") != null;
		try{
			if(isPassed){
				capitalSupplyDTO.setAuditStatus(AuditStatus.AuditStatus_PASS.getValue());
			}else if(isReject){
				capitalSupplyDTO.setAuditStatus(AuditStatus.AuditStatus_REFUSE.getValue());
			}
			supplyChainCapitalAppService.capitalSupplyAudit(capitalSupplyDTO);
		}catch(Exception ex){
			saveErrorMessage("审核信息出错" + ex.getMessage());
			return INPUT;
		}
		return SUCCESS;
	}
	
	public String capitalRequirementsToAudit(){
		if(capitalRequirementsDTO != null ){
			try {
				capitalRequirementsDTO = supplyChainCapitalAppService.capitalRequirementsSelectOne(capitalRequirementsDTO.getMessageSeq());
			} catch ( Exception e ) {
				this.saveErrorMessage( "供应链系统异常无法获取协议：" + e.getMessage());
			}
		}
		return SUCCESS;
	}
	
	/**
	 * 审核
	 */
	public String capitalRequirementsAudit(){
		if(capitalRequirementsDTO == null || StringUtils.isEmpty(capitalRequirementsDTO.getMessageSeq())){
			saveErrorMessage("审核出错，数据编号为空");
			return INPUT;
		}
		Boolean isPassed = request.getParameter("passed") != null;
		Boolean isReject = request.getParameter("reject") != null;
		try{
			if(isPassed){
				capitalRequirementsDTO.setAuditStatus(AuditStatus.AuditStatus_PASS.getValue());
			}else if(isReject){
				capitalRequirementsDTO.setAuditStatus(AuditStatus.AuditStatus_REFUSE.getValue());
			}
			supplyChainCapitalAppService.capitalRequirementsAudit(capitalRequirementsDTO);
		}catch(Exception ex){
			saveErrorMessage("审核信息出错" + ex.getMessage());
			return INPUT;
		}
		return SUCCESS;
	}

	public ContractDTO getContractDTO() {
		return contractDTO;
	}

	public void setContractDTO(ContractDTO contractDTO) {
		this.contractDTO = contractDTO;
	}

	public ListPage<ContractDTO> getPage() {
		return page;
	}

	public void setPage(ListPage<ContractDTO> page) {
		this.page = page;
	}

	public CapitalSupplyDTO getCapitalSupplyDTO() {
		return capitalSupplyDTO;
	}

	public void setCapitalSupplyDTO(CapitalSupplyDTO capitalSupplyDTO) {
		this.capitalSupplyDTO = capitalSupplyDTO;
	}

	public ListPage<CapitalSupplyDTO> getCapitalSupplyPage() {
		return capitalSupplyPage;
	}

	public void setCapitalSupplyPage(ListPage<CapitalSupplyDTO> capitalSupplyPage) {
		this.capitalSupplyPage = capitalSupplyPage;
	}

	public List<CapitalSupplyDTO> getCapitalSupplyList() {
		return capitalSupplyList;
	}

	public void setCapitalSupplyList(List<CapitalSupplyDTO> capitalSupplyList) {
		this.capitalSupplyList = capitalSupplyList;
	}

	public CapitalRequirementsDTO getCapitalRequirementsDTO() {
		return capitalRequirementsDTO;
	}

	public void setCapitalRequirementsDTO(
			CapitalRequirementsDTO capitalRequirementsDTO) {
		this.capitalRequirementsDTO = capitalRequirementsDTO;
	}

	public ListPage<CapitalRequirementsDTO> getCapitalRequirementsPage() {
		return capitalRequirementsPage;
	}

	public void setCapitalRequirementsPage(
			ListPage<CapitalRequirementsDTO> capitalRequirementsPage) {
		this.capitalRequirementsPage = capitalRequirementsPage;
	}

}	