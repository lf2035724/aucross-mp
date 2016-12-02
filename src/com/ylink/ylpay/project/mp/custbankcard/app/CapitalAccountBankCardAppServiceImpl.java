package com.ylink.ylpay.project.mp.custbankcard.app;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.google.code.lightssh.common.model.page.ListPage;
import com.ylink.ylpay.common.project.mp.app.CapitalAccountBankCardAppService;
import com.ylink.ylpay.common.project.mp.dto.CapitalAccountBankCardDTO;
import com.ylink.ylpay.common.project.mp.exception.MpCheckedException;
import com.ylink.ylpay.project.mp.custbankcard.service.CapitalAccountBankCardManager;

/** 
 * @author feng.li
 * @date 2014-11-21
 * @description：TODO
 */
@Component( "capitalAccountBankCardAppService" )
public class CapitalAccountBankCardAppServiceImpl implements CapitalAccountBankCardAppService{

	@Resource(name="capitalAccountBankCardManager")
	private CapitalAccountBankCardManager capitalAccountBankCardManager;
	
	@Override
	public boolean isExists(String bankCardId, String capitalAccountNO)
			throws MpCheckedException {
		if(bankCardId == null ) throw new MpCheckedException("bankCardId不能为空");
		if(capitalAccountNO == null ) throw new MpCheckedException("capitalAccountNO不能为空");
		boolean result = capitalAccountBankCardManager.isExists(bankCardId, capitalAccountNO);
		return result;
	}

	@Override
	public boolean isFirstBind(String bankCardId) throws MpCheckedException {
		if(bankCardId == null ) throw new MpCheckedException("bankCardId不能为空");
		boolean result = capitalAccountBankCardManager.isFirstBind(bankCardId);
		return result;
	}

	@Override
	public boolean isOnlyOne(String bankCardId) throws MpCheckedException {
		if(bankCardId == null ) throw new MpCheckedException("bankCardId不能为空");
		boolean result = capitalAccountBankCardManager.isOnlyOne(bankCardId);
		return result;
	}

	@Override
	public ListPage<CapitalAccountBankCardDTO> list(
			ListPage<CapitalAccountBankCardDTO> page,
			CapitalAccountBankCardDTO t) {
		if(page == null){
			page = new ListPage<CapitalAccountBankCardDTO>();
		}
		return capitalAccountBankCardManager.list(page, t);
	}

	@Override
	public CapitalAccountBankCardDTO get(String bankCardId,
			String capitalAccountNO) throws MpCheckedException {
		if(bankCardId == null ) throw new MpCheckedException("bankCardId不能为空");
		if(capitalAccountNO == null ) throw new MpCheckedException("capitalAccountNO不能为空");
		return capitalAccountBankCardManager.get(bankCardId,capitalAccountNO);
	}

	public CapitalAccountBankCardManager getCapitalAccountBankCardManager() {
		return capitalAccountBankCardManager;
	}

	public void setCapitalAccountBankCardManager(
			CapitalAccountBankCardManager capitalAccountBankCardManager) {
		this.capitalAccountBankCardManager = capitalAccountBankCardManager;
	}

	@Override
	public void save(CapitalAccountBankCardDTO dto) throws MpCheckedException {
		capitalAccountBankCardManager.save(dto);
	}

	@Override
	public void remove(String bankCardId, String capitalAccountNO)
			throws MpCheckedException {
		capitalAccountBankCardManager.remove(bankCardId, capitalAccountNO);
	}
}
