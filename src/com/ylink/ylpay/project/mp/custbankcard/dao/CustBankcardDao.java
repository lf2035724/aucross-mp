package com.ylink.ylpay.project.mp.custbankcard.dao;

import java.util.List;

import com.ylink.modules.orm.jpa.CustomJpaDao;
import com.ylink.ylpay.common.project.account.constant.CustSubject;
import com.ylink.ylpay.common.project.channel.constant.BankType;
import com.ylink.ylpay.common.project.mp.exception.MpCheckedException;
import com.ylink.ylpay.project.mp.custbankcard.entity.CustBankcard;

/**
 * 个人客户DAO
 * 
 * @author 张磊
 * @date 2012-10-31
 */
public interface CustBankcardDao extends CustomJpaDao<CustBankcard> {

	/**
	 * @description  查询银行卡(门户)
	 * @param custId 
	 * @param accountType 
	 * @return  List<BankCard>
	 * @author zhanglei
	 * @date 2012-10-30
	 */
	public List<CustBankcard> getListByBankcard(String custId,String accountType) ;
	
	public List<CustBankcard> listForEnt(String custId,String accTypeKey);

	/**
	 * @description 校验客户授权码
	 * @param custId 客户ID
	 * @param bankCardNo 银行卡号
	 * @param bankAuthCode 授权码
	 * @return boolean
	 * @author YangXiaojin
	 * @date 2012-10-30
	 */
	public CustBankcard getBankAuthCode(String custId,String bankCardNo,String bankAuthCode);
	
	/**
	 * @description  查询银行卡(门户)
	 * @param id 
	 * @param bankAuthCode 
	 * @param cardNo 
	 * @return CustBankcard
	 * @author zhanglei
	 * @date 2012-10-30
	 */
	public CustBankcard getCustBankcard(String id,  String cardNo);
	
	/**
	 * @description  查询银行卡(门户)
	 * @param id 
	 * @param accountTypeKey 
	 * @param cardNo 
	 * @return  CustBankcard
	 * @author zhanglei
	 * @date 2012-10-30
	 */
	public CustBankcard get(String custId,
			String accountTypeKey, String cardNo,String bindCanal);
	
	/**
	 * 查询银行卡.(基金)
	 * 
	 * @author 张磊
	 * @date 2012-11-9
	 * @param custId
	 * @param bankNo
	 * @param createType
	 * @return bankAuthCode,bankType
	 */
	public List<CustBankcard> getBankCardByFund(String custId, String bankNo, String bankType,String isProtocol,String createType);
		
	/**
	 * 查询银行卡.(慧投)
	 * 
	 * @author 张磊
	 * @date 2012-11-9
	 * @param custId
	 * @param bankNo
	 * @param createType
	 * @return bankAuthCode,bankType
	 */
	public List<CustBankcard> getBankCardByInvest(String custId, String bankNo, String bankType,String isProtocol,String createType);
	
	/**
	 * 基金业务网关绑卡、开户获取银行卡信息
	 * @description：
	 * @param cardNo
	 * @param newCardType
	 * @param isCert
	 * @return  
	 * @author LiXiPing
	 * @date 2013-12-25
	 */
	public List<CustBankcard> getBankCardByFundGateway(String cardNo,String newCardType, String isCert);
	
	/**
	 * 查询银行卡.(门户)
	 * @author 张磊
	 * @date 2012-11-9
	 * @param bankAuthCode
	 * @return BankCard
	 */
	public CustBankcard getBankCardByBankAuthCode(String bankAuthCode);
	/**
	 * 查询银行卡（门户）
	 * @param custId 客户编号
	 * @param bankNo 银行卡卡号
	 * @param bankType 银行行别
	 * @param createType 创建类型
	 * @return
	 */
	public List<CustBankcard> getBankCardByPortal(String custId,String cardNo,BankType bankType,String createType,CustSubject custSubject);
	/**
	 * 查询客户银行卡
	 * @description 
	 * @param accType 帐户类型
	 * @param custId 客户编码
	 * @param bankType 行别
	 * @param name 户名
	 * @param cardNo 卡号
	 * @return  
	 * @author YangXiaojin
	 * @date 2012-11-23
	 */
	public List<CustBankcard> list(String accType,String custId, 
			String bankType, String name,String cardNo);
	/**
	 * 根据企业编号和账户类型查询银行卡（门户）
	 * @param custId 企业客户编号
	 * @param custSubject 账户类型
	 * @return 客户银行卡
	 * @throws MpCheckedException
	 */
	public List<CustBankcard> getBankCardByCustIdAtPortal(String custId,CustSubject custSubject);
	
	/**
	 * 查询银行卡信息
	 * @description：
	 * @param custId
	 * @param cardNO
	 * @param bankCode
	 * @return  
	 * @author LiXiPing
	 * @date 2014-1-20
	 */
	public List<CustBankcard> getCustBankInfo(String custId, String cardNO, String bankCode);
	/**查询客户银行卡
	 * 
	 * @description 
	 * @param custBankcard
	 * @return  
	 * @author yu.han
	 * @date 2014-7-4
	 */
	public CustBankcard getCustBankInfo(CustBankcard custBankcard);
	/**
	 * 查询银行卡
	 * @description 
	 * @param custBankcard
	 * @return  
	 * @author yu.han
	 * @date 2014-7-7
	 */
	public List<CustBankcard> getCustBankInfoList(CustBankcard custBankcard);
	/**
	 * 查询银行卡信息
	 * @description：
	 * @param cardNo
	 * @param createType
	 * @param bankType
	 * @param cardName
	 * @return  CustBankcard
	 * @author duyulong
	 * @date 2014-4-22
	 */
	public CustBankcard getByCardNoAndCreType(String cardNo, String createType,String bankType,String cardName);
	/**
	 * 修改联行号
	 * @param identity
	 * @param contactBankNo
	 */
	public void updateContactBankNo(String identity, String contactBankNo);

}
