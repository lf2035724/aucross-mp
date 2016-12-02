package com.ylink.ylpay.project.mp.custbankcard.service;

import java.util.Date;
import java.util.List;

import com.google.code.lightssh.common.dao.Term;
import com.google.code.lightssh.common.service.BaseManager;
import com.ylink.ylpay.common.project.account.constant.CustSubject;
import com.ylink.ylpay.common.project.channel.constant.BankType;
import com.ylink.ylpay.common.project.mp.constant.AuditStatus;
import com.ylink.ylpay.common.project.mp.dto.BankCard;
import com.ylink.ylpay.common.project.mp.exception.MpCheckedException;
import com.ylink.ylpay.project.mp.custbankcard.entity.CustBankcard;

/**
 * 客户银行卡业务层.
 * 
 * @author 张磊
 * @date 2012-11-2
 */
public interface CustBankcardManager extends BaseManager<CustBankcard> {

	public boolean updateAuditStatus(String id,AuditStatus old,AuditStatus newVal );
	

	/**
	 * 新增银行卡
	 */
	public CustBankcard saveBankcard( CustBankcard custBankcard );

	/**
	 * 更新客户银行卡授权码.
	 * 
	 * @author 张磊
	 * @date 2012-11-9
	 * @param custId
	 * @param cardNo
	 * @param authNo
	 */
	public void updateBankAuthCode( String id, String bankAuthCode, String cardNo );
	/**
	 * 
	 * @description 查询银行卡
	 * @param id
	 * @param accountTypeKey
	 * @param cardNo
	 * @param bindCanal
	 * @return CustBankcard
	 * @author zhanglei
	 * @date 2012-10-30
	 */
	public CustBankcard get( String custId, String accountTypeKey, String cardNo,String bindCanal );
	
	/**
	 * 查询银行卡
	 * @param custId
	 * @param cardNo
	 * @return
	 */
	public CustBankcard get( String custId,String cardNo,String bindCanal);
	
	/**
	 * 查询银行卡
	 * @param custId
	 * @param cardNo
	 * @param status
	 * @return
	 */
	public CustBankcard getWithStatus( String custId,String cardNo,String status,String bindCanal);
	
	
	
	/**
	 * 查询银行卡
	 * @param custId
	 * @param cardNo
	 * @param status
	 * @param bankCode
	 * @return
	 */
	public CustBankcard getWithStatus( String custId,String cardNo,String status,String bindCanal,String bankCode);

	/**
	 * 
	 * @description 查询银行卡
	 * @param custId
	 * @param accountType
	 * @return List<BankCard>
	 * @author zhanglei
	 * @date 2012-10-30
	 */
	public List<CustBankcard> getListByBankcard( String custId, CustSubject accountType );

	/**
	 * 
	 * @description 删除客户银行卡门户（状态）
	 * @param id
	 * @param custId
	 * @author zhanglei
	 * @date 2012-10-30
	 */
	public void delBankcard( String id, String custId );

	/**
	 * 
	 * @description 校验客户授权码
	 * @param custId
	 *            客户ID
	 * @param bankCardNo
	 *            银行卡号
	 * @param bankAuthCode
	 *            授权码
	 * @return boolean
	 * @author YangXiaojin
	 * @date 2012-10-30
	 */
	public CustBankcard getBankAuthCode( String custId, String bankCardNo, String bankAuthCode );

	/**
	 * 
	 * @description 查询银行卡
	 * @param id
	 * @param bankAuthCode
	 * @param cardNo
	 * @return CustBankcard
	 * @author zhanglei
	 * @date 2012-10-30
	 */
	public CustBankcard getCustBankcard( String id, String cardNo );

	/**
	 * 快捷绑定.
	 * 
	 * @author 张磊
	 * @date 2012-11-9
	 * @param bankCardId
	 * @param success
	 */
	public void bind( String bankCardId );
	
	/**
	 * 快捷绑定.
	 * 
	 * @author feng.li
	 * @date 2013-06-26
	 * @param bankCardId
	 * @param authCode
	 */
	public void bind( String bankCardId, String authCode);

	/**
	 * 快捷解绑.
	 * 
	 * @author 张磊
	 * @date 2012-11-9
	 * @param bankCardId
	 * @param success
	 */
	public void unbind( String bankCardId );

	/**
	 * 基金支付查询银行卡.
	 * 
	 * @author 张磊
	 * @date 2012-11-9
	 * @param custId
	 * @param bankNo
	 * @param createType
	 * @param amount
	 * @return bankAuthCode,bankType
	 */
	public CustBankcard getBankCardByFund( String custId, String bankNo, String bankType,String isProtocol,String createType, String amount );

	/**
	 * 慧投支付查询银行卡.
	 * 
	 * @author 张磊
	 * @date 2012-11-9
	 * @param custId
	 * @param bankNo
	 * @param createType
	 * @param amount
	 * @return bankAuthCode,bankType
	 */
	public CustBankcard getBankCardByInvest( String custId, String bankNo, String bankType,String isProtocol,String createType, String amount );

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
	public List<CustBankcard> getBankCardByFundGateway(String cardNo,String newCardType, String isCert, String bindCanal);
	
	/**
	 * 查询银行卡.(门户)
	 * 
	 * @author 张磊
	 * @date 2012-11-9
	 * @param bankAuthCode
	 * @return BankCard
	 */
	public CustBankcard getBankCardByBankAuthCode( String bankAuthCode ) throws MpCheckedException;

	/**
	 * 
	 * @description 删除客户银行卡基金（状态）
	 * @param custId
	 * @author zhanglei
	 * @date 2012-10-30
	 */
	public void delCard( CustBankcard custBankcard );

	/**
	 * 
	 * @description 查询客户银行卡（基金）
	 * @param custId
	 * @author zhanglei
	 * @date 2012-10-30
	 */
	public boolean getBankCardFund( BankCard bancard );

	/**
	 * 标记删除
	 */
	public void markForDelete( String id );

	/**
	 * 标记删除
	 */
	public void markForDelete( BankCard bancard );

	/**
	 * 客户银行卡是否存在(门户)
	 */
	public boolean isExists( CustSubject accType, String custId, String bankType, String cardName, String cardNo, String bindCanal );

	/**
	 * 关闭快捷支付
	 * 
	 * @param id
	 * @param isProtocol
	 */
	public void close( String id, String custId );
	
	/**
	 * 条件查询.
	 * 
	 * @author 潘瑞峥
	 * @date 2012-12-19
	 * @param termList
	 * @return
	 */
	public List<CustBankcard> findList( List<Term> termList );

	/**
	 * 后台管理员绑定银行卡.
	 * 
	 * @author 潘瑞峥
	 * @date 2012-12-24
	 * @param custId
	 * @param entity
	 */
	public void bindingForAdmin( String custId, CustBankcard entity );

	/**
	 * 后台管理员复核绑定的银行卡.
	 * 
	 * @author 潘瑞峥
	 * @date 2012-12-25
	 * @param id
	 * @param fromStatus
	 * @param toStatus
	 */
	public void auditForAdmin( String id, AuditStatus fromStatus, AuditStatus toStatus );

	/**
	 * 根据id查询银行卡.
	 * 
	 * @author 潘瑞峥
	 * @date 2013-01-19
	 * @param id
	 */
	public CustBankcard findById( String id );

	/**
	 * 后台管理员解绑银行卡.
	 * 
	 * @param id
	 */
	public void unbindingForAdmin( String id );
	//TODO
	/**
	 * 投资者备案
	 */
	public List<CustBankcard> listRecord( Date start, Date end );

	/**
	 * 根据custId和cardNo查询有效的银行卡信息.
	 * 
	 * @author 潘瑞峥
	 * @date 2013-03-20
	 * @param custId
	 * @param cardNo
	 * @param bindCanal
	 * @return
	 */
	public CustBankcard findByCustidAndCardno( String custId, String cardNo, String bindCanal );
	
	/**
	 * 查询银行卡（门户）
	 * @author yanghan
	 * @param custId
	 * @param custNo
	 * @param bankType
	 * @param createType
	 * @return
	 */
	public CustBankcard getBankCardByPortal(String custId,String custNo,BankType bankType,String createType,CustSubject custSubject,String bindCanal);
	/**
	 * 根据企业编号和账户类型查询银行卡（门户）
	 * @param custId 企业客户编号
	 * @param custSubject 账户类型
	 * @return 客户银行卡
	 * @throws MpCheckedException
	 */
	public CustBankcard getBankCardByCustIdAtPortal(String custId,CustSubject custSubject,String bindCanal);
	
	/**
	 * 门户为企业客户保存银行卡
	 * @param bancard
	 */
	public CustBankcard saveByPortalForEnt(BankCard bancard);
	
	/**
	 *企业客户变更银行卡
	 */
	public CustBankcard changeByPortalForEnt(BankCard bankcard);
	/**银行卡查询
	 * 
	 * @description 如要返回唯一一条记录，则至少要传8大要素 
	 * @param custBankcard
	 * @return  
	 * @author yu.han
	 * @date 2014-7-7
	 */
	public CustBankcard getCustBankInfo(CustBankcard custBankcard);
	/**
	 * 银行卡查询
	 */
	public List<CustBankcard> getCustBankInfoList(CustBankcard custBankcard);
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
	public CustBankcard getCustBankInfo(String custId, String cardNO, String bankCode,String bindCanal);
	/**
	 * 通过卡号和创建类型查询协议号不为空的银行卡信息
	 * @description：
	 * @param cardNO
	 * @param createType
	 * @return  
	 * @author duyulong
	 * @date 2014-4-21
	 */
	public CustBankcard getByCardNoAndCreType(String cardNo,String createType,String bankType,String cardName);
	/**
	 * 更新协议状态和认证状态
	 * @description：
	 * @param cardId
	 * @param isProtocol
	 * @param isCert
	 * @param originalProtocol
	 * @param originalCert
	 * @return  
	 * @author duyulong
	 * @date 2014-4-23
	 */
	public void updateOriginalCardStatus(String cardId,String  isProtocol,String isCert,String  originalProtocol,String originalCert);

	/**
	 * 修改联行号
	 * @param identity
	 * @param contactBankNo
	 */
	public void modifyContactBankNo(String identity, String contactBankNo);
}