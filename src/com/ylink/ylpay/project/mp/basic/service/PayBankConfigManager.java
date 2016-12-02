/**
 * 版权所有(C) 2013 深圳市雁联计算系统有限公司
 */
package com.ylink.ylpay.project.mp.basic.service;

import com.google.code.lightssh.common.service.BaseManager;
import com.ylink.ylpay.project.mp.basic.entity.PayBankConfig;

/**
 * 
 * @author yu
 *
 */
public interface PayBankConfigManager extends BaseManager<PayBankConfig>{

	/**
	 * 保存支付行配置
	 * @description 
	 * @param payBankConfig  
	 * @author yu.han
	 * @date 2013-4-9
	 */
	void saveEntity(PayBankConfig payBankConfig)throws Exception;
	/**
	 * 根据行别和业务编码查询匹配付款行
	 * @description 
	 * @param bankType
	 * @param optType
	 * @return  
	 * @author yu.han
	 * @date 2013-4-10
	 */
	PayBankConfig getMatchConfig(String bankType,String optType);
}
