package com.ylink.ylpay.project.mp.system.service;

import java.util.List;

import com.google.code.lightssh.common.model.page.ListPage;
import com.google.code.lightssh.common.service.BaseManager;
import com.ylink.ylpay.common.project.mp.dto.AcceptBankMessageDTO;
import com.ylink.ylpay.project.mp.system.entity.AcceptBankMessage;

/**
 * AcceptBankMessageManager
 * 
 * @author feng.li
 * @date 2014-11-12
 */  
public interface AcceptBankMessageManager extends BaseManager<AcceptBankMessage> {
	
	public List<AcceptBankMessageDTO> findList(ListPage<AcceptBankMessage> page,AcceptBankMessageDTO t);
}