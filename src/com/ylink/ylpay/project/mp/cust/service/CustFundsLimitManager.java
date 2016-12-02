package com.ylink.ylpay.project.mp.cust.service;

import java.util.List;

import com.google.code.lightssh.common.service.BaseManager;
import com.ylink.ylpay.common.project.mp.constant.OptType;
import com.ylink.ylpay.common.project.mp.exception.MpCheckedException;
import com.ylink.ylpay.project.mp.cust.entity.CustFundsLimit;

/**
 * 客户资金限额业务层.
 * 
 * @author 潘瑞峥
 * @date 2012-11-26
 */
public interface CustFundsLimitManager extends BaseManager<CustFundsLimit> {
	

	/**
	 * 保存.
	 * 
	 * @param custId
	 * @param fundsLimitId
	 */
	public void save( String custId, Long fundsLimitId );

	/**
	 * 保存.
	 * 
	 * @param custId
	 */
	public void save( String custId );
	
	/**
	 * 查询资金限额
	 * @param custId
	 * @param optType
	 * @author xuwei
	 * @return
	 */
	public List<CustFundsLimit> getList(String custId, OptType optType);
	
	/**
	 * 修改资金限额
	 * @param custId
	 * @param optType
	 * @param singleWarn
	 * @param singleLimit
	 * @param dayWarn
	 * @param dayLimit
	 * @return
	 * @throws MpCheckedException
	 */
	public List<CustFundsLimit> updateFundsLimit(String custId,
			OptType optType, Long singleWarn, Long singleLimit, Long dayWarn,
			Long dayLimit);

}