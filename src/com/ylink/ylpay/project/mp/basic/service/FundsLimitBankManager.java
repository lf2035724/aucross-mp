package com.ylink.ylpay.project.mp.basic.service;

import java.util.List;

import com.google.code.lightssh.common.service.BaseManager;
import com.ylink.ylpay.common.project.channel.constant.BankType;
import com.ylink.ylpay.common.project.channel.constant.ChannelType;
import com.ylink.ylpay.common.project.mp.constant.CustBankCardType;
import com.ylink.ylpay.project.mp.basic.entity.FundsLimitBank;

public interface FundsLimitBankManager extends BaseManager<FundsLimitBank>{
	/**
	 * 判断唯一性(行别、银行卡类型、渠道类型、交易类型)
	 * @param fund
	 * @return
	 */
	
	public boolean isUniqueBankIdAndCardTypeAndChannelTypeAndBusinessId(FundsLimitBank fund);

	/**
	 * 查询银行卡限额
	 * @param bankType
	 * @param cardType
	 * @param channelType
	 * @return
	 */
	public List<FundsLimitBank> getList(
			BankType bankType, CustBankCardType cardType,
			ChannelType channelType);

}
