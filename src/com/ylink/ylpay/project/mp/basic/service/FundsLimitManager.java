package com.ylink.ylpay.project.mp.basic.service;

import java.util.List;

import com.google.code.lightssh.common.service.BaseManager;
import com.ylink.ylpay.project.mp.basic.entity.FundsLimit;



public interface FundsLimitManager extends BaseManager<FundsLimit>{
	
	/**
	 * 判断账户类型和交易类型是否唯一
	 */
	public boolean isUniqueAccountAndBusiness(FundsLimit fund);
	
	/**
	 * 查询全部
	 */
	public List<FundsLimit> listAll();
	
	/**
	 * 查询是否已经存在账户和交易类型
	 * @param id
	 * @return
	 */
	public FundsLimit findByAccountAndBusiness(String id, String accType,String businessId);
	
	/**
	 * 查询虚户资金限额数据
	 * @param businessId
	 * @return FundsLimit
	 */
	public FundsLimit findBBusiness(String businessId);
	

}
