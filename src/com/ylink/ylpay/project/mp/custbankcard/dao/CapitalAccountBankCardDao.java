/**
 * 版权所有(C) 2012 深圳市雁联计算系统有限公司
 */

package com.ylink.ylpay.project.mp.custbankcard.dao;

import java.util.List;

import com.google.code.lightssh.common.dao.Dao;
import com.ylink.ylpay.project.mp.custbankcard.entity.CapitalAccountBankCard;

/** 
 * @author feng.li
 * @date 2012-11-21
 * @description：TODO
 */

public interface CapitalAccountBankCardDao extends Dao<CapitalAccountBankCard>{
	
	/**
	 * 查询银行卡绑定记录
	 */
	public List<CapitalAccountBankCard> listBankCardBindInfo( String bankCardId);
	
	/**
	 * 查询资金账户号绑定记录
	 */
	public List<CapitalAccountBankCard> listCapitalAccountBindInfo(String capitalAccountNO);
	
	/**
	 * 删除绑定关系
	 */
	public int delete( String bankCardId,String capitalAccountNO);
	
	/**
	 * 查询绑定关系
	 */
	public CapitalAccountBankCard get(String bankCardId,String capitalAccountNO);

}
