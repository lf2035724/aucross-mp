package com.ylink.ylpay.project.mp.cust.web;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.google.code.lightssh.common.model.page.ListPage;
import com.google.code.lightssh.common.web.action.BaseAction;
import com.ylink.ylpay.common.project.fund.app.QueryCorpBuyBackAppService;
import com.ylink.ylpay.common.project.fund.dto.CorpBuyBackDTO;

/** 
 * @author feng.li
 * @date 2014-2-26
 * @description：做市商action
 */

/**
 * @author feng.li
 */
@Component( "corpBuyBackAction" )
@Scope( "prototype" )
public class CorpBuyBackAction extends BaseAction{

	private static final long serialVersionUID = 573261417807121147L;
	
	@Resource(name="queryCorpBuyBackAppService")
	private QueryCorpBuyBackAppService queryCorpBuyBackAppService;
	
	private ListPage<CorpBuyBackDTO> page;
	
	private CorpBuyBackDTO dto;
	
	public String list(){
		if( page == null )
			page = new ListPage<CorpBuyBackDTO>();
		try{
			page = queryCorpBuyBackAppService.findCorpBuyBackListPage(page, dto);
		}catch( Exception e ){
			this.saveErrorMessage("账务系统异常："+e.getMessage());
		}
		return SUCCESS;
	}
	
	public String view(){
		if(dto == null || StringUtils.isEmpty(dto.getId())){
			this.saveErrorMessage("请求参数为空！");
			return INPUT;
		}
		dto = queryCorpBuyBackAppService.findCorpBuyBackById(dto.getId());
		return SUCCESS;
	}

	public ListPage<CorpBuyBackDTO> getPage() {
		return page;
	}

	public void setPage(ListPage<CorpBuyBackDTO> page) {
		this.page = page;
	}

	public CorpBuyBackDTO getDto() {
		return dto;
	}

	public void setDto(CorpBuyBackDTO dto) {
		this.dto = dto;
	}
	
}
