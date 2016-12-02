package com.ylink.ylpay.project.mp.cust.service;

import com.google.code.lightssh.project.security.entity.LoginAccount;
import com.ylink.ylpay.common.project.account.dto.AuthMoneyDTO;

/** 
 * @author feng.li
 * @date 2013-10-25
 * @description：TODO
 */

/**
 * @author feng.li
 */
public interface AuthMoneyManager{
	
	/**
	 * 更新授信信息（需要审核）
	 * 
	 * @author  feng.li
	 * @param authMoneyDTO
	 * @param user
	 * @return 企业客户信息DTO
	 */
	public String update( AuthMoneyDTO authMoneyDTO,LoginAccount user ) throws Exception;
	
	/**
	 * 更新授信信息（需要审核）
	 * 
	 * @author  feng.li
	 * @param authMoneyDTO
	 * @param user
	 * @return 企业客户信息DTO
	 */
	public String update( AuthMoneyDTO authMoneyDTO,LoginAccount user,String remark) throws Exception;
	
	/**
	 * 冻结支付授信额度
	 * 
	 * @author  feng.li
	 * @param custId
	 */
	public void freezePayUsed(String custId, LoginAccount user) throws Exception;
	
	/**
	 * 冻结赎回授信额度
	 * 
	 * @author  feng.li
	 * @param custId
	 */
	public void freezeRedeemUsed(String custId, LoginAccount user) throws Exception;
	
	/**
	 * 解冻支付授信额度
	 * 
	 * @author  feng.li
	 * @param custId
	 */
	public void unfreezePayUsed(String custId, LoginAccount user) throws Exception;
	
	/**
	 * 解冻赎回授信额度
	 * 
	 * @author  feng.li
	 * @param custId
	 */
	public void unfreezeRedeemUsed(String custId, LoginAccount user) throws Exception;
}
