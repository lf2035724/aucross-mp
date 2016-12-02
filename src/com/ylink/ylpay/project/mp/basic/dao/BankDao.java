/**
 * 版权所有(C) 2013 深圳市雁联计算系统有限公司
 */
package com.ylink.ylpay.project.mp.basic.dao;

import java.util.List;

import com.google.code.lightssh.common.dao.Dao;
import com.ylink.ylpay.project.mp.basic.entity.Bank;

/**
 * @author yanghan
 * @date 2013-3-18下午3:36:59
 */
public interface BankDao extends Dao<Bank>{
	/**
	 * 根据银行行号查询银行信息
	 * @param BankCode(银行行号)
	 * @return 银行信息
	 * @throws Exception
	 */
	public Bank findBankByBankCode(String BankCode);
	
	/**
	 * 根据银行行别查询银行信息
	 * @param banType银行行别
	 * @return 银行信息
	 * @throws Exception
	 */
	public List<Bank> findBankListByBankType(Bank bank) throws Exception;
}
