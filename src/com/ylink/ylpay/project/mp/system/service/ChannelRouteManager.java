package com.ylink.ylpay.project.mp.system.service;

import com.google.code.lightssh.common.service.BaseManager;
import com.ylink.ylpay.common.project.channel.constant.BankType;
import com.ylink.ylpay.common.project.mp.constant.InterBankType;
import com.ylink.ylpay.common.project.mp.constant.OptType;
import com.ylink.ylpay.project.mp.system.entity.ChannelRoute;

/**
 * 渠道路由业务层.
 * 
 * @author 潘瑞峥
 * @date 2013-3-15
 */
public interface ChannelRouteManager extends BaseManager<ChannelRoute> {

	/**
	 * 查询默认值.
	 * 
	 * @param optType
	 * @return
	 */
	ChannelRoute findDefault( OptType optType );

	/**
	 * 查询.
	 * 
	 * @param optType
	 * @param bankType
	 * @param interBankType
	 * @return
	 */
	ChannelRoute find( OptType optType, BankType bankType, InterBankType interBankType );
	/**
	 * 查询.
	 * 
	 * @param optType
	 * @param bankType
	 * @param interBankType
	 * @return
	 */
	ChannelRoute find( OptType optType, String bankType, InterBankType interBankType );

	/**
	 * 查询默认值.
	 * 
	 * @param optType
	 * @return
	 */
	String findDefaultValue( OptType optType );

	/**
	 * 查询.
	 * 
	 * @param optType
	 * @param bankType
	 * @param interBankType
	 * @return
	 */
	String findValue( OptType optType, BankType bankType, InterBankType interBankType );
	/**
	 * 查询.
	 * 
	 * @param optType
	 * @param bankType
	 * @param interBankType
	 * @return
	 */
	String findValue( OptType optType, String bankType, InterBankType interBankType );

}