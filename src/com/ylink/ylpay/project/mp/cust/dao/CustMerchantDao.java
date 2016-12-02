package com.ylink.ylpay.project.mp.cust.dao;

import java.util.List;

import com.ylink.modules.orm.jpa.CustomJpaDao;
import com.ylink.ylpay.common.project.mp.constant.CustType;
import com.ylink.ylpay.common.project.mp.constant.MerchantStatus;
import com.ylink.ylpay.project.mp.cust.entity.CustMerchant;

/**
 * 商户DAO.
 * 
 * @author 潘瑞峥
 * @date 2012-11-1
 */
public interface CustMerchantDao extends CustomJpaDao<CustMerchant> {

	/**
	 * @description 更新用户状态
	 * @param identity
	 * @param status
	 * @return
	 * @author yu.han
	 * @date 2013-3-13
	 */
	int activateOrFreeze( String identity, MerchantStatus status );

	/**
	 * 查询除开证联融通和虚拟商户的所有商户.
	 * 
	 * @return
	 */
	public List<CustMerchant> listNotSelf();

	/**
	 * 根据企业客户编号和产品类型查找商户
	 * @param custId 企业客户编号
	 * @param custType 产品类型
	 * @return
	 */
	public CustMerchant getCustMerchantByCustIdAndCustType(String custId,CustType custType);
	
	/**
	 * 根据企业客户编号查找商户
	 * @param custId 企业客户编号
	 * @return
	 */
	public CustMerchant getCustMerchantByCustId(String custId);
}