/**
 * 版权所有(C) 2013 深圳市雁联计算系统有限公司
 */
package com.ylink.ylpay.project.mp.basic.service;

import java.util.List;

import com.google.code.lightssh.common.service.BaseManager;
import com.ylink.ylpay.project.mp.basic.entity.Bank;

/**
 * @author yanghan
 * @date 2013-3-18下午3:44:10
 */
public interface BankManager extends BaseManager<Bank>{
	/**
	 * 检查银行行号是否重复
	 * @param bankCode
	 * @return 不重复 true 重复 false
	 */
	public boolean checkRepeatBankCode(String bankCode) throws Exception;
	
	/**
	 * 根据银行行别和地理位置查询银行信息
	 * @param banType
	 * @return  
	 */
	public List<Bank> findBankListByBankType(Bank bank) throws Exception;
	/**
	 * 保存银行信息
	 * @param bank
	 * @throws Exception
	 */
	public void saveEntity(Bank bank) throws Exception;
	
	/**
	 * 通过联行号查
	 * @param ubankNo
	 * @return
	 */
	public Bank getByUbankNo(String ubankNo);
}
