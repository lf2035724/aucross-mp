package com.ylink.ylpay.project.mp.system.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.code.lightssh.common.dao.SearchCondition;
import com.google.code.lightssh.common.model.page.ListPage;
import com.google.code.lightssh.common.model.page.ListPage.OrderType;
import com.google.code.lightssh.common.model.page.OrderBy;
import com.google.code.lightssh.common.service.BaseManagerImpl;
import com.ylink.ylpay.common.project.mp.dto.AcceptBankMessageDTO;
import com.ylink.ylpay.project.mp.system.dao.AcceptBankMessageDao;
import com.ylink.ylpay.project.mp.system.entity.AcceptBankMessage;

/**
 * acceptBankMessageManager
 * 
 * @author feng.li
 * @date 2014-11-12
 */
@Component("acceptBankMessageManager")  
public class AcceptBankMessageManagerImpl extends
		BaseManagerImpl<AcceptBankMessage> implements AcceptBankMessageManager {

	private static final long serialVersionUID = -2553913521248335581L;

	private static Logger logger = LoggerFactory
			.getLogger(AcceptBankMessageManagerImpl.class);

	private AcceptBankMessageDao acceptBankMessageDao;

	@Autowired
	public void setAcceptBankMessageDao(
			AcceptBankMessageDao acceptBankMessageDao) {
		this.acceptBankMessageDao = acceptBankMessageDao;
		super.dao = this.acceptBankMessageDao;
	}

	public List<AcceptBankMessageDTO> findList(ListPage<AcceptBankMessage> page,
			AcceptBankMessageDTO t) {

		SearchCondition sc = new SearchCondition();
		if (t != null) {
			if (!StringUtils.isEmpty(t.getCustType())) {
				sc.equal("custType", t.getCustType());
			}

			if (t.getAcceptBank() != null) {
				sc.equal("acceptBank", t.getAcceptBank());
			}

			if (t.getTradeAmount()!= null)
				sc.lessThan("startAmount",Long.valueOf(t.getTradeAmount()));
				sc.greateThanOrEqual("cutoffAmount", Long.valueOf(t.getTradeAmount()));

				sc.equal("expiryFlag", "OPEN");

			if (t.getOptType() != null) {
				sc.equal("optType", t.getOptType());
			}
			if (t.getMerchantNO() != null) {
				sc.equal("merchantNO", t.getMerchantNO());
			}
			if (t.getPayOptType() != null) {
				sc.equal("payOptType", t.getPayOptType());
			}
			if (t.getPublishBank() != null) {
				sc.equal("publishBank", t.getPublishBank());
			}
			if (t.getSignTerminal() != null) {
				sc.equal("signTerminal", t.getSignTerminal());
			}
			if (t.getSignBank() != null) {
				sc.equal("signBank", t.getSignBank());
			}
		}
		OrderBy ob = new OrderBy();
		ob.setProperty("weightLevel");
		ob.setType(OrderType.DESCENDING);
		page.setOrderBy(ob);
		ListPage<AcceptBankMessage> listPage = dao.list(page, sc);
		if(listPage ==null||listPage.getList()==null){
			return null;
		}
		List<AcceptBankMessageDTO> resultList = new ArrayList<AcceptBankMessageDTO>();
		AcceptBankMessageDTO dto = null;
		for(AcceptBankMessage entity : listPage.getList()){
			dto = new AcceptBankMessageDTO();
			BeanUtils.copyProperties(entity,dto,
					new String[]{"tradeAmount","startAmount","cutoffAmount"});
			resultList.add(dto);
			break;//只返回第一个记录，按权重排序
		}
		return resultList;
	}
}