package com.ylink.ylpay.project.mp.system.app;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.google.code.lightssh.common.model.page.ListPage;
import com.ylink.ylpay.common.project.mp.app.AcceptBankMessageAppService;
import com.ylink.ylpay.common.project.mp.dto.AcceptBankMessageDTO;
import com.ylink.ylpay.common.project.mp.exception.MpCheckedException;
import com.ylink.ylpay.project.mp.system.entity.AcceptBankMessage;
import com.ylink.ylpay.project.mp.system.service.AcceptBankMessageManager;

/** 
 * @author feng.li
 * @date 2014-11-12
 * @description：TODO
 */
 
/**
 * @author feng.li
 *
 */
@Component("acceptBankMessageAppService")
public class AcceptBankMessageAppServiceImpl implements AcceptBankMessageAppService{

	@Resource(name="acceptBankMessageManager") 
	private AcceptBankMessageManager acceptBankMessageManager;
	
	@Override
	public List<AcceptBankMessageDTO> findList(AcceptBankMessageDTO dto) throws MpCheckedException{
		if(dto == null){
			throw new MpCheckedException("AcceptBankMessageDTO can not be null!");
		}
		if(dto.getCustType()==null||dto.getOptType()==null
				||dto.getPayOptType()==null||dto.getPublishBank()==null
				||dto.getSignBank()==null||dto.getTradeAmount()==null||dto.getSignTerminal()==null){
			throw new MpCheckedException("param can not be null!");
		}
		ListPage<AcceptBankMessage> page = new ListPage<AcceptBankMessage>();
		return acceptBankMessageManager.findList(page, dto);
	}

	public AcceptBankMessageManager getAcceptBankMessageManager() {
		return acceptBankMessageManager;
	}

	public void setAcceptBankMessageManager(
			AcceptBankMessageManager acceptBankMessageManager) {
		this.acceptBankMessageManager = acceptBankMessageManager;
	}

}
